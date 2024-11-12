package com.nekosuki.multieditor.components;

import com.nekosuki.multieditor.components.topmenu_items.EditFX;
import com.nekosuki.multieditor.components.topmenu_items.FileFX;

public class MenuBar extends javafx.scene.control.MenuBar {

    public MenuBar() {
        super();
        this.getMenus().addAll(
            new FileFX(),
            new EditFX()
        );
    }

    
}
