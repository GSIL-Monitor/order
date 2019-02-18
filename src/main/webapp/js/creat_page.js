function createPageDiv(page){
	var html="<input type=\"hidden\" id=\"totalRecord\" value='"+page.totalRecord+"'/><input type=\"hidden\" id=\"curPage\" value='"+page.curPage+"'><input type=\"hidden\" id=\"maxpage\" value='"+page.maxPage+"'/>";
	if(page.curPage<=1){
		html+="<span class='disabled'>上一页</span>";
	}else{
		html+="<a href=\"javascript:void(0)\" onclick=\"toPage("+(page.curPage-1)+")\" >上一页</a>";
	}
	if(page.curPage>4){
		html+="<a href=\"javascript:void(0)\" onclick=\"toPage(1)\">1</a>...";
	} 
	if(page.curPage<=4){
		for ( var int = 1; int <= (page.maxPage>5?5:page.maxPage); int++) {
			if(int==page.curPage){
				html+="<span class=\"current\">"+int+"</span>";
			}else{
				html+="<a href=\"javascript:void(0)\" onclick=\"toPage("+int+")\">"+int+"</a>";
			}
		}
	}else if(page.curPage>4 && (page.curPage+5)<= page.maxPage){
		var max = ((page.curPage+4)>page.maxPage?(page.maxPage-1):(page.curPage +4));
		for ( var i = (page.curPage-2); i < max ; i++) {
			if(i == page.curPage){
				html+="<span class=\"current\">"+i+"</span>";
			}else{
				html+="<a href=\"javascript:void(0)\" onclick=\"toPage("+i+")\">"+i+"</a>";
			}
		}
	}else {
		for ( var int = (page.maxPage-6); int <= page.maxPage; int++) {
			if(int==page.curPage){
				html+="<span class=\"current\">"+int+"</span>";
			}else if(int>1){
				if(!(int == page.maxPage && page.curPage < page.maxPage-1) ){
					html+="<a href=\"javascript:void(0)\" onclick=\"toPage("+int+")\">"+int+"</a>";
				}
			}
		}
	}
	if(page.curPage<=page.maxPage-2 &&page.maxPage>5){
		if(page.curPage<=page.maxPage-5){
			html+="...<a href=\"Javascript:void(0);\" onclick=\"toPage("+
			page.maxPage+")\">"+page.maxPage+"</a>";
		}else{
			html+="<a href=\"Javascript:void(0);\" onclick=\"toPage("+
			page.maxPage+")\">"+page.maxPage+"</a>";
		}
		
	}
	if(page.curPage>=page.maxPage){
		html+="<span class=\"disabled\">下一页</span>";
	}
	if(page.curPage<page.maxPage){
		html+="<a href=\"javascript:void(0)\" onclick=\"toPage("+(page.curPage+1)+")\">下一页</a>";
	}
	//定义下拉框
	var _sel="";
	if (page.pageCount==-1) {
		_sel="selected";
	}
	var opt="<option value='1' "+_sel+">1</option>";
	for (var int2 = 5; int2 <= 50; int2+=5) {
		var sel="";
		if(page.pageCount==int2){
			sel="selected";
		}
		opt+="<option value='"+int2+"' "+sel+">"+int2+"</option>"
	}
	if (page.pageCount==-1) {
		_sel="selected";
	}
	opt+="<option value='-1' "+_sel+">all</option>";
	html+=" 每页<select id=\"pageCount\" value='"+ page.pageCount+"' onchange='toPage(1,this.value)'>"+opt+"</select>条&nbsp&nbsp";
	html+="共:<font color=\"red\">" + page.totalRecord + "</font>&nbsp;条";
	html+="<span style=\"font-size:12px;\"> 跳到<input id=\"inputToPage\" type=\"text\"  style=\"width:25px; height:20px; margin:0px 5px;\" maxlength=\"4\" value='"+page.curPage+"' onblur='toBlur(this.value," + page.maxPage + ")'/>页</span><span style=\"margin-right:3px;\"><a style='float: none;color: blue'   href=\"javascript:void(0)\" onclick=\"toPage(document.getElementById('inputToPage').value)\">GO</a></span>";
	return html;
}
function toBlur(vars,maxp){
	  var  inputToPage= document.getElementById("inputToPage");
	 var reg = new RegExp("^[0-9]*$");
	if(!reg.test(vars)){
      alert("请输入正确的页码数!");
      inputToPage.value=""; 
 }else if (vars>maxp) {
	   alert("输入页码不在查询范围内!");
	   inputToPage.value="";
 }else if(vars<1){
	   alert("输入页码不在查询范围内!");
	   inputToPage.value="";
 }	
}

