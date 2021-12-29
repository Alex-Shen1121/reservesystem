package com.reserve.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.reserve.model.Menu;
import com.reserve.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 菜单信息表 前端控制器
 * </p>
 */
@Api(tags = {"菜单信息表"})
@RestController
@RequestMapping("/menu")
public class MenuController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IMenuService menuService;


    @ApiOperation(value = "新增菜单信息表")
    @PostMapping()
    public int add(@RequestBody Menu menu){
        return menuService.add(menu);
    }

    @ApiOperation(value = "删除菜单信息表")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return menuService.delete(id);
    }

    @ApiOperation(value = "更新菜单信息表")
    @PutMapping()
    public int update(@RequestBody Menu menu){
        return menuService.updateData(menu);
    }

    @ApiOperation(value = "查询菜单信息表分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Menu> findListByPage(@RequestParam Integer page,
                                      @RequestParam Integer pageCount){
        return menuService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询菜单信息表")
    @GetMapping("{id}")
    public Menu findById(@PathVariable Long id){
        return menuService.findById(id);
    }

}
