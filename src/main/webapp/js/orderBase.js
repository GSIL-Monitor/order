var orderServerFeeType = {1:"定金",2:"押金",3:"预收",4:"收入",5:"客户信息费",6:"信息费收入",7:"押金转收入",8:"唯品会白条分期服务费",10:"售后手续费",11:"海金保理白条分期服务费",12:"招行分期分期服务费"};
var timeRuleFlag = false;
/**
 * 查询 t_base_dictionary
 * @param dictCode  数据字典上级编码
 * @param dictLength 数据字典需要的编码长度
 * @param typeId 页面需要加载的下拉框ID
 */
function queryBaseDictionary(dictCode,dictLength,typeId){
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
}
/**
 * 查询查询门店基地    t_auth_org
 */
function selectOrderDeptName(typeId){
    var ctx=$("#ctx").val();
    $.ajax({
        url:ctx+"/orderbase/querydeptname",
        data:{
        },
        type:'post',
        async:false,
        dataType:"json",
        success:function(data){
            var html="";
            html +="<option style='color:blue;' name='4' value='' >...请选择...</option>";
            var selected = false ;
            $.each(data.list,function(i,v){
                html +="<option value='" +v.dkey +"' keyValue='"+v.dvalue +"'>"+v.dvalue+"</option>";
            })
            $("#"+typeId).html(html); 
            $("#"+typeId).checkSelect(function(a,b,c){
                          selguanjia();
              });
          
            
        }
    })
    
}
/**
 * 查询 t_custom_channel
 * @param dictCode  数据字典上级编码
 * @param dictLength 数据字典需要的编码长度
 * @param typeId 页面需要加载的下拉框ID
 */
function queryBaseChannel(id,typeId){
    var ctx=$("#ctx").val();
	$.ajax({
		url:ctx+"/orderbase/queryServerChannel",
		data:{
			dkey:id
		},
		type:'post',
		async:false,
		dataType : "json",
		success:function(data){
			var html="";
			html +="<option style='color:blue;' value='' >...请选择...</option>";
			var selected = false ;
			$.each(data.dict,function(i,v){
				html +="<option value='" +v.dkey +"' keyValue='"+v.dvalue +"'>"+v.dvalue+"</option>";
			})
			$("#"+typeId).html(html); 
			$("#"+typeId).checkSelect(function(a,b,c){
			    personOrder()
              });
		}
	})
}


function queryBaseChannelByhousekeeper(id,typeId,housekeeperAvailable){
    var ctx=$("#ctx").val();
	$.ajax({
		url:ctx+"/orderbase/queryServerChannel",
		data:{
			dkey:id,
			housekeeperAvailable:housekeeperAvailable
		},
		type:'post',
		async:false,
		dataType : "json",
		success:function(data){
			var html="";
			html +="<option style='color:blue;' value='' >...请选择...</option>";
			var selected = false ;
			$.each(data.dict,function(i,v){
				html +="<option value='" +v.dkey +"' keyValue='"+v.dvalue +"'>"+v.dvalue+"</option>";
			})
			$("#"+typeId).html(html); 
			$("#"+typeId).checkSelect(function(a,b,c){
			    personOrder()
              });
		}
	})
}

function queryBaseChannelBak(id,typeId){
    var ctx=$("#ctx").val();
	$.ajax({
		url:ctx+"/orderbase/queryServerChannel",
		data:{
			dkey:id
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
		}
	})
}
/**
 * 查询order_dictionary
 * @param pid  类型上级id
 * @param types 类型名称
 * @param typeId 下拉菜单id
 * @param value 需要回显的值
 */
function queryAllSelect(pid,types,typeId,value){
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
}
/**
 * 查询 t_base_dictionary
 * @param dictCode  数据字典上级编码
 * @param dictLength 数据字典需要的编码长度
 * @param dictLength 页面需要加载的下拉框ID
 */
function queryServerType(dictCode,dictLength,typeId){
    var ctx=$("#ctx").val();
	$.ajax({
		url:ctx+"/orderbase/queryServerType",
		data:{
			dictCode:dictCode,
			dictLength:dictLength
		},
		type:'post',
		async:false,
		dataType:"json",
		success:function(data){
			var html="";
			html +="<option style='color:blue;' value='' >...请选择...</option>";
			html +="<option value='" +"-3" +"' keyValue='"+"商品" +"'>"+"商品"+"</option>";
			var selected = false ;
			$.each(data.list,function(i,v){
				//alert(v.key +" and " +v.value);
				html +="<option value='" +v.dkey +"' keyValue='"+v.dvalue +"'>"+v.dvalue+"</option>";
			})
			$("#"+typeId).html(html); 
		}
	})
}
/**
 * 查询 t_base_city
 * @param cityCode  9位城市编码
 */
