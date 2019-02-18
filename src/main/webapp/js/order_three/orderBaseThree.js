/**
 * 查询 t_base_dictionary
 * @param dictCode  数据字典上级编码
 * @param dictLength 数据字典需要的编码长度
 * @param typeId 页面需要加载的下拉框ID
 */
/*function queryBaseDictionary(dictCode,dictLength,typeId){
    var ctx=$("#ctx").val();
	$.ajax({
		url:ctx+"/orderbase/queryBaseDictionary",
		data:{
			dictCode:dictCode,
			dictLength:dictLength
		},
		type:'post',
		async:false,
		success:function(data){
			var html="";
			var obj = JSON.parse(data); 
			html +="<option style='color:blue;' value='' >...请选择...</option>";
			var selected = false ;
			$.each(obj.dict,function(i,v){
				//alert(v.key +" and " +v.value);
				html +="<option value='" +v.key +"' keyValue='"+v.value +"'>"+v.value+"</option>";
			})
			$("#"+typeId).html(html); 
			// 设置默认选中
//			if(value!=null && value!=''){
//				$("#" +typeId +" option[value='" +value +"']").attr("selected","selected"); 
//			}
		}
	})
}*/

/**
 * 查询order_dictionary
 * @param pid  类型上级id
 * @param types 类型名称
 * @param typeId 下拉菜单id
 * @param value 需要回显的值
 */
/*function queryAllSelect(pid,types,typeId,value){
    var ctx=$("#ctx").val();
    if(pid==null || pid==''){
    	pid=0;
    }
 $.ajax({
	 url:ctx+"/orderbase/queryAllSelect",
	 data:{
		 pid:pid,
		 types:types
	 },
	 type:'post',
	 async:false,
	 success:function(data){
		var html="";
		var obj = JSON.parse(data); 
		html +="<option style='color:blue;' value='' >...请选择...</option>";
		var selected = false ;
		$.each(obj.se,function(i,v){
			//alert(v.key +" and " +v.value);
			html +="<option value='" +v.key +"' keyValue='"+v.value +"'>"+v.value+"</option>";
		})
		$("#"+typeId).html(html); 
		// 设置默认选中
		if(value!=null && value!=''){
			$("#" +typeId +" option[value='" +value +"']").attr("selected","selected"); 
		}
	 }
 })
}*/
/**
 * 查询 t_base_dictionary
 * @param dictCode  数据字典上级编码
 * @param dictLength 数据字典需要的编码长度
 * @param dictLength 页面需要加载的下拉框ID
 */
/*function queryServerType(dictCode,dictLength,typeId){
    var ctx=$("#ctx").val();
	$.ajax({
		url:ctx+"/orderbase/queryServerType",
		data:{
			dictCode:dictCode,
			dictLength:dictLength
		},
		type:'post',
		async:false,
		success:function(data){
			var html="";
			var obj = JSON.parse(data); 
			html +="<option style='color:blue;' value='' >...请选择...</option>";
			var selected = false ;
			$.each(obj.dict,function(i,v){
				//alert(v.key +" and " +v.value);
				html +="<option value='" +v.key +"' keyValue='"+v.value +"'>"+v.value+"</option>";
			})
			$("#"+typeId).html(html); 
		}
	})
}*/
/**
 * 查询 t_base_city
 * @param cityCode  9位城市编码
 */
/*function queryOriginDictionary(cityCode,typeId,value){
    var ctx=$("#ctx").val();
	$.ajax({
		url:ctx+"/orderbase/queryOriginDictionary",
		data:{
			dkey:cityCode
		},
		type:'post',
		async:false,
		success:function(data){
			var html="";
			var obj = JSON.parse(data); 
			html +="<option style='color:blue;' value='' >...请选择...</option>";
			var selected = false ;
			$.each(obj.dict,function(i,v){
				//alert(v.key +" and " +v.value);
				html +="<option value='" +v.key +"' keyValue='"+v.value +"'>"+v.value+"</option>";
			})
			$("#"+typeId).html(html);
			// 设置默认选中
			if(value!=null && value!=''){
				$("#" +typeId +" option[value='" +value +"']").attr("selected","selected"); 
			}
		}
	})
}*/

