package org.xmdl.taslak.webapp.action;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.xmdl.ida.lib.web.action.BaseAction;
import org.xmdl.taslak.model.Order;
import org.xmdl.taslak.model.OrderElement;
import org.xmdl.taslak.model.search.OrderElementSearch;
import org.xmdl.taslak.service.OrderElementManager;
import org.xmdl.taslak.service.OrderManager;
import org.xmdl.taslak.service.ProductManager;

import com.opensymphony.xwork2.Preparable;

public class OrderElementAction extends BaseAction implements Preparable {
    private OrderElementManager orderElementManager;
    private OrderManager orderManager;
    private ProductManager productManager;
    private Collection<OrderElement> orderElements;
    private OrderElement orderElement;
    private Long id;
    private Long idToCopy;
    private Long orderId;
    private Long productId;
    private Order order;

    private OrderElementSearch orderElementSearch = new OrderElementSearch();

    /**
     * Grab the entity from the database before populating with request parameters
     */
    public void prepare() {
        if (log.isDebugEnabled()) log.debug("prepare() <-");

        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            if (id != null) {
                orderElement = orderElementManager.get(id);
            } else {
                orderElement = new OrderElement();
                if (orderId != null) {
                    order = orderManager.get(orderId);
                } else {
                    order = new Order();
                }
            }
        }
        productList = productManager.getAll();

        if (log.isDebugEnabled()) log.debug("prepare() ->");
    }

    public String list() {
        if (log.isDebugEnabled()) log.debug("list() <-");

        orderElements = orderElementManager.search(orderElementSearch);

        if (log.isDebugEnabled()) log.debug("listing items:" + orderElements.size());
        if (log.isDebugEnabled()) log.debug("list() ->");
        return SUCCESS;
    }

    @org.apache.struts2.interceptor.validation.SkipValidation
    public String delete() {
        if (log.isDebugEnabled()) log.debug("delete() <-");

        orderElementSearch.setOrder(orderElement.getOrder());
        orderElements = orderElementManager.search(orderElementSearch);

        orderElementManager.remove(orderElement.getId());
        saveMessage(getText("orderElement.deleted"));

        if (log.isDebugEnabled()) log.debug("deleting orderElement: " + orderElement);
        if (log.isDebugEnabled()) log.debug("delete() ->");
        return SUCCESS;
    }

    public String copy() {
        if (log.isDebugEnabled()) log.debug("copy() <-");

        if (idToCopy != null) {
            orderElement = orderElementManager.get(idToCopy);
        }
        if (log.isDebugEnabled()) log.debug("copying orderElement: " + orderElement);

        order = orderElement.getOrder();

        orderElementSearch.setOrder(orderElement.getOrder());
        orderElements = orderElementManager.search(orderElementSearch);

        if (log.isDebugEnabled()) log.debug("copy() ->");

        orderElement.setId(null);
        return SUCCESS;
    }

    public String edit() {
        if (log.isDebugEnabled()) log.debug("edit() <-");

        if (id != null) {
            orderElement = orderElementManager.get(id);
            order = orderElement.getOrder();
        } else {
            orderElement = new OrderElement();
            order = orderManager.get(orderId);
            orderElement.setOrder(order);
        }

        if (log.isDebugEnabled()) log.debug("editing orderElement: " + orderElement);

        orderElementSearch.setOrder(orderElement.getOrder());
        orderElements = orderElementManager.search(orderElementSearch);

        if (log.isDebugEnabled()) log.debug("edit() ->");

        return SUCCESS;
    }

    @org.apache.struts2.interceptor.validation.SkipValidation
    public String deleteMass() throws Exception {
        if (log.isDebugEnabled()) log.debug("deleteMass() <-");

        boolean cannotDeleted = false;
        boolean anyDeleted = false;
        if (getDeleteId() != null) {
            for (String idStr : getDeleteId()) {
                try {
                    orderElementManager.remove(new Long(idStr));
                    anyDeleted = true;

                    if (log.isDebugEnabled()) log.debug("deleting orderElement with id: " + idStr);

                } catch (DataIntegrityViolationException e) {
                    e.printStackTrace();
                    cannotDeleted = true;

                    if (log.isDebugEnabled()) log.debug("could not delete orderElement with id: " + idStr);
                }
            }
        }
        if (cannotDeleted) saveMessage(getText("OrderElement.cannotBeDeleted"));
        if (anyDeleted) saveMessage(getText("OrderElement.deleted"));

        order = orderElement.getOrder();
        orderElementSearch.setOrder(order);
        orderElements = orderElementManager.search(orderElementSearch);

        if (log.isDebugEnabled()) log.debug("deleteMass() ->");

        return SUCCESS;
    }

    public String save() throws Exception {
        if (log.isDebugEnabled()) log.debug("save() <-");

        if (cancel != null) {
            if (log.isDebugEnabled()) log.debug("save() ->");
            return "cancel";
        }

        if (delete != null) {
            if (log.isDebugEnabled()) log.debug("save() ->");
            return delete();
        }

        boolean isNew = (orderElement.getId() == null);
        orderElement.setOrder(orderManager.get(orderElement.getOrder().getId()));
        orderElementManager.save(orderElement);

        String key = (isNew) ? "orderElement.added" : "orderElement.updated";
        saveMessage(getText(key));

        String logMessage = (isNew) ? "adding orderElement: "+ orderElement : "updating orderElement: " + orderElement;
        if (log.isDebugEnabled()) log.debug(logMessage);

        orderElementSearch.setOrder(orderElement.getOrder());
        orderElements = orderElementManager.search(orderElementSearch);

        order = orderElement.getOrder();

        // prepare page to create a new OrderElement item.
        orderElement = new OrderElement();
        orderElement.setOrder(order);

        if (log.isDebugEnabled()) log.debug("save() ->");

        if (!isNew) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    private List productList;

    public List getProductList() {
        return productList;
    }

    public void setProductList(List productList) {
        this.productList = productList;
    }

    public OrderElementSearch getOrderElementSearch() {
        return orderElementSearch;
    }

    public void setOrderElementSearch(OrderElementSearch orderElementSearch) {
        this.orderElementSearch = orderElementSearch;
    }

    public Long getIdToCopy() {
        return idToCopy;
    }

    public void setIdToCopy(Long idToCopy) {
        this.idToCopy = idToCopy;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setOrderElementManager(OrderElementManager orderElementManager) {
        this.orderElementManager = orderElementManager;
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    public Collection<OrderElement> getOrderElements() {
        return orderElements;
    }

    public void setId(Long id) {
        this. id = id;
    }

    public OrderElement getOrderElement() {
        return orderElement;
    }

    public void setOrderElement(OrderElement orderElement) {
        this.orderElement = orderElement;
    }
    
}
