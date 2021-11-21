package com.scy;

import com.scy.model.User;
import com.scy.service.IUserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("com.scy.dao")
class ReservesystemApplicationTests {

    @Autowired
    private IUserService userService;
    @Test
    void contextLoads() {
//        List<User> list= userService.list();
//        for(User user:list){
//            System.out.println(user);
//        }

        //添加用户
        User user=new User();
        user.setId(10);
        user.setUsername("admin");
        user.setPassword("123");
        userService.save(user);

//        User user=new User();
//        user.setId(10);
//        user.setUsername("admin2");
//        user.setPassword("1232");
//        userService.updateById(user);

         userService.removeById(10);
    }


}
