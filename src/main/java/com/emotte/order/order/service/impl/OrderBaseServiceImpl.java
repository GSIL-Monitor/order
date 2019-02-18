package com.emotte.order.order.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.emotte.order.order.model.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wildhorse.server.core.model.Page;

import com.emotte.dubbo.service.PersonnelInterfaceService;
//import com.emotte.order.order.alimapper.writer.WrOrderAliMapper;
import com.emotte.order.order.mapper.reader.ReItemDetailServerMapper;
import com.emotte.order.order.mapper.reader.ReOrderBaseMapper;
import com.emotte.order.order.mapper.reader.ReOrderMapper;
import com.emotte.order.order.mapper.writer.WrOrderBaseMapper;
import com.emotte.order.order.mapper.writer.WrOrderMapper;
import com.emotte.order.order.service.OrderBaseService;
import com.emotte.order.util.JSONObjectDeleteEmpty;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("orderBaseService")
@Transactional
public class OrderBaseServiceImpl implements OrderBaseService{
	Logger log=Logger.getLogger(this.getClass()); 
	
	@Resource
	private ReOrderBaseMapper reOrderBaseMapper;
	@Resource
	private WrOrderBaseMapper wrOrderBaseMapper;
	@Resource
	private ReItemDetailServerMapper reItemDetailServerMapper;
	@Resource
	private ReOrderMapper reOrderMapper;
	@Resource
	private WrOrderMapper wrOrderMapper;
//	@Resource
//	private WrOrderAliMapper wrOrderAliMapper;
	@Resource
	private PersonnelInterfaceService personnelInterfaceService;
	

	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public StringBuffer queryBaseDictionary(String dictCode,int dictLength){
		StringBuffer jsonstr = new StringBuffer("");
		Dictionary dictionary = new Dictionary();
		dictionary.setTypes(dictCode);
		dictionary.setLength(dictLength);
		dictionary.setValid(1);
		List<Dictionary> list = this.reOrderBaseMapper.queryBaseDictionary(dictionary);
		if(list!=null && list.size()>0){
			jsonstr.append("[{");
			for(int i=0; i<list.size(); i++){
				Dictionary dict = list.get(i);
				if(i>0){
					jsonstr.append(",{");
				}
				jsonstr.append("'key':'" +dict.getDkey()+"'");
				jsonstr.append(",");
				jsonstr.append("'value':'" +dict.getDvalue()+"'");
				jsonstr.append("}");
			}
			jsonstr.append("]");
		}
		return jsonstr;
	}
	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public List<Dictionary> queryServerType(String dictCode,int dictLength){
		Dictionary dictionary = new Dictionary();
		dictionary.setTypes(dictCode);
		dictionary.setLength(dictLength);
		dictionary.setValid(1);
		return this.reOrderBaseMapper.queryServerType(dictionary);
	}
	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public StringBuffer queryDictionaryMapper(Long pid,String types){
		StringBuffer jsonstr = new StringBuffer("");
		Dictionary dictionary = new Dictionary();
		dictionary.setPid(pid);
		dictionary.setTypes(types);
		dictionary.setValid(1);
		List<Dictionary> list = this.reOrderBaseMapper.queryDictionaryMapper(dictionary);
		if(list!=null && list.size()>0){
			jsonstr.append("[{");
			for(int i=0; i<list.size(); i++){
				Dictionary dict = list.get(i);
				if(i>0){
					jsonstr.append(",{");
				}
				jsonstr.append("'key':'" +dict.getDkey()+"'");
				jsonstr.append(",");
				jsonstr.append("'value':'" +dict.getDvalue()+"'");
				jsonstr.append("}");
			}
			jsonstr.append("]");
		}
		return jsonstr;
	}
	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public List<Dictionary> queryServerChannel(String dkey){
		Dictionary dictionary = new Dictionary();
		dictionary.setDkey(dkey);
		dictionary.setValid(1);
		return  this.reOrderBaseMapper.queryServerChannelNew(dictionary);
//		return  this.reOrderBaseMapper.queryServerChannel(dictionary);
	}
	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public StringBuffer queryServerChannelBak(String dkey){
		StringBuffer jsonstr = new StringBuffer("");
		Dictionary dictionary = new Dictionary();
		dictionary.setDkey(dkey);
		dictionary.setValid(1);
		List<Dictionary> list = this.reOrderBaseMapper.queryServerChannel(dictionary);
		if(list!=null && list.size()>0){
			jsonstr.append("[{");
			for(int i=0; i<list.size(); i++){
				Dictionary dict = list.get(i);
				if(i>0){
					jsonstr.append(",{");
				}
				jsonstr.append("'key':'" +dict.getDkey()+"'");
				jsonstr.append(",");
				jsonstr.append("'value':'" +dict.getDvalue()+"'");
				jsonstr.append("}");
			}
			jsonstr.append("]");
		}
		return jsonstr;
	}
	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public StringBuffer queryOriginDictionary(String dkey){
		StringBuffer jsonstr = new StringBuffer("");
		Dictionary dictionary = new Dictionary();
		dictionary.setDkey(dkey);
		dictionary.setValid(1);
		List<Dictionary> list = this.reOrderBaseMapper.queryOriginDictionary(dictionary);
		if(list!=null && list.size()>0){
			jsonstr.append("[{");
			for(int i=0; i<list.size(); i++){
				Dictionary dict = list.get(i);
				if(i>0){
					jsonstr.append(",{");
				}
				jsonstr.append("'key':'" +dict.getDkey()+"'");
				jsonstr.append(",");
				jsonstr.append("'value':'" +dict.getDvalue()+"'");
				jsonstr.append("}");
			}
			jsonstr.append("]");
		}
		return jsonstr;
	}
	
	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public List<OrderUser> queryUserMapper(OrderUser orderUser,Page page){
		if(page.needQueryPading()){
			page.setTotalRecord(reOrderBaseMapper.countUserMapper(orderUser));
		}
		orderUser.setBeginRow(page.getFilterRecord().toString());
		orderUser.setPageSize(page.getPageCount().toString());
		List<OrderUser> listUser = this.reOrderBaseMapper.queryUserMapper(orderUser);
		return listUser;
		
	}
	
	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public List<OrderUser> queryUserAddressMapper(OrderUser orderUser){
		List<OrderUser> listUser = null;
		// 如果没传ID，直接查订单的收货人信息
		if(orderUser.getId()!=null || orderUser.getUserId()!=null){
			listUser = this.reOrderBaseMapper.queryUserAddressMapper(orderUser);
		}else{
			listUser = this.reOrderBaseMapper.queryUserAddressOrder(orderUser);
		}
		return listUser;
		
	}
	
	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public List<OrderUser> queryUserAddressByMobile(OrderUser orderUser){
		List<OrderUser> listUser = this.reOrderBaseMapper.queryUserAddressByMobile(orderUser);
		List<OrderUser> listUser2 = new ArrayList<OrderUser>();
		for (OrderUser orderUser2 : listUser) {
			Long unicode = orderUser2.getUnicode();
			orderUser2.setUnicodes(unicode.toString());
			listUser2.add(orderUser2);
		}
		orderUser.setUsValid(1);
		orderUser.setAdValid(1);
		return listUser2;
		
	}
	
