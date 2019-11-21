package com.iPresence.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InputUtil {

	WebDriver webdriver;

	public InputUtil(WebDriver webdriver) {
		this.webdriver = webdriver;
	}

	public void InputElement(String element, String value) {

		WebElement xpathelement = webdriver.findElement(By.id(element));
		xpathelement.clear();
		InputElement(xpathelement, value);
	}

	public boolean InputElement(WebElement element, Object value) {
		try {
			element.sendKeys(String.valueOf(value));
		} catch (NoSuchElementException e) {
			System.out.println("No such element found, please provide the correct element");
			return false;
		}
		return true;
	}

	public Boolean InputElementValidation(List<String> locatorvalue, String inputtype, Object input) {
		boolean expectedresult = false;
		String xpathdetail = locatorvalue.get(0);

		StringBuilder sb = new StringBuilder(xpathdetail);
		String firtvalueremoval = sb.deleteCharAt(0).toString();
		StringBuilder sb1 = new StringBuilder(firtvalueremoval);
		String finalString = sb.deleteCharAt(sb1.length() - 1).toString();

		String[] parts = finalString.split(",");
		String xpathvalue = parts[0];
		String xpathtype = parts[1];
		switch (inputtype) {
		case "textbox":
			try {
				if (xpathtype.trim().equalsIgnoreCase("id")) {
					WebElement xpathelement = webdriver.findElement(By.id(xpathvalue));
					xpathelement.clear();
					expectedresult = InputElement(xpathelement, input);
				} else {
					WebElement xpathelement = webdriver.findElement(By.xpath(xpathvalue));
					xpathelement.clear();
					expectedresult = InputElement(xpathelement, input);
				}
			} catch (NoSuchElementException e) {
				System.out.println("No such element found, please provide the correct element");
				expectedresult = false;
			}
			break;
		}
		return expectedresult;
	}

	public boolean EnterInput(String jsonfilename, String jsonfieldname, String fieldname) {
		List<Map<String, Object>> fielddetails = JsonReader.readJsonMapArrayList(jsonfilename, jsonfieldname);
		Map<String, Boolean> finalresult = new HashMap<String, Boolean>();
		boolean finalvalue = false;
		for (Map<String, Object> fielddetail : fielddetails) {
			finalresult.put(fielddetail.get("fieldName").toString(), InputSpecificField(fielddetail, fieldname));
		}
		if (finalresult.values().contains(false)) {
			finalvalue = false;
		} else {
			finalvalue = true;
		}
		return finalvalue;
	}

	public boolean InputSpecificField(Map<String, Object> fielddetail, String fieldname) {
		boolean finalvalue = false;
		if(fielddetail.get("fieldName").toString().equals(fieldname)) {
		if ((fielddetail.get("fieldType").toString().equals("textbox"))
				|| (fielddetail.get("fieldType").toString().equals("Textbox"))) {
			if (fielddetail.get("fieldName").toString().equals("post")) {
				List<String> inputfieldId = new ArrayList<String>();
				String value = fielddetail.get("fieldLocator").toString();
				inputfieldId.add(value);
				String inputfieldType = fielddetail.get("fieldType").toString();
				String fieldValue = fielddetail.get("fieldValue") + RandomStringUtils.random(8, true, true);
				finalvalue = InputElementValidation(inputfieldId, inputfieldType, fieldValue);
			} else {
				List<String> inputfieldId = new ArrayList<String>();
				String value = fielddetail.get("fieldLocator").toString();
				inputfieldId.add(value);
				String inputfieldType = fielddetail.get("fieldType").toString();
				finalvalue = InputElementValidation(inputfieldId, inputfieldType, fielddetail.get("fieldValue"));
			}
		}
		}
		return finalvalue;
	}
}
