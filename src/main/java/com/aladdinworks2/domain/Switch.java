package com.aladdinworks2.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="switchs")
@Getter @Setter @NoArgsConstructor
public class Switch {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="switch_id")
	private Integer switchId;
    
  	@Column(name="ports")
	private Integer ports;
    
  	@Column(name="manufacturer")
	private String manufacturer;
    
  	@Column(name="model")
	private String model;
    
	




}
