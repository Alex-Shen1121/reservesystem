package com.scy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scy.dao.PatientInfoMapper;
import com.scy.model.PatientInfo;
import com.scy.service.IPatientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 病人信息表 服务实现类
 * </p>
 *
 * @author kappy
 * @since 2021-02-02
 */
@Service
public class PatientInfoServiceImpl extends ServiceImpl<PatientInfoMapper, PatientInfo> implements IPatientInfoService {

    @Autowired
    private PatientInfoMapper patientDao;

    @Override
    public PageInfo<PatientInfo> findUserAll(int page, int pageSize, PatientInfo patientInfo) {
        PageHelper.startPage(page,pageSize);
        List<PatientInfo> list=patientDao.queryAll(patientInfo);
        PageInfo<PatientInfo> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public IPage<PatientInfo> findListByPage(Integer page, Integer pageCount){
        IPage<PatientInfo> wherePage = new Page<>(page, pageCount);
        PatientInfo where = new PatientInfo();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(PatientInfo patientInfo){
        return baseMapper.insert(patientInfo);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(PatientInfo patientInfo){
        return baseMapper.updateById(patientInfo);
    }

    @Override
    public PatientInfo findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public PatientInfo queryPatByUsernameAndPassword(String username, String password) {
        return patientDao.queryPatByUsernameAndPassword(username,password);
    }

    @Override
    public PatientInfo queryPatByName(String username) {
        return patientDao.queryPatByName(username);
    }
}
