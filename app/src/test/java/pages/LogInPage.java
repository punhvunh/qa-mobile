package pages;

import static org.junit.Assert.assertEquals;
import static locators.LogInPageLocators.confirmButton;
import static locators.LogInPageLocators.headerLogInAlfaTest;
import static locators.LogInPageLocators.headerLogInAlfaTestExecuted;
import static locators.LogInPageLocators.incorrectDataEnteredError;
import static locators.LogInPageLocators.incorrectDataEnteredErrorUnderFieldLogin;
import static locators.LogInPageLocators.loginField;
import static locators.LogInPageLocators.passwordField;
import static locators.LogInPageLocators.showPasswordButton;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import io.qameta.allure.Step;

public class LogInPage extends BasePage {

    @Step("Проверям название заголовка")
    public LogInPage checksTheHeaderName(String headerName) {
        elementIsVisible(headerLogInAlfaTest);
        String actualHeader = getsTextAttributeFromElement(headerLogInAlfaTest);
        assertEquals(actualHeader, headerName);
        return this;
    }

    @Step("Проверяем лэйблы полей {fieldName}")
    public LogInPage checksFieldLabels(String fieldName, String value) {
        switch (fieldName) {
            case "Логин" -> {
                elementIsVisible(loginField);
                String actualLabel = getsTextAttributeFromElement(loginField);
                assertEquals(actualLabel, value);
            }
            case "Пароль" -> {
                elementIsVisible(passwordField);
                String actualLabel = getsTextAttributeFromElement(passwordField);
                assertEquals(actualLabel, value);
            }
        }
        return this;
    }

    @Step("Проверям название кнопки")
    public LogInPage checksButtonName(String buttonName) {
        elementIsVisible(confirmButton);
        String actualName = getsTextAttributeFromElement(confirmButton);
        assertEquals(actualName, buttonName);
        return this;
    }

    @Step("Заполняем поле {fieldName}")
    public LogInPage fillsInTheField(String fieldName, String value) {
        switch (fieldName) {
            case "Логин" -> {
                elementIsVisible(loginField);
                typesTextIntoField(loginField, value);
            }
            case "Пароль" -> {
                elementIsVisible(passwordField);
                typesTextIntoField(passwordField, value);
            }
        }
        return this;
    }

    @Step("Заполняем поле {fieldName} посимвольно")
    public LogInPage fillsInTheFieldCharacterByCharacter(String fieldName, String value) {
        switch (fieldName) {
            case "Логин" -> {
                elementIsVisible(loginField).click();
                fillsFieldCharacterByCharacter(loginField, value);
            }
            case "Пароль" -> {
                elementIsVisible(passwordField).click();
                fillsFieldCharacterByCharacter(passwordField, value);
            }
        }
        return this;
    }

    @Step("Проверяем что поле заполнено")
    public LogInPage checksThatFillIsNotEmpty(String text, SelenideElement fieldElement) {
        String actualText = getsTextAttributeFromElement(fieldElement);
        assertEquals(actualText, text);
        return this;
    }

    public LogInPage checksThatFieldIsEmpty(SelenideElement fieldElement, SelenideElement placeholder) {
        elementIsVisible(fieldElement).click();
        String actualText = getsTextAttributeFromElement(placeholder);
        assertEquals(actualText, "");
        return this;
    }

    @Step("Кликаем на кнопку вход")
    public LogInPage clicksOnLogInButton() {
        elementIsVisible(confirmButton).click();
        return this;
    }

    @Step("Не видим кнопку вход")
    public LogInPage doesNotSeeLogInButton() {
        elementIsNotVisible(confirmButton);
        return this;
    }

    @Step("Кликаем на кнопку Показать пароль")
    public LogInPage clicksOnShowPasswordButton(){
        elementIsVisible(showPasswordButton).click();
        return this;
    }

    @Step("Видим ошибку после авторизации с неправильным данными")
    public LogInPage seesErrorAfterTryingToLogInUsingIncorrectData(String errorName) {
        elementIsVisible(incorrectDataEnteredError);
        String actualError = getsTextAttributeFromElement(incorrectDataEnteredError);
        assertEquals(actualError, errorName);
        return this;
    }

    @Step("Не видим ошибку после авторизации с правильным данными под полем")
    public LogInPage doesNotSeeErrorAfterTryingToLogInUsingCorrectDataUnderField() {
        try {
            elementIsNotVisible(incorrectDataEnteredErrorUnderFieldLogin);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return this;
    }

    @Step("Видим ошибку после авторизации с неправильным данными")
    public LogInPage seesErrorAfterTryingToLogInUsingIncorrectDataUnderField(String errorName) {
        try {
            elementIsVisible(incorrectDataEnteredErrorUnderFieldLogin);
            String actualError = getsTextAttributeFromElement(incorrectDataEnteredErrorUnderFieldLogin);
            assertEquals(actualError, errorName);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return this;
    }

    @Step("Кликаем на кнопку вход и видим ошибку")
    public LogInPage clicksOnLogInButtonAndSeesError(String errorName) {
        clicksOnLogInButton();
        Selenide.sleep(2000);
        seesErrorAfterTryingToLogInUsingIncorrectData(errorName);
        return this;
    }

    @Step("Проверяем название заголовка после авторизации")
    public LogInPage checksFieldLabelsAfterLogIn(String headerName) {
        elementIsVisible(headerLogInAlfaTestExecuted);
        String actualHeader = getsTextAttributeFromElement(headerLogInAlfaTestExecuted);
        assertEquals(actualHeader, headerName);
        return this;
    }
}
