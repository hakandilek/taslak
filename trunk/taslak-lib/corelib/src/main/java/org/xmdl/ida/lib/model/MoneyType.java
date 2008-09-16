package org.xmdl.ida.lib.model;

import java.util.Currency;

public interface MoneyType {

	Double getAmount();
	
	void setAmount(Double amount);
	
	Currency getCurrency();
}
