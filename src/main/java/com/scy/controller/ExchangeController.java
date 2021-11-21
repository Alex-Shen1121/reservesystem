package com.scy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.scy.model.Exchange;
import com.scy.model.User;
import com.scy.service.IExchangeService;
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
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 调休记录信息  前端控制器
 * </p>
 *
 * @author kappy
 * @since 2021-02-02
 */
@Api(tags = {"调休记录信息 "})
@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IExchangeService exchangeService;

    @RequestMapping("/queryExchangeInfoAll")
    public JsonObject queryExchangeInfoAll(User user, Integer status,
                                   @RequestParam(defaultValue = "1")  Integer page,
                                   @RequestParam(defaultValue = "15")   Integer limit){
        JsonObject object=new JsonObject();
        Exchange exchange= new Exchange();
        exchange.setUser(user);
        exchange.setStatus(status);
        PageInfo<Exchange> pageInfo= exchangeService.queryExchangeAll(page,limit,exchange);
        object.setMsg("ok");
        object.setCode(0);
        object.setCount(pageInfo.getTotal());
        object.setData(pageInfo.getList());
        return object;

    }


    @RequestMapping("/queryExchangeInfoAll2")
    public JsonObject queryExchangeInfoAll2(User user, Integer status, HttpServletRequest request,
                                           @RequestParam(defaultValue = "1")  Integer page,
                                            @RequestParam(defaultValue = "15")   Integer limit){
        JsonObject object=new JsonObject();
        Exchange exchange= new Exchange();
        exchange.setUser(user);
        exchange.setStatus(status);
        //获取当前登录用户
        //获取登录用户信息
        String token= request.getHeader("token");
        Integer userId= JWTUtil.getUserId(token);
        exchange.setUserId(userId);


        PageInfo<Exchange> pageInfo= exchangeService.queryExchangeAll(page,limit,exchange);
        object.setMsg("ok");
        object.setCode(0);
        object.setCount(pageInfo.getTotal());
        object.setData(pageInfo.getList());
        return object;

    }


    @ApiOperation(value = "新增调休记录信息 ")
    @RequestMapping("/add")
    public R add(@RequestBody Exchange exchange){
        exchange.setStatus(0);
        int num=exchangeService.add(exchange);
        if (num>0) {
            return R.ok();
        }
        return R.fail("提报申请失败");
    }

    @ApiOperation(value = "新增调休记录信息 ")
    @RequestMapping("/add2")
    public R add2(@RequestBody Exchange exchange, HttpServletRequest request){
        //获取登录用户信息
        String token= request.getHeader("token");
        Integer userId= JWTUtil.getUserId(token);
        exchange.setUserId(userId);
        exchange.setStatus(0);
        int num=exchangeService.add(exchange);
        if (num>0) {
            return R.ok();
        }
        return R.fail("提报申请失败");
    }

    @ApiOperation(value = "删除调休记录信息 ")
    @RequestMapping("/deleteByIds")
    public R delete(String ids){
        //把字符串转成集合对象
        List<String> list= Arrays.asList(ids);
        int num=0;
        for(String idString:list){
            num += exchangeService.delete(new Long(idString));
        }
        if(num>0){
            return R.ok();
        }
        return R.fail("删除失败");
    }


    @ApiOperation(value = "更新调休记录信息 ")
    @RequestMapping("/update")
    public R update(String ids) {
        //把字符串转成集合对象
        List<String> list = Arrays.asList(ids);
        for (String idString : list) {
            Exchange exchange=new Exchange();
            exchange.setId(Integer.parseInt(idString));
            exchange.setStatus(1);
            exchangeService.updateData(exchange);

        }
       return R.ok();
    }
    @ApiOperation(value = "查询调休记录信息 分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Exchange> findListByPage(@RequestParam Integer page,
                                          @RequestParam Integer pageCount){
        return exchangeService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询调休记录信息 ")
    @GetMapping("{id}")
    public Exchange findById(@PathVariable Long id){
        return exchangeService.findById(id);
    }

}
