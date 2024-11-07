package com.aladdinworks2.dao;

import java.util.List;

import com.aladdinworks2.dao.GenericDAO;
import com.aladdinworks2.domain.Switch;





public interface SwitchDAO extends GenericDAO<Switch, Integer> {
  
	List<Switch> findAll();
	






}


