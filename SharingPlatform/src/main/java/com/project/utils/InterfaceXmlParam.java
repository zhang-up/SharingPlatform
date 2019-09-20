/**
 * 
 */
package com.project.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Administrator
 *
 */
public class InterfaceXmlParam {

	/**
	 * 
	 * @Title: getSoapXMl_s_1200000900000_2108
	 * @Description:TODO(获取人口基准信息查询xml参数)
	 * @author Jinoe
	 * @date 2019年9月18日 上午9:38:02
	 * @param query 查询参数
	 * @param rid 请求者标识
	 * @param sid 接口编码
	 * @param appKey 发送方签名
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 *
	 */
	public static String getSoapXMl_s_1200000900000_2108(JSONObject query, String rid, String sid, String appKey,
			String username, String password) {
		// 当前时间
		Date now = new Date();
		// 当前时间戳
		Long rtime = now.getTime();
		// 密钥
		String appSecret = SoapXmlUtil.getAppSecret(rid, sid, appKey);
		// 使用密钥加密后签名信息
		String sign = Hmacsha256.getHmacsha(rid, sid, rtime, appSecret);
		// 发送时间
		String fssj = new SimpleDateFormat("yyyyMMddHHmmss").format(now);
		
		// 查询记录参数
		JSONArray record = new JSONArray();
		if (query.containsKey("record")) {
			record = query.getJSONArray("record");
		}
		// 包体请求数据
		Map<String, Object> bodyParam = new LinkedHashMap<String, Object>();
		// 接口请求ID(服务请求编号)
		bodyParam.put("FWQQBH", query.get("FWQQBH"));
		// 请求部委代码
		bodyParam.put("QQBWDM", query.get("QQBWDM"));
		// 请求部委名称
		bodyParam.put("QQBWMC", query.get("QQBWMC"));
		// 省级政务系统所属机构代码
		bodyParam.put("SJLYXTDM", query.get("SJLYXTDM"));
		// 省级政务系统所属机构名称
		bodyParam.put("SJLYXTMC", query.get("SJLYXTMC"));
		// 请求记录数
		bodyParam.put("JLS", record.size());
		// 发送时间
		bodyParam.put("FSSJ", fssj);
		// 请求人公民身份号码
		bodyParam.put("QQRGMSFHM", query.get("QQRGMSFHM"));
		// 请求人姓名
		bodyParam.put("QQRXM", query.get("QQRXM"));
		// 请求单位代码
		bodyParam.put("QQRDWDM", query.get("QQRDWDM"));
		// 请求单位名称
		bodyParam.put("QQRDWMC", query.get("QQRDWMC"));
		// 请求业务类型代码
		bodyParam.put("QQYWLXDM", query.get("QQYWLXDM"));
		// 请求业务类型名称
		bodyParam.put("QQYWLXMC", query.get("QQYWLXMC"));
		// 请求业务系统代码
		bodyParam.put("QQYWXTDM", query.get("QQYWXTDM"));
		// 请求业务系统名称
		bodyParam.put("QQYWXTMC", query.get("QQYWXTMC"));
				
		Map<String, Object> tongtechParam = new LinkedHashMap<String, Object>();
		tongtechParam.put("gjgxjhpt_rid", rid);
		tongtechParam.put("gjgxjhpt_sid", sid);
		tongtechParam.put("gjgxjhpt_rtime", rtime);
		tongtechParam.put("gjgxjhpt_sign", sign);
		String soapXMl = SoapXmlUtil.getSoapXmlData_web(tongtechParam, "GK_JZCX_RKJCXXCX", bodyParam,
				record, username, password);
		return soapXMl;
	}
	
	/**
	 * 
	 * @Title: getSoapXMl_s_1200000900000_2109
	 * @Description:TODO()
	 * @author Jinoe
	 * @date 2019年9月19日 下午2:35:34
	 * @param queryParams 查询参数
	 * @param rid 请求者标识
	 * @param sid 接口编码
	 * @param appKey 发送方签名
	 * @return
	 *
	 */
	public static String getSoapXMl_s_1200000900000_2109(JSONObject query, String rid, String sid, String appKey,
			String username, String password) {
		// 当前时间
		Date now = new Date();
		// 当前时间戳
		Long rtime = now.getTime();
		// 密钥
		String appSecret = SoapXmlUtil.getAppSecret(rid, sid, appKey);
		// 使用密钥加密后签名信息
		String sign = Hmacsha256.getHmacsha(rid, sid, rtime, appSecret);
		// 发送时间
		String fssj = new SimpleDateFormat("yyyyMMddHHmmss").format(now);
		
		// 查询记录参数
		JSONArray record = new JSONArray();
		if (query.containsKey("record")) {
			record = query.getJSONArray("record");
		}
		// 包体请求数据
		Map<String, Object> bodyParam = new LinkedHashMap<String, Object>();
		// 接口请求ID(服务请求编号)
		bodyParam.put("FWQQBH", query.get("FWQQBH"));
		// 请求部委代码
		bodyParam.put("QQBWDM", query.get("QQBWDM"));
		// 请求部委名称
		bodyParam.put("QQBWMC", query.get("QQBWMC"));
		// 省级政务系统所属机构代码
		bodyParam.put("SJLYXTDM", query.get("SJLYXTDM"));
		// 省级政务系统所属机构名称
		bodyParam.put("SJLYXTMC", query.get("SJLYXTMC"));
		// 请求记录数
		bodyParam.put("JLS", record.size());
		// 发送时间
		bodyParam.put("FSSJ", fssj);
		// 请求人公民身份号码
		bodyParam.put("QQRGMSFHM", query.get("QQRGMSFHM"));
		// 请求人姓名
		bodyParam.put("QQRXM", query.get("QQRXM"));
		// 请求单位代码
		bodyParam.put("QQRDWDM", query.get("QQRDWDM"));
		// 请求单位名称
		bodyParam.put("QQRDWMC", query.get("QQRDWMC"));
		// 请求业务类型代码
		bodyParam.put("QQYWLXDM", query.get("QQYWLXDM"));
		// 请求业务类型名称
		bodyParam.put("QQYWLXMC", query.get("QQYWLXMC"));
		// 请求业务系统代码
		bodyParam.put("QQYWXTDM", query.get("QQYWXTDM"));
		// 请求业务系统名称
		bodyParam.put("QQYWXTMC", query.get("QQYWXTMC"));
				
		Map<String, Object> tongtechParam = new LinkedHashMap<String, Object>();
		tongtechParam.put("gjgxjhpt_rid", rid);
		tongtechParam.put("gjgxjhpt_sid", sid);
		tongtechParam.put("gjgxjhpt_rtime", rtime);
		tongtechParam.put("gjgxjhpt_sign", sign);
		String soapXMl = SoapXmlUtil.getSoapXmlData_web(tongtechParam, "GK_GXFW_SFHC", bodyParam,
				record, username, password);
		return soapXMl;
	}
	
