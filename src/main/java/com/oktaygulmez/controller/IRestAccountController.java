package com.oktaygulmez.controller;

import com.oktaygulmez.dto.DtoAccount;
import com.oktaygulmez.dto.DtoAccountIU;

public interface IRestAccountController {

	public RootEntity<DtoAccount> saveAccount(DtoAccountIU input);
}
