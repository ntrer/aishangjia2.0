package com.shushang.aishangjia.Bean;

import java.util.List;

public class ClueGoods {


    /**
     * ret : 200
     * msg : success
     * data : null
     * dataList : [{"clueId":"402880b76730231c016733cf23740068","name":"yuyuy","telephone":"13858677654","goodsOrder":{"goodsorderId":"402880b76730231c016733cf23730064","goodsorderNum":142,"goodsorderCode":null,"orderMerchantId":"402880b7653b275e01653b69cc770050","orderManagerId":"402880b7653b275e01653d0285c901cd","orderManagerName":"徐珊珊","customerId":null,"customerName":"yuyuy","customerPhone":"13858677654","customerMerchantId":null,"customerManagerId":null,"orderIntentIds":null,"totalPrice":945,"dikoujia":0,"yingshou":945,"shishou":800,"orderTime":1542762472000,"chuangjianren":"402880b7653b275e01653d0285c901cd","xiugairen":"402880b7653b275e01653d0285c901cd","cjsj":1542762472000,"xgsj":1542762472000,"del":"0","enable":"1","payType":null,"beizhu":"rqtewriyuqwterui","merchantName":null,"activityId":null,"activityName":null,"orders":[],"goods":[{"ordergoodsId":"402880b76730231c016733cf23730065","goodsorderId":"402880b76730231c016733cf23730064","goodsId":"402880b765fb56450165fb5ff89e001c","goodsName":"生活家","goodsCode":"10064","goodsSpecs":"DC122橡木月白色","goodsDescription":"除醛环保 耐磨 花色大方","goodsUnit":"平米","goodsImages":"402880b765fb56450165fb5ff65e001b","goodsColor":null,"goodsModelType":null,"goodsRetailPrice":418,"goodsWholesalePrice":219,"merchantId":"402880b7653b275e01653b69cc770050","merchantCode":"ssdb","merchantName":"数尚地板中华路","beizhu":"送货入户（长途运输+同城配送+步梯≤1楼免费搬楼（电梯入户免费搬楼））+上门安装（包含收边条、防潮膜、胶垫安装费用，实木地板同时包含龙骨铺装费）。","goodsCount":1,"discount":1,"discountPrice":418,"totalPrice":418,"chuangjianren":"402880b7653b275e01653d0285c901cd","xiugairen":"402880b7653b275e01653d0285c901cd","cjsj":1542762472000,"xgsj":1542762472000,"del":"0","enable":"1"},{"ordergoodsId":"402880b76730231c016733cf23730066","goodsorderId":"402880b76730231c016733cf23730064","goodsId":"402880b765fb56450165fb5c790b000f","goodsName":"居之星竹地板","goodsCode":"ER1","goodsSpecs":"对节哑光本色","goodsDescription":"商家承诺：购买\u201c预约安装\u201d服务，如未履行服务可获得赔付","goodsUnit":"平米","goodsImages":"402880b765fb56450165fb5c48ce000e","goodsColor":null,"goodsModelType":null,"goodsRetailPrice":248,"goodsWholesalePrice":128,"merchantId":"402880b7653b275e01653b69cc770050","merchantCode":"ssdb","merchantName":"数尚地板中华路","beizhu":"竹地板特别漂亮，地板和寄过来的小样一样，质量好价格经济实惠，卖家服务态度很好","goodsCount":1,"discount":1,"discountPrice":248,"totalPrice":248,"chuangjianren":"402880b7653b275e01653d0285c901cd","xiugairen":"402880b7653b275e01653d0285c901cd","cjsj":1542762472000,"xgsj":1542762472000,"del":"0","enable":"1"},{"ordergoodsId":"402880b76730231c016733cf23730067","goodsorderId":"402880b76730231c016733cf23730064","goodsId":"402880b765fb56450165fb58b4170009","goodsName":"包安装 大自然地板强化复合地板家用木地板悦享优选系列","goodsCode":"E1","goodsSpecs":"悦享优选系列","goodsDescription":"包安装包辅料 前2小时减3元/平 立即抢购","goodsUnit":"平米","goodsImages":"402880b765fb56450165fb5896610008","goodsColor":null,"goodsModelType":null,"goodsRetailPrice":279,"goodsWholesalePrice":109,"merchantId":"402880b7653b275e01653b69cc770050","merchantCode":"ssdb","merchantName":"数尚地板中华路","beizhu":"送货入户（长途运输+同城配送+步梯≤1楼免费搬楼（电梯入户免费搬楼））+上门安装（包含收边条、防潮膜、胶垫安装费用，实木地板同时包含龙骨铺装费）。","goodsCount":1,"discount":1,"discountPrice":279,"totalPrice":279,"chuangjianren":"402880b7653b275e01653d0285c901cd","xiugairen":"402880b7653b275e01653d0285c901cd","cjsj":1542762472000,"xgsj":1542762472000,"del":"0","enable":"1"}],"goodsSize":0}}]
     * currentPage : 0
     * totalCount : 0
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
         * clueId : 402880b76730231c016733cf23740068
         * name : yuyuy
         * telephone : 13858677654
         * goodsOrder : {"goodsorderId":"402880b76730231c016733cf23730064","goodsorderNum":142,"goodsorderCode":null,"orderMerchantId":"402880b7653b275e01653b69cc770050","orderManagerId":"402880b7653b275e01653d0285c901cd","orderManagerName":"徐珊珊","customerId":null,"customerName":"yuyuy","customerPhone":"13858677654","customerMerchantId":null,"customerManagerId":null,"orderIntentIds":null,"totalPrice":945,"dikoujia":0,"yingshou":945,"shishou":800,"orderTime":1542762472000,"chuangjianren":"402880b7653b275e01653d0285c901cd","xiugairen":"402880b7653b275e01653d0285c901cd","cjsj":1542762472000,"xgsj":1542762472000,"del":"0","enable":"1","payType":null,"beizhu":"rqtewriyuqwterui","merchantName":null,"activityId":null,"activityName":null,"orders":[],"goods":[{"ordergoodsId":"402880b76730231c016733cf23730065","goodsorderId":"402880b76730231c016733cf23730064","goodsId":"402880b765fb56450165fb5ff89e001c","goodsName":"生活家","goodsCode":"10064","goodsSpecs":"DC122橡木月白色","goodsDescription":"除醛环保 耐磨 花色大方","goodsUnit":"平米","goodsImages":"402880b765fb56450165fb5ff65e001b","goodsColor":null,"goodsModelType":null,"goodsRetailPrice":418,"goodsWholesalePrice":219,"merchantId":"402880b7653b275e01653b69cc770050","merchantCode":"ssdb","merchantName":"数尚地板中华路","beizhu":"送货入户（长途运输+同城配送+步梯≤1楼免费搬楼（电梯入户免费搬楼））+上门安装（包含收边条、防潮膜、胶垫安装费用，实木地板同时包含龙骨铺装费）。","goodsCount":1,"discount":1,"discountPrice":418,"totalPrice":418,"chuangjianren":"402880b7653b275e01653d0285c901cd","xiugairen":"402880b7653b275e01653d0285c901cd","cjsj":1542762472000,"xgsj":1542762472000,"del":"0","enable":"1"},{"ordergoodsId":"402880b76730231c016733cf23730066","goodsorderId":"402880b76730231c016733cf23730064","goodsId":"402880b765fb56450165fb5c790b000f","goodsName":"居之星竹地板","goodsCode":"ER1","goodsSpecs":"对节哑光本色","goodsDescription":"商家承诺：购买\u201c预约安装\u201d服务，如未履行服务可获得赔付","goodsUnit":"平米","goodsImages":"402880b765fb56450165fb5c48ce000e","goodsColor":null,"goodsModelType":null,"goodsRetailPrice":248,"goodsWholesalePrice":128,"merchantId":"402880b7653b275e01653b69cc770050","merchantCode":"ssdb","merchantName":"数尚地板中华路","beizhu":"竹地板特别漂亮，地板和寄过来的小样一样，质量好价格经济实惠，卖家服务态度很好","goodsCount":1,"discount":1,"discountPrice":248,"totalPrice":248,"chuangjianren":"402880b7653b275e01653d0285c901cd","xiugairen":"402880b7653b275e01653d0285c901cd","cjsj":1542762472000,"xgsj":1542762472000,"del":"0","enable":"1"},{"ordergoodsId":"402880b76730231c016733cf23730067","goodsorderId":"402880b76730231c016733cf23730064","goodsId":"402880b765fb56450165fb58b4170009","goodsName":"包安装 大自然地板强化复合地板家用木地板悦享优选系列","goodsCode":"E1","goodsSpecs":"悦享优选系列","goodsDescription":"包安装包辅料 前2小时减3元/平 立即抢购","goodsUnit":"平米","goodsImages":"402880b765fb56450165fb5896610008","goodsColor":null,"goodsModelType":null,"goodsRetailPrice":279,"goodsWholesalePrice":109,"merchantId":"402880b7653b275e01653b69cc770050","merchantCode":"ssdb","merchantName":"数尚地板中华路","beizhu":"送货入户（长途运输+同城配送+步梯≤1楼免费搬楼（电梯入户免费搬楼））+上门安装（包含收边条、防潮膜、胶垫安装费用，实木地板同时包含龙骨铺装费）。","goodsCount":1,"discount":1,"discountPrice":279,"totalPrice":279,"chuangjianren":"402880b7653b275e01653d0285c901cd","xiugairen":"402880b7653b275e01653d0285c901cd","cjsj":1542762472000,"xgsj":1542762472000,"del":"0","enable":"1"}],"goodsSize":0}
         */

