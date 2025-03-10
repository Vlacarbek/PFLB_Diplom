package tests;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


@Log4j2
public class LoggerTest {

    private static final Logger log = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test() {
//        log.fatal("fatal");
        log.error("error");
        log.warn("warn");
        log.info("");
        log.debug("");
        log.trace("trace");
    }
}
