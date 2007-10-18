package org.xmdl.taslak.model;

import javax.persistence.*;

import org.xmdl.ida.lib.model.BaseObject;

import java.io.Serializable;
import java.text.MessageFormat;

@Entity (name=("t_product"))
public class Product extends BaseObject implements Serializable  {

    private Long id;
    private String name;
    private Double price;

    public Product() {
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

    @Column(name = ("price"), nullable = true)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String toString() {
        return MessageFormat.format("Product [id={0}][name={1}][price={2}]",id,name,price);
    }

    public boolean equals(Object o) {
        return o instanceof Product && ((Product) o).getId() == this.getId();
    }

    public int hashCode() {
        int result ;
        result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }
}
