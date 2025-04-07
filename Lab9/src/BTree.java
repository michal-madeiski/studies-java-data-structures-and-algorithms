import java.util.*;

public class BTree<T> {
    private class Node<T> {
        private int t;
        private T[] keys;
        private Node<T>[] children;
        private int keysAmount;
        private int childrenAmount;
        private boolean isLeaf;
        private Node<T> extraChild;

        public Node(int t) {
            if (t < 2) {
                throw new IllegalArgumentException("Minimum value of t is 2!");
            }
            this.t = t;
            this.keys = (T[]) new Object[2*t - 1];
            this.children = new Node[2*t];
            this.keysAmount = 0;
            this.childrenAmount = 0;
            this.isLeaf = true;
            this.extraChild = null;
        }

        public int getT() {
            return t;
        }
        public void setT(int t) {
            this.t = t;
        }
        public T[] getKeys() {
            return keys;
        }
        public void setKeys(T[] keys) {
            this.keys = keys;
        }
        public Node[] getChildren() {
            return children;
        }
        public void setChildren(Node[] children) {
            this.children = children;
        }
        public int getKeysAmount() {
            return keysAmount;
        }
        public void setKeysAmount(int keysAmount) {
            this.keysAmount = keysAmount;
        }
        public int getChildrenAmount() {
            return childrenAmount;
        }
        public void setChildrenAmount(int childrenAmount) {
            this.childrenAmount = childrenAmount;
        }
        public boolean isLeaf() {
            return isLeaf;
        }
        public void setLeaf(boolean leaf) {
            isLeaf = leaf;
        }

        private void print() {
            for (int i = 0; i < keysAmount; i++) {
                System.out.printf(keys[i] + " ");
            }
        }

        private int search(T val, Comparator<T> comparator) {
            int start = 0;
            int end = keysAmount - 1;
            int mid;
            while (start <= end) {
                mid = (start + end) / 2;
                int comp = comparator.compare(keys[mid], val);
                if (comp == 0) {
                    return mid;
                }
                if (comp < 0) {
                    start = mid + 1;
                }
                if (comp > 0) {
                    end = mid - 1;
                }
            }
            return start;
        }

        private boolean isFull() {
            return keysAmount == 2*t - 1;
        }
        private boolean isMin() {return keysAmount == t - 1;}

        private void increaseKeysAmount() {
            setKeysAmount(getKeysAmount() + 1);
        }
        private void decreaseKeysAmount() {
            setKeysAmount(getKeysAmount() - 1);
        }
        private void increaseChildrenAmount() {
            setChildrenAmount(getChildrenAmount() + 1);
        }
        private void decreaseChildrenAmount() {
            setChildrenAmount(getChildrenAmount() - 1);
        }

        private void addKeyOnIdx(T val, int idx) {
            keys[idx] = val;
            increaseKeysAmount();
        }
        private void addKey(T val, Comparator<T> comparator) {
            int idx = search(val, comparator);
            moveElements(idx);
            increaseKeysAmount();
            keys[idx] = val;
        }
        private void moveElements(int start) {
            for (int i = keysAmount; i > start ; i--) {
                swap(i, i - 1);
            }
        }
        private void swap(int i, int j) {
            T temp = keys[i];
            keys[i] = keys[j];
            keys[j] = temp;
        }

        private void removeKey(int idx) {
            for (int i = idx; i < (2*t - 2); i++) {
                keys[i] = keys[i + 1];
            }
            keys[2*t - 2] = null;
            decreaseKeysAmount();
        }

        private void addChildren(int n, Node<T> node) {
            if (isLeaf()) {
                setLeaf(false);
            }
            increaseChildrenAmount();
            children[n] = node;
        }
        private void moveChildren(int start, Node<T> newChild) {
            if (childrenAmount < 2*t) {
                Node<T>[] tempTab = children;
                for (int i = 2*t - 1; i >= start + 1; i--) {
                    children[i] = tempTab[i - 1];
                }
                children[start] = newChild;
                increaseChildrenAmount();
            } else {
                extraChild = newChild;
            }

        }

        private boolean is_Leaf() {
            return childrenAmount <= 0;
        }
    }


    
    private int t;
    private Comparator<T> comparator;
    private Node<T> root;

    public BTree(Comparator<T> comparator, int t) {
        if (t < 2) {
            throw new IllegalArgumentException("Minimum value of t is 2!");
        }
        this.comparator = comparator;
        this.t = t;
        this.root = null;
    }

