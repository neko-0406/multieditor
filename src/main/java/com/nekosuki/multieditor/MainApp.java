package com.nekosuki.multieditor;

import com.nekosuki.multieditor.components.*;
import com.nekosuki.multieditor.components.treeview.FileItem;
import com.nekosuki.multieditor.components.treeview.FileTreeItem;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;

public class MainApp extends Application {

    private static Components components = null;
    private static AppConfig appConfig = null;

    public static void main(String...arts) {
        launch();
    }

    @Override
    public void init() {
        appConfig = new AppConfig();
    }

    @Override
    public void start(Stage primaryStage) {
        components = new Components();
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(components.getMenuBar());
        borderPane.setCenter(components.getSplitter());
        borderPane.setLeft(components.getSideMenuBar());
        borderPane.setBottom(components.getStatusBar());

        String styleTheme = appConfig.getProperty(AppConfig.DISPLAY_THEME, "light");
        Scene scene = new Scene(borderPane, 1200, 800);
        scene.getStylesheets().add(getStyleSheetPath(styleTheme));

        primaryStage.setScene(scene);
        primaryStage.showingProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue && !newValue) {
                String currentPath = appConfig.getProperty(AppConfig.CURRENT_DIR, "");
                if (!currentPath.isEmpty()) appConfig.replaceProperty(AppConfig.LAST_OPEN_DIR, currentPath);
                appConfig.replaceProperty(AppConfig.CURRENT_DIR, "");
                appConfig.writeProperties();
            }
        });
        appInit();
        primaryStage.show();
    }

    private void appInit() {
        String dirPath = appConfig.getProperty(AppConfig.LAST_OPEN_DIR, "");
        File dir = new File(dirPath);
        if (dir.exists()) {
            FileTreeItem treeItem = new FileTreeItem(new FileItem(dir));
            treeItem.setExpanded(true);
            components.getCustomTreeView().setRoot(treeItem);
            components.getRootDirTitlePane().setText(dir.getName());

            appConfig.replaceProperty(AppConfig.CURRENT_DIR, dirPath);
            appConfig.replaceProperty(AppConfig.LAST_OPEN_DIR, "");
        }
        System.out.println("↓");
        System.out.println(appConfig.getProperty(AppConfig.CURRENT_DIR, "None"));
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
