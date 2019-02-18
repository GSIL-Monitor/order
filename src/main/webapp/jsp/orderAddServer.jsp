<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<body>
	<div style="height:5px; border-bottom:1px solid #CCC;"></div>
	<div >
			<form class="form-inline" action="" method="post">
				<div class="row pt10">
				 <input type="hidden" id="StartTimeSolt"/>
				 <input type="hidden" id="EndTimeSolt">
				 <input type="hidden" id="StartTime">
				 <input type="hidden" id="EndTime">
				 <input type="hidden" id="numStock">
					<div class="row" id="oneServeStock" style="display:none">
                   	 	 <div class="form-group  col-xs-3" style="margin-left:15px;">
                       		<label>
                       			<span style="font-weight:bold;">服务日期：</span>
                        		<input id="timeServer" class="Wdate form-control" type="text" 
									onfocus="WdatePicker({onpicked:selUpTimes,Date:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" />
                       		</label>
                        	</div>

					 	<div class="form-group  col-xs-3">
                       		<label>
                       			<span style="font-weight:bold;">单人服务时长 ：</span>
                       			<select name="servicehours" id="servicehours" class="form-control" onchange="changeTimes()">
                        			<option style="color:blue;" value="">...请选择...</option>
                        		</select>
                       		</label>
                       	</div>
					 	<div class="form-group  col-xs-3">
                       		<label>
                       			<span style="font-weight:bold;">服务时间段 ：</span>
                        		<select name="timesoltKun" id="timesoltKun" class="form-control" onchange="checkNumStock()">
                        			<option style="color:blue;" value="">...请选择...</option>
                        		</select>
                       		</label>
                       	</div>
                       
 					</div>
                   	<div class="form-group  col-xs-12" id="serverOneTime" >
                   		<div class="form-group  col-xs-4" id="serverTimeStart" > 
	                   		<label style="float:left;">
	                   			开始时间：
	                   			<input id="startTimeServer" class="Wdate form-control" type="text"  placeholder="订单排期"
								onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTimeServer\')}',dateFmt:'yyyy-MM-dd HH:mm',onpicked:pickedFunc})" onclick="removeDisabledBegin()"/>
	                   		</label>
                   		</div>
                   		<div class="form-group  col-xs-4" id="serverTimeEnd">
                   			<label style="float:left;">
	                          	&nbsp;结束时间：&nbsp;
								<input id="endTimeServer" class="Wdate form-control" type="text" onclick="removeDisabledEnd()"  placeholder="订单排期"
	                     		onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTimeServer\')}',dateFmt:'yyyy-MM-dd HH:mm',onpicked:pickedFunc1})" />
	                   		</label>
                   		</div>
                   		<div class="form-group  col-xs-4" style="width:310px;float:left;" id="birthDate">
                            	<label><span style="font-weight:bold;">预产期 ：</span>
                            		<input id="birthDatetime" class="Wdate form-control" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                            	</label>
                        </div>	
                   	</div>
                   	
                </div>
                <div class="row">
                   	<div class="form-group  col-xs-12">
                   		<div class="form-group  col-xs-3"  style="display:none" id="typeCaboli">
                       		<label>
                       			<span style="color: red">*</span>服务面积 ：
                        		<input id="ForestsServerOne" class="form-control" type="text" onkeydown="inputOnlyNum(event);" onkeypress="checkIfNum(event);" style="ime-mode:Disabled">
                       		</label>
                       		
                       	</div>
                   		<input type="hidden" id="productUnitAddServer" >
                   		<input type="hidden" id="productPriceTypeAddServer" >
                   		<label>
							<span style="color: red">*</span>需要服务人员个数：
					  		<input id="personNumberServer" class="form-control" type="text" style="width:90px;ime-mode:Disabled" 
					  		onkeydown="inputOnlyNum(event);" onkeypress="checkIfNum(event);">
				  		</label>
                   		<label><span style="color: red">*</span>总数量(<span id="productUnitTextAddServer"></span>)：
						 <input id="pickQuantityServer" type="text" class="form-control" name="pickQuantityServer" style="width:80px;ime-mode:Disabled" onkeydown="inputOnlyNum(event);" onkeypress="checkIfNum(event);"    >
						</label>
						<label>价格(元)：
						 <input id="totalPayAddServer" type="text" class="form-control" name="totalPayAddServer" readonly="true" style="width:120px;" >
						</label>
						<label>价格体系：
						 <input id="priceTextAddServer" type="text" class="form-control" name="totalPayAddServer" readonly="true" style="width:200px;">
						</label>
						<label>规格：
						 <input id="productSpecAddServer" type="text" class="form-control" readonly="true" style="width:120px;">
						</label>
                   	</div>
                </div>
			     <div id="serverAddCont" style="display:none;">
			     <div class="row">
				    <div class="form-group col-xs-12">
					  <label>面试时间：
					  	<input id="interviewTimeServer" class="Wdate form-control" type="text" 
							onfocus="WdatePicker({minDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd HH:mm'})" />
					  </label>
					  <label>面试地址：
						 <input id="interviewAddressServer" type="text" class="form-control" style="width:320px;"name="interviewAddressServer" >
					  </label>
					</div>
			     </div>
			     <div class="row">
				    <div class="form-group col-xs-12">
					  <label>学历要求：
					  	<select id="educationServer" class="form-control">
					  		<option style='color:blue;' value='' >...请选择...</option>
					  		<option value='1' >无学历</option>
					  		<option value='2' >小学</option>
					  		<option value='3' >初中</option>
					  		<option value='4' >高中/中专</option>
					  		<option value='5' >本科/大专</option>
					  		<option value='6' >研究生</option>
					  	</select>
					  </label>
					  <label>等级要求：
					  	<select id="levelServer" class="form-control"></select>
					  </label>
					  <label>籍贯要求：
					  	<select id="originServer" class="form-control"></select>
					  </label>
					  <label>年龄要求：
					  	<input id="minAgeServer" class="form-control" type="text" maxlength="2"  style="width:50px;" onkeyup="javascrpt:if(!(event.ctrlKey||event.shiftKey)){this.value=this.value.replace(/[^\d]/g,'');}"
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						至<input id="maxAgeServer" class="form-control" type="text"  maxlength="2"  style="width:50px;" onkeyup="javascrpt:if(!(event.ctrlKey||event.shiftKey)){this.value=this.value.replace(/[^\d]/g,'');}"
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					  </label>
					</div>
			     </div>
			     <div class="row">
				    <div class="form-group col-xs-12">
					  <label>居家面积：
					  	<input id="homeForestsServer" class="form-control" type="text" onkeydown="inputOnlyNum(event);" onkeypress="checkIfNum(event);" style="ime-mode:Disabled">
					  </label>
					  <label>家里人口：
					  	<input id="familysServer" class="form-control" type="text" onkeydown="inputOnlyNum(event);" onkeypress="checkIfNum(event);" style="ime-mode:Disabled">
					  </label>
					   <label>性别要求：
					  	<select id="sexAddServer" class="form-control">
					  		<option value='-1' selected="selected">不限</option>
					  		<option value='1' >男</option>
					  		<option value='2' >女</option>
					  	</select>
					  </label> 
					</div>
			     </div>
			     </div>
			     <div id="addContSex" style="display:none;">
			     	<label>性别要求：
					  	<select id="sexInsert" class="form-control">
					  		<option value='-1' selected="selected">不限</option>
					  		<option value='1' >男</option>
					  		<option value='2' >女</option>
					  	</select>
					  </label>
			     </div>
			     <div class="row">
				    <div class="form-group col-xs-12">
					  <label><p>备注：</p>
						 <textarea id="remarkServer" class="form-control form-textarea" style="width:320px; height:40px;"rows="3"></textarea>
					  </label>
					  <!-- <label>性别要求：
					  	<select id="sexInsert" class="form-control">
					  		<option value='-1' selected="selected">不限</option>
					  		<option value='1' >男</option>
					  		<option value='2' >女</option>
					  	</select>
					  </label> -->
					</div>
			     </div>
			     <div class="row">
		            <div class="form-group col-xs-2">
		                <label>
		                    <p><span style="color: red">*</span>服务地址：</p>
		                </label>
		            </div>
					<div class="form-group col-xs-8" >
		                <div class="panel-content">
		                    <table id="listBodyAddressServer" class="table order-table" style="margin-bottom:0">
							</table>
						</div>
					</div>
					<div class="form-group col-xs-2">
		                <button type="button" class="btn btn-primary btn-sm"
							onclick="openModule('/order/jsp/orderItemaddressAdd.jsp',{lx:3,userId:$('#orderAddUserId').val(),userAddressId:0},'','','orderItemaddressAdd')">新增地址</button>
		            </div>
				</div>
			</form>
		</div>
