package com.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.THistoryOperateEntity;
import com.project.service.THistoryOperateService;
import com.project.utils.PageUtils;
import com.project.utils.Query;



@RestController
@RequestMapping("/thistoryoperate")
public class THistoryOperateController extends  AbstractController{

	@Autowired
	private THistoryOperateService tHistoryOperateService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<THistoryOperateEntity> tHistoryOperateList = tHistoryOperateService.queryList(query);
		int total = tHistoryOperateService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tHistoryOperateList, total, query.getLimit(), query.getPage());
		
		return pageUtil;
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{operateId}")
	public THistoryOperateEntity info(@PathVariable("operateId") String operateId){
		THistoryOperateEntity tHistoryOperate = tHistoryOperateService.queryObject(operateId);
		
		return tHistoryOperate;
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public void save(@RequestBody THistoryOperateEntity tHistoryOperate){
		tHistoryOperateService.save(tHistoryOperate);
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public void update(@RequestBody THistoryOperateEntity tHistoryOperate){
		tHistoryOperateService.update(tHistoryOperate);
		
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public void delete(@RequestBody String[] operateIds){
		tHistoryOperateService.deleteBatch(operateIds);
		
	}
	
}
