package com.ssm.common.lang;

import lombok.Data;

import java.io.Serializable;
@Data
//首先序列化合数据
public class Result implements Serializable {
    //三个字段,返回数据类型不知道是什么用Object
    private int code;
    private String msg;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
//令其他可以直接调用返回数据

    //成功的时候,msg不需要，code也只有200
    public static Result succ(int code,String msg,Object data){
        Result r=new Result();
        r.setCode(code);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }
    //一个更简单的方法
    public static Result succ(Object data) {

        return succ(200, "操作成功", data);
    }

    //失败的时候，需要更多的信息
    public static Result fail(int code,String msg,Object data){
        Result r=new Result();
        r.setCode(code);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }
    public static Result fail(String msg,Object data){

        return fail(400,msg,data);
    }
    //错误的时候没有数据，msg才是重要的
    public static Result fail(String msg){

        return fail(400,msg,null);
    }

}

