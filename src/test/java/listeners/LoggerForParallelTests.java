package listeners;

import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.BeforeMethod;

public class LoggerForParallelTests {
    @BeforeMethod
    public void addThread() {
        ThreadContext.put("threadName", this.getClass().getName());
    }
}
