package com.datalogging.user.components.utility;

import com.datalogging.user.components.Component;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IconComponent extends Component {
    public Image image;
    public ImageView imageView;

    public IconComponent(String imagePath) {
        image = new Image(getClass().getResource(imagePath).toExternalForm());

        imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(24);
        imageView.setFitHeight(24);
    }
}
