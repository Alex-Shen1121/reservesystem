package com.reserve.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.reserve.dao.OrderInfoMapper;
import com.reserve.model.OrderInfo;
import com.reserve.service.IOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 病人的预约记录 服务实现类
 * </p>
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {
   @Autowired
   private OrderInfoMapper orderInfoDao;

    @Override
    public PageInfo<OrderInfo> findOrderInfoAll(int page, int limit, OrderInfo orderInfo) {
        PageHelper.startPage(page,limit);
        List<OrderInfo> list= orderInfoDao.queryOrderInfoByAll(orderInfo);
        PageInfo<OrderInfo> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public IPage<OrderInfo> findListByPage(Integer page, Integer pageCount){
        IPage<OrderInfo> wherePage = new Page<>(page, pageCount);
        OrderInfo where = new OrderInfo();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(OrderInfo orderInfo){
        return baseMapper.insert(orderInfo);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(OrderInfo orderInfo){
        return baseMapper.updateById(orderInfo);
    }

    @Override
    public OrderInfo findById(Long id){
        return  baseMapper.selectById(id);
    }
}
