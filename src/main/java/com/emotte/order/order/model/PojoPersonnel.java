package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_emp_personnel
 * 
 * 
 * @author army
 */
public class PojoPersonnel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5428375751121139753L;
	// Grade字段
	// : emp_id
	private Long g_empId;
	// 当前登录人岗位
	private String post;
	// 当前登录人部门code
	private String orgcode;
	private String deptName;

	// 初试评价人 : grade_user
	private String g_gradeUser;

	// 初试评价内容 : grade_content
	private String g_gradeContent;

	// 初试评价时间 : grade_time
	private java.util.Date g_gradeTime;

	// 评价类型（1 初试，2 培训，3 评级， 4 产品管家，5 营销管家，5 客户 ） : grade_type
	private Integer g_gradeType;

	// : create_by
	private Long g_createBy;
	private String original_address;

	// : update_by
	private Long g_updateBy;

	private String sort;

	private long managementStatus;

	// : updatetime
	private java.util.Date g_updatetime;

	// : createtime
	private java.util.Date g_createtime;

	// : version
	private Long g_version;
	// newRecord 字段
	// id : id
	private Long id;
	private Long r_id;
	// 服务人员id : r_emp_id
	private Long r_empId;
	// 操作人 : opt_user
	private Long r_optUser;

	// 当前操作人 : current_opt_user
	private Long r_currentOptUser;

	// 分派时间 : process_time
	private java.util.Date r_processTime;

	// 隶属部门一级(0 总部，1 学校) : l1_dept
	private Integer r_l1Dept;

	// 隶属部门二级（具体部门） : l2_dept
	private Integer r_l2Dept;

	// 创建人 : create_by
	private Long r_createBy;

	// 修改人 : update_by
	private Long r_updateBy;

	// 修改时间 : updatetime
	private java.util.Date r_updatetime;

	private Integer sort_test;
	// 创建时间 : createtime
	private java.util.Date r_createtime;

	private Long r_targetDept;
	// : version
	private Long r_version;
	// personnel 字段
	// id : id
	private Long p_id;

	// 姓名 : name
	private String p_name;

	// 1 女，2 男 : sex
	private String p_sex;

	// 年龄 : age
	private String p_age;
	// 查询年龄起
	private Integer p_ageBegin;
	// 查询年龄止
	private Integer p_ageEnd;
	// 籍贯省 : origin
	private String p_origin;
	// 籍贯市 : originCity
	private String p_originCity;

	// 属相 : zodiac
	private String p_zodiac;

	// 身份证号 : id_card_num
	private String p_idCardNum;

	// 普通话 : mandarin
	private String p_mandarin;

	private String healthtime;

	private String healthtimeend;
	// 民族 : nation
	private String p_nation;

	// 联系方式（手机） : mobile
	private String p_mobile;

	// 1 未婚 ， 2 已婚 : is_marry
	private String p_isMarry;

	// 现在住址 : current_address
	private String p_currentAddress;
	// 经度
	private String p_usualLng;
	// 纬度
	private String p_usualLat;
	// 原籍住址 : home_address
	private String p_homeAddress;

	// 1无学历，2 小学 ，3 初中，4高中/中专 ，5 本科/大专， 6.研究生 : education
	private String p_education;

	// 紧急联系（电话） : emergency_contact_phone
	private String p_emergencyContactPhone;

	// 1不住家 ，2 住家 : is_home
	private String p_isHome;

	// 从事家政工作年限 : working_life
	private String p_workingLife;

	// 1 健康证，2 检验证明 ，3 已过期，4未办过 : health_certificate
	private String p_healthCertificate;

	// 1 不怕，2 害怕 : fear_pet
	private String p_fearPet;
	// 籍贯
	private String c_name;

	// 期望薪资 : salary_expectation
	private String p_salaryExpectation;
	// 查询期望薪资起
	private Long p_salaryExpectationBegin;
	// 查询期望薪资止
	private Long p_salaryExpectationEnd;
	// 工作期望内容 : job_expectation_content
	private String p_jobExpectationContent;

	// 特长 : specialty
	private String p_specialty;

	// 1 未删除 ， 2 已删除 : valid
	private Integer p_valid;

	// 是否到校 1已到校 2 未到校 : is_inschool
	private String p_isInschool;

	// 预约到校时间 : appointment_school_time
	private String p_appointmentSchoolTime;
	// 预约到校时间起 : appointment_school_time
	private String p_appointmentSchoolTimeBegin;
	// 预约到校时间 止: appointment_school_time
	private String p_appointmentSchoolTimeEnd;

	// 一级渠道 : l1_channel
	private String p_l1Channel;

	// 二级渠道 : l2_channel
	private String p_l2Channel;

	// 三级渠道 : l3_channel
	private Long p_l3Channel;
	// 优先渠道 : low_channel
	private Long p_lowChannel;
	// 创建人 : create_by
	private String p_createBy;

	// 修改人 : update_by
	private Long p_updateBy;

	// 创建时间 : createtime
	private java.util.Date p_createtime;
	// 创建时间起 : createtime
	private java.util.Date p_createtimeBegin;
	// 创建时间止 : createtime
	private java.util.Date p_createtimeEnd;

	// 修改时间 : updatetime
	private java.util.Date p_updatetime;

	// 产品管家id : productid
	private Long p_productid;
	private Long productId;
	// 隶属基地id : base_id
	private Integer p_baseId;

	// 员工类型 1.员工制 2.非员工制 : staff_type
	private String p_staffType;

	// 是否黑名单(1 是，2 否) : is_black
	private Integer p_isBlack;

	// 期望工种 : hope_work_type
	private String p_hopeWorkType;

	// 初试是否通过 : is_pass
	private Integer p_isPass;

	// 推荐人姓名 : recommend_name
	private String p_recommendName;

	// 推荐人电话 : recommend_phone
	private String p_recommendPhone;

	// : version
	private Long p_version;

	// 隶属城市 : in_city
	private String p_inCity;
	// 隶属省份
	private String p_inProvince;
	// 期望工作城市 : hopeCity
	private String hopeCity;

	// 创建时间起始
	private String p_creStart;

	// 创建时间结束
	private String p_creEnd;

	// 预约时间开始
	private String p_appStart;
	// 预约时间开始
	private String v_interview_type;

	// 预约时间结束
	private String p_appEnd;
	private Long version;

	private Long d_userId;
	private Long d_deptId;
	private Long d_operDeptId;
	private Long d_type;
	private Long d_valid;
	private Long d_version;

	// 协会会员 1是 2否
	private Long association;
	// 协会会员编码
	private Long association_code;
	// 管家帮会员
	private String personVipCard;
	private Integer isPersonVipCard;
	// 订单编号
	private Long orderCode;
	// 审核状态
	private Long review;
	// 审核结果
	private String review_result;
	// 查询是否有评级和银行信息 type 1 评级 2银行
	private int type;

	/** 2017-9-11新增 */
	/** t_crd-血型 (1,'A型',2,'B型',3,'O型',4,'AB型') **/
	private String blood;
	/** 证件类型 (1,'身份证',2,'护照') **/
	private String idType;
	/**
	 * 星座
	 * (1,'水瓶座',2,'双鱼座',3,'白羊座',4,'金牛座',5,'双子座',6,'巨蟹座',7,'狮子座',8,'处女座',9,'天秤座',
	 * 10,'天蝎座',11,'射手座',12,'摩羯座')
	 **/
	private String constellation;
	/** 出生日期 身份证系统计算所得;其他证件手动输入 **/
	private String birthday;
	/** 户籍类型 (1,'农业户口',2,'非农业户口') **/
	private String householdType;
	/** 病史(null为无病史,不为null为有病史,内容为病史内容) **/
	private String diseaseHistory;
	/** 厨师证等级 (1,'初级',2,'中级',3,'高级',4,'技师',5,'高级技师',) **/
	private String chefLevel;
	/**
	 * 驾驶证类型
	 * (1,'无',2,'A1',3,'A2',4,'A3',5,'B1',6,'B2',7,'C1',8,'C2',9,'C3',10,'C4')
	 **/
	private String drivingType;
	/** 微信号 **/
	private String wechatNum;
	/** QQ号 **/
	private String qqNum;
	/** 配偶是否在本市 1是 2否 **/
	private String coupleHere;
	/** 配偶是否有工作 1是 2否 **/
	private String coupleHasJob;
	/** 配偶职业 **/
	private String coupleProfession;
	/** 子女数量 **/
	private String childrenNum;
	/** 子女学校 **/
	private String childSchool;
	/** 子女是否有工作 1是 2否 **/
	private String childHasJob;
	/** 子女是否成家 1是 2否 **/
	private String childHasHome;
	/** 子女是否在本市 1是 2否 **/
	private String childInCity;
	/** 父母是否健在 1 父在 2母在 3 离世 4 健在 **/
	private String parentsAlive;
	/** 父母是否在本市 1是 2否 **/
	private String parentsHere;
	/** 当前服务人员用户类型 1游客 2想学 **/
	private String userType;
	/** 信息维护标识(回访备注 加筛选条件) **/
	private String informationNote;
	/** 是否喜欢宠物 (1,'是',2,'否') **/
	private String likePet;
	/** 子女职业 **/
	private String childProfession;
	/** 是否喜欢宠物 文字 **/
	private String likePetText;
	/**
	 * 家庭信息text展示
	 */
	private String coupleHereText;
	private String coupleHasJobText;
	private String childHasJobText;
	private String childHasHomeText;
	private String childInCityText;
	private String parentsAliveText;
	private String parentsHereText;

	/** 籍贯区县 **/
	private String p_originCounty;

	/** 客户类型文字 **/
	private String userTypeText;
	/** 厨师证文字 **/
	private String chefLevelText;
	/** 驾驶证文字 **/
	private String drivingTypeText;
	/** 是否有病史文字 **/
	private String diseaseHistoryType;
	/** 血型文字 **/
	private String bloodText;
	/** 证件类型文字 **/
	private String idTypeText;
	/** 星座文字 **/
	private String constellationText;
	/** 户口类型文字 **/
	private String householdTypeText;
	private String workLifePhoto;
	private String certificate;
	private String typeCodes;

	/**
	 * @return the p_appointmentSchoolTime
	 */
	public String getP_appointmentSchoolTime() {
		return p_appointmentSchoolTime;
	}

	public String getOriginal_address() {
		return original_address;
	}

	public void setOriginal_address(String original_address) {
		this.original_address = original_address;
	}

	/**
	 * @return the r_targetDept
	 */
	public Long getR_targetDept() {
		return r_targetDept;
	}

	/**
	 * @param r_targetDept
	 *            the r_targetDept to set
	 */
	public void setR_targetDept(Long r_targetDept) {
		this.r_targetDept = r_targetDept;
	}

	/**
	 * @return the d_userId
	 */
	public Long getD_userId() {
		return d_userId;
	}

	/**
	 * @param d_userId
	 *            the d_userId to set
	 */
	public void setD_userId(Long d_userId) {
		this.d_userId = d_userId;
	}

	/**
	 * @return the d_deptId
	 */
	public Long getD_deptId() {
		return d_deptId;
	}

	/**
	 * @param d_deptId
	 *            the d_deptId to set
	 */
	public void setD_deptId(Long d_deptId) {
		this.d_deptId = d_deptId;
	}

	/**
	 * @return the d_operDeptId
	 */
	public Long getD_operDeptId() {
		return d_operDeptId;
	}

	/**
	 * @param d_operDeptId
	 *            the d_operDeptId to set
	 */
	public void setD_operDeptId(Long d_operDeptId) {
		this.d_operDeptId = d_operDeptId;
	}

	/**
	 * @return the d_type
	 */
	public Long getD_type() {
		return d_type;
	}

	/**
	 * @param d_type
	 *            the d_type to set
	 */
	public void setD_type(Long d_type) {
		this.d_type = d_type;
	}

	/**
	 * @return the d_valid
	 */
	public Long getD_valid() {
		return d_valid;
	}

	/**
	 * @param d_valid
	 *            the d_valid to set
	 */
	public void setD_valid(Long d_valid) {
		this.d_valid = d_valid;
	}

	/**
	 * @return the d_version
	 */
	public Long getD_version() {
		return d_version;
	}

	/**
	 * @param d_version
	 *            the d_version to set
	 */
	public void setD_version(Long d_version) {
		this.d_version = d_version;
	}

	/**
	 * @return the p_creStart
	 */
	public String getP_creStart() {
		return p_creStart;
	}

	/**
	 * @param p_creStart
	 *            the p_creStart to set
	 */
	public void setP_creStart(String p_creStart) {
		this.p_creStart = p_creStart;
	}

	/**
	 * @return the p_creEnd
	 */
	public String getP_creEnd() {
		return p_creEnd;
	}

	/**
	 * @param p_creEnd
	 *            the p_creEnd to set
	 */
	public void setP_creEnd(String p_creEnd) {
		this.p_creEnd = p_creEnd;
	}

	/**
	 * @return the p_appStart
	 */
	public String getP_appStart() {
		return p_appStart;
	}

	/**
	 * @param p_appStart
	 *            the p_appStart to set
	 */
	public void setP_appStart(String p_appStart) {
		this.p_appStart = p_appStart;
	}

	/**
	 * @return the p_appEnd
	 */
	public String getP_appEnd() {
		return p_appEnd;
	}

	/**
	 * @param p_appEnd
	 *            the p_appEnd to set
	 */
	public void setP_appEnd(String p_appEnd) {
		this.p_appEnd = p_appEnd;
	}

	// workType 字段
	// 工种 : emp_work_type_name
	private String w_empWorkTypeName;

	// 工种ID : emp_work_type_id
	private Integer w_empWorkTypeId;

	// 工种等级ID : work_type_level_id
	private Integer w_workTypeLevelId;

	// 工种等级名称 : work_type_level_name
	private String w_workTypeLevelName;

	private String startTime;
	private String lineStart;
	private String lineEnd;
	private String endTime;
	private String productManagerName;

	public Long getId() {
		return id;
	}

	/**
	 * @return the w_empWorkTypeId
	 */
	public Integer getW_empWorkTypeId() {
		return w_empWorkTypeId;
	}

	/**
	 * @param w_empWorkTypeId
	 *            the w_empWorkTypeId to set
	 */
	public void setW_empWorkTypeId(Integer w_empWorkTypeId) {
		this.w_empWorkTypeId = w_empWorkTypeId;
	}

	/**
	 * @return the w_workTypeLevelId
	 */
	public Integer getW_workTypeLevelId() {
		return w_workTypeLevelId;
	}

	/**
	 * @param w_workTypeLevelId
	 *            the w_workTypeLevelId to set
	 */
	public void setW_workTypeLevelId(Integer w_workTypeLevelId) {
		this.w_workTypeLevelId = w_workTypeLevelId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public void setR_createtimeBegin(java.util.Date p_createtimeBegin) {
		this.p_createtimeBegin = p_createtimeBegin;
	}

	public java.util.Date getR_createtimeEnd() {
		return p_createtimeEnd;
	}

	public void setR_createtimeEnd(java.util.Date p_createtimeEnd) {
		this.p_createtimeEnd = p_createtimeEnd;
	}

	public java.util.Date getR_createtimeBegin() {
		return p_createtimeBegin;
	}

	/**
	 * @return the p_appointmentSchoolTimeBegin
	 */
	public String getP_appointmentSchoolTimeBegin() {
		return p_appointmentSchoolTimeBegin;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	/**
	 * @param p_appointmentSchoolTimeBegin
	 *            the p_appointmentSchoolTimeBegin to set
	 */
	public void setP_appointmentSchoolTimeBegin(String p_appointmentSchoolTimeBegin) {
		this.p_appointmentSchoolTimeBegin = p_appointmentSchoolTimeBegin;
	}

	/**
	 * @return the p_appointmentSchoolTimeEnd
	 */
	public String getP_appointmentSchoolTimeEnd() {
		return p_appointmentSchoolTimeEnd;
	}

	/**
	 * @param p_appointmentSchoolTimeEnd
	 *            the p_appointmentSchoolTimeEnd to set
	 */
	public void setP_appointmentSchoolTimeEnd(String p_appointmentSchoolTimeEnd) {
		this.p_appointmentSchoolTimeEnd = p_appointmentSchoolTimeEnd;
	}

	/**
	 * @param p_appointmentSchoolTime
	 *            the p_appointmentSchoolTime to set
	 */
	public void setP_appointmentSchoolTime(String p_appointmentSchoolTime) {
		this.p_appointmentSchoolTime = p_appointmentSchoolTime;
	}

	public Long getR_id() {
		return r_id;
	}

	public void setR_id(Long r_id) {
		this.r_id = r_id;
	}

	public Long getR_empId() {
		return r_empId;
	}

	public void setR_empId(Long r_empId) {
		this.r_empId = r_empId;
	}

	public Long getR_optUser() {
		return r_optUser;
	}

	public void setR_optUser(Long r_optUser) {
		this.r_optUser = r_optUser;
	}

	public Long getR_currentOptUser() {
		return r_currentOptUser;
	}

	public void setR_currentOptUser(Long r_currentOptUser) {
		this.r_currentOptUser = r_currentOptUser;
	}

	public java.util.Date getR_processTime() {
		return r_processTime;
	}

	public void setR_processTime(java.util.Date r_processTime) {
		this.r_processTime = r_processTime;
	}

	public Integer getR_l1Dept() {
		return r_l1Dept;
	}

	public void setR_l1Dept(Integer r_l1Dept) {
		this.r_l1Dept = r_l1Dept;
	}

	public Integer getR_l2Dept() {
		return r_l2Dept;
	}

	public void setR_l2Dept(Integer r_l2Dept) {
		this.r_l2Dept = r_l2Dept;
	}

	public Long getR_createBy() {
		return r_createBy;
	}

	public void setR_createBy(Long r_createBy) {
		this.r_createBy = r_createBy;
	}

	public Long getR_updateBy() {
		return r_updateBy;
	}

	public void setR_updateBy(Long r_updateBy) {
		this.r_updateBy = r_updateBy;
	}

	public java.util.Date getR_updatetime() {
		return r_updatetime;
	}

	public void setR_updatetime(java.util.Date r_updatetime) {
		this.r_updatetime = r_updatetime;
	}

	public java.util.Date getR_createtime() {
		return r_createtime;
	}

	public void setR_createtime(java.util.Date r_createtime) {
		this.r_createtime = r_createtime;
	}

	public Long getR_version() {
		return r_version;
	}

	public void setR_version(Long r_version) {
		this.r_version = r_version;
	}

	public Long getP_id() {
		return p_id;
	}

	public void setP_id(Long p_id) {
		this.p_id = p_id;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getP_sex() {
		return p_sex;
	}

	public void setP_sex(String p_sex) {
		this.p_sex = p_sex;
	}

	public String getP_age() {
		return p_age;
	}

	public void setP_age(String p_age) {
		this.p_age = p_age;
	}

	public Integer getP_ageBegin() {
		return p_ageBegin;
	}

	public void setP_ageBegin(Integer p_ageBegin) {
		this.p_ageBegin = p_ageBegin;
	}

	public Integer getP_ageEnd() {
		return p_ageEnd;
	}

	public void setP_ageEnd(Integer p_ageEnd) {
		this.p_ageEnd = p_ageEnd;
	}

	public String getP_origin() {
		return p_origin;
	}

	public void setP_origin(String p_origin) {
		this.p_origin = p_origin;
	}

	public String getP_zodiac() {
		return p_zodiac;
	}

	public void setP_zodiac(String p_zodiac) {
		this.p_zodiac = p_zodiac;
	}

	public String getP_idCardNum() {
		return p_idCardNum;
	}

	public void setP_idCardNum(String p_idCardNum) {
		this.p_idCardNum = p_idCardNum;
	}

	public String getP_mandarin() {
		return p_mandarin;
	}

	public void setP_mandarin(String p_mandarin) {
		this.p_mandarin = p_mandarin;
	}

	public String getP_nation() {
		return p_nation;
	}

	public void setP_nation(String p_nation) {
		this.p_nation = p_nation;
	}

	public String getP_mobile() {
		return p_mobile;
	}

	public void setP_mobile(String p_mobile) {
		this.p_mobile = p_mobile;
	}

	public String getP_isMarry() {
		return p_isMarry;
	}

	public void setP_isMarry(String p_isMarry) {
		this.p_isMarry = p_isMarry;
	}

	public String getP_currentAddress() {
		return p_currentAddress;
	}

	public void setP_currentAddress(String p_currentAddress) {
		this.p_currentAddress = p_currentAddress;
	}

	/**
	 * @return the p_usualLng
	 */
	public String getP_usualLng() {
		return p_usualLng;
	}

	/**
	 * @param p_usualLng
	 *            the p_usualLng to set
	 */
	public void setP_usualLng(String p_usualLng) {
		this.p_usualLng = p_usualLng;
	}

	/**
	 * @return the p_usualLat
	 */
	public String getP_usualLat() {
		return p_usualLat;
	}

	/**
	 * @param p_usualLat
	 *            the p_usualLat to set
	 */
	public void setP_usualLat(String p_usualLat) {
		this.p_usualLat = p_usualLat;
	}

	public String getP_homeAddress() {
		return p_homeAddress;
	}

	public void setP_homeAddress(String p_homeAddress) {
		this.p_homeAddress = p_homeAddress;
	}

	public String getP_education() {
		return p_education;
	}

	public void setP_education(String p_education) {
		this.p_education = p_education;
	}

	public String getP_emergencyContactPhone() {
		return p_emergencyContactPhone;
	}

	public void setP_emergencyContactPhone(String p_emergencyContactPhone) {
		this.p_emergencyContactPhone = p_emergencyContactPhone;
	}

	public String getP_isHome() {
		return p_isHome;
	}

	public void setP_isHome(String p_isHome) {
		this.p_isHome = p_isHome;
	}

	public String getP_workingLife() {
		return p_workingLife;
	}

	public void setP_workingLife(String p_workingLife) {
		this.p_workingLife = p_workingLife;
	}

	public String getP_healthCertificate() {
		return p_healthCertificate;
	}

	public void setP_healthCertificate(String p_healthCertificate) {
		this.p_healthCertificate = p_healthCertificate;
	}

	public String getP_fearPet() {
		return p_fearPet;
	}

	public void setP_fearPet(String p_fearPet) {
		this.p_fearPet = p_fearPet;
	}

	public String getP_salaryExpectation() {
		return p_salaryExpectation;
	}

	public void setP_salaryExpectation(String p_salaryExpectation) {
		this.p_salaryExpectation = p_salaryExpectation;
	}

	public Long getP_salaryExpectationBegin() {
		return p_salaryExpectationBegin;
	}

	public void setP_salaryExpectationBegin(Long p_salaryExpectationBegin) {
		this.p_salaryExpectationBegin = p_salaryExpectationBegin;
	}

	public Long getP_salaryExpectationEnd() {
		return p_salaryExpectationEnd;
	}

	public void setP_salaryExpectationEnd(Long p_salaryExpectationEnd) {
		this.p_salaryExpectationEnd = p_salaryExpectationEnd;
	}

	public String getP_jobExpectationContent() {
		return p_jobExpectationContent;
	}

	public void setP_jobExpectationContent(String p_jobExpectationContent) {
		this.p_jobExpectationContent = p_jobExpectationContent;
	}

	public String getP_specialty() {
		return p_specialty;
	}

	public void setP_specialty(String p_specialty) {
		this.p_specialty = p_specialty;
	}

	public Integer getP_valid() {
		return p_valid;
	}

	public void setP_valid(Integer p_valid) {
		this.p_valid = p_valid;
	}

	public String getP_isInschool() {
		return p_isInschool;
	}

	public void setP_isInschool(String p_isInschool) {
		this.p_isInschool = p_isInschool;
	}

	public String getP_l1Channel() {
		return p_l1Channel;
	}

	public void setP_l1Channel(String p_l1Channel) {
		this.p_l1Channel = p_l1Channel;
	}

	public String getP_l2Channel() {
		return p_l2Channel;
	}

	public void setP_l2Channel(String p_l2Channel) {
		this.p_l2Channel = p_l2Channel;
	}

	public Long getP_l3Channel() {
		return p_l3Channel;
	}

	public void setP_l3Channel(Long p_l3Channel) {
		this.p_l3Channel = p_l3Channel;
	}

	/**
	 * @return the p_lowChannel
	 */
	public Long getP_lowChannel() {
		return p_lowChannel;
	}

	/**
	 * @param p_lowChannel
	 *            the p_lowChannel to set
	 */
	public void setP_lowChannel(Long p_lowChannel) {
		this.p_lowChannel = p_lowChannel;
	}

	public String getP_createBy() {
		return p_createBy;
	}

	public void setP_createBy(String p_createBy) {
		this.p_createBy = p_createBy;
	}

	public Long getP_updateBy() {
		return p_updateBy;
	}

	public void setP_updateBy(Long p_updateBy) {
		this.p_updateBy = p_updateBy;
	}

	public java.util.Date getP_createtime() {
		return p_createtime;
	}

	public void setP_createtime(java.util.Date p_createtime) {
		this.p_createtime = p_createtime;
	}

	public java.util.Date getP_updatetime() {
		return p_updatetime;
	}

	public void setP_updatetime(java.util.Date p_updatetime) {
		this.p_updatetime = p_updatetime;
	}

	/**
	 * @return the p_productid
	 */
	public Long getP_productid() {
		return p_productid;
	}

	public Integer getP_baseId() {
		return p_baseId;
	}

	public void setP_baseId(Integer p_baseId) {
		this.p_baseId = p_baseId;
	}

	/**
	 * @return the p_staffType
	 */
	public String getP_staffType() {
		return p_staffType;
	}

	/**
	 * @param p_staffType
	 *            the p_staffType to set
	 */
	public void setP_staffType(String p_staffType) {
		this.p_staffType = p_staffType;
	}

	/**
	 * @param p_productid
	 *            the p_productid to set
	 */
	public void setP_productid(Long p_productid) {
		this.p_productid = p_productid;
	}

	public Integer getP_isBlack() {
		return p_isBlack;
	}

	public void setP_isBlack(Integer p_isBlack) {
		this.p_isBlack = p_isBlack;
	}

	public String getP_hopeWorkType() {
		return p_hopeWorkType;
	}

	public void setP_hopeWorkType(String p_hopeWorkType) {
		this.p_hopeWorkType = p_hopeWorkType;
	}

	public Integer getP_isPass() {
		return p_isPass;
	}

	public void setP_isPass(Integer p_isPass) {
		this.p_isPass = p_isPass;
	}

	public String getP_recommendName() {
		return p_recommendName;
	}

	public void setP_recommendName(String p_recommendName) {
		this.p_recommendName = p_recommendName;
	}

	public String getP_recommendPhone() {
		return p_recommendPhone;
	}

	public void setP_recommendPhone(String p_recommendPhone) {
		this.p_recommendPhone = p_recommendPhone;
	}

	public Long getP_version() {
		return p_version;
	}

	public void setP_version(Long p_version) {
		this.p_version = p_version;
	}

	public String getP_inCity() {
		return p_inCity;
	}

	public void setP_inCity(String p_inCity) {
		this.p_inCity = p_inCity;
	}

	public String getW_empWorkTypeName() {
		return w_empWorkTypeName;
	}

	public void setW_empWorkTypeName(String w_empWorkTypeName) {
		this.w_empWorkTypeName = w_empWorkTypeName;
	}

	public String getW_workTypeLevelName() {
		return w_workTypeLevelName;
	}

	public void setW_workTypeLevelName(String w_workTypeLevelName) {
		this.w_workTypeLevelName = w_workTypeLevelName;
	}

	public java.util.Date getP_createtimeBegin() {
		return p_createtimeBegin;
	}

	public void setP_createtimeBegin(java.util.Date p_createtimeBegin) {
		this.p_createtimeBegin = p_createtimeBegin;
	}

	public java.util.Date getP_createtimeEnd() {
		return p_createtimeEnd;
	}

	public void setP_createtimeEnd(java.util.Date p_createtimeEnd) {
		this.p_createtimeEnd = p_createtimeEnd;
	}

	@Override
	public Long getVersion() {
		return version;
	}

	@Override
	public void setVersion(Long version) {
		this.version = version;
	}

	/**
	 * @return the g_empId
	 */
	public Long getG_empId() {
		return g_empId;
	}

	/**
	 * @param g_empId
	 *            the g_empId to set
	 */
	public void setG_empId(Long g_empId) {
		this.g_empId = g_empId;
	}

	/**
	 * @return the g_gradeUser
	 */
	public String getG_gradeUser() {
		return g_gradeUser;
	}

	/**
	 * @param g_gradeUser
	 *            the g_gradeUser to set
	 */
	public void setG_gradeUser(String g_gradeUser) {
		this.g_gradeUser = g_gradeUser;
	}

	/**
	 * @return the g_gradeContent
	 */
	public String getG_gradeContent() {
		return g_gradeContent;
	}

	/**
	 * @param g_gradeContent
	 *            the g_gradeContent to set
	 */
	public void setG_gradeContent(String g_gradeContent) {
		this.g_gradeContent = g_gradeContent;
	}

	/**
	 * @return the g_gradeTime
	 */
	public java.util.Date getG_gradeTime() {
		return g_gradeTime;
	}

	/**
	 * @param g_gradeTime
	 *            the g_gradeTime to set
	 */
	public void setG_gradeTime(java.util.Date g_gradeTime) {
		this.g_gradeTime = g_gradeTime;
	}

	/**
	 * @return the g_gradeType
	 */
	public Integer getG_gradeType() {
		return g_gradeType;
	}

	/**
	 * @param g_gradeType
	 *            the g_gradeType to set
	 */
	public void setG_gradeType(Integer g_gradeType) {
		this.g_gradeType = g_gradeType;
	}

	/**
	 * @return the g_createBy
	 */
	public Long getG_createBy() {
		return g_createBy;
	}

	/**
	 * @param g_createBy
	 *            the g_createBy to set
	 */
	public void setG_createBy(Long g_createBy) {
		this.g_createBy = g_createBy;
	}

	/**
	 * @return the g_updateBy
	 */
	public Long getG_updateBy() {
		return g_updateBy;
	}

	/**
	 * @param g_updateBy
	 *            the g_updateBy to set
	 */
	public void setG_updateBy(Long g_updateBy) {
		this.g_updateBy = g_updateBy;
	}

	/**
	 * @return the g_updatetime
	 */
	public java.util.Date getG_updatetime() {
		return g_updatetime;
	}

	/**
	 * @param g_updatetime
	 *            the g_updatetime to set
	 */
	public void setG_updatetime(java.util.Date g_updatetime) {
		this.g_updatetime = g_updatetime;
	}

	/**
	 * @return the g_createtime
	 */
	public java.util.Date getG_createtime() {
		return g_createtime;
	}

	/**
	 * @param g_createtime
	 *            the g_createtime to set
	 */
	public void setG_createtime(java.util.Date g_createtime) {
		this.g_createtime = g_createtime;
	}

	/**
	 * @return the g_version
	 */
	public Long getG_version() {
		return g_version;
	}

	/**
	 * @param g_version
	 *            the g_version to set
	 */
	public void setG_version(Long g_version) {
		this.g_version = g_version;
	}

	public String getV_interview_type() {
		return v_interview_type;
	}

	public void setV_interview_type(String v_interview_type) {
		this.v_interview_type = v_interview_type;
	}

	public String getP_originCity() {
		return p_originCity;
	}

	public void setP_originCity(String p_originCity) {
		this.p_originCity = p_originCity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getHealthtime() {
		return healthtime;
	}

	public void setHealthtime(String healthtime) {
		this.healthtime = healthtime;
	}

	public long getManagementStatus() {
		return managementStatus;
	}

	public void setManagementStatus(long managementStatus) {
		this.managementStatus = managementStatus;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getSort_test() {
		return sort_test;
	}

	public void setSort_test(Integer sort_test) {
		this.sort_test = sort_test;
	}

	public String getHealthtimeend() {
		return healthtimeend;
	}

	public void setHealthtimeend(String healthtimeend) {
		this.healthtimeend = healthtimeend;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getLineStart() {
		return lineStart;
	}

	public void setLineStart(String lineStart) {
		this.lineStart = lineStart;
	}

	public String getLineEnd() {
		return lineEnd;
	}

	public void setLineEnd(String lineEnd) {
		this.lineEnd = lineEnd;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getAssociation() {
		return association;
	}

	public void setAssociation(Long association) {
		this.association = association;
	}

	public String getPersonVipCard() {
		return personVipCard;
	}

	public void setPersonVipCard(String personVipCard) {
		this.personVipCard = personVipCard;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Long getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(Long orderCode) {
		this.orderCode = orderCode;
	}

	public Long getReview() {
		return review;
	}

	public void setReview(Long review) {
		this.review = review;
	}

	public String getReview_result() {
		return review_result;
	}

	public void setReview_result(String review_result) {
		this.review_result = review_result;
	}

	public Long getAssociation_code() {
		return association_code;
	}

	public void setAssociation_code(Long association_code) {
		this.association_code = association_code;
	}

	public String getCoupleProfession() {
		return coupleProfession;
	}

	public void setCoupleProfession(String coupleProfession) {
		this.coupleProfession = coupleProfession;
	}

	public String getChildSchool() {
		return childSchool;
	}

	public void setChildSchool(String childSchool) {
		this.childSchool = childSchool;
	}

	public String getInformationNote() {
		return informationNote;
	}

	public void setInformationNote(String informationNote) {
		this.informationNote = informationNote;
	}

	public String getChildProfession() {
		return childProfession;
	}

	public void setChildProfession(String childProfession) {
		this.childProfession = childProfession;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getDiseaseHistory() {
		return diseaseHistory;
	}

	public void setDiseaseHistory(String diseaseHistory) {
		this.diseaseHistory = diseaseHistory;
	}

	public String getWechatNum() {
		return wechatNum;
	}

	public void setWechatNum(String wechatNum) {
		this.wechatNum = wechatNum;
	}

	public String getQqNum() {
		return qqNum;
	}

	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}

	public String getCoupleHereText() {
		return coupleHereText;
	}

	public void setCoupleHereText(String coupleHereText) {
		this.coupleHereText = coupleHereText;
	}

	public String getCoupleHasJobText() {
		return coupleHasJobText;
	}

	public void setCoupleHasJobText(String coupleHasJobText) {
		this.coupleHasJobText = coupleHasJobText;
	}

	public String getChildHasJobText() {
		return childHasJobText;
	}

	public void setChildHasJobText(String childHasJobText) {
		this.childHasJobText = childHasJobText;
	}

	public String getChildHasHomeText() {
		return childHasHomeText;
	}

	public void setChildHasHomeText(String childHasHomeText) {
		this.childHasHomeText = childHasHomeText;
	}

	public String getChildInCityText() {
		return childInCityText;
	}

	public void setChildInCityText(String childInCityText) {
		this.childInCityText = childInCityText;
	}

	public String getParentsAliveText() {
		return parentsAliveText;
	}

	public void setParentsAliveText(String parentsAliveText) {
		this.parentsAliveText = parentsAliveText;
	}

	public String getParentsHereText() {
		return parentsHereText;
	}

	public void setParentsHereText(String parentsHereText) {
		this.parentsHereText = parentsHereText;
	}

	public String getP_originCounty() {
		return p_originCounty;
	}

	public void setP_originCounty(String p_originCounty) {
		this.p_originCounty = p_originCounty;
	}

	public String getUserTypeText() {
		return userTypeText;
	}

	public void setUserTypeText(String userTypeText) {
		this.userTypeText = userTypeText;
	}

	public String getChefLevelText() {
		return chefLevelText;
	}

	public void setChefLevelText(String chefLevelText) {
		this.chefLevelText = chefLevelText;
	}

	public String getDrivingTypeText() {
		return drivingTypeText;
	}

	public void setDrivingTypeText(String drivingTypeText) {
		this.drivingTypeText = drivingTypeText;
	}

	public String getDiseaseHistoryType() {
		return diseaseHistoryType;
	}

	public void setDiseaseHistoryType(String diseaseHistoryType) {
		this.diseaseHistoryType = diseaseHistoryType;
	}

	public String getBloodText() {
		return bloodText;
	}

	public void setBloodText(String bloodText) {
		this.bloodText = bloodText;
	}

	public String getIdTypeText() {
		return idTypeText;
	}

	public void setIdTypeText(String idTypeText) {
		this.idTypeText = idTypeText;
	}

	public String getConstellationText() {
		return constellationText;
	}

	public void setConstellationText(String constellationText) {
		this.constellationText = constellationText;
	}

	public String getHouseholdTypeText() {
		return householdTypeText;
	}

	public void setHouseholdTypeText(String householdTypeText) {
		this.householdTypeText = householdTypeText;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public String getHouseholdType() {
		return householdType;
	}

	public void setHouseholdType(String householdType) {
		this.householdType = householdType;
	}

	public String getChefLevel() {
		return chefLevel;
	}

	public void setChefLevel(String chefLevel) {
		this.chefLevel = chefLevel;
	}

	public String getDrivingType() {
		return drivingType;
	}

	public void setDrivingType(String drivingType) {
		this.drivingType = drivingType;
	}

	public String getCoupleHere() {
		return coupleHere;
	}

	public void setCoupleHere(String coupleHere) {
		this.coupleHere = coupleHere;
	}

	public String getCoupleHasJob() {
		return coupleHasJob;
	}

	public void setCoupleHasJob(String coupleHasJob) {
		this.coupleHasJob = coupleHasJob;
	}

	public String getChildrenNum() {
		return childrenNum;
	}

	public void setChildrenNum(String childrenNum) {
		this.childrenNum = childrenNum;
	}

	public String getChildHasJob() {
		return childHasJob;
	}

	public void setChildHasJob(String childHasJob) {
		this.childHasJob = childHasJob;
	}

	public String getChildHasHome() {
		return childHasHome;
	}

	public void setChildHasHome(String childHasHome) {
		this.childHasHome = childHasHome;
	}

	public String getChildInCity() {
		return childInCity;
	}

	public void setChildInCity(String childInCity) {
		this.childInCity = childInCity;
	}

	public String getParentsAlive() {
		return parentsAlive;
	}

	public void setParentsAlive(String parentsAlive) {
		this.parentsAlive = parentsAlive;
	}

	public String getParentsHere() {
		return parentsHere;
	}

	public void setParentsHere(String parentsHere) {
		this.parentsHere = parentsHere;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getLikePet() {
		return likePet;
	}

	public void setLikePet(String likePet) {
		this.likePet = likePet;
	}

	public String getLikePetText() {
		return likePetText;
	}

	public void setLikePetText(String likePetText) {
		this.likePetText = likePetText;
	}

	public String getP_inProvince() {
		return p_inProvince;
	}

	public void setP_inProvince(String p_inProvince) {
		this.p_inProvince = p_inProvince;
	}

	public Integer getIsPersonVipCard() {
		return isPersonVipCard;
	}

	public void setIsPersonVipCard(Integer isPersonVipCard) {
		this.isPersonVipCard = isPersonVipCard;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getHopeCity() {
		return hopeCity;
	}

	public void setHopeCity(String hopeCity) {
		this.hopeCity = hopeCity;
	}

	public String getWorkLifePhoto() {
		return workLifePhoto;
	}

	public void setWorkLifePhoto(String workLifePhoto) {
		this.workLifePhoto = workLifePhoto;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getProductManagerName() {
		return productManagerName;
	}

	public void setProductManagerName(String productManagerName) {
		this.productManagerName = productManagerName;
	}

	public String getTypeCodes() {
		return typeCodes;
	}

	public void setTypeCodes(String typeCodes) {
		this.typeCodes = typeCodes;
	}
	

}
