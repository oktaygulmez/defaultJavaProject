package com.oktaygulmez.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "oktayProject_car" ,
uniqueConstraints = {@UniqueConstraint(columnNames = {"oktayProject_id" , "car_id"} , name = "uq_oktayProject_car")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class oktayProjectCar extends BaseEntity{

	@ManyToOne
	private oktayProject oktayProject;
	
	@ManyToOne
	private Car car;
}
