package ui;

import model.DBManager;
import model.FBIAgent;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Copyright (c) Anton on 18.01.2018.
 */
public class Controller {
    FBIAgent fbiAgent = new FBIAgent();

    private Front front;

    public Controller(Front front) {
        this.front = front;
    }

    public void onAddButtonClick() {

        FBIAgent fbiAgent = new FBIAgent();
        fbiAgent.setSurname(front.getSurnameTextField().getText());
        System.out.println(fbiAgent.getSurname());

        DBManager dbManager = new DBManager();
        try {
            dbManager.saveAgent(fbiAgent);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }


    }

    public void onImageLabelClick(JLabel imageLabel) {
        JFileChooser fileOpen = new JFileChooser();
        MyFileFilter fileFilter = new MyFileFilter();

        fileOpen.addChoosableFileFilter(fileFilter);
        int ret = fileOpen.showDialog(null, "Установить изображение");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileOpen.getSelectedFile();
            if (fileFilter.accept(file)) {
                String path = file.getAbsolutePath();
                System.out.println(path);
                front.loadImage(path);
                imageLabel.setIcon(new ImageIcon(front.getAgentImage()));

            }


        }
    }

}
