import java.util.Stack;

public class PersistentArray {
   
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

    public PersistentArray newarray() {
        PersistentArray a = new PersistentArray();
        Node root = new Node();
        stack.push(root);
        return a;
    }

    public PersistentArray set(PersistentArray a, int i, int value) {
        Node oldRoot = a.stack.peek();
        Node newRoot = new Node();

        int height = oldRoot.value;

        if (height == 0 && i == 0) {
            System.out.println("basecase");
            newRoot.right = new Node(value);
            newRoot.value = 1;
            a.stack.push(newRoot);
            return a;
        } else if (i >= (1 << (height-1))) {
            Node temp = newRoot.right = new Node();
            while (i >= (1 << (height-1))) {
                System.out.println("extend left");
                if (oldRoot.right != null) {
                    temp.left = new Node();
                    temp = temp.left;
                }
                height++;
            }
            temp.left = oldRoot.right;
            setRec(i, value, null, newRoot.right, height-1);
        } else {
            newRoot.right = new Node();
            setRec(i, value, oldRoot.right, newRoot.right, height-1);
        }
        newRoot.value = height;
        a.stack.push(newRoot);
        return a;   
    }

    public void setRec(int i, int value, Node currentOld, Node currentNew, int height) {
        if (height == 0) {
            System.out.println("done");
            currentNew.value = value;
            return;
        } else if (currentOld != null) {
                if ((i & (1 << (height-1))) == 0) {
                System.out.println("left");
                currentNew.left = new Node();
                currentNew.right = currentOld.right;
                setRec(i, value, currentOld.left, currentNew.left, height-1);   
            } else {
                System.out.println("right");
                currentNew.right = new Node();
                currentNew.left = currentOld.left;
                setRec(i, value, currentOld.right, currentNew.right, height-1);
            }
        } else {
            if ((i & (1 << (height-1))) == 0) {
                System.out.println("left");
                currentNew.left = new Node();
                setRec(i, value, null, currentNew.left, height-1);   
            } else {
                System.out.println("right");
                currentNew.right = new Node();
                setRec(i, value, null, currentNew.right, height-1);
            }
        }
    }

    public int get(PersistentArray a, int i) {
        if (a.stack.isEmpty()) return 0;
        Node root = a.stack.peek();
        if (root == null) return 0;
        Node current = root.right;
        if (current == null) return 0;
        System.out.println("get.........");
        for (int height = root.value-1; height > 0; height--) {
            if ((i & (1 << (height-1))) == 0) {
                System.out.println("left");
                current = current.left;
            } else {
                System.out.println("right");
                current = current.right;
            }
            if (current == null) return 0;
        }
        return current.value;
    }


}