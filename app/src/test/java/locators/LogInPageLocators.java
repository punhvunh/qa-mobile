package locators;

import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;

import io.appium.java_client.MobileBy;

public class LogInPageLocators {

    public static final SelenideElement loginField = $(MobileBy.id("com.alfabank.qapp:id/etUsername"));

    public static final SelenideElement passwordField = $(MobileBy.id("com.alfabank.qapp:id/etPassword"));

    public static final SelenideElement fieldPlaceholder = $(MobileBy.id("com.alfabank.qapp:id/textinput_placeholder"));

    public static final SelenideElement confirmButton = $(MobileBy.id("com.alfabank.qapp:id/btnConfirm"));

    public static final SelenideElement headerLogInAlfaTest =  $(MobileBy.id("com.alfabank.qapp:id/tvTitle"));

    public static final SelenideElement headerLogInAlfaTestExecuted =  $(MobileBy.xpath("//android.widget.FrameLayout//android.view.ViewGroup//android.widget.TextView"));

    public static final SelenideElement incorrectDataEnteredError = $(MobileBy.id("com.alfabank.qapp:id/tvError"));
    public static final SelenideElement incorrectDataEnteredErrorUnderFieldLogin = $(MobileBy.id(""));

    public static final SelenideElement showPasswordButton = $(MobileBy.id("com.alfabank.qapp:id/text_input_end_icon"));

}
