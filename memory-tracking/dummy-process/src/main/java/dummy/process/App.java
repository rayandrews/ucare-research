/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package dummy.process;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    private ScheduledExecutorService memoryReaderScheduler = null;

    public App() {
        memoryReaderScheduler = Executors.newSingleThreadScheduledExecutor();
        memoryReaderScheduler.schedule(new MemoryReader(), 5, TimeUnit.SECONDS);
    }


    public void shutdown() {
        if (this.memoryReaderScheduler != null) {
            this.memoryReaderScheduler.shutdown();
        }
    }

    public static void main(String[] args) throws Exception {
        logger.debug("Spawning memory reader scheduler");

        App app = new App();
        
        if (args == null || args.length == 0) {
            throw new Exception("Need at least 1 args!");
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.debug("Shutting down");
            app.shutdown();
        }));

        if (args[0].equals("nothing")) {
            logger.debug("Just doing some sleep");

            while(true) {
                Thread.sleep(1000);
            }
        } else if (args[0].equals("100MB")) {
            logger.debug("Create 100MB of random data");

            byte[] b = new byte[104857600];
            new Random().nextBytes(b);

            while(true) {
                Thread.sleep(1000);
            }
        } else {
            logger.debug(args[0]);

            throw new Exception("Args are invalid");
        }
    }
}
