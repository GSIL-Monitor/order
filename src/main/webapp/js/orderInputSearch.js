//创建XMLHttpRequest对象  
function createXMLHttpRequest() {
	var obj;
	if (window.XMLHttpRequest) { // Mozilla 浏览器
		obj = new XMLHttpRequest();
	} else if (window.ActiveXObject) { // IE浏览器
		try {
			obj = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				obj = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}
	return obj;
}
// 当输入框的内容变化时，调用该函数
function searchSuggest() {
	var inputField = $("txtSearch");
	var suggestText = $("suggestResult");
	if (inputField.value.length > 0) {
		var o = createXMLHttpRequest();
		var url = "SearchResult.ashx?searchText=" + escape(inputField.value);
		o.open("GET", url, true);
		o.onreadystatechange = function() {
			if (o.readyState == 4) {
				if (o.status == 200) {
					var sourceItems = o.responseText.split("\n");
					if (sourceItems.length > 1) {
						suggestText.style.display = "";
						suggestText.innerHTML = "";
						for (var i = 0; i < sourceItems.length - 1; i++) {
							var sourceText = sourceItems[i].split("@")[1];
							var sourceValue = sourceItems[i].split("@")[0];
							var s = "<div onmouseover=\"javascript:suggestOver(this);\" ";
							s += " onmouseout=\"javascript:suggestOut(this);\" ";
							s += " onclick=\"javascript:setSearch('"
									+ sourceText + "','" + sourceValue
									+ "');\" ";
							s += " class=\"suggest_link\" >" + sourceText
									+ "</div>";
							suggestText.innerHTML += s;
						}
					} else {
						suggestText.style.display = "none";
					}
				}
			}
		}; // 指定响应函数
		o.send(null); // 发送请求
	} else {
		suggestText.style.display = "none";
	}
}
function delayExecute() {
	$("valueResult").value = "";
	window.setTimeout(function() {
		searchSuggest()
	}, 800);
	// 延时处理
}
function suggestOver(div_value) {
	div_value.className = "suggest_link_over";
}
function suggestOut(div_value) {
	div_value.className = "suggest_link";
}
function setSearch(a, b) {
	$("txtSearch").value = a;
	$("valueResult").value = b;
	var div = $("suggestResult");
	div.innerHTML = "";
	div.style.display = "none";
}
function showResult() {
	alert($("txtSearch").value + $("valueResult").value);
}