function queryOriginDictionary(cityCode,typeId,value){
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
}

//设置选择城市
function setSelProvinceCitys(code,length,htmlId){
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
}

/**
 * 
 * @param provinceId 需要设置省的id
 * @param cityId 需要设置市的id
 */
function setSelCity(provinceId,cityId,countryId){
	var ctx=$("#ctx").val();
    var code=$("#" +provinceId +" option:selected").val();
    if(code==null||code==""){
    	code=-2;
    }
    setSelProvinceCitys(code,9,cityId);
    if(countryId){
    	$("#" +countryId).html("<option value=''>...请选择...</option>");
    }
}
/**
 * 
 * @param cityId 需要设置市的id
 * @param countryId 需要设置区的id
 */
function setSelCountry(cityId,countryId){
	var ctx=$("#ctx").val();
	if(code==null||code==""){
    	code=-2;
    }
  	var code=$("#" +cityId +" option:selected").val();
  	setSelProvinceCitys(code,12,countryId);
}

/**
 * 日期转格式
 * @param datenum
 * @param num   日期需要的数字位数
 * @returns {String}
 */
function numberToDatestr(datenum,num){
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
	
}

//新增、修改结算
function orderBasicAccount(orderId,cateType,totalPay,accountId,checkedIscollection){
	var ctx = $("#ctx").val();
	var orderStatus = parent.$("#checkedOrderStatus").val();
	if(orderId!="" && orderStatus != "9" && orderStatus != "10"){
		openModule(ctx + "/jsp/orderBasicAccount.jsp",
				{orderId:orderId,cateType:cateType,accountId:accountId,totalPay:totalPay,checkedIscollection:checkedIscollection},
				{},{},"orderBasicAccount");
	}else if(orderStatus == "9" ){
		$.alert({top:'30%',text:"此订单已完成，不能新增、修改结算单！"});
	}else if(orderStatus == "10"){
		$.alert({top:'30%',text:"此订单已取消，不能新增、修改结算单！"});
	}else{
		$.alert({
			text : "请选择订单！"
		});
		return;
	}
}

//查看预估结算
function orderBasicYgAccount(orderId,cateType,totalPay,accountId){
	var ctx = $("#ctx").val();
	if(orderId!=""){
		openModule(ctx + "/jsp/orderBasicAccount.jsp",
				{orderId:orderId,cateType:cateType,accountId:accountId,totalPay:totalPay,isYg:1},
				{},{},"orderBasicAccount");
	}else{
		$.alert({
			text : "请选择订单！"
		});
		return;
	}
}

