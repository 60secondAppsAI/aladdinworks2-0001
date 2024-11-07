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
import com.aladdinworks2.dao.NetworkDeviceDAO;
import com.aladdinworks2.domain.NetworkDevice;
import com.aladdinworks2.dto.NetworkDeviceDTO;
import com.aladdinworks2.dto.NetworkDeviceSearchDTO;
import com.aladdinworks2.dto.NetworkDevicePageDTO;
import com.aladdinworks2.dto.NetworkDeviceConvertCriteriaDTO;
import com.aladdinworks2.dto.common.RequestDTO;
import com.aladdinworks2.dto.common.ResultDTO;
import com.aladdinworks2.service.NetworkDeviceService;
import com.aladdinworks2.util.ControllerUtils;





@Service
public class NetworkDeviceServiceImpl extends GenericServiceImpl<NetworkDevice, Integer> implements NetworkDeviceService {

    private final static Logger logger = LoggerFactory.getLogger(NetworkDeviceServiceImpl.class);

	@Autowired
	NetworkDeviceDAO networkDeviceDao;

	


	@Override
	public GenericDAO<NetworkDevice, Integer> getDAO() {
		return (GenericDAO<NetworkDevice, Integer>) networkDeviceDao;
	}
	
	public List<NetworkDevice> findAll () {
		List<NetworkDevice> networkDevices = networkDeviceDao.findAll();
		
		return networkDevices;	
		
	}

	public ResultDTO addNetworkDevice(NetworkDeviceDTO networkDeviceDTO, RequestDTO requestDTO) {

		NetworkDevice networkDevice = new NetworkDevice();

		networkDevice.setNetworkDeviceId(networkDeviceDTO.getNetworkDeviceId());


		networkDevice.setType(networkDeviceDTO.getType());


		networkDevice.setManufacturer(networkDeviceDTO.getManufacturer());


		networkDevice.setModel(networkDeviceDTO.getModel());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		networkDevice = networkDeviceDao.save(networkDevice);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<NetworkDevice> getAllNetworkDevices(Pageable pageable) {
		return networkDeviceDao.findAll(pageable);
	}

	public Page<NetworkDevice> getAllNetworkDevices(Specification<NetworkDevice> spec, Pageable pageable) {
		return networkDeviceDao.findAll(spec, pageable);
	}

	public ResponseEntity<NetworkDevicePageDTO> getNetworkDevices(NetworkDeviceSearchDTO networkDeviceSearchDTO) {
	
			Integer networkDeviceId = networkDeviceSearchDTO.getNetworkDeviceId(); 
 			String type = networkDeviceSearchDTO.getType(); 
 			String manufacturer = networkDeviceSearchDTO.getManufacturer(); 
 			String model = networkDeviceSearchDTO.getModel(); 
 			String sortBy = networkDeviceSearchDTO.getSortBy();
			String sortOrder = networkDeviceSearchDTO.getSortOrder();
			String searchQuery = networkDeviceSearchDTO.getSearchQuery();
			Integer page = networkDeviceSearchDTO.getPage();
			Integer size = networkDeviceSearchDTO.getSize();

	        Specification<NetworkDevice> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, networkDeviceId, "networkDeviceId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, type, "type"); 
			
			spec = ControllerUtils.andIfNecessary(spec, manufacturer, "manufacturer"); 
			
			spec = ControllerUtils.andIfNecessary(spec, model, "model"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("type")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("manufacturer")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("model")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<NetworkDevice> networkDevices = this.getAllNetworkDevices(spec, pageable);
		
		//System.out.println(String.valueOf(networkDevices.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(networkDevices.getTotalPages()));
		
		List<NetworkDevice> networkDevicesList = networkDevices.getContent();
		
		NetworkDeviceConvertCriteriaDTO convertCriteria = new NetworkDeviceConvertCriteriaDTO();
		List<NetworkDeviceDTO> networkDeviceDTOs = this.convertNetworkDevicesToNetworkDeviceDTOs(networkDevicesList,convertCriteria);
		
		NetworkDevicePageDTO networkDevicePageDTO = new NetworkDevicePageDTO();
		networkDevicePageDTO.setNetworkDevices(networkDeviceDTOs);
		networkDevicePageDTO.setTotalElements(networkDevices.getTotalElements());
		return ResponseEntity.ok(networkDevicePageDTO);
	}

	public List<NetworkDeviceDTO> convertNetworkDevicesToNetworkDeviceDTOs(List<NetworkDevice> networkDevices, NetworkDeviceConvertCriteriaDTO convertCriteria) {
		
		List<NetworkDeviceDTO> networkDeviceDTOs = new ArrayList<NetworkDeviceDTO>();
		
		for (NetworkDevice networkDevice : networkDevices) {
			networkDeviceDTOs.add(convertNetworkDeviceToNetworkDeviceDTO(networkDevice,convertCriteria));
		}
		
		return networkDeviceDTOs;

	}
	
	public NetworkDeviceDTO convertNetworkDeviceToNetworkDeviceDTO(NetworkDevice networkDevice, NetworkDeviceConvertCriteriaDTO convertCriteria) {
		
		NetworkDeviceDTO networkDeviceDTO = new NetworkDeviceDTO();
		
		networkDeviceDTO.setNetworkDeviceId(networkDevice.getNetworkDeviceId());

	
		networkDeviceDTO.setType(networkDevice.getType());

	
		networkDeviceDTO.setManufacturer(networkDevice.getManufacturer());

	
		networkDeviceDTO.setModel(networkDevice.getModel());

	

		
		return networkDeviceDTO;
	}

	public ResultDTO updateNetworkDevice(NetworkDeviceDTO networkDeviceDTO, RequestDTO requestDTO) {
		
		NetworkDevice networkDevice = networkDeviceDao.getById(networkDeviceDTO.getNetworkDeviceId());

		networkDevice.setNetworkDeviceId(ControllerUtils.setValue(networkDevice.getNetworkDeviceId(), networkDeviceDTO.getNetworkDeviceId()));

		networkDevice.setType(ControllerUtils.setValue(networkDevice.getType(), networkDeviceDTO.getType()));

		networkDevice.setManufacturer(ControllerUtils.setValue(networkDevice.getManufacturer(), networkDeviceDTO.getManufacturer()));

		networkDevice.setModel(ControllerUtils.setValue(networkDevice.getModel(), networkDeviceDTO.getModel()));



        networkDevice = networkDeviceDao.save(networkDevice);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public NetworkDeviceDTO getNetworkDeviceDTOById(Integer networkDeviceId) {
	
		NetworkDevice networkDevice = networkDeviceDao.getById(networkDeviceId);
			
		
		NetworkDeviceConvertCriteriaDTO convertCriteria = new NetworkDeviceConvertCriteriaDTO();
		return(this.convertNetworkDeviceToNetworkDeviceDTO(networkDevice,convertCriteria));
	}







}
