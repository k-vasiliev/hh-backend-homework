package ru.hh.backend.config;

import io.sentry.logback.SentryAppender;
import org.slf4j.event.Level;
import ru.hh.nab.logging.HhMultiAppender;
import ru.hh.nab.starter.NabLogbackBaseConfigurator;

public class NabLogbackConfigurator extends NabLogbackBaseConfigurator {
    @Override
    public void configure(LoggingContextWrapper loggingContextWrapper, HhMultiAppender hhMultiAppender, HhMultiAppender hhMultiAppender1, SentryAppender sentryAppender) {
        getRootLogger(loggingContextWrapper).setLevel(Level.INFO);
    }
}
