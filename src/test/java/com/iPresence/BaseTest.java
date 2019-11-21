package com.iPresence;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class BaseTest {

	protected static WebDriver webdriver;
	protected ThreadLocal<RemoteWebDriver> threadDriver = null;

	@Parameters({ "browserType" })
	@BeforeSuite
	public static void beforeclass(String browserType) throws MalformedURLException, InterruptedException {

		String URL = "https://www.phptravels.net/thhotels/checkout/Ambassador%20Bangkok%20Hotel";
		if (browserType.equalsIgnoreCase("firefox")) {
			System.out.println(" Executing on FireFox");
			String Node = "http://192.168.0.12:5566/wd/hub";
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setBrowserName("firefox");

			webdriver = new RemoteWebDriver(new URL(Node), cap);
			// Puts an Implicit wait, Will wait for 10 seconds before throwing
			// exception
			webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Launch website
			webdriver.navigate().to(URL);
			webdriver.manage().window().maximize();
		} else if (browserType.equalsIgnoreCase("chrome")) {
			System.out.println(" Executing on CHROME");
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe");
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");
			cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			String Node = "http://192.168.0.12:5567/wd/hub";
			webdriver = new RemoteWebDriver(new URL(Node), cap);
			webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Launch website
			webdriver.navigate().to(URL);
			webdriver.manage().window().maximize();
		} else {
			throw new IllegalArgumentException("The Browser Type is Undefined");
		}
	}

	public static WebDriver getDriver() {
		return webdriver;
	}

	@AfterSuite(alwaysRun = true)
	public static void tearDown() {
		if (getDriver() != null) {
			getDriver().quit();
			System.out.println("Application is logged out");
		}
	}

}
