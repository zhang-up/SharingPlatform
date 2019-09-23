/**
 * 
 */
package com.project.service.impl;

import org.springframework.stereotype.Service;

import com.project.service.NationalInterfaceService;
import com.project.utils.CommonJsonConfig;
import com.project.utils.InterfaceXmlParam;
import com.project.utils.SoapXmlUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Administrator
 *
 */
@Service
public class NationalInterfaceServiceImpl implements NationalInterfaceService {

	/* (non-Javadoc)
	 * @see com.jzz.app.bus.national.service.NationalInfoQueryService#s_1200000900000_2108(net.sf.json.JSONObject)
	 */
	@Override
	public JSONObject s_1200000900000_2108(JSONObject query) {
		String url = "http://59.225.203.112:10252/wsp/scs/pzhsgxpt/s_1200000900000_2108/wsproxy/";
		// 请求者标识
		String rid = "691ededf-f923-4f1b-a183-959e6bceddc4";
		// 接口编码
		String sid = "s_1200000900000_2108";
		// 发送发签名
		String appKey = "7adc089fa2448f9bcc3580109e6b738f";
		// 用户名
		String username = "gxzxjkcs";
		// 密码
		String password = "cLv6jMAaevpC3jFb7/84Ew==";
		String params = InterfaceXmlParam.getSoapXMl_s_1200000900000_2108(query, rid, sid, appKey, username, password);
		String soapResult = SoapXmlUtil.executeXmlRequest(url, params);
		return SoapXmlUtil.convertSoapResultToJson_web(soapResult, "GK_JZCX_RKJCXXCX");
	}

	/* (non-Javadoc)
	 * @see com.jzz.app.bus.national.service.NationalInfoQueryService#s_1200000900000_2109(net.sf.json.JSONObject)
	 */
	@Override
	public JSONObject s_1200000900000_2109(JSONObject query) {
		String url = "http://59.225.203.112:10252/wsp/scs/pzhsjhpt/s_1200000900000_2109/wsproxy/";
		// 请求者标识
		String rid = "691ededf-f923-4f1b-a183-959e6bceddc4";
		// 接口编码
		String sid = "s_1200000900000_2109";
		// 发送发签名
		String appKey = "6a0c7b92f1296d7ebe5b2e8ce04e0227";
		// 用户名
		String username = "gxzxjkcs";
		// 密码
		String password = "cLv6jMAaevpC3jFb7/84Ew==";
		String params = InterfaceXmlParam.getSoapXMl_s_1200000900000_2109(query, rid, sid, appKey, username, password);
		String soapResult = SoapXmlUtil.executeXmlRequest(url, params);
		return SoapXmlUtil.convertSoapResultToJson_web(soapResult, "GK_GXFW_SFHC");
	}

	/* (non-Javadoc)
	 * @see com.jzz.app.bus.national.service.NationalInfoQueryService#s_1200000400000_2974(net.sf.json.JSONObject)
	 */
	@Override
	public JSONObject s_1200000400000_2974(JSONObject query) {
		String url = "http://59.225.203.112:10252/wsp/scs/pzhsjhpt/s_1200000400000_2974/wsproxy/";
		// 请求者标识
		String rid = "691ededf-f923-4f1b-a183-959e6bceddc4";
		// 接口编码
		String sid = "s_1200000400000_2974";
		// 发送发签名
		String appKey = "3c87992851f84f4e276b6c8d0caa436f";
		// 身份标识码
		String identity = "scyjjxxzx";
		// 身份认证key
		String identKey = "9EBC41F3E03970788539777BC665E00E";
		String params = InterfaceXmlParam.getSoapXMl_s_1200000400000_2974(query, rid, sid, appKey, identity, identKey);
		String soapResult = SoapXmlUtil.executeXmlRequest(url, params);
		return SoapXmlUtil.convertSoapResultToJson_ser(soapResult);
	}

	/* (non-Javadoc)
	 * @see com.jzz.app.bus.national.service.NationalInfoQueryService#s_1200000400000_2972(net.sf.json.JSONObject)
	 */
	@Override
	public JSONObject s_1200000400000_2972(JSONObject query) {
		String url = "http://59.225.203.112:10252/wsp/scs/pzhsjhpt/s_1200000400000_2972/wsproxy/";
		// 请求者标识
		String rid = "691ededf-f923-4f1b-a183-959e6bceddc4";
		// 接口编码
		String sid = "s_1200000400000_2972";
		// 发送发签名
		String appKey = "0145e1dc76513e533f042040268998a1";
		// 身份标识码
		String identity = "scyjjxxzx";
		// 身份认证key
		String identKey = "9EBC41F3E03970788539777BC665E00E";
		String params = InterfaceXmlParam.getSoapXMl_s_1200000400000_2972(query, rid, sid, appKey, identity, identKey);
		String soapResult = SoapXmlUtil.executeXmlRequest(url, params);
		return SoapXmlUtil.convertSoapResultToJson_ser(soapResult);
	}

	/* (non-Javadoc)
	 * @see com.jzz.app.bus.national.service.NationalInfoQueryService#s_1200003200000_2157(net.sf.json.JSONObject)
	 */
	@Override
	public JSONObject s_1200003200000_2157(JSONObject query) {
		String url = "http://59.225.203.112:10252/wsp/scs/pzhsjhpt/s_1200003200000_2158/httpproxy/";
		// 请求者标识
		String rid = "691ededf-f923-4f1b-a183-959e6bceddc4";
		// 接口编码
		String sid = "s_1200003200000_2157";
		// 发送发签名
		String appKey = "9697747911790e01c7b3dbea041fd0fa";
		String result = SoapXmlUtil.executeJsonRequest(url, query, rid, sid, appKey);
		return JSONObject.fromObject(result, new CommonJsonConfig());
	}

	/* (non-Javadoc)
	 * @see com.jzz.app.bus.national.service.NationalInfoQueryService#s_1200003200000_2158(net.sf.json.JSONObject)
	 */
	@Override
	public JSONArray s_1200003200000_2158(JSONObject query) {
		String url = "http://59.225.203.112:10252/wsp/scs/pzhsjhpt/s_1200003200000_2157/httpproxy/";
		// 请求者标识
		String rid = "691ededf-f923-4f1b-a183-959e6bceddc4";
		// 接口编码
		String sid = "s_1200003200000_2158";
		// 发送发签名
		String appKey = "52e52b21bf78a8f67979218ff53d0c71";
		String result = SoapXmlUtil.executeJsonRequest(url, query, rid, sid, appKey);
		return JSONArray.fromObject(result, new CommonJsonConfig());
	}


}
