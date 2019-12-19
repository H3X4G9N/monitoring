package com.datalogging.user.javafx;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DLLabel extends Label {
    public DLLabel(DLBuilder builder) {
        setText(builder.getText());
        setStyle("-fx-font-size:" + builder.getFontSize());
        VBox.setMargin(this, builder.getMargin());
    }
}
