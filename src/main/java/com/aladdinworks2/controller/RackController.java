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

import com.aladdinworks2.domain.Rack;
import com.aladdinworks2.dto.RackDTO;
import com.aladdinworks2.dto.RackSearchDTO;
import com.aladdinworks2.dto.RackPageDTO;
import com.aladdinworks2.service.RackService;
import com.aladdinworks2.dto.common.RequestDTO;
import com.aladdinworks2.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/rack")
@RestController
public class RackController {

	private final static Logger logger = LoggerFactory.getLogger(RackController.class);

	@Autowired
	RackService rackService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Rack> getAll() {

		List<Rack> racks = rackService.findAll();
		
		return racks;	
	}

	@GetMapping(value = "/{rackId}")
	@ResponseBody
	public RackDTO getRack(@PathVariable Integer rackId) {
		
		return (rackService.getRackDTOById(rackId));
	}

 	@RequestMapping(value = "/addRack", method = RequestMethod.POST)
	public ResponseEntity<?> addRack(@RequestBody RackDTO rackDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = rackService.addRack(rackDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/racks")
	public ResponseEntity<RackPageDTO> getRacks(RackSearchDTO rackSearchDTO) {
 
		return rackService.getRacks(rackSearchDTO);
	}	

	@RequestMapping(value = "/updateRack", method = RequestMethod.POST)
	public ResponseEntity<?> updateRack(@RequestBody RackDTO rackDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = rackService.updateRack(rackDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
