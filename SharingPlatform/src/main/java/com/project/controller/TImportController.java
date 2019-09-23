package com.project.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.poi.POIXMLException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.entity.TDemandEntity;
import com.project.entity.TDemandOperateEntity;
import com.project.entity.TImportDetailEntity;
import com.project.entity.TImportEntity;
import com.project.entity.TParameterEntity;
import com.project.exception.RRException;
import com.project.info.PubOrganTreeInfo;
import com.project.info.loginUserInfo;
import com.project.service.PubOrganService;
import com.project.service.TImportService;
import com.project.service.TParameterService;
import com.project.utils.DateUtil;
import com.project.utils.PageUtils;
import com.project.utils.Query;
import com.project.utils.R;
import com.project.utils.StringUtil;
import com.project.utils.UUIDUtil;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;



@RestController
@RequestMapping("/timport")
public class TImportController extends  AbstractController{
	@Autowired
	private TImportService tImportService;
	@Autowired
	private PubOrganService pubOrganService;
	@Autowired
	private TParameterService tParameterService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TImportEntity> tImportList = tImportService.queryList(query);
		int total = tImportService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tImportList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{importId}")
	public R info(@PathVariable("importId") String importId){
		TImportEntity tImport = tImportService.queryObject(importId);
		
		return R.ok().put("tImport", tImport);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TImportEntity tImport){
		tImportService.save(tImport);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TImportEntity tImport){
		tImportService.update(tImport);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody String[] importIds){
		tImportService.deleteBatch(importIds);
		
		return R.ok();
	}
	
