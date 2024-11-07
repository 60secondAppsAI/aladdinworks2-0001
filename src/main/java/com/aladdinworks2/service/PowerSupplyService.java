package com.aladdinworks2.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks2.domain.PowerSupply;
import com.aladdinworks2.dto.PowerSupplyDTO;
import com.aladdinworks2.dto.PowerSupplySearchDTO;
import com.aladdinworks2.dto.PowerSupplyPageDTO;
import com.aladdinworks2.dto.PowerSupplyConvertCriteriaDTO;
import com.aladdinworks2.service.GenericService;
import com.aladdinworks2.dto.common.RequestDTO;
import com.aladdinworks2.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PowerSupplyService extends GenericService<PowerSupply, Integer> {

	List<PowerSupply> findAll();

	ResultDTO addPowerSupply(PowerSupplyDTO powerSupplyDTO, RequestDTO requestDTO);

	ResultDTO updatePowerSupply(PowerSupplyDTO powerSupplyDTO, RequestDTO requestDTO);

    Page<PowerSupply> getAllPowerSupplys(Pageable pageable);

    Page<PowerSupply> getAllPowerSupplys(Specification<PowerSupply> spec, Pageable pageable);

	ResponseEntity<PowerSupplyPageDTO> getPowerSupplys(PowerSupplySearchDTO powerSupplySearchDTO);
	
	List<PowerSupplyDTO> convertPowerSupplysToPowerSupplyDTOs(List<PowerSupply> powerSupplys, PowerSupplyConvertCriteriaDTO convertCriteria);

	PowerSupplyDTO getPowerSupplyDTOById(Integer powerSupplyId);







}





