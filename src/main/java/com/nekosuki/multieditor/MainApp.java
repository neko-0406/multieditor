package com.nekosuki.multieditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private final static AppConfig appConfig = new AppConfig();

    @Override
    public void start(Stage stage) throws Exception{
        App app = new App();
        Scene scene = new Scene(app, 800, 600);
        stage.setTitle("Neko-editor");
        stage.setScene(scene);
        stage.show();

        stage.showingProperty().addListener(event -> {
            appConfig.writeProperties();
        });
    }

    public static void main(String...args) {
        launch();
    }

    public static AppConfig getAppConfig() {
        return appConfig;
    }
}
