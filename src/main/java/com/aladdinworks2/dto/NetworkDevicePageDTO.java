package com.aladdinworks2.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NetworkDevicePageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<NetworkDeviceDTO> networkDevices;
}




