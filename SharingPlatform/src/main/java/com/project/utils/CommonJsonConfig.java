/*
 * ******************************************************************
 * 文件名:CommonJsonConfig.java
 * 包名:common.jsonProcessor
 * 项目名:trms
 * 文件说明:
 * 作者:Administrator
 * 版权: Copyright (c) 2011 软通动力版权所有
 * 创建时间:2011-11-16 上午10:24:28
 * ******************************************************************
 */
package com.project.utils;

import net.sf.json.JSONNull;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;

/**
 * 功能：
 * 
 * @author lzp email: zplie@isoftstone.com
 * @date 2011-11-16
 */
// ******************************************************************
/**
 * 类名:common.jsonProcessor.CommonJsonConfig
 * 
 * <pre>
 * 描述:
 * 	基本思路:
 * 	public方法:
 * 	特别说明:
 * 编写者:李志鹏
 * 版权: Copyright (C) 2011  软通动力版权所有
 * 创建时间:2011-11-16 上午10:24:28
 * 修改说明: 类的修改说明
 * </pre>
 */
// ******************************************************************
public class CommonJsonConfig extends JsonConfig {

	/**
	 * 
	 */
	public CommonJsonConfig() {
		super();
		super.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT); // 自动为我排除circle。
		super.setJsonPropertyFilter(new PropertyFilter() {

			@Override
			public boolean apply(Object source, String name, Object value) {
				return value == null || value instanceof JSONNull;
			}
		});
	}
}
