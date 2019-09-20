/**
 * 
 */
package com.project.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Administrator
 *
 */
public interface NationalInterfaceService {

	/**
	 * 
	 * @Title: s_1200000900000_2108
	 * @Description:TODO(人口基准信息查询)
	 * @author Jinoe
	 * @date 2019年9月17日 下午5:51:05
	 * @param query 查询参数
	 * @return
	 *
	 */
	JSONObject s_1200000900000_2108(JSONObject query);

	/**
	 * @Title: s_1200000900000_2109
	 * @Description:TODO(人口身份核查)
	 * @author Jinoe
	 * @date 2019年9月18日 上午10:41:45
	 * @param query 查询参数
	 * @return
	 *
	 */
	JSONObject s_1200000900000_2109(JSONObject query);

	/**
	 * @Title: s_1200000400000_2974
	 * @Description:TODO(黑名单接口)
	 * @author Jinoe
	 * @date 2019年9月18日 上午10:50:58
	 * @param query 查询参数
	 * @return
	 *
	 */
	JSONObject s_1200000400000_2974(JSONObject query);

	/**
	 * @Title: s_1200000400000_2972
	 * @Description:TODO(统一社会信用代码接口)
	 * @author Jinoe
	 * @date 2019年9月18日 上午10:58:31
	 * @param query 查询参数
	 * @return
	 *
	 */
	JSONObject s_1200000400000_2972(JSONObject query);

	/**
	 * @Title: s_1200003200000_2157
	 * @Description:TODO(企业基本信息验证接口服务)
	 * @author Jinoe
	 * @date 2019年9月18日 上午11:05:46
	 * @param query 查询参数
	 * @return
	 *
	 */
	JSONObject s_1200003200000_2157(JSONObject query);

	/**
	 * @Title: s_1200003200000_2158
	 * @Description:TODO(企业基本信息查询接口服务)
	 * @author Jinoe
	 * @date 2019年9月18日 上午11:15:12
	 * @param query 查询参数
	 * @return
	 *
	 */
	JSONArray s_1200003200000_2158(JSONObject query);

}
