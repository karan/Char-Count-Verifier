/**
 * My first GUI program in Java using Swing.
 * This simple program prompts you for a name of a 
 * file that must exist in the same location as the
 * program and a character limit and then shows the 
 * lines which are over said limit.
 * 
 * I created this program before I started using Eclipse
 * so I could check my classes for crazy long lines.
 */

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Karan Goel
 * 
 */
public class charCountVerifier implements ActionListener {
	
	public static void main(String[] args) {
		new charCountVerifier();
	}
	
	private JFrame frame;
	private JPanel north;
	private JTextArea result;
	private JButton run;
	private JTextField inputFile, charLimit;
	
	public charCountVerifier() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(600, 300));
		frame.setLocation(new Point(400, 300));
		frame.setTitle("Character Count Verifier - Karan Goel");
		frame.setLayout(new BorderLayout());
		
		north = new JPanel(new GridLayout(1, 3));
		
		inputFile = new JTextField("File name with extension");
		north.add(inputFile);
		charLimit = new JTextField("Character limit per line");
		north.add(charLimit);
		run = new JButton("Run..");
		north.add(run);
		
		run.addActionListener(this);
		
		result = new JTextArea(5, 120);
		result.setFont(new Font("Serif", Font.BOLD, 15));
		
		frame.add(north, BorderLayout.NORTH);
		frame.add(new JScrollPane(result), BorderLayout.CENTER);
		
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			int limit = Integer.parseInt(charLimit.getText());
			String fileName = inputFile.getText();
			int i = 1;
			boolean flag = false;
			Scanner scan = new Scanner(new File(fileName));
			result.setText("");
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				if(line.length() > limit) {
					flag = true;
					result.append("Line #" + i + ": " + line + " - over by " + 
							(line.length() - limit) + " characters\n");
				}
				i++;
			}
			if(!flag) {
				result.setText("No line exceeds " + limit + " characters.");
			}
			scan.close();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "File not found. Make sure the file is in " + "" +
					"the same folder as this program.");
		}
	}
	
}