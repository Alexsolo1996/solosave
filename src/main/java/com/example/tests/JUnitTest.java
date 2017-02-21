package com.example.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestMethod1.class,
        TestMethod3.class,
        TestMethod2.class
})

public class JUnitTest {

    static WebDriver driver;
    static String baseUrl;
    private static StringBuffer verificationErrors = new StringBuffer();
    static final Logger userLogger = LogManager.getLogger(Logger.class.getName());

 @BeforeClass  public static void setUp() throws Exception {
     System.setProperty("webdriver.chrome.driver","scope/drivers/chromedriver");
     driver = new ChromeDriver();
     baseUrl = "https://mail.ru";
     driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

 }
 @AfterClass  public static void tearDown() throws Exception {
     driver.quit();
     String verificationErrorString = verificationErrors.toString();
     if (!"".equals(verificationErrorString)) {
         fail(verificationErrorString);
     }
 }
}