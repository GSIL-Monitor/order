<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看合同扫描件</title>
</head>
<body>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">查看合同扫描件</h4>
				</div>
				<div class="modal-body">
					<!-- <div id="selectAttachment" ></div> -->
					<div id="myCarousel" class="carousel slide">
						<!-- 轮播（Carousel）指标 -->
						<ol class="carousel-indicators" id="carouselIndicators">
							<!-- <li data-target="#myCarousel" data-slide-to="0" class="active"></li>-->
						</ol>
						<!-- 轮播（Carousel）项目 -->
						<div class="carousel-inner" id="carouselInner">
							<!-- <div class="item active">
								<img
									src="http://erp.95081.com/img/img/2017/05/16/5cbc8968c40d4dd2bfe5bc44e189920e.jpg"
									alt="第1张">
							</div>-->
						</div>
						<!-- 轮播（Carousel）导航 -->
						<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a> 
						<a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
					</div>
					
					<!-- 控制按钮 -->
					<br/>
						<div style="text-align:center;">
							<button type="button"  class="btn prev-slide"><em class="glyphicon glyphicon-chevron-left"></em></button>
							<button type="button"  class="btn next-slide"><em class="glyphicon glyphicon-chevron-right"></em></button>
							<button type="button"  class="btn download-slide"><em class="glyphicon glyphicon-download-alt"></em></button>
						</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<script>
	$(function(){
		// 循环轮播到上一个项目
		$(".prev-slide").click(function(){
			$("#myCarousel").carousel('prev');
		});
		// 循环轮播到下一个项目
		$(".next-slide").click(function(){
			$("#myCarousel").carousel('next');
		});
		// 下载当前项目
		$(".download-slide").click(function(){
			var url = $(".carousel-inner>.item.active>img").prop("src");
			tranUrl = encodeURIComponent(url.substring(url.indexOf('i'),url.length));
			window.open("http://erp.95081.com/img_service/file_download?url="+tranUrl);
		});
		queryAgreementFile()
		
	});
	
	/*查询列表*/
	function queryAgreementFile(){
		var ctx = $("#ctx").val();
		var agreementId = ${param.agreementId};
		$.ajax({
			url:ctx+"/agreementFile/queryAgreementFile",
	        data:{"agreementId":agreementId},
	        type:'post',
	        async:false,
	        dataType:"json",
	        success:function(data){
	        	if(data.msg == "00"){
	        		$.each(data.list,function(j,v){
        					if(j == 0){
        						$("#carouselIndicators").append("<li data-target='#myCarousel' data-slide-to='"+v.rankNumber+"' class='active'></li>");
        						$("#carouselInner").append("<div class='item active'><img src='"+v.url+"' alt='"+v.rankNumber+"'></div>")
        					}else{
        						$("#carouselIndicators").append("<li data-target='#myCarousel' data-slide-to='"+v.rankNumber+"'></li>");
        						$("#carouselInner").append("<div class='item'><img src='"+v.url+"' alt='"+v.rankNumber+"'></div>")
        					}
        			})
	        	}
	        }
	    })
	}
</script>
</body>
</html>