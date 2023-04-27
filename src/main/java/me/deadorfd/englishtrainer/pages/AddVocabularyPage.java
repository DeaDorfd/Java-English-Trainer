package me.deadorfd.englishtrainer.pages;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import me.deadorfd.englishtrainer.Trainer;
import me.deadorfd.englishtrainer.utils.Data;

/**
 * @Author DeaDorfd
 * @Project englishtrainer
 * @Package me.deadorfd.englishtrainer.pages
 * @Date 15.01.2023
 * @Time 23:26:07
 */
public class AddVocabularyPage extends JPanel {

	/**
	 * Create the panel.
	 */

	private JTextField germanTextField;
	private JTextField englishTextField;

	public AddVocabularyPage() {
		setBounds(100, 100, 666, 477);
		setBackground(Data.color);
		setLayout(null);

		JButton summitButton = new JButton("Vokabel hinzuf\u00FCgen");
		summitButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		summitButton.addActionListener(e -> {
			if (!germanTextField.getText().equals("") || !englishTextField.getText().equals("")) {
				String german = germanTextField.getText();
				String english = englishTextField.getText();
				String vocabulary = german + "," + english;
				Data.vocabulary.add(vocabulary);
				germanTextField.setText(null);
				englishTextField.setText(null);
				JOptionPane.showMessageDialog(null, "Deine Vokabel wurde erfolgreich hinzugefügt", "Erfolgreich",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Du musst Deutsch und Englisch angeben", "Fehler",
						JOptionPane.ERROR_MESSAGE);
			}
		});
		summitButton.setBounds(183, 286, 249, 23);
		add(summitButton);

		germanTextField = new JTextField();
		germanTextField.setFont(new Font("Tahoma", Font.BOLD, 13));
		germanTextField.setBounds(282, 118, 150, 23);
		add(germanTextField);
		germanTextField.setColumns(10);

		JLabel germanLabel = new JLabel("Deutsch:");
		germanLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		germanLabel.setLabelFor(germanTextField);
		germanLabel.setBounds(183, 118, 89, 23);
		add(germanLabel);

		JLabel englishLabel = new JLabel("Englisch:");
		englishLabel.setLabelFor(englishLabel);
		englishLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		englishLabel.setBounds(183, 174, 89, 23);
		add(englishLabel);

		englishTextField = new JTextField();
		englishTextField.setFont(new Font("Tahoma", Font.BOLD, 13));
		englishTextField.setBounds(282, 174, 150, 23);
		add(englishTextField);
		englishTextField.setColumns(10);

		JLabel titel = new JLabel(Data.programmName);
		titel.setHorizontalAlignment(SwingConstants.CENTER);
		titel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		titel.setBounds(183, 37, 249, 24);
		add(titel);

		JButton backMenu = new JButton("Men\u00FC");
		backMenu.setFont(new Font("Tahoma", Font.BOLD, 15));
		backMenu.setBounds(183, 427, 249, 23);
		backMenu.addActionListener(e -> {
			int amount = Data.vocabulary.size();
			MainPage.amountVocabulary.setText("Es sind " + amount + " Vokabeln eingetragen");
			Trainer.switchScene("main");
		});
		add(backMenu);
	}

}
