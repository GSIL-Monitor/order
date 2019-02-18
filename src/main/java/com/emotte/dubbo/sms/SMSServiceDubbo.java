package com.emotte.dubbo.sms;

public interface SMSServiceDubbo {

	/**
	 * 发送短信
	 * @param data json串
	 * <p>{<br>
	 * 		mobiles: String （ 必填）手机号码，多个以逗号分隔<br>
	 * 		userIds: String 接收人ID，多个以逗号分隔，顺序与mobiles对应 <br>
	 * 		userNames: String 接收人姓名，多个以逗号分隔，顺序与mobiles对应<br>
	 * 		templet: String （ 必填）模板码（模板的code码） ， 必填<br>
	 * 		channel: String （ 必填）通道码 （验证码, 推送消息, 备用对应的字典码）<br>
	 * 		type: String 短信类型 （会员续费,反馈回复,余额变动对应的字典码）<br>
	 *      rate: String 发送频率 （1:即时发送，2:定时发送）<br>
	 *      source: String 来源（具体到功能点，如某个系统的某个页面的某个按钮，示例:系统名|页面|按钮）<br>
	 * 		deptCode: String 发送部门编码 （对应短信模板中的code）<br>
	 * 		hopeSendTime: String 设置定时发送时间(yyyy-MM-dd hh:mm:ss, 如2016-05-18 13:01:01)<br>
	 * 		params:Object[] [参数1,参数2,参数2]<br>
	 * }</p>
	 * @return json串
	 * <p>{<br>
	 * 		result: 返回结果 （success/fail:失败原因）<br>
	 * }</p>
	 * 2016年8月20日
	 */
	public String send(String data);
	
	
	/**
	 * 验证码验证
	 * @param data json串
	 * <p>{<br>
	 * 		mobile: String （ 必填）手机号<br>
	 * 		system: String （ 必填）系统码（各自系统的程序名如 manager, finance, scm, sns，用于区分不同的系统）
	 * 		templet: String （ 必填）模板码（模板的code码） ， 必填<br>
	 * 		defined: String 自定义标识（用于区分相同系统的不同模块调用相同验证码模板， 与发送接口一致）
	 * 		code: String （ 必填）验证码<br>
	 * }</p>
	 * @return json串
	 * <p>{<br>
	 * 		result: 返回结果 （success/fail:失败原因）<br>
	 * }</p>
	 * 2016年8月30日
	 */
	public String verify(String data);
	
}
