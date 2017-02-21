package com.example.tests;

import org.junit.Test;

public class TestMethod2 extends BaseClassWithActions{

    String xpforContacts = "//a[@bem-id = 108]";
    String xpforLogo = "//a[@href = \"//mail.ru/\"]";
    String xpForSite = "https://e.mail.ru/messages/inbox/?back=1";

    @Test
    public void testMethodForWorkInAcc() throws Exception {

        JUnitTest.driver.navigate().to(xpForSite);
        waitForUploadUrl(xpForSite);
        clickOnElement(xpforContacts);
        JUnitTest.userLogger.info("Переход в блок Контакты");
        shoot();
        JUnitTest.userLogger.info("Скриншот");
        moveToElem(xpforContacts);
        isTextPresent("lesha.soloshenko3@mail.ru");
        getAttrributeandCssvalue(xpforLogo);
        clickOnElement(xpforLogo);
        waitForUploadUrl("https://mail.ru/");
    }
}