//设置选择城市
/*function setSelProvinceCitys(code,length,htmlId){
	var ctx=$("#ctx").val();
	 $.ajax({
		 url:ctx+"/orderbase/queryBaseCity",
		 data:{
			 code:code,
			 length:length
		 },
		 type:'post',
		 async:false,
		 success:function(data){
			var html="<option value=''>...请选择...</option>";
			var obj = JSON.parse(data); 
			$.each(obj.list,function(i,v){
				html +="<option value='" +v.code +"' keyValue='"+v.name +"'>"+v.name+"</option>";
			});
			$("#" +htmlId +"").html(html); 
		 }
	 });
}*/
/**
 * 
 * @param provinceId 需要设置省的id
 * @param cityId 需要设置市的id
 */
/*function setSelCity(provinceId,cityId,countryId){
	var ctx=$("#ctx").val();
    var code=$("#" +provinceId +" option:selected").val();
    if(code==null||code==""){
    	code=-2;
    }
    setSelProvinceCitys(code,9,cityId);
    if(countryId){
    	$("#" +countryId).html("<option value=''>...请选择...</option>");
    }
}*/
/**
 * 
 * @param cityId 需要设置市的id
 * @param countryId 需要设置区的id
 */
/*function setSelCountry(cityId,countryId){
	var ctx=$("#ctx").val();
	if(code==null||code==""){
    	code=-2;
    }
  	var code=$("#" +cityId +" option:selected").val();
  	setSelProvinceCitys(code,12,countryId);
}*/

/**
 * 日期转格式
 * @param datenum
 * @param num   日期需要的数字位数
 * @returns {String}
 */
/*function numberToDatestr(datenum,num){
	var ndate = "" ;
	if(datenum!=null && datenum!="" && datenum!="undefined"){
		if(num==8 && datenum.length>=10){
			ndate = datenum.substr(0,10);
		}else if(num==10 && datenum.length>=13){
			ndate = datenum.substr(0,13);
		}else if(num==12 && datenum.length>=16){
			ndate = datenum.substr(0,16);
		}else if (num == 14 && datenum.length >= 19) {
			ndate = datenum.substr(0, 19);
		}else{
			ndate = datenum;
		}
	}
	return ndate;
	
}*/

//新增、修改结算
function orderBasicAccountThree(orderId,cateType,totalPay,accountId,checkediscollection){
	var ctx = $("#ctx").val();
	if(orderId!=""){
		openModule(ctx + "/jsp/order_three/threeOrderBaseAccounts.jsp",
				{orderId:orderId,cateType:cateType,accountId:accountId,totalPay:totalPay,iscollection:checkediscollection},
				{},{},"orderBasicAccounts");
	}else{
		$.alert({
			text : "请选择订单！"
		});
		return;
	}
}

//新增缴费
function orderBasicPayfeeThree(accountId,orderId,cateType,accountAmount,userMobile,flag){
	var ctx = $("#ctx").val();
	var payStatus = 20110001 ;
	if(accountId>0){
		payStatus = $("#payStatusAccount" +accountId).val();
	}
	if( accountId !="" && accountId!= undefined ){
		openModule(ctx + "/jsp/order_three/threeOrderBasePayfee.jsp",
				{accountId : accountId, orderId:orderId,cateType:cateType,accountAmount:accountAmount,userMobile:userMobile,payStatus:payStatus,flag:flag},
				{},{width:800},"orderBasicPayfeeThree");
	}else{
		$.alert({text : "请先添加结算单！"});
		return;
	}
}


/**
 * 查询所有结算单和缴费
 * @param orderId
 * @param bodyId
 * @param cateType
 * @param totalPay
 */
