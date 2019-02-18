<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<body>
	<div class="mytabs-wrap">
       <ul class="mytabs" id="mytabs">
           <li class="tab-item tab-active">基本信息</li>
           <li class="tab-item">1多媒体</li>
           <li class="tab-item">2缴费</li>
           <li class="tab-item">3押金</li>
           <li class="tab-item">4多媒体</li>
           <li class="tab-item">5缴费</li>
           <li class="tab-item">6押金</li>
       </ul>
   </div>
       <div id="arr">
           <span id="left"><</span><span id="right">></span>
       </div>
       <!-- Tab panes -->
       <div class="tab-content" id="tab_content">
           <div class="main tab-selected" id="person">
               <div class="widget">
                   <a href="javascript:;"><em class="widget-x glyphicon glyphicon-pencil"></em><span>修改</span></a>
                   <a href="javascript:;"><em class="widget-d glyphicon glyphicon-trash"></em><span>删除</span></a>
               </div>
               <div class="widget-content">
                   <table class="table table-condensed">
                       <tr>
                           <td>
                               姓名：陈好
                           </td>
                           <td>
                               性别：女
                           </td>
                           <td rowspan="3" colspan="2">
                               <img src="../images/touxiang.png" alt="" style="width:100px; height:100px;"/>
                           </td>
                       </tr>
                       <tr>
                           <td>
                               年龄：32
                           </td>
                           <td>
                               属相：马
                           </td>
                       </tr>
                       <tr>
                           <td>
                               民族：汉
                           </td>
                           <td>
                               学历:初中
                           </td>
                       </tr>
                       <tr>
                           <td colspan="2">
                               籍贯：北京
                           </td>
                           <td colspan="2">
                               婚姻状况：已婚
                           </td>
                       </tr>
                       <tr>
                           <td colspan="3">紧急联系：122939794749</td>
                       </tr>
                       <tr>
                           <td colspan="2">联系方式：12345678913</td>
                           <td colspan="2">普通话：标准</td>
                       </tr>
                       <tr>
                           <td colspan="3">身份证号：370923199132334449</td>
                       </tr>
                       <tr>
                           <td colspan="3">现住地址：北京市石景山区万商大厦</td>
                       </tr>
                       <tr>
                           <td colspan="3">原籍住址：北京市石景山区万商大厦</td>
                       </tr>
                       <tr>
                           <td>从事家政年限：2年</td>
                           <td colspan="2">健康证状况：检验证明</td>
                       </tr>
                       <tr>
                           <td>期望工种：小时工</td>
                           <td colspan="2">期望工作：打扫家务</td>
                       </tr>
                       <tr>
                           <td>期望薪资：5000</td>
                           <td colspan="2">是否怕宠物：否</td>
                       </tr>
                       <tr>
                           <td colspan="3">
                               <span>有何特长：</span>
                               <textarea cols="30" rows="2"></textarea>
                           </td>
                       </tr>
                       <tr>
                           <td colspan="3">
                               <span>初试评价：</span>
                               <textarea cols="30" rows="2"></textarea>
                           </td>
                       </tr>
                       <tr>
                           <td colspan="2">
                               渠道：呼叫中心
                           </td>
                           <td colspan="2">
                               录入人：王杰
                           </td>
                       </tr>
                   </table>
               </div>
           </div>
           <div class="main" id="media">
               <div class="widget">
                   <a href="#"><em class="widget-s glyphicon glyphicon-cloud-upload"></em><span>上传</span></a>
                   <a href="#"><em class="widget-d glyphicon glyphicon-trash"></em><span>删除</span></a>
               </div>
               <div class="widget-content">
                   <table class="table table-condensed">
                       <tr>
                           <td colspan="3">相片</td>
                       </tr>
                       <tr></tr>
                       <tr>
                           <td>相册：</td>
                           <td colspan="2">
                               <select>
                                   <option>头像</option>
                                   <option>身份证反正面</option>
                                   <option>健康证</option>
                                   <option>证书</option>
                                   <option>工作照</option>
                                   <option>生活照</option>
                                   <option>荣誉照</option>
                                   <option>其他</option>
                               </select>
                           </td>
                       </tr>
                       <tr>
                           <td><img src="../images/nan.jpg" alt="男"/>
                               <input type="radio" checked="checked" name="xingbie" value="男"/>
                           </td>
                           <td><img src="../images/nv.jpg" alt="女"/>
                               <input type="radio" name="xingbie" value="女"/>
                           </td>
                           <td><button class="btncover">默认封面</button></td>
                       </tr>
                       <tr>
                           <td>
                               预览：
                               <img src="../images/nv.jpg" alt="小图"/>
                           </td>
                           <td colspan="2">
                               <div id="localImag"><img id="preview" src="../images/nv.jpg" width="100" height="120" style="display: block; width: 100px; height: 120px;"></div>
                               <input type="file" name="file" id="doc" style="width:160px;" onchange="javascript:setImagePreview();">
                           </td>
                       </tr>
                       <tr>
                           <td colspan="3">影音</td>
                       </tr>
                       <tr class="trvid">
                           <td>
                               视频文件夹：
                           </td>
                           <td></td>
                           <td><button>本地文件</button></td>
                       </tr>
                       <tr>
                           <td colspan="3">
                               <img src="../images/nan.jpg" alt="" style="display: block; width: 100%; height: 120px;"/>
                           </td>
                       </tr>
                   </table>
               </div>
           </div>
           <div class="main" id="payment">
               <div class="widget">
                   <a href="javascript:;" onclick="openModule('pay-xinzeng.html')"><em class="widget-new glyphicon glyphicon glyphicon-plus-sign"></em><span>新增</span></a>
                   <a href="javascript:;"><em class="widget-s glyphicon glyphicon-cloud-upload"></em><span>上传</span></a>
                   <a href="javascript:;"><em class="widget-d glyphicon glyphicon-trash"></em><span>删除</span></a>
               </div>
               <div class="widget-content">
                   <div class="payment-content">
                       <ul>
                           <li>日期</li>
                           <li>住宿管理费</li>
                           <li>培训费</li>
                           <li>洗澡费</li>
                           <li>工服收入</li>
                           <li>设备收入</li>
                           <li>证书收入</li>
                           <li>罚款</li>
                           <li>押金</li>
                           <li>其他</li>
                       </ul>
                       <ul>
                           <li></li>
                           <li></li>
                           <li></li>
                           <li></li>
                           <li></li>
                           <li></li>
                           <li></li>
                           <li></li>
                           <li></li>
                           <li></li>
                       </ul>
                   </div>
               </div>
           </div>
           <div class="main" id="bond">4</div>
       </div>
       
</body>
</html>