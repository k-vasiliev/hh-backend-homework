package ru.hh.backend.config;

import org.springframework.test.context.ContextConfiguration;
import ru.hh.backend.Application;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;

@ContextConfiguration(classes = TestConfig.class)
public abstract class TestBase extends NabTestBase {
  @Override
  protected NabApplication getApplication() {
    return Application.build();
  }
}
