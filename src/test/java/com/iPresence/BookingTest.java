package com.iPresence;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.iPresence.BaseTest;
import com.iPresence.Booking;

public class BookingTest extends BaseTest{
	
	Booking book;
	
	@BeforeMethod
	public void TestHouseHoldInformationSetUp()
	{
		book = PageFactory.initElements(webdriver, Booking.class);
	}

	//Validating all links in a given page.It covers test scenarios 1,2, 3 and 4
	@Test
	public void TestLinksValidation()
	{
		Assert.assertTrue(book.ValidateLinks());
	}
	
	//Login Button validation. Test scenario 5
	@Test(dependsOnMethods = "TestLinksValidation", alwaysRun = true)
	public void TestBookingLoginButton()
	{
		Assert.assertTrue(book.ClickButton("Login"));
	}
	
	//Guest Button validation. Test scenario 6
	@Test(dependsOnMethods = "TestBookingLoginButton", alwaysRun = true)
	public void TestGuestLoginButton()
	{
		Assert.assertTrue(book.ClickButton("Guest"));
	}
	
	//Empty form validations. Test scenarios 7 to 14
	@Test(dependsOnMethods = "TestGuestLoginButton", alwaysRun = true)
	public void TestEmptyFirstName()
	{
		book.ClickButtonWithScroll("Complete_Booking");
		book.waitforpagetoload();
		Assert.assertTrue(book.ErrorMsgValidation("firstname_empty"));
	}
	
	@Test(dependsOnMethods = "TestEmptyFirstName", alwaysRun = true)
	public void TestEmptyLastName()
	{
		book.EnterElement("firstname", "Complete_Booking");
		book.waitforpagetoload();
		Assert.assertTrue(book.ErrorMsgValidation("lastname_empty"));
	}
	
	@Test(dependsOnMethods = "TestEmptyLastName", alwaysRun = true)
	public void TestEmptyEmail()
	{
		book.EnterElement("lastname", "Complete_Booking");
		book.waitforpagetoload();
		Assert.assertTrue(book.ErrorMsgValidation("email_empty"));
	}
	
	@Test(dependsOnMethods = "TestEmptyEmail", alwaysRun = true)
	public void TestEmptyPhone()
	{
		book.EnterElement("phone", "Complete_Booking");
		book.waitforpagetoload();
		Assert.assertTrue(book.ErrorMsgValidation("phone_empty"));
	}
	
	@Test(dependsOnMethods = "TestEmptyPhone", alwaysRun = true)
	public void TestEmptyCardHolderName()
	{
		book.EnterElement("cardholdername", "Complete_Booking");
		book.waitforpagetoload();
		Assert.assertTrue(book.ErrorMsgValidation("cardname_empty"));
	}
	
	@Test(dependsOnMethods = "TestEmptyCardHolderName", alwaysRun = true)
	public void TestEmptyCardNumber()
	{
		book.EnterElement("cardnumber", "Complete_Booking");
		book.waitforpagetoload();
		Assert.assertTrue(book.ErrorMsgValidation("cardnumber_empty"));
	}
	
	@Test(dependsOnMethods = "TestEmptyCardNumber", alwaysRun = true)
	public void TestEmptySecurityCode()
	{
		book.EnterElement("security", "Complete_Booking");
		book.waitforpagetoload();
		Assert.assertTrue(book.ErrorMsgValidation("security_empty"));
	}	
}
