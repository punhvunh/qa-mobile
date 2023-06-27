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
import pages.LogInPage;


public class LoginPageTests extends BaseTest {

    private static LogInPage loginPage;

    @BeforeAll
    public static void init() {
        loginPage = new LogInPage();
    }

    @Description("Проверям название заголовка")
    @Test
    public void testChecksTheHeaderName() {
        loginPage.checksTheHeaderName(LoginPageConstants.HEADER_LOG_IN_ALFA_TEST.getValue());
    }


    @Description("Проверяем лэйблы полей")
    @Test
    public void testChecksFieldLabels() {
        loginPage.checksFieldLabels(LoginPageConstants.LOGIN_FIELD.getValue(), LoginPageConstants.LOGIN_FIELD.getValue());
        loginPage.checksFieldLabels(LoginPageConstants.PASSWORD_FIELD.getValue(), LoginPageConstants.PASSWORD_FIELD.getValue());
    }

    @Description("Проверям название кнопки")
    @Test
    public void testChecksButtonName() {
        loginPage.checksButtonName(LoginPageConstants.LOGIN.getValue());
    }

    @Description("Проверям отсутсвие значений по умолчанию в полях логин и пароль")
    @Test
    public void testChecksThatFieldAreEmptyAfterClickingOnThem() {
        loginPage.checksThatFieldIsEmpty(loginField, fieldPlaceholder);
        loginPage.checksThatFieldIsEmpty(passwordField, fieldPlaceholder);
    }

    @Description("Авторизируемcя в приложении используя логин и пароль")
    @ParameterizedTest
    @CsvSource("Login, Password")
    public void testLogInToTheApplicationUsingUsernameAndPassword(String login, String password) {
        loginPage.fillsInTheFieldCharacterByCharacter(LoginPageConstants.LOGIN_FIELD.getValue(), login);
        loginPage.fillsInTheFieldCharacterByCharacter(LoginPageConstants.PASSWORD_FIELD.getValue(), password);
        loginPage.clicksOnLogInButton();
        loginPage.doesNotSeeLogInButton();
    }

    @Description("Пробуем авторизоваться используя неправильный логин")
    @Test
    public void testTriesToLogInWithTheWrongLogin() {
        loginPage.fillsInTheField(LoginPageConstants.LOGIN_FIELD.getValue(), LoginPageConstants.INCORRECT_LOGIN.getValue());
        loginPage.fillsInTheField(LoginPageConstants.PASSWORD_FIELD.getValue(), LoginPageConstants.CORRECT_PASSWORD.getValue());
        loginPage.clicksOnLogInButtonAndSeesError(LoginPageConstants.INCORRECT_DATA_ENTERED_ERROR.getValue());
    }

    @Description("Пробуем авторизоваться используя неправильный пароль")
    @Test
    public void testTriesToLogInWithTheWrongPassword() {
        loginPage.fillsInTheField(LoginPageConstants.LOGIN_FIELD.getValue(), LoginPageConstants.CORRECT_LOGIN.getValue());
        loginPage.fillsInTheField(LoginPageConstants.PASSWORD_FIELD.getValue(), LoginPageConstants.INCORRECT_PASSWORD.getValue());
        loginPage.clicksOnLogInButtonAndSeesError(LoginPageConstants.INCORRECT_DATA_ENTERED_ERROR.getValue());
    }


    @Description("Ввод корректных символов в поле Логин")
    @Test
    public void testEntersValidCharactersInLoginField() {
        loginPage.fillsInTheField(LoginPageConstants.LOGIN_FIELD.getValue(), LoginPageConstants.VALID_LOGIN_DATA_WITH_DIFFERENT_CHARACTERS.getValue());
        loginPage.checksThatFillIsNotEmpty(LoginPageConstants.VALID_LOGIN_DATA_WITH_DIFFERENT_CHARACTERS.getValue(), loginField);
        loginPage.doesNotSeeErrorAfterTryingToLogInUsingCorrectDataUnderField();
    }

