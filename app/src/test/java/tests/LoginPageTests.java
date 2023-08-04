package tests;

import static constants.LoginPageConstants.CORRECT_LOGIN;
import static constants.LoginPageConstants.CORRECT_PASSWORD;
import static constants.LoginPageConstants.HEADER_LOG_IN_ALFA_TEST;
import static constants.LoginPageConstants.HEADER_LOG_IN_ALFA_TEST_EXECUTED;
import static constants.LoginPageConstants.INCORRECT_DATA_ENTERED_ERROR;
import static constants.LoginPageConstants.INCORRECT_LOGIN;
import static constants.LoginPageConstants.INCORRECT_PASSWORD;
import static constants.LoginPageConstants.INVALID_LOGIN_DATA_WITH_DIFFERENT_CHARACTERS;
import static constants.LoginPageConstants.INVALID_VALUE;
import static constants.LoginPageConstants.LOGIN;
import static constants.LoginPageConstants.LOGIN_FIELD;
import static constants.LoginPageConstants.MAX_CHARACTERS;
import static constants.LoginPageConstants.PASSWORD_FIELD;
import static constants.LoginPageConstants.VALID_LOGIN_DATA_WITH_DIFFERENT_CHARACTERS;
import static locators.LogInPageLocators.fieldPlaceholder;
import static locators.LogInPageLocators.loginField;
import static locators.LogInPageLocators.passwordField;

import com.codeborne.selenide.Selenide;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import io.qameta.allure.Description;
import pages.LogInPage;


public class LoginPageTests extends BaseTest {

    private static LogInPage loginPage;

    @BeforeAll
    public static void init() {
        loginPage = new LogInPage();
    }

    @Description("Проверям название заголовка")
    @Test
    void testChecksTheHeaderName() {
        loginPage.checksTheHeaderName(HEADER_LOG_IN_ALFA_TEST);
    }


    @Description("Проверяем лэйблы полей")
    @Test
    void testChecksFieldLabels() {
        loginPage.checksFieldLabels(LOGIN_FIELD, LOGIN_FIELD);
        loginPage.checksFieldLabels(PASSWORD_FIELD, PASSWORD_FIELD);
    }

    @Description("Проверям название кнопки")
    @Test
    void testChecksButtonName() {
        loginPage.checksButtonName(LOGIN);
    }

    @Description("Проверям отсутсвие значений по умолчанию в полях логин и пароль")
    @Test
    void testChecksThatFieldAreEmptyAfterClickingOnThem() {
        loginPage.checksThatFieldIsEmpty(loginField, fieldPlaceholder);
        loginPage.checksThatFieldIsEmpty(passwordField, fieldPlaceholder);
    }

    @Description("Авторизируемcя в приложении используя логин и пароль")
    @ParameterizedTest
    @CsvSource("Login, Password")
    void testLogInToTheApplicationUsingUsernameAndPassword(String login, String password) {
        loginPage.fillsInTheFieldCharacterByCharacter(LOGIN_FIELD, login);
        loginPage.fillsInTheFieldCharacterByCharacter(PASSWORD_FIELD, password);
        loginPage.clicksOnLogInButton();
        loginPage.doesNotSeeLogInButton();
    }

    @Description("Пробуем авторизоваться используя неправильный логин")
    @Test
    void testTriesToLogInWithTheWrongLogin() {
        loginPage.fillsInTheField(LOGIN_FIELD, INCORRECT_LOGIN);
        loginPage.fillsInTheField(PASSWORD_FIELD, CORRECT_PASSWORD);
        loginPage.clicksOnLogInButtonAndSeesError(INCORRECT_DATA_ENTERED_ERROR);
    }

    @Description("Пробуем авторизоваться используя неправильный пароль")
    @Test
    void testTriesToLogInWithTheWrongPassword() {
        loginPage.fillsInTheField(LOGIN_FIELD, CORRECT_LOGIN);
        loginPage.fillsInTheField(PASSWORD_FIELD, INCORRECT_PASSWORD);
        loginPage.clicksOnLogInButtonAndSeesError(INCORRECT_DATA_ENTERED_ERROR);
    }


