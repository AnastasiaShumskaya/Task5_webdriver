package com.seleniumTask5.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class AppTest {

    private WebDriver driver = new FirefoxDriver();

    private String url = "https://192.168.100.26/";
    private String username = "AnastasiaShumskaya";
    private String password = "1";
    private String loginTitle = "RMSys - Sign In";

    private WebElement element;

    @BeforeMethod(alwaysRun = true)
    public void setUpTest() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//1
        driver.navigate().to(url);
    }

    @AfterClass
    public void tearDownTest() {

        driver.close();
    }

    @Test
    public void seleniumTest() throws InterruptedException {

        driver.findElement(By.id("Username")).sendKeys(username);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("SubmitButton")).click();

        Thread.sleep(1000);//2

        element = (new WebDriverWait(driver, 10)).until(ExpectedConditions
                .presenceOfElementLocated(By.cssSelector("a[title='Sign out']")));//3

        Assert.assertTrue(element.isDisplayed(), "Sign out link is not displayed!");
    }

    @Test
    public void testCase() {

        //4.	Add explicit waiter for following test case:
        // a)	Go to RMSys login page, RMSys login page should appear.
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleContains(loginTitle));

        //b)	Enter correct username and password, both inputs should be filled in.
        driver.findElement(By.id("Username")).sendKeys(username);
        driver.findElement(By.id("Password")).sendKeys(password);

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return !driver.findElement(By.cssSelector("div[id='user-box-validation'] > span")).isDisplayed()
                        &&!driver.findElement(By.cssSelector("div[id='password-box-validation'] > span")).isDisplayed();
            }
        });

        //c)	Click Submit button, RMSys home page should appear.
        element = driver.findElement(By.id("SubmitButton"));
        element.click();

        wait.until(ExpectedConditions.titleContains("Home"));

        //d)	Go to office tab, wait for "Search by office" input to appear (wait 15 seconds, polling frequence - 2,7 seconds).
        driver.findElement(By.id("officeMenu")).click();

        wait = new WebDriverWait(driver, 15, 2700);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-search")));

    }

}
