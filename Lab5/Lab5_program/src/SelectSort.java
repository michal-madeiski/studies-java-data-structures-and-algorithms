import java.util.Comparator;
import java.util.List;

import core.AbstractSwappingSortingAlgorithm;

public class SelectSort<T> extends AbstractSwappingSortingAlgorithm<T> {

    public SelectSort(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public List<T> sort(List<T> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int min = findMin(list, i , size - i - 1);
            if (i != min) {
                swap(list, i, min);
            }
            int max = findMax(list, size - i - 1, i + 1);
            if (size - i - 1 != max) {
                swap(list, size - i - 1, max);
            }
//            swap(list, i, findMin(list, i, size - i - 1));
//            swap(list, size - i - 1, findMax(list, size - i - 1, i + 1));
        }
        return list;
    }

    public int findMin(List<T> list, int start, int end) {
        int minIndex = start;
        T min = list.get(start);
        for (int i = start; i <= end; i++) {
            if (compare(min, list.get(i)) > 0) {
                min = list.get(i);
                minIndex = i;
            }
        }
        return minIndex;
    }
    public int findMax(List<T> list, int start, int end) {
        int maxIndex = start;
        T max = list.get(start);
        for (int i = start; i >= end; i--) {
            if (compare(max, list.get(i)) < 0) {
                max = list.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
