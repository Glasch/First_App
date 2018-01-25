package ui;

import model.FBIAgentStatus;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Copyright (c) Anton on 17.01.2018.
 */
public class Front {


    private BufferedImage agentImage;
    private Controller controller;
    private JTextField surnameTextField;
    private JTextField nameTextField;
    private JRadioButton sexMaleButton;
    private JRadioButton sexFemaleButton;
    private JPasswordField nicknameField;
    private JCheckBox patriotismCheckBox;
    private JCheckBox mentallyStrongCheckBox;
    private JCheckBox physicalPowerCheckBox;
    private JComboBox statusComboBox;

    public Front() {
        controller = new Controller(this);
    }

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
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onAddButtonClick();
                JOptionPane.showMessageDialog(frame, "           AGENT ADDED!");
            }
        });

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
        surnameLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        surnameTextField = new JTextField();
        panel.add(surnameTextField);

        JLabel nameLabel = new JLabel("NAME");
        panel.add(nameLabel);
        nameLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        nameTextField = new JTextField();
        panel.add(nameTextField);

        JLabel sexLabel = new JLabel("SEX");
        panel.add(sexLabel);
        sexLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        sexMaleButton = new JRadioButton("MALE");
        sexFemaleButton = new JRadioButton("FEMALE");
        createSexGroup(sexMaleButton, sexFemaleButton);
        Box box = Box.createVerticalBox();
        box.add(sexMaleButton);
        box.add(sexFemaleButton);
        box.add(Box.createHorizontalGlue());
        panel.add(box);
        box.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JLabel nicknameLabel = new JLabel("NICKNAME");
        panel.add(nicknameLabel);
        nicknameLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        nicknameField = new JPasswordField();
        panel.add(nicknameField);

        JLabel specialCharacteristicsLabel = new JLabel("SPECIAL CHARACTERISTICS");
        panel.add(specialCharacteristicsLabel);
        specialCharacteristicsLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
         physicalPowerCheckBox = new JCheckBox("PHYSICAL POWER");
        mentallyStrongCheckBox = new JCheckBox("MENTAL STRENGTH");
        patriotismCheckBox = new JCheckBox("PATRIOTISM");
        Box box1 = Box.createVerticalBox();
        box1.add(physicalPowerCheckBox);
        box1.add(mentallyStrongCheckBox);
        box1.add(patriotismCheckBox);
        box1.add(Box.createHorizontalGlue());
        panel.add(box1);
        box1.setAlignmentX(Box.RIGHT_ALIGNMENT);


        JLabel statusLabel = new JLabel("STATUS");
        statusComboBox = new JComboBox <>(FBIAgentStatus.values());
        panel.add(statusLabel);
        statusLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panel.add(statusComboBox);
    }

    private void initLeftPanel(JFrame frame) {
        String path = "photo.jpg";
        loadImage(path);
        JLabel imageLabel = new JLabel(new ImageIcon(agentImage));
        imageLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.onImageLabelClick(imageLabel);

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
        JPanel panel = new JPanel();
        panel.setMaximumSize(new Dimension(250, 250));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        frame.add(panel, BorderLayout.WEST);
        panel.add(imageLabel);
    }

    void loadImage(String path) {
        try {
            agentImage = ImageIO.read(new File(path));
            agentImage = Scalr.resize(agentImage, 250);
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


    private void createSexGroup(JRadioButton sexMale, JRadioButton sexFemale) {
        ButtonGroup sexGroup = new ButtonGroup();
        sexGroup.add(sexMale);
        sexGroup.add(sexFemale);
    }


    JTextField getSurnameTextField() {
        return surnameTextField;
    }


    BufferedImage getAgentImage() {
        return agentImage;
    }

    JTextField getNameTextField() {
        return nameTextField;
    }

    JRadioButton getSexMaleButton() {
        return sexMaleButton;
    }

    JPasswordField getNicknameField() {
        return nicknameField;
    }

    JCheckBox getPatriotismCheckBox() {
        return patriotismCheckBox;
    }

    JCheckBox getMentallyStrongCheckBox() {
        return mentallyStrongCheckBox;
    }

    JCheckBox getPhysicalPowerCheckBox() {
        return physicalPowerCheckBox;
    }

    JComboBox getStatusComboBox() {
        return statusComboBox;
    }
}
