/**
 * My first GUI program in Java using Swing.
 * This simple program prompts you for a name of a 
 * file that must exist in the same location as the
 * program and them shows the lines which are over 
 * MAX_LINE_LENGTH.
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
public class over100 implements ActionListener {

	private static final int MAX_LINE_LENGTH = 100;
	
	public static void main(String[] args) {
		new over100();
	}
	
	private JFrame frame;
	private JPanel north;
	private JTextArea result;
	private JButton run;
	private JTextField inputFile;
	
	public over100() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(550, 400));
		frame.setLocation(new Point(400, 300));
		frame.setTitle(MAX_LINE_LENGTH + " Character Count Verifier v1 - Karan Goel");
		frame.setLayout(new BorderLayout());
		
		north = new JPanel(new GridLayout(1, 2));
		
		inputFile = new JTextField();
		north.add(inputFile);
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
			String fileName = inputFile.getText();
			int i = 1;
			boolean flag = false;
			Scanner scan = new Scanner(new File(fileName));
			result.setText("");
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				if(line.length() > MAX_LINE_LENGTH) {
					flag = true;
					result.append("Line #" + i + ": " + line + " over by " + 
							(line.length() - MAX_LINE_LENGTH) + " characters\n");
				}
				i++;
			}
			if(!flag) {
				result.setText("No line exceeds " + MAX_LINE_LENGTH + " characters.");
			}
			scan.close();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "File not found. Make sure the file is in " + "" +
					"the same folder as this program.");
		}
	}
	
}