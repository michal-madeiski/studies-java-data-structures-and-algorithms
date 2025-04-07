import core.AbstractSwappingSortingAlgorithm;

import java.util.*;

public class QuickSort<T> extends AbstractSwappingSortingAlgorithm<T> {

    private int pivotVersion;

    public QuickSort(Comparator<? super T> comparator, int pivotVersion) {
        super(comparator);
        this.pivotVersion = pivotVersion;
    }

    @Override
    public List<T> sort(List<T> list) {
        int size = list.size();
        if (size <= 1) {
            return list;
        } else {
            quickSort(list, 0, list.size() - 1);
            return list;
        }
    }

    public int partition(List<T> list, int start, int end, int pivot) {
        T pivotVal = list.get(pivot);
        swap(list, pivot, end);
        int pom= start;
        Iterator<T> iter = list.listIterator(start);
        for(int i = start; i < end; i++) {
            if(compare(iter.next(), pivotVal) < 0) {
                swap(list, i, pom);
                pom++;
            }
        }
        swap(list, end, pom);
        return pom;
    }

    public void quickSort(List<T> list, int start, int end) {
        if(start < end) {
            int pivot;
            if (pivotVersion == 1) {
                pivot = findPivotStart(list, start, end);
            } else {
                pivot = findPivotRandom(list, start, end);
            }
            int partition = partition(list, start, end, pivot);
            quickSort(list, start, partition - 1);
            quickSort(list, partition + 1, end);
        }
    }

    public int findPivotStart(List<T> list, int start, int end) {
        return start;
    }
    public int findPivotRandom(List<T> list, int start, int end) {
        Random random = new Random();
        return (random.nextInt(end - start + 1) + start);
    }
}
