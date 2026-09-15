package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    private SelenideElement heading = $("h2");

    public SelenideElement getHeading() {
        return heading;
    }
}