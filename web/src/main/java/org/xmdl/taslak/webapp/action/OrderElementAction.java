package org.xmdl.taslak.webapp.action;

import com.opensymphony.xwork2.Preparable;
import org.xmdl.taslak.service.GenericManager;
import org.xmdl.taslak.model.OrderElement;

import java.util.List;

public class OrderElementAction extends BaseAction implements Preparable {
    private GenericManager<OrderElement, Long> orderElementManager;
    private List orderElements;
    private OrderElement orderElement;
    private Long  id;

    public void setOrderElementManager(GenericManager<OrderElement, Long> orderElementManager) {
        this.orderElementManager = orderElementManager;
    }

    public List getOrderElements() {
        return orderElements;
    }

    /**
     * Grab the entity from the database before populating with request parameters
     */
    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            String orderElementId = getRequest().getParameter("orderElement.id");
            if (orderElementId != null && !orderElementId.equals("")) {
                orderElement = orderElementManager.get(new Long(orderElementId));
            }
        }
    }

    public String list() {
        orderElements = orderElementManager.getAll();
        return SUCCESS;
    }

    public void setId(Long  id) {
        this. id =  id;
    }

    public OrderElement getOrderElement() {
        return orderElement;
    }

    public void setOrderElement(OrderElement orderElement) {
        this.orderElement = orderElement;
    }

    public String delete() {
        orderElementManager.remove(orderElement.getId());
        saveMessage(getText("orderElement.deleted"));

        return SUCCESS;
    }

    public String edit() {
        if (id != null) {
            orderElement = orderElementManager.get(id);
        } else {
            orderElement = new OrderElement();
        }

        return SUCCESS;
    }

    public String save() throws Exception {
        if (cancel != null) {
            return "cancel";
        }

        if (delete != null) {
            return delete();
        }

        boolean isNew = (orderElement.getId() == null);

        orderElementManager.save(orderElement);

        String key = (isNew) ? "orderElement.added" : "orderElement.updated";
        saveMessage(getText(key));

        if (!isNew) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }
}