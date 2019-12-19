package com.datalogging.user.javafx;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class DLCheckBox extends CheckBox {
    public DLCheckBox(DLBuilder builder) {
        setText(builder.getText());
        setSelected(builder.getSelected());
        setStyle("-fx-font-size:" + builder.getFontSize());
        VBox.setMargin(this, builder.getMargin());
    }
}
