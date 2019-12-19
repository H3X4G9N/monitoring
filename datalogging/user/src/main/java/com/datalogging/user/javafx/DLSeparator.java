package com.datalogging.user.javafx;

import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DLSeparator extends Separator {
    public DLSeparator(DLBuilder builder) {
        setOrientation(builder.getOrientation());
        VBox.setMargin(this, builder.getMargin());
        FlowPane.setMargin(this, builder.getMargin());
        HBox.setMargin(this, builder.getMargin());
    }
}
