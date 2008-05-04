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
public class SupplierAction extends BaseAction implements Preparable {

    /**
     * @generated
     */
    private SupplierManager supplierManager;

    /**
     * @generated
     */
    private Collection<Supplier> suppliers;

    /**
     * @generated
     */
    private Supplier supplier;
    
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
    private SupplierSearch supplierSearch = new SupplierSearch();

    /**
     * @generated
     */
    public void setSupplierManager(SupplierManager supplierManager) {
        this.supplierManager = supplierManager;
    }

    /**
     * @generated
     */
    public Collection<Supplier> getSuppliers() {
        return suppliers;
    }

    /**
     * @generated
     */
    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            if (id != null) {
                supplier = supplierManager.get((long) id);
            }else{
                supplier = new Supplier();
            }
        }


    }

    /**
     * @generated
     */
    @SkipValidation
    public String list() {
        if (log.isDebugEnabled()) log.debug("list() <-");

        suppliers = supplierManager.search(supplierSearch);

        if (log.isDebugEnabled()) log.debug("listing items:" + suppliers.size());
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
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * @generated
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    /**
     * @generated
     */
    @SkipValidation
    public String delete() {
        if (log.isDebugEnabled()) log.debug("delete() <-");

        supplierManager.remove(supplier.getId());
        saveMessage(getText("supplier.deleted"));

        if (log.isDebugEnabled()) log.debug("deleting supplier: " + supplier);
        if (log.isDebugEnabled()) log.debug("delete() ->");

        return SUCCESS;
    }

    /**
     * @generated
     */
    public String copy() {
        if (log.isDebugEnabled()) log.debug("copy() <-");

        if (idToCopy != null) {
            supplier = supplierManager.get(idToCopy);
        }

        supplier.setId(null);

        if (log.isDebugEnabled()) log.debug("copying supplier: " + supplier);
        if (log.isDebugEnabled()) log.debug("copy() ->");

        return SUCCESS;
    }

    /**
     * @generated
     */
    public String edit() {
        if (log.isDebugEnabled()) log.debug("edit() <-");

        if (id != null) {
            supplier = supplierManager.get(id);
        } else {
            supplier = new Supplier();
        }

        if (log.isDebugEnabled()) log.debug("editing supplier: " + supplier);
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
                    supplierManager.remove(new Long(idStr));
                    anyDeleted = true;

                    if (log.isDebugEnabled()) log.debug("deleting supplier with id: " + idStr);
                } catch (DataIntegrityViolationException e) {
                    e.printStackTrace();
                    cannotDeleted = true;

                    if (log.isDebugEnabled()) log.debug("could not delete supplier with id: " + idStr);
                }
            }
        }
        if (cannotDeleted) saveMessage(getText("Supplier.cannotBeDeleted"));
        if (anyDeleted) saveMessage(getText("Supplier.deleted"));

        suppliers = supplierManager.search(supplierSearch);

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

        boolean isNew = (supplier.getId() == null);


        supplierManager.save(supplier);

        String key = (isNew) ? "supplier.added" : "supplier.updated";
        saveMessage(getText(key));

        String logMessage = (isNew) ? "adding supplier: " + supplier : "updating supplier: " + supplier;
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
    public SupplierSearch getSupplierSearch() {
        return supplierSearch;
    }

    /**
     * @generated
     */
    public void setSupplierSearch(SupplierSearch supplierSearch) {
        this.supplierSearch = supplierSearch;
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
    public SupplierManager getSupplierManager() {
        return supplierManager;
    }


}