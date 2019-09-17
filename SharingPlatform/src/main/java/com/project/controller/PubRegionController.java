package com.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.PubRegionEntity;
import com.project.service.PubRegionService;
import com.project.utils.PageUtils;
import com.project.utils.Query;



@RestController
@RequestMapping("/pubregion")
public class PubRegionController extends  AbstractController{
	@Autowired
	private PubRegionService pubRegionService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<PubRegionEntity> pubRegionList = pubRegionService.queryList(query);
		int total = pubRegionService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(pubRegionList, total, query.getLimit(), query.getPage());
		
		return pageUtil;
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{code}")
	public PubRegionEntity info(@PathVariable("code") String code){
		PubRegionEntity pubRegion = pubRegionService.queryObject(code);
		
		return pubRegion;
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public void save(@RequestBody PubRegionEntity pubRegion){
		pubRegionService.save(pubRegion);
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public void update(@RequestBody PubRegionEntity pubRegion){
		pubRegionService.update(pubRegion);

	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public void delete(@RequestBody String[] codes){
		pubRegionService.deleteBatch(codes);

	}
	
}
