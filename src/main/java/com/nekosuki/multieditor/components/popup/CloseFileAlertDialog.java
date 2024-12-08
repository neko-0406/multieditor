package com.nekosuki.multieditor.components.popup;

import com.nekosuki.multieditor.MainApp;
import eu.hansolo.tilesfx.skins.RadarNodeChartTileSkin;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class CloseFileAlertDialog {
    private CloseFileAlertDialogController.ResultType resultType;
    private final Stage modalStage;

    public CloseFileAlertDialog() {
        this.modalStage = new Stage();
        showAndWait();
    }


    public void showAndWait() {
        try {
            AnchorPane root = FXMLLoader.load(getFxmlURL());
            Scene scene = new Scene(root);
            modalStage.setScene(scene);
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.showAndWait();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private URL getFxmlURL() {
        URL fxmlUrl;
        try {
            fxmlUrl = MainApp.class.getResource("view/popup/CloseFileAlert.fxml");
            System.out.println(fxmlUrl);
        }catch (NullPointerException e) {
            throw new RuntimeException(e.getMessage());
        }

        return fxmlUrl;
    }
}
