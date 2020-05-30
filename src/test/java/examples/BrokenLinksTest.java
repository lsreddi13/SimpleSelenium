package examples;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class BrokenLinksTest {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver = new ChromeDriver();
		
		SoftAssert sa= new SoftAssert();
		sa.assertTrue(2<1);
		
		sa.assertAll();
	
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		if(wait.until(ExpectedConditions.alertIsPresent())==null)
		    System.out.println("alert was not present");
		else
		    System.out.println("alert was present");
		
		
				;
		
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		links.addAll(driver.findElements(By.tagName("img")));

		List<WebElement> activeLinks = new ArrayList<WebElement>();

		// iterate links list : exclude all the links or images - does not have any href
		// attribute
		for (int i = 0; i < links.size(); i++) {
			if (links.get(i).getAttribute("href") != null
					&& (!links.get(i).getAttribute("href").contains("javascript"))) {
				activeLinks.add(links.get(i));
			}
		}

		driver.manage().wait(3000);
		
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

				// 404 not found
				// 500 internal error
				// 400 is bad request
				// 200 is ok success
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

}