        private String clueId;
        private String name;
        private String telephone;
        private GoodsOrderBean goodsOrder;

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

        public GoodsOrderBean getGoodsOrder() {
            return goodsOrder;
        }

        public void setGoodsOrder(GoodsOrderBean goodsOrder) {
            this.goodsOrder = goodsOrder;
        }

        public static class GoodsOrderBean {
            /**
             * goodsorderId : 402880b76730231c016733cf23730064
             * goodsorderNum : 142
             * goodsorderCode : null
             * orderMerchantId : 402880b7653b275e01653b69cc770050
             * orderManagerId : 402880b7653b275e01653d0285c901cd
             * orderManagerName : 徐珊珊
             * customerId : null
             * customerName : yuyuy
             * customerPhone : 13858677654
             * customerMerchantId : null
             * customerManagerId : null
             * orderIntentIds : null
             * totalPrice : 945.0
             * dikoujia : 0.0
             * yingshou : 945.0
             * shishou : 800.0
             * orderTime : 1542762472000
             * chuangjianren : 402880b7653b275e01653d0285c901cd
             * xiugairen : 402880b7653b275e01653d0285c901cd
             * cjsj : 1542762472000
             * xgsj : 1542762472000
             * del : 0
             * enable : 1
             * payType : null
             * beizhu : rqtewriyuqwterui
             * merchantName : null
             * activityId : null
             * activityName : null
             * orders : []
             * goods : [{"ordergoodsId":"402880b76730231c016733cf23730065","goodsorderId":"402880b76730231c016733cf23730064","goodsId":"402880b765fb56450165fb5ff89e001c","goodsName":"生活家","goodsCode":"10064","goodsSpecs":"DC122橡木月白色","goodsDescription":"除醛环保 耐磨 花色大方","goodsUnit":"平米","goodsImages":"402880b765fb56450165fb5ff65e001b","goodsColor":null,"goodsModelType":null,"goodsRetailPrice":418,"goodsWholesalePrice":219,"merchantId":"402880b7653b275e01653b69cc770050","merchantCode":"ssdb","merchantName":"数尚地板中华路","beizhu":"送货入户（长途运输+同城配送+步梯≤1楼免费搬楼（电梯入户免费搬楼））+上门安装（包含收边条、防潮膜、胶垫安装费用，实木地板同时包含龙骨铺装费）。","goodsCount":1,"discount":1,"discountPrice":418,"totalPrice":418,"chuangjianren":"402880b7653b275e01653d0285c901cd","xiugairen":"402880b7653b275e01653d0285c901cd","cjsj":1542762472000,"xgsj":1542762472000,"del":"0","enable":"1"},{"ordergoodsId":"402880b76730231c016733cf23730066","goodsorderId":"402880b76730231c016733cf23730064","goodsId":"402880b765fb56450165fb5c790b000f","goodsName":"居之星竹地板","goodsCode":"ER1","goodsSpecs":"对节哑光本色","goodsDescription":"商家承诺：购买\u201c预约安装\u201d服务，如未履行服务可获得赔付","goodsUnit":"平米","goodsImages":"402880b765fb56450165fb5c48ce000e","goodsColor":null,"goodsModelType":null,"goodsRetailPrice":248,"goodsWholesalePrice":128,"merchantId":"402880b7653b275e01653b69cc770050","merchantCode":"ssdb","merchantName":"数尚地板中华路","beizhu":"竹地板特别漂亮，地板和寄过来的小样一样，质量好价格经济实惠，卖家服务态度很好","goodsCount":1,"discount":1,"discountPrice":248,"totalPrice":248,"chuangjianren":"402880b7653b275e01653d0285c901cd","xiugairen":"402880b7653b275e01653d0285c901cd","cjsj":1542762472000,"xgsj":1542762472000,"del":"0","enable":"1"},{"ordergoodsId":"402880b76730231c016733cf23730067","goodsorderId":"402880b76730231c016733cf23730064","goodsId":"402880b765fb56450165fb58b4170009","goodsName":"包安装 大自然地板强化复合地板家用木地板悦享优选系列","goodsCode":"E1","goodsSpecs":"悦享优选系列","goodsDescription":"包安装包辅料 前2小时减3元/平 立即抢购","goodsUnit":"平米","goodsImages":"402880b765fb56450165fb5896610008","goodsColor":null,"goodsModelType":null,"goodsRetailPrice":279,"goodsWholesalePrice":109,"merchantId":"402880b7653b275e01653b69cc770050","merchantCode":"ssdb","merchantName":"数尚地板中华路","beizhu":"送货入户（长途运输+同城配送+步梯≤1楼免费搬楼（电梯入户免费搬楼））+上门安装（包含收边条、防潮膜、胶垫安装费用，实木地板同时包含龙骨铺装费）。","goodsCount":1,"discount":1,"discountPrice":279,"totalPrice":279,"chuangjianren":"402880b7653b275e01653d0285c901cd","xiugairen":"402880b7653b275e01653d0285c901cd","cjsj":1542762472000,"xgsj":1542762472000,"del":"0","enable":"1"}]
             * goodsSize : 0
             */

