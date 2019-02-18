package com.emotte.order.order.mapper.reader;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.ActivityMoneyGrade;

@Component("ReActivityMoneyGradeMapper") 
public interface ReActivityMoneyGradeMapper extends ReBaseMapper{ 
	
	/**
	 * 查询活动档位
	* @Description: TODO 
	* @author lidenghui
	* @date 2018年4月9日 下午6:44:39 
	* @version
	* @return ActivityMoneyGrade
	 */
 public ActivityMoneyGrade queryActivityMoneyGrade(ActivityMoneyGrade activityMoneyGrade1);
 
} 
