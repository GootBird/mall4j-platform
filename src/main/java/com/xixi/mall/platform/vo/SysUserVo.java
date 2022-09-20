package com.xixi.mall.platform.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xixi.mall.common.core.handle.ImgJsonSerializerHandle;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class SysUserVo {

    /**
     * sysUserId
     */
    @ApiModelProperty("平台用户id")
    private Long sysUserId;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickName;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    @JsonSerialize(using = ImgJsonSerializerHandle.class)
    private String avatar;

    /**
     * 员工编号
     */
    @ApiModelProperty("员工编号")
    private String code;

    /**
     * 联系方式
     */
    @ApiModelProperty("联系方式")
    private String phoneNum;

    @ApiModelProperty("是否已经有账号了")
    private Integer hasAccount;

    @ApiModelProperty("平台id")
    private Long shopId;

    @ApiModelProperty("角色id列表")
    private List<Long> roleIds;

}
