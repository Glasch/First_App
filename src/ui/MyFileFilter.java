package ui;

import java.io.File;

/**
 * Copyright (c) Anton on 24.01.2018.
 */
public class MyFileFilter extends javax.swing.filechooser.FileFilter {


        @Override
    public boolean accept(File f) {

        if (f != null) {

            String name = f.getName();

            int i = name.lastIndexOf('.');

            if (i > 0 && i < name.length() - 1)

                return name.substring(i + 1).equalsIgnoreCase("jpg");
        }


        return false;
    }

    @Override
    public String getDescription() {
        return "Подходящие изображения";
    }
}

