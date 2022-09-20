package com.xixi.mall.platform.service.web;

import com.xixi.mall.api.auth.bo.UserInfoInTokenBo;
import com.xixi.mall.api.auth.dto.AuthAccountDto;
import com.xixi.mall.api.auth.feign.AccountFeignClient;
import com.xixi.mall.api.auth.vo.AuthAccountVo;
import com.xixi.mall.common.core.utils.IpHelper;
import com.xixi.mall.common.core.utils.ThrowUtils;
import com.xixi.mall.common.security.context.AuthUserContext;
import com.xixi.mall.platform.entity.SysUserEntity;
import com.xixi.mall.platform.manange.SysUserManage;
import com.xixi.mall.platform.mapper.SysUserMapper;
import com.xixi.mall.platform.vo.request.UserAddEditReq;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class SysUserAccountService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserManage sysUserManage;

    @Resource
    private AccountFeignClient accountFeignClient;

    public AuthAccountVo getByUserIdAndSysType(Long userId, Integer sysType) {
        return ThrowUtils.checkResAndGetData(accountFeignClient.getById(userId, sysType));
    }

    private AuthAccountDto getAuthAccount(UserAddEditReq req) {
        AuthAccountDto newDto = new AuthAccountDto();
        UserInfoInTokenBo curUser = AuthUserContext.get();
        newDto.setPassword(req.getPassword());
        newDto.setUsername(req.getUsername());
        newDto.setStatus(req.getStatus());
        newDto.setSysType(curUser.getSysType());
        newDto.setTenantId(curUser.getTenantId());
        newDto.setUserId(req.getUserId());
        return newDto;
    }

    @Transactional(rollbackFor = Exception.class)
    @GlobalTransactional(rollbackFor = Exception.class)
    public void addAccount(UserAddEditReq req) {

        Long sysUserId = req.getUserId();
        SysUserEntity sysUserEntity = sysUserManage.getSysUserByUserId(sysUserId);

        if (Objects.isNull(sysUserEntity)) {
            ThrowUtils.throwErr("无法获取账户信息");
        }

        if (Objects.equals(sysUserEntity.getHasAccount(), 1)) {
            ThrowUtils.throwErr("已有账号，无需重复添加");
        }

        AuthAccountDto newAccount = getAuthAccount(req);

        newAccount.setCreateIp(IpHelper.getIpAddr());
        newAccount.setIsAdmin(0);

        ThrowUtils.checkResAndGet(accountFeignClient.save(newAccount));

        SysUserEntity sysUser = new SysUserEntity();
        sysUser.setSysUserId(req.getUserId());
        sysUser.setHasAccount(1);

        sysUserMapper.updateById(sysUser);
    }

    public void updateAccount(UserAddEditReq req) {

        SysUserEntity sysUserEntity = sysUserManage.getSysUserByUserId(req.getUserId());

        if (Objects.isNull(sysUserEntity) || Objects.equals(sysUserEntity.getHasAccount(), 0)) {
            ThrowUtils.throwErr("无法获取账户信息");
        }

        AuthAccountDto newAccount = getAuthAccount(req);
        ThrowUtils.checkResAndGet(accountFeignClient.update(newAccount));
    }
}
