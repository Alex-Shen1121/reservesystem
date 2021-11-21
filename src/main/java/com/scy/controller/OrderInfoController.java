package com.scy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.scy.model.OrderInfo;
import com.scy.model.PatientInfo;
import com.scy.service.IOrderInfoService;
import com.scy.util.JsonObject;
import com.scy.util.R;
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
import java.util.*;

/**
 * <p>
 * 病人的预约记录 前端控制器
 * </p>
 *
 * @author kappy
 * @since 2021-02-02
 */
@Api(tags = {"病人的预约记录"})
@RestController
@RequestMapping("/order")
public class OrderInfoController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IOrderInfoService orderInfoService;


    @RequestMapping("/queryOrderInfoAll")
    public JsonObject queryOrderInfoAll(PatientInfo patientInfo,Integer status,
                                        @RequestParam(defaultValue = "1")  Integer page,
                                        @RequestParam(defaultValue = "15")   Integer limit){
       OrderInfo info=new OrderInfo();
       info.setPatientInfo(patientInfo);
       info.setStatus(status);
        JsonObject object=new JsonObject();
        PageInfo<OrderInfo> pageInfo= orderInfoService.findOrderInfoAll(page,limit,info);
        object.setMsg("ok");
        object.setCode(0);
        object.setCount(pageInfo.getTotal());
        object.setData(pageInfo.getList());
        return object;

    }

    @RequestMapping("/queryOrderInfoAll2")
    public JsonObject queryOrderInfoAll2(PatientInfo patientInfo, Integer status, HttpServletRequest request,
                                        @RequestParam(defaultValue = "1")  Integer page,
                                         @RequestParam(defaultValue = "15")   Integer limit){
        OrderInfo info=new OrderInfo();
        info.setPatientInfo(patientInfo);
        info.setStatus(status);
        //获取登录用户信息
        String token= request.getHeader("token");
        Integer userId= JWTUtil.getUserId(token);
        info.setUserId(userId);

        JsonObject object=new JsonObject();
        PageInfo<OrderInfo> pageInfo= orderInfoService.findOrderInfoAll(page,limit,info);
        object.setMsg("ok");
        object.setCode(0);
        object.setCount(pageInfo.getTotal());
        object.setData(pageInfo.getList());
        return object;

    }


    @RequestMapping("/queryOrderInfoAll3")
    public JsonObject queryOrderInfoAll3(Integer status, HttpServletRequest request,
                                         @RequestParam(defaultValue = "1")  Integer page,
                                         @RequestParam(defaultValue = "15")   Integer limit){
        OrderInfo info=new OrderInfo();
        //获取登录用户信息
        String token= request.getHeader("token");
        Integer userId= JWTUtil.getUserId(token);
        info.setPatientId(userId);
        JsonObject object=new JsonObject();
        PageInfo<OrderInfo> pageInfo= orderInfoService.findOrderInfoAll(page,limit,info);
        object.setMsg("ok");
        object.setCode(0);
        object.setCount(pageInfo.getTotal());
        object.setData(pageInfo.getList());
        return object;

    }


    @ApiOperation(value = "新增病人的预约记录")
    @RequestMapping("/add")
    public R add(@RequestBody OrderInfo orderInfo){
        orderInfo.setStatus(0);
         int num=orderInfoService.add(orderInfo);
         if(num>0){
             return R.ok();
         }
         return R.fail("添加预约失败");
    }

    @RequestMapping("/add2")
    public R add2(@RequestBody Map<String,String> map,HttpServletRequest request){
        OrderInfo info=new OrderInfo();
        info.setStatus(0);

        //获取登录用户信息
        String token= request.getHeader("token");
        Integer patId= JWTUtil.getUserId(token);
        info.setPatientId(patId);

        Integer userId=Integer.parseInt(map.get("userId"));
        info.setUserId(userId);
        info.setDayTime(map.get("dayTime"));
        String day=map.get("day");
        if(day.equals("today")){
            info.setDay(new Date());
        }else{
            Calendar c=Calendar.getInstance();
            c.add(Calendar.DATE,1);
            info.setDay(c.getTime());
        }
        int num=orderInfoService.add(info);
        if(num>0){
            return R.ok();
        }
        return R.fail("添加预约失败");
    }

    @ApiOperation(value = "删除病人的预约记录")
    @RequestMapping("/deleteByIds")
    public R delete(String ids){
        //把字符串转成集合对象
        List<String> list= Arrays.asList(ids);
        int num=0;
        for(String idString:list){
            num += orderInfoService.delete(new Long(idString));
        }
        if(num>0){
            return R.ok();
        }
        return R.fail("删除失败");
    }


    @ApiOperation(value = "更新病人的预约记录")
    @PutMapping()
    public int update(@RequestBody OrderInfo orderInfo){
        return orderInfoService.updateData(orderInfo);
    }

    @ApiOperation(value = "查询病人的预约记录分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<OrderInfo> findListByPage(@RequestParam Integer page,
                                           @RequestParam Integer pageCount){
        return orderInfoService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询病人的预约记录")
    @GetMapping("{id}")
    public OrderInfo findById(@PathVariable Long id){
        return orderInfoService.findById(id);
    }

}
