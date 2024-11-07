package com.aladdinworks2.dao;

import java.util.List;

import com.aladdinworks2.dao.GenericDAO;
import com.aladdinworks2.domain.Equipment;





public interface EquipmentDAO extends GenericDAO<Equipment, Integer> {
  
	List<Equipment> findAll();
	






}


