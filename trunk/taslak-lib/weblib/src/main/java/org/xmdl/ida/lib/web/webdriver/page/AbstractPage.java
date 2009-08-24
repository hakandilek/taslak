package org.xmdl.ida.lib.web.webdriver.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * The abstract page class
 * 
 * @author Hakan Dilek
 */
public abstract class AbstractPage {

	protected final WebDriver driver;

	@FindBy(how = How.ID_OR_NAME, using = "successMessages")
	private WebElement successMessages;

	@FindBy(how = How.ID_OR_NAME, using = "errorMessages")
	private WebElement errorMessages;
	

	public AbstractPage(WebDriver driver) {
		this.driver = driver;
	}

	protected <T> T page(Class<T> pageClass) {
		return PageFactory.initElements(driver, pageClass);
	}

	public String getSuccessMessages() {
		try {
			return successMessages.getText();
		} catch (Exception e) {
			return null;
		}
	}

	public String getErrorMessages() {
		try {
			return errorMessages.getText();
		} catch (Exception e) {
			return null;
		}
	}


}
