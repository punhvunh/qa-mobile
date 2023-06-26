package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import org.openqa.selenium.interactions.Actions;

public class BasePage {

    public static SelenideElement elementIsVisible(SelenideElement element) {
        return element.shouldBe(Condition.visible);
    }

    public static SelenideElement elementIsNotVisible(SelenideElement element) {
        return element.shouldBe(Condition.hidden);
    }

    public static String getsTextAttributeFromElement(SelenideElement element){
        return element.getText();
    }

    public static void typesTextIntoField(SelenideElement element, String text){
        element.sendKeys(text);
    }

    public static void fillsFieldCharacterByCharacter(SelenideElement fieldName, String value) {
        fieldName.clear();
        Actions actions = new Actions(WebDriverRunner.getWebDriver());
        for (char c : value.toCharArray()) {
            String character = String.valueOf(c);
            actions.sendKeys(fieldName, character).build().perform();
            try {
                Thread.sleep(100); // Задержка между вводом символов (100 миллисекунд)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}