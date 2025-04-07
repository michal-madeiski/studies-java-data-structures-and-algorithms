import java.util.Comparator;
import java.util.List;

import core.AbstractSwappingSortingAlgorithm;

public class InsertSort<T> extends AbstractSwappingSortingAlgorithm<T> {

    public InsertSort(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public List<T> sort(List<T> list) {
        int size = list.size();
        for (int i = 1; i < size; i++) {
            int pos = indexBinarySearch(list, 0, i - 1, list.get(i));
            if (pos != -1) {
                moveElements(list, pos, i);
            }
        }
        return list;
    }

    public int indexBinarySearch(List<T> list, int start, int end, T val) {
        int mid;
        while (start <= end) {
            mid = (start + end) / 2;
            int comp = compare(list.get(mid), val);
            if (comp == 0) {
                return mid;
            }
            if (comp < 0) {
                start = mid + 1;
            }
            if (comp > 0) {
                end = mid - 1;
            }
        }
        return start;
    }

    public void moveElements(List<T> list, int start, int end) {
        for (int i = end; i > start ; i--) {
            swap(list, i, i - 1);
        }
    }
}
