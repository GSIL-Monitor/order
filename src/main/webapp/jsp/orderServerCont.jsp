<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<body>
	<div style="height:5px; border-bottom:1px solid #CCC;"></div>
	<div >
		<div style="float:left; width:380px; height:300px; border:1px solid #CCC;">
<%-- 			<iframe src="${ctx}/jsp/orderBaiduMap.jsp"  --%>
<!-- 	      		id="baiduMapCont" name="baiduMapCont" style="" -->
<!-- 	      		frameborder="0" marginwidth="0" -->
<!--                 marginheight="0" height="100%" width="100%" scrolling="yes" allowtransparency="true"> -->
<!--       		</iframe> -->
      		<div id="baiduMapCont"></div>
		</div>
		<div style="float:right; height:300px; width:480px; ">
			<form class="form-inline" action="" method="post">
				<div class="row pt10">
                   	<div class="form-group  col-xs-12">
                   		<label>开始时间：
                   		<input id="startTimeCont" class="Wdate form-control" type="text" 
							onfocus="WdatePicker({minDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" />
                             &nbsp;结束时间：&nbsp;
						<input id="endTimeCont" class="Wdate form-control" type="text" 
                     		onfocus="WdatePicker({minDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" />
                   		</label>
                   	</div>
                </div>
                <div class="row">
				   <div class="form-group col-xs-12">
					 <label style="width:150px;">省：
					 <select style="width:100px;"name="selProvinceCont" id="selProvinceAddressCont" class="form-control" onclick="setSelCity('selProvinceAddressCont','selCityAddressCont')"></select>
					 </label>
					 <label style="width:150px;">市：
					 <select style="width:100px;" name="selCityCont" id="selCityAddressCont" class="form-control" onclick="setSelCountry('selCityAddressCont','selCountryAddressCont')"></select>
					 </label>
					 <label style="width:130px;">区：
					 <select style="width:100px;" name="selCountryCont" id="selCountryAddressCont" class="form-control"></select>
					 </label>
					</div>
				</div>
                <div class="row">
				    <div class="form-group col-xs-12" id="r-result-cont">
					  <label><p>服务地址：</p>
						 <input id="suggestIdCont" type="text" class="form-control" style="width:320px;" name="suggestIdCont">
					  </label>
					</div>
			     </div>
			     <div class="row">
				    <div class="form-group col-xs-12">
					  <label><p>面试时间：</p>
					  	<input id="interviewTimeCont" class="Wdate form-control" type="text" 
							onfocus="WdatePicker({minDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd HH:mm'})" />
					  </label>
					</div>
			     </div>
			     <div class="row">
				    <div class="form-group col-xs-12">
					  <label><p>面试地址：</p>
						 <input id="interviewAddressCont" type="text" class="form-control" style="width:320px;"name="interviewAddressCont" onclick="theLocationCont(2);">
					  </label>
					</div>
			     </div>
			     <div class="row">
				    <div class="form-group col-xs-12">
					  <label>学历要求：
					  	<select id="educationCont" class="form-control"></select>
					  </label>
					  <label>等级要求：
					  	<select id="levelCont" class="form-control"></select>
					  </label>
					</div>
			     </div>
			     <div class="row">
				    <div class="form-group col-xs-12">
					  <label>籍贯要求：
					  	<select id="originCont" class="form-control"></select>
					  </label>
					  <label>年龄要求：
					  	<input id="minAgeCont" class="form-control" type="text" style="width:65px;">
						至<input id="maxAgeCont" class="form-control" type="text" style="width:65px;">
					  </label>
					</div>
			     </div>
			     <div class="row">
				    <div class="form-group col-xs-12">
					  <label>居家面积：
					  	<input id="homeForestsCont" class="form-control" type="text" >
					  </label>
					  <label>家里人口：
					  	<input id="familysCont" class="form-control" type="text" >
					  </label>
					</div>
			     </div>
			     <div class="row">
				    <div class="form-group col-xs-12">
					  <label><p>备注：</p>
						 <textarea id="remarkCont" class="form-control form-textarea" style="width:320px;"rows="3"></textarea>
					  </label>
					</div>
			     </div>
			</form>
		</div>
	</div>      
</body>
<script type="text/javascript">
//地址输入调用方法
// $('#addressCont').keydown(function(e){
// 	if(e.keyCode==13){
// 		theLocationCont(1);
// 	}
// });
//地址输入调用方法
// $('#interviewAddressCont').keydown(function(e){
// 	if(e.keyCode==13){
// 		theLocationCont(2);
// 	}
// });
// function theLocationCont(num){
// 	var address = $("#addressCont").val();
// 	if(num==2){
// 		address = $("#interviewAddressCont").val();
// 	}
// 	document.getElementById("baiduMapCont").contentWindow.theLocation(address);
// }


function saveServerCont(){
	var ctx = $("#ctx").val();
	
	var orderType = parent.$("#").val();
	var userId = $("#userId").val();
	var receiverId = "";
	var address =$("#suggestIdCont").val();
	var startTime=$("#startTimeCont").val();
	var endTime=$("#endTimeCont").val();
	var interviewTime=$("#interviewTimeCont").val();
	var interviewAddress=$("#interviewAddressCont").val();
	var origin=$("#originCont").val();
	var education=$("#educationCont option:selected").val();
	var minAge=$("#minAgeCont").val();
	var maxAge=$("#maxAgeCont").val();
	var homeForests=$("#homeForestsCont").val();
	var familys=$("#familysCont").val();
	var remark=$("#remarkCont").val();
	var serverType=$("#categoryCode").val();
	var productCode=$("#serverSelectFourth option:selected").val();
	var productName=$("#serverSelectFourth option:selected").text();
	var province = $("#selProvinceAddressCont option:selected").text();
	var city = $("#selCityAddressCont option:selected").text();
	var country = $("#selCountryAddressCont option:selected").text();
	var cityCode = $("#selCountryAddressCont option:selected").val();
	var priceType = $("priceType").val();
	var longitude = $("#orderAddLongitude").val();
	var latitude = $("#orderAddLatitude").val();
	var orderChannel = $("#orderAddChannel").val();
	if(productCode==0){
		alert("请选择正确的服务类商品！");
		return ;
	}
	var result = 1 ;
	$.ajax({
		url: ctx +"/itemDetailServer/insertItemDetailServer",
		data:{
			userId:userId,
			receiverId:receiverId,
			cateType:2,
			address:address,
			startTime : startTime,
			endTime : endTime,
			interviewTime : interviewTime,
			interviewAddress : interviewAddress,
			origin : origin,
			education : education,
			minAge : minAge,
			maxAge : maxAge,
			homeForests : homeForests,
			familys : familys,
			remark:remark ,
			serverType:serverType,
			productCode:productCode,
			productName:productName,
			critical:1 ,
			province:province,
			city:city,
			country:country,
			cityCode:cityCode,
			priceType:priceType,
			longitude:longitude,
			latitude:latitude,
			orderChannel:orderChannel
		},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			if(data.msg=='00'){
				result = 0 ;
			}else{
				alert("保存失败！");
			}
		}
	});
	return result ;
}

$(function(){
	queryAllSelect(null,"education","educationCont",null);
	queryAllSelect(null,"serverType","levelCont",null);
	queryOriginDictionary("","originCont",null);
}) ;
</script>

