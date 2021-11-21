package com.reserve.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reserve.model.OrderInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 病人的预约记录 Mapper 接口
 * </p>
 *
 * @author kappy
 * @since 2021-02-02
 */
@Component("orderInfoDao")
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {
    /**
     * 查询预约记录信息
     */
    List<OrderInfo> queryOrderInfoByAll(OrderInfo orderInfo);
}
