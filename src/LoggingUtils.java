import java.io.File;

abstract public class LoggingUtils {

    public static String formatLogMessage(String fileName, String message){
        return ("[" + fileName + "]: " + message);

    }

    public static String fileAlreadyExists(){
        return "Warning, an output file for this may already exist";
    }
}
