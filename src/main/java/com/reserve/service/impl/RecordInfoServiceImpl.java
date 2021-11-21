package com.reserve.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reserve.dao.RecordInfoMapper;
import com.reserve.model.RecordInfo;
import com.reserve.service.IRecordInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 就诊记录信息 服务实现类
 * </p>
 *
 * @author kappy
 * @since 2021-02-02
 */
@Service
public class RecordInfoServiceImpl extends ServiceImpl<RecordInfoMapper, RecordInfo> implements IRecordInfoService {
    @Autowired
    private RecordInfoMapper recordInfoDao;

    @Override
    public List<RecordInfo> queryRecordInfoByPatientId(Integer patientId) {
        return recordInfoDao.queryRecordInfoByPatientId(patientId);
    }

    @Override
    public IPage<RecordInfo> findListByPage(Integer page, Integer pageCount){
        IPage<RecordInfo> wherePage = new Page<>(page, pageCount);
        RecordInfo where = new RecordInfo();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(RecordInfo recordInfo){
        return baseMapper.insert(recordInfo);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(RecordInfo recordInfo){
        return baseMapper.updateById(recordInfo);
    }

    @Override
    public RecordInfo findById(Long id){
        return  baseMapper.selectById(id);
    }
}