	@Override
	public void insertUser(OrderUser orderUser){
		if(orderUser.getId()==null){
			orderUser.setRealName(orderUser.getRealName()==null?"":orderUser.getRealName().trim());
			orderUser.setUserMobile(orderUser.getUserMobile()==null?"":orderUser.getUserMobile().trim());
			this.wrOrderBaseMapper.insertOrderUser(orderUser);
			Long userId = orderUser.getId();//用户id
			if(orderUser.getCardType() != null && !"1".equals(orderUser.getCardType())){
				orderUser.setCardNum(null);
			}
			orderUser.setUserId(this.reOrderMapper.queryOrderId());
			orderUser.setOrigin("订单");
			try{
				this.wrOrderBaseMapper.insertOrderCustomerAnalysis(orderUser);
			}catch(Exception e){}
			try{
				this.wrOrderBaseMapper.insertOrderUserAccount(orderUser);
				OrderUser orderUser1 = orderUser;
				orderUser1.setUserId(userId);
				orderUser1.setProvince(orderUser.getUserProvince());
				orderUser1.setCity(orderUser.getUserCity());
				orderUser1.setCountry(orderUser.getUserCountry());
				orderUser1.setAddressChoose(orderUser.getUserAddress());
				orderUser1.setAddressDetail(orderUser.getUserAddress());
				orderUser1.setContactName(orderUser.getRealName());
				orderUser1.setContactPhone(orderUser.getUserMobile());
				orderUser1.setIsDefault(2);
				this.wrOrderBaseMapper.insertOrderUserAddress(orderUser);//创建客户服务地址
			}catch(Exception e){}
		}else{
			orderUser.setRealName(orderUser.getRealName()==null?"":orderUser.getRealName().trim());
			orderUser.setUserMobile(orderUser.getUserMobile()==null?"":orderUser.getUserMobile().trim());
			this.wrOrderBaseMapper.updateOrderUser(orderUser);
		}
	}
	
