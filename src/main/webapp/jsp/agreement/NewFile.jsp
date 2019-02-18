<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">

function add(){
	$("#s_agreement_type").val("20520001");
	$("#s_agreement_partyA").val("张浩");
	$("#s_agreement_mobile").val("15111111113");
	$("#s_agreement_linkManMobile").val("15111111114");
	$("#order_agree_add_id_cardNum").val("110108199007255413");
	$("#s_agreement_linkManName").val("李四");
	$("#province_ca").val("101001").trigger("change");
	$("#city_ca").val("101001001").trigger("change");
	$("#county_ca").val("101001001007").trigger("change");
	$("#street_ca").val("石景山路22号");
	$("#s_agreement_platformAddress").val("北京石景山区石景山路22号");
	$("#s_mobileB").val("15111111114");
	$("#province_a").val("101001").trigger("change");
	$("#city_a").val("101001001").trigger("change");
	$("#county_a").val("101001001007").trigger("change");
	$("#street_a").val("石景山路22号");
	$("#s_agreement_createTime").val("2019-01-19 19:00:00");
	$("#s_agreement_endTime").val("2019-04-30 19:00:00");
	$("#s_agreement_contractDate").val("2019-01-19 18:00:00");
	$("#s_agreement_serviceMoney").val("100");
	$("#s_agreement_personManageMoney").val("100");
	$("#s_agreement_chargeTimes").val("1");
	$("#s_agreement_personManageMoney").val("10");
	$("#s_agreement_customerManageMoney").val("0");
	$("#s_agreement_payment").val("110");
	$("#input[name=isCollection][type=radio][value=7]").prop("checked",true);
	$("#input[name=advancePeriod2][type=radio][value=1]").prop("checked",true);
	$("#s_agreement_agreementPayDate_new").val("2019-01-19 19:00:00");
	$("#s_agreement_allPay").val("110");
	$("#s_agreement_otherMatters").val(" 其他约定 其他约定 其他约定 其他约定 其他约定 其他约定 其他约定 其他约定 其他约定 其他约定 其他约定 其他约定 其他约定 其他约定 其他约定 其他约定 其他约定 其他约定 其他约定 其他约定 其他约定 其他约定 其他约定 其他约定 其他约定 其他约定 其他约定 其他约定 其他约定 其 其他");
	$("strong").data("value",1);
	$("#s_authCode_flag").val(1);
	$("#u_authCode_flag").val(1);
	
	
	$("#mobile").val("15810993912");
	$("#mobile_c").val("15810993912");
}
</script>
</body>
</html>