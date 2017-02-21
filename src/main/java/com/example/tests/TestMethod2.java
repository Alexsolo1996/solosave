package com.example.tests;

import org.junit.Test;

public class TestMethod2 extends BaseClassWithActions {

    String xpforContacts = "//a[@bem-id = 108]";
    String xpforLogo = "//a[@href = \"//mail.ru/\"]";
    String xpForSite = "https://e.mail.ru/messages/inbox/?back=1";
    String xpforMess = ".//*[@id='js-mailbox-avatar']";
    String xpforMessinAcc = "//div[contains(text(), 'рекомендует прочитать это письмо')]";
    String xpForSwitch = "//a[@href= \"http://vk.com/e_mail_ru\"]";
    String xpforDropInSpam = "//span[@class = \"b-nav__item__text\" ][contains(text(), 'Спам')]";
    String xpforDragMess = "//div[contains(text(), 'больше, чем почта')]";
    String xpForMessInbox = "https://e.mail.ru/messages/inbox/";

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
        Thread.sleep(2000);
        // }

        // @Test
        // public void testMethodForWorkWithMessAndTab() throws Exception {
        clickOnElement(xpforMess);
        shoot();
        drag_drop(xpforDragMess, xpforDropInSpam);
        waitForUploadUrl(xpForMessInbox);
        clickOnElement(xpforMessinAcc);
        switchBetweenWindow(JUnitTest.driver, xpForSwitch);
        shoot();
        Thread.sleep(1000);
        // }
    }
}