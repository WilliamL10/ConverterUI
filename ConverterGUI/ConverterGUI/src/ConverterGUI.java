import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.lang.Math.floor;

public class ConverterGUI extends JFrame {
	
	private final JTextField celsiusField, fahrenheitField, kelvinField;
	private final JTextField centimetersField, inchesField, feetField, 
					f_FeetField, f_InchField, yardsField, milesField;
	
	public ConverterGUI() {
		setTitle("Converter GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 550);
		
		// Center the window instead of creating it at top left of screen
		setLocationRelativeTo(null);
		
		setLayout(new GridLayout(14, 2, 0, 10));
		
		// Temperature
		//add(new JLabel("—— Temperature ———————————————————"));
		
		celsiusField = new JTextField();
		add(new JLabel("Celsius:"));
		add(celsiusField);
		
		fahrenheitField = new JTextField();
		add(new JLabel("Fahrenheit:"));
		add(fahrenheitField);
		
		kelvinField = new JTextField();
		add(new JLabel("Kelvin:"));
		add(kelvinField);
		
		
		// Length
		//add(new JLabel("—— Length ———————————————————"));
		
		centimetersField = new JTextField();
		add(new JLabel("Centimeters:"));
		add(centimetersField);
		
		inchesField = new JTextField();
		add(new JLabel("Inches:"));
		add(inchesField);
		
		feetField = new JTextField();
		add(new JLabel("Feet:"));
		add(feetField);
		
		
		// Separate text fields for Feet + Inches
		JPanel feetInchesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		f_FeetField = new JTextField(5);
		f_InchField = new JTextField(5);
		
		//f_FeetField.setPreferredSize(new Dimension(50, 30));
		//f_InchField.setPreferredSize(new Dimension(50, 30));
		
		feetInchesPanel.add(f_FeetField);
		feetInchesPanel.add(new JLabel("'"));
		feetInchesPanel.add(f_InchField);
		feetInchesPanel.add(new JLabel("\""));
		
		add(new JLabel("Feet + Inches:"));
		
		// Add the panel with two textboxes
		add(feetInchesPanel);
		
		
		yardsField = new JTextField();
		add(new JLabel("Yards:"));
		add(yardsField);
		
		milesField = new JTextField();
		add(new JLabel("Miles:"));
		add(milesField);
		
		// Add Convert Button
		JButton convertButton = new JButton("Convert");
		add(convertButton);
		
		// Add Clear Button
		JButton clearButton = new JButton("Clear");
		add(clearButton);
		
		// Functionality of Buttons
		convertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { performConversions(); }
		});
		
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { clearFields(); }
		});
		
		// Add key listeners to all text fields
		listenKeys(celsiusField);
		listenKeys(fahrenheitField);
		listenKeys(kelvinField);
		listenKeys(centimetersField);
		listenKeys(inchesField);
		listenKeys(feetField);
		listenKeys(f_FeetField);
		listenKeys(f_InchField);
		listenKeys(yardsField);
		listenKeys(milesField);
		
		setVisible(true);
	}
	
	// Perform conversion when the "Convert" button is clicked
	private void performConversions() {
		try {
			// Temperature
			
			// Convert from Celsius input
			if (!celsiusField.getText().isEmpty()) {
				double celsius = Double.parseDouble(celsiusField.getText());
				fahrenheitField.setText(String.format("%.2f", celsius * 9/5 + 32));
				kelvinField.setText(String.format("%.2f", celsius + 273.15));
			}
			// Convert from Fahrenheit input
			else if (!fahrenheitField.getText().isEmpty()) {
				double fahrenheit = Double.parseDouble(fahrenheitField.getText());
				celsiusField.setText(String.format("%.2f", (fahrenheit - 32) * 5/9 ));
				kelvinField.setText(String.format("%.2f", (fahrenheit - 32) * 5/9 + 273.15));
			}
			// Convert from Kelvin input
			else if (!kelvinField.getText().isEmpty()) {
				double kelvin = Double.parseDouble(kelvinField.getText());
				celsiusField.setText(String.format("%.2f", kelvin - 273.15));
				fahrenheitField.setText(String.format("%.2f", (kelvin - 273.15) * 9/5 + 32));
			}
			
			// Length
			
			// Convert from Centimeters input
			if (!centimetersField.getText().isEmpty()) {
				double centimeters = Double.parseDouble(centimetersField.getText());
				inchesField.setText(String.format("%.2f", centimeters / 2.54));
				feetField.setText(String.format("%.2f", centimeters / 30.48));
				yardsField.setText(String.format("%.2f", centimeters / 91.44));
				milesField.setText(String.format("%.5f", centimeters / 160934.4));
				
				f_FeetField.setText(String.format("%.0f", floor(centimeters / 30.48)));
				f_InchField.setText(String.format("%.2f", (centimeters / 2.54) % 12));
			}
			// Convert from Inches input
			else if (!inchesField.getText().isEmpty()) {
				double inches = Double.parseDouble(inchesField.getText());
				centimetersField.setText(String.format("%.2f", inches * 2.54));
				feetField.setText(String.format("%.2f", inches / 12));
				yardsField.setText(String.format("%.2f", inches / 36));
				milesField.setText(String.format("%.5f", inches / 63360));
				
				f_FeetField.setText(String.format("%.0f", floor(inches / 12)));
				f_InchField.setText(String.format("%.2f", inches % 12));
			}
			// Convert from Feet input
			else if (!feetField.getText().isEmpty()) {
				double feet = Double.parseDouble(feetField.getText());
				centimetersField.setText(String.format("%.2f", feet * 2.54));
				inchesField.setText(String.format("%.2f", feet * 12));
				yardsField.setText(String.format("%.2f", feet / 3));
				milesField.setText(String.format("%.5f", feet / 5280));
				
				f_FeetField.setText(String.format("%.0f", floor(feet)));
				f_InchField.setText(String.format("%.2f", (feet * 12) % 12));
			}
			// Convert from Feet + Inches input
			else if (!f_FeetField.getText().isEmpty() || !f_InchField.getText().isEmpty()) {
				double feet;
				if (f_FeetField.getText().isEmpty()) { feet = 0; } 
				else { feet = Double.parseDouble(f_FeetField.getText()); }
				
				double inches;
				if (f_InchField.getText().isEmpty()) { inches = 0; } 
				else { inches = Double.parseDouble(f_InchField.getText()); }
				
				// Convert from inches
				double totalInches = feet * 12 + inches;
				
				centimetersField.setText(String.format("%.2f", totalInches * 2.54));
				inchesField.setText(String.format("%.2f", totalInches));
				feetField.setText(String.format("%.2f", feet + (inches / 12)));
				yardsField.setText(String.format("%.2f", totalInches / 36));
				milesField.setText(String.format("%.5f", totalInches / 63360));
			}
			// Convert from Yards input
			else if (!yardsField.getText().isEmpty()) {
				double yards = Double.parseDouble(yardsField.getText());
				centimetersField.setText(String.format("%.2f", yards * 91.44));
				inchesField.setText(String.format("%.2f", yards * 36));
				feetField.setText(String.format("%.2f", yards * 3));
				milesField.setText(String.format("%.5f", yards * 1760));
				
				f_FeetField.setText(String.format("%.0f", floor(yards * 3)));
				f_InchField.setText(String.format("%.2f", (yards * 36) % 12));	// Normally always 0
			}
			// Convert from Miles input
			else if (!milesField.getText().isEmpty()) {
				double miles = Double.parseDouble(inchesField.getText());
				centimetersField.setText(String.format("%.2f", miles * 1609334));
				inchesField.setText(String.format("%.2f", miles * 63360));
				feetField.setText(String.format("%.2f", miles * 5280));
				yardsField.setText(String.format("%.2f", miles * 1760));
				
				f_FeetField.setText(String.format("%.0f", floor(miles * 5280)));
				f_InchField.setText(String.format("%.2f", (miles * 63360) % 12)); // Normally always 0
			}
		} 
		catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Invalid input");
		}
	}
	
	// Make all TextFields empty
	private void clearFields() {
		celsiusField.setText("");
		fahrenheitField.setText("");
		kelvinField.setText("");
		centimetersField.setText("");
		inchesField.setText("");
		feetField.setText("");
		f_FeetField.setText("");
		f_InchField.setText("");
		yardsField.setText("");
		milesField.setText("");
	}
	
	// Functionality of Keyboard Shortcuts
	private void listenKeys(JTextField textField) {
		textField.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// Shift + Enter
					if (e.isShiftDown()) { clearFields(); } 
					// Enter
					else { performConversions(); }
				}
			}
		});
	}
	
	public static void main(String[] args) {
		// Bring up the window from Constructor
		new ConverterGUI();
	}
}
