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

import com.aladdinworks2.domain.DataCenter;
import com.aladdinworks2.dto.DataCenterDTO;
import com.aladdinworks2.dto.DataCenterSearchDTO;
import com.aladdinworks2.dto.DataCenterPageDTO;
import com.aladdinworks2.service.DataCenterService;
import com.aladdinworks2.dto.common.RequestDTO;
import com.aladdinworks2.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/dataCenter")
@RestController
public class DataCenterController {

	private final static Logger logger = LoggerFactory.getLogger(DataCenterController.class);

	@Autowired
	DataCenterService dataCenterService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<DataCenter> getAll() {

		List<DataCenter> dataCenters = dataCenterService.findAll();
		
		return dataCenters;	
	}

	@GetMapping(value = "/{dataCenterId}")
	@ResponseBody
	public DataCenterDTO getDataCenter(@PathVariable Integer dataCenterId) {
		
		return (dataCenterService.getDataCenterDTOById(dataCenterId));
	}

 	@RequestMapping(value = "/addDataCenter", method = RequestMethod.POST)
	public ResponseEntity<?> addDataCenter(@RequestBody DataCenterDTO dataCenterDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = dataCenterService.addDataCenter(dataCenterDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/dataCenters")
	public ResponseEntity<DataCenterPageDTO> getDataCenters(DataCenterSearchDTO dataCenterSearchDTO) {
 
		return dataCenterService.getDataCenters(dataCenterSearchDTO);
	}	

	@RequestMapping(value = "/updateDataCenter", method = RequestMethod.POST)
	public ResponseEntity<?> updateDataCenter(@RequestBody DataCenterDTO dataCenterDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = dataCenterService.updateDataCenter(dataCenterDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
