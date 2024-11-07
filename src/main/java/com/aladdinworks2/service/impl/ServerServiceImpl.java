package com.aladdinworks2.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.aladdinworks2.dao.GenericDAO;
import com.aladdinworks2.service.GenericService;
import com.aladdinworks2.service.impl.GenericServiceImpl;
import com.aladdinworks2.dao.ServerDAO;
import com.aladdinworks2.domain.Server;
import com.aladdinworks2.dto.ServerDTO;
import com.aladdinworks2.dto.ServerSearchDTO;
import com.aladdinworks2.dto.ServerPageDTO;
import com.aladdinworks2.dto.ServerConvertCriteriaDTO;
import com.aladdinworks2.dto.common.RequestDTO;
import com.aladdinworks2.dto.common.ResultDTO;
import com.aladdinworks2.service.ServerService;
import com.aladdinworks2.util.ControllerUtils;





@Service
public class ServerServiceImpl extends GenericServiceImpl<Server, Integer> implements ServerService {

    private final static Logger logger = LoggerFactory.getLogger(ServerServiceImpl.class);

	@Autowired
	ServerDAO serverDao;

	


	@Override
	public GenericDAO<Server, Integer> getDAO() {
		return (GenericDAO<Server, Integer>) serverDao;
	}
	
	public List<Server> findAll () {
		List<Server> servers = serverDao.findAll();
		
		return servers;	
		
	}

	public ResultDTO addServer(ServerDTO serverDTO, RequestDTO requestDTO) {

		Server server = new Server();

		server.setServerId(serverDTO.getServerId());


		server.setModel(serverDTO.getModel());


		server.setManufacturer(serverDTO.getManufacturer());


		server.setSerialNumber(serverDTO.getSerialNumber());


		server.setStatus(serverDTO.getStatus());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		server = serverDao.save(server);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Server> getAllServers(Pageable pageable) {
		return serverDao.findAll(pageable);
	}

	public Page<Server> getAllServers(Specification<Server> spec, Pageable pageable) {
		return serverDao.findAll(spec, pageable);
	}

	public ResponseEntity<ServerPageDTO> getServers(ServerSearchDTO serverSearchDTO) {
	
			Integer serverId = serverSearchDTO.getServerId(); 
 			String model = serverSearchDTO.getModel(); 
 			String manufacturer = serverSearchDTO.getManufacturer(); 
 			String serialNumber = serverSearchDTO.getSerialNumber(); 
 			String status = serverSearchDTO.getStatus(); 
 			String sortBy = serverSearchDTO.getSortBy();
			String sortOrder = serverSearchDTO.getSortOrder();
			String searchQuery = serverSearchDTO.getSearchQuery();
			Integer page = serverSearchDTO.getPage();
			Integer size = serverSearchDTO.getSize();

	        Specification<Server> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, serverId, "serverId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, model, "model"); 
			
			spec = ControllerUtils.andIfNecessary(spec, manufacturer, "manufacturer"); 
			
			spec = ControllerUtils.andIfNecessary(spec, serialNumber, "serialNumber"); 
			
			spec = ControllerUtils.andIfNecessary(spec, status, "status"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("model")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("manufacturer")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("serialNumber")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("status")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Server> servers = this.getAllServers(spec, pageable);
		
		//System.out.println(String.valueOf(servers.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(servers.getTotalPages()));
		
		List<Server> serversList = servers.getContent();
		
		ServerConvertCriteriaDTO convertCriteria = new ServerConvertCriteriaDTO();
		List<ServerDTO> serverDTOs = this.convertServersToServerDTOs(serversList,convertCriteria);
		
		ServerPageDTO serverPageDTO = new ServerPageDTO();
		serverPageDTO.setServers(serverDTOs);
		serverPageDTO.setTotalElements(servers.getTotalElements());
		return ResponseEntity.ok(serverPageDTO);
	}

	public List<ServerDTO> convertServersToServerDTOs(List<Server> servers, ServerConvertCriteriaDTO convertCriteria) {
		
		List<ServerDTO> serverDTOs = new ArrayList<ServerDTO>();
		
		for (Server server : servers) {
			serverDTOs.add(convertServerToServerDTO(server,convertCriteria));
		}
		
		return serverDTOs;

	}
	
	public ServerDTO convertServerToServerDTO(Server server, ServerConvertCriteriaDTO convertCriteria) {
		
		ServerDTO serverDTO = new ServerDTO();
		
		serverDTO.setServerId(server.getServerId());

	
		serverDTO.setModel(server.getModel());

	
		serverDTO.setManufacturer(server.getManufacturer());

	
		serverDTO.setSerialNumber(server.getSerialNumber());

	
		serverDTO.setStatus(server.getStatus());

	

		
		return serverDTO;
	}

	public ResultDTO updateServer(ServerDTO serverDTO, RequestDTO requestDTO) {
		
		Server server = serverDao.getById(serverDTO.getServerId());

		server.setServerId(ControllerUtils.setValue(server.getServerId(), serverDTO.getServerId()));

		server.setModel(ControllerUtils.setValue(server.getModel(), serverDTO.getModel()));

		server.setManufacturer(ControllerUtils.setValue(server.getManufacturer(), serverDTO.getManufacturer()));

		server.setSerialNumber(ControllerUtils.setValue(server.getSerialNumber(), serverDTO.getSerialNumber()));

		server.setStatus(ControllerUtils.setValue(server.getStatus(), serverDTO.getStatus()));



        server = serverDao.save(server);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ServerDTO getServerDTOById(Integer serverId) {
	
		Server server = serverDao.getById(serverId);
			
		
		ServerConvertCriteriaDTO convertCriteria = new ServerConvertCriteriaDTO();
		return(this.convertServerToServerDTO(server,convertCriteria));
	}







}
