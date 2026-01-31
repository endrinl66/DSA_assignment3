import java.util.*;

public class Assignment3 {

    // ===== Method 1 =====
    // Build a linked list of Integers: all evens first (stable), then odds (stable).
    public static Node<Integer> build(int[] arr) {
        Node<Integer> evenHead = null, evenTail = null;
        Node<Integer> oddHead  = null,  oddTail  = null;

        for (int x : arr) {
            Node<Integer> n = new Node<>(x, null);  // Node(T v, Node<T> n)
            if ((x % 2) == 0) { // even
                if (evenHead == null) { evenHead = evenTail = n; }
                else { evenTail.setNext(n); evenTail = n; }
            } else {             // odd
                if (oddHead == null) { oddHead = oddTail = n; }
                else { oddTail.setNext(n); oddTail = n; }
            }
        }

        if (evenHead == null) return oddHead;    // only odds
        if (oddHead  == null) return evenHead;   // only evens
        evenTail.setNext(oddHead);               // stitch
        return evenHead;                         // head of final list
    }
    

    // ===== Method 2 =====
    // Read k from the provided Scanner, validate 0..n-1, rotate LEFT by k, return new head.
    public static Node<Integer> rotateByInput(Node<Integer> head, Scanner sc) {
        if (head == null || head.getNext() == null) return head;

        // length + tail
        int n = 1;
        Node<Integer> tail = head;
        while (tail.getNext() != null) { tail = tail.getNext(); n++; }
        System.out.println("Enter an integer between 0 and " + (n - 1) + ":");
        int k;
        while (true) {
            if (!sc.hasNextInt()) {
            	sc.next(); // discard
                System.out.printf("Enter an integer between 0 and %d: ", n - 1);
                continue;
            }
            k = sc.nextInt();
            if (k < 0 || k > n - 1) {
                System.out.printf("Error: enter a number between 0 and %d.%n", n - 1);
                System.out.printf("Enter an integer between 0 and %d: ", n - 1);
                continue;
            }
            break;
        }

        if (k == 0) return head;

        // make circular, break after k nodes (left-rotate by k)
        tail.setNext(head);
        Node<Integer> newTail = head;
        for (int i = 1; i < k; i++) newTail = newTail.getNext();
        Node<Integer> newHead = newTail.getNext();
        newTail.setNext(null);
        return newHead;
    }

    // ===== main =====
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // read input
        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.printf("Enter %d integers:%n", n);
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        // build
        Node<Integer> head = build(arr);

        // print (in line)
        {
            Node<Integer> cur = head;
            boolean first = true;
            while (cur != null) {
                if (!first) System.out.print(" ");
                System.out.print(cur.getValue());
                first = false;
                cur = cur.getNext();
            }
            System.out.println();
        }

        // rotate
      
        head = rotateByInput(head, sc);

        {
            Node<Integer> cur = head;
            boolean first = true;
            while (cur != null) {
                if (!first) System.out.print(" ");
                System.out.print(cur.getValue());
                first = false;
                cur = cur.getNext();
            }
            System.out.println();
        }
    }
}
