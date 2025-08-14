package com.oktaygulmez.controller.impl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oktaygulmez.controller.IRestAccountController;
import com.oktaygulmez.controller.RootEntity;
import com.oktaygulmez.dto.DtoAccount;
import com.oktaygulmez.dto.DtoAccountIU;
import com.oktaygulmez.service.IAccountService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rest/api/account")
@RequiredArgsConstructor
public class RestAccountControllerImpl extends RestBaseController implements IRestAccountController {

	private final IAccountService accountService;
	
	@PostMapping(path = "/save")
	@Override
	public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU input) {
		return ok(accountService.saveAccount(input));
	}

}
