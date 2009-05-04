package org.xmdl.ida.lib.web.decorator;

import java.util.Locale;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;
import org.xmdl.ida.lib.model.MoneyType;
import org.xmdl.ida.lib.web.util.LocaleUtils;
import org.xmdl.ida.lib.web.util.MoneyUtils;

/**
 * Used to decorate money fields
 * 
 * @author Hakan Dilek
 */
public class MoneyDecorator implements DisplaytagColumnDecorator {

	public Object decorate(Object columnValue, PageContext pageContext,
			MediaTypeEnum media) throws DecoratorException {
		StringBuffer sb = new StringBuffer();
		if (columnValue instanceof MoneyType) {
			final MoneyType money = (MoneyType) columnValue;
			final Locale locale = LocaleUtils.getLocale(pageContext);
			String str = MoneyUtils.format(money, locale);
			sb.append(str);
		} else {
			if (columnValue != null)
				sb.append(columnValue);
		}
		return sb.toString();
	}


}
