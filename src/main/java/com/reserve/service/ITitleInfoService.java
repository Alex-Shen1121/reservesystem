package com.reserve.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.reserve.model.TitleInfo;

/**
 * <p>
 * 医生职称信息表 服务类
 * </p>
 */
public interface ITitleInfoService extends IService<TitleInfo> {

    /**
     * 查询医生职称信息表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<TitleInfo>
     */
    IPage<TitleInfo> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加医生职称信息表
     *
     * @param titleInfo 医生职称信息表
     * @return int
     */
    int add(TitleInfo titleInfo);

    /**
     * 删除医生职称信息表
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改医生职称信息表
     *
     * @param titleInfo 医生职称信息表
     * @return int
     */
    int updateData(TitleInfo titleInfo);

    /**
     * id查询数据
     *
     * @param id id
     * @return TitleInfo
     */
    TitleInfo findById(Long id);
}
