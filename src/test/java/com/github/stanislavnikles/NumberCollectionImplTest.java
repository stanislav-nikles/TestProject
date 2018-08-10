package com.github.stanislavnikles;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class NumberCollectionImplTest {

    private NumberCollectionImpl coll;

    @Before
    public void setUp() throws Exception {
        coll = new NumberCollectionImpl();
    }

    @After
    public void tearDown() throws Exception {
        coll = null;
    }

    @Test
    public void add() {
        coll.add(99);
        coll.add(28);
        coll.add(2);
        coll.add(83746);
        coll.add(2);
        coll.add(949);
        coll.add(0);
        coll.add(-78);
        coll.add(2);
        coll.add(182773472);
        coll.add(7);
        coll.add(55);

        int size = coll.getSize();
        Assert.assertEquals(12, size);
    }

    @Test(expected = IllegalStateException.class)
    public void optimize() {
        coll.add(99);
        coll.add(28);
        coll.add(2);

        coll.optimize();
        coll.add(83746);
    }

    @Test
    public void select() {
        coll.add(99);
        coll.add(28);
        coll.add(2);
        coll.add(83746);
        coll.add(2);
        coll.add(949);
        coll.add(0);
        coll.add(-78);
        coll.add(2);
        coll.add(182773472);
        coll.add(7);
        coll.add(55);

        coll.optimize();

        int[] select1 = coll.select(-100, 0);
        int[] expected1 = {-78, 0};
        boolean equals1 = Arrays.equals(select1, expected1);
        Assert.assertTrue(equals1);

        int[] select2 = coll.select(0, 10);
        int[] expected2 = {0, 2, 2, 2, 7};
        boolean equals2 = Arrays.equals(select2, expected2);
        Assert.assertTrue(equals2);

        int[] select3 = coll.select(50, 100);
        int[] expected3 = {55, 99};
        boolean equals3 = Arrays.equals(select3, expected3);
        Assert.assertTrue(equals3);
    }
}

