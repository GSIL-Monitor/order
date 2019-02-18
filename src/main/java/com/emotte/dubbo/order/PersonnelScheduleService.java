package com.emotte.dubbo.order;

/**
 * 服务人员排期
 * 
 * @author zs
 *
 */
public interface PersonnelScheduleService {

	/**
	 * 查询服务人员排期冲突
	 * 
	 * @param data={'personnelSchedule':{'empId':12345454,'startDate',20180101,'endDate':20180130,'startTime':110000,'endTime':180000,'weekday','1,2,3,4,5,6,7'}}
	 * @return 示例1 {'code':'0','msg':'请求操作成功，排期有冲突','list':[{},{}]}
	 * @return 示例2 {'code':'1','msg':'请求操作失败'}
	 * @return 示例3 {'code':'2','msg':'请求操作成功，排期无冲突','list':[]}
	 * @return list=[{'id',123456789,orderCode':55111001114,'orderId':8791161116,'empId':12345454,'startDate',20180101,'endDate':20180130,'startTime':110000,'endTime':180000,'weekday','1'},
	 * 				 {'id',123456789,'orderCode':55111001114,'orderId':8791161116,'empId':12345454,'startDate',20180101,'endDate':20180130,'startTime':110000,'endTime':180000,'weekday','2']
	 * @return list返回是t_emp_personnel_schedule表记录
	 * 字段含义：
	 * empId-服务人员
	 * startDate-排期开始日期，格式：yyyyMMdd
	 * endDate-排期结束日期，格式：yyyyMMdd
	 * startTime-时间段开始时间，格式：mmss00
	 * endTime-时间段结束时间，格式：mmss00
	 * weekday-周,星期一至星期日对应1至7
	 */
	public String queryConflictSchedule(String data);
}
