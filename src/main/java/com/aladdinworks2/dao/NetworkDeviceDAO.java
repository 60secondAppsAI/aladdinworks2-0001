package com.aladdinworks2.dao;

import java.util.List;

import com.aladdinworks2.dao.GenericDAO;
import com.aladdinworks2.domain.NetworkDevice;





public interface NetworkDeviceDAO extends GenericDAO<NetworkDevice, Integer> {
  
	List<NetworkDevice> findAll();
	






}


