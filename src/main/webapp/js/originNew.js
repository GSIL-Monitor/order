    /**查询城市
	 *@param id
	 *@param levels
	 *@param code 
	 * */
	function queryCitys(baseCity,tagId){
		var html = "<option value='' >--请选择--</option>";
		var settings = {"levels":1}
		jQuery.extend(settings, baseCity);
		if(settings.levels == 1 || (settings.levels != 1 && settings.code)){
			$.ajax({
				url : ctx+"/itemDetailServer/queryCitys",
				data : settings,
				type : "POST",
				async : false,
				success : function(data) {
					if(data.msg == "00"){
						$.each(data.list,function(i,v){
							html += "<option value='"+v.code+"' data-name='"+v.name+"' data-id='"+v.id+"' data-pid='"+v.pid+"'>"+v.name+"</option>";
						});
					}
				}
			});
		}
		$("#"+tagId).empty().html(html).trigger("change");
	} 
	
	//查询服务人员级别
	function selectPersonnelLevel(){
		$.ajax({
			url:ctx+"/itemDetailServer/selectPersonnelLevel",
			type:"post",
			datetype:"json",
			async : false,
			success : function(data){
				if (data.msg == "00") {
					var option = "<option value=''>...请选择...</option>";
					$.each(data.list,function(i,v){
						option+="<option value='"+v.id+"'>"+v.levelName+"</option>";
					});
					$("#personelLevel").html(option);
				}
			}
		});
	}
	
