package com.xixi.mall.platform.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xixi.mall.common.core.handle.ImgJsonSerializerHandle;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysUserSimpleVo {

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

    private Integer isAdmin;

}
