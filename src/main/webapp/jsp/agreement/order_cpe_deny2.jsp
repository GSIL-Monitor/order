<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>驳回</title>
</head>
<body>
	<!-- 模态框（Modal） -->
	<div class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">驳回</h4>
				</div>
				<div class="modal-body">
					<form role="form">
						<div  class="form-group">
							<label>请选择驳回原因：
							<select id='cpeDeny' name="" class="form-control">
								<option value="0">...请选择...</option>
								<option value="1">2019年收费政策，一次性预存6月及以上的需支付客户会员费，且客户签订合同的手机号与会员身份手机号必须为同一个</option>
								<option value="2">2019年签订的合同：6个月以下按月预缴的需缴纳客户信息费；</option>
								<option value="3">换人合同的本次支付合计写当前余额，合同开始时间取服务员实际上户日期，其他约定中写上换户</option>
								<option value="4">预付方式不正确：合同期限是一年，预付方式选的一次性预付劳务费，但支付金额是一个月的，应该选其他支付的按一个月支付</option>
								<option value="5">甲方签字重影/不清晰，请管家安排甲方重签</option>
								<option value="6">丙方签字重影/不清晰，请管家安排丙方重签</option>
								<option value="7">甲方/丙方未签字，请管家安排重签</option>
								<option value="8">劳务费支付方式是:平台代付，不是客户直付</option>
								<option value="9">会员费365/888/1998不应该体现在合同中客户信息费一栏，可以填写在其他约定中，管家需要将缴费录入到成为会员下</option>
								<option value="10">签订一个月，应该收取888元会员费，且不应该体现在合同中客户信息费一栏，可以填写在其他约定中，管家需要将缴费录入到成为会员下</option>
								<option value="11">签订两个月，应该收取888*2个月的会员费，且不应该体现在合同中客户信息费一栏，可以填写在其他约定中，管家需要将缴费录入到成为会员下</option>
								<option value="12">缴费金额覆盖合同期限的，支付方式选一次性预付劳务费</option>
								<option value="13">本次支付合计的金额是服务人员服务费加信息费的合计</option>
								<option value="14">本次支付合计金额与合同期限不符,本次支付合计写订单当前余额,如果有特殊情况请在其他约定中填写</option>
								<option value="15">本次支付合计是服务人员的服务费加客户信息费，但订单只有客户信息费的缴费，没有服务人员的服务费的缴费</option>
								<option value="16">其他</option>
							</select><br>
								<p ><span style="color:red;">注：若合同信息需修改且签字有误，请选择5~7以外的理由，否则管家无法修改合同。</span></p>
							
							</label><br>
						</div>
						备注：<br>
						<textarea rows="5" cols="55" id="note"></textarea>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary btn-sm" data-dismiss="modal" id="close">关闭</button>
					<button type="button" class="btn btn-primary btn-sm" id="agreementDeny">提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	
	<script type="text/javascript">
	$(function() {
		//$("#cpeDeny").checkSelect();
	});
		$("#agreementDeny").on("click",function(){
			var cpeDeny=$("#cpeDeny").val();
			var options=$("#cpeDeny option:selected"); 
			var cpeDenyText=options.text()+"<br />备注:"+$("#note").val();
			if(cpeDeny!=0){
				//抹除签字
				debugger
				datas="?elecOtherState=2&checkStatus=1";
				//var advanceperiod= $('input[name="selectCheckBox"]:checked').attr('advanceperiod');
				var valueId = $('input[name="agreement"]:checked').val();
				//判断是否是海金合同
				var reslut=queryKingAgreement(valueId);
				//查询合同
				if(cpeDeny==5){
					var signatories ='';
					if(reslut==1){
						signatories+="[{'identityType':'11','fullName':'北京易盟天地信息技术股份有限公司','email':'623161512@qq.com','identityCard':'91110107764338538E',"+
	                 "'signImg':'',"+
	                 "'pageChapteJson':[{'page':'4','chaptes':[{'offsetX':'0.35','offsetY':'0.465'}]},{'page':'-1','chaptes':[{'offsetX':'0.25','offsetY':'0.82'}]}]}]";
					}else{
						signatorieY='0.495';
	                 signatories+="[{'identityType':'11','fullName':'北京易盟天地信息技术股份有限公司','email':'623161512@qq.com','identityCard':'91110107764338538E',"+
	                     "'signImg':'',"+
	                     "'pageChapteJson':[{'page':'-1','chaptes':[{'offsetX':'0.35','offsetY':"+signatorieY+"}]}]}]";
					}
				    dataw={signatories:signatories,
		                    id:valueId,
		                    role:1,
		                    elecClientState:1}
				    sendDenyAjax(dataw,datas);
				}else if(cpeDeny==6){
					var signatories ='';
					if(reslut==1){
						//signatorieY='0.68';
						signatorieY='0.71';
		                signatories+="[{'identityType':'11','fullName':'北京易盟天地信息技术股份有限公司','email':'623161512@qq.com','identityCard':'91110107764338538E',"+
		                    "'signImg':'',"+
		                    "'pageChapteJson':[{'page':'4','chaptes':[{'offsetX':'0.27','offsetY':"+signatorieY+"}]}]}]";
					}else{
						//signatorieY='0.77';
						signatorieY='0.80';
		                signatories+="[{'identityType':'11','fullName':'北京易盟天地信息技术股份有限公司','email':'623161512@qq.com','identityCard':'91110107764338538E',"+
		                    "'signImg':'',"+
		                    "'pageChapteJson':[{'page':'-1','chaptes':[{'offsetX':'0.27','offsetY':"+signatorieY+"}]}]}]";
					}
					dataw={signatories:signatories,
							id:valueId,
		                    role:2,
		                    elecServeState:'1'}
					sendDenyAjax(dataw,datas);
				}else{
					var signatories1 ='';
					if(reslut==1){
						signatories1+="[{'identityType':'11','fullName':'北京易盟天地信息技术股份有限公司','email':'623161512@qq.com','identityCard':'91110107764338538E',"+
	                 "'signImg':'',"+
	                 "'pageChapteJson':[{'page':'4','chaptes':[{'offsetX':'0.35','offsetY':'0.465'}]},{'page':'-1','chaptes':[{'offsetX':'0.25','offsetY':'0.82'}]}]}]";
					}else{
						signatorieY1='0.495';
	                 signatories1+="[{'identityType':'11','fullName':'北京易盟天地信息技术股份有限公司','email':'623161512@qq.com','identityCard':'91110107764338538E',"+
	                     "'signImg':'',"+
	                     "'pageChapteJson':[{'page':'-1','chaptes':[{'offsetX':'0.35','offsetY':"+signatorieY1+"}]}]}]";
					}
					
					var signatories ='';
					if(reslut==1){
						//signatorieY='0.68';
						signatorieY='0.71';
		                signatories+="[{'identityType':'11','fullName':'北京易盟天地信息技术股份有限公司','email':'623161512@qq.com','identityCard':'91110107764338538E',"+
		                    "'signImg':'',"+
		                    "'pageChapteJson':[{'page':'4','chaptes':[{'offsetX':'0.27','offsetY':"+signatorieY+"}]}]}]";
					}else{
						//signatorieY='0.77';
						signatorieY='0.80';
		                signatories+="[{'identityType':'11','fullName':'北京易盟天地信息技术股份有限公司','email':'623161512@qq.com','identityCard':'91110107764338538E',"+
		                    "'signImg':'',"+
		                    "'pageChapteJson':[{'page':'-1','chaptes':[{'offsetX':'0.27','offsetY':"+signatorieY+"}]}]}]";
					}
					dataw={signatories:signatories1,
							id:valueId,
		                    role:1,
		                    elecClientState:'1'}
					dataw1={signatories:signatories,
							id:valueId,
		                    role:2,
		                    elecServeState:'1'}
					sendDenyAjax(dataw,datas);
					sendDenyAjax(dataw1,datas);
				}
				//获取相应的参数
				agreementDeny(cpeDenyText);
			}else{
				$.alert({millis:2000,text:"请选择驳回类型"});
				return;
			}
			/**驳回**/
			function agreementDeny(checkInstructions){
				var ctx = $("#ctx").val();
				var cpeDeny=$("#cpeDeny").val();
				var data = {};
				data.id = ${param.id};
				data.version =${param.version};
				
				data.checkStatus = 3;
				var agreemenetModel = "${param.agreemenetModel}";
				if(agreemenetModel != null && agreemenetModel != ""){
					if(cpeDeny==5||cpeDeny==6||cpeDeny==7){
						data.agreementState = 6;
						data.elecOtherState = 2;
					}else{
						data.agreementState = 1;
						data.elecOtherState = 1;
					}
					
				}
				data.checkInstructions = checkInstructions;
				$.ajax({
					url:ctx+"/agreement/checkAgreement",
			        data:data,
			        type:'post',
			        async:false,
			        dataType:"json",
			        success:function(data){
			        	if(data.msg == "00"){
			        		$.alert({millis:3000,text:"提交成功!"});
			        		$("#close").trigger("click");
			        		queryCheckAgreement_listPage(1, 10);
			        	}else{
			        		$.alert({millis:3000,text:"提交失败!"});
			        	}
			        }
			    })
			}
		})
		
		function sendDenyAjax(dataw,datas){
		debugger
		var ctx = $("#ctx").val();	
		$.ajax({
             url: ctx + "/orderAgreementGs/contractRemark"+datas,
             /* url:"http://10.10.7.85:8080/order/orderAgreementGs/contractRemark"+datas, */ 
            type: "POST",
            data:dataw,
            dataType: "json",
            async: false,
            success: function (res) {
               debugger
            }
        })
	}
		function queryKingAgreement(checkAgreementId){
			debugger
			var ctx = $("#ctx").val();
			$.ajax({
	            url: ctx +"/agreement/queryKingAgreement",
	            type: "POST",
	            data:{
	            	checkAgreementId:checkAgreementId
	            },
	            dataType: "JSON",
	            async: false,
	            success: function (data) {
	            	debugger
	                return data.count;
	            }
	        });
		}
	</script>
</body>
</html>