public class ZigZag {
    public static void drawZigZagFromLeft(int n, int l) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < l; k++) {
                System.out.print("X");
            }
            System.out.println();
        }
    }
    public static void drawZigZagFromRight(int n, int l) {
        int temp = l;
        for (int i = 0; i < n; i++) {
            for (int j = temp - 1; j > 0; j--) {
                System.out.print(" ");
            }
            for (int k = 0; k < l; k++) {
                System.out.print("X");
            }
            System.out.println();
            temp -= 1;
        }
    }
    public static void chooseZigZag(int n, int l, int x) {
        if (x % 2 != 0) {
            drawZigZagFromLeft(n, l);
        } else drawZigZagFromRight(n, l);
    }
    public static void drawZigZag(int n, int l){
        int range = n/(l-1);
        if (n % (l-1) != 0) range += 1;
        int temp = 1;
        int line = 0;
        while (temp <= range) {
            if (temp != range) {
                chooseZigZag(l - 1, l, temp);
                temp += 1;
                line += l - 1;
            } else {
                chooseZigZag(n - line, l, temp);
                temp += 1;
            }
        }
    }

    public static void main(String[] args) {
        int l = 7;
        //l=7
        System.out.println("l=7");
        System.out.println("-------------------------------------------------------");
        //n=0
        System.out.println("n=0");
        drawZigZag(0, l);
        System.out.println("-------------------------------------------------------");
        //n=4
        System.out.println("n=4");
        drawZigZag(4, l);
        System.out.println("-------------------------------------------------------");
        //n=9
        System.out.println("n=9");
        drawZigZag(9, l);
        System.out.println("-------------------------------------------------------");
        //n=22
        System.out.println("n=22");
        drawZigZag(22, l);
        System.out.println("-------------------------------------------------------");
    }
}