package test;

import java.util.Iterator;

import net.sf.json.JSONObject;

public class OrderTest {
	public static void main(String[] args) {
		String json = "{'accountStatus':'2','updateBy':1,'isBackType':1,'feeSum':200,'feePost':20250010,'cardsNum':'','agentUser':1,'payStatus':20110002}";
		//String json = "{'userCity':'110100','listItem':[{'nowPrice':'101.99','productCode':'001001001183337718217588','quantity':'1','productName':'6月15号水果'}],'critical':'2',payStatus':'1'}" ;
		JSONObject jobj=JSONObject.fromObject(json);
		System.out.println(jobj.get("accountStatus"));
		//System.out.println(JSONObject.fromObject(JSONArray.fromObject(jobj.get("item")).get(0)).get("productCode"));
		Iterator it = jobj.keys();
		StringBuffer result = new StringBuffer("{");
		while(it.hasNext()){
			String key = (String) it.next();  
			String value = jobj.getString(key);
			if(value!=null && !value.equals("")){
				if(!result.toString().equals("{")) result.append(",");
				result.append("\"" +key +"\":\"" +value +"\"");
			}
		}
		result.append("}");
		System.out.println(result.toString());
//		Object obj01 = JSONObject.toBean(jobj, Order.class);
//		Order order = (Order)obj01;
//		System.out.println(order.getUserCity() +" and " +order.getListItem());
//		JSONArray jsonItem = JSONArray.fromObject(jobj.get("item"));
//		List list = null ;
//		for(Object objjj : jsonItem){
//			JSONObject jite=JSONObject.fromObject(objjj);
//			System.out.println("0000000000000");
//			Object oite = JSONObject.toBean(jite, Item.class);
//			System.out.println("1111111111111111111");
//			Item item = (Item)oite;
//			System.out.println(item.getNowPrice());
//		}
		//JSONObject jobj=JSONObject.fromObject(json);
//		System.out.println(jobj.get("userCity"));
//		Object obj = JSONObject.toBean(jobj, Item.class);
//		System.out.println(obj);
//		Item item = (Item)obj;
//		System.out.println(item.getQuantity());
//		JSONArray jsonItem = JSONArray.fromObject(item); 
//		System.out.println(jsonItem);
	}
	
	public static long gt(){
	    return convert(getTime());
	  }
	  public static long getTime(){
	    long l=System.currentTimeMillis()-1388505600000l;
	    long l1=Math.abs(System.nanoTime()/1000)%1000;
	    return l*1000+l1;
	  }
	  public static long convert(long l){
	    String s=Long.toHexString(l);
	    StringBuffer buf=new StringBuffer();
	    buf.append(s.charAt(11)).append(s.charAt(1)).append(s.charAt(10))
	        .append(s.charAt(9)).append(s.charAt(8)).append(s.charAt(7))
	        .append(s.charAt(6)).append(s.charAt(3)).append(s.charAt(5))
	        .append(s.charAt(4)).append(s.charAt(2)).append(s.charAt(0));
	    long r=Long.parseLong(buf.toString(), 16);
	    if (r<100000000000000l){
	      r=r+300000000000000l;
	    }
	    double d=Math.random();
	    if (d>0.666){
	      r=r+600000000000000l;
	    }else if (d>0.333){
	      r=r+300000000000000l;
	    }
	    return r;
	  }
}
