package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    private SelenideElement heading = $("h2");

    public DashboardPage checkHeadingVisible() {
        heading.shouldBe(Condition.visible, Duration.ofSeconds(20));
        return this;
    }
}