//新增缴费
function orderBasicPayfee(accountId,orderId,cateType,accountAmount,userMobile,flag,orderChannel,userId,payType){
	//alert(accountId +" and " +orderId +" and " +cateType);
	var ctx = $("#ctx").val();
	var payStatus = 20110001 ;
	var orderStatus = parent.$("#checkedOrderStatus").val();
	var updatePayfeeFlag = true;
	if(flag == 2){
		$("input[name=feePostPayfee]").each(function(){
			var feePostPayfee = $(this).val();
			if(feePostPayfee != null && (feePostPayfee == '20250020' || feePostPayfee == '20250021'
				||feePostPayfee == '20250022'||feePostPayfee == '20250023')){
				updatePayfeeFlag = false;
				return;
			}
		});
	}
	if(updatePayfeeFlag){
		if(accountId>0){
			payStatus = $("#payStatusAccount" +accountId).val();
		}
		if( accountId !="" && accountId!= undefined && orderStatus != "9" && orderStatus != "10"){
			openModule(ctx + "/jsp/orderBasicPayfee.jsp",
					{accountId : accountId, orderId:orderId,cateType:cateType,accountAmount:accountAmount,
				userMobile:userMobile,payStatus:payStatus,flag:flag,orderChannel:orderChannel,userId:userId,payType:payType},
				{},{width:800},"orderBasicPayfee");
		}else if(orderStatus == "9" ){
			$.alert({top:'30%',text:"此订单已完成，不能新增、修改缴费！"});
		}else if(orderStatus == "10"){
			$.alert({top:'30%',text:"此订单已取消，不能新增、修改缴费！"});
		}else{
			$.alert({text : "请先添加结算单！"});
			return;
		}
	}else{
		$.alert({top:'30%',text:"此缴费不能修改！"});
		return;
	}
}
//查询所有非退款结算单
function queryAccount(orderId,bodyId,cateType,totalPay){
	$("#" +bodyId).empty();
	var ctx = $("#ctx").val();
	$.ajax({
		url: ctx +"/payfee/queryAccountList",
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
				/**
				 moneyHtml += "<div class='col-xs-6'>";
				 moneyHtml += "已结算金额：<span id='hasAcountedMoney_"+orderId+"'></span></div>";
				 moneyHtml += "<div class='col-xs-6'>";
				 moneyHtml += "已缴费金额：<span id='hasFeedMoney_"+orderId+"'></span></div></div>";
				 moneyHtml += "<div class='row'>";
				 moneyHtml += "<div class='col-xs-6'>";
				 moneyHtml += "已消费金额：<span id='hasConsumedMoney_"+orderId+"'></span></div>";
				 **/
				 moneyHtml += "<div class='col-xs-6'>";
				 moneyHtml += "可用金额：<span id='hasAvaliableMoney_"+orderId+"'>" +$("#checkedTotalPay").val() +"</span></div></div>";
				 //var cutomerFee = 0;
				 /*if(cateType == 2){
					 cutomerFee = $("#customerManageMoney").val();
					 moneyHtml += "<div class='row'>";
					 moneyHtml += "<div class='col-xs-12'>";
					 if(cutomerFee != "" && cutomerFee != undefined && cutomerFee != 0){
						 moneyHtml += "客户信息费：<span id='consumerMessageMoney_"+orderId+"'>"+intToDecimal(cutomerFee)+"</span></div></div>";
					 }else{
						 cutomerFee = 0;
						 moneyHtml += "客户信息费：<span id='consumerMessageMoney_"+orderId+"'>0.00</span></div></div>";
					 }
				 }*/
				 
				//结算单和缴费信息
				var html = "" ;
				var zxMoney = 0;
				var nxMoney = 0;
				var jfMoney = 0;
				//可用余额
				var usableMoney = 0;
				var num = $.each(data.list,function(i,v){
						var checked = "" ;
						if(i==0){
							checked = "checked='checked'";
						}
						//计算正向和实收金额
						/**
						if(v.payStatus == 20110003){//已支付、已完成的结算单
							if(v.feeType == 1 || v.feeType == 2 || v.feeType == 3 || v.feeType == 5){//计算正向结算单总额
								zxMoney += v.feeSum*1;
							}else if(v.feeType == 4 || v.feeType == 6){//计算实收结算单总额
								nxMoney += v.feeSum*1;
							}
						}
						if(v.payStatus == 20110001 ||v.payStatus == 20110002 || v.payStatus == 20110003){
							if(v.feeType == 1 || v.feeType == 2 || v.feeType == 3 || v.feeType == 5){//计算正向结算单总额
								jfMoney += v.feeSum*1;
							}
						}
						**/
						html += "<div class='row'>";
						html += "<div class='col-xs-8'>";
						html += "<input type='radio' " +checked +" name='radioAccount' value='" +v.id +"' >" ;
						html += "结算单编号：<span id='accountId" +v.id +"'>" +v.id +"</span>";
						html += "<input type='hidden' name='bussStatusAccount" +v.id +"' id='bussStatusAccount" +v.id +"' value='" +v.bussStatus +"' >";
						html += "<input type='hidden' name='payStatusAccount" +v.id +"' id='payStatusAccount" +v.id +"' value='" +v.payStatus +"' >";
						html += "</div><div class='col-xs-4'>";
						html += "<span>应收：</span><span id='accountAmount" +v.id +"'>" +intToDecimal(v.feeSum) +"</span>";
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
						html += "<div class='col-xs-6'>";
						html += "<span>结算状态：</span><span>" +v.payText +"</span>";
						html += "<input type='hidden' id='accountpayType"+v.id+"' value='"+v.feeType+"'></input>";
						html += "</div>" ;
						if(cateType==2 || cateType==3){ //商品或延续订单
							//TODO:押金转收入按钮添加
							if( v.payType == 2){
								html += "<div class='col-xs-6'>";
								html += "<span>结算类型：" +orderServerFeeType[v.feeType];
								html += "&nbsp;&nbsp;<button type='button' class='btn btn-primary btn-xs' onclick='changeAccountType("+v.id+","+orderId+",\""+bodyId+"\","+cateType+","+totalPay+")'>";
								html += "<i class='glyphicon' data-toggle='tooltip' data-placement='top'>转收入</i></button>";
								html += "</span></div>" ;
							}else{
								html += "<div class='col-xs-6'>";
								html += "<span>结算类型：" +orderServerFeeType[v.feeType] +"</span>";
								html += "</div>" ;
							}
						}
						html += "</div><div class='row'>" ;
						html += "<div class='col-xs-12'>" ;
						html += "<div class='panel-content table-responsive'>" ;
						html += "<table class='table table-hover table-striped' >" ;
						html += "<thead><tr align='center'>" ;
						html += "<th>序号</th>" ;
						html += "<th>对账状态</th>" ;
						html += "<th>缴费方式</th>" ;
						html += "<th>劵标题</th>" ;
						html += "<th>缴费金额</th>" ;
						html += "<th>缴费单ID</th>" ;
						html += "<th>流水号/ 交易参考号</th>" ;
						html += "<th>卡号后四位</th>" ;
						html += "<th>商户单号后五位</th>" ;
						html += "<th>银行名称</th>" ;
						html += "<th>汇款人</th>" ;
						html += "<th>平台实付金额</th>" ;
						html += "<th>平台订单编号</th>" ;
						html += "<th>创建人</th>" ;
						html += "<th>创建时间</th>" ;
						//html += "<th>交易参考号</th>";
						html += "</tr></thead>" ;
						html += "<tbody id='" +bodyId+v.id+"'>" +queryPayfee(v.id) +"</tbody>" ;
						html += "</table>" ;
						html += "</div></div></div>";
					});
				// 只有延续性订单才去显示这些金额
				if(cateType==2){
					$("#" +bodyId).html(moneyHtml+html);
					/**
					usableMoney = zxMoney - nxMoney;
					//已完成的各种金额展示
					if(zxMoney != 0){
						$("#hasAcountedMoney_"+orderId).text(intToDecimal(zxMoney));
						$("#hasFeedMoney_"+orderId).text(intToDecimal(jfMoney));
					}else{
						$("#hasAcountedMoney_"+orderId).text("0.00");
						$("#hasFeedMoney_"+orderId).text(intToDecimal(jfMoney));
					}
					if(nxMoney != 0){
						$("#hasConsumedMoney_"+orderId).text(intToDecimal(nxMoney));
					}else{
						$("#hasConsumedMoney_"+orderId).text("0.00");
					}
					if(usableMoney >= 0){
						$("#hasAvaliableMoney_"+orderId).text(intToDecimal(usableMoney));
					}else{
						$("#hasAvaliableMoney_"+orderId).text("0.00");
					}
					**/
				}else{
					$("#" +bodyId).html(html);
				}
			}else{
				$("#" +bodyId).html("");
			}
		}
	});
}
	// 控制右侧按钮是否显示
	function showBtn(cateType,orderId){
		if(cateType == 1){
			var radioAccount = $('input:radio[name=radioAccount]:checked').val();
			if(orderId == ""){
				$("#updateAccountServerOneBtn").hide();
				$("#deleteAccountServerOneBtn").hide();
				$("#updatePayfeeServerOneBtn").hide();
				$("#addPayfeeWeiXinServerOneBtn").hide();
				$("#addPayfeeJiaLianServerOneBtn").hide();
			}else{
				if($("#accountListBodyOne").html() == ""){ 
					$("#updateAccountServerOneBtn").hide();
					$("#deleteAccountServerOneBtn").hide();
					$("#addPayfeeWeiXinServerOneBtn").hide();
					$("#addPayfeeJiaLianServerOneBtn").hide();

				}else{
					$("#updateAccountServerOneBtn").show();
					$("#deleteAccountServerOneBtn").show();
					$("#addPayfeeWeiXinServerOneBtn").show();
					$("#addPayfeeJiaLianServerOneBtn").show();
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
				$("#addPayfeeWeiXinServerBtn").hide();
				$("#addPayfeeJiaLianServerBtn").hide();
			}else{
				if($("#accountListBodyCont").html() == ""){ 
					$("#updateAccountServerBtn").hide();
					$("#deleteAccountServerBtn").hide();
					$("#addPayfeeWeiXinServerBtn").hide();
					$("#addPayfeeJiaLianServerBtn").hide();
				}else{
					$("#updateAccountServerBtn").show();
					$("#deleteAccountServerBtn").show();
					$("#addPayfeeWeiXinServerBtn").show();
					$("#addPayfeeJiaLianServerBtn").show();
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
				$("#addPayfeeWeiXinBtn").hide();
				$("#addPayfeeJiaLianBtn").hide();
			}else{
				if($("#accountListBodyItem").html() == ""){
					$("#updateAccountBtn").hide();
					$("#deleteAccountBtn").hide();
					$("#addPayfeeWeiXinBtn").hide();
					$("#addPayfeeJiaLianBtn").hide();
				}else{
					$("#deleteAccountBtn").show();
					$("#updateAccountBtn").show();
					$("#addPayfeeWeiXinBtn").show();
					$("#addPayfeeJiaLianBtn").show();
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
				$("#addPayfeeWeiXinServerThreeBtn").hide();
				$("#addPayfeeJiaLianServerThreeBtn").hide();
			}else{
				if($("#accountListBodyThree").html() == ""){ 
					$("#updateAccountServerThreeBtn").hide();
					$("#deleteAccountServerThreeBtn").hide();
					$("#addPayfeeWeiXinServerThreeBtn").hide();
					$("#addPayfeeJiaLianServerThreeBtn").hide();
				}else{
					$("#updateAccountServerThreeBtn").show();
					$("#deleteAccountServerThreeBtn").show();
					$("#addPayfeeWeiXinServerThreeBtn").show();
					$("#addPayfeeJiaLianServerThreeBtn").show();
				}
				if($("#accountListBodyThree"+radioAccount).find("tr").length<=0){
					$("#updatePayfeeServerThreeBtn").hide();
				}else{
					$("#updatePayfeeServerThreeBtn").show();
				}
				
			}
		}
	}

/**
 * 联动POS微信支付,联动POS刷卡支付,联动POS支付宝支付
 * @param thiz
 */
function openLiandongPosByPayfeeId(thiz){
	var ctx = $("#ctx").val();
	var payfeeId = $(thiz).attr("data-payfeeId");//缴费id
	var postText = $(thiz).attr("data-postText");//支付方式名称
	var feeSum = $(thiz).attr("data-feeSum");//缴费金额
	var data = {"payfeeId":payfeeId,"postText":postText,"feeSum":feeSum};
	if(payfeeId){
		openModule(ctx+"/jsp/liandongPosByPayfeeId.jsp",data);
	}
}

//查询结算单对应的缴费
function queryPayfee(accountId){
	var ctx = $("#ctx").val();
	var html = "" ;
	$.ajax({
		url: ctx +"/payfee/queryPayfee",
		data:{
			orderId:accountId,
			valid:1,
			//payStatus:'20110002'
		},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			if(data.msg=='00'){
				var num = 
					$.each(data.list,function(i,v){
						if((v.feePost==20250007 && v.payStatus == '20110001')||(v.feePost==20250023 && v.payStatus == '20110001')){
							html += "";
						}else if ((v.feePost==20250017 || v.feePost==20250018 || v.feePost==20250019 || v.feePost==20250020 || v.feePost==20250021 || v.feePost==20250022) 
								&& (v.isManualFee==2 ||v.isManualFee== "0") && v.payStatus == '20110001'){
							html += "";
						}else{
							html += "<tr><td>" +(i+1);
							html += "<input type='hidden' name='feePostPayfee' value='" +v.feePost +"' >";
							html += "<input type='hidden' name='money' value='" +intToDecimal(v.feeSum)+"' >";
							html += "<input type='hidden' name='orderPayFeeId' value='" +v.id+"' >";
							html += "<input type='hidden' id='feePostPayfee"+accountId+"' value='" +v.feePost+"' >";
							if(v.accountStatus==1){
								html += "</td><td style='color:#008B00'>已对账";
							}else{
								html += "</td><td style='color:#FF0000'>未对账";
							}
							html += "</td><td>" +v.postText;
							if(v.feePost == "20250037" || v.feePost == "20250038" || v.feePost == "20250041"){
								//联动POS微信支付、联动POS刷卡支付、联动POS支付宝支付、未对账
								if(v.accountStatus != "1"){
									html += "<button class='btn btn-primary btn-xs' data-payfeeId="+v.id+" data-postText="+v.postText+" data-feeSum="+intToDecimal(v.feeSum)+" onclick='openLiandongPosByPayfeeId(this);'>联动POS二维码</button>";
								}
							}
							html += "</td><td>" +(v.ticketName==''?"--":v.ticketName);
							html += "</td><td>" +intToDecimal(v.feeSum);
							html += "</td><td>" +v.id;
							if(v.feePost==20250007||v.feePost==20250015||v.feePost==20250046){
								html += "</td><td>";
							}else{
								html += "</td><td>" +v.bankFlowNum;
							}
							html += "</td><td>" +v.postNum;
							if(v.feePost==20250007||v.feePost==20250015||v.feePost==20250046){
								html += "</td><td>" +v.bankFlowNum;
							}else{
								html += "</td><td>";
							}
							html += "</td><td>" +v.postBank;
							html += "</td><td>" +v.postUser;
							if(v.feePost==20250007 || v.feePost==20250014 || v.feePost==20250025){
								html += "</td><td>" +v.platformFee;
							}else{
								html += "</td><td>";
							}
							if(v.feePost==20250007 || v.feePost==20250014 || v.feePost==20250025 || v.feePost==20250031){
								html += "</td><td>" +v.platformOrderId;
							}else{
								html += "</td><td>";
							}
							html += "</td><td>" +v.createByName;
							html += "</td><td>" +numberToDatestr(v.createTime,14);
						//	html += "</td><td>" +v.bankFlowNum;
							html += "</td></tr>";
							$("#feePostPayfee"+accountId).val(v.feePost);
						}
					});
			}
		}
	});
	return html ;
}

// 删除结算单
function deleteAccountById(id){
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
function queryWorkTypeAll(typeId,value){
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
}

//服务人员工种等级
function queryWorkTypeLevel(typeId,value){
	var ctx = $("#ctx").val();
	var id = "" ;
	var pid = "" ;
	 $.ajax({
		 url:ctx+"/itemDetailServer/queryWorkTypeLevel",
		 data:{
			 id:id,
			 pid:pid
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
}

//转换价格类型
function intToDecimal(str){
	return new Number(str).toFixed(2);
}

//显示模态框滚动条
function setOrderModalOverflowAuto(modelId){
	$("#" +modelId).addClass("defaultManagerAll");
	$("#defaultManagerAll").removeClass("defaultManagerAll");
	$("#defaultManagerAll").addClass("defaultManagerAllHidden");
}
//关闭第一层模态框，显示父页面滚动条
function setOrderCloseModule(modelId){
	$("#defaultManagerAll").removeClass("defaultManagerAllHidden");
	$("#defaultManagerAll").addClass("defaultManagerAll");
	closeModule(modelId);
}

//查询将要删除的结算单的创建时间
function loadAccountCreTime(accountId,eleId){
	var ctx = $("#ctx").val();
	$.ajax({
		url: ctx +"/payfee/loadAccount",
		data:{
			id:accountId
		},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			if(data.msg=='00'){
				$("#"+eleId).val(data.payfee.createTime);
			}
		}
	});
}
//根据将要删除结算单id,查询对应的缴费的最小创建时间
function loadPayfeeMinCreTime(accountId,eleId){
	var ctx = $("#ctx").val();
	$.ajax({
		url: ctx +"/payfee/loadPayfeeMinCreTime",
		data:{
			accountId:accountId
		},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			if(data.msg=='00'){
				$("#"+eleId).val(data.minCreTime);
			}
		}
	});
}
//查询系统时间
function querySysdate(eleId){
	var ctx = $("#ctx").val();
	$.ajax({
		url: ctx +"/order/querySysdate",
		data:{},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			if(data.msg=='00'){
				$("#"+eleId).val(data.systemDate);
			}
		}
	});
}

//未对账结算单，根据时间规则判断是否可以删除
//每月5日24点前，可以删除上个月和本月5日之前的结算单
//每月5日24点后，可删除本月的结算单
function deleteAccountTimeRule(creTime,sysDate){
	timeRuleFlag = false;
	if(creTime != "" && creTime != null && sysDate != "" && sysDate != null){
		var creTime = string2date(creTime.substr(0,19));
		var sysDate = string2date(sysDate.substr(0,19));
		//创建时间年月日
		var cy = creTime.getFullYear();
		var cm = creTime.getMonth()+1;
		var cd = creTime.getDate();
		//系统时间年月日
		var sy = sysDate.getFullYear();
		var sm = sysDate.getMonth()+1;
		var sd = sysDate.getDate();
		//年份相同
		if(cy == sy){
			if(cm == sm){//月份相同
				timeRuleFlag = true;
			}else if(cm == sm - 1){//月份比系统月份少一个月
				if(sd <= 5){
					timeRuleFlag = true;
				}
			}
		}
		//年份不同
		if(cy == sy - 1){
			if(cm == 12 && sm == 1){
				if(sd <= 5){
					timeRuleFlag = true;
				}
			}
		}
	}
	return timeRuleFlag;
}
	//日期转换
	function string2date(str){
	  return new Date(Date.parse(str.replace(/-/g,  "/")));
	}
	
	//查询渠道
	function checkPayfeeChannel(channelId){
		var ctx = $("#ctx").val();
		$.ajax({
			url:ctx + "/orderbase/queryServerChannel",
			data:{
				dkey:channelId
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg =="00") {
					$.each(data.dict,function(i,v){
						$("#feeSumTRDS_channel").val(v.dvalue); 
						$("#TRDSIsCollection").val(v.isCollection); 
						$("#TRDSChannelId").val(v.dkey); 
					})
				}
			}
		});
	}
	//微信生成二维码
	function weiXinQRCode(orderId,cateType,totalPay,accountId){
		var ctx = $("#ctx").val();
		if(accountId>0){
			payStatus = $("#payStatusAccount" +accountId).val();
			if(payStatus == '20110001'){
				openModule(ctx + "/jsp/orderWeiXinQRCode.jsp",
						{accountId:accountId,orderId:orderId,cateType:cateType,totalPay:totalPay},
						{},{width:'60%',height:'80%'},"orderWeiXinQRCode");
			}else{
				$.alert({text : "此结算单不能微信扫码支付！"});
				return;
			}
		}
	}
	//嘉联生成二维码
	function JiaLianQRCode(orderId,cateType,totalPay,accountId,payStatus){
		var ctx = $("#ctx").val();
		if(accountId>0){
			if(payStatus == '20110001'){
				openModule(ctx + "/jsp/orderJiaLianQRCode.jsp",
						{accountId:accountId,orderId:orderId,cateType:cateType,totalPay:totalPay},
						{},{width:'60%',height:'80%'},"orderJiaLianQRCode");
			}else{
				$.alert({text : "此结算单不能嘉联扫码支付！"});
				return;
			}
		}
	}
	//更新结算单为押金转收入类型
	function changeAccountType(accountId,orderId,bodyId,cateType,totalPay){
		var ctx = $("#ctx").val();
		$.ajax({
			url:ctx + "/payfee/updateAccount",
			data:{
				id:accountId,
				payType:7,
				isYg:1//修改预估标记
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg =="00") {
					queryAccount(orderId,bodyId,cateType,totalPay);
				}else{
					$.alert({text : "押金转收入失败！"});
					return;
				}
			}
		});
	}
	
	//查询缴费是否对账，未对账可修改，已对账不可修改
	function checkAccountStatus(accountId){
		var ctx = $("#ctx").val();
		var tip = true;
		$.ajax({
			url:ctx + "/payfee/queryPayfee?curPage=1&pageCount=10",
			data:{
				orderId:accountId,
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg =="00") {
					$.each(data.list,function(i,v){
		               if(v.accountStatus == 2){
		            	   tip = false;
		            	   return false;
		               }
		            })
				}
			}
		});
		
		return tip;
	}

	
	/**
	 * 查询订单类型下拉列表
	 * @param dictCode
	 * @param dictLength
	 * @param typeId
	 */
	function queryOrderServerTypeNew(dictCode,dictLength,typeId){
	    var ctx=$("#ctx").val();
		$.ajax({
			url:ctx+"/orderbase/queryServerType",
			data:{
				dictCode:dictCode,
				dictLength:dictLength
			},
			type:'post',
			async:false,
			dataType:"json",
			success:function(data){
				var html = "<option style='color:blue;' value='' >...请选择...</option>";
				if(data.msg == "00"){
					html +="<option value='" +"-3" +"' keyValue='"+"商品" +"'>"+"商品"+"</option>";
					$.each(data.list,function(i,v){
						html +="<option value='" +v.dkey +"' keyValue='"+v.dvalue +"' >"+v.dvalue+"</option>";
					})
				}
				$("#"+typeId).html(html).checkSelect(function(){
					$("#"+typeId).trigger("change");
				}); 
			}
		})
	}
	
	//联动生成二维码
	function LianDongQRCode(orderId,cateType,totalPay,orderPayFeeId,payStatus,feePostPayfee){
		var ctx = $("#ctx").val();
		if(orderPayFeeId>0){
			if(payStatus == '20110001'){
				openModule(ctx + "/jsp/orderLianDongQRCode.jsp",
						{orderPayFeeId:orderPayFeeId,orderId:orderId,cateType:cateType,totalPay:totalPay,feePostPayfee:feePostPayfee},
						{},{width:'40%',height:'40%'},"orderLianDongQRCode");
			}else{
				$.alert({text : "此结算单不能联动扫码支付 ！"});
				return;
			}
		}
	}
	
	/**
	 * 查询渠道
	 * 条件：是否启用、管家可见、业务类型为订单和通用
	 * @param tagId 下拉框标签id 必选
	 * @param data 参数 可选
	 */
	function queryChannels(tagId,data){
	    var ctx=$("#ctx").val();
	    if(tagId){
	    	$.ajax({
	    		url:ctx+"/orderbase/queryChannels",
	    		data:data,
	    		type:'post',
	    		async:false,
	    		dataType : "JSON",
	    		success:function(data){
	    			var html = "<option style='color:blue;' value='' >...请选择...</option>";
	    			if(data.msg == "00"){
	    				$.each(data.list,function(i,v){
	    					html +="<option value='" +v.dkey +"' keyValue='"+v.dvalue +"'>"+v.dvalue+"</option>";
	    				})
	    			}
	    			$("#"+tagId).html(html).checkSelect(function(){
	    				    personOrder();
	    			});
	    		}
	    	})
	    }
	}
