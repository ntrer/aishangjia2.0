package com.shushang.aishangjia.Bean;

public class ClueDetail {


    /**
     * ret : 200
     * msg : success
     * data : {"clueId":"6012b41675ca4a8bb178f3c9eac47bb1","name":"郜","telephone":"18567786665","info":null,"sex":"1","source":"10","address":"万达华府","nowTime":1537052134000,"nowIntention":"6","nextTime":1535731200000,"nextIntention":null,"lastFollowupPerson":null,"status":"6","fuZeRen":null,"fuZeRenName":null,"updateTime":1537052134000,"reasons":null,"merchantId":"0000000065885d7901658de3323b00af","chuangjianren":"0000000065c0f8920165d0d2b3890023","xiugairen":"0000000065c0f8920165d0d2b3890023","cjsj":1537052134000,"xgsj":1537052134000,"del":"0","enable":"1","beizhu":null,"chuangjianrenName":"演示卢佳琳","lastFollowupPersonName":null,"clueActions":null,"intentionGoodName":"与","outTime":null,"isSelf":null,"activityCounts":0,"goodsOrderCounts":0,"totalAmount":0,"avgAmount":"NaN","qdCounts":0,"clueActionCounts":0,"orderCounts":4,"orderTotalAmount":1700}
     * dataList : null
     * currentPage : 0
     * totalCount : 0
     * maxPage : 0
     * pageSize : 0
     * intcurrentPage : 0
     * intpageSize : 0
     * intmaxCount : 0
     * intmaxPage : 0
     * totalPrice : 0.0
     * totalYingshou : 0.0
     * totalShiShou : 0.0
     */

    private String ret;
    private String msg;
    private DataBean data;
    private Object dataList;
    private int currentPage;
    private int totalCount;
    private int maxPage;
    private int pageSize;
    private int intcurrentPage;
    private int intpageSize;
    private int intmaxCount;
    private int intmaxPage;
    private double totalPrice;
    private double totalYingshou;
    private double totalShiShou;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getDataList() {
        return dataList;
    }

