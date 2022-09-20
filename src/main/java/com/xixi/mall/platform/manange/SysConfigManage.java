package com.xixi.mall.platform.manange;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.generator.SnowflakeGenerator;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xixi.mall.common.cache.constant.CacheNames;
import com.xixi.mall.platform.entity.SysConfigEntity;
import com.xixi.mall.platform.mapper.SysConfigMapper;
import com.xixi.mall.platform.vo.request.SysConfigAddEditReq;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class SysConfigManage {

    @Resource
    private SysConfigMapper sysConfigMapper;

    private final SnowflakeGenerator snowflakeGenerator = new SnowflakeGenerator();

    @Cacheable(cacheNames = CacheNames.SYS_CONFIG, key = "#key")
    public SysConfigEntity getByKey(String key) {
        return sysConfigMapper.selectOne(
                Wrappers.<SysConfigEntity>lambdaQuery()
                        .eq(SysConfigEntity::getParamKey, key)
        );
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = CacheNames.SYS_CONFIG_OBJECT, key = "#req.paramKey"),
            @CacheEvict(cacheNames = CacheNames.SYS_CONFIG, key = "#req.paramKey")
    })
    public void saveOrUpdateSysConfig(SysConfigAddEditReq req) {
        int count = sysConfigMapper.selectCount(
                Wrappers.<SysConfigEntity>lambdaQuery()
                        .eq(SysConfigEntity::getParamKey, req.getParamKey())
        );
        Date now = new Date();
        if (count > 0) {
            sysConfigMapper.update(
                    null,
                    Wrappers.<SysConfigEntity>lambdaUpdate()
                            .set(SysConfigEntity::getParamValue, req.getParamValue())
                            .set(SysConfigEntity::getRemark, req.getRemark())
                            .set(SysConfigEntity::getUpdateTime, now)
                            .eq(SysConfigEntity::getParamKey, req.getParamKey())
            );
            return;
        }
        SysConfigEntity newEntity = BeanUtil.toBean(req, SysConfigEntity.class);
        newEntity.setId(snowflakeGenerator.next());
        newEntity.setUpdateTime(now);
        newEntity.setCreateTime(now);
        sysConfigMapper.insert(newEntity);
    }
}
