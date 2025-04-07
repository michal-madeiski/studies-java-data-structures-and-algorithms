package z2;

import z1.TableIterator;
import java.util.Arrays;

public class test2 {
    public static void main(String[] args) {
        Integer[] tab1 = {1, 2, 3, 4};
        Integer[] tab2 = {1};
        Integer[] tab3 = {};

        TableIterator<Integer> tableIter1 = new TableIterator<>(tab1);
        TableIterator<Integer> tableIter2 = new TableIterator<>(tab2);
        TableIterator<Integer> tableIter3 = new TableIterator<>(tab3);

        ShiftIterator<Integer> shiftIter1 = new ShiftIterator<>(tableIter1);
        ShiftIterator<Integer> shiftIter2 = new ShiftIterator<>(tableIter2);
        ShiftIterator<Integer> shiftIter3 = new ShiftIterator<>(tableIter3);

        System.out.print(Arrays.asList(tab1) + " -> ");
        while (shiftIter1.hasNext()) {
            System.out.print(shiftIter1.next() + " ");
        }
        System.out.println();

        System.out.print(Arrays.asList(tab2) + " -> ");
        while (shiftIter2.hasNext()) {
            System.out.print(shiftIter2.next() + " ");
        }
        System.out.println();

        System.out.print(Arrays.asList(tab3) + " -> ");
        while (shiftIter3.hasNext()) {
            System.out.print(shiftIter3.next() + " ");
        }
    }
}
