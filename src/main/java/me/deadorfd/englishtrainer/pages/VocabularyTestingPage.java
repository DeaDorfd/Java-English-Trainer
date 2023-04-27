package me.deadorfd.englishtrainer.pages;

import java.awt.Font;
import java.util.Random;

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
 * @Date 16.01.2023
 * @Time 00:01:31
 */
public class VocabularyTestingPage extends JPanel {
	private static JTextField textField;

	/**
	 * Create the panel.
	 */
	static JLabel testText;

	public VocabularyTestingPage() {
		setBounds(100, 100, 666, 477);
		setBackground(Data.color);
		setLayout(null);

		JLabel titel = new JLabel(Data.programmName);
		titel.setHorizontalAlignment(SwingConstants.CENTER);
		titel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		titel.setBounds(183, 37, 249, 24);
		add(titel);

		JButton backMenu = new JButton("Men\u00FC");
		backMenu.setFont(new Font("Tahoma", Font.BOLD, 15));
		backMenu.addActionListener(e -> Trainer.switchScene("main"));
		backMenu.setBounds(183, 426, 249, 23);
		add(backMenu);

		testText = new JLabel("Was ist das englische Wort von: \"Deutschland\" ?");
		testText.setHorizontalAlignment(SwingConstants.CENTER);
		testText.setFont(new Font("Tahoma", Font.BOLD, 15));
		testText.setBounds(10, 128, 646, 59);
		add(testText);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(291, 209, 216, 20);
		add(textField);
		textField.setColumns(10);

		JLabel labelForTextField = new JLabel("Deine Antwort:");
		labelForTextField.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelForTextField.setBounds(147, 210, 134, 14);
		add(labelForTextField);

		JButton testing = new JButton("Antwort Testen");
		testing.setFont(new Font("Tahoma", Font.BOLD, 15));
		testing.addActionListener(e -> {
			if (Data.vocabulary.size() == 0) {
				JOptionPane.showMessageDialog(null, "Du musst Vokabel hinzufügen bevor du dich testen kannst.",
						"Fehler", JOptionPane.ERROR_MESSAGE);
				return;
			}
			proofTest(textField.getText());
		});
		testing.setBounds(183, 256, 249, 23);
		add(testing);
	}

	static int testlanguageint = 0;
	static int testtextint = 0;

	public static void startTest() {
		int random = new Random().nextInt(Data.vocabulary.size());
		if (random == 0) random = 1;
		testlanguageint = random;
		String german = Data.getGermanName(random);
		String english = Data.getEnglishName(random);

		int i = new Random().nextInt(2);
		if (i == 0) i = 1;
		testtextint = i;
		if (i == 1) {
			testText.setText("Was ist das englische Wort von: \"" + german + "\" ?");
		} else if (i == 2) {
			testText.setText("Was ist das Deutsche Wort von: \"" + english + "\" ?");
		}
	}

	public static void proofTest(String answer) {
		String german = Data.getGermanName(testlanguageint);
		String english = Data.getEnglishName(testlanguageint);
		if (testtextint == 1) {
			if (english.equalsIgnoreCase(answer)) {
				JOptionPane.showMessageDialog(null, "Du hast erfolgreich die Vokabel richtig", "Richtig",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Du hast die Vokabel nicht richtig", "Falsch",
						JOptionPane.ERROR_MESSAGE);
			}
			textField.setText(null);
			startTest();
			return;
		} else if (testtextint == 2) {
			if (german.equalsIgnoreCase(answer)) {
				JOptionPane.showMessageDialog(null, "Du hast erfolgreich die Vokabel richtig", "Richtig",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Du hast die Vokabel nicht richtig", "Falsch",
						JOptionPane.ERROR_MESSAGE);
			}
			textField.setText(null);
			startTest();
			return;
		}
	}
}