package org.xmdl.taslak.model.search;

import java.util.Date;

public class OrderSearch {
    private String name;
    private Double fromPriceTotals;
    private Double toPriceTotals;
    private Date fromCreateDate;
    private Date toCreateDate;


    public OrderSearch() {
    }

    public OrderSearch(String name, Double fromPriceTotals,
			Double toPriceTotals, Date fromCreateDate, Date toCreateDate) {
		super();
		this.name = name;
		this.fromPriceTotals = fromPriceTotals;
		this.toPriceTotals = toPriceTotals;
		this.fromCreateDate = fromCreateDate;
		this.toCreateDate = toCreateDate;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFromPriceTotals() {
        return fromPriceTotals;
    }

    public void setFromPriceTotals(Double fromPriceTotals) {
        this.fromPriceTotals = fromPriceTotals;
    }

    public Double getToPriceTotals() {
        return toPriceTotals;
    }

    public void setToPriceTotals(Double toPriceTotals) {
        this.toPriceTotals = toPriceTotals;
    }

    public Date getFromCreateDate() {
        return fromCreateDate;
    }

    public void setFromCreateDate(Date fromCreateDate) {
        this.fromCreateDate = fromCreateDate;
    }

    public Date getToCreateDate() {
        return toCreateDate;
    }

    public void setToCreateDate(Date toCreateDate) {
        this.toCreateDate = toCreateDate;
    }
}
