package org.xmdl.ida.lib.web.taglib.component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.components.TextField;
import org.apache.struts2.util.MakeIterator;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;
import org.xmdl.ida.lib.model.MoneyType;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * Bean for a currency display widget of type input (amount) and a currency A
 * money tag needs to be associated with a form, either by containment or
 * through formId
 */
@StrutsTag(
		name = "money", 
		tldTagClass = "org.xmdl.ida.lib.web.taglib.ui.MoneyTag", 
		description = "Renders a input field of type money with amount and currency"
	    )
public class MoneyTagBean extends TextField {

    /**
     * Transient log to prevent session synchronization issues - children can use instance for logging.
     */
    protected transient final Log log = LogFactory.getLog(getClass());

	/** template constant */
	private static final String TEMPLATE = "money";
	
    protected Object currencyList;
    protected String currencyListKey;
    protected String currencyListValue;
    protected String emptyOption;

	/**
	 * @param stack
	 *            the value stack
	 * @param request
	 *            http request
	 * @param response
	 *            http response
	 */
	public MoneyTagBean(ValueStack stack, HttpServletRequest request,
			HttpServletResponse response) {
		super(stack, request, response);
    	if (log.isDebugEnabled()) log.debug("MoneyTagBean() ->");
	}

    /**
     * Evaluates the OGNL expression
     *
     * @param expr OGNL expression.
     * @return the String value found.
     */
    public String evaluateExpression(String expr) {
        return super.findString(expr);
    }

    @Override
	protected String getDefaultTemplate() {
		return TEMPLATE;
	}

    /**
	 * @see org.apache.struts2.components.UIBean#evaluateParams()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void evaluateParams() {
    	if (log.isDebugEnabled()) log.debug("evaluateParams() <-");
		super.evaluateParams();
		
		Double amount = null;
		Currency currency = null;

        // see if the value was specified as a parameter already
        if (parameters.containsKey("value")) {
        	if (log.isDebugEnabled()) log.debug("params.value = " + parameters.get("value"));
            parameters.put("nameValue", parameters.get("value"));
        } else {
            if (evaluateNameValue()) {
                final Class<?> valueClazz = getValueClassType();
            	if (log.isDebugEnabled()) log.debug("0. valueClazz = " + valueClazz);

                if (valueClazz != null) {
                    if (value != null) {
                        Object findValue = findValue(value, valueClazz);
                    	if (log.isDebugEnabled()) log.debug("1. value = " + value);
                    	if (log.isDebugEnabled()) log.debug("1. findValue = " + findValue);
						addParameter("nameValue", findValue);
                    } else if (name != null) {
                        String expr = name;
                        if (altSyntax()) {
                            expr = "%{" + expr + "}";
                        }
                        Object findValue = findValue(expr, valueClazz);
                    	if (log.isDebugEnabled()) log.debug("2.1 expr = " + expr);
                    	if (log.isDebugEnabled()) log.debug("2.1 findValue = " + findValue);
                    	if (log.isDebugEnabled()) log.debug("2.1 findValue.class = " 
                    			+ (findValue == null ? null : findValue.getClass()));
                    	if (findValue != null && findValue.getClass().equals(String.class) 
                    			&& "ognl.NoConversionPossible".equals(findValue)) {
                    		//if the value is not recognized as MoneyType, then it should be String to be converted.
                    		String val = findValue(expr)+"";
                        	if (log.isDebugEnabled()) log.debug("val = " + val);
                        	
                        	//---
                        	if (log.isDebugEnabled()) log.debug("stack = " + stack);
                        	if (log.isDebugEnabled()) log.debug("stack.size = " + stack.size());
                        	if (log.isDebugEnabled()) log.debug("parameters = " + parameters);
                        	//---
                        	
                    		try {
								amount = Double.parseDouble(val);
							} catch (NumberFormatException e) {
		                    	if (log.isDebugEnabled()) log.debug("this value can not be parsed.", e);
							}
	                    	if (log.isDebugEnabled()) log.debug("amount = " + amount);
							
                        	String currExpr = name + "_currency";
                            if (altSyntax()) {
                            	currExpr = "%{" + currExpr + "}";
                            }
                    		String currVal = findValue(currExpr)+"";
                        	if (log.isDebugEnabled()) log.debug("currVal = " + currVal);
							try {
								currency = Currency.getInstance(currVal);
							} catch (Exception e) {
		                    	if (log.isDebugEnabled()) log.debug("currency can not be parsed.", e);
							}
	                    	if (log.isDebugEnabled()) log.debug("currency = " + currency);
							
                    	}
                    	
                    	if (log.isDebugEnabled()) log.debug("2.2 findValue = " + findValue);
                    	if (log.isDebugEnabled()) log.debug("2.2 findValue.class = " + (findValue == null ? null : findValue.getClass()));
						addParameter("nameValue", findValue);
                    }
                } else {
                    if (value != null) {
                        Object findValue = findValue(value);
                    	if (log.isDebugEnabled()) log.debug("3. value = " + value);
                    	if (log.isDebugEnabled()) log.debug("3. findValue = " + findValue);
						addParameter("nameValue", findValue);
                    } else if (name != null) {
                        Object findValue = findValue(name);
                    	if (log.isDebugEnabled()) log.debug("4. name = " + name);
                    	if (log.isDebugEnabled()) log.debug("4. findValue = " + findValue);
						addParameter("nameValue", findValue);
                    }
                }
            }
        }

		Object nv = parameters.get("nameValue");
    	if (log.isDebugEnabled()) log.debug("nv = " + nv);
    	if (log.isDebugEnabled()) log.debug("nv.class = " + (nv == null ? null : nv.getClass()));
		if (nv != null && nv instanceof MoneyType) {
			MoneyType money = (MoneyType) nv;
	    	if (log.isDebugEnabled()) log.debug("money = " + money);
			amount = money.getAmount();
			currency = money.getCurrency();
			addParameter("amount", amount);
			addParameter("currency", currency);
	    	if (log.isDebugEnabled()) log.debug("amount = " + amount);
	    	if (log.isDebugEnabled()) log.debug("currency = " + currency);
		} else if (amount != null && currency != null) {
	    	if (log.isDebugEnabled()) log.debug("amount and currency available");
			addParameter("amount", amount);
			addParameter("currency", currency);
	    	if (log.isDebugEnabled()) log.debug("amount = " + amount);
	    	if (log.isDebugEnabled()) log.debug("currency = " + currency);
		}
    	if (log.isDebugEnabled()) log.debug("evaluateParams() ->");
	}

	/**
	 *  (non-Javadoc)
	 * @see org.apache.struts2.components.UIBean#getValueClassType()
	 */
	@Override
	protected Class<?> getValueClassType() {
		return MoneyType.class;
	}

