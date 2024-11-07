package com.aladdinworks2.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.aladdinworks2.domain.RackAsset;
import com.aladdinworks2.dto.RackAssetDTO;
import com.aladdinworks2.dto.RackAssetSearchDTO;
import com.aladdinworks2.dto.RackAssetPageDTO;
import com.aladdinworks2.dto.RackAssetConvertCriteriaDTO;
import com.aladdinworks2.service.GenericService;
import com.aladdinworks2.dto.common.RequestDTO;
import com.aladdinworks2.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface RackAssetService extends GenericService<RackAsset, Integer> {

	List<RackAsset> findAll();

	ResultDTO addRackAsset(RackAssetDTO rackAssetDTO, RequestDTO requestDTO);

	ResultDTO updateRackAsset(RackAssetDTO rackAssetDTO, RequestDTO requestDTO);

    Page<RackAsset> getAllRackAssets(Pageable pageable);

    Page<RackAsset> getAllRackAssets(Specification<RackAsset> spec, Pageable pageable);

	ResponseEntity<RackAssetPageDTO> getRackAssets(RackAssetSearchDTO rackAssetSearchDTO);
	
	List<RackAssetDTO> convertRackAssetsToRackAssetDTOs(List<RackAsset> rackAssets, RackAssetConvertCriteriaDTO convertCriteria);

	RackAssetDTO getRackAssetDTOById(Integer rackAssetId);







}





