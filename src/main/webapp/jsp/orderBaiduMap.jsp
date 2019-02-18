<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=tIqExiZw1IyBNAshjMWI1PT1DtIhOsuc"></script>
	<title>地图展示</title>
</head>
<body>
    <!--百度地图容器-->
    <div id="allmap"></div>
</body>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");    // 创建Map实例
	map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);  // 初始化地图,设置中心点坐标和地图级别
	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	map.setCurrentCity("北京");          // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放

	var myGeo = new BMap.Geocoder();// 创建地址解析器实例
	// 通过地址显示地图
	function theLocation(address){
		var point = new BMap.Point(116.331398,39.897445);
		myGeo.getPoint(address, function(point){
			if (point) {
				map.centerAndZoom(point, 16);
				map.addOverlay(new BMap.Marker(point));
			}else{
				//alert("您选择地址没有解析到结果!");
			}
		}, "北京市");
	}
	
	
// 	function theLocation(value){
// 		var options = {
// 			onSearchComplete: function(results){
// 				// 判断状态是否正确
// 				if (local.getStatus() == BMAP_STATUS_SUCCESS){
// 					var s = [];
// 					for (var i = 0; i < results.getCurrentNumPois(); i ++){
// 						s.push(results.getPoi(i).title + ", " + results.getPoi(i).address);
// 					}
// 					parent.document.getElementById("searchResultPanel").innerHTML = s.join("<br/>");
// 				}
// 			}
// 		};
// 		var local = new BMap.LocalSearch(map, options);
// 		local.search(value);
// 	}
	
</script>
</html>