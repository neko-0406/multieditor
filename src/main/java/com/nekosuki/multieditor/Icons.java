package com.nekosuki.multieditor;

public enum Icons {
    FILES("files");

    private String iconName;

    Icons(String iconName) {
        this.iconName = iconName;
    }

    public String getIconName() {
        return this.iconName + ".png";
    }
}