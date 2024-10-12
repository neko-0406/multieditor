package com.nekosuki.multieditor;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;
import java.util.Objects;

public class IconManager {

    public static ImageView getIcon(Icons iconType) {
        ImageView imageView = new ImageView();

        try (InputStream in = MainApp.class.getResourceAsStream("icons/" + iconType.getIconName())) {
            Image image = new Image(Objects.requireNonNull(in));
            imageView.setImage(image);
            imageView.setFitWidth(32);
            imageView.setFitHeight(32);
        } catch (Exception e) {
            System.out.println(iconType.getIconName() + " is not found...");
        }
        return imageView;
    }
}
