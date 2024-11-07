package com.aladdinworks2.dao;

import java.util.List;

import com.aladdinworks2.dao.GenericDAO;
import com.aladdinworks2.domain.Rack;





public interface RackDAO extends GenericDAO<Rack, Integer> {
  
	List<Rack> findAll();
	






}


