package br.dojo.counter;

import java.awt.BorderLayout;

import javax.swing.JFrame;
	
public class Janela extends JFrame{
	
	private static final long serialVersionUID = 7541261664781654462L;
	
	private static Janela instance;
	
	public Janela(){
		super("dojo.counter");
		instance = this;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.add(new PainelDefinirTempo(), BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
	}
	
	public static Janela getInstance(){
		return instance;
	}
}
