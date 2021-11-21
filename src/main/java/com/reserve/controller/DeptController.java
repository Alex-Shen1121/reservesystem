package com.reserve.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.reserve.model.Dept;
import com.reserve.service.IDeptService;
import com.reserve.util.JsonObject;
import com.reserve.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 科室 前端控制器
 * </p>
 *
 * @author kappy
 * @since 2021-02-02
 */
@Api(tags = {"科室"})
@RestController
@RequestMapping("/dept")
public class DeptController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IDeptService deptService;

    @RequestMapping("/deptAll")
    public JsonObject queryDeptAll(Dept dept,
                                  @RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "15") Integer pageSize){
        PageInfo<Dept> pageInfo=deptService.queryDeptAll(page,pageSize,dept);
        JsonObject object=new JsonObject();
        //数据存入
        object.setData(pageInfo.getList());
        object.setCount(pageInfo.getTotal());//总的记录数
        object.setCode(0);
        object.setMsg("ok");
        return object;

    }

    /**
     * 查询树结构
     * @return
     */
    @RequestMapping("/queryDeptTree")
    public Object queryDeptTree(){
      return deptService.queryDeptTree();
    }


    @ApiOperation(value = "新增科室")
    @RequestMapping("/add")
    public R add(@RequestBody Dept dept){
        int nums= deptService.add(dept);
        if(nums>0){
            return  R.ok();
        }
        return R.fail("新增科室失败");
    }

    @ApiOperation(value = "删除科室")
    @RequestMapping("/delete")
    public R delete(@RequestBody Map<String,String> map){
         String idValue=map.get("id");
        //根据id获取当前id的记录信息
        Dept dept=deptService.findById(Long.parseLong(idValue));
        if(dept.getType()==1){//科室 删除该科室以及下面的子节点  部门
            //获取当前节点下的子节点的列表信息
           List<Dept> list= deptService.queryListByParentId(Long.parseLong(idValue));
           for(Dept d:list){
               int ids=d.getId();
               deptService.delete(new Long(ids));
           }
            deptService.delete(Long.parseLong(idValue));//当前节点
        }else{
            deptService.delete(Long.parseLong(idValue));
        }
        return  R.ok();
    }



    @RequestMapping("/update")
    public R update(@RequestBody Dept dept){
        int nums= deptService.updateData(dept);
        if(nums>0){
            return  R.ok();
        }
        return R.fail("修改科室失败");
    }
    @ApiOperation(value = "查询科室分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Dept> findListByPage(@RequestParam Integer page,
                                      @RequestParam Integer pageCount){
        return deptService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询科室")
    @GetMapping("{id}")
    public Dept findById(@PathVariable Long id){
        return deptService.findById(id);
    }

}
