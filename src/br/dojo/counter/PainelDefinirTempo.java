package br.dojo.counter;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PainelDefinirTempo extends JPanel{

	private static final long serialVersionUID = 9193415731488238747L;
	
	private JLabel labelTempo;
	private JTextField fieldTempo;
	private Timer timer;
	private JButton definir;
	
	private static int xContador;
	private static int yContador;
	
	public PainelDefinirTempo(){
		monta();
	}

	public static void setXContador(int contador) {
		xContador = contador;
	}

	public static void setYContador(int contador) {
		yContador = contador;
	}

	private void monta(){
		labelTempo = new JLabel("Tempo (minutos):");
		fieldTempo = new JTextField(5);
		definir = new JButton("Start");
		definir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				actionDefinir();
			}
		});
		
		GridBagConstraints cons = new GridBagConstraints();
		cons.insets = new Insets(2,2,2,2);

		this.setLayout(new GridBagLayout());

		cons.gridy = 0;
		cons.gridx = 0;
		this.add(labelTempo, cons);

		cons.gridy = 0;
		cons.gridx = 2;
		this.add(fieldTempo, cons);

		cons.gridy = 0;
		cons.gridx = 4;
		this.add(definir, cons);
	}

	private void actionDefinir(){
		String value = fieldTempo.getText();
		if (value != null && value.trim().length() > 0){
			try{

				int tempo = Integer.parseInt(value);
				if (tempo > 0){
					if (timer != null){
						this.remove(timer);					
					}

					GridBagConstraints cons = new GridBagConstraints();
					cons.insets = new Insets(2,2,2,2);
					cons.gridy = 0;
					cons.gridx = 3;

					Janela.getInstance().setAlwaysOnTop(false);
					Janela.getInstance().setExtendedState(JFrame.ICONIFIED);
					timer = new Timer(tempo, xContador, yContador);
					Janela.getInstance().setVisible(false);
					
				}else{
					JOptionPane.showMessageDialog(this, "Número positivo!","Erro",JOptionPane.ERROR_MESSAGE);
				}
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(this, "Digite um número inteiro!","Erro",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}






























