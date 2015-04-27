import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class ForecastDispley implements Observer,DisplayElement{
	private float temperature= 0;
	private float humidity= 0;
	private float pressure= 0;
	String str ;
	private Subject weatherData ;
	public JFrame forcaFrm;
	public JTextField txt;
	
	@Override
	public void display() {
		// TODO Auto-generated method stub
		txt.setText(str.toString());
	}

	@Override
	public void update(float temp, float humidity, float pressure) {
		
		if(this.temperature < temperature) str = new String("Will be warmer");
		else str = new String("Will cooler");
		
		if ((this.humidity <  humidity)&&(this.pressure>pressure)) str = new String("Will be no rain");
		else str = new String("May be rain");
		display();
	}

	public ForecastDispley(Subject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
		forcaFrm = new JFrame("Forecast Display");
		forcaFrm.setSize(350, 50);
		txt = new JTextField();
		txt.setHorizontalAlignment(SwingConstants.LEFT);;
		forcaFrm.getContentPane().add(txt);
		
	}

}
