package com.xixi.mall.platform.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SysConfigAddEditReq {

    @ApiModelProperty("key")
    private String paramKey;

    @ApiModelProperty("value")
    private String paramValue;

    @ApiModelProperty("备注")
    private String remark;

}