	/**
	 * 导入清单
	 */
	@SuppressWarnings("resource")
	@RequestMapping("/importD")
	public R importD(@RequestParam(value = "importDFile", required = false) MultipartFile file,@RequestParam Map<String, Object> params, HttpSession session){
		
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
		
		String type = params.get("type") == null ? "" : params.get("type").toString();
		
		String fileName = file.getOriginalFilename();
		
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName  
                .substring(fileName.lastIndexOf(".") + 1); 
		
		String checkList = "";
		String repeatInfo = "";
		try {
			HSSFSheet sheet03=null;
			XSSFSheet sheet07=null;
			
			if ("xls".equals(extension)) {  
	        	try {
	        		HSSFWorkbook book = new HSSFWorkbook(file.getInputStream()); 
	            	sheet03=book.getSheetAt(0);
				} catch (OfficeXmlFileException e) {
					XSSFWorkbook book = new XSSFWorkbook(file.getInputStream()); 
		        	sheet07=book.getSheetAt(0); 
		        	extension = "xlsx";
				}
	        } else if ("xlsx".equals(extension)) {  
	        	try {
	        		XSSFWorkbook book = new XSSFWorkbook(file.getInputStream()); 
	            	sheet07=book.getSheetAt(0); 
				} catch (POIXMLException e) {
					HSSFWorkbook book = new HSSFWorkbook(file.getInputStream()); 
	            	sheet03=book.getSheetAt(0);
		        	extension = "xls";
				}
	        }
			
			List<Map<String,String>> importList = new ArrayList<Map<String,String>>();
			if ("xls".equals(extension)) { 
				for (Row row : sheet03) {
					if (row.getRowNum() == 0) {//排除标题
						continue;
					}
					String provideDepName = this.read2003Excel(row.getRowNum(), 0, sheet03, "");
					String demandName = this.read2003Excel(row.getRowNum(), 1, sheet03, "");
					String keyWord = this.read2003Excel(row.getRowNum(), 2, sheet03, "");
					String demandDetail = this.read2003Excel(row.getRowNum(), 3, sheet03, "");
					String accessModeName = this.read2003Excel(row.getRowNum(), 4, sheet03, "");
					String serveModeName = this.read2003Excel(row.getRowNum(), 5, sheet03, "");
					String frequencyName = this.read2003Excel(row.getRowNum(), 6, sheet03, "");
					String demandUseName = this.read2003Excel(row.getRowNum(), 7, sheet03, "");
					
					Map<String,String> iMap = new HashMap<String,String>();
					iMap.put("provideDepName", provideDepName);
					iMap.put("demandName", demandName);
					iMap.put("keyWord", keyWord);
					iMap.put("demandDetail", demandDetail);
					iMap.put("accessModeName", accessModeName);
					iMap.put("serveModeName", serveModeName);
					iMap.put("frequencyName", frequencyName);
					iMap.put("demandUse", demandUseName);
					iMap.put("rowsNums", String.valueOf(row.getRowNum()));
					importList.add(iMap);
				}
	        } else if ("xlsx".equals(extension)) {  
	        	for (Row row : sheet07) {
					if (row.getRowNum() == 0) {//排除标题
						continue;
					}
					
					String provideDepName = this.read2007Excel(row.getRowNum(), 0, sheet07, "");
					String demandName = this.read2007Excel(row.getRowNum(), 1, sheet07, "");
					String keyWord = this.read2007Excel(row.getRowNum(), 2, sheet07, "");
					String demandDetail = this.read2007Excel(row.getRowNum(), 3, sheet07, "");
					String accessModeName = this.read2007Excel(row.getRowNum(), 4, sheet07, "");
					String serveModeName = this.read2007Excel(row.getRowNum(), 5, sheet07, "");
					String frequencyName = this.read2007Excel(row.getRowNum(), 6, sheet07, "");
					String demandUseName = this.read2007Excel(row.getRowNum(), 7, sheet07, "");
					
					Map<String,String> iMap = new HashMap<String,String>();
					iMap.put("provideDepName", provideDepName);
					iMap.put("demandName", demandName);
					iMap.put("keyWord", keyWord);
					iMap.put("demandDetail", demandDetail);
					iMap.put("accessModeName", accessModeName);
					iMap.put("serveModeName", serveModeName);
					iMap.put("frequencyName", frequencyName);
					iMap.put("demandUse", demandUseName);
					iMap.put("rowsNums", String.valueOf(row.getRowNum()));
					importList.add(iMap);
				}
	        } 
			
			//去重后的List，通过部门，名称，内容三条信息判断
			List<Map<String,String>> removalList = this.removalImportList(importList);
			for(Map<String,String> importMap : removalList){
				if(!StringUtil.isNull(importMap.get("msg_Info"))){
					repeatInfo = importMap.get("msg_Info");
					repeatInfo += "本次导入文件中包含数据"+importList.size()+"条，去除重复后实际保存数据"+(removalList.size()-1)+"条。";
				}
			}
//			System.out.println(removalList);
			JsonConfig jsonConfig = new JsonConfig();
			JSONArray jsonObj =  JSONArray.fromObject(this.creatBaseDate(removalList, lui, type), jsonConfig);
			checkList = jsonObj.toString();
			
		} catch (IOException e) {
			return R.error("文件格式异常！");
		} 
		
		return R.ok(checkList).put("repeatInfo", repeatInfo);
	}
	
