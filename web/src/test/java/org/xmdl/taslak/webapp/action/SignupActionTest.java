package org.xmdl.taslak.webapp.action;

import org.xmdl.ida.lib.web.test.BaseActionTestCase;
import org.xmdl.taslak.TaslakConstants;
import org.xmdl.taslak.model.Address;
import org.xmdl.taslak.model.User;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import org.subethamail.wiser.Wiser;
import org.apache.struts2.ServletActionContext;
import org.acegisecurity.context.SecurityContextHolder;

public class SignupActionTest extends BaseActionTestCase {
    private SignupAction action;

    public void setSignupAction(SignupAction action) {
        this.action = action;
    }
    
    public void testDisplayForm() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest(null, "GET", "/signup.html");
        ServletActionContext.setRequest(request);
        assertEquals("input", action.execute());
    }  
    
    public void testExecute() throws Exception {
        User user = new User();
        user.setUsername("self-registered");
        user.setPassword("Password1");
        user.setConfirmPassword("Password1");
        user.setFirstName("First");
        user.setLastName("Last");
        
        Address address = new Address();
        address.setCity("Denver");
        address.setProvince("CO");
        address.setCountry("USA");
        address.setPostalCode("80210");        
        user.setAddress(address);
        
        user.setEmail("self-registered@raibledesigns.com");
        user.setWebsite("http://raibledesigns.com");
        user.setPasswordHint("Password is one with you.");
        action.setUser(user);

        // set mock response so setting cookies doesn't fail
        ServletActionContext.setResponse(new MockHttpServletResponse());
        
        // start SMTP Server
        Wiser wiser = new Wiser();
        wiser.setPort(2525);
        wiser.start();
        
        assertEquals("success", action.save());
        assertFalse(action.hasActionErrors());
        
        // verify an account information e-mail was sent
        wiser.stop();
        assertTrue(wiser.getMessages().size() == 1);

        // verify that success messages are in the session
        assertNotNull(action.getSession().getAttribute(TaslakConstants.REGISTERED));

        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
