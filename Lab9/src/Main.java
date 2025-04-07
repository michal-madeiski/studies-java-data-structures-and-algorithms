import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        int t = 2;
        System.out.println("BTree (t=" + t + "): ");
        BTree<Integer> bTree = new BTree<Integer>(new Comparator<Integer>(){public int compare(Integer int1, Integer int2){return int1 - int2;}}, t);
        bTree.add(20);
        bTree.add(10);
        bTree.add(30);
        bTree.add(40);
        bTree.add(5);
        bTree.add(50);
        bTree.add(60);
        bTree.add(70);
        bTree.add(80);
        bTree.add(90);
        bTree.add(35);
        bTree.add(45);
        bTree.add(15);
        bTree.add(55);
        bTree.add(33);
        bTree.add(37);
//        bTree.remove(45);
//        bTree.add(20);
        bTree.print();
        System.out.println();
        int val = 13;
        System.out.println("Check if " + val + " is in tree: " + bTree.search(13));
    }
}
