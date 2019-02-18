package com.emotte.order.order.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.atteo.moonshine.jta.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.wildhorse.server.core.exception.BaseException;

import com.emotte.kernel.redis.EJedisPool;
import com.emotte.order.authorg.model.Org;
import com.emotte.order.authorg.service.OrgService;
import com.emotte.order.constant.CommonConstant;
import com.emotte.order.order.mapper.writer.WrOrderTurnLogMapper;
import com.emotte.order.order.model.Order;
import com.emotte.order.util.LogHelper;

/**
 * @author 作者 zj:
 * @version 1.0
 * @date 创建时间：2018年5月4日 上午9:28:02
 * @parameter
 * @return
 */
@Transactional
public abstract class AbstractOrderDistribute implements OrderDistributeService {
	
	/**自营单次*/
    public static final int CATE_TYPE_SELF_ONCE = 1;
    /**自营延续*/
    public static final int CATE_TYPE_SELF_YANXU = 2;
    /**自营商品*/
    public static final int CATE_TYPE_SELF_FA = 3;
    /**他营单次*/
    public static final int CATE_TYPE_OTHER_ONCE = 4;
    /**他营商品*/
    public static final int CATE_TYPE_SELF_TYFA = 7;
    /**解决方案*/
    public static final int CATE_TYPE_SOLUTION = 8;
    
    @Resource
    public OrgService orgService;
    
    @Autowired
    public EJedisPool eJedisPool;
    
    @Resource
    WrOrderTurnLogMapper wrOrderTurnLogMapper;

