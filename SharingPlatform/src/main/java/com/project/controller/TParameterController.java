package com.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.TParameterEntity;
import com.project.service.TParameterService;
import com.project.utils.PageUtils;
import com.project.utils.Query;



@RestController
@RequestMapping("/tparameter")
public class TParameterController extends  AbstractController{
	@Autowired
	private TParameterService tParameterService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TParameterEntity> tParameterList = tParameterService.queryList(query);
		int total = tParameterService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tParameterList, total, query.getLimit(), query.getPage());
		
		return pageUtil;
	}
	
	
	/**
	 * 列表
	 */
	@RequestMapping("/getSelectJson")
	public List<TParameterEntity> getSelectJson(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<TParameterEntity> tParameterList = tParameterService.findListByCode(params);
		return tParameterList;
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{parameterId}")
	public TParameterEntity info(@PathVariable("parameterId") String parameterId){
		TParameterEntity tParameter = tParameterService.queryObject(parameterId);
		
		return tParameter;
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public void save(@RequestBody TParameterEntity tParameter){
		tParameterService.save(tParameter);
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public void update(@RequestBody TParameterEntity tParameter){
		tParameterService.update(tParameter);
		
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public void delete(@RequestBody String[] parameterIds){
		tParameterService.deleteBatch(parameterIds);
		
	}
	
}
