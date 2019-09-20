package com.project.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Hmacsha256
{

	public static String getHmacsha(String rid, String sid, Long rtime, String appSecret)
	{
		if (appSecret == null) return null;
		String result = null;
		try
		{
			Mac hmacSha256 = Mac.getInstance("HmacSHA256");
			byte[] keyBytes = appSecret.getBytes("UTF-8");
			hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256"));

			String inputString = sid + rid + rtime;
			//System.out.println("INPUT: " + inputString);
			byte[] hmacSha256Bytes = hmacSha256.doFinal(inputString.getBytes("UTF-8"));
			result = new String(Base64.encodeBase64(hmacSha256Bytes), "UTF-8");
			//System.out.println("OUTPUT: " + result);
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		} catch (InvalidKeyException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String args[])
	{

		String sid = "s_sys_service001";
		String rid = "fa2c3b47-dc2a-48b5-81a5-c45b17e3d115";
		String rtime = "" + System.currentTimeMillis();
		String appsecret = "f8504775185261810dcc216ff27bf77b";

		String result = null;

		try
		{

			Mac hmacSha256 = Mac.getInstance("HmacSHA256");
			byte[] keyBytes = appsecret.getBytes("UTF-8");
			hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256"));

			String inputString = sid + rid + rtime;
			System.out.println("INPUT: " + inputString);

			byte[] hmacSha256Bytes = hmacSha256.doFinal(inputString.getBytes("UTF-8"));
			result = new String(Base64.encodeBase64(hmacSha256Bytes), "UTF-8");
			System.out.println("OUTPUT: " + result);
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		} catch (InvalidKeyException e)
		{
			e.printStackTrace();
		}
	}

}
