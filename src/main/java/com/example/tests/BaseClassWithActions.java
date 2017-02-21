package com.example.tests;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by user on 2/20/17.
 */
public class BaseClassWithActions {

    WebDriverWait wait = new WebDriverWait(JUnitTest.driver, 5);
    int c = 1;

    public WebElement initObjectforWork(String xpathForElement)
    {
        isElementPresent(xpathForElement);
        WebElement element = JUnitTest.driver.findElement(By.xpath(xpathForElement));
        return element;
    }

    public WebElement initObjectforWorkId(String id)
    {
        CheckElementisPresent(id);
        WebElement element = JUnitTest.driver.findElement(By.id(id));
        return element;
    }

    public void clickOnElement(String xpath)
    {
        wait.until(ExpectedConditions.elementToBeClickable(initObjectforWork(xpath))).click();
    }

    public void isElementClickable(WebElement clicable)
    {
        if(clicable.isDisplayed() && clicable.isEnabled())
            JUnitTest.userLogger.info("Элемент доступeн для нажатия -"+ clicable);
        else JUnitTest.userLogger.error("Элемент не доступен для нажатия");
    }

    public void CheckElementisPresent(String locator)
    {
        try
        {
            Assert.assertTrue(!JUnitTest.driver.findElements(By.id(locator)).isEmpty());
        }
        catch (AssertionError e)
        {
            JUnitTest.userLogger.error("Локатор "+ locator +" не найден");
            Assert.fail();
        }
        JUnitTest.userLogger.info("Локатор - " + locator+ " найден");
    }

    public void isElementPresent(String locator)
    {
        try
        {
            JUnitTest.driver.findElement(By.xpath(locator));
        }
        catch (NoSuchElementException e)
        {
            JUnitTest.userLogger.error("Неверный локатор для поиска - "+locator);
            Assert.fail();
        }
        JUnitTest.userLogger.info("Найден локатор - " + locator);
    }

    public void shoot() throws IOException
    {
        String k = "screen/shoot/screensh";
        String n = ".png";
        ArrayList<File> arr = new ArrayList<File>();
        for (int i = arr.size(); i == arr.size(); i--)
        {
            k = k + c;
            File screenshot = ((TakesScreenshot) JUnitTest.driver).getScreenshotAs(OutputType.FILE);
            arr.add(screenshot);
            if(arr.size()==0)
                JUnitTest.userLogger.error("Скриншот не осуществлен");
            else FileUtils.copyFile(screenshot, new File(k + n));
            c++;
        }
    }

    public void loginInAcc(String passw, String logi, String buttn)
    {
        WebElement password = initObjectforWorkId(passw);
        WebElement login = initObjectforWorkId(logi);
        WebElement auth = initObjectforWorkId(buttn);
        isElementClickable(auth);
        suppMethodForLogInAcc(password,"qwerty1996",login,"lesha.soloshenko3",auth);
    }

    public void suppMethodForLogInAcc(WebElement pass, String password, WebElement log, String login, WebElement auth)
    {
        pass.sendKeys(password);
        log.sendKeys(login);
        auth.click();
        JUnitTest.userLogger.info("Вход в кабинет осуществлен успешно");
    }

    public void moveToElem(String xpath)
    {
        Actions builder = new Actions(JUnitTest.driver);
        builder.moveToElement(initObjectforWork(xpath)).build().perform();
    }

    public void drag_drop(String drag, String drop)
    {
        Actions builder = new Actions(JUnitTest.driver);
        try {
            builder.dragAndDrop((initObjectforWork(drag)), (initObjectforWork(drop))).build().perform();
        }
        catch (AssertionError e)
        {
            JUnitTest.userLogger.error("Не найден элемент");
        }
    }

    public void waitForUploadUrl(String url)
    {
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public void getAttrributeandCssvalue(String xpForElement)
    {
        WebElement elem = JUnitTest.driver.findElement(By.xpath(xpForElement));
        JUnitTest.userLogger.info("getCssValue(\"left\"): " +
                elem.getCssValue("left"));
        JUnitTest.userLogger.info("getCssValue(\"font-size\"): " +
                elem.getCssValue("font-size"));
        JUnitTest.userLogger.info("getCssValue(\"color\"): " +
                elem.getCssValue("color"));
        JUnitTest.userLogger.info("getAttribute(\"style\"): " +
                elem.getAttribute("style"));
        JUnitTest.userLogger.info("getAttribute(\"offsetLeft\"): " +
                elem.getAttribute("offsetLeft"));
        JUnitTest.userLogger.info("getAttribute(\"href\"): " +
                elem.getAttribute("href"));
    }

    public void isTextPresent(String text)
    {
        if (JUnitTest.driver.getPageSource().contains(text))
            JUnitTest.userLogger.info("Переданный текст(method1) присутствует на данной странице");
        else JUnitTest.userLogger.error("На странице не найден текст -" + text);
    }

    public void attachFile(String pathFolder, String pathXpath)
    {
        File f = new File(pathFolder);
        isElementPresent(pathXpath);
        WebElement fileInput = initObjectforWork(pathXpath);
        fileInput.sendKeys(f.getAbsolutePath());
        JUnitTest.userLogger.info("Файл прикреплен");
    }

    public void selectFromTheList(String xpathForSelectedItem)
    {
        isElementPresent(xpathForSelectedItem);
        Select sel = new Select(initObjectforWork(xpathForSelectedItem));
        sel.selectByIndex(1);
        JUnitTest.userLogger.info("Изменена локализация страницы через выпадающий список");
    }

    public void switchBetweenWindow(WebDriver driver, String xpath)
    {
        String firstWindow = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        clickOnElement(xpath);
        String secondWindow = (new WebDriverWait(driver, 10))
                .until(new ExpectedCondition<String>(){
                           public String apply(WebDriver driver) {
                               Set<String> newWindowsSet = driver.getWindowHandles();
                               newWindowsSet.removeAll(oldWindowsSet);
                               return newWindowsSet.size() > 0 ?
                                       newWindowsSet.iterator().next() : null;
                           }
                       }
                );
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JUnitTest.driver.switchTo().window(secondWindow);
        JUnitTest.driver.close();
        JUnitTest.driver.switchTo().window(firstWindow);
    }
}