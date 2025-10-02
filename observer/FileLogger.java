package observer;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class FileLogger implements ClassroomObserver {
    private final String fileName = "classroom_log.txt";

    public void notifyEvent(String message) {
        try (FileWriter fw = new FileWriter(fileName, true)) {
            fw.write(LocalDateTime.now() + " - " + message + "\n");
        } catch (IOException e) {
            System.out.println("❌ Logging failed: " + e.getMessage());
        }
    }
}
