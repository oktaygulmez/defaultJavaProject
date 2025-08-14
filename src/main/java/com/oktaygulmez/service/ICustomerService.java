package com.oktaygulmez.service;

import com.oktaygulmez.dto.DtoCustomer;
import com.oktaygulmez.dto.DtoCustomerIU;

public interface ICustomerService {

	public DtoCustomer saveCustomer(DtoCustomerIU input);

}