//档案借阅表  选择借阅档案时的分页信息 
function createPageDiv1(page){
	var html="<input type=\"hidden\" id=\"totalRecord\" value='"+page.totalRecord+"'/><input type=\"hidden\" id=\"curPage\" value='"+page.curPage+"'><input type=\"hidden\" id=\"maxpage\" value='"+page.maxPage+"'/>";
	if(page.curPage<=1){
		html+="<span class='disabled'>上一页</span>";
	}else{
		html+="<a href=\"javascript:void(0)\" onclick=\"toPage1("+(page.curPage-1)+")\" >上一页</a>";
	}
	if(page.curPage>4){
		html+="<a href=\"javascript:void(0)\" onclick=\"toPage1(1)\">1</a>...";
	} 
	if(page.curPage<=4){
		for ( var int = 1; int <= (page.maxPage>5?5:page.maxPage); int++) {
			if(int==page.curPage){
				html+="<span class=\"current\">"+int+"</span>";
			}else{
				html+="<a href=\"javascript:void(0)\" onclick=\"toPage1("+int+")\">"+int+"</a>";
			}
		}
	}else if(page.curPage>4 && (page.curPage+5)<= page.maxPage){
		var max = ((page.curPage+4)>page.maxPage?(page.maxPage-1):(page.curPage +4));
		for ( var i = (page.curPage-2); i < max ; i++) {
			if(i == page.curPage){
				html+="<span class=\"current\">"+i+"</span>";
			}else{
				html+="<a href=\"javascript:void(0)\" onclick=\"toPage1("+i+")\">"+i+"</a>";
			}
		}
	}else {
		for ( var int = (page.maxPage-6); int <= page.maxPage; int++) {
			if(int==page.curPage){
				html+="<span class=\"current\">"+int+"</span>";
			}else if(int>1){
				if(!(int == page.maxPage && page.curPage < page.maxPage-1) ){
					html+="<a href=\"javascript:void(0)\" onclick=\"toPage1("+int+")\">"+int+"</a>";
				}
			}
		}
	}
	if(page.curPage<=page.maxPage-2 &&page.maxPage>5){
		if(page.curPage<=page.maxPage-5){
			html+="...<a href=\"Javascript:void(0);\" onclick=\"toPage1("+
			page.maxPage+")\">"+page.maxPage+"</a>";
		}else{
			html+="<a href=\"Javascript:void(0);\" onclick=\"toPage1("+
			page.maxPage+")\">"+page.maxPage+"</a>";
		}
		
	}
	if(page.curPage>=page.maxPage){
		html+="<span class=\"disabled\">下一页</span>";
	}
	if(page.curPage<page.maxPage){
		html+="<a href=\"javascript:void(0)\" onclick=\"toPage1("+(page.curPage+1)+")\">下一页</a>";
	}
	//定义下拉框
	var opt="";
	for (var int2 = 10; int2 <= 50; int2+=5) {
		var sel="";
		if(page.pageCount==int2){
			sel="selected";
		}
		opt+="<option value='"+int2+"' "+sel+">"+int2+"</option>"
	}
	if(page.pageCount==-1){
		var _sel="selected";
	}
	opt+="<option value='-1' "+_sel+">all</option>";
	html+=" 每页<select id=\"pageCount1\" value='"+ page.pageCount+"' onchange='toPage1(1,this.value)'>"+opt+"</select>条&nbsp&nbsp";
	html+="共:<font color=\"red\">" + page.totalRecord + "</font>&nbsp;条";
	html+="<span style=\"font-size:12px;\"> 跳到<input id=\"inputToPage1\" type=\"text\"  style=\"width:25px; height:20px; margin:0px 5px;\" maxlength=\"4\" value='"+page.curPage+"' onblur='toBlur1(this.value," + page.maxPage + ")'/>页</span><span style=\"margin-right:3px;\"><a style='float: none;color: blue'   href=\"javascript:void(0)\" onclick=\"toPage1(document.getElementById('inputToPage1').value)\">GO</a></span>";
	return html;
}
function toBlur1(vars,maxp){
	var  inputToPage= document.getElementById("inputToPage1");
	var reg = new RegExp("^[0-9]*$");
	if(!reg.test(vars)){
	    alert("请输入正确的页码数!");
	    inputToPage.value=""; 
	}else if (vars>maxp) {
		   alert("输入页码不在查询范围内!");
		   inputToPage.value="";
	}else if(vars<1){
		   alert("输入页码不在查询范围内!");
		   inputToPage.value="";
	}	
}
function checkAjaxData(data){
	var d= data;
	if(typeof(d)!='object'){d = eval("("+d+")");
	}
	if(d && d!= null && d.error){
		alert(d.error);
		if(d.url){
			var url = d.url;
			if(d.from && d.from=="true"){
				url += "?from=" + window.location ;
			}
			if(window.top==window.self){
				window.location.href=url;
			}else{
				window.top.location.href=url;
			}
		}
		return true;
	}
	if(d && d!= null && d.url){
		window.location.href=d.url;
		return true;
	}
	return false;
} 