package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.util.MyPredicate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

class MyEvenPredicate implements MyPredicate<Integer> {
    @Override
    public boolean test(Integer t) {
        return t % 2 == 0;
    }
}

class MyPredicateTest {
    MyEvenPredicate p1 = new MyEvenPredicate();
    MyPredicate<Integer> p2 = t -> t >= 0 && t <= 10;
    Collection<Integer> collection1 = new ArrayList<>();
    Collection<Integer> collection2 = new ArrayList<>();


    @Test
    void test() {
        assertTrue(p1.test(2));
        assertFalse(p1.test(3));

		assertFalse(p1.negate().test(2));
		assertTrue(p1.negate().test(3));
        MyPredicate<Integer> p2 = p1.negate(); // odd predicate
        assertFalse(p2.test(2));
        assertTrue(p2.test(3));

        MyPredicate<Integer> p3 = p2.negate(); // even predicate
        assertTrue(p3.test(2));
        assertFalse(p3.test(3));
    }
    @Test
    void andTest() {
        assertTrue(p1.and(p2).test(2));
        assertFalse(p1.and(p2).test(3));
        assertFalse(p1.and(p2).test(11));
        assertFalse(p1.and(p2).test(12));
    }
    @Test
    void orTest() {
        assertTrue(p1.or(p2).test(2));
        assertTrue(p1.or(p2).test(3));
        assertFalse(p1.or(p2).test(11));
        assertTrue(p1.or(p2).test(12));
    }
    @Test
    void notTest() {
        assertTrue(MyPredicate.not(p1).test(3));
        assertTrue(MyPredicate.not(p2).test(13));
        assertFalse(MyPredicate.not(p1.or(p2)).test(5));
        assertTrue(MyPredicate.not(p1.and(p2)).test(12));
    }
    @Test
    void isEqualTest() {
        collection1.add(1);
        collection2.add(1);
        assertTrue(MyPredicate.isEqual(collection1).test(collection2));
        collection1.add(2);
        assertFalse(MyPredicate.isEqual(collection1).test(collection2));
        assertFalse(MyPredicate.isEqual(collection1).test(null));
        assertTrue(MyPredicate.isEqual(null).test(null));
    }
}