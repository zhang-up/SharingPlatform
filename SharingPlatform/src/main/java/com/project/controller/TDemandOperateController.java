package com.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.TDemandOperateEntity;
import com.project.info.TDemandOperateInfo;
import com.project.service.TDemandOperateService;
import com.project.utils.PageUtils;
import com.project.utils.Query;



@RestController
@RequestMapping("/tdemandoperate")
public class TDemandOperateController extends  AbstractController{
	@Autowired
	private TDemandOperateService tDemandOperateService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TDemandOperateEntity> tDemandOperateList = tDemandOperateService.queryList(query);
		int total = tDemandOperateService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tDemandOperateList, total, query.getLimit(), query.getPage());
		
		return pageUtil;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/listByDemand/{demandId}")
	public List<TDemandOperateInfo> listByDemand(@PathVariable("demandId") String demandId){
		//查询列表数据
		return tDemandOperateService.queryListByDemand(demandId);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{operateId}")
	public TDemandOperateEntity info(@PathVariable("operateId") String operateId){
		TDemandOperateEntity tDemandOperate = tDemandOperateService.queryObject(operateId);
		
		return tDemandOperate;
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public void save(@RequestBody TDemandOperateEntity tDemandOperate){
		tDemandOperateService.save(tDemandOperate);
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public void update(@RequestBody TDemandOperateEntity tDemandOperate){
		tDemandOperateService.update(tDemandOperate);
		
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public void delete(@RequestBody String[] operateIds){
		tDemandOperateService.deleteBatch(operateIds);
		
	}
	
}
