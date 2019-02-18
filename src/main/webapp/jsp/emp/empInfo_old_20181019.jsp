<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style>
</style>
</head>
<body>
	<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
	<div class="modal fade">
		<div class="modal-dialog" style="height:600px; width: 1180px;">
			<div class="modal-content table-responsive" style="overflow-y:auto; height:900px;position: relative;z-index:9999;" id="personnelDetailCard" >
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">家政基地服务员信息展示</h4>
				</div>
				<div class="modal-body">
					<table class="table table-condensed table-bordered table-striped table-hover">
						<tbody>
						<tr>
							<td rowspan="6" >
								<div style="padding: 3%;margin: 3%;text-align: center">
									<img id="head" src="${ctx}/images/touxiang.png" style="width:180px; height:180px;"/>
								</div>
							</td>
							<td >姓名:<span id="userName"></span></td>
							<td >籍贯:<span id="origin"></span></td>
							<td >年龄:<span id="age"></span></td>
						</tr>
						<tr>
							<td>工种:<span id="workType"></span></td>
							<td>从业年限:<span id="workingLife"></span></td>
							<td>学历:<span id="userEducation"></span></td>
						</tr>
						<tr>
							<td>属相:<span id="zodiac"></span></td>
							<td>星座:<span id="userConstellation"></span></td>
							<td>血型:<span id="blood"></span></td>
						</tr>
						<tr>
							<td>户籍住址:</td>
							<td colspan="2"><span id="homeAddress"></span></td>
						</tr>
						<tr>
							<td>技能证书:</td>
							<td colspan="2"><span id="skill"></span></td>
						</tr>
						<tr>
							<td>特长:</td>
							<td colspan="2"><span id="specialty"></span></td>
						</tr>

						<tr>
							<td rowspan="4">服务员家庭情况</td>
							<td>婚姻状况:
								<input type="radio" name="isMarry" value="1" disabled="disabled">未婚
								<input type="radio" name="isMarry" value="2" disabled="disabled">已婚
								<input type="radio" name="isMarry" value="3" disabled="disabled">离异
							</td>
							<td>是否生育:
								<input type="radio" name="hasChildren" value="1" disabled="disabled">是
								<input type="radio" name="hasChildren" value="2" disabled="disabled">否
							</td>
							<td>子女数量:<span id="childrenNum"></span></td>
						</tr>
						<tr>
							<td>子女年龄:<span id=""></span></td>
							<td>子女是否在本市:
								<input type="radio" name="childrenInCity" value="1" disabled="disabled">是
								<input type="radio" name="childrenInCity" value="2" disabled="disabled">否
							</td>
							<td>子女是否就业:
								<input type="radio" name="childrenHasJob" value="1" disabled="disabled">是
								<input type="radio" name="childrenHasJob" value="2" disabled="disabled">否
							</td>
						</tr>
						<tr>
							<td>配偶是否在本市:
								<input type="radio" name="coupleHere" value="1" disabled="disabled">是
								<input type="radio" name="coupleHere" value="2" disabled="disabled">否
							</td>
							<td>配偶职业:<span id="coupleProfession"></span></td>
							<td>父母是否健在:
								<input type="radio" name="parentsAlive" value="1" disabled="disabled">父在
								<input type="radio" name="parentsAlive" value="2" disabled="disabled">母在
								<input type="radio" name="parentsAlive" value="3" disabled="disabled">离世
								<input type="radio" name="parentsAlive" value="4" disabled="disabled">健在
							</td>
						</tr>
						<tr>
							<td>父母是否在本市:
								<input type="radio" name="parentsHere" value="1" disabled="disabled">是
								<input type="radio" name="parentsHere" value="2" disabled="disabled">否
							</td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td colspan="4">工作经历和自我介绍:<span id="remark"></span></td>
						</tr>
						<tr class="" style="height: 200px;">
							<td>工作照/技能照
								<div style="padding: 5%;text-align: center">
									<img id="img0" src="${ctx}/images/white.jpg" style="width: 220px;height: 150px"/>
								</div>
							</td>
							<td>工作照/技能照
								<div style="padding: 5%;text-align: center">
									<img id="img1" src="${ctx}/images/white.jpg" style="width: 220px;height: 150px"/>
								</div>
							</td>
							<td>工作照/技能照
								<div style="padding: 5%;text-align: center">
									<img id="img2" src="${ctx}/images/white.jpg" style="width: 220px;height: 150px"/>
								</div>
							</td>
							<td>工作照/技能照
								<div style="padding: 5%;text-align: center">
									<img id="img3" src="${ctx}/images/white.jpg" style="width: 220px;height: 150px"/>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">工资:<span id="salaryExpectation"></span></td>
							<td colspan="2">工种等级:<span id="gradeText"></span></td>
						</tr>
						<tr>
							<td colspan="4">负责产品管家姓名:<span id="managerName"></span></td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<div style=" text-align:center;"><font color="red" size="3">打造优质产品基地、塑造专业服务团队</font></div>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
	
