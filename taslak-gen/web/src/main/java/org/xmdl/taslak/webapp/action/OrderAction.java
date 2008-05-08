package org.xmdl.taslak.webapp.action;


import com.opensymphony.xwork2.Preparable;

import java.util.*;

import org.apache.struts2.interceptor.validation.SkipValidation;

import org.springframework.dao.DataIntegrityViolationException;

import org.xmdl.ida.lib.web.action.BaseAction;

import org.xmdl.taslak.model.*;

import org.xmdl.taslak.model.search.*;

import org.xmdl.taslak.service.*; 










/**
 * @generated
 */
public class OrderAction extends BaseAction implements Preparable {

    /**
     * @generated
     */
    private OrderManager orderManager;

    /**
     * @generated
     */
    private Collection<Order> orders;

    /**
     * @generated
     */
    private Order order;
    
    /**
     * @generated
     */
    private Long id;

    /**
     * @generated
     */
    private Long idToCopy;


    /**
     * @generated
     */
    private OrderSearch orderSearch = new OrderSearch();

    /**
     * @generated
     */
    public void setOrderManager(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    /**
     * @generated
     */
    public Collection<Order> getOrders() {
        return orders;
    }

    /**
     * @generated
     */
    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            if (id != null) {
                order = orderManager.get((long) id);
            }else{
                order = new Order();
            }
        }


    }

    /**
     * @generated
     */
    @SkipValidation
    public String list() {
        if (log.isDebugEnabled()) log.debug("list() <-");

        orders = orderManager.search(orderSearch);

        if (log.isDebugEnabled()) log.debug("listing items:" + orders == null ? null : orders.size());
        if (log.isDebugEnabled()) log.debug("list() ->");
        return SUCCESS;
    }

    /**
     * @generated
     */
    public void setId(Long id) {
        this. id = id;
    }

    /**
     * @generated
     */
    public Order getOrder() {
        return order;
    }

    /**
     * @generated
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * @generated
     */
    @SkipValidation
    public String delete() {
        if (log.isDebugEnabled()) log.debug("delete() <-");

        orderManager.remove(order.getId());
        saveMessage(getText("order.deleted"));

        if (log.isDebugEnabled()) log.debug("deleting order: " + order);
        if (log.isDebugEnabled()) log.debug("delete() ->");

        return SUCCESS;
    }

    /**
     * @generated
     */
    public String copy() {
        if (log.isDebugEnabled()) log.debug("copy() <-");

        if (idToCopy != null) {
            order = orderManager.get(idToCopy);
        }

        order.setId(null);

        if (log.isDebugEnabled()) log.debug("copying order: " + order);
        if (log.isDebugEnabled()) log.debug("copy() ->");

        return SUCCESS;
    }

    /**
     * @generated
     */
    public String edit() {
        if (log.isDebugEnabled()) log.debug("edit() <-");

        if (id != null) {
            order = orderManager.get(id);
        } else {
            order = new Order();
        }

        if (log.isDebugEnabled()) log.debug("editing order: " + order);
        if (log.isDebugEnabled()) log.debug("edit() ->");

        return SUCCESS;
    }

    /**
     * @generated
     */
    @SkipValidation
    public String deleteMass() throws Exception {
        if (log.isDebugEnabled()) log.debug("deleteMass() <-");

        boolean cannotDeleted = false;
        boolean anyDeleted = false;
        if (getDeleteId() != null) {
            for (String idStr : getDeleteId()) {
                try {
                    orderManager.remove(new Long(idStr));
                    anyDeleted = true;

                    if (log.isDebugEnabled()) log.debug("deleting order with id: " + idStr);
                } catch (DataIntegrityViolationException e) {
                    e.printStackTrace();
                    cannotDeleted = true;

                    if (log.isDebugEnabled()) log.debug("could not delete order with id: " + idStr);
                }
            }
        }
        if (cannotDeleted) saveMessage(getText("Order.cannotBeDeleted"));
        if (anyDeleted) saveMessage(getText("Order.deleted"));

        orders = orderManager.search(orderSearch);

        if (log.isDebugEnabled()) log.debug("deleteMass() ->");

        return SUCCESS;
    }


    /**
     * @generated
     */
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

        boolean isNew = (order.getId() == null);


        orderManager.save(order);

        String key = (isNew) ? "order.added" : "order.updated";
        saveMessage(getText(key));

        String logMessage = (isNew) ? "adding order: " + order : "updating order: " + order;
        if (log.isDebugEnabled()) log.debug(logMessage);
        if (log.isDebugEnabled()) log.debug("save() ->");

        if (!isNew) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    /**
     * @generated
     */
    public OrderSearch getOrderSearch() {
        return orderSearch;
    }

    /**
     * @generated
     */
    public void setOrderSearch(OrderSearch orderSearch) {
        this.orderSearch = orderSearch;
    }

    /**
     * @generated
     */
    public Long getIdToCopy() {
        return idToCopy;
    }

    /**
     * @generated
     */
    public void setIdToCopy(Long idToCopy) {
        this.idToCopy = idToCopy;
    }

    /**
     * @generated
     */
    public OrderManager getOrderManager() {
        return orderManager;
    }


}