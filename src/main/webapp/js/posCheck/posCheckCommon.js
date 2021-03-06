/**
 * pos审核js
 * @author wn
 * @date 10-23
 */
 var ctx=$("#ctx").val();
 var posCheckStatusArr={1:"待审核",2:"通过",3:"驳回"};
 	//售后单查询
	function queryPosCheckCommonByLike(num,pageCount){
		$("#pagePosCheckCommonDiv").show();
		var data = $("#pos_check_common_form").serialize();
		var categoryCode = categorySelectCommon();//查询选择订单三级分类
		if(categoryCode != null && categoryCode != "") data += "&categoryCode=" + categoryCode;
		var ids = $("#common_pos_check_table").GetCheckboxCheckedValue();
		if (ids.length > 0) data += "&ids=" + ids;
		data += "&loginLevel=1"; //设置权限查询
		$.ajax({
			url: ctx +"/payfee/queryPosCheck?curPage="+num+"&pageCount="+pageCount,
			data:data,
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg == "00") {
					$("#pagePosCheckCommonDiv").pagination(data.page,listPosCheckCommon);
					var pageCount = data.page.pageCount;
					var curPage = data.page.curPage;
					var total = curPage * pageCount;
					var html="";
					var num = $.each(data.list,function(i,v){
						var posJson = {"id":v.id,"posCheckStatus":v.posCheckStatus};
						var posStr = checkStatus(v.posCheckStatus,v.posFailReason);
						html +="<tr>" ;
						html += "<tr><td><input  type='checkbox' name='posCheckBox' data-pos='"+(JSON.stringify(posJson)||"")+"' data-posUrl='"+v.posImgUrl+"'  value='"+v.id+"'>";
						html +="<td>"+(total + (i + 1) - pageCount);
						html +="</td><td align='left'>"+v.payUser;
						html +="</td><td align='left'>"+v.id;
						html +="</td><td align='left'>"+v.bankFlowNum;
						html +="</td><td align='left'>"+v.typeText;
						html +="</td><td align='left'>"+v.orderCode;
						html +="</td><td align='left'>"+(v.orderId=="0"?"":v.orderId);
						html +="</td><td align='left'>"+v.confirmTime;
						html +="</td><td align='left'>"+intToDecimal(v.feeSum);
						html +="</td><td align='left'>"+v.createDeptString;
						html +="</td><td align='left'>"+v.createByString;
						html +="</td><td>"+numberToDatestr(v.createTime,12); 
						html +="</td><td align='left'>"+v.orderCity;
						html +="</td><td align='left'>"+v.postText;
						if(v.accountStatusText != null && v.accountStatusText == "是"){
							html +="</td><td align='left' style='color:green;'>"+v.accountStatusText;
						}else{
							html +="</td><td align='left' style='color:red;'>"+v.accountStatusText;
						}
						html +="</td><td align='left'>"+v.payDate;
						html +="</td><td>";
						html+="<input type='hidden' name='returnURLS' id='returnURLS"+v.id+"' value='"+v.posImgUrl+"'>";
						if(v.posCheckStatus == 1 ||v.posCheckStatus == 3){
							html+="<a href='javascript:void(0)'  name='posCheckUpdateS'  onclick='posCheckUpdateOne(this,"+v.id+",2)' >修改</a>&nbsp;&nbsp;&nbsp;&nbsp;";
						}
						var img = v.posImgUrl;
						html+="<a href='javascript:void(0)' name='posCheckDetailS' id='c_posCheckDetail' onclick='posCheckImg(this)'>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;";
						html+="<a href='javascript:void(0)' name='posCheckDownloadS' id='c_posCheckDownload_"+v.bankFlowNum+"' onclick='posCheckDownload(this)'>下载</a>";
						html +="</td><td align='left'>"+posStr;//checkStatus(v.posCheckStatus,v.posFailReason)
						html +="</td><td align='left'>"+v.feeTypeText;
						html +="</td><td align='left'>"+v.checkDeptString;
						html +="</td><td align='left'>"+v.checkByString;
						html +="</td><td align='left'>"+numberToDatestr(v.posCheckDate,12);
						html +="</td></tr>";
					});
				}else{
					html +="<tr><td colspan='23'><p class='table-empty'>暂无数据</p></td></tr>";
					$("#pagePosCheckCommonDiv").hide();
				}
				$("#common_posCheckListBody").html(html);
			}
		});
		/*初始化所有弹出提示框 */
		$('[data-toggle="popover"]').popover();
		/*表格单选*/
		radioColor("#common_posCheckListBody > tr");
		/*表格点击行高亮*/
		trColor("#native_tbody > tr");
		/**查询未处理项 */
		common_queryUncheck();
	}
	
	// 分页跳转使用
	function listPosCheckCommon(num,pageCount) {
		$("#common_posCheckListBody").empty();
		queryPosCheckCommonByLike(num,pageCount);
	}
	
	//更新POS审核状态
	function common_updatePosCheck(flag){
		  var posCheck = $("input[type='checkbox']:checked"); 
		  var pos = posCheck.data("pos");
		  var posCheckStatus = pos.posCheckStatus;//审核状态
		  var posPayfeeId = pos.id;
		  if (flag == 2) {
			  if (posCheck.length == 0 ) {
				  $.alert({millis:3000,top:'30%',text:"请选择要审核的凭条！"});
				  return;
			  }else if(posCheck.length > 1){
				  $.alert({millis:3000,text:"只能选择<b>1</b>条记录！"});	
				  return;
			  }else{
				  if(posCheckStatus == 2 && flag == 2){
					  $.alert({millis:5000,text:"当前pos凭条己<strong>审核通过</strong>！"});
					  return;
				  }
				  if(posCheckStatus == 3 && flag == 3){
					  $.alert({millis:5000,text:"当前pos凭条己<strong>驳回</strong>！"});
					  return;
				  }
				  //更新凭条审核状态	
				  updatePosPayfeeCommon(posPayfeeId,flag);
			  }
		} else {
			if(posCheckStatus == 2 && flag == 3){
				$.alert({millis:5000,text:"当前pos凭条己<strong>审核通过</strong>不能<strong>驳回</strong>！"});
				return;
			}else{
				openModule('/order/jsp/posCheck/posFailReason.jsp',{id:posPayfeeId},{},{width:'40%'},'posFailReason');
			}
		}
	} 
	 
	

	 /**批量导出POS凭条*/
	 function common_exportPosCheck(){
		var data = $("#pos_check_common_form").serialize();
		var ids = $("#common_pos_check_table").GetCheckboxCheckedValue();
		if (ids.length > 0) data += "&ids=" + ids;
		data += "&loginLevel=1"; //设置权限查询
		 $.confirm({text:"确认导出POS凭条图片?",callback:function(r){
				if(r){ 
					$.ajax({
						url:ctx+'/payfee/exportPosCheck/',
						data:data,
						dataType: "json",
						async:false,
						type:'post',
						success:function(data){
							if(data.msg=='00'){//导出成功
								var form=$("<form action='/img_service/zipdownload/downloadFiles' method='post' style='display:none' >");
								var input1=$("<input type='hidden' name='list' value='"+JSON.stringify(data.list)+"'>");
								$("body").append(form);//将表单放置在web中
								form.append(input1);
								form.submit();//表单提交 
							} else if (data.msg == '01'){
								$.alert({millis:2000,text:"导出失败!"});
							} else {
								$.alert({millis:2000,text:"文件不存在！"});
							}
						}
					});  
				}
		 	}
		 });
	 }
	
	//展示售后单状态，退款原因和审核失败原因悬浮
	function checkStatus(posCheckStatus,posFailReason){
		var auditStr = "";
		switch(posCheckStatus){
			case 1 :
				auditStr = "待审核";
				break;
			case 2 :
				auditStr = "<span style='color:green;'>通过</span>";
				break;
			case 3 :
				auditStr = "<a style='color:red;display:inline-block;' href='javascript:void(0)' data-placement='bottom' bindex='0' title='驳回原因：' data-toggle='popover' data-trigger='hover' data-content='"+(posFailReason?posFailReason:"无")+"'>驳回</a>";
				break;
			default:
				break;
		}
		return auditStr;
	}
	
	
	/**
	 * 新增城市商品关联弹出框,分类联动查询 
	 * @param thiz
	 * @param categorylevel
	 * @param tagId 加载的位置标签id值 
	 */
	function loadCategoryCommon(thiz, categorylevel, tagId) {
		if (categorylevel == "" && tagId == "") {
			return;
		}
		$(tagId).empty().append("<option value='' selected = 'selected'>--" + categorylevel+ "级分类--</option>")
		var cityCode = $("#common_pos_checkCity option:selected").val();
		if(cityCode == null || cityCode == ""){
			$.alert({millis:5000,text:"请选择城市！"});
			$("#common_search_category_1").val("");
			$("#common_search_category_2").val("");
			$("#common_search_category_3").val("");
			return;
		}
		var categoryId = '';
		if (thiz != null) {
			categoryId = $(thiz).val();
		}
		$(tagId).trigger("change");
		if ($(thiz).val() == '-1' || $(thiz).val() == '') {
			return;
		}
		$.ajax({
		url : ctx + "/orderbase/queryCategoryCity",
		type : 'post',
		async : false,
		dataType : 'json',
		data : {
			"cityCode" : cityCode,
			"fid" : categoryId,
			"level" : categorylevel
		},
		success : function(data) {
			if (data.msg == 00) {
				$.each(data.list, function(i, v) {
					if (v.code != null && v.code != "" && v.cname != null && v.cname != "") {
						var orgCode = v.orgCode.split("-")
						if (orgCode.length == (categorylevel + 1)) {
							$(tagId).append("<option data-categoryCode='"+v.code+"' value='"+v.id+"'>"+ v.cname + "</option>")
						}
					}
				})
			}
		}
	
		})
	}
	
	
	function categorySelectCommon(){
		var oneLevel = $("#common_search_category_1").find("option:selected").attr("data-categoryCode");
		var twoLevel = $("#common_search_category_2").find("option:selected").attr("data-categoryCode");
		var threeLevel = $("#common_search_category_3").find("option:selected").attr("data-categoryCode");
		if(threeLevel){
			return threeLevel;
		}else if(twoLevel){
			return twoLevel;
		}else if(oneLevel){
			return oneLevel;
		}else{
			return "";
		}
	}
	
	/**
	 * 导出符合条件的pos凭条Excel
	 */
	function common_exportPosCheckExcel(){
		var data = $("#pos_check_common_form").serialize();
		var categoryCode = categorySelectCommon();//查询选择订单三级分类
		if(categoryCode != null && categoryCode != "") data += "&categoryCode=" + categoryCode;
		var ids = $("#common_pos_check_table").GetCheckboxCheckedValue();
		if (ids.length > 0) data += "&ids=" + ids;
		data += "&loginLevel=1"; //设置权限查询
		$.confirm({text:"确认导出POS凭条Excel?",callback:function(r){
			if(r){ 
				$.ajax({
					url:ctx+'/payfee/exportPosCheckExcel/',
					data:data,
					dataType: "json",
					async:false,
					type:'post',
					success:function(data){
						if(data.msg=='00'){//如果已经搞定
							window.location.href=ctx+"/"+data.url;
						} else if (data.msg == '01'){
							$.alert({millis:2000,text:"导出失败!"});
						} else {
							$.alert({millis:2000,text:data.msg});
						}
					}
				}); 
				}
			}
		});
	}
	
	/**未处理项查询*/
	 function common_queryUncheck(){
		 $.ajax({
				url:ctx+'/payfee/queryUncheck/',
				data:{loginLevel:1},
				dataType: "json",
				async:false,
				type:'post',
				success:function(data){
					if(data.msg=='00'){
						$("#common_pos_check_items").html(data.count);
					} else {
						$("#common_pos_check_items").html("0");
					}
				}
			});  
	 }
	 
	 
	 /**更新POS审批状态*/
	 /*function updatePosPayfeeCommon(ids,flag){
		 var message = "";
		 if(flag == 2){
			 message = "审批";
		 }else if(flag == 3){
			 message = "驳回";
		 }
		 $.confirm({text:"您确定"+message+"吗？",callback:function(y){
				if(y){
					$.ajax({
						url: ctx +"/payfee/updatePayfee",
						data:{
							id:ids,
							posCheckStatus : flag
						},
						type:"post",
						dataType:"json",
						async:false,
						success:function(data){
							if (data.msg =="00") {
								$.alert({text:message+"成功！"});
								queryPosCheckCommonByLike(0,10);
							}else{
								$.alert({text:message+"失败！"});
							}
						}
					});
				  }
				}
			});
	 }*/