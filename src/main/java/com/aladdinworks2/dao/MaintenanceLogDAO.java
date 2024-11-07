package com.aladdinworks2.dao;

import java.util.List;

import com.aladdinworks2.dao.GenericDAO;
import com.aladdinworks2.domain.MaintenanceLog;





public interface MaintenanceLogDAO extends GenericDAO<MaintenanceLog, Integer> {
  
	List<MaintenanceLog> findAll();
	






}


