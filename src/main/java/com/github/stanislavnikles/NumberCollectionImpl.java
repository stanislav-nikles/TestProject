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
        return new int[0];
    }

    /**
     *
     * @param arr   - массив в котором находится элемент
     * @param elem  - искомый элемент
     * @param left  - начальный индекс массива
     * @param right - конечный индекс массива
     * @return      - индекс искомого элемента
     */
    private int binarySearch(int[] arr, int elem, int left, int right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] == elem) {
            return mid;
        } else if (arr[mid] > elem) {
            return binarySearch(arr, elem, left, mid);
        } else {
            return binarySearch(arr, elem, mid + 1, right);
        }
    }

    public int getSize() {
        return size;
    }
}
