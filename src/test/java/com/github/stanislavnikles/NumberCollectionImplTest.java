package com.github.stanislavnikles;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumberCollectionImplTest {

    private NumberCollectionImpl coll;

    @Before
    public void setUp() throws Exception {
        coll = new NumberCollectionImpl();
    }

    @After
    public void tearDown() throws Exception {
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
    }
}