package com.xixi.mall.platform.controller;

import com.xixi.mall.common.core.webbase.vo.BasePageReqBodyVo;
import com.xixi.mall.common.core.webbase.vo.BasePageRespBodyVo;
import com.xixi.mall.common.core.webbase.vo.ServerResponse;
import com.xixi.mall.platform.service.web.SysUserService;
import com.xixi.mall.platform.vo.SysUserSimpleVo;
import com.xixi.mall.platform.vo.SysUserVo;
import com.xixi.mall.platform.vo.request.SysUserAddEditReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Api(tags = "平台用户信息")
@RequestMapping(value = "/sysUser")
public class SysUserController {

    @Resource
    private SysUserService service;

    @GetMapping("/info")
    @ApiOperation(value = "登陆平台用户信息", notes = "获取当前登陆平台用户的用户信息")
    public ServerResponse<SysUserSimpleVo> info() {
        return ServerResponse.success(service.info());
    }

    @GetMapping("/page")
    @ApiOperation(value = "平台用户列表", notes = "获取平台用户列表")
    public ServerResponse<BasePageRespBodyVo<SysUserVo>> page(@Valid BasePageReqBodyVo<String> req) {
        return ServerResponse.success(service.page(req));
    }

    @GetMapping
    @ApiOperation(value = "获取平台用户信息", notes = "根据用户id获取平台用户信息")
    public ServerResponse<SysUserVo> detail(@RequestParam Long sysUserId) {
        return ServerResponse.success(service.getByUserId(sysUserId));
    }

    @PostMapping
    @ApiOperation(value = "保存平台用户信息", notes = "保存平台用户信息")
    public ServerResponse<Void> save(@Valid @RequestBody SysUserAddEditReq req) {
        service.save(req);
        return ServerResponse.success();
    }

    @PutMapping
    @ApiOperation(value = "更新平台用户信息", notes = "更新平台用户信息")
    public ServerResponse<Void> update(@Valid @RequestBody SysUserAddEditReq req) {
        service.update(req);
        return ServerResponse.success();
    }

    @DeleteMapping
    @ApiOperation(value = "删除平台用户信息", notes = "根据平台用户id删除平台用户信息")
    public ServerResponse<Void> delete(@RequestParam Long sysUserId) {
        service.deleteById(sysUserId);
        return ServerResponse.success();
    }
}
