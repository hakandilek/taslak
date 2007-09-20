package org.xmdl.taslak.webapp.action;

import com.opensymphony.xwork2.Preparable;
import org.xmdl.taslak.service.GenericManager;
import org.xmdl.taslak.model.Product;
import org.xmdl.taslak.webapp.action.BaseAction;

import java.util.List;

public class ProductAction extends BaseAction implements Preparable {
    private GenericManager<Product, Long> productManager;
    private List products;
    private Product product;
    private Long  id;

    public void setProductManager(GenericManager<Product, Long> productManager) {
        this.productManager = productManager;
    }

    public List getProducts() {
        return products;
    }

    /**
     * Grab the entity from the database before populating with request parameters
     */
    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            String productId = getRequest().getParameter("product.id");
            if (productId != null && !productId.equals("")) {
                product = productManager.get(new Long(productId));
            }
        }
    }

    public String list() {
        products = productManager.getAll();
        return SUCCESS;
    }

    public void setId(Long  id) {
        this. id =  id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String delete() {
        productManager.remove(product.getId());
        saveMessage(getText("product.deleted"));

        return SUCCESS;
    }

    public String edit() {
        if (id != null) {
            product = productManager.get(id);
        } else {
            product = new Product();
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

        boolean isNew = (product.getId() == null);

        productManager.save(product);

        String key = (isNew) ? "product.added" : "product.updated";
        saveMessage(getText(key));

        if (!isNew) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }
}