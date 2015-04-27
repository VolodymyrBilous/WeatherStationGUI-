import javax.swing.*;

public class CurrentConditionsDisplay implements Observer,DisplayElement{

	private float temperature;
	private float humidity;
	private float pressure;
	private Subject weatherData;
	public JFrame currFrm;
	public JTextField txt;
	
	public CurrentConditionsDisplay(Subject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
		currFrm = new JFrame("Current Conditions Display");
		currFrm.setSize(350, 50);
		txt = new JTextField();
		txt.setHorizontalAlignment(SwingConstants.LEFT);;
		currFrm.getContentPane().add(txt);		
	}
	
	public void update(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		display();
	}
	
	public void display() {
		//System.out.println("Current conditions: " + temperature 
		//	+ "F degrees and " + humidity + "% humidity" + " and pressure "+pressure);
		txt.setText("Current conditions: " + temperature 
					+ "F degrees and " + humidity + "% humidity" + " and pressure "+pressure);		
	} 

}
