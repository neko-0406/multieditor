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
    requires flexmark.util.data;
    requires flexmark;
    requires flexmark.ext.autolink;
    requires flexmark.ext.tables;
    requires flexmark.ext.gfm.strikethrough;
    requires flexmark.ext.gfm.tasklist;
    requires flexmark.ext.footnotes;
    requires flexmark.ext.typographic;
    requires flexmark.util.ast;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.core;
    requires org.jetbrains.annotations;
    requires jdk.compiler;
    requires static lombok;
    requires com.fasterxml.jackson.databind;

    opens com.nekosuki.multieditor to javafx.fxml;
    exports com.nekosuki.multieditor;
    exports com.nekosuki.multieditor.components;
    exports com.nekosuki.multieditor.components.topmenu_items;
    exports com.nekosuki.multieditor.components.popup;
    exports com.nekosuki.multieditor.markdown;
    exports com.nekosuki.multieditor.components.treeview;
    opens com.nekosuki.multieditor.markdown to javafx.fxml;
    opens com.nekosuki.multieditor.components.popup to javafx.fxml;
}
