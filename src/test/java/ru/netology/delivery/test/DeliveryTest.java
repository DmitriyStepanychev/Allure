package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.*;

class DeliveryTest {

    @BeforeAll
    static void setupAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);

        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[data-test-id=date] input").sendKeys(firstMeetingDate);
        $("[data-test-id=name] input").setValue(validUser.getName());
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $x("//span[text() = 'Запланировать']").click();

        $("[data-test-id=success-notification] .notification__content")
                .shouldBe(Condition.visible,
                        Duration.ofSeconds(15))
                .shouldHave(Condition.text("Встреча успешно запланирована на " + firstMeetingDate));

        $("[data-test-id=date] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[data-test-id=date] input").sendKeys(secondMeetingDate);
        $x("//span[text() = 'Запланировать']").click();

        $("[data-test-id=replan-notification] .notification__content")
                .shouldBe(Condition.visible,
                        Duration.ofSeconds(15))
                .shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $x("//span[text() = 'Перепланировать']").click();

        $("[data-test-id=success-notification] .notification__content")
                .shouldBe(Condition.visible,
                        Duration.ofSeconds(15))
                .shouldHave(Condition.text("Встреча успешно запланирована на " + secondMeetingDate));
    }

    @Test
    @DisplayName("Should show error message when phone number field is empty")
    void shouldShowErrorMessageWhenPhoneNumberFieldIsEmpty() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);

        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[data-test-id=date] input").sendKeys(firstMeetingDate);
        $("[data-test-id=name] input").setValue(validUser.getName());
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $x("//span[text() = 'Запланировать']").click();

        $("[data-test-id=phone] .input__sub")
                .shouldBe(visible)
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    @DisplayName("Should successful plan and replan meeting when we use letter 'ё'")
    void shouldSuccessfulPlanAndReplanMeetingWhenWeUseSpecificLetter() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);

        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[data-test-id=date] input").sendKeys(firstMeetingDate);
        $("[data-test-id=name] input").setValue(DataGenerator.generateSpecificName("ru"));
        $("[data-test-id=phone] input").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $x("//span[text() = 'Запланировать']").click();

        $("[data-test-id=success-notification] .notification__content")
                .shouldBe(Condition.visible,
                        Duration.ofSeconds(15))
                .shouldHave(Condition.text("Встреча успешно запланирована на " + firstMeetingDate));

        $("[data-test-id=date] input").sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        $("[data-test-id=date] input").sendKeys(secondMeetingDate);
        $x("//span[text() = 'Запланировать']").click();

        $("[data-test-id=replan-notification] .notification__content")
                .shouldBe(Condition.visible,
                        Duration.ofSeconds(15))
                .shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $x("//span[text() = 'Перепланировать']").click();

        $("[data-test-id=success-notification] .notification__content")
                .shouldBe(Condition.visible,
                        Duration.ofSeconds(15))
                .shouldHave(Condition.text("Встреча успешно запланирована на " + secondMeetingDate));
    }
}
