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
@Table(name="monitoring_points")
@Getter @Setter @NoArgsConstructor
public class MonitoringPoint {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="monitoring_point_id")
	private Integer monitoringPointId;
    
  	@Column(name="type")
	private String type;
    
  	@Column(name="value")
	private Double value;
    
  	@Column(name="timestamp")
	private DateTime timestamp;
    
	




}
