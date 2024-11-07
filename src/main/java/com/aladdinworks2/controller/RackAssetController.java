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

import com.aladdinworks2.domain.RackAsset;
import com.aladdinworks2.dto.RackAssetDTO;
import com.aladdinworks2.dto.RackAssetSearchDTO;
import com.aladdinworks2.dto.RackAssetPageDTO;
import com.aladdinworks2.service.RackAssetService;
import com.aladdinworks2.dto.common.RequestDTO;
import com.aladdinworks2.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/rackAsset")
@RestController
public class RackAssetController {

	private final static Logger logger = LoggerFactory.getLogger(RackAssetController.class);

	@Autowired
	RackAssetService rackAssetService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<RackAsset> getAll() {

		List<RackAsset> rackAssets = rackAssetService.findAll();
		
		return rackAssets;	
	}

	@GetMapping(value = "/{rackAssetId}")
	@ResponseBody
	public RackAssetDTO getRackAsset(@PathVariable Integer rackAssetId) {
		
		return (rackAssetService.getRackAssetDTOById(rackAssetId));
	}

 	@RequestMapping(value = "/addRackAsset", method = RequestMethod.POST)
	public ResponseEntity<?> addRackAsset(@RequestBody RackAssetDTO rackAssetDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = rackAssetService.addRackAsset(rackAssetDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/rackAssets")
	public ResponseEntity<RackAssetPageDTO> getRackAssets(RackAssetSearchDTO rackAssetSearchDTO) {
 
		return rackAssetService.getRackAssets(rackAssetSearchDTO);
	}	

	@RequestMapping(value = "/updateRackAsset", method = RequestMethod.POST)
	public ResponseEntity<?> updateRackAsset(@RequestBody RackAssetDTO rackAssetDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = rackAssetService.updateRackAsset(rackAssetDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
