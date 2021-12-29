package com.reserve.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reserve.model.Exchange;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 调休记录信息  Mapper 接口
 * </p>
 */
@Component("exchangeDao")
public interface ExchangeMapper extends BaseMapper<Exchange> {

    /**
     * 查询所有的调休列表
     */
    List<Exchange> queryExchangeAll(Exchange exchange);

}
