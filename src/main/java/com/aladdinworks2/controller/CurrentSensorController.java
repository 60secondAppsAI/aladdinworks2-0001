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

import com.aladdinworks2.domain.CurrentSensor;
import com.aladdinworks2.dto.CurrentSensorDTO;
import com.aladdinworks2.dto.CurrentSensorSearchDTO;
import com.aladdinworks2.dto.CurrentSensorPageDTO;
import com.aladdinworks2.service.CurrentSensorService;
import com.aladdinworks2.dto.common.RequestDTO;
import com.aladdinworks2.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/currentSensor")
@RestController
public class CurrentSensorController {

	private final static Logger logger = LoggerFactory.getLogger(CurrentSensorController.class);

	@Autowired
	CurrentSensorService currentSensorService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<CurrentSensor> getAll() {

		List<CurrentSensor> currentSensors = currentSensorService.findAll();
		
		return currentSensors;	
	}

	@GetMapping(value = "/{currentSensorId}")
	@ResponseBody
	public CurrentSensorDTO getCurrentSensor(@PathVariable Integer currentSensorId) {
		
		return (currentSensorService.getCurrentSensorDTOById(currentSensorId));
	}

 	@RequestMapping(value = "/addCurrentSensor", method = RequestMethod.POST)
	public ResponseEntity<?> addCurrentSensor(@RequestBody CurrentSensorDTO currentSensorDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = currentSensorService.addCurrentSensor(currentSensorDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/currentSensors")
	public ResponseEntity<CurrentSensorPageDTO> getCurrentSensors(CurrentSensorSearchDTO currentSensorSearchDTO) {
 
		return currentSensorService.getCurrentSensors(currentSensorSearchDTO);
	}	

	@RequestMapping(value = "/updateCurrentSensor", method = RequestMethod.POST)
	public ResponseEntity<?> updateCurrentSensor(@RequestBody CurrentSensorDTO currentSensorDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = currentSensorService.updateCurrentSensor(currentSensorDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
