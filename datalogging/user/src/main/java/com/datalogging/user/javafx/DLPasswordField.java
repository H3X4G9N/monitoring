package com.datalogging.user.javafx;

import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;

public class DLPasswordField extends PasswordField {
    public DLPasswordField(DLBuilder builder) {
        setText(builder.getText());
        setPromptText(builder.getPromptText());
        setEditable(builder.getEditable());
        setStyle("-fx-font-size:" + builder.getFontSize());
        VBox.setMargin(this, builder.getMargin());
    }
}