	/**
	 * 以提供部门，名称，内容三条信息判断去除重复的记录
	 * @param importList
	 * @param lui
	 * @param type
	 * @return map中含“msg_Info”项的是反馈信息在组合实体类时需排除这条
	 */
	private List<Map<String,String>> removalImportList(List<Map<String,String>> importList){
		
		Map<String,Map<String,String>> reMap = new HashMap<String,Map<String,String>>();
		Map<String,String> repeatRowsNums = new HashMap<String,String>();
		for(Map<String,String> importMap : importList){
			
			String rowsNums = importMap.get("rowsNums") == null ? "0" : importMap.get("rowsNums").toString();
			String provideDepName = importMap.get("provideDepName") == null ? "" : importMap.get("provideDepName").toString();
			String demandName = importMap.get("demandName") == null ? "" : importMap.get("demandName").toString();
			String demandDetail = importMap.get("demandDetail") == null ? "" : importMap.get("demandDetail").toString();
			
			String key = provideDepName+demandName+demandDetail;
			
			Map<String,String> tempMap = reMap.get(key);
			if(tempMap == null){//没有重复
				reMap.put(key, importMap);
				repeatRowsNums.put(rowsNums, "");
			}else{//发现重复
				String reRowNums = tempMap.get("rowsNums") == null ? "0" : tempMap.get("rowsNums").toString();
				String rNumsV = repeatRowsNums.get(reRowNums);
				rNumsV += "、"+rowsNums;
				repeatRowsNums.put(reRowNums, rNumsV);
			}
		}
		
		List<Map<String,String>> reList = new ArrayList<Map<String,String>>();
		for(String key : reMap.keySet()){
			reList.add(reMap.get(key));
		}
		
		String msg_Info = "msg_Info";
		for(String key : repeatRowsNums.keySet()){
			if("msg_Info".equals(msg_Info)){
				msg_Info = "";
			}
			String value = repeatRowsNums.get(key);
			if(!StringUtil.isNull(value)){
				msg_Info += "第"+key+"行数据与第"+value.substring(1)+"行数据重复。<br/>";
			}
		}
		Map<String,String> tempMap = new HashMap<String,String>();
		tempMap.put("msg_Info", msg_Info);
		reList.add(tempMap);
		return reList;
	}
	
