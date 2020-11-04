package com.extentReport;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import static org.junit.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestNG {

    private WebDriver driver;
    
    ExtentHtmlReporter htmlReporter;
	ExtentReports extent;

	@BeforeSuite
	public void setUp() {
		htmlReporter = new ExtentHtmlReporter("extentRep.html");

		// create ExtentReports and attach reporter(s)
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		htmlReporter.config().setDocumentTitle("Automation Report"); // Tittle of Report
		htmlReporter.config().setReportName("Extent Report V4"); // Name of the report
		htmlReporter.config().setTheme(Theme.DARK);//Default Theme of Report
	}
	@AfterSuite
  	public void teardown() {
  		extent.flush();

  	}

    @BeforeMethod
      public void Startup(){
        //Initiate driver
        driver = new FirefoxDriver();
      }

    @Test(priority=1)
        public void GoogleLogin() throws Exception{

        //Go to Gmail  
        driver.get("http://gmail.com");
        assertEquals("Sign in", driver.findElement(By.id("signIn")).getAttribute("value"));

        //Maximize the window
        driver.manage().window().maximize();

        //Clear autofilled and enter username, click next
        driver.findElement(By.id("Email")).clear();
        driver.findElement(By.id("Email")).sendKeys("testing20201011@gmail.com");
        driver.findElement(By.id("next")).click();
        Thread.sleep(2000);

        //Clear pass and enter password, click submit
        driver.findElement(By.id("Passwd")).clear();
        driver.findElement(By.id("Passwd")).sendKeys("dell@123");
        driver.findElement(By.id("PersistentCookie")).click();
        driver.findElement(By.id("signIn")).click();
        Thread.sleep(2000);

        //verify login is done
        System.out.println(driver.getTitle());
        Thread.sleep(2000);
    }

    @Test(priority=2)
        public void CreateMail() throws Exception{  

        //Click on Compose button
        driver.findElement(By.xpath("//div[contains(text(),'COMPOSE')]")).click();
        Thread.sleep(4000);

        //ENter the reciepient mail id
        driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys("testing20201011@gmail.com");

        //Enter Subject
        driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys("Selenium Test Mail");

        //ENter mail body
        driver.findElement(By.xpath("//div[@class='Ar Au']//div")).sendKeys("Hi SD");
        Thread.sleep(4000);
    }   

    @Test(priority=3)
    public void SendVerify() throws Exception
        {//Send the email
        driver.findElement(By.xpath("//div[contains(text(),'Send')]")).click();
        Thread.sleep(4000);


        //Go to Sent Items
        driver.findElement(By.xpath("//a[@title='Sent Mail']")).click();

        //Verify that mail is sent
        if (driver.findElement(By.xpath("//div[@class='span']//span[text()='Selenium Test Mail']")) != null)

        {
            System.out.println("Email is sent successfully");
        }

        else
        {
            System.out.println("Error::Email is not sent!!");
        }
        Thread.sleep(2000);
        }
        //Logout and close the window
    @Test(priority=4)
    public void GmailLogout() throws Exception
    {
        driver.findElement(By.xpath("//a[@class='gb_b gb_db gb_R']")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Sign out')]")).click();

        //Verify Login is done

        String Title = driver.getTitle();

        if ( Title != "testing20201011@gmail.com")
        {
            System.out.println("Your Gmail is logged out!!");
        }

        else
        {
            System.out.println("Something went wrong. Pls logout manually!!");
        }
      }

      @AfterMethod
      public void Exit(){
        driver.close();
      }
      
}