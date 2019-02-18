//设置选择城市
function selProvinceCitys(code, length, htmlId) {
	var ctx = $("#ctx").val();
	$.ajax({
		url : ctx + "/threeOrderOut/queryBaseCity",
		data : {
			code : code,
			length : length
		},
		type : 'post',
		async : false,
		success : function(data) {
			var html = "<option style='color:blue;' value=''>...请选择...</option>";
			var obj = JSON.parse(data);
			$.each(obj.list, function(i, v) {
				html += "<option value='" + v.code + "' keyValue='" + v.name + "'>" + v.name + "</option>";
			});
			$("#" + htmlId + "").html(html);
		}
	});
}

