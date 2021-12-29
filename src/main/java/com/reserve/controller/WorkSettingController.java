package com.reserve.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.reserve.model.WorkSetting;
import com.reserve.model.WorkVo;
import com.reserve.service.ISettingService;
import com.reserve.service.IWorkSettingService;

import com.reserve.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.reserve.util.DateUtil;
import jwt.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 医生排班的默认设置，用来初始化处理信息，主要按周1到周五排班配置，后续每月周一到周五均按照这个初始化医生上班时间 前端控制器
 * </p>
 */
@Api(tags = {"医生排班的默认设置，用来初始化处理信息，主要按周1到周五排班配置，后续每月周一到周五均按照这个初始化医生上班时间"})
@RestController
@CrossOrigin
@RequestMapping("/work")
public class WorkSettingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IWorkSettingService workSettingService;
    @Resource
    private ISettingService settingService;

    @RequestMapping("/addInit")
    public R addInit(@RequestBody Map<String,String> maps){
        //把字符串转成集合对象
        String userId=maps.get("ids");
//        List<String> list= Arrays.asList(ids);
        /*
            1、通过用户id 查询是否有当前默认的设置信息 如果有 初始化
               1.1  获取当前用户的默认设置  周一到周日的数据信息
               1.2  遍历当前月信息每天信息
                    获取今日是周几，使用默认设置中的数据进行 添加
               1.3  初始化完成  返回结果
            2、如果没有返回提示 不能初始化
         */
         //根据用户id 获取是否有默认设置
         List listDatas=settingService.querySettingListByUserId(Integer.parseInt(userId));
        //如果有 代表已经有初始化数据
         Collection <WorkSetting> collection=new ArrayList<>();
         if(listDatas.size()>0){
             //根据月份删除当前用户的初始化内容
             Calendar cal=Calendar.getInstance();
             int month=cal.get(Calendar.MONTH)+1;
             workSettingService.deleteWorkSettingByUserIdAndMonth(Integer.parseInt(userId),String.valueOf(month));
             List<Date> dataList=  DateUtil.getBetweenDates();
             //转换时间
             DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
             for(Date date:dataList){
                 //获取周几
                 int day= DateUtil.dateToWeek(sdf.format(date));
                 //获取当前天 上午人数
                 int countUp=settingService.getCountsBySet(Integer.parseInt(userId),String.valueOf(day),"am");
                 //获取当前天 下午人数
                 int countDown=settingService.getCountsBySet(Integer.parseInt(userId),String.valueOf(day),"pm");
                 //设置当天上午 下午的对象
                 WorkSetting setUp=new WorkSetting(Integer.parseInt(userId),date,"am",countUp);
                 WorkSetting setDown=new WorkSetting(Integer.parseInt(userId),date,"pm",countUp);
                 collection.add(setUp);
                 collection.add(setDown);
             }
             workSettingService.saveBatch(collection);
             return R.ok();
         }else{
             return R.fail(405,"不好意思，没有设置默认初始化，请先设置后再初始化....");

         }



    }


    @RequestMapping("/deleteByIds")
    public R delete(@RequestBody Map<String,String> maps){
        //把字符串转成集合对象
        String ids=maps.get("ids");
        List<String> list=Arrays.asList(ids);
        Calendar cal=Calendar.getInstance();
        int month=cal.get(Calendar.MONTH)+1;
        for(String userId:list){
            workSettingService.
                    deleteWorkSettingByUserIdAndMonth(Integer.parseInt(userId),String.valueOf(month));
        }
        return R.ok();
    }


    /**
     * 用户排班信息的获取
     */
    @RequestMapping("/queryListByUserID")
    public List<WorkVo> queryListByUserId(@RequestBody Map<String,String> maps){
      String id=maps.get("userId");
      List<WorkVo> list= workSettingService.findListByUserId(Integer.parseInt(id),null,null);
      return list;
    }


    /**
     * 用户排班信息的获取
     */
    @RequestMapping("/queryListByUserID2")
    public List<WorkVo> queryListByUserId2(HttpServletRequest request){
//        String id=maps.get("userId");
        //获取登录用户信息
        String token= request.getHeader("token");
        Integer userId=JWTUtil.getUserId(token);
        List<WorkVo> list= workSettingService.findListByUserId(userId,null,null);
        return list;
    }

    /**
     * 根据用户排班信息查询
     * @param
     * @return
     */
//    @RequestMapping("/queryListByUserID2")
//    public List<WorkVo> queryListByUserID2(HttpServletRequest request){
//        //获取登录用户id
//        String token=request.getHeader("token");
//        Integer userId= JWTUtil.getUserId(token);
//        List<WorkVo> list=workSettingService.findListByUserId(2,null,null);
//        return list;
//    }


    @RequestMapping("/queryListByUserIdAndDay")
    public Map queryListByUserIdAndDay(@RequestBody Map<String,String> maps){
        String id=maps.get("userId");
        String day=maps.get("day");
        Map map=new HashMap();
        List<WorkSetting> list= workSettingService.findListByUserIdAndDay(Integer.parseInt(id),day);
        for(WorkSetting setting:list){
            if(setting.getDayTime().equals("am")){
                map.put("am",setting.getCounts());
            }else{
                map.put("pm",setting.getCounts());
            }
        }
        map.put("userId",id);
        return map;
    }


    @RequestMapping("/update")
    public R update(@RequestBody Map<String,String> maps){
        String id=maps.get("userId");
        String day=maps.get("day");
        String am=maps.get("am");
        String pm=maps.get("pm");
        //根据用户id和日期获取记录列表
        List<WorkSetting> list=workSettingService.findListByUserIdAndDay(Integer.parseInt(id),day);
        for(WorkSetting setting:list){
            if(setting.getDayTime().equals("am")){
                setting.setCounts(Integer.parseInt(am));
            }else{
                setting.setCounts(Integer.parseInt(pm));
            }
            workSettingService.updateData(setting);
        }
       return R.ok();
    }

    @ApiOperation(value = "查询医生排班的默认设置，用来初始化处理信息，主要按周1到周五排班配置，后续每月周一到周五均按照这个初始化医生上班时间分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<WorkSetting> findListByPage(@RequestParam Integer page,
                                             @RequestParam Integer pageCount){
        return workSettingService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询医生排班的默认设置，用来初始化处理信息，主要按周1到周五排班配置，后续每月周一到周五均按照这个初始化医生上班时间")
    @GetMapping("{id}")
    public WorkSetting findById(@PathVariable Long id){
        return workSettingService.findById(id);
    }

}
