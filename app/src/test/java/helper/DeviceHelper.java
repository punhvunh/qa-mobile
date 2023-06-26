package helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

/**
 * Класс помощник для выполнения bash команд
 */
public class DeviceHelper {

    /**
     * Выполняет bash скрипт с гарантированием возрата полной информации из консоли
     * @param command bash скрипт
     * @return результат скрипта
     * Чтение результата консольной команды при попощи FutertTask
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static String executeSh(String command) throws IOException, ExecutionException, InterruptedException {
        Process p = Runtime.getRuntime().exec(command);//получаем инстатс терминала и выполняем скрипт
        FutureTask<String > future = new FutureTask<>(()->{ //создаем FutureTask
            return new BufferedReader(new InputStreamReader(p.getInputStream())) //читаем поток информации из консоли
                    .lines().map(Object::toString) //информацию преобразуем в строку
                    .collect(Collectors.joining("\n")); //все строки собираем в одну с разделением в виде новой строки
        });
        new Thread(future).start(); //запускаем поток
        return future.get(); //ждем завершения CallBack для получения полной конечной информации из консоли
    }
}
