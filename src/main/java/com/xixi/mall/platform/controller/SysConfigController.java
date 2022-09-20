package com.xixi.mall.platform.controller;

import com.xixi.mall.common.core.webbase.vo.ServerResponse;
import com.xixi.mall.platform.service.web.SysConfigService;
import com.xixi.mall.platform.vo.request.SysConfigAddEditReq;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 支付配置、文件上传配置、短信配置、快递配置、小程序配置、公众号配置
 */
@RestController
@RequestMapping("/sysConfig")
public class SysConfigController {


    @Resource
    private SysConfigService service;

    /**
     * 获取保存支付宝支付配置信息
     */
    @GetMapping("/info/{key}")
    public ServerResponse<String> info(@PathVariable("key") String key) {
        return ServerResponse.success(service.getValue(key));
    }

    /**
     * 保存配置
     */
    @PostMapping("/save")
    public ServerResponse<Void> save(@RequestBody @Valid SysConfigAddEditReq req) {
        service.save(req);
        return ServerResponse.success();
    }


}
