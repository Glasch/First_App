package ui;

import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.concurrent.TimeUnit;


/**
 * Copyright (c) Anton on 27.02.2018.
 */
public class MainFront {

    private JFrame mainMenuFrame;
    private MainMenuTableModel mainMenuTableModel;
    private MainController mainController;
    private JButton viewSelectedButton;
    private JButton editSelected;
    private JButton deleteButton;
    private String btcPrice;


    public MainFront(MainController mainController) {
        this.mainController = mainController;
    }


    public void createMainMenu() {

        mainMenuFrame = new JFrame();
        mainMenuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainMenuFrame.setSize(900, 600);
        mainMenuFrame.setLayout(new BorderLayout());


        Box box = new Box(BoxLayout.X_AXIS);

        JButton addNew = new JButton("Add New");
        box.add(addNew);

        viewSelectedButton = new JButton("View");
        box.add(viewSelectedButton);

        editSelected = new JButton("Edit");
        box.add(editSelected);

        deleteButton = new JButton("Delete");
        box.add(deleteButton);
        enableItemActions(false);

        JButton loadAllButton = new JButton("Load All");
        box.add(loadAllButton);

        JButton searchButton = new JButton("Search");
        box.add(searchButton);
        JLabel priceLabel = new JLabel();
        box.add(priceLabel);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!Thread.currentThread().isInterrupted()) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        btcPrice = getPrice();
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                    priceLabel.setText("BTC price = " + btcPrice + "$");

                }}});

        thread.start();


        mainMenuTableModel = new MainMenuTableModel();
        JTable allAgentsTable = new JTable(mainMenuTableModel);

        allAgentsTable.getSelectionModel().addListSelectionListener(e -> {
            boolean selected = allAgentsTable.getSelectedRow() != -1;
            enableItemActions(selected);
        });


        allAgentsTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
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


        addNew.addActionListener(e -> mainController.onAddNewButtonClick());

        loadAllButton.addActionListener(e -> mainController.onLoadAllButtonClick());

        searchButton.addActionListener(e -> mainController.onMainFrontSearchButtonClick());

        mainMenuFrame.add(previousTasksTableScrollPane, BorderLayout.CENTER);
        mainMenuFrame.add(box, BorderLayout.NORTH);

        mainMenuFrame.pack();
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.setVisible(true);
    }


    private String getPrice() throws JSONException, IOException {
        String path = "https://api.cryptonator.com/api/ticker/btc-usd";
        URLConnection connection = new URL(path).openConnection();
        BufferedReader in = new BufferedReader(
        new InputStreamReader(connection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            stringBuilder.append(inputLine);
        }
        in.close();
        JSONObject jsObject = new JSONObject(stringBuilder.toString());
        return (String) jsObject.getJSONObject("ticker").get("price");
    }

    private void enableItemActions(boolean enable) {
        editSelected.setEnabled(enable);
        viewSelectedButton.setEnabled(enable);
        deleteButton.setEnabled(enable);
    }

    public JFrame getMainMenuFrame() {
        return mainMenuFrame;
    }

    public MainMenuTableModel getMainMenuTableModel() {
        return mainMenuTableModel;

    }


}
