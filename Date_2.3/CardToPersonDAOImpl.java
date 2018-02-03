package com.risen.fmes.dao.impl;

import java.util.List;

import com.risen.dao.impl.BaseHibDAOImpl;
import com.risen.fmes.dao.CardToPersonDAO;
import com.risen.fmes.model.CardToPerson;

public class CardToPersonDAOImpl extends BaseHibDAOImpl implements CardToPersonDAO{

	@Override
	public List<CardToPerson> getCardToPersons(String hql,String[] params) {
		// TODO Auto-generated method stub
		return super.search(hql, params);
	}

}
