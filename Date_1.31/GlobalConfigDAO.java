package com.risen.fmes.dao;

import java.util.List;

import com.risen.fmes.model.GlobalConfig;

/**
 * 全局配置数据库访问层
 * @author 姜程城
 */

public interface GlobalConfigDAO {
	GlobalConfig getGlobalConfigs(String hql,String[] params);
}
