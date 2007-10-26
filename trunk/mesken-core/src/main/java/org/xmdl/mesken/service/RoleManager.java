package org.xmdl.mesken.service;

import org.xmdl.ida.lib.service.UniversalManager;
import org.xmdl.mesken.model.Role;

import java.util.List;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler </a>
 */
public interface RoleManager extends UniversalManager {
    /**
     * {@inheritDoc}
     */
    List<Role> getRoles(Role role);

    /**
     * {@inheritDoc}
     */
    Role getRole(String rolename);

    /**
     * {@inheritDoc}
     */
    Role saveRole(Role role);

    /**
     * {@inheritDoc}
     */
    void removeRole(String rolename);
}
