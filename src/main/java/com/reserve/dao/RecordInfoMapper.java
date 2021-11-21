package com.reserve.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reserve.model.RecordInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 就诊记录信息 Mapper 接口
 * </p>
 *
 * @author kappy
 * @since 2021-02-02
 */
@Component("recordInfoDao")
public interface RecordInfoMapper extends BaseMapper<RecordInfo> {

    /**
     *  根据患者id获取患者的就诊记录
     */
    List<RecordInfo> queryRecordInfoByPatientId(@Param("patientId") Integer patientId);

}
