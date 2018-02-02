package ui;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Copyright (c) Anton on 02.02.2018.
 */
public class TableModel extends AbstractTableModel {

   private  int columnCount = 4;
   private ArrayList<String []> dataArrayList;

    public TableModel(){
          dataArrayList = new ArrayList <>();
          for (int i = 0; i<dataArrayList.size(); i++){
              dataArrayList.add(new String[getColumnCount()]);
          }
    }

    @Override
    public int getRowCount() {
        return dataArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows  = dataArrayList.get(rowIndex);
        return rows[columnIndex];
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

    public  void addData(String[] row){
        String[] rowTable = new String[getColumnCount()];
        rowTable = row;
        dataArrayList.add(rowTable);
    }


}


