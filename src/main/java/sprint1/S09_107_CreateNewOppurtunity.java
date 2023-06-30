package sprint1;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

/*
 * Test Steps:
 * 1. Login to https://login.salesforce.com
 * 2. Click on toggle menu button from the left corner
 * 3. Click view All and click Sales from App Launcher
 * 4. Click on Opportunity tab 
 * 5. Click on New button
 * 6. Enter Opportunity name as 'Salesforce Automation by Your Name,Get the text and Store it 
 * 7. Choose close date as Today
 * 8. Select 'Stage' as Need Analysis
 * 9. click Save and VerifyOppurtunity 
 *    Name Expected Result:New Opportunity should be created with name as  'Salesforce Automation by Your Name'
 */
public class S09_107_CreateNewOppurtunity {

	@SuppressWarnings("deprecation")
	@Test
	public void createNewOppurtunity() throws InterruptedException {
	
		        WebDriverManager.chromedriver().setup();
								
				ChromeOptions options = new ChromeOptions();
				
				options.addArguments("--remote-allow-origins=*");
				
				options.addArguments("--disable-notifications");
				
				ChromeDriver driver = new ChromeDriver(options);
			
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			
				driver.get("https://login.salesforce.com/");
				
				driver.manage().window().maximize();
				
				driver.findElement(By.id("username")).sendKeys("binsy.salesforce@sandbox.com");
			
				driver.findElement(By.id("password")).sendKeys("Basil2921");
			
				driver.findElement(By.id("Login")).click();
				
				driver.findElement(By.xpath("//div[@class='slds-r3']/following-sibling::div[1]")).click();
				
				driver.findElement(By.xpath("//button[text()='View All']")).click();
				
				WebElement weo=driver.findElement(By.xpath("//p[text()='Opportunities']"));
				
				driver.executeScript("arguments[0].scrollIntoView(true);", weo);
			
				weo.click();
				
				driver.findElement(By.xpath("//a[@title='New']")).click();
				
				String oppname = "Electric Wire Cables";
				
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
				
				driver.findElement(By.xpath("//*[text()='Web']")).click();
				
				Thread.sleep(2000);
				
				WebElement weLabelCloseDate = driver.findElement(By.xpath("//label[text()='Close Date']"));
				
				driver.executeScript("arguments[0].scrollIntoView(true)", weLabelCloseDate);
			
				Thread.sleep(2000);
				
				driver.findElement(By.xpath("(//label[text()='Close Date']/following::input)[1]")).click();
				
				Date date = Calendar.getInstance().getTime();
				
				DateFormat formatter =new SimpleDateFormat("dd");
				
				String today=formatter.format(date);
				
				System.out.println(date);
				
				driver.findElement(By.xpath("//span[text()='"+ today +"']")).click();
				
				driver.findElement(By.xpath("(//label[text()='Stage']/following::*)[1]")).click();
				
				Thread.sleep(2000);
				
				WebElement weSelect =driver.findElementByXPath("//*[@data-value='Needs Analysis']");
				
				weSelect.click();
				
				Thread.sleep(2000);
				
				driver.findElement(By.xpath("//button[text()='Save']")).click();
				
				Thread.sleep(5000);
				
				WebElement webOppName= driver.findElementByXPath("(//*[text()='Opportunity']/following::lightning-formatted-text)[1]");
				
				String oppnameresult=webOppName.getText();
						
				System.out.println("Oppurtunity Name - "+oppnameresult);
				
				driver.close();
		
				String ExpectedTitle=oppname;
				
				String ActualTitle=oppnameresult;
				
				Assert.assertEquals(ExpectedTitle, ActualTitle);
				
	}

}
