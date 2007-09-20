package org.xmdl.taslak.webapp.action;

import com.opensymphony.xwork2.Preparable;
import org.xmdl.taslak.service.GenericManager;
import org.xmdl.taslak.model.Order;
import org.xmdl.taslak.webapp.action.BaseAction;

import java.util.List;

public class OrderAction extends BaseAction implements Preparable {
    private GenericManager<Order, Long> orderManager;
    private List orders;
    private Order order;
    private Long  id;

    public void setOrderManager(GenericManager<Order, Long> orderManager) {
        this.orderManager = orderManager;
    }

    public List getOrders() {
        return orders;
    }

    /**
     * Grab the entity from the database before populating with request parameters
     */
    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            String orderId = getRequest().getParameter("order.id");
            if (orderId != null && !orderId.equals("")) {
                order = orderManager.get(new Long(orderId));
            }
        }
    }

    public String list() {
        orders = orderManager.getAll();
        return SUCCESS;
    }

    public void setId(Long  id) {
        this. id =  id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String delete() {
        orderManager.remove(order.getId());
        saveMessage(getText("order.deleted"));

        return SUCCESS;
    }

    public String edit() {
        if (id != null) {
            order = orderManager.get(id);
        } else {
            order = new Order();
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

        boolean isNew = (order.getId() == null);

        orderManager.save(order);

        String key = (isNew) ? "order.added" : "order.updated";
        saveMessage(getText(key));

        if (!isNew) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }
}