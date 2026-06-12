package ru.effectivemobile.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public abstract class BaseApiTest {

    private static final Logger logger = LogManager.getLogger(BaseApiTest.class);

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        logger.info("START TEST: {}", testInfo.getDisplayName());
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        logger.info("FINISH TEST: {}", testInfo.getDisplayName());
    }
}
