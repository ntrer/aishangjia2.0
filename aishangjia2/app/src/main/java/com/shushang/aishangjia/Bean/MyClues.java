package com.shushang.aishangjia.Bean;

import java.io.Serializable;
import java.util.List;

public class MyClues {

    /**
     * ret : 200
     * msg : success
     * data : null
     * dataList : [{"clueId":"402880b7671b255b01671b39d0b2001b","name":"jjj","telephone":"18637280068","info":"定金","sex":"0","source":"11","address":null,"nowTime":1542767591000,"nowIntention":"6","nextTime":1542853980000,"nextIntention":null,"lastFollowupPerson":"402880b7653b275e01653b812599006d","status":"6","fuZeRen":"402880b7653b275e01653b812599006d","fuZeRenName":"徐珊珊","updateTime":1542767614000,"reasons":null,"merchantId":"402880b7653b275e01653b68e4cf0045","chuangjianren":"402880b7653b275e01653b812599006d","xiugairen":"402880b7653b275e01653b812599006d","cjsj":1542350033000,"xgsj":1542767614000,"del":"0","enable":"1","beizhu":null,"chuangjianrenName":"徐珊珊","lastFollowupPersonName":"徐珊珊","clueActions":null,"intentionGoodName":null,"outTime":null,"isSelf":null,"activityCounts":1,"goodsOrderCounts":0,"totalAmount":0,"avgAmount":0,"qdCounts":0,"clueActionCounts":1}]
     * currentPage : 0
     * totalCount : 1
     * maxPage : 0
     * pageSize : 0
     * intcurrentPage : 0
     * intpageSize : 0
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

    public static class DataListBean implements Serializable {
        /**
         * clueId : 402880b7671b255b01671b39d0b2001b
         * name : jjj
         * telephone : 18637280068
         * info : 定金
         * sex : 0
         * source : 11
         * address : null
         * nowTime : 1542767591000
         * nowIntention : 6
         * nextTime : 1542853980000
         * nextIntention : null
         * lastFollowupPerson : 402880b7653b275e01653b812599006d
         * status : 6
         * fuZeRen : 402880b7653b275e01653b812599006d
         * fuZeRenName : 徐珊珊
         * updateTime : 1542767614000
         * reasons : null
         * merchantId : 402880b7653b275e01653b68e4cf0045
         * chuangjianren : 402880b7653b275e01653b812599006d
         * xiugairen : 402880b7653b275e01653b812599006d
         * cjsj : 1542350033000
         * xgsj : 1542767614000
         * del : 0
         * enable : 1
         * beizhu : null
         * chuangjianrenName : 徐珊珊
         * lastFollowupPersonName : 徐珊珊
         * clueActions : null
         * intentionGoodName : null
         * outTime : null
         * isSelf : null
         * activityCounts : 1
         * goodsOrderCounts : 0
         * totalAmount : 0.0
         * avgAmount : 0.0
         * qdCounts : 0
         * clueActionCounts : 1
         */

        private String clueId;
        private String name;
        private String telephone;
        private String info;
        private String sex;
        private String source;
        private Object address;
        private long nowTime;
        private String nowIntention;
        private long nextTime;
        private Object nextIntention;
        private String lastFollowupPerson;
        private String status;
        private String fuZeRen;
        private String fuZeRenName;
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
        private String lastFollowupPersonName;
        private Object clueActions;
        private Object intentionGoodName;
        private Object outTime;
        private Object isSelf;
        private int activityCounts;
        private int goodsOrderCounts;
        private double totalAmount;
        private double avgAmount;
        private int qdCounts;
        private int clueActionCounts;

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

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
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

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
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

        public String getLastFollowupPerson() {
            return lastFollowupPerson;
        }

        public void setLastFollowupPerson(String lastFollowupPerson) {
            this.lastFollowupPerson = lastFollowupPerson;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getFuZeRen() {
            return fuZeRen;
        }

        public void setFuZeRen(String fuZeRen) {
            this.fuZeRen = fuZeRen;
        }

        public String getFuZeRenName() {
            return fuZeRenName;
        }

        public void setFuZeRenName(String fuZeRenName) {
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

        public String getLastFollowupPersonName() {
            return lastFollowupPersonName;
        }

        public void setLastFollowupPersonName(String lastFollowupPersonName) {
            this.lastFollowupPersonName = lastFollowupPersonName;
        }

        public Object getClueActions() {
            return clueActions;
        }

        public void setClueActions(Object clueActions) {
            this.clueActions = clueActions;
        }

        public Object getIntentionGoodName() {
            return intentionGoodName;
        }

        public void setIntentionGoodName(Object intentionGoodName) {
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

        public double getAvgAmount() {
            return avgAmount;
        }

        public void setAvgAmount(double avgAmount) {
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
    }
}
