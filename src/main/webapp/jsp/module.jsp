<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<body>
  <div class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false">  
	  <div class="modal-dialog" >  
	    <div class="modal-content">
	      <div class="modal-header">  
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
	        <h4 class="modal-title">标题</h4>
	      </div>  
	      <div class="modal-body">
<!--表单验证模板-->
			  <form>
				  <input data-require value="" type="text" name="name"  data-valid="isNonEmpty||onlyZh||between:2-5" data-error="姓名不能为空||姓名只能为中文||姓名长度为2-5位">
				  <br/>
				  <input data-require value="" type="text" name="hh"  data-valid="isEmail" data-error="2333">
				  <br/>
			      <span data-require data-valid="isChecked" data-error="性别必选">
			          <label><input type="radio" name="sex">男</label>
			          <label><input type="radio" name="sex">女</label>
			          <label><input type="radio" name="sex">未知</label>
			      </span>
				  <br/>
			      <span data-require data-valid="isChecked" data-error="标签至少选择一项" >
			          <label><input type="checkbox" name="label">红</label>
			          <label><input type="checkbox" name="label">绿</label>
			          <label><input type="checkbox" name="label">蓝</label>
			      </span>
				  <br/>
					  <select data-require data-valid="isNonEmpty" data-error="省份必填">
						  <option value="">请选择省份</option>
						  <option value="001">001</option>
						  <option value="002">002</option>
						  <option value="003">003</option>
						  <option value="004">004</option>
						  <option value="005">005</option>
						  <option value="006">006</option>
						  <option value="007">007</option>
						  <option value="008">008</option>
						  <option value="009">009</option>
						  <option value="010">010</option>
					  </select>
				  <br/>
				  <button type="button" onclick="ss()">提价</button>
			  </form>

			  <script type="text/javascript">
				  function ss(){
					  var d = $('form').formValidate({ });
					  alert(d);
				  }
			  </script>
<!--时间控件模板-->
			  <div class="controls input-append date form_date" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
				  <input size="16" class="form-control" class="" type="text" style="width:120px" value="" readonly>
				  <span class="add-on"><i class="icon-remove"></i></span>
				  <span class="add-on"><i class="icon-th"></i></span>
			  </div>
			  &nbsp;至&nbsp;
			  <div class="controls input-append date form_date" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
				  <input size="16" class="form-control" class="" type="text" style="width:120px" value="" readonly>
				  <span class="add-on"><i class="icon-remove"></i></span>
				  <span class="add-on"><i class="icon-th"></i></span>
			  </div>
			  <script>
				  $('.form_date').datetimepicker({
					  language:  'zh-CN',
					  weekStart: 1,
					  todayBtn:  1,
					  autoclose: 1,
					  todayHighlight: 1,
					  startView: 2,
					  minView: 2,
					  forceParse: 0
				  });
			  </script>
		  </div>
	      <div class="modal-footer">  
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>  
	        <button type="button" class="btn btn-primary" onclick="closeModule()">保存</button>  
	      </div>  
	    </div>
	  </div>
	</div>         
</body>
</html>

