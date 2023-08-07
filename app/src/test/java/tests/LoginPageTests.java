package tests;

import static locators.LogInPageLocators.fieldPlaceholder;
import static locators.LogInPageLocators.loginField;
import static locators.LogInPageLocators.passwordField;

import com.codeborne.selenide.Selenide;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import constants.LoginPageConstants;
import io.qameta.allure.Description;
import locators.pages.LogInPage;


public class LoginPageTests extends BaseTest {

    private static LogInPage loginPage;

    @BeforeAll
    public static void init() {
        loginPage = new LogInPage();
    }

    @Description("Проверям название заголовка")
    @Test
    void testChecksTheHeaderName() {
        loginPage.checksTheHeaderName(LoginPageConstants.HEADER_LOG_IN_ALFA_TEST);
    }


    @Description("Проверяем лэйблы полей")
    @Test
    void testChecksFieldLabels() {
        loginPage.checksFieldLabels(LoginPageConstants.LOGIN_FIELD, LoginPageConstants.LOGIN_FIELD);
        loginPage.checksFieldLabels(LoginPageConstants.PASSWORD_FIELD, LoginPageConstants.PASSWORD_FIELD);
    }

    @Description("Проверям название кнопки")
    @Test
    void testChecksButtonName() {
        loginPage.checksButtonName(LoginPageConstants.LOGIN);
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
        loginPage.fillsInTheFieldCharacterByCharacter(LoginPageConstants.LOGIN_FIELD, login);
        loginPage.fillsInTheFieldCharacterByCharacter(LoginPageConstants.PASSWORD_FIELD, password);
        loginPage.clicksOnLogInButton();
        loginPage.doesNotSeeLogInButton();
    }

    @Description("Пробуем авторизоваться используя неправильный логин")
    @Test
    void testTriesToLogInWithTheWrongLogin() {
        loginPage.fillsInTheField(LoginPageConstants.LOGIN_FIELD, LoginPageConstants.INCORRECT_LOGIN);
        loginPage.fillsInTheField(LoginPageConstants.PASSWORD_FIELD, LoginPageConstants.CORRECT_PASSWORD);
        loginPage.clicksOnLogInButtonAndSeesError(LoginPageConstants.INCORRECT_DATA_ENTERED_ERROR);
    }

    @Description("Пробуем авторизоваться используя неправильный пароль")
    @Test
    void testTriesToLogInWithTheWrongPassword() {
        loginPage.fillsInTheField(LoginPageConstants.LOGIN_FIELD, LoginPageConstants.CORRECT_LOGIN);
        loginPage.fillsInTheField(LoginPageConstants.PASSWORD_FIELD, LoginPageConstants.INCORRECT_PASSWORD);
        loginPage.clicksOnLogInButtonAndSeesError(LoginPageConstants.INCORRECT_DATA_ENTERED_ERROR);
    }


    @Description("Ввод корректных символов в поле Логин")
    @Test
    void testEntersValidCharactersInLoginField() {
        loginPage.fillsInTheField(LoginPageConstants.LOGIN_FIELD, LoginPageConstants.VALID_LOGIN_DATA_WITH_DIFFERENT_CHARACTERS);
        loginPage.checksThatFillIsNotEmpty(LoginPageConstants.VALID_LOGIN_DATA_WITH_DIFFERENT_CHARACTERS, loginField);
        loginPage.doesNotSeeErrorAfterTryingToLogInUsingCorrectDataUnderField();
    }

    @Description("Ввод максимально допустимого количества символов в поле Логин")
    @Test
    void testEntersMaxCharacterLimitInLoginField() {
        loginPage.checksAmountOfCharacters(LoginPageConstants.MAX_CHARACTERS);
        loginPage.fillsInTheField(LoginPageConstants.LOGIN_FIELD, LoginPageConstants.MAX_CHARACTERS);
        loginPage.checksThatFillIsNotEmpty(LoginPageConstants.MAX_CHARACTERS, loginField);
        loginPage.doesNotSeeErrorAfterTryingToLogInUsingCorrectDataUnderField();
    }

    @Description("Ввод некорректных символов в поле Логин")
    @Test
    void testEntersInvalidCharactersInLoginField() {
        loginPage.fillsInTheField(LoginPageConstants.LOGIN_FIELD, LoginPageConstants.INVALID_LOGIN_DATA_WITH_DIFFERENT_CHARACTERS);
        loginPage.checksThatFillIsNotEmpty(LoginPageConstants.INVALID_LOGIN_DATA_WITH_DIFFERENT_CHARACTERS, loginField);
        loginPage.seesErrorAfterTryingToLogInUsingIncorrectDataUnderField(LoginPageConstants.INVALID_VALUE);
    }

    @Description("Ввод превышающего максимальное количество символов в поле Логин")
    @Test
    void testEntersMoreThanTheMaximumNumberOfCharactersInTheLoginField() {
        loginPage.checksAmountOfCharacters(LoginPageConstants.MAX_CHARACTERS + "X");
        loginPage.fillsInTheField(LoginPageConstants.LOGIN_FIELD, LoginPageConstants.MAX_CHARACTERS + "X");
        loginPage.checksThatFillIsNotEmpty(LoginPageConstants.MAX_CHARACTERS + "X", loginField);
        loginPage.seesErrorAfterTryingToLogInUsingIncorrectDataUnderField(LoginPageConstants.INVALID_VALUE);
    }

    @Description("Ввод максимально допустимого количества символов в поле Пароль")
    @Test
    void testEntersMaxCharacterLimitInPasswordField() {
        loginPage.fillsInTheField(LoginPageConstants.PASSWORD_FIELD, LoginPageConstants.MAX_CHARACTERS);
        loginPage.checksThatFillIsNotEmpty(LoginPageConstants.MAX_CHARACTERS, passwordField);
        loginPage.doesNotSeeErrorAfterTryingToLogInUsingCorrectDataUnderField();
    }

    @Description("Ввод превышающего максимальное количество символов в поле Логин")
    @Test
    void testEntersMoreThanTheMaximumNumberOfCharactersInThePasswordField() {
        loginPage.fillsInTheField(LoginPageConstants.PASSWORD_FIELD, LoginPageConstants.MAX_CHARACTERS + "X");
        loginPage.checksThatFillIsNotEmpty(LoginPageConstants.MAX_CHARACTERS + "X", passwordField);
        loginPage.seesErrorAfterTryingToLogInUsingIncorrectDataUnderField(LoginPageConstants.INVALID_VALUE);
    }

    @Description("Проверяем видимость пароля после нажатия на кнопку Показать пароль")
    @Test
    void testCheckTheVisibilityOfThePasswordAfterClickingTheShowPasswordButton() {
        loginPage.fillsInTheField(LoginPageConstants.PASSWORD_FIELD, LoginPageConstants.CORRECT_PASSWORD);
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
        loginPage.checksFieldLabelsAfterLogIn(LoginPageConstants.HEADER_LOG_IN_ALFA_TEST_EXECUTED);
    }
}
