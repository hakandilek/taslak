
package org.xmdl.taslak.service;


import java.util.Collection;

import org.xmdl.ida.lib.service.GenericManager;

import org.xmdl.taslak.model.*;

import org.xmdl.taslak.model.search.*; 







/**
 *
 * Supplier Service interface
 *  
 * $Id$
 *
 * @generated
 */ 
public interface SupplierManager extends GenericManager<Supplier, Long>{

    /**
     * @generated
     */ 
    Collection<Supplier> search(SupplierSearch supplierSearch);

}
