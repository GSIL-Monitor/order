<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
    <style type="text/css">
        .order-add {
            height: 650px;
            width: 900px;
            margin: 20px auto;
        }
		 .modal {overflow: auto !important;}
        .otable-tr-top {
            height: 25px;
            border-top: 1px solid #CCC
        }

        .otable-tr-bottom {
            height: 25px;
            border-bottom: 1px solid #CCC;
        }

        .otable-td-left {
            border-bottom: 1px solid #CCC;
            border-left: 1px solid #CCC;
        }

        .otable-td-right {
            border-bottom: 1px solid #CCC;
            border-right: 1px solid #CCC;
        }
    </style>

    <script type="text/javascript">
        <%
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String sex = request.getParameter("sex");
            String mobile = request.getParameter("mobile");
            String createTime = request.getParameter("createTime");
            String cardType = request.getParameter("cardTypeText");
            String cardNum = request.getParameter("cardNum");
            String city = request.getParameter("city");
            String userCity = request.getParameter("userCity");
            String userAddress = request.getParameter("userAddress");
            String longitude = request.getParameter("longitude");
            String latitude = request.getParameter("latitude");
            String priceType = request.getParameter("priceType");
            String channelId = request.getParameter("channelId");
        %>
    </script>
    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx}/js/main.js"></script>

