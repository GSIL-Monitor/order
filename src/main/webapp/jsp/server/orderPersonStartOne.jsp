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
<div class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false">
    <div class="modal-dialog" >
        <div class="modal-content">
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
                            <div class="form-group col-xs-6">
                                <label>
                                    <p><span style="color: red">*</span><span id="personTimes"></span>时间：</p>
                                    <input id="interviewStartTimead" name="starTime" class="form-control Wdate" type="text" 
	                                   		onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
                               </label>
<!--                                <label> -->
<!--                                		<p><span style="color: red">*</span>下户时间：</p> -->
<!--                                		<input id="interviewEndTimead" name="endTime" class="form-control Wdate" type="text"  -->
<!-- 	                                   	 				onfocus="WdatePicker({minDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  > -->
<!--                                 </label> -->
                            </div>
                            <div class="form-group col-xs-6" id="personQuantityStartOneDiv" style="display:none;">
                                <label>
                                    <p><span style="color: red">*</span>总数量：</p>
                                    <input id="personQuantityStartOne" type="text" class="form-control" style="width:80px;" 
						 				onkeydown="inputOnlyNum(event);" onkeypress="checkIfNum(event);"    >
                               </label>
<!--                                <label> -->
<!--                                		<p><span style="color: red">*</span>下户时间：</p> -->
<!--                                		<input id="interviewEndTimead" name="endTime" class="form-control Wdate" type="text"  -->
<!-- 	                                   	 				onfocus="WdatePicker({minDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"  > -->
<!--                                 </label> -->
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="saveUpdateItemInterviewOne();">保存</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
$(function(){
	
	var ivt = "${param.interviewType}";
	if(ivt==9){
		$("#personTimes").text("下户");
		$("#personQuantityStartOneDiv").css("display","block");
	}else{
		$("#personTimes").text("上户");
	}
});
var keyIsNum = true;
function inputOnlyNum(myEvent) {
	var keys = myEvent.keyCode;
	if (!((keys >= 48 && keys <= 57) || (keys >= 96 && keys <= 105)
			|| (keys == 8) || (keys == 46) || (keys == 37) || (keys == 39)
			|| (keys == 13) || (keys == 229)||(keys == 190)||(keys == 110))) {
		keyIsNum = false;
	} else {
		keyIsNum = true;
	}

}
function checkIfNum(myEvent) {
	if (!keyIsNum) {
		$.alert({
			text : "请输入数字！"
		});
		if (document.all)
			myEvent.returnValue = false;//ie
		else
			myEvent.preventDefault();//ff
	}

}



/**长链接转短链接-20180706  xyp 添加*/
var  messageShortUrls;
function sendpiesgetShortUrl(linkinfo,messageUrl){
	    //长链接参数
			  $.ajax({
					url :linkinfo,
					data :{url:messageUrl},
					type : "POST",
					async : false,
					success : function(data) {
						if(data.msg == '00' ){
							messageShortUrls=data.shortUrl;
						}else{
							messageShortUrls="";
						}
					}
				});
	
	//console.log(messageShortUrls);
}


/**发送派工单验证 该订单是否已发送-20180706  xyp 添加*/
function checkpiesInfos(){
	var ctx = $("#ctx").val();
	var orderId = "${param.orderId}";
	var mobileBasicinfos="${param.mobileBasicinfos}";
	var count = 0;
	$.ajax({
		url: ctx +"/itemInterview/validateSendpies",
		data:{
			orderId:orderId,
			mobilePhone:mobileBasicinfos,
		},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){	
			if (data.msg =="00") {
				count = data.viewcount;
			}else{
				count = -1;
			}
		}
	})
	return count;
}



