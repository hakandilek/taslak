package org.xmdl.taslak.model;

import javax.persistence.*;

import org.xmdl.ida.lib.model.BaseObject;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Parameter;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

@Entity(name = ("t_product"))
public class Product extends BaseObject implements Serializable {

    private Long id;
    private String name;
    private Double price;
	private Set<OrderElement> orderElements = new HashSet<OrderElement>();
	private Set<Supplier> suppliers = new HashSet<Supplier>();

    public Product() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ("id"))
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

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = ("product"))
    public Set<OrderElement> getOrderElements() {
		return orderElements;
    }

    public void setOrderElements(Set<OrderElement> orderElements) {
        this.orderElements = orderElements;
    }

    @ManyToMany(targetEntity = Supplier.class, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "t_product_supplier", 
			joinColumns = { @JoinColumn(name = "product_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "supplier_id") })
	public Set<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Set<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

	public String toString() {
        return MessageFormat.format("Product [id={0}][name={1}][price={2}]", id, name, price);
    }

    public boolean equals(Object o) {
        return o instanceof Product && ((Product) o).getId() == this.getId();
    }

    public int hashCode() {
        int result;
        result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }

    @Column(name = "productType", columnDefinition = "integer", nullable = false)
    @Type(
            type = "org.xmdl.ida.lib.dao.hibernate.GenericEnumUserType",
            parameters = {
            @Parameter(
                    name = "enumClass",
                    value = "org.xmdl.taslak.model.Product$ProductType"),
            @Parameter(
                    name = "identifierMethod",
                    value = "toInt"),
            @Parameter(
                    name = "valueOfMethod",
                    value = "fromInt")
                    }
    )
    private ProductType productType;

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
