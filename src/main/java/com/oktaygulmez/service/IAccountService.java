package com.oktaygulmez.service;

import com.oktaygulmez.dto.DtoAccount;
import com.oktaygulmez.dto.DtoAccountIU;

public interface IAccountService {

	public DtoAccount saveAccount(DtoAccountIU input);
}
