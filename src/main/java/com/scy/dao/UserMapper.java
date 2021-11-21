package com.scy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scy.model.TongJi;
import com.scy.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author kappy
 * @since 2021-02-02
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
