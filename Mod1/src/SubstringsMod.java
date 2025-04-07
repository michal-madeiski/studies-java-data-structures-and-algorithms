public class SubstringsMod {
    public static int growingSubstringSpan(int[] numbers) {
        if (numbers == null) return 0;
        if (numbers.length == 0 || numbers.length == 1) return 0;

        int maxLength = 0;
        int minLength = numbers.length;
        int counter = 0;


        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] < numbers[i + 1]) {
                counter++;
            } else {
                counter++;
                if (counter >= maxLength) maxLength = counter;
                if (counter <= minLength) minLength = counter;
                counter = 0;
            }
        }
        if (numbers[numbers.length - 2] < numbers[numbers.length - 1]) {
            counter++;
            if (counter >= maxLength) maxLength = counter;
            if (counter <= minLength) minLength = counter;
        } else {
            counter = 1;
            if (counter >= maxLength) maxLength = counter;
            if (counter <= minLength) minLength = counter;
        }

        return maxLength - minLength;
    }

    public static void main(String[] args) {
        int[] tab1 = {1, 2, 3, 0, 1, 0, 2, 3, 4, 1};
        int[] tab2 = {0};
        int[] tab3 = {1};
        int[] tab4 = {1, 2, 3, 4, 5};
        int[] tab5 = {5, 4, 3, 2, 1};
        System.out.println(growingSubstringSpan(tab1));
        System.out.println(growingSubstringSpan(tab2));
        System.out.println(growingSubstringSpan(tab3));
        System.out.println(growingSubstringSpan(tab4));
        System.out.println(growingSubstringSpan(tab5));
        tab5 = null;
        System.out.println(growingSubstringSpan(tab5));
    }
}