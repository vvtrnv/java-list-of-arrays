package programUI;

import realization.list.ListOfArrays;
import realization.list.SerializeList;
import realization.types.factory.FactoryUserType;
import realization.types.userTypes.UserType;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
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
    private JButton pushbackOnArrayButton;
    private JButton insertOnArrayButton;
    private JButton sortArrayButton;
    private JButton removeFromArrayButton;
    private JTextField pushbackOnArrayField;
    private JLabel pushbackOnArrayText;
    private JTextField insertNodeIDArrayField;
    private JTextField insertIndexOnArrayField;
    private JLabel insertOnArrayNodeidText;
    private JLabel insertOnArrayIndexText;
    private JTextField removeFromArrayNodeIdField;
    private JTextField removeFromArrayIndexField;
    private JLabel removeFromArrayNodeIdText;
    private JLabel removeFromArrayIndexText;
    private JLabel sortArrayNodeIdText;
    private JTextField sortArrayNodeIdField;
    private JButton saveButton;
    private JButton loadButton;

    String currentType;
    public UserType userType;
    public ListOfArrays list;
    public FactoryUserType factoryUserType;

    public GUI() {
        currentType = "Integer";
        factoryUserType = new FactoryUserType();
        userType = factoryUserType.getBuilderByTypeName(currentType);
        list = new ListOfArrays();

        Set<String> typenameList = factoryUserType.getTypeNameList();


        //construct preComponents
        String[] typeComboBoxItems = {"Integer", "Point"};

        //construct components
        addButton = new JButton ("Вставить");
        insertByIdButton = new JButton ("Вставить по id");
        removeByIdButton = new JButton ("Удалить по id");
        mainField = new JTextArea (5, 5);
        typeComboBox = new JComboBox<>(factoryUserType.getTypeNameList().toArray(new String[0]));
        insertByIdField = new JTextField (5);
        removeByIdField = new JTextField (5);
        sortListBySizeButton = new JButton ("Сортировать по размеру");
        getByIdButton = new JButton ("Получить по id");
        getByIdField = new JTextField (5);
        pushbackOnArrayButton = new JButton ("Вставить в конец массива");
        insertOnArrayButton = new JButton ("Вставить в массив ");
        sortArrayButton = new JButton ("Сортировка массива");
        removeFromArrayButton = new JButton ("Удалить в массиве");
        pushbackOnArrayField = new JTextField (5);
        pushbackOnArrayText = new JLabel ("ID узла");
        insertNodeIDArrayField = new JTextField (5);
        insertIndexOnArrayField = new JTextField (5);
        insertOnArrayNodeidText = new JLabel ("ID");
        insertOnArrayIndexText = new JLabel ("Индекс в массиве");
        removeFromArrayNodeIdField = new JTextField (5);
        removeFromArrayIndexField = new JTextField (5);
        removeFromArrayNodeIdText = new JLabel ("ID");
        removeFromArrayIndexText = new JLabel ("Индекс в массиве");
        sortArrayNodeIdText = new JLabel ("ID узла");
        sortArrayNodeIdField = new JTextField (5);
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
        add (pushbackOnArrayButton);
        add (insertOnArrayButton);
        add (sortArrayButton);
        add (removeFromArrayButton);
        add (pushbackOnArrayField);
        add (pushbackOnArrayText);
        add (insertNodeIDArrayField);
        add (insertIndexOnArrayField);
        add (insertOnArrayNodeidText);
        add (insertOnArrayIndexText);
        add (removeFromArrayNodeIdField);
        add (removeFromArrayIndexField);
        add (removeFromArrayNodeIdText);
        add (removeFromArrayIndexText);
        add (sortArrayNodeIdText);
        add (sortArrayNodeIdField);
        add (saveButton);
        add (loadButton);

        //set component bounds
        typeComboBox.setBounds (600, 15, 180, 30);

        // Work with nodes
        addButton.setBounds (800, 70, 140, 30);

        insertByIdButton.setBounds (800, 110, 140, 30);
        insertByIdField.setBounds (650, 110, 140, 30);

        removeByIdButton.setBounds (800, 155, 140, 30);
        removeByIdField.setBounds (650, 155, 140, 30);

        getByIdButton.setBounds (800, 200, 140, 30);
        getByIdField.setBounds (650, 200, 140, 30);

        sortListBySizeButton.setBounds (650, 250, 290, 30);


        // Work with arrays on nodes
        pushbackOnArrayButton.setBounds (740, 350, 250, 30);
        pushbackOnArrayField.setBounds (660, 350, 60, 30);
        pushbackOnArrayText.setBounds (660, 320, 100, 25);

        insertOnArrayButton.setBounds (740, 415, 250, 30);
        insertNodeIDArrayField.setBounds (550, 415, 60, 30);
        insertIndexOnArrayField.setBounds (660, 415, 60, 30);
        insertOnArrayNodeidText.setBounds (570, 390, 20, 25);
        insertOnArrayIndexText.setBounds (630, 390, 130, 25);

        removeFromArrayButton.setBounds (740, 480, 250, 30);
        removeFromArrayNodeIdField.setBounds (550, 480, 60, 30);
        removeFromArrayIndexField.setBounds (660, 480, 60, 30);
        removeFromArrayNodeIdText.setBounds (570, 455, 20, 25);
        removeFromArrayIndexText.setBounds (630, 455, 130, 25);

        sortArrayButton.setBounds (740, 540, 250, 30);
        sortArrayNodeIdField.setBounds (660, 540, 60, 30);
        sortArrayNodeIdText.setBounds (650, 515, 130, 25);

        // Main field
        mainField.setBounds (25, 10, 500, 680);
        mainField.setEditable(false);
        mainField.setLineWrap(true);
        mainField.setWrapStyleWord(true);

        // Serialization
        saveButton.setBounds (550, 610, 145, 70);
        loadButton.setBounds (720, 610, 140, 70);

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

        pushbackOnArrayButton.addActionListener(e -> pushElementOnArray());
        insertOnArrayButton.addActionListener(e -> insertElementOnArray());
        removeFromArrayButton.addActionListener(e -> removeElementFromArray());
        sortArrayButton.addActionListener(e -> sortArrayByNodeID());

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

    private ArrayList createRandomArrayBySize(final int SIZE) {

        ArrayList arrayToAdd = new ArrayList<>();
        for(int i = 0; i < SIZE; i++) {
            arrayToAdd.add(userType.create());
        }

        return arrayToAdd;
    }
    private ArrayList createRandomArray() {
        Random random = new Random();
        int sizeArray = random.nextInt(8) + 1;

        return createRandomArrayBySize(sizeArray);
    }

    private void selectTypeList(ActionEvent actionEvent) {
        JComboBox comboBox = (JComboBox) actionEvent.getSource();
        String item = (String) comboBox.getSelectedItem();

        if (currentType != item) {
            currentType = item;
            userType = factoryUserType.getBuilderByTypeName(currentType);
            list = new ListOfArrays();
            setTextOnMainField();
        }
    }

    private void pushNode() {
        ArrayList arrayToAdd = createRandomArray();
        list.push_back(arrayToAdd);
        setTextOnMainField();
    }

    private void pushNodeByID() {
        String nodeID = insertByIdField.getText();
        if (nodeID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ошибка. Поле \"id\" пусто!");
            return;
        }

        list.insert(Integer.parseInt(nodeID), createRandomArray());
        setTextOnMainField();
    }

    private void removeNodeByID() { // #TODO когда только один элемент в списке, не удаляется и выбрасывает исключение
        String nodeID = removeByIdField.getText();
        if (nodeID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ошибка. Поле \"id\" пусто!");
            return;
        }

        list.remove(Integer.parseInt(nodeID));
        setTextOnMainField();
    }

    private void sortListBySizeArray() {
        list.sort_bySizeArray();
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

    private void pushElementOnArray() {
        String nodeID = pushbackOnArrayField.getText();
        if (nodeID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ошибка. Поле \"id\" пусто!");
            return;
        }

        list.push_back_byIndexNode(Integer.parseInt(nodeID), userType.create());
        setTextOnMainField();
    }

    private void insertElementOnArray() {
        String nodeID = insertNodeIDArrayField.getText();
        String indexOnArray = insertIndexOnArrayField.getText();

        if (nodeID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ошибка. Поле \"id\" пусто!");
            return;
        } else if(indexOnArray.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ошибка. Поле \"Индекс в массиве\" пусто!");
            return;
        }

        list.insert_byIndexNode(Integer.parseInt(nodeID), Integer.parseInt(indexOnArray), userType.create());
        setTextOnMainField();
    }

    private void removeElementFromArray() {
        String nodeID = removeFromArrayNodeIdField.getText();
        String indexOnArray = removeFromArrayIndexField.getText();

        if (nodeID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ошибка. Поле \"id\" пусто!");
            return;
        } else if(indexOnArray.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ошибка. Поле \"Индекс в массиве\" пусто!");
            return;
        }

        list.remove_byIndexNode(Integer.parseInt(nodeID), Integer.parseInt(indexOnArray));
        setTextOnMainField();
    }

    private void sortArrayByNodeID() {
        String nodeID = sortArrayNodeIdField.getText();

        if (nodeID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ошибка. Поле \"id\" пусто!");
            return;
        }

        list.sortArray(Integer.parseInt(nodeID), userType.getTypeComparator());
        setTextOnMainField();
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
            setTextOnMainField();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ошибка. Файл не найден!");
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ошибка. Структура файла неверна или повреждена!");
        }
    }

    private void setTextOnMainField() {
        mainField.setText(list.toString());
    }
}

