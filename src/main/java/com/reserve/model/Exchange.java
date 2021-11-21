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
 * 调休记录信息 
 * </p>
 *
 * @author kappy
 * @since 2021-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Exchange对象", description="调休记录信息 ")
public class Exchange implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;
    @JsonFormat(locale = "zh",timezone = "GMT+8" ,pattern = "yyyy-MM-dd")
    private Date startTime;
    @JsonFormat(locale = "zh",timezone = "GMT+8" ,pattern = "yyyy-MM-dd")
    private Date endTime;

    private Integer status;

    private User user;


}
