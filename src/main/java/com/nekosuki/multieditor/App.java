package com.nekosuki.multieditor;

import com.nekosuki.multieditor.components.MenuBar;
import com.nekosuki.multieditor.components.SideMenuBar;

import javafx.scene.layout.BorderPane;

public class App extends BorderPane {
    public App() {
        super();
        this.setTop(new MenuBar());
        this.setLeft(new SideMenuBar());
    }
}
