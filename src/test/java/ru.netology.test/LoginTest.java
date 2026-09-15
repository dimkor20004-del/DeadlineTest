package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.UserData;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;
import ru.netology.utils.DataHelper;
import ru.netology.utils.DbUtils;

import java.sql.SQLException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
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
        verificationPage.validVerify(code);

        $("h2").shouldBe(Condition.visible, Duration.ofSeconds(20));
    }

    @Test
    public void testBlockedAfterThreeWrongPasswords() {
        UserData user = DataHelper.getValidUser();

        for (int i = 0; i < 3; i++) {
            LoginPage loginPage = new LoginPage();
            loginPage.invalidLogin(user.getLogin(), "wrongPassword");
            $("[data-test-id='error-notification']")
                    .shouldBe(Condition.visible, Duration.ofSeconds(10));
        }

        LoginPage loginPage = new LoginPage();
        loginPage.invalidLogin(user.getLogin(), user.getPassword());
        $("[data-test-id='error-notification']")
                .shouldBe(Condition.visible, Duration.ofSeconds(10));
    }
}