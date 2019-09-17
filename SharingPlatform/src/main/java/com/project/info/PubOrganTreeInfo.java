package com.project.info;




public class PubOrganTreeInfo {
	 /**
     * 上级Code
     */
    private String parentCode;
    /**
     * 当前Code
     */
    private String code;
    /**
     * 显示内容
     */
    private String text;
    /**
     * 绑定value
     */
    private String value;
    
    private int cNums;
    
    private boolean isParent;
    
    private boolean open;
    
	public PubOrganTreeInfo(){
    	
    }
    public PubOrganTreeInfo(String parentCode){
    	this.parentCode=parentCode;
    }
    
    public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getName() {
		return text;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public int getcNums() {
		return cNums;
	}
	public void setcNums(int cNums) {
		this.cNums = cNums;
	}
	
	public boolean isParent() {
		return true;
	}
	public boolean getIsParent() {
		return cNums>0;
	}
	
	
	public void isParent(boolean isParent) {
		this.isParent = isParent;
	}
	public boolean isOpen() {
		return open;
	}
	public boolean getOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}

}
