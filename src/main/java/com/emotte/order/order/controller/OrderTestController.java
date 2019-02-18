package com.emotte.order.order.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.emotte.dubbo.order.OrderInterfaceService;

@Controller
@RequestMapping("/orderTest")
public class OrderTestController {

	@Resource
	private OrderInterfaceService orderInterfaceService;
	
	@RequestMapping(value = "/testDubbo", method = { RequestMethod.POST, RequestMethod.GET })
	public void testDubbo(){
		String json = "{'orderItem':[{'valid':1,'nowPrice':'780.00','createBy':143081966861701,'productCode':'101001001205043956127444','quantity':1,'createTime':'2016-11-17 15:18:45','updateBy':143081966861701,'saleType':1,'categoryCode':'100100010001','version':1,'productName':'娴风骀娑︿寒缃'}],'handle':'1','account':[{'payKind':2,'createBy':143081966861701,'payType':3,'updateBy':143081966861701,'isManual':2,'isBackType':2,'startTime':'2016-11-17 15:48:20','endTime':'2016-11-17 15:48:20','payStatus':'20110001','version':0,'accountAmount':'780.00','bussStatus':1}],'orderItemServer':[{'valid':1,'createBy':143081966861701,'address':'娉搴浜杩999','updateBy':143081966861701,'startTime':'2016-11-18 15:22','personNumber':1,'version':0,'interviewAddress':'娉搴浜杩999'}],'order':[{'orderType':'100100010001','critical':2,'city':'101001001','latitude':'39.9998','orderStatus':1,'receiverCityCode':'101001001005','receiverProvince':'邃含','orderChannel':'20190023','receiverCity':'邃含甯','cateType':4,'userMobile':'13513513513','startTime':'2016-11-18 15:22','longitude':'116.41981','orderSourceId':20180001,'userSex':'1','receiverName':'邋','ip':'192.168.2.5','priceType':'20000002','receiverMobile':'13513513513','receiverArea':'娉搴','userId':143081966861701,'receiverAddress':'浜杩999','createBy':143081966861701,'createTime':'2016-11-17 15:18:45','payStatus':'20110001'}]}";
		//		String result = this.orderInterfaceService.insertOrUpdateAccount(json);
		String result = this.orderInterfaceService.insertOrUpdateOrder(json);
		System.out.println("insertOrUpdateOrder:" +result);
	}
}
