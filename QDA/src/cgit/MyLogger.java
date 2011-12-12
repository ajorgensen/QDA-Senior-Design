package cgit;

/**
 * This class is a utility class for doing logging to the console. Future plans for this class
 * include logging to a log file as well as controlling the level of logging that is done.
 * 
 * @author andrewjorgensen
 */
public class MyLogger {
    
    private static boolean debug = true;
    
    public static void LogMessageToConsole(Object sender, String message, String type)
    {
        System.out.println("-------------");
        System.out.println("Type: " + type);
        System.out.println("Sender: " + ((sender != null) ? sender.getClass().getName() : "Null"));
        System.out.println("Message: '" + message + "'");
        System.out.println("-------------");
    }
    
}
