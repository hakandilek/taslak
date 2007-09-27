package org.xmdl.taslak.webapp.action;

import com.opensymphony.xwork2.Preparable;
import org.xmdl.taslak.service.OrderManager;
import org.xmdl.taslak.service.OrderElementManager;
import org.xmdl.taslak.service.ProductManager;
import org.xmdl.taslak.model.OrderElement;
import org.xmdl.taslak.model.search.OrderElementSearch;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Collection;

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

    private OrderElementSearch orderElementSearch = new OrderElementSearch();

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

    /**
     * Grab the entity from the database before populating with request parameters
     */
    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            if (id != null ) {
                orderElement = orderElementManager.get(id);
            } else {
                orderElement = new OrderElement();
            }
        }
        orderList = orderManager.getAll();
        productList = productManager.getAll();
    }

    public String list() {
    	if (log.isDebugEnabled()) log.debug("list() <-");

        orderElements = orderElementManager.search(orderElementSearch);

        if (log.isDebugEnabled()) log.debug("listing items:" + orderElements.size());
    	if (log.isDebugEnabled()) log.debug("list() ->");
        return SUCCESS;
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

    public String delete() {
        orderElementManager.remove(orderElement.getId());
        saveMessage(getText("orderElement.deleted"));

        return SUCCESS;
    }

    public String copy() {
        if (idToCopy != null) {
            orderElement = orderElementManager.get(idToCopy);
        }
        orderElement.setId(null);
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


    public String deleteMass() throws Exception {
        boolean cannotDeleted = false;
        boolean anyDeleted = false;
        if (getDeleteId() != null) {
            for (String idStr : getDeleteId()) {
                try {
                    orderElementManager.remove(new Long(idStr));
                    anyDeleted = true;
                } catch (DataIntegrityViolationException e) {
                    e.printStackTrace();
                    cannotDeleted = true;
                }
            }
        }
        if (cannotDeleted) saveMessage(getText("OrderElement.cannotBeDeleted"));
        if (anyDeleted) saveMessage(getText("OrderElement.deleted"));

        orderElements = orderElementManager.getAll();
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

    private List orderList;

    public List getOrderList() {
        return orderList;
    }

    public void setOrderList(List orderList) {
        this.orderList = orderList;
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
}