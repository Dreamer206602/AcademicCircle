package com.lezhian.academiccircle.mvp.bean;

/**
 * Created by hww on 2016/6/21.
 */
public class BaseBean<T> {

    /**
     * 2000：请求成功
     2001：参数错误，(参数错误时，请在message里，提示具体的错误信息)
     3000：用户名或密码错误
     3001：该用户名已存在
     3002：该用户名不存在
     4000：请求失败，该操作不存在
     4001：请求失败，其他错误
     */
    public static final int SUCCESS_CODE = 2000;

   private int status;
   private String message;
   private T data;
    public static int getSuccessCode() {
        return SUCCESS_CODE;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        if (this.status == SUCCESS_CODE) {
            return true;
        } else {
            return false;
        }
    }
}
