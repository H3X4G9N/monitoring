package com.datalogging.client.components;

import com.datalogging.client.Controller;
import com.datalogging.client.components.utility.CardComponent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class DashboardComponent extends Component {
    public DashboardComponent(Controller controller) {
        super(controller);
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

        CardComponent temperatureCard = new CardComponent(getController(),"Average temperature", "69 C");
        CardComponent humidityCard = new CardComponent(getController(),"Humidity temperature", "69 %");
        CardComponent carbonDioxideCard = new CardComponent(getController(),"Carbon dioxide", "69 PPM");

        sensorDataContainer.add(temperatureCard.getContainer(), 0, 0);
        sensorDataContainer.add(humidityCard.getContainer(), 1, 0);
        sensorDataContainer.add(carbonDioxideCard.getContainer(), 2, 0);
    }
}