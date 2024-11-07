package com.aladdinworks2.dao;

import java.util.List;

import com.aladdinworks2.dao.GenericDAO;
import com.aladdinworks2.domain.DataCenter;





public interface DataCenterDAO extends GenericDAO<DataCenter, Integer> {
  
	List<DataCenter> findAll();
	






}


