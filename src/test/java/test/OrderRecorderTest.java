package test;

import com.emotte.eclient.EClientContext;
import com.emotte.interf.EReturnVisitService;

public class OrderRecorderTest {

	public static void main(String[] args) {
		EReturnVisitService service = (EReturnVisitService) EClientContext.getBean(EReturnVisitService.class);
		String result = service.insertReturnVisit("{'orderId':111111111}");
		System.err.println(result);
	}

}
