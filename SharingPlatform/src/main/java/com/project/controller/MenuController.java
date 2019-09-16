package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.info.SysMenusInfo;



@RestController
@RequestMapping("/menu")
public class MenuController {
//	@Autowired
//	private PubOrganService pubOrganService;
	
	/**
	 * 根据权限返回菜单
	 */
	@RequestMapping("/menus")
	public List<SysMenusInfo> creatMenus(String id){
		
		List<SysMenusInfo> smiList = new ArrayList<SysMenusInfo>();
		
		smiList.add(new SysMenusInfo("需求申请", "views/demand/apply.html"));
		smiList.add(new SysMenusInfo("需求初审", "views/demand/firstTrial.html"));
		smiList.add(new SysMenusInfo("需求提供", ""));
		smiList.add(new SysMenusInfo("需求统计", ""));
		
		smiList.add(new SysMenusInfo("历史清单提交", ""));
		smiList.add(new SysMenusInfo("历史清单", ""));
		smiList.add(new SysMenusInfo("历史统计", ""));
		
		
		return smiList;
	}
	
}
