package ui;

import model.FBIAgentPreviousTask;
import model.MiniAgent;

import java.util.ArrayList;

/**
 * Copyright (c) Anton on 20.02.2018.
 */
public class MainMenuTableModel extends TableModel {

    private ArrayList <MiniAgent> allAgents = new ArrayList <>();


    @Override
    public int getRowCount() {
        return allAgents.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "id";
            case 1:
                return "Surname";
            case 2:
                return "Name";
            case 3:
                return "Status";
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MiniAgent miniAgent = allAgents.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return miniAgent.getId();
            case 1:
                return miniAgent.getSurname();
            case 2:
                return miniAgent.getName();
            case 3:
                return miniAgent.getStatus();
        }

        return "";
    }


    public ArrayList <MiniAgent> getAllAgents() {
        return allAgents;
    }

    public void setAllAgents(ArrayList <MiniAgent> allAgents) {
        this.allAgents = allAgents;
    }
}
