<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<body>
	<!-- 字段的命名规则 T--three,O--order,I--in,S--Server,E--Extended,A--Add -->
	<div class="row">
		<div class="form-group col-xs-6">
			<div class="row">
				<div class="form-group col-xs-6">
					<label>
						<p><span style="color:red">*</span>开始时间：</p>
						<input id="TOI_S_E_A_startTime" class="Wdate form-control" type="text" 
                                    onfocus="WdatePicker({minDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd HH:mm'})" />
					</label>
				</div>
				<div class="form-group col-xs-6">
					<label>
						<p><span style="color:red">*</span>结束时间：</p>
						<input id="TOI_S_E_A_endTime" class="Wdate form-control" type="text" 
                                    onfocus="WdatePicker({minDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd HH:mm'})" />
					</label>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-xs-6">
					<label>
						<p><span style="color:red">*</span>面试时间：</p>
						<input id="TOI_S_E_A_interviewTime" class="Wdate form-control" type="text" 
                                    onfocus="WdatePicker({minDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd HH:mm'})" />
					</label>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-xs-12">
					<label>
						<p><span style="color:red">*</span>面试地址：</p>
						<input id="TOI_S_E_A_interviewAddr"  name="TOI_S_E_A_interviewAddr" class="form-control" type="text"
							 style="width:300px;" />
					</label>
				</div>
			</div>
			
		
			<div class="row">
				<div class="form-group col-xs-12">
					<label>
						<p><span style="color:red">*</span>订单渠道：</p>
						<select id="TOI_S_E_A_originchannel" name="TOI_S_E_A_originchannel" class="form-control">
						</select>
					</label>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-xs-12">
					<label class="form-label">
						<p>备注：</p>
						<textarea class= "form-control form-textarea" rows= "3" id="TOI_S_E_A_remark" ></textarea>
					</label>
				</div>
			</div>
		</div>
		<div class="form-group col-xs-6">
			<div class="row">
				<div class="form-group col-xs-6">
					<label>
						<p>学历要求：</p>
						<select id="TOI_S_E_A_education" name="TOI_S_E_A_education"  class="form-control">
							<option style="color:blue;" value="">...请选择...</option>
							<option value="1" keyvalue="小学">小学</option>
							<option value="2" keyvalue="初中">初中</option>
							<option value="3" keyvalue="高中">高中</option>
							<option value="4" keyvalue="大专">大专</option>
							<option value="5" keyvalue="本科">本科</option>
							<option value="6" keyvalue="硕士">硕士</option>
							<option value="7" keyvalue="博士">博士</option>
						</select>
					</label>
				</div>
				<div class="form-group col-xs-6">
					<label>
						<p>等级要求：</p>
						<select id="TOI_S_E_A_level" name="TOI_S_E_A_level"  class="form-control">
							<option style="color:blue;" value="">...请选择...</option>
							<option value="1" keyvalue="商品">商品</option>
							<option value="3" keyvalue="保洁">保洁</option>
							<option value="4" keyvalue="陪护">陪护</option>
							<option value="5" keyvalue="小时工">小时工</option>
							<option value="6" keyvalue="保姆">保姆</option>
							<option value="7" keyvalue="育儿嫂">育儿嫂</option>
							<option value="8" keyvalue="月嫂">月嫂</option>
						</select>
					</label>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-xs-12">
					<label>
						<p>籍贯要求：</p>
						<input  id="TOI_S_E_A_origin"  class="form-control" maxlength="30"/>
					</label>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-xs-12">
					<label>
						<p>年龄要求：</p>
						<input id="TOI_S_E_A_minAge" class="form-control" maxlength="2" 
						onkeyup="javascrpt:if(!(event.ctrlKey||event.shiftKey)){this.value=this.value.replace(/[^\d]/g,'');}"
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />至
						<input id="TOI_S_E_A_maxAge" class="form-control" maxlength="2" 
						onkeyup="javascrpt:if(!(event.ctrlKey||event.shiftKey)){this.value=this.value.replace(/[^\d]/g,'');}"
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
					</label>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-xs-6">
					<label>
						<p>居家面积：</p>
						<input id="TOI_S_E_A_homeForests" class="form-control" 
						onkeyup="javascrpt:if(!(event.ctrlKey||event.shiftKey)){this.value=this.value.replace(/[^\d]/g,'');}"
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" 
						onkeydown="inputOnlyNum(event);" onkeypress="checkIfNum(event);" 
						style="ime-mode:Disabled"/>
					</label>
				</div>
				<div class="form-group col-xs-6">
					<label>
						<p>家里人口：</p>
						<input id="TOI_S_E_A_familys" class="form-control" 
						onkeyup="javascrpt:if(!(event.ctrlKey||event.shiftKey)){this.value=this.value.replace(/[^\d]/g,'');}"
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
						onkeydown="inputOnlyNum(event);" onkeypress="checkIfNum(event);" 
						style="ime-mode:Disabled"/>
					</label>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group col-xs-1">
			<label>
				<p><span style="color:red">*</span>服务地址：</p>
				<input type="hidden" id="unicodes">
			</label>
		</div>
		<div id="TOI_S_E_A_userAddrList" class="form-group col-xs-8" style="overflow: auto;  display: block; height:100px;">
		</div>
		<div class="form-group col-xs-2">
			<button type="button" class="btn btn-sm btn-primary" 
				onclick="openModule('/order/jsp/order_three/in/addr_operator/orderThreeFA_AddrAdd.jsp',{lx:1},'','','orderThreeFA_AddrAdd');">
				新增地址
			</button>
		</div>
	</div>
		
