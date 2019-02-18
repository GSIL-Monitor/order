<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>

    <script type="text/javascript">
        /*tab栏*/
        $(function () {
            $(".mytabs>li").click(function () {
                $(this).addClass("tab-active").siblings().removeClass("tab-active");
                var index = $(this).index();
                $(".main")
                        .eq(index)
                        .addClass("tab-selected")
                        .siblings(".main")
                        .removeClass("tab-selected");
            });
        });

        /*原生JS写Tab栏滑动*/
        var right = document.getElementById("right");
        var left = document.getElementById("left");
        var mytabs = document.getElementById("mytabs");
        right.onclick = function () {
            target -= 80;
        };
        left.onclick = function () {
            target += 80;
        };
        var leader = 0, target = 0;
        setInterval(function () {
            if (target >= 0) {
                target = 0;
            } else if (target <= 0) {
                target = 0;
            }
            leader = leader + (target - leader) / 10;
            mytabs.style.left = leader + "px";
        }, 20);
    </script>
</head>
<body>
<input type="hidden" value="${ctx}" id="ctx">
<div class="mytabs-wrap">
    <ul class="mytabs" id="mytabs">
        <li class="tab-item tab-active">售后信息</li>
        <li id="callBackDetail" class="tab-item" style="display: none">回访信息</li>
    </ul>
    <%--<ul class="mytabs" id="mytabs1">--%>
        <%--<li id="callBackDetail" class="tab-item" style="display: none">回访信息</li>--%>
    <%--</ul>--%>
</div>
<!-- Tab panes -->
<div id="arr">
    <span id="left">&lt;</span><span id="right">&gt;</span>
</div>
<div class="tab-content" id="tab_content">
    <div class="main tab-selected" id="afterSales">
        <jsp:include page="/jsp/quality/qualityDetail.jsp"></jsp:include>
    </div>
    <div class="main" id="quality_call_Back">
        <jsp:include page="/jsp/quality/qualityCallBack.jsp"></jsp:include>
    </div>
</div>
</body>
</html>