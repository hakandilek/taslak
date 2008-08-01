package org.xmdl.taslak.service.impl;

import java.util.Collection;

import org.xmdl.ida.lib.service.impl.GenericManagerImpl;
import org.xmdl.mesken.model.User;
import org.xmdl.taslak.dao.ProfileDao;
import org.xmdl.taslak.model.Profile;
import org.xmdl.taslak.model.search.ProfileSearch;
import org.xmdl.taslak.service.ProfileManager;

public class ProfileManagerImpl extends GenericManagerImpl<Profile, Long>
		implements ProfileManager {

	ProfileDao profileDao;


	public ProfileManagerImpl(ProfileDao profileDao) {
		super(profileDao);
		this.profileDao = profileDao;
	}

	public Collection<Profile> search(ProfileSearch profileSearch) {
		return profileDao.search(profileSearch);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.xmdl.ida.lib.service.impl.GenericManagerImpl#save(java.lang.Object)
	 */
	@Override
	public Profile save(Profile profile) {
		if (log.isDebugEnabled())
			log.debug("profile = " + profile);
		
		// check && create user for that profile
		User user = profile.getUser();
		if (log.isDebugEnabled())
			log.debug("user = " + user);
		if (user == null) {
			user = new User();
			profile.setUser(user);
		}
		
		Profile savedProfile = super.save(profile);
		if (log.isDebugEnabled())
			log.debug("savedProfile = " + savedProfile);
		if (log.isDebugEnabled())
			log.debug("savedProfile.user = " + savedProfile.getUser());
		if (log.isDebugEnabled())
			log.debug("savedProfile.user.id = " + savedProfile.getUser().getId());
		return savedProfile;
	}

}
