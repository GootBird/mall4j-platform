package com.xixi.mall.platform.service.feign;

import com.xixi.mall.platform.entity.SysConfigEntity;
import com.xixi.mall.platform.manange.SysConfigManage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class ConfigFeignService {

    @Resource
    private SysConfigManage sysConfigManage;

    public String getConfig(String key) {
        return Optional.ofNullable(sysConfigManage.getByKey(key))
                .map(SysConfigEntity::getParamValue)
                .orElse(null);
    }
}
