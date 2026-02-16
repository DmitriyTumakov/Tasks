package test.task.logger.impl;

import test.task.dao.type.LogType;
import test.task.logger.Logger;

import java.io.*;
import java.time.LocalDateTime;

public class DefaultLogger implements Logger {

    private final File log = new File("log.txt");

    @Override
    public void log(String message, LogType type, boolean append) throws IOException {
        if (log.exists()) {
            try (FileOutputStream fos = new FileOutputStream(log, append)) {
                fos.write(("[" + LocalDateTime.now() + "] " + type + " " + message + "\n").getBytes());
            } catch (IOException e) {
                log(e.getMessage(), LogType.ERROR, true);
            }
        } else {
            try {
                if (log.createNewFile()) {
                    log("Создание файла логгирования", LogType.INFO, false);
                    log("Запуск программы", LogType.INFO, true);
                }
            } catch (IOException e) {
                log(e.getMessage(), LogType.ERROR, true);
            }
        }
    }
}
