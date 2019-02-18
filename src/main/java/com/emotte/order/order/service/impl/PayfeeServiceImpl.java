package com.emotte.order.order.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.emotte.order.order.mapper.reader.ReItemMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wildhorse.server.core.exception.BaseException;
import org.wildhorse.server.core.model.Page;

import com.emotte.kernel.helper.LogHelper;
import com.emotte.kernel.third.kafka.KafkaFactory;
import com.emotte.order.order.mapper.reader.ReOrderMapper;
import com.emotte.order.order.mapper.reader.RePayfeeMapper;
import com.emotte.order.order.mapper.writer.WrOrderMapper;
import com.emotte.order.order.mapper.writer.WrPayfeeMapper;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.Payfee;
import com.emotte.order.order.service.OrderService;
import com.emotte.order.order.service.PayfeeService;
import com.emotte.order.util.DESedeUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("payfeeService")
@Transactional
public class PayfeeServiceImpl implements PayfeeService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private RePayfeeMapper rePayfeeMapper;

	@Resource
	private WrPayfeeMapper wrPayfeeMapper;
	
	@Resource
	private WrOrderMapper wrOrderMapper;
	
	@Resource
	private OrderService orderService;

	@Resource
	private ReOrderMapper reOrderMapper;

	@Resource
	private ReItemMapper reItemMapper;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Payfee loadPayfee(Long id) {
		return this.rePayfeeMapper.loadPayfee(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Payfee> queryPayfee(Payfee payfee, Page page) {
		if (page.needQueryPading()) {
			page.setTotalRecord(rePayfeeMapper.countPayfee(payfee));
		}
		payfee.setBeginRow(page.getFilterRecord().toString());
		payfee.setPageSize(page.getPageCount().toString());
		return this.rePayfeeMapper.queryPayfee(payfee);
	}

	@Override
	public void insertPayfee(Payfee payfee) {
		this.wrPayfeeMapper.insertPayfee(payfee);
	}
	
	@Override
	public void insertPayfeeList(String data, Long accountId, Long createBy, Long orderId, int cateType) {
		JSONArray array = JSONArray.fromObject(data);  
		Object[] obj = new Object[array.size()];
		List<String> idList=new ArrayList<>();//所有缴费id
		List<String> dsIdList=new ArrayList<>();//他人代收缴费id
        for(int i = 0; i < array.size(); i++){  
             JSONObject JB = array.getJSONObject(i);
           //将建json对象转换为Object对象
            obj[i] = JSONObject.toBean(JB, Payfee.class);  
        }
        Payfee pf = this.rePayfeeMapper.queryPayCreateTime(accountId);//修改缴费时,获取原缴费创建日期,赋给新增的缴费
        this.wrPayfeeMapper.updatePayfeeByAccountId(accountId);
        for (Object object : obj) {
        	Payfee payfee = new Payfee();
        	payfee = (Payfee)object;
        	payfee.setCreateBy(createBy);
        	payfee.setIsManualFee(1);//后台为手动
        	if(pf != null){
        		//修改缴费时,创建日期取原缴费创建日期(为不影响收入统计)
        		payfee.setCreateTime(pf.getCreateTime());
        		payfee.setUpdateTime(pf.getCreateTime());
        	}
        	if (payfee.getVipShopName() != null) {
				String wphName = DESedeUtil.encryptMode(payfee.getVipShopName(), "fed58f8f8bc11129ed584987c773c671");
				payfee.setWphName(wphName);//唯品会加密姓名
			}
        	payfee.setOrderId(accountId);
			this.wrPayfeeMapper.insertPayfee(payfee);
			idList.add(String.valueOf(payfee.getId()));
			//如果是他人代收，更新结算单状态为已支付
			if (payfee.getFeePost() == 20250014) {
				dsIdList.add(String.valueOf(payfee.getId()));//他人代收缴费集合
				Payfee account = new Payfee();
				account.setId(payfee.getOrderId());
				account.setPayStatus(20110003);//结算单状态为已支付
				this.wrPayfeeMapper.updateAccount(account);
			}
		}
        
        //更新订单支付状态
        Order orderRelation = reOrderMapper.loadOrder(orderId);
        Order order = new Order();
        order.setId(orderId);
        if(cateType == 3){
        	order.setPayStatus("20110003");
        }else{
        	if(cateType == 1){
        		if(orderRelation.getOrderStatus()==11){
        			order.setPayStatus("20110003");
        		}else{
        			order.setPayStatus("20110002");
        		}
        	}else{
        		order.setPayStatus("20110002");
        	};
        }
		orderService.updateOrder2(order);
		
		
		//关于客户购买券商品,生成缴费,给客户绑定券,他人代收类型
		try {
			for (String id : dsIdList) {
					LogHelper.info(getClass()+".insertPayfeeList()","放入kafka中的值:finance-payfee参数"+ id);
					KafkaFactory.getProducerHandler().produce("finance-payfee","",id);
					LogHelper.info(getClass()+".insertPayfeeList()","放入kafka中的值:finance-payfee"+ id +"成功");
			}
		} catch (Exception e) {
			LogHelper.error(getClass()+".insertPayfeeList().finance-payfee",e.getMessage());
		}
		
		//关于联动
		try {
			for (String id : idList) {
				LogHelper.info(getClass()+".insertPayfeeList()","放入kafka中的值:t_order_payfee参数"+ id);
				KafkaFactory.getProducerHandler().produce("t_order_payfee","",id);
				LogHelper.info(getClass()+".insertPayfeeList()","放入kafka中的值:t_order_payfee"+ id +"成功");
			}
		} catch (Exception e) {
			LogHelper.error(getClass()+".insertPayfeeList().t_order_payfee",e.getMessage());
		}
	}

	@Override
	public void updatePayfee(Payfee payfee) {
		int returnValue;
		try {
			returnValue = this.wrPayfeeMapper.updatePayfee(payfee);
			if (1 != returnValue) {
				throw new BaseException("update fail!");
			}
		} catch (Exception e) {
			log.error("updatePayfee:" + e);
			throw new BaseException(e);
		}
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Payfee loadAccount(Long id) {
		return this.rePayfeeMapper.loadAccount(id);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Payfee> queryAccount(Payfee payfee) {
		return this.rePayfeeMapper.queryAccount(payfee);
	}
	
	@Override
	public void insertAccount(Payfee payfee,String jsonStr) {
		Long createBy = payfee.getCreateBy();
		if(jsonStr != null && jsonStr != ""){
			JSONObject accountJson=JSONObject.fromObject(jsonStr);
			Object obj = JSONObject.toBean(accountJson, Payfee.class);
		    payfee = (Payfee)obj;
		}
		payfee.setCreateBy(createBy);
		payfee.setValid(1);
		payfee.setVersion(1L);
		//根据订单ID查询商品市场价汇总信息
		Long orderId = payfee.getOrderId();
		BigDecimal marketPrice = reItemMapper.findMarketPriceByOrderId(orderId);
		payfee.setMarketPrice(marketPrice);
		this.wrPayfeeMapper.insertAccount(payfee);
	}

	@Override
	public void updateAccount(Payfee payfee) {
		int returnValue;
		try {
			//缴费类型为他人代收，结算单结算状态置为未结算，以便添加新的缴费信息
			if(payfee.getFeePost() != null && payfee.getFeePost() == 20250014){
				payfee.setPayStatus(20110001);
			}
			//根据订单ID查询商品市场价汇总信息
			Long orderId = payfee.getOrderId();
			BigDecimal marketPrice = reItemMapper.findMarketPriceByOrderId(orderId);
			payfee.setMarketPrice(marketPrice);
			returnValue = this.wrPayfeeMapper.updateAccount(payfee);
			// 如果结算单金额修改前后不等，需要删除对应的缴费信息
			if(payfee.getAccountStatus()!=null && payfee.getAccountStatus()==1){
				this.wrPayfeeMapper.updatePayfeeByAccountId(payfee.getId());
			}
			//是否是修改预估，不是则将缴费置为无效
			if(payfee.getIsYg() == null || payfee.getIsYg() == "" || !"1".equals(payfee.getIsYg()) ){
				this.wrPayfeeMapper.updatePayfeeByAccountId(payfee.getId());
			}
			if (1 != returnValue) {
				throw new BaseException("update fail!");
			}
		} catch (Exception e) {
			log.error("updateAccount:" + e);
			throw new BaseException(e);
		}
	}
	
	@Override
	public List<Payfee> queryPayfeeLpk(Payfee payfee){
		return this.rePayfeeMapper.queryPayfeeLpk(payfee);
	}

	@Override
	public int countPayfee(Payfee payfee) {
		return this.rePayfeeMapper.countPayfee(payfee);
	}

	@Override
	public String loadPayfeeMinCreTime(Long accountId) {
		return this.rePayfeeMapper.loadPayfeeMinCreTime(accountId);
	}

	@Override
	public Payfee loadIOUsByUserId(Long userId) {
		return this.rePayfeeMapper.loadIOUsByUserId(userId);
	}

	@Override
	public BigDecimal queryPayfeeByType(Map<String, Object> map) {
		return this.rePayfeeMapper.queryPayfeeByType(map);
	}

	@Override
	public List<Payfee> queryPosCheck(Payfee payfee, Page page) {
		if (page.needQueryPading()) {
			page.setTotalRecord(rePayfeeMapper.countPayfee(payfee));
		}
		payfee.setBeginRow(page.getFilterRecord().toString());
		payfee.setPageSize(page.getPageCount().toString());
		return this.rePayfeeMapper.queryPosChecklistPage(payfee);
	}


	// 获取一个毫秒数作为一个名称（本地任务）
	public String getSS(){
		Date d = new Date();
		String s = d.getTime()+"";
		s = s.substring(s.length()-4, s.length());
		return s;
	}
	
	// 下载文件  要被下载的文件的绝对路径
	public void downFile(String realPath,String fileName,HttpServletResponse response) throws Exception{
			response.reset(); 
			response.setCharacterEncoding("utf-8");
	        response.setContentType("multipart/form-data");
	        response.addHeader("Content-Disposition", "attachment; filename="+fileName);
	        response.setContentType("application/octet-stream");
			realPath = realPath.replaceAll("\\\\", "/"); 
			String allPath = realPath + fileName;
			File f = new File(allPath);
			FileInputStream  inputStream  = new FileInputStream(f);
			ServletOutputStream  out = response.getOutputStream();
			byte[] buffer = new byte[2048];
			int iLength = 0;
			while ((iLength = inputStream.read(buffer)) != -1) {
				out.write(buffer,0,iLength);
			}
			out.flush();
			out.close();
			inputStream.close();
		}
	/**  
     * 从URL中读取图片,转换成流形式.  
     * @param destUrl  
     * @return  
     */    
    public static InputStream getInputStreamFromURL(String destUrl){    
        HttpURLConnection httpUrl = null;    
        URL url = null;    
        InputStream in = null;     
        try{    
            url = new URL(destUrl);    
            httpUrl = (HttpURLConnection) url.openConnection();    
            httpUrl.connect();               
            in = httpUrl.getInputStream();              
            return in;    
        }catch (Exception e) {    
            e.printStackTrace();    
        }    
        return null;    
    }

	@Override
	public List<Map<String, Object>> queryPosCheckExcel(Payfee payfee) {
		return this.rePayfeeMapper.queryPosCheckExcel(payfee);
	}

	@Override
	public Integer queryUncheck(Payfee payfee) {
		return this.rePayfeeMapper.queryUncheck(payfee);
	}

	@Override
	public List<Payfee> queryOtherDeal(Payfee payfee) {
		return this.rePayfeeMapper.queryOtherDeal(payfee);
	}

	@Override
	public void updateOtherDeal(Payfee payfee) {
		int returnValue;
		try {
			returnValue = this.wrPayfeeMapper.updateOtherDeal(payfee);
			this.wrPayfeeMapper.updateOtherRevenue(payfee);
			if (1 != returnValue) {
				throw new BaseException("update fail!");
			}
		} catch (Exception e) {
			log.error("updateOtherDeal:" + e);
			throw new BaseException(e);
		}		
	}

	@Override
	public List<Map<String, Object>> queryPosCheckImg(Payfee payfee) {
		return this.rePayfeeMapper.queryPosCheck(payfee);
	}

	@Override
	public Long queryChannel(Long orderId) {
		Long channelId=null;
		try {
			channelId=this.rePayfeeMapper.queryChannel(orderId);
			if(null==channelId){
				throw new BaseException("select fail!");
			}
		} catch (Exception e) {
			log.error("updateOtherDeal:" + e);
			throw new BaseException(e);
		}
		return channelId;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Payfee> queryAccountList(Payfee payfee) {
		return this.rePayfeeMapper.queryAccountList(payfee);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Double queryPayfeeDetail(Payfee payfee) {
		return this.rePayfeeMapper.queryPayfeeDetail(payfee);
	}

	@Override
	public Long queryTypeByParentId(Long parentId) {
		return this.rePayfeeMapper.queryTypeByParentId(parentId);
	}

	/**
	 * 查询订单缴费是否大于1000
	 *
	 * @param orderId
	 * @return
	 * @Author zhanghao
	 * @Date 20180927
	 */
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	@Override
	public boolean checkAccountTotal(Long orderId) {
		BigDecimal totalPay = reOrderMapper.checkAccountTotal(orderId);
		if(totalPay.compareTo(new BigDecimal("1000")) < 0){
			return false;
		}
		return true;
	}

	/**
	 * 根据结算单ID校验财务系统汇总表信息
	 *
	 * @param accountId     结算单ID
	 * @Author zhanghao
	 * @Date 20181031
	 */
	@Transactional(readOnly = true)
	@Override
	public boolean checkFinSummaryForAccount(Long accountId) {
		//根据结算单ID查询汇总表中记录
		int count = rePayfeeMapper.checkFinSummaryForAccount(accountId);
		//有记录不允许修改，无记录可以修改结算单
		if(count == 0){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 根据结算单ID ,查询审核状态
	 */
	@Transactional(readOnly = true)
	@Override
	public boolean checkAccountReviewState(Long accountId) {
		//根据结算单ID查询汇总表中记录
		int count = rePayfeeMapper.checkAccountReviewState(accountId);
		//有记录不允许修改，无记录可以修改结算单
		if(count == 0){
			return true;
		}
		return false;
	}
	/**
	 * 查询售后费用
	 */
	@Override
	public Double getHistoryAfterCharge(Long orderId) {
		Double result=rePayfeeMapper.getHistoryAfterCharge(orderId);
		if (null==result) {
			result=0.0;
		}
		return result;
	}	
	/**
	 * 修改结算单
	 */
	@Override
	public void updateAccount(Long accountId) {
		this.wrPayfeeMapper.updateAccountType(accountId);
	}
}