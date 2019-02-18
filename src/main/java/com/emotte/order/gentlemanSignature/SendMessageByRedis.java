package com.emotte.order.gentlemanSignature;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.wildhorse.server.core.exception.BaseException;

import com.emotte.dubbo.sms.SMSServiceDubbo;
import com.emotte.kernel.arch.future.server.FutureWithRedisServer;
import com.emotte.kernel.arch.model.ResultData;
import com.emotte.kernel.redis.EJedisPool;
import com.emotte.order.order.mapper.writer.WrAgreementMapper;
import com.emotte.order.order.model.Agreement;
import com.emotte.order.order.service.AgreementService;

import net.sf.json.JSONObject;

public class SendMessageByRedis{

	private EJedisPool jedispool;
	private String key;
	private String serviceUrl;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private AgreementService agreementService;
	
	@Resource
	private WrAgreementMapper wrAgreementMapper;
	
	@Resource
	private SMSServiceDubbo sMSServiceDubbo;
	
	public EJedisPool getJedispool() {
		return jedispool;
	}

	public void setJedispool(EJedisPool jedispool) {
		this.jedispool = jedispool;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public void sms_process() {
		System.out.println("--------------------");
		new FutureWithRedisServer(key, jedispool) {
			@Override
			public Object validate() {
				return new Object();
			}
			
			@Override
			public ResultData<String> process(String data, Object obj) {
				log.info("-------------自动发送电子合同短信------------参数是"+data);
				ResultData<String> RD = new ResultData<String>();
				try {
					if(null!=data) {
						Agreement agreement = new Agreement();
						agreement.setAgreementCode(data);
						agreement = agreementService.selectAgreement(agreement);
						
						//客户短连接
						String client = com.emotte.order.util.SendUtils.sendGet(serviceUrl+"/sms/su/ts",
								serviceUrl+"/contract/index.html?compactId="+agreement.getId()+"%26mobile="+agreement.getMobile()+"%26identity=client");

						JSONObject client_short_json = JSONObject.fromObject(client);
						
						//服务人员短连接
						String service = com.emotte.order.util.SendUtils.sendGet(serviceUrl+"/sms/su/ts",
								serviceUrl+"/contract/index.html?compactId="+agreement.getId()+"%26mobile="+agreement.getMobileC()+"%26identity=service");

						JSONObject service_short_json = JSONObject.fromObject(service);
						
						//客户短信
						JSONObject send_message_client = new JSONObject();
						String[] params_client = new String[] {client_short_json.getString("shortUrl")};
						send_message_client.accumulate("channel", "sys");
						send_message_client.accumulate("mobiles", agreement.getMobile());
						send_message_client.accumulate("rate", "1");
						send_message_client.accumulate("templet","other_744");
						send_message_client.accumulate("system","other");
						send_message_client.accumulate("params", params_client);
						String send_result_client = sMSServiceDubbo.send(send_message_client.toString());
						
						JSONObject send_result_client_json = JSONObject.fromObject(send_result_client);
						String client_result = send_result_client_json.get("result").toString();
						
						if(null!=client_result && !client_result.isEmpty() && client_result.equals("success")){
							RD.setData(agreement.getMobile());
							RD.setMsg("订单号"+"["+data+"],手机号"+agreement.getMobile()+"发送成功");
						}else{
							RD.setMsg("订单号"+"["+data+"],手机号["+agreement.getMobile()+"发送失败");
						}
						
						JSONObject send_message_service = new JSONObject();
						String[] params_service = new String[] {service_short_json.getString("shortUrl")} ;
						send_message_service.accumulate("channel", "sys");
						send_message_service.accumulate("mobiles", agreement.getMobileC());
						send_message_service.accumulate("rate", "1");
						send_message_service.accumulate("templet","other_744");
						send_message_service.accumulate("system","other");
						send_message_service.accumulate("params", params_service);
						String send_ressult_service = sMSServiceDubbo.send(send_message_service.toString());
						
						JSONObject service_result_json = JSONObject.fromObject(send_ressult_service);
						String service_result = service_result_json.get("result").toString();
						if(null!=service_result && !service_result.isEmpty() && service_result.equals("success")){
							RD.setData(agreement.getMobile()+","+agreement.getMobileC());
							RD.setMsg("订单号"+"["+data+"],手机号"+agreement.getMobile()+","+agreement.getMobileC()+"发送成功");
						}else{
							if(null!=RD.getData()) {
								RD.setData(agreement.getMobile()+","+agreement.getMobileC());
							}
							if(null!=RD.getMsg()) {
								RD.setMsg("订单号"+"["+data+"],手机号"+agreement.getMobile()+","+agreement.getMobileC()+"发送失败");
							}else {
								RD.setMsg("订单号"+"["+data+"],手机号"+agreement.getMobileC()+"发送失败");
							}
							
						}
						if((agreement.getElecClientState()==1 || agreement.getElecServeState()==1) && agreement.getElecOtherState()==1) {
							agreement.setElecOtherState(2);
							wrAgreementMapper.updateAgreement(agreement);
						}
					}
					
				} catch (BaseException e) {
					e.printStackTrace();
				}
				RD.setStatus(true);
				return RD;
			}
		}.start();
	}
	}
