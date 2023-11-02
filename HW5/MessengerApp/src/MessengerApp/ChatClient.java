package MessengerApp.src.MessengerApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ChatClient extends JFrame {
    private String username;
    private ChatLog chatLog;
    private JTextArea chatTextArea;
    private JTextField messageTextField;

    public ChatClient(String username, ChatLog chatLog) {
        this.username = username;
        this.chatLog = chatLog;

        setTitle("Chat Client - " + username);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chatTextArea = new JTextArea();
        chatTextArea.setEditable(false);
        add(new JScrollPane(chatTextArea), BorderLayout.CENTER);

        messageTextField = new JTextField();
        messageTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageTextField.getText();
                if (!message.isEmpty()) {
                    chatLog.addMessage(username + ": " + message);
                    messageTextField.setText("");
                }
            }
        });
        add(messageTextField, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void updateChat(List<String> messages) {
        chatTextArea.setText(String.join("\n", messages));
    }
}
