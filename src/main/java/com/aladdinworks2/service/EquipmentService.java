package com.aladdinworks2.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks2.domain.Equipment;
import com.aladdinworks2.dto.EquipmentDTO;
import com.aladdinworks2.dto.EquipmentSearchDTO;
import com.aladdinworks2.dto.EquipmentPageDTO;
import com.aladdinworks2.dto.EquipmentConvertCriteriaDTO;
import com.aladdinworks2.service.GenericService;
import com.aladdinworks2.dto.common.RequestDTO;
import com.aladdinworks2.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface EquipmentService extends GenericService<Equipment, Integer> {

	List<Equipment> findAll();

	ResultDTO addEquipment(EquipmentDTO equipmentDTO, RequestDTO requestDTO);

	ResultDTO updateEquipment(EquipmentDTO equipmentDTO, RequestDTO requestDTO);

    Page<Equipment> getAllEquipments(Pageable pageable);

    Page<Equipment> getAllEquipments(Specification<Equipment> spec, Pageable pageable);

	ResponseEntity<EquipmentPageDTO> getEquipments(EquipmentSearchDTO equipmentSearchDTO);
	
	List<EquipmentDTO> convertEquipmentsToEquipmentDTOs(List<Equipment> equipments, EquipmentConvertCriteriaDTO convertCriteria);

	EquipmentDTO getEquipmentDTOById(Integer equipmentId);







}





