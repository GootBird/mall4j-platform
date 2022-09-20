package com.xixi.mall.platform.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 系统配置信息表DTO
 */
public class SysConfigDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("")
    private Long id;

    @ApiModelProperty("key")
    private String paramKey;

    @ApiModelProperty("value")
    private String paramValue;

    @ApiModelProperty("备注")
    private String remark;

}
