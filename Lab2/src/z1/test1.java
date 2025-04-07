package z1;

import java.util.Arrays;

public class test1 {
    public static void main(String[] args) {
        Integer[] tab1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer[] tab2 = {1};
        Integer[] tab3 = {};
        String[] tab4 = {"abc", "de", "fg"};

        Table<Integer> table1 = new Table<>(tab1);
        Table<Integer> table2 = new Table<>(tab2);
        Table<Integer> table3 = new Table<>(tab3);
        Table<String> table4 = new Table<>(tab4);

        System.out.print(Arrays.asList(tab1) + " -> ");
        for (Integer i : table1) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.print(Arrays.asList(tab2) + " -> ");
        for (Integer i : table2) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.print(Arrays.asList(tab3) + " -> ");
        for (Integer i : table3) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.print(Arrays.asList(tab4) + " -> ");
        for (String i : table4) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
