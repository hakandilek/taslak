package org.xmdl.mesken.webapp.action;


import org.acegisecurity.AccessDeniedException;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.apache.struts2.ServletActionContext;
import org.xmdl.ida.lib.util.StringUtil;
import org.xmdl.ida.lib.web.util.RequestUtil;
import org.xmdl.mesken.MeskenConstants;
import org.xmdl.mesken.model.User;
import org.xmdl.mesken.service.UserExistsException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Action to allow new users to sign up.
 */
public class SignupAction extends BaseMeskenAction {
    private static final long serialVersionUID = 6558317334878272308L;
    private User user;
    private String cancel;

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Return an instance of the user - to display when validation errors occur
     * @return a populated user
     */
    public User getUser() {
        return user;
    }

    @Override
	public HttpSession getSession() {
		return super.getSession();
	}

    /**
     * When method=GET, "input" is returned. Otherwise, "success" is returned.
     * @return cancel, input or success
     */
    public String execute() {
        if (cancel != null) {
            return CANCEL;
        }
        if (ServletActionContext.getRequest().getMethod().equals("GET")) {
            return INPUT;
        }
        return SUCCESS;
    }

    /**
     * Returns "input"
     * @return "input" by default
     */
    public String doDefault() {
        return INPUT;
    }

    /**
     * Save the user, encrypting their passwords if necessary
     * @return success when good things happen
     * @throws Exception when bad things happen
     */
    public String save() throws Exception {
        Boolean encrypt = (Boolean) getConfiguration().get(MeskenConstants.ENCRYPT_PASSWORD);

        if (encrypt != null && encrypt) {
            String algorithm = (String) getConfiguration().get(MeskenConstants.ENC_ALGORITHM);

            if (algorithm == null) { // should only happen for test case
                if (log.isDebugEnabled()) {
                    log.debug("assuming testcase, setting algorithm to 'SHA'");
                }
                algorithm = "SHA";
            }

            user.setPassword(StringUtil.encodePassword(user.getPassword(), algorithm));
        }

        user.setEnabled(true);

        // Set the default user role on this new user
        user.addRole(roleManager.getRole(MeskenConstants.USER_ROLE));

        try {
            user = userManager.saveUser(user);
        } catch (AccessDeniedException ade) {
            // thrown by UserSecurityAdvice configured in aop:advisor userManagerSecurity 
            log.warn(ade.getMessage());
            getResponse().sendError(HttpServletResponse.SC_FORBIDDEN);
            return null; 
        } catch (UserExistsException e) {
            log.warn(e.getMessage());
            List<String> args = new ArrayList<String>();
            args.add(user.getUsername());
            args.add(user.getEmail());
            addActionError(getText("errors.existing.user", args));

            // redisplay the unencrypted passwords
            user.setPassword(user.getConfirmPassword());
            return INPUT;
        }

        saveMessage(getText("user.registered"));
        getSession().setAttribute(MeskenConstants.REGISTERED, Boolean.TRUE);

        // log user in automatically
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getConfirmPassword(), user.getAuthorities());
        auth.setDetails(user);
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Send an account information e-mail
        mailMessage.setSubject(getText("signup.email.subject"));
        sendUserMessage(user, getText("signup.email.message"), RequestUtil.getAppURL(getRequest()));

        return SUCCESS;
    }
}
