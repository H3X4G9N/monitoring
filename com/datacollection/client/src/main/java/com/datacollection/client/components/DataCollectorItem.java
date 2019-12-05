package com.datacollection.client.components;

import javafx.scene.text.Text;

public class DataCollectorItem extends Component {
	private String name;
	private float carbonDioxide;
	private float humidity;
	private float temperature;
	private Text nameValue;
	private Text carbonDioxideValue;
	private Text humidityValue;
	private Text temperatureValue;
	
	public DataCollectorItem(String name, float carbonDioxide, float humidity, float temperature) {
		this.name = name;
		this.carbonDioxide = carbonDioxide;
		this.humidity = humidity;
		this.temperature = temperature;
		
		this.nameValue = new Text(this.name);
		this.getContainer().getChildren().add(this.nameValue);
		
		Text carbonDioxideKey = new Text("Carbon dioxide: ");
		this.getContainer().getChildren().add(carbonDioxideKey);
		
		this.carbonDioxideValue = new Text(String.valueOf(this.carbonDioxide));
		this.getContainer().getChildren().add(carbonDioxideKey);
		
		Text humidityKey = new Text("Humidity: ");
		this.getContainer().getChildren().add(humidityKey);
		
		this.humidityValue = new Text(String.valueOf(this.humidity));
		this.getContainer().getChildren().add(carbonDioxideKey);
		
		Text temperatureKey = new Text("Temperature: ");
		this.getContainer().getChildren().add(temperatureKey);
		
		this.temperatureValue = new Text(String.valueOf(this.temperature));
		this.getContainer().getChildren().add(carbonDioxideKey);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setCarbonDioxide(float carbonDioxide) {
		this.carbonDioxide = carbonDioxide;
		this.carbonDioxideValue.setText(String.valueOf(this.carbonDioxide));
	}
	
	public float getCarbonDioxide() {
		return carbonDioxide;
	}
	
	public void setHumidity(float humidity) {
		this.humidity = humidity;
		this.humidityValue.setText(String.valueOf(this.humidity));
	}
	
	public float getHumidity() {
		return humidity;
	}
	
	public void setTemperature(float temperature) {
		this.temperature = temperature;
		this.temperatureValue.setText(String.valueOf(this.temperature));
	}
	
	public float getTemperature() {
		return temperature;
	}
}