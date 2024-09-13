package za.ac.cput;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import za.ac.cput.entity.Role;
import za.ac.cput.entity.User;
import za.ac.cput.entity.enums.RoleType;
import za.ac.cput.factory.RoleFactory;
import za.ac.cput.factory.UserFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class UserScreen extends JFrame implements ActionListener {

    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new GsonBuilder().create();

    private JPanel tablePanel, buttonPanel, formPanel;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton deleteButton, addButton;
    private JTextField firstNameField, lastNameField, usernameField;
    private JPasswordField passwordField;
    private JRadioButton receptionistRadio, adminRadio;
    private ButtonGroup roleGroup;
    private JLabel firstNameLabel, lastNameLabel, usernameLabel, passwordLabel, roleLabel;

    public UserScreen() {
        super("User Management");

        // Create the table and make it scrollable
        tableModel = new DefaultTableModel();
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("e-mail");
        tableModel.addColumn("Password");
        tableModel.addColumn("Role");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 400));

        // Add row selection listener to enable/disable the delete button
        table.getSelectionModel().addListSelectionListener(event -> {
            // Enable the delete button if a row is selected
            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                deleteButton.setEnabled(true);
            } else {
                deleteButton.setEnabled(false);
            }
        });

        populateTable();

        // Create Delete button
        deleteButton = new JButton("Delete");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(this);

        // Panel for buttons (Delete button)
        buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);

        // Layout for the table and delete button at the bottom
        tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.add(buttonPanel, BorderLayout.SOUTH);

        // Creating the form for adding users
        firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField(15);

        lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField(15);

        usernameLabel = new JLabel("e-mail:");
        usernameField = new JTextField(15);

        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);

        roleLabel = new JLabel("Role:");
        receptionistRadio = new JRadioButton("Receptionist");
        adminRadio = new JRadioButton("Admin");

        roleGroup = new ButtonGroup();
        roleGroup.add(receptionistRadio);
        roleGroup.add(adminRadio);
        receptionistRadio.setSelected(true);

        // Create Add button
        addButton = new JButton("Add User");
        addButton.setBackground(new Color(0, 123, 255));
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(this);

        // Layout for form panel (adjusted to match the uploaded image)
        formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Add components to form panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(firstNameLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lastNameLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(roleLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(receptionistRadio, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        formPanel.add(adminRadio, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(addButton, gbc);

        // Split pane for placing the table and form side by side
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tablePanel, formPanel);
        splitPane.setResizeWeight(0.5);

        // Add the split pane to the frame
        setLayout(new BorderLayout());
        add(splitPane, BorderLayout.CENTER);

        // Frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void validateInputs(){

    }
    private void saveUser(User user) {
        try {
            final String URL = "http://localhost:8080/easyStayHotel/user/create"; // Adjust the URL as needed
            System.out.println("Sending data to URL: " + URL);

            // Convert user object to JSON
            String jsonUser = gson.toJson(user);
            System.out.println("User data being sent: " + jsonUser);

            // Build the request body with JSON data
            RequestBody body = RequestBody.create(jsonUser, MediaType.get("application/json; charset=utf-8"));

            // Create POST request
            Request request = new Request.Builder()
                    .url(URL)
                    .post(body)
                    .build();

            // Execute request
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response + " with body: " + response.body().string());
                }

                System.out.println("Response received: " + response.body().string());
                showAlert("Success", "User saved successfully!", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to save user: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleAddUser() {
        // Get values from fields
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Validate inputs
        if (firstName.isEmpty()) {
            showAlert("Error", "First Name cannot be empty.", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (lastName.isEmpty()) {
            showAlert("Error", "Last Name cannot be empty.", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (username.isEmpty()) {
            showAlert("Error", "Username (email) cannot be empty.", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (password.isEmpty()) {
            showAlert("Error", "Password cannot be empty.", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!receptionistRadio.isSelected() && !adminRadio.isSelected()) {
            showAlert("Error", "Please select a role.", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Determine the role based on the selected radio button
        String roleText = receptionistRadio.isSelected() ? "Receptionist" : "Admin";
        Role role;
        if (roleText.equals("Receptionist")) {
            role = RoleFactory.buildRole(2, RoleType.RECEPTIONIST);
        } else {
            role = RoleFactory.buildRole(1, RoleType.MANAGER);
        }

        // Create a new user using the factory
        User newUser = UserFactory.buildUserWithoutId(
                firstName,
                lastName,
                username,
                password,
                role
        );

        // Save user to the server
        saveUser(newUser);

        // Add the new user to the table
        tableModel.addRow(new Object[]{firstName, lastName, username, password, role.getRoleType().toString()});

        // Clear the form fields after adding the user
        firstNameField.setText("");
        lastNameField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        receptionistRadio.setSelected(true);
    }


    private void populateTable() {
        try {
            // Fetch all users from the server
            final String URL = "http://localhost:8080/easyStayHotel/user/all";
            String response = run(URL);

            if (response == null || response.isEmpty()) {
                showAlert("Error", "No users found.", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Parse the JSON response into an array of User objects
            User[] users = gson.fromJson(response, User[].class);

            // Clear the existing rows in the table
            tableModel.setRowCount(0);

            // Add each user to the table
            for (User user : users) {
                tableModel.addRow(new Object[]{
                        user.getFirstName(),
                        user.getLastName(),
                        user.getUserName(),
                        user.getPassword(),
                        user.getRole().getRoleType().toString()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to fetch users.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleDeleteUser(String username) {
        String url = "http://localhost:8080/easyStayHotel/user/delete/" + username;
        // Create a DELETE request
        Request request = new Request.Builder()
                .url(url)
                .delete() // Indicates that this is a DELETE request
                .build();

        // Execute the request and handle the response
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response + " with body: " + response.body().string());
            }

            System.out.println("User with ID " + username + " deleted successfully.");
        } catch (IOException e) {
            System.out.println("Error sending data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private static String run(String URL) throws IOException {
        Request request = new Request.Builder().url(URL).build();
        try  {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response + " with body: " + response.body().string());
            }
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void showAlert(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Get the username from the selected row
                String username = (String) tableModel.getValueAt(selectedRow, 2); // Assuming username is in the 3rd column

                // Call the API to delete the user by username
                handleDeleteUser(username);

                // Remove the selected row from the table
                tableModel.removeRow(selectedRow);

                showAlert("Success", "User deleted successfully!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                showAlert("Error", "Please select a user to delete.", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == addButton) {
            handleAddUser();
        }
    }


    public static void main(String[] args) {
        new UserScreen();
    }
}
