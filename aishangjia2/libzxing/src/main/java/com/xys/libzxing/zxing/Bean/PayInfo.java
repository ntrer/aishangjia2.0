package com.xys.libzxing.zxing.Bean;

public class PayInfo {


    /**
     * ret : 200
     * data : {"orderId":"0000000067c91c330167ca654514001e","totalPrice":0.01,"shopName":"双旦活动订金","tradeNo":"2018122022001427810585006463","payType":1,"transactionTime":"1545288902000"}
     * code : 1
     * msg : 支付成功
     */

    private String ret;
    private DataBean data;
    private int code;
    private String msg;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {
        /**
         * orderId : 0000000067c91c330167ca654514001e
         * totalPrice : 0.01
         * shopName : 双旦活动订金
         * tradeNo : 2018122022001427810585006463
         * payType : 1
         * transactionTime : 1545288902000
         */

        private String orderId;
        private double totalPrice;
        private String shopName;
        private String tradeNo;
        private int payType;
        private String transactionTime;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getTradeNo() {
            return tradeNo;
        }

        public void setTradeNo(String tradeNo) {
            this.tradeNo = tradeNo;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public String getTransactionTime() {
            return transactionTime;
        }

        public void setTransactionTime(String transactionTime) {
            this.transactionTime = transactionTime;
        }
    }
}
