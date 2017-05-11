package com.coding4fun.main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.math.BigInteger;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Main {

	private JFrame frame;
	private JLabel resultLBL, resultsLBL;
	private JTextField textField;
	String kk="",qq="",aaa="",prev="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Miller Rabin");
		frame.getContentPane().setBackground(Color.decode("#26A69A"));
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 11, 424, 45);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		/*textField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar()>='0' && e.getKeyChar()<='9'){
					checkInput(textField.getText().toString()+e.getKeyChar());
				} else{
					e.consume();
					JOptionPane.showMessageDialog(null, "Only integers are allowed!", "ERROR!", JOptionPane.ERROR_MESSAGE);
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});*/
		
		textField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				checkInput(textField.getText().toString());
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				//JOptionPane.showMessageDialog(null, textField.getText().toString());
				checkInput(textField.getText().toString());
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
			}
		});
		
		resultLBL = new JLabel("Enter an integer above");
		resultLBL.setBounds(10, 108, 424, 70);
		resultLBL.setHorizontalAlignment(SwingConstants.CENTER);
		resultLBL.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		resultLBL.setForeground(Color.decode("#b2dfdb"));//e0f2f1 004d40
		frame.getContentPane().add(resultLBL);
		
		resultsLBL = new JLabel("");
		resultsLBL.setHorizontalAlignment(SwingConstants.CENTER);
		resultsLBL.setFont(new Font("Tahoma", Font.BOLD, 14));
		resultsLBL.setForeground(Color.decode("#b2dfdb"));
		resultsLBL.setBounds(10, 236, 424, 24);
		frame.getContentPane().add(resultsLBL);
		
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void checkInput(String x){
		if(x.isEmpty()){
			resultLBL.setText("Enter an integer above");
			resultLBL.setForeground(Color.decode("#b2dfdb"));
			resultsLBL.setText("");
		} else {
			try{
				BigInteger n = new BigInteger(x);
				prev=x;
				if(isPrime(n)){
					resultLBL.setText("maybe PRIME :)");
					resultLBL.setForeground(Color.decode("#ffffff"));
					resultsLBL.setText("k = "+kk+"    q = "+qq+"    a = "+aaa);
					if(n.intValue()==1 || n.intValue()==2 || n.intValue()==3)
						resultsLBL.setText("");
				} else {
					resultLBL.setText("Composite! :/");
					resultLBL.setForeground(Color.decode("#004d40"));
					resultsLBL.setText("");
				}
			} catch(Exception e){
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "Only integers are allowed!", "ERROR!", JOptionPane.ERROR_MESSAGE);
				Runnable r = new Runnable() {
					@Override
					public void run() {
						textField.setText(prev);
					}
				};
				SwingUtilities.invokeLater(r);
			}
		}
	}
	
	public boolean isPrime(BigInteger n){
		//check if n equals one or two, bcz Miller Rabin algo works when x>=3
		if(n.equals(BigInteger.ONE) || n.equals(BigInteger.valueOf(2L)) || n.equals(BigInteger.valueOf(3L)))
			return true;
		//check if n is even, then not prime
		if(n.mod(BigInteger.valueOf(2L)).equals(BigInteger.ZERO))	
			return false;
		//find k & q
		BigInteger q = n.subtract(BigInteger.ONE);
		BigInteger k = BigInteger.ZERO;
		while(q.mod(BigInteger.valueOf(2L)).equals(BigInteger.ZERO)){
			q = q.shiftRight(1);	//equivalent to dividing by 2
			k = k.add(BigInteger.ONE);
		}
		//generate random number 'a' where 1<a<n-1
		Random r = new Random();
		int aa= (n.compareTo(BigInteger.valueOf(Integer.MAX_VALUE))>0) ? r.nextInt(Integer.MAX_VALUE-2-2+1)+2 : r.nextInt(n.intValue()-2-2+1)+2;
		BigInteger a = BigInteger.valueOf((long)aa);
		kk=k.toString();qq=q.toString();aaa=a.toString();
		//checking 1st property
		if(a.modPow(q, n).equals(BigInteger.ONE))
			return true;
		//checking 2nd property
		for(int j=0;BigInteger.valueOf((long)j).compareTo(k) < 0;j++){
			if(a.modPow(BigInteger.valueOf((long)2).pow(j).multiply(q),n).equals(n.subtract(BigInteger.ONE)))
				return true;
		}
		return false;
	}
}
