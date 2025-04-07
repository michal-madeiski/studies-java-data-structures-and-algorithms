public class Substrings {
    public static int[][] longestGrowingSubstrings(int[] numbers) {
        int[] temp = new int[numbers.length];
        int howManySubstrings = 0;
        int counter = 0;
        int[][] tempReturnTab = new int[numbers.length][numbers.length];

        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] < numbers[i + 1]) {
                temp[counter] = numbers[i];
                counter += 1;
            } else {
                temp[counter] = numbers[i];
                counter += 1;
                tempReturnTab[howManySubstrings] = new int[counter];
                for (int j = 0; j < counter; j++) {
                    tempReturnTab[howManySubstrings][j] = temp[j];
                }
                howManySubstrings += 1;
                counter = 0;
                for (int k = 0; k < temp.length; k++) {
                    temp[k] = 0;
                }
            }
        }
        if (numbers[numbers.length - 2] < numbers[numbers.length - 1]) {
            temp[counter] = numbers[numbers.length - 1];
            counter += 1;
            tempReturnTab[howManySubstrings] = new int[counter];
            for (int j = 0; j < counter; j++) {
                tempReturnTab[howManySubstrings][j] = temp[j];
            }
            howManySubstrings += 1;
        } else {
            tempReturnTab[howManySubstrings] = new int[1];
            for (int i = 0; i < temp.length; i++) {
                temp[i] = 0;
            }
            temp[0] = numbers[numbers.length - 1];
            tempReturnTab[howManySubstrings][0] = temp[0];
            howManySubstrings += 1;
        }

        int[][] returnTab = new int[howManySubstrings][];
        for (int i = 0; i < howManySubstrings; i++) {
            returnTab[i] = tempReturnTab[i];
        }
        return returnTab;
    }
    public static void printTab(int[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print(tab[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] numbersTab = {2, 1, 2, 3, 3, -2, 0, 6, 6, 6, 7, 8, 9, 1, -1, 0};
        printTab(longestGrowingSubstrings(numbersTab));
    }
}
