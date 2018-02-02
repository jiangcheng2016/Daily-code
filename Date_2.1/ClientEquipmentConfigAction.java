package com.risen.struts.mes.action;

import com.risen.fmes.service.Client_Equipment_ConfigService;
import com.risen.struts.action.BaseAction;

public class ClientEquipmentConfigAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Client_Equipment_ConfigService client_Equipment_ConfigService;

	public Client_Equipment_ConfigService getClient_Equipment_ConfigService() {
		return client_Equipment_ConfigService;
	}

	public void setClient_Equipment_ConfigService(
			Client_Equipment_ConfigService client_Equipment_ConfigService) {
		this.client_Equipment_ConfigService = client_Equipment_ConfigService;
	}
	
	
	public String getClientEquipmentConfigs(){
		
		writeJsonString(client_Equipment_ConfigService.getClientEquipmentConfigs());
		return SUCCESS;
		
	}

}
