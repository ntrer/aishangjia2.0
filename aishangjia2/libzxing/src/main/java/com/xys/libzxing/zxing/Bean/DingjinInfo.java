package com.xys.libzxing.zxing.Bean;

public class DingjinInfo {


    /**
     * ret : 200
     * msg : success
     * data : {"orderId":"402880b767bef1650167bf370e640053","customerId":"402880b767ba50d50167bae59c360035","customerName":"刘芒","customerPhone":"13346778856","address":"随意","shengCode":"370000","shengName":null,"shiCode":"370100","shiName":null,"quCode":"370101","quName":null,"customerMerchantId":"0000000065885d7901658fead74600dc","customerMerchantName":"斯美特卫浴伊川华美建材城","customerMerchantCode":"ycsmtwy","customerManagerId":"0000000065885d7901658de3323e00b0","customerManagerName":"sadmin","orderManagerId":"0000000066a993450166af4dadc902c3","orderManagerName":"收银员","orderMerchantId":"0000000065885d7901658fead74600dc","orderMerchantCode":"ycsmtwy","orderMerchantName":"斯美特卫浴伊川华美建材城","status":"0","totalPrice":0.01,"activityId":"402880b767ba50d50167bada33cb001c","chuangjianren":"8af2c2d8f80848ae844adcc95eab5eef","xiugairen":"8af2c2d8f80848ae844adcc95eab5eef","cjsj":1545101315683,"xgsj":1545101315683,"del":"0","enable":"1","orderNum":null,"payType":null,"xdsj":1545101315683,"source":"1","tailMoney":null,"activityName":"测试订金活动","orderNumber":"140-1045070","payStatus":0,"openPay":0}
     * dataList : null
     * intcurrentPage : 0
     * intpageSize : 0
     * intmaxCount : 0
     * intmaxPage : 0
     */

    private String ret;
    private String msg;
    private DataBean data;
    private Object dataList;
    private int intcurrentPage;
    private int intpageSize;
    private int intmaxCount;
    private int intmaxPage;

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

    public static class DataBean {
        /**
         * orderId : 402880b767bef1650167bf370e640053
         * customerId : 402880b767ba50d50167bae59c360035
         * customerName : 刘芒
         * customerPhone : 13346778856
         * address : 随意
         * shengCode : 370000
         * shengName : null
         * shiCode : 370100
         * shiName : null
         * quCode : 370101
         * quName : null
         * customerMerchantId : 0000000065885d7901658fead74600dc
         * customerMerchantName : 斯美特卫浴伊川华美建材城
         * customerMerchantCode : ycsmtwy
         * customerManagerId : 0000000065885d7901658de3323e00b0
         * customerManagerName : sadmin
         * orderManagerId : 0000000066a993450166af4dadc902c3
         * orderManagerName : 收银员
         * orderMerchantId : 0000000065885d7901658fead74600dc
         * orderMerchantCode : ycsmtwy
         * orderMerchantName : 斯美特卫浴伊川华美建材城
         * status : 0
         * totalPrice : 0.01
         * activityId : 402880b767ba50d50167bada33cb001c
         * chuangjianren : 8af2c2d8f80848ae844adcc95eab5eef
         * xiugairen : 8af2c2d8f80848ae844adcc95eab5eef
         * cjsj : 1545101315683
         * xgsj : 1545101315683
         * del : 0
         * enable : 1
         * orderNum : null
         * payType : null
         * xdsj : 1545101315683
         * source : 1
         * tailMoney : null
         * activityName : 测试订金活动
         * orderNumber : 140-1045070
         * payStatus : 0
         * openPay : 0
         */

        private String orderId;
        private String customerId;
        private String customerName;
        private String customerPhone;
        private String address;
        private String shengCode;
        private Object shengName;
        private String shiCode;
        private Object shiName;
        private String quCode;
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
        private String activityId;
        private String chuangjianren;
        private String xiugairen;
        private long cjsj;
        private long xgsj;
        private String del;
        private String enable;
        private Object orderNum;
        private Object payType;
        private long xdsj;
        private String source;
        private Object tailMoney;
        private String activityName;
        private String orderNumber;
        private int payStatus;
        private int openPay;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getShengCode() {
            return shengCode;
        }

        public void setShengCode(String shengCode) {
            this.shengCode = shengCode;
        }

        public Object getShengName() {
            return shengName;
        }

        public void setShengName(Object shengName) {
            this.shengName = shengName;
        }

        public String getShiCode() {
            return shiCode;
        }

        public void setShiCode(String shiCode) {
            this.shiCode = shiCode;
        }

        public Object getShiName() {
            return shiName;
        }

        public void setShiName(Object shiName) {
            this.shiName = shiName;
        }

        public String getQuCode() {
            return quCode;
        }

        public void setQuCode(String quCode) {
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

        public String getActivityId() {
            return activityId;
        }

        public void setActivityId(String activityId) {
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

        public Object getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(Object orderNum) {
            this.orderNum = orderNum;
        }

        public Object getPayType() {
            return payType;
        }

        public void setPayType(Object payType) {
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

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public int getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(int payStatus) {
            this.payStatus = payStatus;
        }

        public int getOpenPay() {
            return openPay;
        }

        public void setOpenPay(int openPay) {
            this.openPay = openPay;
        }
    }
}
