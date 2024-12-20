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
    private final Stage modalStage;

    public CloseFileAlertDialog() {
        this.modalStage = new Stage();
    }

    public ResultType showAndWait() {
        ResultType resultType;
        try {
            CloseFileAlertDialogController controller = new CloseFileAlertDialogController();
            FXMLLoader loader = new FXMLLoader(getFxmlURL());
            loader.setController(controller);

            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            modalStage.setScene(scene);
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.showAndWait();
            resultType = controller.getResult();
        }catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return resultType;
    }

    private URL getFxmlURL() {
        URL fxmlUrl;
        try {
            fxmlUrl = MainApp.class.getResource("view/popup/CloseFileAlert.fxml");
        }catch (NullPointerException e) {
            throw new RuntimeException(e.getMessage());
        }

        return fxmlUrl;
    }
}
