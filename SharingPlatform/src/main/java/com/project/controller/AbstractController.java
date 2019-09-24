package com.project.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.com.project.service.PubUserService;
import com.project.entity.PubUserEntity;
import com.project.exception.RRException;
import com.project.info.loginUserInfo;
import com.project.utils.StringUtil;

/**
 * Controller公共组件
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月9日 下午9:42:26
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PubUserService pubUserService;
	
	private void findLogingUserInfo(String token, HttpSession session){
		if(StringUtil.isNull(token)){
			session.removeAttribute("loginUserInfo");
			throw new RRException("登录信息异常！");
		}
		
		loginUserInfo lui = (loginUserInfo)session.getAttribute("loginUserInfo");
		if(lui==null || StringUtil.isNull(lui.getUserId()) || !lui.getUserId().equals(token)){
			lui = new loginUserInfo();
			PubUserEntity pue = pubUserService.queryObject(token);
			if(pue==null){
				throw new RRException("登录信息异常！");
			}
			lui.setUserId(pue.getId());
			lui.setUserName(pue.getName());
			lui.setAccount(pue.getAccount());
			lui.setMobile(pue.getMobile());
			lui.setOrgCode(pue.getOrgCode());
			lui.setOrgName(pue.getOrgName());
			session.setAttribute("loginUserInfo", lui);
		}
	}
	
	protected loginUserInfo getLoginedInfo(String token, HttpSession session) {
		this.findLogingUserInfo(token, session);
		return (loginUserInfo)session.getAttribute("loginUserInfo");
	}
	
	
	
}
