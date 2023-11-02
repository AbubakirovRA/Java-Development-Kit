package MessengerApp.src.MessengerApp;

import java.util.ArrayList;
import java.util.List;

public class MessengerApp {
    public static void main(String[] args) {
        ChatLog chatLog = new ChatLog();

        List<ChatClient> clients = new ArrayList<>();
        clients.add(new ChatClient("User1", chatLog));
        clients.add(new ChatClient("User2", chatLog));

        while (true) {
            List<String> messages = chatLog.getMessages();
            for (ChatClient client : clients) {
                client.updateChat(messages);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
