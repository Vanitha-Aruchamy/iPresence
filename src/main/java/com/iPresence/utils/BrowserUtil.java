package com.iPresence.utils;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowserUtil {
	
	WebDriver webdriver;

	public BrowserUtil(WebDriver webdriver) {
		this.webdriver = webdriver;
	}

	public void SwitchtoNewWindow(WebDriver webdriver) {
		String parentWindow = webdriver.getWindowHandle();
		Set<String> handles = webdriver.getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) {
				webdriver.switchTo().window(windowHandle);
			}
		}
	}

public void SwitchtoParentWindow(WebDriver webdriver) {
		String parentWindow = webdriver.getWindowHandle();
		Set<String> handles = webdriver.getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) {
				webdriver.switchTo().window(windowHandle);
				webdriver.close();// Closing child window
				webdriver.switchTo().window(parentWindow);// Switching to parent
															// window
			}
		}
	}

	public boolean clickandexpectElementInParentWindow(WebElement element, String expected)
	{
		boolean expectedresult = false;
		if(ClickElement(element)){
		SwitchtoNewWindow(webdriver);
				WebElement idelement = webdriver.findElement(By.xpath(expected));
				expectedresult = idelement.isDisplayed();
				if (expectedresult == false) {
					idelement.isDisplayed();	
				}
		}
		return expectedresult;
		
	}
	

public boolean ClickElement(WebElement element) {

		try {
			if (element.isDisplayed()) {
				element.click();
			}
			else
			{
				webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				element.click();
			}
		} catch (NoSuchElementException e) {
			System.out.println("No such element found, please provide the correct element");
			return false;
		}
		return true;
	}

}