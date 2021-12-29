package com.reserve.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reserve.model.TongJi;
import com.reserve.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 */
@Component("userDao")
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名密码类型判断是否存在
     */
    public User findUserByNameAndPwd(User user);

    List<User> queryUserAll(User user);

    List<TongJi> queryTongjiCounts();

}