function queryAccountThree(orderId,bodyId,cateType,totalPay){
	$("#" +bodyId).empty();
	var ctx = $("#ctx").val();
	$.ajax({
		url: ctx +"/payfee/queryAccount",
		data:{
			orderId:orderId,
			isBackType:2,
			valid:1
		},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			if(data.msg=='00'){
				//各种金额
				var moneyHtml = "<div class='row'>";
				 moneyHtml += "<div class='col-xs-6'>";
				 moneyHtml += "已结算金额：<span id='hasAcountedMoney_"+orderId+"'></span></div>";
				 moneyHtml += "<div class='col-xs-6'>";
				 moneyHtml += "已缴费金额：<span id='hasFeedMoney_"+orderId+"'></span></div></div>";
				 moneyHtml += "<div class='row'>";
				 moneyHtml += "<div class='col-xs-6'>";
				 moneyHtml += "已消费金额：<span id='hasConsumedMoney_"+orderId+"'></span></div>";
				 moneyHtml += "<div class='col-xs-6'>";
				 moneyHtml += "可用金额：<span id='hasAvaliableMoney_"+orderId+"'></span></div></div>";
				 var cutomerFee = 0;
				 if(cateType == 2){
					 cutomerFee = $("#customerManageMoney").val();
					 moneyHtml += "<div class='row'>";
					 moneyHtml += "<div class='col-xs-12'>";
					 if(cutomerFee != "" && cutomerFee != undefined && cutomerFee != 0){
						 moneyHtml += "客户信息费：<span id='consumerMessageMoney_"+orderId+"'>"+intToDecimalThree(cutomerFee)+"</span></div></div>";
					 }else{
						 cutomerFee = 0;
						 moneyHtml += "客户信息费：<span id='consumerMessageMoney_"+orderId+"'>0.00</span></div></div>";
					 }
				 }
				 
				//结算单和缴费信息
				var html = "" ;
				var zxMoney = 0;
				var nxMoney = 0;
				//可用余额
				var usableMoney = 0;
				var num = $.each(data.list,function(i,v){
						var checked = "" ;
						if(i==0){
							checked = "checked='checked'";
						}
						//计算正向和逆向金额
						if(v.payStatus == 20110002 || v.payStatus == 20110003){//已支付、已完成的结算单
							if(v.feeType == 1 || v.feeType == 2 || v.feeType == 3){//计算正向结算单总额
								zxMoney += v.feeSum*1;
							}else if(v.feeType == 4){//计算逆向结算单总额
								nxMoney += v.feeSum*1;
							}
						}
						html += "<div class='row'>";
						html += "<div class='col-xs-8'>";
						html += "<input type='radio' " +checked +" name='radioAccount' value='" +v.id +"' >" ;
						html += "结算单编号：<span id='accountId" +v.id +"'>" +v.id +"</span>";
						html += "<input type='hidden' name='bussStatusAccount" +v.id +"' id='bussStatusAccount" +v.id +"' value='" +v.bussStatus +"' >";
						html += "<input type='hidden' name='payStatusAccount" +v.id +"' id='payStatusAccount" +v.id +"' value='" +v.payStatus +"' >";
						html += "</div><div class='col-xs-4'>";
						html += "<span>应收：</span><span id='accountAmount" +v.id +"'>" +intToDecimalThree(v.feeSum) +"</span>";
						html += "</div></div>";
						html += "<div class='row'>";
						html += "<div class='col-xs-12'>";
						html += "<span>创建人：</span><span>" +v.createByName +"</span>";
						html += "&nbsp;&nbsp;&nbsp;&nbsp;";
						html += "<span>创建时间：</span><span>" +numberToDatestr(v.createTime,14) +"</span>";
						html += "</div></div>" ;
						html += "<div class='row'>";
						html += "<div class='col-xs-12'>";
						html += "<span>结算时间：</span><span id='accountpayStartTime" +v.id +"'>" +numberToDatestr(v.feeStatrtPeriod,14) +"</span>";
						html += "至<span id='accountpayEndTime" +v.id +"'>" +numberToDatestr(v.feeEndPeriod,14) +"</span>";
						html += "</div></div>" ;
						html += "<div class='row'>";
						html += "<div class='col-xs-12'>";
						html += "<span>结算状态：</span><span>" +v.payText +"</span>";
						html += "</div></div>" ;
						html += "<div class='row'>" ;
						html += "<div class='col-xs-12'>" ;
						html += "<div class='panel-content table-responsive'>" ;
						html += "<table class='table table-hover table-striped' >" ;
						html += "<thead><tr align='center'>" ;
						html += "<th width='50px' align='center'>序号</th>" ;
						html += "<th width='10%' align='center'>对账状态</th>" ;
						html += "<th width='10%' align='center'>缴费方式</th>" ;
						html += "<th width='10%' align='center'>缴费金额</th>" ;
						html += "<th width='10%' align='center'>流水号</th>" ;
						html += "<th width='10%' align='center'>卡号后四位</th>" ;
						html += "<th width='10%' align='center'>商户单号后五位</th>" ;
						html += "<th width='10%' align='center'>银行名称</th>" ;
						html += "<th width='10%' align='center'>汇款人</th>" ;
						html += "<th width='10%' align='center'>创建人</th>" ;
						html += "<th width='' align='center'>创建时间</th>" ;
						html += "</tr></thead>" ;
						html += "<tbody id='" +bodyId+v.id+"'>" +queryPayfeeThree(v.id) +"</tbody>" ;
						html += "</table>" ;
						html += "</div></div></div>";
					});
				// 只有延续性订单才去显示这些金额
				if(cateType==2){
					$("#" +bodyId).html(moneyHtml+html);
					usableMoney = totalPay - nxMoney - intToDecimalThree(cutomerFee);
					//已完成的各种金额展示
					if(zxMoney != 0){
						$("#hasAcountedMoney_"+orderId).text(intToDecimalThree(zxMoney));
						$("#hasFeedMoney_"+orderId).text(intToDecimalThree(zxMoney));
					}else{
						$("#hasAcountedMoney_"+orderId).text("0.00");
						$("#hasFeedMoney_"+orderId).text("0.00");
					}
					if(nxMoney != 0){
						$("#hasConsumedMoney_"+orderId).text(intToDecimalThree(nxMoney));
					}else{
						$("#hasConsumedMoney_"+orderId).text("0.00");
					}
					if(usableMoney >= 0){
						$("#hasAvaliableMoney_"+orderId).text(intToDecimalThree(usableMoney));
					}else{
						$("#hasAvaliableMoney_"+orderId).text("0.00");
					}
				}else{
					$("#" +bodyId).html(html);
					usableMoney = totalPay - nxMoney;
				}
			}else{
				$("#" +bodyId).html("");
			}
		}
	});
}
	
