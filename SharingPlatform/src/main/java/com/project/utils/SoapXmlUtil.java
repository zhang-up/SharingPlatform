/**
 * 
 */
package com.project.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;


/**
 * @author Administrator
 *
 */
public class SoapXmlUtil {

	/**
	 * 
	 * @Title: getAppSecret
	 * @Description:TODO(获取签名密钥)
	 * @author Jinoe
	 * @date 2019年9月17日 上午11:19:57
	 * @param rid 请求者标识
	 * @param sid 接下来要请求的接口编码
	 * @param appKey 发送方签名
	 * @return
	 *
	 */
	public static String getAppSecret(String rid, String sid, String appKey) {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpEntity responseEntity = null;
		String authUrl_Post = "http://59.225.203.112:10252/wsp/scs/pzhsgxpt/refreshsecret/";
		HttpPost httpPost = new HttpPost(authUrl_Post);
		httpPost.setHeader("gjgxjhpt_rid", rid);
		httpPost.setHeader("gjgxjhpt_sid", sid);
		httpPost.setHeader("gjgxjhpt_sign", appKey);
		
		try {
			HttpResponse response = httpClient.execute(httpPost);
			responseEntity = response.getEntity();
			String result = EntityUtils.toString(responseEntity);
			if (StringUtils.isNotBlank(result)) {
				JSONObject obj = JSONObject.fromObject(result);
				if ("0".equals(obj.getString("code"))) {
					return obj.getString("appSecret");
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try
			{
				EntityUtils.consume(responseEntity);
			} catch (IOException e) { }
		}
		return null;
	}
	
	/**
	 * 
	 * @Title: getWebParam
	 * @Description:TODO(获取包体数据)
	 * @author Jinoe
	 * @date 2019年9月17日 下午3:37:24
	 * @param bodyParam 包体请求参数
	 * @param record 查询数据参数
	 * @return
	 *
	 */
	public static String getWebParam(Map<String, Object> bodyParam, JSONArray record) {
		Document doc = DocumentHelper.createDocument();
		// 根节点
		Element root = doc.addElement("PACKAGE");
		// 包体请求参数
		Element bodyNode = root.addElement("PACKAGEHEAD");
		for (Object key : bodyParam.keySet()) {
			Element dataNode = bodyNode.addElement(key.toString());
			if (bodyParam.get(key) != null) {
				dataNode.setText(bodyParam.get(key).toString());
			}
		}
		// 查询参数
		Element queryNode = root.addElement("DATA");
		for (int i = 0; i < record.size(); i++) {
			JSONObject obj = record.getJSONObject(i);
			Element recordNode = queryNode.addElement("RECORD");
			recordNode.addAttribute("no", (i + 1) + "");
			for (Object key : obj.keySet()) {
				Element dataNode = recordNode.addElement(key.toString());
				if (obj.get(key) != null) {
					dataNode.setText(obj.get(key).toString());
				}
			}
		}
		return doc.asXML();
		
	}
	
	public static String getSoapXmlData_web(Map<String, Object> tongtechParam, String interName,
			Map<String, Object> bodyParam, JSONArray record, String username, String password) {
		try {
			MessageFactory messageFactory = MessageFactory.newInstance();  
			SOAPMessage message = messageFactory.createMessage();
			
			SOAPPart soapPart = message.getSOAPPart();// 创建soap部分  
			SOAPEnvelope envelope = soapPart.getEnvelope();
			envelope.addAttribute(envelope.createName("xmlns:web"), "http://webservice.tongtech.com/");
			SOAPHeader header = envelope.getHeader();
			SOAPElement tongtechNode = header.addChildElement("tongtechheader", null, null);
			for (Object key : tongtechParam.keySet()) {
				SOAPElement node = tongtechNode.addChildElement(key.toString());
				if (tongtechParam.get(key) != null) {
					node.setTextContent(tongtechParam.get(key).toString());
				}
			}
			
			SOAPBody body = envelope.getBody();
			SOAPElement webNode = body.addChildElement(interName, "web");
			String webParam = getWebParam(bodyParam, record);
			webParam = "<![CDATA[" + webParam + "]]>";
			webNode.addChildElement("in0", "web").setTextContent(webParam);
			webNode.addChildElement("in1", "web").setTextContent(username);
			webNode.addChildElement("in2", "web").setTextContent(password);
			OutputStream os = new ByteArrayOutputStream();
			message.writeTo(os);
			return os.toString().replace("&lt;", "<").replace("&gt;", ">");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static String getSoapXmlData2_web(Map<String, Object> tongtechParam, String interName,
			Map<String, Object> bodyParam, JSONArray queryParam, String username, String password) {
		Document doc = DocumentHelper.createDocument();
		// 根节点
		Element root = doc.addElement("soapenv:Envelope");
		root.addNamespace("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
		root.addNamespace("web", "http://webservice.tongtech.com/");
		// header
		Element headerPart = root.addElement("soapenv:Header");
		Element tongtechNode = headerPart.addElement("tongtechheader");
		for (Object key : tongtechParam.keySet()) {
			Element node = tongtechNode.addElement(key.toString());
			if (tongtechParam.get(key) != null) {
				node.setText(tongtechParam.get(key).toString());
			}
		}
		// body
		Element bodyPart = root.addElement("soapenv:Body");
		Element webNode = bodyPart.addElement("web:" + interName);
		String webParam = getWebParam(bodyParam, queryParam);
		webParam = "<![CDATA[" + webParam + "]]>";
		webNode.addElement("web:in0").setText(webParam);
		webNode.addElement("web:in1").setText(username);
		webNode.addElement("web:in2").setText(password);
		return doc.asXML().replace("&lt;", "<").replace("&gt;", ">");
	}
	
	public static String getSoapXmlData_ser(Map<String, Object> tongtechParam, String requestSer) {
		try {
			MessageFactory messageFactory = MessageFactory.newInstance();  
			SOAPMessage message = messageFactory.createMessage();
			
			SOAPPart soapPart = message.getSOAPPart();// 创建soap部分  
			SOAPEnvelope envelope = soapPart.getEnvelope();
			envelope.addAttribute(envelope.createName("xmlns:ser"), "http://service.ws.sic.nasoft.com/");
			SOAPHeader header = envelope.getHeader();
			SOAPElement tongtechNode = header.addChildElement("tongtechheader", null, null);
			for (Object key : tongtechParam.keySet()) {
				SOAPElement node = tongtechNode.addChildElement(key.toString());
				if (tongtechParam.get(key) != null) {
					node.setTextContent(tongtechParam.get(key).toString());
				}
			}
			
			SOAPBody body = envelope.getBody();
			SOAPElement serNode = body.addChildElement("service", "ser");
			SOAPElement reqNode = serNode.addChildElement("request", null, null);
			String bodyParam = "<![CDATA[" + requestSer + "]]>";
			reqNode.setTextContent(bodyParam);
			OutputStream os = new ByteArrayOutputStream();
			message.writeTo(os);
			return os.toString().replace("&lt;", "<").replace("&gt;", ">");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static String getSoapXmlData2_ser(Map<String, Object> tongtechParam, String requestSer) {
		Document doc = DocumentHelper.createDocument();
		// 根节点
		Element root = doc.addElement("soapenv:Envelope");
		root.addNamespace("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
		root.addNamespace("ser", "http://service.ws.sic.nasoft.com/");
		// header
		Element headerPart = root.addElement("soapenv:Header");
		Element tongtechNode = headerPart.addElement("tongtechheader");
		for (Object key : tongtechParam.keySet()) {
			Element node = tongtechNode.addElement(key.toString());
			if (tongtechParam.get(key) != null) {
				node.setText(tongtechParam.get(key).toString());
			}
		}
		// body
		Element bodyPart = root.addElement("soapenv:Body");
		Element serNode = bodyPart.addElement("ser:service");
		Element reqNode = serNode.addElement("request");
		String bodyParam = "<![CDATA[" + requestSer + "]]>";
		reqNode.setText(bodyParam);
		return doc.asXML().replace("&lt;", "<").replace("&gt;", ">");
	}
	
	/**
	 * 
	 * @Title: toMD5
	 * @Description:TODO(转MD5)
	 * @author Jinoe
	 * @date 2019年9月18日 上午9:58:28
	 * @param str
	 * @return
	 *
	 */
	public static String toMD5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			try {
				md.update(str.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			byte[] encryContext = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < encryContext.length; offset++) {
				i = encryContext[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 
	 * @Title: executeXmlRequest
	 * @Description:TODO(执行soapXML请求)
	 * @author Jinoe
	 * @date 2019年9月18日 上午10:28:23
	 * @param url 请求地址
	 * @param params 参数
	 * @return
	 *
	 */
	public static String executeXmlRequest(String url, String params) {
		HttpPost httpPost = null;
		HttpEntity responseEntity = null;
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			httpPost = new HttpPost(url);
			
			httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
			httpPost.setHeader("X-Forwarded-For", "59.225.203.112");
			
			// 参数
			StringEntity entity = new StringEntity(params, "UTF-8");
			httpPost.setEntity(entity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			responseEntity = httpResponse.getEntity();
			
			return EntityUtils.toString(responseEntity);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally {
			try {
				httpPost.releaseConnection();  // 关闭连接
			} catch (Exception e) { }
			try
			{
				EntityUtils.consume(responseEntity);
			} catch (IOException e) { }
		}
	}
	
	public static String executeJsonRequest(String url, JSONObject params, String rid, String sid, String appKey) {
		HttpPost httpPost = null;
		HttpEntity responseEntity = null;
		try {
			// 当前时间
			Date now = new Date();
			// 当前时间戳
			Long rtime = now.getTime();
			// 密钥
			String appSecret = SoapXmlUtil.getAppSecret(rid, sid, appKey);
			// 使用密钥加密后签名信息
			String sign = Hmacsha256.getHmacsha(rid, sid, rtime, appSecret);
			
			HttpClient httpClient = HttpClientBuilder.create().build();
			httpPost = new HttpPost(url);
			
//			httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
//			httpPost.setHeader("X-Forwarded-For", "59.225.203.112");
			
			httpPost.setHeader("gjgxjhpt_rid", rid);
			httpPost.setHeader("gjgxjhpt_sid", sid);
			httpPost.setHeader("gjgxjhpt_rtime", rtime + "");
			httpPost.setHeader("gjgxjhpt_sign", sign);
			httpPost.setHeader("Authorization", "Basic Z3hzZDAxOjY2NjY2Ng==");
			
			// 参数
			StringEntity entity = new StringEntity(params.toString(), "UTF-8");
			httpPost.setEntity(entity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			responseEntity = httpResponse.getEntity();
			String result = EntityUtils.toString(responseEntity);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally {
			try {
				httpPost.releaseConnection();  // 关闭连接
			} catch (Exception e) { }
			try
			{
				EntityUtils.consume(responseEntity);
			} catch (IOException e) { }
		}
	}
	
	/**
	 * 
	 * @Title: convertSoapResultToJson_web
	 * @Description:TODO(将web请求的soapResult转换成Json)
	 * @author Jinoe
	 * @date 2019年9月19日 下午3:54:55
	 * @param soapResult soap返回结果
	 * @param webName web接口名称
	 * @return
	 *
	 */
	public static JSONObject convertSoapResultToJson_web(String soapResult, String webName) {
		XMLSerializer xmlSerializer = new XMLSerializer();
		JSONObject obj = (JSONObject)xmlSerializer.read(soapResult);
		JSONObject body_obj = obj.getJSONObject("soap:Body");
		// 请求失败
		if (body_obj.get("soap:Fault") != null) {
			return body_obj.getJSONObject("soap:Fault");
		}
		// 请求成功
		else {
			String out = body_obj.getJSONObject("ns1:" + webName + "Response").getString("ns1:out");
			JSONObject out_obj = (JSONObject)xmlSerializer.read(out);
			return out_obj;
		}
	}
	
	/**
	 * 
	 * @Title: convertSoapResultToJson_ser
	 * @Description:TODO(将service请求的soapResult转换成Json)
	 * @author Jinoe
	 * @date 2019年9月19日 下午3:56:34
	 * @param soapResult
	 * @return
	 *
	 */
	public static JSONObject convertSoapResultToJson_ser(String soapResult) {
		XMLSerializer xmlSerializer = new XMLSerializer();
		JSONObject obj = (JSONObject)xmlSerializer.read(soapResult);
		JSONObject body_obj = obj.getJSONObject("soap:Body");
		// 请求失败
		if (body_obj.get("soap:Fault") != null) {
			return body_obj.getJSONObject("soap:Fault");
		}
		// 请求成功
		else {
			String out = body_obj.getJSONObject("ns2:serviceResponse").getString("return");
			JSONObject out_obj = (JSONObject)xmlSerializer.read(out);
			return out_obj;
		}
	}
	
}
