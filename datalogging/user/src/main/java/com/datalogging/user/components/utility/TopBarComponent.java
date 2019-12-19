package com.datalogging.user.components.utility;

import com.datalogging.user.components.Component;
import com.datalogging.user.javafx.DLBuilder;
import com.datalogging.user.javafx.DLButton;
import com.datalogging.user.javafx.DLText;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TopBarComponent extends Component {
    public FlowPane topBarContainer;
    public DLButton goBackButton;
    public DLText heading;
    public VBox mainContainer;
    private Component component;
    private GoBackType goBackType;

    public TopBarComponent(Component component, Boolean topBar, String headingText, GoBackType goBackType) {
        VBox.setVgrow(getContainer(), Priority.ALWAYS);
        this.goBackType = goBackType;
        this.component = component;

        if (topBar) {
            topBarContainer = new FlowPane();
            topBarContainer.setMinHeight(64);
            topBarContainer.setPadding(new Insets(16));
            getContainer().getChildren().add(topBarContainer);


            goBackButton = new DLButton(new DLBuilder().setText("Go Back").setActionEventEventHandler(new GoBackHandler()).setMargin(new Insets(0, 16, 0, 0)));
            topBarContainer.getChildren().add(goBackButton);

            heading = new DLText(new DLBuilder().setText(headingText).setFontSize(32).setMargin(new Insets(0, 0, 16, 0)));
            topBarContainer.getChildren().add(heading);
        }

        mainContainer = new VBox();
        mainContainer.setPadding(new Insets(16));
        VBox.setVgrow(mainContainer, Priority.ALWAYS);
        getContainer().getChildren().add(mainContainer);

        setGoBackType(goBackType);
    }

    public GoBackType getGoBackType() {
        return goBackType;
    }

    public void setGoBackType(GoBackType goBackType) {
        this.goBackType = goBackType;

        if (goBackType == GoBackType.None) {
            if (topBarContainer != null) {
                topBarContainer.getChildren().remove(goBackButton);
            }
        }
    }

    public enum GoBackType {
        Scene,
        ScrollPane,
        None
    }

    private class GoBackHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (goBackType == GoBackType.Scene) {
                component.goBackInScene();
            } else if (goBackType == GoBackType.ScrollPane) {
                component.goBackInScrollPane();
            }
        }
    }
}
