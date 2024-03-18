package iofile;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JScrollPane;

public class notepad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					notepad frame = new notepad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public notepad() {
		setTitle("Notepad");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(128, 128, 128));
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem Open = new JMenuItem("Open");
		mnNewMenu.add(Open);
		Open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openFile();
			}
		});
		
		JMenuItem save = new JMenuItem("Save");
		mnNewMenu.add(save);
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				saveFile();
			}
		});
		  contentPane = new JPanel();
	      setContentPane(contentPane);
	      contentPane.setLayout(new BorderLayout());

	      textArea = new JTextArea();
	      textArea.setBackground(new Color(192, 192, 192));
	      JScrollPane scrollPane = new JScrollPane(textArea);
	      contentPane.add(scrollPane, BorderLayout.CENTER);

	      addComponentListener(new java.awt.event.ComponentAdapter() {
	            public void componentResized(java.awt.event.ComponentEvent evt) {
	                textArea.setSize(contentPane.getSize());
	            }
	        });
	    
	}
	JTextArea textArea = new JTextArea();
	JFileChooser fileChooser = new JFileChooser();
	 private void openFile() {
	        int returnVal = fileChooser.showOpenDialog(this);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fileChooser.getSelectedFile();
	            try {
	                BufferedReader br = new BufferedReader(new FileReader(file));
	                textArea.setText("");
	                String line;
	                while ((line = br.readLine()) != null) {
	                    textArea.append(line + "\n");
	                }
	                br.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	 private void saveFile() {
	        int returnVal = fileChooser.showSaveDialog(this);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fileChooser.getSelectedFile();
	            try {
	                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
	                bw.write(textArea.getText());
	                bw.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}
