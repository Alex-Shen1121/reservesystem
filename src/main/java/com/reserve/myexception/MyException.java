package com.reserve.myexception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

@ControllerAdvice
public class MyException {

    @ExceptionHandler(value = Exception.class)
    public String excptionHandler(Exception e, HandlerMethod method){
        System.out.println("走到这里啦....");
        System.out.println(e);
//        // 获取方法名称
        System.out.println(method.getMethod().getName());
        System.out.println(method.getMethod().getParameters()[0]);
        return e.getMessage();
    }

}

