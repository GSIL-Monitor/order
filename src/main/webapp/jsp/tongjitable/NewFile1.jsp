<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="auth" uri="/emotte-auth" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
	<script src="${ctx}/js/orderBase.js"></script>
<%-- 	<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script> --%>
	<style>
		.orderlisttable tr td{text-align: left}
		.feesOnediv{margin:20px auto auto 15px}
		
	</style>
</head>
<body>
<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
<!-- /.col-lg-12 -->
    <div class="row">
        <div class="col-lg-12">
            <ol class="page-header breadcrumb">
                <li><a href="#">Home</a></li>
                <li><a href="#">订单管理系统</a></li>
                <li><a href="#">订单管理</a></li>
                <li class="active">订单管理</li>
            </ol>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
        <div class="row">
               <div class="col-lg-12">
        					 <tbody>
                            <!--列表开始-->
                            <div class="panel-content table-responsive">
                                <table class="table table-hover table-striped" style="width:1300px;">
                                    <thead>
                                    <tr>
										<th width="5%">序号</th>
										<th width="8%">订单号</th>
										<th width="5%">收货人姓名</th>
										<th width="7%">收货人电话</th>
										<th width="7%">下单电话</th>
										<th width="10%">收货地址</th>
										<th width="8%">下单日期</th>
										<th width="8%">配送日期</th>
										<th width="8%">商品名称</th>
										<th width="8%">数量</th>
										<th width="8%">规格</th>
										<th width="8%">订单渠道</th>
										<th width="8%">下单部门</th>
										<th width="8%">订单来源</th>
										<th width="8%">用户备注</th>
                                    </tr>
                                    </thead>
                                    <tbody id="listBodyOrder" style="width:100%">
                                    </tbody>
                                </table>
                            </div>
                            </tbody>
              </div>
       </div>
</body>
<script type="text/javascript">
//点击选中一行
// function updateColorOrder(value){
// 	$("#listBodyOrder > tr").css("background","#FFFFFF");
// 	$("#listBodyOrder tr:eq("+value+")").css("background","#C0C0C0");
// }

/* //全选
function checkboxAll(e){
	$("input[name='odId']:enabled").prop("checked", document.getElementById("odIds").checked==true);
} */
// 复选框取值
// function checkboxVale(){
// 	  var ids=''; 
// 	  $('input[name="odId"]:checked').each(function(){ 
// 		  ids += $(this).val() + ','; 
// 	  });
// 	  ids = ids.substring(0, ids.length-1);
// 	  return ids;
// }

//ready方法
$(function() { 
	queryItemList(0, 10);
	
})

function queryItemList(num,pageCount){
	var ctx = $("#ctx").val();
	$.ajax({
		url: ctx +"/itemDetailFa/queryItemList?curPage="+num+"&pageCount="+pageCount,
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			showMsg(data.msg);
			var pageCount = data.page.pageCount;
			var curPage = data.page.curPage;
			var total = curPage * pageCount;
			var html="";
			if (data.msg =="00") {
				var num = 
					$.each(data.list,function(i,v){
						num=i+1;
						html +="<tr >" ;
						html +="<td>"+(total + num - pageCount);
						html +="</td><td align='left'>"+v.ordercode;
						html +="</td><td align='left'>"+v.receivername;
						html +="</td><td>"+v.receivermobile;
						html +="</td><td>"+v.usermobile;
						html +="</td><td>"+v.useraddress; 
						html +="</td><td>"+v.createtime;
						html +="</td><td align='left'>"+v.receipttime;
						html +="</td><td align='left'>"+v.productspec;
						html +="</td><td align='left'>"+v.quantity;
						html +="</td><td align='left'>"+v.productspec;
						html +="</td><td align='left'>"+v.orderchannel;
						html +="</td><td align='left'>"+v.name;
						html +="</td><td align='left'>"+v.ordersourceid;
						html +="</td><td align='left'>"+v.remark;
						html +="</td></tr>"; 
					})
			}else{
				html="<tr><td colspan='17'><p class='table-empty'>暂无数据</p></td></tr>";
		   }
			$("#listBodyOrder").html(html);
			if (data.msg =="00") { 
				trColorOrder("#listBodyOrder tr");
				var orderId = $("#checkedOrderId").val();
				checkOrderBasic(orderId);
				$("#listBodyOrder > tr").eq(0).addClass('info').siblings().removeClass('info').css('cursor', 'pointer');
			}
		}
	});
}



</script>
</html>

