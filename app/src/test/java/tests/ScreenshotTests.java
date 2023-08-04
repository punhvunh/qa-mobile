package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.io.File;

import constants.LoginPageConstants;
import pages.LogInPage;

class ScreenshotTests extends BaseTest {

    private TestInfo testInfo;

    /**
     * Перед каждым тестом инициализация тестовой информации
     *
     * @param testInfo информация из junit5
     */
    @BeforeEach
    public void init(TestInfo testInfo) {
        this.testInfo = testInfo;
    }

    /**
     * Запускать первым, чтобы сделать эталон скриншота
     * Проверка верстки страницы Каталог
     * Надо чтобы в test.properties было значение true у updateScreenshots
     */
    @Test
    void testLoginPageScreenshot() {
        File loginPageScreenshot = new LogInPage()
                .checksTheHeaderName(LoginPageConstants.HEADER_LOG_IN_ALFA_TEST)
                .fullPageScreenshot();
        assertScreenshot(loginPageScreenshot, testInfo.getDisplayName());
    }

    /**
     * Падающий тест для проверки что в аллюр сохраняется картинка с отличиями
     * Надо чтобы в test.properties было значение false у updateScreenshots
     * Запускать вторым, чтобы увидеть различия
     */
    @Test
    void testLoginPageScreenshotFail() {
        File loginPageScreenshot = new LogInPage()
                .fullPageScreenshot();
        assertScreenshot(loginPageScreenshot, "testLoginPageScreenshot()");
    }
}