	/**
	 * 组合需要保存的实体类并存入数据库
	 */
	private List<Map<String,String>> creatBaseDate(List<Map<String,String>> importList, loginUserInfo lui, String type){
		
		List<Map<String,String>> checkList = new ArrayList<Map<String,String>>();
		
		//导入表
		TImportEntity tie = new TImportEntity();
		tie.setImportId(UUIDUtil.getUUID32());
		tie.setCreater(lui.getUserId());
		tie.setSaveTime(DateUtil.getDate());
		
		//查询出选择的项
		//部门
		List<PubOrganTreeInfo> proDep = pubOrganService.findAllInfo();
		Map<String,String> proMap = new HashMap<String,String>();
		for(PubOrganTreeInfo poti : proDep){
			proMap.put(poti.getName(), poti.getCode());
		}
		//期望提供方式
		TParameterEntity accPar = new TParameterEntity();
		accPar.setParCode("T_DEMAND|ACCESS_MODE");
		List<TParameterEntity> accessModeList = tParameterService.findList(accPar);
		Map<String,String> accMap = new HashMap<String,String>();
		for(TParameterEntity tpe : accessModeList){
			accMap.put(tpe.getParName(), tpe.getParValue());
		}
		//期望共享服务方式:
		TParameterEntity serPar = new TParameterEntity();
		serPar.setParCode("T_DEMAND|SERVE_MODE");
		List<TParameterEntity> serveModeList = tParameterService.findList(serPar);
		Map<String,String> serMap = new HashMap<String,String>();
		for(TParameterEntity tpe : serveModeList){
			serMap.put(tpe.getParName(), tpe.getParValue());
		}
		//期望更新频率:
		TParameterEntity frePar = new TParameterEntity();
		frePar.setParCode("T_DEMAND|FREQUENCY");
		List<TParameterEntity> frequencyList = tParameterService.findList(frePar);
		Map<String,String> freMap = new HashMap<String,String>();
		for(TParameterEntity tpe : frequencyList){
			freMap.put(tpe.getParName(), tpe.getParValue());
		}
		
		List<TDemandEntity> tedList = new ArrayList<TDemandEntity>();
		List<TDemandOperateEntity> tdoeList = new ArrayList<TDemandOperateEntity>();
		List<TImportDetailEntity> tideList = new ArrayList<TImportDetailEntity>();
		for(Map<String,String> importMap : importList){
			if(!StringUtil.isNull(importMap.get("msg_Info"))){
				continue;
			}
			
			Map<String,String> checkMap = new HashMap<String,String>();
			
			//需求清单表
			TDemandEntity tde = new TDemandEntity();
			tde.setDemandId(UUIDUtil.getUUID32());
			tde.setDemandDep(lui.getOrgCode());
			tde.setCreater(lui.getUserId());
			tde.setState("00");
			tde.setSaveTime(DateUtil.getDate());
			
			//操作记录
			TDemandOperateEntity tdoe = new TDemandOperateEntity();
			tdoe.setOperateId(UUIDUtil.getUUID32());
			tdoe.setDemandId(tde.getDemandId());
			tdoe.setOperator(lui.getUserId());
			tdoe.setOperateTime(DateUtil.getDate());
			tdoe.setState(tde.getState());
			tdoe.setOperateRes("0");
			tdoe.setCause("0");
			
			//导入明细表
			TImportDetailEntity tide = new TImportDetailEntity();
			tide.setImportDetailId(UUIDUtil.getUUID32());
			tide.setImportId(tie.getImportId());
			tide.setDemandId(tde.getDemandId());
			
			String rowsNums = importMap.get("rowsNums") == null ? "0" : importMap.get("rowsNums").toString();
			tide.setRowsNums(Integer.valueOf(rowsNums));
			checkMap.put("rowsNums", rowsNums);
			
			String provideDepName = importMap.get("provideDepName") == null ? "" : importMap.get("provideDepName").toString();
			String provideDep = "";
			if(!StringUtil.isNull(provideDepName)){
				provideDep = proMap.get(provideDepName);
				if(StringUtil.isNull(provideDep)){
					provideDep = "";
				}
			}
			tde.setProvideDep(provideDep);
			tide.setProvideDep(provideDep);
			tide.setProvideDepName(provideDepName);
			checkMap.put("provideDepName", StringUtil.isNull(provideDep) ? "无法匹配" : "");
			
			String demandName = importMap.get("demandName") == null ? "" : importMap.get("demandName").toString();
			tde.setDemandName(demandName);
			checkMap.put("demandName", StringUtil.isNull(demandName) ? "为空" : "");
			
			String demandDetail = importMap.get("demandDetail") == null ? "" : importMap.get("demandDetail").toString();
			tde.setDemandDetail(demandDetail);
			checkMap.put("demandDetail", StringUtil.isNull(demandDetail) ? "为空" : "");
			
			String accessModeName = importMap.get("accessModeName") == null ? "" : importMap.get("accessModeName").toString();
			String accessMode = "";
			if(!StringUtil.isNull(accessModeName)){
				accessMode = accMap.get(accessModeName);
				if(StringUtil.isNull(accessMode)){
					accessMode = "";
				}
			}
			tde.setAccessMode(accessMode);
			tide.setAccessMode(accessMode);
			tide.setAccessModeName(accessModeName);
			checkMap.put("accessModeName", StringUtil.isNull(accessMode) ? "无法匹配" : "");
			
			String serveModeName = importMap.get("serveModeName") == null ? "" : importMap.get("serveModeName").toString();
			String serveMode = "";
			if(!StringUtil.isNull(serveModeName)){
				serveMode = serMap.get(serveModeName);
				if(StringUtil.isNull(serveMode)){
					serveMode = "";
				}
			}
			tde.setServeMode(serveMode);
			tide.setServeMode(serveMode);
			tide.setServeModeName(serveModeName);
			checkMap.put("serveModeName", StringUtil.isNull(serveMode) ? "无法匹配" : "");
			
			String frequencyName = importMap.get("frequencyName") == null ? "" : importMap.get("frequencyName").toString();
			String frequency = "";
			if(!StringUtil.isNull(frequencyName)){
				frequency = freMap.get(frequencyName);
				if(StringUtil.isNull(frequency)){
					frequency = "";
				}
			}
			tde.setFrequency(frequency);
			tide.setFrequency(frequency);
			tide.setFrequencyName(frequencyName);
			checkMap.put("frequencyName", StringUtil.isNull(frequency) ? "无法匹配" : "");
			
			String demandUse = importMap.get("demandUse") == null ? "" : importMap.get("demandUse").toString();
			tde.setDemandUse(demandUse);
			checkMap.put("demandUse", StringUtil.isNull(demandUse) ? "为空" : "");
			
			String keyWord = importMap.get("keyWord") == null ? "" : importMap.get("keyWord").toString();
			tde.setKeyWord(keyWord);
			
			tedList.add(tde);
			tdoeList.add(tdoe);
			tideList.add(tide);
			
			int erNums = 0;
			for(String key : checkMap.keySet()){
				if(!"rowsNums".equals(key) && !StringUtil.isNull(checkMap.get(key))){
					erNums++;
				}
			}
			if(erNums>0){
				checkList.add(checkMap);
			}
		}
		
		if("up".equals(type)){
			tImportService.saveInport(tie, tedList, tdoeList, tideList);
		}
		
		return checkList;
	}
	
