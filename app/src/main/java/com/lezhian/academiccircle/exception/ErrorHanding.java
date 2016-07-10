package com.lezhian.academiccircle.exception;

/**
 * Created by hww on 2016/6/21.
 */
public class ErrorHanding {
    public ErrorHanding() {
    }

    public static String handleError(Throwable throwable) {
        String message;
        if (throwable instanceof ServerException) {
            message = throwable.getMessage();
        } else {
            message = "连接服务器失败";
        }
        return message;
    }
}