</head>
<body>
<input type="hidden" id="ctx" name="ctx" value="${ctx}"/>
<div class="modal fade" id="modelFrameOrderAdd">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"
                        onclick="setOrderCloseModule('orderAdd');">×
                </button>
                <h4 class="modal-title">新增订单>>录入订单信息</h4>
            </div>
            <div class="modal-body">
                <form id="orderAddForm" action="" method="post" class="form-inline">
                    <input type="hidden" id="orderAddUserId" value="<%=id%>">
                    <input type="hidden" id="cateType" value="">
                    <input type="hidden" id="productCateType">
                    <input type="hidden" id="categoryCode" value="">
                    <input type="hidden" id="mobiles" value="<%=mobile%>">
                    <input type="hidden" id="userCity" value="<%=userCity%>">
                    <input type="hidden" id="userAddress" value="<%=userAddress%>">
                    <input type="hidden" id="priceType" value="<%=priceType %>">
                    <input type="hidden" id="orderAddLongitude">
                    <input type="hidden" id="orderAddLatitude">
                    <input type="hidden" id="orderAddReceiverId">

                    <header class="mb10"><h4>客户信息</h4></header>
                    <div class="info-select clearfix">
                        <div class="row">
                            <div class="form-group col-xs-3">
                                <label>客户姓名：<%=name %>
                                </label>
                            </div>
                            <div class="form-group col-xs-3">
                                <label>性别：<%=sex %>
                                </label>
                            </div>
                            <div class="form-group col-xs-6">
                                <label>客户电话：<%=mobile %>
                                </label>
                                <label style="margin-left: 50px;">
                            		<button onclick="similarityOrder();"  type="button" class="btn btn-sm btn-default">
                            			录入相似订单
                            		</button>
                            	</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-3">
                                <label>证件类型：<%=cardType %>
                                </label>
                            </div>
                            <div class="form-group col-xs-3">
                                <label>证件号码：<%=cardNum %>
                                </label>
                            </div>
                            <div class="form-group col-xs-6">
                                <label>地址：<%=userAddress %>
                                </label>
                            </div>
                        </div>
                    </div>
                    <header class="mb10"><h4>订单信息</h4></header>
                    <div class="info clearfix">
                        <div class="row">
                            <div class="form-group col-xs-12">
                                <label><span style="color: red">*</span>订单渠道：
                                    <select id="orderAddChannel" class="form-control" style="width:250px;">
                                        <option value="-1">---请选择---</option>
                                    </select>
                                </label>
                                <label>三方平台关联订单号：
                                    <input id="threeOrderCode_add" type="text" class="form-control" style="width:220px;"
                                           name="threeOrderCode" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')">
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-12">
                                <label><span style="color: red">*</span>选择省份：
                                    <select id="orderAddProvince" class="form-control"
                                            onclick="setSelCity('orderAddProvince','orderAddCity')"
                                            style="width:120px;">
                                        <option value="-1">---请选2择---</option>
                                    </select>
                                    <span style="color: red">*</span>商品城市：
                                    <select id="orderAddCity" class="form-control" onchange="clearProducts();"
                                            style="width:120px;">
                                        <option value="-1">---请选择---</option>
                                    </select>
                                    是否享受解决方案价格：
                                    <select id="orderAddSolutionOrNot" class="form-control" onchange="clearProducts();"
                                            style="width:80px;">
                                        <option value="2">否</option>
                                        <option value="1">是</option>
                                    </select>
                                </label>
                            </div>
                        </div>
                        <div class="row" id="orderItemCategoryFouth" style="display:none;">
                            <div class="form-group col-xs-12">
                                <!-- <label>商品搜索：
                                      <select id="SelectFourth" class="form-control" style="width:250px;">
                                        <option value="-1">---请选择---</option>
                                      </select>
                                </label> -->
                                <label>
                                    <span style="color: red">*</span>一级分类：
                                    <select id="oneClassify" class="form-control"
                                            onchange="javascript:oneChange(this),selectBaseProduct(this)"
                                            style="width:200px;">
                                        <option value="-1">---请选择---</option>
                                    </select>
                                </label>
                                <label>
                                    <span style="color: red">*</span>二级分类：
                                    <select id="twoClassify" class="form-control"
                                            onchange="javascript:twoChange(this),selectBaseProduct(this)"
                                            style="width:200px;">
                                        <option value="-1">---请选择---</option>
                                    </select>
                                </label>
                                <label>
                                    <span style="color: red">*</span>三级分类：
                                    <select id="threeClassify" class="form-control" onchange="selectBaseProduct(this)"
                                            style="width:200px;">
                                        <option value="-1">---请选择---</option>
                                    </select>
                                </label>
                            </div>
                            <div class="form-group col-xs-12">
                                <label>
                                    <span style="color: red">*</span>基础商品：
                                    <select id="baseProduct" class="form-control" style="width:300px;">
                                        <option value="-1">---请选择---</option>
                                    </select>
                                </label>
                                <label id="serverTypeFourth" style="display:none;">
                                    <span style="color: red">*</span>城市商品：
                                    <select id="serverSelectFourth" class="form-control" style="width:350px;"
                                            onchange="javascript:setCityServerProduct(this)">
                                        <option value="-1">---请选择---</option>
                                        <option value="101001001628653740620980,12800,20070008,月,20000002,市场价:12800,月嫂:无特殊">
                                            中级月嫂无特殊
                                        </option>
                                    </select>
                                </label>
                                &nbsp;&nbsp;&nbsp;<span id="cateTypeItem"></span>
                                &nbsp;&nbsp;&nbsp;<span id="cityProductCode"></span>
                            </div>
                        </div>
                        <div class="row" id="orderDeliver" style="display:none;">
                            <div class="form-group col-xs-12">
                                <label>订单分发：
                                    <select id="orderFenfa" class="form-control" onchange="selfenfa(this)">
                                        <option value="-2">手动</option>
                                        <option value="-3" selected="selected">自动</option>
                                    </select>
                                </label>
                                <label id="mendian"><span style="color: red">*</span>门店/门店基地：
                                    <select id="mendianjidi" class="form-control" style="width:250px;">
                                        <option value="-1">---请选择---</option>
                                    </select>
                                </label>
                                <label id="guanjia"><span style="color: red">*</span>管家姓名：
                                    <select id="guanjiaxingming" class="form-control" style="width:120px;">
                                        <option value="-1">---请选择---</option>
                                    </select>
                                </label>
                                <label id="toubao">
                                    是否投保:
                                    <select id="insure" class="form-control" style="width:120px;" onchange="selectInsure(this);">
                                        <option value="2" selected="selected">否</option>
                                        <option value="1">是</option>
                                    </select>
                                </label>
                                <label id="toubaoAmount" style="display: none;">
                                    <span style="color:red">*</span>投保金额(元):
                                    <input type="number" id="insureAmount" name="insureAmount" class="form-control" value="" onkeydown="inputOnlyNum(event);" onkeypress="checkIfNum(event);">
                                </label>
                               <label id="serviceObjectView" style="display: none;" >
                                    	*服务对象:
                                    <select id="serviceObject"  class="form-control" style="width:120px;" >
                                     	<option value="0" selected="selected">请选择</option>
                                     	<option value="2">个人</option>
                                     	<option value="1" >企业</option>
                                    </select>
                                </label>
                            </div>
                        </div>
                        <div class="row" id="personOrderDiv" style="display:none;">
                            <div class="form-group col-xs-12">
                                <label><span style="color: red">*</span>选择带单服务人员: </label>
                                <label><span style="color: red"></span>姓名：
                                    <input type=text id="fname" class="form-control" style="width:120px"/>
                                </label>
                                <label><span style="color: red"></span>电话：
                                    <input type=text id="fmobile" class="form-control" style="width:120px"/>
                                </label>
                                <button type="button" class="btn btn-sm btn-primary" onclick="selectPerson(0,5);">查询
                                </button>
                            </div>
                            <!--列表开始-->
                            <tbody class="dataTable_wrapper">
                            <div class="panel-content table-responsive">
                                <table class="table table-hover table-striped" id="tablecode">
                                    <thead>
                                    <tr>
                                        <th></th>
                                        <th>序号</th>
                                        <th>姓名</th>
                                        <th>电话</th>
                                        <th>籍贯</th>
                                    </tr>
                                    </thead>
                                    <tbody id="listBodyOrderPerson">
                                    </tbody>
                                </table>
                                <ul class="pagination pagination-sm navbar-left" id="pageInfoDivPerson"></ul>
                            </div>
                            </tbody>
                        </div>

                    </div>

                    <div id="conversionPage" class="info-select table-scroll-y"
                         style="height:335px; border:1px solid #CCC;">
                        <div name="orderAll" id="orderAddItem" style="display: none;">
                            <%@ include file="orderAddItem.jsp" %>
                        </div>-
                        <div name="orderAll" id="orderAddServer" style="display: none;">
                            <%@ include file="orderAddServer.jsp" %>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal"
                        onclick="setOrderCloseModule('orderAdd');">取消
                </button>
                <button type="button" class="btn btn-sm btn-primary" id="orderAddSaveo" onclick="saveOrder();">提交
                </button>
            </div>
        </div>
    </div>
