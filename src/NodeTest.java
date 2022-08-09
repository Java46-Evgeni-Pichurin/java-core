import A07.Node;
import org.junit.jupiter.api.Test;

import static A07.ListValidator.*;
import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {
    Node n9 = new Node(9, null);
    Node n8 = new Node(8, n9);
    Node n7 = new Node(7, n8);
    Node n6 = new Node(6, n7);
    Node n5 = new Node(5, n6);
    Node n4 = new Node(4, n5);
    Node n3 = new Node(3, n4);
    Node n2 = new Node(2, n3);
    Node n1 = new Node(1, n2);
    Node n0 = new Node(0, n1); // Head


    @Test
    void hasLoopTest() {
        assertFalse(isCircular(n0)); // no loop
        n9.next = n3;
        assertTrue(isCircular(n0));
    }
    @Test
    void indexTest() {
        n9.next = null;
        assertEquals(-1, indexOfCircular(n0)); // no loop
        n5.next = n0;
        assertEquals(0, indexOfCircular(n0)); // loop starts from n0
        n5.next = n6;
        n8.next = n6;
        assertEquals(6, indexOfCircular(n0)); // loop starts from n6
        n8.next = n9;
        n4.next = n4;
        assertEquals(4, indexOfCircular(n0)); // loop starts from n4
        n4.next = n5;
    }
}
