package ui;

import model.DBManager;
import model.FBIAgent;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Copyright (c) Anton on 06.03.2018.
 */
public class SearchController {

    private SearchFront searchFront;


    public SearchController() {
        searchFront = new SearchFront(this);
    }


    public void OnSearchFrontSearchButtonClick(JTextField idTextField, JTextField nameTextField, JTextField surnameTextField, JComboBox status, MainMenuTableModel mainMenuTableModel,JFrame parent) {

        if(validateSearch(idTextField,nameTextField,surnameTextField,status)){
            JOptionPane.showMessageDialog(parent, "Input Information to start searching!", "Search Validation failed", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = create_SQL_String(idTextField, nameTextField, surnameTextField, status);

        DBManager dbManager = new DBManager();
        ArrayList <FBIAgent> allFoundAgents;
        allFoundAgents = dbManager.loadAllAgents(sql);

        if(allFoundAgents.isEmpty()){
            JOptionPane.showMessageDialog(parent,"No Agents Found!","Search Complete", JOptionPane.INFORMATION_MESSAGE );
        }

        mainMenuTableModel.getAllAgents().clear();
        mainMenuTableModel.setAllAgents(allFoundAgents);
        mainMenuTableModel.fireTableDataChanged();
    }


    private String create_SQL_String(JTextField idTextField, JTextField nameTextField, JTextField surnameTextField, JComboBox status) {
        int counter = 0;
        String sql = "SELECT * FROM agent WHERE ";

            if (!idTextField.getText().isEmpty()) {

                sql = sql + " id = " + idTextField.getText();
                return sql;

            } else {


                if (!surnameTextField.getText().isEmpty()) {

                    sql = sql + "surname = " + "'" + surnameTextField.getText() + "'";
                    counter++;
                }

                if (!nameTextField.getText().isEmpty()) {
                    if (counter > 0){
                        sql = sql + " AND ";
                    }
                    sql = sql + " name = " + "'" + nameTextField.getText()+ "'";
                    counter++;
                }

                if (status.getSelectedItem() != null) {
                    if (counter > 0) {
                        sql = sql + " AND ";
                    }
                    sql = sql + "status = " + "'" + status.getSelectedItem() + "'";
                }

                return sql;

            }
        }




    private boolean validateSearch(JTextField idTextField, JTextField nameTextField, JTextField surnameTextField, JComboBox status) {
        return (idTextField.getText().isEmpty() && nameTextField.getText().isEmpty() && surnameTextField.getText().isEmpty() && status.getSelectedItem() == null);

    }

    public SearchFront getSearchFront() {
        return searchFront;
    }
}
