package Utils;

import org.apache.log4j.Logger;

public class Log {
    public static Logger log=Logger.getLogger(Log.class.getName());

    public static void startTestCase(String testCaseName){
        log.info(""+testCaseName+"");
    }
    public static void endTestCase(String testCaseName){
        log.info(""+testCaseName+"");
    }
    public static void info(String massage){
        log.info(massage);
    }
    public static void warning(String massage){
        log.warn(massage);
    }
}