</div>

</body>

<script type="text/javascript">
    $(function () {
        var oldUserCity = "${param.userCity}";
        var newUserCity = oldUserCity.substring(0, 6);
        queryChannels("orderAddChannel", {"valid": 1});//查询已启用的渠道
        $("#orderAddChannel option[value=${param.channelId}]").attr("selected", "selected");
        setSelProvinceCitys("101", 6, "orderAddProvince");
        $("#orderAddProvince option[value='" + newUserCity + "']").attr("selected", "selected").click();
        $("#orderAddCity option[value='" + oldUserCity + "']").attr("selected", "selected").change();
        /* var cityCode_c = eval('(' + $.cookie().manager + ')').cityCode;
         var cityCode_p =  cityCode_c.substr(0,6);
         $("#orderAddProvince").val(cityCode_p||-1).trigger("click").prop("disabled",true);
         $("#orderAddCity").val(cityCode_c||-1).trigger("change").prop("disabled",true); */
        setSelProvinceCitys("101", 6, "selProvinceAddressServer");
        selectOrderDeptName("mendianjidi");
        personOrder();
    });

    //订单渠道
    function personOrder() {
        var channelPerson = $("#orderAddChannel").val();
        if (channelPerson == 212028286803173 || channelPerson == "212028286803173") {
            //$("personOrderDiv").css("display","block");1
            $("#personOrderDiv").css("display", "inline");
        } else {
            $("#personOrderDiv").css("display", "none");
        }


    }

    // 查询商品分类
    function queryCategory(typeId, code, length) {
        var ctx = $("#ctx").val();
        var cityCode = $("#orderAddCity option:selected").val();
        //console.log(cityCode);
        $.ajax({
            url: ctx + "/item/queryCategory",
            type: "post",
            datetype: "json",
            async: false,
            data: {
                code: code,
                length: length,
                cityCode: cityCode
            },
            success: function (data) {
                if (data.msg == "00") {
                    var html = "";
                    var result = data.list;
                    var html = "<option style='color:blue;' value='0' >...请选择...</option>";
                    /* if("threeClassify"==typeId){
                     $.each(result,function(i, p) {
                     html += "<option value='"+p.code +"," +p.cateType +"' >"+p.cname+"</option>";
                     });
                     }else{ */
                    $.each(result, function (i, p) {
                        html += "<option value='" + p.code + "' >" + p.cname + "</option>";
                    });
                    /* } */
                    $("#" + typeId).html(html);
                } else {
                    //$("#SelectFourth").empty();
                    $("#" + typeId).html("<option style='color:blue;' value='0' >...无可选项...</option>");
                }
            }
        })
    }

    /* 查询商品的二级分类 */
    function oneChange(oc) {
        var code = oc.options[oc.options.selectedIndex].value;
        queryCategory("twoClassify", code, 8);
        $("#threeClassify").html("<option style='color:blue;' value='0' >...请选择...</option>");
        $("#serverSelectFourth").html("<option style='color:blue;' value='0' >...请选择...</option>");
        if (code != '1002') {
            $("#orderDeliver").css("display", "none");
        }
    }

    /* 查询商品的三级分类 */
    function twoChange(oc) {
        var code = oc.options[oc.options.selectedIndex].value;
        queryCategory("threeClassify", code, 12);
    }

    /**
     * 单机城市查询一级分类一级基础商品
     */
    function clearProducts() {
        var threeClassify = $("#threeClassify option:selected").val();
        var city = $("#orderAddCity option:selected").val();
        queryCategory("oneClassify", "1", 4);// 查询出一级分类
        selectBaseProduct();//查询基础商品
        $("#orderAddReceiverId").val("");
        $("#orderAddLongitude").val("");
        $("#orderAddLatitude").val("");
        if (city == -1 || city == null || city == "") {
            $("#listBodyAddressItem").html("");
            $("#listBodyAddressServer").html("");
            $("#orderItemCategoryFouth").css("display", "none");
            $("#itemsAll").html("");
            $("#itemsChecked").html("<tr></tr>");
            return;
        } else {
        	 $("#itemsAll").html("");
             $("#itemsChecked").html("<tr></tr>");
             $("#payfeesum").html("");
             $("#orderItemCategoryFouth").css("display", "block");
        }
        /* if(threeClassify!=0 && threeClassify.split(",")[1]==3){
         //queryProduct(threeClassify.split(",")[0],$("#orderAddCity").val());
         checkOrderAddUserAddress("listBodyAddressItem");//通过电话号码取到送贷地址
         $("#itemsChecked").html("<tr></tr>");
         }else{
         //queryCityServerProduct(threeClassify.split(",")[0],$("#orderAddCity").val());
         //queryCityServerProduct("",$("#orderAddCity").val());
         $("#itemsChecked").html("<tr></tr>");
         checkOrderAddUserAddress("listBodyAddressServer");//通过电话号码取到送贷地址

         } */
        //$("#threeClassify").html("<option style='color:blue;' value='0' >...请选择...</option>");
        //$("#serverSelectFourth").html("<option style='color:blue;' value='0' >...请选择...</option>");
        $("#totalPayAddServer").val("");
        $("#priceTextAddServer").val("");
    }
    //选择自动及其手动
    function selfenfa() {
        var fenfa = $("#orderFenfa  option:selected").val();
        if (fenfa == "-3" || fenfa == "-1") {
            $("#mendian").css("display", "none");
            $("#guanjia").css("display", "none");
        } else {
            $("#mendian").css("display", "inline");
            $("#guanjia").css("display", "inline");
            $("#mendianjidi option[name=4]").prop("selected", true);
            $("#guanjiaxingming").html("");
            $("#guanjiaxingming").prepend("<option>...请选择...</option>");
        }
    }
    //加载管家
    function selguanjia() {
        var ctx = $("#ctx").val();
        var deptId = $("#mendianjidi option:selected").val();
        if (deptId == "" || deptId == null) {
            $("#guanjiaxingming").empty();
            $("#guanjiaxingming").html("<option>...请选择...</option>");
        } else {
            $.ajax({
                url: ctx + "/orderbase/queryguanjia",
                data: {
                    deptId: deptId
                },
                type: 'post',
                async: false,
                dataType: "json",
                success: function (data) {
                    var html = "";
                    html += "<option style='color:blue;' value='' >...请选择...</option>";
                    if (data.msg == "00") {
                        $.each(data.list, function (i, v) {
                            html += "<option   value='" + v.id + "'>" + v.realName + "</option>";
                        });
                    } else if (data.msg == "02") {
                        $.alert({millis: 2000, text: "无记录!"});
                    } else {
                        $.alert({millis: 2000, text: "查询出错，请稍后再试!"});
                    }
                    $("#guanjiaxingming").html(html);
                }
            });
        }

    }
    //查询服务人员
    function selectPerson(num, pageCount) {
        var ctx = $("#ctx").val();
        var fname = $("#fname").val();
        var fmobile = $("#fmobile").val();
        if (fname == "" && fmobile == "") {
            $.alert({text: "请输入服务人员姓名或电话!"});
            return;
        }
        $.ajax({
            url: ctx + "/order/queryPerson?curPage=" + num + "&pageCount=" + pageCount,
            data: {
                userName: fname,
                userMobile: fmobile
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                var html = "";
                $("#pageInfoDivPerson").pagination(data.page, listBodyOrderPerson);
                var pageCount = data.page.pageCount;
                var curPage = data.page.curPage;
                var total = curPage * pageCount;
                if (data.msg == "00") {
                    var num =
                            $.each(data.list, function (i, v) {
                                num = i + 1;
                                html += "<tr>";
                                html += "<td ><input type='radio' name='person_id' value='" + v.ID + "'/>"
                                html += "</td><td>" + (total + num - pageCount);
                                html += "</td><td align='left'>" + v.NAME;
                                html += "</td><td align='left'>" + v.MOBILE;
                                html += "</td><td>" + v.ORIGIN;
                                html += "</td></tr>";
                            })
                    $("#pageInfoDivPerson").show();
                } else {
                    html = "<tr><td colspan='10'><p class='table-empty'>暂无数据</p></td></tr>";
                    $("#pageInfoDivPerson").hide();
                }
                $("#listBodyOrderPerson").html(html);
                /*表格点击行高亮*/
                trColor("#native_tbody > tr");
                /*表格单选*/
                radioColor("#listBodyOrderPerson > tr");
                $("#listBodyOrderPerson").find("input[type=radio]").eq(0).prop("checked", true);
            }
        });
    }
    /**
     * 分页跳转使用
     */
    function listBodyOrderPerson(num, pageCount) {
        $("#listBodyOrderPerson").empty();
        selectPerson(num, pageCount);

    }
    //确定\保存按钮方法
    function saveOrder() {
        $("#orderAddSaveo").attr("disabled", "disabled");
        var result = 1;
        /** 周鑫 2018-12-07 **/
        var pickQuantityServer=$("#pickQuantityServer").val();
        if(pickQuantityServer==null||pickQuantityServer==''){
        	pickQuantityServer=1;
        }
        var totalPayAddServer=$("#totalPayAddServer").val();
        var threeClassify=$("#threeClassify").val();
        var personNumberServer=$("#personNumberServer").val();
        var sum=0;
        if(threeClassify=='100200020004'||threeClassify=='100200020002'){
        	if(personNumberServer==null||personNumberServer==''){
        		personNumberServer=1;
        	}
        	sum=personNumberServer*pickQuantityServer*totalPayAddServer;
        }else{
        	sum=pickQuantityServer*totalPayAddServer;
        }
        if(sum>=10000000){
          	 $.alert({text: "订单金额过大！"});
          	 $("#orderAddSaveo").removeAttr("disabled");
          	 return;
          }
        
        /**  周鑫 2018-12-07  **/
        
        /** 2019-01-03 周鑫 **/
        var threeOrderCodeAdd=$("#threeOrderCode_add").val();
        if(threeOrderCodeAdd!=null&&threeOrderCodeAdd!=''){
        	var re = /^[0-9a-zA-Z]+$/; /*只能是数字或字母，不包括下划线的正则表达式*/
        	if (!re.test(threeOrderCodeAdd)) {
        		$.alert({text: "订单号输入有误，请修改"});
        		$("#orderAddSaveo").removeAttr("disabled");
        		return;
        	}
        }
        
        /** 2019-01-03 周鑫 **/
        
        
        
        var orderChannel = $("#orderAddChannel").val();
        if (orderChannel == null || orderChannel == "" || orderChannel == -1) {
            $.alert({text: "请选择渠道！"});
            $("#orderAddSaveo").removeAttr("disabled");
            return;
        }
        if (orderChannel == 212028286803173 || orderChannel == '212028286803173') {
            person = $("input[name='person_id']:checked").val();
            if (person == "" || person == null) {
                $.alert({text: "请选择服务人员！"});
                $("#orderAddSaveo").removeAttr("disabled");
                return;
            }
        }
        var province = $("#orderAddProvince").val();
        if (province == null || province == "" || province == -1) {
            $.alert({text: "请选择省份！"});
            $("#orderAddSaveo").removeAttr("disabled");
            return;
        }
        var city = $("#orderAddCity").val();
        if (city == null || city == "" || city == -1) {
            $.alert({text: "请选择城市！"});
            $("#orderAddSaveo").removeAttr("disabled");
            return;
        }
        var cateType = $("#cateType").val();
        //获取一级分类
        var oneClassify = $("#oneClassify option:selected").val();
        if (cateType == null || cateType == "") {
            $.alert({text: "请选择基础商品！"});
            $("#orderAddSaveo").removeAttr("disabled");
            return;
        }
        if (cateType == 1 || cateType == 2) {
            var orderFenfa = $("#orderFenfa").val();
            var mendianjidi = $("#mendianjidi").val();
            var guanjiaxingming = $("#guanjiaxingming").val();
            if (orderFenfa == null || orderFenfa == "" || orderFenfa == -1 || orderFenfa == '-1') {
                $.alert({text: "请选择分发方式！"});
                $("#orderAddSaveo").removeAttr("disabled");
                return;
            }
            if (orderFenfa == -2 || orderFenfa == '-2') {
                if (guanjiaxingming == "...请选择..." || guanjiaxingming == "") {
                    $.alert({text: "请选择基地及管家！"});
                    $("#orderAddSaveo").removeAttr("disabled");
                    return;
                }
            }
            //增加“是否投保”填项 add zhanghao
            if(oneClassify == "1002"){
                var insure = $("#insure").val();
                if(insure != "1" && insure != "2"){//是否投保必选
                    $.alert({text: "请选是否投保！"});
                    $("#orderAddSaveo").removeAttr("disabled");
                    return;
                }else if(insure == "1"){//如果选择投保，投保金额必选 add 20181130 zhanghao
                    var insureAmount = $("#insureAmount").val();
                    if(insureAmount == "" || insureAmount <= "0"){
                        $.alert({text:"请输入投保金额!"});
                        $("#orderAddSaveo").removeAttr("disabled");
                        return;
                    }
                }
            }
            result = saveServerAdd(cateType);
        } else if (cateType == 3) {
            result = dosaveItems(cateType);
        } else if (cateType == 4) {
            result = saveServerAdd(cateType);
        }
        // 如果保存成功，刷新父页面并关掉窗口
        if (result == 0) {
            parent.queryOrders(0, 10);
            setOrderCloseModule('orderAdd');
        }else {
            $("#orderAddSaveo").removeAttr("disabled");
// 		setTimeout($("#orderAddSaveo").removeAttr("disabled"), 5000) ;
        }
    }

    //通过电话号码取到送贷地址
    function checkOrderAddUserAddress(adressId) {
        var ctx = $("#ctx").val();
        var userId = $("#orderAddUserId").val();
        var city = $("#orderAddCity").val();
        $.ajax({
            url: ctx + "/orderbase/queryUserAddressMapper",
            data: {
                userId: userId,
                /* cityCode:city, */
                valid: 1
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                var html = "";
                if (data.msg == "00") {
                    var num = $.each(data.list, function (i, v) {
                        num = i + 1;
                        html += "<tbody><tr></tr>";
                        html += "<tr><td rowspan='2' class='bor-r'>";
                        html += "<input type='radio' name='orderAddReceiverId' onclick=\"setOrderAddReceiverId(" + v.id + ",'" + v.longitude + "','" + v.latitude + "');\"/></td>";
                        html += "<td>" + v.contactName + "</td>";
                        html += "<td>" + v.contactPhone + "</td>";
                        html += "<td rowspan='2' class='bor-l'>";
                        html += "<div><a href='#' onclick='orderAddUpdateAddress(" + v.id + ")'>修改</a></div>";
                        html += "</td></tr><tr>";
                        html += "<td colspan='2'>" + v.province + v.city + v.country + v.addressChoose + v.addressDetail + "</td>";
                        html += "</tr></tbody>";
                    });
                }
                $("#" + adressId).html(html);
                $("input[name='orderAddReceiverId']:eq(0)").prop("checked", true).click();
            }
        });
    }
    function setOrderAddReceiverId(id, longitude, latitude) {
        $("#orderAddReceiverId").val(id);
        $("#orderAddLongitude").val(longitude);
        $("#orderAddLatitude").val(latitude);
    }

    function orderAddUpdateAddress(userAddressId) {
        var values = $("#threeClassify option:selected").val();
        var userId = $('#orderAddUserId').val();
        var val = values.split(",");
        //alert(userAddressId +" and " +userId + " and " +val[1]) ;
        if (val[1] == 3) {
            openModule('/order/jsp/orderItemaddressAdd.jsp', {
                lx: 2,
                userId: userId,
                userAddressId: userAddressId
            }, '', '', 'orderItemaddressAdd');
        } else {
            openModule('/order/jsp/orderItemaddressAdd.jsp', {
                lx: 4,
                userId: userId,
                userAddressId: userAddressId
            }, '', '', 'orderItemaddressAdd');
        }
    }
    function setCityServerProduct(item) {
        var items = item.options[item.options.selectedIndex].value;
        $("#cityProductCode").text("");
        $("#cityProductCode").text("商品码:" + items.split(",")[0]);
        $("#pickQuantityServer").val(1);
        $("#totalPayAddServer").val(items.split(",")[1]);
        $("#productUnitAddServer").val(items.split(",")[2]);
        $("#productUnitTextAddServer").text(items.split(",")[3]);
        $("#productPriceTypeAddServer").val(items.split(",")[4]);
        $("#priceTextAddServer").val(items.split(",")[5]);
        $("#productSpecAddServer").val(items.split(",")[6]);
    }
    // 计算总价格
    function setTotalPayAddServer() {
        ForestsServer
        if ($("#ForestsServer").val() != null || $("#ForestsServer").val() != null) {
            $("#totalPayAddServer").val($("#serverSelectFourth option:selected").val().split(",")[1] * $("#pickQuantityServer").val() * $("#ForestsServer").val());
        } else {
            $("#totalPayAddServer").val($("#serverSelectFourth option:selected").val().split(",")[1] * $("#pickQuantityServer").val());
        }
    }
    /*表格点击行高亮*/
    function trColorOrder(trli) {
        $(trli).on("click", function () {
            //$(this).toggleClass('info').siblings().removeClass('info').css('cursor', 'pointer');
            $(this).addClass('info').siblings().removeClass('info').css('cursor', 'pointer');
        })

    }
    /**
     * 查询基础商品
     * 王世博
     */
    function selectBaseProduct(thiz) {
        var categoryCode = $(thiz).val();
        var cityCode = $("#orderAddCity").val();
        $("#baseProduct").html("");
        $("#baseProduct").nextAll("div").remove();
        $("#baseProduct").append("<option value=''>请选择</option>");
        $.ajax({
            url: ctx + "/item/queryBaseProduct",
            type: 'post',
            data: {
                categoryCode: categoryCode,
                cityCode: cityCode
            },
            async: false,
            success: function (data) {
                if (data.msg == "00") {
                    $.each(data.list, function (i, v) {
                        var option = $("<option value=" + v.id + " data-cateCode=" + v.categoryCode + " data-isProdServe=" + v.isProdServe + " >" + v.name + "</option>");
                        $("#baseProduct").append(option);
                    });
                    $("#baseProduct").checkSelect(function (a, b, c) {
                        var categoryCode = $("#baseProduct").find("option:selected").attr("data-catecode");
                        $("#oneClassify option[value=" + categoryCode.substring(0, 4) + "]").prop("selected", true);
                        queryCategory("twoClassify", categoryCode.substring(0, 4), 8);
                        $("#twoClassify option[value=" + categoryCode.substring(0, 8) + "]").prop("selected", true);
                        queryCategory("threeClassify", categoryCode.substring(0, 8), 12);
                        $("#threeClassify option[value=" + categoryCode + "]").prop("selected", true);
                        selectStock();
                    })
                } else if (data.msg == "02") {
                    var option = $("<option value='0'>暂无选项</option>");
                    $("#baseProduct").html(option);
                }
            }
        });
    }
    /**
     * 通过基础商品种类判断下面怎么展示
     * 王世博
     */
    function selectStock() {
        checkedInsure();
        var cityCode = $("#orderAddCity option:selected").val();
        var productId =$("#baseProduct").val();
        var cateCode = $("#baseProduct").find("option:selected").attr("data-cateCode");
        var isProdServe = $("#baseProduct").find("option:selected").attr("data-isProdServe");
        /** 周鑫 2018-12-11 **/
        if(isProdServe==1){
        	 $("#serviceObjectView").css("display", "block");
        	 $("#serviceObject").val("0");
        }else{
        	 $("#serviceObjectView").css("display", "none");
        	 $("#serviceObject").val("0");
        }
        /** 周鑫 2018-12-11 **/
        /** 清空时间和改变时间的选择限制  开始 周鑫  2018-11-13  **/
        $("#startTimeServer").val(null);
        $("#endTimeServer").val(null);
        $("#endTimeServer").attr("onfocus","WdatePicker({minDate:'#F{$dp.$D(\\'startTimeServer\\')}',dateFmt:'yyyy-MM-dd HH:mm',onpicked:pickedFunc1})");
        $("#startTimeServer").attr("onfocus","WdatePicker({maxDate:'#F{$dp.$D(\\'endTimeServer\\')}',dateFmt:'yyyy-MM-dd HH:mm',onpicked:pickedFunc1})");
        /**清空时间结束    **/
        $("#cateTypeItem").text("");
        //选择月嫂或者育婴师，显示预产期
        if (cateCode == 100200010001 || cateCode == 100200010002) {
            $("#birthDate").show();
        } else {
            $("#birthDate").hide();
        }
        if (isProdServe == 1 || isProdServe == 2 || isProdServe == 4) {
            //擦玻璃增加服务面积
            if (isProdServe == 1) {
                //擦玻璃增加服务面积
                if (cateCode == 100200020004) {
                    $("#typeCaboli").css("display", "block");
                } else {
                    $("#typeCaboli").css("display", "none");
                }
            }
            $("#serverTypeFourth").css("display", "inline");
            $("#orderAddServer").css("display", "block");
            $("#orderAddItem").css("display", "none");
            $("#fourClassify").css("display", "inline");
            //自营商品，显示分派流程
            if (isProdServe == 1 || isProdServe == 2) {
                $("#orderDeliver").css("display", "block");
                $("#orderFenfa").css("display", "inline").trigger("change");
                //$("#mendian").css("display", "none");
                //$("#guanjia").css("display", "none");
                // $("#orderFenfa option[value='-1']").prop("selected",true);
            } else {
                $("#orderDeliver").css("display", "none");
            }
            //延续性显示，别的类型隐藏   1.自营单次 2.自营延续 3.自营商品 4.他营单次 5.预存卡 6.礼品卡 7.他营商品  8.课程
            if (isProdServe == 1 || isProdServe == 4) {
                $("#serverAddCont").css("display", "none");
                $("#addContSex").css("display", "block");
            } else {
                $("#serverAddCont").css("display", "block");
                $("#addContSex").css("display", "none");
            }
            $("#categoryCode").val(cateCode);
            $("#cateType").val(isProdServe);
            queryCityServerProduct(productId, cityCode);////查询该基础商品的城市商品
            checkOrderAddUserAddress("listBodyAddressServer");//通过电话查询收货地址
            //查询库存
            $.ajax({
                url: ctx + "/orderbase/selectStock",
                data: {
                    dkey: cityCode,
                    dvalue: cateCode
                },
                type: 'post',
                async: false,
                dataType: "json",
                success: function (data) {
                    var html = "";
                    if (data.msg == "00") {
                        if (isProdServe == 1) {
                            $("#oneServeStock").css("display", "block");//有库存服务时长
                            $("#serverTimeStart").css("display", "none")
                            $("#serverTimeEnd").css("display", "none")
                        } else {
                            $("#oneServeStock").css("display", "none");
                            $("#serverTimeStart").css("display", "block")
                            $("#serverTimeEnd").css("display", "block")
                        }
                    } else {
                        $("#oneServeStock").css("display", "none");
                        $("#serverTimeStart").css("display", "block")
                        $("#serverTimeEnd").css("display", "block")
                    }
                }
            });
        } else if (isProdServe == 3) {
            $("#orderAddItem").css("display", "block");
            $("#fourClassify").css("display", "none");
            $("#orderAddServer").css("display", "none");
            $("#serverTypeFourth").css("display", "none");
            $("#categoryCode").val(cateCode);
            $("#cateType").val(isProdServe);
            checkOrderAddUserAddress("listBodyAddressItem");//通过电话查询收货地址
            queryProduct(productId, cityCode);//查询该基础商品的城市商品
        }
        //商品自营或他营显示
        if (isProdServe == 1 || isProdServe == 2 || isProdServe == 3) {
            $("#cateTypeItem").text("自营");
        } else if (isProdServe == 4 || isProdServe == 7) {
            $("#cateTypeItem").text("他营");
        }
        /*
        * add 20181212 zhanghao 新增订单时，根据订单类型带入默认时间
        *
        * 1、延续性订单：
        *       开始时间为当前时间+1d，结束时间为开始时间+30d；
        * 2、单次订单：
        *       开始时间为当前时间+1h，结束时间为开始时间+3h，
        *       开始时间如果超过14:00，则为第二天早上8:00，因为下班时间为20:00所以结束时间不能超过20:00 (deleted 20190107 zhanghao)
        * */
        if(isProdServe == 1){//单次
            var date = new Date();
            var afterFourHoursTime = date.getTime() + 60*60*1*1000;//开始时间为当前时间+1d
            var startDateText = formatDate(new Date(afterFourHoursTime));
            var startDate = new Date(startDateText);
            var endDate = new Date(startDate.getTime() + 60*60*3*1000);//结束时间为开始时间+30d
            var endDateText = formatDate(endDate);
            $("#startTimeServer").val(startDateText);
            $("#endTimeServer").val(endDateText);
//            date.setHours(14);
//            var todayFourteenTime = date.getTime();
//            var startDateText = "";
//            if(afterFourHoursTime > todayFourteenTime){
//                date.setDate(date.getDate() + 1);
//                date.setHours(8);
//                date.setMinutes(0);
//                startDateText = formatDate(new Date(date));
//            }else{
//                startDateText = formatDate(new Date(afterFourHoursTime));
//            }
//            var startDate = new Date(startDateText);
//            var endDate = new Date(startDate.getTime() + 60*60*6*1000);
//            var endDateText = formatDate(endDate);
//            $("#startTimeServer").val(startDateText);
//            $("#endTimeServer").val(endDateText);
        }else if(isProdServe == 2){//延续
            var date = new Date();
            var afterOneDate = new Date(date.getTime() + 60*60*24*1000);
            var startDate = formatDate(afterOneDate);
            $("#startTimeServer").val(startDate);
            var newDate = new Date(date.setMonth(date.getMonth() + 1));
            var endDate = formatDate(newDate);
            $("#endTimeServer").val(endDate);
        }
    }
    /**
     * 查询服务商品
     * 王世博
     */
    function queryCityServerProduct(productId, cityCode) {
        var ctx = $("#ctx").val();
        var priceType = $("#priceType").val();
        // 查询是否享受解决方案价格
        var solution = $("#orderAddSolutionOrNot").val();
        if (solution == 1) {
            priceType = 20000005;
        }
        $.ajax({
            url: ctx + "/item/queryCityServerProduct",
            type: "post",
            datetype: "json",
            async: false,
            data: {
                productId: productId,
                cityCode: cityCode,
                status: 1,
                dictCode: priceType
            },
            success: function (data) {
                if (data.msg == "00") {
                    var result = data.list;
                    var html = "<option style='color:blue;' value='0'>...请选择...</option>";
                    $.each(result, function (i, p) {
                        html += "<option data-name='" + p.productName + "' value='" + p.productCode + "," + p.marketPrice + "," + p.productUnit + "," + p.productUnitText;
                        html += "," + p.productPriceType + "," + p.priceText + "," + p.productSpec + "," + p.categoryCode + "," + p.cateType;
                        html += "'>" + p.productName + "(" + p.productSpec + ")</option>";
                    });
                    $("#serverSelectFourth").html(html);
                } else {
                    $("#serverSelectFourth").html("<option style='color:blue;' value='0' >...无可选项...</option>");
                }
            }
        });
    }

    /**
     * 增加“是否投保”填项
     * 一级分类为【家政】基础商品【catetype】为1或2
     */
      function checkedInsure(){
        //获取一级分类
        var oneClassify = $("#oneClassify option:selected").val();
        //获取订单类型
        var isProdServe = $("#baseProduct option:selected").attr("data-isProdServe");
        if(oneClassify == "1002" && (isProdServe == "1" || isProdServe == "2")){
            $("#toubao").css("display", "inline");
        }else{
            $("#toubao").css("display", "none");
        }
    }
    //打开相似订单，查询窗口
    function similarityOrder(){
    	var orderAddUserId=$("#orderAddUserId").val();
    	openModule('/order/jsp/similarityOrder.jsp',{'orderAddUserId':orderAddUserId},null,{'width':'50%'},'similaryOrder');
    }

    //是否投保追加div
    function selectInsure(opt){
        var selected = $("#insure option:selected").val();
        if(selected == "1"){
            $("#toubaoAmount").css("display", "inline");
            $("#insureAmount").val("0.00");//是否投保切换时，清空投保金额内容
        }else{
            $("#toubaoAmount").css("display", "none");
            $("#insureAmount").val("0.00");//是否投保切换时，清空投保金额内容
        }
    }

    //时间格式化
    function formatDate(date){
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();
        var hours = date.getHours();
        var minutes = date.getMinutes();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (day >= 0 && day <= 9) {
            day = "0" + day;
        }
        if(hours >= 0 && hours <= 9){
            hours = "0" + hours;
        }
        if(minutes >= 0 && minutes <=9){
            minutes = "0" + minutes;
        }
        var currentDate = year + "-" + month + "-" + day + " " + hours + ":" + minutes;
        return currentDate;
    }
</script>
</html>

