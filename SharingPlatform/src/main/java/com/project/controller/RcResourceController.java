package com.project.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.RcResourceEntity;
import com.project.exception.RRException;
import com.project.info.RcResourceInfo;
import com.project.info.loginUserInfo;
import com.project.service.RcResourceService;
import com.project.utils.PageUtils;
import com.project.utils.Query;
import com.project.utils.R;
import com.project.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;



@RestController
@RequestMapping("/rcresource")
public class RcResourceController extends  AbstractController{
	@Autowired
	private RcResourceService rcResourceService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<RcResourceEntity> rcResourceList = rcResourceService.queryList(query);
		int total = rcResourceService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(rcResourceList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 匹配资源
	 */
	@RequestMapping("/matchingRes")
	public R matchingRes(@RequestParam Map<String, Object> params, HttpSession session){
		String token = params.get("token") == null ? "" : params.get("token").toString();
		if(StringUtil.isNull(token)){
			return R.error("登录状态异常！");
		}
		
		loginUserInfo lui;
		try {
			lui = super.getLoginedInfo(token, session);
		} catch (RRException e) {
			return R.error(e.getMsg());
		}
		
		String keyWord = params.get("keyWord") == null ? "" : params.get("keyWord").toString();
		
		List<RcResourceInfo> rrf = rcResourceService.matchingRes(keyWord);
		
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray jsonObj =  JSONArray.fromObject(rrf, jsonConfig);
		return R.ok(jsonObj.toString());
	}
	
	/**
	 * 全部匹配资源
	 */
	@RequestMapping("/matchingResAll")
	public R matchingResAll(@RequestParam Map<String, Object> params, HttpSession session){
		String token = params.get("token") == null ? "" : params.get("token").toString();
		if(StringUtil.isNull(token)){
			return R.error("登录状态异常！");
		}
		loginUserInfo lui;
		try {
			lui = super.getLoginedInfo(token, session);
			rcResourceService.matchingAllRes(lui.getUserId());
		} catch (RRException e) {
			return R.error(e.getMsg());
		}
		
		return R.ok();
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") String id){
		RcResourceEntity rcResource = rcResourceService.queryObject(id);
		
		return R.ok().put("rcResource", rcResource);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody RcResourceEntity rcResource){
		rcResourceService.save(rcResource);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody RcResourceEntity rcResource){
		rcResourceService.update(rcResource);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody String[] ids){
		rcResourceService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
