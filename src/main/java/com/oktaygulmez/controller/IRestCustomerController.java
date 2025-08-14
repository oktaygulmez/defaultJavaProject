package com.oktaygulmez.controller;

import com.oktaygulmez.dto.DtoCustomer;
import com.oktaygulmez.dto.DtoCustomerIU;

public interface IRestCustomerController {

	public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU input);
}
