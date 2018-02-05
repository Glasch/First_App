package ui;

import model.FBIAgentPreviousTask;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Copyright (c) Anton on 02.02.2018.
 */
public class TableModel extends AbstractTableModel {

    private ArrayList<FBIAgentPreviousTask> previousTasks = new ArrayList <>() ;

    @Override
    public int getRowCount() {
        return previousTasks.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0 : return "#";
            case 1 : return "Start Date";
            case 2 : return "End Date";
            case 3 : return  "City";
        }
        return "";
    }



    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FBIAgentPreviousTask previousTask = previousTasks.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return rowIndex+1;
            case 1:
                return previousTask.getStartDate();
            case 2:
                return previousTask.getEndDate();
            case 3:
                return previousTask.getCity();
        }

        return "";
    }

    void addData(FBIAgentPreviousTask previousTask){
        previousTasks.add(previousTask);
        fireTableRowsInserted(0, previousTasks.size());
    }

    public ArrayList <FBIAgentPreviousTask> getPreviousTasks() {
        return previousTasks;
    }
}


