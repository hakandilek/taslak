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
public class ProductAction extends BaseAction implements Preparable {

    /**
     * @generated
     */
    private ProductManager productManager;

    /**
     * @generated
     */
    private Collection<Product> products;

    /**
     * @generated
     */
    private Product product;
    
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
    private ProductSearch productSearch = new ProductSearch();

    /**
     * @generated
     */
    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

    /**
     * @generated
     */
    public Collection<Product> getProducts() {
        return products;
    }

    /**
     * @generated
     */
    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            if (id != null) {
                product = productManager.get((long) id);
            }else{
                product = new Product();
            }
        }


    }

    /**
     * @generated
     */
    @SkipValidation
    public String list() {
        if (log.isDebugEnabled()) log.debug("list() <-");

        products = productManager.search(productSearch);

        if (log.isDebugEnabled()) log.debug("listing items:" + products == null ? null : products.size());
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
    public Product getProduct() {
        return product;
    }

    /**
     * @generated
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @generated
     */
    @SkipValidation
    public String delete() {
        if (log.isDebugEnabled()) log.debug("delete() <-");

        productManager.remove(product.getId());
        saveMessage(getText("product.deleted"));

        if (log.isDebugEnabled()) log.debug("deleting product: " + product);
        if (log.isDebugEnabled()) log.debug("delete() ->");

        return SUCCESS;
    }

    /**
     * @generated
     */
    public String copy() {
        if (log.isDebugEnabled()) log.debug("copy() <-");

        if (idToCopy != null) {
            product = productManager.get(idToCopy);
        }

        product.setId(null);

        if (log.isDebugEnabled()) log.debug("copying product: " + product);
        if (log.isDebugEnabled()) log.debug("copy() ->");

        return SUCCESS;
    }

    /**
     * @generated
     */
    public String edit() {
        if (log.isDebugEnabled()) log.debug("edit() <-");

        if (id != null) {
            product = productManager.get(id);
        } else {
            product = new Product();
        }

        if (log.isDebugEnabled()) log.debug("editing product: " + product);
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
                    productManager.remove(new Long(idStr));
                    anyDeleted = true;

                    if (log.isDebugEnabled()) log.debug("deleting product with id: " + idStr);
                } catch (DataIntegrityViolationException e) {
                    e.printStackTrace();
                    cannotDeleted = true;

                    if (log.isDebugEnabled()) log.debug("could not delete product with id: " + idStr);
                }
            }
        }
        if (cannotDeleted) saveMessage(getText("Product.cannotBeDeleted"));
        if (anyDeleted) saveMessage(getText("Product.deleted"));

        products = productManager.search(productSearch);

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

        boolean isNew = (product.getId() == null);


        productManager.save(product);

        String key = (isNew) ? "product.added" : "product.updated";
        saveMessage(getText(key));

        String logMessage = (isNew) ? "adding product: " + product : "updating product: " + product;
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
    public ProductSearch getProductSearch() {
        return productSearch;
    }

    /**
     * @generated
     */
    public void setProductSearch(ProductSearch productSearch) {
        this.productSearch = productSearch;
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
    public ProductManager getProductManager() {
        return productManager;
    }


}