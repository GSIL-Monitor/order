/**
 * 售后迁单新建js
 * @author wn
 */
 var ctx=$("#ctx").val();
 	//要迁单的订单，A单
	function queryOrderA(){
		var orderCode = $("#c_orderCode").val();
		var html="";
		if(orderCode != null && orderCode != ""){//未填写订单编号，默认不显示
			$.ajax({
				url: ctx +"/afterSales/queryOrderA",
				data:{
					orderCode:orderCode
				},
				type:"post",
				dataType:"json",
				async:false,
				success:function(data){
					if (data.msg =="00") {
							$.each(data.list,function(i,v){
								if(v.vphOrderId != null && v.vphOrderId != "" && (v.orderStatusOther == "20130001" || v.orderStatusOther == "20130001")&& v.orderStatusOther != "20130010"){//售后单有迁单id
									html += "<tr><td colspan='7'><p class='table-empty' style='line-height:20px;'>暂无数据</p></td></tr>";
									$.alert({top:'30%',text:"要迁订单号已有迁单数据！"});
								}else{
									html +="<tr>";
									html +="<td align='left'>"+v.orderCode;
									html +="</td><td align='left'>"+v.userName;
									html +="</td><td align='left'>"+v.userMobile;
									html +="</td><td>"+v.typeText;
									html +="</td><td>"+numberToDatestr(v.createTime,8); 
									html +="</td><td align='left'>"+v.statusText;
									html +="</td><td align='left'>"+v.sourceText;
									html +="</td></tr>";
									queryOrderB(v.userMobile,v.orderCode);//查询转单
									queryChangeFee(v.id);//查询可迁单金额
									//将参数放入隐藏域
									$("#h_userName").val(v.userName);
									$("#h_userMobile").val(v.userMobile);
									$("#h_orderId").val(v.id);
								}
							});
					}else{
						html += "<tr><td colspan='7'><p class='table-empty' style='line-height:20px;'>暂无数据</p></td></tr>";
				   }
					/*表格点击行高亮*/
				    trColor("#c_order_info > tr");
				}
			});
		}else{
			html += "<tr><td colspan='7'><p class='table-empty' style='line-height:20px;'>暂无数据</p></td></tr>";
		}
		$("#c_order_info").html(html);
	}
	//根据客户电话，查询迁至订单号，B单
	function queryOrderB(mobile,orderCode){
		$.ajax({
			url: ctx+"/order/queryChangeOrder",
			data:{
				userMobile:mobile,
				orderCodeNotIn:orderCode
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				var html = "";
				html +="<option style='color:blue;'  value='' >...请选择...</option>";
				if (data.msg == "00") {
					 $.each(data.list,function(i,v){
						 html +="<option value='" +v.id +"' >"+v.orderCode+"</option>";
					});
				}
				 $("#c_orderSelect").html(html); 
			}
		});
	}
			
	
	//重置订单号输入框
	function resetChangeInfo(){
		$("#c_orderCode").val("");
		$("#c_order_info").html("<tr><td colspan='7'><p class='table-empty' style='line-height:20px;'>暂无数据</p></td></tr>");
		$("#c_orderSelect").val("");
		$("#c_memberShipFee").html("");
		$("#c_salery_novph").html("");
		$("#c_salery_vph").html("");
		$("#h_userName").val("");
		$("#h_userMobile").val("");
		$("#h_orderId").val("");
	}
	
	//新增迁单售后单
	function insertOrderSale(){
		var orderId = $("#h_orderId").val();//要迁单的订单id
		var memberShipFee = $("#h_memberShipFee").val(); //可退信息费
		var saleryNoFee = $("#h_salery_novph").val(); //可退服务费（非白条）
		var saleryFee = $("#h_salery_vph").val();//可退服务费（白条）
		var userName = $("#h_userName").val(); //客户名称
		var userMobile = $("#h_userMobile").val();//客户电话
		var moveToOrderId = $("#c_orderSelect").find("option:selected").val();//选择迁至订单的订单id
		var approveDept = $("#c_ApproveDeptSelect").find("option:selected").val();//审核部门
		var approveBy = $("#c_ApproveBySelect").find("option:selected").val();//审核人
		var reason = $("#c_reason").val();//迁单原因
		if(orderId == null || orderId == ""){
			$.alert({millis:3000,top:'30%',text:"请输入要迁订单号,并查询获取"});
			$("#changeOrderSaleBtn").removeAttr("disabled");
			return;
		}
		var result = isOrderSale(orderId);
		if(result==false){
			$.alert({millis:5000,top:'30%',text:"抱歉,您的订单已经进行过迁单操作,不能进行二次迁单! "});
			$("#changeOrderSaleBtn").removeAttr("disabled");
			return;
		};
		if(moveToOrderId == null || moveToOrderId == "") {
			$.alert({millis:3000,top:'30%',text:"请选择迁至订单！"});
			$("#changeOrderSaleBtn").removeAttr("disabled");
			return;
		}
		if(approveDept == "" || approveDept == null){
			$.alert({millis:2000,top:'30%',text:"请选择审批部门！"});
			$("#changeOrderSaleBtn").removeAttr("disabled");
			return ;
		}
		if(approveBy == "" || approveBy == null){
			$.alert({millis:2000,top:'30%',text:"请选择审批人！"});
			$("#changeOrderSaleBtn").removeAttr("disabled");
			return ;
		}
		if(memberShipFee == null || memberShipFee*1 < 0  ){
			$.alert({millis:2000,top:'30%',text:"可迁信息费应大于等于0元！"});
			$("#changeOrderSaleBtn").removeAttr("disabled");
			return ;
		}
		if(saleryNoFee == null || saleryNoFee*1 < 0  ){
			$.alert({millis:2000,top:'30%',text:"可迁服务人员服务费(非白条)应大于等于0元！"});
			$("#changeOrderSaleBtn").removeAttr("disabled");
			return ;
		}
		if(saleryFee == null || saleryFee*1 < 0  ){
			$.alert({millis:2000,top:'30%',text:"可迁(白条)应大于等于0元！"});
			$("#changeOrderSaleBtn").removeAttr("disabled");
			return ;
		}
		var refundTotal = memberShipFee *1 + saleryNoFee *1; //银行卡可退金额总和
		var refundMoney = refundTotal + saleryFee *1;//迁单总金额 = 银行卡总额 + 白条总额
		if(refundMoney == null || refundMoney <= 0  ){
			$.alert({millis:2000,top:'30%',text:"迁单总金额不能为0！"});
			$("#changeOrderSaleBtn").removeAttr("disabled");
			return ;
		}
		$.ajax({
			url: ctx+"/afterSales/insertChangeOrder",
			data:{
				custMobile:userMobile,
				custName:userName,
				orderId:orderId,
				refundMembershipFee:memberShipFee,
				refundSalaryFee:saleryNoFee,
				vphFee:saleryFee,
				refundTotal:refundTotal,
				moveToOrderId:moveToOrderId,
				approveDept:approveDept,
				approveBy:approveBy,
				reason:reason
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg =="00") {
					$.alert({millis:3000,top:'30%',text:"添加迁单成功！"});
					queryAfterSalesByLike(0,10);
					closeModule('changeOrderSale');
				} else if (data.msg == '01'){
					$.alert({millis:3000,top:'30%',text:"添加迁单失败！"});
					closeModule('changeOrderSale');
				} else {
					$.alert({millis:3000,text:data.message});
				}
			}
		});
	}
	
	//根据订单id查询可迁单金额
	function queryChangeFee(orderId){
		$.ajax({
			url: ctx+"/afterSales/queryChangeFee",
			data:{
				orderId:orderId
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg == "00") {
					 $("#c_memberShipFee").html(data.memberShipMoney); 
					 $("#c_salery_novph").html(data.salaryMoney); 
					 $("#c_salery_vph").html(data.vphSumMoney); 
					 //隐藏域赋值
					 $("#h_memberShipFee").val(data.memberShipMoney); 
					 $("#h_salery_novph").val(data.salaryMoney); 
					 $("#h_salery_vph").val(data.vphSumMoney); 
					 
				}
			}
		});
	}
			
	//延续性服务验证
	$('#c_orderForm').bootstrapValidator({
	    message: 'This value is not valid',
	    excluded: ':disabled,:hidden',
	    feedbackIcons: {
	        valid: 'glyphicon glyphicon-ok',
	        invalid: 'glyphicon glyphicon-remove',
	        validating: 'glyphicon glyphicon-refresh'
	    },
	    fields: {         //fields下的是表单name属性
            reason : {
            	validators: {
            		notEmpty: {
	                    message: '必选！'
	                },
            		stringLength: {
	                    min: 0,
	                    max: 200,
	                    message: '原因不超过200字！'
	                },
            	}
            },
	        approveBy: {
	            validators: {
	                notEmpty: {
	                    message: '必选！'
	                },
	            }
	        },
	    }
	}).on('success.form.bv', function(e,data) {
	    // 阻止表单提交【submit】【必填】
	    e.preventDefault();
	    //保存售后单
	    insertOrderSale();
	});
	
	
	//查询审批人
	function queryGuanjiaName(){
		var deptId =  $("#c_ApproveDeptSelect option:selected").val();
		if(deptId==""||deptId==null){
			$("#c_ApproveDeptSelect").empty();
			$("#c_ApproveDeptSelect").html("<option value=''>...请选择...</option>");
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
					$("#c_ApproveBySelect").html(html);
				}
			});
		}
	}
	
	
	/*验证当前订单是否已操作过一次迁单-20180416*/
	function isOrderSale(orderId){
			var result = false;
			if(orderId){
					$.ajax({
						url:ctx+"/afterSales/isOrderSale",
						data : {orderId:orderId},
						type : 'post',
						async : false,
						dataType : "json",
						success : function(data) {
							if(data.msg == "02"){
								result = true;
							}else{
								result = false;
							}
						}
					});
			}
			return result;
	}