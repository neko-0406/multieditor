package com.nekosuki.multieditor.components.tabs;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;

public class FileType {
    public static final String MARKDOWN = "MARKDOWN";
    public static final String TEXT = "TEXT";
//    public static final String IMAGE = "IMAGE";
    private static final String[] markdown = new String[]{"md", "markdown"};

    public static String getFileType(@NotNull File file) {
        String[] strings = file.getName().split("\\.");
        if (strings.length > 1) {
            String extension = strings[strings.length - 1];
            boolean bool = false;
            for (String s : markdown) {
                if (extension.equals(s)) {
                    bool = true;
                    break;
                }
            }
            if (bool) return MARKDOWN;
            else return TEXT;
        }else {
            return TEXT;
        }
    }
}
