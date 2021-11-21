package com.scy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scy.dao.SettingMapper;
import com.scy.model.Setting;
import com.scy.service.ISettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 每月的自动生成排班设置，管理员可以根据生成的信息特殊处理 服务实现类
 * </p>
 *
 * @author kappy
 * @since 2021-02-02
 */
@Service
public class SettingServiceImpl extends ServiceImpl<SettingMapper, Setting> implements ISettingService {
   @Autowired
   private SettingMapper settingDao;

    @Override
    public List<Setting> querySettingListByUserId(Integer userId) {
        return settingDao.querySettingListByUserId(userId);
    }

    @Override
    public int deleteByUserId(Integer userId) {
        return settingDao.deleteByUserId(userId);
    }

    @Override
    public Integer getCountsBySet(Integer userId, String day, String dayTime) {
        Setting setting=new Setting();
        setting.setDay(day);
        setting.setUserId(userId);
        setting.setDayTime(dayTime);
        return settingDao.getCountsBySet(setting);
    }


    @Override
    public IPage<Setting> findListByPage(Integer page, Integer pageCount){
        IPage<Setting> wherePage = new Page<>(page, pageCount);
        Setting where = new Setting();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Setting setting){
        return baseMapper.insert(setting);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Setting setting){
        return baseMapper.updateById(setting);
    }

    @Override
    public Setting findById(Long id){
        return  baseMapper.selectById(id);
    }
}
