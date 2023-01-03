import org.junit.Before;
import org.junit.Test;
import realization.list.MyListOfArrays;
import realization.types.factory.FactoryUserType;
import realization.types.userTypes.UserType;


import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class TestMyListOfArrays {
    private FactoryUserType userFactory;
    private UserType userType;
    private MyListOfArrays actualListOfArrays, expectedListOfArrays;

    @Before
    public void setUp() {
        userFactory = new FactoryUserType();
        userType = userFactory.getBuilderByTypeName("Integer");

        actualListOfArrays =  new MyListOfArrays(100);
        expectedListOfArrays = new MyListOfArrays(100);
    }

    /**
     * Тест с исходным набором, который содержит одинаковые значения
     */
    @Test
    public void sortListWithSameValues() {
        for (int i = 0; i <= 15; i++) {
            actualListOfArrays.add(userType.parseValue(1 + ""));
            expectedListOfArrays.add(userType.parseValue(1 + ""));
        }
        actualListOfArrays = actualListOfArrays.sort(userType.getTypeComparator());
        assertEquals(expectedListOfArrays.toString(), actualListOfArrays.toString());
    }

    /**
     * Тест с исходным набором, который упорядочен в прямом порядке
     */
    @Test
    public void sortListOrderedInDirectOrder() {
        for (int i = 0; i <= 15; i++) {
            actualListOfArrays.add(userType.parseValue(i + ""));
            expectedListOfArrays.add(userType.parseValue(i + ""));
        }

        actualListOfArrays = actualListOfArrays.sort(userType.getTypeComparator());
        assertEquals(expectedListOfArrays.toString(), actualListOfArrays.toString());
    }

    /**
     * Тест с исходным набором, который упорядочен в обратном порядке
     */
    @Test
    public void sortListOrderedInReverseOrder() {
        for (int i = 15; i >= 0; i--) {
            actualListOfArrays.add(userType.parseValue(i + ""));
        }
        for (int i = 0; i <= 15; i++) {
            expectedListOfArrays.add(userType.parseValue(i + ""));
        }

        actualListOfArrays = actualListOfArrays.sort(userType.getTypeComparator());
        assertEquals(expectedListOfArrays.toString(), actualListOfArrays.toString());
    }

    /**
     * Тест с исходным набором, в котором есть несколько групп повторяющихся элементов
     */
    @Test
    public void sortListContainsDuplicateValues() {
        ArrayList<Integer> values =  new ArrayList<>();
        values.add(0);
        values.add(1);
        values.add(2);
        values.add(3);
        values.add(4);
        values.add(5);
        values.add(1);
        values.add(6);
        values.add(7);
        values.add(8);
        values.add(9);
        values.add(10);
        values.add(11);
        values.add(12);
        values.add(13);
        values.add(14);

        for (Integer value : values) {
            actualListOfArrays.add(userType.parseValue(value.toString()));
        }

        Collections.sort(values);
        for (Integer value : values) {
            expectedListOfArrays.add(userType.parseValue(value.toString()));
        }

        actualListOfArrays = actualListOfArrays.sort(userType.getTypeComparator());
        assertEquals(expectedListOfArrays.toString(), actualListOfArrays.toString());
    }

    /**
     * Тест с исходным набором, в котором есть несколько групп повторяющихся элементов
     */
    @Test
    public void sortListContainsGroupOfDuplicateValues() {
        ArrayList<Integer> values =  new ArrayList<>();
        values.add(0);
        values.add(1);
        values.add(2);
        values.add(3);
        values.add(1);
        values.add(3);
        values.add(2);
        values.add(4);
        values.add(5);
        values.add(4);
        values.add(5);
        values.add(0);
        values.add(6);
        values.add(7);
        values.add(6);
        values.add(7);

        for (Integer value : values) {
            actualListOfArrays.add(userType.parseValue(value.toString()));
        }

        Collections.sort(values);
        for (Integer value : values) {
            expectedListOfArrays.add(userType.parseValue(value.toString()));
        }

        actualListOfArrays = actualListOfArrays.sort(userType.getTypeComparator());
        assertEquals(expectedListOfArrays.toString(), actualListOfArrays.toString());
    }

    /**
     * Тест с исходным набором. Экстремальное значение находится в начале
     */
    @Test
    public void sortListExtremeValueAtBegin() {
        ArrayList<Integer> values =  new ArrayList<>();
        values.add(99999);
        values.add(1);
        values.add(2);
        values.add(3);
        values.add(4);
        values.add(5);
        values.add(6);
        values.add(7);
        values.add(8);
        values.add(9);
        values.add(10);
        values.add(11);
        values.add(12);
        values.add(13);
        values.add(14);
        values.add(15);

        for (Integer value : values) {
            actualListOfArrays.add(userType.parseValue(value.toString()));
        }

        Collections.sort(values);
        for (Integer value : values) {
            expectedListOfArrays.add(userType.parseValue(value.toString()));
        }

        actualListOfArrays = actualListOfArrays.sort(userType.getTypeComparator());
        assertEquals(expectedListOfArrays.toString(), actualListOfArrays.toString());
    }

    /**
     * Тест с исходным набором. Экстремальное значение находится в середине
     */
    @Test
    public void sortListExtremeValueAtMiddle() {
        ArrayList<Integer> values =  new ArrayList<>();
        values.add(1);
        values.add(2);
        values.add(3);
        values.add(4);
        values.add(5);
        values.add(6);
        values.add(7);
        values.add(99999);
        values.add(8);
        values.add(9);
        values.add(10);
        values.add(11);
        values.add(12);
        values.add(13);
        values.add(14);
        values.add(15);

        for (Integer value : values) {
            actualListOfArrays.add(userType.parseValue(value.toString()));
        }

        Collections.sort(values);
        for (Integer value : values) {
            expectedListOfArrays.add(userType.parseValue(value.toString()));
        }

        actualListOfArrays = actualListOfArrays.sort(userType.getTypeComparator());
        assertEquals(expectedListOfArrays.toString(), actualListOfArrays.toString());
    }

    /**
     * Тест с исходным набором. Экстремальное значение находится в конце
     */
    @Test
    public void sortListExtremeValueAtEnd() {
        ArrayList<Integer> values =  new ArrayList<>();
        values.add(1);
        values.add(2);
        values.add(3);
        values.add(10);
        values.add(11);
        values.add(12);
        values.add(13);
        values.add(4);
        values.add(5);
        values.add(6);
        values.add(7);
        values.add(8);
        values.add(9);
        values.add(14);
        values.add(15);
        values.add(99999);

        for (Integer value : values) {
            actualListOfArrays.add(userType.parseValue(value.toString()));
        }

        Collections.sort(values);
        for (Integer value : values) {
            expectedListOfArrays.add(userType.parseValue(value.toString()));
        }

        actualListOfArrays = actualListOfArrays.sort(userType.getTypeComparator());
        assertEquals(expectedListOfArrays.toString(), actualListOfArrays.toString());
    }

    /**
     * Тест с исходным набором. Несколько совпадающих экстремальных значений
     */
    @Test
    public void sortListMultiplyExtremeValues() {
        ArrayList<Integer> values =  new ArrayList<>();
        values.add(99999);
        values.add(2);
        values.add(3);
        values.add(4);
        values.add(5);
        values.add(6);
        values.add(7);
        values.add(99999);
        values.add(9);
        values.add(10);
        values.add(11);
        values.add(12);
        values.add(13);
        values.add(14);
        values.add(15);
        values.add(99999);

        for (Integer value : values) {
            actualListOfArrays.add(userType.parseValue(value.toString()));
        }

        Collections.sort(values);
        for (Integer value : values) {
            expectedListOfArrays.add(userType.parseValue(value.toString()));
        }

        actualListOfArrays = actualListOfArrays.sort(userType.getTypeComparator());
        assertEquals(expectedListOfArrays.toString(), actualListOfArrays.toString());
    }

    /**
     * Тест производительности сортировки
     */
    @Test
    public void sortPerformance() {
        for (int i = 1; i < 1025; i *= 2) {
            int size = i * 1000;
            System.out.println("SIZE = " + size);

            MyListOfArrays listOfArrays = new MyListOfArrays(size + 100);

            for (int j = 0; j < size; j++) {
                listOfArrays.add(userType.create());
            }

            long startTime = System.nanoTime();

            try {
                listOfArrays = listOfArrays.sort(userType.getTypeComparator());
            } catch (StackOverflowError err) {
                System.out.println("Stack error");
                return;
            }

            long stopTime =System.nanoTime();

            System.out.println("Passed time: " + ((stopTime - startTime) * 1.0 / 1_000_000_000));
        }
    }
}
