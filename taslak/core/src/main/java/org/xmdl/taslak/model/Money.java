package org.xmdl.taslak.model;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Currency;

import javax.persistence.Column;

import org.xmdl.ida.lib.model.BaseObject;
import org.xmdl.ida.lib.model.MoneyType;

public class Money extends BaseObject implements Serializable, Cloneable, MoneyType {

    /** serial id */
	private static final long serialVersionUID = -7961698215037632696L;

    /** the money currency */
    private transient Currency currency = Currency.getInstance("TRY");

    @Column(name = ("F_AMOUNT"), length = 15)
    private Double amount;
	
    public Money() {
    }

    public Money(Double amount) {
		this.amount = amount;
	}

    public Money(Double amount, String currencyCode) {
		this.amount = amount;
		this.currency = Currency.getInstance(currencyCode);
	}

    public Money(Double amount, Currency currency) {
		this.amount = amount;
		this.currency = currency;
	}
    
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    public String toString() {
        return MessageFormat.format("Money [amount={1}]", amount);
    }

    public boolean equals(Object o) {
        return o instanceof Money ;
    }

    public int hashCode() {
        int result = 1;
        
        if (amount != null) result = 31 * result + amount.hashCode();
        return result;
    }

     public Money createClone() {
     	try {
     		return (Money) clone();
        } catch (CloneNotSupportedException e) {
            Money copy = new Money();
            copy.amount = this.amount;
            return copy;
        }
     }

     public Currency getCurrency() {
    	 return currency;
     }
}
