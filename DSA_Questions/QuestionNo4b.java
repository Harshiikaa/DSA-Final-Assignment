/*
Question 4
        b)	Given a linked list containing an integer value, return the number of steps required
        to sort an array in ascending order by eliminating elements at each step
        Note: at each step remove element a[i] where a[i-1]> a[i]
*/

class Node {
    static class ListNode {
        int val;
        ListNode next;
        // This is a constructor for the ListNode that takes an integer value

        ListNode(int x) {
            val = x;
        }
    }
// This is a method that takes the head of a LinkedList as input and returns the number of steps required to sort it
    public int sortList(ListNode head) {
        // If the LinkedList is null or has only one node, then it is already sorted, so return 0
        if (head == null || head.next == null)
            return 0;
        // Initialize a count variable to 0
        int count = 0;
        // Initialize a current ListNode variable to the head of the LinkedList
        ListNode current = head;
        // Traverse the LinkedList and compare each pair of adjacent nodes
        while (current.next != null) {
            // If the current node has a greater value than the next node, remove the next node and increment the count variable
            if (current.val > current.next.val) {
                current.next = current.next.next;
                count++;
            } else {
                // If the current node has a lesser or equal value than the next node, move to the next node
                current = current.next;
            }
        }
        // Return the number of steps required to sort the LinkedList
        return count;
    }

    // This is the main method of the class that creates a LinkedList and calls the sortList method to sort it
    public static void main(String[] args) {
        // Create an instance of the Node class
        Node obj = new Node();
        // Create a LinkedList with the head node having a value of 5
        ListNode head = new ListNode(5);
        // Add nodes to the LinkedList with the given values
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(0);
        head.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next = new ListNode(3);
        head.next.next.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next.next.next = new ListNode(7);
        // Call the sortList method with the head node of the LinkedList as input and print the result
        System.out.println("Number of steps required to sort the linked list: " + obj.sortList(head));
    }
}