package com.iPresence;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.iPresence.utils.ClickUtil;
import com.iPresence.utils.InputUtil;
import com.iPresence.utils.TextUtil;
import com.iPresence.utils.URLUtil;

public class Booking {
	
	WebDriver webdriver;
	InputUtil input;
	URLUtil urlutil;
	TextUtil textutil;
	String jsonfilename = "booking";
	ClickUtil clickutil;

	public Booking(WebDriver webdriver) {
		this.webdriver = webdriver;
		input = PageFactory.initElements(webdriver, InputUtil.class);
		clickutil = PageFactory.initElements(webdriver, ClickUtil.class);
		textutil = PageFactory.initElements(webdriver, TextUtil.class);
		urlutil = PageFactory.initElements(webdriver, URLUtil.class);
	}
	
	public boolean EnterElement(String fieldname, String buttonname)
	{
		input.EnterInput(jsonfilename, "booking_field", fieldname);
		WebElement element = webdriver.findElement(By.id("completeBooking"));
		JavascriptExecutor js = (JavascriptExecutor) webdriver;
		js.executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", element);
		return clickutil.ClickButtonWithScroll(jsonfilename, "booking_field", buttonname);
	}
	
	public boolean ClickButtonWithScroll(String buttonname)
	{
		WebElement element = webdriver.findElement(By.id("completeBooking"));
		JavascriptExecutor js = (JavascriptExecutor) webdriver;
		js.executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", element);
		return clickutil.ClickButtonWithScroll(jsonfilename, "booking_field", buttonname);
	}
	
	public boolean ClickButton(String buttonname)
	{
		return clickutil.ClickButton(jsonfilename, "button_field", buttonname);
	}
	
	public boolean ErrorMsgValidation(String element)
	{
		return textutil.RequiredFieldValidation(jsonfilename, "validation", element);
	}
	
	public boolean ValidateLinks()
	{
		return urlutil.VerifyLinkValidation();
	}
	
	public void waitforpagetoload() {
		webdriver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
	}
}
