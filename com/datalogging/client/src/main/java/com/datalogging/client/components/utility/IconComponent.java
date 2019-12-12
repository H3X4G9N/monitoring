package com.datalogging.client.components.utility;

import com.datalogging.client.Controller;
import com.datalogging.client.components.Component;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IconComponent extends Component {
    public Image image;
    public ImageView imageView;

    public IconComponent(Controller controller, String imagePath) {
        super(controller);
        image = new Image(getClass().getResource(imagePath).toExternalForm());

        imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(24);
        imageView.setFitHeight(24);
    }
}
