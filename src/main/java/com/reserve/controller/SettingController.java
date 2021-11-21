package com.reserve.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.reserve.model.Setting;
import com.reserve.service.ISettingService;
import com.reserve.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 每月的自动生成排班设置，管理员可以根据生成的信息特殊处理 前端控制器
 * </p>
 *
 * @author kappy
 * @since 2021-02-02
 */
@Api(tags = {"每月的自动生成排班设置，管理员可以根据生成的信息特殊处理"})
@RestController
@RequestMapping("/setting")
public class SettingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ISettingService settingService;


    /**
     * 根据用户id获取用的设置初始值信息
     */
    @RequestMapping("/queryListSettingByUserId")
    public Map queryListSettingByUserId(@RequestBody Map<String,String> maps){
        //获取前端传过来的用户id
        String userId=maps.get("userId");
        //返回对象map
        Map map=new HashMap();
        for(int i=1;i<=7;i++){
            map.put("dayup"+i,0);
            map.put("daydown"+i,0);
        }
        //根据用id 获取用户设置信息
        List<Setting> list=settingService.querySettingListByUserId(Integer.parseInt(userId));
        for(Setting set:list){
            String day=set.getDay();
            String time=set.getDayTime();
            switch (day){
                case "1":
                    if(time.equals("am")){
                        map.put("dayup1",set.getCounts());
                    }else{
                        map.put("daydown1",set.getCounts());
                    }
                    break;
                case "2":
                    if(time.equals("am")){
                        map.put("dayup2",set.getCounts());
                    }else{
                        map.put("daydown2",set.getCounts());
                    }
                    break;
                case "3":
                    if(time.equals("am")){
                        map.put("dayup3",set.getCounts());
                    }else{
                        map.put("daydown3",set.getCounts());
                    }
                    break;
                case "4":
                    if(time.equals("am")){
                        map.put("dayup4",set.getCounts());
                    }else{
                        map.put("daydown4",set.getCounts());
                    }
                    break;
                case "5":
                    if(time.equals("am")){
                        map.put("dayup5",set.getCounts());
                    }else{
                        map.put("daydown5",set.getCounts());
                    }
                    break;
                case "6":
                    if(time.equals("am")){
                        map.put("dayup6",set.getCounts());
                    }else{
                        map.put("daydown6",set.getCounts());
                    }
                    break;
                case "7":
                    if(time.equals("am")){
                        map.put("dayup7",set.getCounts());
                    }else{
                        map.put("daydown7",set.getCounts());
                    }
                    break;
            }

        }

        return map;

    }



    @ApiOperation(value = "新增每月的自动生成排班设置，管理员可以根据生成的信息特殊处理")
    @RequestMapping("/add")
    public R add(@RequestBody Map<String,String> maps){
        String userId= maps.get("userId");
        //根据id查询是否有信息
        List<Setting> list=settingService.querySettingListByUserId(Integer.parseInt(userId));
        for(Setting setting:list){
            //获取主键 根据主键删除
            Integer id=setting.getId();
            settingService.delete(new Long(id));
        }
      //添加新传入的值
        Collection<Setting> collection=new ArrayList<>();
        for(int i=1;i<=7;i++){
            String updata=maps.get("dayup"+i);
            String downdata=maps.get("daydown"+i);
            //设置setting对象
            Setting setup=
                    new Setting(Integer.parseInt(userId),String.valueOf(i),"am",Integer.parseInt(updata));

            Setting setdown=
                    new Setting(Integer.parseInt(userId),String.valueOf(i),"pm",Integer.parseInt(downdata));
            collection.add(setup);
            collection.add(setdown);
        }

       //批量添加操作
        boolean bs=settingService.saveBatch(collection);
        return R.ok();

    }

    @ApiOperation(value = "删除每月的自动生成排班设置，管理员可以根据生成的信息特殊处理")
    @RequestMapping("/deleteByIds")
    public R delete(@RequestBody Map<String,String> maps){
        //把字符串转成集合对象
        String ids=maps.get("ids");
        List<String> list=Arrays.asList(ids);
        int num=0;
        for(String idString:list){
            settingService.deleteByUserId(Integer.parseInt(idString));
        }
//        if(num>0){
            return R.ok();
//        }
//        return R.fail("删除失败");
    }

    @ApiOperation(value = "更新每月的自动生成排班设置，管理员可以根据生成的信息特殊处理")
    @PutMapping()
    public int update(@RequestBody Setting setting){
        return settingService.updateData(setting);
    }

    @ApiOperation(value = "查询每月的自动生成排班设置，管理员可以根据生成的信息特殊处理分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Setting> findListByPage(@RequestParam Integer page,
                                         @RequestParam Integer pageCount){
        return settingService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询每月的自动生成排班设置，管理员可以根据生成的信息特殊处理")
    @GetMapping("{id}")
    public Setting findById(@PathVariable Long id){
        return settingService.findById(id);
    }

}
