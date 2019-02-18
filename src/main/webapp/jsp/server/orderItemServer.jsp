<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<script src="${ctx}/js/metisMenu.min.js"></script>
<script src="${ctx}/js/jquery/jquery.min.js"></script>
<script src="${ctx}/js/orderBase.js"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	<%
		String userId = request.getParameter("userId");
	%>
</script>
<style type="text/css">
	.vasdivcss{height:25px; background-color:#CFCFCF; border:5px solid #FFFFFF;}
</style>
<script type="text/javascript">
var divNumber=1;
</script>
</head>
<body>
	<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
	<input type="hidden" name="receiverId" id="receiverId" value="<%= userId%>">
	<div style="height:20px;">需求信息</div>
	<div style="height:5px; border-bottom:1px solid #CCC;"></div>
	<div >
		<div style="float:left; width:450px; height:350px; border:1px solid #CCC;">
			<div style="height:5px;"></div>
			<div style="heigth:25px; display:inline;" >
				<div style="heigth:25px; width:160px; float:left; display:inline;" >
			  		分类：一级<select style="width:80px;">
						<option value="1">家政</option>
						<option value="2">健康</option>
						<option value="3">营养</option>
					</select>
				</div>
			  	<div style="width:150px; margin-left:10px; display:inline;" >
			  		二级<select style="width:80px;">
			  			<option value="1">家政</option>
			  			<option value="2">便民</option>
			  			<option value="3">教育</option>
			  		</select>
			  	</div>
			  	<div style="width:150px; margin-left:10px; display:inline;" >
			  		三级<select style="width:80px;" theme="simple" id="serverType">
			  		  <option value="6">月嫂</option>
			          <option value="7">育婴师</option>
			          <option value="2">一般家务</option>
			          <option value="3">老人陪护</option>
			          <option value="4">钟点工</option>
			          <option value="8">医院陪护</option>
			          <option value="5">小时工</option>
			          <option value="9">深度保洁</option>
			          <option value="10">新居开荒</option>
			          <option value="11">床具沙发除螨</option>
			  		</select>
			  	</div>
			</div>
			<div style="height:20px; border-bottom:1px solid #CCC;"></div>
			<div style="height:300px; width:448px; border:1px solid #CCC;">
		  		<jsp:include page="${ctx}/jsp/orderBaiduMap.jsp" />
			</div>
		</div>
		<div style="float:right; height:330px; width:450px; ">
			<div>
				<table style="wdith:98%;">
					<thead style="wdith:100%;">
						<tr>
							<th style="width:20% "></th>
							<th style="width:30% "></th>
							<th style="width:20% "></th>
							<th style="width:30% "></th>
						</tr>
					</thead>
					<tbody style="wdith:100%;">
						<tr>
							<td>面试地址：</td>
							<td colspan="3">
							<input style="width:320px;" id="address" onblur="theLocation()" ></td>
						</tr>
						<tr>
							<td>开始时间：</td>
							<td>
								<input id="startTime" class="Wdate" type="text" style="width: 100px"
                                    onfocus="WdatePicker({minDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd HH:mm'})" />
							</td>
							<td>结束时间：</td>
							<td>
								<input id="endTime" class="Wdate" type="text" style="width: 100px"
                                    onfocus="WdatePicker({minDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd HH:mm'})" />
                            </td>
						</tr>
						<tr>
							<td>面试时间：</td>
							<td>
								<input id="interviewTime" class="Wdate" type="text" style="width: 100px"
                                    onfocus="WdatePicker({minDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd HH:mm'})" />
							</td>
							<td>籍贯要求：</td>
							<td><input style="width:110px;" id="origin" and name="origin"></td>
						</tr>
						<tr>
							<td>学历要求：</td>
							<td>
								<select style="width: 100px; " name="education" id="education" theme="simple" list="">
								</select>
							</td>
							<td>年龄要求：</td>
							<td>
								<input style="width:40px;" id="minAge">
								至<input style="width:40px;" id="maxAge">
							</td>
						</tr>
						<tr>
							<td>居家面积：</td>
							<td><input style="width:110px;" id="homeForests"></td>
							<td>家里人口：</td>
							<td><input style="width:110px;" id="familys"></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:5px;"></div>
			<div style="display:inline;">
				<div style="float:left; ">增加服务项:
					<select id="vasSelect" name="vasSelect" theme="simple" list="">
						<option value="1">双胞胎</option>
			  			<option value="2">早产儿</option>
			  			<option value="3">其他</option>
					</select>
					<input type="button" value="+" onclick="addVasDiv()">
				</div>
				<div>&nbsp;&nbsp;预产期：
					<input style="width:110px;">
				</div>
				<div style="height:5px;"></div>
			</div>
			<div style="overflow:auto; height:130px; border:1px solid #CCC; " id="vasDivpa">
<!-- 				<div class="vasdiv" id="vasdiv00"> -->
<!-- 					<div style="display:inline;">类型</div> -->
<!-- 					<div style="display:inline;">金额</div> -->
<!-- 					<div style="display:inline;">操作 -->
<!-- 					</div> -->
<!-- 				</div> -->
			</div>
			<div style="text-align:center">
				备注：<textarea id="remark" name="remark" style="width:350px; height:35px;" ></textarea>
			</div>
		</div>
	</div>      
	<script type="text/javascript">
	
	</script>
</body>
<script type="text/javascript">

//确定\保存按钮方法
function makeSure(){
	//var options=$("#education option:selected");  //获取选中的项
	var ctx = $("#ctx").val();
	var ctx = "/order" ;
	
	var address =$("#address").val();
	var receiverId=$("#receiverId").val();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	var interviewTime=$("#interviewTime").val();
	var origin=$("#origin").val();
	var education=$("#education option:selected").val();
	
	var minAge=$("#minAge").val();
	var maxAge=$("#maxAge").val();
	var homeForests=$("#homeForests").val();
	var familys=$("#familys").val();
	var remark=$("#remark").val();
	var serverType=$("#serverType option:selected").val();
	var serverText=$("#serverType option:selected").text();

	$.ajax({
		url: ctx +"/itemDetailServer/insertItemDetailServer",
		data:{
			address:address,
			receiverId:receiverId,
			startTime : startTime,
			endTime : endTime,
			interviewTime : interviewTime,
			origin : origin,
			education : education,
			minAge : minAge,
			maxAge : maxAge,
			homeForests : homeForests,
			familys : familys,
			remark:remark ,
			serverType:serverType,
			productName:serverText
		},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			if(data.msg=='00'){
				//alert("保存成功");
				// 刷新父面订单列表方法
				parent.reloadParent();
				parent.document.getElementById("cancel").click();
			}else{
				alert("保存失败");
				return ;
			}
		}
	});
}

