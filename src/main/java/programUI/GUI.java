package programUI;

import realization.list.MyListOfArrays;
import realization.list.SerializeList;
import realization.types.factory.FactoryUserType;
import realization.types.userTypes.UserType;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.Objects;
import javax.swing.*;

public class GUI extends JPanel {
    private JButton addButton;
    private JButton insertByIdButton;
    private JButton removeByIdButton;
    private JTextArea mainField;
    private JComboBox<String> typeComboBox;
    private JTextField insertByIdField;
    private JTextField removeByIdField;
    private JButton sortListBySizeButton;
    private JButton getByIdButton;
    private JTextField getByIdField;
    private JButton saveButton;
    private JButton loadButton;
    private JLabel totalElementsLabel;

    String currentType;
    String totalElements = "TOTAL ELEMENTS = 0";
    public UserType userType;
    public MyListOfArrays list;
    public FactoryUserType factoryUserType;

    public GUI() {
        currentType = "Integer";
        factoryUserType = new FactoryUserType();
        userType = factoryUserType.getBuilderByTypeName(currentType);
        list = new MyListOfArrays(64);


        //construct components
        addButton = new JButton ("Вставить");
        insertByIdButton = new JButton ("Вставить по id");
        removeByIdButton = new JButton ("Удалить по id");
        mainField = new JTextArea (5, 5);
        typeComboBox = new JComboBox<>(factoryUserType.getTypeNameList().toArray(new String[0]));
        insertByIdField = new JTextField (5);
        removeByIdField = new JTextField (5);
        sortListBySizeButton = new JButton ("Сортировать");
        getByIdButton = new JButton ("Получить по id");
        getByIdField = new JTextField (5);
        totalElementsLabel = new JLabel(totalElements);

        saveButton = new JButton ("Сохранить");
        loadButton = new JButton ("Загрузить");

        //adjust size and set layout
        setPreferredSize (new Dimension (1000, 700));
        setLayout (null);

        //add components
        add (addButton);
        add (insertByIdButton);
        add (removeByIdButton);
        add (mainField);
        add (typeComboBox);
        add (insertByIdField);
        add (removeByIdField);
        add (sortListBySizeButton);
        add (getByIdButton);
        add (getByIdField);
        add (totalElementsLabel);

        add (saveButton);
        add (loadButton);

        //set component bounds
        typeComboBox.setBounds (650, 15, 180, 30);

        // Work with nodes
        addButton.setBounds (800, 70, 140, 30);

        insertByIdButton.setBounds (800, 110, 140, 30);
        insertByIdField.setBounds (650, 110, 140, 30);

        removeByIdButton.setBounds (800, 155, 140, 30);
        removeByIdField.setBounds (650, 155, 140, 30);

        getByIdButton.setBounds (800, 200, 140, 30);
        getByIdField.setBounds (650, 200, 140, 30);

        sortListBySizeButton.setBounds (650, 250, 290, 30);

        totalElementsLabel.setBounds(650, 350, 290, 30);

        // Serialization
        saveButton.setBounds (650, 610, 145, 70);
        loadButton.setBounds (850, 610, 140, 70);

        // Main field
        mainField.setBounds (25, 10, 600, 680);
        mainField.setEditable(false);
        mainField.setLineWrap(true);
        mainField.setWrapStyleWord(true);

        // set action
        setActions();
    }

    public void setActions() {
        addButton.addActionListener(e -> pushNode());
        insertByIdButton.addActionListener(e -> pushNodeByID());
        typeComboBox.addActionListener(e -> selectTypeList(e));
        removeByIdButton.addActionListener(e -> removeNodeByID());
        getByIdButton.addActionListener(e -> getNodeByID());
        sortListBySizeButton.addActionListener(e -> sortListBySizeArray());

        saveButton.addActionListener(e -> saveList());
        loadButton.addActionListener(e -> loadList());
    }

    public void showWindow() {
        JFrame frame = new JFrame ("Lab1 Java");

        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new GUI());
        frame.pack();
        frame.setVisible (true);
        frame.setResizable(false);
    }

    private void selectTypeList(ActionEvent actionEvent) {
        JComboBox comboBox = (JComboBox) actionEvent.getSource();
        String item = (String) comboBox.getSelectedItem();

        if (!Objects.equals(currentType, item)) {
            currentType = item;
            userType = factoryUserType.getBuilderByTypeName(currentType);
            list = new MyListOfArrays(64);
            setTextOnMainField();
        }
    }

    private void pushNode() {
        list.add(userType.create());
        updateTotalElementsLabel();
        setTextOnMainField();
    }

    private void pushNodeByID() {
        String nodeID = insertByIdField.getText();
        if (nodeID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ошибка. Поле \"id\" пусто!");
            return;
        }

        list.insert(userType.create(), Integer.parseInt(nodeID));
        updateTotalElementsLabel();
        setTextOnMainField();
    }

    private void removeNodeByID() {
        String nodeID = removeByIdField.getText();
        if (nodeID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ошибка. Поле \"id\" пусто!");
            return;
        }

        list.remove(Integer.parseInt(nodeID));
        updateTotalElementsLabel();
        setTextOnMainField();
    }

    private void sortListBySizeArray() {
        list = list.sort(userType.getTypeComparator());
        updateTotalElementsLabel();
        setTextOnMainField();
    }

    private void getNodeByID() {
        String nodeID = getByIdField.getText();
        if (nodeID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ошибка. Поле \"id\" пусто!");
            return;
        }
        JOptionPane.showMessageDialog(null, "Найденый узел:\n" +
                list.get(Integer.parseInt(nodeID)));
    }


    private void saveList() {
        String filename = userType.typeName() + ".dat";
        SerializeList.saveToFile(list, filename, userType);
        JOptionPane.showMessageDialog(null, "Список успешно сохранён в \"" + filename + "\"");
    }

    private void loadList() {
        try {
            String filename = userType.typeName() + ".dat";
            this.list = SerializeList.loadFromFile(filename, userType);
            JOptionPane.showMessageDialog(null, "Список успешно загружен!");
            updateTotalElementsLabel();
            setTextOnMainField();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ошибка. Файл не найден!");
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ошибка. Структура файла неверна или повреждена!");
        }
    }

    public void updateTotalElementsLabel() {
        totalElements = "TOTAL ELEMENTS = " + list.getTotalElements();
        totalElementsLabel.setText(totalElements);
    }
    private void setTextOnMainField() {
        mainField.setText(list.toString());
    }
}

