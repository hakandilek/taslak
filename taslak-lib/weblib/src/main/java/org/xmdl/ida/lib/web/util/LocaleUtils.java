package org.xmdl.ida.lib.web.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.xmdl.ida.lib.BaseConstants;

import com.opensymphony.xwork2.util.LocalizedTextUtil;

/**
 * Utility methods for locale operations
 * 
 * @author Hakan Dilek
 */
public class LocaleUtils {

	public static Locale getLocale(PageContext pageContext) {
		final ServletRequest request = pageContext.getRequest();
		return getLocale(request);
	}

	public static Locale getLocale(ServletRequest request) {
		String locale = request.getParameter("locale");
		Locale preferredLocale = null;

		if (locale != null) {
			int indexOfUnderscore = locale.indexOf('_');
			if (indexOfUnderscore != -1) {
				String language = locale.substring(0, indexOfUnderscore);
				String country = locale.substring(indexOfUnderscore + 1);
				preferredLocale = new Locale(language, country);
			} else {
				preferredLocale = new Locale(locale);
			}
		}

		if (preferredLocale == null && request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpSession session = httpRequest.getSession(false);
			if (session != null) {
				preferredLocale = (Locale) session
						.getAttribute(BaseConstants.PREFERRED_LOCALE_KEY);
			}
		}

		if (preferredLocale == null) {
			preferredLocale = Locale.getDefault();
		}

		return preferredLocale;
	}

	public static String getMessage(Locale locale, String key) {
		String message = null;

		try {
			final ResourceBundle bundle = LocalizedTextUtil.findResourceBundle(
					BaseConstants.BUNDLE_KEY, locale);
			message = LocalizedTextUtil.findText(bundle, key, locale);
		} catch (MissingResourceException e) {
			message = "??" + key + "??";
		}

		return message;

	}
}