//他人代收类型缴费，修改结算
function updateAccountForTRDS(orderId,cateType,totalPay,accountId,feePost){
	var ctx = $("#ctx").val();
	var orderStatus = parent.$("#checkedOrderStatus").val();
	if(orderId!="" && orderStatus != "9" && orderStatus != "10"){
		openModule(ctx + "/jsp/orderBasicAccount.jsp",
			{orderId:orderId,cateType:cateType,accountId:accountId,totalPay:totalPay,feePost:20250014},
			{},{},"orderBasicAccount");
	}else if(orderStatus == "9" ){
		$.alert({top:'30%',text:"此订单已完成，不能新增、修改结算单！"});
	}else if(orderStatus == "10"){
		$.alert({top:'30%',text:"此订单已取消，不能新增、修改结算单！"});
	}else{
		$.alert({
			text : "请选择订单！"
		});
		return;
	}
}

/**
 * 实现给定某日期，判断是星期几  
 * 操作人：周鑫
 * @param dateString  必须yyyy-MM-dd
 * @returns {String}
 */
function getWeekday(dateString){
    var date;
    var dateArray = dateString.split("-");
    date = new Date(dateArray[0], parseInt(dateArray[1] - 1), dateArray[2]);
    return "星期" + "天一二三四五六".charAt(date.getDay());
}
/**
 * 四要素验证
 * 周鑫
 * @param dataString
 * @returns {Number}
 */
