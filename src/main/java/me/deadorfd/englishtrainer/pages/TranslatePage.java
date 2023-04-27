package me.deadorfd.englishtrainer.pages;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import me.deadorfd.englishtrainer.Trainer;
import me.deadorfd.englishtrainer.utils.Data;
import me.deadorfd.englishtrainer.utils.Translator;

import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 * @Author DeaDorfd
 * @Project englishtrainer
 * @Package me.deadorfd.englishtrainer.pages
 * @Date 16.01.2023
 * @Time 02:04:32
 */
public class TranslatePage extends JPanel {

	private static JTextArea translatet;
	private static JTextArea userInput;

	/**
	 * Create the panel.
	 */
	public TranslatePage() {
		setBounds(100, 100, 666, 477);
		setBackground(Data.color);
		setLayout(null);

		JLabel titel = new JLabel(Data.programmName);
		titel.setHorizontalAlignment(SwingConstants.CENTER);
		titel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		titel.setBounds(183, 37, 249, 24);
		add(titel);

		userInput = new JTextArea();
		userInput.setFont(new Font("Monospaced", Font.PLAIN, 15));
		userInput.setBounds(113, 106, 394, 118);
		add(userInput);

		translatet = new JTextArea();
		translatet.setFont(new Font("Monospaced", Font.PLAIN, 15));
		translatet.setBounds(113, 262, 394, 118);
		add(translatet);

		JLabel translateToText = new JLabel("Übersetzten in:");
		translateToText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		translateToText.setBounds(113, 72, 110, 23);
		add(translateToText);

		JComboBox languageSelect = new JComboBox();
		languageSelect.setModel(new DefaultComboBoxModel(new String[] { "Englisch", "Deutsch" }));
		languageSelect.setFont(new Font("Tahoma", Font.PLAIN, 15));
		languageSelect.setBounds(233, 73, 100, 22);
		add(languageSelect);

		JLabel arrowDown = new JLabel("↓");
		arrowDown.setFont(new Font("Tahoma", Font.PLAIN, 26));
		arrowDown.setHorizontalAlignment(SwingConstants.CENTER);
		arrowDown.setBounds(248, 224, 100, 32);
		add(arrowDown);

		JButton btnNewButton = new JButton("Übersetzten");
		btnNewButton.addActionListener(e -> {
			translate(languageSelect.getSelectedItem().toString());
			translatet.updateUI();
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(378, 72, 129, 23);
		add(btnNewButton);

		JButton backMenu = new JButton("Men\u00FC");
		backMenu.setFont(new Font("Tahoma", Font.BOLD, 15));
		backMenu.addActionListener(e -> {
			Trainer.switchScene("main");
			userInput.setText(null);
			translatet.setText(null);
		});
		backMenu.setBounds(183, 426, 249, 23);
		add(backMenu);
	}

	private static void translate(String item) {
		if (item.equals("Englisch")) {
			new Thread(() -> {
				String translat = Translator.translate("de", "en", userInput.getText()).replaceAll("&#39;", "'");
				translatet.setText(translat);
			}).start();
		} else if (item.equals("Deutsch")) {
			new Thread(() -> {
				translatet.setText(Translator.translate("en", "de", userInput.getText()));
			}).start();
		}
	}
}