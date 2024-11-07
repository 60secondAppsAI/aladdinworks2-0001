package com.aladdinworks2.dao;

import java.util.List;

import com.aladdinworks2.dao.GenericDAO;
import com.aladdinworks2.domain.CurrentSensor;





public interface CurrentSensorDAO extends GenericDAO<CurrentSensor, Integer> {
  
	List<CurrentSensor> findAll();
	






}


