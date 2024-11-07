package com.aladdinworks2.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks2.domain.PowerStrip;
import com.aladdinworks2.dto.PowerStripDTO;
import com.aladdinworks2.dto.PowerStripSearchDTO;
import com.aladdinworks2.dto.PowerStripPageDTO;
import com.aladdinworks2.dto.PowerStripConvertCriteriaDTO;
import com.aladdinworks2.service.GenericService;
import com.aladdinworks2.dto.common.RequestDTO;
import com.aladdinworks2.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PowerStripService extends GenericService<PowerStrip, Integer> {

	List<PowerStrip> findAll();

	ResultDTO addPowerStrip(PowerStripDTO powerStripDTO, RequestDTO requestDTO);

	ResultDTO updatePowerStrip(PowerStripDTO powerStripDTO, RequestDTO requestDTO);

    Page<PowerStrip> getAllPowerStrips(Pageable pageable);

    Page<PowerStrip> getAllPowerStrips(Specification<PowerStrip> spec, Pageable pageable);

	ResponseEntity<PowerStripPageDTO> getPowerStrips(PowerStripSearchDTO powerStripSearchDTO);
	
	List<PowerStripDTO> convertPowerStripsToPowerStripDTOs(List<PowerStrip> powerStrips, PowerStripConvertCriteriaDTO convertCriteria);

	PowerStripDTO getPowerStripDTOById(Integer powerStripId);







}





