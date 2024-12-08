package com.nekosuki.multieditor.components.popup;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * ファイルが編集済みの場合にファイルを閉じるときに使う
 */
public class CloseFileAlertDialogController implements Initializable {

    private ResultType resultType = ResultType.CANCEL;

    @FXML private Label label;
    @FXML private Button yesButton;
    @FXML private Button noButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        yesButton.setOnAction(event -> {
            resultType = ResultType.YES;
            closeDialog();
        });

        noButton.setOnAction(event -> {
            resultType = ResultType.NO;
            closeDialog();
        });
    }

    private void closeDialog() {
        Stage stage = (Stage) yesButton.getScene().getWindow();
        stage.close();
    }

    public ResultType getResult() {
        return resultType;
    }

    public enum ResultType {
        YES, NO, CANCEL
    }
}
