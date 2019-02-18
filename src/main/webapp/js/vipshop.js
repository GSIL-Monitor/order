/**
 * 唯品会退费售后单管理js
 * @author wn
 * @date 9-18
 */
 var ctx=$("#ctx").val();
 var afterSalesKindArr2={1:"FA订单取消",4:"单次服务订单取消",5:"延续性服务订单取消",8:"延续性服务订单终止",11:"延续性服务订单分期退费"};
 var auditFlagArr2 = {20260001:"已取消",20130001:"待确认",20130002:"确认无效",20130003:"确认有效 ",20130004:"审核中",20130005:"审核失败",20130006:"审核成功",20130007:"退款中",20130008:"退款成功",20130009:"退款失败",20130010:"已删除"};
 var orderStatus = {1:"新单",2:"已受理",3:"匹配中",4:"已匹配",5:"待面试",6:"面试成功",7:"已签约",8:"已上户",9:"已完成",10:"已取消",11:"已下户",12:"已终止",13:"捡货中",14:"配送中",16:"三方订单匹配失败",17:"工作完成",18:"待受理"};
 var refundObjectArr={1:"唯品会",2:"客户","0":"唯品会"};
 
 var flag = true;
 //售后单查询
	function queryVPHSalesByLike(num,pageCount){
		var custName = $("#vph_userNameOrder").val();
		var custMobile = $("#vph_userMobileOrder").val();
		var auditFlag = $("#vph_orderStatus").val();
		var creStart = $("#vph_creStart").val();
		var creEnd = $("#vph_creEnd").val();
		var afterSaleIdSearch = $("#vph_afterSaleIdSearch").val();
		var orderCodeSearch = $("#vph_orderCodeSearch").val();
		var refundObject = $("#vph_refundObject_Search").find("option:selected").val();
		var vphId = $("#vph_id").val();
		$.ajax({
			url: ctx+"/afterSales/queryVPHSales?curPage="+num+"&pageCount="+pageCount,
			data:{
				custName:custName,
				custMobile:custMobile,
				auditFlag:auditFlag,
				creStart:creStart,
				creEnd:creEnd,
				id:afterSaleIdSearch,
				refundObject:refundObject,
				vphId:vphId,
				orderCode:orderCodeSearch
				
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				var html="";
				$("#pageVphDiv").pagination(data.page,vphListBody);
				var pageCount = data.page.pageCount;
				var curPage = data.page.curPage;
				var total = curPage * pageCount;
				
				if (data.msg == "00") {
					$("#vph_userLoginLevel").val(data.loginLevel);
					var num = $.each(data.list,function(i,v){
						    var auditStr =  auditFlagStr(v.auditFlag,v.remark,v.accountRemark);
							if(i == 0){
								showVPHDetail(v.id,v.orderId,v.afterSalesKind,v.auditFlag);
							}
							num=i+1;
							html +="<tr onclick='showVPHDetail(" +v.id +","+v.orderId+","+v.afterSalesKind+","+v.auditFlag+")'>" ;
							html +="<td><input name='vphRadio' type='radio' value='" +v.id +"-"+v.orderId +"-"+v.auditFlag +"-"+v.order.cateType+"-"+v.afterSalesKind+"'/></td>";
							html +="<td>"+(total + num - pageCount);
							html +="</td><td>"+v.id;
							if(v.auditFlag == "20130009" || v.auditFlag == "20130005"|| v.auditFlag == "20130002"){//退款失败，状态显示红色
								html +="</td><td align='left' style='color:red;'>"+auditStr;
							}else{
								html +="</td><td align='left' style='color:green;'>"+auditStr;
							}
							html +="</td><td>"+v.orderCode;
							html +="</td><td>"+(v.vphId ==undefined ?"-":v.vphId);
							html +="</td><td>"+v.vphFee;
							html +="</td><td>"+refundObjectArr[v.refundObject];
							html +="</td><td>"+v.custName;
							html +="</td><td>"+v.custMobile;
							html +="</td><td>"+numberToDatestr(v.createTime,8);
							html +="</td></tr><tr></tr>";
					})
				}else if(data.msg == "02"){
						html +="<tr><td colspan='13'><p class='table-empty'>暂无数据</p></td></tr>";
						$("#pageVphDiv").hide();
						$("#tableDetail").hide();
						$("#tableSolutionDetail").hide();
						$("#tableNoData").show();
						$("#tableNoData").html("<table class='table table-condensed'><tr><td colspan='2'><p class='table-empty'>暂无数据</p></td></tr></table>");
						resetAllOrderDetail();
				}
				$("#vphListBody").html(html);
				//设置默认行背景色
				$("#vphListBody tr").each(function(n) {
					if(n==0){
						$(this).find("input[name='vphRadio']").attr("checked", "checked");
						$(this).toggleClass('info').siblings().removeClass('info').css('cursor', 'pointer'); return;
					}
				});
			}
		});
		/*初始化所有弹出提示框 */
		$('[data-toggle="popover"]').popover();
		/*表格单选*/
		radioColor("#vphListBody > tr");
		/*表格点击行高亮*/
		trColor("#native_tbody > tr");
	}
	
	  //售后信息详情
	 function showVPHDetail(saleId,orderId,saleKind,auditFlag){
		 $("#tableSolutionDetail").hide();//隐藏解决方案详情table  
		 $("#tableDetail").show();
		 $("#tableNoData").hide();
		 resetAllOrderDetail();
		 $("#saleId").val(saleId);
		 $("#orderId").val(orderId);
		 $("#saleKind").val(saleKind);
		 $("#auditFlag").val(auditFlag);
		 if(saleKind == 1 || saleKind == 2 || saleKind == 3){
			 $("#ServerTr1").hide();
			 $("#ServerTr2").hide();
			 $("#ServerTr3").hide();
		 	 $("#FACreTr").show();
		 	 $("#TKFA").show();
		 	 $("#TKTitle").show();
			 showFADetail(orderId);
		 }else{
			 $("#ServerTr1").show();
			 $("#ServerTr2").show();
			 $("#ServerTr3").show();
		 	 $("#FACreTr").hide();
		 	 $("#TKFA").hide();
		 	 $("#TKTitle").hide();
			 showServerDetail(orderId);
		 }
		 	showVPHSaleDetail(saleId);
	 }
	 

 	//售后信息
 	function showVPHSaleDetail(saleId){
		$.ajax({
			url: ctx+"/afterSales/queryVPHSales",
			data:{
				id:saleId
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg ="00") {
					$.each(data.list,function(i,v){ 
						$("#qd_custName").html(v.custName);
						$("#qd_custMobile").html(v.custMobile);
						$("#qd_total").html(intToDecimal(v.refundTotal));
						$("#qd_salesKind").html(afterSalesKindArr2[v.afterSalesKind]);
						$("#qd_auditFlag").html(auditFlagArr2[v.auditFlag]);
						$("#qd_reason").html(v.reason);
						$("#qd_remark").val(v.remark);
						if(v.vphFee != null && v.vphFee != ""){
							$("#vipshopDetailTr").show();
							$("#qd_vipshop_total").html(intToDecimal(v.vphFee));
						}else{
							$("#vipshopDetailTr").hide();
						}
					});
				}
			}
		});
 	}
 	
 	//重置
	function resetVphSales(){
		$("#vph_userNameOrder").val("");
		$("#vph_userMobileOrder").val("");
		$("#vph_orderStatus").val("");
		$("#vph_serverType").val("");
		$("#vph_creStart").val("");
		$("#vph_creEnd").val("");
		$("#vph_afterSaleIdSearch").val("");
		$("#vph_orderCodeSearch").val("");
		$("#vph_id").val("");
		queryVPHSalesByLike(0,10);
		$("#pageVphDiv").show();
	}

	// 分页跳转使用
	function vphListBody(num,pageCount) {
		$("#vphListBody").empty();
		queryVPHSalesByLike(num,pageCount);
	}
 	
 	//重置订单详情
 	function resetAllOrderDetail(){
	 		 $("#order_code").html("");
			 $("#order_type").html("");
			 $("#order_status").html("");
			 $("#order_creTime").html("");
			 $("#order_channel").html("");
			 $("#order_receiverName").html("");
			 $("#order_receiverMobile").html("");
			 $("#order_receiverAddress").html("");
			 $("#order_source").html("");
			 $("#order_startTime").html("");
			 $("#order_endTime").html("");
			 $("#order_serverAddress").html("");
			 $("#qd_custName").html("");
			 $("#qd_custMobile").html("");
			 $("#qd_total").html("");
			 $("#qd_salesKind").html("");
			 $("#qd_auditFlag").html("");
			 $("#qd_reason").html("");
			 $("#refundFAInfo").html("");
 	}

	//转换价格类型
	function intToDecimal(str){
		return new Number(str).toFixed(2);
	}
	

