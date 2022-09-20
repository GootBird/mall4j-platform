package com.xixi.mall.platform.service.web;

import com.xixi.mall.platform.entity.SysConfigEntity;
import com.xixi.mall.platform.manange.SysConfigManage;
import com.xixi.mall.platform.mapper.SysConfigMapper;
import com.xixi.mall.platform.vo.request.SysConfigAddEditReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * 系统配置信息表
 */
@Service
public class SysConfigService {

    @Resource
    private SysConfigMapper sysConfigMapper;

    @Resource
    private SysConfigManage sysConfigManage;

    public String getValue(String key) {
        return Optional.ofNullable(sysConfigManage.getByKey(key))
                .map(SysConfigEntity::getParamValue)
                .orElse(null);
    }

    public void save(SysConfigAddEditReq req) {
        sysConfigManage.saveOrUpdateSysConfig(req);
    }

}
