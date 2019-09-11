package com.project.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.IUserDao;
import com.project.dao.TestDAO;
import com.project.po.TestPO;
import com.project.service.TestService;
import com.project.utils.PageUtils;
@Service
@Transactional
public class TestServiceImpl implements TestService{
	@Autowired
	private TestDAO testDao;
	@Autowired
	private IUserDao t;

	@Override
	public List<TestPO> find() {		
		return testDao.find();
	}

	@Override
	public List<TestPO> find1() {
	
		return t.findUserByName();
	}
	//分页
	@Override
	public PageUtils fenye(int currPage, int pageSize) {		
		Map<String, Object>data=new HashMap<String, Object>();
		data.put("currIndex", (currPage-1)*pageSize);
		data.put("pageSize", pageSize);		
		//PageUtils page=new PageUtils(t.findUser(data), t.totalCount(), pageSize, currPage);
		return new PageUtils(t.findUser(data), t.totalCount(), pageSize, currPage);
	}

}
