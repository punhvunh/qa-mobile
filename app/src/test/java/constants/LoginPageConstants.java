package constants;

public enum LoginPageConstants {
    LOGIN_FIELD("Логин"),
    PASSWORD_FIELD("Пароль"),
    CORRECT_LOGIN("Login"),
    CORRECT_PASSWORD("Password"),
    INCORRECT_LOGIN("Logins"),
    INCORRECT_PASSWORD("Passwords"),

    LOGIN("Вход"),
    HEADER_LOG_IN_ALFA_TEST("Вход в Alfa-Test"),
    HEADER_LOG_IN_ALFA_TEST_EXECUTED("Вход в Alfa-Test выполнен"),
    INCORRECT_DATA_ENTERED_ERROR("Введены неверные данные"),
    VALID_LOGIN_DATA_WITH_DIFFERENT_CHARACTERS("ValidLogin123.-_ /',"),
    INVALID_LOGIN_DATA_WITH_DIFFERENT_CHARACTERS("@#$%^&*()+=?!\""),
    MAX_CHARACTERS("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWX"),
    INVALID_VALUE("InvalidValue");

    private final String value;

    LoginPageConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
