package com.emotte.dubbo.order.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.emotte.dubbo.order.PersonnelScheduleService;
import com.emotte.kernel.helper.LogHelper;
import com.emotte.order.order.mapper.reader.RePersonnelScheduleMapper;
import com.emotte.order.order.model.Personnel;
import com.emotte.order.order.model.PersonnelSchedule;
import com.emotte.order.util.JsonUtil;

import net.sf.json.JSONObject;

@Service("personnelScheduleService")
public class PersonnelScheduleServiceImpl implements PersonnelScheduleService {

	@Resource
	private RePersonnelScheduleMapper RePersonnelScheduleMapper;

	@Override
	public String queryConflictSchedule(String json) {
		LogHelper.info(getClass() + ".queryConflictSchedule()", "接收的数据:" + json);
		String result = "{'code':'1','msg':'请求操作失败'}";
		try {
			if (json.startsWith("[{")) {
				json = json.substring(1, json.length() - 1);
			}
			JSONObject jsonObject = JSONObject.fromObject(json);
			JSONObject personnelSchedule = JSONObject.fromObject(jsonObject.get("personnelSchedule"));
			String cMsg = PersonnelScheduleServiceImpl.check(personnelSchedule);
			if ("".equals(cMsg)) {
				Object temp = JSONObject.toBean(personnelSchedule, PersonnelSchedule.class);
				PersonnelSchedule ps = (PersonnelSchedule) temp;
				String weekDay = ps.getWeekday();
				Personnel personnel= RePersonnelScheduleMapper.loadPersonnel(ps.getEmpId());
				if(personnel != null){
						if (weekDay != null && !"".equals(weekDay)) {
							ps.setWeekday(weekDay + ",0");
						}
						List<PersonnelSchedule> list = this.RePersonnelScheduleMapper.queryConflictSchedule(ps);
						if (list.size() > 0) {
							result = "{\"code\":\"0\",\"msg\":\"请求操作成功，排期有冲突\",\"list\":" + JsonUtil.toJson(list) + "}";
						} else {
							result = "{\"code\":\"2\",\"msg\":\"请求操作成功,排期无冲突\",\"list\":[]}";
						}
				}else{
					result = "{'code':'1','msg':'服务人员不存在'}";
				}
			} else {
				result = "{'code':'1','msg':" + cMsg + "}";
			}
		} catch (Exception e) {
			LogHelper.error(getClass() + ".queryConflictSchedule()",e.toString());
		}
		LogHelper.info(getClass() + ".queryConflictSchedule()", "返回的数据:" + result);
		return result;
	}

	/**
	 * 验证
	 * 
	 * @param personnelSchedule
	 * @return
	 */
	public static String check(JSONObject personnelSchedule) {
		String reg = "[0-9]{1,8}";
		if (personnelSchedule == null || "".equals(personnelSchedule)) {
			return "personnelSchedule不能为空";
		}
		Object startDate = personnelSchedule.get("startDate");
		Object endDate = personnelSchedule.get("endDate");
		Object empId = personnelSchedule.get("empId");
		if (startDate == null || "".equals(startDate)) {
			return "startDate不能为空";
		}
		if (endDate == null || "".equals(endDate)) {
			return "endDate不能为空";
		}
		if (empId == null || "".equals(empId)) {
			return "empId不能为空";
		}
		if (startDate != null && !startDate.toString().matches(reg)
				&& PersonnelScheduleServiceImpl.isValidDate(startDate.toString())) {
			return "startDate格式不正确";
		}
		if (endDate != null && !endDate.toString().matches(reg)
				&& PersonnelScheduleServiceImpl.isValidDate(endDate.toString())) {
			return "endDate格式不正确";
		}
		return "";
	}

	/**
	 * 是否为日期
	 * @param str
	 * @return
	 */
	public static boolean isValidDate(String str) {
		boolean convertSuccess = true;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			// 设置lenient为false.
			// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess = false;
		}
		return convertSuccess;
	}

	public static void main(String[] args) {
		boolean m = PersonnelScheduleServiceImpl.isValidDate("20181328");
		System.err.println(m);
	}

}
