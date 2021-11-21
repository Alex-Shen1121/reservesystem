package com.scy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scy.model.Setting;

import java.util.List;

/**
 * <p>
 * 每月的自动生成排班设置，管理员可以根据生成的信息特殊处理 服务类
 * </p>
 *
 * @author kappy
 * @since 2021-02-02
 */
public interface ISettingService extends IService<Setting> {

    /**
     * 根据用户id 获取默认的设置信息
     */
    List<Setting> querySettingListByUserId(Integer userId);

    //根据用户id删除排班设置信息
    int deleteByUserId( Integer userId);

    /**
     * 根据用户id  时间  时间段 获取它的 数据信息
     */
    Integer getCountsBySet(Integer userId,String day,String dayTime);


    /**
     * 查询每月的自动生成排班设置，管理员可以根据生成的信息特殊处理分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Setting>
     */
    IPage<Setting> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加每月的自动生成排班设置，管理员可以根据生成的信息特殊处理
     *
     * @param setting 每月的自动生成排班设置，管理员可以根据生成的信息特殊处理
     * @return int
     */
    int add(Setting setting);

    /**
     * 删除每月的自动生成排班设置，管理员可以根据生成的信息特殊处理
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改每月的自动生成排班设置，管理员可以根据生成的信息特殊处理
     *
     * @param setting 每月的自动生成排班设置，管理员可以根据生成的信息特殊处理
     * @return int
     */
    int updateData(Setting setting);

    /**
     * id查询数据
     *
     * @param id id
     * @return Setting
     */
    Setting findById(Long id);
}
