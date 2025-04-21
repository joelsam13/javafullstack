package auth;

import javax.swing.*;

public class AuthUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginBtn);
        panel.add(registerBtn);

        frame.add(panel);
        frame.setVisible(true);

        loginBtn.addActionListener(e -> {
            boolean success = AuthBackend.login(usernameField.getText(), new String(passwordField.getPassword()));
            JOptionPane.showMessageDialog(frame, success ? "Login Successful" : "Invalid Credentials");
        });

        registerBtn.addActionListener(e -> {
            String email = JOptionPane.showInputDialog("Enter Email:");
            boolean success = AuthBackend.register(usernameField.getText(), new String(passwordField.getPassword()), email);
            JOptionPane.showMessageDialog(frame, success ? "Registration Successful" : "Registration Failed");
        });
    }
}
