package test;

import java.util.Date;

import com.emotte.order.util.DateUtil;

public class SalaryTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String startDateStr = "2016-6-18";
		Long order_id = 1l;
		Date startDate = DateUtil.str2Date(startDateStr);//订单上户开始时间
		System.out.println(startDate);
		
		Date dateToday = new Date(); 
		Date dateTodayDay = DateUtil.stringDayToDate(DateUtil.dateToStringDay(new Date())) ; //当期日期 年月日
		
		String linshi= startDateStr;
		System.out.println(dateTodayDay);
		for(int i=1;i<30;i++){
			Date tempDate = DateUtil.addDay(DateUtil.addMonth(startDate, i),-1);
			if(dateTodayDay.getTime()-tempDate.getTime()<0){
				break;
			}else{
				
			}
			System.out.println( "第 "+i+"个月的服务人员服务费开始日期:"+linshi+" 到结束日期："+DateUtil.getYmd(tempDate));
			linshi = DateUtil.getYmd(DateUtil.addMonth(startDate, i));
		}
		
		/*for()
		
		Date dateToday = new Date();
		
		for*/

	}

	
	
	
    
}
