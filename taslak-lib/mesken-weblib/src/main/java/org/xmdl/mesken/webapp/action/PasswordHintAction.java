package org.xmdl.mesken.webapp.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.xmdl.ida.lib.web.util.RequestUtil;
import org.xmdl.mesken.model.User;

/**
 * Action class to send password hints to registered users.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class PasswordHintAction extends BaseMeskenAction {
    private static final long serialVersionUID = -4037514607101222025L;
    private String username;

    /**
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
	public HttpSession getSession() {
		return super.getSession();
	}

	/**
     * Execute sending the password hint via e-mail.
     *
     * @return success if username works, input if not
     */
    public String execute() {
        List<Object> args = new ArrayList<Object>();

        // ensure that the username has been sent
        if (username == null) {
            log.warn("Username not specified, notifying user that it's a required field.");

            args.add(getText("user.username"));
            addActionError(getText("errors.requiredField", args));
            return INPUT;
        }

        if (log.isDebugEnabled()) {
            log.debug("Processing Password Hint...");
        }

        // look up the user's information
        try {
            User user = userManager.getUserByUsername(username);
            String hint = user.getPasswordHint();

            if (hint == null || hint.trim().equals("")) {
                log.warn("User '" + username + "' found, but no password hint exists.");
                addActionError(getText("login.passwordHint.missing"));
                return INPUT;
            }

            StringBuffer msg = new StringBuffer();
            msg.append("Your password hint is: ").append(hint);
            msg.append("\n\nLogin at: ").append(RequestUtil.getAppURL(getRequest()));

            mailMessage.setTo(user.getEmail());
            String subject = '[' + getText("webapp.name") + "] " + getText("user.passwordHint");
            mailMessage.setSubject(subject);
            mailMessage.setText(msg.toString());
            mailEngine.send(mailMessage);
            
            args.add(username);
            args.add(user.getEmail());
            
            saveMessage(getText("login.passwordHint.sent", args));
            
        } catch (Exception e) {
            log.warn("Username '" + username + "' not found in database.");
            args.add(username);
            addActionError(getText("login.passwordHint.error", args));
            return INPUT;
        }

        return SUCCESS;
    }
}
