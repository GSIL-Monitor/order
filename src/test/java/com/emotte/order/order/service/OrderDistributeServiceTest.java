package com.emotte.order.order.service;

import java.io.FileWriter;
import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;

import com.emotte.BaseTestService;
import com.emotte.order.order.mapper.reader.ReOrderMapper;
import com.emotte.order.order.mapper.writer.WrOrderMapper;
import com.emotte.order.order.model.Order;

public class OrderDistributeServiceTest extends BaseTestService {
	@Resource
	private OrderDistributeService distributeService;
	@Resource
	private OrderService orderService;
	@Resource
	ReOrderMapper reOrderMapper;
	@Resource
	WrOrderMapper wrOrderMapper;
	/*@Test
	public void testFenfa(){
		Order order = new Order();
		order.setOrderChannel("212028286803194"); 
		order.setProductCode("101001001205603112522436");
		order.setCateType(2);
		order.setLongitude("116.513842");
		order.setLatitude("39.796066");
		order.setCity("101001001");
//		order.setUserMobile("13000212564");
		distributeService.distribute (order);
		System.out.println(order.getRechargeDept());
		
	}*/
	@Test
	public void testFenfa(){
		Order order = new Order();
		order.setId(863135664665399L);
		order.setCateType(1);
		order.setUserMobile("13810569224");
		order.setOrderChannel("212028286803194"); 
		order.setLongitude("116.329621");
		order.setLatitude("39.680456");
		order.setCity("101001001");
		order.setProductCode("101001001205609369641799");
//		order.setUserMobile("13000212564");
		distributeService.distribute (order);
		System.err.println(order.getRechargeBy()+" == "+order.getRechargeDept());
	}
	@Test
	public void testFenfa1(){
		String sb = "580955634067255,264948120986376,563623857913575,264940082881304,317933268703448,319332537980968,829397367462360,423857970770648,211875937176264,141644631881336,564074598912584,688827339611512,159648202606392,759120242702072,496276940933464,407901256769880,953941840019832,247453865232968,866968118892824,159471012384632,920286567850328,603012390582808,460178586317944,389204984736120,990499632731400,795732960227640,936754313321960,761182110718728,812857029833976,372914193440856,265618054054904,973230539763016,938306454468152,602521519946040,196755943499048,143320068430856,"
		; 
		String[] split = sb.split(",");
//		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < split.length; i++) {
			Order order = new Order();
			Order loadOrder = reOrderMapper.loadOrder(Long.valueOf(split[i]));
			order.setId(loadOrder.getId());
			order.setCateType(loadOrder.getCateType());
			order.setUserMobile(loadOrder.getUserMobile());
			order.setOrderChannel(loadOrder.getOrderChannel()); 
			order.setLongitude(loadOrder.getLongitude());
			order.setLatitude(loadOrder.getLatitude());
			order.setCity(loadOrder.getCity());
			order.setProductCode(loadOrder.getProductCode());
			distributeService.distribute (order);
			Order order2 = new Order();
			order2.setId(order.getId());
			order2.setRechargeBy(order.getRechargeBy());
			order2.setRechargeDept(order.getRechargeDept());
			wrOrderMapper.updateOrder(order2);
//			stringBuilder.append(order.getId()+" == "+order.getRechargeBy()+" === "+order.getRechargeDept() +" ; ");
			append2File(order.getOrderCode()+" == "+order.getId()+" == "+order.getRechargeBy()+" === "+order.getRechargeDept(),"d://new/orderList.txt","\r\n");
			try {
				Thread.sleep(100L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		System.out.println(stringBuilder.toString());
	}
	
	public static synchronized void append2File(String strs, String filePath,String separator) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(filePath, true);
			writer.write(strs+separator);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != writer)
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
