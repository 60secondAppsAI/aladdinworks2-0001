package com.aladdinworks2.dao;

import java.util.List;

import com.aladdinworks2.dao.GenericDAO;
import com.aladdinworks2.domain.Alert;





public interface AlertDAO extends GenericDAO<Alert, Integer> {
  
	List<Alert> findAll();
	






}


