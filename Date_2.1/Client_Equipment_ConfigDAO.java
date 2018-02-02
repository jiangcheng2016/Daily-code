package com.risen.fmes.dao;

import java.util.List;


import com.risen.fmes.model.Client_Equipment_Config;

public interface Client_Equipment_ConfigDAO {
    void save(Client_Equipment_Config client_Equipment_Config);
    
    List<Client_Equipment_Config> getClient_Equipment_Config(String hql,String[] params);
    
}
