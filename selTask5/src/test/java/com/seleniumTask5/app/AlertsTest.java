package com.seleniumTask5.app;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AlertsTest {

    private WebDriver driver = new FirefoxDriver();

    private WebElement element;

    private String url = "https://the-internet.herokuapp.com/javascript_alerts";
    private String textAlert = "I am a JS Alert";
    private String textConfirm = "I am a JS Confirm";
    private String textPrompt = "Work is done!";

    @BeforeMethod(alwaysRun = true)
    public void setUpTest() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(url);
    }

    @AfterClass
    public void tearDownTest() {

        driver.close();
    }

    @Test
    public void alertFirst() {

        driver.findElement(By.cssSelector("button[onclick='jsAlert()']")).click();

        try {
            Alert alert = driver.switchTo().alert();

            Assert.assertEquals(alert.getText(), textAlert);

            alert.accept();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void alertSecond() {

        driver.findElement(By.cssSelector("button[onclick='jsConfirm()']")).click();

        try {
            Alert alert = driver.switchTo().alert();

            Assert.assertEquals(alert.getText(), textConfirm);

            alert.dismiss();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void alertThird() {

        driver.findElement(By.cssSelector("button[onclick='jsPrompt()']")).click();

        try {
            Alert alert = driver.switchTo().alert();

            alert.sendKeys(textPrompt);
            alert.accept();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }

        element = driver.findElement(By.id("result"));
        Assert.assertEquals(element.getText(), "You entered: " + textPrompt);
    }
}