<script type="text/javascript">
	$(function(){
		var orderId = "${param.orderId}";
		var personnelId = "${param.personnelId}";
		var url = ctx+"/itemDetailServer/personnelDetailCard/"+orderId+"/"+personnelId;
		$.ajax(url,{
			data:{order:orderId,personnelId:personnelId},
			type:"post",
			sync:false,
			success:function(data){
				if(data.msg == "00"){
					var personnel = data.personnels;
					//头像
					var head = personnel.head;
					if(head.length > 0){
						$("#head").attr("src",personnel.head);
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
					if(education == 1){
						userEducation = "文盲(无学历)";
					}else if(education == 2){
						userEducation = "小学";
					}else if(education == 3){
						userEducation = "初中";
					}else if(education == 4){
						userEducation = "高中/中专";
					}else if(education == 5){
						userEducation = "本科/大专";
					}else if(education == 6){
						userEducation = "研究生";
					}else{
						userEducation = "";
					}
					$("#userEducation").text(userEducation);
					//属相
					$("#zodiac").text(personnel.zodiac);
					//星座
					var constellation = personnel.constellation;
					var userConstellation;
					if(constellation == 1){
						userConstellation = "水瓶座";
					}else if(constellation == 2){
						userConstellation = "双鱼座";
					}else if(constellation == 3){
						userConstellation = "白羊座";
					}else if(constellation == 4){
						userConstellation = "金牛座";
					}else if(constellation == 5){
						userConstellation = "双子座";
					}else if(constellation == 6){
						userConstellation = "巨蟹座";
					}else if(constellation == 7){
						userConstellation = "狮子座";
					}else if(constellation == 8){
						userConstellation = "处女座";
					}else if(constellation == 9){
						userConstellation = "天秤座";
					}else if(constellation == 10){
						userConstellation = "天蝎座";
					}else if(constellation == 11){
						userConstellation = "射手座";
					}else if(constellation == 12){
						userConstellation = "摩羯座";
					}else{
						userConstellation = "";
					}
					$("#userConstellation").text(userConstellation);
					//血型
					var blood = personnel.blood;
					var userBlood;
					if(blood == 1){
						userBlood = "A型";
					}else if(blood == 2){
						userBlood = "B型";
					}else if(blood == 3){
						userBlood = "O型";
					}else if(blood == 4){
						userBlood = "AB型";
					}else{
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
					$("input[name = 'isMarry']").each(function(i,n){
						if(n.value == personnel.isMarry){
							n.checked = true;
						}
					});
					//是否生育
					var childrenNum = personnel.childrenNum;
					$("input[name = 'hasChildren']").each(function(i,n){
						if(childrenNum != 0 && n.value == 1){
							n.checked = true;
						}else if(childrenNum == 0 && n.value == 2){
							n.checked = true;
						}
					});
					//子女数量
					$("#childrenNum").text(childrenNum);
					//子女是否在本市
					$("input[name = 'childrenInCity']").each(function(i,n){
						if(n.value == personnel.childrenInCity){
							n.checked = true;
						}
					});
					//子女是否就业
					$("input[name = 'childrenHasJob']").each(function(i,n){
						if(n.value == personnel.childrenHasJob){
							n.checked = true;
						}
					});
					//配偶是否在本市
					$("input[name = 'coupleHere']").each(function(i,n){
						if(n.value == personnel.coupleHere){
							n.checked = true;
						}
					});
					//配偶职业
					$("#coupleProfession").text(personnel.coupleProfession);
					//父母是否健在
					$("input[name = 'parentsAlive']").each(function(i,n){
						if(n.value == personnel.parentsAlive){
							n.checked = true;
						}
					});
					//父母是否在京
					$("input[name = 'parentsHere']").each(function(i,n){
						if(n.value == personnel.parentsHere){
							n.checked = true;
						}
					});
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
					if(skillAddress.length > 0){
						skillArr = skillAddress.split(",");
					}
					if(imgAddress.length > 0){
						imgArr = imgAddress.split(",");
					}
					if(skillArr.length >= 4){
						skillAndImgArr.push(skillArr[0]);
						skillAndImgArr.push(skillArr[1]);
						skillAndImgArr.push(skillArr[2]);
						skillAndImgArr.push(imgArr[0]);
					}else{
						for(var i=0 ; i<skillArr.length ; i++){
							if(skillArr[i].length > 0){
								skillAndImgArr.push(skillArr[i]);
							}
						}
						var newVar = (4-skillArr.length) >= imgArr.length ? imgArr.length : (4-skillArr.length);
						for(var i=0 ; i<newVar ; i++){
							if(imgArr[i].length > 0){
								skillAndImgArr.push(imgArr[i])
							}
						}
					}
					for(var i=0 ; i<skillAndImgArr.length ; i++){
						if(skillAndImgArr[i].length > 0 && typeof(skillAndImgArr[i]) != "undefined"){
							$("#img"+i).attr("src",skillAndImgArr[i]);
						}
					}
				}
			}
		});
	});
</script>
</html>