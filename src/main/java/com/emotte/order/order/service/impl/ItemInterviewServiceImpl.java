package com.emotte.order.order.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wildhorse.server.core.exception.BaseException;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.dataUtils.DateUtil;

import com.emotte.dubbo.service.PersonnelInterfaceService;
import com.emotte.dubbo.service.PushInterfaceService;
import com.emotte.eclient.EClientContext;
import com.emotte.interf.EScheduleService;
import com.emotte.kernel.helper.LogHelper;
import com.emotte.order.order.mapper.reader.ReItemDetailServerMapper;
import com.emotte.order.order.mapper.reader.ReItemInterviewMapper;
import com.emotte.order.order.mapper.reader.ReOrderBaseMapper;
import com.emotte.order.order.mapper.reader.RePayfeeMapper;
import com.emotte.order.order.mapper.writer.WrItemDetailServerMapper;
import com.emotte.order.order.mapper.writer.WrItemInterviewMapper;
import com.emotte.order.order.mapper.writer.WrItemMapper;
import com.emotte.order.order.mapper.writer.WrOrderMapper;
import com.emotte.order.order.mapper.writer.WrPayfeeMapper;
import com.emotte.order.order.model.Item;
import com.emotte.order.order.model.ItemDetailServer;
import com.emotte.order.order.model.ItemInterview;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.OrderBaseModel;
import com.emotte.order.order.model.Payfee;
import com.emotte.order.order.model.ServerLinedRecord;
import com.emotte.order.order.service.ItemInterviewService;
import com.emotte.order.order.service.OrderService;
import com.emotte.order.util.Tools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("itemInterviewService")
@Transactional
public class ItemInterviewServiceImpl implements ItemInterviewService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ReItemInterviewMapper reItemInterviewMapper;

	@Resource
	private WrItemInterviewMapper wrItemInterviewMapper;
	@Resource
	private PersonnelInterfaceService personnelInterfaceService;
	
	@Resource
	private WrOrderMapper wrOrderMapper;
	@Resource
	private PushInterfaceService pushInterfaceService;
	@Resource
	private ReItemDetailServerMapper reItemDetailServerMapper;
	@Resource
	private RePayfeeMapper rePayfeeMapper;
	@Resource
	private WrPayfeeMapper wrPayfeeMapper;
	@Resource
	private ReOrderBaseMapper reOrderBaseMapper;
	@Resource
	private WrItemMapper wrItemMapper;
	@Resource
	private OrderService orderService;
	@Resource
	private WrItemDetailServerMapper wrItemDetailServerMapper;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public ItemInterview loadItemInterview(Long id) {
		return this.reItemInterviewMapper.loadItemInterview(id);
	}

	
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<ItemInterview> queryItemInterview(ItemInterview itemInterview, Page page) {
		if (page.needQueryPading()) {
			page.setTotalRecord(reItemInterviewMapper.countItemInterview(itemInterview));
		}
		itemInterview.setBeginRow(page.getFilterRecord().toString());
		itemInterview.setPageSize(page.getPageCount().toString());
		return this.reItemInterviewMapper.queryItemInterview(itemInterview);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<ItemInterview> queryNeedPersons(ItemInterview itemInterview) {
		if(itemInterview.getType()!=null && (itemInterview.getType()==13 ||itemInterview.getType()==14)){
			// 单次服务订单 上户及取消专用校验查询
			return this.reItemInterviewMapper.queryInterviewsByOrderId(itemInterview);
		}else{
			return this.reItemInterviewMapper.queryNeedPersons(itemInterview);
		}
	}
	
	@Deprecated
	public void insertItemInterviewOld(ItemInterview itemInterview) {
		if (itemInterview.getIds() != null && !"".equals(itemInterview.getIds())) {
			String[] idst = itemInterview.getIds().split(",");
			//System.err.println(idst);
			for (int i = 0; i < idst.length; i++) {
				ItemInterview item = new ItemInterview();
				item.setPersonId(Long.valueOf(idst[i]));
				item.setOrderId(itemInterview.getOrderId());
				item.setServerType(itemInterview.getServerType());
				item.setCreateBy(itemInterview.getCreateBy());
				item.setInterviewTime(itemInterview.getInterviewTime());
				item.setValid(1);
				item.setVersion(0L);
				item.setRemarks(itemInterview.getRemarks());
				if(itemInterview.getType()==1){
					// 单次自动匹配保存
					item.setInterviewType(2);
				}else if(itemInterview.getType()==11){
					// 单次手动匹配保存
					item.setInterviewType(4);
					// 锁定服务人员排期
						//ItemDetailServer itd = this.reItemDetailServerMapper.loadItemDetailServerByOrderId(itemInterview.getOrderId());
						ItemDetailServer itd = this.reItemDetailServerMapper.loadOrderServerLined(itemInterview.getOrderId());
						itd.setLinedType(1);
						lindDays(itd,item.getPersonId(),4);
						this.wrItemInterviewMapper.insertItemInterview(item);
				}else{
					// 延续订单匹配保存
					item.setInterviewType(1);
					// 锁定服务人员排期
					EScheduleService service = (EScheduleService)EClientContext.getBean(EScheduleService.class);
						ItemDetailServer itd = this.reItemDetailServerMapper.loadOrderServerLined(itemInterview.getOrderId());
						if(itemInterview.getPushType() != null && "2".equals(itemInterview.getPushType())){
							//顶岗推送
							JSONObject param = new JSONObject();
							param.accumulate("startDate", itemInterview.getLineStartTime().replace("-", ""));
							param.accumulate("endDate", itemInterview.getLineEndTime().replace("-", ""));
							if(itd.getLinedType() == 2){
								//钟点工
								String[] hoursArr = Tools.lindHoursNew1(itemInterview.getTimeSlot()).split("-");
								param.accumulate("startTime",hoursArr[0]);
								param.accumulate("endTime",hoursArr[1]);
							}
							if(!itemInterview.getWeek().equals("") && itemInterview.getWeek() != null){
								String weeks = itemInterview.getWeek();
								weeks = weeks.replaceAll("[^一二三四五六天,]", "").replaceAll("[,]+", ",");
								weeks = weeks.replaceAll("一", "1").replaceAll("二", "2").replaceAll("三", "3")
								             .replaceAll("四", "4").replaceAll("五", "5").replaceAll("六", "6")
								             .replaceAll("天", "7");
								param.accumulate("weekday",weeks); 
							}
							param.accumulate("createBy",itemInterview.getCreateBy());
							param.accumulate("empId",item.getPersonId());
							param.accumulate("orderId", itemInterview.getOrderId());
							param.accumulate("status",4);
							param.accumulate("pushType", 2);
							param.accumulate("state", 2);
							System.err.println(param.toString());
							String result =  service.savePersonNelSchedule(param.toString());
							System.err.println("result："+result);
							if(!result.equals("") && result != null){
								JSONObject r = JSONObject.fromObject(result);
								JSONObject data = JSONObject.fromObject(r.get("data"));
								String code = data.getString("code");
								String msg = data.getString("msg");
								if(!code.equals("0")){
									throw new BaseException("fail:"+msg);
								}
							}else{
								throw new BaseException("fail");
							}
							//保存默认上下户时间
							item.setStarTime(itemInterview.getLineStartTime());
							item.setEndTime(itemInterview.getLineEndTime());
						}else{
							//正常推送
							ItemInterview itemInterviews = new ItemInterview();
							itemInterview.setOrderId(itemInterview.getOrderId());
							List<ItemInterview> list = this.reOrderBaseMapper.queryOrderItemInterview(itemInterview);//查询订单是否有下户人员
							//推送
							JSONObject para = new JSONObject();
							para.accumulate("endDate", itemInterview.getLineEndTime().replace("-", ""));
							if(itd.getLinedType() == 2){
								//钟点工
								String[] hoursArr = Tools.lindHoursNew1(itemInterview.getTimeSlot()).split("-");
								para.accumulate("startTime",hoursArr[0]);
								para.accumulate("endTime",hoursArr[1]);
							}
							if(!itemInterview.getWeek().equals("") && itemInterview.getWeek() != null){
								String weeks = itemInterview.getWeek();
								weeks = weeks.replaceAll("[^一二三四五六天,]", "").replaceAll("[,]+", ",");
								weeks = weeks.replaceAll("一", "1").replaceAll("二", "2").replaceAll("三", "3")
								             .replaceAll("四", "4").replaceAll("五", "5").replaceAll("六", "6")
								             .replaceAll("天", "7");
								para.accumulate("weekday",weeks); 
							}
							para.accumulate("createBy",itemInterview.getCreateBy());
							para.accumulate("empId",item.getPersonId());
							para.accumulate("orderId", itemInterview.getOrderId());
							para.accumulate("status",4);
							para.accumulate("state", 1);//推送
							
							String lineStartTime = itemInterview.getLineStartTime().replace("-", "");
							boolean flag = false;
							try {
								SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
								Date startDate = sdf.parse(lineStartTime); // 判断所选时间是否大于当前时间+7天，如果大于则+冗余时间，原则就是距离比较近的排期不需要冗余时间
								flag = startDate.getTime() > (System.currentTimeMillis() + 7 * 24 * 3600 * 1000);
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
							if(list.size() == 0 && flag) {
								para.accumulate("pushType", 1);//加冗余
//								para.accumulate("startDate", itemInterview.getLineStartTime().replace("-", ""));
								para.accumulate("startDate", lineStartTime);
							}else{
								Date endTimedate = null;
								SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd");
								Calendar Endtimes = Calendar.getInstance();
								int result = 1;
								if (list != null && list.size() > 0) {
									String listendTime = list.get(0).getEndTime();
									String endTime = listendTime.replace("-", "");
									result = lineStartTime.compareTo(endTime);//对比时间
									try {
										endTimedate = sdff.parse(listendTime);
									} catch (ParseException e) {
										e.printStackTrace();
									}
									Endtimes.setTime(endTimedate);
									Endtimes.add(Calendar.DATE,1);
								}
								if (result <= 0){
									para.accumulate("startDate", sdff.format(Endtimes.getTime()).replace("-", ""));
								}else{
//									para.accumulate("startDate", itemInterview.getLineStartTime().replace("-", ""));
									para.accumulate("startDate", lineStartTime);
								}
								para.accumulate("pushType", 2);//不加冗余
							}
							String result =  service.savePersonNelSchedule(para.toString());
							System.err.println("参数："+para.toString());
							System.err.println("result："+result);
							if(!result.equals("") && result != null){
								JSONObject r = JSONObject.fromObject(result);
								JSONObject data = JSONObject.fromObject(r.get("data"));
								String code = data.getString("code");
								String msg = data.getString("msg");
								if(!code.equals("0")){
									throw new BaseException("fail:"+msg);
								}
							}else{
								throw new BaseException("fail");
							}
							//lindDays(itd,item.getPersonId(),4);
						}
				}
				/*if(!"2".equals(itemInterview.getPushType())){
					this.wrItemInterviewMapper.insertItemInterview(item);
				}*/
			}
			if(!"2".equals(itemInterview.getPushType())){
				if(itemInterview.getType()==1 && itemInterview.getOrderStatus()==2){
					// 单次服务自动匹配，更新订单状态
					Order order = new Order();
					order.setId(itemInterview.getOrderId());
					order.setOrderStatus(3);
					orderService.updateOrder2(order);
				}else if(itemInterview.getType()==2){
					// 延续服务，更新订单状态
					Order order = new Order();
					order.setId(itemInterview.getOrderId());
					order.setOrderStatus(3);
					orderService.updateOrder2(order);
				}else if(itemInterview.getType()==11 && (itemInterview.getOrderStatus()==1||itemInterview.getOrderStatus()==2||itemInterview.getOrderStatus()==3)){
					// 单次服务手动匹配，更新订单状态
					Order order = new Order();
					order.setId(itemInterview.getOrderId());
					order.setOrderStatus(4);
					orderService.updateOrder2(order);
				}
			}
		}

	}
	@Override
	public void insertItemInterview(ItemInterview itemInterview) {
		if (itemInterview.getIds() != null && !"".equals(itemInterview.getIds())) {
			String[] idst = itemInterview.getIds().split(",");
			EScheduleService service = (EScheduleService)EClientContext.getBean(EScheduleService.class);
			for (int i = 0; i < idst.length; i++) {
				ItemInterview item = new ItemInterview();
				item.setPersonId(Long.valueOf(idst[i]));
				item.setOrderId(itemInterview.getOrderId());
				item.setCreateBy(itemInterview.getCreateBy());
				item.setServerType(itemInterview.getServerType());
				item.setInterviewTime(itemInterview.getInterviewTime());
				item.setValid(1);
				item.setVersion(1L);
				if(itemInterview.getType() == 11){
					/*单次订单推送服务人员*/
					item.setInterviewType(4);//初始状态：已匹配
					this.wrItemInterviewMapper.insertItemInterview(item);//添加上户记录
					ItemDetailServer itd = this.reItemDetailServerMapper.loadOrderServerLined(itemInterview.getOrderId());//查询订单排期
					String startDate = itd.getStartTime().replace(" ", "").replace("-", "").replace(":", "");
					String startTime = Tools.lindHours(itd.getHours()).split("-")[0]; //时间段转换
					String endTime = Tools.lindHours(itd.getHours()).split("-")[1];
					JSONObject jsonParam = new JSONObject();
					jsonParam.put("interviewId",item.getId());//上户记录id
					jsonParam.put("empId", item.getPersonId());
					jsonParam.put("orderId", itd.getOrderId());
					jsonParam.put("startDate", startDate.substring(0, 8));
					jsonParam.put("endDate", startDate.substring(0,8));
					jsonParam.put("startTime", startTime);
					jsonParam.put("endTime", endTime);
					jsonParam.put("status", 4);
					JSONArray jsonParamArr = new JSONArray();
					jsonParamArr.add(jsonParam);
					LogHelper.info(getClass()+ ".insertItemInterview()","创建排期参数："+jsonParamArr.toString());
					String jsonStr = service.updateScheduleOnce(jsonParamArr.toString());
					LogHelper.info(getClass()+ ".insertItemInterview()","创建排期返回："+jsonStr.toString());
					JSONObject jsons = JSONObject.fromObject(jsonStr);
					boolean result = jsons.getBoolean("data");
					if(!result){
						throw new BaseException("推送创建服务人员排期失败");
					}
				}else if(itemInterview.getType() == 2){
						/*延续订单推送服务人员*/
						ItemDetailServer itd = this.reItemDetailServerMapper.loadOrderServerLined(itemInterview.getOrderId());
						if(itemInterview.getPushType() != null && "2".equals(itemInterview.getPushType())){
							/*顶岗推送*/
							item.setInterviewType(1);//初始状态：未处理
							item.setStarTime(itemInterview.getLineStartTime());//保存默认上户时间
							item.setEndTime(itemInterview.getLineEndTime());//保存默认下户时间
							this.wrItemInterviewMapper.insertItemInterview(item);//添加上户记录
							JSONObject param = new JSONObject();
							param.put("interviewId",item.getId());//上户记录id
							param.accumulate("startDate", itemInterview.getLineStartTime().replace("-", ""));
							param.accumulate("endDate", itemInterview.getLineEndTime().replace("-", ""));
							if(itd.getLinedType() == 2){
								//钟点工
								String[] hoursArr = Tools.lindHoursNew1(itemInterview.getTimeSlot()).split("-");
								param.accumulate("startTime",hoursArr[0]);
								param.accumulate("endTime",hoursArr[1]);
							}
							if(!itemInterview.getWeek().equals("") && itemInterview.getWeek() != null){
								String weeks = itemInterview.getWeek();
								weeks = weeks.replaceAll("[^一二三四五六天,]", "").replaceAll("[,]+", ",");
								weeks = weeks.replaceAll("一", "1").replaceAll("二", "2").replaceAll("三", "3")
								             .replaceAll("四", "4").replaceAll("五", "5").replaceAll("六", "6")
								             .replaceAll("天", "7");
								param.accumulate("weekday",weeks); 
							}
							param.accumulate("createBy",itemInterview.getCreateBy());
							param.accumulate("empId",item.getPersonId());
							param.accumulate("orderId", itemInterview.getOrderId());
							param.accumulate("status",4);
							param.accumulate("pushType", 2);//是否加冗余 1是2否
							param.accumulate("state", 2);//1是正常推送 2是顶岗推送
							LogHelper.info(getClass()+ ".insertItemInterview()","创建排期参数："+param.toString());
							String result =  service.savePersonNelSchedule(param.toString());
							LogHelper.info(getClass()+ ".insertItemInterview()","创建排期返回："+result.toString());
							if(!result.equals("") && result != null){
								JSONObject r = JSONObject.fromObject(result);
								JSONObject data = JSONObject.fromObject(r.get("data"));
								String code = data.getString("code");
								String msg = data.getString("msg");
								if(!code.equals("0")){
									throw new BaseException(msg);
								}
							}else{
								throw new BaseException("推送创建服务人员排期失败");
							}
						}else{
							/*普通推送*/
							item.setInterviewType(1);//初始状态：未处理
							this.wrItemInterviewMapper.insertItemInterview(item);//添加上户记录
							itemInterview.setOrderId(itemInterview.getOrderId());
							List<ItemInterview> list = this.reOrderBaseMapper.queryOrderItemInterview(itemInterview);//查询订单是否有下户人员
							JSONObject para = new JSONObject();
							para.put("interviewId",item.getId());//上户记录id
							para.accumulate("endDate", itemInterview.getLineEndTime().replace("-", ""));
							if(itd.getLinedType() == 2){
								//钟点工
								String[] hoursArr = Tools.lindHoursNew1(itemInterview.getTimeSlot()).split("-");
								para.accumulate("startTime",hoursArr[0]);
								para.accumulate("endTime",hoursArr[1]);
							}
							if(!itemInterview.getWeek().equals("") && itemInterview.getWeek() != null){
								String weeks = itemInterview.getWeek();
								weeks = weeks.replaceAll("[^一二三四五六天,]", "").replaceAll("[,]+", ",");
								weeks = weeks.replaceAll("一", "1").replaceAll("二", "2").replaceAll("三", "3")
								             .replaceAll("四", "4").replaceAll("五", "5").replaceAll("六", "6")
								             .replaceAll("天", "7");
								para.accumulate("weekday",weeks); 
							}
							para.accumulate("createBy",itemInterview.getCreateBy());
							para.accumulate("empId",item.getPersonId());
							para.accumulate("orderId", itemInterview.getOrderId());
							para.accumulate("status",4);
							para.accumulate("state", 1);//1是正常推送 2是顶岗推送
							String lineStartTime = itemInterview.getLineStartTime().replace("-", "");
							boolean flag = false;
							try {
								SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
								Date startDate = sdf.parse(lineStartTime); // 判断所选时间是否大于当前时间+7天，如果大于则+冗余时间，原则就是距离比较近的排期不需要冗余时间
								flag = startDate.getTime() > (System.currentTimeMillis() + 7 * 24 * 3600 * 1000);
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
							if(list.size() == 0 && flag) {
								para.accumulate("pushType", 1);//加冗余
								para.accumulate("startDate", lineStartTime);
							}else{
								para.accumulate("pushType", 2);//不加冗余
								Date endTimedate = null;
								SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd");
								Calendar Endtimes = Calendar.getInstance();
								int result = 1;
								if (list != null && list.size() > 0) {
									String listendTime = list.get(0).getEndTime();
									String endTime = listendTime.replace("-", "");
									result = lineStartTime.compareTo(endTime);//对比时间
									try {
										endTimedate = sdff.parse(listendTime);
									} catch (ParseException e) {
										e.printStackTrace();
									}
									Endtimes.setTime(endTimedate);
									Endtimes.add(Calendar.DATE,1);
								}
								if (result <= 0){
									para.accumulate("startDate", sdff.format(Endtimes.getTime()).replace("-", ""));
								}else{
									para.accumulate("startDate", lineStartTime);
								}
							}
							LogHelper.info(getClass()+ ".insertItemInterview()","创建排期参数："+para.toString());
							String result =  service.savePersonNelSchedule(para.toString());
							LogHelper.info(getClass()+ ".insertItemInterview()","创建排期返回："+result.toString());
							if(!result.equals("") && result != null){
								JSONObject r = JSONObject.fromObject(result);
								JSONObject data = JSONObject.fromObject(r.get("data"));
								String code = data.getString("code");
								String msg = data.getString("msg");
								if(!code.equals("0")){
									throw new BaseException("fail:"+msg);
								}
							}else{
								throw new BaseException("推送创建服务人员排期失败");
							}
						}
				}
				//this.wrItemInterviewMapper.insertItemInterview(item);//添加上户记录
			}
			if(!"2".equals(itemInterview.getPushType())){
				if(itemInterview.getType()==1 && itemInterview.getOrderStatus()==2){
					// 单次服务自动匹配，更新订单状态
					Order order = new Order();
					order.setId(itemInterview.getOrderId());
					order.setOrderStatus(3);
					orderService.updateOrder2(order);
				}else if(itemInterview.getType()==2){
					// 延续服务，更新订单状态
					Order order = new Order();
					order.setId(itemInterview.getOrderId());
					order.setOrderStatus(3);
					orderService.updateOrder2(order);
				}else if(itemInterview.getType()==11 && (itemInterview.getOrderStatus()==1||itemInterview.getOrderStatus()==2||itemInterview.getOrderStatus()==3)){
					// 单次服务手动匹配，更新订单状态
					Order order = new Order();
					order.setId(itemInterview.getOrderId());
					order.setOrderStatus(4);
					orderService.updateOrder2(order);
				}
			}
		}

	}
	
	public boolean insert(ItemInterview itemInterview ){
		EScheduleService service = (EScheduleService)EClientContext.getBean2(EScheduleService.class);
		ItemDetailServer itd = this.reItemDetailServerMapper.loadOrderServerLined(itemInterview.getOrderId());
		itemInterview.setOrderId(itemInterview.getOrderId());
		List<ItemInterview> list = this.reOrderBaseMapper.queryOrderItemInterview(itemInterview);//查询订单是否有下户人员
		//推送
		JSONObject para = new JSONObject();
		para.accumulate("endDate", itd.getEndTime().replace("-", ""));
//		para.accumulate("endDate", itemInterview.getLineEndTime().replace("-", ""));
		if(itd.getLinedType() == 2){
			//钟点工
			String[] hoursArr = Tools.lindHoursNew1(Tools.hourTimes(itd.getHours())).split("-");
			para.accumulate("startTime",hoursArr[0]);
			para.accumulate("endTime",hoursArr[1]);
		}
		if(!"".equals(itd.getWeeks()) && null != itd.getWeeks()){
			String weeks = itd.getWeeks();
			weeks = weeks.replaceAll("[^一二三四五六天,]", "").replaceAll("[,]+", ",");
			weeks = weeks.replaceAll("一", "1").replaceAll("二", "2").replaceAll("三", "3")
			             .replaceAll("四", "4").replaceAll("五", "5").replaceAll("六", "6")
			             .replaceAll("天", "7");
			para.accumulate("weekday",weeks); 
		}
		para.accumulate("createBy",itemInterview.getCreateBy());
		para.accumulate("empId",itemInterview.getPersonids());
		para.accumulate("orderId", itemInterview.getOrderId());
		para.accumulate("status",4);
		para.accumulate("state", 1);//推送
		
		String lineStartTime = itd.getStartTime().replace("-", "");
		boolean flag = false;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date startDate = sdf.parse(lineStartTime); // 判断所选时间是否大于当前时间+7天，如果大于则+冗余时间，原则就是距离比较近的排期不需要冗余时间
			flag = startDate.getTime() > (System.currentTimeMillis() + 7 * 24 * 3600 * 1000);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		if(list.size() == 0 && flag) {
			para.accumulate("pushType", 1);//加冗余
			para.accumulate("startDate", lineStartTime);
		}else{
			Date endTimedate = null;
			SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd");
			Calendar Endtimes = Calendar.getInstance();
			int result = 1;
			if (list != null && list.size() > 0) {
				String listendTime = list.get(0).getEndTime();
				String endTime = listendTime.replace("-", "");
				result = lineStartTime.compareTo(endTime);//对比时间
				try {
					endTimedate = sdff.parse(listendTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Endtimes.setTime(endTimedate);
				Endtimes.add(Calendar.DATE,1);
			}
			if (result <= 0){
				para.accumulate("startDate", sdff.format(Endtimes.getTime()).replace("-", ""));
			}else{
//				para.accumulate("startDate", itemInterview.getLineStartTime().replace("-", ""));
				para.accumulate("startDate", lineStartTime);
			}
			para.accumulate("pushType", 2);//不加冗余
		}
		String result =  service.savePersonNelSchedule(para.toString());
		System.err.println("参数："+para.toString());
		System.err.println("result："+result);
		if(!"".equals(result) && null != result){
			JSONObject r = JSONObject.fromObject(result);
			//JSONObject data = JSONObject.fromObject(r.get("data"));
			String code = r.getString("code");
			String msg = r.getString("msg");
			if(!code.equals("0")){
				throw new BaseException("fail:"+msg);
			}
		}else{
			throw new BaseException("fail");
		}
		return true;
	}

	@Override
	public void updateItemInterview(ItemInterview itemInterview) {
		int returnValue;
		try {
			if(itemInterview.getType()==null&&itemInterview.getId()==null&&itemInterview.getOrderId()!=null){
				returnValue = this.wrItemInterviewMapper.updateItemInterviewByOrderId(itemInterview);
				if(itemInterview.getType()!=null && itemInterview.getType()==2){
					Order order = new Order();
					order.setId(itemInterview.getOrderId());
					order.setOrderStatus(2);
					orderService.updateOrder2(order);
				}
			}else{
				EScheduleService service = (EScheduleService)EClientContext.getBean2(EScheduleService.class);
				JSONObject json=new JSONObject();
				json.accumulate("orderId", itemInterview.getOrderId());
				json.accumulate("personids", itemInterview.getPersonids());
				// 状态 1 空闲 2 忙碌 3 预占 4 其他 
				if(itemInterview.getType()!=null && itemInterview.getType()==6){
					 	//增加排期
					String requestData=service.updateScheduleIsEnable(json.toString());
					if(!requestData.equals("") && requestData != null){
						JSONObject r = JSONObject.fromObject(requestData);
						String code = r.getString("code");
						//String msg = r.getString("msg");
						if(!code.equals("0")){
							throw new BaseException("启用服务人员排期失败!");
						}
					}
					//释放为面试失败服务人员的排期
					/*ItemDetailServer itemDetailServer = new ItemDetailServer();
					itemDetailServer.setOrderId(itemInterview.getOrderId());
					List<ItemDetailServer> list = this.reItemDetailServerMapper.queryOrderServerLined(itemDetailServer);
					if(list.size() > 0){
						itemInterview.setPersonId(Long.valueOf(itemInterview.getPersonids()));
						itemInterview.setType(20);
						List<Long> personIDList=reItemInterviewMapper.queryNotHousehold(itemInterview);
						for (Long persponID : personIDList) {
							System.err.println(persponID);
							ItemDetailServer itd = this.reItemDetailServerMapper.loadOrderServerLined(itemInterview.getOrderId());
							if(itd.getLinedType()==2){
								//钟点工
								itd.setLinedType(2);
							}else if(itd.getLinedType()==3){
								//保姆类
								itd.setLinedType(3);
							}
							lindDays(itd,persponID,1);
						}
					}*/
					//修改服务人员状态
					String[] idst = itemInterview.getIds().split(",");
					int num=0;
					for (String inwid : idst) {
						// 修改本次面试通过的服务人员状态
						itemInterview.setId(Long.parseLong(inwid));
				     	num = this.wrItemInterviewMapper.updateItemInterview(itemInterview);
					}
					returnValue=num;
					// 修改本次其他面试未通过的服务人员状态
					itemInterview.setType(6);
					itemInterview.setInterviewType(7);
					this.wrItemInterviewMapper.updateItemInterviewByOrderId(itemInterview);
					if(itemInterview.getInterviewTypeBak().equals("6")){//正常面试成功改订单状态
						Order order = new Order();
						order.setId(itemInterview.getOrderId());
						order.setOrderStatus(6);
						orderService.updateOrder2(order);
					}
					
					
				}else if(itemInterview.getType()!=null && itemInterview.getType()==8){
					// 延续订单上户
					if(itemInterview.getStarTime()==null){
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						itemInterview.setStarTime(df.format(Calendar.getInstance().getTime()));
					}
					itemInterview.setId(Long.valueOf(itemInterview.getIds()));
						returnValue =this.wrItemInterviewMapper.updateItemInterview(itemInterview);
					// 更新订单
					Order order = new Order();
					// 判断是否为当前订单的第一个上户的服务人员，如果是，需要新增一笔信息费结算单
					/**
					if("0".equals(itemInterview.getIds())){
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						// 扣除信息费
						Payfee account = new Payfee();
						account.setOrderId(itemInterview.getOrderId());
						account.setAccountAmount(itemInterview.getCustomerManageMoney());
						account.setVersion(1L);
						account.setValid(1);
						account.setPayStatus(20110003);
						account.setPayType("6");
						account.setIsBackType(2);
						account.setPayKind(2);
						account.setBussStatus(2);
						account.setIsManual(1);
						account.setStartTime(df.format(Calendar.getInstance().getTime()));
						account.setEndTime(df.format(Calendar.getInstance().getTime()));
						account.setCreateBy(itemInterview.getUpdateBy());
						this.wrPayfeeMapper.insertAccount(account);
						// 服务订单当前余额扣除信息费
						order.setTotalPay(itemInterview.getTotalPay().subtract(itemInterview.getCustomerManageMoney()));
					}
					**/
					order.setId(itemInterview.getOrderId());
					order.setOrderStatus(8);
					order.setUpdateBy(itemInterview.getUpdateBy());
					orderService.updateOrder2(order);

					//服务人员排期处理
					ItemDetailServer itd = this.reItemDetailServerMapper.loadOrderServerLined(itemInterview.getOrderId());
					
					//插入排期记录表
					ServerLinedRecord serverLinedRecord=new ServerLinedRecord();
					serverLinedRecord.setOrderId(itd.getId());
					serverLinedRecord.setStartTime(itd.getStartTime());
					serverLinedRecord.setEndTime(itd.getEndTime());
					serverLinedRecord.setCreateBy(itemInterview.getUpdateBy());
					serverLinedRecord.setCreateTime(DateUtil.getCurrDateTime());
					this.wrItemInterviewMapper.insertOrderLineRecord(serverLinedRecord);

					//字符串转换为时间
					SimpleDateFormat fmtDateTime  = new SimpleDateFormat("yyyy-MM-dd");
					Date shnghuDate = fmtDateTime.parse(itemInterview.getStarTime()); 
					Date paqiDate = fmtDateTime.parse(itd.getStartTime()); 
					
					//创建时间对象
					 Calendar shanghuCalendar = Calendar.getInstance();  
					 shanghuCalendar.setTime(shnghuDate);
					 Calendar paiqiStsrtCalendar = Calendar.getInstance();  
					 paiqiStsrtCalendar.setTime(paqiDate);
					 
					//该订单一共排期一共多少天
				    Calendar paiqiEndCalendar = Calendar.getInstance();
				    paiqiEndCalendar.setTime(fmtDateTime.parse(itd.getEndTime()));
				    long paiqiDay=(paiqiEndCalendar.getTimeInMillis()-paiqiStsrtCalendar.getTimeInMillis())/(1000 * 60 * 60 * 24);

					System.err.println("上户时间"+itemInterview.getStarTime()+"排期开始时间"+itd.getStartTime()+"排期结束时间"+itd.getEndTime());
					long day = (shanghuCalendar.getTimeInMillis() - paiqiStsrtCalendar.getTimeInMillis()) / (1000 * 60 * 60 * 24);  
					
					//正常上户
					if(day==0){
						//不进行操作
						System.err.println("正常上户");
					}else {
						System.err.println("非正常上户");
						//释放该服务人员排期
						if(itd.getLinedType()==2){
							//钟点工
							itd.setLinedType(2);
						}else if(itd.getLinedType()==3){
							//保姆类
							itd.setLinedType(3);
						}
						System.err.println(itemInterview.getPersonId());
						itd.setMarkType(10);//非正常上户，释放所有排期
						lindDays(itd,itemInterview.getPersonId(),1);
						//锁定相应排期
						if(itd.getLinedType()==2){
							//钟点工
							itd.setLinedType(2);
						}else if(itd.getLinedType()==3){
							//保姆类
							itd.setLinedType(3);
						}
						itd.setStartTime(itemInterview.getStarTime());
						shanghuCalendar.add(Calendar.DATE, (int)paiqiDay);
						itd.setEndTime((new SimpleDateFormat("yyyy-MM-dd")).format(shanghuCalendar.getTime()));
						System.err.println("start"+itd.getStartTime()+"end"+itd.getEndTime());
						lindDays(itd,itemInterview.getPersonId(),4);

						//修改订单排期   并增加排期记录
						ItemDetailServer itemDetailServer=new ItemDetailServer();
						itemDetailServer.setId(itd.getId());
						itemDetailServer.setStartTime(itd.getStartTime());//服务人员实际上户时间
						itemDetailServer.setEndTime(itd.getEndTime());//修改后订单排期结束时间
						this.wrItemDetailServerMapper.updateOrderServerLined(itemDetailServer);
						
					}
					
				}else if(itemInterview.getType()!=null && itemInterview.getType()==9){
					// 延续订单下户
					if(itemInterview.getEndTime()==null){
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						itemInterview.setEndTime(df.format(Calendar.getInstance().getTime()));
					}
					returnValue =this.wrItemInterviewMapper.updateItemInterview(itemInterview);
					//itemInterview.setInterviewType(9);
					//this.wrItemInterviewMapper.updateItemInterviewByOrderId(itemInterview);
					Order order = new Order();
					order.setId(itemInterview.getOrderId());
					order.setOrderStatus(11);
					order.setUpdateBy(itemInterview.getUpdateBy());
					orderService.updateOrder2(order);
					//服务人员排期处理
					ItemInterview itemInterview2=reItemInterviewMapper.queryInterview(itemInterview);
					System.err.println("下户时间"+itemInterview.getEndTime()+"排期上户时间"+itemInterview2.getStarTime());
					Long end=Long.valueOf(itemInterview.getEndTime().replace("-","").substring(0,8));
					Long start=Long.valueOf(itemInterview2.getStarTime().replace("-","").substring(0,8));
					if (end-start<0) {
						throw new BaseException("update fail!");
					}
					ItemDetailServer itd = this.reItemDetailServerMapper.loadOrderServerLined(itemInterview.getOrderId());
					
					//插入排期记录表
					ServerLinedRecord serverLinedRecord=new ServerLinedRecord();
					serverLinedRecord.setOrderId(itd.getId());
					serverLinedRecord.setStartTime(itd.getStartTime());
					serverLinedRecord.setEndTime(itd.getEndTime());
					serverLinedRecord.setCreateBy(itemInterview.getUpdateBy());
					serverLinedRecord.setCreateTime(DateUtil.getCurrDateTime());
					this.wrItemInterviewMapper.insertOrderLineRecord(serverLinedRecord);
					//字符串转换为时间
					SimpleDateFormat fmtDateTime  = new SimpleDateFormat("yyyy-MM-dd");
					
					//创建时间对象
					 Calendar xiahuCalendar = Calendar.getInstance(); //实际下户时间
					 xiahuCalendar.setTime(fmtDateTime.parse(itemInterview.getEndTime()));
					 Calendar paiqiEndCalendar = Calendar.getInstance(); //排期下户时间
					 paiqiEndCalendar.setTime(fmtDateTime.parse(itd.getEndTime()));
					 Calendar shanghuCalendar = Calendar.getInstance(); //实际上户时间
					 shanghuCalendar.setTime(fmtDateTime.parse(itemInterview2.getStarTime()));
					 Calendar paiqiStartCalendar = Calendar.getInstance();//排期上户时间
					 paiqiStartCalendar.setTime(fmtDateTime.parse(itd.getStartTime()));
					 
					 //排期天数
					 long paiqiDay=((paiqiEndCalendar.getTimeInMillis()-paiqiStartCalendar.getTimeInMillis())/(1000 * 60 * 60 * 24))+1;
					 //实际上户天数
					 long huDay= ((xiahuCalendar.getTimeInMillis()-shanghuCalendar.getTimeInMillis())/(1000 * 60 * 60 * 24))+1;
					 //排期和实际上户天数的差
					 long cha=paiqiDay-huDay;
					 //实际与排期下户天数的差
					 long day = (xiahuCalendar.getTimeInMillis() - paiqiEndCalendar.getTimeInMillis()) / (1000 * 60 * 60 * 24);  
					 //实际与排期上户日期的天数差
					 long shanghuday = (shanghuCalendar.getTimeInMillis() - paiqiStartCalendar.getTimeInMillis()) / (1000 * 60 * 60 * 24);  
					//正常下户（释放服务人员排期）
					if(day==0 && itemInterview.getEndTime().equals(itd.getEndTime())){
						if(itd.getLinedType()==2){
							//钟点工
							itd.setLinedType(2);
						}else if(itd.getLinedType()==3){
							//保姆类
							itd.setLinedType(3);
						}
						//提前上户、正常上户与推迟上户，都先释放所有排期
						itd.setMarkType(10);
						lindDays(itd,itemInterview.getPersonId(),1);
						//按照实际上户时间和正常下户时间，重新占用排期
						itd.setStartTime(itemInterview2.getStarTime());
						itd.setEndTime(itemInterview.getEndTime());
						System.err.println("start"+itd.getStartTime()+"end"+itd.getEndTime());
						itd.setMarkType(null);
						lindDays(itd,itemInterview.getPersonId(),4);
					}else {
						System.err.println("非正常上下户");
						//释放原有排期
						if(itd.getLinedType()==2){
							//钟点工
							itd.setLinedType(2);
						}else if(itd.getLinedType()==3){
							//保姆类
							itd.setLinedType(3);
						}
						itd.setMarkType(10);
						lindDays(itd,itemInterview.getPersonId(),1);
						//锁定新的排期	
						itd.setStartTime(itemInterview2.getStarTime());
						itd.setEndTime(itemInterview.getEndTime());
						System.err.println("开始时间"+itd.getStartTime()+"结束时间"+itd.getEndTime());
						itd.setMarkType(null);
						lindDays(itd,itemInterview.getPersonId(),4);
						
						//修改订单排期   并增加排期记录
						ItemDetailServer itemDetailServer=new ItemDetailServer();
						itemDetailServer.setId(itd.getId());
						itemDetailServer.setStartTime(itd.getStartTime().substring(0, 19));//服务人员实际上户时间
						itemDetailServer.setEndTime(itd.getEndTime());//修改后订单排期结束时间
						this.wrItemDetailServerMapper.updateOrderServerLined(itemDetailServer);
						
					}
				}else if(itemInterview.getType()!=null && itemInterview.getType()==10){
					// 延续性服务订单取消
					String[] idst = itemInterview.getIds().split(",");
					int num=0;
					for (String inwid : idst) {
						// 修改本次面试通过的服务人员状态
						itemInterview.setId(Long.parseLong(inwid));
						num =this.wrItemInterviewMapper.updateItemInterview(itemInterview);
					}
					returnValue =num;
					//itemInterview.setInterviewType(10);
					//this.wrItemInterviewMapper.updateItemInterviewByOrderId(itemInterview);
					// 取消当前确定的服务人员后，订单状态改为已受理
					ItemInterview itemInterviewss = new ItemInterview();
					itemInterviewss.setOrderId(itemInterview.getOrderId());
					itemInterviewss.setInterviewType(8);
					List<ItemInterview> list = this.reItemInterviewMapper.queryInterviews(itemInterviewss);
					if(list.size() == 0){
						Order order = new Order();
						order.setId(itemInterview.getOrderId());
						order.setOrderStatus(2);
						orderService.updateOrder2(order);
					}
					//释放服务人员排期
					ItemDetailServer itd = this.reItemDetailServerMapper.loadOrderServerLined(itemInterview.getOrderId());
					/*if(itd.getLinedType()==2){
						//钟点工
						itd.setLinedType(2);
					}else if(itd.getLinedType()==3){
						//保姆类
						itd.setLinedType(3);
					}*/
					if(itd!=null){
						//根据上户类型设置排期类型
						itd.setMarkType(itemInterview.getType());
						lindDays(itd,itemInterview.getPersonId(),1);
					}
					
				}else if(itemInterview.getType()!=null && itemInterview.getType()==11){
					// 单次服务订单取消
					returnValue = this.wrItemInterviewMapper.updateItemInterview(itemInterview);
					
					//释放排期
					ItemDetailServer itd = this.reItemDetailServerMapper.loadOrderServerLined(itemInterview.getOrderId());
					itd.setLinedType(1);
					lindDays(itd,itemInterview.getPersonId(),1);
					// 释放服务人员排期
//					ItemDetailServer itd = this.reItemDetailServerMapper.loadOrderServerLined(itemInterview.getOrderId());
//					lindDays(itd,itemInterview.getPersonId(),1);
				}else if(itemInterview.getType()!=null && itemInterview.getType()==12){
					// 自营单次服务上户
					List<ItemInterview> list = this.reItemInterviewMapper.queryItemInterviewByOrderId(itemInterview);
					if(list!=null&&list.size()>=1){
						itemInterview.setStarTime(list.get(0).getStarTime());
					}else{
						Order order = new Order();
						order.setId(itemInterview.getOrderId());
						order.setOrderStatus(8);
						orderService.updateOrder2(order);
					}
					//释放排期
					itemInterview.setEmp_id(itemInterview.getPersonids().split(","));
					itemInterview.setType(21);
					List<Long> personIDList=reItemInterviewMapper.queryNotHousehold(itemInterview);
					for (Long persponID : personIDList) {
						//returnValue = this.wrItemInterviewMapper.updatePersonStartOneByOrderId(itemInterview);
						ItemDetailServer itd = this.reItemDetailServerMapper.loadOrderServerLined(itemInterview.getOrderId());
						itd.setLinedType(1);
						lindDays(itd,persponID,1);
						// 修改本次其他未上户的服务人员设置为取消
						ItemInterview itemInter = new ItemInterview();
						itemInter.setOrderId(itemInterview.getOrderId());
						itemInter.setInterviewType(10);
						itemInter.setPersonId(persponID);
						itemInter.setType(7);
						this.wrItemInterviewMapper.updateItemInterviewByOrderId(itemInter);
					}
					//修改服务人员面试状态
					String[] idst = itemInterview.getIds().split(",");
					int num=0;
					for (String inwid : idst) {
						// 修改本次面试通过的服务人员状态
						itemInterview.setId(Long.parseLong(inwid));
						num = this.wrItemInterviewMapper.updateItemInterview(itemInterview);
					}
					returnValue=num;

					//20180613日服务人员上户后修改服务人员状态
					String personids = itemInterview.getPersonids();
					String[] personnal = personids.split(",");
					for(int i=0 ; i<personnal.length ; i++){
						wrOrderMapper.updatePersonnalStatus(1l,Long.parseLong(personnal[i]));
					}
				}else if(itemInterview.getType()!=null && itemInterview.getType()==13){
					// 自营单次服务下户
					/*returnValue = this.wrItemInterviewMapper.updatePersonStartOneByOrderId(itemInterview);*/
					String[] idst = itemInterview.getIds().split(",");
					int num=0;
					for (String inwid : idst) {
						// 修改本次面试通过的服务人员状态
						itemInterview.setId(Long.parseLong(inwid));
						num = this.wrItemInterviewMapper.updateItemInterview(itemInterview);
					}
					returnValue=num;
					//释放服务人员排期
					ItemDetailServer Serverlist = new ItemDetailServer();
					Serverlist.setOrderId(itemInterview.getOrderId());
					List<ItemDetailServer> listDetail = this.reItemDetailServerMapper.queryOrderServerLined(Serverlist);
					if(listDetail.size() > 0){//有订单排期
						String[] personId=itemInterview.getPersonids().split(",");
						for (String person : personId) {
							ItemDetailServer itd = this.reItemDetailServerMapper.loadOrderServerLined(itemInterview.getOrderId());
							itd.setLinedType(1);
							lindDays(itd,Long.parseLong(person),1);
							//20180613日服务人员下户后修改服务人员状态
							wrOrderMapper.updatePersonnalStatus(10l,Long.parseLong(person));
						}
					}
					// 修改订单状态
					Order order = new Order();
					order.setId(itemInterview.getOrderId()); 
					order.setOrderStatus(11);
					// 校验是否是已完成订单
					Payfee pff = new Payfee();
					pff.setPayStatus(20110003);
					pff.setValid(1);
					pff.setOrderId(itemInterview.getOrderId());
					Payfee pf = this.rePayfeeMapper.queryAccountByOrderId(pff);
					if(pf!=null&&pf.getFeeSum()!=null&&pf.getFeeSum().floatValue()>=0){
						OrderBaseModel obm = this.reOrderBaseMapper.queryOrderBasicServer(itemInterview.getOrderId()).get(0);
						// 当本次录入 的数据 与之前的总数量不相等的时候，更新商品的总数量
						if(obm.getQuantity()!=itemInterview.getQuantity()){
							Item item = new Item();
							item.setOrderId(itemInterview.getOrderId());
							item.setProductCode(obm.getProductCode());
							item.setQuantity(itemInterview.getQuantity());
							item.setValid(1);
							this.wrItemMapper.updateItemQuantityByOrderId(item);
						}
						// 如果是对账的金额大于或等于下户时应该付的总金额，则当前订单设为已完成，订单支付状态为已支付
						if(pf.getFeeSum().floatValue()>=obm.getNowPrice().floatValue()*itemInterview.getQuantity()){
							order.setOrderStatus(9);
							order.setPayStatus("20110003");
						}
					}
					orderService.updateOrder2(order);
				}else if(itemInterview.getType()!=null && itemInterview.getType()==14){
					// 自营单次服务取消
					String[] idst = itemInterview.getIds().split(",");
					int num=0;
					for (String inwid : idst) {
						// 修改本次面试通过的服务人员状态
						itemInterview.setId(Long.parseLong(inwid));
						num =this.wrItemInterviewMapper.updateItemInterview(itemInterview);
					}
					returnValue =num;
					// 释放服务人员排期
					String[] personId=itemInterview.getPersonids().split(",");
					for (String person : personId) {
						ItemDetailServer Serverlist = new ItemDetailServer();
						Serverlist.setOrderId(itemInterview.getOrderId());
						Serverlist.setEmp_id(Long.parseLong(person));
						List<ItemDetailServer> list = this.reItemDetailServerMapper.queryEmpSchedule(Serverlist);
						if(list.size() > 0){
							ItemDetailServer itd = this.reItemDetailServerMapper.loadOrderServerLined(itemInterview.getOrderId());
							itd.setLinedType(1);
							lindDays(itd,Long.parseLong(person),1);
						}
					}
					/*try{
						ItemDetailServer itd = this.reItemDetailServerMapper.loadItemDetailServerByOrderId(itemInterview.getOrderId());
						itd.setLinedType(11);
						lindDays(itd,itemInterview.getPersonId(),1);
					}catch(Exception e){}*/
				}else{
					returnValue = this.wrItemInterviewMapper.updateItemInterview(itemInterview);
				}
			}
			if (returnValue<=0) {
				throw new BaseException("update fail!");
			}
		} catch (Exception e) {
			log.error("updateItemInterview:" + e);
			throw new BaseException(e);
		}
		//throw new RuntimeException("lindDays");
	}
	
	//查询做服务人员服务人员服务费的时段月份人员等
	@Override
	public List<HashMap> querySalaryCondition(HashMap mapCondition){
		return this.reItemInterviewMapper.querySalaryCondition(mapCondition);
	}

	/**
	 * 
	 * @param itd
	 * @param personId 服务人员Id
	 * @param status 状态 1 空闲 2 忙碌 3 预占 4 其他
	 * @return
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public void lindDays(ItemDetailServer itd,Long personId,int status){
		boolean result = false;
		LogHelper.info(getClass()+ ".lindDays()","设置排期状态："+status);
		try {
			if(itd.getLinedType()==1){
				// 单次排期
				String startDate=itd.getStartTime().replace(" ", "").replace("-", "").replace(":", "");
				System.err.println(startDate);
				String timeSlot=itd.getDays();
				String startTime=Tools.lindHours(itd.getHours()).split("-")[0]; //时间段转换
				String endTime=Tools.lindHours(itd.getHours()).split("-")[1];
				String json = "{\"startDate\":" +startDate.substring(0, 8) 
							+",\"endDate\":" +startDate.substring(0,8) 
							+",\"startTime\":" +startTime 
							+",\"endTime\":" +endTime
							+",\"status\":" +status 
							+",\"empId\":" +personId 
							+",\"orderId\":" +itd.getOrderId() 
							+"}";
				System.out.println("lindDays:" +json);
				LogHelper.info(getClass()+ ".lindDays()","json字符串："+json);
				//result = this.personnelInterfaceService.updateSchedule("[" +json +"]");
				EScheduleService service = (EScheduleService)EClientContext.getBean(EScheduleService.class);
				String jsonStr = service.updateScheduleOnce("[" +json +"]");
				JSONObject jsons = JSONObject.fromObject(jsonStr);
				result = jsons.getBoolean("data");
				LogHelper.info(getClass()+ ".lindDays()","排期是否更新成功："+result);
			}else if(itd.getLinedType()==3){
				//延续性（保姆类）
				String startDate=itd.getStartTime().replace(" ", "").replace("-", "").replace(":", "");
				String endDate=itd.getEndTime().replace(" ", "").replace("-", "").replace(":", "");
				startDate = startDate.replace(" ", "").replace("-", "").replace(":", "");
				endDate = endDate.replace(" ", "").replace("-", "").replace(":", "");
				//将时间字符串转为Date类型，再推算一周，再转为字符串类型
				//占用时间的前一周冗余时间段
				String startDateLeft=com.emotte.order.util.DateUtil.dateToString(com.emotte.order.util.DateUtil .addDay(com.emotte.order.util.DateUtil.stringDayToDate(itd.getStartTime()), -7));
				String endDateLeft=com.emotte.order.util.DateUtil.dateToString(com.emotte.order.util.DateUtil .addDay(com.emotte.order.util.DateUtil.stringDayToDate(itd.getStartTime()), -1));
				startDateLeft = startDateLeft.replace(" ", "").replace("-", "").replace(":", "").substring(0, 8);
				endDateLeft = endDateLeft.replace(" ", "").replace("-", "").replace(":", "").substring(0, 8);
				//占用时间的后一周冗余时间段
				String startDateRight=com.emotte.order.util.DateUtil.dateToString(com.emotte.order.util.DateUtil .addDay(com.emotte.order.util.DateUtil.stringDayToDate(itd.getEndTime()), 1));
				String endDateRight=com.emotte.order.util.DateUtil.dateToString(com.emotte.order.util.DateUtil .addDay(com.emotte.order.util.DateUtil.stringDayToDate(itd.getEndTime()), 7));
				startDateRight = startDateRight.replace(" ", "").replace("-", "").replace(":", "").substring(0, 8);
				endDateRight = endDateRight.replace(" ", "").replace("-", "").replace(":", "").substring(0, 8);
				
				String json1 = "{\"startDate\":" +startDate.substring(0, 8)
				+",\"endDate\":" +endDate.substring(0,8) 
				+",\"orderId\":" +itd.getOrderId()  
				+",\"status\":" +status +",\"empId\":\"" +personId +"\"}";
				String json ="";
				int statusBack = 2;
				if (itd.getMarkType() != null) {//排期取消标记不为空
					if (status == 1 &&  itd.getMarkType() == 10) {
						statusBack = 1;
					} 
					//排期时间段 = 前一周冗余时间段 + 正常排期 + 后一周冗余时间段
					json= "{\"startDate\":" +startDateLeft
							+",\"endDate\":" +endDateLeft
							+",\"orderId\":" +itd.getOrderId() 
							+",\"status\":" +statusBack +",\"empId\":\"" +personId +"\"},"
							+json1
							+",{\"startDate\":" +startDateRight
							+",\"endDate\":" +endDateRight
							+",\"orderId\":" +itd.getOrderId() 
							+",\"status\":" +statusBack +",\"empId\":\"" +personId +"\"}";
				}else {//排期取消标记为空，为正常操作
					/*if (status == 1) {//释放排期
						statusBack = 1; //冗余时间也释放
						if (startDate.equals(endDate)) {//开始时间和结束时间相同，释放后边的冗余时间
							json=   "{\"startDate\":" +startDateRight
									+",\"endDate\":" +endDateRight
									+",\"orderId\":" +itd.getOrderId() 
									+",\"status\":" +statusBack +",\"empId\":\"" +personId +"\"}";
						} else {//开始时间和结束时间不相同，释放后边正常部分排期和冗余时间
							json=json1
									+",{\"startDate\":" +startDateRight
									+",\"endDate\":" +endDateRight
									+",\"orderId\":" +itd.getOrderId() 
									+",\"status\":" +statusBack +",\"empId\":\"" +personId +"\"}";
						}
					}else{*/
						//排期时间段 = 前一周冗余时间段 + 正常排期 + 后一周冗余时间段
						json= "{\"startDate\":" +startDateLeft
								+",\"endDate\":" +endDateLeft
								+",\"orderId\":" +itd.getOrderId() 
								+",\"status\":" +status +",\"empId\":\"" +personId +"\"},"
								+json1
								+",{\"startDate\":" +startDateRight
								+",\"endDate\":" +endDateRight
								+",\"orderId\":" +itd.getOrderId() 
								+",\"status\":" +status +",\"empId\":\"" +personId +"\"}";
					/*}*/
				}
				System.out.println("lindDays:" +json);
				LogHelper.info(getClass()+ ".lindDays()","json字符串："+json);
				EScheduleService service = (EScheduleService)EClientContext.getBean(EScheduleService.class);
				String jsonStr = service.updateSchedule("[" +json +"]");
				JSONObject jsons = JSONObject.fromObject(jsonStr);
				result = jsons.getBoolean("data");
				//result = this.personnelInterfaceService.updateSchedule("[" +json +"]");
				LogHelper.info(getClass()+ ".lindDays()","排期是否更新成功："+result);
			}else if(itd.getLinedType()==2){
				//延续性（钟点工）
				System.out.println(itd.getStartTime());
				System.out.println(itd.getEndTime());
				String startDate=itd.getStartTime().replace(" ", "").replace("-", "").replace(":", "");
				String endDate=itd.getEndTime().replace(" ", "").replace("-", "").replace(":", "");
				String startTime=Tools.lindHours(itd.getHours()).split("-")[0]; //时间段转换
				String endTime=Tools.lindHours(itd.getHours()).split("-")[1];
				String[] Week = itd.getWeeks().split("星期");
				//前台排期，用星期截取后，有多个逗号
				String str1=StringUtils.join(Week, ",");
				//将多个逗号替换成一个逗号
				str1 = str1.replaceAll("[',']+", ",");  
				//将字符串替换成数组
				Week = str1.split(",");
				String wek = "" ;
				for(int i=0 ; i<Week.length; i++){
					if(!wek.equals("")){
						wek += ",";
					}
					if(Week[i].endsWith("一")){
						wek += "1";
					}else if(Week[i].endsWith("二")){
						wek += "2";
					}else if(Week[i].endsWith("三")){
						wek += "3";
					}else if(Week[i].endsWith("四")){
						wek += "4";
					}else if(Week[i].endsWith("五")){
						wek += "5";
					}else if(Week[i].endsWith("六")){
						wek += "6";
					}else if(Week[i].endsWith("天")){
						wek += "7";
					}
				}
				/*String json = "{\"startDate\":" +startDate.substring(0, 8) +",\"endDate\":" +endDate.substring(0, 8)
				+",\"startTime\":" +startTime +",\"endTime\":" +endTime+",\"orderId\":" +itd.getOrderId() +","
				+ "\"weekday\":\"" +wek +"\",\"status\":" +status +",\"empId\":\"" +personId +"\"}";*/
				
				//将时间字符串转为Date类型，再推算一周，再转为字符串类型
				//占用时间的前一周冗余时间段
				String startDateLeft=com.emotte.order.util.DateUtil.dateToString(com.emotte.order.util.DateUtil .addDay(com.emotte.order.util.DateUtil.stringDayToDate(itd.getStartTime()), -7));
				String endDateLeft=com.emotte.order.util.DateUtil.dateToString(com.emotte.order.util.DateUtil .addDay(com.emotte.order.util.DateUtil.stringDayToDate(itd.getStartTime()), -1));
				startDateLeft = startDateLeft.replace(" ", "").replace("-", "").replace(":", "").substring(0, 8);
				endDateLeft = endDateLeft.replace(" ", "").replace("-", "").replace(":", "").substring(0, 8);
				//占用时间的后一周冗余时间段
				String startDateRight=com.emotte.order.util.DateUtil.dateToString(com.emotte.order.util.DateUtil .addDay(com.emotte.order.util.DateUtil.stringDayToDate(itd.getEndTime()), 1));
				String endDateRight=com.emotte.order.util.DateUtil.dateToString(com.emotte.order.util.DateUtil .addDay(com.emotte.order.util.DateUtil.stringDayToDate(itd.getEndTime()), 7));
				startDateRight = startDateRight.replace(" ", "").replace("-", "").replace(":", "").substring(0, 8);
				endDateRight = endDateRight.replace(" ", "").replace("-", "").replace(":", "").substring(0, 8);
				
				String json1 = "{\"startDate\":" +startDate.substring(0, 8) +",\"endDate\":" +endDate.substring(0, 8)
						+",\"startTime\":" +startTime +",\"endTime\":" +endTime+",\"orderId\":" +itd.getOrderId() +","
						+ "\"weekday\":\"" +wek +"\",\"status\":" +status +",\"empId\":\"" +personId +"\"}";
				String json ="";
				int statusBack = 2;
				if (itd.getMarkType() != null) {//排期取消标记不为空
					if (status == 1 &&  itd.getMarkType() == 10) {
						statusBack = 1;
					} 
					//排期时间段 = 前一周冗余时间段 + 正常排期 + 后一周冗余时间段
					json= "{\"startDate\":" +startDateLeft +",\"endDate\":" +endDateLeft
							+",\"startTime\":" +startTime +",\"endTime\":" +endTime+",\"orderId\":" +itd.getOrderId() +","
							+ "\"weekday\":\"" +wek +"\",\"status\":" +statusBack +",\"empId\":\"" +personId +"\"},"
							+json1
							+",{\"startDate\":" +startDateRight +",\"endDate\":" +endDateRight
							+",\"startTime\":" +startTime +",\"endTime\":" +endTime+",\"orderId\":" +itd.getOrderId() +","
							+ "\"weekday\":\"" +wek +"\",\"status\":" +statusBack +",\"empId\":\"" +personId +"\"}";
				}else {//排期取消标记为空，为正常操作
					/*if (status == 1) {//释放排期
						statusBack = 1; //冗余时间也释放
						if (startDate.equals(endDate)) {//开始时间和结束时间相同，释放后边的冗余时间
							json=   "{\"startDate\":" +startDateRight +",\"endDate\":" +endDateRight
								+",\"startTime\":" +startTime +",\"endTime\":" +endTime+",\"orderId\":" +itd.getOrderId() +","
								+ "\"weekday\":\"" +wek +"\",\"status\":" +statusBack +",\"empId\":\"" +personId +"\"}";
						} else {//开始时间和结束时间不相同，释放后边正常部分排期和冗余时间
							json=json1
									+",{\"startDate\":" +startDateRight +",\"endDate\":" +endDateRight
									+",\"startTime\":" +startTime +",\"endTime\":" +endTime+",\"orderId\":" +itd.getOrderId() +","
									+ "\"weekday\":\"" +wek +"\",\"status\":" +statusBack +",\"empId\":\"" +personId +"\"}";
						}
					}else{*/
						//排期时间段 = 前一周冗余时间段 + 正常排期 + 后一周冗余时间段
					if(status == 4){
						json= "{\"startDate\":" +startDateLeft +",\"endDate\":" +endDateLeft
								+",\"startTime\":" +startTime +",\"endTime\":" +endTime+",\"orderId\":" +itd.getOrderId() +","
								+"\"weekday\":\"" +wek +"\",\"status\":" +statusBack +",\"empId\":\"" +personId +"\"},"
								+json1
								+",{\"startDate\":" +startDateRight +",\"endDate\":" +endDateRight
								+",\"startTime\":" +startTime +",\"endTime\":" +endTime+",\"orderId\":" +itd.getOrderId() +","
								+ "\"weekday\":\"" +wek +"\",\"status\":" +statusBack +",\"empId\":\"" +personId +"\"}";
					}else{
						json= "{\"startDate\":" +startDateLeft +",\"endDate\":" +endDateLeft
								+",\"startTime\":" +startTime +",\"endTime\":" +endTime+",\"orderId\":" +itd.getOrderId() +","
								+"\"weekday\":\"" +wek +"\",\"status\":" +status +",\"empId\":\"" +personId +"\"},"
								+json1
								+",{\"startDate\":" +startDateRight +",\"endDate\":" +endDateRight
								+",\"startTime\":" +startTime +",\"endTime\":" +endTime+",\"orderId\":" +itd.getOrderId() +","
								+ "\"weekday\":\"" +wek +"\",\"status\":" +status +",\"empId\":\"" +personId +"\"}";
					}
					/*}*/
				}
				System.out.println("lindDays:" +json);
				LogHelper.info(getClass()+ ".lindDays()","json字符串："+json);
				EScheduleService service = (EScheduleService)EClientContext.getBean(EScheduleService.class);
				String jsonStr = service.updateSchedule("[" +json +"]");
				JSONObject jsons = JSONObject.fromObject(jsonStr);
				//JSONObject jsondata = JSONObject.fromObject(jsons.get("data"));
				System.out.println(jsons.getBoolean("data"));
				result = jsons.getBoolean("data");
				//{"code":"1000","data":"true","sign":"312ed39582404c1eea26bd07a07d14dc","iplist":{"die":"","alive":"192.168.1.229"}
				//result = this.personnelInterfaceService.updateSchedule("[" +json +"]");
				LogHelper.info(getClass()+ ".lindDays()","排期是否更新成功："+result);
				
			}
		} catch (BaseException e) {
			LogHelper.error(getClass(), ".lindDays()"+e.getMessage(),e);
		}
		/*try {
			String st = itd.getStartTime().substring(0, 10);
			System.err.println(itd.getEndTime());
			String et = itd.getEndTime().substring(0, 10);
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date nowDate = Calendar.getInstance().getTime();
			// 如果开始时间小于当前日期
			if(Integer.parseInt(st.replace("-", ""))<=Integer.parseInt(sdf.format(nowDate).replace("-", ""))){
				st = sdf.format(nowDate);
			}
			System.err.println(itd.getLinedType());
			//延续性（保姆类）
			if(itd.getLinedType()==1){
				// 按月延续
				String json = "{\"startDate\":" +st.replace("-", "") +",\"endDate\":" +et.replace("-", "") 
				+",\"status\":" +status +",\"empId\":\"" +personId +"\"}";
				this.personnelInterfaceService.updateSchedule("[" +json +"]");
//				int stm = Integer.valueOf(st.replace("-", ""));
//				int etm = Integer.valueOf(et.replace("-", ""));
//				String[] dary = itd.getDays().split(",");
//				for(int year=Integer.valueOf(st.split("-")[0]); year<=Integer.valueOf(et.split("-")[0]); year++){
//					for(int month=1; month<=12; month++){
//						for(int i=1; i<=31; i++){
//						}
//					}
//				}
			//延续性（钟点工）
			}else if(itd.getLinedType()==2){
				// 按周延续
				String[] weeks = itd.getWeeks().split(",");
				String wek = "" ;
				for(int i=0 ; i<weeks.length; i++){
					if(!wek.equals("")){
						wek += ",";
					}
					if(weeks[i].endsWith("一")){
						wek += "1";
					}else if(weeks[i].endsWith("二")){
						wek += "2";
					}else if(weeks[i].endsWith("三")){
						wek += "3";
					}else if(weeks[i].endsWith("四")){
						wek += "4";
					}else if(weeks[i].endsWith("五")){
						wek += "5";
					}else if(weeks[i].endsWith("六")){
						wek += "6";
					}else if(weeks[i].endsWith("天")){
						wek += "7";
					}
				}
				String hours = lindHours(itd.getHours());
				String[] hary = hours.split(",");
				//String aaa = "[{\"startDate\":20160813,\"endDate\":20160813,\"startTime\":70000,\"endTime\":223000,\"weekday\":\"0\",\"status\":3,\"empId\":\"277753000226836\"}]";
				for(int i=0; i<hary.length; i++){
					String json = "{\"startDate\":" +st.replace("-", "") +",\"endDate\":" +et.replace("-", "") 
						+",\"startTime\":" +hary[i].split("-")[0] +",\"endTime\":" +hary[i].split("-")[1]+","
						+ "\"weekday\":\"" +wek +"\",\"status\":" +status +",\"empId\":\"" +personId +"\"}";
					System.out.println("lindDays:" +json);
					this.personnelInterfaceService.updateSchedule("[" +json +"]");
				}
				//单次（保洁）
			}else if(itd.getLinedType()==3){
				// 全天延续
				String json = "{\"startDate\":" +st.replace("-", "") +",\"endDate\":" +et.replace("-", "") 
					+",\"status\":" +status +",\"empId\":\"" +personId +"\"}";
				System.out.println("lindDays:" +json);
				this.personnelInterfaceService.updateSchedule("[" +json +"]");
			}else if(itd.getLinedType()==11){
				// 单次排期
				String aa = itd.getStartTime().replace(" ", "").replace("-", "").replace(":", "");
				String bb = itd.getEndTime().replace(" ", "").replace("-", "").replace(":", "");
				String startDate=itd.getStartTime().replace(" ", "").replace("-", "").replace(":", "");
				System.err.println(startDate);
				String timeSlot=itd.getHours();
				String startTime=itd.getDays().split("-")[0];
				String endTime=itd.getDays().split("-")[1];
				String json = "{\"startDate\":" +startDate.substring(0, 8) 
							+",\"endDate\":" +startDate.substring(0,8) 
							+",\"startTime\":" +Long.valueOf(startDate.substring(8)) 
							+",\"endTime\":" +Long.valueOf(startDate.substring(8))
							+",\"status\":" +status 
							+",\"empId\":" +personId 
							+"}";
				System.out.println("lindDays:" +json);
				this.personnelInterfaceService.updateSchedule("[" +json +"]");
			}
		} catch (Exception e) {
			
		}*/
		
	}
	@Override
	public void contradictionPai(ItemDetailServer itemDetailServer) {
		ItemInterview interview=new ItemInterview();
		interview.setOrderId(itemDetailServer.getOrderId());
		ItemDetailServer itd = this.reItemDetailServerMapper.loadOrderServerLined(interview.getOrderId());
		//释放排期
		for (String personId : itemDetailServer.getOrderFenfa().split(",")){
			//根据上户类型设置排期类型
			itd.setMarkType(itemDetailServer.getMarkType());
			lindDays(itd,Long.valueOf(personId),1);
		}
		//将矛盾的服务人员进行删除
		if (itemDetailServer.getBirthDate()!=""&&!(itemDetailServer.getBirthDate().equals(""))) {
			for (String personId : itemDetailServer.getBirthDate().split(",")) {
				System.err.println("矛盾服务人员"+personId);
				interview.setInterviewType(7);
				interview.setType(7);
				interview.setPersonId(Long.valueOf(personId));
				this.wrItemInterviewMapper.updateItemInterviewByOrderId(interview);
			}
		}
		//不矛盾的服务人员添加新的排期
		if (itemDetailServer.getOrderFenfa()!=""&&!(itemDetailServer.getOrderFenfa().equals(""))) {
			for (String personId : itemDetailServer.getOrderFenfa().split(",")) {
				if (Arrays.binarySearch(itemDetailServer.getBirthDate().split(","), personId) < 0) {
					System.err.println("排期不矛盾"+personId);
					//添加新的排期
					if(itd.getLinedType()==2){
						//钟点工
						itd.setLinedType(2);
						itd.setStartTime(itemDetailServer.getStartTime());
						itd.setEndTime(itemDetailServer.getEndTime());
						itd.setHours(Tools.ChangeTime(itemDetailServer.getHours()));
						itd.setWeeks(itemDetailServer.getWeeks());
					}else if(itd.getLinedType()==3){
						//保姆类
						itd.setLinedType(3);
						itd.setStartTime(itemDetailServer.getStartTime());
						itd.setEndTime(itemDetailServer.getEndTime());
					}else if(itd.getLinedType()==1){
						//保洁类
						itd.setLinedType(1);
						itd.setStartTime(itemDetailServer.getStartTime());
						itd.setHours(Tools.ChangeTime(itemDetailServer.getHours()));
					}
					lindDays(itd,Long.valueOf(personId),4);
				}
				}
		}
	}
    /**
     * 查询已匹配的服务人员
     * @author YUNNA
     * @param itemInterview
     * @return 
     * @date 2017年07月27日
     */
	@Override
	public int  queryMatchingPersonId(ItemInterview itemInterview) {
		int  count = this.reItemInterviewMapper.queryMatchingPersonId(itemInterview);
	    try{	
		     if(count==0){
	             //当前所有已匹配的服务人员全部取消时，将订单状态改回匹配中
	             Order order = new Order();
	             order.setId(itemInterview.getOrderId());
	             order.setOrderStatus(3);
	             orderService.updateOrder2(order);			
		    }
	  }catch(Exception e){
		  e.printStackTrace();
	  }
		return count;
	    
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<ItemInterview> queryInterviews(ItemInterview itemInterview) {
		return this.reItemInterviewMapper.queryInterviews(itemInterview);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public ItemInterview checkRetuningJobsUp(ItemInterview itemInterview) {
		return this.reItemInterviewMapper.checkRetuningJobsUp(itemInterview);
	}

	@Override
	public ItemInterview selectConflictSchedule(ItemInterview itemInterview) {
		//查询当前订单服务人员排期
		List<ItemInterview> listSchedule=reItemInterviewMapper.selectSchedule(itemInterview);
		//查询排期冲突不冲突
		ItemInterview itemInterview1=null;
		if(listSchedule.size()>0){
			for (ItemInterview itemInterview2 : listSchedule) {
				String week = itemInterview2.getWeek();
				if(week != null){
					if(week.equals("0")){
						itemInterview2.setWeek("0,1,2,3,4,5,6,7");
					}else{
						itemInterview2.setWeek("0,"+week);
					}
				}
				itemInterview1=reItemInterviewMapper.selectConflictSchedule(itemInterview2);
				if(itemInterview1!=null){
					break;
				}
			}
		}
		return itemInterview1;
	}

	/**
	 * 修改服务人员状态
	 *
	 * @param status		人员状态
	 * @param personId		人员ID
	 */
	@Override
	public void updatePersonnalStatus(long status, Long personId) {
		wrOrderMapper.updatePersonnalStatus(status,personId);
	}

	
	
	/**
	 * 点击发送派工单按钮 验证是否订单已发送过派工单
	 * @author xyp
	 * 20180706
	 */
	
	
	@Override
	public Integer validateSendpies(ItemInterview itemInterview) {
		return reItemInterviewMapper.validateSendpies(itemInterview);
	}

	
	
	
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Map> queryShortRecode(Map map) {
		return this.reItemInterviewMapper.queryShortRecode(map);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public boolean checkOrderId(Map map) {
		Long orderId = (Long) map.get("orderId");
		List<Long> list = new ArrayList<>();
		if(list.contains(orderId)) {
			return true;
		}else {
			list.add(orderId);
			return false;
		}
	}



	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<ItemInterview> queryOrderStatus(Long orderId) {
		return this.reItemInterviewMapper.queryOrderStatus(orderId);
	}



	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public ItemInterview loadItemInterviewInfo(Long id) {
		return this.reItemInterviewMapper.loadItemInterviewInfo(id);
	}
public static void main(String[] args) {
	JSONObject json = new JSONObject();
	json.put("empId", 1);
	json.put("orderId", 1);
	json.put("startDate", 1);
	json.put("endDate", 1);
	json.put("startTime", 1);
	json.put("endTime", 1);
	json.put("status", 4);
	JSONArray jsonArr = new JSONArray();
	jsonArr.add(json);
	LogHelper.info(".insertItemInterview()","创建排期参数："+jsonArr.toString());
}
}





