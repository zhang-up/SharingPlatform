package com.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.TDemandEntity;
import com.project.service.TDemandService;
import com.project.utils.PageUtils;
import com.project.utils.Query;



@RestController
@RequestMapping("/tdemand")
public class TDemandController {
	@Autowired
	private TDemandService tDemandService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		System.out.println(params);
		//查询列表数据
        Query query = new Query(params);

		List<TDemandEntity> tDemandList = tDemandService.queryList(query);
		int total = tDemandService.queryTotal(query);
		
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
	public void delete(@RequestBody String[] demandIds){
		tDemandService.deleteBatch(demandIds);

	}
	
}
