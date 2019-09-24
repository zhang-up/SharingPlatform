package com.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.com.project.service.TDemandResourceService;
import com.project.entity.TDemandResourceEntity;
import com.project.info.TDemandResourceInfo;
import com.project.utils.PageUtils;
import com.project.utils.Query;



@RestController
@RequestMapping("/tdemandresource")
public class TDemandResourceController extends  AbstractController{
	@Autowired
	private TDemandResourceService tDemandResourceService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TDemandResourceEntity> tDemandResourceList = tDemandResourceService.queryList(query);
		int total = tDemandResourceService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tDemandResourceList, total, query.getLimit(), query.getPage());
		
		return pageUtil;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/listByDemand/{demandId}")
	public List<TDemandResourceInfo> listByDemand(@PathVariable("demandId") String demandId){
		//查询列表数据
		return tDemandResourceService.findInfoListByDemand(demandId);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{dResId}")
	public TDemandResourceEntity info(@PathVariable("dResId") String dResId){
		TDemandResourceEntity tDemandResource = tDemandResourceService.queryObject(dResId);
		
		return tDemandResource;
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public void save(@RequestBody TDemandResourceEntity tDemandResource){
		tDemandResourceService.save(tDemandResource);
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public void update(@RequestBody TDemandResourceEntity tDemandResource){
		tDemandResourceService.update(tDemandResource);
		
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public void delete(@RequestBody String[] dResIds){
		tDemandResourceService.deleteBatch(dResIds);
		
	}
	
}
