package com.reserve.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reserve.model.WorkSetting;
import com.reserve.model.WorkVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 医生排班的默认设置，用来初始化处理信息，主要按周1到周五排班配置，后续每月周一到周五均按照这个初始化医生上班时间 Mapper 接口
 * </p>
 *
 * @author kappy
 * @since 2021-02-02
 */
@Component("workSettingDao")
public interface WorkSettingMapper extends BaseMapper<WorkSetting> {

    /**
     * 根据用户的id和月份删除排班记录信息
     */
     void deleteWorkSettingByUserIdAndMonth(@Param("userId") Integer userId,
                                            @Param("month") String month);

    /**
     * 根据用户id获取当前月的排班信息
     */
    List<WorkVo> findListByUserId(@Param("userId") Integer userId,
                                  @Param("month") Integer month,
                                  @Param("year") Integer year);


    /**
     * 根据用户id和时间获取当天的数据信息
     */
    List<WorkSetting> findListByUserIdAndDay(@Param("userId") Integer userId,
                                  @Param("day") String day);


    /**
     * 根据医生id  时间段  日期 获取可预约的人数
     */
    Integer getUserOrderByIdAndDayAndDayTime(@Param("userId") Integer userId,
                                             @Param("day") String day,
                                             @Param("dayTime") String dayTime);
}
