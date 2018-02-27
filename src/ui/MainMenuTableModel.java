package ui;

import model.FBIAgent;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Copyright (c) Anton on 20.02.2018.
 */
public class MainMenuTableModel extends AbstractTableModel {

    private ArrayList <FBIAgent> allAgents = new ArrayList <>();


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
        FBIAgent fbiAgent = allAgents.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return fbiAgent.getId();
            case 1:
                return fbiAgent.getSurname();
            case 2:
                return fbiAgent.getName();
            case 3:
                return fbiAgent.getStatus();
        }

        return "";
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public ArrayList <FBIAgent> getAllAgents() {
        return allAgents;
    }

    public void setAllAgents(ArrayList <FBIAgent> allAgents) {
        this.allAgents = allAgents;
    }
}
