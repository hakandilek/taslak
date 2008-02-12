package org.xmdl.mesken.webapp.listener;

import junit.framework.TestCase;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.xmdl.mesken.MeskenConstants;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Map;


/**
 * This class tests the StartupListener class to verify that variables are
 * placed into the servlet context.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class StartupListenerTest extends TestCase {
    private MockServletContext sc = null;
    private ServletContextListener listener = null;
    private ContextLoaderListener springListener = null;

    protected void setUp() throws Exception {
        super.setUp();
        sc = new MockServletContext("");
        sc.addInitParameter("daoType", "hibernate");
        sc.addInitParameter(MeskenConstants.CSS_THEME, "puzzlewithstyle");
        
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

    protected void tearDown() throws Exception {
        super.tearDown();
        springListener = null;
        listener = null;
        sc = null;
    }

    public void testContextInitialized() {
        listener.contextInitialized(new ServletContextEvent(sc));

        assertTrue(sc.getAttribute(MeskenConstants.CONFIG) != null);
        Map<?, ?> config = (Map<?, ?>) sc.getAttribute(MeskenConstants.CONFIG);
        assertEquals(config.get(MeskenConstants.CSS_THEME), "puzzlewithstyle");
        
        assertTrue(sc.getAttribute(WebApplicationContext
                .ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE) != null);
        assertTrue(sc.getAttribute(MeskenConstants.AVAILABLE_ROLES) != null);
    }
}
