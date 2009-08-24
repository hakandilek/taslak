package org.xmdl.ida.lib.web.webdriver.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.xmdl.ida.lib.web.test.BaseActionTestCase;
import org.xmdl.ida.lib.web.webdriver.page.LoginPage;
import org.xmdl.ida.lib.web.webdriver.page.LogoutPage;

/**
 * Base class for selenium tests.
 * 
 * @author Hakan Dilek
 */
public class BaseSeleniumTestCase extends BaseActionTestCase {

	protected static final String TEST_USERNAME = "admin";
	protected static final String TEST_PASSWORD = "admin";

	protected WebDriver webDriver;

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	protected void login() {
		LoginPage loginPage = get("/", LoginPage.class);
		loginPage.login(TEST_USERNAME, TEST_PASSWORD, false);
	}

	protected LogoutPage logout() {
		return get("/logout.jsp", LogoutPage.class);
	}
	
	protected <T> T get(String uri, Class<T> pageClass) {
		if (!uri.startsWith("/"))
			uri = "/" + uri;
		webDriver.get("http://localhost:8081" + uri);
		T page = PageFactory.initElements(webDriver, pageClass);
		return page;
	}

	@Override
	protected void onSetUpBeforeTransaction() throws Exception {
		super.onSetUpBeforeTransaction();
		logout();
	}

	@Override
	protected void onTearDownAfterTransaction() throws Exception {
		super.onTearDownAfterTransaction();
		logout();
	}

	
}
