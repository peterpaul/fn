package com.github.peterpaul.fn;

import org.junit.Test;

import java.util.ArrayList;

import static com.github.peterpaul.fn.List.cons;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

public class ListTest {
    @Test
    public void test() {
        List<Integer> actual = cons(1, cons(2, List.<Integer>nil()));
        assertThat(actual.stream().to(new ArrayList<Integer>()), contains(1, 2));
    }


}
