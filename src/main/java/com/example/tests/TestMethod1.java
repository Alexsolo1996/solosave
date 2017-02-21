package com.example.tests;
import org.junit.*;

public class TestMethod1 extends BaseClassWithActions{

    String pass = "mailbox__password";
    String log = "mailbox__login";
    String butt = "mailbox__auth__button";

    @Test
    public void testMethodForLoginInAcc() throws Exception
    {
        JUnitTest.driver.get(JUnitTest.baseUrl + "/");
        JUnitTest.userLogger.info("Сайт открыт");
        initObjectforWorkId(pass);
        initObjectforWorkId(log);
        initObjectforWorkId(butt);
        loginInAcc(pass,log,butt);
    }
}