    public void setDataList(Object dataList) {
        this.dataList = dataList;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalYingshou() {
        return totalYingshou;
    }

    public void setTotalYingshou(double totalYingshou) {
        this.totalYingshou = totalYingshou;
    }

    public double getTotalShiShou() {
        return totalShiShou;
    }

    public void setTotalShiShou(double totalShiShou) {
        this.totalShiShou = totalShiShou;
    }

    public static class DataBean {
        /**
         * clueId : 6012b41675ca4a8bb178f3c9eac47bb1
         * name : 郜
         * telephone : 18567786665
         * info : null
         * sex : 1
         * source : 10
         * address : 万达华府
         * nowTime : 1537052134000
         * nowIntention : 6
         * nextTime : 1535731200000
         * nextIntention : null
         * lastFollowupPerson : null
         * status : 6
         * fuZeRen : null
         * fuZeRenName : null
         * updateTime : 1537052134000
         * reasons : null
         * merchantId : 0000000065885d7901658de3323b00af
         * chuangjianren : 0000000065c0f8920165d0d2b3890023
         * xiugairen : 0000000065c0f8920165d0d2b3890023
         * cjsj : 1537052134000
         * xgsj : 1537052134000
         * del : 0
         * enable : 1
         * beizhu : null
         * chuangjianrenName : 演示卢佳琳
         * lastFollowupPersonName : null
         * clueActions : null
         * intentionGoodName : 与
         * outTime : null
         * isSelf : null
         * activityCounts : 0
         * goodsOrderCounts : 0
         * totalAmount : 0.0
         * avgAmount : NaN
         * qdCounts : 0
         * clueActionCounts : 0
         * orderCounts : 4
         * orderTotalAmount : 1700.0
         */

        private String clueId;
        private String name;
        private String telephone;
        private Object info;
        private String sex;
        private String source;
        private String address;
        private long nowTime;
        private String nowIntention;
        private long nextTime;
        private Object nextIntention;
        private Object lastFollowupPerson;
        private String status;
        private Object fuZeRen;
        private Object fuZeRenName;
        private long updateTime;
        private Object reasons;
        private String merchantId;
        private String chuangjianren;
        private String xiugairen;
        private long cjsj;
        private long xgsj;
        private String del;
        private String enable;
        private Object beizhu;
        private String chuangjianrenName;
        private Object lastFollowupPersonName;
        private Object clueActions;
        private String intentionGoodName;
        private Object outTime;
        private Object isSelf;
        private int activityCounts;
        private int goodsOrderCounts;
        private double totalAmount;
        private String avgAmount;
        private int qdCounts;
        private int clueActionCounts;
        private int orderCounts;
        private double orderTotalAmount;

        public String getClueId() {
            return clueId;
        }

        public void setClueId(String clueId) {
            this.clueId = clueId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public Object getInfo() {
            return info;
        }

        public void setInfo(Object info) {
            this.info = info;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getNowTime() {
            return nowTime;
        }

        public void setNowTime(long nowTime) {
            this.nowTime = nowTime;
        }

        public String getNowIntention() {
            return nowIntention;
        }

        public void setNowIntention(String nowIntention) {
            this.nowIntention = nowIntention;
        }

        public long getNextTime() {
            return nextTime;
        }

        public void setNextTime(long nextTime) {
            this.nextTime = nextTime;
        }

        public Object getNextIntention() {
            return nextIntention;
        }

        public void setNextIntention(Object nextIntention) {
            this.nextIntention = nextIntention;
        }

        public Object getLastFollowupPerson() {
            return lastFollowupPerson;
        }

        public void setLastFollowupPerson(Object lastFollowupPerson) {
            this.lastFollowupPerson = lastFollowupPerson;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getFuZeRen() {
            return fuZeRen;
        }

        public void setFuZeRen(Object fuZeRen) {
            this.fuZeRen = fuZeRen;
        }

        public Object getFuZeRenName() {
            return fuZeRenName;
        }

        public void setFuZeRenName(Object fuZeRenName) {
            this.fuZeRenName = fuZeRenName;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public Object getReasons() {
            return reasons;
        }

        public void setReasons(Object reasons) {
            this.reasons = reasons;
        }

        public String getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(String merchantId) {
            this.merchantId = merchantId;
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

        public String getEnable() {
            return enable;
        }

        public void setEnable(String enable) {
            this.enable = enable;
        }

        public Object getBeizhu() {
            return beizhu;
        }

        public void setBeizhu(Object beizhu) {
            this.beizhu = beizhu;
        }

        public String getChuangjianrenName() {
            return chuangjianrenName;
        }

        public void setChuangjianrenName(String chuangjianrenName) {
            this.chuangjianrenName = chuangjianrenName;
        }

        public Object getLastFollowupPersonName() {
            return lastFollowupPersonName;
        }

        public void setLastFollowupPersonName(Object lastFollowupPersonName) {
            this.lastFollowupPersonName = lastFollowupPersonName;
        }

        public Object getClueActions() {
            return clueActions;
        }

        public void setClueActions(Object clueActions) {
            this.clueActions = clueActions;
        }

        public String getIntentionGoodName() {
            return intentionGoodName;
        }

        public void setIntentionGoodName(String intentionGoodName) {
            this.intentionGoodName = intentionGoodName;
        }

        public Object getOutTime() {
            return outTime;
        }

        public void setOutTime(Object outTime) {
            this.outTime = outTime;
        }

        public Object getIsSelf() {
            return isSelf;
        }

        public void setIsSelf(Object isSelf) {
            this.isSelf = isSelf;
        }

        public int getActivityCounts() {
            return activityCounts;
        }

        public void setActivityCounts(int activityCounts) {
            this.activityCounts = activityCounts;
        }

        public int getGoodsOrderCounts() {
            return goodsOrderCounts;
        }

        public void setGoodsOrderCounts(int goodsOrderCounts) {
            this.goodsOrderCounts = goodsOrderCounts;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getAvgAmount() {
            return avgAmount;
        }

        public void setAvgAmount(String avgAmount) {
            this.avgAmount = avgAmount;
        }

        public int getQdCounts() {
            return qdCounts;
        }

        public void setQdCounts(int qdCounts) {
            this.qdCounts = qdCounts;
        }

        public int getClueActionCounts() {
            return clueActionCounts;
        }

        public void setClueActionCounts(int clueActionCounts) {
            this.clueActionCounts = clueActionCounts;
        }

        public int getOrderCounts() {
            return orderCounts;
        }

        public void setOrderCounts(int orderCounts) {
            this.orderCounts = orderCounts;
        }

        public double getOrderTotalAmount() {
            return orderTotalAmount;
        }

        public void setOrderTotalAmount(double orderTotalAmount) {
            this.orderTotalAmount = orderTotalAmount;
        }
    }
}
