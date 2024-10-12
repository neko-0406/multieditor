module com.nekosuki.multieditor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires transitive javafx.graphics;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires eu.hansolo.tilesfx;
    requires org.fxmisc.richtext;

    opens com.nekosuki.multieditor to javafx.fxml;
    exports com.nekosuki.multieditor;
}
