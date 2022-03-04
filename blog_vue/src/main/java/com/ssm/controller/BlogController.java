package com.ssm.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssm.common.lang.Result;
import com.ssm.entity.Blog;
import com.ssm.service.BlogService;

import com.ssm.util.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2022-02-28
 */
@RestController
public class BlogController {
    //定义三个接口
    //详情页
    //index
    //editor
    @Autowired
    BlogService blogService;
    //参数：分页.没有传值的默认值@Requestparam
    @GetMapping("/blogs")//详情页
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){
        if(currentPage == null || currentPage < 1) currentPage = 1;
        Page page = new Page(currentPage, 5);
        //一页最多五条
        //查询条件：根据创建时间降序
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.succ(pageData);



    }
    @GetMapping("/blog/{id}")//详情页
    public Result detail(@PathVariable(name = "id") Long id){
        Blog blog=blogService.getById(id);
        //查出来blog之后
        if(blog==null){
            return Result.fail("该博客已被删除");
        }
        return Result.succ(blog);

    }
    @RequiresAuthentication
    @PostMapping("/blog/edit")//详情页
    public Result edit(@Validated @RequestBody Blog blog){
        Blog temp=null;
        //先判断是编辑还是新增的状态
        if(blog.getId()!=null){
            //编辑
            //查询之后，首先确定有这个blog；接着编辑的话要确保是自己的
            temp=blogService.getById(blog.getId());
            Assert.isTrue(temp.getUserId().equals(ShiroUtil.getProfile().getId()),"你没有权限编辑");
        }else{
            //添加
            temp=new Blog();
            //必填的参数
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);

        }
        //复制
        BeanUtils.copyProperties(blog,temp,"id","userId","created","status");
        //更新保存
        blogService.saveOrUpdate(temp);
        return Result.succ(null);

    }
}
