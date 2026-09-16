package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    private SelenideElement heading = $("[data-test-id='dashboard']");

    public DashboardPage checkHeadingVisible() {
        heading.shouldBe(Condition.visible, Duration.ofSeconds(20));
        heading.shouldHave(Condition.text("Личный кабинет"));
        return this;
    }
}