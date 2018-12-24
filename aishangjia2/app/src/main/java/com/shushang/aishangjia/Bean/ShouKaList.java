package com.shushang.aishangjia.Bean;

import java.util.List;

public class ShouKaList {


    /**
     * ret : 200
     * msg : success
     * data : null
     * dataList : [{"customerActionId":"402880b7672ea2db01672f09ef490032","customerName":"yd","customerMobile":"18637280068","customerSex":"1","decorationProgress":"毛坯","decorationStyle":"jjj","thinkBuyGood":"jjj","likeColor":null,"merchantId":"402880b7653b275e01653b69cc770050","merchantCode":"ssdb","shengCode":"150000","shengName":"内蒙古自治区","shiCode":"150400","shiName":"赤峰市","quCode":"150423","quName":"巴林右旗","address":"bdnxn","managerId":"402880b7653b275e01653d0285c901cd","managerRealName":"徐珊珊","managerAcount":"xushanshan","status":"0","statusMessage":null,"customersource":"1","activityId":"402880b7672ea2db01672f06ff8a002d","activityName":"杨栋的活动6","activityCode":1198,"shuoming":null,"wechatPageId":null,"chuangjianren":"402880b7653b275e01653d0285c901cd","xiugairen":"402880b7653b275e01653d0285c901cd","cjsj":1542682439000,"xgsj":1542682439000,"del":"0","cardNum":"871198001","merchantName":"数尚地板中华路","qdsj":null,"qdsucess":"0","lqsuccess":null,"lqsj":null,"isBack":null}]
     * currentPage : 0
     * totalCount : 0
     * maxPage : 0
     * pageSize : 0
     * intcurrentPage : 1
     * intpageSize : 10
     * intmaxCount : 0
     * intmaxPage : 0
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
         * customerActionId : 402880b7672ea2db01672f09ef490032
         * customerName : yd
         * customerMobile : 18637280068
         * customerSex : 1
         * decorationProgress : 毛坯
         * decorationStyle : jjj
         * thinkBuyGood : jjj
         * likeColor : null
         * merchantId : 402880b7653b275e01653b69cc770050
         * merchantCode : ssdb
         * shengCode : 150000
         * shengName : 内蒙古自治区
         * shiCode : 150400
         * shiName : 赤峰市
         * quCode : 150423
         * quName : 巴林右旗
         * address : bdnxn
         * managerId : 402880b7653b275e01653d0285c901cd
         * managerRealName : 徐珊珊
         * managerAcount : xushanshan
         * status : 0
         * statusMessage : null
         * customersource : 1
         * activityId : 402880b7672ea2db01672f06ff8a002d
         * activityName : 杨栋的活动6
         * activityCode : 1198
         * shuoming : null
         * wechatPageId : null
         * chuangjianren : 402880b7653b275e01653d0285c901cd
         * xiugairen : 402880b7653b275e01653d0285c901cd
         * cjsj : 1542682439000
         * xgsj : 1542682439000
         * del : 0
         * cardNum : 871198001
         * merchantName : 数尚地板中华路
         * qdsj : null
         * qdsucess : 0
         * lqsuccess : null
         * lqsj : null
         * isBack : null
         */

        private String customerActionId;
        private String customerName;
        private String customerMobile;
        private String customerSex;
        private String decorationProgress;
        private String decorationStyle;
        private String thinkBuyGood;
        private Object likeColor;
        private String merchantId;
        private String merchantCode;
        private String shengCode;
        private String shengName;
        private String shiCode;
        private String shiName;
        private String quCode;
        private String quName;
        private String address;
        private String managerId;
        private String managerRealName;
        private String managerAcount;
        private String status;
        private Object statusMessage;
        private String customersource;
        private String activityId;
        private String activityName;
        private int activityCode;
        private Object shuoming;
        private Object wechatPageId;
        private String chuangjianren;
        private String xiugairen;
        private long cjsj;
        private long xgsj;
        private String del;
        private String cardNum;
        private String merchantName;
        private Object qdsj;
        private String qdsucess;
        private Object lqsuccess;
        private Object lqsj;
        private Object isBack;

        public String getCustomerActionId() {
            return customerActionId;
        }

