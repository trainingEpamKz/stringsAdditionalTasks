package kz.e16training.crazylogger;

import java.util.Calendar;

/**
 * @author DK
 * @version 1.0.0
 *
 */
public class Run {
    public static void main(String[] args) {
        CrazyLogger logger = CrazyLogger.getLogger();
        logger.log("Error 1");
        logger.log("Error 2");
        logger.log("Error 3");
        logger.log("Error 4");
        logger.printLog();
        System.out.println("find by \"Error 2:\"");
        logger.printLog("Error 2");
        System.out.println("find by date today's:");
        Calendar calendar = Calendar.getInstance();
        logger.printLog(calendar.getTime());
    }
}
