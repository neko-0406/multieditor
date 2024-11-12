package com.nekosuki.multieditor;

import com.nekosuki.multieditor.components.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Objects;

public class MainApp extends Application {

    private static Components components = null;
    private static AppConfig appConfig = null;

    public static void main(String...arts) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        components = new Components();
        appConfig = new AppConfig();

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(components.getMenuBar());
        borderPane.setCenter(components.getSplitter());
        borderPane.setLeft(components.getSideMenuBar());
        borderPane.setBottom(components.getStatusBar());

        String styleTheme = appConfig.getProperty("display_theme", "light");
        Scene scene = new Scene(borderPane, 1200, 800);
        scene.getStylesheets().add(getStyleSheetPath(styleTheme));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String getStyleSheetPath(String theme) {
        String styleSheetPath = "";
        try{
            if (theme.equals("light")) {
                styleSheetPath = Objects.requireNonNull(MainApp.class.getResource("/com/nekosuki/multieditor/style_light.css")).toExternalForm();
            }
            else if (theme.equals("dark")) {
                styleSheetPath = Objects.requireNonNull(MainApp.class.getResource("/com/nekosuki/multieditor/style_dark.css")).toExternalForm();
            }
        }catch (NullPointerException e){
            System.out.println("StyleSheet is not found....");
        }

        return styleSheetPath;
    }

    public static Components getComponents() { return components; }
    public static AppConfig getAppConfig() { return appConfig; }
}
