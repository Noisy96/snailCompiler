package mainPackage;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.Font;

public class Window extends JFrame {
	
	JFileChooser fileChooser = new JFileChooser();
	private String ChoosenFilePath;
	private FileReading fileReading;
	private static final long serialVersionUID = 1L;

	public Window() {
		initialize();
	}

	private void initialize() {
		
		this.setTitle("Analyse lexicale - syntaxique - s\u00E9matique");
		this.setResizable(false);
		this.setBounds(100, 100, 658, 630);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setVisible(true);
		fileChooser.setFileFilter(new FileNameExtensionFilter("SNAIL","SNL","snl"));
		
		JTextArea textArea = new JTextArea();		
		textArea.setMargin(new Insets(2, 10, 2, 2));
		textArea.setEditable(false);
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(20, 203, 613, 344);
		getContentPane().add(scroll);
		
		JLabel lblpath = new JLabel("(Vide)");
		lblpath.setBounds(236, 53, 397, 14);
		this.getContentPane().add(lblpath);
		
		JButton btnSemantique = new JButton("S\u00E9mantique");
		btnSemantique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText(fileReading.SemanticAnalysisFunction());
			}
		});
		btnSemantique.setIcon(new ImageIcon(Window.class.getResource("/icons/semantic.png")));
		btnSemantique.setEnabled(false);
		btnSemantique.setIconTextGap(15);
		btnSemantique.setBounds(441, 118, 192, 62);
		this.getContentPane().add(btnSemantique);
		
		JButton btnSyntaxe = new JButton("Syntaxe");
		btnSyntaxe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText(fileReading.syntaxAnalysisFunction());
				if(fileReading.getErrorCount()==0) btnSemantique.setEnabled(true);
			}
		});
		btnSyntaxe.setIconTextGap(15);
		btnSyntaxe.setIcon(new ImageIcon(Window.class.getResource("/icons/syntax.png")));
		btnSyntaxe.setEnabled(false);
		btnSyntaxe.setBounds(231, 118, 192, 62);
		this.getContentPane().add(btnSyntaxe);
		
		JButton btnLexicale = new JButton("Lexicale");
		btnLexicale.setIconTextGap(15);
		btnLexicale.setEnabled(false);
		btnLexicale.setIcon(new ImageIcon(Window.class.getResource("/icons/lexicale.png")));
		btnLexicale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText(fileReading.lexicalAnalysisFunction());
				btnSyntaxe.setEnabled(true);
			}
		});
		btnLexicale.setBounds(20, 118, 192, 62);
		this.getContentPane().add(btnLexicale);
		
		JLabel lblFileSize = new JLabel("Taille de fichier : 0 octects");
		lblFileSize.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFileSize.setBounds(20, 562, 208, 23);
		getContentPane().add(lblFileSize);
		
		JButton btnChargerUnFichier = new JButton("Charger un fichier");
		btnChargerUnFichier.setIconTextGap(15);
		btnChargerUnFichier.setIcon(new ImageIcon(Window.class.getResource("/icons/browse.png")));
		btnChargerUnFichier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileChooser.setCurrentDirectory(new java.io.File("C:\\Users\\Abdelhak\\Desktop\\School\\Projet COMP"));
				fileChooser.setDialogTitle("Choisissez le fichier");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.showOpenDialog(btnChargerUnFichier);
				ChoosenFilePath = fileChooser.getSelectedFile().getAbsolutePath();
				lblFileSize.setText("Taille de fichier : "+fileChooser.getSelectedFile().length()+" octects");
				lblpath.setText(ChoosenFilePath);
				btnLexicale.setEnabled(true);
				
				//awesome place i guess
				fileReading = new FileReading(ChoosenFilePath);
			}
		});
		btnChargerUnFichier.setBounds(20, 29, 192, 62);
		this.getContentPane().add(btnChargerUnFichier);
		
		JButton btnVider = new JButton("Vider");
		btnVider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
			}
		});
		btnVider.setBounds(544, 558, 89, 32);
		getContentPane().add(btnVider);
		
		JButton btnRinitialiser = new JButton("R\u00E9initialiser");
		btnRinitialiser.setMargin(new Insets(2, 2, 2, 2));
		btnRinitialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblFileSize.setText("Taille de fichier : 0 octects");
				lblpath.setText("Vide");
				textArea.setText("");
				btnLexicale.setEnabled(false);
				btnSyntaxe.setEnabled(false);
				btnSemantique.setEnabled(false);
			}
		});
		btnRinitialiser.setBounds(438, 558, 95, 32);
		getContentPane().add(btnRinitialiser);
		
		this.repaint();
	}
	
	public FileReading getFileReadingObject() {
		return fileReading;
	}
}
