package com.oktaygulmez.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.oktaygulmez.dto.DtoAccount;
import com.oktaygulmez.dto.DtoAccountIU;
import com.oktaygulmez.model.Account;
import com.oktaygulmez.repository.AccountRepository;
import com.oktaygulmez.service.IAccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {

	private final AccountRepository accountRepository;

	@Override
	public DtoAccount saveAccount(DtoAccountIU input) {
		DtoAccount dtoAccount = new DtoAccount();

		Account account = new Account();
		account.setCreateTime(new Date());

		BeanUtils.copyProperties(input, account);

		Account savedAccount = accountRepository.save(account);
		BeanUtils.copyProperties(savedAccount, dtoAccount);
		return dtoAccount;
	}

}
