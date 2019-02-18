<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body, html, #allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
	font-family: "微软雅黑";
}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=tIqExiZw1IyBNAshjMWI1PT1DtIhOsuc"></script>
<title>地图展示</title>
</head>
<body>
	<!--百度地图容器-->
	<div id="allmap"></div>
</body>
<script type="text/javascript">
	//百度地图API功能
	var map = new BMap.Map("allmap");
	//添加地图类型控件
	map.addControl(new BMap.MapTypeControl());
	//开启鼠标滚轮缩放
	map.enableScrollWheelZoom(true);
	// 创建地址解析器实例
	var myGeo = new BMap.Geocoder();
	var marker;
	//通过地址显示地图
	function theLocation(address, isOnLoad) {
		myGeo.getPoint(address, function(point) {
			if(isOnLoad==1){
				if(point){
					marker = new BMap.Marker(point);
					map.centerAndZoom(point, 16);
					map.addOverlay(marker);
				}else{
					// 初始化地图,设置中心点坐标和地图级别
					map.centerAndZoom("北京", 11);
				}
			}else{
				if(point){
					map.removeOverlay(marker);
					marker = new BMap.Marker(point);
					map.centerAndZoom(point, 16);
					map.addOverlay(marker);
				}else{
					//alert("没有定位到坐标点")
				}
			}
		}, "北京市");
	}
</script>
</html>