</body>
<script type="text/javascript" src="${ctx}/js/orderBase.js"></script>
<script type="text/javascript">
	var keyIsNum = true;
 function inputOnlyNum(myEvent) {
	var keys=myEvent.keyCode;
	if (!((keys>=48&&keys<=57)||(keys>=96&&keys<=105)||( keys==8 )||(keys==46)||(keys==37)||(keys==39)||(keys==13)||(keys==229)||(keys == 190)||(keys == 110))) {
		keyIsNum = false;
	} else {
		keyIsNum = true;
	}
}
	function checkIfNum(myEvent) {
		if(!keyIsNum) {
			$.alert({text:"请输入数字！"});
			if(document.all)
			myEvent.returnValue=false;//ie
			else
			myEvent.preventDefault();//ff
		}
	}
	function selUpTimes(){
		var ctx = $("#ctx").val();
		var cityCode = parent.$("#orderAddCity").val();
		var productCode = parent.$("#categoryCode").val();
		var timeServer = $("#timeServer").val();
		if(timeServer!=null||timeServer!=""){
	$.ajax({
		url: ctx +"/itemDetailServer/queryOrderKucun",
		data:{
			cityCode : cityCode,
			productCode : productCode,
			startTimeLinedOne : timeServer 
		},
		type:'post',
		async:false,
		dataType:'json',
		success:function(data){
			var option= "<option style='color:blue;' value='0'>...请选择...</option>";
			for(i=0;i<data.hours.length;i++){
				 option += "<option value="+data.hours[i].name+" >"+data.hours[i].name+"</option>";
					$("#servicehours").html(option); 
				}
				
			var option1= "<option style='color:blue;' value='0'>...请选择...</option>";
			for(i=0;i<data.quantums.length;i++){
					option1 += "<option value="+data.quantums[i]+">"+data.quantums[i]+"</option>";
				$("#timesoltKun").html(option1);
			}
			$("#timesoltKun option[value=" +data.quantums[0]+"]").prop("selected",true);
			
	 	}
	 	});
	}
}
function changeTimes(){
	var ctx = $("#ctx").val();
	var cityCode = parent.$("#orderAddCity").val();
	var productCode = parent.$("#categoryCode").val();
	var timeServer = $("#timeServer").val();
	var  servicehours=$("#servicehours").val();
	$.ajax({
		url: ctx +"/itemDetailServer/queryOrderKucun1",
		data:{
			cityCode : cityCode,
			productCode : productCode,
			startTimeLinedOne :timeServer,
			serviceHours : servicehours
		},
		type:'post',
		async:false,
		dataType:'json',
		success:function(data){
			var	option="<option style='color:blue;' value='0'>...请选择...</option>";
			for(i=0;i<data.quantums.length;i++){
				option += "<option value="+data.quantums[i]+">"+data.quantums[i]+"</option>";
			$("#timesoltKun").html(option);
		}
		}
	 	});
}
function checkNumStock(){
	$.ajax({
        url: ctx +"/orderbase/checkNumStock",
        data:{
            dkey    : parent.$("#orderAddCity").val(),
            dvalue  : parent.$("#categoryCode").val(),
            remarks :$("#timeServer").val(),
            level   : $("#timesoltKun").val().split("-")[0],
            types   : $("#timesoltKun").val().split("-")[1]
        },
        type:'post',
        async:false,
        dataType:'json',
        success:function(data){
            $("#numStock").val(data.count);
       	 }
        });
}
//checkNumStock(parent.$("#orderAddCity").val(),parent.$("#categoryCode").val(),$("#timeServer").val(),$("#timesoltKun").val());
function saveServerAdd(cateType){
	/* if(!$("#startTimeServer").val() || $.trim($("#startTimeServer").val())=='' 
		|| !$("#endTimeServer").val() || $.trim($("#endTimeServer").val())=='' ){
		$.alert({text:"请选择开始、结束时间。"});
		return;
	} */
	var ctx = $("#ctx").val();
	var userId = $("#orderAddUserId").val();
	var orderChannel = $("#orderAddChannel").val();
	var productCode=$("#serverSelectFourth option:selected").val().split(",")[0];
	if(productCode==0){
		$.alert({text:"请选择正确的服务类商品！"});
		return ;
	}
	var birthDate=$("#birthDatetime").val();
	var orderFenfa=$("#orderFenfa").val();
	var mendianjidi = "";
	var guanjiaxingming = "";
	if(orderFenfa == "-2"){
		mendianjidi=$("#mendianjidi").val();
		guanjiaxingming=$("#guanjiaxingming").val();
	}
	var  personId=0;
	var channel=$("#orderAddChannel").val()
	if(channel==212028286803173||channel=='212028286803173'){
		personId =$("input[name='person_id']:checked").val();
	}
	//当选择新居开荒是进行数量判空 update 20181212 zhanghao 所有订单都校验总数量
	var pickQuantity = $("#pickQuantityServer").val();
	var reg = /^[0-9]{1,}$/;
	if(pickQuantity==null || pickQuantity=="" ){
		$.alert({text:"请填写总数量！"});
		return ;
	}
//	else if(!reg.test(pickQuantity)){
//		$.alert({millis: 2000, text: "总数量请输入大于0的整数数字！"});
//		return;
//	}else if(pickQuantity == 0){
//		$.alert({millis: 2000, text: "总数量请输入大于0的整数数字！"});
//		return;
//	}
	var receiverId = $("#orderAddReceiverId").val();
	var longitude = $("#orderAddLongitude").val();
	var latitude = $("#orderAddLatitude").val();
	if(longitude==null||longitude==""||latitude==null||latitude==""){
		$.alert({text:"当前选择的地址没有经纬度坐标，请完善地址或重新选择！"});
		return;
	}
	var serverType=$("#categoryCode").val();
	var productName=$("#serverSelectFourth option:selected").attr("data-name");
	var cityCode = $("#orderAddCity option:selected").val();
	var remark=$("#remarkServer").val();
	var productUnit=$("#productUnitAddServer").val();
	var productPriceType=$("#productPriceTypeAddServer").val();
	var productSpec=$("#productSpecAddServer").val();
	var personNumber = $("#personNumberServer").val();
	var threeOrderCode = $("#threeOrderCode_add").val();
	var insure = $("#insure").val();//是否投保
	var insureAmount = $("#insureAmount").val();//投保金额
	/** 周鑫  2018-12-11 **/
    var serviceObject = $("#serviceObject").val();
    var display=$("#serviceObjectView").css("display");
	if(display=='none'){
		serviceObject=2;
	}
	if(serviceObject=='0'){
		$.alert({text:"请选择服务对象 !"});
		return;
	}
    /** 周鑫 **/
	var interviewTime="";
	var interviewAddress="";
	var origin="";
	var education="";
	var minAge="";
	var maxAge="";
	var homeForests="";
	var familys="";
	var personLevel = "";
	if(cateType==1 || cateType==4){
		sex = $("#sexInsert").val();
	}else{
		var sex = "" ;
	}
	var priceType = $("#priceType").val();
	// 查询是否享受解决方案价格
	var solution = $("#orderAddSolutionOrNot").val();
	if(solution==1){
		priceType = 20000005;
	}
	var startTime="";
	var endTime="";
	if(cateType==1){
		var timeServer = "";
	  	var  serviceHours = "";
	  	var  timesoltKun ="";
	  	var ifKunCun ="";
	  	var ForestsServer="";
		var str={};
		str = SelStockOne($("#orderAddCity option:selected").val(),parent.$("#categoryCode").val())
		if ("error" == str) {
			return;
		}
		startTime=str.startTime;
		endTime=str.endTime;
		serviceHours=str.serviceHours;
		timesoltKun=str.timesoltKun;
		ForestsServer=str.ForestsServer;
		ifKunCun=str.ifKunCun;
	    if(startTime==""||startTime==null){
			$.alert({text:"请输入服务日期!"});
			return;
		}
		if(serviceHours==""||serviceHours==null){
			$.alert({text:"请选择服务时长!"});
			return;
		}
		if(timesoltKun==""||timesoltKun==null){
			$.alert({text:"请选择时间段 !"});
			return;
		}
		/* var verification = $("#endTimeServer").val().split(" ")[0].split("-")[1] - $("#startTimeServer").val().split(" ")[0].split("-")[1];
		if(verification != 0){
			$.alert({text:"暂不支持隔天服务。"});
			return;
		}else{
			var verification = $("#endTimeServer").val().split(" ")[0].split("-")[2] - $("#startTimeServer").val().split(" ")[0].split("-")[2];
			if(verification != 0){
				$.alert({text:"暂不支持隔天服务。"});
				return;
			}
		} */
		if(serviceHours.split("小")[0] < 1){
			$.alert({text:"服务时间不能小于一小时。"});
			return;
		}
		if(parseInt(personNumber)>parseInt($("#numStock").val())){
			$.alert({text:"库存不足!"});
			return;
		}
		//擦玻璃增加服务面积  深度保洁   新居开荒  深度保洁
		var threeClassifyCode=$("#threeClassify").val().split(",")[0];
		if(threeClassifyCode==100200020004){
		  if(ForestsServer==null||ForestsServer==""){
			  $.alert({text:"请输入服务面积!"});
				return;
		    }
		}
	}
	if(cateType==2){
		startTime=$("#startTimeServer").val();
		endTime=$("#endTimeServer").val();
		interviewTime=$("#interviewTimeServer").val();
		interviewAddress=$("#interviewAddressServer").val();
		origin=$("#originServer").val();
		education=$("#educationServer option:selected").val();
		minAge=$("#minAgeServer").val();
		maxAge=$("#maxAgeServer").val();
		homeForests=$("#homeForestsServer").val();
		familys=$("#familysServer").val();
		sex = $("#sexAddServer").val();
		personLevel = $("#levelServer").val();
		//add 20181128 zhanghao 取消时间校验
//		if(!startTime || $.trim(startTime)==''
//			|| !endTime || $.trim(endTime)=='' ){
//			$.alert({text:"请选择开始、结束时间。"});
//			return;
//		}
		if(!startTime || $.trim(startTime)=='' || !endTime || $.trim(endTime)=='' ){
			try{
				var st = new Date(startTime.replace(/\-/g, "\/"));
				var et = new Date(endTime.replace(/\-/g, "\/"));
				if(et<st){
					$.alert({text:"开始时间大于结束时间。"});
					return;
				}
			}catch(e){
				$.alert({text:"时间格式不正确。"});
				return;
			}
		}
		if (parseInt(minAge) > parseInt(maxAge)){
			$.alert({text:"请输入正确的年龄大小!"});
			return;
		}
	}else{
		if(personNumber<=0){
			$.alert({text:"请确定服务人员数量！"});
			return ;
		}
	}
	if(cateType==4){
		startTime=$("#startTimeServer").val();
		endTime=$("#endTimeServer").val();
	}
	
	if(receiverId==null || receiverId=="" || receiverId==0){
		$.alert({text:"请选择服务地址！"});
		return ;
	}
	var result = 1 ;
	$.ajax({
		url: ctx +"/itemDetailServer/insertItemDetailServer",
		data:{
			userId:userId,
			receiverId:receiverId,
			cateType:cateType,
			startTime : startTime,
			endTime : endTime,
			pickQuantity : pickQuantity,
			interviewTime : interviewTime,
			interviewAddress : interviewAddress,
			origin : origin,
			education : education,
			minAge : minAge,
			maxAge : maxAge,
			homeForests : homeForests,
			familys : familys,
			personNumber : personNumber,
			sex : sex,
			personLevel : personLevel,
			remark : remark ,
			serverType : serverType,
			productCode : productCode,
			productName : productName,
			critical : 2 ,
			cityCode : cityCode,
			priceType : priceType,
			longitude : longitude,
			latitude : latitude,
			orderChannel : orderChannel,
			productUnit : productUnit,
			productPriceType : productPriceType,
			productSpec : productSpec,
			orderFenfa : orderFenfa,
			rechargeDept : mendianjidi,
			rechargeBy : guanjiaxingming,
			emp_id : personId,
			birthDate : birthDate,
			serviceHours : serviceHours ,
			timeSlot :timesoltKun,
			forestsServer:ForestsServer,
			cityCode :parent.$("#orderAddCity").val(),
			categoryCode:parent.$("#categoryCode").val(),
			ifKunCun:ifKunCun,
			threeOrderCode:threeOrderCode,
			insure:insure,
			insureAmount:insureAmount,
			serviceObject:serviceObject
		},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			if(data.msg=='00'){
				result = 0 ;
			}else{
				$.alert({text:"保存失败！"});
			}
		}
	});
	return result ;
}

