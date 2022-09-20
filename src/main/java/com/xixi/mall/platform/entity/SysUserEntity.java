package com.xixi.mall.platform.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xixi.mall.common.core.webbase.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 平台用户
 */
@Getter
@Setter
@ToString
@TableName("sys_user")
public class SysUserEntity extends BaseEntity implements Serializable {

    /**
     * 平台用户id
     */
    @TableId
    private Long sysUserId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 员工编号
     */
    private String code;

    /**
     * 联系方式
     */
    private String phoneNum;

    /**
     * 是否已经设置账号
     */
    private Integer hasAccount;

}
