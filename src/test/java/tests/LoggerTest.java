package tests;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;


@Log4j2
public class LoggerTest {

    @Test
    public void test() {
        log.fatal("fatal");
        log.error("error");
        log.warn("warn");
        log.info("");
        log.debug("");
        log.trace("trace");
    }
}
