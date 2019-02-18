<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<style type="text/css">
		.modal-user-account {height: 220px; width: 400px; margin: 30px auto;}
	</style>
	<script type="text/javascript">
		<%
		String orderId = request.getParameter("orderId");
		String accountsId = request.getParameter("accountsId");
		String userId = request.getParameter("userId");
		%>
	</script>
</head>
<body>
	<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
	<div class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title">导入订单记录</h4>
				</div>
				<div class="modal-body">
					<div class="info clearfix">
						<form class="form-inline">
							 <div class="row">
			                         <div class="form-group col-xs-11">
			                            <label>
			                                <p>时间</p>
			                             <input id="recordStartTime" class="Wdate  form-control" type="text"
                                    onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'createTimeEnd\')||\'%y-%M-{%d}\'}' ,dateFmt:'yyyy-MM-dd'})" />
                                            &nbsp;至&nbsp;
                                            </label>
                                             <label >
                                    <input id="recordEndTime" class="Wdate form-control" type="text"
                                    onfocus="WdatePicker({minDate:'#F{$dp.$D(\'createTimeOrder\')}',maxDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" />
			                            </label>
			                        </div>
			                        <div class="form-group col-xs-1">
			                         	<button class="btn btn-sm btn-default fr mr20"  onclick="queryThreeOrderRecord(0,10)" type="button">
										<em class="glyphicon glyphicon-search"></em>
									查询
									</button>
			                         </div>
			                  </div>
						</form>
						
						<div class="panel-content table-responsive">
                                <table class="table table-hover" style="width:100%">
                                    <thead>
                                    <tr>
										<th width="40%">文件名</th>
										<th width="30%">上传时间</th>
										<th width="30%">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="threeOrderRecordList" style="width:100%">
                                    </tbody>
                                </table>
                           </div>
                            <div class="pagination fr">
				            	<ul id="TOI_pageRecordDiv"></ul>
				            </div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(function() {
		queryThreeOrderRecord(0,10,1);
	});
	
	function threeOrderRecordList(num,pageCount) {
		$("#threeOrderRecordList").empty();
		queryThreeOrderRecord(num,pageCount,1);
	}
	/**
	 * 分页跳转使用
	 */
	function toPage(num) {
		if (null == num || "" == num || isNaN(parseInt(num))) {
			$.alert({text:"请输入数字。"});
			return;
		}
		var pageCount = $("#pageCount").val();
		if (pageCount == "" || pageCount == undefined) {
			pageCount = 3;
		}
		$("#threeOrderRecordList").empty();
		queryThreeOrderRecord(num, pageCount);
	}
	
	
	function queryThreeOrderRecord(num,pageCount){
		var ctx = $("#ctx").val();
		var recordStartTime  = $('#recordStartTime').val();
		var recordEndTime = $('#recordEndTime').val();
		$.ajax({
			url:ctx+"/threeOrderIn/queryThreeOrderRecord?curPage="+num+"&pageCount="+pageCount,
			data:{
				recordStartTime:recordStartTime,
				recordEndTime:recordEndTime
			},
			type:"post",
			dataType:"json",
			success:function(data){
				$("#TOI_pageRecordDiv").show();
	 			$("#TOI_pageRecordDiv").pagination(data.page,threeOrderRecordList);
				var pageCount = data.page.pageCount;
				var curPage = data.page.curPage;
				var total = curPage * pageCount;
				var html="";
				if (data.msg =="00") {
					$.each(data.list,function(i,v){
						html +="<tr>";
						html +="<td>"+v.fileName+"</td>";
						html +="<td>"+v.createTime+"</td>";
						html +="<td><a href='"+v.filePath+"'>下载</td>";
						html +="</tr>";
					})
				}else if(data.msg =="02"){
					html += "<tr><td colspan='3'>暂无记录</td></tr>"
				}
				$("#threeOrderRecordList").html(html);
			}
		})
	}
	</script>
</body>
</html>