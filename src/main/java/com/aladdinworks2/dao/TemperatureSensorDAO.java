package com.aladdinworks2.dao;

import java.util.List;

import com.aladdinworks2.dao.GenericDAO;
import com.aladdinworks2.domain.TemperatureSensor;





public interface TemperatureSensorDAO extends GenericDAO<TemperatureSensor, Integer> {
  
	List<TemperatureSensor> findAll();
	






}


