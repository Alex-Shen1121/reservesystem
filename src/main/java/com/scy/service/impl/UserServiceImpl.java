package com.scy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scy.dao.UserMapper;
import com.scy.model.TongJi;
import com.scy.model.User;
import com.scy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author kappy
 * @since 2021-02-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userDao;
    @Override
    public IPage<User> findListByPage(Integer page, Integer pageCount){
        IPage<User> wherePage = new Page<>(page, pageCount);
        User where = new User();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(User user){
        return baseMapper.insert(user);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(User user){
        return baseMapper.updateById(user);
    }

    @Override
    public User findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public User findUserByNameAndPwd(User user) {
        return userDao.findUserByNameAndPwd(user);
    }

    @Override
    public PageInfo<User> findUserAll(int page, int pageSize, User user) {
        PageHelper.startPage(page,pageSize);
        List<User> list=userDao.queryUserAll(user);
        PageInfo<User> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<TongJi> queryTongjiCounts() {
        return userDao.queryTongjiCounts();
    }
}
