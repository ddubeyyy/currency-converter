import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Homepage extends JFrame {
    public Homepage() {
        setTitle("Homepage");
        setSize(400, 200); // Set size of the window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bgImage = new ImageIcon("path/to/your/homepage/background.png"); // Ensure the path is correct
                g.drawImage(bgImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        // Add a greeting label
        JLabel greetingLabel = new JLabel("Welcome to the Currency Converter!");
        panel.add(greetingLabel, BorderLayout.NORTH);

        // Add a button to open the CurrencyConverter window
        JButton openConverterButton = new JButton("Open Currency Converter");
        openConverterButton.addActionListener(e -> {
            CurrencyConverter converter = new CurrencyConverter();
            converter.setVisible(true);
            dispose(); // Close the homepage window
        });
        panel.add(openConverterButton);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Homepage homepage = new Homepage();
            homepage.setVisible(true);
        });
    }
}