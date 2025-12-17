package ru.netology.aqa.test;

import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private SelenideElement buyButton = $(byText("Купить"));
    private SelenideElement creditButton = $(byText("Купить в кредит"));
    private SelenideElement cardNumber = $(".input__control[placeholder='0000 0000 0000 0000']");
    private SelenideElement month = $(".input__control[placeholder='08']");
    private SelenideElement year = $(".input__control[placeholder='22']");
    private SelenideElement holder = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement cvc = $(".input__control[placeholder='999']");
    private SelenideElement continueButton = $(byText("Продолжить"));

    private SelenideElement successNotification = $(".notification_status_ok");
    private SelenideElement errorNotification = $(".notification_status_error");

    public void openPaymentPage() { buyButton.click(); }
    public void openCreditPage() { creditButton.click(); }

    public void fillForm(String card, String m, String y, String name, String code) {
        cardNumber.setValue(card);
        month.setValue(m);
        year.setValue(y);
        holder.setValue(name);
        cvc.setValue(code);
        continueButton.click();
    }

    public void waitSuccess() { successNotification.shouldBe(visible, Duration.ofSeconds(15)); }
    public void waitError() { errorNotification.shouldBe(visible, Duration.ofSeconds(15)); }
}
