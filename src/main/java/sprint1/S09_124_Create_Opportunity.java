package sprint1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class S09_124_Create_Opportunity {

/*
* Test Steps:
1. Login to https://login.salesforce.com
2. Click on toggle menu button from the left corner
3. Click view All.
4. Click on Content tab 
5. Click View All Key Deals in Key Deals 
6. Click the dropdown from Opportunities and select All Opportunities
7. Click on New
8. Give Opportunity Name as SRM Steels
9. Select Type as New Customer and Lead Source as Partner Referral
10. Give Amount as 75000 and Select Close Date as Next month 20th day 
11. Select Stage as Needs Analysis
12. Click in Primary Campaign  Source and Select first option
13. Click Save and Verify the SRM Steels opportunity is created 
Expected Result: opportunity "SRM Steels" should be created with the given details      
*/
	@Test
	public void createOppurtunity() throws InterruptedException {
	
	        WebDriverManager.chromedriver().setup();
			
			ChromeOptions options = new ChromeOptions();
			
			options.addArguments("--remote-allow-origins=*");
			
			options.addArguments("--disable-notifications");
			
			ChromeDriver driver = new ChromeDriver(options);
		
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
			driver.get("https://login.salesforce.com/");
			
			driver.manage().window().maximize();
			
			driver.findElement(By.id("username")).sendKeys("hari.radhakrishnan@qeagle.com");
		
			driver.findElement(By.id("password")).sendKeys("Leaf@1234");
			
			driver.findElement(By.id("Login")).click();
			
			driver.findElement(By.xpath("//div[@class='slds-r3']/following-sibling::div[1]")).click();
			
			driver.findElement(By.xpath("//button[text()='View All']")).click();
			
			driver.findElement(By.xpath("//p[text()='Content']")).click();
			
			// Click View All Key Deals in Key Deals 
			
			WebElement wekeydeals = driver.findElement(By.xpath("//span[text()='View All Key Deals']"));
			
			driver.executeScript("arguments[0].scrollIntoView(true);",wekeydeals);
			
			wekeydeals.click();
			
			driver.findElement(By.xpath("//span[text()='Recently Viewed']")).click();
			
			driver.findElement(By.xpath("(//span[text()='All Opportunities']/../..)[1]")).click();
			
            Thread.sleep(3000);

			driver.findElement(By.xpath("//a[@title='New']")).click();
			
			Thread.sleep(5000);
			
			String oppname = "SRM Steels";
			
			driver.findElement(By.xpath("//label[text()='Opportunity Name']/following::input[1]"))
			                            .sendKeys(oppname);
			
			WebElement weAccountName =driver.findElement(By.xpath("//label[text()='Account Name']"));
			
			driver.executeScript("arguments[0].scrollIntoView(true);", weAccountName);
					
			driver.findElement(By.xpath("//label[text()='Account Name']/following::input[1]")).click();
			
			Thread.sleep(2000);
					
			driver.findElement(By.xpath("//*[text()='Recent Accounts']/following::li[2]")).click();
			
			WebElement weType = driver.findElement(By.xpath("//*[text()='Type']/following::button[1]"));
			
			driver.executeScript("arguments[0].scrollIntoView(true);", weType);
			
			weType.click();
			
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//span[text()='New Customer']")).click();
			
			driver.findElement(By.xpath("//label[text()='Lead Source']/following::button[1]")).click();
			
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//*[text()='Partner Referral']")).click();
			
			Thread.sleep(2000);
			
			WebElement weamount= driver.findElement(By.xpath("(//label[text()='Amount']/following::input)[1]"));
			
			driver.executeScript("arguments[0].scrollIntoView(true)", weamount);
			
			weamount.sendKeys("7500");
			
			WebElement weLabelCloseDate = driver.findElement(By.xpath("//label[text()='Close Date']"));
			
            Thread.sleep(2000);
			
			driver.findElement(By.xpath("(//label[text()='Close Date']/following::input)[1]")).click();
			
			WebElement we = driver.findElement(By.xpath("//button[@title='Next Month']"));
			
			we.click();
			
			String dt ="20";
			
			driver.findElement(By.xpath("//span[text()='"+ dt +"']")).click();
			
	        driver.findElement(By.xpath("(//label[text()='Stage']/following::*)[1]")).click();
			
			Thread.sleep(2000);
			
			String stage = "Needs Analysis";
			
			WebElement weSelect =driver.findElementByXPath("//*[@data-value='"+stage+"']");
			
			weSelect.click();
			
            Thread.sleep(2000);
            
            WebElement weprimarycamp = driver.findElement(By.xpath("(//label[text()='Primary Campaign Source']/following::input)[1]"));
            
            weprimarycamp.click();
            
            WebElement werecentcamp = driver.findElement(By.xpath("(//*[@title='Recent Campaigns']/following::*)[1]"));
            
            werecentcamp.click();
            
            driver.findElement(By.xpath("//button[text()='Save']")).click();
			
			Thread.sleep(5000);
			
			WebElement webOppName= driver.findElementByXPath("(//*[text()='Opportunity']/following::lightning-formatted-text)[1]");
			
			String oppnameresult=webOppName.getText();
					
			System.out.println("Oppurtunity Name - "+oppnameresult);
			
			driver.close();
			

	}

}