/**
 * 控制右边栏按钮是否显示
 * @param cateType
 * @param orderId
 */
	function showBtnThree(cateType,orderId){
		if(cateType == 1){
			var radioAccount = $('input:radio[name=radioAccount]:checked').val();
			if(orderId == ""){
				$("#updateAccountServerOneBtn").hide();
				$("#deleteAccountServerOneBtn").hide();
				$("#updatePayfeeServerOneBtn").hide();
			}else{
				if($("#accountListBodyOne").html() == ""){ 
					$("#updateAccountServerOneBtn").hide();
					$("#deleteAccountServerOneBtn").hide();

				}else{
					$("#updateAccountServerOneBtn").show();
					$("#deleteAccountServerOneBtn").show();
				}
				if($("#accountListBodyOne"+radioAccount).find("tr").length<=0){
					$("#updatePayfeeServerOneBtn").hide();
				}else{
					$("#updatePayfeeServerOneBtn").show();
				}
				
			}
		}else if(cateType == 2){
			var radioAccount = $('input:radio[name=radioAccount]:checked').val();
			if(orderId == ""){
				$("#updateAccountServerBtn").hide();
				$("#deleteAccountServerBtn").hide();
				$("#updatePayfeeServerBtn").hide();
			}else{
				if($("#accountListBodyCont").html() == ""){ 
					$("#updateAccountServerBtn").hide();
					$("#deleteAccountServerBtn").hide();
				}else{
					$("#updateAccountServerBtn").show();
					$("#deleteAccountServerBtn").show();
				}
				if($("#accountListBodyCont"+radioAccount).find("tr").length<=0){
					$("#updatePayfeeServerBtn").hide();
				}else{
					$("#updatePayfeeServerBtn").show();
				}
				
			}
		}else if(cateType == 3){
			var radioAccount = $('input:radio[name=radioAccount]:checked').val();
			
			if(orderId == ""){ 
				$("#updateAccountBtn").hide();
				$("#deleteAccountBtn").hide();
				$("#updatePayfeeItemBtn").hide();
			}else{
				if($("#accountListBodyItem").html() == ""){
					$("#updateAccountBtn").hide();
					$("#deleteAccountBtn").hide();
				}else{
					$("#deleteAccountBtn").show();
					$("#updateAccountBtn").show();
				}
				if($("#accountListBodyItem"+radioAccount).find("tr").length<=0){
					$("#updatePayfeeItemBtn").hide();
				}else{
					$("#updatePayfeeItemBtn").show();
				}
			}
		}else if(cateType == 4){
			var radioAccount = $('input:radio[name=radioAccount]:checked').val();
			if(orderId == ""){ 
				$("#updateAccountServerThreeBtn").hide();
				$("#deleteAccountServerThreeBtn").hide();
				$("#updatePayfeeServerThreeBtn").hide();
			}else{
				if($("#accountListBodyThree").html() == ""){ 
					$("#updateAccountServerThreeBtn").hide();
					$("#deleteAccountServerThreeBtn").hide()
				}else{
					$("#updateAccountServerThreeBtn").show();
					$("#deleteAccountServerThreeBtn").show();
				}
				if($("#accountListBodyThree"+radioAccount).find("tr").length<=0){
					$("#updatePayfeeServerThreeBtn").hide();
				}else{
					$("#updatePayfeeServerThreeBtn").show();
				}
				
			}
		}
	}

