package com.reserve.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.reserve.dao.ExchangeMapper;
import com.reserve.model.Exchange;
import com.reserve.service.IExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 调休记录信息  服务实现类
 * </p>
 *
 * @author kappy
 * @since 2021-02-02
 */
@Service
public class ExchangeServiceImpl extends ServiceImpl<ExchangeMapper, Exchange> implements IExchangeService {

    @Autowired
    private ExchangeMapper exchangeDao;
    @Override
    public PageInfo<Exchange> queryExchangeAll(int page, int limit, Exchange exchange) {
        PageHelper.startPage(page,limit);
        List<Exchange> list=exchangeDao.queryExchangeAll(exchange);
        PageInfo<Exchange> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public IPage<Exchange> findListByPage(Integer page, Integer pageCount){
        IPage<Exchange> wherePage = new Page<>(page, pageCount);
        Exchange where = new Exchange();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Exchange exchange){
        return baseMapper.insert(exchange);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Exchange exchange){
        return baseMapper.updateById(exchange);
    }

    @Override
    public Exchange findById(Long id){
        return  baseMapper.selectById(id);
    }
}
