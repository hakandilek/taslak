package org.xmdl.taslak.model;

import javax.persistence.*;

import org.xmdl.ida.lib.model.BaseObject;

import java.text.MessageFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity (name=("t_order"))
public class Order extends BaseObject implements Serializable  {

    private Long id;
    private String name;
    private Double priceTotals;
    private Date createDate;
    private Set<OrderElement> orderElements = new HashSet<OrderElement>();

    public Order() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name=("id"))
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = ("name"), length = 63, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = ("pricetotals"), nullable = false)
    public Double getPriceTotals() {
        return priceTotals;
    }

    public void setPriceTotals(Double priceTotals) {
        this.priceTotals = priceTotals;
    }

    @Column(name = ("createdate"), nullable = false)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = ("order"))
    public Set<OrderElement> getOrderElements() {
        return orderElements;
    }

    public void setOrderElements(Set<OrderElement> orderElements) {
        this.orderElements = orderElements;
    }

    public String toString() {
        return MessageFormat.format("Order [id={0}][name={1}][priceTotals={2}][createDate={3}]",id,name, priceTotals, createDate);
    }

    public boolean equals(Object o) {
        return o instanceof Order && ((Order) o).getId() == this.getId();
    }

    public int hashCode() {
        int result ;
        result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + priceTotals.hashCode();
        result = 31 * result + createDate.hashCode();
        return result;
    }
}

