package com.ssm.controller;


import com.ssm.common.lang.Result;
import com.ssm.entity.User;
import com.ssm.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2022-02-28
 */
@RestController
@RequestMapping("/user")
public class UserController {

//    @Autowired
//    UserService userService;
//    @GetMapping("/{id}")
//    public Object test(@PathVariable("id") Long id) {
//        return userService.getById(id);
//    }
    @Autowired
    UserService userService;
    //需要登录才可以访问
    @RequiresAuthentication
    @GetMapping("/index")
    public Result index(){
        User user=userService.getById(1L);
        return Result.succ(user);
    }

    //拦截表单的post请求，使用save函数处理这个拦截
    //@RequestBody表示取出post请求中的user信息
    //@Validated表示对其进行校验，有错误则抛出异常。
    // 全局异常GlobalEcxceptionHandler会捕获
    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user){

        return Result.succ(user);
    }
}
