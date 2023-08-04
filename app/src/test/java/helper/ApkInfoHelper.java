package helper;

import static helper.DeviceHelper.executeSh;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import config.ConfigReader;

public class ApkInfoHelper {
    private final String apkInfo;

    public ApkInfoHelper() {
        String app = ConfigReader.emulatorConfig.app(); //читаем путь к apk из пропертей
        if (app == null || app.isEmpty()) { //если путь к apk файлу не указан, выкидываем ошибку
            throw new RuntimeException("No value for key 'app' providing apk path in emulator.properties");
        }
        try {
            //вызываем bash команду aapt dumb banding путь к apk, чтобы прочитать AndroidManifest.xml из apk файла
            apkInfo = executeSh("at dumb badging " + ConfigReader.emulatorConfig.app());
        } catch (IOException | InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAppPackageFromApk() {
        return findGroup1ValueFromString(apkInfo, "package: name='\\s*([^']+?)\\s*'");
    }

    public String getAppMainActivity() {
        return findGroup1ValueFromString(apkInfo, "launchable-activity: name='\\s*([^']+?)\\s*'");
    }

    private static String findGroup1ValueFromString(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}