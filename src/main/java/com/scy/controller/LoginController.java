package com.scy.controller;

import com.scy.model.PatientInfo;
import com.scy.model.User;
import com.scy.model.UserInfoVo;
import com.scy.service.IPatientInfoService;
import com.scy.service.IUserService;
import jwt.JWTUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Resource
    private IUserService userService;
    @Resource
    private IPatientInfoService patientInfoService;
    @RequestMapping("/loginIn")
    public Map loginIn(@RequestBody Map<String,String> map){
        Map m=new HashMap();
        //接收前端传过来的值信息
        String username= map.get("username");
        String password= map.get("password");
        String type= map.get("type");
        //根据用户名密码判断当前用户是否存在
        if(type.equals("0") || type.equals("1")){//如果是医生或者管理员
            //调用service层中的根据
            User u=new User();
            u.setPassword(password);
            u.setUsername(username);
            u.setRoleName(type);
            User user= userService.findUserByNameAndPwd(u);
            if(user!=null){
                //生成token信息
                UserInfoVo us=new UserInfoVo();
                us.setUsername(username);
                us.setType(type);
                us.setId(user.getId());
                String token= JWTUtil.createJsonWebToken(us);
                m.put("token",token);
                m.put("username",username);
                m.put("type",type);
                //登录成功
                m.put("code",200);
                m.put("msg","登录成功");
                return m;
            }else{
                //登录失败
                m.put("code",400);
                m.put("msg","该用户不存在,请重新登录");
                return m;
            }
        }else{//否则是患者
             PatientInfo pat=patientInfoService.queryPatByUsernameAndPassword(username,password);
             if(pat!=null){
                 //生成token信息
                 UserInfoVo us=new UserInfoVo();
                 us.setUsername(username);
                 us.setType("2");//患者
                 us.setId(pat.getId());
                 String token= JWTUtil.createJsonWebToken(us);
                 m.put("token",token);
                 m.put("username",username);
                 m.put("type",type);
                 //登录成功
                 m.put("code",200);
                 m.put("msg","登录成功");
                 return m;
             }else{
                 //登录失败
                 m.put("code",400);
                 m.put("msg","该用户不存在,请重新登录");
                 return m;
             }
        }
    }
}
