package com.xixi.mall.platform.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Setter
@Getter
@ToString
public class SysUserAddEditReq {

    @ApiModelProperty("平台用户id")
    private Long sysUserId;

    @NotBlank(message = "昵称不能为空")
    @ApiModelProperty("昵称")
    private String nickName;

    @NotBlank(message = "头像不能为空")
    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("员工编号")
    private String code;

    @ApiModelProperty("联系方式")
    private String phoneNum;

    @ApiModelProperty("角色id列表")
    private List<Long> roleIds;


}
