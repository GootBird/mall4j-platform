package com.xixi.mall.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xixi.mall.common.core.webbase.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 系统配置信息表
 */
@Getter
@Setter
@TableName("sys_config")
public class SysConfigEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Long id;

    /**
     * key
     */
    private String paramKey;

    /**
     * value
     */
    private String paramValue;

    /**
     * 备注
     */
    private String remark;

}
