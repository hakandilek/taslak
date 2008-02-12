package org.xmdl.mesken.webapp.action;

import java.util.HashMap;
import java.util.Map;

import org.xmdl.ida.lib.web.action.BaseAction;
import org.xmdl.mesken.model.User;
import org.xmdl.mesken.service.RoleManager;
import org.xmdl.mesken.service.UserManager;

public class BaseMeskenAction extends BaseAction {
	
	/**
	 * serialization ID
	 */
	private static final long serialVersionUID = -1623323347918364072L;

	/**
	 * The UserManager
	 */
	protected UserManager userManager;

	/**
	 * The RoleManager
	 */
	protected RoleManager roleManager;

	/**
	 * Convenience method to send e-mail to users
	 * 
	 * @param user
	 *            the user to send to
	 * @param msg
	 *            the message to send
	 * @param url
	 *            the URL to the application (or where ever you'd like to send
	 *            them)
	 */
	protected void sendUserMessage(User user, String msg, String url) {
		if (log.isDebugEnabled()) {
			log.debug("sending e-mail to user [" + user.getEmail() + "]...");
		}

		mailMessage.setTo(user.getFullName() + "<" + user.getEmail() + ">");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", user);
		// TODO: figure out how to get bundle specified in struts.xml
		// model.put("bundle", getTexts());
		model.put("message", msg);
		model.put("applicationURL", url);
		mailEngine.sendMessage(mailMessage, templateName, model);
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}

}
