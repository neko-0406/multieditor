package com.nekosuki.multieditor;

public enum ConfigIndex {
    CODE_TEXT_SIZE("code_text_size"),
    CURRENT_DIR("current_dir"),
    DISPLAY_THEME("display_theme"),
    LAST_OPEN_DIR("last_open_dir");

    private final String name;

    ConfigIndex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