$(function(){
	queryWorkTypeLevel("levelServer",null);
	queryOriginDictionary("","originServer",null);
}) ;
//查询库存
function SelStockOne(cityCode,cateCode){
	var str1={};
	var flag = true;
	$.ajax({
		url:ctx+"/orderbase/selectStock",
		data : {
			dkey   : cityCode,
			dvalue : cateCode
		},
		type : 'post',
		async : false,
		dataType : "json",
		success : function(data) {
			if (data.msg == "00") {
				//有库存
				startTime = $("#timeServer").val()+" "+$("#timesoltKun").val().split("-")[0]+":"+"00";
				endTime = $("#timeServer").val()+" "+$("#timesoltKun").val().split("-")[1]+":"+"00";
				serviceHours = $("#servicehours").val();
				timesoltKun = $("#timesoltKun").val();
				ForestsServer=$("#ForestsServerOne").val();
				ifKunCun="1";
				str1.startTime=startTime;
				str1.endTime=endTime;
				str1.serviceHours=serviceHours;
				str1.timesoltKun=timesoltKun;
				str1.ForestsServer=ForestsServer;
				str1.ifKunCun=ifKunCun;
			}else{
				 startTime = $("#startTimeServer").val();
				 endTime = $("#endTimeServer").val();
				 var timesolt1 = parseInt(startTime.substring(14,17));
				 var timesolt2 = parseInt(endTime.substring(14,17));
				 if(15<=timesolt1&&timesolt1<=45){
					 timesolt1="30"
				 }else if((timesolt1>45&&timesolt1<60)||(timesolt1<15&&timesolt1>0)||(timesolt1==0)){
					 timesolt1="00"
				 }
				 if(15<=timesolt2&&timesolt2<=45){
					 timesolt2="30"
				 }else if((timesolt2>45&&timesolt2<60)||(timesolt2<15&&timesolt2>0)||(timesolt2==0)){
					 timesolt2="00"
				 }
			  	 timesoltKun = startTime.substring(11,13)+":"+timesolt1+"-"+endTime.substring(11,13)+":"+timesolt2;
			  	 if(timesolt2==timesolt1){
					 serviceHours =endTime.substring(11,13)-startTime.substring(11,13)+"小时";
			  	 }else if(timesolt2<timesolt1){
			  		serviceHours =(endTime.substring(11,13)-startTime.substring(11,13)-1)+".5"+"小时";
			  	 }else if(timesolt2>timesolt1){
			  		serviceHours =(endTime.substring(11,13)-startTime.substring(11,13))+".5"+"小时";
			  	 }
			  	var verification = $("#endTimeServer").val().split(" ")[0].split("-")[1] - $("#startTimeServer").val().split(" ")[0].split("-")[1];
				if(verification != 0){
					$("#orderAddSaveo").removeAttr("disabled");
					$.alert({text:"暂不支持隔天服务。"});
					flag = false;
					return;
				}else{
					var verification = $("#endTimeServer").val().split(" ")[0].split("-")[2] - $("#startTimeServer").val().split(" ")[0].split("-")[2];
				  	 if(verification != 0){
				  		$("#orderAddSaveo").removeAttr("disabled");
						$.alert({text:"暂不支持隔天服务。"});
						flag = false;
						return;
					}
				}
			  	ForestsServer=$("#ForestsServerOne").val();
			  	ifKunCun="";
			  	str1.startTime=startTime;
				str1.endTime=endTime;
			  	str1.serviceHours=serviceHours;
			  	str1.timesoltKun=timesoltKun;
			  	str1.ifKunCun=ifKunCun;
			  	str1.ForestsServer=ForestsServer;
			}
		}
	});
	if (flag) {
		return  str1;
	} else {
		return "error";
	}
}
function pickedFunc(){
	var min=parseInt($dp.cal.getP('m'));
	if(15<=min&&min<=45){
		$("#StartTime").val(parseInt($dp.cal.getP('H')));
		$("#StartTimeSolt").val($dp.cal.getP('H')+":"+"30");
	}
	if((min>45&&min<60)||(min<15&&min>0)){
		$("#StartTime").val(parseInt($dp.cal.getP('H')));
		$("#StartTimeSolt").val($dp.cal.getP('H')+":"+"00");
	}  
	}
