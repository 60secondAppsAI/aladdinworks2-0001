package com.aladdinworks2.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.aladdinworks2.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.aladdinworks2.domain.MaintenanceLog;
import com.aladdinworks2.dto.MaintenanceLogDTO;
import com.aladdinworks2.dto.MaintenanceLogSearchDTO;
import com.aladdinworks2.dto.MaintenanceLogPageDTO;
import com.aladdinworks2.service.MaintenanceLogService;
import com.aladdinworks2.dto.common.RequestDTO;
import com.aladdinworks2.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/maintenanceLog")
@RestController
public class MaintenanceLogController {

	private final static Logger logger = LoggerFactory.getLogger(MaintenanceLogController.class);

	@Autowired
	MaintenanceLogService maintenanceLogService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<MaintenanceLog> getAll() {

		List<MaintenanceLog> maintenanceLogs = maintenanceLogService.findAll();
		
		return maintenanceLogs;	
	}

	@GetMapping(value = "/{maintenanceLogId}")
	@ResponseBody
	public MaintenanceLogDTO getMaintenanceLog(@PathVariable Integer maintenanceLogId) {
		
		return (maintenanceLogService.getMaintenanceLogDTOById(maintenanceLogId));
	}

 	@RequestMapping(value = "/addMaintenanceLog", method = RequestMethod.POST)
	public ResponseEntity<?> addMaintenanceLog(@RequestBody MaintenanceLogDTO maintenanceLogDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = maintenanceLogService.addMaintenanceLog(maintenanceLogDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/maintenanceLogs")
	public ResponseEntity<MaintenanceLogPageDTO> getMaintenanceLogs(MaintenanceLogSearchDTO maintenanceLogSearchDTO) {
 
		return maintenanceLogService.getMaintenanceLogs(maintenanceLogSearchDTO);
	}	

	@RequestMapping(value = "/updateMaintenanceLog", method = RequestMethod.POST)
	public ResponseEntity<?> updateMaintenanceLog(@RequestBody MaintenanceLogDTO maintenanceLogDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = maintenanceLogService.updateMaintenanceLog(maintenanceLogDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
