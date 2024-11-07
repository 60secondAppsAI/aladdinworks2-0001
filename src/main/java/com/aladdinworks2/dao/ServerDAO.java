package com.aladdinworks2.dao;

import java.util.List;

import com.aladdinworks2.dao.GenericDAO;
import com.aladdinworks2.domain.Server;





public interface ServerDAO extends GenericDAO<Server, Integer> {
  
	List<Server> findAll();
	






}


