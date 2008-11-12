package org.xmdl.taslak.webapp.listener;


import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import junit.framework.TestCase;

import org.springframework.mock.web.MockServletContext;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import org.xmdl.taslak.ApplicationConstants; 













/**
 * This class tests the StartupListener class to verify that variables are
 * placed into the servlet context.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @generated
 */
public class StartupListenerTest extends TestCase {

    /**
     * @generated
     */ 
    private MockServletContext sc = null;

    /**
     * @generated
     */ 
    private ServletContextListener listener = null;
    
    /**
     * @generated
     */ 
    private ContextLoaderListener springListener = null;

    /**
     * @generated
     */ 
    protected void setUp() throws Exception {
        super.setUp();
        sc = new MockServletContext("");
        sc.addInitParameter("daoType", "hibernate");
        sc.addInitParameter(ApplicationConstants.CSS_THEME, "simplicity");
        
        // initialize Spring
        sc.addInitParameter(ContextLoader.CONFIG_LOCATION_PARAM,
                "classpath*:/**/applicationContext*.xml, " +
                "classpath*:**/applicationContext*.xml, " +
                "/WEB-INF/applicationContext*.xml"
                );

        springListener = new ContextLoaderListener();
        springListener.contextInitialized(new ServletContextEvent(sc));
        listener = new StartupListener();
    }

    /**
     * @generated
     */ 
    protected void tearDown() throws Exception {
        super.tearDown();
        springListener = null;
        listener = null;
        sc = null;
    }

    /**
     * @generated
     */ 
    public void testContextInitialized() {
        listener.contextInitialized(new ServletContextEvent(sc));

        assertTrue(sc.getAttribute(ApplicationConstants.CONFIG) != null);
        Map<?, ?> config = (Map<?, ?>) sc.getAttribute(ApplicationConstants.CONFIG);
        assertEquals(config.get(ApplicationConstants.CSS_THEME), "simplicity");
        
        assertTrue(sc.getAttribute(WebApplicationContext
                .ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE) != null);
        assertTrue(sc.getAttribute(ApplicationConstants.AVAILABLE_ROLES) != null);
    }
}
