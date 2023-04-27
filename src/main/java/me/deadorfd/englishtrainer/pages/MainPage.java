package me.deadorfd.englishtrainer.pages;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import me.deadorfd.englishtrainer.Trainer;
import me.deadorfd.englishtrainer.utils.Data;

/**
 * @Author DeaDorfd
 * @Project englishtrainer
 * @Package me.deadorfd.englishtrainer.pages
 * @Date 15.01.2023
 * @Time 23:06:07
 */
public class MainPage extends JPanel {

	/**
	 * Create the panel.
	 */
	static JLabel amountVocabulary;

	public MainPage() {
		setBounds(100, 100, 666, 477);
		setBackground(Data.color);
		setLayout(null);

		JLabel titel = new JLabel(Data.programmName);
		titel.setHorizontalAlignment(SwingConstants.CENTER);
		titel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		titel.setBounds(197, 39, 249, 24);
		add(titel);

		JButton addVocabulary = new JButton("Vokabel hinzuf\u00FCgen");
		addVocabulary.setFont(new Font("Tahoma", Font.BOLD, 15));
		addVocabulary.addActionListener(e -> Trainer.switchScene("vokabeladd"));
		addVocabulary.setBounds(230, 162, 197, 23);
		add(addVocabulary);

		JButton listVocabulary = new JButton("Vokabel Liste");
		listVocabulary.setFont(new Font("Tahoma", Font.BOLD, 15));
		listVocabulary.setBounds(230, 212, 197, 23);
		listVocabulary.addActionListener(e -> {
			ListVocabularyPage.updateVocabularys();
			Trainer.switchScene("vokabelliste");
		});
		add(listVocabulary);

		JButton removeVocabulary = new JButton("Teste dich");
		removeVocabulary.setFont(new Font("Tahoma", Font.BOLD, 15));
		removeVocabulary.addActionListener(e -> {
			if (Data.vocabulary.size() != 0) {
				VocabularyTestingPage.startTest();
				Trainer.switchScene("vokabeltesting");
			} else {
				JOptionPane.showMessageDialog(null, "Du hast keine Vokabeln eingetragen damit du dich Testen kannst.",
						"Fehler", JOptionPane.ERROR_MESSAGE);
			}
		});
		removeVocabulary.setBounds(230, 262, 197, 23);
		add(removeVocabulary);

		JButton translate = new JButton("Übersetzter");
		translate.addActionListener(e -> Trainer.switchScene("translator"));
		translate.setFont(new Font("Tahoma", Font.BOLD, 16));
		translate.setBounds(230, 312, 197, 23);
		add(translate);

		int amount = 0;
		for (String vocabulary : Data.vocabulary) amount++;
		amountVocabulary = new JLabel("Es sind " + amount + " Vokabeln eingetragen");
		amountVocabulary.setHorizontalAlignment(SwingConstants.RIGHT);
		amountVocabulary.setFont(new Font("Tahoma", Font.BOLD, 16));
		amountVocabulary.setBounds(387, 442, 269, 24);
		add(amountVocabulary);

		JLabel creditsLabel = new JLabel("Gemacht von Dominik H. f\u00FCr Lara");
		creditsLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		creditsLabel.setBounds(10, 458, 206, 14);
		add(creditsLabel);
	}
}
