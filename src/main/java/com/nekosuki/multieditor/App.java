package com.nekosuki.multieditor;

import com.nekosuki.multieditor.components.MenuBar;
import com.nekosuki.multieditor.components.SideMenuBar;
import com.nekosuki.multieditor.components.Splitter;
import com.nekosuki.multieditor.components.StatusBar;

import com.nekosuki.multieditor.markdown.MarkDownParser;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {

    private final AppConfig appConfig = new AppConfig();

    @Override
    public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(new MenuBar());
        borderPane.setCenter(new Splitter());
        borderPane.setLeft(new SideMenuBar());
        borderPane.setBottom(new StatusBar());

        String styleTheme = appConfig.getProperty("display_theme", "light");
        Scene scene = new Scene(borderPane, 1200, 800);
        scene.getStylesheets().add(getStyleSheetPath(styleTheme));

        primaryStage.setScene(scene);
        primaryStage.show();

        MarkDownParser parser = new MarkDownParser();
        parser.convert("# This is Test");
    }

    public void AppStart() {
        launch();
    }

    private String getStyleSheetPath(String theme) {
        String styleSheetPath = "";
        try{
            if (theme.equals("light")) {
                styleSheetPath = Objects.requireNonNull(MainApp.class.getResource("/style_light.css")).toExternalForm();
            }
            else if (theme.equals("dark")) {
                styleSheetPath = Objects.requireNonNull(MainApp.class.getResource("/style_dark.css")).toExternalForm();
            }
        }catch (NullPointerException e){
            System.out.println("StyleSheet is not found....");
        }

        return styleSheetPath;
    }
}
