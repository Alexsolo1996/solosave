package com.example.tests;

import org.junit.Test;

/**
 * Created by user on 2/20/17.
 */
public class TestMethod4 extends BaseClassWithActions {

    String xpforMess = ".//*[@id='js-mailbox-avatar']";
    String xpforMessinAcc = "//div[contains(text(), 'рекомендует прочитать это письмо')]";
    String xpForSwitch = "//a[@href= \"http://vk.com/e_mail_ru\"]";
    String xpforDropInSpam = "//span[@class = \"b-nav__item__text\" ][contains(text(), 'Спам')]";
    String xpforDragMess = "//div[contains(text(), 'больше, чем почта')]";
    String xpForMessInbox = "https://e.mail.ru/messages/inbox/";

    @Test
    public void testMethodForWorkWithMessAndTab() throws Exception {
        clickOnElement(xpforMess);
        shoot();
        drag_drop(xpforDragMess,xpforDropInSpam);
        waitForUploadUrl(xpForMessInbox);
        clickOnElement(xpforMessinAcc);
        switchBetweenWindow(JUnitTest.driver, xpForSwitch);
        shoot();
        Thread.sleep(1000);
    }
}
