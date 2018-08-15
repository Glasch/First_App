package ui;

import model.DBManager;
import model.FBIAgent;
import model.FBIAgentPreviousTask;
import model.FrontType;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Copyright (c) Anton on 18.01.2018.
 */
class EditController {

    private EditFront editFront;
    private String message;
    //    private PreviousTasksTableModel previousTasksTableModel;
    private DBManager dbManager = new DBManager();
    private Window parentWindow;

    public EditController(Window parentWindow) {
        this.parentWindow = parentWindow;
        this.editFront = new EditFront(this);
    }

    void onAddButtonClick() throws IOException {
        DBManager dbManager = new DBManager();
        try {
            dbManager.saveAgent(DataHelper.getFromUI(editFront));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }

    public boolean validateInput() {
        if ("".equals(editFront.getSurnameTextField().getText())) {
            message = "Input Surname";
            return false;
        }
        if ("".equals(editFront.getNameTextField().getText())) {
            message = "Input Name";
            return false;
        }
        if (editFront.getNicknameField().getPassword().length == 0) {
            message = "Input Nickname";
            return false;
        }
        if ("".equals(editFront.getOtherCommentsArea().getText())) {
            message = "Input Comment";
            return false;
        }
        return true;
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
                editFront.loadImage(path);
                imageLabel.setIcon(new ImageIcon(editFront.getAgentImage()));

            }


        }
    }

    public String getMessage() {
        return message;
    }


    public FBIAgentPreviousTask createPreviousTask() {
        FBIAgentPreviousTask previousTask = new FBIAgentPreviousTask();

        UtilDateModel start = editFront.getStartDatePickerModel();
        UtilDateModel end = editFront.getEndDatePickerModel();

        String city = editFront.getCityTextField().getText();
        LocalDate startLocal = start.getValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        previousTask.setStartDate(startLocal);

        LocalDate endLocal = end.getValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        previousTask.setEndDate(endLocal);
        previousTask.setCity(city);

        return previousTask;
    }


    public void onAddPreviousTaskClick() {
        FBIAgentPreviousTask previousTask = createPreviousTask();
        editFront.getPreviousTasksTableModel().addData(previousTask);
    }

    public String validatePreviousTask() {
        UtilDateModel start = editFront.getStartDatePickerModel();
        UtilDateModel end = editFront.getEndDatePickerModel();

        if (start.getValue().after(end.getValue())) {
            return "Dates are incorrect!";
        }
        return null;
    }


    public void onDeleteFromTableButtonClick() {
        JTable table = editFront.getPreviousTasksTable();
        editFront.getPreviousTasksTableModel().getPreviousTasks().remove(table.getSelectedRow());
        editFront.getPreviousTasksTableModel().fireTableDataChanged();
    }

    public void onClearTableButtonClick() {
        editFront.getPreviousTasksTableModel().getPreviousTasks().clear();
        editFront.getPreviousTasksTableModel().fireTableDataChanged();
    }


    public void loadSelectedAgent(int id) {
        try {
            FBIAgent fbiAgent = dbManager.loadAgent(id);
            createGUI(FrontType.WATCHSELECTED);
            DataHelper.setToUI(editFront, fbiAgent);
            show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parentWindow, e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void createGUI(FrontType frontType) {
        editFront.createGUI(frontType, parentWindow);
    }

    public void show() {
        editFront.show();
    }

}
