package com.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.PubOrganEntity;
import com.project.info.PubOrganTreeInfo;
import com.project.service.PubOrganService;
import com.project.utils.PageUtils;
import com.project.utils.Query;



@RestController
@RequestMapping("/puborgan")
public class PubOrganController extends  AbstractController{
	@Autowired
	private PubOrganService pubOrganService;
	
	@GetMapping("/orgTree")
	public List<PubOrganTreeInfo> tree(@RequestParam Map<String, Object> params){
		return pubOrganService.tree(params.get("parentCode").toString());		
	}
	
	@GetMapping("/orgTreeByKey")
	public List<PubOrganTreeInfo> treeByKey(@RequestParam Map<String, Object> params){
		return pubOrganService.treeByKey(params.get("name").toString());		
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<PubOrganEntity> pubOrganList = pubOrganService.queryList(query);
		int total = pubOrganService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(pubOrganList, total, query.getLimit(), query.getPage());
		
		return pageUtil;
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public PubOrganEntity info(@PathVariable("id") String id){
		PubOrganEntity pubOrgan = pubOrganService.queryObject(id);
		
		return pubOrgan;
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public void save(@RequestBody PubOrganEntity pubOrgan){
		pubOrganService.save(pubOrgan);
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public void update(@RequestBody PubOrganEntity pubOrgan){
		pubOrganService.update(pubOrgan);
		
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public void delete(@RequestBody String[] ids){
		pubOrganService.deleteBatch(ids);
		
	}
	
}
