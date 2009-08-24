package org.xmdl.ida.lib.web.webdriver.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends AbstractPage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.ID_OR_NAME, using = "j_username")
	private WebElement username;

	@FindBy(how = How.ID_OR_NAME, using = "j_password")
	private WebElement password;

	@FindBy(how = How.ID_OR_NAME, using = "rememberMe")
	private WebElement rememberMe;

	public void login(String username, String password, boolean remember) {
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		if (remember)
			this.rememberMe.setSelected();
		System.out.println("driver:" + driver.getCurrentUrl());
		this.password.submit();
		System.out.println("driver:" + driver.getCurrentUrl());
	}

}
