package com.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.RcResourceEntity;
import com.project.service.RcResourceService;
import com.project.utils.PageUtils;
import com.project.utils.Query;
import com.project.utils.R;



@RestController
@RequestMapping("/rcresource")
public class RcResourceController {
	@Autowired
	private RcResourceService rcResourceService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<RcResourceEntity> rcResourceList = rcResourceService.queryList(query);
		int total = rcResourceService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(rcResourceList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") String id){
		RcResourceEntity rcResource = rcResourceService.queryObject(id);
		
		return R.ok().put("rcResource", rcResource);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody RcResourceEntity rcResource){
		rcResourceService.save(rcResource);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody RcResourceEntity rcResource){
		rcResourceService.update(rcResource);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody String[] ids){
		rcResourceService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
