package org.xmdl.taslak.service;

import org.xmdl.ida.lib.service.GenericManager;
import org.xmdl.taslak.model.Profile;
import org.xmdl.taslak.model.search.ProfileSearch;

import java.util.Collection;

public interface ProfileManager extends GenericManager<Profile,Long>{
    Collection<Profile> search(ProfileSearch profileSearch);

	Profile getProfileByUsername(String username);
}
