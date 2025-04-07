package z1;

public class KnotArray<E> extends Knot<E>{
    private Object[] tab;
    private Knot next;
    int c;

    public KnotArray(int c, Knot start) {
        this.c = c;
        this.tab = new Object[c];

        Knot active = start;

        this.tab[0] = active.getValue();

        for (int i = 1; i < c; i++) {
            if (active.getNext() != null) {
                active = active.getNext();
                this.tab[i] = active.getValue();
            }
        }

        this.next = active.getNext();
    }

    public Object[] getTab() {
        return tab;
    }

    public void setTab(Object[] tab) {
        this.tab = tab;
    }

    @Override
    public Knot getNext() {
        return next;
    }

    @Override
    public void setNext(Knot next) {
        this.next = next;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int knotArraySize() {
        int counter = 0;
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] != null) {
                counter++;
            }
        }
        return counter;
    }

    public String toString() {
        String s = "[ ";
        for (int i = 0; i < knotArraySize(); i++) {
            s += (tab[i] + " ");
        }
        s += "]";
        return s;
    }

    public void decompress() {
        Knot newElem = new Knot(getTab()[knotArraySize() - 1], getNext());
        for (int i = knotArraySize() - 2; i >= 0; i--) {
            Knot newElem2 = new Knot();
        }
    }
}
