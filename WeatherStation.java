import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.*;

import java.awt.event.*;
import java.util.Random;

public class WeatherStation {
    private boolean isRunning = false;
    private WheatherData weatherData = new WheatherData(); 
	JFrame mainFrm = new JFrame("Weather Station");
	
	private JToggleButton condDisplayBtn = new JToggleButton("Current condition display");
	private JToggleButton statDisplayBtn = new JToggleButton("Statistic display");
	private JToggleButton predDisplayBtn = new JToggleButton("Prediction display");
	private JButton setMes = new JButton("setMeasurements");
	
    class Go extends Thread{
    	public void run(){
    	float f1,f2,f3;
    	
    	Random rand = new Random();	    

    	do {
        	f1 = (float)(rand.nextInt((60 - 50) + 1) + 50);
        	f2 = (float)((float)(rand.nextInt((50 - 40) + 1) + 40));
        	f3 = (float)((float)(rand.nextInt((30 - 20) + 1) + 20));    		
    		weatherData.setMeasurements(f1,f2 ,f3);
            try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (isRunning);
    	}
    }
	public WeatherStation() {		

		
		CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
		StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
		ForecastDispley forecastDisplay = new ForecastDispley(weatherData);
		Go th = new Go();
		th.setDaemon(true);
		//Thread th = new Thread(mGo);
		setMes.addMouseListener(new MouseAdapter(){
		public void mouseClicked(MouseEvent event) {
			JPopupMenu popup = new JPopupMenu();
			popup.add(new JMenuItem("Run"));
			popup.add(new JMenuItem("Burn"));			
		if (SwingUtilities.isRightMouseButton(event))
		 popup.show(setMes, event.getX(), event.getY());
		}
		});


		setMes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				switch (th.getState()){
				case NEW :
					isRunning = true;
					th.start();
					break;
			    /*case TERMINATED :
				    isRunning = true;
				    Thread th = new Thread(mGo);
				    th.start();
                    break;*/
			    case RUNNABLE :  
			    	//isRunning = false;
			    	try {
						th.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};
			        break;
			    case WAITING : 
			    	th.interrupt();
				}
		    	/*float f1,f2,f3;
		    	
		    	Random rand = new Random();	
				f1 = (float)(rand.nextInt((60 - 50) + 1) + 50);
	        	f2 = (float)((float)(rand.nextInt((50 - 40) + 1) + 40));
	        	f3 = (float)((float)(rand.nextInt((30 - 20) + 1) + 20));    		
	    		weatherData.setMeasurements(f1,f2 ,f3);*/
				
			}
		});
		condDisplayBtn.addItemListener(new ItemListener() {
        	@Override
			public void itemStateChanged(ItemEvent ie) {
				if (condDisplayBtn.isSelected()) {
					currentDisplay.currFrm.setLocation(mainFrm.getLocation().x+mainFrm.getWidth(),mainFrm.getLocation().y );
					currentDisplay.currFrm.setVisible(true);
					}
				else {currentDisplay.currFrm.setVisible(false);}
				
			}
		});			
        statDisplayBtn.addItemListener(new ItemListener() {
        	@Override
			public void itemStateChanged(ItemEvent ie) {
				if (statDisplayBtn.isSelected()) {
					statisticsDisplay.statFrm.setLocation(mainFrm.getLocation().x+mainFrm.getWidth(),mainFrm.getLocation().y+statisticsDisplay.statFrm.getHeight() );
					statisticsDisplay.statFrm.setVisible(true);
					}
				else {statisticsDisplay.statFrm.setVisible(false);}
				
			}
		});	
        predDisplayBtn.addItemListener(new ItemListener() {
        	@Override
			public void itemStateChanged(ItemEvent ie) {
				if (predDisplayBtn.isSelected()) {
					forecastDisplay.forcaFrm.setLocation(mainFrm.getLocation().x+mainFrm.getWidth(),mainFrm.getLocation().y+statisticsDisplay.statFrm.getHeight()+forecastDisplay.forcaFrm.getHeight() );
					forecastDisplay.forcaFrm.setVisible(true);
					}
				else {forecastDisplay.forcaFrm.setVisible(false);}
				
			}
		});        
		mainFrm.setSize(200, 400);
		mainFrm.getContentPane().setLayout(new GridLayout(4,1));

		mainFrm.getContentPane().add(condDisplayBtn);
		mainFrm.getContentPane().add(statDisplayBtn);
		mainFrm.getContentPane().add(predDisplayBtn);
	    
		setMes.setBackground(Color.red);
		mainFrm.getContentPane().add(setMes);
		
		
		mainFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		//mainFrm.pack();
		mainFrm.setVisible(true);
		condDisplayBtn.doClick();
		statDisplayBtn.doClick();
		predDisplayBtn.doClick();
		

	}
	public static void main(String[] args) {
		 SwingUtilities.invokeLater(new Runnable() {  
		      public void run() {  
		        new WeatherStation();  
		      }  
		    }); 
	}

}
