package za.ac.cput.views;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import za.ac.cput.entity.User;
import za.ac.cput.factory.UserFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends JFrame implements ActionListener {

    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new GsonBuilder().create();
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
    private void validateUser(User user){

        if(user.getUserName().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter a valid email address");
            emailTextField.requestFocus();
        } else if (user.getPassword().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a password");
            passwordTextField.requestFocus();
        }else {

        }
    }
    private void sendData(User user) {
        final String URL = "http://localhost:8080/easyStayHotel/user/findUserByUsernameAndPassword";
        System.out.println("Sending data to URL: " + URL);

        // Validate user object before sending
        validateUser(user);

        // Convert the User object to JSON
        String jsonLoginData = gson.toJson(user);
        System.out.println("Login data being sent: " + jsonLoginData);

        // Create request body with the User object in JSON format
        RequestBody body = RequestBody.create(jsonLoginData, MediaType.get("application/json; charset=utf-8"));

        // Build the request
        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response + " with body: " + response.body().string());
            }

            // Handle the server's response
            System.out.println("Response received: " + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {

            User user = UserFactory.buildUserLogin(emailTextField.getText(),new String(passwordTextField.getPassword()));
            System.out.println(user);
           sendData(user);
           clearTextFields();
            JOptionPane.showMessageDialog(contentPane, "Login Successful");
        } else if (e.getSource() == cancelButton) {
            // Clear fields
            clearTextFields();
        }
    }
    private void clearTextFields(){
        emailTextField.setText("");
        passwordTextField.setText("");
    }

    public static void main(String[] args) {
        new LoginScreen();
    }
}