package za.ac.cput.views;

import za.ac.cput.views.BookingViews.BookingInfo;
//import za.ac.cput.views.BookingViews.BookingView;
import za.ac.cput.views.BookingViews.RoomSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame implements ActionListener {

    // Panels for the layout
    private JPanel panelNorth, panelWest, panelEast;

    // North panel components
    private JLabel lblLogo;
    private JLabel lblHeader;
    private JButton btnLogout;

    // West panel components (buttons)
    private JButton btnBooking, btnCheckout, btnBookingDetails, btnCancelBooking, btnRooms, btnGuests, btnHomePage;

    // Fonts for styling
    private Font headerFont = new Font("Arial", Font.BOLD, 36);
    private Font buttonFont = new Font("Arial", Font.BOLD, 18);

    // File path for the logo image
    private static final String LOGO_PATH = "C:\\Users\\USER\\Desktop\\App Dev 2023\\ADP2 (2023)\\Capstone\\Last Chance\\EasyStay_Views\\src\\main\\resources\\logo.png";

    public Dashboard() {
        super("Booking Management Dashboard");

        // Initialize the panels
        panelNorth = new JPanel();
        panelWest = new JPanel();
        panelEast = new JPanel();

        // Initialize North panel components
        lblLogo = new JLabel(scaleImage(LOGO_PATH, 200, 100));  // Scaled logo
        lblHeader = new JLabel("BOOKING MANAGEMENT", JLabel.CENTER);
        btnLogout = new JButton("LOG OUT");

        // Initialize West panel components (buttons)
        btnBooking = new JButton("BOOKING");
        btnCheckout = new JButton("CHECKOUT");
        btnBookingDetails = new JButton("BOOKING DETAILS");
        btnCancelBooking = new JButton("CANCEL BOOKING");
        btnRooms = new JButton("ROOMS");
        btnGuests = new JButton("GUESTS");
        btnHomePage = new JButton("HOME-PAGE");

        // Set up the GUI
        setGUI();
    }

    public void setGUI() {
        // North panel layout
        panelNorth.setLayout(new BorderLayout());
        panelNorth.setBackground(new Color(0, 51, 102));  // Dark blue
        panelNorth.setPreferredSize(new Dimension(0, 100));  // Match the height of the logo

        // Add logo to the left, header to center, and logout button to the right in the North panel
        panelNorth.add(lblLogo, BorderLayout.WEST);
        lblHeader.setFont(headerFont);
        lblHeader.setForeground(Color.WHITE);
        panelNorth.add(lblHeader, BorderLayout.CENTER);
        btnLogout.setBackground(Color.RED);
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFont(buttonFont);
        panelNorth.add(btnLogout, BorderLayout.EAST);

        // West panel layout
        panelWest.setLayout(new GridLayout(7, 1, 10, 10));
        panelWest.setBackground(new Color(0, 51, 102));  // Dark blue

        // Add buttons to the West panel
        addButtonToPanel(panelWest, btnBooking);
        addButtonToPanel(panelWest, btnCheckout);
        addButtonToPanel(panelWest, btnBookingDetails);
        addButtonToPanel(panelWest, btnCancelBooking);
        addButtonToPanel(panelWest, btnRooms);
        addButtonToPanel(panelWest, btnGuests);
        addButtonToPanel(panelWest, btnHomePage);

        // East panel (empty space for now)
        panelEast.setBackground(Color.WHITE);

        // Add panels to the frame
        this.add(panelNorth, BorderLayout.NORTH);
        this.add(panelWest, BorderLayout.WEST);
        this.add(panelEast, BorderLayout.CENTER);

        // Add action listeners to buttons
        btnBooking.addActionListener(this);
        btnCheckout.addActionListener(this);
        btnBookingDetails.addActionListener(this);
        btnCancelBooking.addActionListener(this);
        btnRooms.addActionListener(this);
        btnGuests.addActionListener(this);
        btnHomePage.addActionListener(this);
        btnLogout.addActionListener(this);

        // Frame settings
        this.setSize(1200, 700);  // Set appropriate size
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    // Helper method to scale the image
    private ImageIcon scaleImage(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    // Helper method to add buttons with consistent styles to the panel
    private void addButtonToPanel(JPanel panel, JButton button) {
        button.setBackground(new Color(135, 206, 250));  // Sky blue
        button.setForeground(Color.BLACK);
        button.setFont(buttonFont);
        button.setFocusPainted(false);  // Removes the focus border
        panel.add(button);
    }

    // Method to load the RoomSearch panel into the central space
    private void displayRoomSearch() {
        panelEast.removeAll();
        RoomSearch roomSearch = new RoomSearch(this);
        panelEast.setLayout(new BorderLayout());
        panelEast.add(roomSearch.getContentPane(), BorderLayout.CENTER);
        panelEast.revalidate();
        panelEast.repaint();
    }

    private void displayBookingInfo(){
        panelEast.removeAll();
        BookingInfo bookingInfo = new BookingInfo();
        panelEast.setLayout(new BorderLayout());
        panelEast.add(bookingInfo.getContentPane(), BorderLayout.CENTER);
        panelEast.revalidate();
        panelEast.repaint();
    }


    // Method to load GuestDetails form into the central space
    private void displayGuestDetails() {
        panelEast.removeAll();
        GuestView guestDetails = new GuestView();
        panelEast.setLayout(new BorderLayout());
        panelEast.add(guestDetails.getContentPane(), BorderLayout.CENTER);
        panelEast.revalidate();
        panelEast.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogout) {
            System.out.println("Logged out");
            // Perform logout actions here
        } else if (e.getSource() == btnBooking) {
            System.out.println("Booking button clicked");
            displayRoomSearch();
        }
        else if (e.getSource() == btnBookingDetails) {
            System.out.println("Booking Details button clicked");
            displayBookingInfo();
        }
        else if (e.getSource() == btnGuests) {
            System.out.println("Guests button clicked");
            displayGuestDetails();
        }
    }

    // Method to display GuestDetails when the "Book" button is clicked in RoomSearch
    public void displayGuestDetailsFromRoomSearch() {
//        displayGuestDetails();
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}
