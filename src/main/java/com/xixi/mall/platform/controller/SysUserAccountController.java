package com.xixi.mall.platform.controller;

import com.xixi.mall.api.auth.vo.AuthAccountVo;
import com.xixi.mall.common.core.webbase.vo.ServerResponse;
import com.xixi.mall.common.security.context.AuthUserContext;
import com.xixi.mall.platform.service.web.SysUserAccountService;
import com.xixi.mall.platform.vo.request.UserAddEditReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/sysUser/account")
@Api(tags = "平台用户账号信息")
public class SysUserAccountController {

    @Resource
    private SysUserAccountService service;


    @GetMapping
    @ApiOperation(value = "获取账号信息", notes = "获取账号信息")
    public ServerResponse<AuthAccountVo> getAccount(Long userId) {
        return ServerResponse.success(
                service.getByUserIdAndSysType(userId, AuthUserContext.get().getSysType())
        );
    }


    @PostMapping
    @ApiOperation(value = "添加账号", notes = "添加账号")
    public ServerResponse<Void> addAccount(@Valid @RequestBody UserAddEditReq req) {
        service.addAccount(req);
        return ServerResponse.success();
    }

    @PutMapping
    @ApiOperation(value = "修改账号", notes = "修改账号")
    public ServerResponse<Void> updateAccount(@Valid @RequestBody UserAddEditReq req) {
        service.updateAccount(req);
        return ServerResponse.success();
    }
}
