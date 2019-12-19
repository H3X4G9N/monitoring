package com.datalogging.user.javafx;

import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class DLComboBox extends ComboBox {
    public DLComboBox(DLBuilder builder) {
        setValue(builder.getValue());
        setEditable(builder.getEditable());
        setStyle("-fx-font-size:" + builder.getFontSize());
        VBox.setMargin(this, builder.getMargin());
    }
}
