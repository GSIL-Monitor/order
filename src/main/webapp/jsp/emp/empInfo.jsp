<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style>
        .zh {
            margin: 6px;
            margin-right: 0px;
            margin-left: 5px;
        }
        .zh2{
            margin: 6px;
            padding-left: 40px;
            margin-right: 0px;
        }
        .zh3{
            margin: 10px;
            padding-left: 40px;
            margin-right: 0px;
        }
        .hr {
            width:100%;
            margin:0 auto;
            border: 0;
            height: 5px;
            background-image: linear-gradient(to right, rgba(0, 0, 0, 0.05), rgba(0, 0, 0, 0.05), rgba(0, 0, 0, 0.05));
        }
        .hr2 {
            width:100%;
            margin:0 auto;
            border: 0;
            height: 1px;
            background-image: linear-gradient(to right, rgba(0, 0, 0, 0.05), rgba(0, 0, 0, 0.05), rgba(0, 0, 0, 0.05));
        }
    </style>
</head>
<body>
<input type="hidden" id="ctx" name="ctx" value="${ctx}"/>
<div class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content" style="height:auto;z-index:9999;" id="personnelDetailCard">
            <div class="modal-header">
                <div class="row col-xs-6" style="">
                    <img id="logoImg" src="${ctx}/images/logoImg.png" style="width:100px; height:35px;"/>
                </div>
                <h3 class="modal-title" style="text-align: right;color: #0f0f0f;" id="myModalLabel">家政基地服务员信息展示</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="row col-xs-3">
                        <div style="padding: 3%;margin: 3%;text-align: center;padding-top: 30px;padding-left: 15px;">
                            <img id="head" src="${ctx}/images/touxiang.png" class=".img-responsive" style="width:150px; height:180px;"/>
                        </div>
                    </div>
                    <div class="row col-xs-9 zh">
                        <div class="row col-xs-3 zh">
                            <h5><div style="float:left;width: 4px;height: 15px; background: cornflowerblue;"></div>&nbsp;详细信息</h5>
                        </div>
                        <div class="row col-xs-12 zh2">
                            <div class="row col-xs-4" style="">
                                姓名:<span id="userName"></span>
                            </div>
                            <div class="row col-xs-4" style="">
                                籍贯:<span id="origin"></span>
                            </div>
                            <div class="row col-xs-4" style="">
                                年龄:<span id="age"></span>
                            </div>
                        </div>
                        <div class="row col-xs-12 zh2">
                            <div class="row col-xs-4" style="">
                                学历:<span id="userEducation"></span>
                            </div>
                            <div class="row col-xs-4" style="">
                                从业年限:<span id="workingLife"></span>
                            </div>
                        </div>
                        <div class="row col-xs-12 zh2">
                            <div class="row col-xs-4" style="">
                                属相:<span id="zodiac"></span>
                            </div>
                            <div class="row col-xs-4" style="">
                                星座:<span id="userConstellation"></span>
                            </div>
                            <div class="row col-xs-4" style="">
                                血型:<span id="blood"></span>
                            </div>
                        </div>
                        <div class="row zh">
                            <hr class="hr2"/>
                        </div>
                        <div class="row col-xs-12 zh2">
                            <div class="row col-xs-12" style="">
                                户籍住址:<span id="homeAddress"></span>
                            </div>
                        </div>
                        <div class="row col-xs-12 zh2">
                            <div class="row col-xs-12" style="">
                                技能证书:<span id="skill"></span>
                            </div>
                        </div>
                        <div class="row col-xs-12 zh2">
                            <div class="row col-xs-12" style="">
                                工种:<span id="workType"></span>
                            </div>
                        </div>
                        <div class="row col-xs-12 zh2">
                            <div class="row col-xs-12" style="">
                                特长:<span id="specialty"></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <hr class="hr"/>
                </div>
                <div class="row">
                    <div class="row col-xs-12 zh">
                        <h5>
                            <div style="float:left;width: 4px;height: 15px; background: cornflowerblue;"></div>
                            &nbsp;服务员家庭情况
                        </h5>
                    </div>
                    <div class="row col-xs-12 zh3">
                        <div class="row col-xs-4" style="">
                            婚姻状况:<span id="isMarry"></span>
                        </div>
                        <div class="row col-xs-4" style="">
                            是否生育:<span id="hasChildren"></span>
                        </div>
                        <div class="row col-xs-4" style="">
                            子女数量:<span id="childrenNum"></span>
                        </div>
                    </div>
                    <div class="row col-xs-12 zh3">
                        <div class="row col-xs-4" style="">
                            子女年龄:<span id="">暂无信息</span>
                        </div>
                        <div class="row col-xs-4" style="">
                            子女是否在本市:<span id="childrenInCity"></span>
                        </div>
                        <div class="row col-xs-4" style="">
                            子女是否就业:<span id="childrenHasJob"></span>
                        </div>
                    </div>
                    <div class="row col-xs-12 zh3">
                        <div class="row col-xs-4" style="">
                            配偶是否在本市:<span id="coupleHere"></span>
                        </div>
                        <div class="row col-xs-4" style="">
                            配偶职业:<span id="coupleProfession"></span>
                        </div>
                        <div class="row col-xs-4" style="">
                            父母是否健在:<span id="parentsAlive"></span>
                        </div>
                    </div>
                    <div class="row zh3 col-xs-12">
                        <div class="row col-xs-12">
                            父母是否在本市:<span id="parentsHere"></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <hr class="hr"/>
                </div>
                <div class="row">
                    <div class="row col-xs-12 zh">
                        <h5>
                            <div style="float:left;width: 4px;height: 15px; background: cornflowerblue;"></div>
                            &nbsp;工作经历和自我介绍
                        </h5>
                    </div>
                    <div class="row col-xs-12 zh2">
                        <span id="remark"></span>
                    </div>
                </div>
                <div class="row">
                    <hr class="hr"/>
                </div>
                <div class="row">
                    <div class="row col-xs-12 zh">
                        <h5>
                            <div style="float:left;width: 4px;height: 15px; background: cornflowerblue;"></div>
                            &nbsp;工作照/技能照
                        </h5>
                    </div>
                    <div class="row col-xs-12 zh">
                        <div class="row col-xs-3 img-responsive" style="margin: 0 auto;align-content: center;">
                            <img id="img0" src="${ctx}/images/white.jpg" style="width: 120px;height: 120px;"/>
                        </div>
                        <div class="row col-xs-3 img-responsive" style="margin: 0 auto;align-content: center;">
                            <img id="img1" src="${ctx}/images/white.jpg" style="width: 120px;height: 120px;"/>
                        </div>
                        <div class="row col-xs-3 img-responsive" style="margin: 0 auto;align-content: center;">
                            <img id="img2" src="${ctx}/images/white.jpg" style="width: 120px;height: 120px;"/>
                        </div>
                        <div class="row col-xs-3 img-responsive" style="margin: 0 auto;align-content: center;">
                            <img id="img3" src="${ctx}/images/white.jpg" style="width: 120px;height: 120px;"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <hr class="hr"/>
                </div>
                <div class="row zh2">
                    <div class="row col-xs-8">
                        工资:<span id="salaryExpectation"></span>
                    </div>
                    <div class="row col-xs-4">
                        工种等级:<span id="gradeText"></span>
                    </div>
                </div>
                <div class="row zh2">
                    <div class="row col-xs-6">
                        负责产品管家姓名:<span id="managerName"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="" style="text-align:center;background-color: rgba(152, 152, 152, 0.2);height: 40px;">
                        <div class="" style="text-align: center;align-content: center;padding-top: 12px;">
                            <font style="text-align: center;" color="#1e90ff" size="3">打造优质产品基地、塑造专业服务团队</font>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">
    $(function () {
        var orderId = "${param.orderId}";
        var personnelId = "${param.personnelId}";
        var url = ctx + "/itemDetailServer/personnelDetailCard/" + orderId + "/" + personnelId;
        $.ajax(url, {
            data: {order: orderId, personnelId: personnelId},
            type: "post",
            sync: false,
            success: function (data) {
                if (data.msg == "00") {
                    var personnel = data.personnels;
                    //头像
                    var head = personnel.head;
                    if (head.length > 0) {
                        $("#head").attr("src", personnel.head);
                    }
                    //姓名
                    $("#userName").text(personnel.name);
                    //籍贯
                    $("#origin").text(personnel.origin);
                    //年龄
                    $("#age").text(personnel.age);
                    //工种
                    $("#workType").text(personnel.workType);
                    //从业年限
                    $("#workingLife").text(personnel.workingLife);
                    //学历
                    var education = personnel.education;
                    var userEducation;
                    if (education == 1) {
                        userEducation = "文盲(无学历)";
                    } else if (education == 2) {
                        userEducation = "小学";
                    } else if (education == 3) {
                        userEducation = "初中";
                    } else if (education == 4) {
                        userEducation = "高中/中专";
                    } else if (education == 5) {
                        userEducation = "本科/大专";
                    } else if (education == 6) {
                        userEducation = "研究生";
                    } else {
                        userEducation = "";
                    }
                    $("#userEducation").text(userEducation);
                    //属相
                    $("#zodiac").text(personnel.zodiac);
                    //星座
                    var constellation = personnel.constellation;
                    var userConstellation;
                    if (constellation == 1) {
                        userConstellation = "水瓶座";
                    } else if (constellation == 2) {
                        userConstellation = "双鱼座";
                    } else if (constellation == 3) {
                        userConstellation = "白羊座";
                    } else if (constellation == 4) {
                        userConstellation = "金牛座";
                    } else if (constellation == 5) {
                        userConstellation = "双子座";
                    } else if (constellation == 6) {
                        userConstellation = "巨蟹座";
                    } else if (constellation == 7) {
                        userConstellation = "狮子座";
                    } else if (constellation == 8) {
                        userConstellation = "处女座";
                    } else if (constellation == 9) {
                        userConstellation = "天秤座";
                    } else if (constellation == 10) {
                        userConstellation = "天蝎座";
                    } else if (constellation == 11) {
                        userConstellation = "射手座";
                    } else if (constellation == 12) {
                        userConstellation = "摩羯座";
                    } else {
                        userConstellation = "";
                    }
                    $("#userConstellation").text(userConstellation);
                    //血型
                    var blood = personnel.blood;
                    var userBlood;
                    if (blood == 1) {
                        userBlood = "A型";
                    } else if (blood == 2) {
                        userBlood = "B型";
                    } else if (blood == 3) {
                        userBlood = "O型";
                    } else if (blood == 4) {
                        userBlood = "AB型";
                    } else {
                        userBlood = "";
                    }
                    $("#blood").text(userBlood);
                    //户籍住址
                    $("#homeAddress").text(personnel.homeAddress);
                    //技能证书
                    $("#skill").text(personnel.skill);
                    //特长
                    $("#specialty").text(personnel.specialty);
                    //婚姻状况
                    var isMarry = personnel.isMarry;
                    var marry;
                    if (isMarry == "1") {
                        marry = "未婚";
                    } else if (isMarry == "2") {
                        marry = "已婚";
                    } else if (isMarry == "3") {
                        marry = "离异";
                    } else {
                        marry = "暂无信息";
                    }
                    $("#isMarry").text(marry);
                    //是否生育
                    var childrenNum = personnel.childrenNum;
                    var hasChildren;
                    if (childrenNum == 0) {
                        hasChildren = "否";
                    } else if (childrenNum != 0) {
                        hasChildren = "是";
                    }
                    $("#hasChildren").text(hasChildren);
                    //子女数量
                    $("#childrenNum").text(childrenNum);
                    //子女是否在本市
                    var childrenInCity = personnel.childrenInCity;
                    var childrenIn;
                    if (childrenInCity == 1) {
                        childrenIn = "是";
                    } else if (childrenInCity == 2) {
                        childrenIn = "否";
                    } else {
                        childrenIn = "暂无信息";
                    }
                    $("#childrenInCity").text(childrenIn);
                    //子女是否就业
                    var childrenHasJob = personnel.childrenHasJob;
                    var childrenJob;
                    if (childrenHasJob == 1) {
                        childrenJob = "是";
                    } else if (childrenHasJob == 2) {
                        childrenJob = "否";
                    } else {
                        childrenJob = "暂无信息";
                    }
                    $("#childrenHasJob").text(childrenJob);
                    //配偶是否在本市
                    var coupleHere = personnel.coupleHere;
                    var coupleInCity;
                    if (coupleHere == 1) {
                        coupleInCity = "是";
                    } else if (coupleHere == 2) {
                        coupleInCity = "否";
                    } else {
                        coupleInCity = "暂无信息";
                    }
                    $("#coupleHere").text(coupleInCity);
                    //配偶职业
                    $("#coupleProfession").text(personnel.coupleProfession);
                    //父母是否健在
                    var parentsAlive = personnel.parentsAlive;
                    var parentIsAlive;
                    if (parentsAlive == 1) {
                        parentIsAlive = "父在";
                    } else if (parentsAlive == 1) {
                        parentIsAlive = "母在";
                    } else if (parentsAlive == 1) {
                        parentIsAlive = "离世";
                    } else if (parentsAlive == 1) {
                        parentIsAlive = "健在";
                    } else {
                        parentIsAlive = "暂无信息";
                    }
                    $("#parentsAlive").text(parentIsAlive);
                    //父母是否在京
                    var parentsHere = personnel.parentsHere;
                    var parentInCity;
                    if (parentsHere == 1) {
                        parentInCity = "是";
                    } else if (parentsHere == 2) {
                        parentInCity = "否";
                    } else {
                        parentInCity = "暂无信息";
                    }
                    $("#parentsHere").text(parentInCity);
                    //工作经历和自我介绍
                    $("#remark").text(personnel.remark);
                    //工资
                    $("#salaryExpectation").text(personnel.salaryExpectation + "元");
                    //等级
                    $("#gradeText").text(personnel.gradeText);
                    //负责产品管家姓名
                    $("#managerName").text(personnel.managerName);
                    /*
                     * 工作照、技能照
                     *
                     * 显示规则：
                     * 页面一共4个栏位，
                     * 	数据足够时显示3张技能照，1张工作照
                     * 	数据不足时显示全部技能照，其余工作照补充
                     * */
                    var imgAddress = personnel.orginalAddress;
                    var skillAddress = personnel.skillAddress;
                    var skillAndImgArr = [];
                    var skillArr = [];
                    var imgArr = [];
                    if (skillAddress.length > 0) {
                        skillArr = skillAddress.split(",");
                    }
                    if (imgAddress.length > 0) {
                        imgArr = imgAddress.split(",");
                    }
                    if (skillArr.length >= 4) {
                        skillAndImgArr.push(skillArr[0]);
                        skillAndImgArr.push(skillArr[1]);
                        skillAndImgArr.push(skillArr[2]);
                        skillAndImgArr.push(imgArr[0]);
                    } else {
                        for (var i = 0; i < skillArr.length; i++) {
                            if (skillArr[i].length > 0) {
                                skillAndImgArr.push(skillArr[i]);
                            }
                        }
                        var newVar = (4 - skillArr.length) >= imgArr.length ? imgArr.length : (4 - skillArr.length);
                        for (var i = 0; i < newVar; i++) {
                            if (imgArr[i].length > 0) {
                                skillAndImgArr.push(imgArr[i])
                            }
                        }
                    }
                    for (var i = 0; i < skillAndImgArr.length; i++) {
                        if (skillAndImgArr[i].length > 0 && typeof(skillAndImgArr[i]) != "undefined") {
                            $("#img" + i).attr("src", skillAndImgArr[i]);
                        }
                    }
                }
            }
        });
    });
</script>
</html>