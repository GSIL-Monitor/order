<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
<div class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false" data-keyboard="false">
    <div class="modal-dialog" >
        <div class="modal-content" >
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">操作信息</h4>
            </div>
            <div class="modal-body new-sort" id="new_sort">
                <div class="info clearfix">
                	<input type="hidden" id="accountAmountadOld"/>
                    <form id="balanceAddForm" action="" class="form-inline" method="post"
                        		data-validator-option="{theme:'yellow_left_effect',stopOnError:true}">
                       
                         <div class="row">
                            <div class="form-group col-xs-12">
                                <label>
                                    <p><em style="color: red;">*</em><span id="personTimess"></span>时间：</p>
                                    <input id="interviewStartTimead" name="startTime" class="form-control Wdate" type="text" 
	                                   	 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
                               </label>
                            </div>
                         <!-- <div class="form-group col-xs-12"  id="underDoor_reason" style="display:none;">
                              	<label ><p><span style="color: #FF3333;" id="reason" name="reason">*</span>下户原因:</p></label>
								<label><input value="1" name="reason"  type="radio">合同到期</label>
								<label><input value="2" name="reason"  type="radio">客户终止服务</label>	  
								<label><input value="3" name="reason"  type="radio">客户要求换人</label>
								<label><input value="4" name="reason"  type="radio">服务人员要求下户</label>	
								<label><input value="5" name="reason"  type="radio">其他</label>	  
                         </div> -->
                          <div class="form-group col-xs-12" id="underDoor_reason" style="display:none;">
                                <label ><p><em style="color: #FF3333;" id="reason" name="reason">*</em>下户原因:</p></label>
	                               <select class="form-control" id="oneLevel" >
		                               	 <option value="">--请选择--</option>
		                               	 <option value="1">合同到期</option>
		                               	 <option value="2">客户终止服务</option>
		                               	 <option value="3">客户要求换人</option>
		                               	 <option value="4">服务人员要求下户</option>
		                               	 <option value="6">正常下户</option>
		                               	 <option value="5">其他原因</option>
	                               </select>
	                               <select class="form-control" id="twoLevel">
	                               		 <option value="">--请选择--</option>
	                               </select>
                            </div>
                            <div class="form-group col-xs-12" >
                                <label style="display:none;" id="underDoor_explain">
                                    <p style="line-height: 75px;float:left;"><em style="color: red;" >*</em>下户说明:</p>
                                    <textarea rows="5" cols="" name="explain" id="explain" style="display:inline-block;width:332px;" disabled="disabled"></textarea>                                 	  
                               </label> 
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="saveUpdateItemInterviewCont();">保存</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
	$(function(){
		var ivt = "${param.interviewType}";
		var nowInterviewType = "${param.nowInterviewType}";//当前服务人员上户状态
		if(ivt==8){
			$("#personTimess").text("上户");
			if(nowInterviewType == "11"){
				//顶岗预备
				$("#interviewStartTimead").val("${param.startTime}").prop("disabled",true);
			}else{
				$("#interviewStartTimead").val("${param.startTime}");
			}
		}else{
			radioOperation();
			$("#personTimess").text("下户");
			$("#underDoor_reason").show();
			$("#underDoor_explain").show();
			if(nowInterviewType == "12"){
				//顶岗上户
				$("#interviewStartTimead").val("${param.endTime}").prop("disabled",true);
			}
		}
	});
	function radioOperation(){
		 $('input[name="reason"]').click(function(){
			 var reason = $(this).val();//下户理由
			 if(reason==1){
				 $("#explain").val("履约完成");
			 }else{
				 $("#explain").val("");
			 }
		    });
	}
	// 保存上、下户操作
	function saveUpdateItemInterviewCont(){
		debugger
		var ctx = $("#ctx").val();
		var orderId = "${param.orderId}";
		var dotime = "${param.dotime}";
		var interviewType = "${param.interviewType}";
		var personId = "${param.personId}";
		var totalPay = "${param.totalPay}";
		var customerManageMoney = "${param.customerManageMoney}";
		var ids = "${param.ids}";
		var interviewStartTimead=$("#interviewStartTimead").val();
		if (!interviewStartTimead || $.trim(interviewStartTimead) == '') {
			$.alert({
				text : "请选择时间!"
			});
			return;
		}
		var starTime = "";
		var endTime = "";
		if(interviewType == 8){
			starTime = $("#interviewStartTimead").val();
			//阻止第一个弹框的操作
			$(".modal.in #interviewStartTimead,.modal.in button").prop("disabled",true);
			var message = "上户以后订单排期不可修改,请确认您选择<br/><br/>的上户时间为【<strong>"+starTime+"</strong>】是否正确？";
			$.confirm({text:message,callback:function(cb){
				//取消对第一个弹框的阻止
				$(".modal.in #interviewStartTimead,.modal.in button").prop("disabled",false);
				if(cb){
					$.ajax({
						url: ctx +"/delay/goHourse",
						data:{
							orderId : orderId,
							empId : personId,
							startDate : starTime 
						},
						type:"post",
						dataType:"json",
						async:false,
						success:function(data){
							if (data.code =="0") {
								$.alert({ text : "上户成功！" });
								parent.serverInterviews();
								//刷新服务人员
								parent.getPersonInfo();
								parent.getHistoryPerson();
							}else if(data.code =="-1"){
								$.alert({ text : data.msg });
							}else{
								$.alert({ text : "上户失败，请稍后在试！"});
							}
						}
					});
					closeModule("orderPersonStartCont");
				}
			}})
		}else if(interviewType==9){
			radioOperation();
			endTime = $("#interviewStartTimead").val();
			debugger
			if(endTime<dotime){
				$.alert({text:"下户时间不能小于上户时间！"});
				return;
			}
			var explain = $("#explain").val();//下户说明
			var oneLevelVal = $("#oneLevel").val();//下户原因1级
			var twoLevelVal = $("#twoLevel").val();//下户原因2级
			var message = "错误的下户时间会影响您的服务人员服务费申报, 您选择的下户时间是【<strong>"+endTime+"</strong>】, 请确认";
			 if(!explain || !oneLevelVal || !twoLevelVal){
					$.alert({text:"下户理由、下户说明不能为空！"});
					return;
			} 
			var isOther = $("#twoLevel").find("option:selected").attr("data-other");
			if(isOther == 1){
				var oneText = $("#oneLevel").find("option:selected").text();
				explain = oneText+","+explain;
			}
			$.confirm({text:message,callback:function(a){
				if(a){
					$.ajax({
						url: ctx +"/delay/downHourse",
						data:{
							/* id:ids, */
							orderId : orderId,
							empId : personId,
							/* interviewType:9,
							type:9, */
							reason:oneLevelVal,
							explain:explain,
							endDate : endTime
						},
						type:"post",
						dataType:"json",
						async:false,
						success:function(data){ 
							if (data.code =="0") {						
								$.alert({ text : "下户成功！" });
								//下户发送短息  @author 周鑫 2018-10-30
								smss(ids);
								parent.serverInterviews();
								//刷新服务人员
								parent.getPersonInfo();
								parent.getHistoryPerson();
							}else if(data.code =="-1"){
								$.alert({ text : data.msg });
							}else if(data.code =="5"){
								$.alert({ text : data.msg });
							}else{
								$.alert({ text : "下户失败，请稍后在试！"});
							}
						}
					});
					closeModule("orderPersonStartCont");
				}
			}})
		}
	}
