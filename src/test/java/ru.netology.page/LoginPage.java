package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement loginField = $("[data-test-id='login'] input");
    private SelenideElement passwordField = $("[data-test-id='password'] input");
    private SelenideElement loginButton = $("[data-test-id='action-login']");
    private SelenideElement errorNotification = $("[data-test-id='error-notification']");

    public VerificationPage validLogin(String login, String password) {
        enterCredentialsAndClick(login, password);
        return new VerificationPage();
    }

    public void invalidLogin(String login, String password) {
        enterCredentialsAndClick(login, password);
    }

    public void checkLoginError() {
        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(10));
        errorNotification.shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль"));
    }

    private void enterCredentialsAndClick(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        loginButton.click();
    }
}