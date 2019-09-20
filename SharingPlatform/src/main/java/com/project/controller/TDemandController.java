package com.project.controller;


import java.util.ArrayList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.TDemandEntity;
import com.project.exception.RRException;
import com.project.info.RcResourceInfo;
import com.project.info.TDemandInfo;
import com.project.info.TDemandStatisticInfo;
import com.project.info.loginUserInfo;
import com.project.service.RcResourceService;
import com.project.service.TDemandService;
import com.project.utils.PageUtils;
import com.project.utils.Query;
import com.project.utils.R;
import com.project.utils.StringUtil;
import com.project.utils.Util;



@RestController
@RequestMapping("/tdemand")
public class TDemandController extends  AbstractController{
	@Autowired
	private TDemandService tDemandService;
	@Autowired
	private RcResourceService rcResourceService;
	
	/**
	 *对接人列表
	 */
	@RequestMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){	
		params.put("firstT", "true");		
		//查询列表数据
        Query query = new Query(params);

		List<TDemandInfo> tDemandList = tDemandService.dockingList(query);
		int total = tDemandService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tDemandList, total, query.getLimit(), query.getPage());
		
		return pageUtil;
	}
	/**
	 * 提供方列表
	 */
	@RequestMapping("/provideList")
	public PageUtils list2(@RequestParam Map<String, Object> params, HttpSession session){	
		String token = params.get("token") == null ? "" : params.get("token").toString();
		if(StringUtil.isNull(token)){		
			return new PageUtils();
		}
		loginUserInfo lui;
		try {
			lui = super.getLoginedInfo(token, session);
		} catch (RRException e) {
			return new PageUtils();
		}
		params.put("firstT", "true");	
		params.put("provideDep", lui.getOrgCode());
		//查询列表数据
        Query query = new Query(params);

		List<TDemandInfo> tDemandList = tDemandService.dockingList(query);
		int total = tDemandService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tDemandList, total, query.getLimit(), query.getPage());
		
		return pageUtil;

	}
	
	@RequestMapping("/applyList")
	public PageUtils applyList(@RequestParam Map<String, Object> params, HttpSession session){
		
		String token = params.get("token") == null ? "" : params.get("token").toString();
		if(StringUtil.isNull(token)){
			return new PageUtils();
		}
		
		loginUserInfo lui;
		try {
			lui = super.getLoginedInfo(token, session);
		} catch (RRException e) {
			return new PageUtils();
		}
		
		params.put("demandDep", lui.getOrgCode());
		
		params.put("apply", "true");
		params.put("userId", lui.getUserId());
		
		//查询列表数据
        Query query = new Query(params);

		List<TDemandInfo> tDemandList = tDemandService.queryApplyList(query);
		int total = tDemandService.queryApplyTotal(query);
		
		PageUtils pageUtil = new PageUtils(tDemandList, total, query.getLimit(), query.getPage());
		
		return pageUtil;
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{demandId}")
	public TDemandEntity info(@PathVariable("demandId") String demandId){
		TDemandEntity tDemand = tDemandService.queryObject(demandId);
		
		return tDemand;
	}
	
	/**
	 * 详细信息
	 */
	@RequestMapping("/infoD/{demandId}")
	public TDemandInfo infoDetail(@PathVariable("demandId") String demandId){
		TDemandInfo tDemand = tDemandService.queryDetailObject(demandId);
		return tDemand;
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
		TDemandEntity tDemand = new TDemandEntity();
		tDemand.setDemandDep(lui.getOrgCode());
		tDemand.setCreater(lui.getUserId());
		
		String provideDep = params.get("provideDep") == null ? "" : params.get("provideDep").toString();
		if(StringUtil.isNull(provideDep)){
			return R.error("请选择信息资源提供部门！");
		}
		tDemand.setProvideDep(provideDep);
		
		String demandName = params.get("demandName") == null ? "" : params.get("demandName").toString();
		if(StringUtil.isNull(demandName)){
			return R.error("请填写需求名称！");
		}
		tDemand.setDemandName(demandName);
		
		String demandDetail = params.get("demandDetail") == null ? "" : params.get("demandDetail").toString();
		if(StringUtil.isNull(demandDetail)){
			return R.error("请填写需求内容！");
		}
		tDemand.setDemandDetail(demandDetail);
		
		String accessMode = params.get("accessMode") == null ? "" : params.get("accessMode").toString();
		if(StringUtil.isNull(accessMode)){
			return R.error("请选择期望提供方式！");
		}
		tDemand.setAccessMode(accessMode);
		
		String serveMode = params.get("serveMode") == null ? "" : params.get("serveMode").toString();
		if(StringUtil.isNull(serveMode)){
			return R.error("请选择期望共享服务方式！");
		}
		tDemand.setServeMode(serveMode);
		
		String frequency = params.get("frequency") == null ? "" : params.get("frequency").toString();
		if(StringUtil.isNull(frequency)){
			return R.error("请选择期期望更新频率！");
		}
		tDemand.setFrequency(frequency);
		
		String demandUse = params.get("demandUse") == null ? "" : params.get("demandUse").toString();
		if(StringUtil.isNull(demandUse)){
			return R.error("请填写用途！");
		}
		tDemand.setDemandUse(demandUse);
		
		String keyWord = params.get("keyWord") == null ? "" : params.get("keyWord").toString();
		tDemand.setKeyWord(keyWord);
		
		String demandId = params.get("demandId") == null ? "" : params.get("demandId").toString();
		tDemand.setDemandId(demandId);
		
		String state = params.get("state") == null ? "" : params.get("state").toString();
		if(!"00".equals(state) && !"01".equals(state)){
			return R.error("提交信息异常！");
		}
		tDemand.setState(state);
		
		String hiteMatch = params.get("hiteMatch") == null ? "" : params.get("hiteMatch").toString();
		List<RcResourceInfo> rrf = new ArrayList<RcResourceInfo>();
		if("yes".equals(hiteMatch)){
			rrf = rcResourceService.matchingRes(keyWord);
		}
		String choose_res = params.get("chooseRes") == null ? "" : params.get("chooseRes").toString();
		
		tDemandService.edit(tDemand, lui.getUserId(), rrf, choose_res,hiteMatch);
		
		return R.ok();
	}
	
	/**
	 * 撤销
	 */
	@RequestMapping("/revoke")
	public R revoke(@RequestParam Map<String, Object> params, HttpSession session){
		
		String token = params.get("token") == null ? "" : params.get("token").toString();
		if(StringUtil.isNull(token)){
			return R.error("登录状态异常！");
		}
		
		loginUserInfo lui;
		try {
			lui = super.getLoginedInfo(token, session);
			
			TDemandEntity tDemand = new TDemandEntity();
			
			String demandId = params.get("demandId") == null ? "" : params.get("demandId").toString();
			if(StringUtil.isNull(demandId)){
				return R.error("提交信息异常！");
			}
			tDemand.setDemandId(demandId);
			
			String remark = params.get("remark") == null ? "" : params.get("remark").toString();
			tDemand.setRemark(remark);
			
			tDemandService.revoke(tDemand, lui.getUserId());
		} catch (RRException e) {
			return R.error(e.getMsg());
		}
		
		
		return R.ok();
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public void save(@RequestBody TDemandEntity tDemand){
		tDemandService.save(tDemand);

	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public void update(@RequestBody TDemandEntity tDemand){
		tDemandService.update(tDemand);

	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestParam Map<String, Object> params, HttpSession session){
		
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
		
		String demandId = params.get("demandId") == null ? "" : params.get("demandId").toString();
		if(StringUtil.isNull(demandId)){
			return R.error("提交参数异常，删除记录失败！");
		}
		
		try {
			tDemandService.delete(demandId, lui.getUserId());
		} catch (RRException e) {
			return R.error(e.getMsg());
		}
		
		
		return R.ok();
	}
	

	//提供方统计
	@RequestMapping("/statisticPro")
	public List<TDemandStatisticInfo> statisticPro(@RequestParam Map<String, Object> params, HttpSession session){
		
		String token = params.get("token") == null ? "" : params.get("token").toString();
		if(StringUtil.isNull(token)){
			return new ArrayList();
		}
		
		loginUserInfo lui;
		try {
			lui = super.getLoginedInfo(token, session);
		} catch (RRException e) {
			return new ArrayList();
		}
		
		return tDemandService.statisticPro(params);
	}
	
	//导出提供方统计
	@RequestMapping("/exporStaPro")
	public String exporStaPro(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		
		String token = params.get("token") == null ? "" : params.get("token").toString();
		if(StringUtil.isNull(token)){
			return "登录状态异常！";
		}
		
		loginUserInfo lui;
		try {
			lui = super.getLoginedInfo(token, session);
		} catch (RRException e) {
			return e.getMsg();
		}
		
		String fileName = "需求申请提供方统计"+Util.getStdfDate()+".xls";
		try {
			ServletOutputStream out = response.getOutputStream();
			response.setContentType("application/binary;charset=UTF-8");
			response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gbk"),"ISO-8859-1"));
        	this.exportProExcel(params, out);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return "导出成功！";
		
	}
	
	private void exportProExcel(Map<String, Object> params, ServletOutputStream out) {
		
		Map<String,String> titMap = new HashMap<String,String>();
		titMap.put("provideDepName", "信息资源提供部门");
		titMap.put("shouldProNums", "应提供");
		titMap.put("alreadyProNums", "已提供");
		titMap.put("notProNums", "未提供");
		titMap.put("appliedForNums", "已申请");
		titMap.put("firstTrialNums", "已初审");
		titMap.put("confirmedNums", "已确认");
		titMap.put("rescindedNums", "已撤销");
		
		List<TDemandStatisticInfo> tdsiList = tDemandService.statisticPro(params);
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("提供方统计");
		
		HSSFCellStyle headStyle=wb.createCellStyle();
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); ;
		headStyle.setFont(font);
		
		HSSFCellStyle styleInt=wb.createCellStyle();
		styleInt.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleInt.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		HSSFCellStyle style=wb.createCellStyle();
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		try {
			String colId = params.get("colId").toString();
			String[] colIds = colId.split(",");
			
			int row_nums = 0;
			HSSFRow headRow = sheet.createRow(row_nums);
			//根据heads的长度来创建格子
			for (int i = 0; i < colIds.length; i++) {
				Cell headCell = headRow.createCell(i);
				headCell.setCellValue(titMap.get(colIds[i]));
				headCell.setCellStyle(headStyle);
			}
			headRow.setHeightInPoints(30);
			row_nums++;
			//根据有多少条数据来创建填写数据的行
			for(TDemandStatisticInfo tdsi : tdsiList){
				HSSFRow row = sheet.createRow(row_nums);
				for (int i = 0; i < colIds.length; i++) {
					Cell cell = row.createCell(i);
					cell.setCellValue(tdsi.get(colIds[i]));
					if(!"provideDepName".equals(colIds[i])){
						cell.setCellStyle(styleInt);
					}else{
						cell.setCellStyle(style);
					}
				}
				row.setHeightInPoints(30);
				row_nums++;
			}
			
			for (int i = 0; i < colIds.length; i++) {
				
				if("provideDepName".equals(colIds[i])){
					sheet.setColumnWidth(i, 50 * 256);
				}else{
					sheet.setColumnWidth(i, 10 * 256);
				}
			}
			
        	wb.write(out);
            out.flush();
            out.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
	}

}
