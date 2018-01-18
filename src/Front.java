import javax.swing.*;
import java.awt.*;

/**
 * Copyright (c) Anton on 17.01.2018.
 */
class Front {


    void createGUI() {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(900,600);

        GridBagLayout gbl = new GridBagLayout();
        frame.setLayout(gbl);

        GridBagConstraints c = new GridBagConstraints();


        addLabels(frame,c);
        addButtons(frame,c);
        addTextFields(frame,c);
    }

    private void addTextFields(JFrame frame,GridBagConstraints c) {
        JTextField surnameField = new JTextField(20);
        frame.add(surnameField, c);

        JTextField nameField = new JTextField(20);
        frame.add(nameField, c);

        JPasswordField nicknameField = new JPasswordField(20);
        frame.add(nicknameField,c);

        JTextArea otherCommentsField = new JTextArea(10,25);
        frame.add(otherCommentsField,c);
    }

    private void addButtons( JFrame frame, GridBagConstraints c ) {
        JButton addButton = new JButton("ADD");
        frame.add(addButton,c);

        JRadioButton sexMaleButton = new JRadioButton("MALE");
        frame.add(sexMaleButton, c);
        JRadioButton sexFemaleButton = new JRadioButton("FEMALE");
        frame.add(sexFemaleButton,c);
        createSexGroup(sexMaleButton, sexFemaleButton);

        JCheckBox PhysicalPowerCheckBox = new JCheckBox("PHYSICAL POWER");
        frame.add(PhysicalPowerCheckBox,c);
        JCheckBox MentallyStrongCheckBox = new JCheckBox("MENTALLY STRONG");
        frame.add(MentallyStrongCheckBox,c);
        JCheckBox PatriotismCheckBox = new JCheckBox("PATRIOTISM");
        frame.add(PatriotismCheckBox,c);


        String[] items = CreateItems();
        JComboBox statusComboBox = new JComboBox(items);
        frame.add(statusComboBox,c);



    }

    private String[] CreateItems() {
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

    private void addLabels(JFrame frame, GridBagConstraints c) {
        JLabel headerLabel = new JLabel("FBI AGENTS");
        frame.add(headerLabel,c);

        JLabel surnameLabel = new JLabel("SURNAME");
        frame.add(surnameLabel,c);

        JLabel nameLabel = new JLabel("NAME");
        frame.add(nameLabel,c);

        JLabel sexLabel = new JLabel("SEX");
        frame.add(sexLabel,c);

        JLabel nicknameLabel = new JLabel("NICKNAME");
        frame.add(nicknameLabel,c);

        JLabel specialCharacteristicsLabel = new JLabel("SPECIAL CHARACTERISTICS");
        frame.add(specialCharacteristicsLabel,c);

        JLabel statusLabel = new JLabel("STATUS");
        frame.add(statusLabel,c);

        JLabel previousTasksLabel = new JLabel("PREVIOUS TASKS");
        frame.add(previousTasksLabel,c);

        JLabel otherCommentsLabel = new JLabel("OTHER COMMENTS");
        frame.add(otherCommentsLabel,c);


    }

    GridBagConstraints createConstraint(GridBagConstraints c){

        return  c;
    }




}
