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
import com.aladdinworks2.dao.MaintenanceLogDAO;
import com.aladdinworks2.domain.MaintenanceLog;
import com.aladdinworks2.dto.MaintenanceLogDTO;
import com.aladdinworks2.dto.MaintenanceLogSearchDTO;
import com.aladdinworks2.dto.MaintenanceLogPageDTO;
import com.aladdinworks2.dto.MaintenanceLogConvertCriteriaDTO;
import com.aladdinworks2.dto.common.RequestDTO;
import com.aladdinworks2.dto.common.ResultDTO;
import com.aladdinworks2.service.MaintenanceLogService;
import com.aladdinworks2.util.ControllerUtils;





@Service
public class MaintenanceLogServiceImpl extends GenericServiceImpl<MaintenanceLog, Integer> implements MaintenanceLogService {

    private final static Logger logger = LoggerFactory.getLogger(MaintenanceLogServiceImpl.class);

	@Autowired
	MaintenanceLogDAO maintenanceLogDao;

	


	@Override
	public GenericDAO<MaintenanceLog, Integer> getDAO() {
		return (GenericDAO<MaintenanceLog, Integer>) maintenanceLogDao;
	}
	
	public List<MaintenanceLog> findAll () {
		List<MaintenanceLog> maintenanceLogs = maintenanceLogDao.findAll();
		
		return maintenanceLogs;	
		
	}

	public ResultDTO addMaintenanceLog(MaintenanceLogDTO maintenanceLogDTO, RequestDTO requestDTO) {

		MaintenanceLog maintenanceLog = new MaintenanceLog();

		maintenanceLog.setMaintenanceLogId(maintenanceLogDTO.getMaintenanceLogId());


		maintenanceLog.setDescription(maintenanceLogDTO.getDescription());


		maintenanceLog.setDate(maintenanceLogDTO.getDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		maintenanceLog = maintenanceLogDao.save(maintenanceLog);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<MaintenanceLog> getAllMaintenanceLogs(Pageable pageable) {
		return maintenanceLogDao.findAll(pageable);
	}

	public Page<MaintenanceLog> getAllMaintenanceLogs(Specification<MaintenanceLog> spec, Pageable pageable) {
		return maintenanceLogDao.findAll(spec, pageable);
	}

	public ResponseEntity<MaintenanceLogPageDTO> getMaintenanceLogs(MaintenanceLogSearchDTO maintenanceLogSearchDTO) {
	
			Integer maintenanceLogId = maintenanceLogSearchDTO.getMaintenanceLogId(); 
 			String description = maintenanceLogSearchDTO.getDescription(); 
  			String sortBy = maintenanceLogSearchDTO.getSortBy();
			String sortOrder = maintenanceLogSearchDTO.getSortOrder();
			String searchQuery = maintenanceLogSearchDTO.getSearchQuery();
			Integer page = maintenanceLogSearchDTO.getPage();
			Integer size = maintenanceLogSearchDTO.getSize();

	        Specification<MaintenanceLog> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, maintenanceLogId, "maintenanceLogId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<MaintenanceLog> maintenanceLogs = this.getAllMaintenanceLogs(spec, pageable);
		
		//System.out.println(String.valueOf(maintenanceLogs.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(maintenanceLogs.getTotalPages()));
		
		List<MaintenanceLog> maintenanceLogsList = maintenanceLogs.getContent();
		
		MaintenanceLogConvertCriteriaDTO convertCriteria = new MaintenanceLogConvertCriteriaDTO();
		List<MaintenanceLogDTO> maintenanceLogDTOs = this.convertMaintenanceLogsToMaintenanceLogDTOs(maintenanceLogsList,convertCriteria);
		
		MaintenanceLogPageDTO maintenanceLogPageDTO = new MaintenanceLogPageDTO();
		maintenanceLogPageDTO.setMaintenanceLogs(maintenanceLogDTOs);
		maintenanceLogPageDTO.setTotalElements(maintenanceLogs.getTotalElements());
		return ResponseEntity.ok(maintenanceLogPageDTO);
	}

	public List<MaintenanceLogDTO> convertMaintenanceLogsToMaintenanceLogDTOs(List<MaintenanceLog> maintenanceLogs, MaintenanceLogConvertCriteriaDTO convertCriteria) {
		
		List<MaintenanceLogDTO> maintenanceLogDTOs = new ArrayList<MaintenanceLogDTO>();
		
		for (MaintenanceLog maintenanceLog : maintenanceLogs) {
			maintenanceLogDTOs.add(convertMaintenanceLogToMaintenanceLogDTO(maintenanceLog,convertCriteria));
		}
		
		return maintenanceLogDTOs;

	}
	
	public MaintenanceLogDTO convertMaintenanceLogToMaintenanceLogDTO(MaintenanceLog maintenanceLog, MaintenanceLogConvertCriteriaDTO convertCriteria) {
		
		MaintenanceLogDTO maintenanceLogDTO = new MaintenanceLogDTO();
		
		maintenanceLogDTO.setMaintenanceLogId(maintenanceLog.getMaintenanceLogId());

	
		maintenanceLogDTO.setDescription(maintenanceLog.getDescription());

	
		maintenanceLogDTO.setDate(maintenanceLog.getDate());

	

		
		return maintenanceLogDTO;
	}

	public ResultDTO updateMaintenanceLog(MaintenanceLogDTO maintenanceLogDTO, RequestDTO requestDTO) {
		
		MaintenanceLog maintenanceLog = maintenanceLogDao.getById(maintenanceLogDTO.getMaintenanceLogId());

		maintenanceLog.setMaintenanceLogId(ControllerUtils.setValue(maintenanceLog.getMaintenanceLogId(), maintenanceLogDTO.getMaintenanceLogId()));

		maintenanceLog.setDescription(ControllerUtils.setValue(maintenanceLog.getDescription(), maintenanceLogDTO.getDescription()));

		maintenanceLog.setDate(ControllerUtils.setValue(maintenanceLog.getDate(), maintenanceLogDTO.getDate()));



        maintenanceLog = maintenanceLogDao.save(maintenanceLog);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public MaintenanceLogDTO getMaintenanceLogDTOById(Integer maintenanceLogId) {
	
		MaintenanceLog maintenanceLog = maintenanceLogDao.getById(maintenanceLogId);
			
		
		MaintenanceLogConvertCriteriaDTO convertCriteria = new MaintenanceLogConvertCriteriaDTO();
		return(this.convertMaintenanceLogToMaintenanceLogDTO(maintenanceLog,convertCriteria));
	}







}