            private String goodsorderId;
            private int goodsorderNum;
            private Object goodsorderCode;
            private String orderMerchantId;
            private String orderManagerId;
            private String orderManagerName;
            private Object customerId;
            private String customerName;
            private String customerPhone;
            private Object customerMerchantId;
            private Object customerManagerId;
            private Object orderIntentIds;
            private double totalPrice;
            private double dikoujia;
            private double yingshou;
            private double shishou;
            private long orderTime;
            private String chuangjianren;
            private String xiugairen;
            private long cjsj;
            private long xgsj;
            private String del;
            private String enable;
            private Object payType;
            private String beizhu;
            private Object merchantName;
            private Object activityId;
            private Object activityName;
            private int goodsSize;
            private List<?> orders;
            private List<GoodsBean> goods;

            public String getGoodsorderId() {
                return goodsorderId;
            }

            public void setGoodsorderId(String goodsorderId) {
                this.goodsorderId = goodsorderId;
            }

            public int getGoodsorderNum() {
                return goodsorderNum;
            }

            public void setGoodsorderNum(int goodsorderNum) {
                this.goodsorderNum = goodsorderNum;
            }

            public Object getGoodsorderCode() {
                return goodsorderCode;
            }

            public void setGoodsorderCode(Object goodsorderCode) {
                this.goodsorderCode = goodsorderCode;
            }

