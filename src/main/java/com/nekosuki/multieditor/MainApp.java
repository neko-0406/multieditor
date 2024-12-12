package com.nekosuki.multieditor;

import com.nekosuki.multieditor.components.*;
import com.nekosuki.multieditor.components.treeview.FileItem;
import com.nekosuki.multieditor.components.treeview.FileTreeItem;
import com.nekosuki.multieditor.process.config.AppConfig;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.File;
import java.util.Objects;

public class MainApp extends Application {

    @Getter
    private static Components components = null;

    @Getter
    private static AppConfig appConfig = null;

    public static void main(String...arts) {
        launch();
    }

    @Override
    public void init() {
        appConfig = AppConfig.loadAppConfig();
    }

    @Override
    public void start(Stage primaryStage) {
        components = new Components();

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(components.getMenuBar());
        borderPane.setCenter(components.getSplitter());
        borderPane.setLeft(components.getSideMenuBar());
        borderPane.setBottom(components.getStatusBar());

        String styleTheme = appConfig.getRoot().getTheme();
        Scene scene = new Scene(borderPane, 1200, 800);
        scene.getStylesheets().add(getStyleSheetPath(styleTheme));


        primaryStage.setScene(scene);
        primaryStage.showingProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue && !newValue) {
                String currentPath = appConfig.getDirectory().getCurrentDir();
                if (!currentPath.isEmpty()) {
                    appConfig.getDirectory().setLastOpenDir(currentPath);
                    appConfig.getDirectory().setCurrentDir("");
                }
                appConfig.storeConfig();
            }
        });
        appInit();
        primaryStage.show();
    }

    private void appInit() {
        String dirPath = appConfig.getDirectory().getLastOpenDir();
        File dir = new File(dirPath);
        if (dir.exists()) {
            FileTreeItem treeItem = new FileTreeItem(new FileItem(dir));
            treeItem.setExpanded(true);
            components.getCustomTreeView().setRoot(treeItem);
            components.getRootDirTitlePane().setText(dir.getName());

            appConfig.getDirectory().setCurrentDir(dirPath);
            appConfig.getDirectory().setLastOpenDir("");
            appConfig.storeConfig();
        }
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
}
