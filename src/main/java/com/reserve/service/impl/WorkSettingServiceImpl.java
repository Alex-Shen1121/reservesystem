package com.reserve.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reserve.dao.WorkSettingMapper;
import com.reserve.model.WorkSetting;
import com.reserve.model.WorkVo;
import com.reserve.service.IWorkSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * <p>
 * 医生排班的默认设置，用来初始化处理信息，主要按周1到周五排班配置，后续每月周一到周五均按照这个初始化医生上班时间 服务实现类
 * </p>
 */
@Service
public class WorkSettingServiceImpl extends ServiceImpl<WorkSettingMapper, WorkSetting> implements IWorkSettingService {
    @Autowired
    private WorkSettingMapper workSettingDao;

    @Override
    public void deleteWorkSettingByUserIdAndMonth(Integer userId, String month) {
       workSettingDao.deleteWorkSettingByUserIdAndMonth(userId,month);
    }

    @Override
    public List<WorkVo> findListByUserId(Integer userId, Integer month, Integer year) {
        if(month==null){//如果没有年份月份 默认使用当前年月
            Calendar cal=Calendar.getInstance();
            month=cal.get(Calendar.MONTH)+1;
            year=cal.get(Calendar.YEAR);
        }
        List<WorkVo> list=workSettingDao.findListByUserId(userId,month,year);
        for(WorkVo vo:list){
            if(vo.getDayTime().equals("am")){
                vo.setDayTime("上午：");
            }else{
                vo.setDayTime("下午：");
            }
        }
        return list;
    }

    @Override
    public List<WorkSetting> findListByUserIdAndDay(Integer userId, String day) {
        return workSettingDao.findListByUserIdAndDay(userId,day);
    }

    @Override
    public IPage<WorkSetting> findListByPage(Integer page, Integer pageCount){
        IPage<WorkSetting> wherePage = new Page<>(page, pageCount);
        WorkSetting where = new WorkSetting();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }




    @Override
    public int add(WorkSetting workSetting){
        return baseMapper.insert(workSetting);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(WorkSetting workSetting){
        return baseMapper.updateById(workSetting);
    }

    @Override
    public WorkSetting findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public Integer getUserOrderByIdAndDayAndDayTime(Integer userId, String day, String dayTime) {
        return workSettingDao.getUserOrderByIdAndDayAndDayTime(userId,day,dayTime);
    }
}
