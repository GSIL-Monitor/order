 ( function ( $ ) {

	jQuery.fn.defaults = {
		mode : 'left',		//	轮播滚动方向
		speed : 500,		//	轮播动画执行速度
		isshownav : true,	//	是否显示轮播导航
		isshowbtn : true,	//	是否显示轮播两侧按钮
		eventnav : 'click',	//	轮播导航响应事件
		mintimer : 2000		//	图片滚动间隔时间
	};
	jQuery.fn.rollmodule = function( options ){
		var options    = $.extend( {},jQuery.fn.defaults,options );
		var that       = $(this);
		var thatwidth  = that.find('ul li').width();
		var thatheight = that.find('ul li').height();
		var thatsize   = that.find('ul li').size();
		var navstr     = '';
		var index      = 0;
		var ifmode = options.mode == 'left';
		var timer;

		if( ifmode ){
			that.children('ul').css({'width':thatwidth * ( thatsize + 1 ) + 'px'});
		}
		that.children('ul').append( that.find('ul li:first').clone() );

		//	初始化轮播导航按钮
		if( options.isshownav == true ){
			for( var i = 0; i < thatsize; i++ ){
				( i == 0 ) ? navstr += '<li class="hover"></li>' : navstr += '<li></li>';
			}
			that.append( '<ol>'+ navstr +'</ol>' );
		}

		//	是否显示两侧导航按钮
		if( options.isshowbtn == false ){
			$(this).children('div').hide();
		}

		that.hover( function () {
			clearInterval( timer );
		},function(){
			timer = setInterval( function () {
				index++
				_playAnimate( index );
			},options.mintimer)
		}).mouseout();

		//	导航按钮响应事件
		that.find('ol li').on(options.eventnav,function () {
			index = $(this).index();
			_playAnimate( index );
		})

		//	点击右侧按钮
		that.children('.right_btn').click( function () {
			index++;
			_playAnimate( index );
		})

		//	点击左侧按钮
		var modeleft = {'left':-thatwidth * thatsize + 'px'};
		var modetop = {'top':-thatheight * thatsize + 'px'};
		that.children('.left_btn').click( function () {
			index--;
			if( index < 0 ){
				index = thatsize-1;
				that.children('ul').css(( ifmode ) ? modeleft : modetop,options.speed);
			}
			_playAnimate( index );
		})

		//	动画函数
		function _playAnimate( num ){
			if( index == thatsize ){
				index = num = 0;
				that.children('ul').append( that.find('ul li:first').clone() );
				that.children('ul').animate(( ifmode ) ? modeleft : modetop,options.speed,function () {
					that.children('ul').css(( ifmode ) ? {'left':'0px'} : {'top':'0px'});
					that.find('ul li:last').remove();
				})
			}
			if( ifmode ){
				that.children('ul').animate({'left': -num * thatwidth + 'px'},options.speed);
			}else{
				that.children('ul').animate({'top': -num * thatheight + 'px'},options.speed);
			}
			that.find('ol li').eq( num ).addClass('hover').siblings().removeClass('hover');
		}

	}

})( jQuery )