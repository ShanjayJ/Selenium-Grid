package com.vmetry.selenium.grid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SeleniumGrid {
	WebDriver driver;
	
	@Parameters({"browser","platform"})
	@BeforeTest
	public void setup(String browser, String platform) throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		
		if (browser.equalsIgnoreCase("Chrome")) {
			caps =DesiredCapabilities.chrome();
		} else if (browser.equalsIgnoreCase("Firefox")) {
			caps =DesiredCapabilities.firefox();
		}
		if (platform.equalsIgnoreCase("Windows 10")) {
			caps.setPlatform(Platform.WIN10);
		}
		
		driver = new RemoteWebDriver(new URL("http://192.168.1.3:8885/wd/hub/"),caps);
		driver.manage().window().maximize();
		
		
	}
	
	@Test
	public void Launch() {
		driver.get("https://www.google.co.in/");
	}
	
	@AfterTest
	public void tearDown() throws InterruptedException {
		if (driver != null) {
			Thread.sleep(3000);
			driver.quit();
		}
	}

}
