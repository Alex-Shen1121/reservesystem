package com.reserve.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reserve.model.PatientInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 病人信息表 Mapper 接口
 * </p>
 */
@Component("patientDao")
public interface PatientInfoMapper extends BaseMapper<PatientInfo> {
    List<PatientInfo> queryAll(PatientInfo patientInfo);

    PatientInfo queryPatByUsernameAndPassword(@Param("username") String username,
                                              @Param("password") String password);

    PatientInfo queryPatByName(@Param("username") String username);

}
