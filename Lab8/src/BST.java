import java.util.*;

public class BST<T> {
    private class Node {
        T value;
        Node leftNode;
        Node rightNode;

        public Node(T value) {
            this.value = value;
        }

        public Node(T value, Node leftNode, Node rightNode) {
            this.value = value;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        public T getValue() {
            return value;
        }
        public void setValue(T value) {
            this.value = value;
        }
        public Node getLeftNode() {
            return leftNode;
        }
        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }
        public Node getRightNode() {
            return rightNode;
        }
        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }
        @Override
        public String toString() {
            return "" + value;
        }
    }

    private Comparator<T> comparator;
    private Node root;

    public BST(Comparator<T> comparator) {
        this.comparator = comparator;
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }
    public void setRoot(Node root) {
        this.root = root;
    }
    public Comparator<T> getComparator() {
        return comparator;
    }
    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void printByLevels() {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node tempNode = queue.poll();
            System.out.print(tempNode.value + "; ");

            if (tempNode.leftNode != null) {
                queue.add(tempNode.leftNode);
            }

            if (tempNode.rightNode != null) {
                queue.add(tempNode.rightNode);
            }
        }
    }

    public void insert(T value) {
        if (root == null) {
            root = new Node(value);
            return;
        }
        Node currentNode = root;
        int flag = 1;
        while (currentNode != null && flag != 0) {
            if (comparator.compare(currentNode.getValue(), value) == 0) {
                throw new IllegalArgumentException("Value:" + value.toString() + " is already in the tree!");
            }
            if (comparator.compare(currentNode.getValue(), value) > 0) {
                if (currentNode.getLeftNode() == null) {
                    currentNode.setLeftNode(new Node(value));
                    flag = 0;
                } else {
                    currentNode = currentNode.getLeftNode();
                }
            }
            if (comparator.compare(currentNode.getValue(), value) < 0) {
                if (currentNode.getRightNode() == null) {
                    currentNode.setRightNode(new Node(value));
                    flag = 0;
                } else {
                    currentNode = currentNode.getRightNode();
                }
            }
        }
    }

    public T getMax() {
        return max(root).getValue();
    }
    private Node max(Node node) {
        Node n;
        if (node.getRightNode() == null) {
            n =  node;
        } else {
            n = max(node.getRightNode());
        }
        return n;
    }

    public T getMin() {
        return min(root).getValue();
    }
    private Node min(Node node) {
        Node n;
        if (node.getLeftNode() == null) {
            n = node;
        } else {
            n = min(node.getLeftNode());
        }
        return n;
    }

    public boolean find(T value) {
        Node n = findNodeFromValue(root, value);
        if (n == null) {
            return false;
        } else {
            if (comparator.compare(n.getValue(), value) == 0) {
                return true;
            } else {
                return false;
            }
        }
    }
    private Node findNodeFromValue(Node node, T value) {
        Node n = null;
        if (node != null && comparator.compare(value, node.getValue()) > 0) {
            n = findNodeFromValue(node.getRightNode(), value);
        }
        if (node != null && comparator.compare(value, node.getValue()) < 0) {
            n = findNodeFromValue(node.getLeftNode(), value);
        }
        if (node != null && comparator.compare(value, node.getValue()) == 0) {
            n = node;
        }
        return n;
    }

    public Node successor(T value) {
        Node retNode = null;
        Node node = findNodeFromValue(root, value);
        if (node == null) {
            throw new IllegalArgumentException("Node cannot be null!");
        }
        if (value == getMax()) {
            retNode = null;
        }
        else if (node.getRightNode() != null) {
            retNode = min(node.getRightNode());
        }
        else {
            Stack<Node> stack = createStack(node);
            stack.pop();
            while (!stack.isEmpty()) {
                Node tempNode = stack.pop();
                if (tempNode.getLeftNode() != null) {
                    retNode = tempNode;
                    break;
                }
            }
        }
        return retNode;
    }
    private Stack<Node> createStack(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node current = root;
        while (comparator.compare(current.getValue(), node.getValue()) != 0) {
            if (comparator.compare(node.getValue(), current.getValue()) < 0) {
                current = current.getLeftNode();
                stack.push(current);
            }
            if (comparator.compare(node.getValue(), current.getValue()) > 0) {
                current = current.getRightNode();
                stack.push(current);
            }
        }
        return stack;
    }

    public void delete (T value) {
        Node node = findNodeFromValue(root, value);
        if (node == null) {
            throw new NoSuchElementException("Value: " + value + "is not in tree!");
        }
        Stack<Node> stack = createStack(node);
        if (stack.size() == 1) {
            deleteRoot();
            return;
        }
        Node child = stack.pop();
        Node parent = stack.pop();
        if (child.getLeftNode() == null && child.getRightNode() == null) {
            detachChild(parent, child, null);
        }
        if (child.getLeftNode() != null && child.getRightNode() == null) {
            detachChild(parent, child, child.getLeftNode());
            child.setLeftNode(null);
        }
        if (child.getLeftNode() == null && child.getRightNode() != null) {
            detachChild(parent, child, child.getRightNode());
            child.setRightNode(null);
        }
        if (child.getLeftNode() != null && child.getRightNode() != null) {
            Node nextNode = successor(node.getValue());
            T nextValue = nextNode.getValue();
            delete(nextValue);
            T tempValue = node.getValue();
            node.setValue(nextValue);
            nextNode.setValue(tempValue);
        }
    }
    private void detachChild(Node parent, Node child, Node newChild) {
        if (parent.getLeftNode() != null && comparator.compare(parent.getLeftNode().getValue(), child.getValue()) == 0) {
            parent.setLeftNode(newChild);
        }
        if (parent.getRightNode() != null && comparator.compare(parent.getRightNode().getValue(), child.getValue()) == 0) {
            parent.setRightNode(newChild);
        }
    }
    private void deleteRoot() {
        if (root.getLeftNode() == null && root.getRightNode() == null) {
            root = null;
            return;
        }
        if (root.getLeftNode() != null && root.getRightNode() == null) {
            root = root.getLeftNode();
        }
        if (root.getLeftNode() == null && root.getRightNode() != null) {
            root = root.getRightNode();
        }
        if (root.getLeftNode() != null && root.getRightNode() != null) {
            Node nextNode = successor(root.getValue());
            T nextValue = nextNode.getValue();
            delete(nextValue);
            root.setValue(nextValue);
        }
    }

    public<R> void inOrderWalk(IExecutor<T, R> exec) {
        walk(root, exec);
    }
    private<R> void walk(Node node, IExecutor<T, R> exec) {
        if(node.getLeftNode() != null) {
            walk(node.getLeftNode(), exec);
        }
        exec.execute(node.getValue());
        if(node.getRightNode() != null) {
            walk(node.getRightNode(), exec);
        }
    }

    //Mod8
    public BST<T> mostInbalancedSubtree() {
        BST<T> retBst = new BST<>(comparator);
        int maxBalance = 0;
        int idx = 0;
        ArrayList<T> list = new ArrayList<>();
        walkByLevelsAndAddToArray(root, list);
        for (int i = 0; i < list.size(); i++) {
            Node node = findNodeFromValue(root, list.get(i));
            if (maxBalance < balance(node)) {
                maxBalance = balance(node);
                idx = i;
            }
        }
        addByLevels(retBst, findNodeFromValue(root, list.get(idx)));
        return retBst;
    }
    private void addByLevels(BST<T> bst, Node startNode) {
        if (startNode == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<Node>();
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node tempNode = queue.poll();
            bst.insert(tempNode.getValue());

            if (tempNode.leftNode != null) {
                queue.add(tempNode.leftNode);
            }

            if (tempNode.rightNode != null) {
                queue.add(tempNode.rightNode);
            }
        }
    }
    private void walkByLevelsAndAddToArray(Node startNode, ArrayList<T> list) {
        if (startNode == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<Node>();
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node tempNode = queue.poll();
            list.add(tempNode.getValue());

            if (tempNode.leftNode != null) {
                queue.add(tempNode.leftNode);
            }

            if (tempNode.rightNode != null) {
                queue.add(tempNode.rightNode);
            }
        }
    }

    public int getBalance(T element) {
        return balance(findNodeFromValue(root, element));
    }
    private int balance(Node node) {
        int leftH = 0;
        int rightH = 0;
        if (node.getLeftNode() != null) {
            leftH = height(node.getLeftNode());
        }
        if (node.getRightNode() != null) {
            rightH = height(node.getRightNode());
        }
        return Math.abs(leftH - rightH);
    }

    public int getHeight(T element) {
        return height(findNodeFromValue(root, element));
    }
    private int height(Node startNode) {
        int leftH = 0;
        int rightH = 0;
        if (startNode.getLeftNode() != null) {
            leftH = height(startNode.getLeftNode()) + 1;
        } else {
            leftH = 1;
        }
        if (startNode.getRightNode() != null) {
            rightH = height(startNode.getRightNode()) + 1;
        } else {
            rightH = 1;
        }
        return Math.max(leftH, rightH);
    }

}


