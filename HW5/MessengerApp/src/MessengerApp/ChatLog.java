package MessengerApp.src.MessengerApp;

import java.util.ArrayList;
import java.util.List;

public class ChatLog {
    private List<String> messages;

    public ChatLog() {
        messages = new ArrayList<>();
    }

    public synchronized void addMessage(String message) {
        messages.add(message);
    }

    public synchronized List<String> getMessages() {
        return new ArrayList<>(messages);
    }
}
