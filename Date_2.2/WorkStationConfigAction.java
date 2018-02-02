package com.risen.struts.mes.action;

import com.risen.fmes.service.WorkStationConfigService;
import com.risen.struts.action.BaseAction;

public class WorkStationConfigAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WorkStationConfigService workStationConfigService;

	public WorkStationConfigService getWorkStationConfigService() {
		return workStationConfigService;
	}

	public void setWorkStationConfigService(WorkStationConfigService workStationConfigService) {
		this.workStationConfigService = workStationConfigService;
	}
	
	public String getWorkStationConfigs(){
		
		writeJsonString(workStationConfigService.getWorkStationConfigs());
		return SUCCESS;
	}
}
