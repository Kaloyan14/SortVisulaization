import java.util.List;

public class Main {
    private static Visual frame;
    private static final int delay = 10;
    public static void main(String[] args) {
         frame = new Visual();
    }

    public static void add(int element, List<Integer> heap) {
        heap.add(element);
        int curr = heap.size() - 1;

        while(curr > 0) {
            int parent = (curr - 1) / 2;
            if(heap.get(parent) < heap.get(curr)) {
                int temp = heap.get(curr);
                heap.set(curr, heap.get(parent));
                heap.set(parent, temp);
                frame.repaint();
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ignored) {}
            } else break;

            curr = parent;
        }
    }

    public static int remove(List<Integer> heap, int size) {
        if(heap.isEmpty()) return 0;

        int root = heap.get(0);
        heap.set(0, heap.get(size - 1));
        heap.set(size - 1, root);
        size--;

        int curr = 0;
        while(curr < size) {
            int l = curr * 2 + 1;
            int r = curr * 2 + 2;

            if(l >= size) break;

            int max = l;
            if(r < size && heap.get(r) > heap.get(l)) max = r;

            if(heap.get(curr) < heap.get(max)) {
                int temp = heap.get(curr);
                heap.set(curr, heap.get(max));
                heap.set(max, temp);
                curr = max;
                frame.repaint();
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ignored) {}
            } else break;
        }

        return root;
    }

    public static void heapSort(List<Integer> arr, List<Integer> heap) {
        int n = arr.size();
        for(int i : arr) {
            add(i, heap);
        }
        for(int i = n - 1; i >= 0; i--) {
            arr.set(i, remove(heap, i + 1));
        }
        frame.repaint();
    }

    public static void mergeSort(List<Integer> list) {
        if (list == null || list.size() <= 1) {
            return;
        }
        mergeSort(list, 0, list.size() - 1);
        frame.repaint();
    }

    private static void mergeSort(List<Integer> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);
            merge(list, left, mid, right);
        }
        frame.repaint();
    }

    private static void merge(List<Integer> list, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        for (int i = 0; i < n1; ++i)
            leftArr[i] = list.get(left + i);
        for (int j = 0; j < n2; ++j)
            rightArr[j] = list.get(mid + 1 + j);

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                list.set(k, leftArr[i]);
                frame.repaint();
                try {
                    Thread.sleep(delay / 10);
                } catch (InterruptedException ignored) {}
                i++;
            } else {
                list.set(k, rightArr[j]);
                frame.repaint();
                try {
                    Thread.sleep(delay / 10);
                } catch (InterruptedException ignored) {}
                j++;
            }
            k++;
        }

        while (i < n1) {
            list.set(k, leftArr[i]);
            frame.repaint();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ignored) {}
            i++;
            k++;
        }

        while (j < n2) {
            list.set(k, rightArr[j]);
            frame.repaint();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ignored) {}
            j++;
            k++;
        }
        frame.repaint();
    }

    public static int partition(List<Integer> arr, int low, int high) {
        int pivot = arr.get(high);
        int i = low;

        for (int j = low; j <= high - 1; j++) {
            if (arr.get(j) < pivot) {
                int temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
                i++;
                frame.repaint();
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ignored) {
                }
            }
        }
        int temp = arr.get(i);
        arr.set(i, arr.get(high));
        arr.set(high, temp);
        frame.repaint();
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ignored) {
        }

        return i;
    }

    public static void quickSort(List<Integer> arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
        frame.repaint();
    }

    public static void printArray(List<Integer> array) {
        for (int element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static void bubbleSort(List<Integer> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);

                    frame.repaint();
                    try {
                        Thread.sleep(delay / 10);
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        }
        frame.repaint();
    }

    public static void insertionSort(List<Integer> arr) {
        int n = arr.size();
        for (int i = 1; i < n; i++) {
            int key = arr.get(i);
            int j = i - 1;
            while (j >= 0 && arr.get(j) > key) {
                arr.set(j + 1, arr.get(j));
                j--;

                frame.repaint();
                try {
                    Thread.sleep(delay / 10);
                } catch (InterruptedException ignored) {
                }
            }
            arr.set(j + 1, key);
        }
        frame.repaint();
    }

    public static void selectionSort(List<Integer> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr.get(j) < arr.get(minIndex)) {
                    minIndex = j;
                }
            }
            int tmp = arr.get(i);
            arr.set(i, arr.get(minIndex));
            arr.set(minIndex, tmp);
            frame.repaint();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ignored) {
            }
        }
        frame.repaint();
    }

}

