package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.TestPO;
import com.project.entity.TreePo;
import com.project.service.TestService;
import com.project.service.TreeService;
import com.project.utils.PageUtils;

@RestController
public class TestController {
	@Autowired
	private TestService seatService;	
	@Autowired
	private TreeService TreeService;

	@GetMapping("/seat")
	public List<TestPO> find(){
		seatService.find();
		return seatService.find1();		
	}
	@GetMapping("/fenye")
	public  PageUtils fenye(){
		return seatService.fenye(3,3);		
	}
	
	@GetMapping("/tree")
	public List<TreePo> tree(){
		return TreeService.tree("#");		
	}
}
