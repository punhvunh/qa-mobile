package helper;

import config.ConfigReader;
import driver.EmulatorDriver;

public class RunHelper {

    private RunHelper() {
    }

    public static RunHelper runHelper() {
        return new RunHelper();
    }

    public Class<?> getDriverClass() {
        String deviceHost = ConfigReader.testConfig.deviceHost();

        switch (deviceHost) {
            case "emulator":
                return EmulatorDriver.class; //класс для инициализации сессии для эмулятора
            default:
                throw new RuntimeException("В файле конфигурации нет параметра deviceHost: " +
                        "browserstack/selenoid/emulator/real");
        }
    }
}