//查询结算单对应的缴费
function queryPayfeeThree(accountId){
	var ctx = $("#ctx").val();
	var html = "" ;
	$.ajax({
		url: ctx +"/payfee/queryPayfee",
		data:{
			orderId:accountId,
			valid:1
		},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			if(data.msg=='00'){
				var num = 
					$.each(data.list,function(i,v){
						html += "<tr><td>" +(i+1);
						if(v.accountStatus==1){
							html += "</td><td style='color:#008B00'>已对账";
						}else{
							html += "</td><td style='color:#FF0000'>未对账";
						}
						html += "</td><td>" +v.postText;
						html += "</td><td>" +intToDecimalThree(v.feeSum);
						if(v.feePost==20250007||v.feePost==20250015){
							html += "</td><td>";
						}else{
							html += "</td><td>" +v.bankFlowNum;
						}
						html += "</td><td>" +v.postNum;
						if(v.feePost==20250007||v.feePost==20250015){
							html += "</td><td>" +v.bankFlowNum;
						}else{
							html += "</td><td>";
						}
						html += "</td><td>" +v.postBank;
						html += "</td><td>" +v.postUser;
						html += "</td><td>" +v.createByName;
						html += "</td><td>" +numberToDatestr(v.createTime,14);
						html += "</td></tr>";
					});
			}
		}
	});
	return html ;
}

// 删除结算单
function deleteAccountByIdThree(id){
	var ctx = $("#ctx").val();
	$.ajax({
		url: ctx +"/payfee/updateAccount",
		data:{
			id:id,
			valid:2
		},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			if(data.msg=='00'){
				
			}
		}
	});
}

//服务人员等级
/*function queryWorkTypeAll(typeId,value){
	var ctx = $("#ctx").val();
	var id = "" ;
	var pid = "" ;
	 $.ajax({
		 url:ctx+"/itemDetailServer/queryWorkTypeAll",
		 data:{
			 id:id,
			 pid:pid,
		 },
		 type:'post',
		 async:false,
		 success:function(data){
			var html="";
			var obj = JSON.parse(data); 
			html +="<option style='color:blue;' value='' >...请选择...</option>";
			var selected = false ;
			$.each(obj.dict,function(i,v){
				html +="<option value='" +v.key +"' keyValue='"+v.value +"'>"+v.value+"</option>";
			})
			$("#"+typeId).html(html); 
			// 设置默认选中
			if(value!=null && value!=''){
				$("#" +typeId +" option[value='" +value +"']").attr("selected","selected"); 
			}
		 }
	 })
}*/

//服务人员工种等级
/*function queryWorkTypeLevel(typeId,value){
	var ctx = $("#ctx").val();
	var id = "" ;
	var pid = "" ;
	 $.ajax({
		 url:ctx+"/itemDetailServer/queryWorkTypeLevel",
		 data:{
			 id:id,
			 pid:pid,
		 },
		 type:'post',
		 async:false,
		 success:function(data){
			var html="";
			var obj = JSON.parse(data); 
			html +="<option style='color:blue;' value='' >...请选择...</option>";
			var selected = false ;
			$.each(obj.dict,function(i,v){
				//alert(v.key +" and " +v.value);
				html +="<option value='" +v.key +"' keyValue='"+v.value +"'>"+v.value+"</option>";
			})
			$("#"+typeId).html(html); 
			// 设置默认选中
			if(value!=null && value!=''){
				$("#" +typeId +" option[value='" +value +"']").attr("selected","selected"); 
			}
		 }
	 })
}*/

//转换价格类型
function intToDecimalThree(str){
	return new Number(str).toFixed(2);
}