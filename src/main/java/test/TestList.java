package test;

import realization.list.ListOfArrays;
import realization.list.SerializeList;
import realization.types.factory.FactoryUserType;
import realization.types.userTypes.UserType;

import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;

/**
 * Класс тестирования списка
 * Вывод производится в консоль
 */
public class TestList {

    private FactoryUserType factoryUserType;
    private UserType userType;
    private ListOfArrays list;

    private final String INTEGER_FILENAME = "testInteger.dat";
    private final String POINT2D_FILENAME = "testPoint2D.dat";
    private ArrayList<Object> createRandomArrayBySize(final int SIZE) {
        ArrayList<Object> arrayToAdd = new ArrayList<>();
        for(int i = 0; i < SIZE; i++) {
            arrayToAdd.add(userType.create());
        }

        return arrayToAdd;
    }
    private ArrayList<Object> createRandomArray(int minSize, int maxSize) {
        Random random = new Random();
        int sizeArray = random.nextInt(maxSize-minSize) + minSize;

        return createRandomArrayBySize(sizeArray);
    }

    private void printList() {
        System.out.println("~ PRINT LIST ~");
        System.out.println(list.toString());
    }

    private void saveList(String filename) {
        System.out.println("\n~~ SAVE TO FILE " + filename + " ~~");
        SerializeList.saveToFile(list, filename, userType);
    }

    private void loadList(String filename) {
        try {
            System.out.println("\n~~ LOAD LIST FROM FILE " + filename + " ~~");
            list.clear();
            list = SerializeList.loadFromFile(filename, userType);
            printList();
        } catch (FileNotFoundException ex) {
            System.out.println("Ошибка. Файл не найден!");
        }
        catch (Exception ex) {
            System.out.println("Ошибка. Структура файла неверна или повреждена!");
        }
    }
    private void findNode(int[] arrayOfIndex) {
        for(int i = 0; i < arrayOfIndex.length; i++) {
            System.out.println(arrayOfIndex[i] + ") " + list.get(arrayOfIndex[i]).toString());
        }
    }

    private void insertNode(int[] arrayOfIndex) {
        for(int i = 0; i < arrayOfIndex.length; i++) {
            list.insert(arrayOfIndex[i], createRandomArray(2, 5));
        }
    }

    private void removeNode(int[] arrayOfIndex) {
        for(int i = 0; i < arrayOfIndex.length; i++) {
            list.remove(arrayOfIndex[i]);
        }
    }

    private void pushbackElementOnArray(int[] arrayOfIndex) {
        for(int i = 0; i < arrayOfIndex.length; i++) {
            list.push_back_byIndexNode(arrayOfIndex[i], userType.create());
        }
    }

    private void sortArrayNode(int[] arrayOfIndex) {
        for(int i = 0; i < arrayOfIndex.length; i++) {
            list.sortArray(arrayOfIndex[i], userType.getTypeComparator());
        }
    }

    private void testProgram(String filename) {
        printList();
        saveList(filename);

        System.out.println("~~ FIND NODE BY INDEX 0, 4, 5 ~~");
        findNode(new int[] {0, 4, 5});

        System.out.println("\n~~ INSERT NODE ON INDEX 0, 5 ~~");
        insertNode(new int[] {0, 5});
        printList();

        System.out.println("\n~~ REMOVE NODE ON INDEX 0, 5 ~~");
        removeNode(new int[] {0, 5});
        printList();

        System.out.println("\n~~ SORT LIST BY ARRAY SIZE~~");
        list.sort_bySizeArray();
        printList();

        loadList(filename);

        System.out.println("\n~~ ITERATOR FOREACH ~~");
        list.forEach(System.out::println);

        System.out.println("\n~~ PUSH ELEMENT ON ARRAY ON INDEX 0, 3, 6 ~~");
        pushbackElementOnArray(new int[] {0, 3, 6});
        printList();

        System.out.println("\n~~ INSERT ELEMENT ON ARRAY ON NODE 3 BY INDEX 1");
        list.insert_byIndexNode(3, 1, userType.create());
        printList();

        System.out.println("\n~~ REMOVE ELEMENT FROM ARRAY ON NODE 3 BY INDEX 1");
        list.remove_byIndexNode(3, 1);
        printList();


        System.out.println("\n~~ SORT ARRAY ON NODE 0, 1, 2, 3");
        sortArrayNode(new int[] {0, 1, 2, 3});
        printList();
    }

    public void testingIntegerUserType() {
        factoryUserType = new FactoryUserType();
        userType = factoryUserType.getBuilderByTypeName("Integer");
        list = new ListOfArrays();

        System.out.println("~~~~ TESTING INTEGER USER TYPE ~~~~");
        list.push_back(createRandomArray(4, 10));
        list.push_back(createRandomArray(4, 10));
        list.push_back(createRandomArray(4, 10));
        list.push_back(createRandomArray(4, 10));
        list.push_back(createRandomArray(4, 10));
        list.push_back(createRandomArray(4, 10));
        list.push_back(createRandomArray(4, 10));
        testProgram(INTEGER_FILENAME);
        System.out.println("\n~~~~ END TESTING INTEGER USER TYPE ~~~~");
    }

    public void testingPoint2DUserType() {
        factoryUserType = new FactoryUserType();
        userType = factoryUserType.getBuilderByTypeName("Point2D");
        list = new ListOfArrays();

        System.out.println("\n\n\n~~~~ TESTING POINT2D USER TYPE ~~~~");
        list.push_back(createRandomArray(2, 6));
        list.push_back(createRandomArray(2, 6));
        list.push_back(createRandomArray(2, 6));
        list.push_back(createRandomArray(2, 6));
        list.push_back(createRandomArray(2, 6));
        list.push_back(createRandomArray(2, 6));
        list.push_back(createRandomArray(2, 6));
        list.push_back(createRandomArray(2, 6));
        testProgram(POINT2D_FILENAME);
        System.out.println("~~~~ TESTING POINT2D USER TYPE ~~~~");
    }
}
