package com.example.tests;

import org.junit.Test;

/**
 * Created by user on 2/20/17.
 */
public class TestMethod3 extends BaseClassWithActions {

    String xpforSite = "//input[@type = \"file\"]";
    String path = "/home/user/screensh/log.txt";
    String urladd = "http://bugscatcher.net/archives/540";
    String urladd1 = "http://bugscatcher.net/archives/4183";
    String xpForSelect = ".//*[@id=':0.targetLanguage']/select";

    @Test
    public void testMethodForWorkInAcc() throws Exception {
        JUnitTest.driver.navigate().to(urladd);
        waitForUploadUrl(urladd);
        JUnitTest.userLogger.info("Переход на сайт "+ urladd);
        attachFile(path, xpforSite);
        JUnitTest.driver.navigate().to(urladd1);
        waitForUploadUrl(urladd1);
        JUnitTest.userLogger.info("Переход на сайт "+ urladd1);
        selectFromTheList(xpForSelect);
    }
}
