package com.aladdinworks2.dao;

import java.util.List;

import com.aladdinworks2.dao.GenericDAO;
import com.aladdinworks2.domain.RackAsset;





public interface RackAssetDAO extends GenericDAO<RackAsset, Integer> {
  
	List<RackAsset> findAll();
	






}


