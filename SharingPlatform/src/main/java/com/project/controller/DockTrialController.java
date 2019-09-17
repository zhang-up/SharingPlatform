package com.project.controller;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.entity.TDemandEntity;
import com.project.exception.RRException;
import com.project.info.DockTrialInfo;
import com.project.info.loginUserInfo;
import com.project.service.DockTrialService;
import com.project.utils.R;
import com.project.utils.StringUtil;

@RestController
@RequestMapping("/dock")
public class DockTrialController extends  AbstractController{
	@Autowired
	private DockTrialService dockTrialService;
	
	@RequestMapping("/list")
	public DockTrialInfo trialInfoList(@RequestParam Map<String, Object> map, HttpSession session) {
		String token = map.get("token") == null ? "" : map.get("token").toString();
		if(StringUtil.isNull(token)){			
		}
		return dockTrialService.trialInfoList(map);
	}
	/**
	 * 提供方确认需求的查询
	 */
	@RequestMapping("/findTrial")
	public DockTrialInfo findTrial(@RequestParam Map<String, Object> map, HttpSession session) {
		String token = map.get("token") == null ? "" : map.get("token").toString();
		if(StringUtil.isNull(token)){			
		}
		return dockTrialService.trialInfoList(map);
	}
	/**
	 * 编辑
	 */
	@RequestMapping("/edit")
	public R edit(@RequestParam Map<String, Object> params, HttpSession session){		
		String token = params.get("token") == null ? "" : params.get("token").toString();
		if(StringUtil.isNull(token)){
			return R.error("登录状态异常！");
		}		
		loginUserInfo lui;
		try {
			lui = super.getLoginedInfo(token, session);
		} catch (RRException e) {
			return R.error(e.getMsg());
		}
		String dealResult = params.get("dealResult") == null ? "" : params.get("dealResult").toString();
		if(StringUtil.isNull(dealResult)){
			return R.error("请选择处理结果！");
		}
		String backCause=params.get("backCause")==null ? "" : params.get("backCause").toString();
		if(dealResult.equals("2")){
			if(StringUtil.isNull(backCause)){				
				return R.error("请选择退回原因！");
			}		
		}	
		dockTrialService.dealTrial(params);		
		return R.ok();
	}
}
