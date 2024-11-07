package com.aladdinworks2.dao;

import java.util.List;

import com.aladdinworks2.dao.GenericDAO;
import com.aladdinworks2.domain.Generator;





public interface GeneratorDAO extends GenericDAO<Generator, Integer> {
  
	List<Generator> findAll();
	






}


