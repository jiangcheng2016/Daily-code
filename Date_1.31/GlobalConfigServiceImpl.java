package com.risen.fmes.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.risen.dao.impl.BaseHibDAOImpl;
import com.risen.fmes.dao.GlobalConfigDAO;
import com.risen.fmes.model.GlobalConfig;
import com.risen.fmes.service.GlobalConfigService;
import com.risen.utils.DateJsonValueProcessor;

public class GlobalConfigServiceImpl extends BaseHibDAOImpl implements GlobalConfigService {

	private GlobalConfigDAO globalConfigDAO;

	public GlobalConfigDAO getGlobalConfigDAO() {
		return globalConfigDAO;
	}
	public void setGlobalConfigDAO(GlobalConfigDAO globalConfigDAO) {
		this.globalConfigDAO = globalConfigDAO;
	}

	public String getGlobalConfigs() {
		//通过hql到数据库查询
		String hql = "from GlobalConfig order by modifiedDate desc";
		GlobalConfig config = globalConfigDAO.getGlobalConfigs(hql, null);
		
		//将String 转成json
		JsonConfig jsonConfig = new JsonConfig();	//建立配置文件	
		jsonConfig.setIgnoreDefaultExcludes(false);	//设置默认忽略
		
		jsonConfig.registerJsonValueProcessor(java.util.Date.class,
				new DateJsonValueProcessor("yyyy-MM-dd"));	//设置时间的转换格式
		jsonConfig.setExcludes(new String[]{"creator"});
		
		JSONObject jsonObject = JSONObject.fromObject(config, jsonConfig);	//将list集合转化为JSONArray
			
		return jsonObject.toString();
	}






	

}