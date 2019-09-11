package com.project.po;




public class TreePo {
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public TreePo(){
    	
    }
    public TreePo(String parentCode){
    	this.parentCode=parentCode;
    }

}
