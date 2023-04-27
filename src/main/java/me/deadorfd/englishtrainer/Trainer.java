package me.deadorfd.englishtrainer;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import me.deadorfd.englishtrainer.pages.AddVocabularyPage;
import me.deadorfd.englishtrainer.pages.ListVocabularyPage;
import me.deadorfd.englishtrainer.pages.MainPage;
import me.deadorfd.englishtrainer.pages.TranslatePage;
import me.deadorfd.englishtrainer.pages.VocabularyTestingPage;
import me.deadorfd.englishtrainer.utils.Data;

/**
 * @Author DeaDorfd
 * @Project englishtrainer
 * @Package me.deadorfd.englishtrainer
 * @Date 15.01.2023
 * @Time 22:59:47
 */
public class Trainer extends JFrame {

	private static JPanel cards;

	// Random pick for Testing, export and import vocabulary, vocabulary list with
	// remove button

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Trainer frame = new Trainer();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Trainer() {
		setResizable(false);
		setBackground(Data.color);
		setTitle(Data.programmName);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 694, 532);
		cards = new JPanel();
		cards.setBackground(Data.color);
		cards.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(cards);
		cards.setLayout(new CardLayout(0, 0));

		JPanel mainPage = new MainPage();
		cards.add(mainPage, "main");

		JPanel addVocabulary = new AddVocabularyPage();
		cards.add(addVocabulary, "vokabeladd");

		JPanel listVocabulary = new ListVocabularyPage();
		cards.add(listVocabulary, "vokabelliste");

		JPanel vocabularyTesting = new VocabularyTestingPage();
		cards.add(vocabularyTesting, "vokabeltesting");

		JPanel translator = new TranslatePage();
		cards.add(translator, "translator");
	}

	public static void switchScene(String name) {
		CardLayout cl = (CardLayout) (cards.getLayout());
		cl.show(cards, name);
	}
}