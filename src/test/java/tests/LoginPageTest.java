package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginPageTest extends BaseTest {
    @Test
    public void testLoginButtonDisabledWhenFieldAreEmpty() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.verifyAndPrintError("Empty Fields"), "Error message should appear when trying to login with empty fields");
    }

    @Test
    public void testPasswordMaskedbutton() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterPassword("@dummypass");
        Assert.assertTrue(loginPage.isPasswordMasked(), "Password should be masked initially");

        loginPage.togglePasswordVisibility();
        Assert.assertFalse(loginPage.isPasswordMasked(), "Password should be visible after clicking eye icon");
    }

    @Test
    public void testInvalidLoginShowErrorMsg() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserId("invalid@example.com");
        loginPage.enterPassword("wrongpass");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.verifyAndPrintError("Invalid Login"), "Error message should appear for invalid login");
    }

    @Test
    public void testPresenceOfLoginElements() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.areElementsVisible(), "Login page elements should be visible");
    }
}
