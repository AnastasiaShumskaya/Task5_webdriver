package com.seleniumTask5.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FrameTest {

    private WebDriver driver = new FirefoxDriver();

    private WebElement element;

    private String url = "https://the-internet.herokuapp.com/iframe";
    private String text = "Hello, world!";

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
    public void seleniumTest() {

        WebElement iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);

        element = driver.findElement(By.cssSelector("#tinymce"));
        element.clear();
        element.sendKeys(text);

        Assert.assertEquals(element.getText(), text);
    }

}
