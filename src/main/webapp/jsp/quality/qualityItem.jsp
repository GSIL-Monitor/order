<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<link type="text/css" href="${ctx}/css/fileinput.css" media="all" rel="stylesheet" />
 	<script src="${ctx}/js/fileinput.js" type="text/javascript" charset="utf-8"></script>
 	<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
 	<style>
 	.form-inline .input-group > .file-caption {
    width: 100%;
    height: 33px;
	}
	.form-inline .input-group .input-group-btn {
	    width: 1%;
	    height:30px;
	} 
	</style>
</head>
<body>
	<div class="modal fade">  
	    <div class="modal-dialog">
	    	<div class="modal-content">  
	    		<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"  onclick="closeModule('qualityItem')">×</button>  
			        <h4 class="modal-title">新增售后>>新增FA售后单</h4>
		        </div>
	      <form class="form-inline" id="qualityItemForm">
	      		<input type="hidden" id="itemOrderStatus"/>
	      		<input type="hidden" id="itemPayStatus"/>
	      		<input type="hidden" id="itemParentId"/>
	      		<input type="hidden" id="itemBankCardMoney"/>
			      <div class="modal-body" >
			      	<div class="modal-content-basic">
			      	 <header><h4>客户信息</h4></header>
		      	<div class="info-select clearfix" >
		      		<div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>客户姓名：</p>
	                            <span id="itemUserName" name="custName"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>联系方式：</p>
	                            <span id="itemMobile" name="custMobile"></span>
	                        </label>
	                    </div>
	                </div>
	                </div>
	                <header><h4>订单信息</h4></header>
	               <div class="info-select clearfix" >
	                 <div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单编号：</p>
	                            <span id="itemOrderCode"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单类型：</p>
	                            <span id="itemOrderType"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>支付状态：</p>
	                            <span id="itemPayText"></span>
	                        </label>
	                    </div>
	                    
	                 </div>
	                <div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>收货人姓名：</p>
	                            <span id="itemReceiptName"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>收货人电话：</p>
	                            <span id="itemReceiptMobile"></span>
	                        </label>
	                    </div>
	                    <!-- <div class="form-group col-xs-4">
	                        <label>
	                            <p>收货时间：</p>
	                            <span id="serverReceiptDate"></span>
	                        </label>
	                    </div> -->
	                 </div>
	                 
	                 <div class="row">
	                    <div class="form-group col-xs-12">
	                        <label>
	                            <p>收货人地址：</p>
	                            <span id="itemReceiptAddress"></span>
	                        </label>
	                    </div>
	                 </div>
	                 <div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单总金额：</p>
	                            <span id="itemTotalPay"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>配送金额：</p>
	                            <span id="itemDeliver"></span>
	                        </label>
	                    </div>
	                 </div>
	               <div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单状态：</p>
	                            <span id="itemStatus"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单渠道：</p>
	                            <span id="itemOrderChannel"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单来源：</p>
	                            <span id="itemOrderSource"></span>
	                        </label>
	                    </div>
	                 </div>
	                 <div class="row">
	                    <div class="form-group col-xs-12">
	                        <label class="form-label">
	                            <p>订单备注：</p>
	                            <textarea rows="2" id="itemOrderRemark" style="height:50px;" class= "form-control form-textarea" readonly="readonly"></textarea>
	                        </label>
	                    </div>
	                 </div>
	                 </div>
	                <header><h4>需求信息</h4></header>
	               <div class="info-select clearfix" >
		                <div class="panel-content table-responsive">
		                    <table class="table table-hover table-striped">
						        <thead>
						        <tr>
						            <th style="width:15%">序号</th>
						            <th style="width:35%">商品名称</th>
						            <th style="width:25%">价格</th>
						            <th style="width:25%">数量</th>
						        </tr>
						        </thead>
						        <tbody id="FAInfo">
						        </tbody>
						     </table>
					     </div>
	                    <!-- <div class="form-group col-xs-4">
	                        <label>
	                            <p>收货人姓名：</p>
	                            <span id="itemReceiptName"></span>
	                        </label>
	                    </div> -->
	                </div>
	                <header><h4>售后信息</h4></header>
	               <div class="info-select clearfix" >
	               <div class="row">
	               		<div class="form-group col-xs-12">
	                        <label>
	                            <p><span style="color:red">*</span>售后类型：</p>
	                            <select class="form-control" name="afterSalesKind" id="item_afterSalesKind" >
	                           			 <option value="">...请选择...</option>
	                                     <option value="1">FA订单取消</option>
	                                     <!-- <option value="2">FA订单退货/退款</option>
	                                     <option value="3">FA订单换货重发</option> -->
	                             </select>
	                        </label>
	                    </div>
	               </div>
	               <div class="row" id="itemQualityApproveDiv">
                        <div class="form-group col-xs-6">
							<label>
								<p><span style="color:red">*</span>审批部门:</p>
								<select  id="itemApproveDeptSelect" name="approveDept" class="form-control" style="width:260px">
									<option value="-1">...请选择...</option>
								</select>
	                     	</label>
                    	</div>
                        <div class="form-group col-xs-6">
							<label>
								<p><span style="color:red">*</span>审批人:</p>
								<select id="itemApproveBySelect" name="approveBy" class="form-control" >
									<option value="">...请选择...</option>
				  				</select>
	                     	</label>
                    	</div>
					</div>
	               <hr>
	                <div class="row">
	                    <div class="form-group col-xs-12">
	                        <label>
	                            <p>退费总额（元）：</p>
	                            <span id="itemReturnTotal" name="refundTotal"></span>
	                        </label>
	                    </div>
	                    
	                </div>
	                <div class="row">
	                	<div class="form-group col-xs-12">
	                        <label>退款明细（元）：
	                            <span id="refundDetail">
	                        </span>
	                        </label>
	                    </div>
	                	<!-- <div class="form-group col-xs-4">
	                        <label>银行打卡退费合计：
	                            <span id="itemBankReturnTotal"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label><p>&nbsp;&nbsp;微信退费：</p>
	                            <span id="WeiXinItemRefund"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>e币退费：
	                            <span  id="EbItemRefund"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>体验券：
	                            <span  id="TyqItemRefund" ></span>
	                        </label>
	                    </div> -->
	                </div>
	                <hr>
	                <div class="row">
	                    <div class="form-group col-xs-12" id="item_bank">
	                        <label>
	                            <p>&nbsp;&nbsp;银行：</p>
	                            <select class="form-control" name="bankSupportId" id="item_bankSupportId" >
	                           			 <option value="">...请选择...</option>
	                                     <option value="1">工商银行</option>
	                                     <option value="2">建设银行</option>
	                                     <option value="3">农业银行</option>
	                                     <option value="4">中国银行</option>
	                                     <option value="5">兴业银行</option>
	                                     <option value="6">民生银行</option>
	                                     <option value="7">招商银行</option>
	                                     <option value="8">交通银行</option>
	                             </select>
	                        </label>
	                    </div>
	                </div>
	                <div class="row" id="item_bankCity">
	                    <div class="form-group col-xs-6">
	                        <label>
	                            <p>&nbsp;&nbsp;开户省份：</p>
	                            <select class="form-control" name="validateProvince" id="item_bankProvinceCode"  onclick="setSelCity('item_bankProvinceCode','item_bankCityCode')">
	                             </select>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-6">
	                        <label>
	                            <p>开户城市：</p>
	                            <select class="form-control" name="bankCityCode" id="item_bankCityCode" >
	                                     <option value="">...请选择...</option>
	                             </select>
	                        </label>
	                    </div>
	                </div>
	                <div class="row" id="item_bankCN">
                           <div class="form-group  col-xs-6">
                               <label>
                                   <p>&nbsp;&nbsp;银行卡号：</p>
                                   <input type="text" name="bankCard" style="width:200px" class="form-control" id="item_bankCard"/>
                               </label>
                           </div>
                           <div class="form-group  col-xs-6">
                               <label>
                                   <p>开户行：</p>
                                   <input type="text" name="bankName" class="form-control" id="item_bankName"/>
                               </label>
                           </div>
                    </div>
	                <div class="row" id="item_bankUn">
                           <div class="form-group  col-xs-6">
                               <label>
                                   <p>&nbsp;&nbsp;持卡人：</p>
                                   <input type="text" name="bankUserName" class="form-control" id="item_bankUserName"/>
                               </label>
                           </div>
                    </div>
	                <div class="row">
	                    <div class="form-group col-xs-12">
	                        <label class="form-label">
	                            <p>&nbsp;&nbsp;原因：</p>
	                            <textarea rows="2"  name="reason" id="item_reason" style="height:50px;" class= "form-control form-textarea"></textarea>
	                        </label>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="form-group col-xs-12">
	                        <label class="form-label">
	                       	    <p style="color:red;">&nbsp;&nbsp;售后规则：</p><br/>
	                            <span style="font-size: 12px;color:red;padding-left:100px;">（1）FA订单：新建、待受理、已受理 以上状态可以取消;</span><br/>
								<span style="font-size: 12px;color:red;padding-left:100px;">（2）延续性订单：已受理、匹配中、待面试、面试成功、已签约 以上状态可以取消,已上户和已下户状态可以终止;</span><br/>
								<span style="font-size: 12px;color:red;padding-left:100px;">（3）自营单次订单：已受理、匹配中、已匹配 以上状态可以取消;</span><br/>
								<span style="font-size: 12px;color:red;padding-left:100px;">（4）他营单次订单：服务开始时间之前4个小时可以取消;</span><br/>
								<span style="font-size: 12px;color:red;padding-left:100px;">（5）解决方案大订单：新建状态可以取消。</span><br/>
								<span style="font-size: 12px;color:red;padding-left:100px;">（6）机器人订单需要退费时，审批部门请选择—产品事业部，审批人选择-黄强。</span><br/>
                   		 		</span>
	                        </label>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
	                    </div>
	                </div>
	                <!-- <div>
				      	<div><label><span style="color:red">*</span><p>上传图片：</p></label></div>
				      		<span class="text-danger">注意：最多只能上传5张图片，先上传图片，再做保存操作</span>
					      	<br>
					      	<br>
				      		<div id="add_FAImg">
					      		 <input id="itemImgFile" type="file" multiple >
					      	</div>
					 </div> -->
		      		 
	                
	                </div>
				</div>
			  </div>
		      <div class="modal-footer">  
		        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="lastStep('qualityItem')" >上一步</button>  
		        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="closeModule('qualityItem')" >取消</button>  
		      	<button type="submit" class="btn btn-sm btn-primary" id="qualityItemBtn">保存</button>
		      </div>  
		      
	      	</form>
	      	
		  </div> 
	    </div>
	</div>     