    @Description("Ввод максимально допустимого количества символов в поле Логин")
    @Test
    public void testEntersMaxCharacterLimitInLoginField() {
        loginPage.fillsInTheField(LoginPageConstants.LOGIN_FIELD.getValue(), LoginPageConstants.MAX_CHARACTERS.getValue());
        loginPage.checksThatFillIsNotEmpty(LoginPageConstants.MAX_CHARACTERS.getValue(), loginField);
        loginPage.doesNotSeeErrorAfterTryingToLogInUsingCorrectDataUnderField();
    }

    @Description("Ввод некорректных символов в поле Логин")
    @Test
    public void testEntersInvalidCharactersInLoginField() {
        loginPage.fillsInTheField(LoginPageConstants.LOGIN_FIELD.getValue(), LoginPageConstants.INVALID_LOGIN_DATA_WITH_DIFFERENT_CHARACTERS.getValue());
        loginPage.checksThatFillIsNotEmpty(LoginPageConstants.INVALID_LOGIN_DATA_WITH_DIFFERENT_CHARACTERS.getValue(), loginField);
        loginPage.seesErrorAfterTryingToLogInUsingIncorrectDataUnderField(LoginPageConstants.INVALID_VALUE.getValue());
    }

    @Description("Ввод превышающего максимальное количество символов в поле Логин")
    @Test
    public void testEntersMoreThanTheMaximumNumberOfCharactersInTheLoginField() {
        loginPage.fillsInTheField(LoginPageConstants.LOGIN_FIELD.getValue(), LoginPageConstants.MAX_CHARACTERS.getValue() + "X");
        loginPage.checksThatFillIsNotEmpty(LoginPageConstants.MAX_CHARACTERS.getValue() + "X", loginField);
        loginPage.seesErrorAfterTryingToLogInUsingIncorrectDataUnderField(LoginPageConstants.INVALID_VALUE.getValue());
    }

    @Description("Ввод максимально допустимого количества символов в поле Пароль")
    @Test
    public void testEntersMaxCharacterLimitInPasswordField() {
        loginPage.fillsInTheField(LoginPageConstants.PASSWORD_FIELD.getValue(), LoginPageConstants.MAX_CHARACTERS.getValue());
        loginPage.checksThatFillIsNotEmpty(LoginPageConstants.MAX_CHARACTERS.getValue(), passwordField);
        loginPage.doesNotSeeErrorAfterTryingToLogInUsingCorrectDataUnderField();
    }

    @Description("Ввод превышающего максимальное количество символов в поле Логин")
    @Test
    public void testEntersMoreThanTheMaximumNumberOfCharactersInThePasswordField() {
        loginPage.fillsInTheField(LoginPageConstants.PASSWORD_FIELD.getValue(), LoginPageConstants.MAX_CHARACTERS.getValue() + "X");
        loginPage.checksThatFillIsNotEmpty(LoginPageConstants.MAX_CHARACTERS.getValue() + "X", passwordField);
        loginPage.seesErrorAfterTryingToLogInUsingIncorrectDataUnderField(LoginPageConstants.INVALID_VALUE.getValue());
    }

    @Description("Проверяем видимость пароля после нажатия на кнопку Показать пароль")
    @Test
    public void testCheckTheVisibilityOfThePasswordAfterClickingTheShowPasswordButton() {
        loginPage.fillsInTheField(LoginPageConstants.PASSWORD_FIELD.getValue(), LoginPageConstants.CORRECT_PASSWORD.getValue());
        loginPage.clicksOnShowPasswordButton();
    }

    @Description("Проверяем не видимость пароля после повторного нажатия на кнопку Показать пароль")
    @Test
    public void testCheckTheInvisibilityOfThePasswordAfterRepeatClickingTheShowPasswordButton() {
        testCheckTheVisibilityOfThePasswordAfterClickingTheShowPasswordButton();
        loginPage.clicksOnShowPasswordButton();
    }

    @Description("Проверям название заголовка после авторизации")
    @ParameterizedTest
    @CsvSource("Login, Password")
    public void testChecksTheHeaderNameAfterLogIn(String login, String password) {
        testLogInToTheApplicationUsingUsernameAndPassword(login, password);
        Selenide.sleep(2000);
        loginPage.checksFieldLabelsAfterLogIn(LoginPageConstants.HEADER_LOG_IN_ALFA_TEST_EXECUTED.getValue());
    }
}
