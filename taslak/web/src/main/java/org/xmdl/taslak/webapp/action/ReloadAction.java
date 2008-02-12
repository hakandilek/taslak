package org.xmdl.taslak.webapp.action;

import org.xmdl.ida.lib.web.action.BaseReloadAction;
import org.xmdl.taslak.webapp.listener.StartupListener;

/**
 * This class is used to reload the drop-downs initialized in the
 * StartupListener.

 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class ReloadAction extends BaseReloadAction {
    private static final long serialVersionUID = 295460450224891051L;

    
	@Override
	protected void setStartupContext() {
        StartupListener.setupContext(getSession().getServletContext());
	}
}
