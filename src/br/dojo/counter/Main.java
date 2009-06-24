package br.dojo.counter;

import javax.swing.UIManager;

public class Main {
	
	private static int tempo;
	
	public static int getTempo() {
		return tempo;
	}

	public static void setTempo(int tempo) {
		Main.tempo = tempo;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String lookAndFeelSistema = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(lookAndFeelSistema);
		} catch (Exception e) {
			String crossLookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
			try {
				UIManager.setLookAndFeel(crossLookAndFeel);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		new Janela();
	}

}
