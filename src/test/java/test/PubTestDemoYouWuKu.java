package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wildhorse.server.core.utils.encryptUtils.MD5;

import com.emotte.order.util.DateUtil;
import com.emotte.order.util.HttpUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/application*.xml"})
public class PubTestDemoYouWuKu {
	
	
	
	public String getSign(String aa){
        /*$tmp_param = $this->argSort($param) ;
        $tmp_param_str = $this->createLinkstring($tmp_param) ;
        $sign_param_str = $param["uid"].$tmp_param_str ;
        $sign = strtoupper(md5($sign_param_str)) ;*/
        return null ;
    }
	
	
	
	
	/*@Resource
	TestService testService;

	@org.junit.Test
	public void testDubbo(){
		String tt = testService.testDubbo("测试接口");
		System.out.println(tt);
	}*/
	

	
	
	
	@org.junit.Test
	public void testPiPei(){
		System.out.println("测试匹配");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("USER_ID","123");
		map.put("USER_NAME", "");
		map.put("REAL_NAME", "");
		String result = HttpUtil.post("http://hjcrm.95081.com/ekt_gjb/index.php/gjb/cc_interface/register_sync",map, null, null);
		net.sf.json.JSONObject jo = net.sf.json.JSONObject.fromObject(result);
		
	}
	
	@org.junit.Test
	public String sign(Map<String,String> map){
		System.out.println("asdadasd");
		map = new HashMap<String, String>();
		
		
		map.put("zz", "23");
		map.put("mm", "324");
		map.put("ss", "234");
		map.put("aa", "324");
		map.put("bb", "234");
		
		
		if(map==null||map.isEmpty()) return null;
		Set<String> set1 = map.keySet();
		List ll = new ArrayList(set1);
		Collections.sort(ll);
		for (Object obj : ll) {
			System.out.println(obj);
		}
		
		
		return null;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(DateUtil.dateToTimeStamp(new Date()));
		String client_id="w29244";  //商家client_id,平台提供
		String appscret="1df301128b42d4992ced511248fba350"; //商家密钥，平台提供
		String v ="3.0"; //版本号
		String platform = "sina"; //请求平台数据，目前仅支持sina
		String sign_type="md5"; //签名加密方式,目前仅支持md5加密方式，默认md5
		String format  = "json";  //数据返回格式,目前仅支持json格式
		String timestamp=DateUtil.dateToTimeStamp(new Date());
		String host = "http://bestweshop.dianking.cn/egou/index.php/api/uwuku/";  //请求主机地址请勿修改
		String method = "youwuku.order.get";
		String request_type = "order";

		System.out.println("asdadasd");
		HashMap<String,String> map = new HashMap<String, String>();
		
		
		//map.put("appscret", appscret);
		map.put("uid", client_id);
		map.put("platform", platform);
		map.put("sign_type", sign_type);
		map.put("format", format);
		map.put("v", v);
		map.put("request_type", request_type);
		map.put("method", method);
		map.put("timestamp",timestamp);
		map.put("fields", "tid,status,payment,total_fee");
		map.put("page", "1");
		map.put("page_no", "5");
	
		Set<String> set1 = map.keySet();
		List ll = new ArrayList(set1);
		Collections.sort(ll);
		StringBuffer bf = new StringBuffer("");
		StringBuffer bfSim = new StringBuffer("");
		for (Object obj : ll) {
			System.out.println(obj+"="+map.get(obj));
			//bf.append(obj+"="+map.get(obj)+"&");
			bfSim.append(obj+map.get(obj));
		}
		//String ss =bf.toString().substring(0,bf.toString().length()-1);
		//System.out.println(ss);
		String a ="appscret"+appscret+bfSim.toString();
		System.out.println("::::::"+a);
		String canAndVal = client_id+a;
		System.out.println("11::::"+canAndVal);
		String sign = (MD5.MD5Encode(canAndVal).toUpperCase());
		System.out.println(sign);
		map.put("sign", sign);
		//System.out.println(MD5.MD5Encode("123456").toUpperCase());
		String url = host+request_type;
		String result = HttpUtil.post(url,map, null, null);
		System.out.println(result);
		net.sf.json.JSONObject jo = net.sf.json.JSONObject.fromObject(result);
		System.out.println(jo);
		
	}
	
	
	
	
	
	
}