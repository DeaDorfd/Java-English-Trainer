package me.deadorfd.englishtrainer.pages;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import me.deadorfd.englishtrainer.Trainer;
import me.deadorfd.englishtrainer.utils.Data;

import javax.swing.JButton;
import javax.swing.JFileChooser;

/**
 * @Author DeaDorfd
 * @Project englishtrainer
 * @Package me.deadorfd.englishtrainer.pages
 * @Date 15.01.2023
 * @Time 23:34:05
 */
public class ListVocabularyPage extends JPanel {

	/**
	 * Create the panel.
	 */

	static int starty = 122;
	static int currentpage = 1;
	static JPanel panel;

	static JButton arrowNextPage;
	static JButton arrowBackPage;
	static JButton arrowNextPageBig;

	static ArrayList<JLabel> germanLabel = new ArrayList<>();
	static ArrayList<JLabel> englishLabel = new ArrayList<>();
	static ArrayList<JButton> removeButton = new ArrayList<>();

	public ListVocabularyPage() {
		setBounds(100, 100, 666, 477);
		setBackground(Data.color);
		setLayout(null);
		panel = this;

		JLabel titel = new JLabel(Data.programmName);
		titel.setHorizontalAlignment(SwingConstants.CENTER);
		titel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		titel.setBounds(183, 37, 249, 24);
		add(titel);

		JLabel germanLabel = new JLabel("Deutsch");
		germanLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		germanLabel.setHorizontalAlignment(SwingConstants.CENTER);
		germanLabel.setBounds(75, 72, 72, 24);
		add(germanLabel);

		JLabel englishLabel = new JLabel("Englisch");
		englishLabel.setHorizontalAlignment(SwingConstants.CENTER);
		englishLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		englishLabel.setBounds(269, 72, 72, 24);
		add(englishLabel);

		JLabel removeLabel = new JLabel("Entfernen");
		removeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		removeLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		removeLabel.setBounds(452, 72, 99, 24);
		add(removeLabel);
		starty = 122; // +40
		for (int j = currentpage; j < currentpage + 5; j++) {
			addVocabularyToList(j, starty);
			starty += 40;
		}
		arrowBackPage = new JButton("<--");
		arrowBackPage.setFont(new Font("Tahoma", Font.BOLD, 15));
		arrowBackPage.setBounds(183, 390, 118, 23);
		arrowBackPage.setVisible(false);
		arrowBackPage.addActionListener(e -> {
			currentpage -= 6;
			if (currentpage == 1) {
				arrowBackPage.setVisible(false);
				arrowNextPage.setVisible(false);
				arrowNextPageBig.setVisible(true);
			}
			updateVocabularys();
			updateUI();
		});
		add(arrowBackPage);

		arrowNextPage = new JButton("-->");
		arrowNextPage.setFont(new Font("Tahoma", Font.BOLD, 15));
		arrowNextPage.setBounds(314, 390, 118, 23);
		arrowNextPage.setVisible(false);
		arrowNextPage.addActionListener(e -> {
			currentpage += 6;
			updateVocabularys();
			if (Data.vocabulary.size() < currentpage) arrowNextPage.setVisible(false);
		});
		add(arrowNextPage);

		arrowNextPageBig = new JButton("-->");
		arrowNextPageBig.setFont(new Font("Tahoma", Font.BOLD, 15));
		arrowNextPageBig.setBounds(183, 390, 249, 23);
		if (Data.vocabulary.size() < currentpage + 5) arrowNextPageBig.setVisible(false);
		arrowNextPageBig.addActionListener(e -> {
			currentpage += 6;
			arrowNextPageBig.setVisible(false);
			if (Data.vocabulary.size() > currentpage) arrowNextPage.setVisible(true);
			arrowBackPage.setVisible(true);
			updateVocabularys();
		});
		add(arrowNextPageBig);

		JButton backMenu = new JButton("Menü");
		backMenu.addActionListener(e -> Trainer.switchScene("main"));
		backMenu.setFont(new Font("Tahoma", Font.BOLD, 15));
		backMenu.setBounds(183, 443, 249, 23);
		add(backMenu);

		JButton importButton = new JButton("Importieren");
		importButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		importButton.setToolTipText("Importiert Vokabeln aus einer Datei");
		importButton.addActionListener(e -> {
			JFileChooser chooser = new JFileChooser();
			chooser.setSelectedFile(new File("Vokabel.yml"));
			int i = chooser.showOpenDialog(ListVocabularyPage.this);
			if (i == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				if (file.exists()) {
					BufferedReader br = null;
					try {
						br = new BufferedReader(new FileReader(file.getPath()));
					} catch (FileNotFoundException e1) {}
					String st;
					try {
						if (br.readLine().equals("#VocabularyFile")) {
							while ((st = br.readLine()) != null) {
								if (st.equals("#VocabularyFile")) continue;
								Data.vocabulary.add(st);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Diese Datei ist keine Vokabel Datei!", "Fehler",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (IOException e1) {}
					updateVocabularys();
				}
			}
		});
		importButton.setBounds(525, 392, 131, 23);
		add(importButton);

		JButton exportButton = new JButton("Exportieren");
		exportButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		exportButton.setToolTipText("Exportiert deine Vokabeln in einer Datei");
		exportButton.addActionListener(e -> {
			JFileChooser chooser = new JFileChooser();
			chooser.setSelectedFile(new File("Vokabel.yml"));
			int i = chooser.showSaveDialog(ListVocabularyPage.this);
			if (i == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				try {
					if (!file.exists()) file.createNewFile();
					FileWriter writer = new FileWriter(file);
					writer.write(getVocabulayAsString());
					writer.close();
					JOptionPane.showMessageDialog(null, "Deine Vokabeln wurden erfolgreich exportiert", "Erfolgreich",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		exportButton.setBounds(525, 426, 131, 23);
		add(exportButton);
	}

	private static String getVocabulayAsString() {
		String str = "#VocabularyFile \n";
		for (String vocabulary : Data.vocabulary) {
			str = str + vocabulary + "\n";
		}
		return str;
	}

	public static void addVocabularyToList(int count, int y) {
		String german = Data.getGermanName(count);
		String english = Data.getEnglishName(count);

		JLabel vocabularyGerman = new JLabel(german);
		vocabularyGerman.setFont(new Font("Tahoma", Font.PLAIN, 15));
		vocabularyGerman.setBounds(75, y, 184, 24);
		germanLabel.add(vocabularyGerman);
		if (Data.vocabulary.size() < count) vocabularyGerman.setVisible(false);
		panel.add(vocabularyGerman);

		JLabel vocabularyEnglish = new JLabel(english);
		vocabularyEnglish.setFont(new Font("Tahoma", Font.PLAIN, 15));
		vocabularyEnglish.setBounds(269, y, 163, 24);
		englishLabel.add(vocabularyEnglish);
		if (Data.vocabulary.size() < count) vocabularyEnglish.setVisible(false);
		panel.add(vocabularyEnglish);

		JButton removeVocabularyButton = new JButton("Entfernen");
		removeVocabularyButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		removeVocabularyButton.setBounds(452, y, 99, 23);
		removeVocabularyButton.addActionListener(e -> {
			Data.vocabulary.remove(count - 1);
			System.out.println(Data.vocabulary.size());
			starty = 122;
			updateVocabularys();
		});
		removeButton.add(removeVocabularyButton);
		if (Data.vocabulary.size() < count) removeVocabularyButton.setVisible(false);
		panel.add(removeVocabularyButton);
	}

	public static void updateVocabularys() {
		int amount = Data.vocabulary.size();
		MainPage.amountVocabulary.setText("Es sind " + amount + " Vokabeln eingetragen");
		int germancount = currentpage - 1;
		for (JLabel german : germanLabel) {
			germancount++;
			if (Data.vocabulary.size() >= germancount) {
				german.setVisible(true);
				german.setText(Data.getGermanName(germancount));
				continue;
			}
			german.setVisible(false);
			continue;
		}
		int englishcount = currentpage - 1;
		for (JLabel english : englishLabel) {
			englishcount++;
			if (Data.vocabulary.size() >= englishcount) {
				english.setVisible(true);
				english.setText(Data.getEnglishName(englishcount));
				continue;
			}
			english.setVisible(false);
			continue;
		}
		int removecount = currentpage - 1;
		for (JButton remove : removeButton) {
			removecount++;
			if (Data.vocabulary.size() >= removecount) {
				remove.setVisible(true);
				continue;
			}
			remove.setVisible(false);
			continue;
		}
		if (currentpage != 1) {
			if (Data.vocabulary.size() < currentpage + 5) arrowNextPage.setVisible(false);
			if (Data.vocabulary.size() > currentpage + 5) arrowNextPage.setVisible(true);
		} else {
			if (Data.vocabulary.size() < currentpage + 5) arrowNextPageBig.setVisible(false);
			if (Data.vocabulary.size() > currentpage + 5) arrowNextPageBig.setVisible(true);
		}
		panel.updateUI();
	}
}