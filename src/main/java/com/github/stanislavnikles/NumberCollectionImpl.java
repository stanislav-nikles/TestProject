package com.github.stanislavnikles;


import java.util.Arrays;

/**
 * Реализация интерфейса {@link NumberCollection}
 */

public class NumberCollectionImpl implements NumberCollection {

    private static final int DEFAULT_CAPACITY = 10;

    private int size = 0;
    private int[] data;
    private boolean isOptimized = false;

    public NumberCollectionImpl() {
        data = new int[DEFAULT_CAPACITY];
    }

    public void add(int value) {
        /// если массив оптимизирован изменений больше не происходит
        if (!isOptimized) {
            // если в массиве закончилось место, увеличиваем его в 1.5 раза
            if (size == data.length) {
                int old = data.length;
                int current = old + (old >> 1);
                data = Arrays.copyOf(data, current);
            }
            // добавляем элемент в массив
            data[size] = value;
            size++;

        } else throw new IllegalStateException(
                "Вы пытаетесь добавить элемент после оптимизации массива!");
    }

    public void optimize() {
        // убираем пустые элементы массива
        data = Arrays.copyOf(data, size);
        // сортируем в порядке возростания
        Arrays.sort(data);
        isOptimized = true;
    }

    public int[] select(int value1, int value2) {

        if (value2 < value1) throw new IllegalArgumentException("Входные параметры не корректны!");

        int from = binarySearch(data, value1, 0, data.length, true);
        int before = binarySearch(data, value2, from, data.length, false);

        // определяем количество элементов вошедших в выборку
        int count = before - from + 1;
        int[] result = new int[count];

        // копируем элементы в результирующий массив
        System.arraycopy(data, from, result, 0, count);

        return result;
    }

    /**
     * @param arr   - Массив в котором находится элемент.
     * @param elem  - Искомый элемент.
     * @param left  - Начальный индекс массива.
     * @param right - Конечный индекс массива.
     * @param flag  - Флаг сработает если искомый элемент {@param elem} не будет найдет.
     *              Если флаг {@param flag} установлен в {@literal true} будет найдет ближайший больший элемент.
     *              Если флаг {@param flag} установлен в {@literal false} будет найдет ближайший меньший элемент.
     * @return - индекс искомого элемента
     */
    private int binarySearch(int[] arr, int elem, int left, int right, boolean flag) {
        int mid = left + (right - left) / 2;

        // если искомый элемент не найден
        if (left >= right) {
            // возвращаем ближайший больший
            if (flag == true) {
                return left;
            } else {
                // иначе возвращаем ближайший меньший
                return left - 1;
            }
        }

        if (arr[mid] == elem) {
            return mid;
        } else if (arr[mid] > elem) {
            return binarySearch(arr, elem, left, mid, flag);
        } else {
            return binarySearch(arr, elem, mid + 1, right, flag);
        }
    }

    public int getSize() {
        return size;
    }
}
