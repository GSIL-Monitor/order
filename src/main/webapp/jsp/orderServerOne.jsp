<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<body>
	<div class="pt10">
	<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
		<div style="float:left; width:380px; height:300px; border:1px solid #CCC;">
<%-- 			<iframe src="${ctx}/jsp/orderBaiduMap.jsp"  --%>
<!-- 	      		id="baiduMapOne" name="baiduMapOne" style="" -->
<!-- 	      		frameborder="0" marginwidth="0" -->
<!--                 marginheight="0" height="100%" width="100%" scrolling="yes" allowtransparency="true"> -->
<!--       		</iframe> -->
			<div id="baiduMapOne"></div>
			
		</div>
		<div style="float:right; height:300px; width:480px; ">
			<div>
				<form class="form-inline" action="" method="post">
					<div class="row pt10">
	                   	<div class="form-group  col-xs-12">
	                   		<label>开始时间：
	                   		<input id="startTimeOne" class="Wdate form-control" type="text" 
								onfocus="WdatePicker({minDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" />
	                             &nbsp;结束时间：&nbsp;
							<input id="endTimeOne" class="Wdate form-control" type="text" 
	                     		onfocus="WdatePicker({minDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" />
	                   		</label>
	                   	</div>
	                </div>
	                <div class="row">
					   <div class="form-group col-xs-12">
						 <label style="width:150px;">省：
						 <select style="width:100px;"name="selProvinceOne" id="selProvinceAddressOne" class="form-control" onclick="setSelCity('selProvinceAddressOne','selCityAddressOne')"></select>
						 </label>
						 <label style="width:150px;">市：
						 <select style="width:100px;" name="selCityOne" id="selCityAddressOne" class="form-control" onclick="setSelCountry('selCityAddressOne','selCountryAddressOne')"></select>
						 </label>
						 <label style="width:130px;">区：
						 <select style="width:100px;" name="selCountryOne" id="selCountryAddressOne" class="form-control"></select>
						 </label>
						</div>
					</div>
	                <div class="row">
					    <div class="form-group col-xs-12" id="r-result-one">
						  <label><p>服务地址：</p>
							<input name="suggestIdOne" id="suggestIdOne" style="width:330px;" type="text" class="form-control">
						  </label>
						</div>
				     </div>
				     <div class="row">
					    <div class="form-group col-xs-12">
						  <label><p>备注：</p>
							 <textarea class="form-control form-textarea" style="width:330px;"rows="3" id="remarkOne"></textarea>
						  </label>
						</div>
				     </div>
				</form>
			</div>
		</div>
	</div>      
</body>
<script type="text/javascript">
// 地址输入调用方法
// $('#suggestIdOne').keydown(function(e){
// 	if(e.keyCode==13){
// 		var address = $("#suggestIdOne").val();
// 		initOne:theLocation(address);
// 	}
// });




// 保存
function saveServerOne(){
	var ctx = $("#ctx").val();
	
	var userId = $("#userId").val();
	var receiverId = "";
	var address =$("#suggestIdOne").val();
	var startTime=$("#startTimeOne").val();
	var endTime=$("#endTimeOne").val();
	var remark=$("#remarkOne").val();
	var serverType=$("#categoryCode").val();
	var productCode=$("#serverSelectFourth option:selected").val();
	var productName=$("#serverSelectFourth option:selected").text();
	var province = $("#selProvinceAddressOne option:selected").text();
	var city = $("#selCityAddressOne option:selected").text();
	var country = $("#selCountryAddressOne option:selected").text();
	var cityCode = $("#selCountryAddressOne option:selected").val();
	var priceType = $("#priceType").val();
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
			cateType:1,
			address:address,
			startTime : startTime,
			endTime : endTime,
			remark:remark ,
			serverType:serverType,
			productCode:productCode,
			productName:productName,
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
				result = 0;
			}else{
				alert("保存失败");
			}
		}
	});
	return result ;
}

</script>

