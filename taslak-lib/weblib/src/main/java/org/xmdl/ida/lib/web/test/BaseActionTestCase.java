package org.xmdl.ida.lib.web.test;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.struts2.ServletActionContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import org.xmdl.ida.lib.BaseConstants;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.config.Configuration;
import com.opensymphony.xwork2.config.ConfigurationManager;
import com.opensymphony.xwork2.config.providers.XWorkConfigurationProvider;
import com.opensymphony.xwork2.inject.Container;
import com.opensymphony.xwork2.util.LocalizedTextUtil;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.ValueStackFactory;

/**
 * Base class for running Struts 2 Action tests.
 * @author mraible
 */
public abstract class BaseActionTestCase extends AbstractTransactionalDataSourceSpringContextTests {
    protected transient final Log log = logger;

    protected String[] getConfigLocations() {
        super.setAutowireMode(AUTOWIRE_BY_NAME);
        return new String[] {
        		"classpath*:/**/applicationContext*.xml",
        		"classpath*:**/applicationContext*.xml",
                "/WEB-INF/applicationContext*.xml"
            };
    }

    @Override
    protected void onSetUpBeforeTransaction() throws Exception {
        LocalizedTextUtil.addDefaultResourceBundle(BaseConstants.BUNDLE_KEY); 
        
        /**
         * A quick fix for ActionContext.getContext() returning null :
         * {@link http
         * ://cwiki.apache.org/S2WIKI/troubleshooting-guide-migrating-
         * from-struts
         * -20x-to-21x.html#TroubleshootingguidemigratingfromStruts2.0
         * .xto2.1.x-UpdateUnitTests }
         **/
        ConfigurationManager configurationManager = new ConfigurationManager();
        XWorkConfigurationProvider confProvider = new XWorkConfigurationProvider();
        configurationManager.addContainerProvider(confProvider);
        Configuration config = configurationManager.getConfiguration();
        Container container = config.getContainer();
        final ValueStackFactory factory = container.getInstance(ValueStackFactory.class);
        ValueStack stack = factory.createValueStack();
        stack.getContext().put(ActionContext.CONTAINER, container);
        ActionContext.setContext(new ActionContext(stack.getContext()));
        assertNotNull(ActionContext.getContext());
        
        ActionContext.getContext().setSession(new HashMap<String, Object>());
        
        // change the port on the mailSender so it doesn't conflict with an 
        // existing SMTP server on localhost
        JavaMailSenderImpl mailSender = (JavaMailSenderImpl) applicationContext.getBean("mailSender");
        mailSender.setPort(2525);
        mailSender.setHost("localhost");

        // populate the request so getRequest().getSession() doesn't fail in BaseAction.java
        ServletActionContext.setRequest(new MockHttpServletRequest());
    }

    @Override
    protected void onTearDownAfterTransaction() throws Exception {
        ActionContext.getContext().setSession(null);   
    }
}
