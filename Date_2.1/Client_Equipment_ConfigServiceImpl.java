package com.risen.fmes.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.risen.dao.impl.BaseHibDAOImpl;
import com.risen.fmes.dao.Client_Equipment_ConfigDAO;
import com.risen.fmes.model.ClientEquipmentConfig;
import com.risen.fmes.model.Client_Equipment_Config;
import com.risen.fmes.service.Client_Equipment_ConfigService;
import com.risen.mes.dao.R_WorkCellToGroupDAO;
import com.risen.mes.model.R_WorkCellToGroup;
import com.risen.mes.resources.equipment.DAO.CombineDAO;
import com.risen.mes.resources.equipment.model.Combine;
import com.risen.utils.DateJsonValueProcessor;

public class Client_Equipment_ConfigServiceImpl extends BaseHibDAOImpl implements Client_Equipment_ConfigService {

	private Client_Equipment_ConfigDAO client_Equipment_ConfigDAO;
	private CombineDAO combineDAO;
	private R_WorkCellToGroupDAO r_WorkCellToGroupDAO;
	
	public Client_Equipment_ConfigDAO getClient_Equipment_ConfigDAO() {
		return client_Equipment_ConfigDAO;
	}
	public void setClient_Equipment_ConfigDAO(Client_Equipment_ConfigDAO client_Equipment_ConfigDAO) {
		this.client_Equipment_ConfigDAO = client_Equipment_ConfigDAO;
	}
	public CombineDAO getCombineDAO() {
		return combineDAO;
	}
	public void setCombineDAO(CombineDAO combineDAO) {
		this.combineDAO = combineDAO;
	}
	public R_WorkCellToGroupDAO getR_WorkCellToGroupDAO() {
		return r_WorkCellToGroupDAO;
	}
	public void setR_WorkCellToGroupDAO(R_WorkCellToGroupDAO r_WorkCellToGroupDAO) {
		this.r_WorkCellToGroupDAO = r_WorkCellToGroupDAO;
	}
	
	
	
	public String getClientEquipmentConfigs() {
		List<ClientEquipmentConfig> configs = new ArrayList<ClientEquipmentConfig>();
		//通过hql获取Client_Equipment_Config数据 and save to list
		String hql = "from Client_Equipment_Config";
		List<Client_Equipment_Config> config_cli_list = client_Equipment_ConfigDAO.getClient_Equipment_Config(hql, null);
		
		for (Client_Equipment_Config cli : config_cli_list) {
			// 根据combinId 找到 Combin
			Combine combine = combineDAO.get(cli.getCombineId());
			
			// 根据 cellId 找 WorkGroup
			hql = "from R_WorkCellToGroup where cellId = ?";
			
			String cellId = combine.getCell().getId();
			List<R_WorkCellToGroup> groups = r_WorkCellToGroupDAO.getR_WorkCellToGroups(hql, new String[] {cellId});
			R_WorkCellToGroup group = groups.get(0);
			
			ClientEquipmentConfig config = new ClientEquipmentConfig();
			config.setEquipmentGroupId(combine.getId());
			config.setEquipmentGroupName(combine.getCbName());
			config.setEquipmentId(cli.getId());
			config.setEquipmentName(cli.getEquipmentName());
			config.setHandler(cli.getHandlerName());
			config.setLogicAddr(cli.getLogicAddr());
			config.setParser(cli.getParserName());
			config.setServiceName(cli.getServiceName());
			config.setWorkCellId(combine.getCellId());
			config.setWorkCellName(combine.getCellName());
			config.setWorkGroupId(group.getId());
			config.setWorkGroupName(group.getGroupName());
			configs.add(config);
		}		
		
		JsonConfig jsonConfig = new JsonConfig();		//建立配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);		//设置默认忽略		
		jsonConfig.registerJsonValueProcessor(java.util.Date.class,
				new DateJsonValueProcessor("yyyy-MM-dd"));	//设置时间的转换格式	
		JSONArray jsonArray = JSONArray.fromObject(configs);
		return jsonArray.toString();
		//	return null;
	}

}
