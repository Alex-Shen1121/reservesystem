package com.reserve.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 就诊记录信息
 * </p>
 *
 * @author kappy
 * @since 2021-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RecordInfo对象", description="就诊记录信息")
public class RecordInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer patientId;
    @JsonFormat(locale = "zh",timezone = "GMT+8" ,pattern = "yyyy-MM-dd")
    private Date day;
    @JsonFormat(locale = "zh",timezone = "GMT+8" ,pattern = "yyyy-MM-dd")
    private Date dayTime;

    private String descInfo;

    private String recipelInfo;

    private String remarks;

    private Integer status;


}
