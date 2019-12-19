package com.datalogging.user.javafx;

import javafx.scene.control.DatePicker;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DLDatePicker extends DatePicker {
    public DLDatePicker(DLBuilder builder) {
        setStyle("-fx-font-size:" + builder.getFontSize());
        VBox.setMargin(this, builder.getMargin());
        FlowPane.setMargin(this, builder.getMargin());
        HBox.setMargin(this, builder.getMargin());
    }
}
