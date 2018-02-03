package com.risen.fmes.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.risen.dao.impl.BaseHibDAOImpl;
import com.risen.fmes.model.GlobalConfig;
import com.risen.fmes.service.GlobalConfigService;
import com.risen.utils.DateJsonValueProcessor;

public class GlobalConfigServiceImpl extends BaseHibDAOImpl implements GlobalConfigService {



	
	
	
	
	
	
	public String getGlobalConfigs() {
		
		//目前无数据，set数据测试接口
		List<GlobalConfig> configs = new ArrayList<GlobalConfig>();
		
		//先set两条数据
		GlobalConfig config = new GlobalConfig();
		//第一条
		config.setProductId("454125");
		config.setProductName("马哈鱼");
		config.setProcessId("455656");
		config.setFlowName("马哈鱼流程");
		configs.add(config);
		//第二条
		config.setProductId("123456");
		config.setProductName("红鱼");
		config.setProcessId("1234567");
		config.setFlowName("红鱼流程");
		configs.add(config);
		
		//转json
		JsonConfig jsonConfig = new JsonConfig();		//配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);		//设置默认忽略
		jsonConfig.registerJsonValueProcessor(java.util.Date.class,
				new DateJsonValueProcessor("yyyy-MM-dd"));	//设置时间的转换格式	
		JSONArray jsonArray = JSONArray.fromObject(configs, jsonConfig);
		
		return jsonArray.toString();
	}

	
	
	
	
	
	

}