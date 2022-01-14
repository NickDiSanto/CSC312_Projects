import java.util.Vector;

public class MaxPriorityQueue {
    public static void main(String[] args) {
        Vector<Integer> heap = new Vector<>();
        maxHeapInsert(heap, 18);
        maxHeapInsert(heap, 7);
        maxHeapInsert(heap, 27);
        maxHeapInsert(heap, 92);
        maxHeapInsert(heap, 4);
        maxHeapInsert(heap, 5);
        maxHeapInsert(heap, 16);
        maxHeapInsert(heap, 13);
        maxHeapInsert(heap, 100);
        maxHeapInsert(heap, 1);
        maxHeapInsert(heap, 12);
        maxHeapInsert(heap, 35);
        maxHeapInsert(heap, 43);
        maxHeapInsert(heap, 41);
        maxHeapInsert(heap, 29);
        maxHeapInsert(heap, 33);
        maxHeapInsert(heap, 98);
        maxHeapInsert(heap, 99);
        maxHeapInsert(heap, 67);
        maxHeapInsert(heap, 76);

        System.out.println(heap);

        heapIncreaseKey(heap, 2, 12);
        heapIncreaseKey(heap, 3, 25);
        heapIncreaseKey(heap, 1, 200);
        heapIncreaseKey(heap, 8, 15);
        heapIncreaseKey(heap, 0, 201);
        heapIncreaseKey(heap, 19, 77);

        System.out.println(heap);

        while (heap.size() > 0)
            System.out.print(heapExtractMax(heap) + " ");
    }

    private static void maxHeapInsert(Vector<Integer> heap, int key) {
        heap.addElement(Integer.MIN_VALUE);
        heapIncreaseKey(heap, heap.size() - 1, key);
    }

    private static void heapIncreaseKey(Vector<Integer> heap, int i, int key) {
        if (key < heap.elementAt(i))
            System.out.println("New key is smaller than current key");
        else {
            heap.set(i, key);
            while (i >= 1 && heap.elementAt(parent(i)) < heap.elementAt(i)) {
                int temp = heap.elementAt(i);
                heap.set(i, heap.elementAt(parent(i)));
                heap.set(parent(i), temp);
                i = parent(i);
            }
        }
    }

    private static int heapExtractMax(Vector<Integer> heap) {
        if (heap.size() < 1)
            System.out.println("Heap underflow");

        int max = heap.elementAt(0);
        heap.set(0, heap.elementAt(heap.size() - 1));
        heap.remove(heap.size() - 1);

        maxHeapify(heap, 0);
        return max;
    }

    private static void maxHeapify(Vector<Integer> heap, int key) {
        int left = left(key);
        int right = right(key);
        int largest;

        if (left < heap.size() && heap.elementAt(left) > heap.elementAt(key))
            largest = left;
        else
            largest = key;
        if (right < heap.size() && heap.elementAt(right) > heap.elementAt(largest))
            largest = right;
        if (largest != key) {
            int temp = heap.elementAt(key);
            heap.set(key, heap.elementAt(largest));
            heap.set(largest, temp);

            maxHeapify(heap, largest);
        }
    }

    private static int parent(int i) {
        return (i / 2);
    }

    private static int left(int i) {
        return (2 * i);
    }

    private static int right(int i) {
        return (2 * i + 1);
    }
}
