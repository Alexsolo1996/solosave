package com.example.tests;
import org.junit.*;
import org.openqa.selenium.*;

public class TestMethod1 {

    String pass = "mailbox__password";
    String log = "mailbox__login";
    String butt = "mailbox__auth__button";

    @Test
    public void testMethodForLoginInAcc() throws Exception
    {
        JUnitTest.driver.get(JUnitTest.baseUrl + "/");
        JUnitTest.userLogger.info("Сайт открыт");
        AssertionForCheckElementisPresent(pass);
        JUnitTest.userLogger.info("Поле " + pass+ " для ввода пароля найдено");
        AssertionForCheckElementisPresent(log);
        JUnitTest.userLogger.info("Поле " + log+ " для ввода логина найдено");
        AssertionForCheckElementisPresent(butt);
        JUnitTest.userLogger.info("Кнока " + butt+ " для входа в акк");
        dateForLoginAndLoginInAcc(pass,log,butt);
        JUnitTest.userLogger.info("Вход в кабинет осуществлен успешно");
    }

    public void AssertionForCheckElementisPresent(String locator)
    {
        try
        {
            Assert.assertTrue("Не найден локатор -" + locator,!JUnitTest.driver.findElements(By.id(locator)).isEmpty());
        }
        catch (AssertionError e)
        {
            JUnitTest.userLogger.error(e);
            Assert.fail();
        }
    }

    public void isElementClickable(WebElement clicable)
    {
        if(clicable.isDisplayed() && clicable.isEnabled())
            JUnitTest.userLogger.info("Элемент доступ для нажатия -"+ clicable);
        else JUnitTest.userLogger.error("Элемент не доступен для нажатия");
    }

    public void loginInAcc(WebElement pass, String password, WebElement log, String login, WebElement auth)
    {
        pass.sendKeys(password);
        try {
            Assert.assertEquals("qwerty1996", password);
        }
        catch (AssertionError e){
            JUnitTest.userLogger.error(e);
        }
        log.sendKeys(login);
        try {
            Assert.assertEquals("lesha.soloshenko3",login);
        }
        catch (AssertionError e){
            JUnitTest.userLogger.error(e);
        }
        auth.click();
    }
    public void dateForLoginAndLoginInAcc(String passw, String logi, String buttn)
    {
        WebElement password = JUnitTest.driver.findElement(By.id(passw));
        WebElement login = JUnitTest.driver.findElement(By.id(logi));
        WebElement auth = JUnitTest.driver.findElement(By.id(buttn));
        isElementClickable(auth);
        loginInAcc(password,"qwerty1996",login,"lesha.soloshenko3",auth);
    }
}