package com.project.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StringUtil {
	
	/**
	 * 
	 * @param input
	 * @return 空返回true，非空返回false
	 */
	public static boolean isNull(String input) {
		if (input != null && !"".equals(input.trim()) && !"undefined".equals(input)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 判断两个值是否相同
	 * @param oValue
	 * @param nValue
	 * @return true:相同  false:不相同
	 */
	public static boolean isSame(String oValue,String nValue){
		if(oValue==null && nValue==null){//相同
			return true;
		}else if(oValue==null && "".equals(nValue)){//相同
			return true;
		}else if("".equals(oValue) && nValue==null){//相同
			return true;
		}else if(oValue!=null && oValue.equals(nValue)){//相同
			return true;
		}
		return false;
	}
	
	public static Boolean isTrue(String isTrue){
		if("true".equals(isTrue)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * get time string
	 * */
	public static String getTimeStr(){
		Long time = new Date().getTime();
		String timeStr = time.toString();
		return timeStr;
	}
	
	/**
	 * print dyadic array
	 */
	public static void printDyadicArray(ArrayList<ArrayList<Object>> arr){
		for(ArrayList<Object>  list : arr){
			System.out.println(list.toString());
		}
	}
	
	public static void printDyadicArrayOfString(ArrayList<ArrayList<String>> arr){
		for(ArrayList<String>  list : arr){
			System.out.println(list.toString());
		}
	}
	
	public static void printThreeDimArray(ArrayList<ArrayList<ArrayList<String>>> threeDimArr){
		String outputStr = "";
		for(ArrayList<ArrayList<String>> dyadicArr : threeDimArr){
			for(ArrayList<String> arr : dyadicArr){
				for(String str : arr){
					outputStr= outputStr + " || " + str;
				}
				outputStr = outputStr + "||";
				System.out.println(outputStr);
				outputStr = "";
			} 
		}
	}
	
	/**
	 * print list
	 * */
	public static void printList(List<?> list){
		System.out.print(list.toString());
	}
	
	/**
	 * print object
	 * */
	public static void printObject(Object obj){
		System.out.println(obj.toString());
	}
	
	/**
	 * create prefix
	 * */
	public static String createPrintStr(String name, int indent) {  
        // 输出的前缀  
        String printStr = "";  
        // 按层次进行缩进  
        for (int i = 0; i < indent; i ++) {  
                printStr  = printStr + "  ";  
        }  
        printStr = printStr + "- " + name;  
        return printStr;  
	}
}
	