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

import com.project.entity.TDemandEntity;
import com.project.exception.RRException;
import com.project.info.TDemandInfo;
import com.project.info.loginUserInfo;
import com.project.service.TDemandService;
import com.project.utils.PageUtils;
import com.project.utils.Query;
import com.project.utils.R;
import com.project.utils.StringUtil;



@RestController
@RequestMapping("/tdemand")
public class TDemandController extends  AbstractController{
	@Autowired
	private TDemandService tDemandService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TDemandEntity> tDemandList = tDemandService.queryList(query);
		int total = tDemandService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tDemandList, total, query.getLimit(), query.getPage());
		
		return pageUtil;
	}
	
	@RequestMapping("/applyList")
	public PageUtils applyList(@RequestParam Map<String, Object> params, HttpSession session){
		
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
		
		params.put("demandDep", lui.getOrgCode());
		
		params.put("apply", "true");
		params.put("userId", lui.getUserId());
		
		//查询列表数据
        Query query = new Query(params);

		List<TDemandInfo> tDemandList = tDemandService.queryApplyList(query);
		int total = tDemandService.queryApplyTotal(query);
		
		PageUtils pageUtil = new PageUtils(tDemandList, total, query.getLimit(), query.getPage());
		
		return pageUtil;
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{demandId}")
	public TDemandEntity info(@PathVariable("demandId") String demandId){
		TDemandEntity tDemand = tDemandService.queryObject(demandId);
		
		return tDemand;
	}
	
	/**
	 * 详细信息
	 */
	@RequestMapping("/infoD/{demandId}")
	public TDemandInfo infoDetail(@PathVariable("demandId") String demandId){
		TDemandInfo tDemand = tDemandService.queryDetailObject(demandId);
		return tDemand;
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
		TDemandEntity tDemand = new TDemandEntity();
		tDemand.setDemandDep(lui.getOrgCode());
		tDemand.setCreater(lui.getUserId());
		
		String provideDep = params.get("provideDep") == null ? "" : params.get("provideDep").toString();
		if(StringUtil.isNull(provideDep)){
			return R.error("请选择信息资源提供部门！");
		}
		tDemand.setProvideDep(provideDep);
		
		String demandName = params.get("demandName") == null ? "" : params.get("demandName").toString();
		if(StringUtil.isNull(demandName)){
			return R.error("请填写需求名称！");
		}
		tDemand.setDemandName(demandName);
		
		String demandDetail = params.get("demandDetail") == null ? "" : params.get("demandDetail").toString();
		if(StringUtil.isNull(demandDetail)){
			return R.error("请填写需求内容！");
		}
		tDemand.setDemandDetail(demandDetail);
		
		String accessMode = params.get("accessMode") == null ? "" : params.get("accessMode").toString();
		if(StringUtil.isNull(accessMode)){
			return R.error("请选择期望提供方式！");
		}
		tDemand.setAccessMode(accessMode);
		
		String serveMode = params.get("serveMode") == null ? "" : params.get("serveMode").toString();
		if(StringUtil.isNull(serveMode)){
			return R.error("请选择期望共享服务方式！");
		}
		tDemand.setServeMode(serveMode);
		
		String frequency = params.get("frequency") == null ? "" : params.get("frequency").toString();
		if(StringUtil.isNull(frequency)){
			return R.error("请选择期期望更新频率！");
		}
		tDemand.setFrequency(frequency);
		
		String demandUse = params.get("demandUse") == null ? "" : params.get("demandUse").toString();
		if(StringUtil.isNull(demandUse)){
			return R.error("请填写用途！");
		}
		tDemand.setDemandUse(demandUse);
		
		String keyWord = params.get("keyWord") == null ? "" : params.get("keyWord").toString();
		tDemand.setKeyWord(keyWord);
		
		String demandId = params.get("demandId") == null ? "" : params.get("demandId").toString();
		tDemand.setDemandId(demandId);
		
		String state = params.get("state") == null ? "" : params.get("state").toString();
		if(!"00".equals(state) && !"01".equals(state)){
			return R.error("提交信息异常！");
		}
		tDemand.setState(state);
		
		tDemandService.edit(tDemand, lui.getUserId());
		
		return R.ok();
	}
	
	/**
	 * 撤销
	 */
	@RequestMapping("/revoke")
	public R revoke(@RequestParam Map<String, Object> params, HttpSession session){
		
		String token = params.get("token") == null ? "" : params.get("token").toString();
		if(StringUtil.isNull(token)){
			return R.error("登录状态异常！");
		}
		
		loginUserInfo lui;
		try {
			lui = super.getLoginedInfo(token, session);
			
			TDemandEntity tDemand = new TDemandEntity();
			
			String demandId = params.get("demandId") == null ? "" : params.get("demandId").toString();
			if(StringUtil.isNull(demandId)){
				return R.error("提交信息异常！");
			}
			tDemand.setDemandId(demandId);
			
			String remark = params.get("remark") == null ? "" : params.get("remark").toString();
			tDemand.setRemark(remark);
			
			tDemandService.revoke(tDemand, lui.getUserId());
		} catch (RRException e) {
			return R.error(e.getMsg());
		}
		
		
		return R.ok();
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public void save(@RequestBody TDemandEntity tDemand){
		tDemandService.save(tDemand);

	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public void update(@RequestBody TDemandEntity tDemand){
		tDemandService.update(tDemand);

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
		
		String demandId = params.get("demandId") == null ? "" : params.get("demandId").toString();
		if(StringUtil.isNull(demandId)){
			return R.error("提交参数异常，删除记录失败！");
		}
		
		try {
			tDemandService.delete(demandId, lui.getUserId());
		} catch (RRException e) {
			return R.error(e.getMsg());
		}
		
		
		return R.ok();
	}
	
	
}
