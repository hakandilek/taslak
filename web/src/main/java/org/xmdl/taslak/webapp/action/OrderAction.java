package org.xmdl.taslak.webapp.action;

import com.opensymphony.xwork2.Preparable;
import org.xmdl.taslak.model.Order;
import org.xmdl.taslak.model.search.OrderSearch;
import org.xmdl.taslak.service.OrderManager;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Collection;

public class OrderAction extends BaseAction implements Preparable {
    private OrderManager orderManager;
    private Collection<Order> orders;
    private Order order;
    private Long  id;
    private OrderSearch orderSearch = new OrderSearch();
    private Long idToCopy;

    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    /**
     * Grab the entity from the database before populating with request parameters
     */
    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            if (id != null ) {
                order = orderManager.get(id);
            }
        }
    }

    public String list() {
    	if (log.isDebugEnabled()) log.debug("list() <-");

        orders = orderManager.search(orderSearch);

        if (log.isDebugEnabled()) log.debug("listing items:" + orders.size());
    	if (log.isDebugEnabled()) log.debug("list() ->");
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

    public String copy() {
        if(idToCopy !=null){
            order = orderManager.get(idToCopy);
        }
        order.setId(null);
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


    public String deleteMass() throws Exception {
        boolean cannotDeleted = false;
        boolean anyDeleted = false;
        if (getDeleteId() != null) {
            for (String idStr : getDeleteId()) {
                try {
                    orderManager.remove(new Long(idStr));
                    anyDeleted = true;
                } catch (DataIntegrityViolationException e) {
                    e.printStackTrace();
                    cannotDeleted = true;
                }
            }
        }
        if (cannotDeleted)      saveMessage(getText("Order.cannotBeDeleted"));
        if (anyDeleted)         saveMessage(getText("Order.deleted"));

        orders = orderManager.getAll();
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

    public OrderSearch getOrderSearch() {
        return orderSearch;
    }

    public void setOrderSearch(OrderSearch orderSearch) {
        this.orderSearch = orderSearch;
    }

    public Long getIdToCopy() {
        return idToCopy;
    }

    public void setIdToCopy(Long idToCopy) {
        this.idToCopy = idToCopy;
    }
}