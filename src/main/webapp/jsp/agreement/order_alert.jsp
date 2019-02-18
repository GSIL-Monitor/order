<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>帮助</title>
</head>
<body>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
<!-- 					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
<!-- 					<h4 class="modal-title" id="myModalLabel">帮助</h4> -->
				</div>
				<div class="modal-body">
					<div class="container">
						<img src="/order/images/order_add_alert.png" alt="提示" >
						<ul class="list-group">
						<li class="list-group-item active">以上填写内容如下：</li>
						<li class="list-group-item">1、甲方名称（带有先生、女士、小姐、老师等字样的视为不合格）”要求和身份证姓名一致</li>
						<li class="list-group-item">2、甲方联系方式（要求11位手机号或座机，空视为不合格）</li>
						<li class="list-group-item">3、甲方紧急联系人姓名（请如实填写直系亲属姓名）</li>
						<li class="list-group-item">4、甲方紧急联系人电话（请如实填写直系亲属电话）</li>
						<li class="list-group-item">5、甲方证件类型（选择身份证、或护照、驾照等其他证件）</li>
						<li class="list-group-item">6、甲方身份证号码（要求18位，与身份证原件一致，非身份证与证件原件号码一致）</li>
						<li class="list-group-item">7、甲方地址（请填写真实详细地址，精确到街道或小区）</li>
						<!-- <li class="list-group-item">8、乙方服务站（门店名称）</li> -->
						<li class="list-group-item">8、乙方服务地址（请填写真实详细地址，请精确）</li>
						<li class="list-group-item">9、丙方服务员名称（带有先生、女士、小姐、老师等字样的视为不合格）”要求和身份证姓名一致</li>
						<li class="list-group-item">10、丙方联系方式（要求11位手机号，空视为不合格）</li>
						<li class="list-group-item">11、丙方证件类型（选择身份证）</li>
						<li class="list-group-item">12、丙方身份证号码（要求18位，与身份证原件一致）</li>
						<li class="list-group-item">13、丙方地址（请填写真实详细地址，精确到街道或小区）</li>
						<!-- <li class="list-group-item">15、是否代收（代收有预付日期，非代收无预付日期）</li> -->
						<li class="list-group-item">14、服务场所（请填写真实详细地址，精确到街道或小区）</li>
						<li class="list-group-item">15、合同期限（开始日期与签订日期要求一致）</li>
						<li class="list-group-item">16、合同期限（月嫂必须大于26天；其余延续性应该是一年）</li>
						<li class="list-group-item">17、服务人员服务费（按实际情况填写） </li>
						<li class="list-group-item">18、服务人员法定假日加班费，是日劳务费的几倍（按实际情况填写）</li>
						<li class="list-group-item">19、服务人员信息费（按实际情况填写）</li>
						<li class="list-group-item">20、客户信息（一次）（指客户交给管家帮的服务费，与实际一致，月嫂服务人员服务费全额填写在服务员服务费列，不要全额填写在信息费中）</li>
						<li class="list-group-item">21、本次支付合计（按实际情况填写）（可选填）</li>
						<li class="list-group-item">22、预付方式（按实际情况选填）</li>
						<li class="list-group-item">23、甲方预支付时间（按实际情况选填）</li>
						<li class="list-group-item">24、预支付日期（按实际情况选填）</li>
						<li class="list-group-item">25、其他服务（选填）</li>
						<li class="list-group-item">26、其他约定（选填）</li>
						</ul>
					</div>
				</div>
				<div class="modal-footer">
					<!-- <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button> -->
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