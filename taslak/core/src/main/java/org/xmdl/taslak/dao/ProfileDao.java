package org.xmdl.taslak.dao;

import java.util.Collection;

import org.xmdl.ida.lib.dao.GenericDao;
import org.xmdl.taslak.model.Profile;
import org.xmdl.taslak.model.search.ProfileSearch;

public interface ProfileDao extends GenericDao<Profile, Long> {
    Collection<Profile> search(ProfileSearch profileSearch);

	Profile getProfileByUsername(String username);
}
