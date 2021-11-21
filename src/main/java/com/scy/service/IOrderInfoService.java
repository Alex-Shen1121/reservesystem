package com.scy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.scy.model.OrderInfo;

/**
 * <p>
 * 病人的预约记录 服务类
 * </p>
 *
 * @author kappy
 * @since 2021-02-02
 */
public interface IOrderInfoService extends IService<OrderInfo> {

    PageInfo<OrderInfo> findOrderInfoAll(int page,int limit,OrderInfo orderInfo);
    /**
     * 查询病人的预约记录分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<OrderInfo>
     */
    IPage<OrderInfo> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加病人的预约记录
     *
     * @param orderInfo 病人的预约记录
     * @return int
     */
    int add(OrderInfo orderInfo);

    /**
     * 删除病人的预约记录
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改病人的预约记录
     *
     * @param orderInfo 病人的预约记录
     * @return int
     */
    int updateData(OrderInfo orderInfo);

    /**
     * id查询数据
     *
     * @param id id
     * @return OrderInfo
     */
    OrderInfo findById(Long id);
}
