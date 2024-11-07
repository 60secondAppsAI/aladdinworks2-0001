package com.aladdinworks2.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks2.domain.NetworkDevice;
import com.aladdinworks2.dto.NetworkDeviceDTO;
import com.aladdinworks2.dto.NetworkDeviceSearchDTO;
import com.aladdinworks2.dto.NetworkDevicePageDTO;
import com.aladdinworks2.dto.NetworkDeviceConvertCriteriaDTO;
import com.aladdinworks2.service.GenericService;
import com.aladdinworks2.dto.common.RequestDTO;
import com.aladdinworks2.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface NetworkDeviceService extends GenericService<NetworkDevice, Integer> {

	List<NetworkDevice> findAll();

	ResultDTO addNetworkDevice(NetworkDeviceDTO networkDeviceDTO, RequestDTO requestDTO);

	ResultDTO updateNetworkDevice(NetworkDeviceDTO networkDeviceDTO, RequestDTO requestDTO);

    Page<NetworkDevice> getAllNetworkDevices(Pageable pageable);

    Page<NetworkDevice> getAllNetworkDevices(Specification<NetworkDevice> spec, Pageable pageable);

	ResponseEntity<NetworkDevicePageDTO> getNetworkDevices(NetworkDeviceSearchDTO networkDeviceSearchDTO);
	
	List<NetworkDeviceDTO> convertNetworkDevicesToNetworkDeviceDTOs(List<NetworkDevice> networkDevices, NetworkDeviceConvertCriteriaDTO convertCriteria);

	NetworkDeviceDTO getNetworkDeviceDTOById(Integer networkDeviceId);







}





