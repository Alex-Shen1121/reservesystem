package com.reserve.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 每月的自动生成排班设置，管理员可以根据生成的信息特殊处理
 * </p>
 *
 * @author kappy
 * @since 2021-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Setting对象", description="每月的自动生成排班设置，管理员可以根据生成的信息特殊处理")
public class Setting implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String day;

    private String dayTime;

    private Integer counts;

    private Integer deptId;

    public Setting() {
    }

    public Setting( Integer userId, String day, String dayTime, Integer counts) {
        this.userId = userId;
        this.day = day;
        this.dayTime = dayTime;
        this.counts = counts;
    }
}
