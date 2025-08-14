package com.oktaygulmez.controller.impl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oktaygulmez.controller.IRestCustomerController;
import com.oktaygulmez.controller.RootEntity;
import com.oktaygulmez.dto.DtoCustomer;
import com.oktaygulmez.dto.DtoCustomerIU;
import com.oktaygulmez.service.ICustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/api/customer")
public class RestCustomerControllerImpl extends RestBaseController implements IRestCustomerController{
	
	private final ICustomerService customerService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerIU input) {
		return ok(customerService.saveCustomer(input));
	}

}
