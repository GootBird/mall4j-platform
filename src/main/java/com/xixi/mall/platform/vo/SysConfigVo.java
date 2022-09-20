package com.xixi.mall.platform.vo;

import com.xixi.mall.common.core.webbase.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统配置信息表VO
 */
@Getter
@Setter
@ToString
public class SysConfigVo extends BaseVo {
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
