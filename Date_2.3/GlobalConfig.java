package com.risen.fmes.model;


/**
 * 全局信息  	只是实体类，不生成表
 * @author 姜程城
 */

public class GlobalConfig{
	
	/**
	 * 当前生成的产品ID
	 */
	private String productId;
	/**
	 * 当前生成的产品名称
	 */
	private String productName;
	/**
	 * 当前产品使用的生成流程ID
	 */
	private String processId;
	/**
	 * 当前产品使用的生成流程名称
	 */
	private String flowName;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getFlowName() {
		return flowName;
	}
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	
}