	@Override
	public void insertUserAddress(OrderUser orderUser){
		orderUser.setVersion(1L);
		orderUser.setValid(1);
		orderUser.setIsDefault(2);
		orderUser.setUpdateTime(orderUser.getCreateTime());
//		Map map = LngAndLatUtil.getLngAndLat(orderUser.getAddressDetail());
//		orderUser.setLongitude(map.get("longitude").toString());
//		orderUser.setLatitude(map.get("latitude").toString());
		this.wrOrderBaseMapper.insertOrderUserAddress(orderUser);
	}
	
	@Override
	public void updateUserAddress(OrderUser orderUser){
		if(null!=orderUser.getId() && orderUser.getId()!=0){
			this.wrOrderBaseMapper.updateOrderUserAddress(orderUser);
		}
	}
	
	@Override
	public List<OrderBaseModel> queryBaseCity(String code,int length){
		List<OrderBaseModel> list = null;
		OrderBaseModel baseModel = new OrderBaseModel();
		baseModel.setAddress(code);
		baseModel.setCritical(length);
		list = this.reOrderBaseMapper.queryBaseCity(baseModel);
		return list;
	}
	
	@Override
	public List<OrderBaseModel> queryOrderBasicServer(Long id){
		 List<OrderBaseModel> list = this.reOrderBaseMapper.queryOrderBasicServer(id);
		for (OrderBaseModel orderBaseModel : list) {
			String maxString = null;
			if(orderBaseModel.getTimeslot()!=null&&!(orderBaseModel.getTimeslot().equals(""))){
				for(int i=0;i<=orderBaseModel.getTimeslot().split(",").length-1;i++){
					if(i==orderBaseModel.getTimeslot().split(",").length-1){
						maxString=orderBaseModel.getTimeslot().split(",")[i];
					}
				}
				if(maxString.split(":")[1].equals("00")){
					maxString=maxString.split(":")[0]+":"+"30";
				}else{
					maxString=String.valueOf((Long.valueOf(maxString.split(":")[0])+1))+":"+"00";
				}
				orderBaseModel.setTimeslot(orderBaseModel.getTimeslot().split(",")[0]+'-'+maxString);
				System.err.println(orderBaseModel.getTimeslot());
			}
		}
		return list;
	}
	
	@Override
	public List<OrderBaseModel> queryOrderBasicServertype(Long id){
		List<OrderBaseModel> list = null;
		list = this.reOrderBaseMapper.queryOrderBasicServerType(id);
		return list;
	}
	
