package ui;

import model.DBManager;
import model.FrontType;

import javax.swing.*;

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

        mainMenuTableModel.setAllAgents(dbManager.loadAllAgents());
        mainMenuTableModel.fireTableDataChanged();
    }

    public void onAddNewButtonClick() {
        EditController editController = new EditController(mainFront.getMainMenuFrame());
        editController.createGUI(FrontType.ADDNEW);
        editController.show();
    }

    public void showGUI() {
        mainFront.createMainMenu();

    }


    public void OnMainFrontSearchButtonClick() {

        SearchController searchController = new SearchController();
        searchController.getSearchFront().createSearchGUI(mainFront.getMainMenuFrame(), mainFront.getMainMenuTableModel());

    }

    public MainFront getMainFront() {
        return mainFront;
    }
}