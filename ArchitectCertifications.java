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

public class ArchitectCertifications {


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
		driver.findElement(By.xpath("//img[@alt='Salesforce<br/>Architect']/following::div[1]")).click();

		WebElement findElement = driver.findElement(By.xpath("//div[@class='cert-site_text slds-text-align--center Lh(1.5) Fz(16px) slds-container--center slds-p-bottom--large']"));
		String s1=findElement.getText();
		System.out.println(s1);

		List<WebElement> titles = driver.findElements(By.xpath("//div[@class='credentials-card_title']/a"));
		System.out.println("----------List of Salesforce Architect Certification------------------");
		for (WebElement webElement : titles) {
			String certs=webElement.getText();
			System.out.println(certs);
		}
		driver.findElement(By.linkText("Application Architect")).click();
		List<WebElement> creds = driver.findElements(By.xpath("//div[@class='credentials-card_title']/a"));
		System.out.println("----------List of Application Architect Credentials------------------");
		for (WebElement webElement : creds) {
			String cred=webElement.getText();
			System.out.println(cred);
		}

	}

}
