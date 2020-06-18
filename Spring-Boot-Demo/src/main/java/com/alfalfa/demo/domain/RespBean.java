package com.alfalfa.demo.domain;


public class RespBean {
    private String status;
    private String msg;
    private Object obj;

    public static RespBean build() {
        return new RespBean();
    }

    public static RespBean ok(String status,String msg,Object object) {
        return new RespBean(status, msg, object);
    }


    public static RespBean error(String status,String msg,Object object) {
        return new RespBean(status, msg, object);
    }

    private RespBean() {
    }

    private RespBean(String status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    public String getStatus() {
        return status;
    }

    public RespBean setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RespBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getObj() {
        return obj;
    }

    public RespBean setObj(Object obj) {
        this.obj = obj;
        return this;
    }
}