package com.iPresence.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TextUtil {
	WebDriver webdriver;

	public TextUtil(WebDriver webdriver) {
		this.webdriver = webdriver;
	}

	WebDriverWait wait;

	public boolean TextMessagevalidation(String jsonfilename, String jsonfieldname, String fieldname) {
		List<Map<String, Map<String, Object>>> fielddetails = JsonReader.readJsonMapofMap(jsonfilename, jsonfieldname);
		boolean finalvalue = false;
		for (Map<String, Map<String, Object>> fielddetail : fielddetails) {
			if (fielddetail.containsKey(fieldname)) {
				Map<String, Object> textdetail = fielddetail.get(fieldname);
				List<String> inputfieldId = new ArrayList<String>();
				String locator = textdetail.get("fieldLocator").toString();
				inputfieldId.add(locator);
				String errmsg = textdetail.get("fieldMsg").toString();
				finalvalue = TextMessageValidationn(inputfieldId, errmsg);
			}
		}
		return finalvalue;
	}

	public boolean TextMessageValidationn(List<String> errlocator, String errmsg) {
		boolean expectedresult = false;
		WebDriverWait wait = new WebDriverWait(webdriver, 120);
		String xpathdetail = errlocator.get(0);
		StringBuilder sb = new StringBuilder(xpathdetail);
		String firtvalueremoval = sb.deleteCharAt(0).toString();
		StringBuilder sb1 = new StringBuilder(firtvalueremoval);
		String finalString = sb.deleteCharAt(sb1.length() - 1).toString();
		String[] parts = finalString.split(",");
		String xpathvalue = parts[0];
		String xpathtype = parts[1];
		if (xpathtype.trim().contains("id") || xpathtype.trim().equalsIgnoreCase("xpath")) {
			if (!xpathvalue.contains("//")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(xpathvalue)));
				String returnmessage = webdriver.findElement(By.id(xpathvalue)).getText();
				expectedresult = returnmessage.equalsIgnoreCase(errmsg);
			} else {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathvalue)));
				String returnmessage = webdriver.findElement(By.xpath(xpathvalue)).getText();
				expectedresult = returnmessage.equalsIgnoreCase(errmsg);
			}
		} else {
			String returnmessage = webdriver.findElement(By.cssSelector(xpathvalue)).getText();
			expectedresult = returnmessage.equalsIgnoreCase(errmsg);
		}
		return expectedresult;
	}
	
	public boolean RequiredFieldValidation(String jsonfilename, String jsonfieldname, String fieldname) {
		List<Map<String, Map<String, Object>>> fielddetails = JsonReader.readJsonMapofMap(jsonfilename, jsonfieldname);
		boolean finalvalue = false;
		for (Map<String, Map<String, Object>> fielddetail : fielddetails) {
			if (fielddetail.containsKey(fieldname)) {
				Map<String, Object> textdetail = fielddetail.get(fieldname);
				List<String> inputfieldId = new ArrayList<String>();
				String locator = textdetail.get("fieldLocator").toString();
				inputfieldId.add(locator);
				finalvalue = RequiredFieldValidation(inputfieldId);
			}
		}
		return finalvalue;
	}

	public boolean RequiredFieldValidation(List<String> errlocator) {
		boolean expectedresult = false;
		WebDriverWait wait = new WebDriverWait(webdriver, 120);
		String xpathdetail = errlocator.get(0);
		StringBuilder sb = new StringBuilder(xpathdetail);
		String firtvalueremoval = sb.deleteCharAt(0).toString();
		StringBuilder sb1 = new StringBuilder(firtvalueremoval);
		String finalString = sb.deleteCharAt(sb1.length() - 1).toString();
		String[] parts = finalString.split(",");
		String xpathvalue = parts[0];
		String xpathtype = parts[1];
		if (xpathtype.trim().contains("id") || xpathtype.trim().equalsIgnoreCase("xpath")) {
			if (!xpathvalue.contains("//")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(xpathvalue)));
				WebElement element = webdriver.findElement(By.id(xpathvalue));
				JavascriptExecutor js = (JavascriptExecutor) webdriver;  
				expectedresult = (Boolean) js.executeScript("return arguments[0].required;",element);
			} else {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathvalue)));
				WebElement element = webdriver.findElement(By.xpath(xpathvalue));
				JavascriptExecutor js = (JavascriptExecutor) webdriver;  
				expectedresult = (Boolean) js.executeScript("return arguments[0].required;",element);
			}
		}
		return expectedresult;
	}
	

}