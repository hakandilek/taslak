package org.xmdl.taslak.webapp.action;

import java.util.Collection;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.dao.DataIntegrityViolationException;
import org.xmdl.ida.lib.web.action.BaseAction;
import org.xmdl.taslak.model.Supplier;
import org.xmdl.taslak.model.search.SupplierSearch;
import org.xmdl.taslak.service.SupplierManager;

import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
public class SupplierAction extends BaseAction implements Preparable {

    private SupplierManager supplierManager;
    private Collection<Supplier> suppliers;
    private Supplier supplier;
    private Long id;

    private Long idToCopy;

    private SupplierSearch supplierSearch = new SupplierSearch();

    public void setSupplierManager(SupplierManager supplierManager) {
        this.supplierManager = supplierManager;
    }

    public Collection<Supplier> getSuppliers() {
        return suppliers;
    }

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

    @SkipValidation
    public String list() {
        if (log.isDebugEnabled()) log.debug("list() <-");

        suppliers = supplierManager.search(supplierSearch);

        if (log.isDebugEnabled()) log.debug("listing items:" + suppliers.size());
        if (log.isDebugEnabled()) log.debug("list() ->");
        return SUCCESS;
    }

    public void setId(Long id) {
        this. id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @SkipValidation
    public String delete() {
        if (log.isDebugEnabled()) log.debug("delete() <-");

        supplierManager.remove(supplier.getId());
        saveMessage(getText("supplier.deleted"));

        if (log.isDebugEnabled()) log.debug("deleting supplier: " + supplier);
        if (log.isDebugEnabled()) log.debug("delete() ->");

        return SUCCESS;
    }

    public String copy() {
        if (log.isDebugEnabled()) log.debug("copy() <-");

        if (idToCopy != null) {
            supplier = supplierManager.get(idToCopy);
        }

        if (log.isDebugEnabled()) log.debug("copying supplier: " + supplier);

        if (log.isDebugEnabled()) log.debug("copy() ->");

        supplier.setId(null);
        return SUCCESS;
    }

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

    public SupplierSearch getSupplierSearch() {
        return supplierSearch;
    }

    public void setSupplierSearch(SupplierSearch supplierSearch) {
        this.supplierSearch = supplierSearch;
    }

    public Long getIdToCopy() {
        return idToCopy;
    }

    public void setIdToCopy(Long idToCopy) {
        this.idToCopy = idToCopy;
    }

    public SupplierManager getSupplierManager() {
        return supplierManager;
    }

	@SkipValidation
	public String ajaxList() {
	    if (log.isDebugEnabled()) log.debug("list() <-");
	
	    suppliers = supplierManager.search(supplierSearch);
	
	    if (log.isDebugEnabled()) log.debug("listing items:" + suppliers.size());
	    if (log.isDebugEnabled()) log.debug("list() ->");
	    return SUCCESS;
	}

}