<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
		.modal-order-iframe{height:400px; width: 800px;margin: 30px auto; }
		.otable-tr-top{height:25px; border-top:1px solid #CCC}
		.otable-tr-bottom{height:25px; border-bottom:1px solid #CCC;}
		.otable-td-left{border-bottom:1px solid #CCC; border-left:1px solid #CCC; } 
		.otable-td-right{border-bottom:1px solid #CCC; border-right:1px solid #CCC; } 
	</style>

</head>
<body>
<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
<div class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false">
	 <div class="modal-order-iframe" >
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">订单回访</h4>
            </div>
            <div class="modal-body new-sort" id="new_sort">
                <div class="info clearfix">
                	<input type="hidden" id="accountAmountadOld"/>
                	<input type="hidden" id="accountCustomerManageMoney"/>
                    <form id="balanceAddForm" action="" class="form-inline" method="post"
                        		data-validator-option="{theme:'yellow_left_effect',stopOnError:true}">
                        <div class="row">
                            <div class="form-group col-xs-12">
                            	<label>
	                            	<p style="width:110px;padding-left:2px;"><em style="color: red">*</em>满意度：</p>
	                                    <select id="satisfy" name="satisfy" class="form-control"  style="width:150px;">
	                                        <option value="">请选择</option>
											<option value="1">很满意</option>
											<option value="2">一般满意</option>
											<option value="3">不满意</option> 
	                                    </select>
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-12">
                                <label>
                         			<p style="width:110px;padding-left:2px;">销售意向：</p>
                                    <select style="width:150px;margin-left:2px;" id="oneClassify" name=""  onchange="javascript:oneChange1(this)" class="form-control" >
										<option value="-1">---请选择---</option>                             
                                    </select>
                                    <select style="width:150px;margin-left:2px;" id="twoClassify" name="" onchange="javascript:twoChange2(this)" class="form-control" >
										<option value="-1">---请选择---</option>                             
                                    </select>
                                </label>
                        	</div>
                        	<div class="form-group col-xs-12">
                                <label>
                         			<p style="width:110px;padding-left:2px;"></p>
                                    <select style="width:150px;margin-left:2px;" id="threeClassify" name="classfycode" class="form-control" >
										<option value="-1">---请选择---</option>                                 
                                    </select>
                                </label>
                        	</div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-12">
                                <label>
                                    <p style="width:110px;padding-left:2px;">意向程度：</p>
                                    <select id="meaning" name="meaning" class="form-control" style="width:150px;" onchange="checkMeaning(this);">
                                    	<option value="">请选择</option>
										<option value="1">强烈意向</option>
										<option value="2">一般意向</option>
										<option value="3">远期意向</option>
										<option value="4">无意向</option>  
                                    </select>
                                    <select id="unintentionalReason" name="unintentionalReason" class="form-control" style="width:150px;" disabled="disabled">
                                    	<option value="">请选择</option>
										<option value="1">服务人员服务费高</option>
										<option value="2">不想预存</option>
										<option value="3">只是咨询</option> 
										<option value="4">中介费高</option>
										<option value="5">距离远</option>
										<option value="6">联系不上</option>
										<option value="7">无合适人员</option>
										<option value="8">其他</option> 
                                    </select>
                                </label>
                            </div>
                        </div>
                        <div class="row" >
                            <div class="form-group col-xs-12">
                                <label>
                                	<p style="width:110px;padding-left:2px;"><em style="color: red">*</em>下次回访时间：</p>
                                    <input id="nexttime" name="nexttime" class="form-control Wdate" type="text" 
	                                   	 				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-{%d}',maxDate:'%y-{%M}-{%d+7}'})" style="width:150px;">
                                </label>
                            </div>
                        </div>
                         <div class="row">
                            <div class="form-group col-xs-12">
                                <label>
                                    <p style="width:110px;padding-left:2px;"><em style="color: red">*</em>订单进度：</p>
                                    <select id="orderProgress" name="orderProgress" class="form-control" style="width:150px;" onchange="changeOrderProgress(this);">
										<option value="" >请选择</option>
										<option value='10'>不需要服务</option>
										<option value='22'>不接受收费模式</option>
										<option value='13'>距离远服务不到</option>
										<option value='12'>已经找到服务</option>
										<option value='23'>提供不了初级服务人员</option>
										<option value='24'>提供不了中级服务人员</option>
										<option value='25'>提供不了高级及以上服务人员</option>
										<option value='26'>正在匹配初级服务人员</option>
										<option value='27'>正在匹配中级服务人员</option>
										<option value='28'>正在匹配高级及以上服务人员</option>
										<option value='29'>咨询价格及政策</option>
										<option value='15'>觉得信息费太贵</option>
										<option value='11'>一直联系不上</option>
										<option value='30'>有意向跟进中</option>
										<option value='31'>远期意向</option>
										<option value='32'>正准备签合同</option>
										<option value='33'>已签约</option>
                                    </select>
                                </label>
                            </div>
                        </div>
                        <!-- <div class="row">
							    <div class="form-group col-xs-12">
								  <label><p style="width:110px;padding-left:2px;">其他情况内容：</p>
									 <textarea placeholder="如订单进度选择其他情况,请输入内容,字数限20字"  disabled="disabled" id="orderProgressInfo" class="form-control form-textarea" style="width:400px; height:50px;"rows="2"></textarea>
								  </label>
								</div>
					     </div> -->
                        <div class="row">
							    <div class="form-group col-xs-12">
								  <label><p style="width:110px;padding-left:2px;">备注：</p>
									 <textarea id="remark" class="form-control form-textarea" style="width:400px; height:100px;"rows="10"></textarea>
								  </label>
								</div>
					     </div>
                        
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="save();" >保存</button>
            </div>
        </div>
    </div>
    </div>
</div>
</body>
<script type="text/javascript">
var orderId = "${param.orderId}" ;
var record = "${param.record}" ;
var customId = "${param.customId}" ;
$(function() {
	//查询一级分类
	queryCategory("oneClassify","1",4);
	//查询回访信息
	if(record!=""){
		var ctx = $("#ctx").val();
		$.ajax({
			 url: ctx +"/recorder/queryRecorder",
			data:{
				id : record
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg=="00") {
					$.each(data.list,function(i, v) {
					$("#satisfy option[value='" +v.satisfy +"']").attr("selected","selected");
					queryCategory("oneClassify","1",4);
					$("#oneClassify option[value='" +v.oneclass +"']").attr("selected","selected");
					oneChange(v.oneclass);
					$("#twoClassify option[value='" +v.twoclass +"']").attr("selected","selected");
					twoChange(v.twoclass);
					$("#threeClassify option[value='" +v.classfyCode +"']").prop("selected","ture");
					$("#meaning option[value=" +v.meaning +"]").prop("selected","ture").trigger("change");
					$("#unintentionalReason option[value=" +(v.unintentionalReason||"") +"]").prop("selected",true);
					$("#nexttime").val(v.nextTime);
					$("#remark").val(v.remark); 
					$("#orderProgress").val(v.orderProgress||"").trigger("change");
					$("#orderProgressInfo").val(v.orderProgressInfo||"");
					});
				}
			} 
		});
	}
			
});

//查询商品分类
function queryCategory(typeId,code,length){
	var ctx = $("#ctx").val();
	$.ajax({
		url:ctx+"/item/queryCategory",
		type:"post",
		datetype:"json",
		async : false,
		data:{
			code:code,
			length:length,
			cityCode:101001001
		},
		success : function(data){
			if (data.msg == "00") {
				var html="";
				var result = data.list;
				var html = "<option style='color:blue;' value='0' >---请选择---</option>";
					$.each(result,function(i, p) {
						html += "<option value='"+p.code+"' >"+p.cname+"</option>";  
					});
				$("#"+typeId).html(html);
			}else{
				$("#"+typeId).html("<option style='color:blue;' value='0' >...无可选项...</option>");
			}
		}
	})
}

 function oneChange1(oc){
    var code = oc.options[oc.options.selectedIndex].value;
    queryCategory("twoClassify",code,8);
    $("#threeClassify").html("<option style='color:blue;' value='0' >---请选择---</option>");
} 
/* 查询商品的二级分类 */	
function oneChange(code){
    queryCategory("twoClassify",code,8);
    $("#threeClassify").html("<option style='color:blue;' value='0' >---请选择---</option>");
}

/* 查询商品的三级分类 */
function twoChange2(oc){
	var code = oc.options[oc.options.selectedIndex].value;
    queryCategory("threeClassify",code,12);
}
function twoChange(code){
    queryCategory("threeClassify",code,12);
}
function save(){
	var orderProgress = $("#orderProgress").val();
	var orderProgressInfo = "";
	var satisfy = $("#satisfy").val();
	var val = $("#threeClassify").val().split(",");
	var meaning = $("#meaning").val();
	var nexttime = $("#nexttime").val();
	var remark = $("#remark").val();
	var unintentionalReason = $("#unintentionalReason").val();
	if(satisfy==null||satisfy==""){
		$.alert({
			text : "请选择满意度"
		});
		return ;
	}
	if(nexttime==null||nexttime==""){
		$.alert({
			text : "请选择下次回访时间"
		});
		return ;
	}
	if(meaning && meaning == '4'){
		if(!unintentionalReason){
			$.alert({millis:5000,text:"请选择无意向原因"});
			return ;
		}
	}
	if(orderProgress){
		if(orderProgress == 19){
			orderProgressInfo = $("#orderProgressInfo").val();
			if(!orderProgressInfo){
				$.alert({millis:5000,text : "请输入其他情况内容"});
				return ;
			}else{
				if(orderProgressInfo.length > 20){
					$.alert({millis:5000,text : "其他情况内容限20字，已超"+(orderProgressInfo.length-20)+"字"});
					return ;
				}
			}
		}
	}else{
		$.alert({millis:5000,text : "请选择订单进度"});
		return ;
	}
	if(record==null||record==""){
	$.ajax({
		url: ctx +"/recorder/insertRecorder",
		data:{
			orderId : orderId,
			satisfy : satisfy,
			meaning : meaning,
			nextTime : nexttime,
			remark : remark,
			classfyCode : val[0],
			orderProgress:orderProgress,
			orderProgressInfo:orderProgressInfo,
			customId:customId,
			unintentionalReason:unintentionalReason
		},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			if (data.msg=="00") {
				parent.checkOrderBasic(orderId);
				closeModule("orderReturn");
				$.alert({millis:2000,text:"新增成功"});
			}else{
				$.alert({millis:2000,text:"新增失败"});
			}
		}
	});
	}else{
		$.ajax({
			url: ctx +"/recorder/updateRecorder",
			data:{
				id : record,
				satisfy : satisfy,
				meaning : meaning,
				nextTime : nexttime,
				remark : remark,
				classfyCode : val[0],
				orderProgress:orderProgress,
				orderProgressInfo:orderProgressInfo,
				unintentionalReason:unintentionalReason
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg=="00") {
					parent.checkOrderBasic(orderId);
					closeModule("orderReturn");
					$.alert({millis:2000,text:"修改成功"});		
				}else{
					$.alert({millis:2000,text:"修改失败"});
				}
			}
		});
		
	}
	
}

/**选择订单进度 */
function changeOrderProgress(thiz){
	var val = $(thiz).val();
	var orderProgressInfo = $("#orderProgressInfo");
	if(val == 19){
		orderProgressInfo.val("").prop("disabled",false);
	}else{
		orderProgressInfo.val("").prop("disabled",true);
	}
}

/**选择意向程度*/
function checkMeaning(thiz){
	var val = $(thiz).val();
	if(val == '4'){
		$("#unintentionalReason").val("").prop("disabled",false);
	}else{
		$("#unintentionalReason").val("").prop("disabled",true);
	}
}



</script>
</html>