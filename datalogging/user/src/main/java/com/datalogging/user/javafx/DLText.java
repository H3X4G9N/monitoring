package com.datalogging.user.javafx;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DLText extends Text {
    public DLText(DLBuilder builder) {
        setText(builder.getText());
        setStyle("-fx-font-size:" + builder.getFontSize());
        VBox.setMargin(this, builder.getMargin());
    }
}
