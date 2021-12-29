package com.reserve.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.reserve.model.TongJi;
import com.reserve.model.User;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 */
public interface IUserService extends IService<User> {

    /**
     * 查询用户信息表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<User>
     */
    IPage<User> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加用户信息表
     *
     * @param user 用户信息表
     * @return int
     */
    int add(User user);

    /**
     * 删除用户信息表
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改用户信息表
     *
     * @param user 用户信息表
     * @return int
     */
    int updateData(User user);

    /**
     * id查询数据
     *
     * @param id id
     * @return User
     */
    User findById(Long id);

    /**
     * 根据用户名密码类型判断是否存在
     */
     User findUserByNameAndPwd(User user);


     PageInfo<User> findUserAll(int page,int pageSize,User user);

     List<TongJi> queryTongjiCounts();
}
