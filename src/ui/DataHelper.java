package ui;

import model.FBIAgent;
import model.FBIAgentStatus;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Copyright (c) Anton on 27.02.2018.
 */
public class DataHelper {

    static void setToUI(EditFront editFront, FBIAgent fbiAgent){
        editFront.getSurnameTextField().setText(fbiAgent.getSurname());
        editFront.getNameTextField().setText(fbiAgent.getName());
        editFront.getSexMaleButton().setSelected(fbiAgent.getSex());
        editFront.getSexFemaleButton().setSelected(!fbiAgent.getSex());
        editFront.getPhysicalPowerCheckBox().setSelected(fbiAgent.isPhysicalPower());
        editFront.getMentallyStrongCheckBox().setSelected(fbiAgent.isMentalStrength());
        editFront.getPatriotismCheckBox().setSelected(fbiAgent.isPatriotism());
        editFront.getStatusComboBox().setSelectedItem(fbiAgent.getStatus());
        editFront.getImageLabel().setIcon(new ImageIcon(byteToJPG(fbiAgent.getImage())));
        editFront.getPreviousTasksTableModel().getPreviousTasks().addAll(fbiAgent.getPreviousTasks());
        editFront.getPreviousTasksTableModel().fireTableDataChanged();
        editFront.getOtherCommentsArea().setText(fbiAgent.getOtherComments());
    }


    static  FBIAgent  getFromUI(EditFront editFront){
        FBIAgent fbiAgent = new FBIAgent();
        fbiAgent.setPreviousTasks(editFront.getPreviousTasksTableModel().getPreviousTasks());
        fbiAgent.setSurname(editFront.getSurnameTextField().getText());
        fbiAgent.setName(editFront.getNameTextField().getText());
        fbiAgent.setSex(editFront.getSexMaleButton().isSelected());
        fbiAgent.setNickname(Arrays.toString(editFront.getNicknameField().getPassword()));
        fbiAgent.setPhysicalPower(editFront.getPhysicalPowerCheckBox().isSelected());
        fbiAgent.setMentalStrength(editFront.getMentallyStrongCheckBox().isSelected());
        fbiAgent.setPatriotism(editFront.getPatriotismCheckBox().isSelected());
        fbiAgent.setStatus(selectStatus(editFront));
        fbiAgent.setImage(JPGtoByte(editFront.getAgentImage()));
        fbiAgent.setOtherComments(editFront.getOtherCommentsArea().getText());
        return fbiAgent;

    }

    private static byte[] JPGtoByte(BufferedImage image)  {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageInByte;
        try {
            ImageIO.write(image, "jpg", baos);
            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return imageInByte;

    }

    private static BufferedImage byteToJPG(byte[] bytes) {
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        try {
            return ImageIO.read(is);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static FBIAgentStatus selectStatus(EditFront editFront) {
        return (FBIAgentStatus) editFront.getStatusComboBox().getSelectedItem();
    }
}
