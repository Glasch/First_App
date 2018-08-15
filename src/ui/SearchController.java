package ui;

import model.DBManager;
import model.FBIAgent;
import model.FBIAgentStatus;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Copyright (c) Anton on 06.03.2018.
 */
public class SearchController {

    private SearchFront searchFront;
    private MainController mainController;


    public SearchController(MainController mainController) {
        this.mainController = mainController;
        searchFront = new SearchFront(this);
    }


    public void onSearchButtonClick() {
        String id = searchFront.getIdTextField().getText();
        String name = searchFront.getNameTextField().getText();
        String surname = searchFront.getSurnameTextField().getText();
        FBIAgentStatus status = (FBIAgentStatus) searchFront.getStatusComboBox().getSelectedItem();

        if (!validateSearch(id, name, surname, status)) {
            JOptionPane.showMessageDialog(getParentWindow(), "Input Information to start searching!", "Search Validation failed", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DBManager dbManager = new DBManager();

        String sql = dbManager.create_SQL_String(id, name, surname, status);

        ArrayList <FBIAgent> allFoundAgents = null;
        try {
            allFoundAgents = dbManager.loadAllAgents(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(getParentWindow(),e.getMessage(),"Database Error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (allFoundAgents.isEmpty()) {
            JOptionPane.showMessageDialog(getParentWindow(), "No Agents Found!", "Search Complete", JOptionPane.INFORMATION_MESSAGE);
        }


        MainMenuTableModel tableModel = mainController.getMainMenuTableModel();
        tableModel.getAllAgents().clear();
        tableModel.setAllAgents(allFoundAgents);
        tableModel.fireTableDataChanged();
    }

    private boolean validateSearch(String id, String name, String surname, FBIAgentStatus status) {
        return !id.isEmpty() || !name.isEmpty() || !surname.isEmpty() || status != null;

    }

    void createSearchGUI() {
        searchFront.createSearchGUI();
    }

    public Window getParentWindow() {
        return mainController.getWindow();
    }
}
