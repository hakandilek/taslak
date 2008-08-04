package org.xmdl.taslak.webapp.action;


import com.opensymphony.xwork2.ActionSupport;

import java.util.*;

import org.apache.struts2.ServletActionContext;

import org.hibernate.exception.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.mock.web.MockHttpServletRequest;

import org.xmdl.ida.lib.web.test.BaseActionTestCase;

import org.xmdl.taslak.model.*;

import org.xmdl.taslak.model.search.*;

import org.xmdl.taslak.service.*; 












/**
 * 
 * @author Hakan Dilek
 * 
 * @generated
 */
public class ProfileActionTest extends BaseActionTestCase {
    /**
     * @generated
     */
    private ProfileAction action;


    /**
     * @generated
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();
        action = new ProfileAction();
        ProfileManager profileManager = (ProfileManager) applicationContext.getBean("profileManager");
        action.setProfileManager(profileManager);

        // add a test profile to the database
        Profile profile = new Profile();

        // enter all required fields
        profile.setPrivatePhone("HMrrM6nArUp SJc");


        profileManager.save(profile);
    }

    /**
     * @generated
     */
    public void testSearch() throws Exception {
        ProfileSearch search = new ProfileSearch();
        action.setProfileSearch(search);

        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertTrue(action.getProfiles().size() >= 1);
    }

    /**
     * @generated
     */
    public void testCopy() throws Exception {
        action.setIdToCopy(1L);
        assertEquals("success", action.copy());
        assertNotNull(action.getProfile());
        assertNull(action.getProfile().getId());
    }

    /**
     * @generated
     */
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        action.setId(1L);
        assertNull(action.getProfile());
        assertEquals("success", action.edit());
        assertNotNull(action.getProfile());
        assertFalse(action.hasActionErrors());
    }

    /**
     * @generated
     */
    public void testSave() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setId(1L);
        assertEquals("success", action.edit());
        assertNotNull(action.getProfile());

        Profile profile = action.getProfile();
        // update required fields

        action.setProfile(profile);

        assertEquals("input", action.save());
        assertFalse(action.hasActionErrors());
        assertFalse(action.hasFieldErrors());
        assertNotNull(request.getSession().getAttribute("messages"));
    }

    /**
     * @generated
     */
    public void testRemove() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setDelete("");
        Profile profile = new Profile();
        profile.setId(2L);
        action.setProfile(profile);
        assertEquals("success", action.delete());
        assertNotNull(request.getSession().getAttribute("messages"));
    }

    /**
     * @generated
     */
    public void testMassDelete() throws Exception {
        Profile p = action.getProfileManager().get(new Long(3));

        List<String> deleteIds = new ArrayList<String>();
        deleteIds.add(p.getId() + "");

        action.setDeleteId(deleteIds);
        try {
            assertEquals("success", action.deleteMass());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
    }
}