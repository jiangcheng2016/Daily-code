package com.risen.struts.mes.action;

import com.risen.fmes.service.GlobalConfigService;
import com.risen.struts.action.BaseAction;

public class GlobalConfigAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GlobalConfigService globalConfigService;

	public GlobalConfigService getGlobalConfigService() {
		return globalConfigService;
	}

	public void setGlobalConfigService(GlobalConfigService globalConfigService) {
		this.globalConfigService = globalConfigService;
	}

	
	public String getGlobalConfigs(){
		//writeJson("hello world");
		writeJsonString(globalConfigService.getGlobalConfigs());
		return SUCCESS;
		
	}
}
