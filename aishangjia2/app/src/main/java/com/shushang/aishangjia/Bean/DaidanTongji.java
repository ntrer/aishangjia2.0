package com.shushang.aishangjia.Bean;

import java.util.List;

public class DaidanTongji {


    /**
     * ret : 200
     * msg : success
     * data : null
     * dataList : [{"merchantName":"t商家1黄河路","merchantCode":"tsj1","merchantId":"402880b76627e96c016627f06eb20011","merchantSubName":"商家5时机","merchantSubCode":"tsj5","merchantSubId":"402880b766995837016699c23eb3001c","orderCount":1,"orderMoney":200,"mode":null,"situation":0,"mode_count":0}]
     * currentPage : 0
     * totalCount : 0
     * maxPage : 0
     * pageSize : 0
     * intcurrentPage : 1
     * intpageSize : 10
     * intmaxCount : 1
     * intmaxPage : 1
     */

    private String ret;
    private String msg;
    private Object data;
    private int currentPage;
    private int totalCount;
    private int maxPage;
    private int pageSize;
    private int intcurrentPage;
    private int intpageSize;
    private int intmaxCount;
    private int intmaxPage;
    private List<DataListBean> dataList;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
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

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getIntcurrentPage() {
        return intcurrentPage;
    }

    public void setIntcurrentPage(int intcurrentPage) {
        this.intcurrentPage = intcurrentPage;
    }

    public int getIntpageSize() {
        return intpageSize;
    }

    public void setIntpageSize(int intpageSize) {
        this.intpageSize = intpageSize;
    }

    public int getIntmaxCount() {
        return intmaxCount;
    }

    public void setIntmaxCount(int intmaxCount) {
        this.intmaxCount = intmaxCount;
    }

    public int getIntmaxPage() {
        return intmaxPage;
    }

    public void setIntmaxPage(int intmaxPage) {
        this.intmaxPage = intmaxPage;
    }

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * merchantName : t商家1黄河路
         * merchantCode : tsj1
         * merchantId : 402880b76627e96c016627f06eb20011
         * merchantSubName : 商家5时机
         * merchantSubCode : tsj5
         * merchantSubId : 402880b766995837016699c23eb3001c
         * orderCount : 1
         * orderMoney : 200.0
         * mode : null
         * situation : 0.0
         * mode_count : 0.0
         */

        private String merchantName;
        private String merchantCode;
        private String merchantId;
        private String merchantSubName;
        private String merchantSubCode;
        private String merchantSubId;
        private int orderCount;
        private double orderMoney;
        private String mode;
        private double situation;
        private double mode_count;

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }

        public String getMerchantCode() {
            return merchantCode;
        }

        public void setMerchantCode(String merchantCode) {
            this.merchantCode = merchantCode;
        }

        public String getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(String merchantId) {
            this.merchantId = merchantId;
        }

        public String getMerchantSubName() {
            return merchantSubName;
        }

        public void setMerchantSubName(String merchantSubName) {
            this.merchantSubName = merchantSubName;
        }

        public String getMerchantSubCode() {
            return merchantSubCode;
        }

        public void setMerchantSubCode(String merchantSubCode) {
            this.merchantSubCode = merchantSubCode;
        }

        public String getMerchantSubId() {
            return merchantSubId;
        }

        public void setMerchantSubId(String merchantSubId) {
            this.merchantSubId = merchantSubId;
        }

        public int getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(int orderCount) {
            this.orderCount = orderCount;
        }

        public double getOrderMoney() {
            return orderMoney;
        }

        public void setOrderMoney(double orderMoney) {
            this.orderMoney = orderMoney;
        }

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public double getSituation() {
            return situation;
        }

        public void setSituation(double situation) {
            this.situation = situation;
        }

        public double getMode_count() {
            return mode_count;
        }

        public void setMode_count(double mode_count) {
            this.mode_count = mode_count;
        }
    }
}
