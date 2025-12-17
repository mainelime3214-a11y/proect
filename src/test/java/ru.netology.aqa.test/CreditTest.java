package ru.netology.aqa.test;

import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Selenide.open;

public class CreditTest {
    MainPage page;

    @BeforeEach
    void setup() {
        page = open("http://localhost:8080", MainPage.class);
    }

    @Test
    @DisplayName("Успешная заявка на кредит")
    void shouldSuccessCredit() {
        page.openCreditPage();
        page.fillForm("4444 4444 4444 4441", "12", "25", "IVAN IVANOV", "123");
        page.waitSuccess();
    }
}