    @Description("Ввод корректных символов в поле Логин")
    @Test
    void testEntersValidCharactersInLoginField() {
        loginPage.fillsInTheField(LOGIN_FIELD, VALID_LOGIN_DATA_WITH_DIFFERENT_CHARACTERS);
        loginPage.checksThatFillIsNotEmpty(VALID_LOGIN_DATA_WITH_DIFFERENT_CHARACTERS, loginField);
        loginPage.doesNotSeeErrorAfterTryingToLogInUsingCorrectDataUnderField();
    }

    @Description("Ввод максимально допустимого количества символов в поле Логин")
    @Test
    void testEntersMaxCharacterLimitInLoginField() {
        loginPage.checksAmountOfCharacters(MAX_CHARACTERS);
        loginPage.fillsInTheField(LOGIN_FIELD, MAX_CHARACTERS);
        loginPage.checksThatFillIsNotEmpty(MAX_CHARACTERS, loginField);
        loginPage.doesNotSeeErrorAfterTryingToLogInUsingCorrectDataUnderField();
    }

    @Description("Ввод некорректных символов в поле Логин")
    @Test
    void testEntersInvalidCharactersInLoginField() {
        loginPage.fillsInTheField(LOGIN_FIELD, INVALID_LOGIN_DATA_WITH_DIFFERENT_CHARACTERS);
        loginPage.checksThatFillIsNotEmpty(INVALID_LOGIN_DATA_WITH_DIFFERENT_CHARACTERS, loginField);
        loginPage.seesErrorAfterTryingToLogInUsingIncorrectDataUnderField(INVALID_VALUE);
    }

    @Description("Ввод превышающего максимальное количество символов в поле Логин")
    @Test
    void testEntersMoreThanTheMaximumNumberOfCharactersInTheLoginField() {
        loginPage.checksAmountOfCharacters(MAX_CHARACTERS + "X");
        loginPage.fillsInTheField(LOGIN_FIELD, MAX_CHARACTERS + "X");
        loginPage.checksThatFillIsNotEmpty(MAX_CHARACTERS + "X", loginField);
        loginPage.seesErrorAfterTryingToLogInUsingIncorrectDataUnderField(INVALID_VALUE);
    }

    @Description("Ввод максимально допустимого количества символов в поле Пароль")
    @Test
    void testEntersMaxCharacterLimitInPasswordField() {
        loginPage.fillsInTheField(PASSWORD_FIELD, MAX_CHARACTERS);
        loginPage.checksThatFillIsNotEmpty(MAX_CHARACTERS, passwordField);
        loginPage.doesNotSeeErrorAfterTryingToLogInUsingCorrectDataUnderField();
    }

    @Description("Ввод превышающего максимальное количество символов в поле Логин")
    @Test
    void testEntersMoreThanTheMaximumNumberOfCharactersInThePasswordField() {
        loginPage.fillsInTheField(PASSWORD_FIELD, MAX_CHARACTERS + "X");
        loginPage.checksThatFillIsNotEmpty(MAX_CHARACTERS + "X", passwordField);
        loginPage.seesErrorAfterTryingToLogInUsingIncorrectDataUnderField(INVALID_VALUE);
    }

    @Description("Проверяем видимость пароля после нажатия на кнопку Показать пароль")
    @Test
    void testCheckTheVisibilityOfThePasswordAfterClickingTheShowPasswordButton() {
        loginPage.fillsInTheField(PASSWORD_FIELD, CORRECT_PASSWORD);
        loginPage.clicksOnShowPasswordButton();
    }

    @Description("Проверяем не видимость пароля после повторного нажатия на кнопку Показать пароль")
    @Test
    void testCheckTheInvisibilityOfThePasswordAfterRepeatClickingTheShowPasswordButton() {
        testCheckTheVisibilityOfThePasswordAfterClickingTheShowPasswordButton();
        loginPage.clicksOnShowPasswordButton();
    }

    @Description("Проверям название заголовка после авторизации")
    @ParameterizedTest
    @CsvSource("Login, Password")
    void testChecksTheHeaderNameAfterLogIn(String login, String password) {
        testLogInToTheApplicationUsingUsernameAndPassword(login, password);
        Selenide.sleep(2000);
        loginPage.checksFieldLabelsAfterLogIn(HEADER_LOG_IN_ALFA_TEST_EXECUTED);
    }
}
