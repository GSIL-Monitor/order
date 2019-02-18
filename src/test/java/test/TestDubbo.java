package test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.emotte.dubbo.custom.RevisitRecordService;
import com.emotte.dubbo.order.OrderInterfaceService;
import com.emotte.dubbo.sms.SMSServiceDubbo;
import com.emotte.eclient.EClientContext;
import com.emotte.interf.ESalaryService;
import com.emotte.kernel.helper.LogHelper;
import com.emotte.order.util.SendUtils;

import net.sf.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/application*.xml" })
public class TestDubbo {

	@Resource
	private RevisitRecordService revisitRecordService;

	@Resource
	private OrderInterfaceService OrderInterfaceService;
	
	@Resource
	private SMSServiceDubbo smsServiceDubbo;
	
	
	/*@Test
	public void testInsertRecorder() {
		JSONObject parm = new JSONObject();
		parm.accumulate("id", 11111);
		String result = revisitRecordService.insertRecorder(parm.toString());
		System.err.println(result);
	}
	
	@Test
	public void testInsertActivityAccount() {
		String parm = "[{\"orderId\":986437010784007,\"isManual\":2,\"isBackType\":2,\"type\":\"20460002\",\"version\":0,\"bussStatus\":1,\"payKind\":2,\"accountId\":121771756292631,\"createBy\":420824596769671,\"payType\":3,\"updateBy\":420824596769671,\"prestoreId\":172873174767751,\"startTime\":\"2018-05-24 15:05:32\",\"endTime\":\"2018-05-24 15:05:32\",\"payStatus\":\"20110001\",\"accountAmount\":\"7000.00\"}]";
		String result = OrderInterfaceService.insertActivityAccount(parm);
		System.err.println(result);
	}
	
	@Test
	public void testUpdateOrderForTrack() {
		//离职交接转单
		JSONObject parm = new JSONObject();
		parm.accumulate("updateBy", "1");//操作人id
		parm.accumulate("updateDept", "1");//操作人部门id
		parm.accumulate("followBy", "95272");//离职人id
		parm.accumulate("followDept", "1");//离职人部门id
		parm.accumulate("rechargeBy", "1");//接收人id
		parm.accumulate("rechargeDept", "1");//接收人部门id
		JSONObject result = OrderInterfaceService.updateOrderForTrack(parm.toString());
		System.err.println(result.toString());
	}

	@Test
	public void testUpdateSolutionHandover() {
		//离职交接解决方案
		JSONObject parm = new JSONObject();
		parm.accumulate("updateBy", "1");//操作人id
		parm.accumulate("updateDept", "1");//操作人部门id
		parm.accumulate("followBy", "1");//离职人id
		parm.accumulate("followDept", "1");//离职人部门id
		parm.accumulate("rechargeBy", "95272");//接收人id
		parm.accumulate("rechargeDept", "1");//接收人部门id
		String result = OrderInterfaceService.updateSolutionHandover(parm.toString());
		System.err.println(result);
	}

	
	@Test
	public void testUpdateOrderHandover() {
		//离职交接订单
		JSONObject parm = new JSONObject();
		parm.accumulate("updateBy", "1");//操作人id
		parm.accumulate("updateDept", "1");//操作人部门id
		parm.accumulate("followBy", "d");//离职人id
		parm.accumulate("followDept", "1");//离职人部门id
		parm.accumulate("rechargeBy", "1");//接收人id
		parm.accumulate("rechargeDept", "1");//接收人部门id
		String result = OrderInterfaceService.updateOrderHandover(parm.toString());
		System.err.println(result);
	}
	
	@Test
	public void testInsertOrUpdateAccountAndPayfee() {
		//结算和缴费同时操作
		String parm = "{\"handle\":\"1\",\"account\":[{\"orderId\":946816671522360,\"accountAmount\":150,\"createBy\":1,\"updateBy\":1,\"payType\":4,\"payKind\":2,\"payStatus\":20110001,\"isBackType\":2,\"bussStatus\":1}],\"payfee\":[{\"feePost\":20250010,\"feeType\":1,\"isBackType\":1,\"cardsNum\":\"p80830002\",\"feeSum\":150,\"accountStatus\":2,\"createBy\":1,\"updateBy\":1,\"agentUser\":1,\"payStatus\":20110001}]}";
		String result = OrderInterfaceService.insertOrUpdateAccountAndPayfee(parm.toString());
		System.err.println(result);
	}
	
	@Test
	public void testInsertOrUpdateOrder() {
		//订单新增、修改、删除接口
		String parm = "{'handle':'2','order':[{'isShow':2,'id':'510490658218935'}]}";
		String result = OrderInterfaceService.insertOrUpdateOrder(parm.toString());
		System.err.println(result);
	}
	
	public static void main(String[] args) {
		ESalaryService service = (ESalaryService)EClientContext.getBean(ESalaryService.class);
		JSONObject  j = new JSONObject();
		j.put("orderId", "196375254790008");
		j.put("createSource", "20500005");
		String result = service.insertSalary1(j.toString());
		System.err.println(result);
	}
	
	@Test
	public void testSend(){
		// 给服务人员发通知短信
		JSONObject p_sendParam = new JSONObject();
		p_sendParam.put("mobiles", "15204402582"); // 接收人手机号(必填)
		p_sendParam.put("userIds", 111111); // 接收人ID(必填)
		p_sendParam.put("userNames","测试"); // 接收人姓名(选填)
		p_sendParam.put("params", new String[] { "测试", "15810993912", "22222", "" });// 模板内容(选填)
		p_sendParam.put("templet", "crm_302"); // 模板(必填)
		p_sendParam.put("channel", "sys"); // 通道字典码(yzm:验证码,sys:系统消息,ts:营销消息)(必填)
		p_sendParam.put("type", "2"); // 短信类型字典码(1:续费,2:反馈回复,3:余额变动)(选填)
		p_sendParam.put("rate", "1"); // 发送频率(1:即时发送,2:定时发送)(必填)
		p_sendParam.put("system", "crm"); // 系统码(必填)
		p_sendParam.put("source", "crm|attendance.jsp|save"); // 来源(格式:系统名|页面|按钮)(选填)
		LogHelper.info(getClass(), "给服务人员发服务人员服务费通知短信参数：" + p_sendParam);
		String p_dubboResult = this.smsServiceDubbo.send(p_sendParam.toString());
		LogHelper.info(getClass(), "给服务人员发服务人员服务费通知短信返回：" + p_dubboResult);
	}
	
	@Test
	public void testSendEmpSignOut() throws UnsupportedEncodingException{
			String shortUrl = null;
			String linkConvertUrl = ResourceBundle.getBundle("config").getString("link_convert_url");
			String clockBillUrl = ResourceBundle.getBundle("config").getString("clockRecord_url");
			String param = URLEncoder.encode(clockBillUrl + "?personId=" + 123456 + "&orderId=" + 456789, "UTF-8");
			String jsonStr = SendUtils.sendGet(linkConvertUrl, param);
			if (null != jsonStr && !"".equals(jsonStr)) {
				JSONObject jsonObject1 = JSONObject.fromObject(jsonStr);
				if (jsonObject1.get("msg").equals("00")) {
					shortUrl = jsonObject1.get("shortUrl")!=null?jsonObject1.getString("shortUrl"):"";
				}
			}
			System.err.println(shortUrl);
	}*/
	
