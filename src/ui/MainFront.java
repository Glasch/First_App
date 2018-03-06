package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Copyright (c) Anton on 27.02.2018.
 */
public class MainFront {

    private JFrame mainMenuFrame;
    private MainMenuTableModel mainMenuTableModel;
    private MainController mainController;


    public MainFront(MainController mainController) {
        this.mainController = mainController;
    }


    public  void createMainMenu(){

        mainMenuFrame = new JFrame();
        mainMenuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainMenuFrame.setSize(900, 600);
        mainMenuFrame.setLayout(new BorderLayout());


        Box box = new Box(BoxLayout.X_AXIS);

        JButton addNew = new JButton("Add New");
        box.add(addNew);

        JButton watchSelectedButton = new JButton("View");
        box.add(watchSelectedButton);

        JButton editSelected = new JButton("Edit");
        box.add(editSelected);

        JButton loadAllButton = new JButton("Load All");
        box.add(loadAllButton);

        JButton searchButton = new JButton("Search");
        box.add(searchButton);

        mainMenuTableModel = new MainMenuTableModel();
        JTable allAgentsTable = new JTable(mainMenuTableModel);


        allAgentsTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==2){
                    mainController.loadSelectedAgent(allAgentsTable);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        JScrollPane previousTasksTableScrollPane = new JScrollPane(allAgentsTable);



        addNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.onAddNewButtonClick();
            }
        });

        loadAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.onLoadAllButtonClick();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             mainController.OnMainFrontSearchButtonClick();
           }
        });

        mainMenuFrame.add(previousTasksTableScrollPane,BorderLayout.CENTER);
        mainMenuFrame.add(box,BorderLayout.NORTH);

        mainMenuFrame.pack();
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.setVisible(true);
    }

    public JFrame getMainMenuFrame() {
        return mainMenuFrame;
    }

    public MainMenuTableModel getMainMenuTableModel() {
        return mainMenuTableModel;

    }




}
