<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style>
.tabOrder {
	border-left: 0px;
	border-top: 1px solid #CCC;
	border-right: 0px;
	border-bottom: 1px solid #CCC;
	margin: auto auto auto 5px
}
</style>
<script src="${ctx}/js/main.js"></script>
</head>
<body>
	<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
		<div class="mytabs-wrap">
			<ul class="mytabs">
				<li class="tab-item tab-active" >基本信息</li>
			</ul>
		</div>
		<div id="arr">
			<span id="left"><</span><span id="right">></span>
		</div>
		<div class="tab-content">
			<div>
                <div class="panel-content ">
                    <table class="table table-hover table-striped">
                        <tbody>
                        <tr>
                            <td colspan="2"><p class="table-empty">暂无数据</p></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
		</div>
</body>
<script type="text/javascript">
	
</script>
</html>