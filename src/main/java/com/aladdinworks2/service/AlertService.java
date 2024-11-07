package com.aladdinworks2.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks2.domain.Alert;
import com.aladdinworks2.dto.AlertDTO;
import com.aladdinworks2.dto.AlertSearchDTO;
import com.aladdinworks2.dto.AlertPageDTO;
import com.aladdinworks2.dto.AlertConvertCriteriaDTO;
import com.aladdinworks2.service.GenericService;
import com.aladdinworks2.dto.common.RequestDTO;
import com.aladdinworks2.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AlertService extends GenericService<Alert, Integer> {

	List<Alert> findAll();

	ResultDTO addAlert(AlertDTO alertDTO, RequestDTO requestDTO);

	ResultDTO updateAlert(AlertDTO alertDTO, RequestDTO requestDTO);

    Page<Alert> getAllAlerts(Pageable pageable);

    Page<Alert> getAllAlerts(Specification<Alert> spec, Pageable pageable);

	ResponseEntity<AlertPageDTO> getAlerts(AlertSearchDTO alertSearchDTO);
	
	List<AlertDTO> convertAlertsToAlertDTOs(List<Alert> alerts, AlertConvertCriteriaDTO convertCriteria);

	AlertDTO getAlertDTOById(Integer alertId);







}





