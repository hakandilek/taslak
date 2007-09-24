package org.xmdl.taslak.webapp.action;

import com.opensymphony.xwork2.Preparable;
import org.xmdl.taslak.service.ProductManager;
import org.xmdl.taslak.model.Product;
import org.xmdl.taslak.model.search.ProductSearch;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Collection;

public class ProductAction extends BaseAction implements Preparable {
    private ProductManager productManager;
    private Collection<Product> products;
    private Product product;
    private Long id;

    private Long idToCopy;

    private ProductSearch productSearch;

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

    public Collection getProducts() {
        return products;
    }

    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            if (id != null) {
                product = productManager.get((long) id);
            }
        }
    }

    public String list() {
        products = productManager.search(productSearch);
        return SUCCESS;
    }

    public void setId(Long id) {
        this. id = id;
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

    public String copy() {
        if(idToCopy !=null){
            product = productManager.get(idToCopy);
        }
        product.setId(null);
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

    public String deleteMass() throws Exception {
        boolean cannotDeleted = false;
        boolean anyDeleted = false;
        if (getDeleteId() != null) {
            for (String idStr : getDeleteId()) {
                try {
                    productManager.remove(new Long(idStr));
                    anyDeleted = true;
                } catch (DataIntegrityViolationException e) {
                    e.printStackTrace();
                    cannotDeleted = true;
                }
            }
        }
        if (cannotDeleted) saveMessage(getText("Product.cannotBeDeleted"));
        if (anyDeleted) saveMessage(getText("Product.deleted"));

        products = productManager.getAll();
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

    public ProductSearch getProductSearch() {
        return productSearch;
    }

    public void setProductSearch(ProductSearch productSearch) {
        this.productSearch = productSearch;
    }

    public Long getIdToCopy() {
        return idToCopy;
    }

    public void setIdToCopy(Long idToCopy) {
        this.idToCopy = idToCopy;
    }
}