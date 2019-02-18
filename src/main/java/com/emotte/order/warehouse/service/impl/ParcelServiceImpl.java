package com.emotte.order.warehouse.service.impl;

import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wildhorse.server.core.exception.BaseException;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.dataUtils.DateUtil;

import com.emotte.dubbo.order.OrderInterfaceService;
import com.emotte.dubbo.wms.SubPackageService;
import com.emotte.order.warehouse.mapper.reader.ReDictionaryParcelMapper;
import com.emotte.order.warehouse.mapper.reader.ReOrderParcelMapper;
import com.emotte.order.warehouse.mapper.reader.RePackageItemRefMapper;
import com.emotte.order.warehouse.mapper.reader.ReParcelMapper;
import com.emotte.order.warehouse.mapper.writer.WrPackageItemRefMapper;
import com.emotte.order.warehouse.mapper.writer.WrParcelMapper;
import com.emotte.order.warehouse.mapper.writer.WrUploadLogMapper;
import com.emotte.order.warehouse.model.Dictionary;
import com.emotte.order.warehouse.model.Order;
import com.emotte.order.warehouse.model.PackageItemRef;
import com.emotte.order.warehouse.model.Parcel;
import com.emotte.order.warehouse.model.UploadLog;
import com.emotte.order.warehouse.service.ParcelService;

