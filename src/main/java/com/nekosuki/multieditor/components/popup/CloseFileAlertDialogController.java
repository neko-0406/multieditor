package com.nekosuki.multieditor.components.popup;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * ファイルが編集済みの場合にファイルを閉じるときに使う
 */
public class CloseFileAlertDialogController implements Initializable {

    private boolean isAccept;

    @FXML Button yesButton;
    @FXML Button noButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isAccept = false;
        yesButton.setOnAction(event -> {
            isAccept = true;
        });
    }
}