//20180706 xyp 添加-手动发送派工单方法
 function sendpies() {
	//
	var ctx = $("#ctx").val();
	var orderId = "${param.orderId}";
	var mobileBasicinfos="${param.mobileBasicinfos}";
	
	var num = checkpiesInfos();
	if(num>0){
			$.alert({millis:5000,text:"该订单已发送过派工单,发送客户电话为:"+mobileBasicinfos+",请勿重复发送！"});
			return;
	}else if(num<0){
		    $.alert({millis:3000,text:"查询异常，请稍后处理！"});
		    return;
	}

	//弹出确认窗口 给提示
	$.confirm({text:"即将为电话为:"+mobileBasicinfos+"的客户发送派工单,请确认.",callback:function(re){
		if(re){
		/* 	var messageUrl="http://erp.95081.com/jzjy-wechat/html/singleSurvey/index.html?id="+orderId;
			var linkinfo="http://erp.95081.com/sms/su/ts"; */
			var messageUrl = "http://" + window.location.host + "/jzjy-wechat/html/singleSurvey/index.html?id=" + orderId;
            var linkinfo = "http://" + window.location.host + "/sms/su/ts";
			
			//长连接转短链接
			sendpiesgetShortUrl(linkinfo,messageUrl);
			$.ajax({
				url: ctx +"/itemInterview/sendpiesinfo",
				data:{
					orderId:orderId,
					mobilePhone:mobileBasicinfos,
					shortUrl:messageShortUrls
				},
				type:"post",
				dataType:"json",
				async:false,
				success:function(data){
					
					if(data.myvalida=="1"){
						  $.alert({millis:5000,text: "短信已发送！" });
					}else if(data.myvalida =="0"){
						  $.alert({millis:5000,text:"前端已发送！" });
					}else if(data.myvalida =="2"){
						$.alert({millis:3000,text:"短信发送失败！" });
					}else{
						$.alert({millis:3000,text:"短信发送异常！" });
					}
				}
			});
			
		}else{
			return;
		}
	 }});
	
  }
    //手动发送派工单方法 end


   


	// 保存上、下户操作
	function saveUpdateItemInterviewOne(){
    	debugger
		var ctx = $("#ctx").val();
		// var intvId = "${param.intvId}";
		var orderId = "${param.orderId}";
		var interviewType = "${param.interviewType}";
		var ids="${param.ids}"
		var personid="${param.personid}";
		var dotime="${param.dotime}";
		var type=0;
		var shanghu="";
		var interviewStartTimead=$("#interviewStartTimead").val();
		if (!interviewStartTimead || $.trim(interviewStartTimead) == '') {
			$.alert({
				text : "请选择时间!"
			});
			return;
		}
		var starTime = "";
		var endTime = "";
		var quantity = $("#personQuantityStartOne").val();
		if(interviewType==9&&(quantity==null||quantity=="")){
			$.alert({
				text : "请填写总数量!"
			});
			return;
		}
		if(interviewType==8){
			type=12;
		  shanghu="上户已选服务人员,释放未选择的服务人员订单排期？";
			starTime = $("#interviewStartTimead").val();
		}else if(interviewType==9){
			type=13;
			endTime = $("#interviewStartTimead").val();
			//下户
			if(dotime>endTime){
				$.alert({
					text : "下户时间不能小于上户时间"
				});
				return;
			}
			shanghu="错误的下户时间会影响您的服务人员服务费申报, 您选择的下户时间是【<strong>"+endTime+"</strong>】, 请确认."
		}else{
			type=14;
		}
			$.confirm({text:shanghu,callback:function(re){
				 if(re){
					$.ajax({
						url: ctx +"/itemInterview/updateItemInterview",
						data:{
							ids : ids,
							personids:personid,
							orderId:orderId,
							starTime:starTime,
							endTime:endTime,
							interviewType:interviewType,
							quantity:quantity,
							type:type
						},
						type:"post",
						dataType:"json",
						async:false,
						success:function(data){
							if(data.msg=='00'){
								if(interviewType==8){
									parent.$("#checkedOrderStatus").val(8);
								}else if(interviewType==9){
									parent.$("#checkedOrderStatus").val(11);
									//订单状态已完成   发送 派工单
									sendpies();
								}
								parent.getPersonInfoOne();
								parent.getPersonInfoHisOne();
								
								closeModule("orderPersonStartOne");
							}else{
								$.alert({
									text : "保存失败！"
								});
							}
						}
					});
				} 
			}
			});
	}
</script>
</html>