            public String getOrderMerchantId() {
                return orderMerchantId;
            }

            public void setOrderMerchantId(String orderMerchantId) {
                this.orderMerchantId = orderMerchantId;
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

            public Object getCustomerMerchantId() {
                return customerMerchantId;
            }

            public void setCustomerMerchantId(Object customerMerchantId) {
                this.customerMerchantId = customerMerchantId;
            }

            public Object getCustomerManagerId() {
                return customerManagerId;
            }

            public void setCustomerManagerId(Object customerManagerId) {
                this.customerManagerId = customerManagerId;
            }

            public Object getOrderIntentIds() {
                return orderIntentIds;
            }

            public void setOrderIntentIds(Object orderIntentIds) {
                this.orderIntentIds = orderIntentIds;
            }

            public double getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(double totalPrice) {
                this.totalPrice = totalPrice;
            }

            public double getDikoujia() {
                return dikoujia;
            }

            public void setDikoujia(double dikoujia) {
                this.dikoujia = dikoujia;
            }

            public double getYingshou() {
                return yingshou;
            }

            public void setYingshou(double yingshou) {
                this.yingshou = yingshou;
            }

            public double getShishou() {
                return shishou;
            }

            public void setShishou(double shishou) {
                this.shishou = shishou;
            }

            public long getOrderTime() {
                return orderTime;
            }

            public void setOrderTime(long orderTime) {
                this.orderTime = orderTime;
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

            public Object getPayType() {
                return payType;
            }

            public void setPayType(Object payType) {
                this.payType = payType;
            }

            public String getBeizhu() {
                return beizhu;
            }

            public void setBeizhu(String beizhu) {
                this.beizhu = beizhu;
            }

            public Object getMerchantName() {
                return merchantName;
            }

            public void setMerchantName(Object merchantName) {
                this.merchantName = merchantName;
            }

            public Object getActivityId() {
                return activityId;
            }

            public void setActivityId(Object activityId) {
                this.activityId = activityId;
            }

            public Object getActivityName() {
                return activityName;
            }

            public void setActivityName(Object activityName) {
                this.activityName = activityName;
            }

            public int getGoodsSize() {
                return goodsSize;
            }

            public void setGoodsSize(int goodsSize) {
                this.goodsSize = goodsSize;
            }

            public List<?> getOrders() {
                return orders;
            }

            public void setOrders(List<?> orders) {
                this.orders = orders;
            }

            public List<GoodsBean> getGoods() {
                return goods;
            }

            public void setGoods(List<GoodsBean> goods) {
                this.goods = goods;
            }

            public static class GoodsBean {
                /**
                 * ordergoodsId : 402880b76730231c016733cf23730065
                 * goodsorderId : 402880b76730231c016733cf23730064
                 * goodsId : 402880b765fb56450165fb5ff89e001c
                 * goodsName : 生活家
                 * goodsCode : 10064
                 * goodsSpecs : DC122橡木月白色
                 * goodsDescription : 除醛环保 耐磨 花色大方
                 * goodsUnit : 平米
                 * goodsImages : 402880b765fb56450165fb5ff65e001b
                 * goodsColor : null
                 * goodsModelType : null
                 * goodsRetailPrice : 418.0
                 * goodsWholesalePrice : 219.0
                 * merchantId : 402880b7653b275e01653b69cc770050
                 * merchantCode : ssdb
                 * merchantName : 数尚地板中华路
                 * beizhu : 送货入户（长途运输+同城配送+步梯≤1楼免费搬楼（电梯入户免费搬楼））+上门安装（包含收边条、防潮膜、胶垫安装费用，实木地板同时包含龙骨铺装费）。
                 * goodsCount : 1
                 * discount : 1.0
                 * discountPrice : 418.0
                 * totalPrice : 418.0
                 * chuangjianren : 402880b7653b275e01653d0285c901cd
                 * xiugairen : 402880b7653b275e01653d0285c901cd
                 * cjsj : 1542762472000
                 * xgsj : 1542762472000
                 * del : 0
                 * enable : 1
                 */

                private String ordergoodsId;
                private String goodsorderId;
                private String goodsId;
                private String goodsName;
                private String goodsCode;
                private String goodsSpecs;
                private String goodsDescription;
                private String goodsUnit;
                private String goodsImages;
                private Object goodsColor;
                private Object goodsModelType;
                private double goodsRetailPrice;
                private double goodsWholesalePrice;
                private String merchantId;
                private String merchantCode;
                private String merchantName;
                private String beizhu;
                private int goodsCount;
                private double discount;
                private double discountPrice;
                private double totalPrice;
                private String chuangjianren;
                private String xiugairen;
                private long cjsj;
                private long xgsj;
                private String del;
                private String enable;

                public String getOrdergoodsId() {
                    return ordergoodsId;
                }

                public void setOrdergoodsId(String ordergoodsId) {
                    this.ordergoodsId = ordergoodsId;
                }

                public String getGoodsorderId() {
                    return goodsorderId;
                }

                public void setGoodsorderId(String goodsorderId) {
                    this.goodsorderId = goodsorderId;
                }

                public String getGoodsId() {
                    return goodsId;
                }

                public void setGoodsId(String goodsId) {
                    this.goodsId = goodsId;
                }

                public String getGoodsName() {
                    return goodsName;
                }

                public void setGoodsName(String goodsName) {
                    this.goodsName = goodsName;
                }

                public String getGoodsCode() {
                    return goodsCode;
                }

                public void setGoodsCode(String goodsCode) {
                    this.goodsCode = goodsCode;
                }

                public String getGoodsSpecs() {
                    return goodsSpecs;
                }

                public void setGoodsSpecs(String goodsSpecs) {
                    this.goodsSpecs = goodsSpecs;
                }

                public String getGoodsDescription() {
                    return goodsDescription;
                }

                public void setGoodsDescription(String goodsDescription) {
                    this.goodsDescription = goodsDescription;
                }

                public String getGoodsUnit() {
                    return goodsUnit;
                }

                public void setGoodsUnit(String goodsUnit) {
                    this.goodsUnit = goodsUnit;
                }

                public String getGoodsImages() {
                    return goodsImages;
                }

                public void setGoodsImages(String goodsImages) {
                    this.goodsImages = goodsImages;
                }

                public Object getGoodsColor() {
                    return goodsColor;
                }

                public void setGoodsColor(Object goodsColor) {
                    this.goodsColor = goodsColor;
                }

                public Object getGoodsModelType() {
                    return goodsModelType;
                }

                public void setGoodsModelType(Object goodsModelType) {
                    this.goodsModelType = goodsModelType;
                }

                public double getGoodsRetailPrice() {
                    return goodsRetailPrice;
                }

                public void setGoodsRetailPrice(double goodsRetailPrice) {
                    this.goodsRetailPrice = goodsRetailPrice;
                }

                public double getGoodsWholesalePrice() {
                    return goodsWholesalePrice;
                }

                public void setGoodsWholesalePrice(double goodsWholesalePrice) {
                    this.goodsWholesalePrice = goodsWholesalePrice;
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

                public String getMerchantName() {
                    return merchantName;
                }

                public void setMerchantName(String merchantName) {
                    this.merchantName = merchantName;
                }

                public String getBeizhu() {
                    return beizhu;
                }

                public void setBeizhu(String beizhu) {
                    this.beizhu = beizhu;
                }

                public int getGoodsCount() {
                    return goodsCount;
                }

                public void setGoodsCount(int goodsCount) {
                    this.goodsCount = goodsCount;
                }

                public double getDiscount() {
                    return discount;
                }

                public void setDiscount(double discount) {
                    this.discount = discount;
                }

                public double getDiscountPrice() {
                    return discountPrice;
                }

                public void setDiscountPrice(double discountPrice) {
                    this.discountPrice = discountPrice;
                }

                public double getTotalPrice() {
                    return totalPrice;
                }

                public void setTotalPrice(double totalPrice) {
                    this.totalPrice = totalPrice;
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
            }
        }
    }
}
