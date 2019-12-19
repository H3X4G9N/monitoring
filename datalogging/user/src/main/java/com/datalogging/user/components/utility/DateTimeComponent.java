package com.datalogging.user.components.utility;

import com.datalogging.user.components.Component;
import com.datalogging.user.javafx.DLBuilder;
import com.datalogging.user.javafx.DLDatePicker;
import com.datalogging.user.javafx.DLTimeSpinner;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeComponent extends Component {
    public DLDatePicker datePicker;
    public DLTimeSpinner timeSpinner;

    public DateTimeComponent() {
        HBox container = new HBox();

        getContainer().getChildren().add(container);
        datePicker = new DLDatePicker(new DLBuilder());
        datePicker.setValue(LocalDate.now());
        container.getChildren().add(datePicker);
        timeSpinner = new DLTimeSpinner(new DLBuilder());
        container.getChildren().add(timeSpinner);
    }

    LocalDateTime getValue() {
        return LocalDateTime.of(datePicker.getValue(), timeSpinner.getValue());
    }
}
