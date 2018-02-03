package com.risen.fmes.service.impl;



import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.risen.dao.impl.BaseHibDAOImpl;
import com.risen.fmes.dao.CardToPersonDAO;
import com.risen.fmes.model.CardToPerson;
import com.risen.fmes.service.CardToPersonService;
import com.risen.utils.DateJsonValueProcessor;

public class CardToPersonServiceImpl extends BaseHibDAOImpl implements CardToPersonService{


	private CardToPersonDAO cardToPersonDAO;
	
	
	public CardToPersonDAO getCardToPersonDAO() {
		return cardToPersonDAO;
	}

	public void setCardToPersonDAO(CardToPersonDAO cardToPersonDAO) {
		this.cardToPersonDAO = cardToPersonDAO;
	}



	public String getCardToPersons() {

		String hql = "from CardToPerson";
		List<CardToPerson> configs = cardToPersonDAO.getCardToPersons(hql, null);
		
		//转成JSON
		JsonConfig jsonConfig = new JsonConfig();		//配置文件
		jsonConfig.setIgnoreDefaultExcludes(false);		//设置默认忽略
		jsonConfig.registerJsonValueProcessor(java.util.Date.class,
				new DateJsonValueProcessor("yyyy-MM-dd"));	//设置时间的转换格式	
		jsonConfig.setExcludes(new String[] {"creator","person"});
		
		JSONArray jsonArray = JSONArray.fromObject(configs, jsonConfig);
				
		return jsonArray.toString();
	}

}




