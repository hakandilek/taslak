package org.xmdl.ida.lib.model;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A dummy bean used only for test purposes.
 * 
 * @author Hakan Dilek
 *
 */
@Entity (name=("t_dummy"))
public class Dummy extends BaseObject implements Serializable{

    /**
	 * serial id
	 */
	private static final long serialVersionUID = -6073521785792548462L;
	
	private Long id;
    private String name;
    private Double value;
    private Date date;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name=("F_id"))
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = ("F_name"), length = 63, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = ("F_value"), nullable = false)
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Column(name = ("F_date"), nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public String toString() {
        return MessageFormat.format("Order [id={0}][name={1}][value={2}][date={3}]",id,name, value, date);
    }

    public boolean equals(Object o) {
        return o instanceof Dummy && ((Dummy) o).getId() == this.getId();
    }

    public int hashCode() {
        int result ;
        result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + value.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }



}
