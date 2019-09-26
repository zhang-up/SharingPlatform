package com.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.TImportDetailEntity;
import com.project.service.TImportDetailService;
import com.project.utils.PageUtils;
import com.project.utils.Query;
import com.project.utils.R;



@RestController
@RequestMapping("/timportdetail")
public class TImportDetailController extends  AbstractController{
	@Autowired
	private TImportDetailService tImportDetailService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TImportDetailEntity> tImportDetailList = tImportDetailService.queryList(query);
		int total = tImportDetailService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tImportDetailList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{importDetailId}")
	public R info(@PathVariable("importDetailId") String importDetailId){
		TImportDetailEntity tImportDetail = tImportDetailService.queryObject(importDetailId);
		
		return R.ok().put("tImportDetail", tImportDetail);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TImportDetailEntity tImportDetail){
		tImportDetailService.save(tImportDetail);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TImportDetailEntity tImportDetail){
		tImportDetailService.update(tImportDetail);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody String[] importDetailIds){
		tImportDetailService.deleteBatch(importDetailIds);
		
		return R.ok();
	}
	
}
