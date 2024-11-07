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

import com.aladdinworks2.domain.Generator;
import com.aladdinworks2.dto.GeneratorDTO;
import com.aladdinworks2.dto.GeneratorSearchDTO;
import com.aladdinworks2.dto.GeneratorPageDTO;
import com.aladdinworks2.service.GeneratorService;
import com.aladdinworks2.dto.common.RequestDTO;
import com.aladdinworks2.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/generator")
@RestController
public class GeneratorController {

	private final static Logger logger = LoggerFactory.getLogger(GeneratorController.class);

	@Autowired
	GeneratorService generatorService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Generator> getAll() {

		List<Generator> generators = generatorService.findAll();
		
		return generators;	
	}

	@GetMapping(value = "/{generatorId}")
	@ResponseBody
	public GeneratorDTO getGenerator(@PathVariable Integer generatorId) {
		
		return (generatorService.getGeneratorDTOById(generatorId));
	}

 	@RequestMapping(value = "/addGenerator", method = RequestMethod.POST)
	public ResponseEntity<?> addGenerator(@RequestBody GeneratorDTO generatorDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = generatorService.addGenerator(generatorDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/generators")
	public ResponseEntity<GeneratorPageDTO> getGenerators(GeneratorSearchDTO generatorSearchDTO) {
 
		return generatorService.getGenerators(generatorSearchDTO);
	}	

	@RequestMapping(value = "/updateGenerator", method = RequestMethod.POST)
	public ResponseEntity<?> updateGenerator(@RequestBody GeneratorDTO generatorDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = generatorService.updateGenerator(generatorDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
