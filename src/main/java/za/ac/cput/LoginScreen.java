package za.ac.cput;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField emailTextField;
    private JPasswordField passwordTextField; // Use JPasswordField for security
    private JButton loginButton, cancelButton;

    public LoginScreen() {
        super("Login Screen");

        // Frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());

        // GridBagConstraints for component layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Login label
        JLabel lblLogin = new JLabel("Login");
        lblLogin.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(lblLogin, gbc);

        // Email label and text field
        JLabel lblEmail = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(lblEmail, gbc);

        emailTextField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        contentPane.add(emailTextField, gbc);

        // Password label and text field
        JLabel lblPassword = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPane.add(lblPassword, gbc);

        passwordTextField = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        contentPane.add(passwordTextField, gbc);

        // Login button
        loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        contentPane.add(loginButton, gbc);

        // Cancel button
        cancelButton = new JButton("Cancel");
        gbc.gridx = 1;
        gbc.gridy = 3;
        contentPane.add(cancelButton, gbc);

        // Add action listeners
        loginButton.addActionListener(this);
        cancelButton.addActionListener(this);

        // Final settings
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Handle button actions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String email = emailTextField.getText();
            String password = new String(passwordTextField.getPassword()); // Get password safely

            // Perform login logic here
            if (email.equals("admin@example.com") && password.equals("admin123")) {
                JOptionPane.showMessageDialog(this, "Login successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == cancelButton) {
            // Clear fields or close window
            emailTextField.setText("");
            passwordTextField.setText("");
        }
    }

    public static void main(String[] args) {
        new LoginScreen();
    }
}
