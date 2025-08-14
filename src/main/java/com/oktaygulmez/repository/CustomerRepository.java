package com.oktaygulmez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oktaygulmez.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
