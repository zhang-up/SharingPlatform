package com.project.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.entity.TDemandEntity;
import com.project.exception.RRException;
import com.project.info.DockTrialInfo;
import com.project.info.loginUserInfo;
import com.project.service.DockTrialService;
import com.project.utils.R;
import com.project.utils.StringUtil;
import com.project.utils.UUIDUtil;

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
		map.put("state", "01");
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
		map.put("state", "02");
		return dockTrialService.findTrialPeople(map);
	}
	/**
	 * 提供方完成确认的查询(finishVerify)
	 */

	@RequestMapping("/finishVerify")
	public DockTrialInfo finishVerify(@RequestParam Map<String, Object> map, HttpSession session) {
		String token = map.get("token") == null ? "" : map.get("token").toString();
		if(StringUtil.isNull(token)){			
		}		
		map.put("state", "04");
		return dockTrialService.finishVerify(map);
	}
	/**
	 * 对接人编辑
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
	/**
	 * 提供单位确认需求的处理
	 */
	@RequestMapping("/editDleal")
	public R editDleal(@RequestParam Map<String, Object> params, HttpSession session){	
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
		String dealReasonYes=params.get("dealReasonYes")==null ? "" : params.get("dealReasonYes").toString();
		String dealReasonNo=params.get("dealReasonNo")==null ? "" : params.get("dealReasonNo").toString();		
		if(StringUtil.isNull(dealReasonYes)&&StringUtil.isNull(dealReasonNo)){				
				return R.error("请选择处理原因！");
		}		
		System.out.println(params);
		//String String=dockTrialService.dealProvide(params);	
		R r=new R();
		//r.put("opertorid", String);
		//System.out.println(r);
		return r;
	}
	/**
	 * 提供单位完成需求的处理
	 */
	@RequestMapping("/finishDleal")
	public R finishDleal(@RequestParam Map<String, Object> params, HttpSession session){	
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
		dockTrialService.finishDleal(params);
		return R.ok();
	}
	/**
	 * 上传文件
	 * @throws IOException 
	 */
	@RequestMapping("/importD")
	public String importD(@RequestParam(value = "importDFile", required = false) MultipartFile file,@RequestParam(value = "operateid") String operateid,HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{	
		if(file==null){
			return "1";
		}
		String file_Name = file.getOriginalFilename();
		String newName=UUIDUtil.getUUID32()+file_Name.substring(file_Name.lastIndexOf("."));
		String url = session.getServletContext().getRealPath("/")+"static\\upload";
		String file_add="static/upload/"+newName;
		File root=new File(url);
			if(!root.exists()){//如果文件夹不存在
				root.mkdir();//创建文件夹
			}
		File newFile=new File(root,newName);		
		FileUtils.copyInputStreamToFile(file.getInputStream(), newFile);
		System.out.println(file_add);System.out.println(file_Name);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("file_Name", file_Name);
		map.put("file_add", file_add);
		map.put("operateid", operateid);
		//dockTrialService.importD(map);
		return "2";
	}
	@RequestMapping("/submitd")
	public R test(@RequestParam(value = "importDFile", required = false) MultipartFile file,
			@RequestParam(value = "demandid") String demandid,
			@RequestParam(value = "token") String token,
			@RequestParam(value = "demandUse") String demandUse,
			@RequestParam(value = "dealResult") String dealResult,
			@RequestParam(value = "dealReasonYes") String dealReasonYes,
			@RequestParam(value = "dealReasonNo") String dealReasonNo,
			HttpServletRequest request, HttpServletResponse response, 
			HttpSession session
			) throws IOException{	
        if(file==null){
		}	
		if(StringUtil.isNull(token)){
			return R.error("登录状态异常！");
		}				
		loginUserInfo lui;
		try {
			lui = super.getLoginedInfo(token, session);
		} catch (RRException e) {
			return R.error(e.getMsg());
		}
		if(StringUtil.isNull(dealResult)){
			return R.error("请选择处理结果！");
		}
		if(StringUtil.isNull(dealReasonYes)&&StringUtil.isNull(dealReasonNo)){				
				return R.error("请选择处理原因！");
		}		
		Map<String, Object> map=new HashMap<String, Object>();
//上传文档
		if(file!=null){
			String file_Name = file.getOriginalFilename();
			String newName=UUIDUtil.getUUID32()+file_Name.substring(file_Name.lastIndexOf("."));
			String url = session.getServletContext().getRealPath("/")+"static\\upload";
			String file_add="static/upload/"+newName;
			File root=new File(url);
				if(!root.exists()){//如果文件夹不存在
					root.mkdir();//创建文件夹
				}
			File newFile=new File(root,newName);		
			FileUtils.copyInputStreamToFile(file.getInputStream(), newFile);
			map.put("file_add", file_add);
			map.put("file_Name", file_Name);
		}
//往map当中传值		
		map.put("token", token);
		map.put("demandid", demandid);
		map.put("demandUse", demandUse);
		map.put("dealResult", dealResult);
		map.put("dealReasonNo", dealReasonNo);		
		map.put("dealReasonYes", dealReasonYes);
		dockTrialService.dealProvide(map);
		return R.ok();
	}
}
