/**
 * 用户评价及评论
 * @auth wn
 * @time 2017年7月2日
 */
var ctx=$("#ctx").val();

	/**
	 * 分页跳转使用
	 */
	function listAppraiseReply(num,pageCount) {
		$("#appraisePageInfoDiv").empty();
		var orderId = $('#checkedOrderId').val();
		queryEvaluateBack(orderId,'appraiseReplyServerList',num,pageCount);
	}

    //查询用户评价
	function queryEvaluate(orderId,eleId,num,pageCount){
		var empId = $('#empIdHide'+orderId).val();
		var html1="";
		$("#" +eleId).empty();
		var ctx = $("#ctx").val();
		var cateType = $('#checkedCateType').val();//订单类型
		if(empId != undefined && empId != ""){
			$.ajax({
				url: ctx +"/appraise/queryAppraise?curPage="+num+"&pageCount="+pageCount,
				data:{ 
					ordId:orderId,
					reId:empId,
					isFisrt:2 //评价
				},
				type:"post",
				dataType:"json",
				async:false,
				contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				success:function(data){
					if(data.msg=='00'){
						html1 +="<div class='user-evaluation'>";
						html1 +="<div class='user-evaluation-title clearfix'><h2><a></a>用户评价</h2>";
						html1 +="<div class='click_btn'>";		
						/*html1 +="<input type='button' class='btn btn-primary btn-xs' value='显示' onclick='updateAppraise("+orderId+",appraiseServerList,2,2)'>";		
						html1 +="<input type='button' class='btn btn-primary btn-xs ' value='隐藏' onclick='updateAppraise("+orderId+",appraiseServerList,2,1)'>";		
						html1 +="<input type='button' class='btn btn-primary btn-xs' value='回复' onclick='orderAppraiseReply("+orderId+",2,\"appraiseServerList\");'></div></div>";*/		
						html1 +="<button type='button' style='margin-right:10px;' name='orderButtonsAllOne' class='btn btn-primary btn-xs'  value='显示' onclick='updateAppraise("+orderId+",appraiseServerList,2,2)'>" +
								"<i class='glyphicon glyphicon-pencil' data-toggle='tooltip' data-placement='top' title='显示'>显示</i></button>";
						
						html1 +="<button type='button' style='margin-right:10px;' name='orderButtonsAllOne' class='btn btn-primary btn-xs'  value='隐藏' onclick='updateAppraise("+orderId+",appraiseServerList,2,1)'>" +
						"<i class='glyphicon glyphicon-pencil' data-toggle='tooltip' data-placement='top' title='隐藏'>隐藏</i></button>";
						
						html1 +="<button type='button' name='orderButtonsAllOne' class='btn btn-primary btn-xs'  value='回复' onclick='orderAppraiseReply("+orderId+",2,\"appraiseServerList\");'>"+
							"<i class='glyphicon glyphicon-pencil' data-toggle='tooltip' data-placement='top' title='回复'>回复</i></button></div></div>";
								 
						$.each(data.list,function(i,v){ 
							html1 +="<div class='user-evaluation'>";
							html1 +="<div id='container' class='clearfix'>";		
							html1 +=" <span><input type='checkbox' class='input_check' name='appraiseCheck' id='appraiseCheck"+v.id+"' value='" +v.id +"_"+v.reply.length+"' ><label for='appraiseCheck"+v.id+"'></label></span>";		
							if(v.isSh != 1 && cateType != 1){//
								if(v.reply.length != 0){
									if(v.isShow == 1){//隐藏
										html1 +=" <img class='imgobj' src='"+ctx+"/images/eye_close.png' /> <h3>二次评价<a>（已回复）</a></h3></div>";		
									}else{//显示
										html1 +=" <img class='imgobj' src='"+ctx+"/images/eye_open.png' /> <h3>二次评价<a>（已回复）</a></h3></div>";
									}
								}else{
									if(v.isShow == 1){//隐藏
										html1 +=" <img class='imgobj' src='"+ctx+"/images/eye_close.png' /> <h3>二次评价<a>（未回复）</a></h3></div>";	
									}else{//显示
										html1 +=" <img class='imgobj' src='"+ctx+"/images/eye_open.png'/> <h3>二次评价<a>（未回复）</a></h3></div>";	
									}
								}
							}else{
								if(v.reply.length != 0){
									if(v.isShow == 1){//隐藏
										html1 +=" <img class='imgobj' src='"+ctx+"/images/eye_close.png' /> <h3>初次评价<a>（已回复）</a></h3></div>";		
									}else{//显示
										html1 +=" <img class='imgobj' src='"+ctx+"/images/eye_open.png' /> <h3>初次评价<a>（已回复）</a></h3></div>";		
									}
								}else{
									if(v.isShow == 1){ //隐藏
										html1 +="  <img class='imgobj' src='"+ctx+"/images/eye_close.png'/><h3>初次评价<a>（未回复）</a></h3></div>";		
									}else{//显示
										html1 +=" <img class='imgobj' src='"+ctx+"/images/eye_open.png'/> <h3>初次评价<a>（未回复）</a></h3></div>";		
									}
								}
							}
							if(v.isSh != 1){ 
								html1 +="  <div class='message clearfix'>";		
								html1 +="<p>服务人员<a>（"+(v.reName == undefined)?"未知":v.reName+"）</a></p>";		
								html1 +="<span>评价时间："+v.createTime+"</span></div>";		
								html1 +="<div class='Star-rating_x'>";		
								html1 +="<label><p>准时上户：</p>";		
								html1 +="<input id='input-21e' value='"+v.starLevelOntime+"' type='number' class='rating' min=0 max=5 step=0.5 data-size='xs' ></label>";		
								html1 +="<label><p>服务态度：</p>";		
								html1 +="<input id='input-21e' value='"+v.starLevel+"' type='number' class='rating' min=0 max=5 step=0.5 data-size='xs' ></label>";		
								html1 +="<label><p>专业技能：</p>";		
								html1 +="<input id='input-21e' value='"+v.starLevelSkill+"' type='number' class='rating' min=0 max=5 step=0.5 data-size='xs'></label>";		
								html1 +="</div></div>";
							}else{
								html1 +="  <div class='message clearfix'>";		
								html1 +="<p>服务人员<a>（"+(v.reName == undefined)?"未知":v.reName+"）</a></p>";		
								html1 +="<span>评价时间："+v.createTime+"</span></div>";		
								html1 +="<div class='Star-rating_x'>";		
								html1 +="<label><p>服务质量：</p>";		
								html1 +="<input id='input-21e' value='"+v.starLevelOntime+"' type='number' class='rating' min=0 max=5 step=0.5 data-size='xs' ></label>";		
								html1 +="<label><p>人员态度：</p>";		
								html1 +="<input id='input-21e' value='"+v.starLevel+"' type='number' class='rating' min=0 max=5 step=0.5 data-size='xs' ></label>";		
								html1 +="<label><p>技能掌握：</p>";		
								html1 +="<input id='input-21e' value='"+v.starLevelSkill+"' type='number' class='rating' min=0 max=5 step=0.5 data-size='xs'></label>";		
								html1 +="</div></div>";
							}
							html1 +="<div class='wrap_comment_main clearfix'>";
							html1 +="<p>"+v.ordContent+"</p>";
							//判断图片是否为空
							html1 +="<div class='wrap_video clearfix'>";
							var imgs = v.img;
							if(imgs != null && imgs != ""){
								var eachImg = imgs.split(",");
								for(var j = 0;j<eachImg.length;j++){
									if( j == 0){//第一张图片特殊处理
										html1 +="<div class='Add_to pic_sta'><img src='"+eachImg[j]+"'/></div>";
									}else{
										html1 +="<div class='pic_sta'><img src='"+eachImg[j]+"'/></div>";
									}
								}
							}
							html1 +="</div>";
							var replylist = v.reply;
							//判断是否有回复
							$.each(replylist,function(x,y){ 
								html1 +="<div class='Reply_content clearfix'>";
								html1 +="<div class='main_buttom'>"+y.reContent+"</div>";
								html1 +="<div class='main_buttom'>";
								html1 +="<span class='namede'>回复人："+y.replyUserName+"</span>";
								html1 +="<span class='timeer'>"+y.createTime+"</span>";
								html1 +="</div></div>";
							});
							html1 +="</div>";
							html1 +="<div class='dotted'></div>";
							html1 +="<div class='shadow'>";
							html1 +="<div class='img'></div>";
							html1 +="<div class='swiper-container swiper1'>";
							html1 +="<div class='swiper-wrapper'>";
							html1 +="<div class='swiper-slide'>Slide 1</div>";
							html1 +="<div class='swiper-slide'>Slide 2</div>";
							html1 +="<div class='swiper-slide'>Slide 3</div>";
							html1 +="<div class='swiper-slide'>Slide 4</div> </div>";
							html1 +="<div class='swiper-pagination1' style='bottom:30px;'></div>";
							html1 +="<div class='swiper-button-next'></div>";
							html1 +="<div class='swiper-button-prev'></div></div></div>";
							
						});
						html1 +="</div>";
					}else{
						html1 +="<div class='user-evaluation'>";
						html1 +="<div class='user-evaluation-title clearfix'><h2><a></a>用户评价</h2>";
						html1 +="<p class='table-empty' style='line-height:30px;background:#ddd;'>暂无数据</p>";
						html1 +="</div></div>";
					}
				}
			});
		}else{
			html1 +="<div class='user-evaluation'>";
			html1 +="<div class='user-evaluation-title clearfix'><h2><a></a>用户评价</h2>";
			/*html1 +="<tr><td colspan='17'><p class='table-empty' style='display:block;line-height:30px;background:#ddd;'>暂无数据</p></td></tr>";*/
			html1 +="<p class='table-empty' style='line-height:30px;background:#ddd;'>暂无数据</p>";
			html1 +="</div></div>";
		}
		//将结果放入指定位置
		$("#" +eleId).html(html1);
		$('.wrap_main').height($(window).height());
	
		//点击导航添加样式
	    $('.navp ul li').on('click',function(){
	    	$(this).addClass('add').siblings().removeClass('add');
	    })
	    //星级评分
		 var $input = $('input.rating'), count = Object.keys($input).length;
	        if (count > 0) {
	            $input.rating();
	        }
	     // 点击照片出遮罩及轮播
	        $(document).on('click','#appraiseServerOneList .wrap_video div',function (){
	    	    var _thisIndex = $(this).index(); //点击哪个图片最先显示的就是哪个
	    	    var winH = $(document).height();  
	    	    var len = $(this).parent('.wrap_video').children('.pic_sta');
	    	    var strcontent = '';
	    	    var thatindex = $(this).children().index();
	    	    $('.shadow').height( winH ).show();
	    	    for( var i = 0; i < len.length; i++ ){
	    	        var src = len.eq( i ).children('img').attr('src');
	    	        strcontent += '<div class="swiper-slide"><img src="'+ src +'"/></div>';
	    	    }
	    	    $('.swiper-wrapper').html( strcontent );
	    	    console.log( _thisIndex )
	            var swiper = new Swiper('.swiper1', {
	                pagination: '.swiper-pagination1',
	                nextButton: '.swiper-button-next',
	                prevButton: '.swiper-button-prev',
	                loop: true,
	                initialSlide : _thisIndex
	            });
	    	    //点击遮罩 轮播加遮罩消失
	    	    $('#appraiseServerOneList .img').on('click',function(){
	    	    	$(this).parent('.shadow').hide();
	    	    })    
	    	    $('.shadow2').hide();
	    	});
	    	$('.swiper-container').on('click',function ( event ) {
	            event.stopPropagation();
	        });
	
	}
	//查看用户评论
	function queryEvaluateBack(orderId,eleId,num,pageCount){
		var empId = $('#empIdHide'+orderId).val();
		$("#" +eleId).empty();
		var ctx = $("#ctx").val();
		var html1 = "";
		if(empId != undefined){ 
			$.ajax({
				url: ctx +"/appraise/queryAppraise?curPage="+num+"&pageCount="+pageCount,
				data:{
					ordId:orderId,
					reId:empId,
					isFisrt:1 //评论
				},
				type:"post",
				dataType:"json",
				async:false,
				contentType: "application/x-www-form-urlencoded; charset=utf-8", 
				success:function(data){
					if(eleId == 'appraiseReplyServerList'){
						$("#appraisePageInfoDiv").show();
						$("#appraisePageInfoDiv").pagination(data.page,listAppraiseReply);
					}else{
						$("#appraiseServerOnePageDiv").show();
						$("#appraiseServerOnePageDiv").pagination(data.page,listAppraiseReply);
					}
					var pageCount = data.page.pageCount;
					var curPage = data.page.curPage;
					var total = curPage * pageCount;
					if(data.msg=='00'){
						html1 +="<div class='user-evaluation'>";
						html1+="<div class='user-evaluation-title clearfix'><h2><a></a>用户评论</h2>";
						html1 +="<div class='click_btn'>";		
						html1 +="<button type='button' style='margin-right:10px;' class='btn btn-primary btn-xs' value='显示' onclick='updateAppraise("+orderId+",appraiseReplyServerList,1,2)'>" +
								"<i class='glyphicon glyphicon-pencil' data-toggle='tooltip' data-placement='top' title='显示'>显示</i></button>";		
						html1 +="<button type='button' style='margin-right:10px;' class='btn btn-primary btn-xs' value='隐藏' onclick='updateAppraise("+orderId+",appraiseReplyServerList,1,1)'>" +
								"<i class='glyphicon glyphicon-pencil' data-toggle='tooltip' data-placement='top' title='隐藏'>隐藏</i></button>";		
						html1 +="<button type='button' class='btn btn-primary btn-xs' value='回复' onclick='orderAppraiseReply("+orderId+",1,\"appraiseReplyServerList\");'>" +
								"<i class='glyphicon glyphicon-pencil' data-toggle='tooltip' data-placement='top' title='回复'>回复</i></button></div></div>";		
						var num = $.each(data.list,function(i,v){ 
							num=i+1;
							html1 +="<div id='container' class='clearfix'>";	
							html1 +=" <span><input type='checkbox' class='input_check' name='appraiseReplyCheck' id='appraiseReplyCheck"+v.id+"' value='"+v.id+"_"+v.reply.length+"'><label for='appraiseReplyCheck"+v.id+"'></label></span>";		
							html1 +="<div class='wrap_comment_main clearfix' style='margin-left:6%;'>";
							if(v.isShow == 1){
								html1 +="<img class='imgobj' src='"+ctx+"/images/eye_close.png'/><p>"+v.ordContent+"</p>";
							}else{
								html1 +="<img class='imgobj' src='"+ctx+"/images/eye_open.png'/><p>"+v.ordContent+"</p>";
							}
							//判断图片是否为空
							html1 +="<div class='wrap_video clearfix'>";
							var imgs = v.img;
							if(imgs != null && imgs != ""){
								var eachImg = imgs.split(",");
								for(var j = 0;j<eachImg.length;j++){
									if( j == 0){//第一张图片特殊处理
										html1 +="<div class='Add_to pic_sta'><img src='"+eachImg[j]+"'/></div>";
									}else{
										html1 +="<div class='pic_sta'><img src='"+eachImg[j]+"'/></div>";
									}
								}
							}
							html1 +="</div>";
							var replylist = v.reply;
							$.each(replylist,function(x,y){ 
								html1 +="<div class='Reply_content clearfix'>";
								html1 +="<div class='main_buttom'>"+y.reContent+"</div>";
								html1 +="<div class='main_buttom'>";
								html1 +="<span class='namede'>回复人："+y.replyUserName+"</span>";
								html1 +="<span class='timeer' style='float:right;'>"+y.createTime+"</span>";
								html1 +="</div></div>";
							});
							html1 +="</div></div>";
							
							html1 +="<div class='shadow2'>";
							html1 +="<div class='img'></div>";
							html1 +="<div class='swiper-container swiper2'>";
							html1 +="<div class='swiper-wrapper'>";
							html1 +="<div class='swiper-slide'>Slide 1</div>";
							html1 +="<div class='swiper-slide'>Slide 2</div>";
							html1 +="<div class='swiper-slide'>Slide 3</div>";
							html1 +="<div class='swiper-slide'>Slide 4</div> </div>";
							html1 +="<div class='swiper-pagination2' style='bottom:30px;'></div>";
							html1 +="<div class='swiper-button-next'></div>";
							html1 +="<div class='swiper-button-prev'></div></div></div>";
						});
						html1 +="</div>";
					}else{
						html1 +="<div class='user-evaluation'>";
						html1 +="<div class='user-evaluation-title clearfix'><h2><a></a>用户评论</h2>";
						html1 +="<p class='table-empty' style='line-height:30px;background:#ddd;'>暂无数据</p>";
						html1 +="</div></div>";
						if(eleId == 'appraiseReplyServerList'){ 
							$("#appraisePageInfoDiv").hide();
						}else{
							$("#appraiseServerOnePageDiv").hide();
						}
					}
				}
			});
		}else{
			html1 +="<div class='user-evaluation'>";
			html1 +="<div class='user-evaluation-title clearfix'><h2><a></a>用户评论</h2>";
			html1 +="<p class='table-empty' style='line-height:30px;background:#ddd;'>暂无数据</p>";
			html1 +="</div></div>";
			if(eleId == 'appraiseReplyServerList'){ 
				$("#appraisePageInfoDiv").hide();
			}else{
				$("#appraiseServerOnePageDiv").hide();
			}
		}
		$("#" +eleId).html(html1);
		
		$('.wrap_main').height($(window).height());
		
		//点击导航添加样式
	    $('.navp ul li').on('click',function(){
	    	$(this).addClass('add').siblings().removeClass('add');
	    })
	    //星级评分
		 var $input = $('input.rating'), count = Object.keys($input).length;
	        if (count > 0) {
	            $input.rating();
	        }
	     // 点击照片出遮罩及轮播
	        $(document).on('click','#appraiseReplyServerOneList .wrap_video div',function (){
	    	    var _thisIndex = $(this).index(); //点击哪个图片最先显示的就是哪个
	    	    var winH = $(document).height();  
	    	    var len = $(this).parent('.wrap_video').children('.pic_sta');
	    	    var strcontent = '';
	    	    var thatindex = $(this).children().index();
	    	    $('.shadow2').height( winH ).show();
	    	    for( var i = 0; i < len.length; i++ ){
	    	        var src = len.eq( i ).children('img').attr('src');
	    	        strcontent += '<div class="swiper-slide"><img src="'+ src +'"/></div>';
	    	    }
	    	    $('.swiper-wrapper').html( strcontent );
	    	    console.log( _thisIndex )
	            var swiper = new Swiper('.swiper2', {
	                pagination: '.swiper-paginatio2',
	                nextButton: '.swiper-button-next',
	                prevButton: '.swiper-button-prev',
	                loop: true,
	                initialSlide : _thisIndex
	            });
	    	    $('#appraiseReplyServerOneList .img').on('click',function(){
	    	    	$(this).parent('.shadow2').hide();
	    	    }) 
	    	    $('.shadow').hide();
	    	});
	    	$('.swiper-container').on('click',function ( event ) {
	            event.stopPropagation();
	        });
	    	//点击遮罩 轮播加遮罩消失
	        /*$('.img').on('click',function(){
	        	$(this).parent('.shadow2').hide();
	        })*/
	}

	//显示或隐藏评价或评论
	function updateAppraise(orderId,eleId,type,isShow){
		//type  1 评论  2评价
		//isShow 是否显示 1：否，2：是
		var checkedList = [];
		//var empId = $('#empIdHide').val();
		//获取所有选中ID
		if(type == 1){
			$("input[name='appraiseReplyCheck']:checked").each(function() {
				var value = $(this).val();
				var id = value.split("_")[0];
				checkedList.push(id);
			});
		}else{
			$("input[name='appraiseCheck']:checked").each(function() {
				var value = $(this).val();
				var id = value.split("_")[0];
				checkedList.push(id);
			}); 
		}
		var message = "";
		if(isShow == 1){
			message = "隐藏";
		}else{
			message = "显示";
		}
		if(checkedList != null && checkedList != "" && checkedList.length != 0){
			//新增回复
			$.ajax({
				url : ctx+"/appraise/updateAppraise",
				data : {ids:checkedList.toString(),isShow:isShow},
				type : "POST",
				async : false,
				traditional: true,
				success : function(data) {
					var msg = data.msg;
					if(msg == "00"){
						$.alert({millis:2000,top:'30%',text:message+"成功!！"});
						//根据评价或评论类型刷新页面
						if(type == 1){
							queryEvaluateBack(orderId,'appraiseReplyServerList',0,5);
						}else{
							queryEvaluate(orderId,'appraiseServerList',0,5);
						}
					}else{
						$.alert({millis:2000,top:'30%',text:message+"失败！"});
						if(type == 1){
							queryEvaluateBack(orderId,'appraiseReplyServerList',0,5);
						}else{
							queryEvaluate(orderId,'appraiseServerList',0,5);
						}
					}
				}
			});
		}else{
			$.alert({millis:2000,top:'30%',text:"请选择要显示/隐藏的评价/评论！"});
		}
	}
	//评价、评论回复
	function orderAppraiseReply(orderId,type,eleId){
		var empId = $('#empIdHide').val();
		if(empId == undefined){
			empId = "";
		}
		var checkedList = [];
		var checkReplyList = [];
		//判断选中评价或评论
		if(type == 1){//评论
			$("input[name='appraiseReplyCheck']:checked").each(function() {
				var value = $(this).val();
				var id = value.split("_")[0];
				var num = value.split("_")[1];
				checkedList.push(id);
				checkReplyList.push(num);
			}); 
		}else{//评价
			//判断选中评价或评论是否有回复
			$("input[name='appraiseCheck']:checked").each(function() {
				var value = $(this).val();
				var id = value.split("_")[0];
				var num = value.split("_")[1];
				checkedList.push(id);
				checkReplyList.push(num);
			}); 
		}
		//判断回复个数
		var startFalg = $.inArray("1",checkReplyList);
		var endFalg = $.inArray("0",checkReplyList);
		if(checkedList.length == 0){
			$.alert({millis:2000,text:"请选择要回复的评价或评论!"});
		}else{
			if(startFalg == -1 && endFalg == 0){
				openModule(ctx + "/jsp/appraise/appraiseOrder.jsp",{ids:checkedList.toString(),orderId:orderId,empId:empId,type:type,eleId:eleId},{},{},"appraiseReplyAdd");
			}else if(startFalg >= 0 && endFalg >= -1){
				$.alert({millis:2000,text:"选择的评价或评论已有回复!"});
				return;
			}
		}
	}
	
