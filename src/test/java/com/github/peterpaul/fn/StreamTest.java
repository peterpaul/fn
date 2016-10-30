package com.github.peterpaul.fn;

import com.github.peterpaul.fn.recitables.IterableRecitable;
import org.hamcrest.Matchers;
import org.junit.Test;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static com.github.peterpaul.fn.Stream.stream;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

public class StreamTest {
    @Test
    public void testFilter() {
        java.util.List<Integer> actual =
                stream(1, 2, 3, 4, 5)
                        .filter(new Predicate<Integer>() {
                            @Nonnull
                            @Override
                            public Boolean apply(@Nonnull Integer input) {
                                return input % 2 == 1;
                            }
                        })
                        .to(new ArrayList<Integer>());
        assertThat(actual, contains(1, 3, 5));
    }

    @Test
    public void testFlatMap() {
        java.util.List<Integer> actual =
                stream(Arrays.asList(1, 2, 3), Collections.singleton(4), Collections.<Integer>emptySet(), Arrays.asList(5, 6))
                        .flatMap(new Function<Collection<Integer>, Recitable<Integer>>() {
                            @Nonnull
                            @Override
                            public Recitable<Integer> apply(@Nonnull Collection<Integer> input) {
                                return new IterableRecitable<>(input);
                            }
                        })
                        .map(new Function<Integer, Integer>() {
                            @Nonnull
                            @Override
                            public Integer apply(@Nonnull Integer input) {
                                return input * 2;
                            }
                        })
                        .to(new ArrayList<Integer>());
        assertThat(actual, contains(2, 4, 6, 8, 10, 12));
    }

    @Test
    public void testReduceEmptyStream() {
        Option<Object> actual =
                stream(Collections.emptyList())
                        .reduce(new Reduction<Object>() {
                            @Nonnull
                            @Override
                            public Object apply(@Nonnull Object o, @Nonnull Object o2) {
                                return new Object();
                            }
                        });
        assertThat(actual, is(equalTo(Option.none())));
    }

    @Test
    public void testReduceList() {
        Option<Integer> actual =
                stream(1, 2, 3, 4, 5)
                        .reduce(new Reduction<Integer>() {
                            @Nonnull
                            @Override
                            public Integer apply(@Nonnull Integer a, @Nonnull Integer b) {
                                return a + b;
                            }
                        });
        assertThat(actual, is(equalTo(Option.some(15))));
    }

    @Test
    public void testReduceListWithInitialValue() {
        int actual =
                stream(1, 2, 3, 4, 5)
                        .reduce(0, new Reduction<Integer>() {
                            @Nonnull
                            @Override
                            public Integer apply(@Nonnull Integer a, @Nonnull Integer b) {
                                return a + b;
                            }
                        });
        assertThat(actual, is(equalTo(15)));
    }

    @Test
    public void testTake5() {
        ArrayList<Integer> actual = stream(1, 2, 3, 4, 5, 6, 7)
                .take(5)
                .to(new ArrayList<Integer>());
        assertThat(actual, contains(1, 2, 3, 4, 5));
    }

    @Test
    public void testTake5From3() {
        ArrayList<Integer> actual = stream(1, 2, 3)
                .take(5)
                .to(new ArrayList<Integer>());
        assertThat(actual, contains(1, 2, 3));
    }

    @Test
    public void testDrop5() {
        ArrayList<Integer> actual = stream(1, 2, 3, 4, 5, 6, 7)
                .drop(5)
                .to(new ArrayList<Integer>());
        assertThat(actual, contains(6, 7));
    }

    @Test
    public void testDrop5From3() {
        ArrayList<Integer> actual = stream(1, 2, 3)
                .drop(5)
                .to(new ArrayList<Integer>());
        assertThat(actual, is(Matchers.<Integer>empty()));
    }

    @Test
    public void testZip() {
        stream(1, 2, 3, 4, 5)
                .zip(stream('a', 'b', 'c'))
                .peek(new Consumer<Pair<Integer, Character>>() {
                    @Override
                    public void consume(@Nonnull Pair<Integer, Character> input) {
                        System.out.println(input);
                    }
                })
                .to(new ArrayList<Pair<Integer, Character>>());
    }
}