// 新增div
function addVasDiv(){
	var oDiv = document.createElement("div");
	var html = "<div class='vasdivcss' name='vasdiv' id='vasdiv" +divNumber +"'>" ;
		html += "<div style='display:none;'>" +$("#vasSelect option:selected").val() +"</div>" ;
		html += "<div style='width:80px; display:inline;'>" +$("#vasSelect option:selected").text() +"</div>" ;
		html += "<div style='width:80px; display:inline;'>" +100 +"</div>" ;
		html += "<div style='width:80px; display:inline;' >" +
			'<a href="#" onclick="' +"deleteVasDiv('vasdiv" +divNumber +"')" +'" > 删除</a></div>' ;
		html += "</div>";
	oDiv.innerHTML = html ;
	divNumber ++;
	document.getElementById("vasDivpa").appendChild(oDiv);
}

function getVasValue(){
	var options=$("#vasSelect option:selected");
	return options.val();
}
// 删除div
function deleteVasDiv(divName){
   var oDiv = document.getElementById(divName);
   if (oDiv != null){
	   oDiv.parentNode.removeChild(oDiv);
	    //document.getElementById("vasDivpa").removeChild(oDiv); 
   }
}

function cityToPoint(cityName){
	alert(cityName);
}

// 日期控件
// <input id="receiptTime" class="Wdate" type="text" style="width: 100px"
//    onfocus="WdatePicker({minDate:'%y-%M-{%d+1}',dateFmt:'yyyy-MM-dd'})" />
</script>
</html>

