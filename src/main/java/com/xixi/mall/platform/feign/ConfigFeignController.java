package com.xixi.mall.platform.feign;

import com.xixi.mall.api.platform.feign.ConfigFeignClient;
import com.xixi.mall.common.core.webbase.vo.ServerResponse;
import com.xixi.mall.platform.service.feign.ConfigFeignService;
import com.xixi.mall.platform.service.web.SysConfigService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ConfigFeignController implements ConfigFeignClient {

    @Resource
    private ConfigFeignService service;

    @Override
    public ServerResponse<String> getConfig(String key) {
        return ServerResponse.success(service.getConfig(key));
    }
}
