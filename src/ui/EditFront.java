package ui;

import model.FBIAgentStatus;
import model.FrontType;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 * Copyright (c) Anton on 17.01.2018.
 */
public class EditFront {


    private BufferedImage agentImage;
    private EditController editController;
    private JTextField surnameTextField;
    private JTextField nameTextField;
    private JRadioButton sexMaleButton;
    private JPasswordField nicknameField;
    private JCheckBox patriotismCheckBox;
    private JCheckBox mentallyStrongCheckBox;
    private JCheckBox physicalPowerCheckBox;
    private JComboBox statusComboBox;
    private JRadioButton sexFemaleButton;
    private JLabel imageLabel;
    private JTextField loadAgentTextField;
    private JTextField cityTextField;
    private UtilDateModel startDatePickerModel;
    private UtilDateModel endDatePickerModel;
    private PreviousTasksTableModel previousTasksTableModel;
    private JTextArea otherCommentsArea;
    private JTable previousTasksTable;
    private JDialog dialog;

    public EditFront(EditController editController) {
        this.editController = editController;
    }

    public void createGUI(FrontType frontType, Window parent) {

        dialog = new JDialog(parent, Dialog.ModalityType.DOCUMENT_MODAL);

        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setSize(900, 600);
        dialog.setLocationRelativeTo(parent);
        dialog.setLayout(new BorderLayout(5, 5));


        initTopPanel(dialog);
        initLeftPanel(dialog);
        initCenterPanel(dialog);
        initRightPanel(dialog);


        JPanel panel = new JPanel();
        panel.setAlignmentX(JPanel.RIGHT_ALIGNMENT);
        dialog.add(panel, BorderLayout.SOUTH);

        if (frontType == FrontType.ADDNEW) {
            JButton addButton = new JButton("ADD");
            panel.add(addButton);
            addButton.addActionListener(e -> {
                if (editController.validateInput()) {
                    try {
                        editController.onAddButtonClick();
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(dialog, e1.getMessage(), "Can not save agent", ERROR_MESSAGE);
                        return;
                    }
                    JOptionPane.showMessageDialog(dialog, "AGENT ADDED!");
                } else {
                    JOptionPane.showMessageDialog(dialog, editController.getMessage(), "Validation failed", JOptionPane.WARNING_MESSAGE);
                }
            });
        }

    }

    public void show() {
        dialog.pack();
        dialog.setVisible(true);
    }

