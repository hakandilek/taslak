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
public class OrderElementAction extends BaseAction implements Preparable {

    /**
     * @generated
     */
    private OrderElementManager orderElementManager;

    /**
     * @generated
     */
    private OrderManager orderManager;

    /**
     * @generated
     */
    private Collection<OrderElement> orderElements;

    /**
     * @generated
     */
    private OrderElement orderElement;
    
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
    private OrderElementSearch orderElementSearch = new OrderElementSearch();

    /**
     * @generated
     */
     private Order order;

    /**
     * @generated
     */
     private Long orderID;

    /**
     * @generated
     */
    public void setOrderElementManager(OrderElementManager orderElementManager) {
        this.orderElementManager = orderElementManager;
    }

    /**
     * @generated
     */
    public Collection<OrderElement> getOrderElements() {
        return orderElements;
    }

    /**
     * @generated
     */
    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            if (id != null) {
                orderElement = orderElementManager.get((long) id);
            }else{
                orderElement = new OrderElement();
               	if (orderID == null) {
                    try {
               			String parameter = getRequest().getParameter("orderElement.order.id");
                		orderID = Long.parseLong(parameter);
                    } catch (NumberFormatException e) {
                        log.info("no id");
                    }
                }
                
                if (orderID != null) {
                    order = orderManager.get(orderID);
                   	orderElementSearch.setOrder(order);
                    orderElements = orderElementManager.search(orderElementSearch);
                } else {
                	order = new Order();
            	}
            }
        }


    }

    /**
     * @generated
     */
    @SkipValidation
    public String list() {
        if (log.isDebugEnabled()) log.debug("list() <-");

        orderElements = orderElementManager.search(orderElementSearch);

        if (log.isDebugEnabled()) log.debug("listing items:" + orderElements == null ? null : orderElements.size());
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
    public OrderElement getOrderElement() {
        return orderElement;
    }

    /**
     * @generated
     */
    public void setOrderElement(OrderElement orderElement) {
        this.orderElement = orderElement;
    }

    /**
     * @generated
     */
    @SkipValidation
    public String delete() {
        if (log.isDebugEnabled()) log.debug("delete() <-");

        orderElementManager.remove(orderElement.getId());
        saveMessage(getText("orderElement.deleted"));

        if (log.isDebugEnabled()) log.debug("deleting orderElement: " + orderElement);
        if (log.isDebugEnabled()) log.debug("delete() ->");

        return SUCCESS;
    }

    /**
     * @generated
     */
    public String copy() {
        if (log.isDebugEnabled()) log.debug("copy() <-");

        if (idToCopy != null) {
            orderElement = orderElementManager.get(idToCopy);
        }

        orderElement.setId(null);

        if (log.isDebugEnabled()) log.debug("copying orderElement: " + orderElement);
        if (log.isDebugEnabled()) log.debug("copy() ->");

        return SUCCESS;
    }

    /**
     * @generated
     */
    public String edit() {
        if (log.isDebugEnabled()) log.debug("edit() <-");

        if (id != null) {
            orderElement = orderElementManager.get(id);
        } else {
            orderElement = new OrderElement();
        }

        if (log.isDebugEnabled()) log.debug("editing orderElement: " + orderElement);
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

        orderElements = orderElementManager.search(orderElementSearch);

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

        boolean isNew = (orderElement.getId() == null);


        orderElementManager.save(orderElement);

        String key = (isNew) ? "orderElement.added" : "orderElement.updated";
        saveMessage(getText(key));

        String logMessage = (isNew) ? "adding orderElement: " + orderElement : "updating orderElement: " + orderElement;
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
    public OrderElementSearch getOrderElementSearch() {
        return orderElementSearch;
    }

    /**
     * @generated
     */
    public void setOrderElementSearch(OrderElementSearch orderElementSearch) {
        this.orderElementSearch = orderElementSearch;
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
    public OrderElementManager getOrderElementManager() {
        return orderElementManager;
    }


}