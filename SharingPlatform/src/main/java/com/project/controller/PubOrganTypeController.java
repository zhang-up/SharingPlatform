package com.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.PubOrganTypeEntity;
import com.project.service.PubOrganTypeService;
import com.project.utils.PageUtils;
import com.project.utils.Query;



@RestController
@RequestMapping("/puborgantype")
public class PubOrganTypeController extends  AbstractController{
	@Autowired
	private PubOrganTypeService pubOrganTypeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<PubOrganTypeEntity> pubOrganTypeList = pubOrganTypeService.queryList(query);
		int total = pubOrganTypeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(pubOrganTypeList, total, query.getLimit(), query.getPage());
		
		return pageUtil;
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public PubOrganTypeEntity info(@PathVariable("id") String id){
		PubOrganTypeEntity pubOrganType = pubOrganTypeService.queryObject(id);
		
		return pubOrganType;
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public void save(@RequestBody PubOrganTypeEntity pubOrganType){
		pubOrganTypeService.save(pubOrganType);
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public void update(@RequestBody PubOrganTypeEntity pubOrganType){
		pubOrganTypeService.update(pubOrganType);
		
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public void delete(@RequestBody String[] ids){
		pubOrganTypeService.deleteBatch(ids);
		
	}
	
}
