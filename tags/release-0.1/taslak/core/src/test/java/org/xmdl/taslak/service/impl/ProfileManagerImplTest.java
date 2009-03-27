package org.xmdl.taslak.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jmock.Mock;
import org.xmdl.ida.lib.test.BaseManagerMockTestCase;
import org.xmdl.mesken.model.User;
import org.xmdl.taslak.dao.ProfileDao;
import org.xmdl.taslak.model.Profile;
import org.xmdl.taslak.model.search.ProfileSearch;

public class ProfileManagerImplTest extends BaseManagerMockTestCase {
	private ProfileManagerImpl manager = null;
	private Mock profileDao = null;
	private Profile profile = null;

	protected void setUp() throws Exception {
		profileDao = new Mock(ProfileDao.class);
		manager = new ProfileManagerImpl((ProfileDao) profileDao.proxy());
	}

	protected void tearDown() throws Exception {
		manager = null;
	}

	public void testGet() {
		log.debug("testing getProfile");

		Long id = 7L;
		profile = new Profile();

		// set expected behavior on profileDao
		profileDao.expects(once()).method("get").with(eq(id)).will(
				returnValue(profile));

		Profile result = manager.get(id);
		assertSame(profile, result);
	}

	public void testGetAll() {
		log.debug("testing getProfiles");

		List<Profile> list = new ArrayList<Profile>();

		// set expected behavior on profileDao
		profileDao.expects(once()).method("getAll").will(returnValue(list));

		List<Profile> result = manager.getAll();
		assertSame(list, result);
	}

	public void testSearch() {
		log.debug("testing search");

		List<Profile> list = new ArrayList<Profile>();
		ProfileSearch searchBean = new ProfileSearch();

		// set expected behavior on profileDao
		profileDao.expects(once()).method("search").with(eq(searchBean)).will(
				returnValue(list));

		Collection<Profile> result = manager.search(searchBean);
		assertSame(list, result);
	}

	public void testSave() {
		log.debug("testing saveProfile");

		profile = new Profile();
		Profile savedProfile = new Profile();
		User user = new User();
		user.setId(11L);
		savedProfile.setId(11L);
		savedProfile.setUser(user);

		// set expected behavior on profileDao
		profileDao.expects(once()).method("save").with(same(profile)).will(
				returnValue(savedProfile));

		savedProfile = manager.save(profile);
		user = savedProfile.getUser();
		Long profileID = savedProfile.getId();
		assertNotNull(profileID);
		assertEquals(new Long(11L), profileID);
		assertNotNull(user);
		assertEquals(profileID, user.getId());
	}

	public void testRemove() {
		log.debug("testing removeProfile");

		Long id = 11L;
		profile = new Profile();

		// set expected behavior on profileDao
		profileDao.expects(once()).method("remove").with(eq(id)).isVoid();

		manager.remove(id);
	}
	
    public void testGetProfileByUsername() throws Exception {
		profile = new Profile();
		User user = new User();
		profile.setUser(user);
		user.setUsername("testuser");
		user.setId(11L);
		profile.setId(11L);

		// set expected behavior on profileDao
		profileDao.expects(once()).method("getProfileByUsername").with(same("testuser")).will(
				returnValue(profile));

		Profile newProfile = manager.getProfileByUsername("testuser");
		assertNotNull(newProfile);
		User newUser = newProfile.getUser();
		assertNotNull(newUser);
		assertNotNull("testuser", newUser.getUsername());
		assertEquals(new Long(11L), newProfile.getId());
		assertEquals(new Long(11L), newUser.getId());
    }

}