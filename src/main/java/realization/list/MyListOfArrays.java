package realization.list;

import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Arrays;

import static java.lang.Math.log;
import static java.lang.Math.sqrt;

public class MyListOfArrays {
    private int sizeOfArrays; // размерность каждого массива SIZE0
    private int size; // Размер списка SIZEP
    //private int cidx; // Текущее кол-во массивов CIDX
    private int totalElements; // Количество элементов во всех массивах TOTAL

    private Node head;
    private Node tail;

    public MyListOfArrays(int sizeOfArrays) {
        this.sizeOfArrays = (int) Math.sqrt(sizeOfArrays);
        //this.cidx = 1; // не пустая = 1 линейный массив
        this.size = 1;
        this.head = this.tail = new Node(this.sizeOfArrays);
        this.totalElements = 0;
    }


    /**
     * Добавить элемент в конец всей структуры
     * @param value
     */
    public void add(Object value) {
        Node current = this.tail;
        current.addElementOnArray(value);

        if (current.getCountOfElementsInArray() == current.getSizeArr()) {
            int kk1 = current.getSizeArr() * 3/4; // 75% в старом
            int kk2 = current.getSizeArr() - kk1; // 25% в новом

            // Новое количество элементов в старом массиве
            current.setCountOfElementsInArray(kk1);

            // Создаём новый узел и ставим ему количество элементов в массиве
            Node newNode = new Node(current.getSizeArr());
            current.next = newNode;
            newNode.prev = current;
            this.tail = newNode;
            newNode.setCountOfElementsInArray(kk2);

            // Копируем 25% данных в новый узел из предыдущего узла
            for (int i = 0; i < kk2; i++) {
                newNode.array[i] = current.array[kk1 + i];
                current.array[kk1 + i] = null;
            }

            // Увеличиваем счётчик узлов списка
            this.size++;
        }

        this.totalElements++;
    }

    /**
     * Преобразование логического номера в физический
     * @param logicalIndexOfElement
     * @return Возвращает массив из двух элементов:
     * 1 - индекс узла
     * 2 - физический индекс элемента
     */
    private int[] getIndex(int logicalIndexOfElement) {
        // Обработка неверного значения
        if (logicalIndexOfElement < 0 || logicalIndexOfElement >= this.totalElements) {
            return new int[] {-1, -1};
        }

        // Преобразование в физический индекс
        Node tmp = head;
        int indexNode;
        int physicalIndexOfElement = logicalIndexOfElement;
        for (indexNode = 0; tmp != null; indexNode++) {
            if (physicalIndexOfElement < tmp.getCountOfElementsInArray()) {
                return new int[] {indexNode, physicalIndexOfElement};
            }

            physicalIndexOfElement -= tmp.getCountOfElementsInArray();
            tmp = tmp.next;
        }


        return new int[] {-1, -1};
    }

    /**
     * Вспомогательный метод поиска узла
     * @param index
     * @return
     */
    private Node getNode(int index) {
        try {
            if (index < 0 || index >= this.size) {
                throw new IndexOutOfBoundsException();
            }

            Node tmp = head;
            for (int i = 0; i < index; i++) {
                tmp = tmp.next;
            }

            return tmp;
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Error. Out of bounds list");
        }

        return null;
    }

