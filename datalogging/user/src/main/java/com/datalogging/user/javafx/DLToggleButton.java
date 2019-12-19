package com.datalogging.user.javafx;

import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

public class DLToggleButton extends ToggleButton {
    public DLToggleButton(DLBuilder builder) {
        setText(builder.getText());
        setSelected(builder.getSelected());
        setStyle("-fx-font-size:" + builder.getFontSize());
        VBox.setMargin(this, builder.getMargin());
    }
}
