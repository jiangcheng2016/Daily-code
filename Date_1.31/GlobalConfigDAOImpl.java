package com.risen.fmes.dao.impl;

import com.risen.dao.impl.BaseHibDAOImpl;
import com.risen.fmes.dao.GlobalConfigDAO;
import com.risen.fmes.model.GlobalConfig;

public class GlobalConfigDAOImpl extends BaseHibDAOImpl implements GlobalConfigDAO {

	public GlobalConfig getGlobalConfigs(String hql, String[] params) {
		// TODO Auto-generated method stub
		Object o =  super.searchListsFromFirst(0, 1, hql).get(0);
		return (GlobalConfig)o;
	}
}
