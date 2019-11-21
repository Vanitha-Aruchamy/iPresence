package com.iPresence.utils;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class URLUtil {
	
	WebDriver webdriver;
	WebDriverWait wait;
	static String linkvalidationdetails;
	BrowserUtil browserutil;

	public URLUtil(WebDriver webdriver) {
		this.webdriver = webdriver;
		browserutil = PageFactory.initElements(webdriver, BrowserUtil.class);
	}
	
	public Boolean VerifyLinkValidation() {
		boolean linkvalidation = true;
		ArrayList<Boolean> finalvalue = new ArrayList<Boolean>();
			try {
				String PrimaryWindow = webdriver.getCurrentUrl();
				String pagesource = webdriver.getPageSource();
				Document afterparsing = Jsoup.parse(pagesource);
				Elements inputelements = afterparsing.getElementsByTag("a");
				// To store all the retrieved elements in an array
				ArrayList<Element> elements = new ArrayList<Element>();

				for (Element e : inputelements) {
					elements.add(e);
				}

				Element[] elementArr = elements.toArray(new Element[] {});

				for (int i = 0; i < elementArr.length; i++) {
					Element j = elementArr[i];
					boolean validurl = validateURL(j);
					if (validurl == true) {
						String k = j.attr("href");
						String l = j.attr("target");

						WebElement element = null;
							try {
								if(l.contains("blank")) {
									element = webdriver.findElement(By.xpath("//*[@href='" + k + "']"));
									element.click();
									webdriver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
									browserutil.SwitchtoParentWindow(webdriver);
									finalvalue.add(linkvalidation);
								}else
								{
								element = webdriver.findElement(By.xpath("//*[@href='" + k + "']"));
								element.click();
								webdriver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
								webdriver.get(PrimaryWindow);
								webdriver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
								finalvalue.add(linkvalidation);
								}
							} catch (ElementNotInteractableException | ElementNotSelectableException ex) {
								linkvalidation = false;
								finalvalue.add(linkvalidation);
								continue;
							}
					} else {
						continue;
					}
				}
				if (finalvalue.toString().contains("false")) {
					linkvalidation = false;
				} else {linkvalidation = true;
				}
			} catch (Exception e) {linkvalidation = false;
				finalvalue.add(linkvalidation);
				return linkvalidation;
		}
		return linkvalidation;

	}
	
	public Boolean validateURL(Element element) {
		boolean flag = true;
		String noturl = element.attr("target");
		String href = element.attr("href");
		if (noturl.matches("_blank") && href.contains("//")) {
			return flag;
		} else if (noturl.matches("_blank")) {
			flag = false;
			return flag;
		}
		return flag;
	}

}
