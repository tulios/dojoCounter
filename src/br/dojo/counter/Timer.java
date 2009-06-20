package br.dojo.counter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.Calendar;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Timer extends JDialog{
	
	private static final long serialVersionUID = -830730110248927593L;
	
	private int tempo;
	private Calendar cal;		
	
	private JLabel value;
	private boolean parar;
		
	public Timer(int tempo, int x, int y){	
		
		this.tempo = tempo;
		this.setLocation(x, y);
		
		this.setTitle("Contando");
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				parar = true;
				
				PainelDefinirTempo.setXContador(Timer.this.getLocationOnScreen().x);
				PainelDefinirTempo.setYContador(Timer.this.getLocationOnScreen().y);
				
				Janela.getInstance().setVisible(true);
				Janela.getInstance().setAlwaysOnTop(true);
				Janela.getInstance().setExtendedState(JFrame.NORMAL);
			}
		});
		
		preparaTempo();
		parar = false;
		
		this.setLayout(new BorderLayout());
		this.value = new JLabel(" 00:00 ");
		this.add(value, BorderLayout.CENTER);
		this.value.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,40));
		this.pack();
		
		init();		
		setVisible(true);
	}

	private void preparaTempo(){
		cal = Calendar.getInstance();
		cal.set(Calendar.MINUTE, tempo+cal.get(Calendar.MINUTE));//tempo definido + atual		
	}

	private void init(){		
		Runnable run = new Runnable() {
			public void run() {
				int minutos = 0;
				int segundos = 0;
				
				DecimalFormat df = new DecimalFormat("00");  				
				
				while (minutos != tempo && !parar){					
					try{
						segundos++;
						if (segundos == 60){
							segundos = 0;
							minutos++;
						}
						value.setText(" "+df.format(minutos)+":"+df.format(segundos)+" ");						
						Thread.sleep(1000);
					}catch(Exception e){}
				}
				if (!parar){
					acabou();
				}
			}
		};
		new Thread(run).start();
	}

	public int getTempo() {
		return tempo;
	}
	
	private void acabou(){
		this.value.setText("Acabou!");
		this.value.setForeground(Color.WHITE);
		this.getContentPane().setBackground(Color.RED);
		this.pack();
		this.validate();
	}
}