    /**
     * Вставка по логическому индексу в структуру
     * @param value
     * @param logicalIndexOfElement
     * @return
     */
    public int insert(Object value, int logicalIndexOfElement) {
        try {
            int[] physicalIndex = getIndex(logicalIndexOfElement);
            if (physicalIndex[0] == -1 || physicalIndex[1] == -1) {
                throw new IndexOutOfBoundsException("Error. Index out of bounds");
            }

            Node current = getNode(physicalIndex[0]);

            // Раздвижка в массиве
            for (int i = current.getCountOfElementsInArray() - 1; i >= physicalIndex[1]; i--) {
                current.array[i + 1] = current.array[i];
            }

            // Вставка нового элемента
            current.array[physicalIndex[1]] = value;
            current.setCountOfElementsInArray(current.getCountOfElementsInArray() + 1);
            this.totalElements++;

            // В случае переполнения -> раздвижка списка
            if (current.getCountOfElementsInArray() == current.getSizeArr()) {
                Node newNode = new Node(current.getSizeArr());

                // Перекидываем указатели
                newNode.prev = current;
                newNode.next = current.next;
                if (current.next == null) {
                    // Если current это последний узел списка
                    this.tail = newNode;
                } else {
                    // Если за current ещё есть узел
                    current.next.prev = newNode;
                }
                current.next = newNode;

                // #TODO: ПОМЕНЯТЬ getSizeArr на this.sizeOfArrays
                // Перенос половины current в новый узел
                current.setCountOfElementsInArray(this.sizeOfArrays / 2);
                newNode.setCountOfElementsInArray(current.getSizeArr() - current.getCountOfElementsInArray());
                int countElementsOfNewNode = newNode.getCountOfElementsInArray();
                for (int i = 0; i < countElementsOfNewNode; i++) {
                    newNode.array[i] = current.array[this.sizeOfArrays / 2 + i];
                    current.array[this.sizeOfArrays / 2 + i] = null;
                }

                this.size++;
            }

            return 1;
        } catch (IndexOutOfBoundsException error) {
            System.out.println(error.getMessage());
        }
        return 0;
    }

    public int remove(int logicalIndexOfElement) {
        try {
            int[] physicalIndex = getIndex(logicalIndexOfElement);
            if (physicalIndex[0] == -1 || physicalIndex[1] == -1) {
                throw new IndexOutOfBoundsException("Error. Index out of bounds");
            }

            Node current = getNode(physicalIndex[0]);

            // Сдвиг в массиве
            current.array[physicalIndex[1]] = null;
            for (int i = physicalIndex[1]; i < current.getCountOfElementsInArray(); i++) {
                current.array[i] = current.array[i + 1];
            }

            current.setCountOfElementsInArray(current.getCountOfElementsInArray() - 1);
            this.totalElements--;

            // Если массив оказался пустым, то удалим узел перекинув указатели
            if (current.getCountOfElementsInArray() == 0) {
                if (current.prev != null) {
                    if (current.next != null) {
                        current.prev.next = current.next;
                    } else {
                        current.prev.next = null;
                        this.tail = current.prev;
                    }
                }
                if (current.next != null) {
                    if (current.prev != null) {
                        current.next.prev = current.prev;
                    } else {
                        current.next.prev = null;
                        this.head = current.next;
                    }
                }

                current.array = null;
            }

            return 1;
        } catch (IndexOutOfBoundsException error) {
            System.out.println(error.getMessage());
        }

        return 0;
    }

    public void show() {
        Node tmp = this.head;
        int numOfCurrentNode = 0;
        while (tmp != null) {
            System.out.println(numOfCurrentNode + ": " + Arrays.toString(tmp.array));

            numOfCurrentNode++;
            tmp = tmp.next;
        }
    }

    public int getSize() {
        return size;
    }

    public int getTotalElements() {
        return totalElements;
    }

    private class Node {
        Object[] array;
        int sizeArr;
        int countOfElementsInArray;
        Node next;
        Node prev;

        public Node(int size) {
            this.sizeArr = size;
            this.array = new Object[this.sizeArr];
            this.countOfElementsInArray = 0;
            this.next = this.prev = null;
        }

        public void addElementOnArray(Object value) {
            this.array[countOfElementsInArray] = value;
            this.countOfElementsInArray++;
        }

        public int getCountOfElementsInArray() {
            return countOfElementsInArray;
        }
        public void setCountOfElementsInArray(int countOfElementsInArray) {
            this.countOfElementsInArray = countOfElementsInArray;
        }

        public int getSizeArr() {
            return sizeArr;
        }
        public void setSizeArr(int sizeArr) {
            this.sizeArr = sizeArr;
        }
    }

}
