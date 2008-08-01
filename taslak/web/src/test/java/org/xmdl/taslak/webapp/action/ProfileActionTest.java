package org.xmdl.taslak.webapp.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.xmdl.ida.lib.web.test.BaseActionTestCase;
import org.xmdl.mesken.model.Address;
import org.xmdl.mesken.model.User;
import org.xmdl.taslak.model.Profile;
import org.xmdl.taslak.model.search.ProfileSearch;
import org.xmdl.taslak.service.ProfileManager;

import com.opensymphony.xwork2.ActionSupport;

public class ProfileActionTest extends BaseActionTestCase {
    private ProfileAction action;

    @Override
    protected void onSetUpInTransaction() throws Exception {
        super.onSetUpInTransaction();
        action = new ProfileAction();
        ProfileManager profileManager = (ProfileManager) applicationContext.getBean("profileManager");
        action.setProfileManager(profileManager);

        // add a test profile to the database
        Profile profile = new Profile();
        User user = new User();
        profile.setUser(user);
        Address address = new Address();
        user.setAddress(address);

        // enter all required fields
        profile.setPrivatePhone("55555");
        address.setCity("city");
        address.setPostalCode("00000");
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email");

        ProfileSearch search = new ProfileSearch();
        action.setProfileSearch(search);

        log.debug("profile = " + profile);
        profile = profileManager.save(profile);
        log.debug("profile = " + profile);
        log.debug("profile.user = " + profile.getUser());
        log.debug("profile.user.id = " + profile.getUser().getId());
    }

    public void testSearch() throws Exception {
        action.setProfileTypeIds(null);
        assertEquals(action.list(), ActionSupport.SUCCESS);
        assertTrue(action.getProfiles().size() >= 1);
    }

    public void testCopy() throws Exception {
        action.setIdToCopy(1L);
        assertEquals("success", action.copy());
        assertNotNull(action.getProfile());
        assertNull(action.getProfile().getId());
    }

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        action.setId(1L);
        assertNull(action.getProfile());
        assertEquals("success", action.edit());
        assertNotNull(action.getProfile());
        assertFalse(action.hasActionErrors());
    }

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
        
        User user = profile.getUser();
        assertNotNull(user);
        assertNotNull(user.getId());
    }

    public void testRemove() throws Exception {
    	MockHttpServletRequest request = new MockHttpServletRequest();
        ServletActionContext.setRequest(request);
        action.setDelete("");
        Profile profile = new Profile();
        profile.setId(1L);
        log.debug("profile = " + profile);
        log.debug("profile.id = " + profile.getId());
        action.setProfile(profile);
        assertEquals("success", action.delete());
        assertNotNull(request.getSession().getAttribute("messages"));
    }

    public void testMassDelete() throws Exception {
        Profile p = action.getProfileManager().getAll().get(0);

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