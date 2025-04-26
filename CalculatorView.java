import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CalculatorView extends JFrame {
    private JTextField firstNumber, secondNumber, calcSolution;
    private JComboBox<String> operationComboBox;
    private JButton calculateButton, clearButton;
    private JToggleButton themeToggle;
    private JTextArea historyArea;
    private JSpinner decimalSpinner;
    
    public CalculatorView() {
        // Set up the view
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        
        // Set up the panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        // Create the components
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        
        inputPanel.add(new JLabel("First Number:"));
        firstNumber = new JTextField(10);
        inputPanel.add(firstNumber);
        
        inputPanel.add(new JLabel("Operation:"));
        String[] operations = {"+", "-", "*", "/", "%", "^"};
        operationComboBox = new JComboBox<>(operations);
        inputPanel.add(operationComboBox);
        
        inputPanel.add(new JLabel("Second Number:"));
        secondNumber = new JTextField(10);
        inputPanel.add(secondNumber);
        
        JPanel resultPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        
        resultPanel.add(new JLabel("Result:"));
        calcSolution = new JTextField(10);
        calcSolution.setEditable(false);
        resultPanel.add(calcSolution);
        
        resultPanel.add(new JLabel("Decimal Places:"));
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(2, 0, 10, 1);
        decimalSpinner = new JSpinner(spinnerModel);
        resultPanel.add(decimalSpinner);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        calculateButton = new JButton("Calculate");
        buttonPanel.add(calculateButton);
        
        clearButton = new JButton("Clear");
        buttonPanel.add(clearButton);
        
        themeToggle = new JToggleButton(" Dark Mode");
        buttonPanel.add(themeToggle);
        
        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.add(new JLabel("History:"), BorderLayout.NORTH);
        
        historyArea = new JTextArea(10, 30);
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        historyPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Add components to panel
        panel.add(inputPanel);
        panel.add(resultPanel);
        panel.add(buttonPanel);
        panel.add(historyPanel);
        
        // Add panel to frame
        add(panel);
    }
    
    public double getFirstNumber() {
        return Double.parseDouble(firstNumber.getText());
    }
    
    public double getSecondNumber() {
        return Double.parseDouble(secondNumber.getText());
    }
    
    public String getOperation() {
        return operationComboBox.getSelectedItem().toString();
    }
    
    public void setCalcSolution(double solution) {
        int decimalPlaces = (Integer) decimalSpinner.getValue();
        if (decimalPlaces == 0) {
            calcSolution.setText(String.valueOf((int) solution));
        } else {
            calcSolution.setText(String.format("%." + decimalPlaces + "f", solution));
        }
    }
    
    public void addHistoryEntry(String entry) {
        historyArea.append(entry + "\n");
    }
    
    public void clearInputs() {
        firstNumber.setText("");
        secondNumber.setText("");
        calcSolution.setText("");
        firstNumber.requestFocus();
    }
    
    public void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void addCalculateListener(ActionListener listener) {
        calculateButton.addActionListener(listener);
    }
    
    public void addClearListener(ActionListener listener) {
        clearButton.addActionListener(listener);
    }
    
    public void addThemeToggleListener(ActionListener listener) {
        themeToggle.addActionListener(listener);
    }
    
    public JToggleButton getThemeToggle() { 
        return themeToggle; 
    }
    
    public void setTheme(boolean isDarkMode) {
        // Define colors for dark and light mode
        Color darkBackground = new Color(50, 50, 50);
        Color darkForeground = new Color(220, 220, 220);
        Color lightBackground = new Color(240, 240, 240);
        Color lightForeground = new Color(30, 30, 30);
        
        // Set background and foreground colors for main components
        this.getContentPane().setBackground(isDarkMode ? darkBackground : lightBackground);
        
        // Update all input components
        firstNumber.setBackground(isDarkMode ? darkBackground : Color.WHITE);
        firstNumber.setForeground(isDarkMode ? darkForeground : lightForeground);
        secondNumber.setBackground(isDarkMode ? darkBackground : Color.WHITE);
        secondNumber.setForeground(isDarkMode ? darkForeground : lightForeground);
        calcSolution.setBackground(isDarkMode ? darkBackground : Color.WHITE);
        calcSolution.setForeground(isDarkMode ? darkForeground : lightForeground);
        
        // Update history area
        historyArea.setBackground(isDarkMode ? darkBackground : Color.WHITE);
        historyArea.setForeground(isDarkMode ? darkForeground : lightForeground);
        
        // Update buttons
        calculateButton.setBackground(isDarkMode ? new Color(70, 70, 70) : Color.LIGHT_GRAY);
        calculateButton.setForeground(isDarkMode ? darkForeground : lightForeground);
        clearButton.setBackground(isDarkMode ? new Color(70, 70, 70) : Color.LIGHT_GRAY);
        clearButton.setForeground(isDarkMode ? darkForeground : lightForeground);
        
        // Update toggle button text
        themeToggle.setText(isDarkMode ? " Light Mode" : " Dark Mode");
        
        // Update combo box
        operationComboBox.setBackground(isDarkMode ? darkBackground : Color.WHITE);
        operationComboBox.setForeground(isDarkMode ? darkForeground : lightForeground);
        
        // Update spinner
        decimalSpinner.setBackground(isDarkMode ? darkBackground : Color.WHITE);
        decimalSpinner.setForeground(isDarkMode ? darkForeground : lightForeground);
    }
}