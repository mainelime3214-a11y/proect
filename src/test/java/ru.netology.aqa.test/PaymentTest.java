package ru.netology.aqa.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Selenide.open;

public class PaymentTest {
    MainPage page;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        // Открываем страницу перед каждым тестом
        page = open("http://localhost:8080", MainPage.class);
    }

    @Test
    @DisplayName("Успешная оплата одобренной картой")
    void shouldSuccessWithApprovedCard() {
        page.openPaymentPage();
        // Используем номер из задания: 1111 2222 3333 4444
        page.fillForm("1111 2222 3333 4444", "12", "26", "IVAN IVANOV", "123");
        page.waitSuccess();
    }

    @Test
    @DisplayName("Отказ при оплате отклоненной картой")
    void shouldFailWithDeclinedCard() {
        page.openPaymentPage();
        // Используем номер из задания: 5555 6666 7777 8888
        page.fillForm("5555 6666 7777 8888", "12", "26", "IVAN IVANOV", "123");
        page.waitError();
    }
}
