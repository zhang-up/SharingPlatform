package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.po.TestPO;

import com.project.service.TestService;



@RestController
public class TestController {
	@Autowired
	private TestService seatService;	


	@GetMapping("/seat")
	public List<TestPO> find(){
		seatService.find();
		return seatService.find();		
	}

}
