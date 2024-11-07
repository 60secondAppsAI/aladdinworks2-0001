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
import com.aladdinworks2.dao.CoolingUnitDAO;
import com.aladdinworks2.domain.CoolingUnit;
import com.aladdinworks2.dto.CoolingUnitDTO;
import com.aladdinworks2.dto.CoolingUnitSearchDTO;
import com.aladdinworks2.dto.CoolingUnitPageDTO;
import com.aladdinworks2.dto.CoolingUnitConvertCriteriaDTO;
import com.aladdinworks2.dto.common.RequestDTO;
import com.aladdinworks2.dto.common.ResultDTO;
import com.aladdinworks2.service.CoolingUnitService;
import com.aladdinworks2.util.ControllerUtils;





@Service
public class CoolingUnitServiceImpl extends GenericServiceImpl<CoolingUnit, Integer> implements CoolingUnitService {

    private final static Logger logger = LoggerFactory.getLogger(CoolingUnitServiceImpl.class);

	@Autowired
	CoolingUnitDAO coolingUnitDao;

	


	@Override
	public GenericDAO<CoolingUnit, Integer> getDAO() {
		return (GenericDAO<CoolingUnit, Integer>) coolingUnitDao;
	}
	
	public List<CoolingUnit> findAll () {
		List<CoolingUnit> coolingUnits = coolingUnitDao.findAll();
		
		return coolingUnits;	
		
	}

	public ResultDTO addCoolingUnit(CoolingUnitDTO coolingUnitDTO, RequestDTO requestDTO) {

		CoolingUnit coolingUnit = new CoolingUnit();

		coolingUnit.setCoolingUnitId(coolingUnitDTO.getCoolingUnitId());


		coolingUnit.setCapacity(coolingUnitDTO.getCapacity());


		coolingUnit.setManufacturer(coolingUnitDTO.getManufacturer());


		coolingUnit.setModel(coolingUnitDTO.getModel());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		coolingUnit = coolingUnitDao.save(coolingUnit);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<CoolingUnit> getAllCoolingUnits(Pageable pageable) {
		return coolingUnitDao.findAll(pageable);
	}

	public Page<CoolingUnit> getAllCoolingUnits(Specification<CoolingUnit> spec, Pageable pageable) {
		return coolingUnitDao.findAll(spec, pageable);
	}

	public ResponseEntity<CoolingUnitPageDTO> getCoolingUnits(CoolingUnitSearchDTO coolingUnitSearchDTO) {
	
			Integer coolingUnitId = coolingUnitSearchDTO.getCoolingUnitId(); 
  			String manufacturer = coolingUnitSearchDTO.getManufacturer(); 
 			String model = coolingUnitSearchDTO.getModel(); 
 			String sortBy = coolingUnitSearchDTO.getSortBy();
			String sortOrder = coolingUnitSearchDTO.getSortOrder();
			String searchQuery = coolingUnitSearchDTO.getSearchQuery();
			Integer page = coolingUnitSearchDTO.getPage();
			Integer size = coolingUnitSearchDTO.getSize();

	        Specification<CoolingUnit> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, coolingUnitId, "coolingUnitId"); 
			
			
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

		Page<CoolingUnit> coolingUnits = this.getAllCoolingUnits(spec, pageable);
		
		//System.out.println(String.valueOf(coolingUnits.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(coolingUnits.getTotalPages()));
		
		List<CoolingUnit> coolingUnitsList = coolingUnits.getContent();
		
		CoolingUnitConvertCriteriaDTO convertCriteria = new CoolingUnitConvertCriteriaDTO();
		List<CoolingUnitDTO> coolingUnitDTOs = this.convertCoolingUnitsToCoolingUnitDTOs(coolingUnitsList,convertCriteria);
		
		CoolingUnitPageDTO coolingUnitPageDTO = new CoolingUnitPageDTO();
		coolingUnitPageDTO.setCoolingUnits(coolingUnitDTOs);
		coolingUnitPageDTO.setTotalElements(coolingUnits.getTotalElements());
		return ResponseEntity.ok(coolingUnitPageDTO);
	}

	public List<CoolingUnitDTO> convertCoolingUnitsToCoolingUnitDTOs(List<CoolingUnit> coolingUnits, CoolingUnitConvertCriteriaDTO convertCriteria) {
		
		List<CoolingUnitDTO> coolingUnitDTOs = new ArrayList<CoolingUnitDTO>();
		
		for (CoolingUnit coolingUnit : coolingUnits) {
			coolingUnitDTOs.add(convertCoolingUnitToCoolingUnitDTO(coolingUnit,convertCriteria));
		}
		
		return coolingUnitDTOs;

	}
	
	public CoolingUnitDTO convertCoolingUnitToCoolingUnitDTO(CoolingUnit coolingUnit, CoolingUnitConvertCriteriaDTO convertCriteria) {
		
		CoolingUnitDTO coolingUnitDTO = new CoolingUnitDTO();
		
		coolingUnitDTO.setCoolingUnitId(coolingUnit.getCoolingUnitId());

	
		coolingUnitDTO.setCapacity(coolingUnit.getCapacity());

	
		coolingUnitDTO.setManufacturer(coolingUnit.getManufacturer());

	
		coolingUnitDTO.setModel(coolingUnit.getModel());

	

		
		return coolingUnitDTO;
	}

	public ResultDTO updateCoolingUnit(CoolingUnitDTO coolingUnitDTO, RequestDTO requestDTO) {
		
		CoolingUnit coolingUnit = coolingUnitDao.getById(coolingUnitDTO.getCoolingUnitId());

		coolingUnit.setCoolingUnitId(ControllerUtils.setValue(coolingUnit.getCoolingUnitId(), coolingUnitDTO.getCoolingUnitId()));

		coolingUnit.setCapacity(ControllerUtils.setValue(coolingUnit.getCapacity(), coolingUnitDTO.getCapacity()));

		coolingUnit.setManufacturer(ControllerUtils.setValue(coolingUnit.getManufacturer(), coolingUnitDTO.getManufacturer()));

		coolingUnit.setModel(ControllerUtils.setValue(coolingUnit.getModel(), coolingUnitDTO.getModel()));



        coolingUnit = coolingUnitDao.save(coolingUnit);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CoolingUnitDTO getCoolingUnitDTOById(Integer coolingUnitId) {
	
		CoolingUnit coolingUnit = coolingUnitDao.getById(coolingUnitId);
			
		
		CoolingUnitConvertCriteriaDTO convertCriteria = new CoolingUnitConvertCriteriaDTO();
		return(this.convertCoolingUnitToCoolingUnitDTO(coolingUnit,convertCriteria));
	}







}
