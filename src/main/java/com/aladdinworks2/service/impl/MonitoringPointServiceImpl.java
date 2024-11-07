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
import com.aladdinworks2.dao.MonitoringPointDAO;
import com.aladdinworks2.domain.MonitoringPoint;
import com.aladdinworks2.dto.MonitoringPointDTO;
import com.aladdinworks2.dto.MonitoringPointSearchDTO;
import com.aladdinworks2.dto.MonitoringPointPageDTO;
import com.aladdinworks2.dto.MonitoringPointConvertCriteriaDTO;
import com.aladdinworks2.dto.common.RequestDTO;
import com.aladdinworks2.dto.common.ResultDTO;
import com.aladdinworks2.service.MonitoringPointService;
import com.aladdinworks2.util.ControllerUtils;





@Service
public class MonitoringPointServiceImpl extends GenericServiceImpl<MonitoringPoint, Integer> implements MonitoringPointService {

    private final static Logger logger = LoggerFactory.getLogger(MonitoringPointServiceImpl.class);

	@Autowired
	MonitoringPointDAO monitoringPointDao;

	


	@Override
	public GenericDAO<MonitoringPoint, Integer> getDAO() {
		return (GenericDAO<MonitoringPoint, Integer>) monitoringPointDao;
	}
	
	public List<MonitoringPoint> findAll () {
		List<MonitoringPoint> monitoringPoints = monitoringPointDao.findAll();
		
		return monitoringPoints;	
		
	}

	public ResultDTO addMonitoringPoint(MonitoringPointDTO monitoringPointDTO, RequestDTO requestDTO) {

		MonitoringPoint monitoringPoint = new MonitoringPoint();

		monitoringPoint.setMonitoringPointId(monitoringPointDTO.getMonitoringPointId());


		monitoringPoint.setType(monitoringPointDTO.getType());


		monitoringPoint.setValue(monitoringPointDTO.getValue());


		monitoringPoint.setTimestamp(monitoringPointDTO.getTimestamp());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		monitoringPoint = monitoringPointDao.save(monitoringPoint);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<MonitoringPoint> getAllMonitoringPoints(Pageable pageable) {
		return monitoringPointDao.findAll(pageable);
	}

	public Page<MonitoringPoint> getAllMonitoringPoints(Specification<MonitoringPoint> spec, Pageable pageable) {
		return monitoringPointDao.findAll(spec, pageable);
	}

	public ResponseEntity<MonitoringPointPageDTO> getMonitoringPoints(MonitoringPointSearchDTO monitoringPointSearchDTO) {
	
			Integer monitoringPointId = monitoringPointSearchDTO.getMonitoringPointId(); 
 			String type = monitoringPointSearchDTO.getType(); 
   			String sortBy = monitoringPointSearchDTO.getSortBy();
			String sortOrder = monitoringPointSearchDTO.getSortOrder();
			String searchQuery = monitoringPointSearchDTO.getSearchQuery();
			Integer page = monitoringPointSearchDTO.getPage();
			Integer size = monitoringPointSearchDTO.getSize();

	        Specification<MonitoringPoint> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, monitoringPointId, "monitoringPointId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, type, "type"); 
			
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("type")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<MonitoringPoint> monitoringPoints = this.getAllMonitoringPoints(spec, pageable);
		
		//System.out.println(String.valueOf(monitoringPoints.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(monitoringPoints.getTotalPages()));
		
		List<MonitoringPoint> monitoringPointsList = monitoringPoints.getContent();
		
		MonitoringPointConvertCriteriaDTO convertCriteria = new MonitoringPointConvertCriteriaDTO();
		List<MonitoringPointDTO> monitoringPointDTOs = this.convertMonitoringPointsToMonitoringPointDTOs(monitoringPointsList,convertCriteria);
		
		MonitoringPointPageDTO monitoringPointPageDTO = new MonitoringPointPageDTO();
		monitoringPointPageDTO.setMonitoringPoints(monitoringPointDTOs);
		monitoringPointPageDTO.setTotalElements(monitoringPoints.getTotalElements());
		return ResponseEntity.ok(monitoringPointPageDTO);
	}

	public List<MonitoringPointDTO> convertMonitoringPointsToMonitoringPointDTOs(List<MonitoringPoint> monitoringPoints, MonitoringPointConvertCriteriaDTO convertCriteria) {
		
		List<MonitoringPointDTO> monitoringPointDTOs = new ArrayList<MonitoringPointDTO>();
		
		for (MonitoringPoint monitoringPoint : monitoringPoints) {
			monitoringPointDTOs.add(convertMonitoringPointToMonitoringPointDTO(monitoringPoint,convertCriteria));
		}
		
		return monitoringPointDTOs;

	}
	
	public MonitoringPointDTO convertMonitoringPointToMonitoringPointDTO(MonitoringPoint monitoringPoint, MonitoringPointConvertCriteriaDTO convertCriteria) {
		
		MonitoringPointDTO monitoringPointDTO = new MonitoringPointDTO();
		
		monitoringPointDTO.setMonitoringPointId(monitoringPoint.getMonitoringPointId());

	
		monitoringPointDTO.setType(monitoringPoint.getType());

	
		monitoringPointDTO.setValue(monitoringPoint.getValue());

	
		monitoringPointDTO.setTimestamp(monitoringPoint.getTimestamp());

	

		
		return monitoringPointDTO;
	}

	public ResultDTO updateMonitoringPoint(MonitoringPointDTO monitoringPointDTO, RequestDTO requestDTO) {
		
		MonitoringPoint monitoringPoint = monitoringPointDao.getById(monitoringPointDTO.getMonitoringPointId());

		monitoringPoint.setMonitoringPointId(ControllerUtils.setValue(monitoringPoint.getMonitoringPointId(), monitoringPointDTO.getMonitoringPointId()));

		monitoringPoint.setType(ControllerUtils.setValue(monitoringPoint.getType(), monitoringPointDTO.getType()));

		monitoringPoint.setValue(ControllerUtils.setValue(monitoringPoint.getValue(), monitoringPointDTO.getValue()));

		monitoringPoint.setTimestamp(ControllerUtils.setValue(monitoringPoint.getTimestamp(), monitoringPointDTO.getTimestamp()));



        monitoringPoint = monitoringPointDao.save(monitoringPoint);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public MonitoringPointDTO getMonitoringPointDTOById(Integer monitoringPointId) {
	
		MonitoringPoint monitoringPoint = monitoringPointDao.getById(monitoringPointId);
			
		
		MonitoringPointConvertCriteriaDTO convertCriteria = new MonitoringPointConvertCriteriaDTO();
		return(this.convertMonitoringPointToMonitoringPointDTO(monitoringPoint,convertCriteria));
	}







}
