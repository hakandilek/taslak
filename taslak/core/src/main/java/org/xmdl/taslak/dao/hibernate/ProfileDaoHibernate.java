package org.xmdl.taslak.dao.hibernate;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.xmdl.ida.lib.dao.hibernate.GenericDaoHibernate;
import org.xmdl.mesken.model.User;
import org.xmdl.taslak.dao.ProfileDao;
import org.xmdl.taslak.model.Profile;
import org.xmdl.taslak.model.search.ProfileSearch;

public class ProfileDaoHibernate extends GenericDaoHibernate<Profile, Long> implements ProfileDao {

    public ProfileDaoHibernate() {
        super(Profile.class);
    }

    @SuppressWarnings("unchecked")
	public Collection<Profile> search(ProfileSearch profileSearch) {
    	if (log.isDebugEnabled()) {
    		log.debug("search(ProfileSearch profileSearch) <-");
        	log.debug("profileSearch: " + profileSearch);
    	}
    	
		Collection<Profile> list = null;
		if (profileSearch == null) {
			list = new ArrayList<Profile>();
		} else {
			User user = profileSearch.getUser();
			String privatePhone = profileSearch.getPrivatePhone();

	    	if (log.isDebugEnabled()) {
	        	log.debug("user       : " + user);
	        	log.debug("privatePhone    : " + privatePhone);
	    	}

	    	Session session = getSession();
			Criteria criteria = session.createCriteria(Profile.class);

	        if (privatePhone != null && !privatePhone.equals(""))
	            criteria.add(Restrictions.like("privatePhone", "%" + privatePhone + "%"));
	        if (user != null){
	        	criteria.add(Restrictions.idEq(user.getId()));
	        }

	        list = criteria.list();
		}

		if (log.isDebugEnabled())
			log.debug("search(ProfileSearch profileSearch) ->");
    	return list;
    }

}
