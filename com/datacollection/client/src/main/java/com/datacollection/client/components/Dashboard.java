package com.datacollection.client.components;

import javafx.geometry.HPos;
import javafx.geometry.Insets;

import javafx.scene.control.Label;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import com.datacollection.client.components.Card;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.DatePicker;

public class Dashboard extends Component {
	public Dashboard() {
		ColumnConstraints column1Left = new ColumnConstraints();
		column1Left.setPercentWidth(1.0 / 1.0 * 100.0);

		ColumnConstraints column2Left = new ColumnConstraints();
		column2Left.setPercentWidth(1.0 / 2.0 * 100.0);

		ColumnConstraints column2Right = new ColumnConstraints();
		column2Right.setPercentWidth(1.0 / 2.0 * 100.0);
		column2Right.setHalignment(HPos.RIGHT);

		ColumnConstraints column3Left = new ColumnConstraints();
		column3Left.setPercentWidth(1.0 / 3.0 * 100.0);
		
		ColumnConstraints column3Center = new ColumnConstraints();
		column3Center.setPercentWidth(1.0 / 3.0 * 100.0);
		column3Center.setHalignment(HPos.CENTER);
	
			Label dashboardHeading = new Label("Dasboard");
			GridPane.setMargin(dashboardHeading, new Insets(0, 0, 8, 0));
			dashboardHeading.setStyle("-fx-font-size:32");
			this.getContainer().getChildren().add(dashboardHeading);
			
			GridPane sensorDataContainer = new GridPane();
			sensorDataContainer.getColumnConstraints().add(column3Left);
			sensorDataContainer.getColumnConstraints().add(column3Left);
			sensorDataContainer.getColumnConstraints().add(column3Left);
			sensorDataContainer.setPadding(new Insets(16, 16, 16, 16));
			sensorDataContainer.setStyle("-fx-background-color:#e3e3e3");
			this.getContainer().getChildren().add(sensorDataContainer);

			Card temperatureCard = new Card("Average temperature", "69 C");
			Card humidityCard = new Card("Humidity temperature", "69 %");
			Card carbonDioxideCard = new Card("Carbon dioxide", "69 PPM");
			
			sensorDataContainer.add(temperatureCard.getContainer(), 0, 0);
			sensorDataContainer.add(humidityCard.getContainer(), 1, 0);
			sensorDataContainer.add(carbonDioxideCard.getContainer(), 2, 0);
			
			
		      NumberAxis xAxis = new NumberAxis(1960, 2020, 10); 
		      xAxis.setLabel("Years"); 
		        
		      //Defining the y axis   
		      NumberAxis yAxis = new NumberAxis   (0, 350, 50); 
		      yAxis.setLabel("No.of schools"); 
		        
		      //Creating the line chart 
		      LineChart linechart = new LineChart(xAxis, yAxis);  
		        
		      //Prepare XYChart.Series objects by setting data 
		      XYChart.Series series = new XYChart.Series(); 

		      series.getData().add(new XYChart.Data(1970, 15)); 
		      series.getData().add(new XYChart.Data(1980, 30)); 
		      series.getData().add(new XYChart.Data(1990, 60)); 
		      series.getData().add(new XYChart.Data(2000, 120)); 
		      series.getData().add(new XYChart.Data(2013, 240)); 
		      series.getData().add(new XYChart.Data(2014, 300)); 
		            
		      //Setting the data to Line chart    
		      linechart.getData().add(series);    
		      
		      XYChart.Series series2 = new XYChart.Series(); 
		      series.setName("Temperature"); 
		        
		      series2.getData().add(new XYChart.Data(1970, 20)); 
		      series2.getData().add(new XYChart.Data(1980, 70)); 
		      series2.getData().add(new XYChart.Data(1990, 30)); 
		      series2.getData().add(new XYChart.Data(2000, 70)); 
		      series2.getData().add(new XYChart.Data(2013, 300)); 
		      series2.getData().add(new XYChart.Data(2014, 260));
		      series2.setName("Humidity");
		      linechart.getData().add(series2);
		      


		      this.getContainer().getChildren().add(linechart);
		      DatePicker date = new DatePicker();
		      
		      this.getContainer().getChildren().add(date);

	}
}