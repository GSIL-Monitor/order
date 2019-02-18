package com.emotte.order.order.service.chain.model;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public class ChainModel {

	private String channelName;
	private MultipartFile file;
	private List<Map<String, String>> data;
	private String redundancyField;
	private Integer titleNum;
	private String batchNum;
	
	
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public List<Map<String, String>> getData() {
		return data;
	}
	public void setData(List<Map<String, String>> data) {
		this.data = data;
	}
	public String getRedundancyField() {
		return redundancyField;
	}
	public void setRedundancyField(String redundancyField) {
		this.redundancyField = redundancyField;
	}
	public Integer getTitleNum() {
		return titleNum;
	}
	public void setTitleNum(Integer titleNum) {
		this.titleNum = titleNum;
	}
	public String getBatchNum() {
		return batchNum;
	}
	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
	
	
}
