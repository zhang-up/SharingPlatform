package com.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.THistoryDataEntity;
import com.project.service.THistoryDataService;
import com.project.utils.PageUtils;
import com.project.utils.Query;



@RestController
@RequestMapping("/thistorydata")
public class THistoryDataController {
	@Autowired
	private THistoryDataService tHistoryDataService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
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
	 * 删除
	 */
	@RequestMapping("/delete")
	public void delete(@RequestBody String[] historyIds){
		tHistoryDataService.deleteBatch(historyIds);
		
	}
	
}
