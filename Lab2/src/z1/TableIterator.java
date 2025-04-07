package z1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TableIterator<E> implements Iterator<E> {

    private E[] tab;

    int index = 0;

    public TableIterator(E[] tab) {
        if (tab == null) {
            throw new NullPointerException("Given Table is null");
        }
        this.tab = tab;
    }

    @Override
    public boolean hasNext() {
        return index < tab.length;
    }

    @Override
    public E next() {
        if (!hasNext()) throw new NoSuchElementException();
        int returnIndex = index;
        index++;
        return tab[returnIndex];
    }

}