import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class CurrencyConverter extends JFrame implements ActionListener {
    private final JTextField textField;
    private final JComboBox<String> fromComboBox, toComboBox;
    private final JLabel resultLabel;

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(500, 400); // Increased frame size
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("main-icon.png");
        setIconImage(icon.getImage());

        // Panel with a background image
        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bgImage = new ImageIcon("path/to/your/background.png"); // Ensure the path is correct
                g.drawImage(bgImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
            }
        };
        panel.setOpaque(false);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel label = new JLabel("Enter amount");
        label.setFont(new Font("Arial", Font.BOLD, 14)); // Bold font
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 30)); // Increase width to 200 and height to 40
        textField.setFont(new Font("Arial", Font.PLAIN, 14)); // Adjusted font size
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Bold outline
        // Bold outline
        JButton convertButton = new JButton("Convert");
        JButton clearButton = new JButton("Clear");
        resultLabel = new JLabel();
        resultLabel.setForeground(new Color(0, 102, 0)); // Dark green text

        convertButton.addActionListener(this);
        convertButton.setBackground(new Color(255, 204, 153)); // Light orange button background
        convertButton.setForeground(new Color(0, 51, 102)); // Dark blue button text

        clearButton.addActionListener(this);
        clearButton.setBackground(new Color(255, 204, 153)); // Light orange button background
        clearButton.setForeground(new Color(0, 51, 102)); // Dark blue button text

        String[] currencies = {"INR", "USD", "EUR", "JPY", "KYD", "KWD", "GBP"};
        fromComboBox = new JComboBox<>(currencies);
        toComboBox = new JComboBox<>(currencies);

        fromComboBox.setBackground(new Color(204, 255, 204)); // Light green dropdown background
        fromComboBox.setForeground(new Color(0, 51, 102)); // Dark blue dropdown text

        toComboBox.setBackground(new Color(204, 255, 204)); // Light green dropdown background
        toComboBox.setForeground(new Color(0, 51, 102)); // Dark blue dropdown text

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(label, constraints);

        constraints.gridx = 1;
        panel.add(textField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("From:"), constraints);

        constraints.gridx = 1;
        panel.add(fromComboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(new JLabel("To:"), constraints);

        constraints.gridx = 1;
        panel.add(toComboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(convertButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(clearButton, constraints);

        constraints.gridy = 5;
        panel.add(resultLabel, constraints);
        JButton backButton = new JButton("Back to Homepage");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Homepage().setVisible(true);
                dispose();
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 6; // Or adjust based on your layout
        constraints.gridwidth = 2; // Span across two columns
        panel.add(backButton, constraints);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Convert")) {
            try {
                double usdAmount = Double.parseDouble(textField.getText());
                String fromCurrency = fromComboBox.getSelectedItem() != null ? fromComboBox.getSelectedItem().toString() : "";
                String toCurrency = toComboBox.getSelectedItem() != null ? toComboBox.getSelectedItem().toString() : "";
                double exchangeRate = getExchangeRate(fromCurrency, toCurrency);
                double convertedAmount = usdAmount * exchangeRate;
                resultLabel.setText(String.format("<html><font color='blue'>Converted amount:</font> <font color='green'>%.2f %s</font></html>", convertedAmount, toCurrency));
            } catch (NumberFormatException ex) {
                resultLabel.setText("Error: Invalid input");
            }
        } else if (e.getActionCommand().equals("Clear")) {
            textField.setText("");
            resultLabel.setText("");
        }
    }

    private double getExchangeRate(String fromCurrency, String toCurrency) {
        return fromCurrency.equals(toCurrency) ? 1.0 : Math.random() * 100;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CurrencyConverter::new);
    }
}
