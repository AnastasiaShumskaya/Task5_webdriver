package com.seleniumTask5.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestDDT {

    private WebDriver driver = new FirefoxDriver();
    private String url = "https://192.168.100.26/";

    @DataProvider(name = "credentials")
    public Object[][] dataSet() {
        return new Object[][] {
                {"AnastasiaShumskaya", "1", "Shumskaya, Anastasia"},
                {"q",                  "1", "*Invalid credentials."},
                {"",                   "",  "Username is required"},
                {"",                   "",  "Password is required"},
                {"q",                  "2", "*Invalid credentials."},
                {"q",                  "",  "Password is required"},
                {"AnastasiaShumskaya", "",  "Password is required"},
                {"",                   "1", "Username is required"},
                {"",                   "2", "Username is required"}
        };
    }

    @BeforeMethod(alwaysRun = true)
    public void setUpTest() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(url);
    }

    @AfterClass
    public void tearDownTest() {

        driver.close();
    }

    @Test(dataProvider = "credentials")
    @Parameters({ "username", "password", "message"})
    public void testDDT(String username, String password, final String message) {

        driver.findElement(By.id("Username")).sendKeys(username);
        driver.findElement(By.id("Password")).sendKeys(password);

        driver.findElement(By.id("SubmitButton")).click();

        Assert.assertTrue(driver.getPageSource().contains(message));

    }
}
