package com.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.PubOrganTreeEntity;
import com.project.service.PubOrganTreeService;
import com.project.utils.PageUtils;
import com.project.utils.Query;



@RestController
@RequestMapping("/puborgantree")
public class PubOrganTreeController extends  AbstractController{
	@Autowired
	private PubOrganTreeService pubOrganTreeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<PubOrganTreeEntity> pubOrganTreeList = pubOrganTreeService.queryList(query);
		int total = pubOrganTreeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(pubOrganTreeList, total, query.getLimit(), query.getPage());
		
		return pageUtil;
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public PubOrganTreeEntity info(@PathVariable("id") String id){
		PubOrganTreeEntity pubOrganTree = pubOrganTreeService.queryObject(id);
		
		return pubOrganTree;
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public void save(@RequestBody PubOrganTreeEntity pubOrganTree){
		pubOrganTreeService.save(pubOrganTree);
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public void update(@RequestBody PubOrganTreeEntity pubOrganTree){
		pubOrganTreeService.update(pubOrganTree);
		
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public void delete(@RequestBody String[] ids){
		pubOrganTreeService.deleteBatch(ids);
		
	}
	
}
