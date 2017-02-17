package com.example.tests;

/**
 * Created by user on 1/30/17.
 */
public class CommentFunc
{
    /*public void isTextPresent2(String text)
    {
        if(JUnitTest.driver.findElement(By.xpath("//html")).getText().contains(text))
            System.out.println("Переданный текст(method2) присутствует на данной странице");
        else Assert.fail("Переданный текст(method2) отсутствует на данной странице");
    }*/

    /*public void isElementClickable(WebElement clicable)
    {
        if(clicable.isDisplayed() && clicable.isEnabled())
           System.out.println("Элемент доступен для нажатия");
        else System.out.println("Элемент не доступен для нажатия");
    }*/
    /*public void mouseOver(WebElement element)
    {
        String code = "var fireOnThis = arguments[0];"
                + "var evObj = document.createEvent('MouseEvents');"
                + "evObj.initEvent( 'mouseover', true, true );"
                + "fireOnThis.dispatchEvent(evObj);";
        ((JavascriptExecutor) JUnitTest.driver).executeScript(code, element);
    }

    public void clickOnInvisibleElement(WebElement element)
    {
        String script = "var object = arguments[0];"
                + "var theEvent = document.createEvent(\"MouseEvent\");"
                + "theEvent.initMouseEvent(\"click\", true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
                + "object.dispatchEvent(theEvent);";

        ((JavascriptExecutor) JUnitTest.driver).executeScript(script, element);
    }*/


        /*try
        {
            Assert.assertTrue("Не найден локатор -" + locator,!JUnitTest.driver.findElements(By.id(locator)).isEmpty());
        }
        catch (AssertionError e)
        {
            JUnitTest.userLogger.error(e);
            Assert.fail();
        }*/

}
