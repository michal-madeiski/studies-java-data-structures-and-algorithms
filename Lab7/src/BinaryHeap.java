import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryHeap<T> implements IBinaryHeap<T> {
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

        public void setValue(T value) {
            this.value = value;
        }
        public T getValue() {
            return value;
        }
        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }
        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }
        public Node getLeftNode() {
            return leftNode;
        }
        public Node getRightNode() {
            return rightNode;
        }

        @Override
        public String toString() {
            return "" + value;
        }
    }

    private Node root;
    private Comparator<T> comparator;
    private int size;

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.root = null;
        this.size = 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public void add(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null!");
        }
        if (size == 0) {
            root = new Node(element);
            size++;
        } else {
            size += 1;
            int[] binValueTab = binValue(size);
            root = insert(root, element, binValueTab, 1);
        }
    }
    private Node insert (Node node, T element, int[] binValueTab, int count) {
        if (node == null) {
            node = new Node(element);
        }
        else {
            if (comparator.compare(element, node.value) > 0) {
                T temp = element;
                element = node.value;
                node.setValue(temp);
            }
            int direction = binValueTab[count];
            if (direction == 0) {
                if (node.leftNode != null && comparator.compare(element, node.leftNode.value) == 0) {
                    throw new IllegalArgumentException("Element cannot be added to the heap!");
                }
                int newCount = count + 1;
                node.leftNode = insert(node.leftNode, element, binValueTab, newCount);
            }
            if (direction == 1) {
                if (node.rightNode != null && comparator.compare(element, node.rightNode.value) == 0) {
                    throw new IllegalArgumentException("Element cannot be added to the heap!");
                }
                int newCount = count + 1;
                node.rightNode = insert(node.rightNode, element, binValueTab, newCount);
            }
        }
        return node;
    }

    @Override
    public T maximum() {
        if (root != null) {
            T returnVal = root.value;
            T lastElement = detachLastElement(root, binValue(size), 1);
            root.setValue(lastElement);
            repairHeap(root);
            size--;
            return returnVal;
        } else {
            return null;
        }
    }
    private T detachLastElement(Node node, int[] binValueTab, int count) {
        T returnVal = node.value;

        if (count == binValueTab.length - 1) {
            if (node.leftNode != null && binValueTab[count] == 0) {
                returnVal = node.leftNode.value;
                node.setLeftNode(null);
            }
            if (node.rightNode != null && binValueTab[count] == 1) {
                returnVal = node.rightNode.value;
                node.setRightNode(null);
            }
        }

        if (binValueTab[count] == 0) {
            if (node.leftNode != null) {
                int newCount = count + 1;
                returnVal = detachLastElement(node.leftNode, binValueTab, newCount);
            }
        }
        if (binValueTab[count] == 1) {
            if (node.rightNode != null) {
                int newCount = count + 1;
                returnVal = detachLastElement(node.rightNode, binValueTab, newCount);
            }
        }

        return returnVal;
    }
    private void repairHeap(Node node) {
        if (node.rightNode != null && node.leftNode != null && comparator.compare(node.rightNode.value ,node.leftNode.value) > 0) {
            if (comparator.compare(node.value, node.rightNode.value) < 0) {
                T temp = node.value;
                node.value = node.rightNode.value;
                node.rightNode.setValue(temp);
                repairHeap(node.rightNode);
            }
        }

        if (node.leftNode != null && node.rightNode != null && comparator.compare(node.rightNode.value ,node.leftNode.value) < 0) {
            if (comparator.compare(node.value, node.leftNode.value) < 0) {
                T temp = node.value;
                node.value = node.leftNode.value;
                node.leftNode.setValue(temp);
                repairHeap(node.leftNode);
            }
        }

        if (node.leftNode != null && node.rightNode == null) {
            if (comparator.compare(node.value, node.leftNode.value) < 0) {
                T temp = node.value;
                node.value = node.leftNode.value;
                node.leftNode.setValue(temp);
                repairHeap(node.leftNode);
            }
        }
    }

    private int[] binValue(int n) {
        ArrayList<Integer> tempList = new ArrayList<>();
        while (n > 0) {
            tempList.add(n % 2);
            n /= 2;
        }
        int size = tempList.size();
        int[] tab = new int[size];
        for (int i = 0; i < size; i++) {
            tab[i] = tempList.get(size - i - 1);
        }
        return tab;
    }

    public void printHeapByLevels() {
        if (root == null) {
            System.out.print("heapByLevels: " + "\n");
        }
        else {
            System.out.print("heapByLevels: " + "\n");

            Queue<Node> queue = new LinkedList<Node>();
            queue.add(root);

            int level = 0;
            int elementsInLevelCurrent = 0;

            while (!queue.isEmpty()) {
                Node tempNode = queue.poll();
                System.out.print(tempNode + " ");

                elementsInLevelCurrent++;
                if (elementsInLevelCurrent == Math.pow(2, level)) {
                    level++;
                    elementsInLevelCurrent = 0;
                    System.out.println();
                }

                if (tempNode != null) {
                    queue.add(tempNode.leftNode);
                }

                if (tempNode != null) {
                    queue.add(tempNode.rightNode);
                }
            }
            System.out.print("\n");
        }
    }
    public void addTabToBinHeap(T [] tab) {
        for (int i = 0; i < tab.length; i++) {
            add(tab[i]);
        }
    }

    //Mod7
    public void widePrint() {
        System.out.print("widePrint: ");

        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);

        while (!queue.isEmpty()) {
            if (root == null) {
                return;
            }
            Node tempNode = queue.poll();
            System.out.print(tempNode + "   ");

            if (tempNode.leftNode != null) {
                queue.add(tempNode.leftNode);
            }

            if (tempNode.rightNode != null) {
                queue.add(tempNode.rightNode);
            }
        }
        System.out.print("\n");
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        BinaryHeap<Integer> binHeap = new BinaryHeap<Integer>(new Comparator<Integer>(){public int compare(Integer int1, Integer int2){return int1 - int2;}});

        Integer[] tab1 = {20, 15, 17, 6, 5, 4, 3, 2, 1};
        printTabToAdd(tab1);
        binHeap.addTabToBinHeap(tab1);
        binHeap.printHeapByLevels();
        System.out.println();
        binHeap.widePrint();
        System.out.println();

        Integer[] tab2 = {12};
        printTabToAdd(tab2);
        binHeap.addTabToBinHeap(tab2);
        binHeap.printHeapByLevels();
        System.out.println();
        binHeap.widePrint();
        System.out.println();

        Integer[] tab3 = {1000};
        printTabToAdd(tab3);
        binHeap.addTabToBinHeap(tab3);
        binHeap.printHeapByLevels();
        System.out.println();
        binHeap.widePrint();
        System.out.println();

        System.out.println("max: " + binHeap.maximum() + "\n");
        binHeap.printHeapByLevels();
        System.out.println();

        Integer[] tab4 = {18};
        printTabToAdd(tab4);
        binHeap.addTabToBinHeap(tab4);
        binHeap.printHeapByLevels();
        System.out.println();
        binHeap.widePrint();
        System.out.println();

        System.out.println("*clear*");
        binHeap.clear();
        binHeap.printHeapByLevels();
        binHeap.widePrint();
        System.out.println();

        System.out.println();
        Integer[] tab5 = {20, 17, 15, 8, 6};
        printTabToAdd(tab5);
        binHeap.addTabToBinHeap(tab5);
        binHeap.printHeapByLevels();
        System.out.println();
        binHeap.widePrint();
        System.out.println();
        System.out.println("max: " + binHeap.maximum() + "\n");
        binHeap.printHeapByLevels();
        System.out.println();


    }

    public static void printTabToAdd(Integer[] tab) {
        System.out.print("add: ");
        for (int i = 0; i < tab.length; i++) {
            System.out.print(tab[i] + " ");
        }
        System.out.print("\n");
    }
}
