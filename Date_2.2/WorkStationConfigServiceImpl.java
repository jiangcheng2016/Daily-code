package com.risen.fmes.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;


import com.risen.dao.impl.BaseHibDAOImpl;
import com.risen.fmes.model.WorkStationConfig;
import com.risen.fmes.service.WorkStationConfigService;
import com.risen.mes.dao.R_WorkCellToGroupDAO;
import com.risen.mes.dao.WorkCellDAO;
import com.risen.mes.model.R_WorkCellToGroup;
import com.risen.mes.model.WorkCell;
import com.risen.mes.resources.equipment.DAO.CombineDAO;
import com.risen.mes.resources.equipment.model.Combine;
import com.risen.utils.DateJsonValueProcessor;

public class WorkStationConfigServiceImpl extends BaseHibDAOImpl implements WorkStationConfigService {

	private WorkCellDAO workCellDAO;
	private CombineDAO combineDAO;
	private R_WorkCellToGroupDAO r_WorkCellToGroupDAO;
	
	
	public WorkCellDAO getWorkCellDAO() {
		return workCellDAO;
	}
	public void setWorkCellDAO(WorkCellDAO workCellDAO) {
		this.workCellDAO = workCellDAO;
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
	public String getWorkStationConfigs() {
		
		List<WorkStationConfig> configs = new ArrayList<WorkStationConfig>();
		//通过hql找到WorkCells 表
		String hql = "from Combine";
		List<Combine> config_com = combineDAO.getCombines(hql, null);
		
		for(Combine combine:config_com) {
			//根据cellId 找到WorkCell
			WorkCell wcell = workCellDAO.getWorkCellById(combine.getCell().getId());
			
			//根据cellId 找到WorkCellToGroup
			hql = "from R_WorkCellToGroup where cellId = ?";
			
			
			R_WorkCellToGroup group = r_WorkCellToGroupDAO.getR_WorkCellToGroup(hql,new String[] {wcell.getId()});
//			R_WorkCellToGroup group = groups.get(0);
			
			//将数据set到WorkStationConfig中
			WorkStationConfig config = new WorkStationConfig();
			config.setWorkStationId(wcell.getId());
			config.setEquipmentGroupId(combine.getId());
			config.setEquipmentGroupName(combine.getCbName());
			config.setEquipmentGroupId(group.getWorkGroupId());
			config.setPersonGroupName(group.getGroupName());
			
			configs.add(config);
		}
		
		//将configs集合转化成JSON
		JsonConfig jsonConfig = new JsonConfig();		//建立配置文件 
		jsonConfig.setIgnoreDefaultExcludes(false);		//设置默认忽略
		jsonConfig.registerJsonValueProcessor(java.util.Date.class,
				new DateJsonValueProcessor("yyyy-MM-dd"));	//设置时间的转换格式	
		JSONArray jsonArray = JSONArray.fromObject(configs);
				
		return jsonArray.toString();
	}

}






