package com.reserve.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.reserve.model.TitleInfo;
import com.reserve.service.ITitleInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 医生职称信息表 前端控制器
 * </p>
 */
@Api(tags = {"医生职称信息表"})
@RestController
@RequestMapping("/titleInfo")
public class TitleInfoController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ITitleInfoService titleInfoService;

    @RequestMapping("/titleAll")
    public List<TitleInfo> titleAll(){
        return titleInfoService.list();
    }


    @ApiOperation(value = "新增医生职称信息表")
    @PostMapping()
    public int add(@RequestBody TitleInfo titleInfo){
        return titleInfoService.add(titleInfo);
    }

    @ApiOperation(value = "删除医生职称信息表")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return titleInfoService.delete(id);
    }

    @ApiOperation(value = "更新医生职称信息表")
    @PutMapping()
    public int update(@RequestBody TitleInfo titleInfo){
        return titleInfoService.updateData(titleInfo);
    }

    @ApiOperation(value = "查询医生职称信息表分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<TitleInfo> findListByPage(@RequestParam Integer page,
                                           @RequestParam Integer pageCount){
        return titleInfoService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询医生职称信息表")
    @GetMapping("{id}")
    public TitleInfo findById(@PathVariable Long id){
        return titleInfoService.findById(id);
    }

}