	@Override
	public List<OrderBaseModel> queryOrderNeedServer(OrderBaseModel orderBaseModel,Page page){
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		List<OrderBaseModel> list = null;
		Long orderId = orderBaseModel.getOrderId();
		orderBaseModel.setName(orderBaseModel.getName()==null?"":orderBaseModel.getName().trim());
		orderBaseModel.setMobile(orderBaseModel.getMobile()==null?"":orderBaseModel.getMobile().trim());
		orderBaseModel.setNation(orderBaseModel.getNation()==null?"":orderBaseModel.getNation().trim());
		if(orderBaseModel.getType()!=null && orderBaseModel.getType()==1){
			// 自动匹配
			ItemDetailServer itemDetailServer = this.reItemDetailServerMapper.loadItemDetailServerByOrderId(orderBaseModel.getOrderId());
			OrderServerNeeds need = new OrderServerNeeds();
			if(itemDetailServer.getStartTime()!=null&&itemDetailServer.getStartTime().length()>=8){
				need.setStartDate(itemDetailServer.getStartTime().replace("-", "").replace(" ", "").replace(":", "").substring(0, 8));
			}
			if(itemDetailServer.getEndTime()!=null&&itemDetailServer.getEndTime().length()>=8){
				need.setEndDate(itemDetailServer.getEndTime().replace("-", "").replace(" ", "").replace(":", "").substring(0, 8));
			}
			if(itemDetailServer.getMinAge()!=null && itemDetailServer.getMaxAge()!=null){
				need.setAge(itemDetailServer.getMinAge() +" TO " +itemDetailServer.getMaxAge());
			}
			need.setEducation(itemDetailServer.getEducation());
			need.setOrigin(itemDetailServer.getOrigin());
			need.setStatus("1");
			need.setSex(itemDetailServer.getSex());
//			need.setUsualLng(itemDetailServer.getLongitude());
//			need.setUsualLat(itemDetailServer.getLatitude());
			need.setProductCode(itemDetailServer.getProductCode());
			String ned = JSONObjectDeleteEmpty.deleteEmpty(JSONObject.fromObject(need).toString()) ;
//			String ned = "{\"startDate\":\"20161103\",\"status\":1,\"endDate\":20161123}" ;
			System.out.println("need.toString():" +JSONObjectDeleteEmpty.deleteEmpty(JSONObject.fromObject(need).toString()));
			String data = this.personnelInterfaceService.query(ned,null, 0, 99);
			System.out.println("data:" +data);
			JSONObject jsonObj = JSONObject.fromObject(data);
			String ids = "";
			if(jsonObj.getInt("total")>0){
				JSONArray ary = JSONArray.fromObject(jsonObj.get("data"));
				int k = 0;
				for(int i=0;i<ary.size(); i++){
					if(k==2) break;
					String productid ="";
					if(JSONObject.fromObject(ary.get(i)).get("productId")!=null){
						productid = JSONObject.fromObject(ary.get(i)).get("productId").toString();
					}
					if(null!=productid&&!"".equals(productid)){
						if(ids!=""){
							ids += "," ;
						}
						ids += JSONObject.fromObject(ary.get(i)).getString("id");
						k ++;
					}
				}
			}
			orderBaseModel = new OrderBaseModel();
			orderBaseModel.setOrderId(orderId);
			orderBaseModel.setIdsAry(ids.split(","));
			orderBaseModel.setIds(ids);
			orderBaseModel.setPid(orderId);
			System.out.println("ids:" +ids);
			if(ids!=null && !ids.equals("")){
				list = this.reOrderBaseMapper.queryOrderNeeds(orderBaseModel);	
			}
		}else if(orderBaseModel.getType()!=null && orderBaseModel.getType()==2){
			if(orderBaseModel.getOrderId()!=null){
				OrderBaseModel orderBaseModel2 = this.reOrderBaseMapper.queryOrdertypeLevelWorkTypeId(orderBaseModel.getOrderId());
				orderBaseModel.setOrderType(orderBaseModel2.getOrderType());
				orderBaseModel.setGrade(orderBaseModel2.getGrade());
			}
			// 手动匹配条件查询
			System.err.println(orderBaseModel.getTimeslot());
			ItemInterview itemInterview = new ItemInterview();
			itemInterview.setOrderId(orderBaseModel.getOrderId());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd");
			if(orderBaseModel.getLinedType() != 1){
				List<ItemInterview> lists = this.reOrderBaseMapper.queryOrderItemInterview(itemInterview);//查询订单是否有下户人员
				//判断是否有下户
				if(lists.size() == 0){
					//没有下户加冗余
					try {
						Date Enddate = null;
						Date Startdate = null;
						Calendar Starttime = Calendar.getInstance(); 
						Calendar Endtime = Calendar.getInstance(); 
						Startdate = sdff.parse(sdff.format(sdf.parse(orderBaseModel.getStartTime())));
						Starttime.setTime(Startdate);
						Starttime.add(Calendar.DATE,-7);
						orderBaseModel.setStartTime(sdff.format(Starttime.getTime()).replace("-", ""));
						Enddate = sdff.parse(sdff.format(sdf.parse(orderBaseModel.getEndTime())));
						Endtime.setTime(Enddate);
						Endtime.add(Calendar.DATE,7);
						orderBaseModel.setEndTime(sdff.format(Endtime.getTime()).replace("-", ""));
						System.out.println(sdff.format(Starttime.getTime()).replace("-", ""));
						System.out.println(sdff.format(Endtime.getTime()).replace("-", ""));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else{
					//有下户不加冗余
					Date endTimedate = null;
					Calendar Endtimes = Calendar.getInstance(); 
					String listendTime = lists.get(0).getEndTime();
					String endTime = listendTime.replace("-", "");
					int result = orderBaseModel.getStartTime().compareTo(endTime);//对比时间
					try {
						endTimedate = sdff.parse(listendTime);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					Endtimes.setTime(endTimedate);
					Endtimes.add(Calendar.DATE,1);
					if (result <= 0){
						orderBaseModel.setStartTime(sdff.format(Endtimes.getTime()).replace("-", ""));
					}
				}
			}
			if(orderBaseModel.getTimeslot()!="1"&&!(orderBaseModel.getTimeslot().equals("1"))){
				orderBaseModel.setStartTimeSolt(orderBaseModel.getTimeslot().split("-")[0].replace(":", "")+"00");
				orderBaseModel.setEndTimeSolt(orderBaseModel.getTimeslot().split("-")[1].replace(":", "")+"00");
				if(orderBaseModel.getWeek() != ("") && orderBaseModel.getWeek() != null){
					String weeks = orderBaseModel.getWeek();
					weeks = weeks.replaceAll("[^一二三四五六天,]", "").replaceAll("[,]+", ",");
					weeks = weeks.replaceAll("一", "1").replaceAll("二", "2").replaceAll("三", "3")
					             .replaceAll("四", "4").replaceAll("五", "5").replaceAll("六", "6")
					             .replaceAll("天", "7");
					orderBaseModel.setWeek(weeks+",0");
				}
			}
			if(page.needQueryPading()){
				page.setTotalRecord(this.reOrderBaseMapper.countOrderNeedServer(orderBaseModel));
			}
			orderBaseModel.setBeginRow(page.getFilterRecord().toString());
			orderBaseModel.setPageSize(page.getPageCount().toString());
			orderBaseModel.setFlagPage(0);// 表示分页查询
			list = this.reOrderBaseMapper.queryOrderNeedServer(orderBaseModel);
		}else if(orderBaseModel.getType()!=null && orderBaseModel.getType()==3){
			// 查询单个明细
			Long id = orderBaseModel.getId();
			orderBaseModel = new OrderBaseModel();
			orderBaseModel.setId(id);
			orderBaseModel.setValid(1);
			orderBaseModel.setFlagPage(1); // 表示不分页查询
			list = this.reOrderBaseMapper.queryOrderNeedServerByPersonId(id);
		}else if(orderBaseModel.getType()!=null && orderBaseModel.getType()==4){
			// 查询选定的两条
//			orderBaseModel.setOrderId(orderId);
//			orderBaseModel.setInterviewType(1);
			if(null!=orderBaseModel.getIds()){
				String ids = orderBaseModel.getIds();
				orderBaseModel = new OrderBaseModel();
				orderBaseModel.setOrderId(orderId);
				orderBaseModel.setIdsAry(ids.split(","));
				orderBaseModel.setIds(ids);
				orderBaseModel.setValid(1);
				list = this.reOrderBaseMapper.queryOrderNeeds(orderBaseModel);
			}
		}else if((orderBaseModel.getType()!=null && orderBaseModel.getType()==5)){
			// 待确定人员列表
			orderBaseModel = new OrderBaseModel();
			orderBaseModel.setOrderId(orderId);
			orderBaseModel.setValid(1);
			orderBaseModel.setType(5);
			list = this.reOrderBaseMapper.queryOrderNeeds(orderBaseModel);
		}else if((orderBaseModel.getType()!=null && orderBaseModel.getType()==15)){
			// 待历史服务人员列表
			orderBaseModel = new OrderBaseModel();
			orderBaseModel.setOrderId(orderId);
			orderBaseModel.setValid(1);
			orderBaseModel.setType(15);
			list = this.reOrderBaseMapper.queryOrderNeeds(orderBaseModel);
		}else if(orderBaseModel.getType()!=null && orderBaseModel.getType()==8){
			// 上户人员列表
			orderBaseModel = new OrderBaseModel();
			orderBaseModel.setOrderId(orderId);
			orderBaseModel.setValid(1);
			orderBaseModel.setType(8);
			list = this.reOrderBaseMapper.queryOrderNeeds(orderBaseModel);
		}else if(orderBaseModel.getType()!=null && orderBaseModel.getType()==11){
			// 单次服务订单人员查询
			orderBaseModel = new OrderBaseModel();
			orderBaseModel.setOrderId(orderId);
			orderBaseModel.setType(5);
			orderBaseModel.setValid(1);
			list = this.reOrderBaseMapper.queryOrderNeeds(orderBaseModel);
		}
  		return list;
	}
	
	@Override
	public int checkedUserOrNot(String mobile){
		OrderUser ou = new OrderUser();
		ou.setUserMobile(mobile);
		ou.setValid(1);
		int result = this.reOrderBaseMapper.countUserMapper(ou);
		return result;
	}

	/**
	 * 订单新用户分包
	 * @param urderId
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	@Override
	public OrderUser orderUserFollow(Order order){
//		if(null!=order.getCity() && order.getCity().length()>=9){
//			user.setCityCode(order.getCity().substring(0, 9));
//		}
		// 先判断当前录入人是否是管家
		OrderUser user = new OrderUser();
		OrderUser userMa = this.reOrderBaseMapper.queryOrderUserManagerById(order.getCreateBy());
		if(userMa!=null&&userMa.getId()!=null&&!"".equals(userMa.getId())){
			user.setId(order.getUserId());
			user.setMarketBy(userMa.getId());
			user.setDeptId(userMa.getDeptId());
			user.setIsCommit(2);
			user.setPhones(userMa.getPhones());
			return user;
		}
		// 判断当前录入人是否是门店或门店基地对应的管家
		user.setCreateBy(order.getCreateBy());
		List<OrderUser> list = this.reOrderBaseMapper.queryOrderUserFollow(user) ;
		if(list==null || list.size()==0){
			// 如果不是,就对当前用户进行分配门店或基地
			// 如果是FA订单和他营单次服务，则不暂不分包
			if(order.getCateType()!=null && (order.getCateType()==3 || order.getCateType()==4)){
				user.setId(order.getUserId());
				user.setIsCommit(2);
			}else{
				user.setCreateBy(null);
				user.setId(order.getUserId());
				user.setIsCommit(2);
				userMa = this.reOrderBaseMapper.queryOrderUserFollowByOrg(order) ;
				if(userMa!=null&&userMa.getId()!=null){
					user.setDeptId(userMa.getId());
					user.setPhones(userMa.getPhones());
				}
			}
			return user;
		}else{
			userMa = list.get(0);
			// 如果是 ，直接把当前用户分给当前录入人
			user.setId(order.getUserId());
			user.setMarketBy(order.getCreateBy());
			user.setDeptId(userMa.getId());
			user.setIsCommit(2);
			user.setPhones(userMa.getPhones());
			//this.wrOrderBaseMapper.updateOrderUser(user);
			return user;
		}
		
	}
	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public List<Dictionary> querydeptname(Dictionary  dictionary){
		return this.reOrderBaseMapper.querydeptname(dictionary);
	}
	
	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public List<Managers> queryguanjia(Managers managers){
		return this.reOrderBaseMapper.queryguanjia(managers);
	}
	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public OrderUser queryUserByMobile(OrderUser orderUser) {
		return this.reOrderBaseMapper.queryUserByMobile(orderUser);
	}
	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public List<OrderUser> queryCityCodeByArea(OrderUser orderUser) {
		return this.reOrderBaseMapper.queryCityCodeByArea(orderUser);
	}
	
	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public List<Dictionary> querydeptnameWithDept(Dictionary dictionary) {
		return this.reOrderBaseMapper.querydeptnameWithDept(dictionary);
	}
	
	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public List<OrderUser> queryUserWithVip() {
		return this.reOrderBaseMapper.queryUserWithVip();
	}
	@Override
	public void updateOrderUser(OrderUser orderUser) {
		if(null!=orderUser.getId() && orderUser.getId()!=0){
			this.wrOrderBaseMapper.updateOrderUser(orderUser);
		}
	}
	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public List<Dictionary> queryOrderChannel(Dictionary dictionary) {
		return this.reOrderBaseMapper.queryOrderChannel(dictionary);
	}
	@Override
	public int selectStock(Dictionary dictionary) {
		return this.reOrderBaseMapper.selectStock(dictionary);
	}
	@Override
	public int checkNumStock(Dictionary dictionary) {
		return this.reOrderBaseMapper.checkNumStock(dictionary);
	}
	@Override
	public List<Category> queryCategoryCity(Category category) {
		return this.reOrderBaseMapper.queryCategoryCity(category);
	}
	@Override
	public Long queryDeptIdById(Long id) {
		return this.reOrderBaseMapper.queryDeptIdById(id);
	}
	@Override
	public Dictionary queryCategoryType(Dictionary dictionary) {
		return this.reOrderBaseMapper.queryCategoryType(dictionary);
	}
	@Override
	public List<Dictionary> queryServerChannel(String dkey, Integer housekeeperAvailable) {
		Dictionary dictionary = new Dictionary();
		dictionary.setDkey(dkey);
		dictionary.setValid(1);
		dictionary.setHousekeeperAvailable(housekeeperAvailable);
		return  this.reOrderBaseMapper.queryServerChannelNew(dictionary);
	}
	
	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public List<Dictionary> queryChannels(Dictionary dictionary) {
		return this.reOrderBaseMapper.queryChannels(dictionary);
	}

	/**
	 * 根据ids查询服务人员信息
	 *
	 * @param personnelIds
	 * @return
	 * @Author zhanghao
	 * @Date 20180927
	 */
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	@Override
	public List<Personnel> checkPersonnelText(List<Long> personnelIds) {
		List<Personnel> personnels = reOrderBaseMapper.checkPersonnelText(personnelIds);
		return personnels;
	}

	/**
	 * 根据服务人员ID查询管理状态
	 *
	 * @param orderBaseModel
	 * @return
	 * @Author zhanghao
	 * @Date 20180928
	 */
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	@Override
	public List<Personnel> checkOrderTrue(OrderBaseModel orderBaseModel) {
		List<Personnel> orderBaseModels = reOrderBaseMapper.checkOrderTrue(orderBaseModel);
		return orderBaseModels;
	}
	@Override
	public Map<String, String> queryShowMsg(PersonnelSchedule personnelSchedule) {
			Map map = reOrderBaseMapper.queryShowMsg(personnelSchedule);
		return map;
	}

	/**
	 * 推送页面查询服务人员信息卡方法
	 *
	 * @param orderId        订单ID
	 * @param personnelId    服务人员ID
	 * @return
	 * @Author zhanghao
	 * @Date 20181011
	 */
	@Override
	public Personnel findPersonnelDetailCard(Long orderId, Long personnelId) {
		Personnel personnels = reOrderBaseMapper.findPersonnelDetailCard(orderId,personnelId);
		return personnels;
	}


}
