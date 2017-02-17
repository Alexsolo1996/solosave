package com.example.tests;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
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

public class TestMethod2 {

    int c = 1;
    String xpforContacts = "//a[@bem-id = 108]";
    String xpforLogo = "//a[@href = \"//mail.ru/\"]";
    String xpforMess = ".//*[@id='js-mailbox-avatar']";
    String xpforMessinAcc = "//div[contains(text(), 'рекомендует прочитать это письмо')]";
    String xpforSite = "//input[@type = \"file\"]";
    String path = "/home/user/screensh/log.txt";
    String urladd = "http://bugscatcher.net/archives/540";
    String xpForSelect = ".//*[@id=':0.targetLanguage']/select";
    String xpForSwitch = "//a[@href= \"http://vk.com/e_mail_ru\"]";
    String xpforDropInSpam = "//span[@class = \"b-nav__item__text\" ][contains(text(), 'Спам')]";
    String xpforDragMess = "//div[contains(text(), 'больше, чем почта')]";
    WebDriverWait wait = new WebDriverWait(JUnitTest.driver, 5);


    @Test
    public void testMethodForWorkInAcc() throws Exception {

        //Код для блока Контакты с проверками
        isElementPresent(xpforContacts);
        wait.until(ExpectedConditions.elementToBeClickable(initObjectforWork(xpforContacts))).click();
        JUnitTest.userLogger.info("Переход в блок Контакты");
        shoot();
        JUnitTest.userLogger.info("Скриншот");
        Actions builder = new Actions(JUnitTest.driver);
        builder.moveToElement(initObjectforWork(xpforContacts)).build().perform();
        isTextPresent("lesha.soloshenko1@mail.ru");

        //Отправка файла
        JUnitTest.driver.navigate().to(urladd);
        wait.until(ExpectedConditions.urlToBe(urladd));
        JUnitTest.userLogger.info("Переход на сайт "+ urladd);
        attachFile(path, xpforSite);
        shoot();
        JUnitTest.userLogger.info("Файл прикреплен");

        JUnitTest.driver.navigate().back();
        wait.until(ExpectedConditions.urlToBe("https://e.mail.ru/addressbook"));
        Thread.sleep(1000);

        //Блок кода для работы с выпадающим списком
        JUnitTest.driver.navigate().to("http://bugscatcher.net/archives/4183");
        wait.until(ExpectedConditions.urlToBe("http://bugscatcher.net/archives/4183"));
        isElementPresent(xpForSelect);
        selectFromTheList(xpForSelect);
        Thread.sleep(1500);
        JUnitTest.driver.navigate().back();

        //Блок кода с проверками для блока Логотип
        isElementPresent(xpforLogo);
        getAttrributeandCssvalue(xpforLogo);
        wait.until(ExpectedConditions.elementToBeClickable(initObjectforWork(xpforLogo))).click();
        wait.until(ExpectedConditions.urlToBe("https://mail.ru/"));

        //Блок кода с проверками для блока Непрочитанные сообщения
        isElementPresent(xpforMess);
        wait.until(ExpectedConditions.elementToBeClickable(initObjectforWork(xpforMess))).click();
        shoot();
        //builder.dragAndDrop((JUnitTest.driver.findElement(By.xpath(xpforDragMess))),JUnitTest.driver.findElement(By.xpath(xpforDropInSpam))).build().perform();
        wait.until(ExpectedConditions.urlToBe("https://e.mail.ru/messages/inbox/"));

        //Блок кода с проверками для конкретного сообщения
        isElementPresent(xpforMessinAcc);
        wait.until(ExpectedConditions.elementToBeClickable(initObjectforWork(xpforMessinAcc))).click();
        switchBetweenWindow(JUnitTest.driver);
        shoot();
        Thread.sleep(1000);
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
        WebElement fileInput = JUnitTest.driver.findElement(By.xpath(pathXpath));
        fileInput.sendKeys(f.getAbsolutePath());
    }

    public WebElement initObjectforWork(String xpathForElement)
    {
        WebElement element = JUnitTest.driver.findElement(By.xpath(xpathForElement));
        return element;
    }

    public void selectFromTheList(String xpathForSelectedItem)
    {
        Select sel = new Select(JUnitTest.driver.findElement(By.xpath(xpathForSelectedItem)));
        sel.selectByIndex(1);
    }

    public void switchBetweenWindow(WebDriver driver)
    {
        String firstWindow = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpForSwitch)))).click();
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