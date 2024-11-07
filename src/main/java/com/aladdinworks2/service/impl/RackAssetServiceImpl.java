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
import com.aladdinworks2.dao.RackAssetDAO;
import com.aladdinworks2.domain.RackAsset;
import com.aladdinworks2.dto.RackAssetDTO;
import com.aladdinworks2.dto.RackAssetSearchDTO;
import com.aladdinworks2.dto.RackAssetPageDTO;
import com.aladdinworks2.dto.RackAssetConvertCriteriaDTO;
import com.aladdinworks2.dto.common.RequestDTO;
import com.aladdinworks2.dto.common.ResultDTO;
import com.aladdinworks2.service.RackAssetService;
import com.aladdinworks2.util.ControllerUtils;





@Service
public class RackAssetServiceImpl extends GenericServiceImpl<RackAsset, Integer> implements RackAssetService {

    private final static Logger logger = LoggerFactory.getLogger(RackAssetServiceImpl.class);

	@Autowired
	RackAssetDAO rackAssetDao;

	


	@Override
	public GenericDAO<RackAsset, Integer> getDAO() {
		return (GenericDAO<RackAsset, Integer>) rackAssetDao;
	}
	
	public List<RackAsset> findAll () {
		List<RackAsset> rackAssets = rackAssetDao.findAll();
		
		return rackAssets;	
		
	}

	public ResultDTO addRackAsset(RackAssetDTO rackAssetDTO, RequestDTO requestDTO) {

		RackAsset rackAsset = new RackAsset();

		rackAsset.setRackAssetId(rackAssetDTO.getRackAssetId());


		rackAsset.setPosition(rackAssetDTO.getPosition());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		rackAsset = rackAssetDao.save(rackAsset);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<RackAsset> getAllRackAssets(Pageable pageable) {
		return rackAssetDao.findAll(pageable);
	}

	public Page<RackAsset> getAllRackAssets(Specification<RackAsset> spec, Pageable pageable) {
		return rackAssetDao.findAll(spec, pageable);
	}

	public ResponseEntity<RackAssetPageDTO> getRackAssets(RackAssetSearchDTO rackAssetSearchDTO) {
	
			Integer rackAssetId = rackAssetSearchDTO.getRackAssetId(); 
 			Integer position = rackAssetSearchDTO.getPosition(); 
 			String sortBy = rackAssetSearchDTO.getSortBy();
			String sortOrder = rackAssetSearchDTO.getSortOrder();
			String searchQuery = rackAssetSearchDTO.getSearchQuery();
			Integer page = rackAssetSearchDTO.getPage();
			Integer size = rackAssetSearchDTO.getSize();

	        Specification<RackAsset> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, rackAssetId, "rackAssetId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, position, "position"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

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

		Page<RackAsset> rackAssets = this.getAllRackAssets(spec, pageable);
		
		//System.out.println(String.valueOf(rackAssets.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(rackAssets.getTotalPages()));
		
		List<RackAsset> rackAssetsList = rackAssets.getContent();
		
		RackAssetConvertCriteriaDTO convertCriteria = new RackAssetConvertCriteriaDTO();
		List<RackAssetDTO> rackAssetDTOs = this.convertRackAssetsToRackAssetDTOs(rackAssetsList,convertCriteria);
		
		RackAssetPageDTO rackAssetPageDTO = new RackAssetPageDTO();
		rackAssetPageDTO.setRackAssets(rackAssetDTOs);
		rackAssetPageDTO.setTotalElements(rackAssets.getTotalElements());
		return ResponseEntity.ok(rackAssetPageDTO);
	}

	public List<RackAssetDTO> convertRackAssetsToRackAssetDTOs(List<RackAsset> rackAssets, RackAssetConvertCriteriaDTO convertCriteria) {
		
		List<RackAssetDTO> rackAssetDTOs = new ArrayList<RackAssetDTO>();
		
		for (RackAsset rackAsset : rackAssets) {
			rackAssetDTOs.add(convertRackAssetToRackAssetDTO(rackAsset,convertCriteria));
		}
		
		return rackAssetDTOs;

	}
	
	public RackAssetDTO convertRackAssetToRackAssetDTO(RackAsset rackAsset, RackAssetConvertCriteriaDTO convertCriteria) {
		
		RackAssetDTO rackAssetDTO = new RackAssetDTO();
		
		rackAssetDTO.setRackAssetId(rackAsset.getRackAssetId());

	
		rackAssetDTO.setPosition(rackAsset.getPosition());

	

		
		return rackAssetDTO;
	}

	public ResultDTO updateRackAsset(RackAssetDTO rackAssetDTO, RequestDTO requestDTO) {
		
		RackAsset rackAsset = rackAssetDao.getById(rackAssetDTO.getRackAssetId());

		rackAsset.setRackAssetId(ControllerUtils.setValue(rackAsset.getRackAssetId(), rackAssetDTO.getRackAssetId()));

		rackAsset.setPosition(ControllerUtils.setValue(rackAsset.getPosition(), rackAssetDTO.getPosition()));



        rackAsset = rackAssetDao.save(rackAsset);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public RackAssetDTO getRackAssetDTOById(Integer rackAssetId) {
	
		RackAsset rackAsset = rackAssetDao.getById(rackAssetId);
			
		
		RackAssetConvertCriteriaDTO convertCriteria = new RackAssetConvertCriteriaDTO();
		return(this.convertRackAssetToRackAssetDTO(rackAsset,convertCriteria));
	}







}
