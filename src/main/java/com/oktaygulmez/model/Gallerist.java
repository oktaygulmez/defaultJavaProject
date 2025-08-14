package com.oktaygulmez.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "oktayProject")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class oktayProject extends BaseEntity{

	@Column(name = "first_name" , length = 50)
	private String firstName;
	
	@Column(name = "last_name" , length = 60)
	private String lastName;
	
	@OneToOne
	private Address address;
}
