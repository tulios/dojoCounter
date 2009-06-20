package br.dojo.counter;

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
		new Janela();
	}

}
