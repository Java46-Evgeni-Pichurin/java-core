package A07;

public class ListValidator {

    public static boolean isCircular(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static int indexOfCircular(Node head) {

        // If there is no loop, return -1
        if (!isCircular(head)) {
            return -1;
        }

        int index = 0;
        int elementsInLoop = 0;

        // Detect node in the loop
        Node detectedNodeInLoop = findNodeInLoop(head);
        Node tmp = detectedNodeInLoop;

        // Count N elements in the loop
        do {
            elementsInLoop++;
            tmp = tmp.next;
        } while (tmp != detectedNodeInLoop);

        // Come back references to head and shift N elements for detectedNode
        tmp = head;
        detectedNodeInLoop = head;
        while (elementsInLoop > 0) {
            detectedNodeInLoop = detectedNodeInLoop.next;
            elementsInLoop--;
        }

        // Increment both tmp and detectedNode till they meet
        // Meeting Node is the first Node in the loop
        Node firstNodeInLoop = findFirstNodeInLoop(tmp, detectedNodeInLoop);

        // Run from head till the first Node in loop to find his index
        while (head != firstNodeInLoop) {
            head = head.next;
            index++;
        }
        return index;
    }

    private static Node findFirstNodeInLoop(Node tmp, Node detectedNodeInLoop) {
        while (tmp != detectedNodeInLoop) {
            tmp = tmp.next;
            detectedNodeInLoop = detectedNodeInLoop.next;
        }
        return detectedNodeInLoop;
    }

    public static Node findNodeInLoop(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return slow;
            }
        }
        return null;
    }
}