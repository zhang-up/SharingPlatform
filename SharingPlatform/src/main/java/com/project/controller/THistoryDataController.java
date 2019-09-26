package com.project.controller;

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
import com.project.entity.THistoryDataEntity;
import com.project.exception.RRException;
import com.project.info.RcResourceInfo;
import com.project.info.TDemandInfo;
import com.project.info.TDemandStatisticInfo;
import com.project.info.THistoryDataInfo;
import com.project.info.THistoryStatisticInfo;
import com.project.info.loginUserInfo;
import com.project.service.THistoryDataService;
import com.project.utils.PageUtils;
import com.project.utils.Query;
import com.project.utils.R;
import com.project.utils.StringUtil;
import com.project.utils.Util;



@RestController
@RequestMapping("/thistorydata")
public class THistoryDataController extends  AbstractController{
	@Autowired
	private THistoryDataService tHistoryDataService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params, HttpSession session){
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
		params.put("provideDep", lui.getOrgCode());		
		params.put("people", "true");
		params.put("userId", lui.getUserId());
		//System.out.println(params);
		//查询列表数据
        Query query = new Query(params);
		List<THistoryDataEntity> tHistoryDataList = tHistoryDataService.queryList(query);
		int total = tHistoryDataService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(tHistoryDataList, total, query.getLimit(), query.getPage());
		
		return pageUtil;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/listDock")
	public PageUtils listDock(@RequestParam Map<String, Object> params, HttpSession session){	
		params.put("firstT", "true");
        Query query = new Query(params);
		List<THistoryDataEntity> tHistoryDataList = tHistoryDataService.queryList(query);
		int total = tHistoryDataService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(tHistoryDataList, total, query.getLimit(), query.getPage());
		
		return pageUtil;
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{historyId}")
	public THistoryDataEntity info(@PathVariable("historyId") String historyId){
		THistoryDataEntity tHistoryData = tHistoryDataService.queryObject(historyId);
		
		return tHistoryData;
	}
	

	//提供方统计
	@RequestMapping("/statisticPro")
	public List<THistoryStatisticInfo> statisticPro(@RequestParam Map<String, Object> params, HttpSession session){
		
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
		
		return tHistoryDataService.historyStatisticPro(params);
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
		params.put("userId", lui.getUserId());
		params.put("provide_dep", lui.getOrgCode())	;				
		String hisName = params.get("h_demandName") == null ? "" : params.get("h_demandName").toString();
		if(StringUtil.isNull(hisName)){
			return R.error("请填写资源名称！");
		}
				
		String hisDetail = params.get("h_demantDetail") == null ? "" : params.get("h_demantDetail").toString();
		if(StringUtil.isNull(hisDetail)){
			return R.error("请填写数据项！");
		}
				
		String period = params.get("h_startTime") == null ? "" : params.get("h_startTime").toString();
		if(StringUtil.isNull(period)){
			return R.error("请选择期数据周期！");
		}
		
		String period_e = params.get("h_endTime") == null ? "" : params.get("h_endTime").toString();
		if(StringUtil.isNull(period_e)){
			return R.error("请选择期数据周期！");
		}
		
		
		String operate_res = params.get("h_dealResult") == null ? "" : params.get("h_dealResult").toString();
		if(StringUtil.isNull(operate_res)){
			return R.error("请选择处理结果！");
		}
		
		String state = params.get("state") == null ? "" : params.get("state").toString();
		if(!"00".equals(state) && !"01".equals(state)){
			return R.error("提交信息异常！");
		}
			tHistoryDataService.insertHistory(params);
		return R.ok();
	}
		
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public void save(@RequestBody THistoryDataEntity tHistoryData){
		tHistoryDataService.save(tHistoryData);
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public void update(@RequestBody THistoryDataEntity tHistoryData){
		tHistoryDataService.update(tHistoryData);
		
	}
	/**
	 * 详细信息
	 */
	@RequestMapping("/infoD")
	public THistoryDataInfo findHistoryList(@RequestParam Map<String, Object> params){
		//System.out.println(params);
		return tHistoryDataService.findHistoryList(params);
	}
	/**
	 * 撤退
	 */
	@RequestMapping("/recall")
	public R recall(@RequestParam Map<String, Object> params, HttpSession session){
		String token = params.get("token") == null ? "" : params.get("token").toString();

		loginUserInfo lui;
		try {
			lui = super.getLoginedInfo(token, session);
		} catch (RRException e) {
			return R.error(e.getMsg());
		}
			
		String remark = params.get("remark") == null ? "" : params.get("remark").toString();
		if(StringUtil.isNull(remark)){
			return R.error("请选填写说明！");
		}
		params.put("userId", lui.getUserId());
	
		try {
			tHistoryDataService.recall(params);
		} catch (RRException e) {
			return R.error(e.getMsg());
		}
		return R.ok();
	}
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestParam Map<String, Object> params, HttpSession session){

		String token = params.get("token") == null ? "" : params.get("token").toString();
		
		loginUserInfo lui;
		try {
			lui = super.getLoginedInfo(token, session);
		} catch (RRException e) {
			return R.error(e.getMsg());
		}
		
		String historyId = params.get("historyId") == null ? "" : params.get("historyId").toString();
		if(StringUtil.isNull(historyId)){
			return R.error("提交参数异常，删除记录失败！");
		}
		
		try {
			tHistoryDataService.delete(historyId,lui.getUserId());
		} catch (RRException e) {
			return R.error(e.getMsg());
		}
	
		return R.ok();
	}
	
	
	
	//导出提供方统计
	@RequestMapping("/exporStaProH")
	public String exporStaProH(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		
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
		
		String fileName = "历史清单提供方统计"+Util.getStdfDate()+".xls";
		try {
			ServletOutputStream out = response.getOutputStream();
			response.setContentType("application/binary;charset=UTF-8");
			response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gbk"),"ISO-8859-1"));
        	this.exportProExcelH(params, out);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return "导出成功！";
		
	}
	
	private void exportProExcelH(Map<String, Object> params, ServletOutputStream out) {
		
		Map<String,String> titMap = new HashMap<String,String>();
		titMap.put("provideDepName", "信息资源提供部门");
		titMap.put("alreadyProNums", "已提供");
		titMap.put("rescindedNums", "已撤销");
		
		List<THistoryStatisticInfo> tdsiList = tHistoryDataService.historyStatisticPro(params);
		
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
			for(THistoryStatisticInfo tdsi : tdsiList){
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
