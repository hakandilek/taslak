package org.xmdl.taslak.webapp.action;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.dao.DataIntegrityViolationException;
import org.xmdl.ida.lib.web.action.BaseAction;
import org.xmdl.mesken.MeskenConstants;
import org.xmdl.mesken.model.Role;
import org.xmdl.mesken.model.User;
import org.xmdl.taslak.model.Profile;
import org.xmdl.taslak.model.search.ProfileSearch;
import org.xmdl.taslak.service.ProfileManager;

import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
public class ProfileAction extends BaseAction implements Preparable {

    private ProfileManager profileManager;
    private Collection<Profile> profiles;
    private Profile profile;
    private Long id;

    private Long idToCopy;
    private Integer[] profileTypeIds;

    private ProfileSearch profileSearch = new ProfileSearch();

    public void setProfileManager(ProfileManager profileManager) {
        this.profileManager = profileManager;
    }

    public Collection<Profile> getProfiles() {
        return profiles;
    }

    public void prepare() {
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            if (id != null) {
                profile = profileManager.get((long) id);
            }else{
                profile = new Profile();
            }
        }

    }

    @SkipValidation
    public String list() {
        if (log.isDebugEnabled()) log.debug("list() <-");

        profiles = profileManager.search(profileSearch);

        if (log.isDebugEnabled()) log.debug("listing items:" + profiles.size());
        if (log.isDebugEnabled()) log.debug("list() ->");
        return SUCCESS;
    }

    public void setId(Long id) {
        this. id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @SkipValidation
    public String delete() {
        if (log.isDebugEnabled()) log.debug("delete() <-");

        profileManager.remove(profile.getId());
        saveMessage(getText("profile.deleted"));

        if (log.isDebugEnabled()) log.debug("deleting profile: " + profile);
        if (log.isDebugEnabled()) log.debug("delete() ->");

        return SUCCESS;
    }

    public String copy() {
        if (log.isDebugEnabled()) log.debug("copy() <-");

        if (idToCopy != null) {
            profile = profileManager.get(idToCopy);
        }

        if (log.isDebugEnabled()) log.debug("copying profile: " + profile);

        if (log.isDebugEnabled()) log.debug("copy() ->");

        profile.setId(null);
        return SUCCESS;
    }

    public String edit() throws IOException {
        if (log.isDebugEnabled()) log.debug("edit() <-");

        HttpServletRequest request = getRequest();
        boolean editProfile = (request.getRequestURI().indexOf("editProfile") > -1);

        // if URL is "editProfile" - make sure it's the current user
        if (editProfile) {
            // reject if id passed in or "list" parameter passed in
            // someone that is trying this probably knows the AppFuse code
            // but it's a legitimate bug, so I'll fix it. ;-)
            if ((request.getParameter("id") != null) || (request.getParameter("from") != null)) {
                ServletActionContext.getResponse().sendError(HttpServletResponse.SC_FORBIDDEN);
                log.warn("User '" + request.getRemoteUser() + "' is trying to edit user '" 
                         + request.getParameter("id") + "'");

                return null;
            }
        }

		if (id != null) {
            profile = profileManager.get(id);
        } else if (editProfile) {
            String username = request.getRemoteUser();
			profile = profileManager.getProfileByUsername(username);
        } else {
            profile = new Profile();
            User user  = new User();
            profile.setUser(user);
            user.addRole(new Role(MeskenConstants.USER_ROLE));
        }
		
        if (log.isDebugEnabled()) log.debug("editing profile: " + profile);
        if (log.isDebugEnabled()) log.debug("edit() ->");

        return SUCCESS;
    }

    @SkipValidation
    public String deleteMass() throws Exception {
        if (log.isDebugEnabled()) log.debug("deleteMass() <-");

        boolean cannotDeleted = false;
        boolean anyDeleted = false;
        if (getDeleteId() != null) {
            for (String idStr : getDeleteId()) {
                try {
                    profileManager.remove(new Long(idStr));
                    anyDeleted = true;

                    if (log.isDebugEnabled()) log.debug("deleting profile with id: " + idStr);
                } catch (DataIntegrityViolationException e) {
                    e.printStackTrace();
                    cannotDeleted = true;

                    if (log.isDebugEnabled()) log.debug("could not delete profile with id: " + idStr);
                }
            }
        }
        if (cannotDeleted) saveMessage(getText("Profile.cannotBeDeleted"));
        if (anyDeleted) saveMessage(getText("Profile.deleted"));


        profiles = profileManager.search(profileSearch);

        if (log.isDebugEnabled()) log.debug("deleteMass() ->");

        return SUCCESS;
    }


    public String save() throws Exception {
        if (log.isDebugEnabled()) log.debug("save() <-");

        if (cancel != null) {
            if (log.isDebugEnabled()) log.debug("save() ->");
            return "cancel";
        }

        if (delete != null) {
            if (log.isDebugEnabled()) log.debug("save() ->");
            return delete();
        }

        boolean isNew = (profile.getId() == null);

        profileManager.save(profile);

        String key = (isNew) ? "profile.added" : "profile.updated";
        saveMessage(getText(key));

        String logMessage = (isNew) ? "adding profile: " + profile : "updating profile: " + profile;
        if (log.isDebugEnabled()) log.debug(logMessage);
        if (log.isDebugEnabled()) log.debug("save() ->");

        if (!isNew) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    public ProfileSearch getProfileSearch() {
        return profileSearch;
    }

    public void setProfileSearch(ProfileSearch profileSearch) {
        this.profileSearch = profileSearch;
    }

    public Long getIdToCopy() {
        return idToCopy;
    }

    public void setIdToCopy(Long idToCopy) {
        this.idToCopy = idToCopy;
    }

    public ProfileManager getProfileManager() {
        return profileManager;
    }

    public Integer[] getProfileTypeIds() {
        return profileTypeIds;
    }

    public void setProfileTypeIds(Integer[] profileTypeIds) {
        this.profileTypeIds = profileTypeIds;
    }
}