</body>
<script type="text/javascript">
var keyIsNum = true;
function inputOnlyNum(myEvent)
	{
	var keys=myEvent.keyCode;
	if (!((keys>=48&&keys<=57)||(keys>=96&&keys<=105)||( keys==8 )||(keys==46)||(keys==37)||(keys==39)||(keys==13)||(keys==229)
	))
	{
	keyIsNum = false;
	}
	else
	{
	keyIsNum = true;
	}
	
	}
	function checkIfNum(myEvent)
	{
	if(!keyIsNum)
	{
		$.alert({text:"请输入数字！"});
	if(document.all)
	myEvent.returnValue=false;//ie
	else
	myEvent.preventDefault();//ff
	}
	
	}
	
	// 保存自营延续性服务
	function saveServerExtended(){
		
		var ctx = $("#ctx").val();
		//用户
		var userId = $("#TOI_A_userId").val();
		var receiverId = "";
		//开始时间
		var startTime=$("#TOI_S_E_A_startTime").val();
		//结束时间
		var endTime=$("#TOI_S_E_A_endTime").val();
		//服务地址
		var receiverId = $("#TOI_A_userAddrId").val();
		//面试时间
		var interviewTime=$("#TOI_S_E_A_interviewTime").val();
		//面试地址
		var interviewAddress=$("#TOI_S_E_A_interviewAddr").val();
		//教育要求
		var education=$("#TOI_S_E_A_education option:selected").val();
		//等级要求
		var personLevel = $("#TOI_S_E_A_level option:selected").val();
		//籍贯要求
		var origin=$("#TOI_S_E_A_origin").val();
		//年龄要求 最小值
		var minAge=$("#TOI_S_E_A_minAge").val();
		//年龄要求 最大值
		var maxAge=$("#TOI_S_E_A_maxAge").val();
		//住房面积
		var homeForests=$("#TOI_S_E_A_homeForests").val();
		//人口
		var familys=$("#TOI_S_E_A_familys").val();
		var chennel = $("#TOI_S_E_A_originchannel option:selected").val();
		//备注
		var remark=$("#TOI_S_E_A_remark").val();
		// 服务类型：3类别
		var serverType=$("#TOI_A_threeClassify").val();
		var productCode = $("#TOI_A_serverType").val();
		var serverText=$("#TOI_A_serverType option:selected").text();
		serverType = serverType.split(",")[0];
		
		if(!userId || $.trim(userId)=='' ){
			$.alert({text:"请选择用户。"});
			return;
		}
		if(!productCode ||$.trim(productCode) == ''){
			$.alert({text:"请选择产品。"});
			return ;
		}
		
		if(!startTime || $.trim(startTime)=='' 
			|| !endTime || $.trim(endTime)=='' ){
			$.alert({text:"请选择开始、结束时间。"});
			return;
		}
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
		
		if(!receiverId || $.trim(receiverId)==''){
			$.alert({text:"请选择地址。"});
			return;
		}
		
		if(!interviewTime || $.trim(interviewTime)==''){
			$.alert({text:"面试时间 不能为空。"});
			return;
		}
		
		if(!interviewAddress || $.trim(interviewAddress)==''){
			$.alert({text:"面试地址 不能为空。"});
			return;
		}
		
		if(!chennel || $.trim(chennel)==''){
			$.alert({text:"请选择订单渠道。"});
			return;
		}
		var result = 1 ;
		$.ajax({
			url: ctx +"/threeOrderIn/saveThreeOrder",
			data:{
				userId:userId,
				receiverId:receiverId,
				cateType:2,
				startTime : startTime,
				endTime : endTime,
				receiverId:receiverId,
				interviewTime : interviewTime,
				interviewAddress:interviewAddress,
				education : education,
				personLevel:personLevel,
				origin : origin,
				minAge : minAge,
				maxAge : maxAge,
				homeForests : homeForests,
				familys : familys,
				remark:remark ,
				serverType:serverType,
				productCode:productCode,
				productName:serverText,
				orderChannel:chennel,
				critical:1 
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if(data.msg=='00'){
					result = 0 ;
				}else{
					$.alert({text:"保存失败。"});
				}
			}
		});
		return result ;
	}

	$(function(){
		queryBaseChannel(null,"TOI_S_E_A_originchannel");
		//清除之前选择的服务地址
		$("#TOI_A_userAddrId").val("");
		//调用
		getAddrListByUser("TOI_S_E_A_userAddrList");
		
		
	}) ;
	
</script>

