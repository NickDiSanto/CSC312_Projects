import java.util.Vector;

public class HeapSort {

    public static void main(String[] args) {
        Vector<Integer> numbers = new Vector<>();
        numbers.addElement(16);
        numbers.addElement(12);
        numbers.addElement(11);
        numbers.addElement(9);
        numbers.addElement(8);
        numbers.addElement(5);
        numbers.addElement(4);
        numbers.addElement(3);
        numbers.addElement(2);
        numbers.addElement(1);

        sort(numbers);

        for (int i = 0; i < numbers.size() - 1; i++)
            System.out.print(numbers.elementAt(i) + ", ");
        System.out.print(numbers.elementAt(numbers.size() - 1));
    }

    public static void sort(Vector<Integer> numbers) {
        int heapSize = numbers.size();
        for (int i = numbers.size() / 2 - 1; i >= 0; i--)
            heapify(numbers, heapSize, i);
        for (int i = heapSize - 1; i >= 0; i--) {
            int temp = numbers.elementAt(0);
            numbers.set(0, numbers.elementAt(i));
            numbers.set(i, temp);
            heapify(numbers, i, 0);
        }
    }

    private static void heapify(Vector<Integer> numbers, int heapSize, int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < heapSize && numbers.elementAt(left) > numbers.elementAt(largest))
            largest = left;
        if (right < heapSize && numbers.elementAt(right) > numbers.elementAt(largest))
            largest = right;
        if (largest != i) {
            int temp = numbers.elementAt(i);
            numbers.set(i, numbers.elementAt(largest));
            numbers.set(largest, temp);

            heapify(numbers, heapSize, largest);
        }
    }
}
