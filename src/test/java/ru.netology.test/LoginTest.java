package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.UserData;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;
import ru.netology.utils.DataHelper;
import ru.netology.utils.DbUtils;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void testValidLogin() throws SQLException {
        UserData user = DataHelper.getValidUser();
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin(user.getLogin(), user.getPassword());

        String code = DbUtils.getVerificationCode(user.getLogin());
        DashboardPage dashboardPage = verificationPage.validVerify(code);
        dashboardPage.checkHeadingVisible();
    }

    @Test
    public void testBlockedAfterThreeWrongPasswords() {
        UserData user = DataHelper.getValidUser();

        for (int i = 0; i < 3; i++) {
            LoginPage loginPage = new LoginPage();
            loginPage.invalidLogin(user.getLogin(), "wrongPassword");
        }

        LoginPage loginPage = new LoginPage();
        loginPage.invalidLogin(user.getLogin(), user.getPassword());
    }
}