    @Override
    public boolean distribute(Order order) {
        /*
		 * 需求
		 * 1、FA订单分发（写死cpsyb 产品事业部）
		 * 2、三方商家订单不分发
		 * 3、解决方案订单不分发
		 * 4、自营单次/自营延续性订单
		 * 新增订单：增加选择“管家派单”和“自动派单”功能
		 * 	A、管家派单：管家可指定将订单分发到门店/门店基地的管家
		 *	B、自动派单：系统根据订单分发逻辑自动分发到相应门店/门店基地或者管家
		 * 		1、按照门店/门店基地与渠道的受理关系过滤
		 * 		2、按照门店/门店基地与商品的受理关系过滤（如果门店/门店基地没有配置相应订单受理类型，订单将分派到呼叫中心）
		 * 		3、按照门店/门店基地与服务地址的距离最终匹配到一个门店/门店基地
		 */
        boolean result = false;
        try {
            if (order.getCity() == null || order.getOrderChannel() == null || (order.getProductCode() == null && order.getCateType() != 3)) {
                throw new BaseException("不能为空,城市:" + order.getCity() + ",渠道:" + order.getOrderChannel() + ",商品码:" + order.getProductCode());
            }
            if (null != order.getRechargeDept() && order.getRechargeDept() > 0) {
            	// 判断是否为自动派单
            	// 负责部门不为空表示管家指定了，不需要再自动派单了
                return true;
            }
            Integer cateType = order.getCateType();
            switch (cateType) {
                case CATE_TYPE_SELF_ONCE:
                    result = distributeOnSelfOnce(order);
                    break;
                case CATE_TYPE_SELF_YANXU:
                    result = distributeOnSelfYanxu(order);
                    break;
                case CATE_TYPE_SELF_FA:
                    result = distributeOnSelfFa(order);
                    break;
                case CATE_TYPE_OTHER_ONCE:
                    break;
                case CATE_TYPE_SOLUTION:
                    break;
                default:
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	
        }
        return result;
    }

    /**
     * <b>自营延续分发</b>
     * <p>
     * 1、按照门店/门店基地与渠道的受理关系过滤<br>
     * 2、按照门店/门店基地与商品的受理关系过滤（如果门店/门店基地没有配置相应订单受理类型，订单将分派到呼叫中心）<br>
     * 3、按照门店/门店基地与服务地址的距离最终匹配到一个门店/门店基地<br>
     * </p>
     *
     * @param order
     * @return 2017年1月9日
     */
    public boolean distributeOnSelfYanxu(Order order) throws BaseException {
        try {
            Long rechargeUser = null;
            String rechargeUserName = null;
            Long rechargeOrg = null;
            String rechargeOrgName = null;

            // 先判断录入人是否为营销管家，或者部门是否为门店
//		Long recorder = order.getCreateBy(); // 录入人ID
//		Map<String, Object> manerinfo = authManagerService.loadManagerInfo (recorder);
//		String position = MapUtils.getString(manerinfo, "post"); // 岗位
//		if (null != position && "20140005|20140006|20140007".contains(position)) { // 判断是否为营销管家（20140005：营销大管家|20140006：营销高级管家|20140007：营销管家）
//			rechargeUser = MapUtils.getLong(manerinfo, "userId"); // 负责人ID
//			rechargeUserName = MapUtils.getString(manerinfo, "realName"); // 负责人名称
//			rechargeOrg = MapUtils.getLong(manerinfo, "orgId"); // 负责人部门ID
//			rechargeOrgName = MapUtils.getString(manerinfo, "orgName"); // 负责人部门名称
//		} else {
//			int deptType = MapUtils.getInteger(manerinfo, "orgType"); // 部门类型
//			if (5 == deptType || 6 == deptType || 7 == deptType) { // 判断是否为5门店，6基地，7门店基地
//				rechargeUser = MapUtils.getLong(manerinfo, "userId"); // 负责人ID
//				rechargeUserName = MapUtils.getString(manerinfo, "realName"); // 负责人名称
//				rechargeOrg = MapUtils.getLong(manerinfo, "orgId"); // 负责人部门ID
//				rechargeOrgName = MapUtils.getString(manerinfo, "orgName"); // 负责人部门名称
//			} else {
            List<Org> list = new ArrayList<Org>(); // 用于存储筛选出来的门店，基地
					/*if (!filterByChannel(order, list)) {
						if (!filterByProduct(order, list)) {
							if ( order.getLongitude() != null && order.getLatitude() != null ) {
								filterByDistance(order, list);
							} else {
								list.clear();
							}
						}
					}*/
            //自动分发老流程
            if (!filterByChannelAndProduct(order, list)) {
                if (order.getLongitude() != null && order.getLatitude() != null) {
                    //判断符合所有订单分发的门店
                    //filterByDistance(order, list);
                    filterAllOrgByDistance(order, list);
                    //判断最近的一个门店或基地
                    filterOrgByTypeAndDistance(order, list);
                    //1，新list不为空
                    //for循环，判断第一个最短距离的门店或基地是不是易盟的
                    //list.size()为空 则执行循环
                    //是，则再判断范围，
                    //范围为空，则按照全市分发，调用易盟标记的sql,取第一个
                    //范围不为空
                    //在范围内，直接分发
                    //不在范围内，跳到下一条，直到成功放入list一条数据
                    //不是，先判断list中有没有易盟，
                    //有，则继续循环，找到易盟门店，直接分发
                    //没有，则判断范围
                    //范围为空，则按照全市分发，调用易盟标记的sql,取第一个
                    //范围不为空
                    //在范围内，直接分发
                    //不在范围内，跳到下一条，直到成功放入list一条数据
                    //2，list为空
                    //进入呼叫中心分发
                    //添加订单负责人字段
                    if (!list.isEmpty() && list.get(0).getManagerId() != null) {
                        rechargeUser = list.get(0).getManagerId();
                    }
                } else {
                    list.clear();
                }
                order.setIsNewFF(2);//老流程标记
            }

            //自动分发新流程
            //判断是否北京的延续订单
            if (list.size() != 0 && !list.isEmpty()) {
                if (CommonConstant.BEIJING_CITY_CODE.equals(order.getCity()) && order.getCateType() == 2
                        && (CommonConstant.BEIJING_QIJI_DEPT_ID.equals(list.get(0).getId().toString())        //奇迹队门店
                        || CommonConstant.BEIJING_XIONGYING_DEPT_ID.equals(list.get(0).getId().toString())    //雄鹰队门店
                        || CommonConstant.BEIJING_ZHULI_DEPT_ID.equals(list.get(0).getId().toString())        //主力队门店
                        || CommonConstant.BEIJING_LIEYING_DEPT_ID.equals(list.get(0).getId().toString())      //猎鹰队门店
                        || CommonConstant.BEIJING_JINGYING_DEPT_ID.equals(list.get(0).getId().toString())     //精英队门店
                        || CommonConstant.BEIJING_TENGFEI_DEPT_ID.equals(list.get(0).getId().toString())      //腾飞队门店
                )) {
                    rechargeUser = checkRechargeManager(list, order);
                }
            }


            if (list.isEmpty()) {
                if (callCenter(list)) {
                    rechargeOrg = list.get(0).getId();
                    rechargeOrgName = list.get(0).getName();
                } else {
                    return false; // 获取负责人，负责部门失败
                }
            } else {
                rechargeOrg = list.get(0).getId();
                rechargeOrgName = list.get(0).getName();
            }
//			}
//		}
            order.setRechargeBy(rechargeUser);
            order.setRechargeByName(rechargeUserName);
            order.setRechargeDept(rechargeOrg);
            order.setRechargeDeptName(rechargeOrgName);

        } catch (Exception e) {
            LogHelper.error(getClass(), "分发失败" + e.getMessage());
            throw new BaseException();
        }
        return true;
    }

    /**
     * FA订单分发
     *
     * @param order
     * @return 2017年1月19日
     */
    public boolean distributeOnSelfFa(Order order) {
        //查询是否是管家下单，是管家下单走后台
        if (orgService.queryGuanjia(order.getCreateBy())) {
            order.setRechargeBy(order.getCreateBy());
            order.setRechargeDept(order.getCreateDept());
        } else {
            order.setRechargeBy(97625l);//cpsyb
            order.setRechargeDept(12891953l);// 产品事业部
        }
        return true;
    }

    /**
     * 自营单次分发
     *
     * @param order
     * @return true:成功<br>false:失败<br>
     * 2017年1月9日
     */
    public boolean distributeOnSelfOnce(Order order) {
        return distributeOnSelfYanxu(order);
    }

    /**
     * 根据距离，查出所有符合条件的门店、基地
     *
     * @param order
     * @param list
     */
    public boolean filterAllOrgByDistance(Order order, List<Org> list) {
        List<Org> tmp = orgService.queryAllOrgByDistance(order.getLongitude(), order.getLatitude(), order.getCity(), list);
        list.clear();
        if (tmp != null && !tmp.isEmpty()) {
            list.addAll(tmp);
            if (list.size() == 1) return true;
        } else {
            return true; // 如果为空直接跳转到呼叫中心
        }
        return false;
    }

    /**
     * 新流程确定负责管家判断
     *
     * @param list
     * @param order
     * @param rechargeUser
     * @return
     */
    public Long checkRechargeManager(List<Org> list, Order order) {
        Long rechargeUser = null;
        //注释匹配客户是否有老单的负责部门和负责人功能
        //根据客户电话，查询客户是否下单过延续服务订单
		/*List<Order> orderList = this.orderService.queryOrderByUserMobile(order.getUserMobile());
		if (orderList.size() != 0 && !orderList.isEmpty()) {
			for (Order order2 : orderList) {
				//如果有，判断负责人是否存在，存在，指派新单负责人为原订单负责人
				if (order2.getRechargeBy() != null) {
					rechargeUser = order2.getRechargeBy();
					list.clear();
					List<Org> temp = new ArrayList<Org>();
					Org org =new Org();
					org.setId(order2.getRechargeDept());
					temp.add(org);
					list.addAll(temp);
					order.setIsNewFF(3);//新流程标记2
					break ;
				} else {//如果没有，轮单
					//轮单
					distributeInPeking(list);
					if (list.size() == 0 || list.isEmpty()) {
						break;
					}else {
						rechargeUser = Long.parseLong(list.get(0).getManager().toString());
						order.setIsNewFF(3);//新流程标记2
						break;
					}
				}
			}
		} else {
		}*/
        //轮单
        rechargeUser = distributeInPeking(list);
		/*if (list.size() != 0 && !list.isEmpty()) {
			rechargeUser = Long.parseLong(list.get(0).getManager().toString());
		}*/
        order.setIsNewFF(1);//新流程标记1
        return rechargeUser;
    }

    public boolean filterByChannelAndProduct(Order order, List<Org> list) {
        List<Org> tmp = orgService.queryByChannelAndProduct(order.getOrderChannel(), order.getProductCode(), order.getCity(), list);
        list.clear();
        if (tmp != null && !tmp.isEmpty()) {
            list.addAll(tmp);
//			if (list.size() == 1) return true;
        } else {
            return true; // 如果为空直接跳转到呼叫中心
        }
        return false;
    }

    public Long distributeInPeking(List<Org> list) {
        Long rechargeUser = null;
        List<Org> tmp = orgService.distributeInPeking();
        list.clear();
        if (tmp != null && !tmp.isEmpty()) {
            list.addAll(tmp);
        }
        if (list.size() != 0 && !list.isEmpty()) {
            rechargeUser = Long.parseLong(list.get(0).getManager().toString());
        }
        return rechargeUser;
    }

    /**
     * 判断最近的一个门店或基地
     *
     * @param order
     * @param list
     * @return
     */
    public boolean filterOrgByTypeAndDistance(Order order, List<Org> list) {
        if (list != null && list.size() != 0) {
            for (Org org : list) {
                //type2 类型: 1公司、2第三方、3加盟商、4分公司、5子公司、 6共享模式、88无
                //accept_type接单类型:(1全市、2手动设置)
                //accept_range接单范围:几公里范围
                if (org.getType2() != null && org.getType2() == 3 && org.getAcceptType() != null &&
                        org.getAcceptType() == 2 && org.getAcceptRange() != null) {
                    //判断加盟商范围，是否符合，不符合则移除
                    if (!filterByAcceptRange(org, order, list)) {
                        continue;
                    } else {
                        list.clear();
                        list.add(org);
                        break;
                    }
                } else {
                    list.clear();
                    list.add(org);
                    break;
                }
            }
        } else {
            list.clear();
        }
        return list != null && list.size() == 1 ? true : false;
    }

    /**
     * 根据接单范围，判断是否在门店基地接单范围
     *
     * @param org
     * @param order
     * @return
     */
    public boolean filterByAcceptRange(Org org, Order order, List<Org> list) {
        Org tmp = orgService.queryByAcceptRange(org.getAcceptRange(), order.getLongitude(), order.getLatitude(), order.getCity(), list);
        if (tmp != null) {
//			list.clear();
//			list.add(tmp);
            return true;
        }
        return false;
    }

    /**
     * 呼叫中心
     *
     * @param list
     * @return true:成功<br>false:失败<br>
     * 2017年1月10日
     */
    public boolean callCenter(List<Org> list) {
        List<Org> tmp = orgService.queryCallCenter();
        if (null != tmp && !tmp.isEmpty()) {
            list.clear();
            list.add(tmp.get(0));
            return true;
        }
        return false;
    }

    /**
     * 查询客户最后一次下单的管家
     */
    public Order queryLastRechargeUser(Order order) {
        return orgService.queryLastRechargeUser(order);
    }
}
