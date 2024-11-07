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

import com.aladdinworks2.domain.NetworkDevice;
import com.aladdinworks2.dto.NetworkDeviceDTO;
import com.aladdinworks2.dto.NetworkDeviceSearchDTO;
import com.aladdinworks2.dto.NetworkDevicePageDTO;
import com.aladdinworks2.service.NetworkDeviceService;
import com.aladdinworks2.dto.common.RequestDTO;
import com.aladdinworks2.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/networkDevice")
@RestController
public class NetworkDeviceController {

	private final static Logger logger = LoggerFactory.getLogger(NetworkDeviceController.class);

	@Autowired
	NetworkDeviceService networkDeviceService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<NetworkDevice> getAll() {

		List<NetworkDevice> networkDevices = networkDeviceService.findAll();
		
		return networkDevices;	
	}

	@GetMapping(value = "/{networkDeviceId}")
	@ResponseBody
	public NetworkDeviceDTO getNetworkDevice(@PathVariable Integer networkDeviceId) {
		
		return (networkDeviceService.getNetworkDeviceDTOById(networkDeviceId));
	}

 	@RequestMapping(value = "/addNetworkDevice", method = RequestMethod.POST)
	public ResponseEntity<?> addNetworkDevice(@RequestBody NetworkDeviceDTO networkDeviceDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = networkDeviceService.addNetworkDevice(networkDeviceDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/networkDevices")
	public ResponseEntity<NetworkDevicePageDTO> getNetworkDevices(NetworkDeviceSearchDTO networkDeviceSearchDTO) {
 
		return networkDeviceService.getNetworkDevices(networkDeviceSearchDTO);
	}	

	@RequestMapping(value = "/updateNetworkDevice", method = RequestMethod.POST)
	public ResponseEntity<?> updateNetworkDevice(@RequestBody NetworkDeviceDTO networkDeviceDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = networkDeviceService.updateNetworkDevice(networkDeviceDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
