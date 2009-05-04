package org.xmdl.ida.lib.web.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import org.xmdl.ida.lib.model.MoneyType;

/**
 * Helper methods for money types
 * 
 * @author Hakan Dilek
 */
public class MoneyUtils {

	protected final static DecimalFormat FORMAT = new DecimalFormat("##0.##");

	public static String format(MoneyType money, Locale locale) {
		StringBuffer sb = new StringBuffer();
		final Double amount = money.getAmount();
		final Currency currency = money.getCurrency();
		sb.append(getFormat().format(amount)).append(" ").append(currency);
		return sb.toString();
	}

	protected static NumberFormat getFormat() {
		return FORMAT;
	}

}
