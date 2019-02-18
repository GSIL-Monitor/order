<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <style>
        .table-condensed-order-needs tbody tr {
            text-align: left;
            line-height: 20px;
        }

        .table-condensed-order-needs tbody tr td {
            text-align: left;
            line-height: 15px;
        }
    </style>
    <script type="text/javascript" src="${ctx}/js/originNew.js"></script>
    <%-- <script type="text/javascript">
        <%
            String orderId = request.getParameter("orderId");
            String cateType = request.getParameter("cateType");
            String cityCode = request.getParameter("cityCode");
        %>
    </script> --%>
</head>
<body>
<div class="modal fade">
    <div class="modal-dialog" style="height:600px; width: 1180px;">
        <div class="modal-content" id="modalCPM">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h4 class="modal-title">推送</h4>
            </div>
            <div class="modal-body" style="height:600px; width: 980px;">
                <tbody class="dataTable_wrapper">
                <input type="hidden" id="lineStartTime">
                <input type="hidden" id="lineEndTime">
                <input type="hidden" id="timeSlot">
                <input type="hidden" id="hours">
                <input type="hidden" id="weeks">
                <input type="hidden" id="linedType">
                <div class="info-select clearfix"
                     style="float:left;width:800px;height:490px;border-top:1px solid #CCC;border-right:1px solid #CCC;">
                    <div>
                        <form class="form-inline">
                            <div class="row">
                                <div class="form-group  col-xs-12" style="color: red">
                                    <lable><p id="orderType"></p></lable>
                                </div>
                            </div>
                            <div class="row" style="display: none">
                                <div class="form-group  col-xs-6">
                                    <label><p>学历:</p>
                                        <select id="education" class="form-control">
                                            <option style='color:blue;' value=''>...请选择...</option>
                                            <option value='1'>无学历</option>
                                            <option value='2'>小学</option>
                                            <option value='3'>初中</option>
                                            <option value='4'>高中/中专</option>
                                            <option value='5'>本科/大专</option>
                                            <option value='6'>研究生</option>
                                        </select>
                                    </label>
                                </div>
                                <div class="form-group  col-xs-6">
                                    <label ><p>民族:</p>
                                        <select id="nation" name="nation" class="form-control"></select>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <label><p>服务人员姓名:</p>
                                        <input type="text" name="nameNeedsCheck" id="nameNeedsCheck"
                                               class="form-control"/>
                                    </label>
                                </div>
                                <div class="form-group  col-xs-6">
                                    <label><p>性别:</p>
                                        <input type="radio" name="sex" value="2"> 男
                                        <input type="radio" name="sex" value="1"> 女
                                        <input type="radio" checked="checked" name="sex" value=""> 不限
                                    </label>
                                </div>
                                <!-- <div class="form-group  col-xs-6">
                                    <label><p>服务人员电话:</p>
                                        <input type="text" name="mobileNeedsCheck" id="     " class="form-control"/>
                                    </label>
                                </div> -->
                            </div>
                            <div class="row">
                                <div class="form-group  col-xs-6">
                                    <label><p>省&nbsp;份:</p>
                                        <select name="orderCheckedProvinces1" id="orderCheckedProvinces1"
                                                onchange="setSelCity('orderCheckedProvinces1','orderCheckedCitys1')"
                                                class="form-control"/>
                                    </label>
                                </div>
                                <div class="form-group  col-xs-6">
                                    <label><p>城&nbsp;市:</p>
                                        <select name="orderCheckedCitys1" id="orderCheckedCitys1" class="form-control">
                                            <option value="">...请选择...</option>
                                        </select>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <label><p>年龄:</p>
                                        <input type="text" name=minAge id="minAge" class="form-control"
                                               style="width:60px;">
                                        &nbsp;至&nbsp;
                                        <input type="text" name="maxAge" id="maxAge" class="form-control"
                                               style="width:60px;">
                                    </label>
                                </div>
                                <div class="form-group  col-xs-6">
                                    <label><p>工作年限:</p>
                                        <input type="text" name=minWorkAge id="minWorkAge" class="form-control"
                                               style="width:60px;">
                                        &nbsp;至&nbsp;
                                        <input type="text" name="maxWorkAge" id="maxWorkAge" class="form-control"
                                               style="width:60px;">
                                    </label>
                                    <%--<label style="display: none"><p>性别:</p>--%>
                                        <%--<input type="radio" name="sex" value="2"> 男--%>
                                        <%--<input type="radio" name="sex" value="1"> 女--%>
                                        <%--<input type="radio" checked="checked" name="sex" value=""> 不限--%>
                                    <%--</label>--%>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <%--<label><p>工作年限:</p>--%>
                                        <%--<input type="text" name=minWorkAge id="minWorkAge" class="form-control"--%>
                                               <%--style="width:60px;">--%>
                                        <%--&nbsp;至&nbsp;--%>
                                        <%--<input type="text" name="maxWorkAge" id="maxWorkAge" class="form-control"--%>
                                               <%--style="width:60px;">--%>
                                    <%--</label>--%>
                                        <label><p>级别:</p>
                                            <select id="personelLevel" name="personelLevel" class="form-control"></select>
                                        </label>
                                </div>
                                <div class="form-group  col-xs-6">
                                    <label style="display: none">
                                        <p>属相：</p>
                                        <select id="pd_zodiac2" name="p_zodiac" class="form-control">
                                            <option value="" data-name="">请选择</option>
                                            <option value="1" data-name="鼠">鼠</option>
                                            <option value="2" data-name="牛">牛</option>
                                            <option value="3" data-name="虎">虎</option>
                                            <option value="4" data-name="兔">兔</option>
                                            <option value="5" data-name="龙">龙</option>
                                            <option value="6" data-name="龙">蛇</option>
                                            <option value="7" data-name="马">马</option>
                                            <option value="8" data-name="马">羊</option>
                                            <option value="9" data-name="羊">猴</option>
                                            <option value="10" data-name="鸡">鸡</option>
                                            <option value="11" data-name="狗">狗</option>
                                            <option value="12" data-name="猪">猪</option>
                                        </select>
                                    </label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group col-xs-12">
                                    <label style="display: none">
                                        <p>星座：</p>
                                        <select name="constellation" id="constellation" class="form-control">
                                            <option value='' data-name="">请选择</option>
                                            <option value='1' title="1.20-2.18" data-name="水瓶座">水瓶座</option>
                                            <option value='2' title="2.19-3.20" data-name="双鱼座">双鱼座</option>
                                            <option value='3' title="3.21-4.19" data-name="白羊座">白羊座</option>
                                            <option value='4' title="4.20-5.20" data-name="金牛座">金牛座</option>
                                            <option value='5' title="5.21-6.21" data-name="双子座">双子座</option>
                                            <option value='6' title="6.22-7.22" data-name="巨蟹座">巨蟹座</option>
                                            <option value='7' title="7.23-8.22" data-name="狮子座">狮子座</option>
                                            <option value='8' title="8.23-9.22" data-name="处女座">处女座</option>
                                            <option value='9' title="9.23-10.23" data-name="天秤座">天秤座</option>
                                            <option value='10' title="10.24-11.22" data-name="天蝎座">天蝎座</option>
                                            <option value='11' title="11.23-12.21" data-name="射手座">射手座</option>
                                            <option value='12' title="12.22-1.19" data-name="摩羯座">摩羯座</option>
                                        </select>
                                    </label>
                                </div>
                                <%--<div class="form-group  col-xs-6">--%>
                                <%--<label>--%>
                                <%--<p>籍贯省：</p>--%>
                                <%--<select id="origin1"--%>
                                <%--onchange="queryCitys({'levels':2,'code':this.value},'originCity');"--%>
                                <%--data-rule="籍贯省:required;" placeholder="籍贯省"--%>
                                <%--class="form-control"></select>--%>
                                <%--</label>--%>
                                <%--</div>--%>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p>籍贯(南北区)：</p>
                                        <select id="origin1" data-rule="籍贯:required;"
                                                onchange="checkOrigin(this)"
                                                class="form-control">
                                            <option value="">--请选择--</option>
                                            <option value="101001,101002,101003,101004,101005,101006,101007,101008,101015,101027,101028,101029,101030,101031">北区</option>
                                            <option value="101016,101010,101012,101017,101023,101022,101026,101025,101024,101018,101014,101019,101020,101013,101011,101009,101021,101032,101033,101034">南区</option>
                                        </select>
                                    </label>
                                </div>
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p>籍贯省：</p>
                                        <select id="originCity" name="p_origin" data-rule="籍贯省:required;"
                                                class="form-control">
                                            <option selected="selected" value="">--请选择--</option>
                                        </select>
                                    </label>
                                </div>
                            </div>
                            <!-- <div class="row">
                                  <div class="form-group  col-xs-6">
                                      <label><p>籍贯:</p>
                                          <select id="origin" class="form-control"></select>
                                      </label>
                                  </div>
                                  <div class="form-group  col-xs-6">
                                      <label><p>工种:</p>
                                          <select id="personWorkTypeAll" class="form-control"></select>
                                      </label>
                                  </div>
                            </div> -->
                            <!-- <div class="row">
                                  <div class="form-group  col-xs-10">
                                      <label><p>空档期:</p>
                                          <input type="text" name="startTimeOrderNeeds" id="startTimeOrderNeeds" class="Wdate form-control"
                                              onfocus="WdatePicker({minDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
                                                &nbsp;至&nbsp;
                                        <input type="text" name="endTimeOrderNeeds" id="endTimeOrderNeeds" class="Wdate form-control"
                                            onfocus="WdatePicker({minDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
                                      </label>
                                  </div>
                                  <div class="form-group  col-xs-2">
                                      <button type="button" onclick="queryPersonnel(0,5);" class="xinzeng btn btn-sm btn-default">查询</button>
                                  </div>
                            </div> -->
                            <div class="row">
                                <div class="form-group  col-xs-10">
                                    <label><p>推送排期:</p>
                                        <input type="text" name="startTimeOrderNeeds" id="startTimeOrderNeeds"
                                               class="Wdate form-control"
                                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'endTimeOrderNeeds\')}'})"/>
                                        &nbsp;至&nbsp;
                                        <input type="text" name="endTimeOrderNeeds" id="endTimeOrderNeeds"
                                               class="Wdate form-control"
                                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'startTimeOrderNeeds\')}'})"/>
                                    </label>
                                </div>
                                <div class="form-group  col-xs-2">
                                    <button type="button" onclick="queryPersonnel(0,5);"
                                            class="xinzeng btn btn-sm btn-default">查询
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="panel-content table-responsive"
                         style="overflow-y:auto; height:250px;position: relative;z-index:9999;">
                        <table class="table table-hover table-striped" style="width:950px;">
                            <thead>
                            <tr>
                                <th><input name="odIdsPerson" id="odIdsPerson" onclick="checkboxAll(this)"
                                           type="checkbox"></th>
                                <th>序号</th>
                                <th>姓名</th>
                                <th>性别</th>
                                <th>所属管家</th>
                                <th>所属城市</th>
                                <th>管家联系方式</th>
                                <th>是否员工制</th>
                                <th>籍贯</th>
                                <th>年龄</th>
                                <th>学历</th>
                            </tr>
                            </thead>
                            <tbody id="listBodyPerson">
                            </tbody>
                        </table>
                        <div class="clearfix">
                            <ul class="pagination pagination-sm navbar-right" id="pageNeedsDiv"></ul>
                        </div>
                        <!-- 	                    <div class="clearfix" style="width:96%;margin:0 auto;">
                                                    <ul style="width:700px;position: absolute;bottom: 30px;left: 133px;z-index: 0;" class="pagination pagination-sm navbar-right" id="pageNeedsDiv"></ul>
                                                </div> -->
                    </div>
                </div>
                </tbody>
                <div style="margin-left:800px; width:350px; border:1px solid #CCC; border-bottom:0px;color:#000;">
                    <div>
                        <table class="table-condensed table-condensed-order-needs" style="font-family: " Helvetica Neue
                        ", Helvetica, Arial, sans-serif!important; ">
                        <tr>
                            <td width="35%">
                                <input type="hidden" id="needPersonnelId" value="">
                                姓名：<span id="nameNeeds"></span>
                            </td>
                            <td width="35%">
                                性别：<span id="sexNeeds"></span>
                            </td>
                            <td width="30%" rowspan="4">
                                <img src="${ctx}/images/touxiang.png" style="width:90px; height:90px;"
                                     id="orderNeedsImg"/>
                                &nbsp;&nbsp;
                                <!-- <div>
                                    <input type="hidden" id="downloadEmpFileCardById">
                                    <button style='width:90px;' class='btn btn-primary btn-xs' type='button' onclick="downloadEmpFileCardById()" >下载信息卡</button>
                                </div> -->
                            </td>
                        </tr>
                        <tr>
                            <td>
                                年龄：<span id="ageNeeds"></span>
                            </td>
                            <td>
                                属相：<span id="zodiacNeeds"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                民族：<span id="nationNeeds"></span>
                            </td>
                            <td>
                                学历：<span id="educationNeeds"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                籍贯：<span id="originNeeds"></span>
                            </td>
                            <td>
                                婚姻状况：<span id="isMarryNeeds"></span>
                            </td>
                        </tr>
                        <!-- <tr>
                            <td colspan="2">
                                联系方式：<span id="mobileNeeds"></span>
                            </td>
                        </tr> -->
                        <tr>
                            <td colspan="2">
                                证件号：<span id="idCardNumNeeds"></span>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                现住址：<span id="userAddressNeeds"></span>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                原籍地址：<span id="addressNeeds"></span>
                            </td>
                        </tr>
                        </table>
                    </div>
                    <div style="border-top:1px solid #CCC">
                        <table class="table-condensed table-condensed-order-needs" style="width:100%;">
                            <tr style="height:30px;">
                                <td width="45%">工种：<span id="workTypeTextNeeds"></span></td>
                                <td width="55%">级别：<span id="workLevelTextNeeds"></span></td>
                            </tr>
                            <tr style="height:30px;">
                                <td>从事家政年限:<span id="workingLifeNeeds"></span></td>
                                <td>是否怕宠物：<span id="fearPetNeeds"></span></td>
                            </tr>
                            <tr>
                                <td colspan="3">有何特长：<span id="specialtyNeeds"></span></td>
                            </tr>
                            <tr>
                                <td>所属管家：<span id="realNameNeeds"></span></td>
                                <td>管家电话：<span id="phoneNeeds"></span></td>
                            </tr>
                            <tr>
                                <td colspan="3"><button type="button" class="btn btn-sm btn-primary" onclick="personnelDetailCard()">查看信息卡</button></td>
                            </tr>
                        </table>
                    </div>
                </div>
                </tbody>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-primary" onclick="selPersonLine()">查看排期</button>
                <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal">关闭</button>
                <button type="button" id="makeMateButton" class="btn btn-sm btn-primary" onclick="setPersons()">确定匹配</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
		//queryCitys(null, "origin1");
        //queryCitys(null,"workTwo"); 
        selectPersonnelLevel();
        selectOrderWorkType();
        nationFun();
        queryOriginDictionary("", "origin", null);
        setSelProvinceCitys("101", 6, "orderCheckedProvinces1");
        var cityCode = "${param.cityCode}";
        $("#orderCheckedProvinces1").val(cityCode.substring(0, 6)).trigger("change");
        $("#orderCheckedCitys1").val(cityCode);
        //queryWorkTypeAll("personWorkTypeAll",null);
        //queryPersonnel(0,5);

        //20180927加载南北区查询条件
        var origins = {
            "101001,101002,101003,101004,101005,101006,101007,101008,101015,101027,101028,101029,101030,101031": [
                {id: 101001, value: "北京"},
                {id: 101002, value: "天津"},
                {id: 101003, value: "河北省"},
                {id: 101004, value: "山西省"},
                {id: 101005, value: "内蒙古自治区"},
                {id: 101006, value: "辽宁省"},
                {id: 101007, value: "吉林省"},
                {id: 101008, value: "黑龙江省"},
                {id: 101015, value: "山东省"},
                {id: 101027, value: "陕西省"},
                {id: 101028, value: "甘肃省"},
                {id: 101029, value: "青海省"},
                {id: 101030, value: "宁夏回族自治区"},
                {id: 101031, value: "新疆维吾尔自治区"}
            ],
            "101016,101010,101012,101017,101023,101022,101026,101025,101024,101018,101014,101019,101020,101013,101011,101009,101021,101032,101033,101034": [
                {id: 101016, value: "河南省"},
                {id: 101010, value: "江苏省"},
                {id: 101012, value: "安徽省"},
                {id: 101017, value: "湖北省"},
                {id: 101023, value: "四川省"},
                {id: 101022, value: "重庆"},
                {id: 101026, value: "西藏自治区"},
                {id: 101025, value: "云南省"},
                {id: 101024, value: "贵州省"},
                {id: 101018, value: "湖南省"},
                {id: 101014, value: "江西省"},
                {id: 101019, value: "广东省"},
                {id: 101020, value: "广西壮族自治区"},
                {id: 101013, value: "福建省"},
                {id: 101011, value: "浙江省"},
                {id: 101009, value: "上海"},
                {id: 101021, value: "海南省"},
                {id: 101032, value: "台湾"},
                {id: 101033, value: "香港特别行政区"},
                {id: 101034, value: "澳门特别行政区"}]
        }
        checkOrigin = function(e){
            var target = e;
            var value = target.value;
            $("#originCity").html('<option selected="selected" value>--请选择--</option>');
            origins[value].forEach(function (item) {
                $("#originCity").append('<option value="' + item.id + '">' + item.value + '</option>')
            });
        }
    })
    /**
     * 分页跳转使用
     */
    function listBodyPerson(num, pageCount) {
        $("#listBodyPerson").empty();
        queryPersonnel(num, pageCount);
        /*表格点击行高亮*/
        //trColor("#listBodyPerson > tr");
    }
    /**
     * 分页跳转使用
     */
    function toPage(num) {
        if (null == num || "" == num || isNaN(parseInt(num))) {
            alert("请输入数字");
            return;
        }
        var pageCount = $("#pageCount").val();
        if (pageCount == "" || pageCount == undefined) {
            pageCount = 3;
        }
        $("#listBodyPerson").empty();
        queryPersonnel(num, pageCount);
    }
    //民族查询
    function nationFun() {
        var nationStr = "汉族;阿昌族;白族;保安族;布朗族;布依族;朝鲜族;达斡尔族;傣族;德昂族;侗族;东乡族;独龙族;鄂伦春族;俄罗斯族;鄂温克族;高山族;仡佬族;哈尼族;哈萨克族;赫哲族;回族;基诺族;京族;景颇族;柯尔克孜族;拉祜族;黎族;傈僳族;珞巴族;满族;毛南族;门巴族;蒙古族;苗族;仫佬族;纳西族;怒族;普米族;羌族;撒拉族;畲族;水族;塔吉克族;塔塔尔族;土族;土家族;佤族;维吾尔族;乌兹别克族;锡伯族;瑶族;彝族;裕固族;藏族;壮族";
        var option = "<option value=''>...请选择...</option>";
        $(nationStr.split(";")).each(function (i) {
            option += "<option >" + this + "</option>";
        });
        $("#nation").html(option);
    }
    //全选
    function checkboxAll(e) {
        $("input[name='odIdPerson']:enabled").prop("checked", document.getElementById("odIdsPerson").checked == true);
    }
    // 复选框取值
    function checkboxVale() {
// 	  var ids='';
// 	  $('input[name="odIdPerson"]:checked').each(function(){
// 		  ids += $(this).val() + ',';
// 	  });
// 	  ids = ids.substring(0, ids.length-1);
// 	  return ids;
        var result = new Array();
        $("[name = odIdPerson]:checkbox").each(function () {
            if ($(this).is(":checked")) {
                result.push($(this).attr("value"));
            }
        });
        return result.join(",");

    }
    var orderId = "${param.orderId}";
    var checkOrderType = "${param.orderType}";
    var cateType = "${param.cateType}";
    var grade;
    var orderType;
    // 匹配人员列表
    function queryPersonnel(num, pageCount) {
        //20180927根据订单类型及种类规定可匹配人员管理状态
        var managerMentStatus;
        if(cateType == "2" && checkOrderType != "100200010001"){//延续性非月嫂查询“可匹配”状态人员
            managerMentStatus = "17";
        }else{//其余查询五种状态人员
            managerMentStatus = "1,4,9,10,17";
        }

        var ctx = $("#ctx").val();
		//var origin = $("#origin").val();
        var pd_zodiac2 = $("#pd_zodiac2").find('option:selected').attr('data-name'); //属相
        var education = $("#education").val();
        var nation = $("#nation").val();
        var minAge = $("#minAge").val();
        var maxAge = $("#maxAge").val();
        var constellation = $("#constellation").val(); //星座
        var minWorkAge = $("#minWorkAge").val(); //工作年限
        var maxWorkAge = $("#maxWorkAge").val();
        var levelId = $("#personelLevel").val(); //等级id码
        var sex = $('input:radio[name=sex]:checked').val();
        var pid = parent.$("#checkedOrderId").val();
        //var orderType = $("#personWorkTypeAll").val();
        var name = $("#nameNeedsCheck").val();
        var mobile = $("#mobileNeedsCheck").val();
        //var cityCode = "${param.cityCode}";
        var cityCode = "";
        var city = $("#orderCheckedCitys1").val();
        if (city == "") {
            cityCode = $("#orderCheckedProvinces1").val();
        } else {
            cityCode = city;
        }
        var startTime = "";
        var endTime = "";
        var timeSolt = "";
        selLine();//排期
        var sTime;
        var eTime;
        //日期天数
        if ($("#startTimeOrderNeeds").val() == "" || $("#startTimeOrderNeeds").val() == null) {
            sTime = "";
            startTime = $("#lineStartTime").val().split(" ")[0].replace(new RegExp("-", "gm"), "");
        } else {
            startTime = $("#startTimeOrderNeeds").val().split(" ")[0].replace(new RegExp("-", "gm"), "");
        }
        if ($("#endTimeOrderNeeds").val() == "" || $("#endTimeOrderNeeds").val() == null) {
            eTime = "";
            endTime = $("#lineEndTime").val().split(" ")[0].replace(new RegExp("-", "gm"), "");
        } else {
            endTime = $("#endTimeOrderNeeds").val().split(" ")[0].replace(new RegExp("-", "gm"), "");
        }
        //时间段
        var linedType = $("#linedType").val();
        var weeks = "";
        if (linedType == 1) {
            //单次服务  结束时间就是开始时间
            timeSolt = $("#timeSlot").val();
            endTime = startTime;
            weeks = "";
        } else if (linedType == 2) {
            //钟点工
            timeSolt = $("#timeSlot").val();
            weeks = $("#weeks").val();
        } else if (linedType == 3) {
            timeSolt = "1";
            weeks = "";
        }
        /* if(($("#startTimeOrderNeeds").val()==""||$("#startTimeOrderNeeds").val()==null)&&($("#endTimeOrderNeeds").val()==""||$("#endTimeOrderNeeds").val()==null)){
         if($("#hours").val()==""||$("#hours").val()==null){
         timeSolt="1";
         }else if($("#weeks").val()==""||$("#weeks").val()==null){
         //单次服务  结束时间就是开始时间
         timeSolt=$("#timeSlot").val();
         endTime=startTime;
         }else {
         //钟点工
         timeSolt=$("#timeSlot").val();
         }
         }else{
         timeSolt=$("#startTimeOrderNeeds").val().split(" ")[1]+"-"+$("#endTimeOrderNeeds").val().split(" ")[1];
         } */

        //20180929籍贯条件如果选择省那么查询省，如果没选省按照南北大区条件检索
        var originP = $("#origin1").val();//籍贯南北区
        var originCity = $("#originCity").val(); //籍贯省
        var origin = originCity||originP||"";
        /**
         * condition:精确查找flag
         * 2：模糊 1：精确
         */
        var condition = 2;
        if (name.length > 0) {
            condition = 1;
        }
        $.ajax({
            url: ctx + "/itemDetailServer/queryOrderNeedServer?curPage=" + num + "&pageCount=" + pageCount,
            data: {
                origin: origin, //籍贯
                education: education,
                nation: nation,
                minAge: minAge,
                maxAge: maxAge,
                minWorkAge: minWorkAge,//工作年限
                maxWorkAge: maxWorkAge,
                levelId: levelId, //等级id
                constellation: constellation,//星座
                zodiac: pd_zodiac2,//属相
                sex: sex,
                valid: 1,
                type: 2,
                pid: pid,
                startTime: startTime,
                endTime: endTime,
                name: name,
                mobile: mobile,
                orderId: orderId,
                cityCode: cityCode,
                timeslot: timeSolt,
                week: weeks,
                linedType: linedType,
                condition: condition,
                checkOrderType: checkOrderType, //20180927增加订单类型参数
                managerMentStatus:managerMentStatus //20180928增加人员管理状态
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                orderType = data.orderType;
                grade = data.grade;
                $("#pageNeedsDiv").pagination(data.page, listBodyPerson);
                var pageCount = data.page.pageCount;
                var curPage = data.page.curPage;
                var total = curPage * pageCount;
                var html = "";
                var id = "";
                var img = "";
                var k = 0;
                if (data.msg == "00") {
                    var num =
                            $.each(data.list, function (i, v) {
                                if (i == 0) {
                                    $("#fistPersonId").val(v.id);
                                    id = v.id;
                                    img = v.originalAddress;
                                }
                                num = i + 1;
                                html += "<tr width='98%' onclick='checkPersion(" + v.id + "," + i + ")'><td>";
                                html += "<input name='odIdPerson' type='checkbox' value='" + v.id + "'>";
                                html += "</td><td>" + (total + num - pageCount);
                                html += "</td><td>" + v.name;
                                html += "</td><td>" + (v.sex == 1 ? "女" : "男");
                                html += "</td><td>" + v.realName;
                                html += "</td><td>" + v.city;
                                html += "</td><td>" + v.phone;
                                html += "</td><td>" + v.staffType;
                                html += "</td><td>" + v.origin;
                                html += "</td><td>" + v.age;
                                html += "</td><td>" + v.educationText;
                                html += "</td></tr>";
                                k++;
                            })
                }
                $("#listBodyPerson").html(html);
                /*表格点击行高亮*/
                trColor("#listBodyPerson > tr");
                if (k > 0) {
                    $("#listBodyPerson > tr").eq(0).toggleClass('info').siblings().removeClass('info').css('cursor', 'pointer');
                    checkPersion(id, 0);
                } else {
                    cleanOrderNeedsInput();
                }
            }
        });
    }
    //取到订单详细信息
    function checkPersion(id, index) {
        var ctx = $("#ctx").val();
        $.ajax({
            url: ctx + "/itemDetailServer/queryOrderNeedServer",
            data: {
                orderId: orderId,
                id: id,
                valid: 1,
                type: 3
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg == "00") {
                    var fearPetText = "";
                    var num = $.each(data.list, function (i, v) {
                        $("#needPersonnelId").val(id);//服务人员ID
                        $("#nameNeeds").text(v.name);
                        $("#sexNeeds").text(v.sex == 1 ? "女" : "男");
                        $("#ageNeeds").text(v.age);
                        $("#zodiacNeeds").text(v.zodiac);
                        $("#nationNeeds").text(v.nation);
                        $("#educationNeeds").text(v.educationText);
                        $("#originNeeds").text(v.origin);
                        $("#isMarryNeeds").text(v.isMarry);
                        $("#idCardNumNeeds").text(v.idCardNum);
                        $("#addressNeeds").text(v.address);
                        $("#userAddressNeeds").text(v.userAddress);
                        $("#workingLifeNeeds").text(v.workingLife);
                        if (v.fearPet == 1) {
                            fearPetText = "是";
                        } else if (v.fearPet == 2) {
                            fearPetText = "否";
                        } else if (v.fearPet == 3) {
                            fearPetText = "不喜欢";
                        }
                        $("#fearPetNeeds").text(fearPetText);
                        $("#specialtyNeeds").text(v.specialty);
                        $("#realNameNeeds").text(v.realName);
                        //$("#mobileNeeds").text(v.mobile);
                        $("#phoneNeeds").text(v.phone);
                        $("#workTypeTextNeeds").text(v.workTypeText);
                        $("#workLevelTextNeeds").text(v.workLevelText);
                        $("#orderNeedsImg").attr("src", v.originalAddress);
                        $("#downloadEmpFileCardById").val(v.id);
                    })
                } else {
                    cleanOrderNeedsInput();
                }
            }
        });
    }

    // 清空显示框
    function cleanOrderNeedsInput() {
        $("#nameNeeds").text("");
        $("#sexNeeds").text("");
        $("#ageNeeds").text("");
        $("#zodiacNeeds").text("");
        $("#nationNeeds").text("");
        $("#educationNeeds").text("");
        $("#originNeeds").text("");
        $("#isMarryNeeds").text("");
        $("#idCardNumNeeds").text("");
        $("#addressNeeds").text("");
        $("#userAddressNeeds").text("");
        $("#workingLifeNeeds").text("");
        $("#fearPetNeeds").text("");
        $("#specialtyNeeds").text("");
        $("#realNameNeeds").text("");
        //$("#mobileNeeds").text("");
        $("#phoneNeeds").text("");
        $("#workTypeTextNeeds").text("");
        $("#workLevelTextNeeds").text("");
        $("#orderNeedsImg").attr("src", "");
    }

    function setPersons() {
        $("#makeMateButton").attr("style","display:none;");
        if (!matchingPersonnel()) {
            $("#makeMateButton").attr("style","display:inline;");
            return;
        }
        var ctx = $("#ctx").val();
        var ids = checkboxVale();
        var orderId = "${param.orderId}";
        var personNumber = "${param.personNumber}";
        var cateType = "${param.cateType}";
        if (ids == null || ids == "" || ids == "undefined") {
            $.alert({text: "请选择服务人员！"});
            $("#makeMateButton").attr("style","display:inline;");
            return;
        }

        //20180928确定匹配时[延续性非月嫂]订单校验选择服务人员管理状态是否可用
        if(cateType == "2" && checkOrderType != "100200010001"){
            if(!checkOrderTrue(orderId,cateType,ids)){
                $("#makeMateButton").attr("style","display:inline;");
                return;
            }
        }

        //var lineStartTime=$("#lineStartTime").val();
        var lineStartTime = "";
        //var lineEndTime=$("#lineEndTime").val();
        var lineEndTime = "";
        var timeSlot = $("#timeSlot").val();
        var hours = $("#hours").val();
        var weeks = $("#weeks").val();


        if ($("#startTimeOrderNeeds").val() == "" || $("#startTimeOrderNeeds").val() == null) {
            lineStartTime = $("#lineStartTime").val().split(" ")[0];
        } else {
            lineStartTime = $("#startTimeOrderNeeds").val().split(" ")[0];
        }
        if ($("#endTimeOrderNeeds").val() == "" || $("#endTimeOrderNeeds").val() == null) {
            lineEndTime = $("#lineEndTime").val().split(" ")[0];
        } else {
            lineEndTime = $("#endTimeOrderNeeds").val().split(" ")[0];
        }
        //判断选择时间是否大于订单排期。如果大于选择订单结束时间
        if (lineEndTime > $("#lineEndTime").val().split(" ")[0]) {
            lineEndTime = $("#lineEndTime").val().split(" ")[0];
        }
        //判断选择时间是否小于订单排期。如果小于选择订单开始时间
        if (lineStartTime < $("#lineStartTime").val().split(" ")[0]) {
            lineStartTime = $("#lineStartTime").val().split(" ")[0];
        }

        //20180927更改匹配确认弹窗
        var personnelIds = [];
        var queryPersonnelText = "";
        var text = "";
        $("input[name = odIdPerson]:checked").each(function (i,n) {
            var personnelId = $(this).val();
            personnelIds[i] = (personnelId);
        });
        $.ajax({
            url:ctx+"/itemDetailServer/checkPersonnelText",
            data:{"personnelIds":personnelIds},
            type:"post",
            async:false,
            success:function(data){
                if(data.msg == "00"){
                    $.each(data.personnels,function(i,n){
                        text += "请确认匹配人员信息:<br/>";
                        text += "姓名:"+ n.name +"<br/>";
                        text += "城市:"+ n.inCity +"<br/>";
                        text += "电话:"+ n.mobile.substring(0,3)+"****"+n.mobile.substring(7,11) +"<br/>";
                        text += "身份证号:"+ n.idCardNum +"<br/>";
                        text += "--------------------------------------------------------<br/>";
                    });
                }
            }
        });
		
        //单次订单
        if (cateType == 1) {
        	/** 周鑫   2018-11-26 修改推送的时间不能大于**/
            var orderLineDay = "${param.orderLineDay}";
            var orderLineTime = "${param.orderLineTime}";
            var orderTimes=orderLineTime.split("-");
            var startTime=$("#startTimeOrderNeeds").val();
            var endTime=$("#endTimeOrderNeeds").val();
            var orderDay=new Date(orderLineDay+" "+orderTimes[1]);
        	/** 查询的开始时间  **/
            if(startTime!=null){
            	var stratA=new Date(startTime);
            	if(stratA.getTime()>orderDay.getTime()){
            		$.alert({text: "推送排期大于订单排期！"});
                	return;
            	}
            }
            if(endTime!=null){
            	var endA=new Date(endTime);
            	if(endA.getTime()>orderDay.getTime()){
            		$.alert({text: "推送排期大于订单排期！"});
                	return;
            	}
            }
            /** 结束  **/
          
            var orderStatus = parent.$("#checkedOrderStatus").val();
            var result = new Array();
            $("#rightNowPersonOne [name = personCheck]").each(function () {
                if ($(this).attr("data-status") == 4 || $(this).attr("data-status") == 8) { //4已匹配 8已上户
                    result.push($(this).attr("value"));
                }
            });
            var perNumber = personNumber - result.length
            /* if(ids.split(",").length>perNumber){
             $.alert({ text : "最多可以匹配"+perNumber+"个服务人员" });
             return ;
             }  */
            $("#workLevelTextNeeds").val();
            //推送面试表
            $.confirm({
                        text: text, callback: function (re) {
                            if (re) {
                                $.ajax({
                                    url: ctx + "/itemInterview/insertItemInterview",
                                    data: {
                                        orderId: orderId,
                                        ids: ids,
                                        orderStatus: orderStatus,
                                        type: 11,
                                        lineStartTime: lineStartTime,
                                        timeSlot: timeSlot,
                                        hours: hours
                                        //orderType : orderType,//订单商品等级
                                        //grade : grade
                                    },
                                    type: "post",
                                    dataType: "json",
                                    async: false,
                                    success: function (data) {
                                        if (data.msg == "00") {
                                            parent.$("#checkedOrderStatus").val(4);
                                            $.alert({text: "需求推送成功！"});
                                            getPersonInfoOne();
                                        }
                                    }
                                })
                            }
                        }
                    }
            );
        } else if (cateType == 2) {
        	/** 周鑫   2018-11-26 修改推送的时间不能大于**/
            var orderLineDay = "${param.orderLineDay}";
            var orderDay=new Date(orderLineDay);
            var startTime=$("#startTimeOrderNeeds").val();
            var endTime=$("#endTimeOrderNeeds").val();
           	/** 查询的开始时间  **/
            if(startTime!=null){
            	var stratA=new Date(startTime.substring(0,10));
            	if(stratA.getTime()>orderDay.getTime()){
            		$.alert({text: "推送排期大于订单排期！"});
                	return;
            	}
            }
           	/** 结束时间  **/
            if(endTime!=null){
            	var endA=new Date(endTime.substring(0,10));
            	if(endA.getTime()>orderDay.getTime()){
            		$.alert({text: "推送排期大于订单排期！"});
                	return;
            	}
            }
            /** 结束  **/
            var result = new Array();
            $("#rightNowPerson [name = personCheck]").each(function () {
                if ($(this).attr("data-status") == 1) { //1未处理
                    result.push($(this).attr("value"));
                }
            });
            if (parseInt(ids.split(",").length) + parseInt(result.length) > 5) {
                $.alert({text: "最多可以匹配5个服务人员"});
                $("#makeMateButton").attr("style","display:inline;");
                return;
            }
            $.confirm({
                text: text, callback: function (re) {
                    if (re) {
                        $.ajax({
                            url: ctx + "/itemInterview/insertItemInterview",
                            data: {
                                orderId: orderId,
                                ids: ids,
                                type: 2,
                                lineStartTime: lineStartTime,
                                lineEndTime: lineEndTime,
                                week: weeks,
                                timeSlot: timeSlot,
                                hours: hours
                            },
                            type: "post",
                            dataType: "json",
                            async: false,
                            success: function (data) {
                                if (data.msg == "00") {
                                    $.alert({text: "需求推送成功！"});
                                    //推送完成重新查询匹配的服务人员
                                    getPersonInfo();
                                }else{
                                    $("#makeMateButton").attr("style","display:block;");
                                }
                            }
                        })
                    }
                }
            })

        }
        closeModule('orderNeedsModule');
    }
    //查看订单排期
    function selLine() {
        $.ajax({
            url: ctx + "/itemDetailServer/loadOrderServerLined",
            type: "post",
            datetype: "json",
            async: false,
            data: {
                id: "${param.orderId}"
            },
            success: function (data) {
                if (data.msg == "00") {
                    $("#lineStartTime").val(numberToDatestr(data.itemDetailServer.startTime, 12))
                    $("#lineEndTime").val(numberToDatestr(data.itemDetailServer.endTime, 12))
                    $("#timeSlot").val(data.itemDetailServer.hours)
                    $("#hours").val(data.itemDetailServer.days)
                    $("#weeks").val(data.itemDetailServer.weeks)
                    $("#linedType").val(data.itemDetailServer.linedType)
                }
            }
        });
    }
    //查看服务人员排期
    function selPersonLine() {
        var ctx = $("#ctx").val();
        var ids = checkboxVale();
        if (ids.split(",").length > 1 || ids == "" || ids == null) {
            $.alert({text: "最多可以查看一个服务人员排期"});
            return;
        }
        openModule("${ctx}/jsp/server/scheduling.jsp?",
                {"personId": ids},
                "", {}, "scheduling");
    }

    /*判断是否确定匹配*/
    function matchingPersonnel() {
        var flag = true;
        var ctx = $("#ctx").val();
        var orderId = "${param.orderId}";
        var startTime = "";
        var endTime = "";
        //日期天数
        if ($("#startTimeOrderNeeds").val() == "" || $("#startTimeOrderNeeds").val() == null) {
            startTime = $("#lineStartTime").val().split(" ")[0].replace(new RegExp("-", "gm"), "");
        } else {
            startTime = $("#startTimeOrderNeeds").val().split(" ")[0].replace(new RegExp("-", "gm"), "");
        }
        if ($("#endTimeOrderNeeds").val() == "" || $("#endTimeOrderNeeds").val() == null) {
            endTime = $("#lineEndTime").val().split(" ")[0].replace(new RegExp("-", "gm"), "");
        } else {
            endTime = $("#endTimeOrderNeeds").val().split(" ")[0].replace(new RegExp("-", "gm"), "");
        }
        //时间段
        var linedType = $("#linedType").val();
        var weeks = "";
        var timeSolt = "";
        if (linedType == 1) {
            //单次服务  结束时间就是开始时间
            timeSolt = $("#timeSlot").val();
            endTime = startTime;
            weeks = "";
        } else if (linedType == 2) {
            //钟点工
            timeSolt = $("#timeSlot").val();
            weeks = $("#weeks").val();
        } else if (linedType == 3) {
            timeSolt = "1";
            weeks = "";
        }
        var c = $("#listBodyPerson").find("input[name=odIdPerson]:checked");
        if (c.size() > 0) {
            for (var i = 0; i < c.length; i++) {
                var pid = c[i].value;
                $.ajax({
                    url: ctx + "/itemDetailServer/matchingPersonnel",
                    type: "post",
                    datetype: "json",
                    async: false,
                    data: {
                        orderId: orderId,
                        pid: pid,
                        startTime: startTime,
                        endTime: endTime,
                        timeslot: timeSolt,
                        week: weeks,
                        linedType: linedType
                    },
                    success: function (data) {
                        if (data.msg == "00") {
                            var v = data.orderBaseModel;
                            if (v.interviewTypeText) {
                                $.alert({text: "服务人员" + v.name + "在当前订单最新状态(" + v.interviewTypeText + "),不能推送！"});
                                flag = false;
                                return;
                            }
                            if (v.isWorkLevel == 0) {
                                $.alert({text: "服务人员" + v.name + "工种级别不匹配,不能推送！"});
                                flag = false;
                                return;
                            }
                            if (v.isSchedule) {
                                $.alert({text: "服务人员" + v.name + "排期不匹配,不能推送！ 冲突订单信息：" + v.isSchedule});
                                flag = false;
                            }
                        }
                    }
                });
                if (!flag) {
                    break;
                }
            }
        }
        return flag;
    }

    //查看订单工种
    function selectOrderWorkType() {
        var orderId = "${param.orderId}"
        $.ajax({
            url: ctx + "/itemDetailServer/selectOrderWorkType",
            type: "post",
            datetype: "json",
            async: false,
            data: {
                orderId: orderId
            },
            success: function (data) {
                if (data.msg == "00") {
                    $("#orderType").html("当前订单工种级别:&nbsp;&nbsp;" + data.orderBaseModel.orderTypeText + "&nbsp;&nbsp;&nbsp;&nbsp;" + data.orderBaseModel.gradeText);
                }
            }
        });
    }

    //20180928确定匹配时[延续性非月嫂]订单校验选择服务人员管理状态是否可用
    function checkOrderTrue(orderId,cateType,ids){
        var flag = true;
        $.ajax({
            url:ctx+"/itemDetailServer/checkOrderTrue",
            async:false,
            data:{
                ids:ids,
                orderId:orderId,
                cateType:cateType
            },
            type:"post",
            success:function(data){
                if(data.msg == "01"){
                    $.alert({text: data.text});
                    flag = false;
                }else if(data.msg == "-1"){
                    $.alert({text: data.text});
                    flag = false;
                }
            }
        });
        return flag;
    };

    //服务人员卡片页面
    function personnelDetailCard(){
        var orderId = "${param.orderId}";
        var personnelId = $("#needPersonnelId").val();
        if(personnelId.length == 0){
            $.alert({text:"请选择服务人员!"});
        }else{
            openModule("${ctx}/jsp/emp/empInfo.jsp",{"orderId": orderId,"personnelId":personnelId},"",{width:'40%'},"empInfo");
        }
    }
</script>
</html>

