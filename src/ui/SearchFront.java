package ui;

import model.FBIAgentStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Copyright (c) Anton on 06.03.2018.
 */
public class SearchFront {

    private SearchController searchController;
    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JComboBox statusComboBox;

    public SearchFront(SearchController searchController) {
        this.searchController = searchController;
    }



    void createSearchGUI(JFrame parent, MainMenuTableModel mainMenuTableModel) {



        JDialog dialog = new JDialog(parent, Dialog.ModalityType.DOCUMENT_MODAL);

        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setSize(900, 600);
        dialog.setLocationRelativeTo(parent);
        dialog.setLayout(new GridLayout(1, 4));



        Box box = new Box(BoxLayout.Y_AXIS);

        JLabel idLabel = new JLabel("ID");
        idTextField = new JTextField();


        box.add(idLabel);
        box.add(idTextField);

        dialog.add(box);

        Box box1 = new Box(BoxLayout.Y_AXIS);


        JLabel surnameLabel = new JLabel("Surname");
        surnameTextField = new JTextField();

        box1.add(surnameLabel);
        box1.add(surnameTextField);

        dialog.add(box1);

        Box box2 = new Box(BoxLayout.Y_AXIS);

        JLabel nameLabel = new JLabel("Name");
        nameTextField = new JTextField();


        box2.add(nameLabel);
        box2.add(nameTextField);

        dialog.add(box2);

        Box box3 = new Box(BoxLayout.Y_AXIS);

        JLabel statusLabel = new JLabel("STATUS");
        statusComboBox = new JComboBox <>(FBIAgentStatus.values());
        statusComboBox.setSelectedItem(null);

        box3.add(statusLabel);
        box3.add(statusComboBox);

        dialog.add(box3);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchController.OnSearchFrontSearchButtonClick(idTextField,nameTextField,surnameTextField,statusComboBox,mainMenuTableModel,parent);
            }
        });
        dialog.add(searchButton);


        dialog.pack();
        dialog.setVisible(true);


    }


    public SearchController getSearchController() {
        return searchController;
    }
}



