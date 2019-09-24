package com.com.project.service;

import java.util.List;

import com.project.entity.TestPO;
import com.project.utils.PageUtils;

public interface TestService {
	List<TestPO> find();
	List<TestPO> find1();
	PageUtils fenye(int currPage,int pageSize);
	}