function pickedFunc1(){
	var min=parseInt($dp.cal.getP('m'));
	if(15<=min&&min<=45){
		$("#EndTime").val(parseInt($dp.cal.getP('H')));
		$("#EndTimeSolt").val($dp.cal.getP('H')+":"+"30");
	}
	if((min>45&&min<60)||(min<15&&min>0)){
		$("#EndTime").val(parseInt($dp.cal.getP('H')));
		$("#EndTimeSolt").val($dp.cal.getP('H')+":"+"00");
	}  
	}
	
	function removeDisabled(){
		$("#orderAddSaveo").removeAttr("disabled");
	}
	function removeDisabledEnd(){
		/** 修改时间限制条件 周鑫 2018-11-13 **/
		var isProdServe = $("#baseProduct").find("option:selected").attr("data-isProdServe");
		if(isProdServe==2){
			 $("#startTimeServer").attr("onfocus","WdatePicker({maxDate:'#F{$dp.$D(\\'endTimeServer\\',{d:-1})}',dateFmt:'yyyy-MM-dd HH:mm',onpicked:pickedFunc1})");
		}
		$("#orderAddSaveo").removeAttr("disabled");
	}
	
	
	/** 修改结束  **/
	//结束时间限制，更改为，当前天数的后一天
	/* 周鑫   2018-11-13   */
	function removeDisabledBegin(){
		var isProdServe = $("#baseProduct").find("option:selected").attr("data-isProdServe");
		if(isProdServe==2){
			 $("#endTimeServer").attr("onfocus","WdatePicker({minDate:'#F{$dp.$D(\\'startTimeServer\\',{d:1})}',dateFmt:'yyyy-MM-dd HH:mm',onpicked:pickedFunc1})");
		}
		$("#orderAddSaveo").removeAttr("disabled");
	}
</script>

