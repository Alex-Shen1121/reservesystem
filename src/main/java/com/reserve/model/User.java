package com.reserve.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户信息表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="用户信息表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Dept dept;

    private String username;

    private String password;

    private Integer deptId;

    private String roleName;

    private String images;

    private String realname;

    private String sex;

    private String tel;

    private String email;

    private String avatar;

    private String jobTitle;

    private Integer status;

    private String info;

    private Integer sort;

    private Integer delFlag;

    private String createBy;

    private Date updateTime;

    private Date createTime;




}
