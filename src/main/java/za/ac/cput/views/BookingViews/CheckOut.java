package za.ac.cput.views.BookingViews;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CheckOut extends JFrame implements ActionListener {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final OkHttpClient client = new OkHttpClient();
    private static final String BASE_URL = "http://localhost:8080/booking";

    private JPanel panelNorth, panelCenter;
    private JLabel lblFirstName, lblLastName, lblRoomNumber;
    private JTextField txtFirstName, txtLastName, txtRoomNumber;
    private JButton btnCheckOut, btnCancel;

    public CheckOut() {
        super("Check Out");

        panelNorth = new JPanel();
        panelCenter = new JPanel();

        lblFirstName = new JLabel("First Name: ");
        lblLastName = new JLabel("Last Name: ");
        lblRoomNumber = new JLabel("Room Number: ");

        txtFirstName = new JTextField();
        txtLastName = new JTextField();
        txtRoomNumber = new JTextField();

        btnCheckOut = new JButton("Check Out");
        btnCancel = new JButton("Cancel");

        panelNorth.setLayout(new GridLayout(3, 2));
        panelCenter.setLayout(new GridLayout(1, 2));

        panelNorth.add(lblFirstName);
        panelNorth.add(txtFirstName);
        panelNorth.add(lblLastName);
        panelNorth.add(txtLastName);
        panelNorth.add(lblRoomNumber);
        panelNorth.add(txtRoomNumber);

        panelCenter.add(btnCheckOut);
        panelCenter.add(btnCancel);

        add(panelNorth, "North");
        add(panelCenter, "Center");

        btnCheckOut.addActionListener(this);
        btnCancel.addActionListener(this);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void checkOutGuest() {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String roomNumber = txtRoomNumber.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || roomNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        String url = BASE_URL + "/checkout";

        try {
            JSONObject json = new JSONObject();
            json.put("guestFirstName", firstName);
            json.put("guestLastName", lastName);
            json.put("roomNumber", Long.parseLong(roomNumber));

            RequestBody body = RequestBody.create(json.toString(), JSON);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    JOptionPane.showMessageDialog(this, "Checkout successful.");
                } else {
                    JOptionPane.showMessageDialog(this, "Error: " + response.message());
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Room number must be a valid number.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error during checkout: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCheckOut) {
            checkOutGuest();
        } else if (e.getSource() == btnCancel) {
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new CheckOut();
    }
}
