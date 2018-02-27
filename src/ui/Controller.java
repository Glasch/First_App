package ui;

import model.*;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Copyright (c) Anton on 18.01.2018.
 */
class Controller {
    //  FBIAgent fbiAgent = new FBIAgent();

    private Front front;
    private String message;
    private TableModel tableModel;

    Controller(Front front) {
        this.front = front;
    }

    void onAddButtonClick() throws IOException {
        FBIAgent fbiAgent = new FBIAgent();
        fbiAgent.setPreviousTasks(tableModel.getPreviousTasks());
        fbiAgent.setSurname(front.getSurnameTextField().getText());
        fbiAgent.setName(front.getNameTextField().getText());
        fbiAgent.setSex(front.getSexMaleButton().isSelected());
        fbiAgent.setNickname(Arrays.toString(front.getNicknameField().getPassword()));
        fbiAgent.setPhysicalPower(front.getPhysicalPowerCheckBox().isSelected());
        fbiAgent.setMentalStrength(front.getMentallyStrongCheckBox().isSelected());
        fbiAgent.setPatriotism(front.getPatriotismCheckBox().isSelected());
        fbiAgent.setStatus(selectStatus());
        fbiAgent.setImage(JPGtoByte(front.getAgentImage()));
        fbiAgent.setOtherComments(front.getOtherCommentsArea().getText());
        DBManager dbManager = new DBManager();
        try {
            dbManager.saveAgent(fbiAgent);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

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
        if ("".equals(front.getOtherCommentsArea().getText())) {
            message = "Input Comment";
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

    private BufferedImage byteToJPG(byte[] bytes) throws IOException {
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
            front.getImageLabel().setIcon(new ImageIcon(byteToJPG(fbiAgent.getImage())));
            front.getTableModel().getPreviousTasks().addAll(fbiAgent.getPreviousTasks());
            front.getTableModel().fireTableDataChanged();
            front.getOtherCommentsArea().setText(fbiAgent.getOtherComments());



        } catch (Exception e) {
            throw new IllegalStateException(e);
        }


    }

    public String getMessage() {
        return message;
    }


    public FBIAgentPreviousTask createPreviousTask() {

        FBIAgentPreviousTask previousTask = new FBIAgentPreviousTask();

        UtilDateModel start = front.getStartDatePickerModel();
        UtilDateModel end = front.getEndDatePickerModel();

        String city = front.getCityTextField().getText();
        LocalDate startLocal = start.getValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        previousTask.setStartDate(startLocal);

        LocalDate endLocal = end.getValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        previousTask.setEndDate(endLocal);
        previousTask.setCity(city);

        return previousTask;
    }


    public void onAddPreviousTaskClick() {
        FBIAgentPreviousTask previousTask = createPreviousTask();
        tableModel = front.getTableModel();
        tableModel.addData(previousTask);


    }

    public String validatePreviousTask() {
        UtilDateModel start = front.getStartDatePickerModel();
        UtilDateModel end = front.getEndDatePickerModel();

        if (start.getValue().after(end.getValue())) {
            return "Dates are incorrect!";
        }
        return null;
    }


    public void onDeleteFromTableButtonClick() {
        JTable table = front.getPreviousTasksTable();
        tableModel = front.getTableModel();
        tableModel.getPreviousTasks().remove(table.getSelectedRow());
        tableModel.fireTableDataChanged();
}

    public void onClearTableButtonClick() {
        tableModel = front.getTableModel();
        tableModel.getPreviousTasks().clear();
        tableModel.fireTableDataChanged();
    }

    public void anAddNewButtonClick() {
        front.createGUI(FrontType.ADDNEW);
        front.getMainMenuFrame().dispose();
    }

    public void onLoadAllButtonClick() {
        MainMenuTableModel mainMenuTableModel = front.getMainMenuTableModel() ;
        DBManager dbManager = new DBManager();

        try {
            mainMenuTableModel.setAllAgents(dbManager.loadAllAgents());
            mainMenuTableModel.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void loadSelectedAgent(JTable allAgentsTable)  {

       int id = (int) allAgentsTable.getModel().getValueAt(allAgentsTable.getSelectedRow(),0);
       DBManager dbManager = new DBManager();
        FBIAgent fbiAgent = null;
        try {
            fbiAgent = dbManager.loadAgent(id);
        } catch (Exception e) {
            e.printStackTrace();
        }



        front.createGUI(FrontType.WATCHSELECTED);
        front.getSurnameTextField().setText(fbiAgent.getSurname());
        front.getNameTextField().setText(fbiAgent.getName());
        front.getSexMaleButton().setSelected(fbiAgent.getSex());
        front.getSexFemaleButton().setSelected(!fbiAgent.getSex());
        front.getPhysicalPowerCheckBox().setSelected(fbiAgent.isPhysicalPower());
        front.getMentallyStrongCheckBox().setSelected(fbiAgent.isMentalStrength());
        front.getPatriotismCheckBox().setSelected(fbiAgent.isPatriotism());
        front.getStatusComboBox().setSelectedItem(fbiAgent.getStatus());
        try {
            front.getImageLabel().setIcon(new ImageIcon(byteToJPG(fbiAgent.getImage())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        front.getTableModel().getPreviousTasks().addAll(fbiAgent.getPreviousTasks());
        front.getTableModel().fireTableDataChanged();
        front.getOtherCommentsArea().setText(fbiAgent.getOtherComments());






    }

}
