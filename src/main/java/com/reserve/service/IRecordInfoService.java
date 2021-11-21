package com.reserve.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.reserve.model.RecordInfo;

import java.util.List;

/**
 * <p>
 * 就诊记录信息 服务类
 * </p>
 *
 * @author kappy
 * @since 2021-02-02
 */
public interface IRecordInfoService extends IService<RecordInfo> {

    /**
     *  根据患者id获取患者的就诊记录
     */
    List<RecordInfo> queryRecordInfoByPatientId(Integer patientId);


    /**
     * 查询就诊记录信息分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<RecordInfo>
     */
    IPage<RecordInfo> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加就诊记录信息
     *
     * @param recordInfo 就诊记录信息
     * @return int
     */
    int add(RecordInfo recordInfo);

    /**
     * 删除就诊记录信息
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改就诊记录信息
     *
     * @param recordInfo 就诊记录信息
     * @return int
     */
    int updateData(RecordInfo recordInfo);

    /**
     * id查询数据
     *
     * @param id id
     * @return RecordInfo
     */
    RecordInfo findById(Long id);
}