    public int getT() {
        return t;
    }
    public void setT(int t) {
        this.t = t;
    }
    public Comparator<T> getComparator() {
        return comparator;
    }
    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }
    public Node<T> getRoot() {
        return root;
    }
    public void setRoot(Node<T> root) {
        this.root = root;
    }

    public void print() {
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        boolean toStop = false;
        int x = 1;
        int j = 0;
        while(!queue.isEmpty()) {
            Node<T> actNode = queue.poll();
            if(actNode != null) {
                if (!actNode.isLeaf()) {
                    for (int i = 0; i < actNode.getChildren().length; i++) {
                        queue.add(actNode.getChildren()[i]);
                    }
                } else {
                    toStop = true;
                }
            } else {
                for (int i = 0; i < (2*t); i++){
                    queue.add(null);
                }
            } if(actNode != null) {
                actNode.print();
            }
            else {
                System.out.print(" empty ");
            }
            j++;
            if(j == x) {
                x = x*(2*t);
                j = 0;
                System.out.println();
                if(toStop) {
                    return;
                }
            } else {
                System.out.print("|| ");
            }
        }
    }

    public T search(T val) {
        if (root == null) {
            return null;
        }
        return search(root, val);
    }
    private T search(Node<T> node, T val) {
        if (node == null) {
            return null;
        }
        int idx = node.search(val, comparator);
        if (idx >= (2*t - 1)) return null;
        if (node.keysAmount != 1 && idx > node.keysAmount) return null;
        if (node.keysAmount == 1) {
            if (comparator.compare(node.keys[0], val) < 0) {
                return (T) search(node.getChildren()[1], val);
            }
            if (comparator.compare(node.keys[0], val) > 0) {
                return (T) search(node.getChildren()[0], val);
            }
            if (comparator.compare(node.keys[0], val) == 0) {
                return node.getKeys()[0];
            }
        }
        if (node.getKeys()[idx] != null && comparator.compare(node.getKeys()[idx], val) == 0) {
            return node.getKeys()[idx];
        } else {
            if (node.isLeaf()) {
                return null;
            } else {
                return (T) search(node.getChildren()[idx], val);
            }
        }
    }

    public void add(T val) {
        if (val == null) {
            throw new IllegalArgumentException("You cannot add null to the BTree!");
        }
        if (search(val) != null) {
            throw new IllegalArgumentException("Value is already in the BTree!");
        }
        if (root == null) {
            root = new Node<T>(t);
            root.addKeyOnIdx(val, 0);
        } else {
            add(root, val);
        }
    }
    private void add(Node<T> node, T val) {
        Stack<Node<T>> stack = new Stack<>();
        Node<T> actNode = node;
        while (actNode != null) {
            stack.push(actNode);
            int idx = actNode.search(val, comparator);
            actNode = actNode.getChildren()[idx];
        }

        Node<T> child = stack.pop();
        if (!child.isFull()) {
            child.addKey(val, comparator);
        }
        else {
            if (stack.isEmpty()) {
                splitRoot(child, val);
            }
            else {
                Node<T> parent = stack.pop();
                T valToAdd = val;
                while (child.isFull()) {
                    T newValToAdd = split(child, parent, valToAdd);
                    child = parent;
                    valToAdd = newValToAdd;
                    if (!stack.isEmpty()) {
                        parent = stack.pop();
                    } else {
                        if (child.isFull()) {
                            splitRoot(child, valToAdd);
                        } else {
                            child.addKey(valToAdd, comparator);
                        }

                        return;
                    }
                }
                child.addKey(valToAdd, comparator);
            }
        }
    }

    private T split(Node<T> child, Node<T> parent, T newVal) {
        int whichChild = 0;
        for (int i = 0; i < 2 * t; i++) {
            if (parent.children[i] != null && comparator.compare(parent.children[i].keys[0], child.keys[0]) == 0) {
                whichChild = i;
            }
        }
        int mid = (2 * t - 1) / 2;
        Node<T> newNode = new Node<T>(t);
        for (int i = t; i < 2 * t; i++) {
            newNode.children[i - t] = child.children[i];
            newNode.increaseChildrenAmount();
            child.children[i] = null;
            child.decreaseChildrenAmount();
        }
        if (child.extraChild != null) {
            int index = child.search(child.extraChild.keys[0], comparator);
            if (index == (2*t - 1)) index--;
            if (comparator.compare(child.extraChild.keys[0], child.keys[index]) > 0) {
                if (index == (2*t - 2)) {
                    newNode.addChildren(newNode.childrenAmount - 1, child.extraChild);
                } else {
                    newNode.moveChildren(index + 1, child.extraChild);
                    child.extraChild = null;
                }
            }
            else if (comparator.compare(child.extraChild.keys[child.extraChild.keysAmount - 1], child.keys[index]) < 0) {
                if (index == (2*t - 2)) {
                    child.addChildren(child.childrenAmount - 1, child.extraChild);
                } else {
                    child.moveChildren(index + 1, child.extraChild);
                    child.extraChild = null;
                }
            }
        }
        for (int i = mid + 1; i < child.keys.length; i++) {
            newNode.addKeyOnIdx(child.keys[i], i - (mid + 1));
            child.keys[i] = null;
            child.decreaseKeysAmount();
        }
        T midVal = child.keys[mid];
        child.keys[mid] = null;
        child.decreaseKeysAmount();
        if (comparator.compare(newVal, midVal) < 0) {
            child.addKey(newVal, comparator);
        } else {
            newNode.addKey(newVal, comparator);
        }
        parent.moveChildren(whichChild + 1, newNode);

        return midVal;
    }

    private void splitRoot(Node<T> child, T newVal) {
        int mid = (2 * t - 1) / 2;
        Node<T> newNode = new Node<T>(t);
        for (int i = t; i < 2 * t; i++) {
            newNode.children[i - t] = child.children[i];
            newNode.increaseChildrenAmount();
            child.children[i] = null;
            child.decreaseChildrenAmount();
        }
        if (child.extraChild != null) {
            int index = child.search(child.extraChild.keys[0], comparator);
            if (index == (2*t - 1)) index--;
            if (comparator.compare(child.extraChild.keys[0], child.keys[index]) > 0) {
                if (index == (2*t - 2)) {
                    newNode.addChildren(newNode.childrenAmount - 1, child.extraChild);
                } else {
                    newNode.moveChildren(index + 1, child.extraChild);
                    child.extraChild = null;
                }
            }
            else if (comparator.compare(child.extraChild.keys[child.extraChild.keysAmount - 1], child.keys[index]) < 0) {
                if (index == (2*t - 2)) {
                    child.addChildren(child.childrenAmount - 1, child.extraChild);
                } else {
                    child.moveChildren(index + 1, child.extraChild);
                    child.extraChild = null;
                }
            }
        }
        for (int i = mid + 1; i < child.keys.length; i++) {
            newNode.addKeyOnIdx(child.keys[i], i - (mid + 1));
            child.keys[i] = null;
            child.decreaseKeysAmount();
        }
        T midVal = child.keys[mid];
        child.keys[mid] = null;
        child.decreaseKeysAmount();
        if (comparator.compare(newVal, midVal) < 0) {
            child.addKey(newVal, comparator);
        } else {
            newNode.addKey(newVal, comparator);
        }
        child.setLeaf(false);
        newNode.setLeaf(false);
        Node<T> tempNode = child;
        root = new Node<T>(t);
        root.addKeyOnIdx(midVal, 0);
        root.addChildren(0, tempNode);
        root.addChildren(1, newNode);
    }

    public void remove(T val) {
        if (root == null) {
            return;
        }
        if (search(val) == null) {
            return;
        }
        remove(root, val);
    }
    private void remove(Node<T> node, T val) {
        Stack<Node<T>> stack = new Stack<>();
        Node<T> actNode = node;
        while (actNode != null) {
            stack.push(actNode);
            int idx = actNode.search(val, comparator);
            actNode = actNode.getChildren()[idx];
        }

        Node<T> child = stack.pop();
        if (stack.isEmpty() && child.isLeaf) {
            child.removeKey(child.search(val, comparator));
        }
        if (child.is_Leaf() || child.isLeaf) {
            if (!child.isMin()) {
                int idx = child.search(val, comparator);
                if (idx >= child.getKeysAmount()) return;
                if (child.getKeys()[idx] != null && comparator.compare(child.getKeys()[idx], val) == 0) {
                    child.removeKey(idx);
                }
            } else {
                Node<T> parent = stack.pop();
                int whichChild = 0;
                for (int i = 0; i < 2 * t; i++) {
                    if (parent.children[i] != null && comparator.compare(parent.children[i].keys[0], child.keys[0]) == 0) {
                        whichChild = i;
                    }
                }
                if (whichChild == 0) {
                    Node<T> rightBro = parent.children[1];
                    if (!rightBro.isMin()) {
                        T moveToParentVal = rightBro.keys[0];
                        T temp = parent.keys[whichChild];
                        parent.keys[whichChild] = moveToParentVal;
                        child.removeKey(0);
                        child.addKey(temp, comparator);
                    }
                }
                if (whichChild == (2*t - 1)) {
                    Node<T> leftBro = parent.children[2*t - 2];
                    if (!leftBro.isMin()) {
                        T moveToParentVal = leftBro.keys[leftBro.keysAmount - 1];
                        T temp = parent.keys[whichChild];
                        parent.keys[whichChild] = moveToParentVal;
                        child.removeKey(0);
                        child.addKey(temp, comparator);
                    }
                }
                else {
                    Node<T> leftBro = parent.children[whichChild - 1];
                    if (!leftBro.isMin()) {
                        T moveToParentVal = leftBro.keys[leftBro.keysAmount - 1];
                        T temp = parent.keys[whichChild];
                        parent.keys[whichChild] = moveToParentVal;
                        child.removeKey(0);
                        child.addKey(temp, comparator);
                    } else {
                        Node<T> rightBro = parent.children[whichChild + 1];
                        if (!rightBro.isMin()) {
                            T moveToParentVal = rightBro.keys[0];
                            T temp = parent.keys[whichChild];
                            parent.keys[whichChild] = moveToParentVal;
                            child.removeKey(0);
                            child.addKey(temp, comparator);
                        }
                    }
                }
            }
        }
    }

}
