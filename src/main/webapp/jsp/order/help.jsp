<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>帮助</title>
</head>
<body>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<!-- <button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button> -->
					<h4 class="modal-title" id="myModalLabel" align="center">帮助</h4>
				</div>
				<div class="modal-body">
					<div class="container">
						<img src="/order/images/batch_import_order.jpg" alt="提示" >
						<ul class="list-group">
						<li class="list-group-item active">以上填写内容如下：</li>
						<li class="list-group-item">①、平台订单号</li>
						<li class="list-group-item">②、客户手机号（要求11位手机号或座机，空视为不合格）</li>
						<li class="list-group-item">③、客户姓名（请如实填写直系亲属姓名）</li>
						<li class="list-group-item">④、区县</li>
						<li class="list-group-item">⑤、区县code</li>
						<li class="list-group-item">⑥、详细地址</li>
						<li class="list-group-item">⑦、订单渠道</li>
						<!-- <li class="list-group-item">8、乙方服务站（门店名称）</li> -->
						<li class="list-group-item">⑧、订单渠道code</li>
						<li class="list-group-item">⑨、商品名</li>
						<li class="list-group-item">⑩、商品名code</li>
						<li class="list-group-item">⑪、开始时间(选填)</li>
						<li class="list-group-item">⑫、结束时间(选填)</li>
						<li class="list-group-item">⑬、总数量(选填)</li>
						<!-- <li class="list-group-item">15、是否代收（代收有预付日期，非代收无预付日期）</li> -->
						<li class="list-group-item">⑭、服务人数(选填)</li>
						<li class="list-group-item">⑮、备注</li>
						<li class="list-group-item">⑯、录入人erp账号</li>
						<li class="list-group-item">⑰、负责人erp账号 </li>
						</ul>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$('#myModal').on('hide.bs.modal', function () {
		})
	</script>
</body>
</html>