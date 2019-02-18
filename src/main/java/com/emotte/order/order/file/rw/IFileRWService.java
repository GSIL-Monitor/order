package com.emotte.order.order.file.rw;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.emotte.order.order.file.model.Position;


/**
 * 读写文件接口
 * @author ChengJing
 * 2015年5月12日
 * @param <T>
 * @param <D>
 */
public interface IFileRWService<T, D> {
	/**
	 * 读取文件内容到T
	 * @param filePath
	 * @return
	 * 2014年7月24日
	 */
	public T readContent(String filePath);
	
	/**
	 * 读取文件内容
	 * @param filePath
	 * @param mapper 标题映射文件，<p>用于excel标题到数据库表字段的映射，如{"交易金额":"deal_money", "交易日期":"fee_to_date"}</p>
	 * @return
	 * 2016年7月21日
	 */
	public T readContent(String filePath, Map<String, String> mapper);
	
	/**
	 * 读取文件内容
	 * @param stream 文件流
	 * @param fileName 文件名称
	 * @return
	 * 2016年7月20日
	 */
	public T readContent(InputStream stream, String fileName);
	
	/**
	 * 读取文件内容
	 * @param stream 文件流
	 * @param fileName 文件名称
	 * @param mapper 标题映射文件，<p>用于excel标题到数据库表字段的映射，如{"交易金额":"deal_money", "交易日期":"fee_to_date"}</p>
	 * @return
	 * 2016年7月21日
	 */
	public T readContent(InputStream stream, String fileName, Map<String, String> mapper);
	
	/**
	 * 读取文件内容
	 * @param stream 文件流
	 * @param fileName 文件名称
	 * @param titleRow 标题所在行 (从0开始)
	 * @param recordStartRow 记录开始行 (从0开始)
	 * @param endFlag 记录结束标记（放在第一列）
	 * @param mapper 标题映射文件，<p>用于excel标题到数据库表字段的映射，如{"交易金额":"deal_money", "交易日期":"fee_to_date"}</p>
	 * @return
	 * 2016年8月2日
	 */
	public T readContent(InputStream stream, String fileName, Integer titleRow, Integer recordStartRow, String endFlag, Map<String, String> mapper);
	
	/**
	 * 读取文件内容
	 * @param stream 文件流
	 * @param fileName 文件名称
	 * @param recordStartRow 记录开始行 (从0开始)
	 * @param endFlag 记录结束标记（放在第一列）
	 * @param mapper 标题映射文件，<p>用于excel标题到数据库表字段的映射，如{"交易金额":"deal_money", "交易日期":"fee_to_date"}</p>
	 * @return
	 * 2016年8月2日
	 */
	public T readContentByCol(InputStream stream, String fileName, Integer recordStartRow, String endFlag, Map<String, String> mapper);
	/**
	 * 将数据写入文件
	 * @param data
	 * @param filePath
	 * 2014年7月24日
	 */
	public void write2File(D data, String filePath);

	/**
	 * 读取指定单元格内容
	 * @param stream
	 * @param fileName
	 * @param position
	 * @return
	 * 2016年8月2日
	 */
	public String readCell(InputStream stream, String fileName, Position position);
	
	/**
	 * 追加EXCEL
	 * yj
	 * 2017年8月5日	
	 * @param list
	 * @param filePath
	 * @param titles
	 * void
	 */
	public void write2File(List<Map<String, Object>> list,String filePath, List<String> titles);

}
