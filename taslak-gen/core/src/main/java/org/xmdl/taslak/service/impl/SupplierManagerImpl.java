
package org.xmdl.taslak.service.impl;


import java.util.Collection;

import org.xmdl.ida.lib.service.impl.GenericManagerImpl;

import org.xmdl.taslak.dao.*;

import org.xmdl.taslak.model.*;

import org.xmdl.taslak.model.search.*;

import org.xmdl.taslak.service.*; 









/**
 *
 * Supplier Service implementation
 *  
 * $Id$
 *
 * @generated
 */ 
public class SupplierManagerImpl extends GenericManagerImpl<Supplier, Long> implements SupplierManager {

    /**
     * @generated
     */ 
    SupplierDAO supplierDAO;

    /**
     * @generated
     */ 
    public SupplierManagerImpl(SupplierDAO supplierDAO) {
        super(supplierDAO);
        this.supplierDAO = supplierDAO;
    }

    /**
     * @generated
     */ 
    public Collection<Supplier> search(SupplierSearch supplierSearch) {
        return supplierDAO.search(supplierSearch);
    }

}
