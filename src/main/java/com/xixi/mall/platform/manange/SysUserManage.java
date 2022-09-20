package com.xixi.mall.platform.manange;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xixi.mall.common.cache.constant.CacheNames;
import com.xixi.mall.platform.entity.SysUserEntity;
import com.xixi.mall.platform.mapper.SysUserMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SysUserManage {

    @Resource
    private SysUserMapper sysUserMapper;

    @Cacheable(cacheNames = CacheNames.PLATFORM_SIMPLE_INFO_KEY, key = "#userId")
    public SysUserEntity getSysUserByUserId(Long userId) {
        return sysUserMapper.selectOne(
                Wrappers.<SysUserEntity>lambdaQuery()
                        .eq(SysUserEntity::getSysUserId, userId)
        );
    }

}
