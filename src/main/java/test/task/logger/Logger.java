package test.task.logger;

import test.task.dao.type.LogType;

import java.io.IOException;

public interface Logger {
    public void log(String message, LogType type, boolean append) throws IOException;
}
