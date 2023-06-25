package sprint1;

import java.awt.print.Pageable;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.hc.core5.util.Asserts;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class S09_112_CreateAccount {

	@Test
	public void AccountCreation() {
		/*
		Test Steps 
		1.Login to https://login.salesforce.com/
		  Username: hari.radhakrishnan@qeagle.com
		  Password: Leaf@1234 username: id - username password: id - password LoginBtn: id - Login
		2. Click on toggle menu button from the left corner - //one-app-launcher-header/button/div //div[@class='slds-icon-waffle'] 
		3. Click view all and click salce from app lancher //button[text()='View All'], //p[text()='Sales']
		4. Click on Account Tab - linktext-Accounts, xpath-//one-app-nav-bar-item-root/a/span[text()='Accounts'] 5. Click on new button - linktext-New, xpath-//a[@title='New']/div 
		6. Enter 'Your Name' as account name  
		7. Select Ownership as public - //label[text()='Ownership']/following-sibling::div, //span[text()='Public'] 
		8. Click save and verify account name Acceptance criteria: Account should be created successfully

		*/
		
		//Download Chrome Driver and set the path
		
		WebDriverManager.chromedriver().setup();
		
		//Disable Notifications in Chrome Browser 
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable-notifications");
		
				
		//Login to https://login.salesforce.com/
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		
		driver.findElement(By.id("username")).sendKeys("binsy.salesforce@sandbox.com");
		driver.findElement(By.id("password")).sendKeys("Basil2921");
		driver.findElement(By.id("Login")).click();
		
		//Navigate to Account Object
		driver.findElement(By.xpath("//div[@class='slds-r3']/following-sibling::div[1]")).click();
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//p[text()='Sales']")).click();
		
		WebElement we = driver.findElement(By.xpath("//span[text()='Accounts']"));
		driver.executeScript("arguments[0].click();", we);
		
		driver.findElement(By.xpath("//div[text()='New']")).click();
		
		driver.findElement(By.xpath("//label[text()='Account Name']/following::input")).sendKeys("TestLeaf ");
		
		//click on search of parent account 
		driver.findElement(By.xpath("//label[text()='Parent Account']/following::input")).click();
		
		//select the first parent account from the drop down 
		driver.findElement(By.xpath("//*[text()='Recent Accounts']/following::*[1]")).click();
		
		//Send Value for Account Number 
		driver.findElement(By.xpath("//label[text()='Account Number']/following::input")).sendKeys("324234545");
		
		driver.findElement(By.xpath("//label[text()='Account Site']/following::input[1]")).sendKeys("Mumbai");
	
		driver.findElement(By.xpath("(//button[@role='combobox'])[2]")).click();
		
		driver.findElement(By.xpath("//span[text()='Prospect']")).click();
		
		driver.findElement(By.xpath("//label[text()='Ownership']/following::*[1]")).click();
		
		driver.findElement(By.xpath("//span[text()='Public']")).click();
				
		
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		
		//Navigate to details tab after saving details
		
		driver.findElement(By.xpath("//li[@title='Details']//a[1]")).click();
		
        WebElement wes=	driver.findElement(By.xpath("(//*[text()='Account Name'])[2]/following::*[1]"));
		
        String strAccountName=wes.getText();
        
        
        
        if (strAccountName.contains("TestLeaf"))
        	System.out.println("Account created successfully");
        else
        	System.out.println("Account not created ");
        
		
		
		//driver.close();
		
		

	}

}
