package org.xmdl.mesken.webapp.action;

import org.subethamail.wiser.Wiser;
import org.xmdl.ida.lib.web.test.BaseActionTestCase;
import org.xmdl.mesken.webapp.action.PasswordHintAction;

public class PasswordHintActionTest extends BaseActionTestCase {
    private PasswordHintAction action;

    public void setPasswordHintAction(PasswordHintAction action) {
        this.action = action;
    }

    public void testExecute() throws Exception {
        // start SMTP Server
        Wiser wiser = new Wiser();
        wiser.setPort(2525);
        wiser.start();
        
        action.setUsername("user");
        assertEquals("success", action.execute());
        assertFalse(action.hasActionErrors());

        // verify an account information e-mail was sent
        wiser.stop();
        assertTrue(wiser.getMessages().size() == 1);

        // verify that success messages are in the request
        assertNotNull(action.getSession().getAttribute("messages"));
    }
}
