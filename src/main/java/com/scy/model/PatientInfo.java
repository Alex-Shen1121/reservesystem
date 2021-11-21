package com.scy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 病人信息表
 * </p>
 *
 * @author kappy
 * @since 2021-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("patient_info")
@ApiModel(value="PatientInfo对象", description="病人信息表")
public class PatientInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    @ApiModelProperty(value = "0 代表女 1 代表男")
    private String password;

    private String realname;

    private String sex;

    private String tel;

    private String email;

    private String jobTitle;

    private Integer status;

    private Integer sort;

    private Integer delFlag;

    private String createBy;

    private Date updateTime;

    private Date createTime;


}
