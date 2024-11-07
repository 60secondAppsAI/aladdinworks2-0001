package com.aladdinworks2.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks2.domain.Rack;
import com.aladdinworks2.dto.RackDTO;
import com.aladdinworks2.dto.RackSearchDTO;
import com.aladdinworks2.dto.RackPageDTO;
import com.aladdinworks2.dto.RackConvertCriteriaDTO;
import com.aladdinworks2.service.GenericService;
import com.aladdinworks2.dto.common.RequestDTO;
import com.aladdinworks2.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface RackService extends GenericService<Rack, Integer> {

	List<Rack> findAll();

	ResultDTO addRack(RackDTO rackDTO, RequestDTO requestDTO);

	ResultDTO updateRack(RackDTO rackDTO, RequestDTO requestDTO);

    Page<Rack> getAllRacks(Pageable pageable);

    Page<Rack> getAllRacks(Specification<Rack> spec, Pageable pageable);

	ResponseEntity<RackPageDTO> getRacks(RackSearchDTO rackSearchDTO);
	
	List<RackDTO> convertRacksToRackDTOs(List<Rack> racks, RackConvertCriteriaDTO convertCriteria);

	RackDTO getRackDTOById(Integer rackId);







}





