package com.shushang.aishangjia.Bean;

import java.util.List;

public class kehudingjin {


    /**
     * ret : 200
     * msg : success
     * data : null
     * dataList : [{"clueId":"402880b76779055a01677922d7f30094","name":"我是你","telephone":"18978980987","order":{"orderId":"402880b76779055a01677922d6420091","customerId":null,"customerName":"我是你","customerPhone":"18978980987","address":null,"shengCode":null,"shengName":null,"shiCode":null,"shiName":null,"quCode":null,"quName":null,"customerMerchantId":"0000000065885d7901658fead74600dc","customerMerchantName":"斯美特卫浴伊川华美建材城","customerMerchantCode":"ycsmtwy","customerManagerId":"0000000065885d7901658fead74c00dd","customerManagerName":"超级管理员","orderManagerId":"0000000065885d7901658fead74c00dd","orderManagerName":"超级管理员","orderMerchantId":"0000000065885d7901658fead74600dc","orderMerchantCode":"ycsmtwy","orderMerchantName":"斯美特卫浴伊川华美建材城","status":"0","totalPrice":1234,"activityId":null,"chuangjianren":"0000000065885d7901658fead74c00dd","xiugairen":"0000000065885d7901658fead74c00dd","cjsj":1543925585000,"xgsj":1543925585000,"del":"0","enable":"1","orderNum":2095,"payType":"1","xdsj":1543925585000,"source":"0","tailMoney":null,"activityName":null}}]
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
    private Object data;
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

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * clueId : 402880b76779055a01677922d7f30094
         * name : 我是你
         * telephone : 18978980987
         * order : {"orderId":"402880b76779055a01677922d6420091","customerId":null,"customerName":"我是你","customerPhone":"18978980987","address":null,"shengCode":null,"shengName":null,"shiCode":null,"shiName":null,"quCode":null,"quName":null,"customerMerchantId":"0000000065885d7901658fead74600dc","customerMerchantName":"斯美特卫浴伊川华美建材城","customerMerchantCode":"ycsmtwy","customerManagerId":"0000000065885d7901658fead74c00dd","customerManagerName":"超级管理员","orderManagerId":"0000000065885d7901658fead74c00dd","orderManagerName":"超级管理员","orderMerchantId":"0000000065885d7901658fead74600dc","orderMerchantCode":"ycsmtwy","orderMerchantName":"斯美特卫浴伊川华美建材城","status":"0","totalPrice":1234,"activityId":null,"chuangjianren":"0000000065885d7901658fead74c00dd","xiugairen":"0000000065885d7901658fead74c00dd","cjsj":1543925585000,"xgsj":1543925585000,"del":"0","enable":"1","orderNum":2095,"payType":"1","xdsj":1543925585000,"source":"0","tailMoney":null,"activityName":null}
         */

