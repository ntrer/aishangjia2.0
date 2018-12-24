package com.shushang.aishangjia.Bean;

import java.util.List;

/**
 * Created by YD on 2018/7/23.
 */

public class Login {


    /**
     * ret : 200
     * msg : success
     * data : {"token_id":"9feec9a05ab541f79bfed531b0cca0f8","xingming":"董程远","shoujihao":"15289456733","touxiang":"","type":"5","shangjia_code":"yczxgs","shangjia_id":"0000000065885d7901658de3323b00af","shangjia_name":"伊川执行公司伊川华美建材城","activity_id":null,"resources":[{"resourceId":"401230b765288f58016528ad649b0012","resourceNum":"126","resourceType":null,"resourceName":"(用户)查看当前登录执行公司的所有用户(执行公司)","resourceIcon":null,"sortId":126,"url":"userController.do?method=getUsersByMerchantId","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985777000,"xgsj":1533985777000,"merchantEnable":null,"companyEnable":null},{"resourceId":"402130b765288f580165292d07260061","resourceNum":"204","resourceType":null,"resourceName":"(活动)查看某场活动详情(执行公司)","resourceIcon":null,"sortId":204,"url":"activityController.do?method=getActivityByActivityId","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533994141000,"xgsj":1533994141000,"merchantEnable":null,"companyEnable":null},{"resourceId":"402170b765288f580165292f00bd0065","resourceNum":"208","resourceType":null,"resourceName":"(活动游戏)查看游戏列表(执行公司)","resourceIcon":null,"sortId":208,"url":"activityGameController.do?method=getGames","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533994271000,"xgsj":1533994271000,"merchantEnable":null,"companyEnable":null},{"resourceId":"402140b765288f580165292d735f0062","resourceNum":"205","resourceType":null,"resourceName":"(活动)下载某场活动下的所有商户二维码(执行公司)","resourceIcon":null,"sortId":205,"url":"activityController.do?method=downLoadQRCode","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533994169000,"xgsj":1533994169000,"merchantEnable":null,"companyEnable":null},{"resourceId":"68fc285ae93a11e89f32f2801f1b9fd1","resourceNum":"245","resourceType":null,"resourceName":"活动 保存/修改订单(执行公司)","resourceIcon":null,"sortId":245,"url":"orderController.do?method=saveOrderByAc","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985222000,"xgsj":1533985222000,"merchantEnable":null,"companyEnable":"2"},{"resourceId":"4d1e7e8178494785956bbd871f770ee7","resourceNum":"216","resourceType":null,"resourceName":"(上架商品)查看上架商品详情(执行公司)","resourceIcon":null,"sortId":216,"url":"onShelfHotgoodsController.do?method=getOnShelfHotgoodsById","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985404000,"xgsj":1533985404000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401430b765288f580165290dc1870027","resourceNum":"146","resourceType":null,"resourceName":"(意向金)查看意向金订单的备注(执行公司)","resourceIcon":null,"sortId":146,"url":"orderController.do?method=getOrderRemarksByPage","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992092000,"xgsj":1533992092000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401650b765288f580165291b568a0041","resourceNum":"171","resourceType":null,"resourceName":"(客户信息)客户手机短信验证签到(执行公司)","resourceIcon":null,"sortId":171,"url":"customerActionController.do?method=customerActionToQd","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992982000,"xgsj":1533992982000,"merchantEnable":null,"companyEnable":null},{"resourceId":"0c8cad8317a64f8895befdf465927474","resourceNum":"213","resourceType":null,"resourceName":"(上架商品)添加修改上架商品(执行公司)","resourceIcon":null,"sortId":213,"url":"onShelfHotgoodsController.do?method=saveOrUpdateOnShelfHotgoods","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985222000,"xgsj":1533985222000,"merchantEnable":null,"companyEnable":null},{"resourceId":"a32831e0e93a11e89f32f2801f1b9fd1","resourceNum":"246","resourceType":null,"resourceName":"活动现场扫码返回信息(执行公司)","resourceIcon":null,"sortId":246,"url":"orderController.do?method=getInfoByScanning","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985222000,"xgsj":1533985222000,"merchantEnable":null,"companyEnable":"2"},{"resourceId":"401120b765288f580165289c26bc0003","resourceNum":"113","resourceType":null,"resourceName":"(角色)查看角色拥有的所有权限(执行公司)","resourceIcon":null,"sortId":113,"url":"resouceController.do?method=getResoucesByRoleId","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533984647000,"xgsj":1533984647000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401630b765288f580165291a5992003f","resourceNum":"169","resourceType":null,"resourceName":"(客户信息)查看单条客户信息详情(执行公司)","resourceIcon":null,"sortId":169,"url":"customerActionController.do?method=getCustomerActionById","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992917000,"xgsj":1533992917000,"merchantEnable":null,"companyEnable":null},{"resourceId":"1e06b1670fbb4ea58ea3c15a03bae3b6","resourceNum":"212","resourceType":null,"resourceName":"(上架商品)查看上架商品(执行公司)","resourceIcon":null,"sortId":212,"url":"onShelfHotgoodsController.do?method=getOnShelfHotgoods","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985173000,"xgsj":1533985173000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401610b765288f58016529197e6c003d","resourceNum":"167","resourceType":null,"resourceName":"(客户信息)添加或者修改客户信息(执行公司)","resourceIcon":null,"sortId":167,"url":"customerActionController.do?method=saveOrUpdateCustomerAction","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992861000,"xgsj":1533992861000,"merchantEnable":null,"companyEnable":null},{"resourceId":"402050b765288f580165292aec7c005e","resourceNum":"201","resourceType":null,"resourceName":"(活动)删除活动(执行公司)","resourceIcon":null,"sortId":201,"url":"activityController.do?method=delActivity","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533994004000,"xgsj":1533994004000,"merchantEnable":null,"companyEnable":null},{"resourceId":"a3283474e93a11e89f32f2801f1b9fd1","resourceNum":"248","resourceType":null,"resourceName":"(活动)查看已签到客户列表(执行公司)","resourceIcon":null,"sortId":248,"url":"customerActionController.do?method=getQdCustomerActions","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985222000,"xgsj":1533985222000,"merchantEnable":null,"companyEnable":"1"},{"resourceId":"9b5f13b8e93811e89f32f2801f1b9fd1","resourceNum":"243","resourceType":null,"resourceName":"web端签到(执行公司)","resourceIcon":null,"sortId":243,"url":"orderController.do?method=singin","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985222000,"xgsj":1533985222000,"merchantEnable":null,"companyEnable":"1"},{"resourceId":"9b5f112ee93811e89f32f2801f1b9fd1","resourceNum":"240","resourceType":null,"resourceName":"客户手机验证码签到(执行公司)","resourceIcon":null,"sortId":240,"url":"customerActionController.do?method=customerActionToQd","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985222000,"xgsj":1533985222000,"merchantEnable":null,"companyEnable":"1"},{"resourceId":"401400b765288f580165290bab7c0024","resourceNum":"143","resourceType":null,"resourceName":"(意向金)保存或修改活动意向金订单(执行公司)","resourceIcon":null,"sortId":143,"url":"orderController.do?method=saveOrderByAc","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533991955000,"xgsj":1533991955000,"merchantEnable":null,"companyEnable":null},{"resourceId":"402060b765288f580165292c2683005f","resourceNum":"202","resourceType":null,"resourceName":"(活动)查看某场活动的最大售卡数量(执行公司)","resourceIcon":null,"sortId":202,"url":"activityController.do?method=getMerchantsByActivityId","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533994084000,"xgsj":1533994084000,"merchantEnable":null,"companyEnable":null},{"resourceId":"9b5f1264e93811e89f32f2801f1b9fd1","resourceNum":"241","resourceType":null,"resourceName":"web端领取奖品(执行公司)","resourceIcon":null,"sortId":241,"url":"orderController.do?method=scanningCoupon","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985222000,"xgsj":1533985222000,"merchantEnable":null,"companyEnable":"3"},{"resourceId":"402080b765288f58016528a275270005","resourceNum":"115","resourceType":null,"resourceName":"(角色)查看角色列表(执行公司)","resourceIcon":null,"sortId":115,"url":"roleController.do?method=getRoles","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985060000,"xgsj":1533985060000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401510b765288f5801652911c0c5002f","resourceNum":"154","resourceType":null,"resourceName":"(微信引流)启用或禁用微信引流页面(执行公司)","resourceIcon":null,"sortId":154,"url":"wechatPromotionPageController.do?method=activeWechatPromotionPage","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992354000,"xgsj":1533992354000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401340b765288f5801652907d4b7001e","resourceNum":"137","resourceType":null,"resourceName":"(商户)查看商家列表(和执行公司在同一个省市)(执行公司)","resourceIcon":null,"sortId":137,"url":"merchantController.do?method=getMarchantByCompany","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533991704000,"xgsj":1533991704000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401270b765288f58016528b0556a0017","resourceNum":"186","resourceType":null,"resourceName":"(活动)设置收银员、签到员、礼品发放员(执行公司)","resourceIcon":null,"sortId":186,"url":"userController.do?method=setRoleToUser","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985970000,"xgsj":1533985970000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401970b765288f5801652924401a0051","resourceNum":"188","resourceType":null,"resourceName":"(活动)查看某场活动的详情(执行公司)","resourceIcon":null,"sortId":188,"url":"customerLoginController.do?method=getActivityInfoById","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533993566000,"xgsj":1533993566000,"merchantEnable":null,"companyEnable":null},{"resourceId":"402040b765288f580165292a0095005d","resourceNum":"199","resourceType":null,"resourceName":"(活动)添加或者修改活动(执行公司)","resourceIcon":null,"sortId":199,"url":"activityController.do?method=saveOrUpdateActivity","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533993943000,"xgsj":1533993943000,"merchantEnable":null,"companyEnable":null},{"resourceId":"bfc7ddc4ecc511e88eb2f2801f1b9fd1","resourceNum":"142","resourceType":null,"resourceName":"(意向金)修改意向金状态(执行公司)","resourceIcon":null,"sortId":142,"url":"orderController.do?method=changeIntentionOrderStatus","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985222000,"xgsj":1533985222000,"merchantEnable":null,"companyEnable":null},{"resourceId":"a3283abee93a11e89f32f2801f1b9fd1","resourceNum":"250","resourceType":null,"resourceName":"(根据查询条件分页)获取定金列表(执行公司)","resourceIcon":null,"sortId":250,"url":"orderController.do?method=getOrdersByPage","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985222000,"xgsj":1533985222000,"merchantEnable":null,"companyEnable":"2"},{"resourceId":"402160b765288f580165292e6ea70064","resourceNum":"207","resourceType":null,"resourceName":"(活动游戏)创建游戏(执行公司)","resourceIcon":null,"sortId":207,"url":"activityGameController.do?method=saveOrUpdateGame","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533994234000,"xgsj":1533994234000,"merchantEnable":null,"companyEnable":null},{"resourceId":"402120b765288f580165292c94630060","resourceNum":"135","resourceType":null,"resourceName":"(商户)更新商家的最大客户数量(执行公司)","resourceIcon":null,"sortId":135,"url":"activityController.do?method=updateSizeByACMerchantId","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533994112000,"xgsj":1533994112000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401600b765288f58016529190cc8003c","resourceNum":"166","resourceType":null,"resourceName":"(客户信息)查看所有客户信息(执行公司)","resourceIcon":null,"sortId":166,"url":"customerActionController.do?method=getCustomerActions","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992832000,"xgsj":1533992832000,"merchantEnable":null,"companyEnable":null},{"resourceId":"542cb115009845379b66b44537356074","resourceNum":"220","resourceType":null,"resourceName":"(上架商品)查看商户下的爆款商品(执行公司)","resourceIcon":null,"sortId":220,"url":"hotgoodsController.do?method=getHotgoodsByMerchantId","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985777000,"xgsj":1533985777000,"merchantEnable":null,"companyEnable":null},{"resourceId":"bfc7dac4ecc511e88eb2f2801f1b9fd1","resourceNum":"234","resourceType":null,"resourceName":"作废线索(执行公司)","resourceIcon":null,"sortId":234,"url":"clueController.do?method=cancelClue","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985222000,"xgsj":1533985222000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401410b765288f580165290cdd890025","resourceNum":"144","resourceType":null,"resourceName":"(意向金)保存或修改日常意向金订单(执行公司)","resourceIcon":null,"sortId":144,"url":"orderController.do?method=saveOrUpdateOrderByDaily","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992034000,"xgsj":1533992034000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401900b765288f580165291eda080048","resourceNum":"179","resourceType":null,"resourceName":"(客户信息)将所有客户信息导出到excel表格(执行公司)","resourceIcon":null,"sortId":179,"url":"customerActionController.do?method=exportExcelCustomerActions","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533993212000,"xgsj":1533993212000,"merchantEnable":null,"companyEnable":null},{"resourceId":"402190b765288f580165292fe8700067","resourceNum":"210","resourceType":null,"resourceName":"(活动游戏)批量下载参与该游戏的商家的游戏(执行公司)","resourceIcon":null,"sortId":210,"url":"activityGameController.do?method=downLoadGameBarCode","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533994330000,"xgsj":1533994330000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401990b765288f580165292533de0053","resourceNum":"133","resourceType":null,"resourceName":"(商户)查看某个商家信息(执行公司)","resourceIcon":null,"sortId":133,"url":"customerLoginController.do?method=getMerchantInfoById","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533993629000,"xgsj":1533993629000,"merchantEnable":null,"companyEnable":null},{"resourceId":"9b5f0fe4e93811e89f32f2801f1b9fd1","resourceNum":"238","resourceType":null,"resourceName":"客户手机号获取短信签到码(执行公司)","resourceIcon":null,"sortId":238,"url":"customerActionController.do?method=getMessageForQd","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985222000,"xgsj":1533985222000,"merchantEnable":null,"companyEnable":"1"},{"resourceId":"402040b765288f5801652896d9c00001","resourceNum":"111","resourceType":null,"resourceName":"(权限)查看权限列表(执行公司)","resourceIcon":null,"sortId":111,"url":"resouceController.do?method=getResouces","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":"","enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533984299000,"xgsj":1533984299000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401140b765288f58016528a4ed380007","resourceNum":"117","resourceType":null,"resourceName":"(角色)查看角色详情(执行公司)","resourceIcon":null,"sortId":117,"url":"roleController.do?method=getRoleById","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985222000,"xgsj":1533985222000,"merchantEnable":null,"companyEnable":null},{"resourceId":"0a037a0592ed401cba67f1ad5ad35271","resourceNum":"218","resourceType":null,"resourceName":"(上架商品)启用、禁用、删除上架商品(执行公司)","resourceIcon":null,"sortId":218,"url":"onShelfHotgoodsController.do?method=disableOrDelGoods","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985642000,"xgsj":1533985642000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401700b765288f580165291df9140046","resourceNum":"177","resourceType":null,"resourceName":"(客户信息)查看客户信息的修改记录(执行公司)","resourceIcon":null,"sortId":177,"url":"customerActionController.do?method=getCustomerActionRecordsByPage","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533993155000,"xgsj":1533993155000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401910b765288f580165291f5b010049","resourceNum":"180","resourceType":null,"resourceName":"(客户信息)查看客户信息的备注(执行公司)","resourceIcon":null,"sortId":180,"url":"customerNoteController.do?method=getCustomerNotes","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533993245000,"xgsj":1533993245000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401010b765288f58016529123415028b","resourceNum":"183","resourceType":null,"resourceName":"显示客户手机号时,可以显示完整的手机号(执行公司)","resourceIcon":null,"sortId":183,"url":"显示客户手机号时,可以显示完整的手机号","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533995692000,"xgsj":1533995692000,"merchantEnable":null,"companyEnable":null},{"resourceId":"402150b765288f580165292def070063","resourceNum":"206","resourceType":null,"resourceName":"(活动)启用禁用活动(执行公司)","resourceIcon":null,"sortId":206,"url":"activityController.do?method=activeActivity\t","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533994201000,"xgsj":1533994201000,"merchantEnable":null,"companyEnable":null},{"resourceId":"402180b765288f580165292f720d0066","resourceNum":"209","resourceType":null,"resourceName":"(活动游戏)查看某游戏下的所有商家信息(执行公司)","resourceIcon":null,"sortId":209,"url":"activityGameController.do?method=getMerchantsLogo","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533994300000,"xgsj":1533994300000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401660b765288f580165291bbee50042","resourceNum":"185","resourceType":null,"resourceName":"(活动)查看已签到客户列表(执行公司)","resourceIcon":null,"sortId":185,"url":"customerActionController.do?method=getQdCustomerActions","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533993009000,"xgsj":1533993009000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401380b765288f580165290aca750022","resourceNum":"141","resourceType":null,"resourceName":"(意向金)查看意向金订单的详情(执行公司)","resourceIcon":null,"sortId":141,"url":"orderController.do?method=getOrderInfoById","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533991898000,"xgsj":1533991898000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401240b765288f58016528ae67100014","resourceNum":"127","resourceType":null,"resourceName":"(用户)查看当前登录执行公司的所有用户登陆记录(执行公司)","resourceIcon":null,"sortId":127,"url":"userController.do?method=getUsersLoginInfo","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985843000,"xgsj":1533985843000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401370b765288f580165290a4b630021","resourceNum":"140","resourceType":null,"resourceName":"(意向金)查看意向金订单列表(执行公司)","resourceIcon":null,"sortId":140,"url":"orderController.do?method=getOrdersByPage","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533991865000,"xgsj":1533991865000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401640b765288f580165291aeb580040","resourceNum":"170","resourceType":null,"resourceName":"(客户信息)客户手机获取客户签到短信验证码(执行公司)","resourceIcon":null,"sortId":170,"url":"customerActionController.do?method=getMessageForQd","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992955000,"xgsj":1533992955000,"merchantEnable":null,"companyEnable":null},{"resourceId":"22bc888aagg833e89f8bfbb0000b9fd1","resourceNum":"168","resourceType":null,"resourceName":"(客户信息)退卡(执行公司)","resourceIcon":null,"sortId":168,"url":"customerActionController.do?method=delCustomerActionById","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992354000,"xgsj":1533992354000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401020b765266666016529133315008b","resourceNum":"150","resourceType":null,"resourceName":"(意向金)显示意向金订单金额(执行公司)","resourceIcon":null,"sortId":150,"url":"(意向金)显示意向金订单金额","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533995692000,"xgsj":1533995692000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401490b765288f5801652910ac01002d","resourceNum":"152","resourceType":null,"resourceName":"(微信引流)添加或者修改微信引流页面(执行公司)","resourceIcon":null,"sortId":152,"url":"wechatPromotionPageController.do?method=saveOrUpdateWechatPromotionPage","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992283000,"xgsj":1533992283000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401500b765288f58016529114584002e","resourceNum":"153","resourceType":null,"resourceName":"(微信引流)删除微信引流页面(执行公司)","resourceIcon":null,"sortId":153,"url":"wechatPromotionPageController.do?method=delWechatPromotionPage","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992322000,"xgsj":1533992322000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401440b765288f580165290e36210028","resourceNum":"147","resourceType":null,"resourceName":"(意向金)查看意向金订单的修改记录(执行公司)","resourceIcon":null,"sortId":147,"url":"orderController.do?method=getOrderRecordsByPage","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992122000,"xgsj":1533992122000,"merchantEnable":null,"companyEnable":null},{"resourceId":"402190b765288f580165292fe8778967","resourceNum":"203","resourceType":null,"resourceName":"(活动)获取执行公司下活动中的商户(去重)(执行公司)","resourceIcon":null,"sortId":203,"url":"activityController.do?method=getMerchantsByZxgs","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533994330000,"xgsj":1533994330000,"merchantEnable":null,"companyEnable":null},{"resourceId":"32acf824e16511e89f32f2801f1b9fd1","resourceNum":"235","resourceType":null,"resourceName":"(订金)活动中保存/修改订金(执行公司)","resourceIcon":null,"sortId":235,"url":"orderController.do?method=saveActivityIntentionOrder","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992354000,"xgsj":1533992354000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401460b765288f580165290f2984002a","resourceNum":"149","resourceType":null,"resourceName":"(意向金)查看意向金订单中的代单(执行公司)","resourceIcon":null,"sortId":149,"url":"orderController.do?method=subOrderCount","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992184000,"xgsj":1533992184000,"merchantEnable":null,"companyEnable":null},{"resourceId":"c43fcfb8e16111e89f32f2801f1b9fd1","resourceNum":"233","resourceType":null,"resourceName":"(客户信息)根据手机号和卡号获取客户详情(执行公司)","resourceIcon":null,"sortId":233,"url":"orderController.do?method=getCustomerActionByPhoneOrCardNum","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992354000,"xgsj":1533992354000,"merchantEnable":null,"companyEnable":null},{"resourceId":"402770b765288f580165291f5b789049","resourceNum":"148","resourceType":null,"resourceName":"(意向金)导出带单统计Excel表格(执行公司)","resourceIcon":null,"sortId":148,"url":"orderController.do?method=downLoadSubOrderCountTable","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533993245000,"xgsj":1533993245000,"merchantEnable":null,"companyEnable":null}],"leagueFlag":"0","userId":"00000000658861c001658e29d38b00f3"}
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
         * token_id : 9feec9a05ab541f79bfed531b0cca0f8
         * xingming : 董程远
         * shoujihao : 15289456733
         * touxiang :
         * type : 5
         * shangjia_code : yczxgs
         * shangjia_id : 0000000065885d7901658de3323b00af
         * shangjia_name : 伊川执行公司伊川华美建材城
         * activity_id : null
         * resources : [{"resourceId":"401230b765288f58016528ad649b0012","resourceNum":"126","resourceType":null,"resourceName":"(用户)查看当前登录执行公司的所有用户(执行公司)","resourceIcon":null,"sortId":126,"url":"userController.do?method=getUsersByMerchantId","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985777000,"xgsj":1533985777000,"merchantEnable":null,"companyEnable":null},{"resourceId":"402130b765288f580165292d07260061","resourceNum":"204","resourceType":null,"resourceName":"(活动)查看某场活动详情(执行公司)","resourceIcon":null,"sortId":204,"url":"activityController.do?method=getActivityByActivityId","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533994141000,"xgsj":1533994141000,"merchantEnable":null,"companyEnable":null},{"resourceId":"402170b765288f580165292f00bd0065","resourceNum":"208","resourceType":null,"resourceName":"(活动游戏)查看游戏列表(执行公司)","resourceIcon":null,"sortId":208,"url":"activityGameController.do?method=getGames","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533994271000,"xgsj":1533994271000,"merchantEnable":null,"companyEnable":null},{"resourceId":"402140b765288f580165292d735f0062","resourceNum":"205","resourceType":null,"resourceName":"(活动)下载某场活动下的所有商户二维码(执行公司)","resourceIcon":null,"sortId":205,"url":"activityController.do?method=downLoadQRCode","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533994169000,"xgsj":1533994169000,"merchantEnable":null,"companyEnable":null},{"resourceId":"68fc285ae93a11e89f32f2801f1b9fd1","resourceNum":"245","resourceType":null,"resourceName":"活动 保存/修改订单(执行公司)","resourceIcon":null,"sortId":245,"url":"orderController.do?method=saveOrderByAc","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985222000,"xgsj":1533985222000,"merchantEnable":null,"companyEnable":"2"},{"resourceId":"4d1e7e8178494785956bbd871f770ee7","resourceNum":"216","resourceType":null,"resourceName":"(上架商品)查看上架商品详情(执行公司)","resourceIcon":null,"sortId":216,"url":"onShelfHotgoodsController.do?method=getOnShelfHotgoodsById","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985404000,"xgsj":1533985404000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401430b765288f580165290dc1870027","resourceNum":"146","resourceType":null,"resourceName":"(意向金)查看意向金订单的备注(执行公司)","resourceIcon":null,"sortId":146,"url":"orderController.do?method=getOrderRemarksByPage","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992092000,"xgsj":1533992092000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401650b765288f580165291b568a0041","resourceNum":"171","resourceType":null,"resourceName":"(客户信息)客户手机短信验证签到(执行公司)","resourceIcon":null,"sortId":171,"url":"customerActionController.do?method=customerActionToQd","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992982000,"xgsj":1533992982000,"merchantEnable":null,"companyEnable":null},{"resourceId":"0c8cad8317a64f8895befdf465927474","resourceNum":"213","resourceType":null,"resourceName":"(上架商品)添加修改上架商品(执行公司)","resourceIcon":null,"sortId":213,"url":"onShelfHotgoodsController.do?method=saveOrUpdateOnShelfHotgoods","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985222000,"xgsj":1533985222000,"merchantEnable":null,"companyEnable":null},{"resourceId":"a32831e0e93a11e89f32f2801f1b9fd1","resourceNum":"246","resourceType":null,"resourceName":"活动现场扫码返回信息(执行公司)","resourceIcon":null,"sortId":246,"url":"orderController.do?method=getInfoByScanning","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985222000,"xgsj":1533985222000,"merchantEnable":null,"companyEnable":"2"},{"resourceId":"401120b765288f580165289c26bc0003","resourceNum":"113","resourceType":null,"resourceName":"(角色)查看角色拥有的所有权限(执行公司)","resourceIcon":null,"sortId":113,"url":"resouceController.do?method=getResoucesByRoleId","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533984647000,"xgsj":1533984647000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401630b765288f580165291a5992003f","resourceNum":"169","resourceType":null,"resourceName":"(客户信息)查看单条客户信息详情(执行公司)","resourceIcon":null,"sortId":169,"url":"customerActionController.do?method=getCustomerActionById","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992917000,"xgsj":1533992917000,"merchantEnable":null,"companyEnable":null},{"resourceId":"1e06b1670fbb4ea58ea3c15a03bae3b6","resourceNum":"212","resourceType":null,"resourceName":"(上架商品)查看上架商品(执行公司)","resourceIcon":null,"sortId":212,"url":"onShelfHotgoodsController.do?method=getOnShelfHotgoods","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985173000,"xgsj":1533985173000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401610b765288f58016529197e6c003d","resourceNum":"167","resourceType":null,"resourceName":"(客户信息)添加或者修改客户信息(执行公司)","resourceIcon":null,"sortId":167,"url":"customerActionController.do?method=saveOrUpdateCustomerAction","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992861000,"xgsj":1533992861000,"merchantEnable":null,"companyEnable":null},{"resourceId":"402050b765288f580165292aec7c005e","resourceNum":"201","resourceType":null,"resourceName":"(活动)删除活动(执行公司)","resourceIcon":null,"sortId":201,"url":"activityController.do?method=delActivity","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533994004000,"xgsj":1533994004000,"merchantEnable":null,"companyEnable":null},{"resourceId":"a3283474e93a11e89f32f2801f1b9fd1","resourceNum":"248","resourceType":null,"resourceName":"(活动)查看已签到客户列表(执行公司)","resourceIcon":null,"sortId":248,"url":"customerActionController.do?method=getQdCustomerActions","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985222000,"xgsj":1533985222000,"merchantEnable":null,"companyEnable":"1"},{"resourceId":"9b5f13b8e93811e89f32f2801f1b9fd1","resourceNum":"243","resourceType":null,"resourceName":"web端签到(执行公司)","resourceIcon":null,"sortId":243,"url":"orderController.do?method=singin","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985222000,"xgsj":1533985222000,"merchantEnable":null,"companyEnable":"1"},{"resourceId":"9b5f112ee93811e89f32f2801f1b9fd1","resourceNum":"240","resourceType":null,"resourceName":"客户手机验证码签到(执行公司)","resourceIcon":null,"sortId":240,"url":"customerActionController.do?method=customerActionToQd","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985222000,"xgsj":1533985222000,"merchantEnable":null,"companyEnable":"1"},{"resourceId":"401400b765288f580165290bab7c0024","resourceNum":"143","resourceType":null,"resourceName":"(意向金)保存或修改活动意向金订单(执行公司)","resourceIcon":null,"sortId":143,"url":"orderController.do?method=saveOrderByAc","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533991955000,"xgsj":1533991955000,"merchantEnable":null,"companyEnable":null},{"resourceId":"402060b765288f580165292c2683005f","resourceNum":"202","resourceType":null,"resourceName":"(活动)查看某场活动的最大售卡数量(执行公司)","resourceIcon":null,"sortId":202,"url":"activityController.do?method=getMerchantsByActivityId","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533994084000,"xgsj":1533994084000,"merchantEnable":null,"companyEnable":null},{"resourceId":"9b5f1264e93811e89f32f2801f1b9fd1","resourceNum":"241","resourceType":null,"resourceName":"web端领取奖品(执行公司)","resourceIcon":null,"sortId":241,"url":"orderController.do?method=scanningCoupon","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985222000,"xgsj":1533985222000,"merchantEnable":null,"companyEnable":"3"},{"resourceId":"402080b765288f58016528a275270005","resourceNum":"115","resourceType":null,"resourceName":"(角色)查看角色列表(执行公司)","resourceIcon":null,"sortId":115,"url":"roleController.do?method=getRoles","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985060000,"xgsj":1533985060000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401510b765288f5801652911c0c5002f","resourceNum":"154","resourceType":null,"resourceName":"(微信引流)启用或禁用微信引流页面(执行公司)","resourceIcon":null,"sortId":154,"url":"wechatPromotionPageController.do?method=activeWechatPromotionPage","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992354000,"xgsj":1533992354000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401340b765288f5801652907d4b7001e","resourceNum":"137","resourceType":null,"resourceName":"(商户)查看商家列表(和执行公司在同一个省市)(执行公司)","resourceIcon":null,"sortId":137,"url":"merchantController.do?method=getMarchantByCompany","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533991704000,"xgsj":1533991704000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401270b765288f58016528b0556a0017","resourceNum":"186","resourceType":null,"resourceName":"(活动)设置收银员、签到员、礼品发放员(执行公司)","resourceIcon":null,"sortId":186,"url":"userController.do?method=setRoleToUser","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985970000,"xgsj":1533985970000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401970b765288f5801652924401a0051","resourceNum":"188","resourceType":null,"resourceName":"(活动)查看某场活动的详情(执行公司)","resourceIcon":null,"sortId":188,"url":"customerLoginController.do?method=getActivityInfoById","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533993566000,"xgsj":1533993566000,"merchantEnable":null,"companyEnable":null},{"resourceId":"402040b765288f580165292a0095005d","resourceNum":"199","resourceType":null,"resourceName":"(活动)添加或者修改活动(执行公司)","resourceIcon":null,"sortId":199,"url":"activityController.do?method=saveOrUpdateActivity","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533993943000,"xgsj":1533993943000,"merchantEnable":null,"companyEnable":null},{"resourceId":"bfc7ddc4ecc511e88eb2f2801f1b9fd1","resourceNum":"142","resourceType":null,"resourceName":"(意向金)修改意向金状态(执行公司)","resourceIcon":null,"sortId":142,"url":"orderController.do?method=changeIntentionOrderStatus","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985222000,"xgsj":1533985222000,"merchantEnable":null,"companyEnable":null},{"resourceId":"a3283abee93a11e89f32f2801f1b9fd1","resourceNum":"250","resourceType":null,"resourceName":"(根据查询条件分页)获取定金列表(执行公司)","resourceIcon":null,"sortId":250,"url":"orderController.do?method=getOrdersByPage","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985222000,"xgsj":1533985222000,"merchantEnable":null,"companyEnable":"2"},{"resourceId":"402160b765288f580165292e6ea70064","resourceNum":"207","resourceType":null,"resourceName":"(活动游戏)创建游戏(执行公司)","resourceIcon":null,"sortId":207,"url":"activityGameController.do?method=saveOrUpdateGame","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533994234000,"xgsj":1533994234000,"merchantEnable":null,"companyEnable":null},{"resourceId":"402120b765288f580165292c94630060","resourceNum":"135","resourceType":null,"resourceName":"(商户)更新商家的最大客户数量(执行公司)","resourceIcon":null,"sortId":135,"url":"activityController.do?method=updateSizeByACMerchantId","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533994112000,"xgsj":1533994112000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401600b765288f58016529190cc8003c","resourceNum":"166","resourceType":null,"resourceName":"(客户信息)查看所有客户信息(执行公司)","resourceIcon":null,"sortId":166,"url":"customerActionController.do?method=getCustomerActions","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992832000,"xgsj":1533992832000,"merchantEnable":null,"companyEnable":null},{"resourceId":"542cb115009845379b66b44537356074","resourceNum":"220","resourceType":null,"resourceName":"(上架商品)查看商户下的爆款商品(执行公司)","resourceIcon":null,"sortId":220,"url":"hotgoodsController.do?method=getHotgoodsByMerchantId","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985777000,"xgsj":1533985777000,"merchantEnable":null,"companyEnable":null},{"resourceId":"bfc7dac4ecc511e88eb2f2801f1b9fd1","resourceNum":"234","resourceType":null,"resourceName":"作废线索(执行公司)","resourceIcon":null,"sortId":234,"url":"clueController.do?method=cancelClue","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985222000,"xgsj":1533985222000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401410b765288f580165290cdd890025","resourceNum":"144","resourceType":null,"resourceName":"(意向金)保存或修改日常意向金订单(执行公司)","resourceIcon":null,"sortId":144,"url":"orderController.do?method=saveOrUpdateOrderByDaily","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992034000,"xgsj":1533992034000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401900b765288f580165291eda080048","resourceNum":"179","resourceType":null,"resourceName":"(客户信息)将所有客户信息导出到excel表格(执行公司)","resourceIcon":null,"sortId":179,"url":"customerActionController.do?method=exportExcelCustomerActions","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533993212000,"xgsj":1533993212000,"merchantEnable":null,"companyEnable":null},{"resourceId":"402190b765288f580165292fe8700067","resourceNum":"210","resourceType":null,"resourceName":"(活动游戏)批量下载参与该游戏的商家的游戏(执行公司)","resourceIcon":null,"sortId":210,"url":"activityGameController.do?method=downLoadGameBarCode","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533994330000,"xgsj":1533994330000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401990b765288f580165292533de0053","resourceNum":"133","resourceType":null,"resourceName":"(商户)查看某个商家信息(执行公司)","resourceIcon":null,"sortId":133,"url":"customerLoginController.do?method=getMerchantInfoById","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533993629000,"xgsj":1533993629000,"merchantEnable":null,"companyEnable":null},{"resourceId":"9b5f0fe4e93811e89f32f2801f1b9fd1","resourceNum":"238","resourceType":null,"resourceName":"客户手机号获取短信签到码(执行公司)","resourceIcon":null,"sortId":238,"url":"customerActionController.do?method=getMessageForQd","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985222000,"xgsj":1533985222000,"merchantEnable":null,"companyEnable":"1"},{"resourceId":"402040b765288f5801652896d9c00001","resourceNum":"111","resourceType":null,"resourceName":"(权限)查看权限列表(执行公司)","resourceIcon":null,"sortId":111,"url":"resouceController.do?method=getResouces","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":"","enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533984299000,"xgsj":1533984299000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401140b765288f58016528a4ed380007","resourceNum":"117","resourceType":null,"resourceName":"(角色)查看角色详情(执行公司)","resourceIcon":null,"sortId":117,"url":"roleController.do?method=getRoleById","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985222000,"xgsj":1533985222000,"merchantEnable":null,"companyEnable":null},{"resourceId":"0a037a0592ed401cba67f1ad5ad35271","resourceNum":"218","resourceType":null,"resourceName":"(上架商品)启用、禁用、删除上架商品(执行公司)","resourceIcon":null,"sortId":218,"url":"onShelfHotgoodsController.do?method=disableOrDelGoods","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985642000,"xgsj":1533985642000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401700b765288f580165291df9140046","resourceNum":"177","resourceType":null,"resourceName":"(客户信息)查看客户信息的修改记录(执行公司)","resourceIcon":null,"sortId":177,"url":"customerActionController.do?method=getCustomerActionRecordsByPage","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533993155000,"xgsj":1533993155000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401910b765288f580165291f5b010049","resourceNum":"180","resourceType":null,"resourceName":"(客户信息)查看客户信息的备注(执行公司)","resourceIcon":null,"sortId":180,"url":"customerNoteController.do?method=getCustomerNotes","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533993245000,"xgsj":1533993245000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401010b765288f58016529123415028b","resourceNum":"183","resourceType":null,"resourceName":"显示客户手机号时,可以显示完整的手机号(执行公司)","resourceIcon":null,"sortId":183,"url":"显示客户手机号时,可以显示完整的手机号","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533995692000,"xgsj":1533995692000,"merchantEnable":null,"companyEnable":null},{"resourceId":"402150b765288f580165292def070063","resourceNum":"206","resourceType":null,"resourceName":"(活动)启用禁用活动(执行公司)","resourceIcon":null,"sortId":206,"url":"activityController.do?method=activeActivity\t","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533994201000,"xgsj":1533994201000,"merchantEnable":null,"companyEnable":null},{"resourceId":"402180b765288f580165292f720d0066","resourceNum":"209","resourceType":null,"resourceName":"(活动游戏)查看某游戏下的所有商家信息(执行公司)","resourceIcon":null,"sortId":209,"url":"activityGameController.do?method=getMerchantsLogo","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533994300000,"xgsj":1533994300000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401660b765288f580165291bbee50042","resourceNum":"185","resourceType":null,"resourceName":"(活动)查看已签到客户列表(执行公司)","resourceIcon":null,"sortId":185,"url":"customerActionController.do?method=getQdCustomerActions","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533993009000,"xgsj":1533993009000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401380b765288f580165290aca750022","resourceNum":"141","resourceType":null,"resourceName":"(意向金)查看意向金订单的详情(执行公司)","resourceIcon":null,"sortId":141,"url":"orderController.do?method=getOrderInfoById","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533991898000,"xgsj":1533991898000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401240b765288f58016528ae67100014","resourceNum":"127","resourceType":null,"resourceName":"(用户)查看当前登录执行公司的所有用户登陆记录(执行公司)","resourceIcon":null,"sortId":127,"url":"userController.do?method=getUsersLoginInfo","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533985843000,"xgsj":1533985843000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401370b765288f580165290a4b630021","resourceNum":"140","resourceType":null,"resourceName":"(意向金)查看意向金订单列表(执行公司)","resourceIcon":null,"sortId":140,"url":"orderController.do?method=getOrdersByPage","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533991865000,"xgsj":1533991865000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401640b765288f580165291aeb580040","resourceNum":"170","resourceType":null,"resourceName":"(客户信息)客户手机获取客户签到短信验证码(执行公司)","resourceIcon":null,"sortId":170,"url":"customerActionController.do?method=getMessageForQd","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992955000,"xgsj":1533992955000,"merchantEnable":null,"companyEnable":null},{"resourceId":"22bc888aagg833e89f8bfbb0000b9fd1","resourceNum":"168","resourceType":null,"resourceName":"(客户信息)退卡(执行公司)","resourceIcon":null,"sortId":168,"url":"customerActionController.do?method=delCustomerActionById","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992354000,"xgsj":1533992354000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401020b765266666016529133315008b","resourceNum":"150","resourceType":null,"resourceName":"(意向金)显示意向金订单金额(执行公司)","resourceIcon":null,"sortId":150,"url":"(意向金)显示意向金订单金额","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533995692000,"xgsj":1533995692000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401490b765288f5801652910ac01002d","resourceNum":"152","resourceType":null,"resourceName":"(微信引流)添加或者修改微信引流页面(执行公司)","resourceIcon":null,"sortId":152,"url":"wechatPromotionPageController.do?method=saveOrUpdateWechatPromotionPage","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992283000,"xgsj":1533992283000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401500b765288f58016529114584002e","resourceNum":"153","resourceType":null,"resourceName":"(微信引流)删除微信引流页面(执行公司)","resourceIcon":null,"sortId":153,"url":"wechatPromotionPageController.do?method=delWechatPromotionPage","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992322000,"xgsj":1533992322000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401440b765288f580165290e36210028","resourceNum":"147","resourceType":null,"resourceName":"(意向金)查看意向金订单的修改记录(执行公司)","resourceIcon":null,"sortId":147,"url":"orderController.do?method=getOrderRecordsByPage","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992122000,"xgsj":1533992122000,"merchantEnable":null,"companyEnable":null},{"resourceId":"402190b765288f580165292fe8778967","resourceNum":"203","resourceType":null,"resourceName":"(活动)获取执行公司下活动中的商户(去重)(执行公司)","resourceIcon":null,"sortId":203,"url":"activityController.do?method=getMerchantsByZxgs","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533994330000,"xgsj":1533994330000,"merchantEnable":null,"companyEnable":null},{"resourceId":"32acf824e16511e89f32f2801f1b9fd1","resourceNum":"235","resourceType":null,"resourceName":"(订金)活动中保存/修改订金(执行公司)","resourceIcon":null,"sortId":235,"url":"orderController.do?method=saveActivityIntentionOrder","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992354000,"xgsj":1533992354000,"merchantEnable":null,"companyEnable":null},{"resourceId":"401460b765288f580165290f2984002a","resourceNum":"149","resourceType":null,"resourceName":"(意向金)查看意向金订单中的代单(执行公司)","resourceIcon":null,"sortId":149,"url":"orderController.do?method=subOrderCount","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992184000,"xgsj":1533992184000,"merchantEnable":null,"companyEnable":null},{"resourceId":"c43fcfb8e16111e89f32f2801f1b9fd1","resourceNum":"233","resourceType":null,"resourceName":"(客户信息)根据手机号和卡号获取客户详情(执行公司)","resourceIcon":null,"sortId":233,"url":"orderController.do?method=getCustomerActionByPhoneOrCardNum","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533992354000,"xgsj":1533992354000,"merchantEnable":null,"companyEnable":null},{"resourceId":"402770b765288f580165291f5b789049","resourceNum":"148","resourceType":null,"resourceName":"(意向金)导出带单统计Excel表格(执行公司)","resourceIcon":null,"sortId":148,"url":"orderController.do?method=downLoadSubOrderCountTable","ziyuanjibie":null,"ziyuanlujing":null,"groupName":null,"type":"5","beizhu":null,"enable":"1","isDelete":"0","ip":"192.168.0.117","caoZuoRen":"asdfghjkl123qwertg","xiugairen":"asdfghjkl123qwertg","cjsj":1533993245000,"xgsj":1533993245000,"merchantEnable":null,"companyEnable":null}]
         * leagueFlag : 0
         * userId : 00000000658861c001658e29d38b00f3
         */

