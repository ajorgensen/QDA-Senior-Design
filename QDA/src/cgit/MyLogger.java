package cgit;

public class MyLogger {
    public static void LogMessageToConsole(Object sender, String message, String type)
    {
        System.out.println("-------------");
        System.out.println("Type: " + type);
        System.out.println("Sender: " + ((sender != null) ? sender.getClass().getName() : "Null"));
        System.out.println("Message: '" + message + "'");
        System.out.println("-------------");
    }
    
}