	public void evaluateExtraParams() {
    	if (log.isDebugEnabled()) log.debug("evaluateExtraParams() <-");
    	super.evaluateExtraParams();
    	if (log.isDebugEnabled()) log.debug("readonly = " + readonly);
    	
        Object value = null;

        if (currencyList == null) {
            currencyList = parameters.get("currencyList");
        }
    	if (log.isDebugEnabled()) log.debug("currencyList = " + currencyList);
    	
        if (currencyList instanceof String) {
        	if (log.isDebugEnabled()) log.debug("find String value");
            String str = findString((String) currencyList);
        	List<Currency> set = new ArrayList<Currency>();
            String[] strs = str.split(",");
            for (String s : strs) {
				set.add(Currency.getInstance(s.trim()));
			}
        	value = set;
        	if (log.isDebugEnabled()) log.debug("string value= " + value);
        } 
        if (value == null) {
        	if (log.isDebugEnabled()) log.debug("value is null");
        	List<Currency> set = new ArrayList<Currency>();
        	if (log.isDebugEnabled()) log.debug("add TRL");
			set.add(Currency.getInstance("TRY"));
        	if (log.isDebugEnabled()) log.debug("add EUR");
        	set.add(Currency.getInstance("EUR"));
        	if (log.isDebugEnabled()) log.debug("add USD");
        	set.add(Currency.getInstance("USD"));
        	value = set;
        	if (log.isDebugEnabled()) log.debug("default value= " + value);
        }
    	if (log.isDebugEnabled()) log.debug("value = " + value);

        if (value instanceof Collection) {
            addParameter("currencyList", value);
        } else {
            addParameter("currencyList", MakeIterator.convert(value));
        }

        if (value instanceof Collection) {
            addParameter("currencyListSize", new Integer(((Collection<?>) value).size()));
        } else if (value instanceof Map) {
            addParameter("currencyListSize", new Integer(((Map<?, ?>) value).size()));
        } else if (value != null && value.getClass().isArray()) {
            addParameter("currencyListSize", new Integer(Array.getLength(value)));
        }

        if (currencyListKey != null) {
            addParameter("currencyListKey", currencyListKey);
        } else if (value instanceof Map) {
            addParameter("currencyListKey", "key");
        }

        if (currencyListValue != null) {
            if (altSyntax()) {
                // the same logic as with findValue(String)
                // if value start with %{ and end with }, just cut it off!
                if (currencyListValue.startsWith("%{") && currencyListValue.endsWith("}")) {
                    currencyListValue = currencyListValue.substring(2, currencyListValue.length() - 1);
                }
            }
            addParameter("currencyListValue", currencyListValue);
        } else if (value instanceof Map) {
            addParameter("currencyListValue", "value");
        }
        
        if (emptyOption != null) {
            addParameter("emptyOption", findValue(emptyOption, Boolean.class));
        }

        if (log.isDebugEnabled()) log.debug("evaluateExtraParams() ->");
    }

    @StrutsTagAttribute(description="Whether or not to add an empty (--) option after the header option", type="Boolean", defaultValue="false")
    public void setEmptyOption(String emptyOption) {
        this.emptyOption = emptyOption;
    }

    @StrutsTagAttribute(description="Iterable source to populate from. If the currencyList is a Map (key, value), the Map key will become the option 'value'" +
            " parameter and the Map value will become the option body.", required=true)
	public void setCurrencyList(Object currencyList) {
	    this.currencyList = currencyList;
	}
	
	@StrutsTagAttribute(description=" Property of currencyList objects to get field value from")
	public void setCurrencyListKey(String currencyListKey) {
	    this.currencyListKey = currencyListKey;
	}
	
	@StrutsTagAttribute(description="Property of currencyList objects to get field content from")
	public void setCurrencyListValue(String currencyListValue) {
	    this.currencyListValue = currencyListValue;
	}

}
