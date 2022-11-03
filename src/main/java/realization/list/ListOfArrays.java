package realization.list;

import realization.types.comporators.Comporator;

import java.io.Serializable;
import java.util.ArrayList;


public class ListOfArrays {

    protected Node head;
    protected Node tail;
    protected int size;

    /**
     * Вставка массива ссылок в конец списка.
     * @param data данные для добавления в список
     */
    public void push_back(ArrayList data) {
        if (head == null) {
            head = new Node(data);
            tail = head;

            this.size++;
            return;
        }

        Node newElement = new Node(data);
        newElement.prev = tail;
        tail.next = newElement;
        tail = tail.next;

        this.size++;
    }

    /**
     * Добавление элемента в массив, находящийся в указанном узле списка
     * @param indexNode индекс узла
     * @param element элемент для добавления в массив
     */
    public void push_back_byIndexNode(int indexNode, Object element) {
        try {
            Node tmp = getNode(indexNode);
            tmp.data.add(element);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Вставка массива ссылок в произвольное место в списке
     * @param index индекс узла, на место которого нужно вставить элемент
     * @param data данные для вставки
     */
    public void insert(int index, ArrayList data) {
        try {
            Node tmp = getNode(index);
            Node newElement = new Node(data);

            if (tmp == head) {
                newElement.next = head;
                head.prev = newElement;
                head = newElement;
            } else {
                tmp.prev.next = newElement;
                newElement.prev = tmp.prev;
                newElement.next = tmp;
                tmp.prev = newElement;
            }

            this.size++;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Вставка элемента (на указанный индекс) в массив указанного узла
     * @param indexNode порядковый номер узла в списке
     * @param indexOnArray порядковый номер в массиве
     * @param element элемент для вставки в массив
     */
    public void insert_byIndexNode(int indexNode, int indexOnArray, Object element) {
        try {
            Node tmp = getNode(indexNode);
            tmp.data.add(indexOnArray, element);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Получить массив ссылок
     * @param index порядковый номер узла в списке
     * @return
     */
    public ArrayList<Object> get(int index) {
        try {
            return getNode(index).data;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Удаление указанного узла
     * @param index порядковый номер узла в списке
     */
    public void remove(int index) {
        try {
            Node tmp = getNode(index);

            if (tmp == head && head == tail) {
                head = tail = null;
                tmp.next = tmp.prev = null;
                this.size--;
                return;
            } else if (tmp == head && head.next != null) {
                head = head.next;
                head.prev = null;
            } else if (tmp == tail) {
                tail = tail.prev;
                tail.next = null;
            }
            else {
                tmp.prev.next = tmp.next;
                tmp.next.prev = tmp.prev;
            }

            tmp.next = tmp.prev = null;
            this.size--;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Удаление элемента массива по индексу
     * @param indexNode порядковый номер узла
     * @param indexOnArray порядковый номер в массиве
     */
    public void remove_byIndexNode(int indexNode, int indexOnArray) {
        try {
            Node tmp = getNode(indexNode);
            if ( indexOnArray < 0 || indexOnArray >= tmp.data.size()) {
                throw new IndexOutOfBoundsException();
            }
            tmp.data.remove(indexOnArray);

        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Error. Out of bounds array.");
            return;
        }
    }

    /**
     * Получить размер списка
     * @return размер списка
     */
    public int size() {
        return this.size;
    }

    /**
     * Сортировка списка по размеру массива лежащих в узлах списка
     */
    public void sort_bySizeArray() {
        head = mergeSort(head);
    }

    /**
     * Основная функция сортировки слиянием. Разбивает исходный список на подсписки,
     * после чего сливает их в один.
     * @param currentElement
     * @return
     */
    private Node mergeSort(Node currentElement) {
        if (currentElement == null || currentElement.next == null) {
            return currentElement;
        }

        Node middle = getMiddle(currentElement);
        Node middleNext = middle.next;

        middle.next = null;

        Node left = mergeSort(currentElement);

        Node right = mergeSort(middleNext);

        return merge(left, right);
    }

    /**
     * Вспомогательный метод для поиска серединного элемента
     * @param currentElement
     * @return
     */
    private Node getMiddle(Node currentElement) {
        if (currentElement == null)
            return null;

        Node nextElement = currentElement.next;
        Node thisCurElem = currentElement;

        while (nextElement != null) {
            nextElement = nextElement.next;
            if (nextElement != null) {
                thisCurElem = thisCurElem.next;
                nextElement = nextElement.next;
            }
        }
        return thisCurElem;
    }


    /**
     * Вспомогательный метод объеденения подсписков в ходе сортировки слиянием
     * @param left
     * @param right
     * @return
     */
    private Node merge(Node left, Node right) {
        Node merged = new Node(null);
        Node temp = merged;

        while (left != null && right != null) {
            if (left.getSize() < right.getSize()) {
                temp.next = left;
                left.prev = temp;
                left = left.next;
            } else {
                temp.next = right;
                right.prev = temp;
                right = right.next;
            }
            temp = temp.next;
        }

        // Если размеры подсписков были разными, добавляем оставшиеся элементы
        while (left != null) {
            temp.next = left;
            left.prev = temp;
            left = left.next;
            temp = temp.next;
        }
        while (right != null) {
            temp.next = right;
            right.prev = temp;
            right = right.next;
            temp = temp.next;
            this.tail = temp;
        }

        return merged.next;
    }

    /**
     * Метод для сортировки массива указанного узла
     * @param index
     * @param comporator
     */
    public void sortArray(int index, Comporator comporator) {
        try {
            Node tmp = getNode(index);
            tmp.sortArray(comporator);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Вспомогательный метод поиска узла
     * @param index порядковый номер узла в списке
     * @return возвращает искомый узел
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
            System.out.println("Error. Out of bounds list. The size of list = " + this.size);
            throw new IndexOutOfBoundsException("Error. Out of bounds list.");
        }
    }


    public void forEach(Action<Object> a) {
        Node tmp = this.head;

        for (int i = 0; i < this.size; i++) {
            a.toDo(tmp.data);
            tmp = tmp.next;
        }
    }

    @Override
    public String toString() {
        String str = "";

        Node tmp = head;

        for (int i = 0; i < this.size; i++) {
            str += i + ") " + tmp.data.toString() + "[SIZE =" + tmp.getSize() + "]" +"\n";
            tmp = tmp.next;
        }

        return str;
    }

    public void clear() {
        this.size = 0;
        this.head = this.tail = null;
    }

    protected Node getHead() {
        return this.head;
    }
    protected Node getTail() {
        return this.tail;
    }

    protected class Node implements Serializable {

        ArrayList<Object> data;
        Node next;
        Node prev;

        public Node(ArrayList<Object> data) {
            this.data = data;
            this.next = this.prev = null;
        }

        public int getSize() {
            return this.data.size();
        }

        public void sortArray(Comporator comporator) {
            mergeSortArray(this.data, comporator);
        }

        /**
         * Разбиение массива на части
         * @param arrayList
         * @param comporator
         */
        private void mergeSortArray(ArrayList<Object> arrayList, Comporator comporator) {
            int arrSize = arrayList.size();

            if (arrSize == 1) {
                return;
            }

            int middle = arrSize / 2;

            ArrayList<Object> left = new ArrayList<>(middle);
            ArrayList<Object> right = new ArrayList<>(arrSize - middle);

            for (int i = 0; i < middle; i++) {
                left.add(arrayList.get(i));
            }
            for (int i = 0; i < arrSize - middle; i++) {
                right.add(arrayList.get(middle + i));
            }

            mergeSortArray(left, comporator);
            mergeSortArray(right, comporator);
            mergeArrays(arrayList, left, right, comporator);
        }

        /**
         * Слияние подмассивов
         * @param arrayList результирующий массив
         * @param leftArr первый подмассив
         * @param rightArr второй подмассив
         * @param comporator объект для сравнения значений пользовательских типов данных
         */
        private void mergeArrays(ArrayList<Object> arrayList, ArrayList<Object> leftArr, ArrayList<Object> rightArr, Comporator comporator) {
            int leftSize = leftArr.size();
            int rightSize = rightArr.size();

            int i = 0;
            int j = 0;
            int idx = 0;

            while (i < leftSize && j < rightSize) {
                if (comporator.compare(leftArr.get(i), rightArr.get(j)) < 0) {
                    arrayList.set(idx, leftArr.get(i));
                    i++;
                } else {
                    arrayList.set(idx, rightArr.get(j));
                    j++;
                }

                idx++;
            }

            // Если размеры массивов были разными,
            // то добавляем в результирующий массив остатки
            for (int ll = i; ll < leftSize; ll++) {
                arrayList.set(idx++, leftArr.get(ll));
            }
            for (int rr = j; rr < rightSize; rr++) {
                arrayList.set(idx++, rightArr.get(rr));
            }

        }

        @Override
        public String toString() {
            return data.toString();
        }
    }
}
