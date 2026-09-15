package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement loginField = $("[data-test-id='login'] input");
    private SelenideElement passwordField = $("[data-test-id='password'] input");
    private SelenideElement loginButton = $("[data-test-id='action-login']");

    public VerificationPage validLogin(String login, String password) {
        enterCredentialsAndClick(login, password);
        return new VerificationPage();
    }

    public void invalidLogin(String login, String password) {
        enterCredentialsAndClick(login, password);
    }

    private void enterCredentialsAndClick(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        loginButton.click();
    }
}