package com.nekosuki.multieditor;

import com.nekosuki.multieditor.components.MenuBar;
import com.nekosuki.multieditor.components.SideMenuBar;
import com.nekosuki.multieditor.components.Splitter;
import com.nekosuki.multieditor.components.StatusBar;

import javafx.scene.layout.BorderPane;

public class App extends BorderPane {
    public App() {
        super();
        this.setTop(new MenuBar());
        this.setLeft(new SideMenuBar());
        this.setCenter(new Splitter());
        this.setBottom(new StatusBar());
    }
}
