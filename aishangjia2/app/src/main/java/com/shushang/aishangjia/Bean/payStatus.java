package com.shushang.aishangjia.Bean;

public class payStatus {

    /**
     * ret : 200
     * code : -1
     * msg : 支付失败
     */

    private String ret;
    private int code;
    private String msg;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

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
}
