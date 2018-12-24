package com.shushang.aishangjia.Bean;

import java.util.List;

public class GenJInJiLu {


    /**
     * ret : 200
     * msg : success
     * data : null
     * dataList : [{"time":"2018-11-21 17:15:35","detalInfo":"llll","title":"王苗跟进了王先生的线索","merchantName":"数尚地板中华路"},{"time":"2018-11-14 18:58:18","detalInfo":"多钱啊大","title":"超级管理员创建了阿萨德的线索","merchantName":"数尚地板中华路"},{"time":"2018-11-14 18:57:03","detalInfo":"奥术大师大所大所多","title":"超级管理员跟进了牢记的线索","merchantName":"数尚地板中华路"},{"time":"2018-11-03 11:05:14","detalInfo":"sxadx","title":"超级管理员跟进了张全国的线索","merchantName":"数尚地板中华路"},{"time":"2018-11-03 10:47:30","detalInfo":"uhjj","title":"超级管理员跟进了张全国的线索","merchantName":"数尚地板中华路"},{"time":"2018-11-03 10:45:17","detalInfo":"hfjf","title":"超级管理员跟进了张全国的线索","merchantName":"数尚地板中华路"},{"time":"2018-11-03 10:25:55","detalInfo":"hdhdj","title":"超级管理员跟进了张全国的线索","merchantName":"数尚地板中华路"},{"time":"2018-11-03 10:12:50","detalInfo":"ichxh","title":"超级管理员跟进了张全国的线索","merchantName":"数尚地板中华路"},{"time":"2018-11-03 10:12:20","detalInfo":"ijb","title":"超级管理员跟进了张全国的线索","merchantName":"数尚地板中华路"},{"time":"2018-11-03 10:11:12","detalInfo":"hbbb","title":"超级管理员跟进了张全国的线索","merchantName":"数尚地板中华路"}]
     * currentPage : 0
     * totalCount : 117
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

    public static class DataListBean {
        /**
         * time : 2018-11-21 17:15:35
         * detalInfo : llll
         * title : 王苗跟进了王先生的线索
         * merchantName : 数尚地板中华路
         */

        private String time;
        private String detalInfo;
        private String title;
        private String merchantName;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getDetalInfo() {
            return detalInfo;
        }

        public void setDetalInfo(String detalInfo) {
            this.detalInfo = detalInfo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }
    }
}
