package com.risen.fmes.dao.impl;

import java.util.List;

import com.risen.dao.impl.BaseHibDAOImpl;
import com.risen.fmes.dao.Client_Equipment_ConfigDAO;
import com.risen.fmes.model.ClientEquipmentConfig;
import com.risen.fmes.model.Client_Equipment_Config;

public class Client_Equipment_ConfigDAOImpl extends BaseHibDAOImpl implements Client_Equipment_ConfigDAO {

	@Override
	public void save(Client_Equipment_Config client_Equipment_Config) {
		// TODO Auto-generated method stub
		super.save(client_Equipment_Config);
	}

	public List<Client_Equipment_Config> getClient_Equipment_Config(String hql,
			String[] params) {
		// TODO Auto-generated method stub
		return super.search(hql, params);
	}
  
}
