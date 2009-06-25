package br.dojo.counter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.Calendar;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

public class Timer extends JDialog{
	
	private static final long serialVersionUID = -830730110248927593L;
	
	private Calendar cal;		
	private JLabel apresentacaoTempo;
	private JToggleButton pauseButton;

	private boolean pause;
	
	private int tempoAlvo;	
	private int minutos;
	private int segundos;
	
	public Timer(int tempoAlvo, int x, int y){	
		this.tempoAlvo = tempoAlvo;
		this.setLocation(x, y);
		
		preparaDesign();
		preparaTempo();
		
		setVisible(true);
		init();
	}
	
	public int getTempoAlvo() {
		return tempoAlvo;
	}

	private void actionPause(){
		if (pause){
			pauseButton.setText("Pause");
			init();
		}else{
			pause = true;
			pauseButton.setText("Play");
		}
	}
	
	private void preparaTempo(){
		cal = Calendar.getInstance();
		cal.set(Calendar.MINUTE, tempoAlvo+cal.get(Calendar.MINUTE));//tempo definido + atual		
	}

	private void init(){	
		//se nao estiver pausado começa de zero
		if (!pause){
			minutos = 0;
			segundos = 0;
			
		//se estiver voltando do pause
		}else{
			pause = false;
		}
		
		Runnable run = new Runnable() {
			public void run() {
				int min = minutos;
				int seg = segundos;
				
				DecimalFormat df = new DecimalFormat("00");  				
				
				do{			
					
					try{
						apresentacaoTempo.setText(" "+df.format(min)+":"+df.format(seg)+" ");
						
						Thread.sleep(1000);// 1s

						seg++;
						if (seg == 60){
							seg = 0;
							min++;
						}
						
						segundos = seg;
						minutos = min;
						
					}catch(Exception e){}
					
				}while (min != tempoAlvo && !pause);
				
				//completou o tempo
				if (min == tempoAlvo){
					acabou();
				}
			}
		};
		new Thread(run).start();
	}

	private void acabou(){
		this.pauseButton.setVisible(false);
		this.apresentacaoTempo.setText("Acabou!");
		this.apresentacaoTempo.setForeground(Color.WHITE);
		this.getContentPane().setBackground(Color.RED);
		this.pack();
		this.validate();
	}
	
	private void actionFecharJanela(){
		pause = true;
		
		PainelDefinirTempo.setXContador(Timer.this.getLocationOnScreen().x);
		PainelDefinirTempo.setYContador(Timer.this.getLocationOnScreen().y);
		
		Janela.getInstance().setVisible(true);
		Janela.getInstance().setAlwaysOnTop(true);
		Janela.getInstance().setExtendedState(JFrame.NORMAL);
	}
	
	private void preparaDesign() {
		this.setTitle("Contando");
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				actionFecharJanela();
			}
		});
		
		this.setLayout(new BorderLayout());
		this.apresentacaoTempo = new JLabel(" 00:00 ");
		this.add(apresentacaoTempo, BorderLayout.CENTER);
		this.apresentacaoTempo.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,40));
		
		this.pauseButton = new JToggleButton("Pause");
		this.add(pauseButton, BorderLayout.PAGE_END);

		this.pauseButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				actionPause();
			}
		});
		
		this.pack();
	}
}













