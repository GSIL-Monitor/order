package com.emotte.dubbo.model;

import java.io.Serializable;

public class DubboModel implements Serializable {
	/**
	 * TODO
	 * 2017年1月13日
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 成功
	 * 2017年1月13日
	 */
	public static final int FLAG_SUCCESS = 1;
	/**
	 * 失败
	 * 2017年1月13日
	 */
	public static final int FLAG_FAIL = 0;
	private int flag;
	private String msg;
	private String data;
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "DubboModel [flag=" + flag + ", msg=" + msg + ", data=" + data
				+ "]";
	}
	
}
