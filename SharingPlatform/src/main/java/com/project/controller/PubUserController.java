package com.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.PubUserEntity;
import com.project.service.PubUserService;
import com.project.utils.PageUtils;
import com.project.utils.Query;



@RestController
@RequestMapping("/pubuser")
public class PubUserController {
	@Autowired
	private PubUserService pubUserService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<PubUserEntity> pubUserList = pubUserService.queryList(query);
		int total = pubUserService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(pubUserList, total, query.getLimit(), query.getPage());
		
		return pageUtil;
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public PubUserEntity info(@PathVariable("id") String id){
		PubUserEntity pubUser = pubUserService.queryObject(id);
		
		return pubUser;
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public void save(@RequestBody PubUserEntity pubUser){
		pubUserService.save(pubUser);

	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public void update(@RequestBody PubUserEntity pubUser){
		pubUserService.update(pubUser);

	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public void delete(@RequestBody String[] ids){
		pubUserService.deleteBatch(ids);

	}
	
}
