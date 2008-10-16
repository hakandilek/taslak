package org.xmdl.ida.lib.web.util;

import java.util.Currency;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class CurrencyConverter extends StrutsTypeConverter {

    @SuppressWarnings("unchecked")
    public String convertToString(Map ctx, Object data) {
        if (data instanceof Currency) {
        	Currency c = (Currency) data;
        	return c.getSymbol();
        } else {
            return data.toString();
        }
    }

    @SuppressWarnings("unchecked")
	public Object convertFromString(Map ctx, String[] value, Class arg2) {
        if (value[0] == null || value[0].trim().equals("")) {
            return null;
        }
		return Currency.getInstance(value[0]);
    }


}