function checkFourGeneral(dataString){
	var result=-1;
	$.ajax({
		url:ctx+"/agreement/submitAuth",
		data:dataString,
		type:'post',
		async:false,
		dataType : "JSON",
		success:function(data){
			if(data.code=="00"){
				result=data.code;
			}else{
				result=data.msg;
			}
		}
	});
	return result;
}
/**
 * 验证订单缴费的审核状态为“已确认”
 * @param dataString
 * @returns {Number}
 */
function checkAccountReviewState(accountId){
	var result=1;
	$.ajax({
		url:ctx+"/payfee/checkAccountReviewState",
		data:{
			accountId:accountId
		},
		type:'post',
		async:false,
		dataType : "JSON",
		success:function(data){
			if(data.msg=="00"){
				result="00"
			}
		}
	});
	return result;
}



/**
 * 周鑫  2019-01-07 
 * @param orderId
 * @returns {历史退费金额}
 */
function getHistoryAfterCharge(orderId){
	var result=0;
	$.ajax({
		url:ctx+"/payfee/historyAfterCharge",
		data:{
			orderId:orderId
		},
		type:'post',
		async:false,
		dataType : "JSON",
		success:function(data){
			if(data.msg=="00"){
				result=data.result;
			}
		}
	});
	return result;
}
/**
 * 根据订单查询ID，查询产品的三级分类
 * @param orderId
 * @returns {Number}
 */
function getProductItemByOrderId(orderId){
	var result=0;
	$.ajax({
		url:ctx+"/payfee/historyAfterCharge",
		data:{
			orderId:orderId
		},
		type:'post',
		async:false,
		dataType : "JSON",
		success:function(data){
			if(data.msg=="00"){
				result=data.result;
			}
		}
	});
	return result;
}

/**
 * 根据选择服务对象，显示单次合同审核状态选项
 *
 * @Author zhanghao
 * @Date 20190214
 */
function selectOnceAgreementCheckStatus(){
	debugger;
	var serviceObjectQuery = $("#serviceObjectQuery").val();
	if(serviceObjectQuery == 1){
		$("#onceAgreementCheckStatusDiv").css("display","block");
	}else{
		$("#onceAgreementCheckStatusDiv").css("display","none");
	}
}







