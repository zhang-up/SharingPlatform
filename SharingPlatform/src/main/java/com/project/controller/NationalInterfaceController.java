/**
 * 
 */
package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.service.NationalInterfaceService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Administrator
 * 国家接口
 *
 */
@RequestMapping("/ni")
@RestController
public class NationalInterfaceController {
	
	@Autowired
	private NationalInterfaceService nationalInterfaceService;
	
	/**
	 * 
	 * @Title: s_1200000900000_2108
	 * @Description:TODO(人口基准信息查询)
	 * @author Jinoe
	 * @date 2019年9月18日 上午10:40:36
	 * @param query 查询参数
	 * @return
	 *
	 */
	@GetMapping("/s_1200000900000_2108")
	public JSONObject s_1200000900000_2108(@RequestBody JSONObject query) {
		return nationalInterfaceService.s_1200000900000_2108(query);
	}
	
	/**
	 * 
	 * @Title: s_1200000900000_2109
	 * @Description:TODO(人口身份核查)
	 * @author Jinoe
	 * @date 2019年9月18日 上午10:41:09
	 * @param query 查询参数
	 * @return
	 *
	 */
	@GetMapping("/s_1200000900000_2109")
	public JSONObject s_1200000900000_2109(@RequestBody JSONObject query) {
		return nationalInterfaceService.s_1200000900000_2109(query);
	}
	
	/**
	 * 
	 * @Title: s_1200000400000_2974
	 * @Description:TODO(黑名单接口)
	 * @author Jinoe
	 * @date 2019年9月18日 上午10:42:40
	 * @param query 查询参数
	 * @return
	 *
	 */
	@GetMapping("/s_1200000400000_2974")
	public JSONObject s_1200000400000_2974(@RequestBody JSONObject query) {
		return nationalInterfaceService.s_1200000400000_2974(query);
	}
	
	/**
	 * 
	 * @Title: s_1200000400000_2972
	 * @Description:TODO(统一社会信用代码接口)
	 * @author Jinoe
	 * @date 2019年9月18日 上午10:58:12
	 * @param query 查询参数
	 * @return
	 *
	 */
	@GetMapping("/s_1200000400000_2972")
	public JSONObject s_1200000400000_2972(@RequestBody JSONObject query) {
		return nationalInterfaceService.s_1200000400000_2972(query);
	}
	
	/**
	 * 
	 * @Title: s_1200003200000_2157
	 * @Description:TODO(企业基本信息验证接口服务)
	 * @author Jinoe
	 * @date 2019年9月18日 上午11:05:33
	 * @param query 查询参数
	 * @return
	 *
	 */
	@GetMapping("/s_1200003200000_2157")
	public Object s_1200003200000_2157(@RequestBody JSONObject query) {
		return nationalInterfaceService.s_1200003200000_2157(query);
	}
	
	/**
	 * 
	 * @Title: s_1200003200000_2158
	 * @Description:TODO(企业基本信息查询接口服务)
	 * @author Jinoe
	 * @date 2019年9月18日 上午11:14:55
	 * @param query 查询参数
	 * @return
	 *
	 */
	@GetMapping("/s_1200003200000_2158")
	public JSONArray s_1200003200000_2158(@RequestBody JSONObject query) {
		return nationalInterfaceService.s_1200003200000_2158(query);
	}
	
}
