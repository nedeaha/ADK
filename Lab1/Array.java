import java.util.Stack;

public class Array {
   
    Stack<Node> stack = new Stack<>();

    public class Node {
        int value;
        Node left, right;

        Node() {
            this.value = 0;
            this.left = null;
            this.right = null;
        }

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public Array newarray() {
        Array a = new Array();
        Node root = new Node();
        stack.push(root);
        a.stack = this.stack;
        return a;
    }

    public Array set(Array a, int i, int value) {
        Node oldRoot = a.stack.peek();
        Node newRoot;
        Node start;
        
        int height = getHeight(oldRoot.right);

        if  (oldRoot.value < i) {
            newRoot = new Node(i);
            newRoot.right = new Node();
            newRoot.right.left = oldRoot.right;
            newRoot.right.right = new Node();
            start = newRoot.right.right;
            oldRoot = null;
        } else {
            newRoot = new Node(oldRoot.value);
            newRoot.right = new Node();
            start = newRoot.right;
        }

        // if sats för att välja vänster eller höger
        if (left) {
            start.left = new Node();
            start.right = oldRoot.right.right;
            setRec(i, value, oldRoot.left, start.left, height);
        } else {
            start.right = new Node();
            start.left = oldRoot.right.left;
            setRec(i, value, oldRoot.right, start.right, height);
        }

        a.stack.push(newRoot);
        return a;   
    }

    public void setRec(int i, int value, Node currentOld, Node currentNew, int height) {
        if (height == 0) {
            currentNew.value = value;
            return;
        } else {
            if (left) {
                currentNew.left = new Node();
                currentNew.right = currentOld.right;
                setRec(i, value, currentOld.left, currentNew.left, height-1);
            } else {
                currentNew.right = new Node();
                currentNew.left = currentOld.left;
                setRec(i, value, currentOld.right, currentNew.right, height-1);
            }
        }
    }

    public int getHeight(Node n) {
        if (n == null) return 0;
        return height(n);
    }

    public int height(Node n) {
        if (n == null) return -1;
        return 1 + Math.max(height(n.left), height(n.right));
    }

    public int get(Array a, int i) {
        return 0;
    }


}