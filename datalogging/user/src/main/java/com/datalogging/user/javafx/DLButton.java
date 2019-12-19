package com.datalogging.user.javafx;

import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DLButton extends Button {
    public DLButton(DLBuilder builder) {
        setText(builder.getText());
        setOnAction(builder.getActionEventEventHandler());
        setStyle("-fx-font-size:" + builder.getFontSize());
        VBox.setMargin(this, builder.getMargin());
        FlowPane.setMargin(this, builder.getMargin());
        HBox.setMargin(this, builder.getMargin());
    }
}
