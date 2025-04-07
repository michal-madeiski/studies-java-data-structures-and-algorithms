package z1;

public class Knot<E>{
    //ZMIENIC NA INTERFACE!!!
    private E value;
    private Knot next;

    public Knot(E value, Knot next) {
        this.value = value;
        this.next = next;
    }

    public Knot(E value) {
        this.value = value;
        this.next = null;
    }

    public Knot() {
        this.value = null;
        this.next = null;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public Knot getNext() {
        return next;
    }

    public void setNext(Knot next) {
        this.next = next;
    }

    public String toString() {
        return "" + value;
    }
}
