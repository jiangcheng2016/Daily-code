package com.risen.fmes.dao;

import java.util.List;

import com.risen.fmes.model.CardToPerson;

public interface CardToPersonDAO {

	List<CardToPerson> getCardToPersons(String hql,String[] params);
}
