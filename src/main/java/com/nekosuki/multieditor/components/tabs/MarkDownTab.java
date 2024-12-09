package com.nekosuki.multieditor.components.tabs;

import com.nekosuki.multieditor.AppConfig;
import com.nekosuki.multieditor.MainApp;
import com.nekosuki.multieditor.components.CustomTreeView;
import com.nekosuki.multieditor.components.treeview.FileItem;
import com.nekosuki.multieditor.components.treeview.FileTreeItem;
import com.nekosuki.multieditor.components.treeview.FileType;
import com.nekosuki.multieditor.markdown.GenerateHTML;
import javafx.scene.control.*;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class MarkDownTab extends Tab implements ITextTab {
    private final CodeArea codeArea;
    private final WebView webView;
    private final File file;
    private boolean isEdited;
    private final static GenerateHTML generateHtml = new GenerateHTML();

    public MarkDownTab() {
        super();
        codeArea = new CodeArea();
        webView = new WebView();
        SplitPane splitPane = new SplitPane();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        splitPane.getItems().addAll(codeArea,webView);
        this.setContent(splitPane);
        isEdited = false;
        this.file = null;
        addEventListener();
    }

    public MarkDownTab(File markdownFile) {
        super();
        codeArea = new CodeArea();
        webView = new WebView();
        this.file = markdownFile;
        this.setText(markdownFile.getName());
        SplitPane splitPane = new SplitPane();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        splitPane.getItems().addAll(codeArea,webView);
        this.setContent(splitPane);
        String markdown = readMarkDownFile(markdownFile);
        codeArea.replaceText(markdown);
        loadHtml(markdown);
        addEventListener();
    }
    public void undo() {
        codeArea.undo();
    }
    public void redo() {
        codeArea.redo();
    }
    public void saveFileAs() {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setHeaderText("新しいファイル名を入力");
        textInputDialog.setContentText("新しいファイル名：");
        AtomicReference<Path> resultPath = new AtomicReference<>();
        Optional<String> result = textInputDialog.showAndWait();
        result.ifPresent(name -> {
            if (!name.endsWith(".md") || !name.endsWith(".markdown")) {
                name += ".md";
            }
            CustomTreeView treeView = MainApp.getComponents().getCustomTreeView();
            TreeItem<FileItem> selectItem = treeView.getSelectionModel().getSelectedItem();
            if ( selectItem != null) {
                FileType fileType = selectItem.getValue().getFileType();
                File dir;
                if (fileType == FileType.FILE){
                    dir = selectItem.getValue().getFile().getParentFile();
                }
                else{
                    dir = selectItem.getValue().getFile();
                }
                resultPath.set(dir.toPath().resolve(name));
            }else {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle("ファイルを保存するフォルダを選択");
                directoryChooser.setInitialDirectory(new File(MainApp.getAppConfig().getProperty(AppConfig.CURRENT_DIR, System.getProperty("user.home"))));
                File dir = directoryChooser.showDialog(null);
                if (dir != null)  {
                    resultPath.set(dir.toPath().resolve(name));
                }
            }

            try{
                Files.writeString(resultPath.get() ,codeArea.getText());
            }catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        });
    }
    public void saveFile() {
        try {
            if (file != null && file.exists() && isEdited) {
                Path filepath = file.toPath();
                Files.writeString(filepath, codeArea.getText());
                this.setGraphic(null);
                isEdited = false;
            }
            else if ((file == null || !file.exists()) && isEdited) {
                String dirPath = MainApp.getAppConfig().getProperty(AppConfig.CURRENT_DIR, "");
                if (dirPath.isEmpty()) {  // フォルダを開いていない場合
                    DirectoryChooser chooser = new DirectoryChooser();
                    chooser.setTitle("保存するフォルダを選択");
                    chooser.setInitialDirectory(new File(System.getProperty("user.home")));
                    File dir = chooser.showDialog(null);
                    if (dir != null) {
                        Path filepath = dir.toPath().resolve(getText());
                        Files.writeString(filepath, codeArea.getText());
                        this.setGraphic(null);
                        isEdited = false;
                    }
                }else {
                    CustomTreeView treeView = MainApp.getComponents().getCustomTreeView();
                    TreeItem<FileItem> fileItem = treeView.getSelectionModel().getSelectedItem();
                    Path filepath = null;
                    if (fileItem != null) {
                        File dir = fileItem.getValue().getFile();
                        if (dir.isFile()) dir = dir.getParentFile();
                        filepath = dir.toPath().resolve(getText());
                        Files.writeString(filepath, codeArea.getText());
                    }
                    if (filepath != null) {
                        FileItem fItem = new FileItem(filepath.toFile());
                    }
                    FileTreeItem treeItem = new FileTreeItem(new FileItem(new File(dirPath)));
                    treeView.setRoot(treeItem);
                    this.setGraphic(null);
                    isEdited = false;
                }
            }
        }catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ファイルの保存に失敗しました");
            alert.show();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean isEdited() {return isEdited;}

    private void addEventListener() {
        codeArea.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isEdited) {
                isEdited = true;
                FontIcon editIcon = new FontIcon("far-edit");
                editIcon.setIconSize(20);
                this.setGraphic(editIcon);
            }
            loadHtml(newValue);
        });
    }

    private void loadHtml(String value) {
        String html = generateHtml.genHtmlTextsFromMarkDown(value);
        webView.getEngine().loadContent(html);
    }

    private static String readMarkDownFile(File file) {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }

    @Override
    public File getFile() {return file;}


}
