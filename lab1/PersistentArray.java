import java.util.Stack;

public class PersistentArray {
   
    Stack<Node> stack = new Stack<>();
    int height = 0;

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

    public PersistentArray newarray() {
        stack.push(new Node());
        height = 32;
        return this;
    }

    public PersistentArray set(PersistentArray a, int i, int value) {
        Node oldRoot;
        Node newRoot;
        
        if (a.stack.isEmpty()) {
            newarray();
        } 

        newRoot = new Node();
        oldRoot = a.stack.peek();

        newRoot.right = new Node();
        setRec(i, value, oldRoot.right, newRoot.right, a.height-1);

        newRoot.value = height;
        a.stack.push(newRoot);
        return a;   
    }

    public void setRec(int i, int value, Node currentOld, Node currentNew, int height) {
        Node oldLeft = null;
        Node oldRight = null;
        if (height <= 0) {
            currentNew.value = value;
            return;
        } else {
            if (currentOld != null) {
                oldLeft = currentOld.left;
                oldRight = currentOld.right;
            }
            if ((i & (1 << (height-1))) == 0) {
                currentNew.left = new Node();
                if (currentOld != null) currentNew.right = oldRight;
                setRec(i, value, oldLeft, currentNew.left, height-1);   
            } else {
                currentNew.right = new Node();
                if (currentOld != null) currentNew.left = oldLeft;
                setRec(i, value, oldRight, currentNew.right, height-1);
            }
        }
    }

    public int get(PersistentArray a, int i) { 
        Node root = a.stack.peek();
        if (root == null) return 0;
        Node current = root.right;
        if (current == null) return 0;
        for (int height = a.height-1; height > 0; height--) {
            if ((i & (1 << (height-1))) == 0) {
                current = current.left;
            } else {
                current = current.right;
            }
            if (current == null) return 0;
        }
        return current.value;
    }
}