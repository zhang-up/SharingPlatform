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

import com.com.project.service.THistoryDataService;
import com.project.entity.TDemandEntity;
import com.project.entity.THistoryDataEntity;
import com.project.exception.RRException;
import com.project.info.RcResourceInfo;
import com.project.info.TDemandInfo;
import com.project.info.THistoryDataInfo;
import com.project.info.loginUserInfo;
import com.project.utils.PageUtils;
import com.project.utils.Query;
import com.project.utils.R;
import com.project.utils.StringUtil;



@RestController
@RequestMapping("/thistorydata")
public class THistoryDataController extends  AbstractController{
	@Autowired
	private THistoryDataService tHistoryDataService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params, HttpSession session){
		String token = params.get("token") == null ? "" : params.get("token").toString();
		if(StringUtil.isNull(token)){
			return new PageUtils();
		}
		
		loginUserInfo lui;
		try {
			lui = super.getLoginedInfo(token, session);
		} catch (RRException e) {
			return new PageUtils();
		}
		params.put("provideDep", lui.getOrgCode());		
		params.put("people", "true");
		params.put("userId", lui.getUserId());
		//System.out.println(params);
		//查询列表数据
        Query query = new Query(params);
		List<THistoryDataEntity> tHistoryDataList = tHistoryDataService.queryList(query);
		int total = tHistoryDataService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(tHistoryDataList, total, query.getLimit(), query.getPage());
		
		return pageUtil;
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{historyId}")
	public THistoryDataEntity info(@PathVariable("historyId") String historyId){
		THistoryDataEntity tHistoryData = tHistoryDataService.queryObject(historyId);
		
		return tHistoryData;
	}
	
	/**
	 * 编辑
	 */
	@RequestMapping("/edit")
	public R edit(@RequestParam Map<String, Object> params, HttpSession session){
		
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
		params.put("provide_dep", lui.getOrgCode())	;				
		String hisName = params.get("h_demandName") == null ? "" : params.get("h_demandName").toString();
		if(StringUtil.isNull(hisName)){
			return R.error("请填写资源名称！");
		}
				
		String hisDetail = params.get("h_demantDetail") == null ? "" : params.get("h_demantDetail").toString();
		if(StringUtil.isNull(hisDetail)){
			return R.error("请填写数据项！");
		}
				
		String period = params.get("h_startTime") == null ? "" : params.get("h_startTime").toString();
		if(StringUtil.isNull(period)){
			return R.error("请选择期数据周期！");
		}
		
		String period_e = params.get("h_endTime") == null ? "" : params.get("h_endTime").toString();
		if(StringUtil.isNull(period_e)){
			return R.error("请选择期数据周期！");
		}
		
		
		String operate_res = params.get("h_dealResult") == null ? "" : params.get("h_dealResult").toString();
		if(StringUtil.isNull(operate_res)){
			return R.error("请选择处理结果！");
		}
		
		String state = params.get("state") == null ? "" : params.get("state").toString();
		if(!"00".equals(state) && !"01".equals(state)){
			return R.error("提交信息异常！");
		}
			tHistoryDataService.insertHistory(params);
		return R.ok();
	}
		
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public void save(@RequestBody THistoryDataEntity tHistoryData){
		tHistoryDataService.save(tHistoryData);
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public void update(@RequestBody THistoryDataEntity tHistoryData){
		tHistoryDataService.update(tHistoryData);
		
	}
	/**
	 * 详细信息
	 */
	@RequestMapping("/infoD/{historyId}")
	public THistoryDataInfo infoDetail(@PathVariable("historyId") String demandId){
		System.out.println(123);
		//TDemandInfo tDemand = tDemandService.queryDetailObject(historyId);
		return tHistoryDataService.infoDetail(demandId);
	}
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestParam Map<String, Object> params, HttpSession session){

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
		
		String historyId = params.get("historyId") == null ? "" : params.get("historyId").toString();
		if(StringUtil.isNull(historyId)){
			return R.error("提交参数异常，删除记录失败！");
		}
		
		try {
			tHistoryDataService.delete(historyId,lui.getUserId());
		} catch (RRException e) {
			return R.error(e.getMsg());
		}
	
		return R.ok();
	}
	
}
