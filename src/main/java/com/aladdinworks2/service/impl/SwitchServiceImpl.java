package com.aladdinworks2.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.aladdinworks2.dao.GenericDAO;
import com.aladdinworks2.service.GenericService;
import com.aladdinworks2.service.impl.GenericServiceImpl;
import com.aladdinworks2.dao.SwitchDAO;
import com.aladdinworks2.domain.Switch;
import com.aladdinworks2.dto.SwitchDTO;
import com.aladdinworks2.dto.SwitchSearchDTO;
import com.aladdinworks2.dto.SwitchPageDTO;
import com.aladdinworks2.dto.SwitchConvertCriteriaDTO;
import com.aladdinworks2.dto.common.RequestDTO;
import com.aladdinworks2.dto.common.ResultDTO;
import com.aladdinworks2.service.SwitchService;
import com.aladdinworks2.util.ControllerUtils;





@Service
public class SwitchServiceImpl extends GenericServiceImpl<Switch, Integer> implements SwitchService {

    private final static Logger logger = LoggerFactory.getLogger(SwitchServiceImpl.class);

	@Autowired
	SwitchDAO switchDao;

	


	@Override
	public GenericDAO<Switch, Integer> getDAO() {
		return (GenericDAO<Switch, Integer>) switchDao;
	}
	
	public List<Switch> findAll () {
		List<Switch> switchs = switchDao.findAll();
		
		return switchs;	
		
	}

	public ResultDTO addSwitch(SwitchDTO switchDTO, RequestDTO requestDTO) {

		Switch switch = new Switch();

		switch.setSwitchId(switchDTO.getSwitchId());


		switch.setPorts(switchDTO.getPorts());


		switch.setManufacturer(switchDTO.getManufacturer());


		switch.setModel(switchDTO.getModel());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		switch = switchDao.save(switch);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Switch> getAllSwitchs(Pageable pageable) {
		return switchDao.findAll(pageable);
	}

	public Page<Switch> getAllSwitchs(Specification<Switch> spec, Pageable pageable) {
		return switchDao.findAll(spec, pageable);
	}

	public ResponseEntity<SwitchPageDTO> getSwitchs(SwitchSearchDTO switchSearchDTO) {
	
			Integer switchId = switchSearchDTO.getSwitchId(); 
 			Integer ports = switchSearchDTO.getPorts(); 
 			String manufacturer = switchSearchDTO.getManufacturer(); 
 			String model = switchSearchDTO.getModel(); 
 			String sortBy = switchSearchDTO.getSortBy();
			String sortOrder = switchSearchDTO.getSortOrder();
			String searchQuery = switchSearchDTO.getSearchQuery();
			Integer page = switchSearchDTO.getPage();
			Integer size = switchSearchDTO.getSize();

	        Specification<Switch> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, switchId, "switchId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, ports, "ports"); 
			
			spec = ControllerUtils.andIfNecessary(spec, manufacturer, "manufacturer"); 
			
			spec = ControllerUtils.andIfNecessary(spec, model, "model"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("manufacturer")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("model")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Switch> switchs = this.getAllSwitchs(spec, pageable);
		
		//System.out.println(String.valueOf(switchs.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(switchs.getTotalPages()));
		
		List<Switch> switchsList = switchs.getContent();
		
		SwitchConvertCriteriaDTO convertCriteria = new SwitchConvertCriteriaDTO();
		List<SwitchDTO> switchDTOs = this.convertSwitchsToSwitchDTOs(switchsList,convertCriteria);
		
		SwitchPageDTO switchPageDTO = new SwitchPageDTO();
		switchPageDTO.setSwitchs(switchDTOs);
		switchPageDTO.setTotalElements(switchs.getTotalElements());
		return ResponseEntity.ok(switchPageDTO);
	}

	public List<SwitchDTO> convertSwitchsToSwitchDTOs(List<Switch> switchs, SwitchConvertCriteriaDTO convertCriteria) {
		
		List<SwitchDTO> switchDTOs = new ArrayList<SwitchDTO>();
		
		for (Switch switch : switchs) {
			switchDTOs.add(convertSwitchToSwitchDTO(switch,convertCriteria));
		}
		
		return switchDTOs;

	}
	
	public SwitchDTO convertSwitchToSwitchDTO(Switch switch, SwitchConvertCriteriaDTO convertCriteria) {
		
		SwitchDTO switchDTO = new SwitchDTO();
		
		switchDTO.setSwitchId(switch.getSwitchId());

	
		switchDTO.setPorts(switch.getPorts());

	
		switchDTO.setManufacturer(switch.getManufacturer());

	
		switchDTO.setModel(switch.getModel());

	

		
		return switchDTO;
	}

	public ResultDTO updateSwitch(SwitchDTO switchDTO, RequestDTO requestDTO) {
		
		Switch switch = switchDao.getById(switchDTO.getSwitchId());

		switch.setSwitchId(ControllerUtils.setValue(switch.getSwitchId(), switchDTO.getSwitchId()));

		switch.setPorts(ControllerUtils.setValue(switch.getPorts(), switchDTO.getPorts()));

		switch.setManufacturer(ControllerUtils.setValue(switch.getManufacturer(), switchDTO.getManufacturer()));

		switch.setModel(ControllerUtils.setValue(switch.getModel(), switchDTO.getModel()));



        switch = switchDao.save(switch);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public SwitchDTO getSwitchDTOById(Integer switchId) {
	
		Switch switch = switchDao.getById(switchId);
			
		
		SwitchConvertCriteriaDTO convertCriteria = new SwitchConvertCriteriaDTO();
		return(this.convertSwitchToSwitchDTO(switch,convertCriteria));
	}







}
