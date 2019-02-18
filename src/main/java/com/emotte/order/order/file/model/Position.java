package com.emotte.order.order.file.model;

/**
 * 记录位置信息
 * @author ChengJing
 * 2014年7月10日
 */
public class Position {
	private int abscissa,ordinate; // 横、纵坐标
	
	public Position(int abscissa, int ordinate) {
		this.abscissa = abscissa;
		this.ordinate = ordinate;
	}

	/**
	 * 获得横坐标
	 * @return int
	 * 2014年7月10日
	 */
	public int getAbscissa() {
		return abscissa;
	}

	/**
	 * 设置横坐标
	 * @param abscissa
	 * 2014年7月10日
	 */
	public void setAbscissa(int abscissa) {
		this.abscissa = abscissa;
	}

	/**
	 * 获得纵坐标
	 * @return int
	 * 2014年7月10日
	 */
	public int getOrdinate() {
		return ordinate;
	}

	/**
	 * 设置纵坐标
	 * @param ordinate
	 * 2014年7月10日
	 */
	public void setOrdinate(int ordinate) {
		this.ordinate = ordinate;
	}
	@Override
	public String toString() {
		return "(" + this.getAbscissa() +","+ this.getOrdinate() +")";
	}
}