	@Test
	public void testInsertOrUpdateAccount(){
		String param = "{\"handle\":\"1\",\"account\":[{\"orderId\":954282932283465,\"isManual\":2,\"isBackType\":2,\"version\":0,\"bussStatus\":1,\"payKind\":2,\"createBy\":686366588304678,\"payType\":4,\"updateBy\":686366588304678,\"startTime\":\"2019-01-23 16:50:58\",\"endTime\":\"2019-01-23 16:50:58\",\"payStatus\":\"20110001\",\"accountAmount\":\"160.00\"},{\"orderId\":954282932283465,\"isManual\":2,\"isBackType\":2,\"version\":0,\"bussStatus\":1,\"payKind\":2,\"createBy\":686366588304678,\"payType\":4,\"updateBy\":686366588304678,\"startTime\":\"2019-01-23 16:50:58\",\"endTime\":\"2019-01-23 16:50:58\",\"payStatus\":\"20110001\",\"accountAmount\":\"160.00\"}]}";
		String result = this.OrderInterfaceService.insertOrUpdateAccount(param);
		System.err.println(result);
	}
	
	@Test
	public void testInsertOrUpdatePayfee(){
		String param = "{\"handle\":\"1\",\"payfee\":[{\"orderId\":954282932283465,\"FeeSum\":\"100\"},{\"orderId\":954282932283465,\"FeeSum\":\"101\"}]}";
		String result = this.OrderInterfaceService.insertOrUpdatePayfee(param);
		System.err.println(result);
	}
	
	@Test
	public void testInsertOrUpdateAccountAndPayfee(){
		String param = "{\"handle\":\"1\",\"toCardPay\":1,\"account\":[{\"orderId\":954282932283465,\"isManual\":2,\"isBackType\":2,\"version\":0,\"bussStatus\":1,\"payKind\":2,\"createBy\":686366588304678,\"payType\":4,\"updateBy\":686366588304678,\"startTime\":\"2019-01-23 16:50:58\",\"endTime\":\"2019-01-23 16:50:58\",\"payStatus\":\"20110001\",\"accountAmount\":\"160.00\"},{\"orderId\":954282932283465,\"isManual\":2,\"isBackType\":2,\"version\":0,\"bussStatus\":1,\"payKind\":2,\"createBy\":686366588304678,\"payType\":4,\"updateBy\":686366588304678,\"startTime\":\"2019-01-23 16:50:58\",\"endTime\":\"2019-01-23 16:50:58\",\"payStatus\":\"20110001\",\"accountAmount\":\"160.00\"}]}";
		String result = this.OrderInterfaceService.insertOrUpdateAccountAndPayfee(param);
		System.err.println(result);
	}
}
