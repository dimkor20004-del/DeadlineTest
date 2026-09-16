package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.UserData;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;
import ru.netology.utils.DataHelper;
import ru.netology.utils.DbUtils;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    public static void cleanUp() {
        DbUtils.cleanDatabase();
    }

    @Test
    public void testValidLogin() {
        UserData user = DataHelper.getValidUser();
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin(user.getLogin(), user.getPassword());

        String code = DbUtils.getVerificationCode(user.getLogin());
        DashboardPage dashboardPage = verificationPage.validVerify(code);
        dashboardPage.checkHeadingVisible();
    }

    @Test
    public void testInvalidLogin() {
        UserData user = DataHelper.getValidUser();
        String wrongPassword = DataHelper.getRandomPassword();

        LoginPage loginPage = new LoginPage();
        loginPage.invalidLogin(user.getLogin(), wrongPassword);
        loginPage.checkLoginError();
    }
}