        private String clueId;
        private String name;
        private String telephone;
        private OrderBean order;

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

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public static class OrderBean {
            /**
             * orderId : 402880b76779055a01677922d6420091
             * customerId : null
             * customerName : 我是你
             * customerPhone : 18978980987
             * address : null
             * shengCode : null
             * shengName : null
             * shiCode : null
             * shiName : null
             * quCode : null
             * quName : null
             * customerMerchantId : 0000000065885d7901658fead74600dc
             * customerMerchantName : 斯美特卫浴伊川华美建材城
             * customerMerchantCode : ycsmtwy
             * customerManagerId : 0000000065885d7901658fead74c00dd
             * customerManagerName : 超级管理员
             * orderManagerId : 0000000065885d7901658fead74c00dd
             * orderManagerName : 超级管理员
             * orderMerchantId : 0000000065885d7901658fead74600dc
             * orderMerchantCode : ycsmtwy
             * orderMerchantName : 斯美特卫浴伊川华美建材城
             * status : 0
             * totalPrice : 1234.0
             * activityId : null
             * chuangjianren : 0000000065885d7901658fead74c00dd
             * xiugairen : 0000000065885d7901658fead74c00dd
             * cjsj : 1543925585000
             * xgsj : 1543925585000
             * del : 0
             * enable : 1
             * orderNum : 2095
             * payType : 1
             * xdsj : 1543925585000
             * source : 0
             * tailMoney : null
             * activityName : null
             */

            private String orderId;
            private Object customerId;
            private String customerName;
            private String customerPhone;
            private Object address;
            private Object shengCode;
            private Object shengName;
            private Object shiCode;
            private Object shiName;
            private Object quCode;
            private Object quName;
            private String customerMerchantId;
            private String customerMerchantName;
            private String customerMerchantCode;
            private String customerManagerId;
            private String customerManagerName;
            private String orderManagerId;
            private String orderManagerName;
            private String orderMerchantId;
            private String orderMerchantCode;
            private String orderMerchantName;
            private String status;
            private double totalPrice;
            private Object activityId;
            private String chuangjianren;
            private String xiugairen;
            private long cjsj;
            private long xgsj;
            private String del;
            private String enable;
            private int orderNum;
            private String payType;
            private long xdsj;
            private String source;
            private Object tailMoney;
            private Object activityName;

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public Object getCustomerId() {
                return customerId;
            }

            public void setCustomerId(Object customerId) {
                this.customerId = customerId;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getCustomerPhone() {
                return customerPhone;
            }

            public void setCustomerPhone(String customerPhone) {
                this.customerPhone = customerPhone;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public Object getShengCode() {
                return shengCode;
            }

            public void setShengCode(Object shengCode) {
                this.shengCode = shengCode;
            }

            public Object getShengName() {
                return shengName;
            }

            public void setShengName(Object shengName) {
                this.shengName = shengName;
            }

            public Object getShiCode() {
                return shiCode;
            }

            public void setShiCode(Object shiCode) {
                this.shiCode = shiCode;
            }

            public Object getShiName() {
                return shiName;
            }

            public void setShiName(Object shiName) {
                this.shiName = shiName;
            }

            public Object getQuCode() {
                return quCode;
            }

            public void setQuCode(Object quCode) {
                this.quCode = quCode;
            }

            public Object getQuName() {
                return quName;
            }

            public void setQuName(Object quName) {
                this.quName = quName;
            }

            public String getCustomerMerchantId() {
                return customerMerchantId;
            }

            public void setCustomerMerchantId(String customerMerchantId) {
                this.customerMerchantId = customerMerchantId;
            }

            public String getCustomerMerchantName() {
                return customerMerchantName;
            }

            public void setCustomerMerchantName(String customerMerchantName) {
                this.customerMerchantName = customerMerchantName;
            }

            public String getCustomerMerchantCode() {
                return customerMerchantCode;
            }

            public void setCustomerMerchantCode(String customerMerchantCode) {
                this.customerMerchantCode = customerMerchantCode;
            }

            public String getCustomerManagerId() {
                return customerManagerId;
            }

            public void setCustomerManagerId(String customerManagerId) {
                this.customerManagerId = customerManagerId;
            }

            public String getCustomerManagerName() {
                return customerManagerName;
            }

            public void setCustomerManagerName(String customerManagerName) {
                this.customerManagerName = customerManagerName;
            }

            public String getOrderManagerId() {
                return orderManagerId;
            }

            public void setOrderManagerId(String orderManagerId) {
                this.orderManagerId = orderManagerId;
            }

            public String getOrderManagerName() {
                return orderManagerName;
            }

            public void setOrderManagerName(String orderManagerName) {
                this.orderManagerName = orderManagerName;
            }

            public String getOrderMerchantId() {
                return orderMerchantId;
            }

            public void setOrderMerchantId(String orderMerchantId) {
                this.orderMerchantId = orderMerchantId;
            }

            public String getOrderMerchantCode() {
                return orderMerchantCode;
            }

            public void setOrderMerchantCode(String orderMerchantCode) {
                this.orderMerchantCode = orderMerchantCode;
            }

            public String getOrderMerchantName() {
                return orderMerchantName;
            }

            public void setOrderMerchantName(String orderMerchantName) {
                this.orderMerchantName = orderMerchantName;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public double getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(double totalPrice) {
                this.totalPrice = totalPrice;
            }

            public Object getActivityId() {
                return activityId;
            }

            public void setActivityId(Object activityId) {
                this.activityId = activityId;
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

            public int getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(int orderNum) {
                this.orderNum = orderNum;
            }

            public String getPayType() {
                return payType;
            }

            public void setPayType(String payType) {
                this.payType = payType;
            }

            public long getXdsj() {
                return xdsj;
            }

            public void setXdsj(long xdsj) {
                this.xdsj = xdsj;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public Object getTailMoney() {
                return tailMoney;
            }

            public void setTailMoney(Object tailMoney) {
                this.tailMoney = tailMoney;
            }

            public Object getActivityName() {
                return activityName;
            }

            public void setActivityName(Object activityName) {
                this.activityName = activityName;
            }
        }
    }
}
