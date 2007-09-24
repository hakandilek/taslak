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

    private OrderElementSearch orderElementSearch;

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
            String orderElementId = getRequest().getParameter("orderElement.id");
            if (orderElementId != null && !orderElementId.equals("")) {
                orderElement = orderElementManager.get(new Long(orderElementId));
            } else {
                orderElement = new OrderElement();
            }

            String orderId = getRequest().getParameter("orderElement.order.id");
            if (orderId != null && !orderId.equals("")) {
                if (orderId.equals("-12345678")) {
                    orderElement.setOrder(null);
                } else {
                    orderElement.setOrder(orderManager.get(new Long(orderId)));
                }
            }

            String productId = getRequest().getParameter("orderElement.product.id");
            if (productId != null && !productId.equals("")) {
                if (productId.equals("-12345678")) {
                    orderElement.setProduct(null);
                } else {
                    orderElement.setProduct(productManager.get(new Long(productId)));
                }
            }
        }
        orderList = orderManager.getAll();
        productList = productManager.getAll();
    }

    public String list() {
        orderElements = orderElementManager.search(orderElementSearch);
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
        String idToCopy = getRequest().getParameter("idToCopy");
        if (idToCopy != null) {
            orderElement = orderElementManager.get(Long.parseLong(idToCopy));
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
}