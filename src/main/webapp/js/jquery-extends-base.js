/* 
 * jquery扩展
 * author: ChengJing
 * date:2016-7-14 
 * */
jQuery.extend({
	ShowDialog: function(msg, callback) {
		var option = {};
		option["text"] = msg;
		option["callback"] = callback;
		var ops = {
				title:"警告框",
				buttons:[{
							name:'确定',
							handler:function(){
								if(option && option.callback && (typeof option.callback == 'function')){
									option.callback(true);
								}
								return true;
							}
						}]
		};
		$.extend(ops,option);
		$.message(ops);
	},
	/**
	 * 参数:
	 * option:{
	 * 		millis:毫秒数 大于0就会在此时间内自动关闭次框 默认-1
	 * 		title:标题名 默认为 "警告框"
	 * 		text: 内容
	 * 		top:  距离上部距离
	 * 		width: 宽度 默认的 "300px",
	 *      callback： 点击 ‘是’ 或者 ‘否’ 按钮后的回调函数  并传递参数  点是传递 true  点否 传递false
	 * }
	 */
	ShowConfirm: function(title, text, callback) {
		var option = {};
		option["title"] = title;
		option["text"] = text;
		option["callback"] = callback;
		$.confirm(option)
	},
	/** 
	 * 获取字符串
	 * text: 字符串
	 * replace: 代替字符串
	 */
	GetString: function (text, replace) {
		if (!text) {
			text = replace;
		}
		return text;
	},
	/**
	 * 获取文件的后缀
	 */
	GetFileExtension: function (fileName) {
		return fileName.replace(/.*\.(.*)/, "$1");
	}
});

jQuery.fn.extend({
	/* 添加回车键触发器 */
	AddEnterKeyListener : function (func) {
		$(this).keydown(function(e){
			console.log(e.keyCode);
			if (e && e.keyCode === 13) { // 13表示回车键
				if(typeof func === "function") func();
			}
		});
	},
	/* 添加表格复选框监听器 */
	AddTableCheckBoxListener: function () {
		var $table = $(this);
		var $topcheckbox = $table.find(":checkbox#all"); // 全选复选框
		var $checkboxes = $table.find(":checkbox:not(#all)"); // 单个复选框
		$topcheckbox.click(function(){
			var $this = $(this);
			$checkboxes.prop('checked', $this.prop("checked")); // 已经包含遍历处理
		});
		$checkboxes.click(function(){
			var $this = $(this);
			if($this.is(":checked")) { // 选中事件，则修改为选中状态，并判断是否该页面所有复选框是否已被选中，如果选中，则将全选复选框置为选中状态，否则不处理
				$topcheckbox.prop("checked", $table.find(":checkbox:not(#all):checked").length == $checkboxes.length); 
			} else { // 取消选中，则取消全选复选框选中状态
				$topcheckbox.prop("checked", false); // 取消全选复选框选中状态 
			}
		});
	},
	
	/* 获得复选框中选中的值 
	 * 由table调用，且checkbox在tbody中
	 * */
	GetCheckboxCheckedValue: function () {
		var $table = $(this);
		var ids = new Array();
		$table.find("tbody :checked").each(function(){ // 遍历选中的复选框
			var $this = $(this);
			if($this.val()) ids.push($this.val());
		});
		return ids;
	},
	/*
	 * 获得tr对象
	 */
	GetTrObject : function (value) {
		return $(this).find("input:checkbox[value="+value+"]").parents("tr");
	},
	/*
	 * 通过name属性获得td的值
	 */
	GetTDValueByName : function (name) {
		return $(this).find("td[name="+name+"]").html();
	},
	/*
	 * 通过name属性获得td的属性值
	 */
	GetTDPropByName : function (name, prop) {
		return $(this).find("td[name="+name+"]").attr(prop);
	},
	/*
	 * 获得第index位置的值
	 */
	GetTDValueByIndex : function (index) {
		return $(this).find("td:eq("+index+1+")").html(); // +1是为了处理复选框占用的一个td
	}
});

