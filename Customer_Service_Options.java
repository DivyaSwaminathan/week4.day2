package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class Customer_Service_Options {

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
		Shadow dom=new Shadow(driver);
		WebElement findElementByXPath = dom.findElementByXPath("//span[text()='Products']");
		findElementByXPath.click();
		
		Actions builder=new Actions(driver);
		Thread.sleep(1000);
		builder.moveToElement(dom.findElementByXPath("//span[text()='Service']")).perform();
		dom.findElementByXPath("//a[text()='Customer Service']").click();
		
		List<WebElement> findElements = driver.findElements(By.xpath("//div[@class='col  text-left col-xs-12 col-sm-12 col-md-9 col-lg-9']//h2"));
		System.out.println("----------Services Available----------------");
		for (WebElement webElement : findElements) {
			String service=webElement.getText();
			System.out.println(service);
		}
			String title = driver.getTitle();
			System.out.println("Title : " + title);
		}
		
	}