	/**  
     * 读取 office 2003 excel  
     *   
     * @throws IOException  
     * @throws FileNotFoundException  
     */  
    private String read2003Excel(int row,int col,HSSFSheet sheet03,String format)  
            throws IOException {  
    	String value="";
    	HSSFRow xRow = sheet03.getRow(row);
    	if (xRow == null) {  
            return "";  
        }  
		HSSFCell cell = xRow.getCell(col);
		if (cell == null) {  
            return "";  
        }  
		switch (cell.getCellTypeEnum()) {  
        case STRING:  
            value = cell.getStringCellValue();  
            break;  
        case NUMERIC:  
        	if ("@".equals(cell.getCellStyle().getDataFormatString())) {  
            	DecimalFormat df = new DecimalFormat("0");// 格式化 number String  
                value = df.format(cell.getNumericCellValue());  
            } else if ("General".equals(cell.getCellStyle().getDataFormatString())) { 
            	DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字  
                value = nf.format(cell.getNumericCellValue());  
            } else if(org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)){  
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串  
                value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));  
            }else{
            	value = String.valueOf(cell.getNumericCellValue());
            }  
            break;  
        case BOOLEAN: 
            value = String.valueOf(cell.getBooleanCellValue());  
            break;  
        case BLANK:  
            value = "";  
            break;  
        default:  
            value = cell.toString();  
        }
		value = value.replaceAll(String.valueOf((char)160), "");
        return value.trim();  
    }  
  
    /**  
     * 读取Office 2007 excel  
     */  
  
    private String read2007Excel(int row,int col,XSSFSheet sheet07,String format)  
            throws IOException {  
    	String value="";
    	XSSFRow xRow = sheet07.getRow(row);
    	if (xRow == null) {  
            return "";  
        }  
		XSSFCell cell = xRow.getCell(col);
		if (cell == null) {  
            return "";  
        }  
		switch (cell.getCellTypeEnum()) {  
        case STRING:  
            value = cell.getStringCellValue();  
            break;  
        case NUMERIC:  
        	if ("@".equals(cell.getCellStyle().getDataFormatString())) {  
            	DecimalFormat df = new DecimalFormat("0");// 格式化 number String  
                value = df.format(cell.getNumericCellValue());  
            } else if ("General".equals(cell.getCellStyle().getDataFormatString())) { 
            	DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字  
                value = nf.format(cell.getNumericCellValue());  
            } else if(org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)){  
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串  
                value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));  
            }else{
            	value = String.valueOf(cell.getNumericCellValue());
            }  
            break;  
        case BOOLEAN: 
            value = String.valueOf(cell.getBooleanCellValue());  
            break;  
        case BLANK:  
            value = "";  
            break;  
        default:  
            value = cell.toString();  
        }
		value = value.replaceAll(String.valueOf((char)160), "");
        return value.trim();  
    }  
}
