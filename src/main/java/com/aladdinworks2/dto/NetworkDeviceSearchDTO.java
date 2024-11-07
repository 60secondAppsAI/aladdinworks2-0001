package com.aladdinworks2.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NetworkDeviceSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer networkDeviceId;
	
	private String type;
	
	private String manufacturer;
	
	private String model;
	
}