@Service("parcelService")
@Transactional
public class ParcelServiceImpl implements ParcelService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ReParcelMapper reParcelMapper;

	@Resource
	private WrParcelMapper wrParcelMapper;
	
	@Resource
	private RePackageItemRefMapper rePackageItemRefMapper;

	@Resource
	private WrPackageItemRefMapper wrPackageItemRefMapper;
	
	@Resource
	private WrUploadLogMapper wrUploadLogMapper;
	
	@Resource
	private ReDictionaryParcelMapper reDictionaryParcelMapper;
	
	@Resource
	private ReOrderParcelMapper reOrderParcelMapper;
	
	@Resource
	private OrderInterfaceService orderInterfaceService;
	@Resource
	private SubPackageService subPackageService;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Parcel loadParcel(Long id) {
		return this.reParcelMapper.loadParcel(id);
	}
	/**
	 * 查询包裹
	 * @author 王世博
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Parcel> queryParcel(Parcel parcel, Page page) {
		return this.reParcelMapper.queryParcellistPage(parcel);
	}
	/**
	 * 查询今天昨天前天新订单条数
	 * @author 王世博
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Parcel> queryNewOrderCount(Parcel parcel, Page page) {
		return this.reParcelMapper.queryNewOrderCount(parcel);
	}
	/**
	 * 查询今天昨天前天新订单条数
	 * @author 王世博
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Parcel> queryTobeshippedOrderCount(Parcel parcel, Page page) {
		return this.reParcelMapper.queryTobeshippedOrderCount(parcel);
	}
	/**
	 * 查询今天昨天前天待发货订单条数
	 * @author 王世博
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Parcel> queryAlreadyshippedOrderCount(Parcel parcel, Page page) {
		return this.reParcelMapper.queryAlreadyshippedOrderCount(parcel);
	}
	/**
	 * 发货查询详情
	 * @author 王世博
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Parcel> queryDeliveryReturn(Parcel parcel, Page page) {
		return this.reParcelMapper.queryDeliveryReturn(parcel);
	}
	/**
	 * 合并查询包裹
	 * @author 王世博
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Parcel> mergeQueryParcel(Parcel parcel, Page page) {
		return this.reParcelMapper.mergeQueryParcel(parcel);
	}
	/**
	 * 下单拆分包裹
	 * @author 王世博
	 */
	@Override
	public String insertOrderParcel(Long orderId) {
		String returnValue = "";
		Parcel parcel = new Parcel();
		parcel.setOrderId(orderId);
		List<Parcel> listparcel = this.reParcelMapper.queryParcelJudgment(parcel);
		if(listparcel.size() == 0){
			Order order = new Order();
			PackageItemRef packageItemRef = new PackageItemRef();
			order.setId(orderId);
			List<Order> list = this.reParcelMapper.queryOrderItem(order);
			for(int i=0; i<list.size(); i++){
				parcel.setOrderId(orderId);
				parcel.setState(1);
				parcel.setType(2);
				parcel.setValid(1);
				parcel.setVersion(1l);
				parcel.setCreateTime(DateUtil.getCurrDateTime());
				this.wrParcelMapper.insertParcel(parcel);
				String itemid = list.get(i).getItemiId();
				String[] itemidArray = itemid.split(",");
				for(int j=0; j<itemidArray.length; j++){
					packageItemRef.setOrderItemId(Long.valueOf(itemidArray[j]));
				 	packageItemRef.setPackageId(parcel.getId());
				 	packageItemRef.setVersion(1l);
				 	packageItemRef.setValid(1);
				 	packageItemRef.setCreateTime(DateUtil.getCurrDateTime());
				 	this.wrPackageItemRefMapper.insertPackageItemRef(packageItemRef);
				 }
			}
			returnValue = "success";
		}else{
			returnValue = "repeat";
		}
		return returnValue;
	}
	/**
	 * 拆分包裹
	 * @author 王世博
	 */
	@Override
	public void splitParcel(Parcel parcel,Long createBy) {
		PackageItemRef packageItemRef = new PackageItemRef();
		Parcel parcels = new Parcel();
		String[] itemId = parcel.getArrItemId().split("-");
		for(int i=0;i<itemId.length;i++){
			String[] idVer = itemId[i].split(",");
			parcels.setCreateBy(createBy);
			parcels.setCreateTime(DateUtil.getCurrDateTime());
			parcels.setValid(1);
			parcels.setWarehouseId(parcel.getWarehouseId());
			parcels.setVersion(1L);
			parcels.setState(1);
			parcels.setType(2);
			parcels.setOrderId(parcel.getOrderId());
			this.wrParcelMapper.insertParcel(parcels);
			for(int j=0;j<idVer.length;j++){
				packageItemRef.setUpdateBy(createBy);
				packageItemRef.setUpdateTime(DateUtil.getCurrDateTime());
				packageItemRef.setOrderItemId(Long.valueOf(idVer[j]));
				packageItemRef.setPackageId(parcels.getId());
				this.wrPackageItemRefMapper.splitParcelItemRef(packageItemRef);
			}
		}
	}
	/**
	 * 删除多余包裹
	 * @author 王世博
	 */
	@Override
	public void updateParcel(Parcel parcel ,Long updateBy) {
		int returnValue;
		int returnValue1;
		try {
			List<Parcel> list = this.reParcelMapper.mergeQueryParcel(parcel);//合并查询包裹
			for (int i = 0; i < list.size(); i++) {
				if(i != 0){
					//删除多余包裹
					Parcel parcel1 = new Parcel();
					parcel1.setId(list.get(i).getId());
					parcel1.setValid(2);
					parcel1.setVersion(list.get(i).getVersion());
					parcel1.setUpdateBy(updateBy);
					parcel1.setUpdateTime(DateUtil.getCurrDateTime());
					returnValue = this.wrParcelMapper.updateParcel(parcel1);
					if (1 != returnValue) {
						throw new BaseException("updateParcel fail!");
					}
					//查询多余包裹关联商品
					PackageItemRef packageItemRef = new PackageItemRef();
					packageItemRef.setPackageId(list.get(i).getId());
					List<PackageItemRef> listItemRef = this.rePackageItemRefMapper.queryPackageItemRef(packageItemRef);
					//多余包裹关联商品,包裹id变合并的id
					for (int j = 0; j < listItemRef.size(); j++) {
						packageItemRef.setId(listItemRef.get(j).getId());
						System.out.println(list.get(0).getId());
						packageItemRef.setPackageId(list.get(0).getId());
						packageItemRef.setVersion(listItemRef.get(j).getVersion());
						packageItemRef.setUpdateBy(updateBy);
						packageItemRef.setUpdateTime(DateUtil.getCurrDateTime());
						returnValue1 = this.wrPackageItemRefMapper.updatePackageItemRef(packageItemRef);
						if (1 != returnValue1) {
							throw new BaseException("updatePackageItemRef fail!");
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("updateParcel:" + e);
			throw new BaseException(e);
		}
	}
	/**
	 * 发货
	 * @author 王世博
	 */
	@Override
	public void updateDelivergoods(Parcel parcel ,Long updateBy) {
		int returnValue;
		try {
			returnValue = this.wrParcelMapper.updateParcel(parcel);
			UploadLog uploadLog = new UploadLog();
			uploadLog.setPackageId(parcel.getId());
			uploadLog.setState(3);
			uploadLog.setCreateBy(updateBy);
			uploadLog.setCreateTime(DateUtil.getCurrDateTime());
			this.wrUploadLogMapper.insertUploadLog(uploadLog);
			Dictionary dictionary = new Dictionary();
			dictionary.setDictCode(parcel.getDictaryCode()+"");
			List<Dictionary> list = this.reDictionaryParcelMapper.queryDictionary(dictionary);
			Dictionary dict = list.get(0);
			String logisticsCompany = dict.getRemark();
			String LogisticsNumber = parcel.getLogisticsNumber()+"";
			String result = this.subPackageService.subscription(logisticsCompany, LogisticsNumber);
			System.out.println(result);
			Long orderid = parcel.getOrder().getId();
			//查看订单是否已完成，已完成不掉接口
			Order order = new Order();
			order.setId(orderid);
			List<Order> list1 = this.reOrderParcelMapper.selectOrderComplete(order);
			if(list1.get(0).getOrderStatus() != 9){
				String type = "{'handle':'2','order':[{'id':'"+ orderid +"','orderStatus':'14'}]}";
				this.orderInterfaceService.insertOrUpdateOrder(type);
			}
			if (1 != returnValue) {
				throw new BaseException("update fail!");
			}
		} catch (Exception e) {
			log.error("updateParcel:" + e);
			throw new BaseException(e);
		}
	}
	/**
	 *查询包裹详情
	 * @author 王世博
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Parcel> queryParcelDetails(Parcel parcel, Page page) {
		return this.reParcelMapper.queryParcelDetails(parcel);
	}
	/**
	 * 查询包裹物流
	 * @author 王世博
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Parcel> queryParcelLogistics(Parcel parcel, Page page) {
		return this.reParcelMapper.queryParcelLogistics(parcel);
	}
	/**
	 * 导出前查询
	 * @author 王世博
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Parcel> queryExcelList(Parcel parcel) {
		return this.reParcelMapper.queryExcelList(parcel);
	}
	/**
	 * 导出
	 */
	@Override
	public Workbook queryExcel(HttpServletRequest request, HttpServletResponse response,List<Parcel> list,Long updateBy) {
		Parcel parcel = new Parcel();
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
			//循环创建sheet，看有几种物流，同时创建表头
			for(int i=0;i<list.size()+1;i++){
				sheet.createRow(i);
				for(int j=0;j<=20;j++){
					sheet.getRow(i).createCell(j);
				}
			}
			sheet.getRow(0).getCell(0).setCellValue("序号");
			sheet.getRow(0).getCell(1).setCellValue("订单号");
			sheet.getRow(0).getCell(2).setCellValue("包裹号");
			sheet.getRow(0).getCell(3).setCellValue("收货人姓名");
			sheet.getRow(0).getCell(4).setCellValue("收货人电话");
			sheet.getRow(0).getCell(5).setCellValue("下单电话");
			sheet.getRow(0).getCell(6).setCellValue("收货地址");
			sheet.getRow(0).getCell(7).setCellValue("下单日期");
			sheet.getRow(0).getCell(8).setCellValue("配送日期");
			sheet.getRow(0).getCell(9).setCellValue("物流");
			sheet.getRow(0).getCell(10).setCellValue("商品/数量");
			sheet.getRow(0).getCell(11).setCellValue("订单来源");
			sheet.getRow(0).getCell(12).setCellValue("用户备注");
			//ExportPurchase exportPurchase = new ExportPurchase();
			Long batch = System.currentTimeMillis();
			//循环包裹数据
			for (int i = 0,j=1; i < list.size(); i++,j++) {
					sheet.setColumnWidth(0, 2000);//改变表格的长度
					sheet.getRow(j).getCell(0).setCellValue(j);
					sheet.getRow(j).getCell(1).setCellType(1);
					sheet.setColumnWidth(1, 8000);
					sheet.getRow(j).getCell(1).setCellValue(list.get(i).getOrder().getOrderCode());
					sheet.getRow(j).getCell(2).setCellType(1);
					sheet.setColumnWidth(2, 8000);
					sheet.getRow(j).getCell(2).setCellValue(list.get(i).getPackageNumber()+"");
					sheet.setColumnWidth(3, 5000);
					sheet.getRow(j).getCell(3).setCellValue(list.get(i).getOrder().getReceiverName());
					sheet.getRow(j).getCell(4).setCellType(1);
					sheet.setColumnWidth(4, 5000);
					sheet.getRow(j).getCell(4).setCellValue(list.get(i).getOrder().getReceiverMobile());
					sheet.getRow(j).getCell(5).setCellType(1);
					sheet.setColumnWidth(5, 5000);
					sheet.getRow(j).getCell(5).setCellValue(list.get(i).getOrder().getUserMobile());
					sheet.setColumnWidth(6, 10000);
					sheet.getRow(j).getCell(6).setCellValue(list.get(i).getOrder().getReceiverProvince()
							+list.get(i).getOrder().getReceiverCity()
							+list.get(i).getOrder().getReceiverArea()
							+list.get(i).getOrder().getReceiverAddress());
					sheet.setColumnWidth(7, 5000);
					sheet.getRow(j).getCell(7).setCellValue(list.get(i).getOrder().getCreateTime());
					sheet.setColumnWidth(8, 5000);
					sheet.getRow(j).getCell(8).setCellValue(list.get(i).getOrder().getReceiptTime());
					sheet.setColumnWidth(9, 5000);
					if(list.get(i).getDictName() == null){
						sheet.getRow(j).getCell(9).setCellValue("");
					}else if(list.get(i).getDictName() != null){
						sheet.getRow(j).getCell(9).setCellValue(list.get(i).getDictName()+"("+list.get(i).getDictCode()+")");
					}
					sheet.setColumnWidth(10, 10000);
					sheet.getRow(j).getCell(10).setCellValue(list.get(i).getItem().getProductName());
					sheet.setColumnWidth(11, 5000);
					sheet.getRow(j).getCell(11).setCellValue(list.get(i).getOrderSourceName());
					sheet.setColumnWidth(12, 15000);
					sheet.getRow(j).getCell(12).setCellValue(list.get(i).getOrder().getRemark());
					if(list.get(i).getState() == 1){
						parcel.setState(2);
						parcel.setValid(list.get(i).getValid());
						parcel.setVersion(list.get(i).getVersion());
						parcel.setUpdateTime(DateUtil.getCurrDateTime());
						parcel.setUpdateBy(updateBy);
						parcel.setId(list.get(i).getId());
						this.wrParcelMapper.updateParcel(parcel);
						String code = list.get(i).getOrder().getOrderCode();
						Order order = new Order();
						order.setOrderCode(code);
						List<Order> list9 = this.reOrderParcelMapper.OrderCode(order);
						//查看订单是否已完成，已完成不掉接口
						//order.setId(list9.get(0).getId());
						//List<Order> list1 = this.reOrderMapper.selectOrderComplete(order);
						if(list9.get(0).getOrderStatus() == 2){
							String type = "{'handle':'2','order':[{'id':'"+ list9.get(0).getId() +"','orderStatus':'13'}]}";
							this.orderInterfaceService.insertOrUpdateOrder(type);
						}
					}
					/*exportPurchase.setPackageId(list.get(i).getId());
					exportPurchase.setExportBy(updateBy);
					exportPurchase.setExportTime(DateUtil.getCurrDateTime());
					exportPurchase.setBatch(batch);
					this.wrExportPurchaseMapper.insertExportPurchase(exportPurchase);*/
			}
			return workbook;
	}
	/**
	 * 导入
	 * @author 王世博
	 */
	@Override
	public boolean importCustomExcel(HSSFWorkbook excel,Long updateBy) {
		HSSFRow row;  
        HSSFSheet sheet = excel.getSheetAt(0); 
        int rows = sheet.getLastRowNum();
		for(int i = 1;i<=rows;i++){
			System.out.println(rows);
			UploadLog uploadLog = new UploadLog();
			row = sheet.getRow(i);
			Parcel parcel = new Parcel();
			Order order = new Order();
			DecimalFormat dff = new DecimalFormat("0");  
		   /* String packageNumber = dff.format(row.getCell(0).getNumericCellValue());
		    Long packageNumberLong = Long.valueOf(packageNumber);*/
			String receiverName = String.valueOf(row.getCell(1));
			Long packageNumber = (Long.parseLong(row.getCell(0)+""));
			order.setReceiverName(receiverName);
			parcel.setPackageNumber(packageNumber);
			parcel.setOrder(order);
			List<Parcel> list  = this.reParcelMapper.queryPackageNumberExistence(parcel);
			if(list.size() == 0){
			    /*String PackageId = dff.format(row.getCell(0).getNumericCellValue());
			    Long PackageIdLong = Long.valueOf(PackageId);*/
				uploadLog.setPackageId((Long.parseLong(row.getCell(0)+"")));
				uploadLog.setReason("没有这个包裹号："+(Long.parseLong(row.getCell(0)+"")));
				uploadLog.setCreateBy(updateBy);
				uploadLog.setState(5);
				uploadLog.setType(2);
				uploadLog.setCreateTime(DateUtil.getCurrDateTime());
				this.wrUploadLogMapper.insertUploadLog(uploadLog);
			}else{
				Parcel parcel1 = list.get(0);
				if(parcel1.getState() == 1){
					uploadLog.setPackageId(parcel1.getId());
					uploadLog.setState(1);
					uploadLog.setReason("此包裹还没导出");
					uploadLog.setType(2);
					uploadLog.setCreateBy(updateBy);
					uploadLog.setCreateTime(DateUtil.getCurrDateTime());
					this.wrUploadLogMapper.insertUploadLog(uploadLog);
				}else if(parcel1.getState() == 4){
					uploadLog.setPackageId(parcel1.getId());
					uploadLog.setState(4);
					uploadLog.setReason("此包裹已完成");
					uploadLog.setType(2);
					uploadLog.setCreateBy(updateBy);
					uploadLog.setCreateTime(DateUtil.getCurrDateTime());
					this.wrUploadLogMapper.insertUploadLog(uploadLog);
				}else if(parcel1.getState() == 3){
					uploadLog.setPackageId(parcel1.getId());
					uploadLog.setReason("此包裹已发货");
					uploadLog.setState(3);
					uploadLog.setType(2);
					uploadLog.setCreateBy(updateBy);
					uploadLog.setCreateTime(DateUtil.getCurrDateTime());
					this.wrUploadLogMapper.insertUploadLog(uploadLog);
				}else if(parcel1.getState() == 2){
					Dictionary dictionary = new Dictionary();
					//Long logisticsNumber = (Long.parseLong(row.getCell(0)+""));
					//Parcel parce2 = new Parcel();
					//parce2.setLogisticsNumber(logisticsNumber);
					//List<Parcel> list1  = this.reParcelMapper.queryPackagelogisticsNumber(parce2);
					//if(list1.size() == 0){
					parcel.setPackageNumber(Long.parseLong(row.getCell(0)+""));
				    String dictName = row.getCell(2)+"";
				    dictionary.setDictName(dictName);
				    List<Dictionary> listdict = this.reDictionaryParcelMapper.queryDictionary(dictionary);
				    if(listdict.size() == 0){
						uploadLog.setReason(row.getCell(2)+":没有这个快递");
						uploadLog.setCreateBy(updateBy);
						uploadLog.setType(2);
						uploadLog.setCreateTime(DateUtil.getCurrDateTime());
						this.wrUploadLogMapper.insertUploadLog(uploadLog);
				    }else{
				    	parcel.setDictaryCode(listdict.get(0).getDictCode());
						//Long logistics = (Long.parseLong(row.getCell(4)+""));
						//String logistics = dff.format(row.getCell(4).getNumericCellValue());
						//Long logisticsLong = Long.valueOf(logistics);
						int cellType = row.getCell(3).getCellType();
						Long logistics = null;
						switch(cellType) {
							case Cell.CELL_TYPE_NUMERIC:{
								String logisticsString = dff.format(row.getCell(3).getNumericCellValue());
								logistics = (Long.parseLong(logisticsString));
								break;
							}
							case Cell.CELL_TYPE_STRING:{
								logistics = Long.parseLong(row.getCell(3)+"");
								break;
							}
							default:{
								break;
							}
						}
						parcel.setLogisticsNumber(logistics);
						parcel.setState(3);
						parcel.setUpdateBy(updateBy);
						parcel.setUpdateTime(DateUtil.getCurrDateTime());
						this.wrParcelMapper.updateParcelLogistion(parcel);
						uploadLog.setPackageId(parcel1.getId());
						uploadLog.setState(3);
						uploadLog.setType(2);
						uploadLog.setReason("");
						uploadLog.setCreateBy(updateBy);
						uploadLog.setCreateTime(DateUtil.getCurrDateTime());
						this.wrUploadLogMapper.insertUploadLog(uploadLog);
						Long orderid = parcel1.getOrderId();
						//查看订单是否已完成，已完成不掉接口
						order.setId(orderid);
						List<Order> listOrder = this.reOrderParcelMapper.selectOrderComplete(order);
						if(listOrder.get(0).getOrderStatus() != 9){
							String type = "{'handle':'2','order':[{'id':'"+ orderid +"','orderStatus':'14'}]}";
							this.orderInterfaceService.insertOrUpdateOrder(type);
						}
						
						String logisticsCompany = listdict.get(0).getRemark();
						String LogisticsNumber = parcel.getLogisticsNumber()+"";
						String result;
						try {
							result = this.subPackageService.subscription(logisticsCompany, LogisticsNumber);
						} catch (Exception e) {
							e.printStackTrace();
						}
					/*}else{
						uploadLog.setPackageId(parcel1.getId());
						uploadLog.setReason("物流号已有");
						uploadLog.setState(2);
						uploadLog.setCreateBy(updateBy);
						uploadLog.setCreateTime(DateUtil.getCurrDateTime());
						this.wrUploadLogMapper.insertUploadLog(uploadLog);
					}*/
				    }
				}	
			}
		}
		return true;
	}
}
