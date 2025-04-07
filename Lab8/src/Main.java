import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        System.out.println("BST:");
        BST<Integer> bst = new BST<Integer>(new Comparator<Integer>(){public int compare(Integer int1, Integer int2){return int1 - int2;}});
        IntegerToStringExecutor executor = new IntegerToStringExecutor();
        Integer[] tab = {10, 5, 12, 11, 6, 20, 15, 16, 4};
        addTabToBst(bst, tab);
        printAddedTab(tab);
        System.out.println();
        System.out.print("by levels: "); bst.printByLevels();
        System.out.println();
        System.out.print("in-order walk: "); bst.inOrderWalk(executor); System.out.println(executor.getResult());
        System.out.println("max val: " + bst.getMax());
        System.out.println("min val: " + bst.getMin());
        int val1 = 11; int val2 = 100; int val3 = 11;
        System.out.println("find val=" + val1 + ": " + bst.find(val1));
        System.out.println("find val=" + val2 + ": " + bst.find(val2));
        System.out.println("successor of val=" + val3 + ": " + bst.successor(val3));
        int delVal = 12;
        bst.delete(delVal);
        System.out.println("delete(" + delVal + "); ");
        System.out.print("by levels: "); bst.printByLevels();
        System.out.println();
        int delRoot = 10;
        bst.delete(delRoot);
        System.out.println("delete(" + delRoot + "); ");
        System.out.print("by levels: "); bst.printByLevels();
        System.out.println();
        int addVal = 7;
        System.out.println("add(" + addVal + "); ");
        bst.insert(addVal);
        System.out.print("by levels: "); bst.printByLevels();
        System.out.println();
        System.out.print("in-order walk: "); bst.inOrderWalk(executor); System.out.println(executor.getResult());
        System.out.println();

        //Mod8
        System.out.println("//Mod8");
        System.out.println("BST2:");
        BST<Integer> bst2 = new BST<Integer>(new Comparator<Integer>(){public int compare(Integer int1, Integer int2){return int1 - int2;}});
        IntegerToStringExecutor executor2 = new IntegerToStringExecutor();
        Integer[] tab2 = {10, 7, 6, 8, 9, 20, 12, 25, 27};
        addTabToBst(bst2, tab2);
        printAddedTab(tab2);
        System.out.println();
        System.out.print("by levels: "); bst2.printByLevels();
        System.out.println();
        System.out.println("tree height: " + bst2.getHeight(10));
        System.out.println("tree balance: " + bst2.getBalance(10));
        BST<Integer> inBalancedSubtree = bst2.mostInbalancedSubtree();
        System.out.print("most inbalanced subtree: "); inBalancedSubtree.printByLevels();
    }

    public static <T> void addTabToBst(BST<T> bst, T[] tab) {
        for (T t : tab) {
            bst.insert(t);
        }
    }
    public static <T> void printAddedTab(T[] tab) {
        for (T i : tab) {
            System.out.print("add(" + i + "); ");
        }
    }

}