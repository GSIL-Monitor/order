package com.emotte.order.order.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wildhorse.server.core.exception.BaseException;

import com.emotte.dubbo.manager.NewsService;
import com.emotte.order.order.mapper.reader.ReRecorderMapper;
import com.emotte.order.order.mapper.writer.WrRecorderMapper;
import com.emotte.order.order.model.Recorder;
import com.emotte.order.order.service.RecorderService;

@Service("recorderService")
@Transactional
public class RecorderServiceImpl implements RecorderService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private NewsService newsService;
		
	@Resource
	private ReRecorderMapper reRecorderMapper;

	@Resource
	private WrRecorderMapper wrRecorderMapper;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Recorder loadRecorder(Long id) {
		return this.reRecorderMapper.loadRecorder(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Recorder> queryRecorder(Recorder recorder) {
		return this.reRecorderMapper.queryRecorder(recorder);
	}

	@Override
	public void insertRecorder(Recorder recorder) {
		this.wrRecorderMapper.insertRecorder(recorder);
	}

	@Override
	public void updateRecorder(Recorder recorder) {
		int returnValue;
		try {
			returnValue = this.wrRecorderMapper.updateRecorder(recorder);
			if (1 != returnValue) {
				throw new BaseException("update fail!");
			}
		} catch (Exception e) {
			log.error("updateRecorder:" + e);
			throw new BaseException(e);
		}
	}
	@Override
	public void queryRecorderinfo(){
		
		List<Recorder> recorders = this.reRecorderMapper.queryRecorderInfo();
		//int count = reRecorderMapper.countRecorder(recorder);
		for (Recorder recorder2 : recorders) {
			System.err.println(recorder2);
			List<String> managerIds = new ArrayList<String>();
			managerIds.add(String.valueOf(recorder2.getIsread()));
			System.err.println(managerIds);
			newsService.addNews("您今天要回访这个订单,\n" + "订单编号：" + recorder2.getOrderCode() + "\n" + "订单类型"
					+ recorder2.getSatis(), managerIds, recorder2.getIsread());
		}
 	}

	}
