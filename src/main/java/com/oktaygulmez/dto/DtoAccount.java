package com.oktaygulmez.dto;

import java.math.BigDecimal;

import com.oktaygulmez.enums.CurrencyType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAccount {

	private Long id;

	private String accountNo;

	private String iban;

	private BigDecimal amount;

	private CurrencyType currencyType;
}
