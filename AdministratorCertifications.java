package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class AdministratorCertifications {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		//to disable browser notifications 
		ChromeOptions option=new ChromeOptions();
		option.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(option);

		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		//wait declarations 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com ");
		driver.findElement(By.id("password")).sendKeys("Password$123");
		driver.findElement(By.id("Login")).click();

		driver.findElement(By.xpath("//span[contains(text(),'Learn More')]")).click();
		//opens new window and used windowHandles
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> winLst=new ArrayList<String>(windowHandles);
		String firstWin=winLst.get(0);
		String secondWin=winLst.get(1);
		driver.switchTo().window(secondWin);
		

		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
		
		//finding element in shadow dom(elements under shadow-root)-- when u try to inspect the element disappears from webpage
		
		Shadow dom=new Shadow(driver);
		dom.findElementByXPath("//span[text()='Learning']").click();
		Actions builder=new Actions(driver);
		Thread.sleep(1000);
		builder.moveToElement(dom.findElementByXPath("//span[text()='Learning on Trailhead']")).perform();
		dom.findElementByXPath("//a[text()='Salesforce Certification']").click();
		WebElement cert = driver.findElement(By.xpath("//a[text()='Administrator']"))	;
	
		builder.scrollToElement(cert);
		cert.click();
		String title = driver.getTitle();
		System.out.println("Title of the page : " + title);
		
		



	}

}
