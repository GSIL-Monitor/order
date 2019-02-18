package com.emotte.order.order.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wildhorse.server.core.model.Page;

import com.emotte.order.order.mapper.reader.ReThreePartyOrderMapper;
import com.emotte.order.order.model.ThreeOrder;
import com.emotte.order.order.service.ThreePartyOrderService;

@Service("ThreePartyOrderService")
@Transactional
public class PartyOrderServiceImpl  implements ThreePartyOrderService{

	
	
	@Resource
	private ReThreePartyOrderMapper reThreePartyOrderMapper;
	
	
	
	
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<ThreeOrder> queryPartyOrder(ThreeOrder order, Page page) {
		if (page.needQueryPading()) {
			page.setTotalRecord(reThreePartyOrderMapper.countPartyOrder(order));
		}
		order.setBeginRow(page.getFilterRecord().toString());
		order.setPageSize(page.getPageCount().toString());
		return this.reThreePartyOrderMapper.queryPartyOrder(order);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<ThreeOrder> loadPartyOrderId(ThreeOrder order) {
		// TODO Auto-generated method stub
		return reThreePartyOrderMapper.loadPartyOrderId(order);
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<ThreeOrder> queryPartyOrderDeptName(ThreeOrder order) {
		return reThreePartyOrderMapper.queryPartyOrderDeptName(order);
	}

	@Override
	public List<ThreeOrder> queryPartyOrderList(ThreeOrder order) {
		// TODO Auto-generated method stub
		return reThreePartyOrderMapper.queryPartyOrderList(order);
	}
	@Override
	/**
	 * 导出
	 */
	public Workbook queryExcel(HttpServletRequest request, HttpServletResponse response, ThreeOrder order,
			List<ThreeOrder> dataList, Long loginBy) {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("三方订单订单列表");
		/*// 设置标题样式
		CellStyle titleStyle = workbook.createCellStyle();
		// 设置单元格边框样式
		titleStyle.setBorderTop(CellStyle.BORDER_THIN);// 上边框 细边线
		titleStyle.setBorderBottom(CellStyle.BORDER_THIN);// 下边框 细边线
		titleStyle.setBorderLeft(CellStyle.BORDER_THIN);// 左边框 细边线
		titleStyle.setBorderRight(CellStyle.BORDER_THIN);// 右边框 细边线
		// 设置单元格对齐方式
		titleStyle.setAlignment(CellStyle.ALIGN_CENTER); // 水平居中
		titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直居中
		// 设置字体样式
		Font titleFont = workbook.createFont();
		titleFont.setFontHeightInPoints((short) 15); // 字体高度
		titleFont.setFontName("黑体"); // 字体样式
		titleStyle.setFont(titleFont);
		//设置自适应宽度
		sheet.autoSizeColumn(1); 
		sheet.autoSizeColumn(1, true);*/
		
		/**
		 * 创建工作区 长list.size()+1  （即数据长度+一行表头）  宽 10
		 */
		for(int i=0;i<dataList.size()+1;i++){
			sheet.createRow(i);
			for(int j=0;j<=20;j++){
				sheet.getRow(i).createCell(j);
			}
		}
        sheet.getRow(0).getCell(0).setCellValue("序号");
		sheet.getRow(0).getCell(1).setCellValue("*城市");
		sheet.getRow(0).getCell(2).setCellValue("*订单编号");
		sheet.getRow(0).getCell(3).setCellValue("*商品名称");
		sheet.getRow(0).getCell(4).setCellValue("*下单人姓名");
		sheet.getRow(0).getCell(5).setCellValue("*下单人电话");
		sheet.getRow(0).getCell(6).setCellValue("*接收人姓名");
		sheet.getRow(0).getCell(7).setCellValue("*接收人电话");
		sheet.getRow(0).getCell(8).setCellValue("*需求类型");
		sheet.getRow(0).getCell(9).setCellValue("*服务地址省份");
		sheet.getRow(0).getCell(10).setCellValue("*服务地址城市");
		sheet.getRow(0).getCell(11).setCellValue("*服务地址地区");
		sheet.getRow(0).getCell(12).setCellValue("*客户地址");
		sheet.getRow(0).getCell(13).setCellValue("*服务开始时间");
		sheet.getRow(0).getCell(14).setCellValue("*服务结束时间");
		sheet.getRow(0).getCell(15).setCellValue("*创建时间");
		sheet.getRow(0).getCell(16).setCellValue("*服务人员");
		sheet.getRow(0).getCell(17).setCellValue("*快递单号");
		sheet.getRow(0).getCell(18).setCellValue("*订单金额");
		sheet.getRow(0).getCell(19).setCellValue("*订单状态");
		sheet.getRow(0).getCell(20).setCellValue("备注");
		for (int i = 0,j=1; i < dataList.size(); i++,j++) {
			
			sheet.getRow(j).getCell(1).setCellValue(dataList.get(i).getCity()!=null ? dataList.get(i).getCity() : null);
			sheet.getRow(j).getCell(2).setCellValue(dataList.get(i).getOrderCode()!=null ? dataList.get(i).getOrderCode() : null);
			sheet.getRow(j).getCell(3).setCellValue(dataList.get(i).getProductName()!=null ? dataList.get(i).getProductName() : null);
			sheet.getRow(j).getCell(4).setCellValue(dataList.get(i).getUserName()!=null ? dataList.get(i).getUserName() : null);
			sheet.getRow(j).getCell(5).setCellValue(dataList.get(i).getUserMobile()!=null ? dataList.get(i).getUserMobile() : null);
			sheet.getRow(j).getCell(6).setCellValue(dataList.get(i).getReceiverName()!=null ? dataList.get(i).getReceiverName() : null);
			sheet.getRow(j).getCell(7).setCellValue(dataList.get(i).getReceiverMobile()!=null ? dataList.get(i).getReceiverMobile() : null);
			sheet.getRow(j).getCell(8).setCellValue(dataList.get(i).getTypeText()!=null ? dataList.get(i).getTypeText() : null);
			/*sheet.getRow(j).getCell(9).setCellValue(dataList.get(i).getCateType()!=null ? list.get(i).getRechargeDeptText() : null);
			sheet.getRow(j).getCell(10).setCellValue(dataList.get(i).getOrderType()!=null ? list.get(i).getStatusText() : null);*/
			sheet.getRow(j).getCell(9).setCellValue(dataList.get(i).getReceiverProvince()!=null ? dataList.get(i).getReceiverProvince() : null);
			sheet.getRow(j).getCell(10).setCellValue(dataList.get(i).getReceiverCity()!=null ? dataList.get(i).getReceiverCity() : null);
			sheet.getRow(j).getCell(11).setCellValue(dataList.get(i).getReceiverArea()!=null ? dataList.get(i).getReceiverArea() : null);
			sheet.getRow(j).getCell(12).setCellValue(dataList.get(i).getReceiverAddress()!=null ? dataList.get(i).getReceiverAddress() : null);
			sheet.getRow(j).getCell(13).setCellValue(dataList.get(i).getStartTime()!=null ? dataList.get(i).getStartTime() : null);
			sheet.getRow(j).getCell(14).setCellValue(dataList.get(i).getEndTime()!=null ? dataList.get(i).getEndTime() : null);
			sheet.getRow(j).getCell(15).setCellValue(dataList.get(i).getCreateTime()!=null ? dataList.get(i).getCreateTime() : null);
			sheet.getRow(j).getCell(16).setCellValue(dataList.get(i).getPersonName()!=null ? dataList.get(i).getPersonName() : null);
			sheet.getRow(j).getCell(17).setCellValue(dataList.get(i).getPostId()!=null ? dataList.get(i).getPostId() : null);
			sheet.getRow(j).getCell(18).setCellValue(dataList.get(i).getTotalPay()!=null ? dataList.get(i).getTotalPay() : null);
			sheet.getRow(j).getCell(19).setCellValue(dataList.get(i).getOrderStatus()!=null ? dataList.get(i).getOrderStatus() : null);
			sheet.getRow(j).getCell(20).setCellValue(dataList.get(i).getRemark()!=null ? dataList.get(i).getRemark() : null);
			sheet.setColumnWidth(1, 2000);
			sheet.setColumnWidth(2, 4000);
			sheet.setColumnWidth(3, 3000);
			sheet.setColumnWidth(4, 3000);
			sheet.setColumnWidth(5, 3000);
			sheet.setColumnWidth(6, 3000);
			sheet.setColumnWidth(7, 3000);
			sheet.setColumnWidth(8, 3000);
			sheet.setColumnWidth(9, 5000);
			sheet.setColumnWidth(10, 5000);
			sheet.setColumnWidth(11, 5000);
			sheet.setColumnWidth(12, 3000);
			sheet.setColumnWidth(13, 3000);
			sheet.setColumnWidth(14, 3000);
			sheet.setColumnWidth(15, 3000);
			sheet.setColumnWidth(16, 3000);
			sheet.setColumnWidth(17, 3000);
			sheet.setColumnWidth(18, 2000);
			sheet.setColumnWidth(19, 3000);
			sheet.setColumnWidth(20, 3000);
		}
		return workbook;
		
	}
}
