package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Copyright (c) Anton on 17.01.2018.
 */
public class Front {

    BufferedImage agentImage;

   public void createGUI() {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(900, 600);

        frame.setLayout(new BorderLayout(5, 5));

        initTopPanel(frame);
        initLeftPanel(frame);
        initCenterPanel(frame);
        initRightPanel(frame);


        JPanel panel = new JPanel();
        panel.setAlignmentX(JPanel.RIGHT_ALIGNMENT);
        frame.add(panel, BorderLayout.SOUTH);
        JButton addButton = new JButton("ADD");
        panel.add(addButton);

        frame.pack();
        frame.setVisible(true);
    }

    private void initRightPanel(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        panel.setAlignmentY(JPanel.TOP_ALIGNMENT);
        frame.add(panel, BorderLayout.EAST);

        JLabel previousTasksLabel = new JLabel("PREVIOUS TASKS");
        panel.add(previousTasksLabel);

        JTable previousTasksTable = new JTable(5, 4);
        panel.add(previousTasksTable);

        JLabel otherCommentsLabel = new JLabel("OTHER COMMENTS");
        panel.add(otherCommentsLabel);
        JTextArea otherCommentsArea = new JTextArea();
        panel.add(otherCommentsArea);
    }

    private void initCenterPanel(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        panel.setAlignmentY(JPanel.TOP_ALIGNMENT);
        frame.add(panel, BorderLayout.CENTER);

        JLabel surnameLabel = new JLabel("SURNAME");
        panel.add(surnameLabel);
        JTextField surnameTextField = new JTextField();
//        surnameTextField.setPreferredSize(new Dimension(0, 25));
        panel.add(surnameTextField);

        JLabel nameLabel = new JLabel("NAME");
        panel.add(nameLabel);
        JTextField nameTextField = new JTextField();
        panel.add(nameTextField);

        JLabel sexLabel = new JLabel("SEX");
        panel.add(sexLabel);
        JRadioButton sexMaleButton = new JRadioButton("MALE");
        JRadioButton sexFemaleButton = new JRadioButton("FEMALE");
        createSexGroup(sexMaleButton, sexFemaleButton);
        panel.add(sexMaleButton);
        panel.add(sexFemaleButton);

        JLabel nicknameLabel = new JLabel("NICKNAME");
        panel.add(nicknameLabel);
        JPasswordField nicknameField = new JPasswordField();
        panel.add(nicknameField);

        JLabel specialCharacteristicsLabel = new JLabel("SPECIAL CHARACTERISTICS");
        panel.add(specialCharacteristicsLabel);
        JCheckBox PhysicalPowerCheckBox = new JCheckBox("PHYSICAL POWER");
        panel.add(PhysicalPowerCheckBox);
        JCheckBox MentallyStrongCheckBox = new JCheckBox("MENTALLY STRONG");
        panel.add(MentallyStrongCheckBox);
        JCheckBox PatriotismCheckBox = new JCheckBox("PATRIOTISM");
        panel.add(PatriotismCheckBox);

        JLabel statusLabel = new JLabel("STATUS");
        String[] items = createItems();
        JComboBox <String> statusComboBox = new JComboBox <>(items);
        panel.add(statusComboBox);
    }

    private void initLeftPanel(JFrame frame) {
        loadImage();
        JLabel imageLabel = new JLabel(new ImageIcon(agentImage));
        JPanel panel = new JPanel();
        panel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        frame.add(panel, BorderLayout.WEST);
        panel.add(imageLabel);
    }

    private void loadImage() {
        try {
            agentImage = ImageIO.read(new File("photo.jpg"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private void initTopPanel(JFrame frame) {
        JLabel headerLabel = new JLabel("FBI AGENTS");
        JPanel panel = new JPanel();
        panel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        frame.add(panel, BorderLayout.NORTH);
        panel.add(headerLabel);
    }


    private String[] createItems() {
        return new String[]{
                "READY",
                "ON THE TASK",
                "INJURED",
                "LOST",
                "TRAITOR",
                "DIED",
        };
    }

    private void createSexGroup(JRadioButton sexMale, JRadioButton sexFemale) {
        ButtonGroup sexGroup = new ButtonGroup();
        sexGroup.add(sexMale);
        sexGroup.add(sexFemale);
    }


}
