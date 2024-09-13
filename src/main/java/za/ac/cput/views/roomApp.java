package za.ac.cput.views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import okhttp3.*;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.entity.Room;
import za.ac.cput.entity.enums.RoomType;
import za.ac.cput.factory.RoomFactory;


public class roomApp extends JFrame implements ActionListener {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static OkHttpClient client = new OkHttpClient();

    private JFrame frame;
    private JTable tblRoom;
    private JButton btnRetrieve, btnCancel, btnAdd, btnRemove;
    private JLabel lblRoom;
    private JTextField txtRoomNumber, txtpricePerNight;
    private JComboBox<RoomType> cmbRoomType;  // Combo box for enum types
    Gson gson = new Gson();

    private void initComponents() {
        frame = new JFrame("Hotel Room Management");

        String[] columnNames = {"Room ID", "Room Type", "Price"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        tblRoom = new JTable(tableModel);

        btnRetrieve = new JButton("Retrieve Available Rooms");
        btnCancel = new JButton("Cancel");
        btnAdd = new JButton("Add Room");
        btnRemove = new JButton("Remove Room");
        lblRoom = new JLabel("Available Rooms:");

        txtRoomNumber = new JTextField(10);
        txtpricePerNight = new JTextField(10);

        // ComboBox for RoomType Enum
        cmbRoomType = new JComboBox<>(RoomType.values());

        btnRetrieve.addActionListener(this);
        btnCancel.addActionListener(this);
        btnAdd.addActionListener(this);
        btnRemove.addActionListener(this);
    }

    public void setGui() {
        initComponents();
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(btnRetrieve);
        panel.add(btnCancel);
        panel.add(btnAdd);
        panel.add(btnRemove);
        panel.add(new JLabel("Room number:"));
        panel.add(txtRoomNumber);
        panel.add(new JLabel("Room Type:"));
        panel.add(cmbRoomType);  // Adding the ComboBox for RoomType
        panel.add(new JLabel("Price:"));
        panel.add(txtpricePerNight);

        frame.add(panel, BorderLayout.SOUTH);
        frame.add(lblRoom, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(tblRoom);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRetrieve) {
            getAll();
        } else if (e.getSource() == btnCancel) {
            System.exit(0);
        } else if (e.getSource() == btnAdd) {
            addRoom();
        } else if (e.getSource() == btnRemove) {
            deleteRoom();
        }
    }

    private void addRoom() {
        try {
            String roomNumberStr = txtRoomNumber.getText();
            RoomType roomType = (RoomType) cmbRoomType.getSelectedItem();  // Get selected enum
            double price = Double.parseDouble(txtpricePerNight.getText());

            long roomNumber = Long.parseLong(roomNumberStr);

            // room object using the Builder pattern
            Room roomObj = new Room.Builder()
                    .setRoomNumber(roomNumber)
                    .setRoomType(roomType)
                    .setPricePerNight(price)
                    .build();


            String json = gson.toJson(roomObj);

            final String URL = "http://localhost:8080/easyStayHotel/room/save";
            RequestBody body = RequestBody.create(json, JSON);
            Request request = new Request.Builder().url(URL).post(body).build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    JOptionPane.showMessageDialog(null, "Room added successfully");
                    getAll();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add room");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }


    private void deleteRoom() {
        try {
            String roomId = txtRoomNumber.getText();

            final String URL = "http://localhost:8080/easyStayHotel/room/delete/{roomNumber}" + roomId;Request request = new Request.Builder().url(URL).delete().build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    JOptionPane.showMessageDialog(null, "Room deleted successfully");
                    getAll();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete room");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

  /*  public void getAll() {
        try {
            final String URL = "http://localhost:8080/easyStayHotel/room/all";
            String responseBody = run(URL);
            JSONArray rooms = new JSONArray(responseBody);

            DefaultTableModel model = (DefaultTableModel) tblRoom.getModel();
           // model.setRowCount(0);  // Clear the table

            for (int i = 0; i < rooms.length(); i++) {
                JSONObject room = rooms.getJSONObject(i);
                Gson gson = new Gson();
                Room r = gson.fromJson(room.toString(), Room.class);

                Object[] rowData = {r.getRoomNumber(), r.getRoomType(), r.getPricePerNight()};
                model.addRow(rowData);
            }

            tblRoom.revalidate();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }*/

    public void getAll() {
        try {
            final String URL = "http://localhost:8080/easyStayHotel/room/all";
            String response = run(URL);

            if (response == null || response.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No rooms found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            populateTable(response);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to fetch rooms.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void populateTable(String response) {
        try {
            // Parse the JSON response into an array of Room objects
            Room[] rooms = gson.fromJson(response, Room[].class);


            DefaultTableModel tableModel = (DefaultTableModel) tblRoom.getModel();
            tableModel.setRowCount(0);

            // Add each room to the table
            for (Room room : rooms) {
                tableModel.addRow(new Object[]{
                        room.getRoomNumber(),
                        room.getRoomType().toString(),
                        room.getPricePerNight()
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private String run(String URL) throws IOException {
        Request request = new Request.Builder().url(URL).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void main(String[] args) {
        roomApp gui = new roomApp();
        gui.setGui();
    }
}
