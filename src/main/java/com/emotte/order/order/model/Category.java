package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_product_category
 * 
 * 
 * @author army
 */
public class Category extends BaseModel {

	// 商品分类表 Id 基础表请使用code来关联 : id
	private Long id;

	// 父id : fid
	private Long fid;

	// t_product_type表id 商品类型表id : product_type_id
	private Long productTypeId;

	// 分类code 码 基础表都用code 不用id 作为表之间的关联 : code
	private String code;

	// 分类码 -1-2-55 : org_code
	private String orgCode;

	// 中文分类名 : cname
	private String cname;

	// 英语名字 : ename
	private String ename;

	// 图标路径 : icon
	private String icon;

	// 排序 权重 倒序 : priority
	private Integer priority;

	// 排序 权重 倒序 : priority
	private Integer showType;

	// 1:单次服务，2:延续性服务，3:商品，还可以多加
	private Integer cateType;

	// 是否显示 1显示 2 不显示 : display
	private Integer display;

	// 创建人 系统登录表的id : create_by
	private Long createBy;

	// 修改人id : update_by
	private Long updateBy;

	// 是否有效 1 有效 ， 2 无效 : valid
	private Integer valid;

	// 版本号 : version
	private Long version;

	// 辅助
	private String isParent;

	// 辅助 用来树是否显示checkbox
	private Boolean nocheck;

	// 辅助 用来树是否有此城市id
	private String cityCode;

	// 辅助 节点是否被选中
	private Boolean checked;

	// 辅助属性
	private Integer level;

	// 辅助属性 设置树节点是否打开
	private boolean open;

	/**
	 * constitution
	 *
	 */

	/**
	 * 商品分类表 Id 基础表请使用code来关联 : id
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 商品分类表 Id 基础表请使用code来关联 : id
	 * 
	 * @return
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 父id : fid
	 * 
	 * @return
	 */
	public Long getFid() {
		return fid;
	}

	/**
	 * 父id : fid
	 * 
	 * @return
	 */
	public void setFid(Long fid) {
		this.fid = fid;
	}

	/**
	 * t_product_type表id 商品类型表id : product_type_id
	 * 
	 * @return
	 */
	public Long getProductTypeId() {
		return productTypeId;
	}

	/**
	 * t_product_type表id 商品类型表id : product_type_id
	 * 
	 * @return
	 */
	public void setProductTypeId(Long productTypeId) {
		this.productTypeId = productTypeId;
	}

	/**
	 * 分类code 码 基础表都用code 不用id 作为表之间的关联 : code
	 * 
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 分类code 码 基础表都用code 不用id 作为表之间的关联 : code
	 * 
	 * @return
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 分类码 -1-2-55 : org_code
	 * 
	 * @return
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * 分类码 -1-2-55 : org_code
	 * 
	 * @return
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * 中文分类名 : cname
	 * 
	 * @return
	 */
	public String getCname() {
		return cname;
	}

	/**
	 * 中文分类名 : cname
	 * 
	 * @return
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}

	/**
	 * 英语名字 : ename
	 * 
	 * @return
	 */
	public String getEname() {
		return ename;
	}

	/**
	 * 英语名字 : ename
	 * 
	 * @return
	 */
	public void setEname(String ename) {
		this.ename = ename;
	}

	/**
	 * 图标路径 : icon
	 * 
	 * @return
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * 图标路径 : icon
	 * 
	 * @return
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 排序 权重 倒序 : priority
	 * 
	 * @return
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * 排序 权重 倒序 : priority
	 * 
	 * @return
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * 是否显示 1显示 2 不显示 : display
	 * 
	 * @return
	 */
	public Integer getDisplay() {
		return display;
	}

	/**
	 * 是否显示 1显示 2 不显示 : display
	 * 
	 * @return
	 */
	public void setDisplay(Integer display) {
		this.display = display;
	}

	/**
	 * 创建人 系统登录表的id : create_by
	 * 
	 * @return
	 */
	@Override
	public Long getCreateBy() {
		return createBy;
	}

	/**
	 * 创建人 系统登录表的id : create_by
	 * 
	 * @return
	 */
	@Override
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	/**
	 * 修改人id : update_by
	 * 
	 * @return
	 */
	@Override
	public Long getUpdateBy() {
		return updateBy;
	}

	/**
	 * 修改人id : update_by
	 * 
	 * @return
	 */
	@Override
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * 是否有效 1 有效 ， 2 无效 : valid
	 * 
	 * @return
	 */
	public Integer getValid() {
		return valid;
	}

	/**
	 * 是否有效 1 有效 ， 2 无效 : valid
	 * 
	 * @return
	 */
	public void setValid(Integer valid) {
		this.valid = valid;
	}

	/**
	 * 版本号 : version
	 * 
	 * @return
	 */
	@Override
	public Long getVersion() {
		return version;
	}

	/**
	 * 版本号 : version
	 * 
	 * @return
	 */
	@Override
	public void setVersion(Long version) {
		this.version = version;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public Boolean getNocheck() {
		return nocheck;
	}

	public void setNocheck(Boolean nocheck) {
		this.nocheck = nocheck;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public Boolean getChecked() {
		if (this.getCityCode() != null && !this.getCityCode().equals("")) {
			return true;
		}
		return false;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public Integer getShowType() {
		return showType;
	}

	public void setShowType(Integer showType) {
		this.showType = showType;
	}

	public Integer getCateType() {
		return cateType;
	}

	public void setCateType(Integer cateType) {
		this.cateType = cateType;
	}

}
