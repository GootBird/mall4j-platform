package com.xixi.mall.platform.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ChangeAccountDto {

    @NotNull(message = "userId not null")
    @ApiModelProperty("用户id")
    private Long userId;

    @NotBlank(message = "username not blank")
    @ApiModelProperty("用户名")
    private String username;

    @NotBlank(message = "password not blank")
    @ApiModelProperty("密码")
    private String password;

    @NotNull(message = "status not null")
    @ApiModelProperty("状态 1启用 0禁用")
    private Integer status;

}
