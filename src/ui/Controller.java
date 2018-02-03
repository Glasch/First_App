package ui;

import model.DBManager;
import model.FBIAgent;
import model.FBIAgentStatus;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Copyright (c) Anton on 18.01.2018.
 */
class Controller {
    //  FBIAgent fbiAgent = new FBIAgent();

    private Front front;
    private String message;

    Controller(Front front) {
        this.front = front;
    }

    void onAddButtonClick() throws IOException {
        FBIAgent fbiAgent = new FBIAgent();
        fbiAgent.setSurname(front.getSurnameTextField().getText());
        fbiAgent.setName(front.getNameTextField().getText());
        fbiAgent.setSex(front.getSexMaleButton().isSelected());
        fbiAgent.setNickname(Arrays.toString(front.getNicknameField().getPassword()));
        fbiAgent.setPhysicalPower(front.getPhysicalPowerCheckBox().isSelected());
        fbiAgent.setMentalStrength(front.getMentallyStrongCheckBox().isSelected());
        fbiAgent.setPatriotism(front.getPatriotismCheckBox().isSelected());
        fbiAgent.setStatus(selectStatus());
        fbiAgent.setImage(JPGtoByte(front.getAgentImage()));
        DBManager dbManager = new DBManager();
        try {
            dbManager.saveAgent(fbiAgent);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
//        System.out.println(fbiAgent.getSurname());
//        System.out.println(fbiAgent.getName());
//        System.out.println(fbiAgent.getSex());
//        System.out.println(fbiAgent.isPhysicalPower());
//        System.out.println(fbiAgent.isMentalStrength());
//        System.out.println(fbiAgent.isPatriotism());
//        System.out.println(fbiAgent.getStatus());
//        System.out.println(JPGtoByte(front.getAgentImage()));


    }

    public boolean validateInput() {
        if ("".equals(front.getSurnameTextField().getText())) {
            message = "Input Surname";
            return false;
        }
        if ("".equals(front.getNameTextField().getText())) {
            message = "Input Name";
            return false;
        }
        if (front.getNicknameField().getPassword().length == 0) {
            message = "Input Nickname";
            return false;
        }
        return true;
    }


    private FBIAgentStatus selectStatus() {
        return (FBIAgentStatus) front.getStatusComboBox().getSelectedItem();
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

    private byte[] JPGtoByte(BufferedImage image) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
//            ByteArrayInputStream imageByteStream = new ByteArrayInputStream(imageInByte);
//            return imageByteStream;
        return imageInByte;

    }

    private  BufferedImage bytetoJPG (byte[] bytes) throws IOException {
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        return ImageIO.read(is);
    }


    public void onLoadButtonClick() {



        DBManager dbManager = new DBManager();

        try {
            FBIAgent fbiAgent = dbManager.loadAgent(front.getLoadAgentTextField().getText());
            front.getSurnameTextField().setText(fbiAgent.getSurname());
            front.getNameTextField().setText(fbiAgent.getName());
            front.getSexMaleButton().setSelected(fbiAgent.getSex());
            front.getSexFemaleButton().setSelected(!fbiAgent.getSex());
            front.getPhysicalPowerCheckBox().setSelected(fbiAgent.isPhysicalPower());
            front.getMentallyStrongCheckBox().setSelected(fbiAgent.isMentalStrength());
            front.getPatriotismCheckBox().setSelected(fbiAgent.isPatriotism());
            front.getStatusComboBox().setSelectedItem(fbiAgent.getStatus());
            front.getImageLabel().setIcon(new ImageIcon(bytetoJPG(fbiAgent.getImage())));


        } catch (Exception e) {
            throw new IllegalStateException(e);
        }


    }

    public String getMessage() {
        return message;
    }


    public void onAddPreviousTaskClick() {



    }
}
