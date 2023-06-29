package locators;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

import io.appium.java_client.MobileBy;

public class LogInPageLocators {

    private static final String alfaBankId = "com.alfabank.qapp:id/";

    public static final SelenideElement loginField = $(MobileBy.id(alfaBankId+ "etUsername"));

    public static final SelenideElement passwordField = $(MobileBy.id(alfaBankId + "etPassword"));

    public static final SelenideElement fieldPlaceholder = $(MobileBy.id(alfaBankId + "textinput_placeholder"));

    public static final SelenideElement confirmButton = $(MobileBy.id(alfaBankId + "btnConfirm"));

    public static final SelenideElement headerLogInAlfaTest = $(MobileBy.id(alfaBankId + "tvTitle"));

    public static final SelenideElement headerLogInAlfaTestExecuted = $(MobileBy.xpath("//android.widget.FrameLayout//android.view.ViewGroup//android.widget.TextView"));

    public static final SelenideElement incorrectDataEnteredError = $(MobileBy.id(alfaBankId+ "tvError"));
    public static final SelenideElement incorrectDataEnteredErrorUnderFieldLogin = $(MobileBy.id(""));

    public static final SelenideElement showPasswordButton = $(MobileBy.id(alfaBankId + "text_input_end_icon"));

}
