package org.xmdl.ida.lib.web.webdriver.utils;

import java.io.File;
import java.io.FileWriter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class WebDriverLogger extends AbstractWebDriverEventListener {

	public WebDriverLogger(EventFiringWebDriver webDriver) {
		webDriver.register(this);
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		String filename = filter("target/webdriver/" + System.currentTimeMillis() + "_" + url);
		File file = new File(filename);
		File dir = file.getParentFile();
        if (dir != null && !dir.exists() && !dir.mkdirs()) {
            throw new WebDriverException("Could not create directory " + dir.getAbsolutePath());
        }
        
        String pageSource = driver.getPageSource();
        try {
			FileWriter w = new FileWriter(file);
			w.write(pageSource);
			w.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String filter(String string) {
		string = string.replace("https://", "");
		string = string.replace("http://", "");
		string = string.replace(":", "");
		return string;
	}

}
