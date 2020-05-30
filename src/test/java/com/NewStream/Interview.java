package com.NewStream;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Interview {
	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		// What is diff get() and navigate()
		driver.get("");
		// for using browser back, fwd and refresh navigate.
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		// What is diff close and quit
		driver.close();
		// driver close an active window
		driver.quit();
		// driver close all windows which are opened by webdriver

		// implicitwait()
		// page or link is not displayed it will wait for some time, so we use implicit
		// wait
		// define bigining of the Test. it will wait till the mentioned time.
		// It will affect for each and every step in the script.
		// driver will resume before the mentioned time it the element load in the
		// browser.
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// diff between implicit and explicit wait?
		// because of one componenet or one element no need wait for each and every
		// step. we can wait more time only for that particular element by using the
		// explicit wait.
		// both waits should be used robust tests frameworks for selenium

		// how many ways we can handle frames
		// frames handle little bit tricky, switch to frames from browser mode.
		driver.switchTo().frame(0);
		// frameName
		// frameId
		// frame index
		// how to get the frameid, framename, framindex

		// code to handle third child window
		// Selenium will be on in first window (which it opens starting)
		Set<String> windows = driver.getWindowHandles();
		// internally it will store in index based. with help of iterator we can
		Iterator<String> it = windows.iterator();
		it.next();
		it.next();
		String rdWindow = it.next(); // this is 3rd window.
		driver.switchTo().window(rdWindow);

		// how to handles https certifications
		// by using desired capabilities we can manage https certifications
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

		// diff types of locators in webdriver
		// xpath, css, id, name, classname, linkText, partialLinkText, tagName

		// which locator is fast - cssSelector is too fast
		// css => tagName[id=value]
		// xpath => tagname[@id=value]
		// How to use contains regualr express in xpath

		// for dynamic element ids as id =uu012, id=uua98, id = uu43
		// tagname[contains(@id, 'uu')]

		// startwith
		// endswith

		// how to use regular express in css
		// tagname[id*=uu]

		// what is the class in available in selenium to handle dropdowns
		// select class

		// what is the method to check if the check box is selected ?
		//
		driver.findElement(By.xpath("")).isSelected(); // it will return true if the chek box is selected. it will
														// return false if the check box is nto selected.

		// How to validate if element is visible or hidden in webpage?
		// we can use .isDisplayed Method
		driver.findElement(By.xpath("")).isDisplayed(); // return true if it displayed. return false if it not
														// displayed.

		// How to get the similar objects in the webpage?
		// className=ABC with the same name 15 times in the same page.
		driver.findElements(By.className("ABC")).size();
		// you can use for loop and get all

		// DesiredCapabilities is a class which describes browser or os in this
		// importance of desired capabilities mechanism / selenium grid

		// What does selenium grid
		// distributes test across the multiple machines / OS - locally or remote

		// How to enter text in capital lock?
		driver.findElement(By.xpath("")).sendKeys(Keys.SHIFT, "hello"); // it will enter HELLO

		// how to mouse over on the web element
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath(""))).build().perform();

		// methods to handle alerts
		driver.switchTo().alert();

		// how to get links count in a webpage
		driver.findElements(By.tagName("a")).size();

		// How to validate if we are navigated to child window successfully?
		// assert with page title
		driver.getTitle();

		// Handle static dropdowns

		// handle dynamic dropdowns

		// diff with absolute and relative xpath
		// instead of traveresing from starting point to the target element we can find
		// a root or unique parent element
		// Absolute start with / and relative start with //

		// What driver is must to run test in firefox browser?
		// in recent version we need gecko driver.

		// What driver is must to run tests in chrome browsers
		// ChromeDriver

		// How do you set driver in firefox and chrome drivers through scripts?
		System.setProperty("Webdriver.chrome.driver", "path\\.chromedriver.exe");

		// diff with findelement and findElements
		// findElement is finding an element uniquly in the webpage.
		// if u want to work around smiliar multiple objects then use findElements.

		// list out any 2 methods available in explicit wait in selenium
		// visibilityOfElementLocator -- driver will wait till the element visible.
		// presenceOfElementLocator -- here driver will wait and resume the actions even
		// the element is in invisible mode it will work

		// How to takescreenshots in selenium webdriver.

		// how to hit enter from webdriver commands
		driver.findElement(By.xpath("")).sendKeys(Keys.ENTER);

		// --- multi select dropdown code
		// http://naveenautomationlabs.com/handle-dropdown-using-select-class-in-selenium/

		List<WebElement> links = driver.findElements(By.tagName("a"));
		links.addAll(driver.findElements(By.tagName("img")));

		List<WebElement> activeLinks = new ArrayList<WebElement>();

		// iterate links list : exclude all the links or images - does not have any href
		// attribute
		for (int i = 0; i < links.size(); i++) {
			if (links.get(i).getAttribute("href") != null && (! links.get(i).getAttribute("href").contains("javascript"))) {
				activeLinks.add(links.get(i));
			}
		}

		// get the size of activeLinks
		System.out.println(activeLinks.size());
		for (int j = 0; j < activeLinks.size(); j++) {
			try {
				HttpURLConnection connection = (HttpURLConnection) new URL(activeLinks.get(j).getAttribute("href"))
						.openConnection();
				
				connection.connect();
				String response = connection.getResponseMessage(); //
				connection.disconnect();
				System.out.println(activeLinks.get(j).getAttribute("href") + "-->" + response);
				
				//404 not found
				//500 internal error
				//400 is bad request
				//200 is ok success
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

}
