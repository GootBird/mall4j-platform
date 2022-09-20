package com.xixi.mall.platform.service.web;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xixi.mall.api.auth.bo.UserInfoInTokenBo;
import com.xixi.mall.api.auth.feign.AccountFeignClient;
import com.xixi.mall.api.rabc.dto.UserRoleDto;
import com.xixi.mall.api.rabc.feign.UserRoleFeignClient;
import com.xixi.mall.common.cache.constant.CacheNames;
import com.xixi.mall.common.core.utils.ThrowUtils;
import com.xixi.mall.common.core.webbase.vo.BasePageReqBodyVo;
import com.xixi.mall.common.core.webbase.vo.BasePageRespBodyVo;
import com.xixi.mall.common.database.utils.PageObjConvertUtil;
import com.xixi.mall.common.security.context.AuthUserContext;
import com.xixi.mall.platform.entity.SysUserEntity;
import com.xixi.mall.platform.manange.SysUserManage;
import com.xixi.mall.platform.mapper.SysUserMapper;
import com.xixi.mall.platform.vo.SysUserSimpleVo;
import com.xixi.mall.platform.vo.SysUserVo;
import com.xixi.mall.platform.vo.request.SysUserAddEditReq;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private AccountFeignClient accountFeignClient;

    @Resource
    private UserRoleFeignClient userRoleFeignClient;

    @Resource
    private SysUserManage sysUserManage;


    public SysUserVo getByUserId(Long userId) {
        SysUserVo sysUser = BeanUtil.toBean(sysUserManage.getSysUserByUserId(userId), SysUserVo.class);
        sysUser.setRoleIds(ThrowUtils.checkResAndGetData(userRoleFeignClient.getRoleIds(sysUser.getSysUserId())));
        return sysUser;
    }


    @GlobalTransactional(rollbackFor = Exception.class)
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(cacheNames = CacheNames.PLATFORM_SIMPLE_INFO_KEY, key = "#sysUserId")
    public void deleteById(Long sysUserId) {
        accountFeignClient.deleteById(sysUserId);
        userRoleFeignClient.deleteByUserIdAndSysType(sysUserId);
        sysUserMapper.deleteById(sysUserId);
    }

    public SysUserSimpleVo info() {
        UserInfoInTokenBo curUser = AuthUserContext.get();

        SysUserSimpleVo userSimpleVo = BeanUtil.toBean(
                sysUserManage.getSysUserByUserId(curUser.getUserId()),
                SysUserSimpleVo.class
        );

        userSimpleVo.setIsAdmin(curUser.getIsAdmin());
        return userSimpleVo;
    }

    public BasePageRespBodyVo<SysUserVo> page(BasePageReqBodyVo<String> req) {

        String nikeName = req.getParam();

        IPage<SysUserEntity> queryPage = PageObjConvertUtil.getIPageByPageVo(req.getPageVo());

        IPage<SysUserEntity> resPage = sysUserMapper.selectPage(
                queryPage,
                Wrappers.<SysUserEntity>lambdaQuery()
                        .like(StringUtils.isNotBlank(nikeName), SysUserEntity::getNickName, nikeName)
                        .orderByDesc(SysUserEntity::getSysUserId)
        );

        BasePageRespBodyVo<SysUserVo> respBodyVo = new BasePageRespBodyVo<>();

        respBodyVo.setPageInfo(PageObjConvertUtil.getPageVoByIPage(resPage));
        respBodyVo.setResult(
                resPage.getRecords().stream()
                        .map(sysUser -> BeanUtil.toBean(sysUser, SysUserVo.class))
                        .collect(Collectors.toList())
        );

        return respBodyVo;
    }

    @GlobalTransactional(rollbackFor = Exception.class)
    @Transactional(rollbackFor = Exception.class)
    public void save(SysUserAddEditReq req) {
        List<Long> roleIds = req.getRoleIds();

        SysUserEntity newSysUser = BeanUtil.toBean(req, SysUserEntity.class);

        newSysUser.setSysUserId(null);
        newSysUser.setHasAccount(0);
        sysUserMapper.insert(newSysUser);

        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setRoleIds(roleIds);
        userRoleDto.setUserId(newSysUser.getSysUserId());
        userRoleFeignClient.saveByUserIdAndSysType(userRoleDto);
    }

    @GlobalTransactional(rollbackFor = Exception.class)
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(cacheNames = CacheNames.PLATFORM_SIMPLE_INFO_KEY, key = "#req.sysUserId")
    public void update(SysUserAddEditReq req) {
        SysUserEntity sysUser = BeanUtil.toBean(req, SysUserEntity.class);

        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setRoleIds(req.getRoleIds());
        userRoleDto.setUserId(sysUser.getSysUserId());

        sysUserMapper.updateById(sysUser);
        userRoleFeignClient.updateByUserIdAndSysType(userRoleDto);
    }
}
