public class Brigde {
    public static void drawX(int n) {
        int howManyX = 2*n - 1;
        for (int i = 0; i < howManyX; i++) {
            System.out.print("X");
        }
        System.out.println();
    }
    public static void drawBase(int n) {
        int temp = 2*n - 1;
        System.out.print("I");
        for (int i = 0; i < temp - 2; i++) {
            System.out.print(" ");
        }
        System.out.print("I");
        System.out.println();
    }
    public static void drawPart(int n, int x) {
        int temp = 2*n - 1;
        System.out.print("I");
        for (int i = 0; i < x - 1; i++) {
            System.out.print(" ");
        }
        System.out.print("\\");
        for (int i = 0; i < temp - 2 - 2*x; i++) {
            System.out.print(" ");
        }
        System.out.print("/");
        for (int i = 0; i < x - 1; i++) {
            System.out.print(" ");
        }
        System.out.print("I");
        System.out.println();
    }
    public static void drawBridge(int n) {
        if (n <= 0) System.out.println(" ");
        if (n == 1) {
            drawX(n);
        } else if (n == 2) {
            drawX(2);
            drawBase(2);
        } else if (n > 2) {
            drawBase(n);
            for (int i = 1; i < n - 2; i++) {
                drawPart(n, i);
            }
            drawX(n);
            drawBase(n);
        }
    }

    public static void main(String[] args) {
        //n=1
        System.out.println("n=1");
        drawBridge(1);
        System.out.println("-------------------------------------------------------");
        //n=2
        System.out.println("n=2");
        drawBridge(2);
        System.out.println("-------------------------------------------------------");
        //n=5
        System.out.println("n=5");
        drawBridge(5);
        System.out.println("-------------------------------------------------------");
        //n=21
        System.out.println("n=21");
        drawBridge(21);
        System.out.println("-------------------------------------------------------");
    }
}
