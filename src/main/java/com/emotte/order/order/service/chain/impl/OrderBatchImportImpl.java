package com.emotte.order.order.service.chain.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.emotte.eserver.core.helper.PropertiesHelper;
import com.emotte.order.order.file.rw.IFileRWService;
import com.emotte.order.order.file.rw.impl.excel.ExcelServiceImpl;
import com.emotte.order.order.service.chain.TopBalanceChain;
import com.emotte.order.order.service.chain.model.ChainModel;

/**
 * 
 * @author weixd
 * @date 20180726
 */
public class OrderBatchImportImpl extends TopBalanceChain {

	// 模板名称--->对应 importTemplet.properties 文件中的 orderTemplet 标签
	private static final String TEMPLET_NAME = "orderTemplet";
	// excel表格数据非空列
	private static final String RESTRICTSTR = "平台订单号,客户手机号,客户姓名,区县,区县code,详细地址,订单渠道,订单渠道code,商品名,商品名code,备注,录入人erp账号,负责人erp账号";

	private String[] restricts = new String[]{"THIRD_PARTY_ORDER_ID", "ORDER_MONEY", "ORDER_FINISH_TIME"};

	@Override
	public List<Map<String, String>> balance(ChainModel model) {
		List<Map<String, List<Integer>>> listMap = new ArrayList<>();
		String channelName = model.getChannelName();
		List<Map<String, String>> data = model.getData();
		//业务处理
		return data;
	}


	@Override
	public boolean validate(ChainModel model) {
		List<Map<String, String>> data = null;
		Map<String, String> mapper = new HashMap<String, String>();
		String channelName = model.getChannelName();
		if (!StringUtils.equals(TEMPLET_NAME, channelName))
			return false;
		try {
			MultipartFile file = model.getFile();
			String json = PropertiesHelper.getValue("props/importTemplet.properties", TEMPLET_NAME);
			mapper = JSON.parseObject(json, mapper.getClass());
			IFileRWService<List<Map<String, String>>, List<Map<String, Object>>> excelService = new ExcelServiceImpl();
			data = excelService.readContent(file.getInputStream(), file.getOriginalFilename(), 0, 1, "#", mapper);
			model.setTitleNum(0);
			// 数据验证
			if (null == data || data.isEmpty())
				throw new Exception("数据为空，请确认模板是否正确");
			//属性校验
			propValidate(data.get(0));
			model.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean propValidate(Map<String, String> data) throws Exception {
		for (String restrict : restricts) {
			String val = data.get(restrict);
			if (null == val) throw new Exception("模板不正确，请确认是否包含" + RESTRICTSTR);
		}
		return super.propValidate(data);
	}

}