	/**
	 * 
	 * @Title: getSoapXMl_s_1200000400000_2974
	 * @Description:TODO(获取黑名单接口参数)
	 * @author Jinoe
	 * @date 2019年9月18日 上午10:05:57
	 * @param queryParam 查询参数
	 * @param rid 请求者标识
	 * @param sid 接口编码
	 * @param appKey 发送方签名
	 * @param identity 身份标识码
	 * @param identKey 身份认证key
	 * @return
	 *
	 */
	public static String getSoapXMl_s_1200000400000_2974(JSONObject queryParam, String rid, String sid, String appKey,
			String identity, String identKey) {
		// 当前时间戳
		Long rtime = System.currentTimeMillis();
		// 密钥
		String appSecret = SoapXmlUtil.getAppSecret(rid, sid, appKey);
		// 使用密钥加密后签名信息
		String sign = Hmacsha256.getHmacsha(rid, sid, rtime, appSecret);
		// 调用方式(黑名单信息查询)
		String type = "SIC_IF_0001";
		
		String arguments = "<arguments><code_type>" + queryParam.getString("code_type") + "</code_type><code>"
				+ queryParam.getString("code") + "</code></arguments>";
		// 调用签名
		String security = SoapXmlUtil.toMD5(arguments + identKey);
		String requestSer = "<request><type>" + type + "</type><identity>" + identity
				+ "</identity><security>" + security + "</security>" + arguments + "</request>";
		
		Map<String, Object> tongtechParam = new LinkedHashMap<String, Object>();
		tongtechParam.put("gjgxjhpt_rid", rid);
		tongtechParam.put("gjgxjhpt_sid", sid);
		tongtechParam.put("gjgxjhpt_rtime", rtime);
		tongtechParam.put("gjgxjhpt_sign", sign);
		
		return SoapXmlUtil.getSoapXmlData_ser(tongtechParam, requestSer);
	}

	/**
	 * @Title: getSoapXMl_s_1200000400000_2972
	 * @Description:TODO()
	 * @author Jinoe
	 * @date 2019年9月18日 上午10:59:15
	 * @param queryParam 查询参数
	 * @param rid 请求者标识
	 * @param sid 接口编码
	 * @param appKey 发送方签名
	 * @param identity 身份标识码
	 * @param identKey 身份认证key
	 *
	 */
	public static String getSoapXMl_s_1200000400000_2972(JSONObject queryParam, String rid, String sid, String appKey,
			String identity, String identKey) {
		// 当前时间戳
		Long rtime = System.currentTimeMillis();
		// 密钥
		String appSecret = SoapXmlUtil.getAppSecret(rid, sid, appKey);
		// 使用密钥加密后签名信息
		String sign = Hmacsha256.getHmacsha(rid, sid, rtime, appSecret);
		// 调用方式(黑名单信息查询)
		String type = "SIC_IF_0006";
		
		String arguments = "<arguments><unitname>" + queryParam.getString("unitname") + "</unitname></arguments>";
		// 调用签名
		String security = SoapXmlUtil.toMD5(arguments + identKey);
		String requestSer = "<request><type>" + type + "</type><identity>" + identity
				+ "</identity><security>" + security + "</security>" + arguments + "</request>";
		
		Map<String, Object> tongtechParam = new LinkedHashMap<String, Object>();
		tongtechParam.put("gjgxjhpt_rid", rid);
		tongtechParam.put("gjgxjhpt_sid", sid);
		tongtechParam.put("gjgxjhpt_rtime", rtime);
		tongtechParam.put("gjgxjhpt_sign", sign);
		return SoapXmlUtil.getSoapXmlData_ser(tongtechParam, requestSer);
	}
	
}
