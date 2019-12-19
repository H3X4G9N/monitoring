package com.datalogging.user.javafx;

import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class DLTextField extends TextField {
    public DLTextField(DLBuilder builder) {
        setText(builder.getText());
        setPromptText(builder.getPromptText());
        setEditable(builder.getEditable());
        setStyle("-fx-font-size:" + builder.getFontSize());
        VBox.setMargin(this, builder.getMargin());
    }
}