        private String token_id;
        private String xingming;
        private String shoujihao;
        private String touxiang;
        private String type;
        private String shangjia_code;
        private String shangjia_id;
        private String shangjia_name;
        private Object activity_id;
        private String leagueFlag;
        private String userId;
        private List<ResourcesBean> resources;

        public String getToken_id() {
            return token_id;
        }

        public void setToken_id(String token_id) {
            this.token_id = token_id;
        }

        public String getXingming() {
            return xingming;
        }

        public void setXingming(String xingming) {
            this.xingming = xingming;
        }

        public String getShoujihao() {
            return shoujihao;
        }

        public void setShoujihao(String shoujihao) {
            this.shoujihao = shoujihao;
        }

        public String getTouxiang() {
            return touxiang;
        }

        public void setTouxiang(String touxiang) {
            this.touxiang = touxiang;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getShangjia_code() {
            return shangjia_code;
        }

        public void setShangjia_code(String shangjia_code) {
            this.shangjia_code = shangjia_code;
        }

        public String getShangjia_id() {
            return shangjia_id;
        }

        public void setShangjia_id(String shangjia_id) {
            this.shangjia_id = shangjia_id;
        }

        public String getShangjia_name() {
            return shangjia_name;
        }

        public void setShangjia_name(String shangjia_name) {
            this.shangjia_name = shangjia_name;
        }

        public Object getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(Object activity_id) {
            this.activity_id = activity_id;
        }

        public String getLeagueFlag() {
            return leagueFlag;
        }

        public void setLeagueFlag(String leagueFlag) {
            this.leagueFlag = leagueFlag;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public List<ResourcesBean> getResources() {
            return resources;
        }

        public void setResources(List<ResourcesBean> resources) {
            this.resources = resources;
        }

        public static class ResourcesBean {
            /**
             * resourceId : 401230b765288f58016528ad649b0012
             * resourceNum : 126
             * resourceType : null
             * resourceName : (用户)查看当前登录执行公司的所有用户(执行公司)
             * resourceIcon : null
             * sortId : 126
             * url : userController.do?method=getUsersByMerchantId
             * ziyuanjibie : null
             * ziyuanlujing : null
             * groupName : null
             * type : 5
             * beizhu : null
             * enable : 1
             * isDelete : 0
             * ip : 192.168.0.117
             * caoZuoRen : asdfghjkl123qwertg
             * xiugairen : asdfghjkl123qwertg
             * cjsj : 1533985777000
             * xgsj : 1533985777000
             * merchantEnable : null
             * companyEnable : null
             */

            private String resourceId;
            private String resourceNum;
            private Object resourceType;
            private String resourceName;
            private Object resourceIcon;
            private int sortId;
            private String url;
            private Object ziyuanjibie;
            private Object ziyuanlujing;
            private Object groupName;
            private String type;
            private Object beizhu;
            private String enable;
            private String isDelete;
            private String ip;
            private String caoZuoRen;
            private String xiugairen;
            private long cjsj;
            private long xgsj;
            private Object merchantEnable;
            private Object companyEnable;

            public String getResourceId() {
                return resourceId;
            }

            public void setResourceId(String resourceId) {
                this.resourceId = resourceId;
            }

            public String getResourceNum() {
                return resourceNum;
            }

            public void setResourceNum(String resourceNum) {
                this.resourceNum = resourceNum;
            }

            public Object getResourceType() {
                return resourceType;
            }

            public void setResourceType(Object resourceType) {
                this.resourceType = resourceType;
            }

            public String getResourceName() {
                return resourceName;
            }

            public void setResourceName(String resourceName) {
                this.resourceName = resourceName;
            }

            public Object getResourceIcon() {
                return resourceIcon;
            }

            public void setResourceIcon(Object resourceIcon) {
                this.resourceIcon = resourceIcon;
            }

            public int getSortId() {
                return sortId;
            }

            public void setSortId(int sortId) {
                this.sortId = sortId;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Object getZiyuanjibie() {
                return ziyuanjibie;
            }

            public void setZiyuanjibie(Object ziyuanjibie) {
                this.ziyuanjibie = ziyuanjibie;
            }

            public Object getZiyuanlujing() {
                return ziyuanlujing;
            }

            public void setZiyuanlujing(Object ziyuanlujing) {
                this.ziyuanlujing = ziyuanlujing;
            }

            public Object getGroupName() {
                return groupName;
            }

            public void setGroupName(Object groupName) {
                this.groupName = groupName;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Object getBeizhu() {
                return beizhu;
            }

            public void setBeizhu(Object beizhu) {
                this.beizhu = beizhu;
            }

            public String getEnable() {
                return enable;
            }

            public void setEnable(String enable) {
                this.enable = enable;
            }

            public String getIsDelete() {
                return isDelete;
            }

            public void setIsDelete(String isDelete) {
                this.isDelete = isDelete;
            }

            public String getIp() {
                return ip;
            }

            public void setIp(String ip) {
                this.ip = ip;
            }

            public String getCaoZuoRen() {
                return caoZuoRen;
            }

            public void setCaoZuoRen(String caoZuoRen) {
                this.caoZuoRen = caoZuoRen;
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

            public Object getMerchantEnable() {
                return merchantEnable;
            }

            public void setMerchantEnable(Object merchantEnable) {
                this.merchantEnable = merchantEnable;
            }

            public Object getCompanyEnable() {
                return companyEnable;
            }

            public void setCompanyEnable(Object companyEnable) {
                this.companyEnable = companyEnable;
            }
        }
    }
}
