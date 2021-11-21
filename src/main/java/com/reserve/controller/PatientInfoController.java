package com.reserve.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.reserve.model.PatientInfo;
import com.reserve.service.IPatientInfoService;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 病人信息表 前端控制器
 * </p>
 *
 * @author kappy
 * @since 2021-02-02
 */
@Api(tags = {"病人信息表"})
@RestController
@RequestMapping("/patient")
public class PatientInfoController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IPatientInfoService patientInfoService;

    @RequestMapping("/queryAll")
    public JsonObject queryUserAll(PatientInfo user,
                                   @RequestParam(defaultValue = "1")  Integer page,
                                   @RequestParam(defaultValue = "15")   Integer limit){
        JsonObject object=new JsonObject();
        PageInfo<PatientInfo> pageInfo= patientInfoService.findUserAll(page,limit,user);
        object.setMsg("ok");
        object.setCode(0);
        object.setCount(pageInfo.getTotal());
        object.setData(pageInfo.getList());
        return object;
    }

    @RequestMapping("/queryAll2")
    public JsonObject queryUserAll2(PatientInfo user, HttpServletRequest request,
                                   @RequestParam(defaultValue = "1")  Integer page,
                                    @RequestParam(defaultValue = "15")   Integer limit){
        JsonObject object=new JsonObject();
        //获取登录用户信息
        String token= request.getHeader("token");
        Integer userId= JWTUtil.getUserId(token);
        user.setId(userId);
        PageInfo<PatientInfo> pageInfo= patientInfoService.findUserAll(page,limit,user);
        object.setMsg("ok");
        object.setCode(0);
        object.setCount(pageInfo.getTotal());
        object.setData(pageInfo.getList());
        return object;
    }

    @RequestMapping("/queryPatientInfoAll")
    public List<PatientInfo> queryPatientInfoAll(){
        PageInfo<PatientInfo> pageInfo= patientInfoService.findUserAll(1,10,null);
        return pageInfo.getList();
    }

    @RequestMapping("/getUserByName")
    public Map getUserByName(@RequestBody Map<String,String> map){
        Map m=new HashMap<>();
        PatientInfo info= patientInfoService.queryPatByName(map.get("username"));
        if(info!=null){
            m.put("code",100);
            m.put("msg","该名称已存在，请换个新的");
        }else{
            m.put("code",101);
            m.put("msg","可以使用");
        }
        return  m;
    }

    @ApiOperation(value = "新增病人信息表")
    @PostMapping("/add")
    public R add(@RequestBody PatientInfo patientInfo){
        int num= patientInfoService.add(patientInfo);
        if(num>0){
            return R.ok();
        }
        return R.fail("注册失败");
    }

    @ApiOperation(value = "删除用户信息表")
    @RequestMapping("/deleteByIds")
    public R delete(String ids){
        //把字符串转成集合对象
        List<String> list= Arrays.asList(ids);
        int num=0;
        for(String idString:list){
            num += patientInfoService.delete(new Long(idString));
        }
        if(num>0){
            return R.ok();
        }
        return R.fail("删除失败");
    }

    @RequestMapping("/update")
    public R update(@RequestBody PatientInfo patientInfo){
        int num= patientInfoService.updateData(patientInfo);
        if(num>0){
            return  R.ok();
        }
        return  R.fail("修改失败");
    }

    @ApiOperation(value = "查询病人信息表分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<PatientInfo> findListByPage(@RequestParam Integer page,
                                             @RequestParam Integer pageCount){
        return patientInfoService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询病人信息表")
    @GetMapping("{id}")
    public PatientInfo findById(@PathVariable Long id){
        return patientInfoService.findById(id);
    }

}
