package com.datacollection.client.components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Icon extends Component {
	public ImageView imageView;
	public Image image;
	
	public Icon(String imagePath) {
		this.image = new Image(getClass().getResource(imagePath).toExternalForm());
		
		this.imageView = new ImageView();
		this.imageView.setImage(image);
		this.imageView.setFitWidth(32);
		this.imageView.setFitHeight(32);
	}
}
