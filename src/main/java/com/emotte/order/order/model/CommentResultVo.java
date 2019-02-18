package com.emotte.order.order.model;

import java.io.Serializable;
import java.util.List;

import com.emotte.interfac.comment.model.Appraise;
import com.emotte.model.ResultData;

public class CommentResultVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;

	private ResultData<List<Appraise>> data;

	private String msg;

	private String status;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ResultData<List<Appraise>> getData() {
		return data;
	}

	public void setData(ResultData<List<Appraise>> data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
