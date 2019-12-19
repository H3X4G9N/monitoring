package com.datalogging.user.javafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;

public class DLBuilder {
    private String text = "";
    private Boolean selected = false;
    private Object value = null;
    private String promptText = "";
    private Boolean editable = true;
    private EventHandler<ActionEvent> actionEventEventHandler = null;
    private Integer fontSize = 16;
    private Insets margin = new Insets(0, 0, 8, 0);
    private Orientation orientation;

    public DLBuilder setOrientation(Orientation orientation) {
        this.orientation = orientation;
        return this;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public String getText() {
        return text;
    }

    public DLBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public Boolean getSelected() {
        return selected;
    }

    public DLBuilder setSelected(Boolean selected) {
        this.selected = selected;
        return this;
    }

    public Object getValue() {
        return value;
    }

    public DLBuilder setValue(Object value) {
        this.value = value;
        return this;
    }


    public String getPromptText() {
        return promptText;
    }

    public DLBuilder setPromptText(String promptText) {
        this.promptText = promptText;
        return this;
    }

    public Boolean getEditable() {
        return editable;
    }

    public DLBuilder setEditable(Boolean editable) {
        this.editable = editable;
        return this;
    }

    public EventHandler<ActionEvent> getActionEventEventHandler() {
        return actionEventEventHandler;
    }

    public DLBuilder setActionEventEventHandler(EventHandler<ActionEvent> actionEventEventHandler) {
        this.actionEventEventHandler = actionEventEventHandler;
        return this;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public DLBuilder setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public Insets getMargin() {
        return margin;
    }

    public DLBuilder setMargin(Insets margin) {
        this.margin = margin;
        return this;
    }
}
