package TestNG;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import POM.LoginPage;

public class BaseClass {

    public static WebDriver driver;
    public String username;
    public String pwd;

    @BeforeClass
    public void openBrowser() throws IOException {

        FileInputStream fis = new FileInputStream("./testdata/CommonData.properties");
        Properties prop = new Properties();
        prop.load(fis);

        String url = prop.getProperty("url");
        username = prop.getProperty("un");
        pwd = prop.getProperty("pwd");

    
       ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);   
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        driver.get(url);
    }

    @BeforeMethod
    public void login() {

        LoginPage lp = new LoginPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOf(lp.getUsername()));
        wait.until(ExpectedConditions.visibilityOf(lp.getPassword()));

        lp.getUsername().sendKeys(username);

        lp.getPassword().sendKeys(pwd);


        wait.until(ExpectedConditions.elementToBeClickable(lp.getSubmit()));

        lp.getSubmit().click();
    }

    @AfterClass
    public void closeBrowser() throws InterruptedException {

        if (driver!= null) {
        	Thread.sleep(1000);
            driver.quit();
        }
    }
}