package com.project.dao;

import java.util.List;
import java.util.Map;

import com.project.entity.TestPO;

public interface IUserDao {
	public List<TestPO> findUserByName();
	//总页数
	public int totalCount();
	
	public List<TestPO> findUser(Map<String, Object> map);
	
	
	
}
