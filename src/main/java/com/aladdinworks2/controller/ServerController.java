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

import com.aladdinworks2.domain.Server;
import com.aladdinworks2.dto.ServerDTO;
import com.aladdinworks2.dto.ServerSearchDTO;
import com.aladdinworks2.dto.ServerPageDTO;
import com.aladdinworks2.service.ServerService;
import com.aladdinworks2.dto.common.RequestDTO;
import com.aladdinworks2.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/server")
@RestController
public class ServerController {

	private final static Logger logger = LoggerFactory.getLogger(ServerController.class);

	@Autowired
	ServerService serverService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Server> getAll() {

		List<Server> servers = serverService.findAll();
		
		return servers;	
	}

	@GetMapping(value = "/{serverId}")
	@ResponseBody
	public ServerDTO getServer(@PathVariable Integer serverId) {
		
		return (serverService.getServerDTOById(serverId));
	}

 	@RequestMapping(value = "/addServer", method = RequestMethod.POST)
	public ResponseEntity<?> addServer(@RequestBody ServerDTO serverDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = serverService.addServer(serverDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/servers")
	public ResponseEntity<ServerPageDTO> getServers(ServerSearchDTO serverSearchDTO) {
 
		return serverService.getServers(serverSearchDTO);
	}	

	@RequestMapping(value = "/updateServer", method = RequestMethod.POST)
	public ResponseEntity<?> updateServer(@RequestBody ServerDTO serverDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = serverService.updateServer(serverDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
