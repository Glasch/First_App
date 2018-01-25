package ui;

import model.DBManager;
import model.FBIAgent;
import model.FBIAgentStatus;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.Arrays;

/**
 * Copyright (c) Anton on 18.01.2018.
 */
class Controller {
    FBIAgent fbiAgent = new FBIAgent();

    private Front front;

    Controller(Front front) {
        this.front = front;
    }

    void onAddButtonClick() {

        FBIAgent fbiAgent = new FBIAgent();
        fbiAgent.setSurname(front.getSurnameTextField().getText());
        fbiAgent.setName(front.getNameTextField().getText());
        fbiAgent.setSex(front.getSexMaleButton().isSelected());
        fbiAgent.setNickname(Arrays.toString(front.getNicknameField().getPassword()));
        fbiAgent.setPhysicalPower(front.getPhysicalPowerCheckBox().isSelected());
        fbiAgent.setMentalStrength(front.getMentallyStrongCheckBox().isSelected());
        fbiAgent.setPatriotism(front.getPatriotismCheckBox().isSelected());
        fbiAgent.setStatus(selectStatus());
        fbiAgent.setImage(createByteaImage());

        System.out.println(fbiAgent.getSurname());
        System.out.println(fbiAgent.getName());
        System.out.println(fbiAgent.getSex());
        System.out.println(fbiAgent.getNickname());
        System.out.println(fbiAgent.isPhysicalPower());
        System.out.println(fbiAgent.isMentalStrength());
        System.out.println(fbiAgent.isPatriotism());
        System.out.println(fbiAgent.getStatus());

        DBManager dbManager = new DBManager();
        try {
            dbManager.saveAgent(fbiAgent);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }


    }


    private FBIAgentStatus selectStatus() {
        if (front.getStatusComboBox().getSelectedItem() == FBIAgentStatus.READY) {
            return FBIAgentStatus.READY;
        } else if (front.getStatusComboBox().getSelectedItem() == FBIAgentStatus.DECEASED) {
            return FBIAgentStatus.DECEASED;
        } else if (front.getStatusComboBox().getSelectedItem() == FBIAgentStatus.INJURED) {
            return FBIAgentStatus.INJURED;
        } else if (front.getStatusComboBox().getSelectedItem() == FBIAgentStatus.LOST) {
            return FBIAgentStatus.LOST;
        } else if (front.getStatusComboBox().getSelectedItem() == FBIAgentStatus.ON_THE_TASK) {
            return FBIAgentStatus.ON_THE_TASK;
        } else if (front.getStatusComboBox().getSelectedItem() == FBIAgentStatus.TRAITOR) {
            return FBIAgentStatus.TRAITOR;
        } else return null;

    }


    void onImageLabelClick(JLabel imageLabel) {
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

    private byte[] createByteaImage() {

//        todo преобразовать картинку в byte
    }

}
