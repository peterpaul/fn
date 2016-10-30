package com.github.peterpaul.fn;

import com.github.peterpaul.fn.reciters.ArrayReciter;
import org.junit.Before;
import org.junit.Test;

public class ReciterTest {
    private Reciter<Integer> testSubject;

    @Before
    public void setup() {
        testSubject = new ArrayReciter<>(new Integer[]{1, 2, 3, 4});
    }

    @Test
    public void test() {
        for (int i : testSubject) {
            System.out.println(i);
        }
    }
}
