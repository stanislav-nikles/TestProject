package com.github.stanislavnikles;


public interface NumberCollection {

    /**
     * Добавлние объекта
     *
     * @param value
     */
    void add(int value);

    /**
     * Оптимизация коллекции
     */
    void optimize();

    /**
     * Выборка
     *
     * @param value1 - от (включительно)
     * @param value2 - до (включительно)
     * @return
     */
    int[] select(int value1, int value2);
}

