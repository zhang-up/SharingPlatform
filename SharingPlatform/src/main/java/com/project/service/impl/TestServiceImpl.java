package com.project.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.project.dao.TestDAO;
import com.project.po.TestPO;
import com.project.service.TestService;

@Service
@Transactional
public class TestServiceImpl implements TestService{
	@Autowired
	private TestDAO testDao;


	@Override
	public List<TestPO> find() {		
		return testDao.find();
	}


}