</script>
<script type="text/javascript">
$("#oneLevel").change(function(){
	oneLevelSel();
})
$("#twoLevel").change(function(){
	var thiz = $(this);
	var val = thiz.val();
	//提示
	var twoText = thiz.find("option:selected").text();
	thiz.prop("title",val==""||val==null?"":twoText);
	//是否选择其他
	var oneText = $("#oneLevel").find("option:selected").text();
	var isOther = thiz.find("option:selected").attr("data-other");
	if(isOther == 1){
		$("#explain").val("").prop("disabled",false);
	}else{
		$("#explain").val("").prop("disabled",true);
		if(val != "" && val != null){
			var text = (val==601?twoText:oneText+","+twoText);
			$("#explain").val(text);
		}
	}
})
function oneLevelSel(){
	var val = $("#oneLevel").val();
	var opts = "<option value=''>--请选择--</option>";
	if (val == 1) {
		   opts += "<option value='101'>续签继续用</option>\
					<option value='102'>续签要求换人</option>\
					<option value='103'>不需要服务了</option>\
					<option value='104'>想另找公司</option>";
		} else if (val == 2) {
		   opts += "<option value='201'>找不到合适的人（频繁换人）</option>\
					<option value='202'>服务内容变动（内容、时间、地点等）</option>\
					<option value='203'>家人不同意请人</option>\
					<option value='204'>暂时不用服务</option>\
					<option value='205'>服务人员服务费因素</option>\
					<option value='206'>老人去世</option>";
		} else if (val == 3) {
		   opts += "<option value='301'>技能不好/经验不足</option>\
					<option value='302'>责任心不够（偷懒、应付等）</option>\
					<option value='303'>职业素养太差（找理由、挑事、说闲话等）</option>\
					<option value='304'>饭菜做的不好</option>\
					<option value='305'>卫生做不好/个人卫生</option>\
					<option value='306'>频繁请假</option>\
					<option value='307'>身体素质差</option>\
					<option value='308'>性格不合</option>\
					<option value='309'>生活习惯有差异</option>\
					<option value='310'>与家人关系处不好（老人、小孩等）</option>\
					<option value='311'>服务内容变动（内容、时间、地点等）</option>\
					<option value='312'>服务人员服务费因素</option>\
					<option value='313'>失误造成客户损失</option>\
					<option value='314'>孽待家人（对小孩、老人等）</option>\
					<option value='315'>以前服务人员回来了</option>\
					<option value='316'>服务人员生病</option>\
					<option value='317'>不听客户安排</option>\
					<option value='318'>年龄太大</option>\
					<option value='319'>普通话说不好</option>\
					<option value='320'>带不好孩子</option>\
					<option value='321'>干活太慢</option>\
					<option value='322'>太粗心</option>\
					<option value='323'>健忘症</option>\
					<option value='324'>跟客户吵架</option>\
					<option value='325'>玩手机</option>";
		} else if (val == 4) {
			opts += "<option value='401'>家里有事</option>\
					 <option value='402'>家人生病</option>\
					 <option value='403'>个人身体原因</option>\
					 <option value='404'>服务人员服务费因素</option>\
					 <option value='405'>客户关系不好处（老人、小孩等）</option>\
					 <option value='406'>受到不公平待遇（吃不饱、虐待等）</option>\
					 <option value='407'>服务内容变动（内容、时间、地点等） </option>\
					 <option value='408'>想改行</option>\
					 <option value='409'>不想干了（无特别原因）</option>\
					 <option value='410'>工作量太大</option>\
					 <option value='411'>私自下户</option>\
					 <option value='412'>客户要求太高</option>";
		} else if (val == 5) {
			opts += "<option value='501' data-other='1'>其他</option>";
		} else if (val == 6) {
			opts += "<option value='601'>正常下户</option>";
		}
	 $("#twoLevel").html(opts).prop("title","").trigger("change");
	};
	
	
//下户发送短息，@author 周鑫  2018-10-30
 function smss(ids){
	
	$.ajax({
		url : ctx+"/outHouseSendSmss/sendSmss",
		data : {
			ids:ids,
		},
		type : "POST",
		async : false,
		traditional: true,
		success : function(data) {
			if(data.msg == '00'){
				//成功
			}else{
				//失败
			}
		}
	});
};

</script>
</html>
