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
        return a;
    }

    public Array set(Array a, int i, int value) {
        Node oldRoot = a.stack.peek();
        Node newRoot = new Node();
        Node start;

        int height = height(oldRoot.right);

        //System.out.println(height);
        //System.out.println(i + " " + (1 << height-1));
        if (height == 0 && i == 0) {
            newRoot.right = new Node(value);
            a.stack.push(newRoot);
            return a;
        } else if (i >= (1 << height-1)) {
            //System.out.println("väx");
            newRoot.right = new Node();
            Node temp = newRoot.right;
            if (oldRoot.right != null) {
                while (i >= (1 << height-1)) {
                    temp.left = new Node();
                    temp = temp.left;
                    height++;
                }
                temp.left = oldRoot.right;
            }
            setRec(i, value, null, newRoot.right, height-1);
        } else {
            newRoot.right = new Node();
            setRec(i, value, oldRoot.right, newRoot.right, height-1);
        }

        a.stack.push(newRoot);
        return a;   
    }

    public void setRec(int i, int value, Node currentOld, Node currentNew, int height) {
        if (height == 0) {
            //System.out.println("klar");
            currentNew.value = value;
            return;
        } else if (currentOld == null) {
            //System.out.println("null");
            if ((i & (1 << (height-1))) == 0) {
                currentNew.left = new Node();
                setRec(i, value, null, currentNew.left, height-1);   
            } else {
                currentNew.right = new Node();
                setRec(i, value, null, currentNew.right, height-1);
            }
        } else {
            //System.out.println("rekursion");
            if ((i & (1 << (height-1))) == 0) {
                System.out.println("l");
                currentNew.left = new Node();
                currentNew.right = currentOld.right;
                setRec(i, value, currentOld.left, currentNew.left, height-1);   
            } else {
                System.out.println("r");
                currentNew.right = new Node();
                currentNew.left = currentOld.left;
                setRec(i, value, currentOld.right, currentNew.right, height-1);
            }
        }
    }

    public int height(Node n) {
        if (n == null) return 0;
        return 1 + Math.max(height(n.left), height(n.right));
    }

    public int get(Array a, int i) {
        Node current = a.stack.peek();
        if (current == null) return 0;
        int height = height(current.right);

        for (int h = height; h > 0; h--) {
            if ((i & (1 << h-1)) == 0) {
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