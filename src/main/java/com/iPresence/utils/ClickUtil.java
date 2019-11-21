package com.iPresence.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClickUtil {
	
	WebDriver webdriver;
	
	public ClickUtil(WebDriver webdriver) {
		this.webdriver = webdriver;
	}

	WebDriverWait wait;
	
	public boolean ClickButton(String jsonfilename, String jsonfieldname, String buttonname) {
		List<Map<String, Object>> fielddetails = JsonReader.readJsonMapArrayList(jsonfilename,
				jsonfieldname);
		boolean finalvalue = false;
		for (Map<String, Object> fielddetail : fielddetails) {
			if (fielddetail.get("fieldName").equals(buttonname)) {
				List<String> inputfieldId = new ArrayList<String>();
				String value = fielddetail.get("fieldLocator").toString();
				inputfieldId.add(value);
				List<String> expectedelement = new ArrayList<String>();
				String value1 = fielddetail.get("expectedElement").toString();
				expectedelement.add(value1);
				finalvalue = ClickElementAndExpectElementValidation(inputfieldId, expectedelement);
			}
		}
		return finalvalue;
	}
	
	public Boolean ClickElementAndExpectElementValidation(List<String> locatorvalue, List<String> expectedelement) {
		boolean expectedresult = false;
		WebDriverWait wait = new WebDriverWait(webdriver, 15);
		String xpathdetail = locatorvalue.get(0);
		StringBuilder sb = new StringBuilder(xpathdetail);
		String firtvalueremoval = sb.deleteCharAt(0).toString();
		StringBuilder sb1 = new StringBuilder(firtvalueremoval);
		String finalString = sb.deleteCharAt(sb1.length() - 1).toString();
		String[] parts = finalString.split(",");
		String xpathvalue = parts[0];
		String xpathtype = parts[1];
		if (xpathtype.trim().contains("id") || xpathtype.trim().equalsIgnoreCase("xpath")) {
			try {
				if (!xpathvalue.contains("//")) {
					WebElement xpathelement = webdriver.findElement(By.id(xpathvalue));
					ClickElement(xpathelement);
					waitforpagetoload();
					expectedresult = checkElementVisible(expectedelement);
					if (expectedresult = false) {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathvalue)));
						ClickElement(xpathelement);
						waitforpagetoload();
						expectedresult = checkElementVisible(expectedelement);
					} else
						expectedresult = true;
				} else {
					WebElement idelement = webdriver.findElement(By.xpath(xpathvalue));
					ClickElement(idelement);
					waitforpagetoload();
					expectedresult = checkElementVisible(expectedelement);
					if (!expectedresult) {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathvalue)));
						ClickElement(idelement);
						waitforpagetoload();
						expectedresult = checkElementVisible(expectedelement);
					} else {
						expectedresult = true;
					}
				}
			} catch (NoSuchElementException e) {
				System.out.println("No such element found : " + xpathvalue + ", please provide the correct one : "
						+ e.getMessage());
				expectedresult = false;
			}
		}
		return expectedresult;
	}
	
	public boolean ClickElement(WebElement element) {

		try {
			if (element.isDisplayed()) {
				element.click();
				waitforpagetoload();
			}
			else
			{
				webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				element.click();
				waitforpagetoload();
			}
		} catch (NoSuchElementException e) {
			System.out.println(element + "No such element found, please provide the correct element");
			return false;
		}
		return true;
	}
	
	public void waitforpagetoload() {
		webdriver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
	}
	
	public boolean checkElementVisible(List<String> locatorvalue) {
		boolean expectedresult = false;
		String xpathdetail = locatorvalue.get(0);
		WebDriverWait wait = new WebDriverWait(webdriver, 120);
		StringBuilder sb = new StringBuilder(xpathdetail);
		String firtvalueremoval = sb.deleteCharAt(0).toString();
		StringBuilder sb1 = new StringBuilder(firtvalueremoval);
		String finalString = sb.deleteCharAt(sb1.length() - 1).toString();

		String[] parts = finalString.split(",");
		String xpathvalue = parts[0];
		String xpathtype = parts[1];
		try {
			if (xpathtype.trim().equalsIgnoreCase("id")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(xpathvalue)));
				WebElement xpathelement = webdriver.findElement(By.id(xpathvalue));
				expectedresult = ElementVisibleCheck(xpathelement);
			} else {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathvalue)));
				WebElement xpathelement = webdriver.findElement(By.xpath(xpathvalue));
				expectedresult = ElementVisibleCheck(xpathelement);
			}
		} catch (NoSuchElementException e) {
			System.out.println("No such element found, please provide the correct element");
			expectedresult = false;
		}
		return expectedresult;
	}

	public boolean ElementVisibleCheck(WebElement element) {

		boolean elementstatus = false;
		try {
			if(element.isDisplayed()) {
				elementstatus = true;
			}
		} catch (NoSuchElementException e) {
			System.out.println(element + "No such element found, please provide the correct element");
			return false;
		}
		return elementstatus;
	}
	
	public boolean ClickElementWithScroll(WebElement element) {

		try {
			if (element.isDisplayed()) {
				JavascriptExecutor jse = (JavascriptExecutor) webdriver;

				jse.executeScript("arguments[0].scrollIntoView()", element);

				element.click();
				waitforpagetoload();
			}
			else
			{
				webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				JavascriptExecutor jse = (JavascriptExecutor) webdriver;

				jse.executeScript("arguments[0].scrollIntoView()", element);

				element.click();
				waitforpagetoload();
			}
		} catch (NoSuchElementException e) {
			System.out.println(element + "No such element found, please provide the correct element");
			return false;
		}
		return true;
	}
	
	public boolean ClickButtonWithScroll(String jsonfilename, String jsonfieldname, String buttonname) {
		List<Map<String, Object>> fielddetails = JsonReader.readJsonMapArrayList(jsonfilename,
				jsonfieldname);
		boolean finalvalue = false;
		for (Map<String, Object> fielddetail : fielddetails) {
			if (fielddetail.get("fieldName").equals(buttonname)) {
				List<String> inputfieldId = new ArrayList<String>();
				String value = fielddetail.get("fieldLocator").toString();
				inputfieldId.add(value);
				List<String> expectedelement = new ArrayList<String>();
				String value1 = fielddetail.get("expectedElement").toString();
				expectedelement.add(value1);
				finalvalue = ClickElementWithScrollAndExpectElementValidation(inputfieldId, expectedelement);
			}
		}
		return finalvalue;
	}
	
	public Boolean ClickElementWithScrollAndExpectElementValidation(List<String> locatorvalue, List<String> expectedelement) {
		boolean expectedresult = false;
		WebDriverWait wait = new WebDriverWait(webdriver, 15);
		String xpathdetail = locatorvalue.get(0);
		StringBuilder sb = new StringBuilder(xpathdetail);
		String firtvalueremoval = sb.deleteCharAt(0).toString();
		StringBuilder sb1 = new StringBuilder(firtvalueremoval);
		String finalString = sb.deleteCharAt(sb1.length() - 1).toString();
		String[] parts = finalString.split(",");
		String xpathvalue = parts[0];
		String xpathtype = parts[1];
		if (xpathtype.trim().contains("id") || xpathtype.trim().equalsIgnoreCase("xpath")) {
			try {
				if (!xpathvalue.contains("//")) {
					WebElement xpathelement = webdriver.findElement(By.id(xpathvalue));
					ClickElementWithScroll(xpathelement);
					expectedresult = checkElementVisible(expectedelement);
					if (expectedresult = false) {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathvalue)));
						ClickElementWithScroll(xpathelement);
						expectedresult = checkElementVisible(expectedelement);
					} else
						expectedresult = true;
				} else {
					WebElement idelement = webdriver.findElement(By.xpath(xpathvalue));
					ClickElementWithScroll(idelement);
					expectedresult = checkElementVisible(expectedelement);
					if (!expectedresult) {
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathvalue)));
						ClickElementWithScroll(idelement);
						expectedresult = checkElementVisible(expectedelement);
					} else {
						expectedresult = true;
					}
				}
			} catch (NoSuchElementException e) {
				System.out.println("No such element found : " + xpathvalue + ", please provide the correct one : "
						+ e.getMessage());
				expectedresult = false;
			}
		}
		return expectedresult;
	}
	
}
