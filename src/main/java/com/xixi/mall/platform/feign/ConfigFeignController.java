package com.xixi.mall.platform.feign;

import com.xixi.mall.api.platform.feign.ConfigFeignClient;
import com.xixi.mall.common.core.webbase.vo.ServerResponse;
import com.xixi.mall.platform.service.web.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigFeignController implements ConfigFeignClient {

    @Autowired
    private SysConfigService sysConfigService;

    @Override
    public ServerResponse<String> getConfig(String key) {
        return ServerResponse.success(sysConfigService.getValue(key));
    }
}
