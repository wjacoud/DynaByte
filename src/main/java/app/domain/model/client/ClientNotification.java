package app.domain.model.client;

import app.domain.shared.Constants;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * class responsible for the Client Notification
 *
 * @author Ricardo Faria 1201405
 */
public class ClientNotification {
    private static int n = 1;

    /**
     * Sends the notification to client
     *
     * @param type     type of message (email, sms)
     * @param message  message with the user and password
     * @param clientId client id
     * @return return the success of the operation
     */
    public static boolean sendMessage(String type, String message, String clientId) {
        try {
            File file1 = new File(Constants.CLIENT_NOTIFICATION_PATH);
            file1.mkdir();
            return writerMethod(type, message, clientId);
        } catch (IOException e) {
            return false;
        }

    }

    /**
     * module responsible for the write of the message
     *
     * @param type     type of message (email, sms)
     * @param message  message with the user and password
     * @param clientId client id
     * @return return the success of the operation
     */
    private static boolean writerMethod(String type, String message, String clientId) throws IOException {
        File file = new File(Constants.CLIENT_NOTIFICATION_DIRECTORY_PATH + clientId + "_" + n + ".txt");
        file.createNewFile();
        try (FileWriter filewriter = new FileWriter(Constants.CLIENT_NOTIFICATION_DIRECTORY_PATH + clientId + "_" + n + ".txt")) {
            filewriter.write("message type: " + type + "\n" + message);
            filewriter.close();
            n++;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
