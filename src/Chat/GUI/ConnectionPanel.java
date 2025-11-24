package Chat.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;

// Provides controls for connecting and disconnecting from the chat
public class ConnectionPanel {
    private JTextField usernameField;
    private JButton disconnectButton;
    private JButton connectButton;
    private JButton exitButton; // New exit button

    public JPanel setupConnectionPanel(ActionListener listener) {
        JPanel topPanel = new JPanel(new BorderLayout(5, 0));
        JPanel connectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JLabel nameLabel = new JLabel("Username:");
        usernameField = new JTextField(15);
        usernameField.setText("User" + new Random().nextInt(100));
        connectButton = new JButton("Connect");
        disconnectButton = new JButton("Disconnect");
        disconnectButton.setEnabled(false);
        exitButton = new JButton("Shutdown");

        connectionPanel.add(nameLabel);
        connectionPanel.add(usernameField);
        connectionPanel.add(connectButton);
        connectionPanel.add(disconnectButton);

        rightPanel.add(exitButton);

        topPanel.add(connectionPanel, BorderLayout.WEST);
        topPanel.add(rightPanel, BorderLayout.EAST);

        connectButton.addActionListener(listener);
        disconnectButton.addActionListener(listener);
        exitButton.addActionListener(listener); // Add listener to exit button

        return topPanel;
    }

    // Getter methods
    public JTextField getUsernameField() {
        return usernameField;
    }

    public JButton getConnectButton() {
        return connectButton;
    }

    public JButton getDisconnectButton() {
        return disconnectButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }
}
