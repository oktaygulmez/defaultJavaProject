package com.oktaygulmez.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.oktaygulmez.dto.DtoAccount;
import com.oktaygulmez.dto.DtoCustomer;
import com.oktaygulmez.dto.DtoCustomerIU;
import com.oktaygulmez.model.Account;
import com.oktaygulmez.model.Customer;
import com.oktaygulmez.repository.AccountRepository;
import com.oktaygulmez.repository.CustomerRepository;
import com.oktaygulmez.service.ICustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {


	private final AccountRepository accountRepository;

	private final CustomerRepository customerRepository;

	private Customer createCustomer(DtoCustomerIU input) {
		Customer customer = new Customer();
		customer.setCreateTime(new Date());
		BeanUtils.copyProperties(input, customer);

		Optional<Account> optAccount = accountRepository.findById(input.getAccountId());
		customer.setAccount(optAccount.get());

		return customer;
	}

	@Override
	public DtoCustomer saveCustomer(DtoCustomerIU input) {
		DtoCustomer dtoCustomer = new DtoCustomer();
		DtoAccount dtoAccount = new DtoAccount();

		Customer savedCustomer = customerRepository.save(createCustomer(input));

		BeanUtils.copyProperties(savedCustomer, dtoCustomer);
		BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);

		dtoCustomer.setAccount(dtoAccount);

		return dtoCustomer;
	}

}