</body>
<script type="text/javascript">
$(function() {
	var orderId = '${param.orderId}';
	var cateType = '${param.cateType}';
	//加载省份
	setSelProvinceCitys('101',6,'item_bankProvinceCode');
	// 查询订单信息
	getQualityItem(orderId);
	//查询审核部门
	queryApproveDeptName("itemApproveDeptSelect");
	//执行订单判断
	var parentId = $("#itemParentId").val();
	if(parentId != null && parentId != "" && parentId != 0){
		querySolutionBigOrderAfterSale(parentId);
		if(solutionBigOrderflag){
			$("#item_bank").hide();
			$("#item_bankCN").hide();
			$("#item_bankUn").hide();
			$("#item_bankCity").hide();
		}
	}
	/*上传图片*/
	//初始化
/* 	var html = "";
		html += "<input id='itemImgFile' type='file' multiple/>";
			$("#add_FAImg").html(html);
			initFileInput("itemImgFile","${ctx}/afterSales/insertAfterSalesImg");
			
			function initFileInput(eleName, uploadUrl) {    
			    var control = $('#' + eleName); 
			    control.fileinput({
		    	    language: 'zh', 								//设置语言
		    	    showUpload: true, 								//显示上传按钮
		    	    showRemove: true,								//不显示移除按钮
			        uploadUrl: uploadUrl, 							//上传路径
			        allowedFileExtensions : ['jpg', 'png','gif'],	//允许的后缀名
			        overwriteInitial: false,						//显示正确的文件标题
			        allowedFileTypes: ['image'],					//允许的文件类型
			        slugCallback: function(filename) {				//文件名替换
			            return filename.replace('(', '_').replace(']', '_');
			        }
			    });
			} */
			
			 
	//上传图片之前判断是否只有5张照片
	/* $("#itemImgFile").on("filepreupload",function(){
		var $imgs = $("#add_FAImg").find("img");
		  if($imgs.length>5){
			  return $.alert({millis:3000,top:'30%',text:"只允许添加5张照片！请删除多余图片后再上传！"});
		  }
	}); */

	
	//图片上传完成之后，将返回的url放到相应的span 提交完成后的回调函数  
	/*    var imgNum = 0;
	   $("#itemImgFile").on("fileuploaded",function(event, data, previewId, index){
			  $(".kv-file-remove[type='button']").hide();
			   if (data.response.msg == 00) {
				  //获取保存好的图片ID
				  var imgString = data.response.imgInfo.reUrl;
				  var $imgs = $("#add_FAImg").find("img");
		    	  $imgs.each(function(j){
		    		   if(imgNum == j){
						  //将图片ID放入自定义属性
			    		  $(this).attr("data-imgUrl",imgString);
		    		  } 
		    	  }); 
		    	  imgNum++;
				  $.alert({millis:3000,top:'30%',text:"上传成功！"});
				}else{
				  $.alert({millis:3000,top:'30%',text:"上传失败！"});
				} 

	   }) */
	   //退款总额显示
	   showTotal(orderId);
});
	//订单信息
	function getQualityItem(orderId){
		$.ajax({
			url: "${ctx}/order/queryOrderBasicItem",
			data:{
				id:orderId
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				var html = "";
				if (data.msg == "00") {
					var num =  $.each(data.list,function(i,v){
						 $("#itemUserName").html(v.userName);
						 $("#itemMobile").html(v.userMobile);
						 $("#itemReceiptName").html(v.receiverName);
						 $("#itemReceiptMobile").html(v.receiverMobile);
						 $("#itemReceiptAddress").html(v.receiverAddress);
						 $("#itemOrderStatus").val(v.orderStatus);
						 $("#itemPayStatus").val(v.payStatus);
						 $("#itemParentId").val(v.parentId);
						 $("#itemDeliver").html(intToDecimal(v.deliverPay));
						 $("#itemOrderCode").html(v.orderCode);
						 $("#itemOrderType").html("商品");
						 $("#itemPayText").html(v.payText);
						 $("#itemStatus").html(v.statusText);
						 $("#itemOrderChannel").html(v.channelText);
						 $("#itemOrderSource").html(v.sourceText);
						 $("#itemOrderRemark").html(v.item.remark);
						 $("#itemTotalPay").html(intToDecimal(v.totalPay));
						 num=i+1;
					 	html +="<tr><td>"+ num;
						html +="</td><td>"+v.item.productName;
						html +="</td><td>"+intToDecimal(v.item.nowPrice);
						html +="</td><td>"+v.item.quantity;
						html +="</td></tr><tr></tr>";
						if(v.payStatus == "20110001"){
							$("#item_bank").hide();
							$("#item_bankCN").hide();
							$("#item_bankUn").hide();
							$("#item_bankCity").hide();
						}
					});
					$("#FAInfo").html(html);
				}
			}
		});
	}
 	//退款总额信息
 	function showTotal(orderId){
 		$.ajax({
			url: "${ctx}/payfee/queryAccount",
			data:{
				orderId:orderId,
				isBackType:2,
				valid:1
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				var aliPayRefundTotal = 0;
				var bankRefundTotal = 0;
				var dhRefundTotal = 0;
				var ticketRefundTotal = 0;
				var posRefundTotal = 0;
				var taobaofundTotal = 0;
				var weixinRefundTotal = 0;
				var rhPosRefundTotal = 0;
				var ccbcRefundTotal = 0;
				var cardRefundTotal = 0;
				var tyqRefundTotal = 0;
				var remainRefundTotal = 0;
				var otherRefundTotal = 0;
				var weixinSearchRefundTotal = 0;
				var baitiaoRefundTotal = 0;
				var jialianPosweixinRefundTotal = 0;
				var jialianPosalipayRefundTotal = 0;
				var jialianPoscardRefundTotal = 0;
				var jialianweixinRefundTotal = 0;
				var jialianalipayRefundTotal = 0;
				var jialiancardRefundTotal = 0;
				var yiwangtongRefundTotal = 0;
				var daoweiRefundTotal = 0;
				var weizhishuRefundTotal = 0;
				var wangcaiRefundTotal = 0;
				var returnTotal = 0;
				var html = "";
				if(data.msg =='00'){
					var dcAccountPayId = [];//结算单id,防止重复累加缴费
					$.each(data.list,function(j,w){
						if(dcAccountPayId.contains(w.id)){
							//防止重复累加缴费
							return true;
						}
						dcAccountPayId.push(w.id);
						var itemPayStatus = $("#itemPayStatus").val();
						if(w.payStatus == "20110002" || w.payStatus == "20110003"){
							//returnTotal += intToDecimal(w.feeSum);
						
						//缴费展示
						$.ajax({
							url: "${ctx}/payfee/queryPayfee",
							data:{
								orderId:w.id
							},
							type:"post",
							dataType:"json",
							async:false,
							success:function(data){
								if(data.msg=='00'){
									$.each(data.list,function(l,x){
											if(x.payStatus == "20110002" ){
											returnTotal += intToDecimal(x.feeSum) * 1;
											}
											if(x.feePost == "20250001"){//支付宝
												aliPayRefundTotal += intToDecimal(x.feeSum) * 1;
											}
											if(x.feePost == "20250002"){//银行卡
												bankRefundTotal += intToDecimal(x.feeSum) * 1;
											}
											if(x.feePost == "20250003"){//电汇
												dhRefundTotal += intToDecimal(x.feeSum) * 1;
											}
											if(x.feePost == "20250004"){//支票
												ticketRefundTotal += intToDecimal(x.feeSum) * 1;
											}
											if(x.feePost == "20250005"){//pos机
												posRefundTotal += intToDecimal(x.feeSum) * 1;
											}
											if(x.feePost == "20250006"){//淘宝支付
												taobaofundTotal += intToDecimal(x.feeSum) * 1;
											}
											if(x.feePost == "20250007"){//微信退费
												weixinRefundTotal += intToDecimal(x.feeSum) * 1;
											}
											if(x.feePost == "20250008"){//融汇天下POS
												rhPosRefundTotal += intToDecimal(x.feeSum) * 1;
											}
											if(x.feePost == "20250009"){//建行
												ccbcRefundTotal += intToDecimal(x.feeSum) * 1;
											}
											if(x.feePost == "20250010"){//卡支付
												cardRefundTotal += intToDecimal(x.feeSum) * 1;
											}
											if(x.feePost == "20250011"){//体验券
												tyqRefundTotal += intToDecimal(x.feeSum)*1;
											}
											if(x.feePost == "20250013"){//账户
												remainRefundTotal += intToDecimal(x.feeSum) * 1;
											}
											if(x.feePost == "20250014"){//他人代收（三方订单）
												otherRefundTotal += intToDecimal(x.feeSum) * 1;
											}
											if(x.feePost == "20250015"){//微信扫码支付
												weixinSearchRefundTotal += intToDecimal(x.feeSum) * 1;
											}
											if(x.feePost == "20250016"){//白条支付
												baitiaoRefundTotal += intToDecimal(x.feeSum) * 1;
											}
											if(x.feePost == "20250017"){//嘉联pos微信支付
												jialianPosweixinRefundTotal += intToDecimal(x.feeSum) * 1;
											}
											if(x.feePost == "20250018"){//嘉联pos支付宝支付
												jialianPosalipayRefundTotal += intToDecimal(x.feeSum) * 1;
											}
											if(x.feePost == "20250019"){//嘉联pos刷卡支付
												jialianPoscardRefundTotal += intToDecimal(x.feeSum) * 1;
											}
											if(x.feePost == "20250020"){//嘉联微信支付
												jialianweixinRefundTotal += intToDecimal(x.feeSum) * 1;
											}
											if(x.feePost == "20250021"){//嘉联支付宝支付
												jialianalipayRefundTotal += intToDecimal(x.feeSum) * 1;
											}
											if(x.feePost == "20250022"){//嘉联快捷支付
												jialiancardRefundTotal += intToDecimal(x.feeSum) * 1;
											}
											if(x.feePost == "20250023"){//招商银行一网通支付
												yiwangtongRefundTotal += intToDecimal(x.feeSum) * 1;
											}
											if(x.feePost == "20250024"){//支付宝到位
												daoweiRefundTotal += intToDecimal(x.feeSum) * 1;
											}
											if(x.feePost == "20250025"){//微指数余额
												weizhishuRefundTotal += intToDecimal(x.feeSum) * 1;
											}
											if(x.feePost == "20250026"){//旺财支付
												wangcaiRefundTotal += intToDecimal(x.feeSum) * 1;
											}
											/* if(w.feePost == "20250012"){//e币退费
												$("#EbItemRefund").text(intToDecimal(w.feeSum));
											}else{
												$("#EbItemRefund").text("0");
											} */
									});
								}/* else{
									html += "&nbsp;&nbsp;无";
								} */
							}
						});
						}
					});
						if(aliPayRefundTotal != "0" ){
							html +="<label>支付宝退费：<span>"+intToDecimal(aliPayRefundTotal)+"</span>&nbsp;&nbsp;</label>";
						}
						if(bankRefundTotal != "0"){
							html +="<label>银行打卡退费合计：<span>"+intToDecimal(bankRefundTotal)+"</span>&nbsp;&nbsp;</label>";
						}
						if(dhRefundTotal != "0"){
							html +="<label>电汇退费：<span>"+intToDecimal(dhRefundTotal)+"</span>&nbsp;&nbsp;</label>";
						}
						if(ticketRefundTotal != "0"){
							html +="<label>支票退费：<span>"+intToDecimal(ticketRefundTotal)+"</span>&nbsp;&nbsp;</label>";
						}
						if(posRefundTotal != "0"){
							html +="<label>POS机退费：<span>"+intToDecimal(posRefundTotal)+"</span>&nbsp;&nbsp;</label>";
						}
						if(taobaofundTotal != "0"){
							html +="<label>淘宝退费：<span>"+intToDecimal(taobaofundTotal)+"</span>&nbsp;&nbsp;</label>";
						}
						if(weixinRefundTotal != "0"){
							html +="<label>微信退费：<span>"+intToDecimal(weixinRefundTotal)+"</span>&nbsp;&nbsp;</label>";
						}
						if(rhPosRefundTotal != "0"){
							html +="<label>融汇天下POS退费：<span>"+intToDecimal(rhPosRefundTotal)+"</span>&nbsp;&nbsp;</label>";
						}
						if(ccbcRefundTotal != "0"){
							html +="<label>建行退费：<span>"+intToDecimal(ccbcRefundTotal)+"</span>&nbsp;&nbsp;</label>";
						}
						if(cardRefundTotal != "0"){
							html +="<label>卡退费：<span>"+intToDecimal(cardRefundTotal)+"</span>&nbsp;&nbsp;</label>";
						}
						if(tyqRefundTotal != "0"){
							html +="<label>体验券(不退)：<span>"+intToDecimal(tyqRefundTotal)+"</span>&nbsp;&nbsp;</label>";
							//如果退款有券缴费，退款总额-券缴费（不退）
							returnTotal = returnTotal*1 - tyqRefundTotal * 1;
						}
						if(remainRefundTotal != "0"){
							html +="<label>账户退费：<span>"+intToDecimal(remainRefundTotal)+"</span>&nbsp;&nbsp;</label>";
						}
						if(otherRefundTotal != "0"){
							html +="<label>他人代收退费：<span>"+intToDecimal(otherRefundTotal)+"</span>&nbsp;&nbsp;</label>";
						}
						if(weixinSearchRefundTotal != "0"){
							html +="<label>微信扫码支付退费：<span>"+intToDecimal(weixinSearchRefundTotal)+"</span>&nbsp;&nbsp;</label>";
						}
						if(baitiaoRefundTotal != "0"){
							html +="<label>白条退费：<span>"+intToDecimal(baitiaoRefundTotal)+"</span>&nbsp;&nbsp;</label>";
						}
						if(jialianPosweixinRefundTotal != "0"){
							html +="<label>嘉联pos微信支付退费：<span>"+intToDecimal(jialianPosweixinRefundTotal)+"</span>&nbsp;&nbsp;</label>";
						}
						if(jialianPosalipayRefundTotal != "0"){
							html +="<label>嘉联pos支付宝支付退费：<span>"+intToDecimal(jialianPosalipayRefundTotal)+"</span>&nbsp;&nbsp;</label>";
						}
						if(jialianPoscardRefundTotal != "0"){
							html +="<label>嘉联pos刷卡支付退费：<span>"+intToDecimal(jialianPoscardRefundTotal)+"</span>&nbsp;&nbsp;</label>";
						}
						if(jialianweixinRefundTotal != "0"){
							html +="<label>嘉联微信支付退费：<span>"+intToDecimal(jialianweixinRefundTotal)+"</span>&nbsp;&nbsp;</label>";
						}
						if(jialianalipayRefundTotal != "0"){
							html +="<label>嘉联支付宝支付退费：<span>"+intToDecimal(jialianalipayRefundTotal)+"</span>&nbsp;&nbsp;</label>";
						}
						if(jialiancardRefundTotal != "0"){
							html +="<label>嘉联快捷支付退费：<span>"+intToDecimal(jialiancardRefundTotal)+"</span>&nbsp;&nbsp;</label>";
						}
						if(yiwangtongRefundTotal != "0"){
							html +="<label>招商银行一网通支付退费：<span>"+intToDecimal(yiwangtongRefundTotal)+"</span>&nbsp;&nbsp;</label>";
						}
						if(daoweiRefundTotal != "0"){
							html +="<label>支付宝到位退费：<span>"+intToDecimal(daoweiRefundTotal)+"</span>&nbsp;&nbsp;</label>";
						}
						if(weizhishuRefundTotal != "0"){
							html +="<label>微指数余额退费：<span>"+intToDecimal(weizhishuRefundTotal)+"</span>&nbsp;&nbsp;</label>";
						}
						if(wangcaiRefundTotal != "0"){
							html +="<label>旺财支付退费：<span>"+intToDecimal(wangcaiRefundTotal)+"</span>&nbsp;&nbsp;</label>";
						}
						if(aliPayRefundTotal == "0" && bankRefundTotal == "0" && dhRefundTotal == "0" && ticketRefundTotal == "0"
						  &&  posRefundTotal == "0" && taobaofundTotal == "0" && weixinRefundTotal == "0" && rhPosRefundTotal == "0"
						  &&  ccbcRefundTotal == "0" && cardRefundTotal == "0" && tyqRefundTotal == "0" && remainRefundTotal == "0"
						  &&  otherRefundTotal == "0" && weixinSearchRefundTotal == "0"&& baitiaoRefundTotal == "0"&& jialianPosweixinRefundTotal == "0"
						  && jialianPosalipayRefundTotal == "0"&& jialianPoscardRefundTotal == "0"&& jialianweixinRefundTotal == "0"&& jialianalipayRefundTotal == "0"
						  && jialiancardRefundTotal == "0"&& yiwangtongRefundTotal == "0"&& daoweiRefundTotal == "0"
						  && wangcaiRefundTotal == "0"){
							html += "&nbsp;&nbsp;无";
						}
						
						if(aliPayRefundTotal != "0" || bankRefundTotal != "0" 
							  ||  posRefundTotal != "0" || taobaofundTotal != "0" || weixinRefundTotal != "0" || rhPosRefundTotal != "0"
							  ||  ccbcRefundTotal != "0" || cardRefundTotal != "0" || tyqRefundTotal != "0" || remainRefundTotal != "0"
							  ||  otherRefundTotal != "0" || weixinSearchRefundTotal != "0" || jialianPosweixinRefundTotal != "0"
							  || jialianPosalipayRefundTotal != "0"|| jialianPoscardRefundTotal != "0"|| jialianweixinRefundTotal != "0"|| jialianalipayRefundTotal != "0"
							  || jialiancardRefundTotal != "0"|| yiwangtongRefundTotal != "0"|| daoweiRefundTotal != "0"
							  || wangcaiRefundTotal != "0"){
							bankRefundTotal += aliPayRefundTotal *1 + bankRefundTotal *1 + posRefundTotal *1 + taobaofundTotal*1 +weixinRefundTotal*1;
							bankRefundTotal += ccbcRefundTotal *1 + rhPosRefundTotal *1 + otherRefundTotal *1 + weixinSearchRefundTotal*1 ;
							bankRefundTotal += jialianPosweixinRefundTotal *1 + jialianPosalipayRefundTotal *1 + jialianPoscardRefundTotal *1  ;
							bankRefundTotal += jialianweixinRefundTotal *1 + jialianalipayRefundTotal *1 + jialiancardRefundTotal *1  ;
							bankRefundTotal += yiwangtongRefundTotal *1 + daoweiRefundTotal *1 + wangcaiRefundTotal *1  ;
							}
						//退款总额
						$("#itemReturnTotal").text(intToDecimal(returnTotal));
						//退款明细
						$("#refundDetail").html(html);
						//银行卡退费
						$("#itemBankCardMoney").val(intToDecimal(bankRefundTotal));
						if(returnTotal <= "0"){
							$("#item_bank").hide();
							$("#item_bankCN").hide();
							$("#item_bankUn").hide();
							$("#item_bankCity").hide();
							$("#itemQualityApproveDiv").hide();
						}else if(cardRefundTotal != "0" && bankRefundTotal == "0"){
							$("#item_bank").hide();
							$("#item_bankCN").hide();
							$("#item_bankUn").hide();
							$("#item_bankCity").hide();
							//$("#itemQualityApproveDiv").hide();
						}else if(tyqRefundTotal != "0" && bankRefundTotal == "0"){
							$("#item_bank").hide();
							$("#item_bankCN").hide();
							$("#item_bankUn").hide();
							$("#item_bankCity").hide();
							//$("#itemQualityApproveDiv").hide();
						}else if(remainRefundTotal != "0" && bankRefundTotal == "0"){
							$("#item_bank").hide();
							$("#item_bankCN").hide();
							$("#item_bankUn").hide();
							$("#item_bankCity").hide();
							//$("#itemQualityApproveDiv").hide();
						}
					/* $("#WeiXinItemRefund").text(intToDecimal(weixinRefundTotal));
					$("#TyqItemRefund").text(intToDecimal(tyqRefundTotal));
					$("#itemBankReturnTotal").text(intToDecimal(bankRefundTotal)); */
				}else{
					$("#refundDetail").html("&nbsp;&nbsp;无");
					$("#itemReturnTotal").text("0.00");
					$("#itemQualityApproveDiv").hide();
					 /*$("#WeiXinItemRefund").text("0.00");
					$("#TyqItemRefund").text("0.00");
					$("#itemBankReturnTotal").text("0.00"); */
				}
			}
		});
 	}
 	
	//保存操作
	function saveQualityItem(){
		 var orderId = '${param.orderId}';
		 var custName = $("#itemUserName").text();
		 var custMobile = $("#itemMobile").text();
		 var itemReturnTotal = $("#itemReturnTotal").text();
		 var itemStatus = $("#itemOrderStatus").val();
		 var itemPayStatus = $("#itemPayStatus").val();
		 var orderStatus = "";
		 var bankRefundMoney = $("#itemBankCardMoney").val();
		/*  var imgUrls = "";
		$("#add_FAImg").find("img").each(function(){
			var imgs = $(this).attr("data-imgUrl");
			if(imgs != undefined && imgs != null){
				imgUrls = imgUrls + "|" + imgs;
			} 
		}); */
		if(itemReturnTotal != undefined && itemReturnTotal != "" && itemReturnTotal != "0" && itemReturnTotal != "0.00"){
			//验证审批部门
			 var itemApproveDept  = $("#itemApproveDeptSelect").find("option:selected").val();
			 if(itemApproveDept == "" || itemApproveDept == null){
				 $.alert({millis:2000,top:'30%',text:"请选择审批部门！"});
				 $("#qualityItemBtn").removeAttr("disabled");
				  return ;
			 }
			//验证审批人
			 var itemApproveBy = $("#itemApproveBySelect").find("option:selected").val();
			 if(itemApproveBy == "" || itemApproveBy == null){
				 $.alert({millis:2000,top:'30%',text:"请选择审批人！"});
				 $("#qualityItemBtn").removeAttr("disabled");
				  return ;
			 }
		}
		 var ServerBankMainName = $("#item_bankSupportId").find("option:selected").text();
		 if(ServerBankMainName == "...请选择..."){
			 ServerBankMainName = "";
		  }
		if(itemPayStatus != "20110001" && bankRefundMoney != "0.00" && bankRefundMoney != null && !solutionBigOrderflag){
			var banksupportId = $("#item_bankSupportId").val();
			var bankCityCode = $("#item_bankCityCode").val();
			var bankCard = $("#item_bankCard").val();
			var bankName = $("#item_bankName").val();
			var bankUserName = $("#item_bankUserName").val();
			if(banksupportId == null || banksupportId == ""){
				$.alert({millis:3000,top:'30%',text:"请填写银行！"});
				flag = true;//flag = false;
			}else if(bankCityCode == null || bankCityCode == ""){
				$.alert({millis:3000,top:'30%',text:"请填写城市！"});
				flag = true;//flag = false;
			}else if(bankCard == null || bankCard == ""){
				$.alert({millis:3000,top:'30%',text:"请填写银行卡号！"});
				flag = true;//flag = false;
			}else if(bankName == null || bankName == ""){
				$.alert({millis:3000,top:'30%',text:"请填写开户行！"});
				flag = true;//flag = false;
			}else if(bankUserName == null || bankUserName == ""){
				$.alert({millis:3000,top:'30%',text:"请填写持卡人！"});
				flag = true;//flag = false;
			}else{
				flag = true;
				if(flag){
					insertItemAfterSale(orderId,custName,custMobile,itemReturnTotal);
				 }
			}
		}else{
			 flag = true;
			 if(flag){
				 insertItemAfterSale(orderId,custName,custMobile,itemReturnTotal);
			 }
		}
		$("#qualityItemBtn").removeAttr("disabled");
	}
	//保存售后单
	function insertItemAfterSale(orderId,custName,custMobile,itemReturnTotal){
		var data = $("#qualityItemForm").serialize();
		var itemBankMainName = $("#item_bankSupportId").find("option:selected").text();
		if(itemBankMainName == "...请选择..."){
			itemBankMainName = "";
		}
		 $.ajax({
			url: "${ctx}/afterSales/insertAfterSales?orderId="+orderId+"&custName="+encodeURI(encodeURI(custName))+"&custMobile="+custMobile+"&refundTotal="+itemReturnTotal+"&bankMainName="+encodeURI(encodeURI(itemBankMainName)),//"&afterSalesImgs="+imgUrls+
			data:data,
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg =="00") {
					 $.alert({millis:3000,top:'30%',text:"添加成功！"});
					queryAfterSalesByLike(0,10);
					/* var saleId = data.saleId;
					showQualityDetail(saleId,orderId,1,""); */
					closeModule('qualityItem');
				}else{
					 $.alert({millis:3000,top:'30%',text:"添加失败！"});
					closeModule('qualityItem');
				}
			}
		}); 
	}
	
	$("#item_afterSalesKind").change(function(){
		var itemOrderStatus = $("#itemOrderStatus").val();
		if($(this).find("option:selected").val() == 1){
			if(itemOrderStatus != 1 && itemOrderStatus != 18 && itemOrderStatus != 2){
				$.alert({millis:3000,top:'30%',text:"当前订单状态不能进行所选售后操作，请重新选择！"});
				$(this).val("");
			}
		}
	});
	//查询审批人
	function queryGuanjiaName(){
		var ctx=$("#ctx").val();
		var deptId = $("#itemApproveDeptSelect option:selected").val();;
		if(deptId==""||deptId==null){
			$("#itemApproveBySelect").empty();
	    	$("#itemApproveBySelect").html("<option value=''>...请选择...</option>");
		}else{
			$.ajax({
				url:ctx+"/orderbase/queryguanjia",
				data : {
					deptId : deptId
				},
				type : 'post',
				async : false,
				dataType : "json",
				success : function(data) {
					var html = "";
					html +="<option style='color:blue;' value='' >...请选择...</option>";
					if (data.msg == "00") {
						$.each(data.list,function(i,v){
							html += "<option   value='" + v.id + "'>"+v.realName+"（"+v.userName+"）</option>";
						});
					} else if(data.msg== "02"){
						$.alert({millis:2000,text:"无记录!"});
					}else{
						$.alert({millis:2000,text:"查询出错，请稍后再试!"});
					}
					$("#itemApproveBySelect").html(html);
				}
			});
		}
	}
 	//进行验证的方法
	$('#qualityItemForm').bootstrapValidator({
	    message: 'This value is not valid',
	    excluded: ':disabled',
	    feedbackIcons: {
	        valid: 'glyphicon glyphicon-ok',
	        invalid: 'glyphicon glyphicon-remove',
	        validating: 'glyphicon glyphicon-refresh'
	    },
	    fields: {         //fields下的是表单name属性
	    	bankUserName: {
	            message: '持卡人无效！',
	            validators: {
	                stringLength: {
	                    min: 1,
	                    max: 30,
	                    message: '持卡人名称少于30个字！'
	                },
	                regexp: {
	                    regexp: /^(?!_)(?!.*?_$)[a-zA-Z_\u4e00-\u9fa5]+$/,
	                    message: '持卡人名称为中文和英文！'
	                }
	            }
	        },
	        bankName: {
	            message: '开户行无效！',
	            validators: {
	                stringLength: {
	                    min: 1,
	                    max: 30,
	                    message: '开户行名称少于30个字！'
	                },
	                regexp: {
	                    regexp: /^[\u4E00-\u9FA5]{1,30}$/,
	                    message: '开户行名称为中文！'
	                }
	            }
	        },
	        afterSalesKind: {
	            validators: {
	                notEmpty: {
	                    message: '必选！'
	                },
	            }
	        },
	       /*  validateProvince : {
                validators: {
                    notEmpty: {
                        message: '必选！'
                    },
                }
            }, */
              bankCard : {
            	validators: {
            		regexp: {
	                    regexp: /^(\d{12}|\d{16}|\d{18}|\d{19}|\d{20})$/,
	                    message: '银行卡号错误！'
	                }
            	}
            }, 
              /* bankCityCode : {
            	validators: {
            		notEmpty: {
            			message: '必选！'
            		},
            	}
            }, */  
            reason : {
            	validators: {
            		stringLength: {
	                    min: 0,
	                    max: 200,
	                    message: '描述不超过200字！'
	                },
            	}
            },
            
	    }
	}).on('success.form.bv', function(e) {
	    // 阻止表单提交【submit】【必填】
	    e.preventDefault();
	    //保存售后单
	    saveQualityItem();
	}); 
</script>
</html>

