package com.reserve.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.reserve.model.WorkSetting;
import com.reserve.model.WorkVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 医生排班的默认设置，用来初始化处理信息，主要按周1到周五排班配置，后续每月周一到周五均按照这个初始化医生上班时间 服务类
 * </p>
 */
public interface IWorkSettingService extends IService<WorkSetting> {
    /**
     * 根据用户的id和月份删除排班记录信息
     */
    void deleteWorkSettingByUserIdAndMonth( Integer userId,
                                            String month);

    /**
     * 根据用户id获取当前月的排班信息
     */
    List<WorkVo> findListByUserId(Integer userId,
                                  Integer month,
                                  Integer year);

    List<WorkSetting> findListByUserIdAndDay(@Param("userId") Integer userId,
                                             @Param("day") String day);

    /**
     * 查询医生排班的默认设置，用来初始化处理信息，主要按周1到周五排班配置，后续每月周一到周五均按照这个初始化医生上班时间分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<WorkSetting>
     */
    IPage<WorkSetting> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加医生排班的默认设置，用来初始化处理信息，主要按周1到周五排班配置，后续每月周一到周五均按照这个初始化医生上班时间
     *
     * @param workSetting 医生排班的默认设置，用来初始化处理信息，主要按周1到周五排班配置，后续每月周一到周五均按照这个初始化医生上班时间
     * @return int
     */
    int add(WorkSetting workSetting);

    /**
     * 删除医生排班的默认设置，用来初始化处理信息，主要按周1到周五排班配置，后续每月周一到周五均按照这个初始化医生上班时间
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改医生排班的默认设置，用来初始化处理信息，主要按周1到周五排班配置，后续每月周一到周五均按照这个初始化医生上班时间
     *
     * @param workSetting 医生排班的默认设置，用来初始化处理信息，主要按周1到周五排班配置，后续每月周一到周五均按照这个初始化医生上班时间
     * @return int
     */
    int updateData(WorkSetting workSetting);

    /**
     * id查询数据
     *
     * @param id id
     * @return WorkSetting
     */
    WorkSetting findById(Long id);

    /**
     * 根据医生id  时间段  日期 获取可预约的人数
     */
    Integer getUserOrderByIdAndDayAndDayTime( Integer userId,
                                              String day,
                                              String dayTime);
}
