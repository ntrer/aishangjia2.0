package com.shushang.aishangjia.base;

/**
 * Created by YD on 2018/9/18.
 */

public class MessageEvent5 {
    private String message;
    private String activituId;
    public MessageEvent5(String message,String activituId){
        this.message=message;
        this.activituId=activituId;
    }

    public String getActivituId() {
        return activituId;
    }

    public void setActivituId(String activituId) {
        this.activituId = activituId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

