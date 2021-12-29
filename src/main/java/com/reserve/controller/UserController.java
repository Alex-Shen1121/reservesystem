package com.reserve.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.reserve.model.TongJi;
import com.reserve.model.User;
import com.reserve.model.UserVo;
import com.reserve.service.IUserService;
import com.reserve.service.IWorkSettingService;
import com.reserve.util.JsonObject;
import com.reserve.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jwt.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 */
@Api(tags = {"用户信息表"})
@RestController
@RequestMapping("/user")
public class UserController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IUserService userService;

    @Resource
    private IWorkSettingService workSettingService;

    /**
     * 分页查询所有的用户信息
     * @param
     * @return
     */
    @RequestMapping("/queryUserAll")
    public JsonObject queryUserAll(User user,
                                 @RequestParam(defaultValue = "1")  Integer page,
                                   @RequestParam(defaultValue = "15")   Integer limit){
        JsonObject object=new JsonObject();
        PageInfo<User> pageInfo= userService.findUserAll(page,limit,user);
        object.setMsg("ok");
        object.setCode(0);
        object.setCount(pageInfo.getTotal());
        object.setData(pageInfo.getList());
        return object;

    }

    @RequestMapping("/queryUserAll2")
    public JsonObject queryUserAll2(User user, HttpServletRequest request,
                                   @RequestParam(defaultValue = "1")  Integer page,
                                   @RequestParam(defaultValue = "15")   Integer limit){
        JsonObject object=new JsonObject();

        //获取登录用户信息
        String token= request.getHeader("token");
        Integer userId= JWTUtil.getUserId(token);
        user.setId(userId);

        PageInfo<User> pageInfo= userService.findUserAll(page,limit,user);
        object.setMsg("ok");
        object.setCode(0);
        object.setCount(pageInfo.getTotal());
        object.setData(pageInfo.getList());
        return object;

    }

    @RequestMapping("/queryUserAll3")
    public JsonObject queryUserAll3(User user, HttpServletRequest request,
                                    @RequestParam(defaultValue = "1")  Integer page,
                                    @RequestParam(defaultValue = "15")   Integer limit){
        JsonObject object=new JsonObject();
        user.setDeptId(user.getDeptId());//测试使用
        PageInfo<User> pageInfo= userService.findUserAll(page,limit,user);
        List<User> list=pageInfo.getList();
        //创建集合对象
        List<UserVo> userVoList =new ArrayList<>();
        //今天
        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String today=sdf.format(d);
        //明天
        Calendar c=Calendar.getInstance();
        c.add(Calendar.DATE,1);
        String tomorrow =sdf.format(c.getTime());
        for(User u:list){
            UserVo vo=new UserVo();
            vo.setId(u.getId());
            vo.setUsername(u.getUsername());
            vo.setJobTitle(u.getJobTitle());
            vo.setEmail(u.getEmail());
            vo.setSex(u.getSex());
            //今天上午
           Integer tonum= workSettingService.getUserOrderByIdAndDayAndDayTime(u.getId(),today,"am");
           if(tonum==null){
               tonum=0;
           }
            Integer tonum2= workSettingService.getUserOrderByIdAndDayAndDayTime(u.getId(),today,"pm");
            if(tonum2==null){
                tonum2=0;
            }
            Integer tonum3= workSettingService.getUserOrderByIdAndDayAndDayTime(u.getId(),tomorrow,"am");
            if(tonum3==null){
                tonum3=0;
            }
            Integer tonum4= workSettingService.getUserOrderByIdAndDayAndDayTime(u.getId(),tomorrow,"pm");
            if(tonum4==null){
                tonum4=0;
            }
            vo.setTodayUp(tonum);
            vo.setTodayDown(tonum2);
            vo.setTomUp(tonum3);
            vo.setTomDown(tonum4);
            userVoList.add(vo);
        }
        object.setMsg("ok");
        object.setCode(0);
        object.setCount(pageInfo.getTotal());
        object.setData(userVoList);
        return object;

    }



    @RequestMapping("/queryUserInfoAll")
    public List<User> queryUserInfoAll(){
        PageInfo<User> pageInfo= userService.findUserAll(1,10,null);
        return pageInfo.getList();
    }

    /**
     * 统计接口
     * @param
     * @return
     */
    @RequestMapping("/queryTongjiList")
    public List<TongJi> queryTongjiList(){
       return  userService.queryTongjiCounts();
    }


    @ApiOperation(value = "新增用户信息表")
    @RequestMapping("/add")
    public R add(@RequestBody User user){
        int num= userService.add(user);
        if(num>0){
            return  R.ok();
        }
        return  R.fail("用户添加失败");
    }

    @ApiOperation(value = "删除用户信息表")
    @RequestMapping("/deleteByIds")
    public R delete(String ids){
        //把字符串转成集合对象
        List<String> list=Arrays.asList(ids);
        int num=0;
        for(String idString:list){
            num += userService.delete(new Long(idString));
        }
        if(num>0){
           return R.ok();
        }
        return R.fail("删除失败");
    }

    @ApiOperation(value = "新增用户信息表")
    @RequestMapping("/update")
    public R update(@RequestBody User user){
        int num= userService.updateData(user);
        if(num>0){
            return  R.ok();
        }
        return  R.fail("用户修改失败");
    }

    @ApiOperation(value = "查询用户信息表分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<User> findListByPage(@RequestParam Integer page,
                                      @RequestParam Integer pageCount){
        return userService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询用户信息表")
    @GetMapping("{id}")
    public User findById(@PathVariable Long id){
        return userService.findById(id);
    }

}
