package org.xmdl.taslak.dao;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.xmdl.ida.lib.test.BaseDaoTestCase;
import org.xmdl.mesken.dao.UserDao;
import org.xmdl.mesken.model.User;
import org.xmdl.taslak.model.Profile;
import org.xmdl.taslak.model.search.ProfileSearch;

public class ProfileDaoTest extends BaseDaoTestCase {
    private ProfileDao profileDao = null;

    private UserDao userDao = null;

    public void setProfileDao(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    /**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void testAddAndRemoveProfile() throws Exception {
        Profile profile = new Profile();
        profile.setPrivatePhone("abcd");

        profile = profileDao.save(profile);
        flush();

        profile = profileDao.get(profile.getId());

        assertEquals("abcd", profile.getPrivatePhone());
        assertNotNull(profile.getId());

        log.debug("removing profile...");

        profileDao.remove(profile.getId());
        flush();

        try {
            profileDao.get(profile.getId());
            fail("Profile found in database");
        } catch (DataAccessException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }

    public void testSearch() throws Exception {
    	ProfileSearch search = new ProfileSearch(null, "abcd");
        Collection<Profile> profiles = profileDao.search(search);
        assertTrue(profiles.size() > 0);
    }

    public void testSearchWithUser() throws Exception {
    	User user = userDao.get(1L);
		ProfileSearch search = new ProfileSearch(user, null);
        Collection<Profile> profiles = profileDao.search(search);
        assertTrue(profiles.size() > 0);
    }

    public void testGetProfileByUsername() throws Exception {
    	Profile profile = profileDao.getProfileByUsername("user");
    	assertNotNull(profile);
    	User user = profile.getUser();
    	assertNotNull(user);
    	assertEquals("user", user.getUsername());
    	assertEquals("00001", profile.getPrivatePhone());
    }

    protected void onSetUpBeforeTransaction() throws Exception {
        super.onSetUpBeforeTransaction();

        Profile profile = new Profile();
        profile.setPrivatePhone("abcd");
        profileDao.save(profile);
    }
}