        public void setCustomerActionId(String customerActionId) {
            this.customerActionId = customerActionId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getCustomerMobile() {
            return customerMobile;
        }

        public void setCustomerMobile(String customerMobile) {
            this.customerMobile = customerMobile;
        }

        public String getCustomerSex() {
            return customerSex;
        }

        public void setCustomerSex(String customerSex) {
            this.customerSex = customerSex;
        }

        public String getDecorationProgress() {
            return decorationProgress;
        }

        public void setDecorationProgress(String decorationProgress) {
            this.decorationProgress = decorationProgress;
        }

        public String getDecorationStyle() {
            return decorationStyle;
        }

        public void setDecorationStyle(String decorationStyle) {
            this.decorationStyle = decorationStyle;
        }

        public String getThinkBuyGood() {
            return thinkBuyGood;
        }

        public void setThinkBuyGood(String thinkBuyGood) {
            this.thinkBuyGood = thinkBuyGood;
        }

        public Object getLikeColor() {
            return likeColor;
        }

        public void setLikeColor(Object likeColor) {
            this.likeColor = likeColor;
        }

        public String getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(String merchantId) {
            this.merchantId = merchantId;
        }

        public String getMerchantCode() {
            return merchantCode;
        }

        public void setMerchantCode(String merchantCode) {
            this.merchantCode = merchantCode;
        }

        public String getShengCode() {
            return shengCode;
        }

        public void setShengCode(String shengCode) {
            this.shengCode = shengCode;
        }

        public String getShengName() {
            return shengName;
        }

        public void setShengName(String shengName) {
            this.shengName = shengName;
        }

        public String getShiCode() {
            return shiCode;
        }

        public void setShiCode(String shiCode) {
            this.shiCode = shiCode;
        }

        public String getShiName() {
            return shiName;
        }

        public void setShiName(String shiName) {
            this.shiName = shiName;
        }

        public String getQuCode() {
            return quCode;
        }

        public void setQuCode(String quCode) {
            this.quCode = quCode;
        }

        public String getQuName() {
            return quName;
        }

        public void setQuName(String quName) {
            this.quName = quName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getManagerId() {
            return managerId;
        }

        public void setManagerId(String managerId) {
            this.managerId = managerId;
        }

        public String getManagerRealName() {
            return managerRealName;
        }

        public void setManagerRealName(String managerRealName) {
            this.managerRealName = managerRealName;
        }

        public String getManagerAcount() {
            return managerAcount;
        }

        public void setManagerAcount(String managerAcount) {
            this.managerAcount = managerAcount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getStatusMessage() {
            return statusMessage;
        }

        public void setStatusMessage(Object statusMessage) {
            this.statusMessage = statusMessage;
        }

        public String getCustomersource() {
            return customersource;
        }

        public void setCustomersource(String customersource) {
            this.customersource = customersource;
        }

        public String getActivityId() {
            return activityId;
        }

        public void setActivityId(String activityId) {
            this.activityId = activityId;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public int getActivityCode() {
            return activityCode;
        }

        public void setActivityCode(int activityCode) {
            this.activityCode = activityCode;
        }

        public Object getShuoming() {
            return shuoming;
        }

        public void setShuoming(Object shuoming) {
            this.shuoming = shuoming;
        }

        public Object getWechatPageId() {
            return wechatPageId;
        }

        public void setWechatPageId(Object wechatPageId) {
            this.wechatPageId = wechatPageId;
        }

        public String getChuangjianren() {
            return chuangjianren;
        }

        public void setChuangjianren(String chuangjianren) {
            this.chuangjianren = chuangjianren;
        }

        public String getXiugairen() {
            return xiugairen;
        }

        public void setXiugairen(String xiugairen) {
            this.xiugairen = xiugairen;
        }

        public long getCjsj() {
            return cjsj;
        }

        public void setCjsj(long cjsj) {
            this.cjsj = cjsj;
        }

        public long getXgsj() {
            return xgsj;
        }

        public void setXgsj(long xgsj) {
            this.xgsj = xgsj;
        }

        public String getDel() {
            return del;
        }

        public void setDel(String del) {
            this.del = del;
        }

        public String getCardNum() {
            return cardNum;
        }

        public void setCardNum(String cardNum) {
            this.cardNum = cardNum;
        }

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }

        public Object getQdsj() {
            return qdsj;
        }

        public void setQdsj(Object qdsj) {
            this.qdsj = qdsj;
        }

        public String getQdsucess() {
            return qdsucess;
        }

        public void setQdsucess(String qdsucess) {
            this.qdsucess = qdsucess;
        }

        public Object getLqsuccess() {
            return lqsuccess;
        }

        public void setLqsuccess(Object lqsuccess) {
            this.lqsuccess = lqsuccess;
        }

        public Object getLqsj() {
            return lqsj;
        }

        public void setLqsj(Object lqsj) {
            this.lqsj = lqsj;
        }

        public Object getIsBack() {
            return isBack;
        }

        public void setIsBack(Object isBack) {
            this.isBack = isBack;
        }
    }
}
