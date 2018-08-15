package ui;

import model.DBManager;
import model.FrontType;

import javax.swing.*;
import java.awt.*;

/**
 * Copyright (c) Anton on 27.02.2018.
 */
public class MainController {

    private MainFront mainFront;

    public MainController() {
        mainFront = new MainFront(this);
    }


    public void loadSelectedAgent(JTable allAgentsTable) {
        int id = (int) allAgentsTable.getModel().getValueAt(allAgentsTable.getSelectedRow(), 0);
        EditController editController = new EditController(mainFront.getMainMenuFrame());
        editController.loadSelectedAgent(id);
    }

    public void onLoadAllButtonClick() {
        MainMenuTableModel mainMenuTableModel = mainFront.getMainMenuTableModel();
        DBManager dbManager = new DBManager();

        try {
            mainMenuTableModel.setAllAgents(dbManager.loadAllAgents());
            mainMenuTableModel.fireTableDataChanged();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(mainFront.getMainMenuFrame(), e.getMessage(), "Database Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onAddNewButtonClick() {
        EditController editController = new EditController(mainFront.getMainMenuFrame());
        editController.createGUI(FrontType.ADDNEW);
        editController.show();
    }

    public void showGUI() {
        mainFront.createMainMenu();
    }


    public void onMainFrontSearchButtonClick() {
        SearchController searchController = new SearchController(this);
        searchController.createSearchGUI();
    }

    public Window getWindow() {
        return mainFront.getMainMenuFrame();
    }

    public MainMenuTableModel getMainMenuTableModel() {
        return mainFront.getMainMenuTableModel();
    }
}