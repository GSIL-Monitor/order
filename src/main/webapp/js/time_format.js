
//设置页面一打开文本框就默认显示当前日期
//使用方法，即给时间输入框赋值，$("#id").val(new Date().format("yyyy-MM-dd hh:mm:ss"))
Date.prototype.format = function(format) {
	var args = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter

		"S" : this.getMilliseconds()
	};
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var i in args) {
		var n = args[i];
		if (new RegExp("(" + i + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n
					: ("00" + n).substr(("" + n).length));
	}
	return format;
};



/**
 * 转换时间格式 判断月、日、时、分、秒是否小于10 如果小于 填0 如果大于 不变 *注：月从0开始计算所以加1
 * 
 * @author Liucx
 * @param date
 * @returnFormat YYYY-MM-DD HH:mm:ss
 * @returns {String}
 */
function FormatDate(date) {
	var date = new Date(date.time);
	Y = date.getFullYear() + '-';
	M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date
			.getMonth() + 1)
			+ '-';
	D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + ' ';
	h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours());
	m = ':' + (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes());
	s = ':' + (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds());
	return Y + M + D + h + m ;
}