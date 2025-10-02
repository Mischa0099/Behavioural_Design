package observer;

public class ConsoleNotifier implements ClassroomObserver {
    public void notifyEvent(String message) {
        System.out.println("[NOTIFY] " + message);
    }
}
