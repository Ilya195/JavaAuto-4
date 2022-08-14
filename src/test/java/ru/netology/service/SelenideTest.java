package ru.netology.service;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import java.time.format.DateTimeFormatter;


public class SelenideTest {

    @Test
    void shouldOrderCardDelivery() {
        String date = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Екатеринбург");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id=name] input").setValue("Котов Алексей");
        $("[data-test-id=phone] input").setValue("+77777777777");
        $("[data-test-id=agreement]").click();
        $$(By.className("button__text")).last().click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15));
        $x(".//div[@class='notification__content']").should(exactText("Встреча успешно забронирована на " + date));
    }
}
