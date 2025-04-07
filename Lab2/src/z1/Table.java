package z1;

import java.util.Iterator;

public class Table<E> implements Iterable<E> {

    private E[] tab;

    public Table(E[] tab) {
        if (tab == null) {
            throw new NullPointerException("Given Table is null");
        }
        this.tab = tab;
    }

    public int size() {
        return tab.length;
    }

    public E getElement(int i) {
        return tab[i];
    }

    @Override
    public Iterator<E> iterator() {
        return new TableIterator<>(tab);
    }

}
