package za.ac.cput.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingPage extends JFrame {

    private JProgressBar progressBar;

    public LandingPage() {
        super("Landing Page");

        // Set the layout manager
        setLayout(new BorderLayout());

        // Set the background color to dark blue
        Color darkBlue = new Color(0, 51, 102);
        getContentPane().setBackground(darkBlue);

        // Create a panel to hold the welcome label
        JPanel welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.setBackground(darkBlue); // Match the background color

        // Create and add a welcome label
        JLabel welcomeLabel = new JLabel("Welcome to EasyStay!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 35));
        welcomeLabel.setForeground(Color.WHITE); // Set text color to white for contrast
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(70, 0, 30, 0)); // Add some space above and below the label

        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);
        add(welcomePanel, BorderLayout.NORTH);

        // Load and resize the logo/image
        String logoPath = "C:\\Users\\okuhl\\IdeaProjects\\EasyStay_Views\\src\\main\\resources\\logo.png"; // Update with your image filename
        ImageIcon originalLogo = new ImageIcon(logoPath);
        Image scaledImage = originalLogo.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH); // Adjust the width and height as needed
        ImageIcon scaledLogo = new ImageIcon(scaledImage);

        // Check if the image was successfully loaded
        if (scaledLogo.getIconWidth() == -1) {
            JOptionPane.showMessageDialog(this, "Image not found at " + logoPath, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JLabel logoLabel = new JLabel(scaledLogo);
            logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            add(logoLabel, BorderLayout.CENTER);
        }

        // Create a panel for the progress bar and add it below the logo
        JPanel progressBarPanel = new JPanel(new BorderLayout());
        progressBarPanel.setBackground(darkBlue);
        progressBarPanel.setBorder(BorderFactory.createEmptyBorder(30, 200, 30, 200)); // Adjust padding as needed

        // Create and add the progress bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true); // Display percentage on the progress bar
        progressBar.setForeground(new Color(0, 191, 255)); // Set progress bar color

        progressBarPanel.add(progressBar, BorderLayout.CENTER);
        add(progressBarPanel, BorderLayout.SOUTH);

        // Initialize the progress bar timer
        startProgressBar();

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private void startProgressBar() {
        Timer timer = new Timer(20, new ActionListener() { // Fire every 20 milliseconds (for smooth progress)
            int counter = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                counter += 1;
                progressBar.setValue(counter);
                if (counter >= 100) {
                    ((Timer) e.getSource()).stop(); // Stop the timer when progress reaches 100%

                    // Open the Login Page
                    new LoginScreen();

                    // Close the Landing Page
                    dispose();
                }
            }
        });
        timer.setInitialDelay(0); // Start immediately
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LandingPage();
            }
        });
    }
}