    private void initRightPanel(Window frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        panel.setAlignmentY(JPanel.TOP_ALIGNMENT);
        frame.add(panel, BorderLayout.EAST);


        JLabel previousTasksLabel = new JLabel("PREVIOUS TASKS");
        panel.add(previousTasksLabel);


        previousTasksTableModel = new PreviousTasksTableModel();
        previousTasksTable = new JTable(previousTasksTableModel);
        TableColumnModel m = previousTasksTable.getColumnModel();
        DateRenderer dateRenderer = new DateRenderer();

        m.getColumn(1).setCellRenderer(dateRenderer);
        m.getColumn(2).setCellRenderer(dateRenderer);

        JScrollPane previousTasksTableScrollPane = new JScrollPane(previousTasksTable);
        previousTasksTableScrollPane.setPreferredSize(new Dimension(300, 150));

        JPanel p2 = new JPanel();

        JButton addToTable = new JButton("ADD NEW");
        addToTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAddPreviousTaskFrame(frame);
            }


        });
        JButton deleteFromTable = new JButton("DELETE");
        deleteFromTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editController.onDeleteFromTableButtonClick();
            }
        });

        JButton clearTableButton = new JButton("CLEAR");
        clearTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editController.onClearTableButtonClick();
            }
        });

        p2.add(addToTable);
        p2.add(deleteFromTable);
        p2.add(clearTableButton);


        panel.add(previousTasksTableScrollPane);
        panel.add(p2);


        JLabel otherCommentsLabel = new JLabel("OTHER COMMENTS");
        panel.add(otherCommentsLabel);
        otherCommentsArea = new JTextArea();
        panel.add(otherCommentsArea);
    }

    private void initCenterPanel(Window frame) {
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
        sexMaleButton.setSelected(true);
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

    private void initLeftPanel(Window frame) {
        String path = "photo.jpg";
        loadImage(path);
        imageLabel = new JLabel(new ImageIcon(agentImage));
        imageLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                editController.onImageLabelClick(imageLabel);

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

    private void initTopPanel(Window frame) {
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


    private void createAddPreviousTaskFrame(Window frame) {
        JDialog addNewPreviousTaskFrame = new JDialog(frame, Dialog.ModalityType.DOCUMENT_MODAL);
        addNewPreviousTaskFrame.setSize(new Dimension(600, 200));
        addNewPreviousTaskFrame.setLayout(new GridLayout(2, 3));

        startDatePickerModel = new UtilDateModel();
        endDatePickerModel = new UtilDateModel();

        Box leftBox = new Box(BoxLayout.Y_AXIS);
        JLabel startDateLabel = new JLabel("START DATE");
        JDatePanelImpl startDatePanel = new JDatePanelImpl(startDatePickerModel);
        JDatePickerImpl startDatePicker = new JDatePickerImpl(startDatePanel);
        leftBox.add(startDateLabel);
        leftBox.add(startDatePicker);

        Box centerBox = new Box(BoxLayout.Y_AXIS);
        JLabel endDateLabel = new JLabel("END DATE");
        JDatePanelImpl endDatePanel = new JDatePanelImpl(endDatePickerModel);
        JDatePickerImpl endDatePicker = new JDatePickerImpl(endDatePanel);
        centerBox.add(endDateLabel);
        centerBox.add(endDatePicker);


        Box rightBox = new Box(BoxLayout.Y_AXIS);
        JLabel cityLabel = new JLabel("CITY");
        cityTextField = new JTextField(20);
        rightBox.add(cityLabel);
        rightBox.add(cityTextField);

        JButton addPreviousTaskButton = new JButton("ADD");
        addPreviousTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String res = editController.validatePreviousTask();
                if(res == null) {
                    editController.onAddPreviousTaskClick();
                }else{
                    JOptionPane.showMessageDialog(frame,res);
                }
            }
        });


        JButton finishAddingButton = new JButton("FINISH ADDING");
        finishAddingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewPreviousTaskFrame.dispose();
            }
        });

        addNewPreviousTaskFrame.add(leftBox);
        addNewPreviousTaskFrame.add(centerBox);
        addNewPreviousTaskFrame.add(rightBox);
        addNewPreviousTaskFrame.add(addPreviousTaskButton);
        addNewPreviousTaskFrame.add(finishAddingButton);
        addNewPreviousTaskFrame.pack();

        addNewPreviousTaskFrame.setVisible(true);


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

    public JRadioButton getSexFemaleButton() {
        return sexFemaleButton;
    }


    public JLabel getImageLabel() {
        return imageLabel;
    }


    public JTextField getLoadAgentTextField() {
        return loadAgentTextField;
    }

    public JTextField getCityTextField() {
        return cityTextField;
    }


    public UtilDateModel getStartDatePickerModel() {
        return startDatePickerModel;
    }

    public UtilDateModel getEndDatePickerModel() {
        return endDatePickerModel;
    }


    public PreviousTasksTableModel getPreviousTasksTableModel() {
        return previousTasksTableModel;
    }

    public JTextArea getOtherCommentsArea() {
        return otherCommentsArea;
    }

    public void setOtherCommentsArea(JTextArea otherCommentsArea) {
        this.otherCommentsArea = otherCommentsArea;
    }

    public JTable getPreviousTasksTable() {
        return previousTasksTable;
    }

    public void setPreviousTasksTable(JTable previousTasksTable) {
        this.previousTasksTable = previousTasksTable;
    }

}


