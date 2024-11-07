package com.aladdinworks2.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks2.domain.MaintenanceLog;
import com.aladdinworks2.dto.MaintenanceLogDTO;
import com.aladdinworks2.dto.MaintenanceLogSearchDTO;
import com.aladdinworks2.dto.MaintenanceLogPageDTO;
import com.aladdinworks2.dto.MaintenanceLogConvertCriteriaDTO;
import com.aladdinworks2.service.GenericService;
import com.aladdinworks2.dto.common.RequestDTO;
import com.aladdinworks2.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MaintenanceLogService extends GenericService<MaintenanceLog, Integer> {

	List<MaintenanceLog> findAll();

	ResultDTO addMaintenanceLog(MaintenanceLogDTO maintenanceLogDTO, RequestDTO requestDTO);

	ResultDTO updateMaintenanceLog(MaintenanceLogDTO maintenanceLogDTO, RequestDTO requestDTO);

    Page<MaintenanceLog> getAllMaintenanceLogs(Pageable pageable);

    Page<MaintenanceLog> getAllMaintenanceLogs(Specification<MaintenanceLog> spec, Pageable pageable);

	ResponseEntity<MaintenanceLogPageDTO> getMaintenanceLogs(MaintenanceLogSearchDTO maintenanceLogSearchDTO);
	
	List<MaintenanceLogDTO> convertMaintenanceLogsToMaintenanceLogDTOs(List<MaintenanceLog> maintenanceLogs, MaintenanceLogConvertCriteriaDTO convertCriteria);

	MaintenanceLogDTO getMaintenanceLogDTOById(Integer maintenanceLogId);







}





